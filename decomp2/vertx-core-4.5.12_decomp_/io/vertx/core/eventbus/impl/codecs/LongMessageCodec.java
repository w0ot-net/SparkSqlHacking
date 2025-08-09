package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class LongMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Long l) {
      buffer.appendLong(l);
   }

   public Long decodeFromWire(int pos, Buffer buffer) {
      return buffer.getLong(pos);
   }

   public Long transform(Long l) {
      return l;
   }

   public String name() {
      return "long";
   }

   public byte systemCodecID() {
      return 6;
   }
}
