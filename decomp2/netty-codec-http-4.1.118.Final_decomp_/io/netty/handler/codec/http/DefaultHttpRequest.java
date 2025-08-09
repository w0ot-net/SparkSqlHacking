package io.netty.handler.codec.http;

import io.netty.util.internal.ObjectUtil;

public class DefaultHttpRequest extends DefaultHttpMessage implements HttpRequest {
   private static final int HASH_CODE_PRIME = 31;
   private HttpMethod method;
   private String uri;

   public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri) {
      this(httpVersion, method, uri, DefaultHttpHeadersFactory.headersFactory().newHeaders());
   }

   /** @deprecated */
   @Deprecated
   public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, boolean validateHeaders) {
      this(httpVersion, method, uri, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders));
   }

   public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, HttpHeadersFactory headersFactory) {
      this(httpVersion, method, uri, headersFactory.newHeaders());
   }

   public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, HttpHeaders headers) {
      super(httpVersion, headers);
      this.method = (HttpMethod)ObjectUtil.checkNotNull(method, "method");
      this.uri = (String)ObjectUtil.checkNotNull(uri, "uri");
   }

   /** @deprecated */
   @Deprecated
   public HttpMethod getMethod() {
      return this.method();
   }

   public HttpMethod method() {
      return this.method;
   }

   /** @deprecated */
   @Deprecated
   public String getUri() {
      return this.uri();
   }

   public String uri() {
      return this.uri;
   }

   public HttpRequest setMethod(HttpMethod method) {
      this.method = (HttpMethod)ObjectUtil.checkNotNull(method, "method");
      return this;
   }

   public HttpRequest setUri(String uri) {
      this.uri = (String)ObjectUtil.checkNotNull(uri, "uri");
      return this;
   }

   public HttpRequest setProtocolVersion(HttpVersion version) {
      super.setProtocolVersion(version);
      return this;
   }

   public int hashCode() {
      int result = 1;
      result = 31 * result + this.method.hashCode();
      result = 31 * result + this.uri.hashCode();
      result = 31 * result + super.hashCode();
      return result;
   }

   public boolean equals(Object o) {
      if (!(o instanceof DefaultHttpRequest)) {
         return false;
      } else {
         DefaultHttpRequest other = (DefaultHttpRequest)o;
         return this.method().equals(other.method()) && this.uri().equalsIgnoreCase(other.uri()) && super.equals(o);
      }
   }

   public String toString() {
      return HttpMessageUtil.appendRequest(new StringBuilder(256), this).toString();
   }
}
