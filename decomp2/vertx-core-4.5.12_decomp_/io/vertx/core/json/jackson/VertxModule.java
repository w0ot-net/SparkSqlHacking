package io.vertx.core.json.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.Instant;

public class VertxModule extends SimpleModule {
   public VertxModule() {
      this.addSerializer(JsonObject.class, new JsonObjectSerializer());
      this.addSerializer(JsonArray.class, new JsonArraySerializer());
      this.addSerializer(Instant.class, new InstantSerializer());
      this.addDeserializer(Instant.class, new InstantDeserializer());
      this.addSerializer(byte[].class, new ByteArraySerializer());
      this.addDeserializer(byte[].class, new ByteArrayDeserializer());
      this.addSerializer(Buffer.class, new BufferSerializer());
      this.addDeserializer(Buffer.class, new BufferDeserializer());
   }
}
