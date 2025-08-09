package org.apache.spark.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

@Sharable
public final class MessageDecoder extends MessageToMessageDecoder {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(MessageDecoder.class);
   public static final MessageDecoder INSTANCE = new MessageDecoder();

   private MessageDecoder() {
   }

   public void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
      Message.Type msgType = Message.Type.decode(in);
      Message decoded = this.decode(msgType, in);

      assert decoded.type() == msgType;

      logger.trace("Received message {}: {}", msgType, decoded);
      out.add(decoded);
   }

   private Message decode(Message.Type msgType, ByteBuf in) {
      Object var10000;
      switch (msgType) {
         case ChunkFetchRequest -> var10000 = ChunkFetchRequest.decode(in);
         case ChunkFetchSuccess -> var10000 = ChunkFetchSuccess.decode(in);
         case ChunkFetchFailure -> var10000 = ChunkFetchFailure.decode(in);
         case RpcRequest -> var10000 = RpcRequest.decode(in);
         case RpcResponse -> var10000 = RpcResponse.decode(in);
         case RpcFailure -> var10000 = RpcFailure.decode(in);
         case OneWayMessage -> var10000 = OneWayMessage.decode(in);
         case StreamRequest -> var10000 = StreamRequest.decode(in);
         case StreamResponse -> var10000 = StreamResponse.decode(in);
         case StreamFailure -> var10000 = StreamFailure.decode(in);
         case UploadStream -> var10000 = UploadStream.decode(in);
         case MergedBlockMetaRequest -> var10000 = MergedBlockMetaRequest.decode(in);
         case MergedBlockMetaSuccess -> var10000 = MergedBlockMetaSuccess.decode(in);
         default -> throw new IllegalArgumentException("Unexpected message type: " + String.valueOf(msgType));
      }

      return (Message)var10000;
   }
}
