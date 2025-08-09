package io.vertx.core.parsetools;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.Instant;

@VertxGen
public interface JsonEvent {
   JsonEventType type();

   String fieldName();

   Object value();

   boolean isNumber();

   Integer integerValue();

   Long longValue();

   Float floatValue();

   Double doubleValue();

   boolean isBoolean();

   Boolean booleanValue();

   boolean isString();

   String stringValue();

   Buffer binaryValue();

   @GenIgnore({"permitted-type"})
   Instant instantValue();

   boolean isNull();

   boolean isObject();

   JsonObject objectValue();

   boolean isArray();

   JsonArray arrayValue();

   Object mapTo(Class var1);

   @GenIgnore({"permitted-type"})
   Object mapTo(TypeReference var1);
}
