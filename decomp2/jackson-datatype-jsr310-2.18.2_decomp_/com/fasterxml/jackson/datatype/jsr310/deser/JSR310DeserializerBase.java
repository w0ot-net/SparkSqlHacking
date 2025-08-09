package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Arrays;

abstract class JSR310DeserializerBase extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;
   protected final boolean _isLenient;

   protected JSR310DeserializerBase(Class supportedType) {
      super(supportedType);
      this._isLenient = true;
   }

   protected JSR310DeserializerBase(Class supportedType, Boolean leniency) {
      super(supportedType);
      this._isLenient = !Boolean.FALSE.equals(leniency);
   }

   protected JSR310DeserializerBase(JSR310DeserializerBase base) {
      super(base);
      this._isLenient = base._isLenient;
   }

   protected JSR310DeserializerBase(JSR310DeserializerBase base, Boolean leniency) {
      super(base);
      this._isLenient = !Boolean.FALSE.equals(leniency);
   }

   protected abstract JSR310DeserializerBase withLeniency(Boolean var1);

   protected boolean isLenient() {
      return this._isLenient;
   }

   protected Object _fromEmptyString(JsonParser p, DeserializationContext ctxt, String str) throws IOException {
      CoercionAction act = this._checkFromStringCoercion(ctxt, str);
      switch (act) {
         case AsEmpty:
            return this.getEmptyValue(ctxt);
         case TryConvert:
         case AsNull:
         default:
            return !this._isLenient ? this._failForNotLenient(p, ctxt, JsonToken.VALUE_STRING) : null;
      }
   }

   public LogicalType logicalType() {
      return LogicalType.DateTime;
   }

   public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer) throws IOException {
      return typeDeserializer.deserializeTypedFromAny(parser, context);
   }

   protected boolean _isValidTimestampString(String str) {
      return this._isIntNumber(str) && NumberInput.inLongRange(str, str.charAt(0) == '-');
   }

   protected Object _reportWrongToken(DeserializationContext context, JsonToken exp, String unit) throws IOException {
      context.reportWrongTokenException(this, exp, "Expected %s for '%s' of %s value", new Object[]{exp.name(), unit, this.handledType().getName()});
      return null;
   }

   protected Object _reportWrongToken(JsonParser parser, DeserializationContext context, JsonToken... expTypes) throws IOException {
      return context.reportInputMismatch(this.handledType(), "Unexpected token (%s), expected one of %s for %s value", new Object[]{parser.getCurrentToken(), Arrays.asList(expTypes).toString(), this.handledType().getName()});
   }

   protected Object _handleDateTimeException(DeserializationContext context, DateTimeException e0, String value) throws JsonMappingException {
      try {
         return context.handleWeirdStringValue(this.handledType(), value, "Failed to deserialize %s: (%s) %s", new Object[]{this.handledType().getName(), e0.getClass().getName(), e0.getMessage()});
      } catch (JsonMappingException e) {
         e.initCause(e0);
         throw e;
      } catch (IOException var6) {
         if (null == var6.getCause()) {
            var6.initCause(e0);
         }

         throw JsonMappingException.fromUnexpectedIOE(var6);
      }
   }

   protected Object _handleUnexpectedToken(DeserializationContext context, JsonParser parser, String message, Object... args) throws JsonMappingException {
      try {
         return context.handleUnexpectedToken(this.handledType(), parser.getCurrentToken(), parser, message, args);
      } catch (JsonMappingException e) {
         throw e;
      } catch (IOException e) {
         throw JsonMappingException.fromUnexpectedIOE(e);
      }
   }

   protected Object _handleUnexpectedToken(DeserializationContext context, JsonParser parser, JsonToken... expTypes) throws JsonMappingException {
      return this._handleUnexpectedToken(context, parser, "Unexpected token (%s), expected one of %s for %s value", parser.currentToken(), Arrays.asList(expTypes), this.handledType().getName());
   }

   protected Object _failForNotLenient(JsonParser p, DeserializationContext ctxt, JsonToken expToken) throws IOException {
      return ctxt.handleUnexpectedToken(this.handledType(), expToken, p, "Cannot deserialize instance of %s out of %s token: not allowed because 'strict' mode set for property or type (enable 'lenient' handling to allow)", new Object[]{ClassUtil.nameOf(this.handledType()), p.currentToken()});
   }

   protected DateTimeException _peelDTE(DateTimeException e) {
      while(true) {
         Throwable t = e.getCause();
         if (t == null || !(t instanceof DateTimeException)) {
            return e;
         }

         e = (DateTimeException)t;
      }
   }
}
