package io.vertx.core.http.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.util.CharsetUtil;
import io.netty.util.collection.CharObjectMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VertxHttp2ClientUpgradeCodec implements HttpClientUpgradeHandler.UpgradeCodec {
   private static final List UPGRADE_HEADERS;
   private final Http2Settings settings;

   public VertxHttp2ClientUpgradeCodec(Http2Settings settings) {
      this.settings = settings;
   }

   public CharSequence protocol() {
      return "h2c";
   }

   public Collection setUpgradeHeaders(ChannelHandlerContext ctx, HttpRequest upgradeRequest) {
      io.netty.handler.codec.http2.Http2Settings nettySettings = new io.netty.handler.codec.http2.Http2Settings();
      HttpUtils.fromVertxInitialSettings(false, this.settings, nettySettings);
      Buffer buf = Buffer.buffer();

      for(CharObjectMap.PrimitiveEntry entry : nettySettings.entries()) {
         buf.appendUnsignedShort(entry.key());
         buf.appendUnsignedInt((Long)entry.value());
      }

      String encodedSettings = new String(Base64.getUrlEncoder().encode(buf.getBytes()), CharsetUtil.UTF_8);
      upgradeRequest.headers().set(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER, encodedSettings);
      return UPGRADE_HEADERS;
   }

   public void upgradeTo(ChannelHandlerContext ctx, FullHttpResponse upgradeResponse) throws Exception {
   }

   static {
      UPGRADE_HEADERS = Collections.singletonList(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER);
   }
}
