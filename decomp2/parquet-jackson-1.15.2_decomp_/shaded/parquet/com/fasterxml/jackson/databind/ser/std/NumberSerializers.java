package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.io.NumberOutput;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class NumberSerializers {
   protected NumberSerializers() {
   }

   public static void addAll(Map allDeserializers) {
      allDeserializers.put(Integer.class.getName(), new IntegerSerializer(Integer.class));
      allDeserializers.put(Integer.TYPE.getName(), new IntegerSerializer(Integer.TYPE));
      allDeserializers.put(Long.class.getName(), new LongSerializer(Long.class));
      allDeserializers.put(Long.TYPE.getName(), new LongSerializer(Long.TYPE));
      allDeserializers.put(Byte.class.getName(), NumberSerializers.IntLikeSerializer.instance);
      allDeserializers.put(Byte.TYPE.getName(), NumberSerializers.IntLikeSerializer.instance);
      allDeserializers.put(Short.class.getName(), NumberSerializers.ShortSerializer.instance);
      allDeserializers.put(Short.TYPE.getName(), NumberSerializers.ShortSerializer.instance);
      allDeserializers.put(Double.class.getName(), new DoubleSerializer(Double.class));
      allDeserializers.put(Double.TYPE.getName(), new DoubleSerializer(Double.TYPE));
      allDeserializers.put(Float.class.getName(), NumberSerializers.FloatSerializer.instance);
      allDeserializers.put(Float.TYPE.getName(), NumberSerializers.FloatSerializer.instance);
   }

   public abstract static class Base extends StdScalarSerializer implements ContextualSerializer {
      protected final JsonParser.NumberType _numberType;
      protected final String _schemaType;
      protected final boolean _isInt;

      protected Base(Class cls, JsonParser.NumberType numberType, String schemaType) {
         super(cls, false);
         this._numberType = numberType;
         this._schemaType = schemaType;
         this._isInt = numberType == JsonParser.NumberType.INT || numberType == JsonParser.NumberType.LONG || numberType == JsonParser.NumberType.BIG_INTEGER;
      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode(this._schemaType, true);
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         if (this._isInt) {
            this.visitIntFormat(visitor, typeHint, this._numberType);
         } else {
            this.visitFloatFormat(visitor, typeHint, this._numberType);
         }

      }

      public JsonSerializer createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
         JsonFormat.Value format = this.findFormatOverrides(prov, property, this.handledType());
         if (format != null) {
            switch (format.getShape()) {
               case STRING:
                  if (this.handledType() == BigDecimal.class) {
                     return NumberSerializer.bigDecimalAsStringSerializer();
                  }

                  return ToStringSerializer.instance;
            }
         }

         return this;
      }
   }

   @JacksonStdImpl
   public static class ShortSerializer extends Base {
      static final ShortSerializer instance = new ShortSerializer();

      public ShortSerializer() {
         super(Short.class, JsonParser.NumberType.INT, "integer");
      }

      public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber((Short)value);
      }
   }

   @JacksonStdImpl
   public static class IntegerSerializer extends Base {
      public IntegerSerializer(Class type) {
         super(type, JsonParser.NumberType.INT, "integer");
      }

      public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber((Integer)value);
      }

      public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
         this.serialize(value, gen, provider);
      }
   }

   @JacksonStdImpl
   public static class IntLikeSerializer extends Base {
      static final IntLikeSerializer instance = new IntLikeSerializer();

      public IntLikeSerializer() {
         super(Number.class, JsonParser.NumberType.INT, "integer");
      }

      public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber(((Number)value).intValue());
      }
   }

   @JacksonStdImpl
   public static class LongSerializer extends Base {
      public LongSerializer(Class cls) {
         super(cls, JsonParser.NumberType.LONG, "integer");
      }

      public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber((Long)value);
      }
   }

   @JacksonStdImpl
   public static class FloatSerializer extends Base {
      static final FloatSerializer instance = new FloatSerializer();

      public FloatSerializer() {
         super(Float.class, JsonParser.NumberType.FLOAT, "number");
      }

      public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber((Float)value);
      }
   }

   @JacksonStdImpl
   public static class DoubleSerializer extends Base {
      public DoubleSerializer(Class cls) {
         super(cls, JsonParser.NumberType.DOUBLE, "number");
      }

      public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
         gen.writeNumber((Double)value);
      }

      public void serializeWithType(Object value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
         Double d = (Double)value;
         if (NumberOutput.notFinite(d)) {
            WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.VALUE_NUMBER_FLOAT));
            g.writeNumber(d);
            typeSer.writeTypeSuffix(g, typeIdDef);
         } else {
            g.writeNumber(d);
         }

      }

      /** @deprecated */
      @Deprecated
      public static boolean notFinite(double value) {
         return NumberOutput.notFinite(value);
      }
   }
}
