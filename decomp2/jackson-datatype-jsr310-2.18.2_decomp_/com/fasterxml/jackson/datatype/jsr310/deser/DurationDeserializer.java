package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import com.fasterxml.jackson.datatype.jsr310.util.DurationUnitConverter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Duration;
import java.util.Objects;

public class DurationDeserializer extends JSR310DeserializerBase implements ContextualDeserializer {
   private static final long serialVersionUID = 1L;
   public static final DurationDeserializer INSTANCE = new DurationDeserializer();
   protected final DurationUnitConverter _durationUnitConverter;
   protected final Boolean _readTimestampsAsNanosOverride;

   public DurationDeserializer() {
      super(Duration.class);
      this._durationUnitConverter = null;
      this._readTimestampsAsNanosOverride = null;
   }

   protected DurationDeserializer(DurationDeserializer base, Boolean leniency) {
      super((JSR310DeserializerBase)base, leniency);
      this._durationUnitConverter = base._durationUnitConverter;
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
   }

   protected DurationDeserializer(DurationDeserializer base, DurationUnitConverter converter) {
      super((JSR310DeserializerBase)base, base._isLenient);
      this._durationUnitConverter = converter;
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
   }

   protected DurationDeserializer(DurationDeserializer base, Boolean leniency, DurationUnitConverter converter, Boolean readTimestampsAsNanosOverride) {
      super((JSR310DeserializerBase)base, leniency);
      this._durationUnitConverter = converter;
      this._readTimestampsAsNanosOverride = readTimestampsAsNanosOverride;
   }

   protected DurationDeserializer withLeniency(Boolean leniency) {
      return new DurationDeserializer(this, leniency);
   }

   protected DurationDeserializer withConverter(DurationUnitConverter converter) {
      return new DurationDeserializer(this, converter);
   }

   public JsonDeserializer createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
      JsonFormat.Value format = this.findFormatOverrides(ctxt, property, this.handledType());
      boolean leniency = this._isLenient;
      DurationUnitConverter unitConverter = this._durationUnitConverter;
      Boolean timestampsAsNanosOverride = this._readTimestampsAsNanosOverride;
      if (format != null) {
         if (format.hasLenient()) {
            leniency = format.getLenient();
         }

         if (format.hasPattern()) {
            String pattern = format.getPattern();
            unitConverter = DurationUnitConverter.from(pattern);
            if (unitConverter == null) {
               ctxt.reportBadDefinition(this.getValueType(ctxt), String.format("Bad 'pattern' definition (\"%s\") for `Duration`: expected one of [%s]", pattern, DurationUnitConverter.descForAllowed()));
            }
         }

         timestampsAsNanosOverride = format.getFeature(Feature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
      }

      return leniency == this._isLenient && Objects.equals(unitConverter, this._durationUnitConverter) && Objects.equals(timestampsAsNanosOverride, this._readTimestampsAsNanosOverride) ? this : new DurationDeserializer(this, leniency, unitConverter, timestampsAsNanosOverride);
   }

   public Duration deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      switch (parser.currentTokenId()) {
         case 1:
            return this._fromString(parser, context, context.extractScalarFromObject(parser, this, this.handledType()));
         case 2:
         case 4:
         case 5:
         case 9:
         case 10:
         case 11:
         default:
            return (Duration)this._handleUnexpectedToken(context, parser, new JsonToken[]{JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT, JsonToken.VALUE_NUMBER_FLOAT});
         case 3:
            return (Duration)this._deserializeFromArray(parser, context);
         case 6:
            return this._fromString(parser, context, parser.getText());
         case 7:
            return this._fromTimestamp(context, parser.getLongValue());
         case 8:
            BigDecimal value = parser.getDecimalValue();
            return (Duration)DecimalUtils.extractSecondsAndNanos(value, Duration::ofSeconds);
         case 12:
            return (Duration)parser.getEmbeddedObject();
      }
   }

   protected Duration _fromString(JsonParser parser, DeserializationContext ctxt, String value0) throws IOException {
      String value = value0.trim();
      if (value.length() == 0) {
         return (Duration)this._fromEmptyString(parser, ctxt, value);
      } else if (ctxt.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && this._isValidTimestampString(value)) {
         return this._fromTimestamp(ctxt, NumberInput.parseLong(value));
      } else {
         try {
            return Duration.parse(value);
         } catch (DateTimeException e) {
            return (Duration)this._handleDateTimeException(ctxt, e, value);
         }
      }
   }

   protected Duration _fromTimestamp(DeserializationContext ctxt, long ts) {
      if (this._durationUnitConverter != null) {
         return this._durationUnitConverter.convert(ts);
      } else {
         return this.shouldReadTimestampsAsNanoseconds(ctxt) ? Duration.ofSeconds(ts) : Duration.ofMillis(ts);
      }
   }

   protected boolean shouldReadTimestampsAsNanoseconds(DeserializationContext context) {
      return this._readTimestampsAsNanosOverride != null ? this._readTimestampsAsNanosOverride : context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
   }
}
