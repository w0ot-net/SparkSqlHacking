package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.BeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionAction;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import shaded.parquet.com.fasterxml.jackson.databind.util.EnumResolver;

@JacksonStdImpl
public class EnumDeserializer extends StdScalarDeserializer implements ContextualDeserializer {
   private static final long serialVersionUID = 1L;
   protected Object[] _enumsByIndex;
   private final Enum _enumDefaultValue;
   protected final CompactStringObjectMap _lookupByName;
   protected volatile CompactStringObjectMap _lookupByToString;
   protected final Boolean _caseInsensitive;
   private Boolean _useDefaultValueForUnknownEnum;
   private Boolean _useNullForUnknownEnum;
   protected final boolean _isFromIntValue;
   protected final CompactStringObjectMap _lookupByEnumNaming;

   /** @deprecated */
   @Deprecated
   public EnumDeserializer(EnumResolver byNameResolver, Boolean caseInsensitive) {
      this(byNameResolver, Boolean.TRUE.equals(caseInsensitive), (EnumResolver)null);
   }

   /** @deprecated */
   @Deprecated
   public EnumDeserializer(EnumResolver byNameResolver, boolean caseInsensitive, EnumResolver byEnumNamingResolver) {
      super(byNameResolver.getEnumClass());
      this._lookupByName = byNameResolver.constructLookup();
      this._enumsByIndex = byNameResolver.getRawEnums();
      this._enumDefaultValue = byNameResolver.getDefaultValue();
      this._caseInsensitive = caseInsensitive;
      this._isFromIntValue = byNameResolver.isFromIntValue();
      this._lookupByEnumNaming = byEnumNamingResolver == null ? null : byEnumNamingResolver.constructLookup();
      this._lookupByToString = null;
   }

   public EnumDeserializer(EnumResolver byNameResolver, boolean caseInsensitive, EnumResolver byEnumNamingResolver, EnumResolver toStringResolver) {
      super(byNameResolver.getEnumClass());
      this._lookupByName = byNameResolver.constructLookup();
      this._enumsByIndex = byNameResolver.getRawEnums();
      this._enumDefaultValue = byNameResolver.getDefaultValue();
      this._caseInsensitive = caseInsensitive;
      this._isFromIntValue = byNameResolver.isFromIntValue();
      this._lookupByEnumNaming = byEnumNamingResolver == null ? null : byEnumNamingResolver.constructLookup();
      this._lookupByToString = toStringResolver == null ? null : toStringResolver.constructLookup();
   }

   protected EnumDeserializer(EnumDeserializer base, Boolean caseInsensitive, Boolean useDefaultValueForUnknownEnum, Boolean useNullForUnknownEnum) {
      super((StdScalarDeserializer)base);
      this._lookupByName = base._lookupByName;
      this._enumsByIndex = base._enumsByIndex;
      this._enumDefaultValue = base._enumDefaultValue;
      this._caseInsensitive = Boolean.TRUE.equals(caseInsensitive);
      this._isFromIntValue = base._isFromIntValue;
      this._useDefaultValueForUnknownEnum = useDefaultValueForUnknownEnum;
      this._useNullForUnknownEnum = useNullForUnknownEnum;
      this._lookupByEnumNaming = base._lookupByEnumNaming;
      this._lookupByToString = base._lookupByToString;
   }

   /** @deprecated */
   @Deprecated
   protected EnumDeserializer(EnumDeserializer base, Boolean caseInsensitive) {
      this(base, caseInsensitive, (Boolean)null, (Boolean)null);
   }

   /** @deprecated */
   @Deprecated
   public EnumDeserializer(EnumResolver byNameResolver) {
      this((EnumResolver)byNameResolver, (Boolean)null);
   }

   /** @deprecated */
   @Deprecated
   public static JsonDeserializer deserializerForCreator(DeserializationConfig config, Class enumClass, AnnotatedMethod factory) {
      return deserializerForCreator(config, enumClass, factory, (ValueInstantiator)null, (SettableBeanProperty[])null);
   }

