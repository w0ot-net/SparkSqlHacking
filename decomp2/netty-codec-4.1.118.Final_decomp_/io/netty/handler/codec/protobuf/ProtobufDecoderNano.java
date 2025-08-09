package io.netty.handler.codec.protobuf;

import com.google.protobuf.nano.MessageNano;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

@Sharable
public class ProtobufDecoderNano extends MessageToMessageDecoder {
   private final Class clazz;

   public ProtobufDecoderNano(Class clazz) {
      this.clazz = (Class)ObjectUtil.checkNotNull(clazz, "You must provide a Class");
   }

   protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List out) throws Exception {
      int length = msg.readableBytes();
      byte[] array;
      int offset;
      if (msg.hasArray()) {
         array = msg.array();
         offset = msg.arrayOffset() + msg.readerIndex();
      } else {
         array = ByteBufUtil.getBytes(msg, msg.readerIndex(), length, false);
         offset = 0;
      }

      MessageNano prototype = (MessageNano)this.clazz.getConstructor().newInstance();
      out.add(MessageNano.mergeFrom(prototype, array, offset, length));
   }
}
