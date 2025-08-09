package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;

class PerFrameDeflateDecoder extends DeflateDecoder {
   PerFrameDeflateDecoder(boolean noContext) {
      super(noContext, WebSocketExtensionFilter.NEVER_SKIP);
   }

   PerFrameDeflateDecoder(boolean noContext, WebSocketExtensionFilter extensionDecoderFilter) {
      super(noContext, extensionDecoderFilter);
   }

   public boolean acceptInboundMessage(Object msg) throws Exception {
      if (!super.acceptInboundMessage(msg)) {
         return false;
      } else {
         WebSocketFrame wsFrame = (WebSocketFrame)msg;
         if (this.extensionDecoderFilter().mustSkip(wsFrame)) {
            return false;
         } else {
            return (msg instanceof TextWebSocketFrame || msg instanceof BinaryWebSocketFrame || msg instanceof ContinuationWebSocketFrame) && (wsFrame.rsv() & 4) > 0;
         }
      }
   }

   protected int newRsv(WebSocketFrame msg) {
      return msg.rsv() ^ 4;
   }

   protected boolean appendFrameTail(WebSocketFrame msg) {
      return true;
   }
}
