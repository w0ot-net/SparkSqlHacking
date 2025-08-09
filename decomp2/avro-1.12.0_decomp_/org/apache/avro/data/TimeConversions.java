package org.apache.avro.data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;

public class TimeConversions {
   public static class DateConversion extends Conversion {
      public Class getConvertedType() {
         return LocalDate.class;
      }

      public String getLogicalTypeName() {
         return "date";
      }

      public LocalDate fromInt(Integer daysFromEpoch, Schema schema, LogicalType type) {
         return LocalDate.ofEpochDay((long)daysFromEpoch);
      }

      public Integer toInt(LocalDate date, Schema schema, LogicalType type) {
         long epochDays = date.toEpochDay();
         return (int)epochDays;
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.date().addToSchema(Schema.create(Schema.Type.INT));
      }
   }

   public static class TimeMillisConversion extends Conversion {
      public Class getConvertedType() {
         return LocalTime.class;
      }

      public String getLogicalTypeName() {
         return "time-millis";
      }

      public String adjustAndSetValue(String varName, String valParamName) {
         return varName + " = " + valParamName + ".truncatedTo(java.time.temporal.ChronoUnit.MILLIS);";
      }

      public LocalTime fromInt(Integer millisFromMidnight, Schema schema, LogicalType type) {
         return LocalTime.ofNanoOfDay(TimeUnit.MILLISECONDS.toNanos((long)millisFromMidnight));
      }

      public Integer toInt(LocalTime time, Schema schema, LogicalType type) {
         return (int)TimeUnit.NANOSECONDS.toMillis(time.toNanoOfDay());
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.timeMillis().addToSchema(Schema.create(Schema.Type.INT));
      }
   }

   public static class TimeMicrosConversion extends Conversion {
      public Class getConvertedType() {
         return LocalTime.class;
      }

      public String getLogicalTypeName() {
         return "time-micros";
      }

      public String adjustAndSetValue(String varName, String valParamName) {
         return varName + " = " + valParamName + ".truncatedTo(java.time.temporal.ChronoUnit.MICROS);";
      }

      public LocalTime fromLong(Long microsFromMidnight, Schema schema, LogicalType type) {
         return LocalTime.ofNanoOfDay(TimeUnit.MICROSECONDS.toNanos(microsFromMidnight));
      }

      public Long toLong(LocalTime time, Schema schema, LogicalType type) {
         return TimeUnit.NANOSECONDS.toMicros(time.toNanoOfDay());
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.timeMicros().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }

   public static class TimestampMillisConversion extends Conversion {
      public Class getConvertedType() {
         return Instant.class;
      }

      public String getLogicalTypeName() {
         return "timestamp-millis";
      }

      public String adjustAndSetValue(String varName, String valParamName) {
         return varName + " = " + valParamName + ".truncatedTo(java.time.temporal.ChronoUnit.MILLIS);";
      }

      public Instant fromLong(Long millisFromEpoch, Schema schema, LogicalType type) {
         return Instant.ofEpochMilli(millisFromEpoch);
      }

      public Long toLong(Instant timestamp, Schema schema, LogicalType type) {
         return timestamp.toEpochMilli();
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.timestampMillis().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }

   public static class TimestampMicrosConversion extends Conversion {
      public Class getConvertedType() {
         return Instant.class;
      }

      public String getLogicalTypeName() {
         return "timestamp-micros";
      }

      public String adjustAndSetValue(String varName, String valParamName) {
         return varName + " = " + valParamName + ".truncatedTo(java.time.temporal.ChronoUnit.MICROS);";
      }

      public Instant fromLong(Long microsFromEpoch, Schema schema, LogicalType type) {
         long epochSeconds = microsFromEpoch / 1000000L;
         long nanoAdjustment = microsFromEpoch % 1000000L * 1000L;
         return Instant.ofEpochSecond(epochSeconds, nanoAdjustment);
      }

