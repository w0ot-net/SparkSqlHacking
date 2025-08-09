package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;

public class DefaultFullHttpResponse extends DefaultHttpResponse implements FullHttpResponse {
   private final ByteBuf content;
   private final HttpHeaders trailingHeaders;
   private int hash;

   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status) {
      this(version, status, Unpooled.buffer(0), (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory(), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory());
   }

   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content) {
      this(version, status, content, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory(), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory());
   }

   /** @deprecated */
   @Deprecated
   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, boolean validateHeaders) {
      this(version, status, Unpooled.buffer(0), (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders));
   }

   /** @deprecated */
   @Deprecated
   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, boolean validateHeaders, boolean singleFieldHeaders) {
      this(version, status, Unpooled.buffer(0), (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders).withCombiningHeaders(singleFieldHeaders), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders).withCombiningHeaders(singleFieldHeaders));
   }

   /** @deprecated */
   @Deprecated
   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, boolean validateHeaders) {
      this(version, status, content, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders));
   }

   /** @deprecated */
   @Deprecated
   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, boolean validateHeaders, boolean singleFieldHeaders) {
      this(version, status, content, (HttpHeadersFactory)DefaultHttpHeadersFactory.headersFactory().withValidation(validateHeaders).withCombiningHeaders(singleFieldHeaders), (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders).withCombiningHeaders(singleFieldHeaders));
   }

   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, HttpHeadersFactory headersFactory, HttpHeadersFactory trailersFactory) {
      this(version, status, content, headersFactory.newHeaders(), trailersFactory.newHeaders());
   }

   public DefaultFullHttpResponse(HttpVersion version, HttpResponseStatus status, ByteBuf content, HttpHeaders headers, HttpHeaders trailingHeaders) {
      super(version, status, headers);
      this.content = (ByteBuf)ObjectUtil.checkNotNull(content, "content");
      this.trailingHeaders = (HttpHeaders)ObjectUtil.checkNotNull(trailingHeaders, "trailingHeaders");
   }

   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }

   public ByteBuf content() {
      return this.content;
   }

   public int refCnt() {
      return this.content.refCnt();
   }

   public FullHttpResponse retain() {
      this.content.retain();
      return this;
   }

   public FullHttpResponse retain(int increment) {
      this.content.retain(increment);
      return this;
   }

   public FullHttpResponse touch() {
      this.content.touch();
      return this;
   }

   public FullHttpResponse touch(Object hint) {
      this.content.touch(hint);
      return this;
   }

   public boolean release() {
      return this.content.release();
   }

   public boolean release(int decrement) {
      return this.content.release(decrement);
   }

   public FullHttpResponse setProtocolVersion(HttpVersion version) {
      super.setProtocolVersion(version);
      return this;
   }

   public FullHttpResponse setStatus(HttpResponseStatus status) {
      super.setStatus(status);
      return this;
   }

   public FullHttpResponse copy() {
      return this.replace(this.content().copy());
   }

   public FullHttpResponse duplicate() {
      return this.replace(this.content().duplicate());
   }

   public FullHttpResponse retainedDuplicate() {
      return this.replace(this.content().retainedDuplicate());
   }

   public FullHttpResponse replace(ByteBuf content) {
      FullHttpResponse response = new DefaultFullHttpResponse(this.protocolVersion(), this.status(), content, this.headers().copy(), this.trailingHeaders().copy());
      response.setDecoderResult(this.decoderResult());
      return response;
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
      if (!(o instanceof DefaultFullHttpResponse)) {
         return false;
      } else {
         DefaultFullHttpResponse other = (DefaultFullHttpResponse)o;
         return super.equals(other) && this.content().equals(other.content()) && this.trailingHeaders().equals(other.trailingHeaders());
      }
   }

   public String toString() {
      return HttpMessageUtil.appendFullResponse(new StringBuilder(256), this).toString();
   }
}
