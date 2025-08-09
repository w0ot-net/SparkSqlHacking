package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class NullMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, String s) {
   }

   public String decodeFromWire(int pos, Buffer buffer) {
      return null;
   }

   public String transform(String s) {
      return null;
   }

   public String name() {
      return "null";
   }

   public byte systemCodecID() {
      return 0;
   }
}
