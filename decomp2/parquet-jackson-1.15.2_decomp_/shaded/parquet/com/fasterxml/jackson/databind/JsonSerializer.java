package shaded.parquet.com.fasterxml.jackson.databind;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.NameTransformer;

public abstract class JsonSerializer implements JsonFormatVisitable {
   public JsonSerializer unwrappingSerializer(NameTransformer unwrapper) {
      return this;
   }

   public JsonSerializer replaceDelegatee(JsonSerializer delegatee) {
      throw new UnsupportedOperationException();
   }

   public JsonSerializer withFilterId(Object filterId) {
      return this;
   }

   public JsonSerializer withIgnoredProperties(Set ignoredProperties) {
      return this;
   }

   public abstract void serialize(Object var1, JsonGenerator var2, SerializerProvider var3) throws IOException;

   public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
      Class<?> clz = this.handledType();
      if (clz == null) {
         clz = value.getClass();
      }

      serializers.reportBadDefinition(clz, String.format("Type id handling not implemented for type %s (by serializer of type %s)", clz.getName(), this.getClass().getName()));
   }

   public Class handledType() {
      return null;
   }

   /** @deprecated */
   @Deprecated
   public boolean isEmpty(Object value) {
      return this.isEmpty((SerializerProvider)null, value);
   }

   public boolean isEmpty(SerializerProvider provider, Object value) {
      return value == null;
   }

   public boolean usesObjectId() {
      return false;
   }

   public boolean isUnwrappingSerializer() {
      return false;
   }

   public JsonSerializer getDelegatee() {
      return null;
   }

   public Iterator properties() {
      return ClassUtil.emptyIterator();
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType type) throws JsonMappingException {
      visitor.expectAnyFormat(type);
   }

   public abstract static class None extends JsonSerializer {
   }
}
