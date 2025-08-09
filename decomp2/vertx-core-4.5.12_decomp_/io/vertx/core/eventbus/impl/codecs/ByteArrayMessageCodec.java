package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class ByteArrayMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, byte[] byteArray) {
      buffer.appendInt(byteArray.length);
      buffer.appendBytes(byteArray);
   }

   public byte[] decodeFromWire(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      pos += 4;
      return buffer.getBytes(pos, pos + length);
   }

   public byte[] transform(byte[] bytes) {
      byte[] copied = new byte[bytes.length];
      System.arraycopy(bytes, 0, copied, 0, bytes.length);
      return copied;
   }

   public String name() {
      return "bytearray";
   }

   public byte systemCodecID() {
      return 12;
   }
}
