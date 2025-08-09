package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.util.Objects;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.type.WritableTypeId;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContainerSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.ContextualSerializer;

public abstract class ArraySerializerBase extends ContainerSerializer implements ContextualSerializer {
   protected final BeanProperty _property;
   protected final Boolean _unwrapSingle;

   protected ArraySerializerBase(Class cls) {
      super(cls);
      this._property = null;
      this._unwrapSingle = null;
   }

   /** @deprecated */
   @Deprecated
   protected ArraySerializerBase(Class cls, BeanProperty property) {
      super(cls);
      this._property = property;
      this._unwrapSingle = null;
   }

   protected ArraySerializerBase(ArraySerializerBase src) {
      super(src._handledType, false);
      this._property = src._property;
      this._unwrapSingle = src._unwrapSingle;
   }

   protected ArraySerializerBase(ArraySerializerBase src, BeanProperty property, Boolean unwrapSingle) {
      super(src._handledType, false);
      this._property = property;
      this._unwrapSingle = unwrapSingle;
   }

   /** @deprecated */
   @Deprecated
   protected ArraySerializerBase(ArraySerializerBase src, BeanProperty property) {
      super(src._handledType, false);
      this._property = property;
      this._unwrapSingle = src._unwrapSingle;
   }

   public abstract JsonSerializer _withResolved(BeanProperty var1, Boolean var2);

   public JsonSerializer createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
      Boolean unwrapSingle = null;
      if (property != null) {
         JsonFormat.Value format = this.findFormatOverrides(serializers, property, this.handledType());
         if (format != null) {
            unwrapSingle = format.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
            if (!Objects.equals(unwrapSingle, this._unwrapSingle)) {
               return this._withResolved(property, unwrapSingle);
            }
         }
      }

      return this;
   }

   public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      if (this._shouldUnwrapSingle(provider) && this.hasSingleElement(value)) {
         this.serializeContents(value, gen, provider);
      } else {
         gen.writeStartArray(value);
         this.serializeContents(value, gen, provider);
         gen.writeEndArray();
      }
   }

   public final void serializeWithType(Object value, JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(value, JsonToken.START_ARRAY));
      g.assignCurrentValue(value);
      this.serializeContents(value, g, provider);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }

   protected abstract void serializeContents(Object var1, JsonGenerator var2, SerializerProvider var3) throws IOException;

   protected final boolean _shouldUnwrapSingle(SerializerProvider provider) {
      return this._unwrapSingle == null ? provider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) : this._unwrapSingle;
   }
}
