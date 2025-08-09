package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JSR310DateTimeDeserializerBase {
   private static final long serialVersionUID = 1L;
   private static final DateTimeFormatter DEFAULT_FORMATTER;
   public static final LocalDateDeserializer INSTANCE;

   protected LocalDateDeserializer() {
      this(DEFAULT_FORMATTER);
   }

   public LocalDateDeserializer(DateTimeFormatter dtf) {
      super(LocalDate.class, dtf);
   }

   public LocalDateDeserializer(LocalDateDeserializer base, DateTimeFormatter dtf) {
      super((JSR310DateTimeDeserializerBase)base, (DateTimeFormatter)dtf);
   }

   protected LocalDateDeserializer(LocalDateDeserializer base, Boolean leniency) {
      super((JSR310DateTimeDeserializerBase)base, (Boolean)leniency);
   }

   protected LocalDateDeserializer(LocalDateDeserializer base, JsonFormat.Shape shape) {
      super((JSR310DateTimeDeserializerBase)base, (JsonFormat.Shape)shape);
   }

   protected LocalDateDeserializer withDateFormat(DateTimeFormatter dtf) {
      return new LocalDateDeserializer(this, dtf);
   }

   protected LocalDateDeserializer withLeniency(Boolean leniency) {
      return new LocalDateDeserializer(this, leniency);
   }

   protected LocalDateDeserializer withShape(JsonFormat.Shape shape) {
      return new LocalDateDeserializer(this, shape);
   }

   public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      if (parser.hasToken(JsonToken.VALUE_STRING)) {
         return this._fromString(parser, context, parser.getText());
      } else if (parser.isExpectedStartObjectToken()) {
         return this._fromString(parser, context, context.extractScalarFromObject(parser, this, this.handledType()));
      } else {
         if (parser.isExpectedStartArrayToken()) {
            JsonToken t = parser.nextToken();
            if (t == JsonToken.END_ARRAY) {
               return null;
            }

            if (context.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS) && (t == JsonToken.VALUE_STRING || t == JsonToken.VALUE_EMBEDDED_OBJECT)) {
               LocalDate parsed = this.deserialize(parser, context);
               if (parser.nextToken() != JsonToken.END_ARRAY) {
                  this.handleMissingEndArrayForSingle(parser, context);
               }

               return parsed;
            }

            if (t == JsonToken.VALUE_NUMBER_INT) {
               int year = parser.getIntValue();
               int month = parser.nextIntValue(-1);
               int day = parser.nextIntValue(-1);
               if (parser.nextToken() != JsonToken.END_ARRAY) {
                  throw context.wrongTokenException(parser, this.handledType(), JsonToken.END_ARRAY, "Expected array to end");
               }

               return LocalDate.of(year, month, day);
            }

            context.reportInputMismatch(this.handledType(), "Unexpected token (%s) within Array, expected VALUE_NUMBER_INT", new Object[]{t});
         }

         if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (LocalDate)parser.getEmbeddedObject();
         } else if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            CoercionAction act = context.findCoercionAction(this.logicalType(), this._valueClass, CoercionInputShape.Integer);
            this._checkCoercionFail(context, act, this.handledType(), parser.getLongValue(), "Integer value (" + parser.getLongValue() + ")");
            if (this._shape != Shape.NUMBER_INT && !this.isLenient()) {
               return (LocalDate)this._failForNotLenient(parser, context, JsonToken.VALUE_STRING);
            } else {
               return LocalDate.ofEpochDay(parser.getLongValue());
            }
         } else {
            return (LocalDate)this._handleUnexpectedToken(context, parser, "Expected array or string.", new Object[0]);
         }
      }
   }

   protected LocalDate _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
      String string = string0.trim();
      if (string.length() == 0) {
         return (LocalDate)this._fromEmptyString(p, ctxt, string);
      } else {
         try {
            DateTimeFormatter format = this._formatter;
            if (format == DEFAULT_FORMATTER && string.length() > 10 && string.charAt(10) == 'T') {
               if (this.isLenient()) {
                  return string.endsWith("Z") ? LocalDate.parse(string.substring(0, string.length() - 1), DateTimeFormatter.ISO_LOCAL_DATE_TIME) : LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
               } else {
                  JavaType t = this.getValueType(ctxt);
                  return (LocalDate)ctxt.handleWeirdStringValue(t.getRawClass(), string, "Should not contain time component when 'strict' mode set for property or type (enable 'lenient' handling to allow)", new Object[0]);
               }
            } else {
               return LocalDate.parse(string, format);
            }
         } catch (DateTimeException e) {
            return (LocalDate)this._handleDateTimeException(ctxt, e, string);
         }
      }
   }

   static {
      DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
      INSTANCE = new LocalDateDeserializer();
   }
}
