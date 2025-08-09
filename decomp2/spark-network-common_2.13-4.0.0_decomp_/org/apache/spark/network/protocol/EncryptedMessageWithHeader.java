package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;
import io.netty.handler.stream.ChunkedStream;
import java.io.EOFException;
import java.io.InputStream;
import javax.annotation.Nullable;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.sparkproject.guava.base.Preconditions;

public class EncryptedMessageWithHeader implements ChunkedInput {
   @Nullable
   private final ManagedBuffer managedBuffer;
   private final ByteBuf header;
   private final int headerLength;
   private final Object body;
   private final long bodyLength;
   private long totalBytesTransferred;

   public EncryptedMessageWithHeader(@Nullable ManagedBuffer managedBuffer, ByteBuf header, Object body, long bodyLength) {
      Preconditions.checkArgument(body instanceof InputStream || body instanceof ChunkedStream, "Body must be an InputStream or a ChunkedStream.");
      this.managedBuffer = managedBuffer;
      this.header = header;
      this.headerLength = header.readableBytes();
      this.body = body;
      this.bodyLength = bodyLength;
      this.totalBytesTransferred = 0L;
   }

   public ByteBuf readChunk(ChannelHandlerContext ctx) throws Exception {
      return this.readChunk(ctx.alloc());
   }

   public ByteBuf readChunk(ByteBufAllocator allocator) throws Exception {
      if (this.isEndOfInput()) {
         return null;
      } else if (this.totalBytesTransferred < (long)this.headerLength) {
         this.totalBytesTransferred += (long)this.headerLength;
         return this.header.retain();
      } else {
         Object available = this.body;
         if (available instanceof InputStream) {
            InputStream stream = (InputStream)available;
            int available = stream.available();
            if (available <= 0) {
               available = (int)(this.length() - this.totalBytesTransferred);
            } else {
               available = (int)Math.min((long)available, this.length() - this.totalBytesTransferred);
            }

            ByteBuf buffer = allocator.buffer(available);
            int toRead = Math.min(available, buffer.writableBytes());
            int read = buffer.writeBytes(stream, toRead);
            if (read >= 0) {
               this.totalBytesTransferred += (long)read;
               return buffer;
            } else {
               throw new EOFException("Unable to read bytes from InputStream");
            }
         } else {
            available = this.body;
            if (available instanceof ChunkedStream) {
               ChunkedStream stream = (ChunkedStream)available;
               long old = stream.transferredBytes();
               ByteBuf buffer = stream.readChunk(allocator);
               long read = stream.transferredBytes() - old;
               if (read >= 0L) {
                  this.totalBytesTransferred += read;

                  assert this.totalBytesTransferred <= this.length();

                  return buffer;
               } else {
                  throw new EOFException("Unable to read bytes from ChunkedStream");
               }
            } else {
               return null;
            }
         }
      }
   }

   public long length() {
      return (long)this.headerLength + this.bodyLength;
   }

   public long progress() {
      return this.totalBytesTransferred;
   }

   public boolean isEndOfInput() throws Exception {
      return (long)this.headerLength + this.bodyLength == this.totalBytesTransferred;
   }

   public void close() throws Exception {
      this.header.release();
      if (this.managedBuffer != null) {
         this.managedBuffer.release();
      }

      Object var3 = this.body;
      if (var3 instanceof InputStream stream) {
         stream.close();
      } else {
         var3 = this.body;
         if (var3 instanceof ChunkedStream stream) {
            stream.close();
         }
      }

   }
}
