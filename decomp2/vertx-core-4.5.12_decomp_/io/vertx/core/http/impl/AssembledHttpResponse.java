package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

class AssembledHttpResponse implements HttpResponse, HttpContent {
   private boolean head;
   private HttpResponseStatus status;
   private HttpVersion version;
   private HttpHeaders headers;
   private final ByteBuf content;
   private DecoderResult result;

   AssembledHttpResponse(boolean head, HttpVersion version, HttpResponseStatus status, HttpHeaders headers) {
      this(head, version, status, headers, Unpooled.EMPTY_BUFFER);
   }

   AssembledHttpResponse(boolean head, HttpVersion version, HttpResponseStatus status, HttpHeaders headers, ByteBuf content) {
      this.result = DecoderResult.SUCCESS;
      this.head = head;
      this.status = status;
      this.version = version;
      this.headers = headers;
      this.content = content;
   }

   boolean head() {
      return this.head;
   }

   public HttpContent copy() {
      throw new UnsupportedOperationException();
   }

   public HttpContent duplicate() {
      throw new UnsupportedOperationException();
   }

   public HttpContent retainedDuplicate() {
      throw new UnsupportedOperationException();
   }

   public HttpContent replace(ByteBuf content) {
      throw new UnsupportedOperationException();
   }

   public AssembledHttpResponse retain() {
      this.content.retain();
      return this;
   }

   public AssembledHttpResponse retain(int increment) {
      this.content.retain(increment);
      return this;
   }

   public HttpResponseStatus getStatus() {
      return this.status;
   }

   public AssembledHttpResponse setStatus(HttpResponseStatus status) {
      this.status = status;
      return this;
   }

   public AssembledHttpResponse setProtocolVersion(HttpVersion version) {
      this.version = version;
      return this;
   }

   public HttpVersion getProtocolVersion() {
      return this.version;
   }

   public HttpVersion protocolVersion() {
      return this.version;
   }

   public HttpResponseStatus status() {
      return this.status;
   }

   public AssembledHttpResponse touch() {
      this.content.touch();
      return this;
   }

   public AssembledHttpResponse touch(Object hint) {
      this.content.touch(hint);
      return this;
   }

   public DecoderResult decoderResult() {
      return this.result;
   }

   public HttpHeaders headers() {
      return this.headers;
   }

   public DecoderResult getDecoderResult() {
      return this.result;
   }

   public void setDecoderResult(DecoderResult result) {
      this.result = result;
   }

   public ByteBuf content() {
      return this.content;
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
