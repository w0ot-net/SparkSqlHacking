package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.MethodHandle;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.apache.spark.QueryContext;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Option;
import scala.Tuple3;

public final class SparkDateTimeUtils$ implements SparkDateTimeUtils {
   public static final SparkDateTimeUtils$ MODULE$ = new SparkDateTimeUtils$();
   private static TimeZone TimeZoneUTC;
   private static Pattern singleHourTz;
   private static Pattern singleMinuteTz;
   private static long org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS;
   private static String org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName;
   private static MethodHandle org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle;
   private static volatile boolean bitmap$0;

   static {
      SparkDateTimeUtils.$init$(MODULE$);
   }

   public ZoneId getZoneId(final String timeZoneId) {
      return SparkDateTimeUtils.getZoneId$(this, timeZoneId);
   }

   public TimeZone getTimeZone(final String timeZoneId) {
      return SparkDateTimeUtils.getTimeZone$(this, timeZoneId);
   }

   public int anyToDays(final Object obj) {
      return SparkDateTimeUtils.anyToDays$(this, obj);
   }

   public long anyToMicros(final Object obj) {
      return SparkDateTimeUtils.anyToMicros$(this, obj);
   }

   public long microsToMillis(final long micros) {
      return SparkDateTimeUtils.microsToMillis$(this, micros);
   }

   public long millisToMicros(final long millis) {
      return SparkDateTimeUtils.millisToMicros$(this, millis);
   }

   public Instant microsToInstant(final long micros) {
      return SparkDateTimeUtils.microsToInstant$(this, micros);
   }

   public long instantToMicros(final Instant instant) {
      return SparkDateTimeUtils.instantToMicros$(this, instant);
   }

   public long convertTz(final long micros, final ZoneId fromZone, final ZoneId toZone) {
      return SparkDateTimeUtils.convertTz$(this, micros, fromZone, toZone);
   }

   public LocalDateTime getLocalDateTime(final long micros, final ZoneId zoneId) {
      return SparkDateTimeUtils.getLocalDateTime$(this, micros, zoneId);
   }

   public LocalDateTime microsToLocalDateTime(final long micros) {
      return SparkDateTimeUtils.microsToLocalDateTime$(this, micros);
   }

   public long localDateTimeToMicros(final LocalDateTime localDateTime) {
      return SparkDateTimeUtils.localDateTimeToMicros$(this, localDateTime);
   }

   public int localDateToDays(final LocalDate localDate) {
      return SparkDateTimeUtils.localDateToDays$(this, localDate);
   }

   public LocalDate daysToLocalDate(final int days) {
      return SparkDateTimeUtils.daysToLocalDate$(this, days);
   }

   public int microsToDays(final long micros, final ZoneId zoneId) {
      return SparkDateTimeUtils.microsToDays$(this, micros, zoneId);
   }

   public long daysToMicros(final int days, final ZoneId zoneId) {
      return SparkDateTimeUtils.daysToMicros$(this, days, zoneId);
   }

   public int fromJavaDate(final Date date) {
      return SparkDateTimeUtils.fromJavaDate$(this, date);
   }

   public Date toJavaDate(final int days) {
      return SparkDateTimeUtils.toJavaDate$(this, days);
   }

   public Timestamp toJavaTimestamp(final long micros) {
      return SparkDateTimeUtils.toJavaTimestamp$(this, micros);
   }

   public Timestamp toJavaTimestamp(final String timeZoneId, final long micros) {
      return SparkDateTimeUtils.toJavaTimestamp$(this, timeZoneId, micros);
   }

   public Timestamp toJavaTimestampNoRebase(final long micros) {
      return SparkDateTimeUtils.toJavaTimestampNoRebase$(this, micros);
   }

   public long fromJavaTimestamp(final Timestamp t) {
      return SparkDateTimeUtils.fromJavaTimestamp$(this, t);
   }

   public long fromJavaTimestamp(final String timeZoneId, final Timestamp t) {
      return SparkDateTimeUtils.fromJavaTimestamp$(this, timeZoneId, t);
   }

   public long fromJavaTimestampNoRebase(final Timestamp t) {
      return SparkDateTimeUtils.fromJavaTimestampNoRebase$(this, t);
   }

   public Option stringToDate(final UTF8String s) {
      return SparkDateTimeUtils.stringToDate$(this, s);
   }

   public int stringToDateAnsi(final UTF8String s, final QueryContext context) {
      return SparkDateTimeUtils.stringToDateAnsi$(this, s, context);
   }

   public QueryContext stringToDateAnsi$default$2() {
      return SparkDateTimeUtils.stringToDateAnsi$default$2$(this);
   }

   public Tuple3 parseTimestampString(final UTF8String s) {
      return SparkDateTimeUtils.parseTimestampString$(this, s);
   }

   public Option stringToTimestamp(final UTF8String s, final ZoneId timeZoneId) {
      return SparkDateTimeUtils.stringToTimestamp$(this, s, timeZoneId);
   }

   public long stringToTimestampAnsi(final UTF8String s, final ZoneId timeZoneId, final QueryContext context) {
      return SparkDateTimeUtils.stringToTimestampAnsi$(this, s, timeZoneId, context);
   }

   public QueryContext stringToTimestampAnsi$default$3() {
      return SparkDateTimeUtils.stringToTimestampAnsi$default$3$(this);
   }

   public Option stringToTimestampWithoutTimeZone(final UTF8String s, final boolean allowTimeZone) {
      return SparkDateTimeUtils.stringToTimestampWithoutTimeZone$(this, s, allowTimeZone);
   }

   public final TimeZone TimeZoneUTC() {
      return TimeZoneUTC;
   }

   public final Pattern singleHourTz() {
      return singleHourTz;
   }

   public final Pattern singleMinuteTz() {
      return singleMinuteTz;
   }

   public long org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS() {
      return org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS;
   }

   public String org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName() {
      return org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName;
   }

   private MethodHandle org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle = SparkDateTimeUtils.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle$(this);
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle;
   }

   public MethodHandle org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle() {
      return !bitmap$0 ? this.org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle$lzycompute() : org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$getOffsetsByWallHandle;
   }

   public final void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$TimeZoneUTC_$eq(final TimeZone x$1) {
      TimeZoneUTC = x$1;
   }

   public final void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$singleHourTz_$eq(final Pattern x$1) {
      singleHourTz = x$1;
   }

   public final void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$singleMinuteTz_$eq(final Pattern x$1) {
      singleMinuteTz = x$1;
   }

   public final void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS_$eq(final long x$1) {
      org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$MIN_SECONDS = x$1;
   }

   public final void org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName_$eq(final String x$1) {
      org$apache$spark$sql$catalyst$util$SparkDateTimeUtils$$zoneInfoClassName = x$1;
   }

   private SparkDateTimeUtils$() {
   }
}
