package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class DoubleMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Double d) {
      buffer.appendDouble(d);
   }

   public Double decodeFromWire(int pos, Buffer buffer) {
      return buffer.getDouble(pos);
   }

   public Double transform(Double d) {
      return d;
   }

   public String name() {
      return "double";
   }

   public byte systemCodecID() {
      return 8;
   }
}
