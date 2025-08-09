package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

public abstract class AsArraySerializerBase extends ContainerSerializer implements ContextualSerializer {
   protected final JavaType _elementType;
   protected final BeanProperty _property;
   protected final boolean _staticTyping;
   protected final Boolean _unwrapSingle;
   protected final TypeSerializer _valueTypeSerializer;
   protected final JsonSerializer _elementSerializer;
   protected PropertySerializerMap _dynamicSerializers;

   protected AsArraySerializerBase(Class cls, JavaType et, boolean staticTyping, TypeSerializer vts, JsonSerializer elementSerializer) {
      this(cls, et, staticTyping, vts, (BeanProperty)null, elementSerializer, (Boolean)null);
   }

   /** @deprecated */
   @Deprecated
   protected AsArraySerializerBase(Class cls, JavaType et, boolean staticTyping, TypeSerializer vts, BeanProperty property, JsonSerializer elementSerializer) {
      this(cls, et, staticTyping, vts, property, elementSerializer, (Boolean)null);
   }

   protected AsArraySerializerBase(Class cls, JavaType elementType, boolean staticTyping, TypeSerializer vts, BeanProperty property, JsonSerializer elementSerializer, Boolean unwrapSingle) {
      super(cls, false);
      this._elementType = elementType;
      this._staticTyping = staticTyping || elementType != null && elementType.isFinal();
      this._valueTypeSerializer = vts;
      this._property = property;
      this._elementSerializer = elementSerializer;
      this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
      this._unwrapSingle = unwrapSingle;
   }

   protected AsArraySerializerBase(AsArraySerializerBase src, BeanProperty property, TypeSerializer vts, JsonSerializer elementSerializer, Boolean unwrapSingle) {
      super((ContainerSerializer)src);
      this._elementType = src._elementType;
      this._staticTyping = src._staticTyping;
      this._valueTypeSerializer = vts;
      this._property = property;
      this._elementSerializer = elementSerializer;
      this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
      this._unwrapSingle = unwrapSingle;
   }

   /** @deprecated */
   @Deprecated
   protected AsArraySerializerBase(AsArraySerializerBase src, BeanProperty property, TypeSerializer vts, JsonSerializer elementSerializer) {
      this(src, property, vts, elementSerializer, src._unwrapSingle);
   }

   /** @deprecated */
   @Deprecated
   public final AsArraySerializerBase withResolved(BeanProperty property, TypeSerializer vts, JsonSerializer elementSerializer) {
      return this.withResolved(property, vts, elementSerializer, this._unwrapSingle);
   }

   public abstract AsArraySerializerBase withResolved(BeanProperty var1, TypeSerializer var2, JsonSerializer var3, Boolean var4);

   public JsonSerializer createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
      TypeSerializer typeSer = this._valueTypeSerializer;
      if (typeSer != null) {
         typeSer = typeSer.forProperty(property);
      }

      JsonSerializer<?> ser = null;
      Boolean unwrapSingle = null;
      if (property != null) {
         AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
         AnnotatedMember m = property.getMember();
         if (m != null) {
            Object serDef = intr.findContentSerializer(m);
            if (serDef != null) {
               ser = serializers.serializerInstance(m, serDef);
            }
         }
      }

      JsonFormat.Value format = this.findFormatOverrides(serializers, property, this.handledType());
      if (format != null) {
         unwrapSingle = format.getFeature(Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
      }

      if (ser == null) {
         ser = this._elementSerializer;
      }

      ser = this.findContextualConvertingSerializer(serializers, property, ser);
      if (ser == null && this._elementType != null && this._staticTyping && !this._elementType.isJavaLangObject()) {
         ser = serializers.findContentValueSerializer(this._elementType, property);
      }

      return ser == this._elementSerializer && property == this._property && this._valueTypeSerializer == typeSer && Objects.equals(this._unwrapSingle, unwrapSingle) ? this : this.withResolved(property, typeSer, ser, unwrapSingle);
   }

   public JavaType getContentType() {
      return this._elementType;
   }

   public JsonSerializer getContentSerializer() {
      return this._elementSerializer;
   }

   public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      if (provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) && this.hasSingleElement(value)) {
         this.serializeContents(value, gen, provider);
      } else {
         gen.writeStartArray(value);
         this.serializeContents(value, gen, provider);
         gen.writeEndArray();
      }
   }

   public void serializeWithType(Object value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.START_ARRAY));
      g.assignCurrentValue(value);
      this.serializeContents(value, g, provider);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }

   protected abstract void serializeContents(Object var1, JsonGenerator var2, SerializerProvider var3) throws IOException;

   /** @deprecated */
   @Deprecated
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
      ObjectNode o = this.createSchemaNode("array", true);
      if (this._elementSerializer != null) {
         JsonNode schemaNode = null;
         if (this._elementSerializer instanceof SchemaAware) {
            schemaNode = ((SchemaAware)this._elementSerializer).getSchema(provider, (Type)null);
         }

         if (schemaNode == null) {
            schemaNode = JsonSchema.getDefaultSchemaNode();
         }

         o.set("items", schemaNode);
      }

      return o;
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JsonSerializer<?> valueSer = this._elementSerializer;
      if (valueSer == null && this._elementType != null) {
         valueSer = visitor.getProvider().findContentValueSerializer(this._elementType, this._property);
      }

      this.visitArrayFormat(visitor, typeHint, valueSer, this._elementType);
   }

   protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap map, Class type, SerializerProvider provider) throws JsonMappingException {
      PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
      if (map != result.map) {
         this._dynamicSerializers = result.map;
      }

      return result.serializer;
   }

   protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap map, JavaType type, SerializerProvider provider) throws JsonMappingException {
      PropertySerializerMap.SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
      if (map != result.map) {
         this._dynamicSerializers = result.map;
      }

      return result.serializer;
   }
}
