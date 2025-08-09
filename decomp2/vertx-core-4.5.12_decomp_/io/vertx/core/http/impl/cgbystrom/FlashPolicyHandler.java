package io.vertx.core.http.impl.cgbystrom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class FlashPolicyHandler extends ChannelInboundHandlerAdapter {
   private static final String XML = "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>";
   private ParseState state;

   public FlashPolicyHandler() {
      this.state = FlashPolicyHandler.ParseState.MAGIC1;
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf buffer = (ByteBuf)msg;
      int index = buffer.readerIndex();
      switch (this.state) {
         case MAGIC1:
            if (!buffer.isReadable()) {
               return;
            } else {
               int magic1 = buffer.getUnsignedByte(index++);
               this.state = FlashPolicyHandler.ParseState.MAGIC2;
               if (magic1 != 60) {
                  ctx.fireChannelRead(buffer);
                  ctx.pipeline().remove(this);
                  return;
               }
            }
         case MAGIC2:
            if (!buffer.isReadable()) {
               return;
            } else {
               int magic2 = buffer.getUnsignedByte(index);
               if (magic2 != 112) {
                  ctx.fireChannelRead(buffer);
                  ctx.pipeline().remove(this);
               } else {
                  ctx.writeAndFlush(Unpooled.copiedBuffer("<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>", CharsetUtil.UTF_8)).addListener(ChannelFutureListener.CLOSE);
               }
            }
         default:
      }
   }

   static enum ParseState {
      MAGIC1,
      MAGIC2;
   }
}
