package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;

public interface LastHttpContent extends HttpContent {
   LastHttpContent EMPTY_LAST_CONTENT = new LastHttpContent() {
      public ByteBuf content() {
         return Unpooled.EMPTY_BUFFER;
      }

      public LastHttpContent copy() {
         return EMPTY_LAST_CONTENT;
      }

      public LastHttpContent duplicate() {
         return this;
      }

      public LastHttpContent replace(ByteBuf content) {
         return new DefaultLastHttpContent(content);
      }

      public LastHttpContent retainedDuplicate() {
         return this;
      }

      public HttpHeaders trailingHeaders() {
         return EmptyHttpHeaders.INSTANCE;
      }

      public DecoderResult decoderResult() {
         return DecoderResult.SUCCESS;
      }

      /** @deprecated */
      @Deprecated
      public DecoderResult getDecoderResult() {
         return this.decoderResult();
      }

      public void setDecoderResult(DecoderResult result) {
         throw new UnsupportedOperationException("read only");
      }

      public int refCnt() {
         return 1;
      }

      public LastHttpContent retain() {
         return this;
      }

      public LastHttpContent retain(int increment) {
         return this;
      }

      public LastHttpContent touch() {
         return this;
      }

      public LastHttpContent touch(Object hint) {
         return this;
      }

      public boolean release() {
         return false;
      }

      public boolean release(int decrement) {
         return false;
      }

      public String toString() {
         return "EmptyLastHttpContent";
      }
   };

   HttpHeaders trailingHeaders();

   LastHttpContent copy();

   LastHttpContent duplicate();

   LastHttpContent retainedDuplicate();

   LastHttpContent replace(ByteBuf var1);

   LastHttpContent retain(int var1);

   LastHttpContent retain();

   LastHttpContent touch();

   LastHttpContent touch(Object var1);
}
