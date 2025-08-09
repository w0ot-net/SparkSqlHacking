package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LocalDateTimeDeserializer extends JSR310DateTimeDeserializerBase {
   private static final long serialVersionUID = 1L;
   private static final DateTimeFormatter DEFAULT_FORMATTER;
   public static final LocalDateTimeDeserializer INSTANCE;
   protected final Boolean _readTimestampsAsNanosOverride;

   protected LocalDateTimeDeserializer() {
      this(DEFAULT_FORMATTER);
   }

   public LocalDateTimeDeserializer(DateTimeFormatter formatter) {
      super(LocalDateTime.class, formatter);
      this._readTimestampsAsNanosOverride = null;
   }

   protected LocalDateTimeDeserializer(LocalDateTimeDeserializer base, Boolean leniency) {
      super((JSR310DateTimeDeserializerBase)base, (Boolean)leniency);
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
   }

   protected LocalDateTimeDeserializer(LocalDateTimeDeserializer base, Boolean leniency, DateTimeFormatter formatter, JsonFormat.Shape shape, Boolean readTimestampsAsNanosOverride) {
      super(base, leniency, formatter, shape);
      this._readTimestampsAsNanosOverride = readTimestampsAsNanosOverride;
   }

   protected LocalDateTimeDeserializer withDateFormat(DateTimeFormatter dtf) {
      return new LocalDateTimeDeserializer(this, this._isLenient, dtf, this._shape, this._readTimestampsAsNanosOverride);
   }

   protected LocalDateTimeDeserializer withLeniency(Boolean leniency) {
      return new LocalDateTimeDeserializer(this, leniency);
   }

   protected JSR310DateTimeDeserializerBase _withFormatOverrides(DeserializationContext ctxt, BeanProperty property, JsonFormat.Value formatOverrides) {
      LocalDateTimeDeserializer deser = (LocalDateTimeDeserializer)super._withFormatOverrides(ctxt, property, formatOverrides);
      Boolean readTimestampsAsNanosOverride = formatOverrides.getFeature(Feature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
      return !Objects.equals(readTimestampsAsNanosOverride, deser._readTimestampsAsNanosOverride) ? new LocalDateTimeDeserializer(deser, deser._isLenient, deser._formatter, deser._shape, readTimestampsAsNanosOverride) : deser;
   }

   public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      if (parser.hasTokenId(6)) {
         return this._fromString(parser, context, parser.getText());
      } else if (parser.isExpectedStartObjectToken()) {
         return this._fromString(parser, context, context.extractScalarFromObject(parser, this, this.handledType()));
      } else {
         if (parser.isExpectedStartArrayToken()) {
            JsonToken t = parser.nextToken();
            if (t == JsonToken.END_ARRAY) {
               return null;
            }

            if ((t == JsonToken.VALUE_STRING || t == JsonToken.VALUE_EMBEDDED_OBJECT) && context.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
               LocalDateTime parsed = this.deserialize(parser, context);
               if (parser.nextToken() != JsonToken.END_ARRAY) {
                  this.handleMissingEndArrayForSingle(parser, context);
               }

               return parsed;
            }

            if (t == JsonToken.VALUE_NUMBER_INT) {
               int year = parser.getIntValue();
               int month = parser.nextIntValue(-1);
               int day = parser.nextIntValue(-1);
               int hour = parser.nextIntValue(-1);
               int minute = parser.nextIntValue(-1);
               t = parser.nextToken();
               LocalDateTime result;
               if (t == JsonToken.END_ARRAY) {
                  result = LocalDateTime.of(year, month, day, hour, minute);
               } else {
                  int second = parser.getIntValue();
                  t = parser.nextToken();
                  if (t == JsonToken.END_ARRAY) {
                     result = LocalDateTime.of(year, month, day, hour, minute, second);
                  } else {
                     int partialSecond = parser.getIntValue();
                     if (partialSecond < 1000 && !this.shouldReadTimestampsAsNanoseconds(context)) {
                        partialSecond *= 1000000;
                     }

                     if (parser.nextToken() != JsonToken.END_ARRAY) {
                        throw context.wrongTokenException(parser, this.handledType(), JsonToken.END_ARRAY, "Expected array to end");
                     }

                     result = LocalDateTime.of(year, month, day, hour, minute, second, partialSecond);
                  }
               }

               return result;
            }

            context.reportInputMismatch(this.handledType(), "Unexpected token (%s) within Array, expected VALUE_NUMBER_INT", new Object[]{t});
         }

         if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (LocalDateTime)parser.getEmbeddedObject();
         } else {
            if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
               this._throwNoNumericTimestampNeedTimeZone(parser, context);
            }

            return (LocalDateTime)this._handleUnexpectedToken(context, parser, "Expected array or string.", new Object[0]);
         }
      }
   }

   protected boolean shouldReadTimestampsAsNanoseconds(DeserializationContext context) {
      return this._readTimestampsAsNanosOverride != null ? this._readTimestampsAsNanosOverride : context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
   }

   protected LocalDateTime _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
      String string = string0.trim();
      if (string.length() == 0) {
         return (LocalDateTime)this._fromEmptyString(p, ctxt, string);
      } else {
         try {
            if (this._formatter == DEFAULT_FORMATTER && string.length() > 10 && string.charAt(10) == 'T' && string.endsWith("Z")) {
               if (this.isLenient()) {
                  return LocalDateTime.parse(string.substring(0, string.length() - 1), this._formatter);
               } else {
                  JavaType t = this.getValueType(ctxt);
                  return (LocalDateTime)ctxt.handleWeirdStringValue(t.getRawClass(), string, "Should not contain offset when 'strict' mode set for property or type (enable 'lenient' handling to allow)", new Object[0]);
               }
            } else {
               return LocalDateTime.parse(string, this._formatter);
            }
         } catch (DateTimeException e) {
            return (LocalDateTime)this._handleDateTimeException(ctxt, e, string);
         }
      }
   }

   static {
      DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      INSTANCE = new LocalDateTimeDeserializer();
   }
}
