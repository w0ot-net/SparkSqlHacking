package io.vertx.core.eventbus.impl.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class StringMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, String s) {
      byte[] strBytes = s.getBytes(CharsetUtil.UTF_8);
      buffer.appendInt(strBytes.length);
      buffer.appendBytes(strBytes);
   }

   public String decodeFromWire(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      pos += 4;
      byte[] bytes = buffer.getBytes(pos, pos + length);
      return new String(bytes, CharsetUtil.UTF_8);
   }

   public String transform(String s) {
      return s;
   }

   public String name() {
      return "string";
   }

   public byte systemCodecID() {
      return 9;
   }
}
