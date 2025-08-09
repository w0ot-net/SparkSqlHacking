package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContainerSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContextualSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase implements ContextualSerializer {
   private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
   public static final StringArraySerializer instance = new StringArraySerializer();
   protected final JsonSerializer _elementSerializer;

   protected StringArraySerializer() {
      super(String[].class);
      this._elementSerializer = null;
   }

   public StringArraySerializer(StringArraySerializer src, BeanProperty prop, JsonSerializer ser, Boolean unwrapSingle) {
      super(src, prop, unwrapSingle);
      this._elementSerializer = ser;
   }

   public JsonSerializer _withResolved(BeanProperty prop, Boolean unwrapSingle) {
      return new StringArraySerializer(this, prop, this._elementSerializer, unwrapSingle);
   }

   public ContainerSerializer _withValueTypeSerializer(TypeSerializer vts) {
      return this;
   }

   public JsonSerializer createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<?> ser = null;
      if (property != null) {
         AnnotationIntrospector ai = provider.getAnnotationIntrospector();
         AnnotatedMember m = property.getMember();
         if (m != null) {
            Object serDef = ai.findContentSerializer(m);
            if (serDef != null) {
               ser = provider.serializerInstance(m, serDef);
            }
         }
      }

      Boolean unwrapSingle = this.findFormatFeature(provider, property, String[].class, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
      if (ser == null) {
         ser = this._elementSerializer;
      }

      ser = this.findContextualConvertingSerializer(provider, property, ser);
      if (ser == null) {
         ser = provider.findContentValueSerializer(String.class, property);
      }

      if (this.isDefaultSerializer(ser)) {
         ser = null;
      }

      return ser == this._elementSerializer && Objects.equals(unwrapSingle, this._unwrapSingle) ? this : new StringArraySerializer(this, property, ser, unwrapSingle);
   }

   public JavaType getContentType() {
      return VALUE_TYPE;
   }

   public JsonSerializer getContentSerializer() {
      return this._elementSerializer;
   }

   public boolean isEmpty(SerializerProvider prov, String[] value) {
      return value.length == 0;
   }

   public boolean hasSingleElement(String[] value) {
      return value.length == 1;
   }

   public final void serialize(String[] value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      int len = value.length;
      if (len != 1 || (this._unwrapSingle != null || !provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE) {
         gen.writeStartArray(value, len);
         this.serializeContents(value, gen, provider);
         gen.writeEndArray();
      } else {
         this.serializeContents(value, gen, provider);
      }
   }

   public void serializeContents(String[] value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      int len = value.length;
      if (len != 0) {
         if (this._elementSerializer != null) {
            this.serializeContentsSlow(value, gen, provider, this._elementSerializer);
         } else {
            for(int i = 0; i < len; ++i) {
               String str = value[i];
               if (str == null) {
                  gen.writeNull();
               } else {
                  gen.writeString(value[i]);
               }
            }

         }
      }
   }

   private void serializeContentsSlow(String[] value, JsonGenerator gen, SerializerProvider provider, JsonSerializer ser) throws IOException {
      int i = 0;

      for(int len = value.length; i < len; ++i) {
         String str = value[i];
         if (str == null) {
            provider.defaultSerializeNull(gen);
         } else {
            ser.serialize(value[i], gen, provider);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
      return this.createSchemaNode("array", true).set("items", this.createSchemaNode("string"));
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      this.visitArrayFormat(visitor, typeHint, JsonFormatTypes.STRING);
   }
}
