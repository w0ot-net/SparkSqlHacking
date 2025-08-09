package io.netty.handler.codec.http;

public interface HttpRequest extends HttpMessage {
   /** @deprecated */
   @Deprecated
   HttpMethod getMethod();

   HttpMethod method();

   HttpRequest setMethod(HttpMethod var1);

   /** @deprecated */
   @Deprecated
   String getUri();

   String uri();

   HttpRequest setUri(String var1);

   HttpRequest setProtocolVersion(HttpVersion var1);
}
