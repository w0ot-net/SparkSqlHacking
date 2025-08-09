package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LocalTimeDeserializer extends JSR310DateTimeDeserializerBase {
   private static final long serialVersionUID = 1L;
   private static final DateTimeFormatter DEFAULT_FORMATTER;
   public static final LocalTimeDeserializer INSTANCE;
   protected final Boolean _readTimestampsAsNanosOverride;

   protected LocalTimeDeserializer() {
      this(DEFAULT_FORMATTER);
   }

   public LocalTimeDeserializer(DateTimeFormatter formatter) {
      super(LocalTime.class, formatter);
      this._readTimestampsAsNanosOverride = null;
   }

   protected LocalTimeDeserializer(LocalTimeDeserializer base, Boolean leniency) {
      super((JSR310DateTimeDeserializerBase)base, (Boolean)leniency);
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
   }

   protected LocalTimeDeserializer(LocalTimeDeserializer base, Boolean leniency, DateTimeFormatter formatter, JsonFormat.Shape shape, Boolean readTimestampsAsNanosOverride) {
      super(base, leniency, formatter, shape);
      this._readTimestampsAsNanosOverride = readTimestampsAsNanosOverride;
   }

   protected LocalTimeDeserializer withDateFormat(DateTimeFormatter dtf) {
      return new LocalTimeDeserializer(this, this._isLenient, dtf, this._shape, this._readTimestampsAsNanosOverride);
   }

   protected LocalTimeDeserializer withLeniency(Boolean leniency) {
      return new LocalTimeDeserializer(this, leniency);
   }

   protected JSR310DateTimeDeserializerBase _withFormatOverrides(DeserializationContext ctxt, BeanProperty property, JsonFormat.Value formatOverrides) {
      LocalTimeDeserializer deser = (LocalTimeDeserializer)super._withFormatOverrides(ctxt, property, formatOverrides);
      Boolean readTimestampsAsNanosOverride = formatOverrides.getFeature(Feature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
      return !Objects.equals(readTimestampsAsNanosOverride, deser._readTimestampsAsNanosOverride) ? new LocalTimeDeserializer(deser, deser._isLenient, deser._formatter, deser._shape, readTimestampsAsNanosOverride) : deser;
   }

   public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
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
               LocalTime parsed = this.deserialize(parser, context);
               if (parser.nextToken() != JsonToken.END_ARRAY) {
                  this.handleMissingEndArrayForSingle(parser, context);
               }

               return parsed;
            }

            if (t == JsonToken.VALUE_NUMBER_INT) {
               int hour = parser.getIntValue();
               parser.nextToken();
               int minute = parser.getIntValue();
               t = parser.nextToken();
               LocalTime result;
               if (t == JsonToken.END_ARRAY) {
                  result = LocalTime.of(hour, minute);
               } else {
                  int second = parser.getIntValue();
                  t = parser.nextToken();
                  if (t == JsonToken.END_ARRAY) {
                     result = LocalTime.of(hour, minute, second);
                  } else {
                     int partialSecond = parser.getIntValue();
                     if (partialSecond < 1000 && !this.shouldReadTimestampsAsNanoseconds(context)) {
                        partialSecond *= 1000000;
                     }

                     t = parser.nextToken();
                     if (t != JsonToken.END_ARRAY) {
                        throw context.wrongTokenException(parser, this.handledType(), JsonToken.END_ARRAY, "Expected array to end");
                     }

                     result = LocalTime.of(hour, minute, second, partialSecond);
                  }
               }

               return result;
            }

            context.reportInputMismatch(this.handledType(), "Unexpected token (%s) within Array, expected VALUE_NUMBER_INT", new Object[]{t});
         }

         if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (LocalTime)parser.getEmbeddedObject();
         } else {
            if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
               this._throwNoNumericTimestampNeedTimeZone(parser, context);
            }

            return (LocalTime)this._handleUnexpectedToken(context, parser, "Expected array or string.", new Object[0]);
         }
      }
   }

   protected boolean shouldReadTimestampsAsNanoseconds(DeserializationContext context) {
      return this._readTimestampsAsNanosOverride != null ? this._readTimestampsAsNanosOverride : context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
   }

   protected LocalTime _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
      String string = string0.trim();
      if (string.length() == 0) {
         return (LocalTime)this._fromEmptyString(p, ctxt, string);
      } else {
         DateTimeFormatter format = this._formatter;

         try {
            return format == DEFAULT_FORMATTER && string.contains("T") ? LocalTime.parse(string, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : LocalTime.parse(string, format);
         } catch (DateTimeException e) {
            return (LocalTime)this._handleDateTimeException(ctxt, e, string);
         }
      }
   }

   static {
      DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
      INSTANCE = new LocalTimeDeserializer();
   }
}
