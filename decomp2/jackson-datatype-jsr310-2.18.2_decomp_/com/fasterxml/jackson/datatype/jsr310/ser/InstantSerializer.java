package com.fasterxml.jackson.datatype.jsr310.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class InstantSerializer extends InstantSerializerBase {
   private static final long serialVersionUID = 1L;
   public static final InstantSerializer INSTANCE = new InstantSerializer();

   protected InstantSerializer() {
      super((Class)Instant.class, (ToLongFunction)(Instant::toEpochMilli), (ToLongFunction)(Instant::getEpochSecond), (ToIntFunction)(Instant::getNano), (DateTimeFormatter)null);
   }

   /** @deprecated */
   @Deprecated
   protected InstantSerializer(InstantSerializer base, Boolean useTimestamp, DateTimeFormatter formatter) {
      this(base, useTimestamp, base._useNanoseconds, formatter);
   }

   protected InstantSerializer(InstantSerializer base, Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      super((InstantSerializerBase)base, (Boolean)useTimestamp, (Boolean)base._useNanoseconds, (DateTimeFormatter)formatter, (JsonFormat.Shape)shape);
   }

   protected InstantSerializer(InstantSerializer base, Boolean useTimestamp, Boolean useNanoseconds, DateTimeFormatter formatter) {
      super(base, useTimestamp, useNanoseconds, formatter);
   }

   protected JSR310FormattedSerializerBase withFormat(Boolean useTimestamp, DateTimeFormatter formatter, JsonFormat.Shape shape) {
      return new InstantSerializer(this, useTimestamp, formatter, shape);
   }

   protected JSR310FormattedSerializerBase withFeatures(Boolean writeZoneId, Boolean writeNanoseconds) {
      return new InstantSerializer(this, this._useTimestamp, writeNanoseconds, this._formatter);
   }
}
