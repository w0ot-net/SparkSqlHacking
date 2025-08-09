package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;

public class DefaultFullHttpRequest extends DefaultHttpRequest implements FullHttpRequest {
   private final ByteBuf content;
   private final HttpHeaders trailingHeader;
   private int hash;

   public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri) {
      this(httpVersion, method, uri, Unpooled.buffer(0), (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory(), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory());
   }

   public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, ByteBuf content) {
      this(httpVersion, method, uri, content, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory(), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory());
   }

   /** @deprecated */
   @Deprecated
   public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, boolean validateHeaders) {
      this(httpVersion, method, uri, Unpooled.buffer(0), (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders));
   }

   /** @deprecated */
   @Deprecated
   public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, ByteBuf content, boolean validateHeaders) {
      this(httpVersion, method, uri, content, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders));
   }

   public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, ByteBuf content, HttpHeadersFactory headersFactory, HttpHeadersFactory trailersFactory) {
      this(httpVersion, method, uri, content, headersFactory.newHeaders(), trailersFactory.newHeaders());
   }

   public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, ByteBuf content, HttpHeaders headers, HttpHeaders trailingHeader) {
      super(httpVersion, method, uri, headers);
      this.content = (ByteBuf)ObjectUtil.checkNotNull(content, "content");
      this.trailingHeader = (HttpHeaders)ObjectUtil.checkNotNull(trailingHeader, "trailingHeader");
   }

   public HttpHeaders trailingHeaders() {
      return this.trailingHeader;
   }

   public ByteBuf content() {
      return this.content;
   }

   public int refCnt() {
      return this.content.refCnt();
   }

   public FullHttpRequest retain() {
      this.content.retain();
      return this;
   }

   public FullHttpRequest retain(int increment) {
      this.content.retain(increment);
      return this;
   }

   public FullHttpRequest touch() {
      this.content.touch();
      return this;
   }

   public FullHttpRequest touch(Object hint) {
      this.content.touch(hint);
      return this;
   }

   public boolean release() {
      return this.content.release();
   }

   public boolean release(int decrement) {
      return this.content.release(decrement);
   }

   public FullHttpRequest setProtocolVersion(HttpVersion version) {
      super.setProtocolVersion(version);
      return this;
   }

   public FullHttpRequest setMethod(HttpMethod method) {
      super.setMethod(method);
      return this;
   }

   public FullHttpRequest setUri(String uri) {
      super.setUri(uri);
      return this;
   }

   public FullHttpRequest copy() {
      return this.replace(this.content().copy());
   }

   public FullHttpRequest duplicate() {
      return this.replace(this.content().duplicate());
   }

   public FullHttpRequest retainedDuplicate() {
      return this.replace(this.content().retainedDuplicate());
   }

   public FullHttpRequest replace(ByteBuf content) {
      FullHttpRequest request = new DefaultFullHttpRequest(this.protocolVersion(), this.method(), this.uri(), content, this.headers().copy(), this.trailingHeaders().copy());
      request.setDecoderResult(this.decoderResult());
      return request;
   }

   public int hashCode() {
      int hash = this.hash;
      if (hash == 0) {
         if (ByteBufUtil.isAccessible(this.content())) {
            try {
               hash = 31 + this.content().hashCode();
            } catch (IllegalReferenceCountException var3) {
               hash = 31;
            }
         } else {
            hash = 31;
         }

         int var5 = 31 * hash + this.trailingHeaders().hashCode();
         hash = 31 * var5 + super.hashCode();
         this.hash = hash;
      }

      return hash;
   }

   public boolean equals(Object o) {
      if (!(o instanceof DefaultFullHttpRequest)) {
         return false;
      } else {
         DefaultFullHttpRequest other = (DefaultFullHttpRequest)o;
         return super.equals(other) && this.content().equals(other.content()) && this.trailingHeaders().equals(other.trailingHeaders());
      }
   }

   public String toString() {
      return HttpMessageUtil.appendFullRequest(new StringBuilder(256), this).toString();
   }
}
