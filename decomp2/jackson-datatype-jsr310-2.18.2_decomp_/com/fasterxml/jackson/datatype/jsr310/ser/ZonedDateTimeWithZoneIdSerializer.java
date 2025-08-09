package com.fasterxml.jackson.datatype.jsr310.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/** @deprecated */
@Deprecated
public class ZonedDateTimeWithZoneIdSerializer extends InstantSerializerBase {
   private static final long serialVersionUID = 1L;
   public static final ZonedDateTimeWithZoneIdSerializer INSTANCE = new ZonedDateTimeWithZoneIdSerializer();

   protected ZonedDateTimeWithZoneIdSerializer() {
      super((Class)ZonedDateTime.class, (ToLongFunction)((dt) -> dt.toInstant().toEpochMilli()), (ToLongFunction)(ChronoZonedDateTime::toEpochSecond), (ToIntFunction)(ZonedDateTime::getNano), (DateTimeFormatter)null);
   }

   protected ZonedDateTimeWithZoneIdSerializer(ZonedDateTimeWithZoneIdSerializer base, Boolean useTimestamp, DateTimeFormatter formatter) {
      this(base, useTimestamp, (Boolean)null, formatter);
   }

   protected ZonedDateTimeWithZoneIdSerializer(ZonedDateTimeWithZoneIdSerializer base, Boolean useTimestamp, Boolean useNanoseconds, DateTimeFormatter formatter) {
      super(base, useTimestamp, useNanoseconds, formatter);
   }

   protected JSR310FormattedSerializerBase withFormat(Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      return new ZonedDateTimeWithZoneIdSerializer(this, useTimestamp, formatter);
   }

   protected JSR310FormattedSerializerBase withFeatures(Boolean writeZoneId, Boolean writeNanoseconds) {
      return new ZonedDateTimeWithZoneIdSerializer(this, this._useTimestamp, writeNanoseconds, this._formatter);
   }
}
