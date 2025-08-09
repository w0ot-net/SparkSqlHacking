package io.netty.handler.codec.protobuf;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

@Sharable
public class ProtobufDecoder extends MessageToMessageDecoder {
   private static final boolean HAS_PARSER;
   private final MessageLite prototype;
   private final ExtensionRegistryLite extensionRegistry;

   public ProtobufDecoder(MessageLite prototype) {
      this(prototype, (ExtensionRegistry)null);
   }

   public ProtobufDecoder(MessageLite prototype, ExtensionRegistry extensionRegistry) {
      this(prototype, (ExtensionRegistryLite)extensionRegistry);
   }

   public ProtobufDecoder(MessageLite prototype, ExtensionRegistryLite extensionRegistry) {
      this.prototype = ((MessageLite)ObjectUtil.checkNotNull(prototype, "prototype")).getDefaultInstanceForType();
      this.extensionRegistry = extensionRegistry;
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

      if (this.extensionRegistry == null) {
         if (HAS_PARSER) {
            out.add(this.prototype.getParserForType().parseFrom(array, offset, length));
         } else {
            out.add(this.prototype.newBuilderForType().mergeFrom(array, offset, length).build());
         }
      } else if (HAS_PARSER) {
         out.add(this.prototype.getParserForType().parseFrom(array, offset, length, this.extensionRegistry));
      } else {
         out.add(this.prototype.newBuilderForType().mergeFrom(array, offset, length, this.extensionRegistry).build());
      }

   }

   static {
      boolean hasParser = false;

      try {
         MessageLite.class.getDeclaredMethod("getParserForType");
         hasParser = true;
      } catch (Throwable var2) {
      }

      HAS_PARSER = hasParser;
   }
}
