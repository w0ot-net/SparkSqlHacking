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
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OffsetTimeDeserializer extends JSR310DateTimeDeserializerBase {
   private static final long serialVersionUID = 1L;
   public static final OffsetTimeDeserializer INSTANCE = new OffsetTimeDeserializer();
   protected final Boolean _readTimestampsAsNanosOverride;

   protected OffsetTimeDeserializer() {
      this(DateTimeFormatter.ISO_OFFSET_TIME);
   }

   protected OffsetTimeDeserializer(DateTimeFormatter dtf) {
      super(OffsetTime.class, dtf);
      this._readTimestampsAsNanosOverride = null;
   }

   protected OffsetTimeDeserializer(OffsetTimeDeserializer base, Boolean leniency) {
      super((JSR310DateTimeDeserializerBase)base, (Boolean)leniency);
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
   }

   protected OffsetTimeDeserializer(OffsetTimeDeserializer base, Boolean leniency, DateTimeFormatter formatter, JsonFormat.Shape shape, Boolean readTimestampsAsNanosOverride) {
      super(base, leniency, formatter, shape);
      this._readTimestampsAsNanosOverride = readTimestampsAsNanosOverride;
   }

   protected OffsetTimeDeserializer withDateFormat(DateTimeFormatter dtf) {
      return new OffsetTimeDeserializer(this, this._isLenient, dtf, this._shape, this._readTimestampsAsNanosOverride);
   }

   protected OffsetTimeDeserializer withLeniency(Boolean leniency) {
      return new OffsetTimeDeserializer(this, leniency);
   }

   protected JSR310DateTimeDeserializerBase _withFormatOverrides(DeserializationContext ctxt, BeanProperty property, JsonFormat.Value formatOverrides) {
      OffsetTimeDeserializer deser = (OffsetTimeDeserializer)super._withFormatOverrides(ctxt, property, formatOverrides);
      Boolean readTimestampsAsNanosOverride = formatOverrides.getFeature(Feature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
      return !Objects.equals(readTimestampsAsNanosOverride, deser._readTimestampsAsNanosOverride) ? new OffsetTimeDeserializer(deser, deser._isLenient, deser._formatter, deser._shape, readTimestampsAsNanosOverride) : deser;
   }

   public OffsetTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      if (parser.hasToken(JsonToken.VALUE_STRING)) {
         return this._fromString(parser, context, parser.getText());
      } else if (parser.isExpectedStartObjectToken()) {
         return this._fromString(parser, context, context.extractScalarFromObject(parser, this, this.handledType()));
      } else if (!parser.isExpectedStartArrayToken()) {
         if (parser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            return (OffsetTime)parser.getEmbeddedObject();
         } else {
            if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
               this._throwNoNumericTimestampNeedTimeZone(parser, context);
            }

            throw context.wrongTokenException(parser, this.handledType(), JsonToken.START_ARRAY, "Expected array or string.");
         }
      } else {
         JsonToken t = parser.nextToken();
         if (t != JsonToken.VALUE_NUMBER_INT) {
            if (t == JsonToken.END_ARRAY) {
               return null;
            }

            if ((t == JsonToken.VALUE_STRING || t == JsonToken.VALUE_EMBEDDED_OBJECT) && context.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
               OffsetTime parsed = this.deserialize(parser, context);
               if (parser.nextToken() != JsonToken.END_ARRAY) {
                  this.handleMissingEndArrayForSingle(parser, context);
               }

               return parsed;
            }

            context.reportInputMismatch(this.handledType(), "Unexpected token (%s) within Array, expected VALUE_NUMBER_INT", new Object[]{t});
         }

         int hour = parser.getIntValue();
         int minute = parser.nextIntValue(-1);
         if (minute == -1) {
            t = parser.getCurrentToken();
            if (t == JsonToken.END_ARRAY) {
               return null;
            }

            if (t != JsonToken.VALUE_NUMBER_INT) {
               this._reportWrongToken(context, JsonToken.VALUE_NUMBER_INT, "minutes");
            }

            minute = parser.getIntValue();
         }

         int partialSecond = 0;
         int second = 0;
         if (parser.nextToken() == JsonToken.VALUE_NUMBER_INT) {
            second = parser.getIntValue();
            if (parser.nextToken() == JsonToken.VALUE_NUMBER_INT) {
               partialSecond = parser.getIntValue();
               if (partialSecond < 1000 && !this.shouldReadTimestampsAsNanoseconds(context)) {
                  partialSecond *= 1000000;
               }

               parser.nextToken();
            }
         }

         if (parser.getCurrentToken() == JsonToken.VALUE_STRING) {
            OffsetTime result = OffsetTime.of(hour, minute, second, partialSecond, ZoneOffset.of(parser.getText()));
            if (parser.nextToken() != JsonToken.END_ARRAY) {
               this._reportWrongToken(context, JsonToken.END_ARRAY, "timezone");
            }

            return result;
         } else {
            throw context.wrongTokenException(parser, this.handledType(), JsonToken.VALUE_STRING, "Expected string for TimeZone after numeric values");
         }
      }
   }

   protected boolean shouldReadTimestampsAsNanoseconds(DeserializationContext context) {
      return this._readTimestampsAsNanosOverride != null ? this._readTimestampsAsNanosOverride : context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
   }

   protected OffsetTime _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
      String string = string0.trim();
      if (string.length() == 0) {
         return (OffsetTime)this._fromEmptyString(p, ctxt, string);
      } else {
         try {
            return OffsetTime.parse(string, this._formatter);
         } catch (DateTimeException e) {
            return (OffsetTime)this._handleDateTimeException(ctxt, e, string);
         }
      }
   }
}
