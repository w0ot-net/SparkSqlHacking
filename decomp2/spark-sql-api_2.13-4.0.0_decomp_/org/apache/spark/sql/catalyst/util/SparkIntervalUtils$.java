package org.apache.spark.sql.catalyst.util;

import java.time.Duration;
import java.time.Period;
import org.apache.spark.unsafe.types.CalendarInterval;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;

public final class SparkIntervalUtils$ implements SparkIntervalUtils {
   public static final SparkIntervalUtils$ MODULE$ = new SparkIntervalUtils$();
   private static long MAX_DAY;
   private static long MAX_HOUR;
   private static long MAX_MINUTE;
   private static long MAX_SECOND;
   private static long MIN_SECOND;
   private static long org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds;
   private static UTF8String intervalStr;
   private static UTF8String yearStr;
   private static UTF8String monthStr;
   private static UTF8String weekStr;
   private static UTF8String dayStr;
   private static UTF8String hourStr;
   private static UTF8String minuteStr;
   private static UTF8String secondStr;
   private static UTF8String millisStr;
   private static UTF8String microsStr;
   private static UTF8String nanosStr;
   private static volatile SparkIntervalUtils.ParseState$ ParseState$module;

   static {
      SparkIntervalUtils.$init$(MODULE$);
   }

   public long durationToMicros(final Duration duration) {
      return SparkIntervalUtils.durationToMicros$(this, duration);
   }

   public long durationToMicros(final Duration duration, final byte endField) {
      return SparkIntervalUtils.durationToMicros$(this, duration, endField);
   }

   public int periodToMonths(final Period period) {
      return SparkIntervalUtils.periodToMonths$(this, period);
   }

   public int periodToMonths(final Period period, final byte endField) {
      return SparkIntervalUtils.periodToMonths$(this, period, endField);
   }

   public Duration microsToDuration(final long micros) {
      return SparkIntervalUtils.microsToDuration$(this, micros);
   }

   public Period monthsToPeriod(final int months) {
      return SparkIntervalUtils.monthsToPeriod$(this, months);
   }

   public CalendarInterval stringToInterval(final UTF8String input) {
      return SparkIntervalUtils.stringToInterval$(this, input);
   }

   public String toYearMonthIntervalString(final int months, final Enumeration.Value style, final byte startField, final byte endField) {
      return SparkIntervalUtils.toYearMonthIntervalString$(this, months, style, startField, endField);
   }

   public String toDayTimeIntervalString(final long micros, final Enumeration.Value style, final byte startField, final byte endField) {
      return SparkIntervalUtils.toDayTimeIntervalString$(this, micros, style, startField, endField);
   }

   public UTF8String unitToUtf8(final String unit) {
      return SparkIntervalUtils.unitToUtf8$(this, unit);
   }

   public long MAX_DAY() {
      return MAX_DAY;
   }

   public long MAX_HOUR() {
      return MAX_HOUR;
   }

   public long MAX_MINUTE() {
      return MAX_MINUTE;
   }

   public long MAX_SECOND() {
      return MAX_SECOND;
   }

   public long MIN_SECOND() {
      return MIN_SECOND;
   }

   public final long org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds() {
      return org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds;
   }

   public UTF8String intervalStr() {
      return intervalStr;
   }

   public UTF8String yearStr() {
      return yearStr;
   }

   public UTF8String monthStr() {
      return monthStr;
   }

   public UTF8String weekStr() {
      return weekStr;
   }

   public UTF8String dayStr() {
      return dayStr;
   }

   public UTF8String hourStr() {
      return hourStr;
   }

   public UTF8String minuteStr() {
      return minuteStr;
   }

   public UTF8String secondStr() {
      return secondStr;
   }

   public UTF8String millisStr() {
      return millisStr;
   }

   public UTF8String microsStr() {
      return microsStr;
   }

   public UTF8String nanosStr() {
      return nanosStr;
   }

   public SparkIntervalUtils.ParseState$ org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState() {
      if (ParseState$module == null) {
         this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState$lzycompute$1();
      }

      return ParseState$module;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_DAY_$eq(final long x$1) {
      MAX_DAY = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_HOUR_$eq(final long x$1) {
      MAX_HOUR = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_MINUTE_$eq(final long x$1) {
      MAX_MINUTE = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_SECOND_$eq(final long x$1) {
      MAX_SECOND = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MIN_SECOND_$eq(final long x$1) {
      MIN_SECOND = x$1;
   }

   public final void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds_$eq(final long x$1) {
      org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$intervalStr_$eq(final UTF8String x$1) {
      intervalStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$yearStr_$eq(final UTF8String x$1) {
      yearStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$monthStr_$eq(final UTF8String x$1) {
      monthStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$weekStr_$eq(final UTF8String x$1) {
      weekStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$dayStr_$eq(final UTF8String x$1) {
      dayStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$hourStr_$eq(final UTF8String x$1) {
      hourStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$minuteStr_$eq(final UTF8String x$1) {
      minuteStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$secondStr_$eq(final UTF8String x$1) {
      secondStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$millisStr_$eq(final UTF8String x$1) {
      millisStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$microsStr_$eq(final UTF8String x$1) {
      microsStr = x$1;
   }

   public void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$nanosStr_$eq(final UTF8String x$1) {
      nanosStr = x$1;
   }

   private final void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState$lzycompute$1() {
      synchronized(this){}

      try {
         if (ParseState$module == null) {
            ParseState$module = new SparkIntervalUtils.ParseState$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private SparkIntervalUtils$() {
   }
}
