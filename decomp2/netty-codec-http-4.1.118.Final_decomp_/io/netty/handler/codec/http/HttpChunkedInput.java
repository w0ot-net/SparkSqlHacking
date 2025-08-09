package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;

public class HttpChunkedInput implements ChunkedInput {
   private final ChunkedInput input;
   private final LastHttpContent lastHttpContent;
   private boolean sentLastChunk;

   public HttpChunkedInput(ChunkedInput input) {
      this.input = input;
      this.lastHttpContent = LastHttpContent.EMPTY_LAST_CONTENT;
   }

   public HttpChunkedInput(ChunkedInput input, LastHttpContent lastHttpContent) {
      this.input = input;
      this.lastHttpContent = lastHttpContent;
   }

   public boolean isEndOfInput() throws Exception {
      return this.input.isEndOfInput() ? this.sentLastChunk : false;
   }

   public void close() throws Exception {
      this.input.close();
   }

   /** @deprecated */
   @Deprecated
   public HttpContent readChunk(ChannelHandlerContext ctx) throws Exception {
      return this.readChunk(ctx.alloc());
   }

   public HttpContent readChunk(ByteBufAllocator allocator) throws Exception {
      if (this.input.isEndOfInput()) {
         if (this.sentLastChunk) {
            return null;
         } else {
            this.sentLastChunk = true;
            return this.lastHttpContent;
         }
      } else {
         ByteBuf buf = (ByteBuf)this.input.readChunk(allocator);
         return buf == null ? null : new DefaultHttpContent(buf);
      }
   }

   public long length() {
      return this.input.length();
   }

   public long progress() {
      return this.input.progress();
   }
}
