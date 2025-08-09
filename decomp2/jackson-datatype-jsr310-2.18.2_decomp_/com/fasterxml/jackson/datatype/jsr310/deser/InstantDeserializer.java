package com.fasterxml.jackson.datatype.jsr310.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeFeature;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstantDeserializer extends JSR310DateTimeDeserializerBase {
   private static final long serialVersionUID = 1L;
   private static final boolean DEFAULT_NORMALIZE_ZONE_ID;
   private static final boolean DEFAULT_ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS;
   protected static final Pattern ISO8601_COLONLESS_OFFSET_REGEX;
   public static final InstantDeserializer INSTANT;
   public static final InstantDeserializer OFFSET_DATE_TIME;
   public static final InstantDeserializer ZONED_DATE_TIME;
   protected final Function fromMilliseconds;
   protected final Function fromNanoseconds;
   protected final Function parsedToValue;
   protected final BiFunction adjust;
   protected final boolean replaceZeroOffsetAsZ;
   protected final Boolean _adjustToContextTZOverride;
   protected final Boolean _readTimestampsAsNanosOverride;
   protected final boolean _normalizeZoneId;
   protected final boolean _alwaysAllowStringifiedDateTimestamps;

   private static OffsetDateTime decimalToOffsetDateTime(FromDecimalArguments args) {
      if (args.integer == OffsetDateTime.MIN.toEpochSecond() && args.fraction == OffsetDateTime.MIN.getNano()) {
         return OffsetDateTime.ofInstant(Instant.ofEpochSecond(OffsetDateTime.MIN.toEpochSecond(), (long)OffsetDateTime.MIN.getNano()), OffsetDateTime.MIN.getOffset());
      } else {
         return args.integer == OffsetDateTime.MAX.toEpochSecond() && args.fraction == OffsetDateTime.MAX.getNano() ? OffsetDateTime.ofInstant(Instant.ofEpochSecond(OffsetDateTime.MAX.toEpochSecond(), (long)OffsetDateTime.MAX.getNano()), OffsetDateTime.MAX.getOffset()) : OffsetDateTime.ofInstant(Instant.ofEpochSecond(args.integer, (long)args.fraction), args.zoneId);
      }
   }

   protected InstantDeserializer(Class supportedType, DateTimeFormatter formatter, Function parsedToValue, Function fromMilliseconds, Function fromNanoseconds, BiFunction adjust, boolean replaceZeroOffsetAsZ, boolean normalizeZoneId, boolean readNumericStringsAsTimestamp) {
      super(supportedType, formatter);
      this.parsedToValue = parsedToValue;
      this.fromMilliseconds = fromMilliseconds;
      this.fromNanoseconds = fromNanoseconds;
      this.adjust = adjust == null ? (d, z) -> d : adjust;
      this.replaceZeroOffsetAsZ = replaceZeroOffsetAsZ;
      this._adjustToContextTZOverride = null;
      this._readTimestampsAsNanosOverride = null;
      this._normalizeZoneId = normalizeZoneId;
      this._alwaysAllowStringifiedDateTimestamps = readNumericStringsAsTimestamp;
   }

   /** @deprecated */
   @Deprecated
   protected InstantDeserializer(Class supportedType, DateTimeFormatter formatter, Function parsedToValue, Function fromMilliseconds, Function fromNanoseconds, BiFunction adjust, boolean replaceZeroOffsetAsZ) {
      this(supportedType, formatter, parsedToValue, fromMilliseconds, fromNanoseconds, adjust, replaceZeroOffsetAsZ, DEFAULT_NORMALIZE_ZONE_ID, DEFAULT_ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS);
   }

   protected InstantDeserializer(InstantDeserializer base, DateTimeFormatter f) {
      super(base.handledType(), f);
      this.parsedToValue = base.parsedToValue;
      this.fromMilliseconds = base.fromMilliseconds;
      this.fromNanoseconds = base.fromNanoseconds;
      this.adjust = base.adjust;
      this.replaceZeroOffsetAsZ = this._formatter == DateTimeFormatter.ISO_INSTANT;
      this._adjustToContextTZOverride = base._adjustToContextTZOverride;
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
      this._normalizeZoneId = base._normalizeZoneId;
      this._alwaysAllowStringifiedDateTimestamps = base._alwaysAllowStringifiedDateTimestamps;
   }

   protected InstantDeserializer(InstantDeserializer base, Boolean adjustToContextTimezoneOverride) {
      super(base.handledType(), base._formatter);
      this.parsedToValue = base.parsedToValue;
      this.fromMilliseconds = base.fromMilliseconds;
      this.fromNanoseconds = base.fromNanoseconds;
      this.adjust = base.adjust;
      this.replaceZeroOffsetAsZ = base.replaceZeroOffsetAsZ;
      this._adjustToContextTZOverride = adjustToContextTimezoneOverride;
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
      this._normalizeZoneId = base._normalizeZoneId;
      this._alwaysAllowStringifiedDateTimestamps = base._alwaysAllowStringifiedDateTimestamps;
   }

   protected InstantDeserializer(InstantDeserializer base, DateTimeFormatter f, Boolean leniency) {
      super(base.handledType(), f, leniency);
      this.parsedToValue = base.parsedToValue;
      this.fromMilliseconds = base.fromMilliseconds;
      this.fromNanoseconds = base.fromNanoseconds;
      this.adjust = base.adjust;
      this.replaceZeroOffsetAsZ = this._formatter == DateTimeFormatter.ISO_INSTANT;
      this._adjustToContextTZOverride = base._adjustToContextTZOverride;
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
      this._normalizeZoneId = base._normalizeZoneId;
      this._alwaysAllowStringifiedDateTimestamps = base._alwaysAllowStringifiedDateTimestamps;
   }

   protected InstantDeserializer(InstantDeserializer base, Boolean leniency, DateTimeFormatter formatter, JsonFormat.Shape shape, Boolean adjustToContextTimezoneOverride, Boolean readTimestampsAsNanosOverride) {
      super(base, leniency, formatter, shape);
      this.parsedToValue = base.parsedToValue;
      this.fromMilliseconds = base.fromMilliseconds;
      this.fromNanoseconds = base.fromNanoseconds;
      this.adjust = base.adjust;
      this.replaceZeroOffsetAsZ = base.replaceZeroOffsetAsZ;
      this._adjustToContextTZOverride = adjustToContextTimezoneOverride;
      this._readTimestampsAsNanosOverride = readTimestampsAsNanosOverride;
      this._normalizeZoneId = base._normalizeZoneId;
      this._alwaysAllowStringifiedDateTimestamps = base._alwaysAllowStringifiedDateTimestamps;
   }

   protected InstantDeserializer(InstantDeserializer base, JacksonFeatureSet features) {
      super(base.handledType(), base._formatter);
      this.parsedToValue = base.parsedToValue;
      this.fromMilliseconds = base.fromMilliseconds;
      this.fromNanoseconds = base.fromNanoseconds;
      this.adjust = base.adjust;
      this.replaceZeroOffsetAsZ = base.replaceZeroOffsetAsZ;
      this._adjustToContextTZOverride = base._adjustToContextTZOverride;
      this._readTimestampsAsNanosOverride = base._readTimestampsAsNanosOverride;
      this._normalizeZoneId = features.isEnabled(JavaTimeFeature.NORMALIZE_DESERIALIZED_ZONE_ID);
      this._alwaysAllowStringifiedDateTimestamps = features.isEnabled(JavaTimeFeature.ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS);
   }

   protected InstantDeserializer withDateFormat(DateTimeFormatter dtf) {
      return dtf == this._formatter ? this : new InstantDeserializer(this, dtf);
   }

   protected InstantDeserializer withLeniency(Boolean leniency) {
      return new InstantDeserializer(this, this._formatter, leniency);
   }

   public InstantDeserializer withFeatures(JacksonFeatureSet features) {
      return this._normalizeZoneId == features.isEnabled(JavaTimeFeature.NORMALIZE_DESERIALIZED_ZONE_ID) && this._alwaysAllowStringifiedDateTimestamps == features.isEnabled(JavaTimeFeature.ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS) ? this : new InstantDeserializer(this, features);
   }

   protected JSR310DateTimeDeserializerBase _withFormatOverrides(DeserializationContext ctxt, BeanProperty property, JsonFormat.Value formatOverrides) {
      InstantDeserializer<T> deser = (InstantDeserializer)super._withFormatOverrides(ctxt, property, formatOverrides);
      Boolean adjustToContextTZOverride = formatOverrides.getFeature(Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
      Boolean readTimestampsAsNanosOverride = formatOverrides.getFeature(Feature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
      return Objects.equals(adjustToContextTZOverride, deser._adjustToContextTZOverride) && Objects.equals(readTimestampsAsNanosOverride, deser._readTimestampsAsNanosOverride) ? deser : new InstantDeserializer(deser, deser._isLenient, deser._formatter, deser._shape, adjustToContextTZOverride, readTimestampsAsNanosOverride);
   }

   public Temporal deserialize(JsonParser parser, DeserializationContext context) throws IOException {
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
            return (Temporal)this._handleUnexpectedToken(context, parser, new JsonToken[]{JsonToken.VALUE_STRING, JsonToken.VALUE_NUMBER_INT, JsonToken.VALUE_NUMBER_FLOAT});
         case 3:
            return (Temporal)this._deserializeFromArray(parser, context);
         case 6:
            return this._fromString(parser, context, parser.getText());
         case 7:
            return this._fromLong(context, parser.getLongValue());
         case 8:
            return this._fromDecimal(context, parser.getDecimalValue());
         case 12:
            return (Temporal)parser.getEmbeddedObject();
      }
   }

   protected boolean shouldAdjustToContextTimezone(DeserializationContext context) {
      return this._adjustToContextTZOverride != null ? this._adjustToContextTZOverride : context.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
   }

   protected boolean shouldReadTimestampsAsNanoseconds(DeserializationContext context) {
      return this._readTimestampsAsNanosOverride != null ? this._readTimestampsAsNanosOverride : context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
   }

   protected int _countPeriods(String str) {
      int commas = 0;
      int i = 0;

      for(int end = str.length(); i < end; ++i) {
         int ch = str.charAt(i);
         if (ch < 48 || ch > 57) {
            if (ch != 46) {
               return -1;
            }

            ++commas;
         }
      }

      return commas;
   }

   protected Temporal _fromString(JsonParser p, DeserializationContext ctxt, String string0) throws IOException {
      String string = string0.trim();
      if (string.length() == 0) {
         return (Temporal)this._fromEmptyString(p, ctxt, string);
      } else {
         if (this._alwaysAllowStringifiedDateTimestamps || this._formatter == DateTimeFormatter.ISO_INSTANT || this._formatter == DateTimeFormatter.ISO_OFFSET_DATE_TIME || this._formatter == DateTimeFormatter.ISO_ZONED_DATE_TIME) {
            int dots = this._countPeriods(string);
            if (dots >= 0) {
               try {
                  if (dots == 0) {
                     return this._fromLong(ctxt, NumberInput.parseLong(string));
                  }

                  if (dots == 1) {
                     return this._fromDecimal(ctxt, NumberInput.parseBigDecimal(string, false));
                  }
               } catch (NumberFormatException var8) {
               }
            }

            string = this.replaceZeroOffsetAsZIfNecessary(string);
         }

         if (this._formatter == DateTimeFormatter.ISO_OFFSET_DATE_TIME || this._formatter == DateTimeFormatter.ISO_ZONED_DATE_TIME) {
            string = this.addInColonToOffsetIfMissing(string);
         }

         T value;
         try {
            TemporalAccessor acc = this._formatter.parse(string);
            value = (T)((Temporal)this.parsedToValue.apply(acc));
            if (this.shouldAdjustToContextTimezone(ctxt)) {
               return (Temporal)this.adjust.apply(value, this.getZone(ctxt));
            }
         } catch (DateTimeException e) {
            value = (T)((Temporal)this._handleDateTimeException(ctxt, e, string));
         }

         return value;
      }
   }

   protected Temporal _fromLong(DeserializationContext context, long timestamp) {
      return this.shouldReadTimestampsAsNanoseconds(context) ? (Temporal)this.fromNanoseconds.apply(new FromDecimalArguments(timestamp, 0, this.getZone(context))) : (Temporal)this.fromMilliseconds.apply(new FromIntegerArguments(timestamp, this.getZone(context)));
   }

   protected Temporal _fromDecimal(DeserializationContext context, BigDecimal value) {
      FromDecimalArguments args = (FromDecimalArguments)DecimalUtils.extractSecondsAndNanos(value, (s, ns) -> new FromDecimalArguments(s, ns, this.getZone(context)));
      return (Temporal)this.fromNanoseconds.apply(args);
   }

   private ZoneId getZone(DeserializationContext context) {
      if (this._valueClass == Instant.class) {
         return null;
      } else {
         ZoneId zoneId = context.getTimeZone().toZoneId();
         return this._normalizeZoneId ? zoneId.normalized() : zoneId;
      }
   }

   private String replaceZeroOffsetAsZIfNecessary(String text) {
      return this.replaceZeroOffsetAsZ ? replaceZeroOffsetAsZ(text) : text;
   }

   private static String replaceZeroOffsetAsZ(String text) {
      int plusIndex = text.lastIndexOf(43);
      if (plusIndex < 0) {
         return text;
      } else {
         int maybeOffsetIndex = plusIndex + 1;
         int remaining = text.length() - maybeOffsetIndex;
         switch (remaining) {
            case 2:
               return text.regionMatches(maybeOffsetIndex, "00", 0, remaining) ? text.substring(0, plusIndex) + 'Z' : text;
            case 3:
            default:
               return text;
            case 4:
               return text.regionMatches(maybeOffsetIndex, "0000", 0, remaining) ? text.substring(0, plusIndex) + 'Z' : text;
            case 5:
               return text.regionMatches(maybeOffsetIndex, "00:00", 0, remaining) ? text.substring(0, plusIndex) + 'Z' : text;
         }
      }
   }

   private String addInColonToOffsetIfMissing(String text) {
      Matcher matcher = ISO8601_COLONLESS_OFFSET_REGEX.matcher(text);
      if (matcher.find()) {
         StringBuilder sb = new StringBuilder(matcher.group(0));
         sb.insert(3, ":");
         return matcher.replaceFirst(sb.toString());
      } else {
         return text;
      }
   }

   static {
      DEFAULT_NORMALIZE_ZONE_ID = JavaTimeFeature.NORMALIZE_DESERIALIZED_ZONE_ID.enabledByDefault();
      DEFAULT_ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS = JavaTimeFeature.ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS.enabledByDefault();
      ISO8601_COLONLESS_OFFSET_REGEX = Pattern.compile("[+-][0-9]{4}(?=\\[|$)");
      INSTANT = new InstantDeserializer(Instant.class, DateTimeFormatter.ISO_INSTANT, Instant::from, (a) -> Instant.ofEpochMilli(a.value), (a) -> Instant.ofEpochSecond(a.integer, (long)a.fraction), (BiFunction)null, true, DEFAULT_NORMALIZE_ZONE_ID, DEFAULT_ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS);
      OFFSET_DATE_TIME = new InstantDeserializer(OffsetDateTime.class, DateTimeFormatter.ISO_OFFSET_DATE_TIME, OffsetDateTime::from, (a) -> OffsetDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId), InstantDeserializer::decimalToOffsetDateTime, (d, z) -> !d.isEqual(OffsetDateTime.MIN) && !d.isEqual(OffsetDateTime.MAX) ? d.withOffsetSameInstant(z.getRules().getOffset(d.toLocalDateTime())) : d, true, DEFAULT_NORMALIZE_ZONE_ID, DEFAULT_ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS);
      ZONED_DATE_TIME = new InstantDeserializer(ZonedDateTime.class, DateTimeFormatter.ISO_ZONED_DATE_TIME, ZonedDateTime::from, (a) -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId), (a) -> ZonedDateTime.ofInstant(Instant.ofEpochSecond(a.integer, (long)a.fraction), a.zoneId), ZonedDateTime::withZoneSameInstant, false, DEFAULT_NORMALIZE_ZONE_ID, DEFAULT_ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS);
   }

   public static class FromIntegerArguments {
      public final long value;
      public final ZoneId zoneId;

      FromIntegerArguments(long value, ZoneId zoneId) {
         this.value = value;
         this.zoneId = zoneId;
      }
   }

   public static class FromDecimalArguments {
      public final long integer;
      public final int fraction;
      public final ZoneId zoneId;

      FromDecimalArguments(long integer, int fraction, ZoneId zoneId) {
         this.integer = integer;
         this.fraction = fraction;
         this.zoneId = zoneId;
      }
   }
}
