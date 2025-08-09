package io.netty.handler.flow;

import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;

public class FlowControlHandler extends ChannelDuplexHandler {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(FlowControlHandler.class);
   private final boolean releaseMessages;
   private RecyclableArrayDeque queue;
   private ChannelConfig config;
   private boolean shouldConsume;

   public FlowControlHandler() {
      this(true);
   }

   public FlowControlHandler(boolean releaseMessages) {
      this.releaseMessages = releaseMessages;
   }

   boolean isQueueEmpty() {
      return this.queue == null || this.queue.isEmpty();
   }

   private void destroy() {
      if (this.queue != null) {
         if (!this.queue.isEmpty()) {
            logger.trace("Non-empty queue: {}", this.queue);
            Object msg;
            if (this.releaseMessages) {
               while((msg = this.queue.poll()) != null) {
                  ReferenceCountUtil.safeRelease(msg);
               }
            }
         }

         this.queue.recycle();
         this.queue = null;
      }

   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      this.config = ctx.channel().config();
   }

   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
      super.handlerRemoved(ctx);
      if (!this.isQueueEmpty()) {
         this.dequeue(ctx, this.queue.size());
      }

      this.destroy();
   }

   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      this.destroy();
      ctx.fireChannelInactive();
   }

   public void read(ChannelHandlerContext ctx) throws Exception {
      if (this.dequeue(ctx, 1) == 0) {
         this.shouldConsume = true;
         ctx.read();
      } else if (this.config.isAutoRead()) {
         ctx.read();
      }

   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      if (this.queue == null) {
         this.queue = FlowControlHandler.RecyclableArrayDeque.newInstance();
      }

      this.queue.offer(msg);
      int minConsume = this.shouldConsume ? 1 : 0;
      this.shouldConsume = false;
      this.dequeue(ctx, minConsume);
   }

   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      if (this.isQueueEmpty()) {
         ctx.fireChannelReadComplete();
      }

   }

   private int dequeue(ChannelHandlerContext ctx, int minConsume) {
      int consumed = 0;

      while(this.queue != null && (consumed < minConsume || this.config.isAutoRead())) {
         Object msg = this.queue.poll();
         if (msg == null) {
            break;
         }

         ++consumed;
         ctx.fireChannelRead(msg);
      }

      if (this.queue != null && this.queue.isEmpty()) {
         this.queue.recycle();
         this.queue = null;
         if (consumed > 0) {
            ctx.fireChannelReadComplete();
         }
      }

      return consumed;
   }

   private static final class RecyclableArrayDeque extends ArrayDeque {
      private static final long serialVersionUID = 0L;
      private static final int DEFAULT_NUM_ELEMENTS = 2;
      private static final ObjectPool RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator() {
         public RecyclableArrayDeque newObject(ObjectPool.Handle handle) {
            return new RecyclableArrayDeque(2, handle);
         }
      });
      private final ObjectPool.Handle handle;

      public static RecyclableArrayDeque newInstance() {
         return (RecyclableArrayDeque)RECYCLER.get();
      }

      private RecyclableArrayDeque(int numElements, ObjectPool.Handle handle) {
         super(numElements);
         this.handle = handle;
      }

      public void recycle() {
         this.clear();
         this.handle.recycle(this);
      }
   }
}
