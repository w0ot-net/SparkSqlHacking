package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class HttpResponseEncoder extends HttpObjectEncoder {
   public boolean acceptOutboundMessage(Object msg) throws Exception {
      Class<?> msgClass = msg.getClass();
      if (msgClass != DefaultFullHttpResponse.class && msgClass != DefaultHttpResponse.class) {
         return super.acceptOutboundMessage(msg) && !(msg instanceof HttpRequest);
      } else {
         return true;
      }
   }

   protected void encodeInitialLine(ByteBuf buf, HttpResponse response) throws Exception {
      response.protocolVersion().encode(buf);
      buf.writeByte(32);
      response.status().encode(buf);
      ByteBufUtil.writeShortBE(buf, 3338);
   }

   protected void sanitizeHeadersBeforeEncode(HttpResponse msg, boolean isAlwaysEmpty) {
      if (isAlwaysEmpty) {
         HttpResponseStatus status = msg.status();
         if (status.codeClass() != HttpStatusClass.INFORMATIONAL && status.code() != HttpResponseStatus.NO_CONTENT.code()) {
            if (status.code() == HttpResponseStatus.RESET_CONTENT.code()) {
               msg.headers().remove((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
               msg.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, 0);
            }
         } else {
            msg.headers().remove((CharSequence)HttpHeaderNames.CONTENT_LENGTH);
            msg.headers().remove((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
         }
      }

   }

   protected boolean isContentAlwaysEmpty(HttpResponse msg) {
      HttpResponseStatus status = msg.status();
      if (status.codeClass() == HttpStatusClass.INFORMATIONAL) {
         return status.code() == HttpResponseStatus.SWITCHING_PROTOCOLS.code() ? msg.headers().contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_VERSION) : true;
      } else {
         return status.code() == HttpResponseStatus.NO_CONTENT.code() || status.code() == HttpResponseStatus.NOT_MODIFIED.code() || status.code() == HttpResponseStatus.RESET_CONTENT.code();
      }
   }
}
