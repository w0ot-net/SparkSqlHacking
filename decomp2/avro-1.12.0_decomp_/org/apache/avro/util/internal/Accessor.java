package org.apache.avro.util.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.apache.avro.JsonProperties;
import org.apache.avro.Schema;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.io.parsing.ResolvingGrammarGenerator;

public class Accessor {
   private static volatile JsonPropertiesAccessor jsonPropertiesAccessor;
   private static volatile FieldAccessor fieldAccessor;
   private static volatile ResolvingGrammarGeneratorAccessor resolvingGrammarGeneratorAccessor;

   public static void setAccessor(JsonPropertiesAccessor accessor) {
      if (jsonPropertiesAccessor != null) {
         throw new IllegalStateException("JsonPropertiesAccessor already initialized");
      } else {
         jsonPropertiesAccessor = accessor;
      }
   }

   public static void setAccessor(FieldAccessor accessor) {
      if (fieldAccessor != null) {
         throw new IllegalStateException("FieldAccessor already initialized");
      } else {
         fieldAccessor = accessor;
      }
   }

   private static FieldAccessor fieldAccessor() {
      if (fieldAccessor == null) {
         ensureLoaded(Schema.Field.class);
      }

      return fieldAccessor;
   }

   public static void setAccessor(ResolvingGrammarGeneratorAccessor accessor) {
      if (resolvingGrammarGeneratorAccessor != null) {
         throw new IllegalStateException("ResolvingGrammarGeneratorAccessor already initialized");
      } else {
         resolvingGrammarGeneratorAccessor = accessor;
      }
   }

   private static ResolvingGrammarGeneratorAccessor resolvingGrammarGeneratorAccessor() {
      if (resolvingGrammarGeneratorAccessor == null) {
         ensureLoaded(ResolvingGrammarGenerator.class);
      }

      return resolvingGrammarGeneratorAccessor;
   }

   private static void ensureLoaded(Class c) {
      try {
         Class.forName(c.getName());
      } catch (ClassNotFoundException var2) {
      }

   }

   public static void addProp(JsonProperties props, String name, JsonNode value) {
      jsonPropertiesAccessor.addProp(props, name, value);
   }

   public static JsonNode defaultValue(Schema.Field field) {
      return fieldAccessor.defaultValue(field);
   }

   public static void encode(Encoder e, Schema s, JsonNode n) throws IOException {
      resolvingGrammarGeneratorAccessor().encode(e, s, n);
   }

   public static Schema.Field createField(String name, Schema schema, String doc, JsonNode defaultValue, boolean validate, Schema.Field.Order order) {
      return fieldAccessor().createField(name, schema, doc, defaultValue, validate, order);
   }

   public static Schema.Field createField(String name, Schema schema, String doc, JsonNode defaultValue) {
      return fieldAccessor().createField(name, schema, doc, defaultValue);
   }

   public abstract static class JsonPropertiesAccessor {
      protected abstract void addProp(JsonProperties props, String name, JsonNode value);
   }

   public abstract static class FieldAccessor {
      protected abstract JsonNode defaultValue(Schema.Field field);

      protected abstract Schema.Field createField(String name, Schema schema, String doc, JsonNode defaultValue, boolean validate, Schema.Field.Order order);

      protected abstract Schema.Field createField(String name, Schema schema, String doc, JsonNode defaultValue);
   }

   public abstract static class ResolvingGrammarGeneratorAccessor {
      protected abstract void encode(Encoder e, Schema s, JsonNode n) throws IOException;
   }

   public abstract static class EncoderFactoryAccessor {
      protected abstract JsonEncoder jsonEncoder(EncoderFactory factory, Schema schema, JsonGenerator gen) throws IOException;
   }
}
