package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonArray;

public class JsonArrayMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, JsonArray jsonArray) {
      Buffer encoded = jsonArray.toBuffer();
      buffer.appendInt(encoded.length());
      buffer.appendBuffer(encoded);
   }

   public JsonArray decodeFromWire(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      pos += 4;
      return new JsonArray(buffer.slice(pos, pos + length));
   }

   public JsonArray transform(JsonArray jsonArray) {
      return jsonArray.copy();
   }

   public String name() {
      return "jsonarray";
   }

   public byte systemCodecID() {
      return 14;
   }
}
