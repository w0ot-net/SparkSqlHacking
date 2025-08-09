package org.apache.spark.network.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.LinkedList;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

public class TransportFrameDecoder extends ChannelInboundHandlerAdapter {
   public static final String HANDLER_NAME = "frameDecoder";
   private static final int LENGTH_SIZE = 8;
   private static final int MAX_FRAME_SIZE = Integer.MAX_VALUE;
   private static final int UNKNOWN_FRAME_SIZE = -1;
   private static final long CONSOLIDATE_THRESHOLD = 20971520L;
   private final LinkedList buffers;
   private final ByteBuf frameLenBuf;
   private final long consolidateThreshold;
   private CompositeByteBuf frameBuf;
   private long consolidatedFrameBufSize;
   private int consolidatedNumComponents;
   private long totalSize;
   private long nextFrameSize;
   private int frameRemainingBytes;
   private volatile Interceptor interceptor;

   public TransportFrameDecoder() {
      this(20971520L);
   }

   @VisibleForTesting
   TransportFrameDecoder(long consolidateThreshold) {
      this.buffers = new LinkedList();
      this.frameLenBuf = Unpooled.buffer(8, 8);
      this.frameBuf = null;
      this.consolidatedFrameBufSize = 0L;
      this.consolidatedNumComponents = 0;
      this.totalSize = 0L;
      this.nextFrameSize = -1L;
      this.frameRemainingBytes = -1;
      this.consolidateThreshold = consolidateThreshold;
   }

   public void channelRead(ChannelHandlerContext ctx, Object data) throws Exception {
      ByteBuf in = (ByteBuf)data;
      this.buffers.add(in);
      this.totalSize += (long)in.readableBytes();

      while(!this.buffers.isEmpty()) {
         if (this.interceptor != null) {
            ByteBuf first = (ByteBuf)this.buffers.getFirst();
            int available = first.readableBytes();

            assert !this.feedInterceptor(first) || !first.isReadable() : "Interceptor still active but buffer has data.";

            int read = available - first.readableBytes();
            if (read == available) {
               ((ByteBuf)this.buffers.removeFirst()).release();
            }

            this.totalSize -= (long)read;
         } else {
            ByteBuf frame = this.decodeNext();
            if (frame == null) {
               break;
            }

            ctx.fireChannelRead(frame);
         }
      }

   }

   private long decodeFrameSize() {
      if (this.nextFrameSize == -1L && this.totalSize >= 8L) {
         ByteBuf first = (ByteBuf)this.buffers.getFirst();
         if (first.readableBytes() >= 8) {
            this.nextFrameSize = first.readLong() - 8L;
            this.totalSize -= 8L;
            if (!first.isReadable()) {
               ((ByteBuf)this.buffers.removeFirst()).release();
            }

            return this.nextFrameSize;
         } else {
            while(this.frameLenBuf.readableBytes() < 8) {
               ByteBuf next = (ByteBuf)this.buffers.getFirst();
               int toRead = Math.min(next.readableBytes(), 8 - this.frameLenBuf.readableBytes());
               this.frameLenBuf.writeBytes(next, toRead);
               if (!next.isReadable()) {
                  ((ByteBuf)this.buffers.removeFirst()).release();
               }
            }

            this.nextFrameSize = this.frameLenBuf.readLong() - 8L;
            this.totalSize -= 8L;
            this.frameLenBuf.clear();
            return this.nextFrameSize;
         }
      } else {
         return this.nextFrameSize;
      }
   }

   private ByteBuf decodeNext() {
      long frameSize = this.decodeFrameSize();
      if (frameSize == -1L) {
         return null;
      } else {
         if (this.frameBuf == null) {
            Preconditions.checkArgument(frameSize < 2147483647L, "Too large frame: %s", frameSize);
            Preconditions.checkArgument(frameSize > 0L, "Frame length should be positive: %s", frameSize);
            this.frameRemainingBytes = (int)frameSize;
            if (this.buffers.isEmpty()) {
               return null;
            }

            if (((ByteBuf)this.buffers.getFirst()).readableBytes() >= this.frameRemainingBytes) {
               this.frameBuf = null;
               this.nextFrameSize = -1L;
               return this.nextBufferForFrame(this.frameRemainingBytes);
            }

            this.frameBuf = ((ByteBuf)this.buffers.getFirst()).alloc().compositeBuffer(Integer.MAX_VALUE);
         }

         while(this.frameRemainingBytes > 0 && !this.buffers.isEmpty()) {
            ByteBuf next = this.nextBufferForFrame(this.frameRemainingBytes);
            this.frameRemainingBytes -= next.readableBytes();
            this.frameBuf.addComponent(true, next);
         }

         if ((long)this.frameBuf.capacity() - this.consolidatedFrameBufSize > this.consolidateThreshold) {
            int newNumComponents = this.frameBuf.numComponents() - this.consolidatedNumComponents;
            this.frameBuf.consolidate(this.consolidatedNumComponents, newNumComponents);
            this.consolidatedFrameBufSize = (long)this.frameBuf.capacity();
            this.consolidatedNumComponents = this.frameBuf.numComponents();
         }

         return this.frameRemainingBytes > 0 ? null : this.consumeCurrentFrameBuf();
      }
   }

   private ByteBuf consumeCurrentFrameBuf() {
      ByteBuf frame = this.frameBuf;
      this.frameBuf = null;
      this.consolidatedFrameBufSize = 0L;
      this.consolidatedNumComponents = 0;
      this.nextFrameSize = -1L;
      return frame;
   }

   private ByteBuf nextBufferForFrame(int bytesToRead) {
      ByteBuf buf = (ByteBuf)this.buffers.getFirst();
      ByteBuf frame;
      if (buf.readableBytes() > bytesToRead) {
         frame = buf.retain().readSlice(bytesToRead);
         this.totalSize -= (long)bytesToRead;
      } else {
         frame = buf;
         this.buffers.removeFirst();
         this.totalSize -= (long)buf.readableBytes();
      }

      return frame;
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      if (this.interceptor != null) {
         this.interceptor.channelInactive();
      }

      super.channelInactive(ctx);
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      if (this.interceptor != null) {
         this.interceptor.exceptionCaught(cause);
      }

      super.exceptionCaught(ctx, cause);
   }

   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
      for(ByteBuf b : this.buffers) {
         b.release();
      }

      this.buffers.clear();
      this.frameLenBuf.release();
      ByteBuf frame = this.consumeCurrentFrameBuf();
      if (frame != null) {
         frame.release();
      }

      super.handlerRemoved(ctx);
   }

   public void setInterceptor(Interceptor interceptor) {
      Preconditions.checkState(this.interceptor == null, "Already have an interceptor.");
      this.interceptor = interceptor;
   }

   private boolean feedInterceptor(ByteBuf buf) throws Exception {
      if (this.interceptor != null && !this.interceptor.handle(buf)) {
         this.interceptor = null;
      }

      return this.interceptor != null;
   }

   public interface Interceptor {
      boolean handle(ByteBuf var1) throws Exception;

      void exceptionCaught(Throwable var1) throws Exception;

      void channelInactive() throws Exception;
   }
}
