package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;

class AssembledHttpRequest implements HttpContent, HttpRequest {
   private final HttpRequest request;
   protected final HttpContent content;

   AssembledHttpRequest(HttpRequest request, ByteBuf buf) {
      this(request, (HttpContent)(new DefaultHttpContent(buf)));
   }

   AssembledHttpRequest(HttpRequest request, HttpContent content) {
      this.request = request;
      this.content = content;
   }

   public AssembledHttpRequest copy() {
      throw new UnsupportedOperationException();
   }

   public AssembledHttpRequest duplicate() {
      throw new UnsupportedOperationException();
   }

   public HttpContent retainedDuplicate() {
      throw new UnsupportedMessageTypeException();
   }

   public HttpContent replace(ByteBuf content) {
      throw new UnsupportedMessageTypeException();
   }

   public AssembledHttpRequest retain() {
      this.content.retain();
      return this;
   }

   public AssembledHttpRequest retain(int increment) {
      this.content.retain(increment);
      return this;
   }

   public AssembledHttpRequest touch(Object hint) {
      this.content.touch(hint);
      return this;
   }

   public AssembledHttpRequest touch() {
      this.content.touch();
      return this;
   }

   public HttpMethod method() {
      return this.request.method();
   }

   public HttpMethod getMethod() {
      return this.request.method();
   }

   public String uri() {
      return this.request.uri();
   }

   public String getUri() {
      return this.request.uri();
   }

   public HttpHeaders headers() {
      return this.request.headers();
   }

   public HttpRequest setMethod(HttpMethod method) {
      return this.request.setMethod(method);
   }

   public HttpVersion protocolVersion() {
      return this.request.protocolVersion();
   }

   public HttpVersion getProtocolVersion() {
      return this.request.protocolVersion();
   }

   public HttpRequest setUri(String uri) {
      return this.request.setUri(uri);
   }

   public HttpRequest setProtocolVersion(HttpVersion version) {
      return this.request.setProtocolVersion(version);
   }

   public DecoderResult decoderResult() {
      return this.request.decoderResult();
   }

   public DecoderResult getDecoderResult() {
      return this.request.decoderResult();
   }

   public void setDecoderResult(DecoderResult result) {
      this.request.setDecoderResult(result);
   }

   public ByteBuf content() {
      return this.content.content();
   }

   public int refCnt() {
      return this.content.refCnt();
   }

   public boolean release() {
      return this.content.release();
   }

   public boolean release(int decrement) {
      return this.content.release(decrement);
   }
}
