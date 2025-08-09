package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Objects;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.NullValueProvider;
import shaded.parquet.com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;

public class EnumSetDeserializer extends StdDeserializer implements ContextualDeserializer {
   private static final long serialVersionUID = 2L;
   protected final JavaType _enumType;
   protected JsonDeserializer _enumDeserializer;
   protected final TypeDeserializer _valueTypeDeserializer;
   protected final NullValueProvider _nullProvider;
   protected final boolean _skipNullValues;
   protected final Boolean _unwrapSingle;

   public EnumSetDeserializer(JavaType enumType, JsonDeserializer deser, TypeDeserializer valueTypeDeser) {
      super(EnumSet.class);
      this._enumType = enumType;
      if (!enumType.isEnumType()) {
         throw new IllegalArgumentException("Type " + enumType + " not Java Enum type");
      } else {
         this._enumDeserializer = deser;
         this._valueTypeDeserializer = valueTypeDeser;
         this._unwrapSingle = null;
         this._nullProvider = null;
         this._skipNullValues = false;
      }
   }

   /** @deprecated */
   @Deprecated
   public EnumSetDeserializer(JavaType enumType, JsonDeserializer deser) {
      this((JavaType)enumType, deser, (TypeDeserializer)null);
   }

   /** @deprecated */
   @Deprecated
   protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer deser, Boolean unwrapSingle) {
      this(base, deser, base._nullProvider, unwrapSingle);
   }

   protected EnumSetDeserializer(EnumSetDeserializer base, JsonDeserializer deser, NullValueProvider nuller, Boolean unwrapSingle) {
      super((StdDeserializer)base);
      this._enumType = base._enumType;
      this._enumDeserializer = deser;
      this._valueTypeDeserializer = base._valueTypeDeserializer;
      this._nullProvider = nuller;
      this._skipNullValues = NullsConstantProvider.isSkipper(nuller);
      this._unwrapSingle = unwrapSingle;
   }

   public EnumSetDeserializer withDeserializer(JsonDeserializer deser) {
      return this._enumDeserializer == deser ? this : new EnumSetDeserializer(this, deser, this._nullProvider, this._unwrapSingle);
   }

   public EnumSetDeserializer withResolved(JsonDeserializer deser, TypeDeserializer valueTypeDeser, NullValueProvider nuller, Boolean unwrapSingle) {
      return Objects.equals(this._unwrapSingle, unwrapSingle) && this._enumDeserializer == deser && this._valueTypeDeserializer == valueTypeDeser && this._nullProvider == deser ? this : new EnumSetDeserializer(this, deser, nuller, unwrapSingle);
   }

   /** @deprecated */
   @Deprecated
   public EnumSetDeserializer withResolved(JsonDeserializer deser, NullValueProvider nuller, Boolean unwrapSingle) {
      return this.withResolved(deser, this._valueTypeDeserializer, nuller, unwrapSingle);
   }

   public boolean isCachable() {
      return this._enumType.getValueHandler() == null && this._valueTypeDeserializer == null;
   }

   public LogicalType logicalType() {
      return LogicalType.Collection;
   }

   public Boolean supportsUpdate(DeserializationConfig config) {
      return Boolean.TRUE;
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this.constructSet();
   }

   public AccessPattern getEmptyAccessPattern() {
      return AccessPattern.DYNAMIC;
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      Boolean unwrapSingle = this.findFormatFeature(ctxt, property, EnumSet.class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      JsonDeserializer<?> deser = this._enumDeserializer;
      if (deser == null) {
         deser = ctxt.findContextualValueDeserializer(this._enumType, property);
      } else {
         deser = ctxt.handleSecondaryContextualization(deser, property, this._enumType);
      }

      TypeDeserializer valueTypeDeser = this._valueTypeDeserializer;
      if (valueTypeDeser != null) {
         valueTypeDeser = valueTypeDeser.forProperty(property);
      }

      return this.withResolved(deser, valueTypeDeser, this.findContentNullProvider(ctxt, property, deser), unwrapSingle);
   }

   public EnumSet deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      EnumSet result = this.constructSet();
      return !p.isExpectedStartArrayToken() ? this.handleNonArray(p, ctxt, result) : this._deserialize(p, ctxt, result);
   }

   public EnumSet deserialize(JsonParser p, DeserializationContext ctxt, EnumSet result) throws IOException {
      return !p.isExpectedStartArrayToken() ? this.handleNonArray(p, ctxt, result) : this._deserialize(p, ctxt, result);
   }

   protected final EnumSet _deserialize(JsonParser p, DeserializationContext ctxt, EnumSet result) throws IOException {
      TypeDeserializer typeDeser = this._valueTypeDeserializer;

      try {
         JsonToken t;
         while((t = p.nextToken()) != JsonToken.END_ARRAY) {
            Enum<?> value;
            if (t == JsonToken.VALUE_NULL) {
               if (this._skipNullValues) {
                  continue;
               }

               value = (Enum)this._nullProvider.getNullValue(ctxt);
            } else if (typeDeser == null) {
               value = (Enum)this._enumDeserializer.deserialize(p, ctxt);
            } else {
               value = (Enum)this._enumDeserializer.deserializeWithType(p, ctxt, typeDeser);
            }

            if (value != null) {
               result.add(value);
            }
         }

         return result;
      } catch (Exception e) {
         throw JsonMappingException.wrapWithPath(e, result, result.size());
      }
   }

   public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      return typeDeserializer.deserializeTypedFromArray(p, ctxt);
   }

   private EnumSet constructSet() {
      return EnumSet.noneOf(this._enumType.getRawClass());
   }

   protected EnumSet handleNonArray(JsonParser p, DeserializationContext ctxt, EnumSet result) throws IOException {
      boolean canWrap = this._unwrapSingle == Boolean.TRUE || this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      if (!canWrap) {
         return (EnumSet)ctxt.handleUnexpectedToken(EnumSet.class, p);
      } else if (p.hasToken(JsonToken.VALUE_NULL)) {
         return (EnumSet)ctxt.handleUnexpectedToken(this._enumType, p);
      } else {
         try {
            Enum<?> value = (Enum)this._enumDeserializer.deserialize(p, ctxt);
            if (value != null) {
               result.add(value);
            }

            return result;
         } catch (Exception e) {
            throw JsonMappingException.wrapWithPath(e, result, result.size());
         }
      }
   }
}
