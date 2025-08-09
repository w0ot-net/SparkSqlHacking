package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContextualSerializer;

public abstract class StaticListSerializerBase extends StdSerializer implements ContextualSerializer {
   protected final Boolean _unwrapSingle;

   protected StaticListSerializerBase(Class cls) {
      super(cls, false);
      this._unwrapSingle = null;
   }

   protected StaticListSerializerBase(StaticListSerializerBase src, Boolean unwrapSingle) {
      super((StdSerializer)src);
      this._unwrapSingle = unwrapSingle;
   }

   public abstract JsonSerializer _withResolved(BeanProperty var1, Boolean var2);

   public JsonSerializer createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
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
         unwrapSingle = format.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
      }

      ser = this.findContextualConvertingSerializer(serializers, property, ser);
      if (ser == null) {
         ser = serializers.findContentValueSerializer(String.class, property);
      }

      if (this.isDefaultSerializer(ser)) {
         return (JsonSerializer)(Objects.equals(unwrapSingle, this._unwrapSingle) ? this : this._withResolved(property, unwrapSingle));
      } else {
         return new CollectionSerializer(serializers.constructType(String.class), true, (TypeSerializer)null, ser);
      }
   }

   public boolean isEmpty(SerializerProvider provider, Collection value) {
      return value == null || value.isEmpty();
   }

   /** @deprecated */
   @Deprecated
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
      return this.createSchemaNode("array", true).set("items", this.contentSchema());
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
      if (v2 != null) {
         this.acceptContentVisitor(v2);
      }

   }

   protected abstract JsonNode contentSchema();

   protected abstract void acceptContentVisitor(JsonArrayFormatVisitor var1) throws JsonMappingException;

   public abstract void serializeWithType(Collection var1, JsonGenerator var2, SerializerProvider var3, TypeSerializer var4) throws IOException;
}
