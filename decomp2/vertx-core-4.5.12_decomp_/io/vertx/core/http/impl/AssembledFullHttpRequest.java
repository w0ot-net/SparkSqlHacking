package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

class AssembledFullHttpRequest extends AssembledHttpRequest implements FullHttpRequest {
   public AssembledFullHttpRequest(HttpRequest request, LastHttpContent content) {
      super(request, (HttpContent)content);
   }

   public AssembledFullHttpRequest(HttpRequest request) {
      super(request, (HttpContent)LastHttpContent.EMPTY_LAST_CONTENT);
   }

   public AssembledFullHttpRequest(HttpRequest request, ByteBuf buf) {
      super(request, (HttpContent)toLastContent(buf));
   }

   private static LastHttpContent toLastContent(ByteBuf buf) {
      return (LastHttpContent)(buf.isReadable() ? new DefaultLastHttpContent(buf, false) : LastHttpContent.EMPTY_LAST_CONTENT);
   }

   public AssembledFullHttpRequest replace(ByteBuf content) {
      super.replace(content);
      return this;
   }

   public AssembledFullHttpRequest retainedDuplicate() {
      super.retainedDuplicate();
      return this;
   }

   public AssembledFullHttpRequest setUri(String uri) {
      super.setUri(uri);
      return this;
   }

   public AssembledFullHttpRequest setProtocolVersion(HttpVersion version) {
      super.setProtocolVersion(version);
      return this;
   }

   public AssembledFullHttpRequest setMethod(HttpMethod method) {
      super.setMethod(method);
      return this;
   }

   public AssembledFullHttpRequest duplicate() {
      throw new UnsupportedOperationException();
   }

   public AssembledFullHttpRequest copy() {
      throw new UnsupportedOperationException();
   }

   public HttpHeaders trailingHeaders() {
      return ((LastHttpContent)this.content).trailingHeaders();
   }

   public AssembledFullHttpRequest retain() {
      super.retain();
      return this;
   }

   public AssembledFullHttpRequest retain(int increment) {
      super.retain(increment);
      return this;
   }

   public AssembledFullHttpRequest touch(Object hint) {
      super.touch(hint);
      return this;
   }

   public AssembledFullHttpRequest touch() {
      super.touch();
      return this;
   }
}
