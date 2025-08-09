package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class BooleanMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Boolean b) {
      buffer.appendByte((byte)(b ? 0 : 1));
   }

   public Boolean decodeFromWire(int pos, Buffer buffer) {
      return buffer.getByte(pos) == 0;
   }

   public Boolean transform(Boolean b) {
      return b;
   }

   public String name() {
      return "boolean";
   }

   public byte systemCodecID() {
      return 3;
   }
}
