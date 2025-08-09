package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class BufferMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, Buffer b) {
      buffer.appendInt(b.length());
      buffer.appendBuffer(b);
   }

   public Buffer decodeFromWire(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      pos += 4;
      return buffer.getBuffer(pos, pos + length);
   }

   public Buffer transform(Buffer b) {
      return b.copy();
   }

   public String name() {
      return "buffer";
   }

   public byte systemCodecID() {
      return 11;
   }
}
