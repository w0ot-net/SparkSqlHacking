package org.apache.spark.network.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;
import org.apache.spark.network.util.AbstractFileRegion;
import org.apache.spark.network.util.ByteArrayReadableChannel;
import org.apache.spark.network.util.ByteArrayWritableChannel;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

public class CtrTransportCipher implements TransportCipher {
   @VisibleForTesting
   static final String ENCRYPTION_HANDLER_NAME = "CtrTransportEncryption";
   private static final String DECRYPTION_HANDLER_NAME = "CtrTransportDecryption";
   @VisibleForTesting
   static final int STREAM_BUFFER_SIZE = 32768;
   private final Properties conf;
   private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";
   private final SecretKeySpec key;
   private final byte[] inIv;
   private final byte[] outIv;

   public CtrTransportCipher(Properties conf, SecretKeySpec key, byte[] inIv, byte[] outIv) {
      this.conf = conf;
      this.key = key;
      this.inIv = inIv;
      this.outIv = outIv;
   }

   @VisibleForTesting
   public String getKeyId() throws GeneralSecurityException {
      return TransportCipherUtil.getKeyId(this.key);
   }

   @VisibleForTesting
   SecretKeySpec getKey() {
      return this.key;
   }

   public byte[] getInputIv() {
      return this.inIv;
   }

   public byte[] getOutputIv() {
      return this.outIv;
   }

   @VisibleForTesting
   CryptoOutputStream createOutputStream(WritableByteChannel ch) throws IOException {
      return new CryptoOutputStream("AES/CTR/NoPadding", this.conf, ch, this.key, new IvParameterSpec(this.outIv));
   }

   @VisibleForTesting
   CryptoInputStream createInputStream(ReadableByteChannel ch) throws IOException {
      return new CryptoInputStream("AES/CTR/NoPadding", this.conf, ch, this.key, new IvParameterSpec(this.inIv));
   }

   public void addToChannel(Channel ch) throws IOException {
      ch.pipeline().addFirst("CtrTransportEncryption", new EncryptionHandler(this)).addFirst("CtrTransportDecryption", new DecryptionHandler(this));
   }

   @VisibleForTesting
   static class EncryptionHandler extends ChannelOutboundHandlerAdapter {
      private final ByteArrayWritableChannel byteEncChannel = new ByteArrayWritableChannel(32768);
      private final CryptoOutputStream cos;
      private final ByteArrayWritableChannel byteRawChannel;
      private boolean isCipherValid;

      EncryptionHandler(CtrTransportCipher cipher) throws IOException {
         this.cos = cipher.createOutputStream(this.byteEncChannel);
         this.byteRawChannel = new ByteArrayWritableChannel(32768);
         this.isCipherValid = true;
      }

      public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
         ctx.write(this.createEncryptedMessage(msg), promise);
      }

      @VisibleForTesting
      EncryptedMessage createEncryptedMessage(Object msg) {
         return new EncryptedMessage(this, this.cos, msg, this.byteEncChannel, this.byteRawChannel);
      }

