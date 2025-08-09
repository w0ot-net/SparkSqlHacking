package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.core.StreamReadFeature;
import shaded.parquet.com.fasterxml.jackson.core.io.NumberInput;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionAction;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.AccessPattern;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public class NumberDeserializers {
   private static final HashSet _classNames = new HashSet();

   public static JsonDeserializer find(Class rawType, String clsName) {
      if (rawType.isPrimitive()) {
         if (rawType == Integer.TYPE) {
            return NumberDeserializers.IntegerDeserializer.primitiveInstance;
         }

         if (rawType == Boolean.TYPE) {
            return NumberDeserializers.BooleanDeserializer.primitiveInstance;
         }

         if (rawType == Long.TYPE) {
            return NumberDeserializers.LongDeserializer.primitiveInstance;
         }

         if (rawType == Double.TYPE) {
            return NumberDeserializers.DoubleDeserializer.primitiveInstance;
         }

         if (rawType == Character.TYPE) {
            return NumberDeserializers.CharacterDeserializer.primitiveInstance;
         }

         if (rawType == Byte.TYPE) {
            return NumberDeserializers.ByteDeserializer.primitiveInstance;
         }

         if (rawType == Short.TYPE) {
            return NumberDeserializers.ShortDeserializer.primitiveInstance;
         }

         if (rawType == Float.TYPE) {
            return NumberDeserializers.FloatDeserializer.primitiveInstance;
         }

         if (rawType == Void.TYPE) {
            return NullifyingDeserializer.instance;
         }
      } else {
         if (!_classNames.contains(clsName)) {
            return null;
         }

         if (rawType == Integer.class) {
            return NumberDeserializers.IntegerDeserializer.wrapperInstance;
         }

         if (rawType == Boolean.class) {
            return NumberDeserializers.BooleanDeserializer.wrapperInstance;
         }

         if (rawType == Long.class) {
            return NumberDeserializers.LongDeserializer.wrapperInstance;
         }

         if (rawType == Double.class) {
            return NumberDeserializers.DoubleDeserializer.wrapperInstance;
         }

         if (rawType == Character.class) {
            return NumberDeserializers.CharacterDeserializer.wrapperInstance;
         }

         if (rawType == Byte.class) {
            return NumberDeserializers.ByteDeserializer.wrapperInstance;
         }

         if (rawType == Short.class) {
            return NumberDeserializers.ShortDeserializer.wrapperInstance;
         }

         if (rawType == Float.class) {
            return NumberDeserializers.FloatDeserializer.wrapperInstance;
         }

         if (rawType == Number.class) {
            return NumberDeserializers.NumberDeserializer.instance;
         }

         if (rawType == BigDecimal.class) {
            return NumberDeserializers.BigDecimalDeserializer.instance;
         }

         if (rawType == BigInteger.class) {
            return NumberDeserializers.BigIntegerDeserializer.instance;
         }
      }

      throw new IllegalArgumentException("Internal error: can't find deserializer for " + rawType.getName());
   }

   static {
      Class<?>[] numberTypes = new Class[]{Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};

      for(Class cls : numberTypes) {
         _classNames.add(cls.getName());
      }

   }

   protected abstract static class PrimitiveOrWrapperDeserializer extends StdScalarDeserializer {
      private static final long serialVersionUID = 1L;
      protected final LogicalType _logicalType;
      protected final Object _nullValue;
      protected final Object _emptyValue;
      protected final boolean _primitive;

      protected PrimitiveOrWrapperDeserializer(Class vc, LogicalType logicalType, Object nvl, Object empty) {
         super(vc);
         this._logicalType = logicalType;
         this._nullValue = nvl;
         this._emptyValue = empty;
         this._primitive = vc.isPrimitive();
      }

      /** @deprecated */
      @Deprecated
      protected PrimitiveOrWrapperDeserializer(Class vc, Object nvl, Object empty) {
         this(vc, LogicalType.OtherScalar, nvl, empty);
      }

      public AccessPattern getNullAccessPattern() {
         if (this._primitive) {
            return AccessPattern.DYNAMIC;
         } else {
            return this._nullValue == null ? AccessPattern.ALWAYS_NULL : AccessPattern.CONSTANT;
         }
      }

      public final Object getNullValue(DeserializationContext ctxt) throws JsonMappingException {
         if (this._primitive && ctxt.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            ctxt.reportInputMismatch((JsonDeserializer)this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", ClassUtil.classNameOf(this.handledType()));
         }

         return this._nullValue;
      }

      public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
         return this._emptyValue;
      }

      public final LogicalType logicalType() {
         return this._logicalType;
      }
   }

   @JacksonStdImpl
   public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final BooleanDeserializer primitiveInstance;
      static final BooleanDeserializer wrapperInstance;

      public BooleanDeserializer(Class cls, Boolean nvl) {
         super(cls, LogicalType.Boolean, nvl, Boolean.FALSE);
      }

      public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         JsonToken t = p.currentToken();
         if (t == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
         } else if (t == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
         } else {
            return this._primitive ? this._parseBooleanPrimitive(p, ctxt) : this._parseBoolean(p, ctxt, this._valueClass);
         }
      }

      public Boolean deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
         JsonToken t = p.currentToken();
         if (t == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
         } else if (t == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
         } else {
            return this._primitive ? this._parseBooleanPrimitive(p, ctxt) : this._parseBoolean(p, ctxt, this._valueClass);
         }
      }

      static {
         primitiveInstance = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
         wrapperInstance = new BooleanDeserializer(Boolean.class, (Boolean)null);
      }
   }

   @JacksonStdImpl
   public static class ByteDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final ByteDeserializer primitiveInstance;
      static final ByteDeserializer wrapperInstance;

      public ByteDeserializer(Class cls, Byte nvl) {
         super(cls, LogicalType.Integer, nvl, (byte)0);
      }

      public Byte deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.isExpectedNumberIntToken()) {
            return p.getByteValue();
         } else {
            return this._primitive ? this._parseBytePrimitive(p, ctxt) : this._parseByte(p, ctxt);
         }
      }

      protected Byte _parseByte(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            case 9:
            case 10:
            default:
               return (Byte)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return (Byte)this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               return p.getByteValue();
            case 8:
               CoercionAction act = this._checkFloatToIntCoercion(p, ctxt, this._valueClass);
               if (act == CoercionAction.AsNull) {
                  return (Byte)this.getNullValue(ctxt);
               }

               if (act == CoercionAction.AsEmpty) {
                  return (Byte)this.getEmptyValue(ctxt);
               }

               return p.getByteValue();
            case 11:
               return (Byte)this.getNullValue(ctxt);
         }

         CoercionAction act = this._checkFromStringCoercion(ctxt, text);
         if (act == CoercionAction.AsNull) {
            return (Byte)this.getNullValue(ctxt);
         } else if (act == CoercionAction.AsEmpty) {
            return (Byte)this.getEmptyValue(ctxt);
         } else {
            text = text.trim();
            if (this._checkTextualNull(ctxt, text)) {
               return (Byte)this.getNullValue(ctxt);
            } else {
               int value;
               try {
                  value = NumberInput.parseInt(text);
               } catch (IllegalArgumentException var7) {
                  return (Byte)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Byte value");
               }

               return this._byteOverflow(value) ? (Byte)ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value cannot be represented as 8-bit value") : (byte)value;
            }
         }
      }

      static {
         primitiveInstance = new ByteDeserializer(Byte.TYPE, (byte)0);
         wrapperInstance = new ByteDeserializer(Byte.class, (Byte)null);
      }
   }

   @JacksonStdImpl
   public static class ShortDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final ShortDeserializer primitiveInstance;
      static final ShortDeserializer wrapperInstance;

      public ShortDeserializer(Class cls, Short nvl) {
         super(cls, LogicalType.Integer, nvl, Short.valueOf((short)0));
      }

      public Short deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.isExpectedNumberIntToken()) {
            return p.getShortValue();
         } else {
            return this._primitive ? this._parseShortPrimitive(p, ctxt) : this._parseShort(p, ctxt);
         }
      }

      protected Short _parseShort(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            case 9:
            case 10:
            default:
               return (Short)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return (Short)this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               return p.getShortValue();
            case 8:
               CoercionAction act = this._checkFloatToIntCoercion(p, ctxt, this._valueClass);
               if (act == CoercionAction.AsNull) {
                  return (Short)this.getNullValue(ctxt);
               }

               if (act == CoercionAction.AsEmpty) {
                  return (Short)this.getEmptyValue(ctxt);
               }

               return p.getShortValue();
            case 11:
               return (Short)this.getNullValue(ctxt);
         }

         CoercionAction act = this._checkFromStringCoercion(ctxt, text);
         if (act == CoercionAction.AsNull) {
            return (Short)this.getNullValue(ctxt);
         } else if (act == CoercionAction.AsEmpty) {
            return (Short)this.getEmptyValue(ctxt);
         } else {
            text = text.trim();
            if (this._checkTextualNull(ctxt, text)) {
               return (Short)this.getNullValue(ctxt);
            } else {
               int value;
               try {
                  value = NumberInput.parseInt(text);
               } catch (IllegalArgumentException var7) {
                  return (Short)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Short value");
               }

               return this._shortOverflow(value) ? (Short)ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value cannot be represented as 16-bit value") : (short)value;
            }
         }
      }

      static {
         primitiveInstance = new ShortDeserializer(Short.TYPE, Short.valueOf((short)0));
         wrapperInstance = new ShortDeserializer(Short.class, (Short)null);
      }
   }

   @JacksonStdImpl
   public static class CharacterDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final CharacterDeserializer primitiveInstance;
      static final CharacterDeserializer wrapperInstance;

      public CharacterDeserializer(Class cls, Character nvl) {
         super(cls, LogicalType.Integer, nvl, '\u0000');
      }

      public Character deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            default:
               return (Character)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return (Character)this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               CoercionAction act = ctxt.findCoercionAction(this.logicalType(), this._valueClass, CoercionInputShape.Integer);
               switch (act) {
                  case Fail:
                     this._checkCoercionFail(ctxt, act, this._valueClass, p.getNumberValue(), "Integer value (" + p.getText() + ")");
                  case AsNull:
                     return (Character)this.getNullValue(ctxt);
                  case AsEmpty:
                     return (Character)this.getEmptyValue(ctxt);
                  default:
                     int value = p.getIntValue();
                     if (value >= 0 && value <= 65535) {
                        return (char)value;
                     }

                     return (Character)ctxt.handleWeirdNumberValue(this.handledType(), value, "value outside valid Character range (0x0000 - 0xFFFF)");
               }
            case 11:
               if (this._primitive) {
                  this._verifyNullForPrimitive(ctxt);
               }

               return (Character)this.getNullValue(ctxt);
         }

         if (text.length() == 1) {
            return text.charAt(0);
         } else {
            CoercionAction act = this._checkFromStringCoercion(ctxt, text);
            if (act == CoercionAction.AsNull) {
               return (Character)this.getNullValue(ctxt);
            } else if (act == CoercionAction.AsEmpty) {
               return (Character)this.getEmptyValue(ctxt);
            } else {
               text = text.trim();
               return this._checkTextualNull(ctxt, text) ? (Character)this.getNullValue(ctxt) : (Character)ctxt.handleWeirdStringValue(this.handledType(), text, "Expected either Integer value code or 1-character String");
            }
         }
      }

      static {
         primitiveInstance = new CharacterDeserializer(Character.TYPE, '\u0000');
         wrapperInstance = new CharacterDeserializer(Character.class, (Character)null);
      }
   }

   @JacksonStdImpl
   public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final IntegerDeserializer primitiveInstance;
      static final IntegerDeserializer wrapperInstance;

      public IntegerDeserializer(Class cls, Integer nvl) {
         super(cls, LogicalType.Integer, nvl, 0);
      }

      public boolean isCachable() {
         return true;
      }

      public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.isExpectedNumberIntToken()) {
            return p.getIntValue();
         } else {
            return this._primitive ? this._parseIntPrimitive(p, ctxt) : this._parseInteger(p, ctxt, Integer.class);
         }
      }

      public Integer deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
         if (p.isExpectedNumberIntToken()) {
            return p.getIntValue();
         } else {
            return this._primitive ? this._parseIntPrimitive(p, ctxt) : this._parseInteger(p, ctxt, Integer.class);
         }
      }

      static {
         primitiveInstance = new IntegerDeserializer(Integer.TYPE, 0);
         wrapperInstance = new IntegerDeserializer(Integer.class, (Integer)null);
      }
   }

   @JacksonStdImpl
   public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final LongDeserializer primitiveInstance;
      static final LongDeserializer wrapperInstance;

      public LongDeserializer(Class cls, Long nvl) {
         super(cls, LogicalType.Integer, nvl, 0L);
      }

      public boolean isCachable() {
         return true;
      }

      public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.isExpectedNumberIntToken()) {
            return p.getLongValue();
         } else {
            return this._primitive ? this._parseLongPrimitive(p, ctxt) : this._parseLong(p, ctxt, Long.class);
         }
      }

      static {
         primitiveInstance = new LongDeserializer(Long.TYPE, 0L);
         wrapperInstance = new LongDeserializer(Long.class, (Long)null);
      }
   }

   @JacksonStdImpl
   public static class FloatDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final FloatDeserializer primitiveInstance;
      static final FloatDeserializer wrapperInstance;

      public FloatDeserializer(Class cls, Float nvl) {
         super(cls, LogicalType.Float, nvl, 0.0F);
      }

      public Float deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return p.getFloatValue();
         } else {
            return this._primitive ? this._parseFloatPrimitive(p, ctxt) : this._parseFloat(p, ctxt);
         }
      }

      protected final Float _parseFloat(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            case 9:
            case 10:
            default:
               return (Float)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return (Float)this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               CoercionAction act = this._checkIntToFloatCoercion(p, ctxt, this._valueClass);
               if (act == CoercionAction.AsNull) {
                  return (Float)this.getNullValue(ctxt);
               }

               if (act == CoercionAction.AsEmpty) {
                  return (Float)this.getEmptyValue(ctxt);
               }
            case 8:
               return p.getFloatValue();
            case 11:
               return (Float)this.getNullValue(ctxt);
         }

         Float nan = this._checkFloatSpecialValue(text);
         if (nan != null) {
            return nan;
         } else {
            CoercionAction act = this._checkFromStringCoercion(ctxt, text);
            if (act == CoercionAction.AsNull) {
               return (Float)this.getNullValue(ctxt);
            } else if (act == CoercionAction.AsEmpty) {
               return (Float)this.getEmptyValue(ctxt);
            } else {
               text = text.trim();
               if (this._checkTextualNull(ctxt, text)) {
                  return (Float)this.getNullValue(ctxt);
               } else {
                  if (NumberInput.looksLikeValidNumber(text)) {
                     p.streamReadConstraints().validateFPLength(text.length());

                     try {
                        return NumberInput.parseFloat(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
                     } catch (IllegalArgumentException var6) {
                     }
                  }

                  return (Float)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid `Float` value");
               }
            }
         }
      }

      static {
         primitiveInstance = new FloatDeserializer(Float.TYPE, 0.0F);
         wrapperInstance = new FloatDeserializer(Float.class, (Float)null);
      }
   }

   @JacksonStdImpl
   public static class DoubleDeserializer extends PrimitiveOrWrapperDeserializer {
      private static final long serialVersionUID = 1L;
      static final DoubleDeserializer primitiveInstance;
      static final DoubleDeserializer wrapperInstance;

      public DoubleDeserializer(Class cls, Double nvl) {
         super(cls, LogicalType.Float, nvl, (double)0.0F);
      }

      public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return p.getDoubleValue();
         } else {
            return this._primitive ? this._parseDoublePrimitive(p, ctxt) : this._parseDouble(p, ctxt);
         }
      }

      public Double deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
         if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
            return p.getDoubleValue();
         } else {
            return this._primitive ? this._parseDoublePrimitive(p, ctxt) : this._parseDouble(p, ctxt);
         }
      }

      protected final Double _parseDouble(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            case 9:
            case 10:
            default:
               return (Double)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return (Double)this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               CoercionAction act = this._checkIntToFloatCoercion(p, ctxt, this._valueClass);
               if (act == CoercionAction.AsNull) {
                  return (Double)this.getNullValue(ctxt);
               }

               if (act == CoercionAction.AsEmpty) {
                  return (Double)this.getEmptyValue(ctxt);
               }
            case 8:
               return p.getDoubleValue();
            case 11:
               return (Double)this.getNullValue(ctxt);
         }

         Double nan = this._checkDoubleSpecialValue(text);
         if (nan != null) {
            return nan;
         } else {
            CoercionAction act = this._checkFromStringCoercion(ctxt, text);
            if (act == CoercionAction.AsNull) {
               return (Double)this.getNullValue(ctxt);
            } else if (act == CoercionAction.AsEmpty) {
               return (Double)this.getEmptyValue(ctxt);
            } else {
               text = text.trim();
               if (this._checkTextualNull(ctxt, text)) {
                  return (Double)this.getNullValue(ctxt);
               } else {
                  if (NumberInput.looksLikeValidNumber(text)) {
                     p.streamReadConstraints().validateFPLength(text.length());

                     try {
                        return _parseDouble(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
                     } catch (IllegalArgumentException var6) {
                     }
                  }

                  return (Double)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid `Double` value");
               }
            }
         }
      }

      static {
         primitiveInstance = new DoubleDeserializer(Double.TYPE, (double)0.0F);
         wrapperInstance = new DoubleDeserializer(Double.class, (Double)null);
      }
   }

   @JacksonStdImpl
   public static class NumberDeserializer extends StdScalarDeserializer {
      public static final NumberDeserializer instance = new NumberDeserializer();

      public NumberDeserializer() {
         super(Number.class);
      }

      public final LogicalType logicalType() {
         return LogicalType.Integer;
      }

      public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            default:
               return ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               if (ctxt.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                  return this._coerceIntegral(p, ctxt);
               }

               return p.getNumberValue();
            case 8:
               if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) && !p.isNaN()) {
                  return p.getDecimalValue();
               }

               return p.getNumberValue();
         }

         CoercionAction act = this._checkFromStringCoercion(ctxt, text);
         if (act == CoercionAction.AsNull) {
            return this.getNullValue(ctxt);
         } else if (act == CoercionAction.AsEmpty) {
            return this.getEmptyValue(ctxt);
         } else {
            text = text.trim();
            if (this._hasTextualNull(text)) {
               return this.getNullValue(ctxt);
            } else if (this._isPosInf(text)) {
               return Double.POSITIVE_INFINITY;
            } else if (this._isNegInf(text)) {
               return Double.NEGATIVE_INFINITY;
            } else if (this._isNaN(text)) {
               return Double.NaN;
            } else {
               try {
                  if (this._isIntNumber(text)) {
                     p.streamReadConstraints().validateIntegerLength(text.length());
                     if (ctxt.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                        return NumberInput.parseBigInteger(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
                     }

                     long value = NumberInput.parseLong(text);
                     if (!ctxt.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) && value <= 2147483647L && value >= -2147483648L) {
                        return (int)value;
                     }

                     return value;
                  }

                  if (NumberInput.looksLikeValidNumber(text)) {
                     p.streamReadConstraints().validateFPLength(text.length());
                     if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return NumberInput.parseBigDecimal(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
                     }

                     return NumberInput.parseDouble(text, p.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
                  }
               } catch (IllegalArgumentException var7) {
               }

               return ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid number");
            }
         }
      }

      public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
         switch (p.currentTokenId()) {
            case 6:
            case 7:
            case 8:
               return this.deserialize(p, ctxt);
            default:
               return typeDeserializer.deserializeTypedFromScalar(p, ctxt);
         }
      }
   }

   @JacksonStdImpl
   public static class BigIntegerDeserializer extends StdScalarDeserializer {
      public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();

      public BigIntegerDeserializer() {
         super(BigInteger.class);
      }

      public Object getEmptyValue(DeserializationContext ctxt) {
         return BigInteger.ZERO;
      }

      public final LogicalType logicalType() {
         return LogicalType.Integer;
      }

      public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         if (p.isExpectedNumberIntToken()) {
            return p.getBigIntegerValue();
         } else {
            String text;
            switch (p.currentTokenId()) {
               case 1:
                  text = ctxt.extractScalarFromObject(p, this, this._valueClass);
                  break;
               case 2:
               case 4:
               case 5:
               case 7:
               default:
                  return (BigInteger)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
               case 3:
                  return (BigInteger)this._deserializeFromArray(p, ctxt);
               case 6:
                  text = p.getText();
                  break;
               case 8:
                  CoercionAction act = this._checkFloatToIntCoercion(p, ctxt, this._valueClass);
                  if (act == CoercionAction.AsNull) {
                     return (BigInteger)this.getNullValue(ctxt);
                  }

                  if (act == CoercionAction.AsEmpty) {
                     return (BigInteger)this.getEmptyValue(ctxt);
                  }

                  BigDecimal bd = p.getDecimalValue();
                  p.streamReadConstraints().validateBigIntegerScale(bd.scale());
                  return bd.toBigInteger();
            }

            CoercionAction act = this._checkFromStringCoercion(ctxt, text);
            if (act == CoercionAction.AsNull) {
               return (BigInteger)this.getNullValue(ctxt);
            } else if (act == CoercionAction.AsEmpty) {
               return (BigInteger)this.getEmptyValue(ctxt);
            } else {
               text = text.trim();
               if (this._hasTextualNull(text)) {
                  return (BigInteger)this.getNullValue(ctxt);
               } else {
                  if (this._isIntNumber(text)) {
                     p.streamReadConstraints().validateIntegerLength(text.length());

                     try {
                        return NumberInput.parseBigInteger(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
                     } catch (IllegalArgumentException var6) {
                     }
                  }

                  return (BigInteger)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid representation");
               }
            }
         }
      }
   }

   @JacksonStdImpl
   public static class BigDecimalDeserializer extends StdScalarDeserializer {
      public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();

      public BigDecimalDeserializer() {
         super(BigDecimal.class);
      }

      public Object getEmptyValue(DeserializationContext ctxt) {
         return BigDecimal.ZERO;
      }

      public final LogicalType logicalType() {
         return LogicalType.Float;
      }

      public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
         String text;
         switch (p.currentTokenId()) {
            case 1:
               text = ctxt.extractScalarFromObject(p, this, this._valueClass);
               break;
            case 2:
            case 4:
            case 5:
            default:
               return (BigDecimal)ctxt.handleUnexpectedToken(this.getValueType(ctxt), p);
            case 3:
               return (BigDecimal)this._deserializeFromArray(p, ctxt);
            case 6:
               text = p.getText();
               break;
            case 7:
               CoercionAction act = this._checkIntToFloatCoercion(p, ctxt, this._valueClass);
               if (act == CoercionAction.AsNull) {
                  return (BigDecimal)this.getNullValue(ctxt);
               }

               if (act == CoercionAction.AsEmpty) {
                  return (BigDecimal)this.getEmptyValue(ctxt);
               }
            case 8:
               return p.getDecimalValue();
         }

         CoercionAction act = this._checkFromStringCoercion(ctxt, text);
         if (act == CoercionAction.AsNull) {
            return (BigDecimal)this.getNullValue(ctxt);
         } else if (act == CoercionAction.AsEmpty) {
            return (BigDecimal)this.getEmptyValue(ctxt);
         } else {
            text = text.trim();
            if (this._hasTextualNull(text)) {
               return (BigDecimal)this.getNullValue(ctxt);
            } else {
               if (NumberInput.looksLikeValidNumber(text)) {
                  p.streamReadConstraints().validateFPLength(text.length());

                  try {
                     return NumberInput.parseBigDecimal(text, p.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
                  } catch (IllegalArgumentException var6) {
                  }
               }

               return (BigDecimal)ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid representation");
            }
         }
      }
   }
}
