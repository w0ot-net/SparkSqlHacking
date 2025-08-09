package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.util.ReferenceCountUtil;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SpdyHttpResponseStreamIdHandler extends MessageToMessageCodec {
   private static final Integer NO_ID = -1;
   private final Queue ids = new ArrayDeque();

   public boolean acceptInboundMessage(Object msg) throws Exception {
      return msg instanceof HttpMessage || msg instanceof SpdyRstStreamFrame;
   }

   protected void encode(ChannelHandlerContext ctx, HttpMessage msg, List out) throws Exception {
      Integer id = (Integer)this.ids.poll();
      if (id != null && id != NO_ID && !msg.headers().contains((CharSequence)SpdyHttpHeaders.Names.STREAM_ID)) {
         msg.headers().setInt(SpdyHttpHeaders.Names.STREAM_ID, id);
      }

      out.add(ReferenceCountUtil.retain(msg));
   }

   protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
      if (msg instanceof HttpMessage) {
         boolean contains = ((HttpMessage)msg).headers().contains((CharSequence)SpdyHttpHeaders.Names.STREAM_ID);
         if (!contains) {
            this.ids.add(NO_ID);
         } else {
            this.ids.add(((HttpMessage)msg).headers().getInt(SpdyHttpHeaders.Names.STREAM_ID));
         }
      } else if (msg instanceof SpdyRstStreamFrame) {
         this.ids.remove(((SpdyRstStreamFrame)msg).streamId());
      }

      out.add(ReferenceCountUtil.retain(msg));
   }
}
