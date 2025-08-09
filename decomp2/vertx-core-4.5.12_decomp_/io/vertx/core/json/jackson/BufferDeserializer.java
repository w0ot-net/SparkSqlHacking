package io.vertx.core.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.impl.JsonUtil;
import java.io.IOException;
import java.time.Instant;

class BufferDeserializer extends JsonDeserializer {
   public Buffer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      String text = p.getText();

      try {
         return Buffer.buffer(JsonUtil.BASE64_DECODER.decode(text));
      } catch (IllegalArgumentException var5) {
         throw new InvalidFormatException(p, "Expected a base64 encoded byte array", text, Instant.class);
      }
   }
}
