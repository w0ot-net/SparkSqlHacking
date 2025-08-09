package io.netty.handler.codec.rtsp;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpObjectEncoder;

/** @deprecated */
@Sharable
@Deprecated
public abstract class RtspObjectEncoder extends HttpObjectEncoder {
   protected RtspObjectEncoder() {
   }

   public boolean acceptOutboundMessage(Object msg) throws Exception {
      return msg instanceof FullHttpMessage;
   }
}
