package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class FloatMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Float f) {
      buffer.appendFloat(f);
   }

   public Float decodeFromWire(int pos, Buffer buffer) {
      return buffer.getFloat(pos);
   }

   public Float transform(Float f) {
      return f;
   }

   public String name() {
      return "float";
   }

   public byte systemCodecID() {
      return 7;
   }
}