   public static JsonDeserializer deserializerForCreator(DeserializationConfig config, Class enumClass, AnnotatedMethod factory, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps) {
      if (config.canOverrideAccessModifiers()) {
         ClassUtil.checkAndFixAccess(factory.getMember(), config.isEnabled((MapperFeature)MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
      }

      return new FactoryBasedEnumDeserializer(enumClass, factory, factory.getParameterType(0), valueInstantiator, creatorProps);
   }

   public static JsonDeserializer deserializerForNoArgsCreator(DeserializationConfig config, Class enumClass, AnnotatedMethod factory) {
      if (config.canOverrideAccessModifiers()) {
         ClassUtil.checkAndFixAccess(factory.getMember(), config.isEnabled((MapperFeature)MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
      }

      return new FactoryBasedEnumDeserializer(enumClass, factory);
   }

   public EnumDeserializer withResolved(Boolean caseInsensitive, Boolean useDefaultValueForUnknownEnum, Boolean useNullForUnknownEnum) {
      return Objects.equals(this._caseInsensitive, caseInsensitive) && Objects.equals(this._useDefaultValueForUnknownEnum, useDefaultValueForUnknownEnum) && Objects.equals(this._useNullForUnknownEnum, useNullForUnknownEnum) ? this : new EnumDeserializer(this, caseInsensitive, useDefaultValueForUnknownEnum, useNullForUnknownEnum);
   }

   /** @deprecated */
   @Deprecated
   public EnumDeserializer withResolved(Boolean caseInsensitive) {
      return this.withResolved(caseInsensitive, this._useDefaultValueForUnknownEnum, this._useNullForUnknownEnum);
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      Boolean caseInsensitive = (Boolean)Optional.ofNullable(this.findFormatFeature(ctxt, property, this.handledType(), JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)).orElse(this._caseInsensitive);
      Boolean useDefaultValueForUnknownEnum = (Boolean)Optional.ofNullable(this.findFormatFeature(ctxt, property, this.handledType(), JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)).orElse(this._useDefaultValueForUnknownEnum);
      Boolean useNullForUnknownEnum = (Boolean)Optional.ofNullable(this.findFormatFeature(ctxt, property, this.handledType(), JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)).orElse(this._useNullForUnknownEnum);
      return this.withResolved(caseInsensitive, useDefaultValueForUnknownEnum, useNullForUnknownEnum);
   }

   public boolean isCachable() {
      return true;
   }

   public LogicalType logicalType() {
      return LogicalType.Enum;
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return this._enumDefaultValue;
   }

   public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.hasToken(JsonToken.VALUE_STRING)) {
         return this._fromString(p, ctxt, p.getText());
      } else if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
         return this._isFromIntValue ? this._fromString(p, ctxt, p.getText()) : this._fromInteger(p, ctxt, p.getIntValue());
      } else {
         return p.isExpectedStartObjectToken() ? this._fromString(p, ctxt, ctxt.extractScalarFromObject(p, this, this._valueClass)) : this._deserializeOther(p, ctxt);
      }
   }

   protected Object _fromString(JsonParser p, DeserializationContext ctxt, String text) throws IOException {
      CompactStringObjectMap lookup = this._resolveCurrentLookup(ctxt);
      Object result = lookup.find(text);
      if (result == null) {
         String trimmed = text.trim();
         if (trimmed == text || (result = lookup.find(trimmed)) == null) {
            return this._deserializeAltString(p, ctxt, lookup, trimmed);
         }
      }

      return result;
   }

   private CompactStringObjectMap _resolveCurrentLookup(DeserializationContext ctxt) {
      if (this._lookupByEnumNaming != null) {
         return this._lookupByEnumNaming;
      } else {
         return ctxt.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? this._getToStringLookup(ctxt) : this._lookupByName;
      }
   }

   protected Object _fromInteger(JsonParser p, DeserializationContext ctxt, int index) throws IOException {
      CoercionAction act = ctxt.findCoercionAction(this.logicalType(), this.handledType(), CoercionInputShape.Integer);
      if (act == CoercionAction.Fail) {
         if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            return ctxt.handleWeirdNumberValue(this._enumClass(), index, "not allowed to deserialize Enum value out of number: disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow");
         }

         this._checkCoercionFail(ctxt, act, this.handledType(), index, "Integer value (" + index + ")");
      }

      switch (act) {
         case AsNull:
            return null;
         case AsEmpty:
            return this.getEmptyValue(ctxt);
         case TryConvert:
         default:
            if (index >= 0 && index < this._enumsByIndex.length) {
               return this._enumsByIndex[index];
            } else if (this.useDefaultValueForUnknownEnum(ctxt)) {
               return this._enumDefaultValue;
            } else {
               return !this.useNullForUnknownEnum(ctxt) ? ctxt.handleWeirdNumberValue(this._enumClass(), index, "index value outside legal index range [0..%s]", this._enumsByIndex.length - 1) : null;
            }
      }
   }

