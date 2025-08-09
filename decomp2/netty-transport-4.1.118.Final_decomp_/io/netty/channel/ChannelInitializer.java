package io.netty.channel;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ChannelHandler.Sharable
public abstract class ChannelInitializer extends ChannelInboundHandlerAdapter {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelInitializer.class);
   private final Set initMap = Collections.newSetFromMap(new ConcurrentHashMap());

   protected abstract void initChannel(Channel var1) throws Exception;

   public final void channelRegistered(ChannelHandlerContext ctx) throws Exception {
      if (this.initChannel(ctx)) {
         ctx.pipeline().fireChannelRegistered();
         this.removeState(ctx);
      } else {
         ctx.fireChannelRegistered();
      }

   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      if (logger.isWarnEnabled()) {
         logger.warn("Failed to initialize a channel. Closing: " + ctx.channel(), cause);
      }

      ctx.close();
   }

   public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
      if (ctx.channel().isRegistered() && this.initChannel(ctx)) {
         this.removeState(ctx);
      }

   }

   public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
      this.initMap.remove(ctx);
   }

   private boolean initChannel(ChannelHandlerContext ctx) throws Exception {
      if (this.initMap.add(ctx)) {
         try {
            this.initChannel(ctx.channel());
         } catch (Throwable cause) {
            this.exceptionCaught(ctx, cause);
         } finally {
            if (!ctx.isRemoved()) {
               ctx.pipeline().remove((ChannelHandler)this);
            }

         }

         return true;
      } else {
         return false;
      }
   }

   private void removeState(final ChannelHandlerContext ctx) {
      if (ctx.isRemoved()) {
         this.initMap.remove(ctx);
      } else {
         ctx.executor().execute(new Runnable() {
            public void run() {
               ChannelInitializer.this.initMap.remove(ctx);
            }
         });
      }

   }
}
