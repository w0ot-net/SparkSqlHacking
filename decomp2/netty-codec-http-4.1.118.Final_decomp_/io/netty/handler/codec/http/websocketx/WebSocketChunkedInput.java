package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.internal.ObjectUtil;

public final class WebSocketChunkedInput implements ChunkedInput {
   private final ChunkedInput input;
   private final int rsv;

   public WebSocketChunkedInput(ChunkedInput input) {
      this(input, 0);
   }

   public WebSocketChunkedInput(ChunkedInput input, int rsv) {
      this.input = (ChunkedInput)ObjectUtil.checkNotNull(input, "input");
      this.rsv = rsv;
   }

   public boolean isEndOfInput() throws Exception {
      return this.input.isEndOfInput();
   }

   public void close() throws Exception {
      this.input.close();
   }

   /** @deprecated */
   @Deprecated
   public WebSocketFrame readChunk(ChannelHandlerContext ctx) throws Exception {
      return this.readChunk(ctx.alloc());
   }

   public WebSocketFrame readChunk(ByteBufAllocator allocator) throws Exception {
      ByteBuf buf = (ByteBuf)this.input.readChunk(allocator);
      return buf == null ? null : new ContinuationWebSocketFrame(this.input.isEndOfInput(), this.rsv, buf);
   }

   public long length() {
      return this.input.length();
   }

   public long progress() {
      return this.input.progress();
   }
}
