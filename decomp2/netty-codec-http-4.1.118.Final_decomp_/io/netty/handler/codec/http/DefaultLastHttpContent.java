package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.Map;

public class DefaultLastHttpContent extends DefaultHttpContent implements LastHttpContent {
   private final HttpHeaders trailingHeaders;

   public DefaultLastHttpContent() {
      this(Unpooled.buffer(0));
   }

   public DefaultLastHttpContent(ByteBuf content) {
      this(content, (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory());
   }

   /** @deprecated */
   @Deprecated
   public DefaultLastHttpContent(ByteBuf content, boolean validateHeaders) {
      this(content, (HttpHeadersFactory)DefaultHttpHeadersFactory.trailersFactory().withValidation(validateHeaders));
   }

   public DefaultLastHttpContent(ByteBuf content, HttpHeadersFactory trailersFactory) {
      super(content);
      this.trailingHeaders = trailersFactory.newHeaders();
   }

   public DefaultLastHttpContent(ByteBuf content, HttpHeaders trailingHeaders) {
      super(content);
      this.trailingHeaders = (HttpHeaders)ObjectUtil.checkNotNull(trailingHeaders, "trailingHeaders");
   }

   public LastHttpContent copy() {
      return this.replace(this.content().copy());
   }

   public LastHttpContent duplicate() {
      return this.replace(this.content().duplicate());
   }

   public LastHttpContent retainedDuplicate() {
      return this.replace(this.content().retainedDuplicate());
   }

   public LastHttpContent replace(ByteBuf content) {
      return new DefaultLastHttpContent(content, this.trailingHeaders().copy());
   }

   public LastHttpContent retain(int increment) {
      super.retain(increment);
      return this;
   }

   public LastHttpContent retain() {
      super.retain();
      return this;
   }

   public LastHttpContent touch() {
      super.touch();
      return this;
   }

   public LastHttpContent touch(Object hint) {
      super.touch(hint);
      return this;
   }

   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder(super.toString());
      buf.append(StringUtil.NEWLINE);
      this.appendHeaders(buf);
      buf.setLength(buf.length() - StringUtil.NEWLINE.length());
      return buf.toString();
   }

   private void appendHeaders(StringBuilder buf) {
      for(Map.Entry e : this.trailingHeaders()) {
         buf.append((String)e.getKey());
         buf.append(": ");
         buf.append((String)e.getValue());
         buf.append(StringUtil.NEWLINE);
      }

   }
}
