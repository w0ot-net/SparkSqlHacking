package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.LastHttpContent;

class AssembledLastHttpContent extends DefaultByteBufHolder implements LastHttpContent {
   private final HttpHeaders trailingHeaders;
   private DecoderResult result;

   AssembledLastHttpContent(ByteBuf buf, HttpHeaders trailingHeaders) {
      this(buf, trailingHeaders, DecoderResult.SUCCESS);
   }

   AssembledLastHttpContent(ByteBuf buf, HttpHeaders trailingHeaders, DecoderResult result) {
      super(buf);
      this.trailingHeaders = trailingHeaders;
      this.result = result;
   }

   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }

   public LastHttpContent copy() {
      throw new UnsupportedOperationException();
   }

   public LastHttpContent retain(int increment) {
      super.retain(increment);
      return this;
   }

   public LastHttpContent retain() {
      super.retain();
      return this;
   }

   public LastHttpContent duplicate() {
      throw new UnsupportedOperationException();
   }

   public LastHttpContent replace(ByteBuf content) {
      throw new UnsupportedOperationException();
   }

   public LastHttpContent retainedDuplicate() {
      throw new UnsupportedOperationException();
   }

   public DecoderResult decoderResult() {
      return this.result;
   }

   public DecoderResult getDecoderResult() {
      return this.result;
   }

   public void setDecoderResult(DecoderResult result) {
      this.result = result;
   }

   public AssembledLastHttpContent touch() {
      super.touch();
      return this;
   }

   public AssembledLastHttpContent touch(Object hint) {
      super.touch(hint);
      return this;
   }
}
