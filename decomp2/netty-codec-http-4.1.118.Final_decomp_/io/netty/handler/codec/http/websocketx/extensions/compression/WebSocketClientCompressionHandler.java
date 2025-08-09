package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketClientExtensionHandler;

@Sharable
public final class WebSocketClientCompressionHandler extends WebSocketClientExtensionHandler {
   public static final WebSocketClientCompressionHandler INSTANCE = new WebSocketClientCompressionHandler();

   private WebSocketClientCompressionHandler() {
      super(new PerMessageDeflateClientExtensionHandshaker(), new DeflateFrameClientExtensionHandshaker(false), new DeflateFrameClientExtensionHandshaker(true));
   }
}
