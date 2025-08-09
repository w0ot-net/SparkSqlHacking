package io.vertx.core.eventbus.impl.codecs;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.JsonObject;

public class JsonObjectMessageCodec implements MessageCodec {
   public void encodeToWire(Buffer buffer, JsonObject jsonObject) {
      Buffer encoded = jsonObject.toBuffer();
      buffer.appendInt(encoded.length());
      buffer.appendBuffer(encoded);
   }

   public JsonObject decodeFromWire(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      pos += 4;
      return new JsonObject(buffer.slice(pos, pos + length));
   }

   public JsonObject transform(JsonObject jsonObject) {
      return jsonObject.copy();
   }

   public String name() {
      return "jsonobject";
   }

   public byte systemCodecID() {
      return 13;
   }
}
