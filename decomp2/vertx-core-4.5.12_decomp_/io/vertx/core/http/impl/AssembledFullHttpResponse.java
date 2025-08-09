package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

class AssembledFullHttpResponse extends AssembledHttpResponse implements FullHttpResponse {
   private HttpHeaders trailingHeaders;

   public AssembledFullHttpResponse(boolean head, HttpVersion version, HttpResponseStatus status, HttpHeaders headers, ByteBuf buf, HttpHeaders trailingHeaders) {
      super(head, version, status, headers, buf);
      this.trailingHeaders = trailingHeaders;
   }

   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }

   public AssembledFullHttpResponse setStatus(HttpResponseStatus status) {
      super.setStatus(status);
      return this;
   }

   public AssembledFullHttpResponse retain(int increment) {
      super.retain(increment);
      return this;
   }

   public AssembledFullHttpResponse retain() {
      super.retain();
      return this;
   }

   public AssembledFullHttpResponse duplicate() {
      super.duplicate();
      return this;
   }

   public AssembledFullHttpResponse copy() {
      super.copy();
      return this;
   }

   public AssembledFullHttpResponse retainedDuplicate() {
      super.retainedDuplicate();
      return this;
   }

   public AssembledFullHttpResponse replace(ByteBuf content) {
      super.replace(content);
      return this;
   }

   public AssembledFullHttpResponse setProtocolVersion(HttpVersion version) {
      super.setProtocolVersion(version);
      return this;
   }

   public AssembledFullHttpResponse touch() {
      super.touch();
      return this;
   }

   public AssembledFullHttpResponse touch(Object hint) {
      super.touch(hint);
      return this;
   }
}
