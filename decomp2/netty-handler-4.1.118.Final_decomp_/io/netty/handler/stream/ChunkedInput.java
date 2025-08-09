package io.netty.handler.stream;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

public interface ChunkedInput {
   boolean isEndOfInput() throws Exception;

   void close() throws Exception;

   /** @deprecated */
   @Deprecated
   Object readChunk(ChannelHandlerContext var1) throws Exception;

   Object readChunk(ByteBufAllocator var1) throws Exception;

   long length();

   long progress();
}
