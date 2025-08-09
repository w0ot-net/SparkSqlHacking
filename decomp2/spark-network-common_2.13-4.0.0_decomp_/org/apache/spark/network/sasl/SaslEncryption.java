package org.apache.spark.network.sasl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import org.apache.spark.network.util.AbstractFileRegion;
import org.apache.spark.network.util.ByteArrayWritableChannel;
import org.apache.spark.network.util.NettyUtils;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

class SaslEncryption {
   @VisibleForTesting
   static final String ENCRYPTION_HANDLER_NAME = "saslEncryption";

   static void addToChannel(Channel channel, SaslEncryptionBackend backend, int maxOutboundBlockSize) {
      channel.pipeline().addFirst("saslEncryption", new EncryptionHandler(backend, maxOutboundBlockSize)).addFirst("saslDecryption", new DecryptionHandler(backend)).addFirst("saslFrameDecoder", NettyUtils.createFrameDecoder());
   }

   private static class EncryptionHandler extends ChannelOutboundHandlerAdapter {
      private final int maxOutboundBlockSize;
      private final SaslEncryptionBackend backend;

      EncryptionHandler(SaslEncryptionBackend backend, int maxOutboundBlockSize) {
         this.backend = backend;
         this.maxOutboundBlockSize = maxOutboundBlockSize;
      }

      public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
         ctx.write(new EncryptedMessage(this.backend, msg, this.maxOutboundBlockSize), promise);
      }

      public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
         try {
            this.backend.dispose();
         } finally {
            super.handlerRemoved(ctx);
         }

      }
   }

   private static class DecryptionHandler extends MessageToMessageDecoder {
      private final SaslEncryptionBackend backend;

      DecryptionHandler(SaslEncryptionBackend backend) {
         this.backend = backend;
      }

      protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List out) throws Exception {
         int length = msg.readableBytes();
         byte[] data;
         int offset;
         if (msg.hasArray()) {
            data = msg.array();
            offset = msg.arrayOffset();
            msg.skipBytes(length);
         } else {
            data = new byte[length];
            msg.readBytes(data);
            offset = 0;
         }

         out.add(Unpooled.wrappedBuffer(this.backend.unwrap(data, offset, length)));
      }
   }

   @VisibleForTesting
   static class EncryptedMessage extends AbstractFileRegion {
      private final SaslEncryptionBackend backend;
      private final boolean isByteBuf;
      private final ByteBuf buf;
      private final FileRegion region;
      private final int maxOutboundBlockSize;
      private ByteArrayWritableChannel byteChannel;
      private ByteBuf currentHeader;
      private ByteBuffer currentChunk;
      private long currentChunkSize;
      private long currentReportedBytes;
      private long unencryptedChunkSize;
      private long transferred;

      EncryptedMessage(SaslEncryptionBackend backend, Object msg, int maxOutboundBlockSize) {
         Preconditions.checkArgument(msg instanceof ByteBuf || msg instanceof FileRegion, "Unrecognized message type: %s", (Object)msg.getClass().getName());
         this.backend = backend;
         this.isByteBuf = msg instanceof ByteBuf;
         this.buf = this.isByteBuf ? (ByteBuf)msg : null;
         this.region = this.isByteBuf ? null : (FileRegion)msg;
         this.maxOutboundBlockSize = maxOutboundBlockSize;
      }

      public long count() {
         return this.isByteBuf ? (long)this.buf.readableBytes() : this.region.count();
      }

      public long position() {
         return 0L;
      }

      public long transferred() {
         return this.transferred;
      }

      public EncryptedMessage touch(Object o) {
         super.touch(o);
         if (this.buf != null) {
            this.buf.touch(o);
         }

         if (this.region != null) {
            this.region.touch(o);
         }

         return this;
      }

      public EncryptedMessage retain(int increment) {
         super.retain(increment);
         if (this.buf != null) {
            this.buf.retain(increment);
         }

         if (this.region != null) {
            this.region.retain(increment);
         }

         return this;
      }

      public boolean release(int decrement) {
         if (this.region != null) {
            this.region.release(decrement);
         }

         if (this.buf != null) {
            this.buf.release(decrement);
         }

         return super.release(decrement);
      }

      public long transferTo(WritableByteChannel target, long position) throws IOException {
         Preconditions.checkArgument(position == this.transferred(), "Invalid position.");
         long reportedWritten = 0L;
         long actuallyWritten = 0L;

         do {
            if (this.currentChunk == null) {
               this.nextChunk();
            }

            if (this.currentHeader.readableBytes() > 0) {
               int bytesWritten = target.write(this.currentHeader.nioBuffer());
               this.currentHeader.skipBytes(bytesWritten);
               actuallyWritten += (long)bytesWritten;
               if (this.currentHeader.readableBytes() > 0) {
                  break;
               }
            }

            actuallyWritten += (long)target.write(this.currentChunk);
            if (!this.currentChunk.hasRemaining()) {
               long chunkBytesRemaining = this.unencryptedChunkSize - this.currentReportedBytes;
               reportedWritten += chunkBytesRemaining;
               this.transferred += chunkBytesRemaining;
               this.currentHeader.release();
               this.currentHeader = null;
               this.currentChunk = null;
               this.currentChunkSize = 0L;
               this.currentReportedBytes = 0L;
            }
         } while(this.currentChunk == null && this.transferred() + reportedWritten < this.count());

         if (reportedWritten != 0L) {
            return reportedWritten;
         } else if (actuallyWritten > 0L && this.currentReportedBytes < this.currentChunkSize - 1L) {
            ++this.transferred;
            ++this.currentReportedBytes;
            return 1L;
         } else {
            return 0L;
         }
      }

      private void nextChunk() throws IOException {
         if (this.byteChannel == null) {
            this.byteChannel = new ByteArrayWritableChannel(this.maxOutboundBlockSize);
         }

         this.byteChannel.reset();
         if (this.isByteBuf) {
            int copied = this.byteChannel.write(this.buf.nioBuffer());
            this.buf.skipBytes(copied);
         } else {
            this.region.transferTo(this.byteChannel, this.region.transferred());
         }

         byte[] encrypted = this.backend.wrap(this.byteChannel.getData(), 0, this.byteChannel.length());
         this.currentChunk = ByteBuffer.wrap(encrypted);
         this.currentChunkSize = (long)encrypted.length;
         this.currentHeader = Unpooled.copyLong(8L + this.currentChunkSize);
         this.unencryptedChunkSize = (long)this.byteChannel.length();
      }

      protected void deallocate() {
         if (this.currentHeader != null) {
            this.currentHeader.release();
         }

         if (this.buf != null) {
            this.buf.release();
         }

         if (this.region != null) {
            this.region.release();
         }

      }
   }
}
