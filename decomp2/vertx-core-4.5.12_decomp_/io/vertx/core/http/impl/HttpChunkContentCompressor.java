package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentEncoder;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;

final class HttpChunkContentCompressor extends HttpContentCompressor {
   public HttpChunkContentCompressor(CompressionOptions... compressionOptions) {
      super(0, compressionOptions);
   }

   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
      if (msg instanceof ByteBuf) {
         ByteBuf buff = (ByteBuf)msg;
         if (buff.isReadable()) {
            msg = new DefaultHttpContent(buff);
         }
      }

      super.write(ctx, msg, promise);
   }

   protected HttpContentEncoder.Result beginEncode(HttpResponse httpResponse, String acceptEncoding) throws Exception {
      HttpContentEncoder.Result result = super.beginEncode(httpResponse, acceptEncoding);
      if (result == null && httpResponse.headers().contains(HttpHeaderNames.CONTENT_ENCODING, "identity", true)) {
         httpResponse.headers().remove(HttpHeaderNames.CONTENT_ENCODING);
      }

      return result;
   }
}