      public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
         try {
            if (this.isCipherValid) {
               this.cos.close();
            }
         } finally {
            super.close(ctx, promise);
         }

      }

      void reportError() {
         this.isCipherValid = false;
      }

      boolean isCipherValid() {
         return this.isCipherValid;
      }
   }

   private static class DecryptionHandler extends ChannelInboundHandlerAdapter {
      private final CryptoInputStream cis;
      private final ByteArrayReadableChannel byteChannel = new ByteArrayReadableChannel();
      private boolean isCipherValid;

      DecryptionHandler(CtrTransportCipher cipher) throws IOException {
         this.cis = cipher.createInputStream(this.byteChannel);
         this.isCipherValid = true;
      }

      public void channelRead(ChannelHandlerContext ctx, Object data) throws Exception {
         ByteBuf buffer = (ByteBuf)data;

         try {
            if (!this.isCipherValid) {
               throw new IOException("Cipher is in invalid state.");
            }

            byte[] decryptedData = new byte[buffer.readableBytes()];
            this.byteChannel.feedData(buffer);
            int offset = 0;

            while(offset < decryptedData.length) {
               try {
                  offset += this.cis.read(decryptedData, offset, decryptedData.length - offset);
               } catch (InternalError ie) {
                  this.isCipherValid = false;
                  throw ie;
               }
            }

            ctx.fireChannelRead(Unpooled.wrappedBuffer(decryptedData, 0, decryptedData.length));
         } finally {
            buffer.release();
         }

      }

      public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
         try {
            if (this.isCipherValid) {
               this.cis.close();
            }
         } finally {
            super.handlerRemoved(ctx);
         }

      }
   }

   @VisibleForTesting
   static class EncryptedMessage extends AbstractFileRegion {
      private final boolean isByteBuf;
      private final ByteBuf buf;
      private final FileRegion region;
      private final CryptoOutputStream cos;
      private final EncryptionHandler handler;
      private final long count;
      private long transferred;
      private final ByteArrayWritableChannel byteEncChannel;
      private final ByteArrayWritableChannel byteRawChannel;
      private ByteBuffer currentEncrypted;

      EncryptedMessage(EncryptionHandler handler, CryptoOutputStream cos, Object msg, ByteArrayWritableChannel byteEncChannel, ByteArrayWritableChannel byteRawChannel) {
         Preconditions.checkArgument(msg instanceof ByteBuf || msg instanceof FileRegion, "Unrecognized message type: %s", (Object)msg.getClass().getName());
         this.handler = handler;
         this.isByteBuf = msg instanceof ByteBuf;
         this.buf = this.isByteBuf ? (ByteBuf)msg : null;
         this.region = this.isByteBuf ? null : (FileRegion)msg;
         this.transferred = 0L;
         this.cos = cos;
         this.byteEncChannel = byteEncChannel;
         this.byteRawChannel = byteRawChannel;
         this.count = this.isByteBuf ? (long)this.buf.readableBytes() : this.region.count();
      }

      public long count() {
         return this.count;
      }

      public long position() {
         return 0L;
      }

      public long transferred() {
         return this.transferred;
      }

      public EncryptedMessage touch(Object o) {
         super.touch(o);
         if (this.region != null) {
            this.region.touch(o);
         }

         if (this.buf != null) {
            this.buf.touch(o);
         }

         return this;
      }

      public EncryptedMessage retain(int increment) {
         super.retain(increment);
         if (this.region != null) {
            this.region.retain(increment);
         }

         if (this.buf != null) {
            this.buf.retain(increment);
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
         if (this.transferred == this.count) {
            return 0L;
         } else {
            long totalBytesWritten = 0L;

            do {
               if (this.currentEncrypted == null) {
                  this.encryptMore();
               }

               long remaining = (long)this.currentEncrypted.remaining();
               if (remaining == 0L) {
                  this.currentEncrypted = null;
                  this.byteEncChannel.reset();
                  return totalBytesWritten;
               }

               long bytesWritten = (long)target.write(this.currentEncrypted);
               totalBytesWritten += bytesWritten;
               this.transferred += bytesWritten;
               if (bytesWritten < remaining) {
                  break;
               }

               this.currentEncrypted = null;
               this.byteEncChannel.reset();
            } while(this.transferred < this.count);

            return totalBytesWritten;
         }
      }

      private void encryptMore() throws IOException {
         if (!this.handler.isCipherValid()) {
            throw new IOException("Cipher is in invalid state.");
         } else {
            this.byteRawChannel.reset();
            if (this.isByteBuf) {
               int copied = this.byteRawChannel.write(this.buf.nioBuffer());
               this.buf.skipBytes(copied);
            } else {
               this.region.transferTo(this.byteRawChannel, this.region.transferred());
            }

            try {
               this.cos.write(this.byteRawChannel.getData(), 0, this.byteRawChannel.length());
               this.cos.flush();
            } catch (InternalError ie) {
               this.handler.reportError();
               throw ie;
            }

            this.currentEncrypted = ByteBuffer.wrap(this.byteEncChannel.getData(), 0, this.byteEncChannel.length());
         }
      }

      protected void deallocate() {
         this.byteRawChannel.reset();
         this.byteEncChannel.reset();
         if (this.region != null) {
            this.region.release();
         }

         if (this.buf != null) {
            this.buf.release();
         }

      }
   }
}
