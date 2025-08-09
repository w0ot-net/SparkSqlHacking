package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.FileRegion;
import io.netty.util.ReferenceCountUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import javax.annotation.Nullable;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.util.AbstractFileRegion;
import org.sparkproject.guava.base.Preconditions;

public class MessageWithHeader extends AbstractFileRegion {
   @Nullable
   private final ManagedBuffer managedBuffer;
   private final ByteBuf header;
   private final int headerLength;
   private final Object body;
   private final long bodyLength;
   private long totalBytesTransferred;
   private static final int NIO_BUFFER_LIMIT = 262144;

   MessageWithHeader(@Nullable ManagedBuffer managedBuffer, ByteBuf header, Object body, long bodyLength) {
      Preconditions.checkArgument(body instanceof ByteBuf || body instanceof FileRegion, "Body must be a ByteBuf or a FileRegion.");
      this.managedBuffer = managedBuffer;
      this.header = header;
      this.headerLength = header.readableBytes();
      this.body = body;
      this.bodyLength = bodyLength;
   }

   public long count() {
      return (long)this.headerLength + this.bodyLength;
   }

   public long position() {
      return 0L;
   }

   public long transferred() {
      return this.totalBytesTransferred;
   }

   public long transferTo(WritableByteChannel target, long position) throws IOException {
      Preconditions.checkArgument(position == this.totalBytesTransferred, "Invalid position.");
      long writtenHeader = 0L;
      if (this.header.readableBytes() > 0) {
         writtenHeader = (long)this.copyByteBuf(this.header, target);
         this.totalBytesTransferred += writtenHeader;
         if (this.header.readableBytes() > 0) {
            return writtenHeader;
         }
      }

      long writtenBody = 0L;
      Object var10 = this.body;
      if (var10 instanceof FileRegion fileRegion) {
         writtenBody = fileRegion.transferTo(target, this.totalBytesTransferred - (long)this.headerLength);
      } else {
         var10 = this.body;
         if (var10 instanceof ByteBuf byteBuf) {
            writtenBody = (long)this.copyByteBuf(byteBuf, target);
         }
      }

      this.totalBytesTransferred += writtenBody;
      return writtenHeader + writtenBody;
   }

   protected void deallocate() {
      this.header.release();
      ReferenceCountUtil.release(this.body);
      if (this.managedBuffer != null) {
         this.managedBuffer.release();
      }

   }

   private int copyByteBuf(ByteBuf buf, WritableByteChannel target) throws IOException {
      int length = Math.min(buf.readableBytes(), 262144);
      int written = 0;
      if (buf.nioBufferCount() == 1) {
         ByteBuffer buffer = buf.nioBuffer(buf.readerIndex(), length);
         written = target.write(buffer);
      } else {
         ByteBuffer[] buffers = buf.nioBuffers(buf.readerIndex(), length);

         for(ByteBuffer buffer : buffers) {
            int remaining = buffer.remaining();
            int w = target.write(buffer);
            written += w;
            if (w < remaining) {
               break;
            }
         }
      }

      buf.skipBytes(written);
      return written;
   }

   public MessageWithHeader touch(Object o) {
      super.touch(o);
      this.header.touch(o);
      ReferenceCountUtil.touch(this.body, o);
      return this;
   }

   public MessageWithHeader retain(int increment) {
      super.retain(increment);
      this.header.retain(increment);
      ReferenceCountUtil.retain(this.body, increment);
      if (this.managedBuffer != null) {
         for(int i = 0; i < increment; ++i) {
            this.managedBuffer.retain();
         }
      }

      return this;
   }

   public boolean release(int decrement) {
      this.header.release(decrement);
      ReferenceCountUtil.release(this.body, decrement);
      if (this.managedBuffer != null) {
         for(int i = 0; i < decrement; ++i) {
            this.managedBuffer.release();
         }
      }

      return super.release(decrement);
   }

   public String toString() {
      return "MessageWithHeader [headerLength: " + this.headerLength + ", bodyLength: " + this.bodyLength + "]";
   }
}
