package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class TimeSerializers {
   public static void addDefaultSerializers(Kryo kryo) {
      if (Util.isClassAvailable("java.time.Duration")) {
         kryo.addDefaultSerializer(Duration.class, (Serializer)(new DurationSerializer()));
      }

      if (Util.isClassAvailable("java.time.Instant")) {
         kryo.addDefaultSerializer(Instant.class, (Serializer)(new InstantSerializer()));
      }

      if (Util.isClassAvailable("java.time.LocalDate")) {
         kryo.addDefaultSerializer(LocalDate.class, (Serializer)(new LocalDateSerializer()));
      }

      if (Util.isClassAvailable("java.time.LocalTime")) {
         kryo.addDefaultSerializer(LocalTime.class, (Serializer)(new LocalTimeSerializer()));
      }

      if (Util.isClassAvailable("java.time.LocalDateTime")) {
         kryo.addDefaultSerializer(LocalDateTime.class, (Serializer)(new LocalDateTimeSerializer()));
      }

      if (Util.isClassAvailable("java.time.ZoneOffset")) {
         kryo.addDefaultSerializer(ZoneOffset.class, (Serializer)(new ZoneOffsetSerializer()));
      }

      if (Util.isClassAvailable("java.time.ZoneId")) {
         kryo.addDefaultSerializer(ZoneId.class, (Serializer)(new ZoneIdSerializer()));
      }

      if (Util.isClassAvailable("java.time.OffsetTime")) {
         kryo.addDefaultSerializer(OffsetTime.class, (Serializer)(new OffsetTimeSerializer()));
      }

      if (Util.isClassAvailable("java.time.OffsetDateTime")) {
         kryo.addDefaultSerializer(OffsetDateTime.class, (Serializer)(new OffsetDateTimeSerializer()));
      }

      if (Util.isClassAvailable("java.time.ZonedDateTime")) {
         kryo.addDefaultSerializer(ZonedDateTime.class, (Serializer)(new ZonedDateTimeSerializer()));
      }

      if (Util.isClassAvailable("java.time.Year")) {
         kryo.addDefaultSerializer(Year.class, (Serializer)(new YearSerializer()));
      }

      if (Util.isClassAvailable("java.time.YearMonth")) {
         kryo.addDefaultSerializer(YearMonth.class, (Serializer)(new YearMonthSerializer()));
      }

      if (Util.isClassAvailable("java.time.MonthDay")) {
         kryo.addDefaultSerializer(MonthDay.class, (Serializer)(new MonthDaySerializer()));
      }

      if (Util.isClassAvailable("java.time.Period")) {
         kryo.addDefaultSerializer(Period.class, (Serializer)(new PeriodSerializer()));
      }

   }

   private static class DurationSerializer extends Serializer {
      private DurationSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, Duration duration) {
         out.writeLong(duration.getSeconds());
         out.writeInt(duration.getNano(), true);
      }

      public Duration read(Kryo kryo, Input in, Class type) {
         long seconds = in.readLong();
         int nanos = in.readInt(true);
         return Duration.ofSeconds(seconds, (long)nanos);
      }
   }

   private static class InstantSerializer extends Serializer {
      private InstantSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, Instant instant) {
         out.writeLong(instant.getEpochSecond(), true);
         out.writeInt(instant.getNano(), true);
      }

      public Instant read(Kryo kryo, Input in, Class type) {
         long seconds = in.readLong(true);
         int nanos = in.readInt(true);
         return Instant.ofEpochSecond(seconds, (long)nanos);
      }
   }

   private static class LocalDateSerializer extends Serializer {
      private LocalDateSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, LocalDate date) {
         write(out, date);
      }

      static void write(Output out, LocalDate date) {
         out.writeInt(date.getYear(), true);
         out.writeByte(date.getMonthValue());
         out.writeByte(date.getDayOfMonth());
      }

      public LocalDate read(Kryo kryo, Input in, Class type) {
         return read(in);
      }

      static LocalDate read(Input in) {
         int year = in.readInt(true);
         int month = in.readByte();
         int dayOfMonth = in.readByte();
         return LocalDate.of(year, month, dayOfMonth);
      }
   }

   private static class LocalDateTimeSerializer extends Serializer {
      private LocalDateTimeSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, LocalDateTime dateTime) {
         TimeSerializers.LocalDateSerializer.write(out, dateTime.toLocalDate());
         TimeSerializers.LocalTimeSerializer.write(out, dateTime.toLocalTime());
      }

      public LocalDateTime read(Kryo kryo, Input in, Class type) {
         LocalDate date = TimeSerializers.LocalDateSerializer.read(in);
         LocalTime time = TimeSerializers.LocalTimeSerializer.read(in);
         return LocalDateTime.of(date, time);
      }
   }

   private static class LocalTimeSerializer extends Serializer {
      private LocalTimeSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, LocalTime time) {
         write(out, time);
      }

      static void write(Output out, LocalTime time) {
         if (time.getNano() == 0) {
            if (time.getSecond() == 0) {
               if (time.getMinute() == 0) {
                  out.writeByte(~time.getHour());
               } else {
                  out.writeByte(time.getHour());
                  out.writeByte(~time.getMinute());
               }
            } else {
               out.writeByte(time.getHour());
               out.writeByte(time.getMinute());
               out.writeByte(~time.getSecond());
            }
         } else {
            out.writeByte(time.getHour());
            out.writeByte(time.getMinute());
            out.writeByte(time.getSecond());
            out.writeInt(time.getNano(), true);
         }

      }

      public LocalTime read(Kryo kryo, Input in, Class type) {
         return read(in);
      }

      static LocalTime read(Input in) {
         int hour = in.readByte();
         int minute = 0;
         int second = 0;
         int nano = 0;
         if (hour < 0) {
            hour = ~hour;
         } else {
            minute = in.readByte();
            if (minute < 0) {
               minute = ~minute;
            } else {
               second = in.readByte();
               if (second < 0) {
                  second = ~second;
               } else {
                  nano = in.readInt(true);
               }
            }
         }

         return LocalTime.of(hour, minute, second, nano);
      }
   }

   private static class ZoneOffsetSerializer extends Serializer {
      private ZoneOffsetSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, ZoneOffset obj) {
         write(out, obj);
      }

      static void write(Output out, ZoneOffset obj) {
         int offsetSecs = obj.getTotalSeconds();
         int offsetByte = offsetSecs % 900 == 0 ? offsetSecs / 900 : 127;
         out.writeByte(offsetByte);
         if (offsetByte == 127) {
            out.writeInt(offsetSecs);
         }

      }

      public ZoneOffset read(Kryo kryo, Input in, Class type) {
         return read(in);
      }

      static ZoneOffset read(Input in) {
         int offsetByte = in.readByte();
         return offsetByte == 127 ? ZoneOffset.ofTotalSeconds(in.readInt()) : ZoneOffset.ofTotalSeconds(offsetByte * 900);
      }
   }

   private static class ZoneIdSerializer extends Serializer {
      private ZoneIdSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, ZoneId obj) {
         write(out, obj);
      }

      static void write(Output out, ZoneId obj) {
         out.writeString(obj.getId());
      }

      public ZoneId read(Kryo kryo, Input in, Class type) {
         return read(in);
      }

      static ZoneId read(Input in) {
         String id = in.readString();
         return ZoneId.of(id);
      }
   }

   private static class OffsetTimeSerializer extends Serializer {
      private OffsetTimeSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, OffsetTime obj) {
         TimeSerializers.LocalTimeSerializer.write(out, obj.toLocalTime());
         TimeSerializers.ZoneOffsetSerializer.write(out, obj.getOffset());
      }

      public OffsetTime read(Kryo kryo, Input in, Class type) {
         LocalTime time = TimeSerializers.LocalTimeSerializer.read(in);
         ZoneOffset offset = TimeSerializers.ZoneOffsetSerializer.read(in);
         return OffsetTime.of(time, offset);
      }
   }

   private static class OffsetDateTimeSerializer extends Serializer {
      private OffsetDateTimeSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, OffsetDateTime obj) {
         TimeSerializers.LocalDateSerializer.write(out, obj.toLocalDate());
         TimeSerializers.LocalTimeSerializer.write(out, obj.toLocalTime());
         TimeSerializers.ZoneOffsetSerializer.write(out, obj.getOffset());
      }

      public OffsetDateTime read(Kryo kryo, Input in, Class type) {
         LocalDate date = TimeSerializers.LocalDateSerializer.read(in);
         LocalTime time = TimeSerializers.LocalTimeSerializer.read(in);
         ZoneOffset offset = TimeSerializers.ZoneOffsetSerializer.read(in);
         return OffsetDateTime.of(date, time, offset);
      }
   }

   private static class ZonedDateTimeSerializer extends Serializer {
      private ZonedDateTimeSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, ZonedDateTime obj) {
         TimeSerializers.LocalDateSerializer.write(out, obj.toLocalDate());
         TimeSerializers.LocalTimeSerializer.write(out, obj.toLocalTime());
         TimeSerializers.ZoneIdSerializer.write(out, obj.getZone());
      }

      public ZonedDateTime read(Kryo kryo, Input in, Class type) {
         LocalDate date = TimeSerializers.LocalDateSerializer.read(in);
         LocalTime time = TimeSerializers.LocalTimeSerializer.read(in);
         ZoneId zone = TimeSerializers.ZoneIdSerializer.read(in);
         return ZonedDateTime.of(date, time, zone);
      }
   }

   private static class YearSerializer extends Serializer {
      private YearSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, Year obj) {
         out.writeInt(obj.getValue(), true);
      }

      public Year read(Kryo kryo, Input in, Class type) {
         return Year.of(in.readInt(true));
      }
   }

   private static class YearMonthSerializer extends Serializer {
      private YearMonthSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, YearMonth obj) {
         out.writeInt(obj.getYear(), true);
         out.writeByte(obj.getMonthValue());
      }

      public YearMonth read(Kryo kryo, Input in, Class type) {
         int year = in.readInt(true);
         byte month = in.readByte();
         return YearMonth.of(year, month);
      }
   }

   private static class MonthDaySerializer extends Serializer {
      private MonthDaySerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, MonthDay obj) {
         out.writeByte(obj.getMonthValue());
         out.writeByte(obj.getDayOfMonth());
      }

      public MonthDay read(Kryo kryo, Input in, Class type) {
         byte month = in.readByte();
         byte day = in.readByte();
         return MonthDay.of(month, day);
      }
   }

   private static class PeriodSerializer extends Serializer {
      private PeriodSerializer() {
         this.setImmutable(true);
      }

      public void write(Kryo kryo, Output out, Period obj) {
         out.writeInt(obj.getYears(), true);
         out.writeInt(obj.getMonths(), true);
         out.writeInt(obj.getDays(), true);
      }

      public Period read(Kryo kryo, Input in, Class type) {
         int years = in.readInt(true);
         int months = in.readInt(true);
         int days = in.readInt(true);
         return Period.of(years, months, days);
      }
   }
}
