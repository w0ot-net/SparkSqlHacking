package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;

public class StdJdkSerializers {
   public static Collection all() {
      HashMap<Class<?>, Object> sers = new HashMap();
      sers.put(URL.class, new ToStringSerializer(URL.class));
      sers.put(URI.class, new ToStringSerializer(URI.class));
      sers.put(Currency.class, new ToStringSerializer(Currency.class));
      sers.put(UUID.class, new UUIDSerializer());
      sers.put(Pattern.class, new ToStringSerializer(Pattern.class));
      sers.put(Locale.class, new ToStringSerializer(Locale.class));
      sers.put(AtomicBoolean.class, AtomicBooleanSerializer.class);
      sers.put(AtomicInteger.class, AtomicIntegerSerializer.class);
      sers.put(AtomicLong.class, AtomicLongSerializer.class);
      sers.put(File.class, FileSerializer.class);
      sers.put(Class.class, ClassSerializer.class);
      sers.put(Void.class, NullSerializer.instance);
      sers.put(Void.TYPE, NullSerializer.instance);
      return sers.entrySet();
   }

   public static class AtomicBooleanSerializer extends StdScalarSerializer {
      public AtomicBooleanSerializer() {
         super(AtomicBoolean.class, false);
      }

      public void serialize(AtomicBoolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeBoolean(value.get());
      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("boolean", true);
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         visitor.expectBooleanFormat(typeHint);
      }
   }

   public static class AtomicIntegerSerializer extends StdScalarSerializer {
      public AtomicIntegerSerializer() {
         super(AtomicInteger.class, false);
      }

      public void serialize(AtomicInteger value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber(value.get());
      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("integer", true);
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.INT);
      }
   }

   public static class AtomicLongSerializer extends StdScalarSerializer {
      public AtomicLongSerializer() {
         super(AtomicLong.class, false);
      }

      public void serialize(AtomicLong value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber(value.get());
      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("integer", true);
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitIntFormat(visitor, typeHint, JsonParser.NumberType.LONG);
      }
   }
}
