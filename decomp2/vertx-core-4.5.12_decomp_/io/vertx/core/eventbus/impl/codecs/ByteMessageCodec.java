package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class ByteMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Byte b) {
      buffer.appendByte(b);
   }

   public Byte decodeFromWire(int pos, Buffer buffer) {
      return buffer.getByte(pos);
   }

   public Byte transform(Byte b) {
      return b;
   }

   public String name() {
      return "byte";
   }

   public byte systemCodecID() {
      return 2;
   }
}
