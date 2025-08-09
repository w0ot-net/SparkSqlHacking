package io.netty.handler.codec.bytes;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

@Sharable
public class ByteArrayEncoder extends MessageToMessageEncoder {
   protected void encode(ChannelHandlerContext ctx, byte[] msg, List out) throws Exception {
      out.add(Unpooled.wrappedBuffer(msg));
   }
}
