package io.netty.handler.codec.compression;

import com.github.luben.zstd.ZstdInputStreamNoFinalizer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class ZstdDecoder extends ByteToMessageDecoder {
   private final MutableByteBufInputStream inputStream;
   private ZstdInputStreamNoFinalizer zstdIs;
   private State currentState;

   public ZstdDecoder() {
      try {
         Zstd.ensureAvailability();
      } catch (Throwable throwable) {
         throw new ExceptionInInitializerError(throwable);
      }

      this.inputStream = new MutableByteBufInputStream();
      this.currentState = ZstdDecoder.State.DECOMPRESS_DATA;
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
      try {
         if (this.currentState == ZstdDecoder.State.CORRUPTED) {
            in.skipBytes(in.readableBytes());
            return;
         }

         int compressedLength = in.readableBytes();
         this.inputStream.current = in;
         ByteBuf outBuffer = null;

         int w;
         try {
            do {
               if (outBuffer == null) {
                  outBuffer = ctx.alloc().heapBuffer(compressedLength * 2);
               }

               do {
                  w = outBuffer.writeBytes(this.zstdIs, outBuffer.writableBytes());
               } while(w != -1 && outBuffer.isWritable());

               if (outBuffer.isReadable()) {
                  out.add(outBuffer);
                  outBuffer = null;
               }
            } while(w != -1);
         } finally {
            if (outBuffer != null) {
               outBuffer.release();
            }

         }
      } catch (Exception e) {
         this.currentState = ZstdDecoder.State.CORRUPTED;
         throw new DecompressionException(e);
      } finally {
         this.inputStream.current = null;
      }

   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      super.handlerAdded(ctx);
      this.zstdIs = new ZstdInputStreamNoFinalizer(this.inputStream);
      this.zstdIs.setContinuous(true);
   }

   protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
      try {
         closeSilently(this.zstdIs);
      } finally {
         super.handlerRemoved0(ctx);
      }

   }

   private static void closeSilently(Closeable closeable) {
      if (closeable != null) {
         try {
            closeable.close();
         } catch (IOException var2) {
         }
      }

   }

   private static enum State {
      DECOMPRESS_DATA,
      CORRUPTED;
   }

   private static final class MutableByteBufInputStream extends InputStream {
      ByteBuf current;

      private MutableByteBufInputStream() {
      }

      public int read() {
         return this.current != null && this.current.isReadable() ? this.current.readByte() & 255 : -1;
      }

      public int read(byte[] b, int off, int len) {
         int available = this.available();
         if (available == 0) {
            return -1;
         } else {
            len = Math.min(available, len);
            this.current.readBytes(b, off, len);
            return len;
         }
      }

      public int available() {
         return this.current == null ? 0 : this.current.readableBytes();
      }
   }
}
