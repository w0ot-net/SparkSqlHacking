package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Http1xOrH2CHandler extends ChannelInboundHandlerAdapter {
   public static final String HTTP_2_PREFACE = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n";
   private static final byte[] HTTP_2_PREFACE_ARRAY = "PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n".getBytes();
   private int current = 0;

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf buf = (ByteBuf)msg;
      int len = Math.min(buf.readableBytes(), HTTP_2_PREFACE_ARRAY.length - this.current);

      int i;
      for(i = 0; i < len; ++i) {
         if (buf.getByte(buf.readerIndex() + i) != HTTP_2_PREFACE_ARRAY[this.current + i]) {
            this.end(ctx, buf, false);
            return;
         }
      }

      if (this.current + i == HTTP_2_PREFACE_ARRAY.length) {
         this.end(ctx, buf, true);
      } else {
         this.current += len;
         buf.release();
      }

   }

   private void end(ChannelHandlerContext ctx, ByteBuf buf, boolean h2c) {
      if (this.current > 0) {
         ByteBuf msg = Unpooled.buffer(this.current + buf.readableBytes());
         msg.writeBytes(HTTP_2_PREFACE_ARRAY, 0, this.current);
         msg.writeBytes(buf);
         buf.release();
         buf = msg;
      }

      this.configure(ctx, h2c);
      ctx.pipeline().remove(this);
      ctx.fireChannelRead(buf);
   }

   protected void configure(ChannelHandlerContext ctx, boolean h2c) {
   }

   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      Channel channel = ctx.channel();
      channel.close();
   }
}
