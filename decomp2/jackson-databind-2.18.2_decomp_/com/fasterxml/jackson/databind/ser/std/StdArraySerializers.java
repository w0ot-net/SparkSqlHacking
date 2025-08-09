package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class StdArraySerializers {
   protected static final HashMap _arraySerializers = new HashMap();

   protected StdArraySerializers() {
   }

   public static JsonSerializer findStandardImpl(Class cls) {
      return (JsonSerializer)_arraySerializers.get(cls.getName());
   }

   static {
      _arraySerializers.put(boolean[].class.getName(), new BooleanArraySerializer());
      _arraySerializers.put(byte[].class.getName(), new ByteArraySerializer());
      _arraySerializers.put(char[].class.getName(), new CharArraySerializer());
      _arraySerializers.put(short[].class.getName(), new ShortArraySerializer());
      _arraySerializers.put(int[].class.getName(), new IntArraySerializer());
      _arraySerializers.put(long[].class.getName(), new LongArraySerializer());
      _arraySerializers.put(float[].class.getName(), new FloatArraySerializer());
      _arraySerializers.put(double[].class.getName(), new DoubleArraySerializer());
   }

   protected abstract static class TypedPrimitiveArraySerializer extends ArraySerializerBase {
      protected TypedPrimitiveArraySerializer(Class cls) {
         super(cls);
      }

      protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public final ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
         return this;
      }
   }

   @JacksonStdImpl
   public static class BooleanArraySerializer extends ArraySerializerBase {
      private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);

      public BooleanArraySerializer() {
         super(boolean[].class);
      }

      protected BooleanArraySerializer(BooleanArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
         return new BooleanArraySerializer(this, prop, unwrapSingle);
      }

      public ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
         return this;
      }

      public JavaType getContentType() {
         return VALUE_TYPE;
      }

      public JsonSerializer getContentSerializer() {
         return null;
      }

      public boolean isEmpty(SerializerProvider prov, boolean[] value) {
         return value.length == 0;
      }

      public boolean hasSingleElement(boolean[] value) {
         return value.length == 1;
      }

      public final void serialize(boolean[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int len = value.length;
         if (len == 1 && this._shouldUnwrapSingle(provider)) {
            this.serializeContents(value, g, provider);
         } else {
            g.writeStartArray(value, len);
            this.serializeContents(value, g, provider);
            g.writeEndArray();
         }
      }

      public void serializeContents(boolean[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeBoolean(value[i]);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         ObjectNode o = this.createSchemaNode("array", true);
         o.set("items", this.createSchemaNode("boolean"));
         return o;
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.BOOLEAN);
      }
   }

   @JacksonStdImpl
   public static class ShortArraySerializer extends TypedPrimitiveArraySerializer {
      private static final JavaType VALUE_TYPE;

      public ShortArraySerializer() {
         super(short[].class);
      }

      public ShortArraySerializer(ShortArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
         return new ShortArraySerializer(this, prop, unwrapSingle);
      }

      public JavaType getContentType() {
         return VALUE_TYPE;
      }

      public JsonSerializer getContentSerializer() {
         return null;
      }

      public boolean isEmpty(SerializerProvider prov, short[] value) {
         return value.length == 0;
      }

      public boolean hasSingleElement(short[] value) {
         return value.length == 1;
      }

      public final void serialize(short[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int len = value.length;
         if (len == 1 && this._shouldUnwrapSingle(provider)) {
            this.serializeContents(value, g, provider);
         } else {
            g.writeStartArray(value, len);
            this.serializeContents(value, g, provider);
            g.writeEndArray();
         }
      }

      public void serializeContents(short[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeNumber(value[i]);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         ObjectNode o = this.createSchemaNode("array", true);
         return o.set("items", this.createSchemaNode("integer"));
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.INTEGER);
      }

      static {
         VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);
      }
   }

   @JacksonStdImpl
   public static class CharArraySerializer extends StdSerializer {
      public CharArraySerializer() {
         super(char[].class);
      }

      public boolean isEmpty(SerializerProvider prov, char[] value) {
         return value.length == 0;
      }

      public void serialize(char[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         if (provider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
            g.writeStartArray(value, value.length);
            this._writeArrayContents(g, value);
            g.writeEndArray();
         } else {
            g.writeString(value, 0, value.length);
         }

      }

      public void serializeWithType(char[] value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
         boolean asArray = provider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);
         WritableTypeId typeIdDef;
         if (asArray) {
            typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.START_ARRAY));
            this._writeArrayContents(g, value);
         } else {
            typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.VALUE_STRING));
            g.writeString(value, 0, value.length);
         }

         typeSer.writeTypeSuffix(g, typeIdDef);
      }

      private final void _writeArrayContents(JsonGenerator g, char[] value) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeString(value, i, 1);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         ObjectNode o = this.createSchemaNode("array", true);
         ObjectNode itemSchema = this.createSchemaNode("string");
         itemSchema.put("type", "string");
         return o.set("items", itemSchema);
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.STRING);
      }
   }

   @JacksonStdImpl
   public static class IntArraySerializer extends ArraySerializerBase {
      private static final JavaType VALUE_TYPE;

      public IntArraySerializer() {
         super(int[].class);
      }

      protected IntArraySerializer(IntArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
         return new IntArraySerializer(this, prop, unwrapSingle);
      }

      public ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
         return this;
      }

      public JavaType getContentType() {
         return VALUE_TYPE;
      }

      public JsonSerializer getContentSerializer() {
         return null;
      }

      public boolean isEmpty(SerializerProvider prov, int[] value) {
         return value.length == 0;
      }

      public boolean hasSingleElement(int[] value) {
         return value.length == 1;
      }

      public final void serialize(int[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int len = value.length;
         if (len == 1 && this._shouldUnwrapSingle(provider)) {
            this.serializeContents(value, g, provider);
         } else {
            g.writeArray(value, 0, value.length);
         }
      }

      public void serializeContents(int[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeNumber(value[i]);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("array", true).set("items", this.createSchemaNode("integer"));
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.INTEGER);
      }

      static {
         VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);
      }
   }

   @JacksonStdImpl
   public static class LongArraySerializer extends TypedPrimitiveArraySerializer {
      private static final JavaType VALUE_TYPE;

      public LongArraySerializer() {
         super(long[].class);
      }

      public LongArraySerializer(LongArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
         return new LongArraySerializer(this, prop, unwrapSingle);
      }

      public JavaType getContentType() {
         return VALUE_TYPE;
      }

      public JsonSerializer getContentSerializer() {
         return null;
      }

      public boolean isEmpty(SerializerProvider prov, long[] value) {
         return value.length == 0;
      }

      public boolean hasSingleElement(long[] value) {
         return value.length == 1;
      }

      public final void serialize(long[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int len = value.length;
         if (len == 1 && this._shouldUnwrapSingle(provider)) {
            this.serializeContents(value, g, provider);
         } else {
            g.writeArray(value, 0, value.length);
         }
      }

      public void serializeContents(long[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeNumber(value[i]);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("array", true).set("items", this.createSchemaNode("number", true));
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.NUMBER);
      }

      static {
         VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);
      }
   }

   @JacksonStdImpl
   public static class FloatArraySerializer extends TypedPrimitiveArraySerializer {
      private static final JavaType VALUE_TYPE;

      public FloatArraySerializer() {
         super(float[].class);
      }

      public FloatArraySerializer(FloatArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
         return new FloatArraySerializer(this, prop, unwrapSingle);
      }

      public JavaType getContentType() {
         return VALUE_TYPE;
      }

      public JsonSerializer getContentSerializer() {
         return null;
      }

      public boolean isEmpty(SerializerProvider prov, float[] value) {
         return value.length == 0;
      }

      public boolean hasSingleElement(float[] value) {
         return value.length == 1;
      }

      public final void serialize(float[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int len = value.length;
         if (len == 1 && this._shouldUnwrapSingle(provider)) {
            this.serializeContents(value, g, provider);
         } else {
            g.writeStartArray(value, len);
            this.serializeContents(value, g, provider);
            g.writeEndArray();
         }
      }

      public void serializeContents(float[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeNumber(value[i]);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("array", true).set("items", this.createSchemaNode("number"));
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.NUMBER);
      }

      static {
         VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);
      }
   }

   @JacksonStdImpl
   public static class DoubleArraySerializer extends ArraySerializerBase {
      private static final JavaType VALUE_TYPE;

      public DoubleArraySerializer() {
         super(double[].class);
      }

      protected DoubleArraySerializer(DoubleArraySerializer src, BeanProperty prop, Boolean unwrapSingle) {
         super(src, prop, unwrapSingle);
      }

      public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
         return new DoubleArraySerializer(this, prop, unwrapSingle);
      }

      public ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
         return this;
      }

      public JavaType getContentType() {
         return VALUE_TYPE;
      }

      public JsonSerializer getContentSerializer() {
         return null;
      }

      public boolean isEmpty(SerializerProvider prov, double[] value) {
         return value.length == 0;
      }

      public boolean hasSingleElement(double[] value) {
         return value.length == 1;
      }

      public final void serialize(double[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int len = value.length;
         if (len == 1 && this._shouldUnwrapSingle(provider)) {
            this.serializeContents(value, g, provider);
         } else {
            g.writeArray(value, 0, value.length);
         }
      }

      public void serializeContents(double[] value, JsonGenerator g, SerializerProvider provider) throws IOException {
         int i = 0;

         for(int len = value.length; i < len; ++i) {
            g.writeNumber(value[i]);
         }

      }

      /** @deprecated */
      @Deprecated
      public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
         return this.createSchemaNode("array", true).set("items", this.createSchemaNode("number"));
      }

      public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
         this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.NUMBER);
      }

      static {
         VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);
      }
   }
}
