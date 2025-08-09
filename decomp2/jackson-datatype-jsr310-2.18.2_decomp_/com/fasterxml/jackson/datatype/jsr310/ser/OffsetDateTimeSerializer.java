package com.fasterxml.jackson.datatype.jsr310.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class OffsetDateTimeSerializer extends InstantSerializerBase {
   private static final long serialVersionUID = 1L;
   public static final OffsetDateTimeSerializer INSTANCE = new OffsetDateTimeSerializer();

   protected OffsetDateTimeSerializer() {
      super((Class)OffsetDateTime.class, (ToLongFunction)((dt) -> dt.toInstant().toEpochMilli()), (ToLongFunction)(OffsetDateTime::toEpochSecond), (ToIntFunction)(OffsetDateTime::getNano), (DateTimeFormatter)DateTimeFormatter.ISO_OFFSET_DATE_TIME);
   }

   /** @deprecated */
   @Deprecated
   protected OffsetDateTimeSerializer(OffsetDateTimeSerializer base, Boolean useTimestamp, DateTimeFormatter formatter) {
      this(base, useTimestamp, base._useNanoseconds, formatter);
   }

   protected OffsetDateTimeSerializer(OffsetDateTimeSerializer base, Boolean useTimestamp, Boolean useNanoseconds, DateTimeFormatter formatter) {
      super(base, useTimestamp, useNanoseconds, formatter);
   }

   public OffsetDateTimeSerializer(OffsetDateTimeSerializer base, Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      super((InstantSerializerBase)base, (Boolean)useTimestamp, (Boolean)base._useNanoseconds, (DateTimeFormatter)formatter, (JsonFormat.Shape)shape);
   }

   protected JSR310FormattedSerializerBase withFormat(Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      return new OffsetDateTimeSerializer(this, useTimestamp, formatter, shape);
   }

   protected JSR310FormattedSerializerBase withFeatures(Boolean writeZoneId, Boolean writeNanoseconds) {
      return new OffsetDateTimeSerializer(this, this._useTimestamp, writeNanoseconds, this._formatter);
   }
}
