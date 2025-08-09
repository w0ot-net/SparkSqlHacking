package com.fasterxml.jackson.datatype.jsr310.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class ZonedDateTimeSerializer extends InstantSerializerBase {
   private static final long serialVersionUID = 1L;
   public static final ZonedDateTimeSerializer INSTANCE = new ZonedDateTimeSerializer();
   protected final Boolean _writeZoneId;

   protected ZonedDateTimeSerializer() {
      this(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
   }

   public ZonedDateTimeSerializer(DateTimeFormatter formatter) {
      super((Class)ZonedDateTime.class, (ToLongFunction)((dt) -> dt.toInstant().toEpochMilli()), (ToLongFunction)(ChronoZonedDateTime::toEpochSecond), (ToIntFunction)(ZonedDateTime::getNano), (DateTimeFormatter)formatter);
      this._writeZoneId = null;
   }

   protected ZonedDateTimeSerializer(ZonedDateTimeSerializer base, Boolean useTimestamp, DateTimeFormatter formatter, Boolean writeZoneId) {
      this(base, useTimestamp, base._useNanoseconds, formatter, base._shape, writeZoneId);
   }

   /** @deprecated */
   @Deprecated
   protected ZonedDateTimeSerializer(ZonedDateTimeSerializer base, Boolean useTimestamp, Boolean useNanoseconds, DateTimeFormatter formatter, Boolean writeZoneId) {
      this(base, useTimestamp, useNanoseconds, formatter, base._shape, writeZoneId);
   }

   protected ZonedDateTimeSerializer(ZonedDateTimeSerializer base, Boolean useTimestamp, Boolean useNanoseconds, DateTimeFormatter formatter, JsonFormat.Shape shape, Boolean writeZoneId) {
      super((InstantSerializerBase)base, (Boolean)useTimestamp, (Boolean)useNanoseconds, (DateTimeFormatter)formatter, (JsonFormat.Shape)shape);
      this._writeZoneId = writeZoneId;
   }

   protected JSR310FormattedSerializerBase withFormat(Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      return new ZonedDateTimeSerializer(this, useTimestamp, this._useNanoseconds, formatter, shape, this._writeZoneId);
   }

   /** @deprecated */
   @Deprecated
   protected JSR310FormattedSerializerBase withFeatures(Boolean writeZoneId) {
      return new ZonedDateTimeSerializer(this, this._useTimestamp, this._formatter, writeZoneId);
   }

   protected JSR310FormattedSerializerBase withFeatures(Boolean writeZoneId, Boolean writeNanoseconds) {
      return new ZonedDateTimeSerializer(this, this._useTimestamp, writeNanoseconds, this._formatter, writeZoneId);
   }

   public void serialize(ZonedDateTime value, JsonGenerator g, SerializerProvider provider) throws IOException {
      if (!this.useTimestamp(provider) && this.shouldWriteWithZoneId(provider)) {
         g.writeString(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(value));
      } else {
         super.serialize((Temporal)value, g, provider);
      }
   }

   public boolean shouldWriteWithZoneId(SerializerProvider ctxt) {
      return this._writeZoneId != null ? this._writeZoneId : ctxt.isEnabled(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
   }

   protected JsonToken serializationShape(SerializerProvider provider) {
      return !this.useTimestamp(provider) && this.shouldWriteWithZoneId(provider) ? JsonToken.VALUE_STRING : super.serializationShape(provider);
   }
}
