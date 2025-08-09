package io.netty.handler.ipfilter;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.SocketAddress;

public abstract class AbstractRemoteAddressFilter extends ChannelInboundHandlerAdapter {
   public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
      this.handleNewChannel(ctx);
      ctx.fireChannelRegistered();
   }

   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      if (!this.handleNewChannel(ctx)) {
         throw new IllegalStateException("cannot determine to accept or reject a channel: " + ctx.channel());
      } else {
         ctx.fireChannelActive();
      }
   }

   private boolean handleNewChannel(ChannelHandlerContext ctx) throws Exception {
      T remoteAddress = (T)ctx.channel().remoteAddress();
      if (remoteAddress == null) {
         return false;
      } else {
         ctx.pipeline().remove(this);
         if (this.accept(ctx, remoteAddress)) {
            this.channelAccepted(ctx, remoteAddress);
         } else {
            ChannelFuture rejectedFuture = this.channelRejected(ctx, remoteAddress);
            if (rejectedFuture != null) {
               rejectedFuture.addListener(ChannelFutureListener.CLOSE);
            } else {
               ctx.close();
            }
         }

         return true;
      }
   }

   protected abstract boolean accept(ChannelHandlerContext var1, SocketAddress var2) throws Exception;

   protected void channelAccepted(ChannelHandlerContext ctx, SocketAddress remoteAddress) {
   }

   protected ChannelFuture channelRejected(ChannelHandlerContext ctx, SocketAddress remoteAddress) {
      return null;
   }
}
