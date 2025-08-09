package io.netty.handler.codec.http;

import io.netty.util.internal.ObjectUtil;

public abstract class DefaultHttpMessage extends DefaultHttpObject implements HttpMessage {
   private static final int HASH_CODE_PRIME = 31;
   private HttpVersion version;
   private final HttpHeaders headers;

   protected DefaultHttpMessage(HttpVersion version) {
      this(version, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory());
   }

   /** @deprecated */
   @Deprecated
   protected DefaultHttpMessage(HttpVersion version, boolean validateHeaders, boolean singleFieldHeaders) {
      this(version, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders).withCombiningHeaders(singleFieldHeaders));
   }

   protected DefaultHttpMessage(HttpVersion version, HttpHeadersFactory headersFactory) {
      this(version, headersFactory.newHeaders());
   }

   protected DefaultHttpMessage(HttpVersion version, HttpHeaders headers) {
      this.version = (HttpVersion)ObjectUtil.checkNotNull(version, "version");
      this.headers = (HttpHeaders)ObjectUtil.checkNotNull(headers, "headers");
   }

   public HttpHeaders headers() {
      return this.headers;
   }

   /** @deprecated */
   @Deprecated
   public HttpVersion getProtocolVersion() {
      return this.protocolVersion();
   }

   public HttpVersion protocolVersion() {
      return this.version;
   }

   public int hashCode() {
      int result = 1;
      result = 31 * result + this.headers.hashCode();
      result = 31 * result + this.version.hashCode();
      result = 31 * result + super.hashCode();
      return result;
   }

   public boolean equals(Object o) {
      if (!(o instanceof DefaultHttpMessage)) {
         return false;
      } else {
         DefaultHttpMessage other = (DefaultHttpMessage)o;
         return this.headers().equals(other.headers()) && this.protocolVersion().equals(other.protocolVersion()) && super.equals(o);
      }
   }

   public HttpMessage setProtocolVersion(HttpVersion version) {
      this.version = (HttpVersion)ObjectUtil.checkNotNull(version, "version");
      return this;
   }
}
