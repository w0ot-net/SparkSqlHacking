package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class ShortMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Short s) {
      buffer.appendShort(s);
   }

   public Short decodeFromWire(int pos, Buffer buffer) {
      return buffer.getShort(pos);
   }

   public Short transform(Short s) {
      return s;
   }

   public String name() {
      return "short";
   }

   public byte systemCodecID() {
      return 4;
   }
}
