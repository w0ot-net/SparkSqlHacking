package io.vertx.core.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.vertx.core.json.impl.JsonUtil;
import java.io.IOException;

class ByteArraySerializer extends JsonSerializer {
   public void serialize(byte[] value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeString(JsonUtil.BASE64_ENCODER.encodeToString(value));
   }
}