   private final Object _deserializeAltString(JsonParser p, DeserializationContext ctxt, CompactStringObjectMap lookup, String nameOrig) throws IOException {
      String name = nameOrig.trim();
      if (name.isEmpty()) {
         if (this.useDefaultValueForUnknownEnum(ctxt)) {
            return this._enumDefaultValue;
         } else if (this.useNullForUnknownEnum(ctxt)) {
            return null;
         } else {
            CoercionAction act;
            if (nameOrig.isEmpty()) {
               act = this._findCoercionFromEmptyString(ctxt);
               act = this._checkCoercionFail(ctxt, act, this.handledType(), nameOrig, "empty String (\"\")");
            } else {
               act = this._findCoercionFromBlankString(ctxt);
               act = this._checkCoercionFail(ctxt, act, this.handledType(), nameOrig, "blank String (all whitespace)");
            }

            switch (act) {
               case AsNull:
               default:
                  return null;
               case AsEmpty:
               case TryConvert:
                  return this.getEmptyValue(ctxt);
            }
         }
      } else {
         if (Boolean.TRUE.equals(this._caseInsensitive)) {
            Object match = lookup.findCaseInsensitive(name);
            if (match != null) {
               return match;
            }
         }

         if (!ctxt.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS) && !this._isFromIntValue) {
            char c = name.charAt(0);
            if (c >= '0' && c <= '9' && (c != '0' || name.length() <= 1)) {
               try {
                  int index = Integer.parseInt(name);
                  if (!ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
                     return ctxt.handleWeirdStringValue(this._enumClass(), name, "value looks like quoted Enum index, but `MapperFeature.ALLOW_COERCION_OF_SCALARS` prevents use");
                  }

                  if (index >= 0 && index < this._enumsByIndex.length) {
                     return this._enumsByIndex[index];
                  }
               } catch (NumberFormatException var8) {
               }
            }
         }

         if (this.useDefaultValueForUnknownEnum(ctxt)) {
            return this._enumDefaultValue;
         } else {
            return this.useNullForUnknownEnum(ctxt) ? null : ctxt.handleWeirdStringValue(this._enumClass(), name, "not one of the values accepted for Enum class: %s", lookup.keys());
         }
      }
   }

   protected Object _deserializeOther(JsonParser p, DeserializationContext ctxt) throws IOException {
      return p.hasToken(JsonToken.START_ARRAY) ? this._deserializeFromArray(p, ctxt) : ctxt.handleUnexpectedToken(this._enumClass(), p);
   }

   protected Class _enumClass() {
      return this.handledType();
   }

   /** @deprecated */
   @Deprecated
   protected CompactStringObjectMap _getToStringLookup(DeserializationContext ctxt) {
      CompactStringObjectMap lookup = this._lookupByToString;
      if (lookup == null) {
         synchronized(this) {
            lookup = this._lookupByToString;
            if (lookup == null) {
               lookup = EnumResolver.constructUsingToString(ctxt.getConfig(), this._enumClass()).constructLookup();
               this._lookupByToString = lookup;
            }
         }
      }

      return lookup;
   }

   protected boolean useNullForUnknownEnum(DeserializationContext ctxt) {
      return this._useNullForUnknownEnum != null ? this._useNullForUnknownEnum : ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
   }

   protected boolean useDefaultValueForUnknownEnum(DeserializationContext ctxt) {
      if (this._enumDefaultValue != null) {
         return this._useDefaultValueForUnknownEnum != null ? this._useDefaultValueForUnknownEnum : ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
      } else {
         return false;
      }
   }
}
