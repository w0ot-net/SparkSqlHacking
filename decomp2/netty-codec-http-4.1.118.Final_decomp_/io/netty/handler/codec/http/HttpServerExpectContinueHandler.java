package io.netty.handler.codec.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class HttpServerExpectContinueHandler extends ChannelInboundHandlerAdapter {
   private static final FullHttpResponse EXPECTATION_FAILED;
   private static final FullHttpResponse ACCEPT;

   protected HttpResponse acceptMessage(HttpRequest request) {
      return ACCEPT.retainedDuplicate();
   }

   protected HttpResponse rejectResponse(HttpRequest request) {
      return EXPECTATION_FAILED.retainedDuplicate();
   }

   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      if (msg instanceof HttpRequest) {
         HttpRequest req = (HttpRequest)msg;
         if (HttpUtil.is100ContinueExpected(req)) {
            HttpResponse accept = this.acceptMessage(req);
            if (accept == null) {
               HttpResponse rejection = this.rejectResponse(req);
               ReferenceCountUtil.release(msg);
               ctx.writeAndFlush(rejection).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
               return;
            }

            ctx.writeAndFlush(accept).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            req.headers().remove((CharSequence)HttpHeaderNames.EXPECT);
         }
      }

      super.channelRead(ctx, msg);
   }

   static {
      EXPECTATION_FAILED = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.EXPECTATION_FAILED, Unpooled.EMPTY_BUFFER);
      ACCEPT = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);
      EXPECTATION_FAILED.headers().set((CharSequence)HttpHeaderNames.CONTENT_LENGTH, (int)0);
      ACCEPT.headers().set((CharSequence)HttpHeaderNames.CONTENT_LENGTH, (int)0);
   }
}