      public Long toLong(Instant instant, Schema schema, LogicalType type) {
         long seconds = instant.getEpochSecond();
         int nanos = instant.getNano();
         if (seconds < 0L && nanos > 0) {
            long micros = Math.multiplyExact(seconds + 1L, 1000000L);
            long adjustment = (long)nanos / 1000L - 1000000L;
            return Math.addExact(micros, adjustment);
         } else {
            long micros = Math.multiplyExact(seconds, 1000000L);
            return Math.addExact(micros, (long)nanos / 1000L);
         }
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.timestampMicros().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }

   public static class TimestampNanosConversion extends Conversion {
      public Class getConvertedType() {
         return Instant.class;
      }

      public String getLogicalTypeName() {
         return "timestamp-nanos";
      }

      public String adjustAndSetValue(String varName, String valParamName) {
         return varName + " = " + valParamName + ".truncatedTo(java.time.temporal.ChronoUnit.NANOS);";
      }

      public Instant fromLong(Long microsFromEpoch, Schema schema, LogicalType type) {
         long epochSeconds = microsFromEpoch / 1000000000L;
         long nanoAdjustment = microsFromEpoch % 1000000000L;
         return Instant.ofEpochSecond(epochSeconds, nanoAdjustment);
      }

      public Long toLong(Instant instant, Schema schema, LogicalType type) {
         long seconds = instant.getEpochSecond();
         int nanos = instant.getNano();
         if (seconds < 0L && nanos > 0) {
            long micros = Math.multiplyExact(seconds + 1L, 1000000000L);
            long adjustment = (long)(nanos - 1000000);
            return Math.addExact(micros, adjustment);
         } else {
            long micros = Math.multiplyExact(seconds, 1000000000L);
            return Math.addExact(micros, (long)nanos);
         }
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.timestampNanos().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }

   public static class LocalTimestampMillisConversion extends Conversion {
      private final TimestampMillisConversion timestampMillisConversion = new TimestampMillisConversion();

      public Class getConvertedType() {
         return LocalDateTime.class;
      }

      public String getLogicalTypeName() {
         return "local-timestamp-millis";
      }

      public LocalDateTime fromLong(Long millisFromEpoch, Schema schema, LogicalType type) {
         Instant instant = this.timestampMillisConversion.fromLong(millisFromEpoch, schema, type);
         return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
      }

      public Long toLong(LocalDateTime timestamp, Schema schema, LogicalType type) {
         Instant instant = timestamp.toInstant(ZoneOffset.UTC);
         return this.timestampMillisConversion.toLong(instant, schema, type);
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.localTimestampMillis().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }

   public static class LocalTimestampMicrosConversion extends Conversion {
      private final TimestampMicrosConversion timestampMicrosConversion = new TimestampMicrosConversion();

      public Class getConvertedType() {
         return LocalDateTime.class;
      }

      public String getLogicalTypeName() {
         return "local-timestamp-micros";
      }

      public LocalDateTime fromLong(Long microsFromEpoch, Schema schema, LogicalType type) {
         Instant instant = this.timestampMicrosConversion.fromLong(microsFromEpoch, schema, type);
         return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
      }

      public Long toLong(LocalDateTime timestamp, Schema schema, LogicalType type) {
         Instant instant = timestamp.toInstant(ZoneOffset.UTC);
         return this.timestampMicrosConversion.toLong(instant, schema, type);
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.localTimestampMicros().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }

   public static class LocalTimestampNanosConversion extends Conversion {
      private final TimestampNanosConversion timestampNanosConversion = new TimestampNanosConversion();

      public Class getConvertedType() {
         return LocalDateTime.class;
      }

      public String getLogicalTypeName() {
         return "local-timestamp-nanos";
      }

      public LocalDateTime fromLong(Long microsFromEpoch, Schema schema, LogicalType type) {
         Instant instant = this.timestampNanosConversion.fromLong(microsFromEpoch, schema, type);
         return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
      }

      public Long toLong(LocalDateTime timestamp, Schema schema, LogicalType type) {
         Instant instant = timestamp.toInstant(ZoneOffset.UTC);
         return this.timestampNanosConversion.toLong(instant, schema, type);
      }

      public Schema getRecommendedSchema() {
         return LogicalTypes.localTimestampNanos().addToSchema(Schema.create(Schema.Type.LONG));
      }
   }
}
