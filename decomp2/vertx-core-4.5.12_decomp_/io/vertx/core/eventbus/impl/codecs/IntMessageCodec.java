package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class IntMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Integer i) {
      buffer.appendInt(i);
   }

   public Integer decodeFromWire(int pos, Buffer buffer) {
      return buffer.getInt(pos);
   }

   public Integer transform(Integer i) {
      return i;
   }

   public String name() {
      return "int";
   }

   public byte systemCodecID() {
      return 5;
   }
}
