package io.vertx.core.parsetools.impl;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.json.jackson.JacksonFactory;
import io.vertx.core.parsetools.JsonEvent;
import io.vertx.core.parsetools.JsonEventType;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class JsonEventImpl implements JsonEvent {
   private final JsonToken token;
   private final JsonEventType type;
   private final String field;
   private final Object value;

   public JsonEventImpl(JsonToken token, JsonEventType type, String field, Object value) {
      this.token = token;
      this.type = type;
      this.field = field;
      this.value = value;
   }

   public JsonToken token() {
      return this.token;
   }

   public JsonEventType type() {
      return this.type;
   }

   public String fieldName() {
      return this.field;
   }

   public Object value() {
      return this.value;
   }

   public boolean isNumber() {
      return this.value instanceof Number;
   }

   public boolean isBoolean() {
      return this.value instanceof Boolean;
   }

   public boolean isString() {
      return this.value instanceof String;
   }

   public boolean isNull() {
      return this.type == JsonEventType.VALUE && this.value == null;
   }

   public boolean isObject() {
      return this.value instanceof JsonObject;
   }

   public boolean isArray() {
      return this.value instanceof JsonArray;
   }

   public Object mapTo(Class type) {
      try {
         return JacksonFactory.CODEC.fromValue(this.value, type);
      } catch (Exception e) {
         throw new DecodeException(e.getMessage(), e);
      }
   }

   public Object mapTo(TypeReference type) {
      try {
         return JacksonFactory.CODEC.fromValue(this.value, type);
      } catch (Exception e) {
         throw new DecodeException(e.getMessage(), e);
      }
   }

   public Integer integerValue() {
      if (this.value != null) {
         Number number = (Number)this.value;
         return this.value instanceof Integer ? (Integer)this.value : number.intValue();
      } else {
         return null;
      }
   }

   public Long longValue() {
      if (this.value != null) {
         Number number = (Number)this.value;
         return this.value instanceof Integer ? (Long)this.value : number.longValue();
      } else {
         return null;
      }
   }

   public Float floatValue() {
      if (this.value != null) {
         Number number = (Number)this.value;
         return this.value instanceof Float ? (Float)this.value : number.floatValue();
      } else {
         return null;
      }
   }

   public Double doubleValue() {
      if (this.value != null) {
         Number number = (Number)this.value;
         return this.value instanceof Double ? (Double)this.value : number.doubleValue();
      } else {
         return null;
      }
   }

   public Boolean booleanValue() {
      return (Boolean)this.value;
   }

   public String stringValue() {
      return (String)this.value;
   }

   public Buffer binaryValue() {
      return this.value != null ? Buffer.buffer(JsonUtil.BASE64_DECODER.decode((String)this.value)) : null;
   }

   public Instant instantValue() {
      return this.value != null ? Instant.from(DateTimeFormatter.ISO_INSTANT.parse((CharSequence)this.value)) : null;
   }

   public JsonObject objectValue() {
      return (JsonObject)this.value;
   }

   public JsonArray arrayValue() {
      return (JsonArray)this.value;
   }
}
