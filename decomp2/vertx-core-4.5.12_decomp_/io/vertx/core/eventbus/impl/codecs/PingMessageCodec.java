package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class PingMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, String s) {
   }

   public String decodeFromWire(int pos, Buffer buffer) {
      return null;
   }

   public String transform(String s) {
      return null;
   }

   public String name() {
      return "ping";
   }

   public byte systemCodecID() {
      return 1;
   }
}
