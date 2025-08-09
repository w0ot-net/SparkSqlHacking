package org.apache.spark.network.crypto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
import org.apache.spark.network.protocol.Encodable;
import org.apache.spark.network.protocol.Encoders;

record AuthMessage(String appId, byte[] salt, byte[] ciphertext) implements Encodable {
   private static final byte TAG_BYTE = -5;

   public int encodedLength() {
      return 1 + Encoders.Strings.encodedLength(this.appId) + Encoders.ByteArrays.encodedLength(this.salt) + Encoders.ByteArrays.encodedLength(this.ciphertext);
   }

   public void encode(ByteBuf buf) {
      buf.writeByte(-5);
      Encoders.Strings.encode(buf, this.appId);
      Encoders.ByteArrays.encode(buf, this.salt);
      Encoders.ByteArrays.encode(buf, this.ciphertext);
   }

   public static AuthMessage decodeMessage(ByteBuffer buffer) {
      ByteBuf buf = Unpooled.wrappedBuffer(buffer);
      if (buf.readByte() != -5) {
         throw new IllegalArgumentException("Expected ClientChallenge, received something else.");
      } else {
         return new AuthMessage(Encoders.Strings.decode(buf), Encoders.ByteArrays.decode(buf), Encoders.ByteArrays.decode(buf));
      }
   }
}
