package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;

public abstract class ReferenceTypeDeserializer extends StdDeserializer implements ContextualDeserializer {
   private static final long serialVersionUID = 2L;
   protected final JavaType _fullType;
   protected final ValueInstantiator _valueInstantiator;
   protected final TypeDeserializer _valueTypeDeserializer;
   protected final JsonDeserializer _valueDeserializer;

   public ReferenceTypeDeserializer(JavaType fullType, ValueInstantiator vi, TypeDeserializer typeDeser, JsonDeserializer deser) {
      super(fullType);
      this._valueInstantiator = vi;
      this._fullType = fullType;
      this._valueDeserializer = deser;
      this._valueTypeDeserializer = typeDeser;
   }

   /** @deprecated */
   @Deprecated
   public ReferenceTypeDeserializer(JavaType fullType, TypeDeserializer typeDeser, JsonDeserializer deser) {
      this(fullType, (ValueInstantiator)null, typeDeser, deser);
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      JsonDeserializer<?> deser = this._valueDeserializer;
      deser = this.findConvertingContentDeserializer(ctxt, property, deser);
      if (deser == null) {
         deser = ctxt.findContextualValueDeserializer(this._fullType.getReferencedType(), property);
      } else {
         deser = ctxt.handleSecondaryContextualization(deser, property, this._fullType.getReferencedType());
      }

      TypeDeserializer typeDeser = this._valueTypeDeserializer;
      if (typeDeser != null) {
         typeDeser = typeDeser.forProperty(property);
      }

      return deser == this._valueDeserializer && typeDeser == this._valueTypeDeserializer ? this : this.withResolved(typeDeser, deser);
   }

   public AccessPattern getNullAccessPattern() {
      return AccessPattern.DYNAMIC;
   }

   public AccessPattern getEmptyAccessPattern() {
      return AccessPattern.DYNAMIC;
   }

   protected abstract ReferenceTypeDeserializer withResolved(TypeDeserializer var1, JsonDeserializer var2);

   public abstract Object getNullValue(DeserializationContext var1) throws JsonMappingException;

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this.getNullValue(ctxt);
   }

   public abstract Object referenceValue(Object var1);

   public abstract Object updateReference(Object var1, Object var2);

   public abstract Object getReferenced(Object var1);

   public ValueInstantiator getValueInstantiator() {
      return this._valueInstantiator;
   }

   public JavaType getValueType() {
      return this._fullType;
   }

   public LogicalType logicalType() {
      return this._valueDeserializer != null ? this._valueDeserializer.logicalType() : super.logicalType();
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return this._valueDeserializer == null ? null : this._valueDeserializer.supportsUpdate(config);
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (this._valueInstantiator != null) {
         T value = (T)this._valueInstantiator.createUsingDefault(ctxt);
         return this.deserialize(p, ctxt, value);
      } else {
         Object contents = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p, ctxt) : this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer);
         return this.referenceValue(contents);
      }
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt, Object reference) throws IOException {
      Boolean B = this._valueDeserializer.supportsUpdate(ctxt.getConfig());
      Object contents;
      if (!B.equals(Boolean.FALSE) && this._valueTypeDeserializer == null) {
         contents = this.getReferenced(reference);
         if (contents == null) {
            contents = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p, ctxt) : this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer);
            return this.referenceValue(contents);
         }

         contents = this._valueDeserializer.deserialize(p, ctxt, contents);
      } else {
         contents = this._valueTypeDeserializer == null ? this._valueDeserializer.deserialize(p, ctxt) : this._valueDeserializer.deserializeWithType(p, ctxt, this._valueTypeDeserializer);
      }

      return this.updateReference(reference, contents);
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      if (p.hasToken(JsonToken.VALUE_NULL)) {
         return this.getNullValue(ctxt);
      } else {
         return this._valueTypeDeserializer == null ? this.deserialize(p, ctxt) : this.referenceValue(this._valueTypeDeserializer.deserializeTypedFromAny(p, ctxt));
      }
   }
}
