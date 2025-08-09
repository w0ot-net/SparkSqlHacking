package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class CharMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Character chr) {
      buffer.appendShort((short)chr);
   }

   public Character decodeFromWire(int pos, Buffer buffer) {
      return (char)buffer.getShort(pos);
   }

   public Character transform(Character c) {
      return c;
   }

   public String name() {
      return "char";
   }

   public byte systemCodecID() {
      return 10;
   }
}
