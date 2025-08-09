package org.apache.spark.network.sasl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.spark.network.buffer.NettyManagedBuffer;
import org.apache.spark.network.protocol.AbstractMessage;
import org.apache.spark.network.protocol.Encoders;
import org.apache.spark.network.protocol.Message;

class SaslMessage extends AbstractMessage {
   private static final byte TAG_BYTE = -22;
   public final String appId;

   SaslMessage(String appId, byte[] message) {
      this(appId, Unpooled.wrappedBuffer(message));
   }

   SaslMessage(String appId, ByteBuf message) {
      super(new NettyManagedBuffer(message), true);
      this.appId = appId;
   }

   public Message.Type type() {
      return Message.Type.User;
   }

   public int encodedLength() {
      return 1 + Encoders.Strings.encodedLength(this.appId) + 4;
   }

   public void encode(ByteBuf buf) {
      buf.writeByte(-22);
      Encoders.Strings.encode(buf, this.appId);
      buf.writeInt((int)this.body().size());
   }

   public static SaslMessage decode(ByteBuf buf) {
      if (buf.readByte() != -22) {
         throw new IllegalStateException("Expected SaslMessage, received something else (maybe your client does not have SASL enabled?)");
      } else {
         String appId = Encoders.Strings.decode(buf);
         buf.readInt();
         return new SaslMessage(appId, buf.retain());
      }
   }
}
