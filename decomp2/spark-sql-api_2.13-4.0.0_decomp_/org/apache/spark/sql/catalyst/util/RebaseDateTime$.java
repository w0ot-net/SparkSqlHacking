package org.apache.spark.sql.catalyst.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.scala.ClassTagExtensions;
import com.fasterxml.jackson.module.scala.JavaTypeable;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.zone.ZoneOffsetTransition;
import java.util.Calendar;
import java.util.TimeZone;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class RebaseDateTime$ {
   public static final RebaseDateTime$ MODULE$ = new RebaseDateTime$();
   private static ObjectMapper mapper;
   private static final int[] julianGregDiffs = new int[]{2, 1, 0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, 0};
   private static final int[] julianGregDiffSwitchDay = new int[]{-719164, -682945, -646420, -609895, -536845, -500320, -463795, -390745, -354220, -317695, -244645, -208120, -171595, -141427};
   private static final int lastSwitchJulianDay;
   private static final int julianCommonEraStartDay;
   private static final int[] gregJulianDiffs;
   private static final int[] gregJulianDiffSwitchDay;
   private static final int lastSwitchGregorianDay;
   private static final int gregorianCommonEraStartDay;
   private static final LocalDate gregorianStartDate;
   private static final LocalDate julianEndDate;
   private static final HashMap gregJulianRebaseMap;
   private static final long lastSwitchGregorianTs;
   private static final LocalDateTime gregorianStartTs;
   private static final LocalDateTime julianEndTs;
   private static final HashMap julianGregRebaseMap;
   private static final long lastSwitchJulianTs;
   private static volatile boolean bitmap$0;

   static {
      lastSwitchJulianDay = BoxesRunTime.unboxToInt(.MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps(MODULE$.julianGregDiffSwitchDay())));
      julianCommonEraStartDay = MODULE$.julianGregDiffSwitchDay()[0];
      gregJulianDiffs = new int[]{-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
      gregJulianDiffSwitchDay = new int[]{-719162, -682944, -646420, -609896, -536847, -500323, -463799, -390750, -354226, -317702, -244653, -208129, -171605, -141436, -141435, -141434, -141433, -141432, -141431, -141430, -141429, -141428, -141427};
      lastSwitchGregorianDay = BoxesRunTime.unboxToInt(.MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps(MODULE$.gregJulianDiffSwitchDay())));
      gregorianCommonEraStartDay = MODULE$.gregJulianDiffSwitchDay()[0];
      gregorianStartDate = LocalDate.of(1582, 10, 15);
      julianEndDate = LocalDate.of(1582, 10, 4);
      gregJulianRebaseMap = MODULE$.loadRebaseRecords("gregorian-julian-rebase-micros.json");
      lastSwitchGregorianTs = MODULE$.getLastSwitchTs(MODULE$.gregJulianRebaseMap());
      gregorianStartTs = LocalDateTime.of(MODULE$.gregorianStartDate(), LocalTime.MIDNIGHT);
      julianEndTs = LocalDateTime.of(MODULE$.julianEndDate(), LocalTime.of(23, 59, 59, 999999999));
      julianGregRebaseMap = MODULE$.loadRebaseRecords("julian-gregorian-rebase-micros.json");
      lastSwitchJulianTs = MODULE$.getLastSwitchTs(MODULE$.julianGregRebaseMap());
   }

   private int rebaseDays(final int[] switches, final int[] diffs, final int days) {
      int i = switches.length;

      do {
         --i;
      } while(i > 0 && days < switches[i]);

      return days + diffs[i];
   }

   private int[] julianGregDiffs() {
      return julianGregDiffs;
   }

   private int[] julianGregDiffSwitchDay() {
      return julianGregDiffSwitchDay;
   }

   public final int lastSwitchJulianDay() {
      return lastSwitchJulianDay;
   }

   private final int julianCommonEraStartDay() {
      return julianCommonEraStartDay;
   }

   public int localRebaseJulianToGregorianDays(final int days) {
      Calendar utcCal = (new Calendar.Builder()).setCalendarType("gregory").setTimeZone(SparkDateTimeUtils$.MODULE$.TimeZoneUTC()).setInstant(Math.multiplyExact((long)days, 86400000L)).build();
      LocalDate localDate = LocalDate.of(utcCal.get(1), utcCal.get(2) + 1, 1).with(ChronoField.ERA, (long)utcCal.get(0)).plusDays((long)(utcCal.get(5) - 1));
      return Math.toIntExact(localDate.toEpochDay());
   }

   public int rebaseJulianToGregorianDays(final int days) {
      return days < this.julianCommonEraStartDay() ? this.localRebaseJulianToGregorianDays(days) : this.rebaseDays(this.julianGregDiffSwitchDay(), this.julianGregDiffs(), days);
   }

   private int[] gregJulianDiffs() {
      return gregJulianDiffs;
   }

   private int[] gregJulianDiffSwitchDay() {
      return gregJulianDiffSwitchDay;
   }

   public final int lastSwitchGregorianDay() {
      return lastSwitchGregorianDay;
   }

   private final int gregorianCommonEraStartDay() {
      return gregorianCommonEraStartDay;
   }

   private final LocalDate gregorianStartDate() {
      return gregorianStartDate;
   }

   private final LocalDate julianEndDate() {
      return julianEndDate;
   }

   public int localRebaseGregorianToJulianDays(final int days) {
      LocalDate localDate = LocalDate.ofEpochDay((long)days);
      if (localDate.isAfter(this.julianEndDate()) && localDate.isBefore(this.gregorianStartDate())) {
         localDate = this.gregorianStartDate();
      }

      Calendar utcCal = (new Calendar.Builder()).setCalendarType("gregory").setTimeZone(SparkDateTimeUtils$.MODULE$.TimeZoneUTC()).setDate(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()).build();
      scala.Predef..MODULE$.assert(utcCal.getTimeInMillis() % 86400000L == 0L);
      return Math.toIntExact(utcCal.getTimeInMillis() / 86400000L);
   }

   public int rebaseGregorianToJulianDays(final int days) {
      return days < this.gregorianCommonEraStartDay() ? this.localRebaseGregorianToJulianDays(days) : this.rebaseDays(this.gregJulianDiffSwitchDay(), this.gregJulianDiffs(), days);
   }

   private long rebaseMicros(final RebaseDateTime.RebaseInfo rebaseInfo, final long micros) {
      long[] switches = rebaseInfo.switches();
      int i = switches.length;

      do {
         --i;
      } while(i > 0 && micros < switches[i]);

      return micros + rebaseInfo.diffs()[i];
   }

   private ObjectMapper mapper$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            ObjectMapper mapper = new ClassTagExtensions() {
               /** @deprecated */
               public final ObjectMapper addMixin(final ClassTag evidence$1, final ClassTag evidence$2) {
                  return ClassTagExtensions.addMixin$(this, evidence$1, evidence$2);
               }

               /** @deprecated */
               public final Class findMixInClassFor(final ClassTag evidence$3) {
                  return ClassTagExtensions.findMixInClassFor$(this, evidence$3);
               }

               public JavaType constructType(final JavaTypeable evidence$4) {
                  return ClassTagExtensions.constructType$(this, evidence$4);
               }

               public Object readValue(final JsonParser jp, final JavaTypeable evidence$5) {
                  return ClassTagExtensions.readValue$(this, jp, evidence$5);
               }

               public MappingIterator readValues(final JsonParser jp, final JavaTypeable evidence$6) {
                  return ClassTagExtensions.readValues$(this, jp, evidence$6);
               }

               public Object treeToValue(final TreeNode n, final JavaTypeable evidence$7) {
                  return ClassTagExtensions.treeToValue$(this, n, evidence$7);
               }

               public Object readValue(final File src, final JavaTypeable evidence$8) {
                  return ClassTagExtensions.readValue$(this, src, evidence$8);
               }

               public Object readValue(final URL src, final JavaTypeable evidence$9) {
                  return ClassTagExtensions.readValue$(this, src, evidence$9);
               }

               public Object readValue(final String content, final JavaTypeable evidence$10) {
                  return ClassTagExtensions.readValue$(this, content, evidence$10);
               }

               public Object readValue(final Reader src, final JavaTypeable evidence$11) {
                  return ClassTagExtensions.readValue$(this, src, evidence$11);
               }

               public Object readValue(final InputStream src, final JavaTypeable evidence$12) {
                  return ClassTagExtensions.readValue$(this, src, evidence$12);
               }

               public Object readValue(final byte[] src, final JavaTypeable evidence$13) {
                  return ClassTagExtensions.readValue$(this, src, evidence$13);
               }

               public Object readValue(final byte[] src, final int offset, final int len, final JavaTypeable evidence$14) {
                  return ClassTagExtensions.readValue$(this, src, offset, len, evidence$14);
               }

               public Object updateValue(final Object valueToUpdate, final File src, final JavaTypeable evidence$15) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$15);
               }

               public Object updateValue(final Object valueToUpdate, final URL src, final JavaTypeable evidence$16) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$16);
               }

               public Object updateValue(final Object valueToUpdate, final String content, final JavaTypeable evidence$17) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, content, evidence$17);
               }

               public Object updateValue(final Object valueToUpdate, final Reader src, final JavaTypeable evidence$18) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$18);
               }

               public Object updateValue(final Object valueToUpdate, final InputStream src, final JavaTypeable evidence$19) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$19);
               }

               public Object updateValue(final Object valueToUpdate, final byte[] src, final JavaTypeable evidence$20) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$20);
               }

               public Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final JavaTypeable evidence$21) {
                  return ClassTagExtensions.updateValue$(this, valueToUpdate, src, offset, len, evidence$21);
               }

               public ObjectWriter writerWithView(final ClassTag evidence$23) {
                  return ClassTagExtensions.writerWithView$(this, evidence$23);
               }

               public ObjectWriter writerFor(final JavaTypeable evidence$24) {
                  return ClassTagExtensions.writerFor$(this, evidence$24);
               }

               public ObjectReader readerFor(final JavaTypeable evidence$25) {
                  return ClassTagExtensions.readerFor$(this, evidence$25);
               }

               public ObjectReader readerWithView(final ClassTag evidence$26) {
                  return ClassTagExtensions.readerWithView$(this, evidence$26);
               }

               public Object convertValue(final Object fromValue, final JavaTypeable evidence$27) {
                  return ClassTagExtensions.convertValue$(this, fromValue, evidence$27);
               }

               public {
                  ClassTagExtensions.$init$(this);
               }
            };
            mapper.registerModule(com.fasterxml.jackson.module.scala.DefaultScalaModule..MODULE$);
            RebaseDateTime$.mapper = mapper;
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return RebaseDateTime$.mapper;
   }

   private ObjectMapper mapper() {
      return !bitmap$0 ? this.mapper$lzycompute() : mapper;
   }

   public HashMap loadRebaseRecords(final String fileName) {
      URL file = org.apache.spark.util.SparkClassUtils..MODULE$.getSparkClassLoader().getResource(fileName);
      Seq jsonRebaseRecords = (Seq)((ClassTagExtensions)this.mapper()).readValue(file, com.fasterxml.jackson.module.scala.JavaTypeable..MODULE$.collectionJavaTypeable(com.fasterxml.jackson.module.scala.JavaTypeable..MODULE$.gen0JavaTypeable(scala.reflect.ClassTag..MODULE$.apply(RebaseDateTime.JsonRebaseRecord.class)), scala.reflect.ClassTag..MODULE$.apply(Seq.class)));
      HashMap hashMap = new HashMap();
      hashMap.sizeHint(jsonRebaseRecords.size());
      jsonRebaseRecords.foreach((jsonRecord) -> {
         $anonfun$loadRebaseRecords$1(hashMap, jsonRecord);
         return BoxedUnit.UNIT;
      });
      return hashMap;
   }

   private HashMap gregJulianRebaseMap() {
      return gregJulianRebaseMap;
   }

   private long getLastSwitchTs(final HashMap rebaseMap) {
      long latestTs = BoxesRunTime.unboxToLong(((IterableOnceOps)rebaseMap.values().map((x$1) -> BoxesRunTime.boxToLong($anonfun$getLastSwitchTs$1(x$1)))).max(scala.math.Ordering.Long..MODULE$));
      scala.Predef..MODULE$.require(rebaseMap.values().forall((x$2) -> BoxesRunTime.boxToBoolean($anonfun$getLastSwitchTs$2(x$2))), () -> "Differences between Julian and Gregorian calendar after " + SparkDateTimeUtils$.MODULE$.microsToInstant(latestTs) + " are expected to be zero for all available time zones.");
      return latestTs;
   }

   public final long lastSwitchGregorianTs() {
      return lastSwitchGregorianTs;
   }

   private final LocalDateTime gregorianStartTs() {
      return gregorianStartTs;
   }

   private final LocalDateTime julianEndTs() {
      return julianEndTs;
   }

   public long rebaseGregorianToJulianMicros(final TimeZone tz, final long micros) {
      Instant instant = SparkDateTimeUtils$.MODULE$.microsToInstant(micros);
      ZoneId zoneId = tz.toZoneId();
      ZonedDateTime zonedDateTime = instant.atZone(zoneId);
      LocalDateTime ldt = zonedDateTime.toLocalDateTime();
      if (ldt.isAfter(this.julianEndTs()) && ldt.isBefore(this.gregorianStartTs())) {
         ldt = LocalDateTime.of(this.gregorianStartDate(), ldt.toLocalTime());
      }

      Calendar cal = (new Calendar.Builder()).setCalendarType("gregory").setDate(ldt.getYear(), ldt.getMonthValue() - 1, ldt.getDayOfMonth()).setTimeOfDay(ldt.getHour(), ldt.getMinute(), ldt.getSecond()).setTimeZone(tz).build();
      ZoneOffsetTransition trans = zoneId.getRules().getTransition(ldt);
      if (trans != null && trans.isOverlap()) {
         Calendar cloned;
         byte var13;
         label25: {
            label24: {
               cloned = (Calendar)cal.clone();
               ZoneOffset var10000 = trans.getOffsetBefore();
               ZoneOffset var12 = zonedDateTime.getOffset();
               if (var10000 == null) {
                  if (var12 == null) {
                     break label24;
                  }
               } else if (var10000.equals(var12)) {
                  break label24;
               }

               var13 = 1;
               break label25;
            }

            var13 = -1;
         }

         int shift = var13;
         cloned.add(5, shift);
         cal.set(15, cloned.get(15));
         cal.set(16, cloned.get(16));
      }

      return SparkDateTimeUtils$.MODULE$.millisToMicros(cal.getTimeInMillis()) + (long)ldt.get(ChronoField.MICRO_OF_SECOND);
   }

   public long rebaseGregorianToJulianMicros(final String timeZoneId, final long micros) {
      if (micros >= this.lastSwitchGregorianTs()) {
         return micros;
      } else {
         RebaseDateTime.RebaseInfo rebaseRecord = (RebaseDateTime.RebaseInfo)this.gregJulianRebaseMap().get(timeZoneId).orNull(scala..less.colon.less..MODULE$.refl());
         return rebaseRecord != null && micros >= rebaseRecord.switches()[0] ? this.rebaseMicros(rebaseRecord, micros) : this.rebaseGregorianToJulianMicros(TimeZone.getTimeZone(timeZoneId), micros);
      }
   }

   public long rebaseGregorianToJulianMicros(final long micros) {
      return this.rebaseGregorianToJulianMicros(TimeZone.getDefault().getID(), micros);
   }

   public long rebaseJulianToGregorianMicros(final TimeZone tz, final long micros) {
      Calendar cal = (new Calendar.Builder()).setCalendarType("gregory").setInstant(SparkDateTimeUtils$.MODULE$.microsToMillis(micros)).setTimeZone(tz).build();
      LocalDateTime localDateTime = LocalDateTime.of(cal.get(1), cal.get(2) + 1, 1, cal.get(11), cal.get(12), cal.get(13), (int)(Math.floorMod(micros, 1000000L) * 1000L)).with(ChronoField.ERA, (long)cal.get(0)).plusDays((long)(cal.get(5) - 1));
      ZoneId zoneId = tz.toZoneId();
      ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
      ZoneOffsetTransition trans = zoneId.getRules().getTransition(localDateTime);
      ZonedDateTime var10000;
      if (trans != null && trans.isOverlap()) {
         int dstOffset = cal.get(16);
         int zoneOffset = cal.get(15);
         cal.add(5, 1);
         var10000 = zoneOffset == cal.get(15) && dstOffset == cal.get(16) ? zonedDateTime.withLaterOffsetAtOverlap() : zonedDateTime.withEarlierOffsetAtOverlap();
      } else {
         var10000 = zonedDateTime;
      }

      ZonedDateTime adjustedZdt = var10000;
      return SparkDateTimeUtils$.MODULE$.instantToMicros(adjustedZdt.toInstant());
   }

   private HashMap julianGregRebaseMap() {
      return julianGregRebaseMap;
   }

   public final long lastSwitchJulianTs() {
      return lastSwitchJulianTs;
   }

   public long rebaseJulianToGregorianMicros(final String timeZoneId, final long micros) {
      if (micros >= this.lastSwitchJulianTs()) {
         return micros;
      } else {
         RebaseDateTime.RebaseInfo rebaseRecord = (RebaseDateTime.RebaseInfo)this.julianGregRebaseMap().get(timeZoneId).orNull(scala..less.colon.less..MODULE$.refl());
         return rebaseRecord != null && micros >= rebaseRecord.switches()[0] ? this.rebaseMicros(rebaseRecord, micros) : this.rebaseJulianToGregorianMicros(TimeZone.getTimeZone(timeZoneId), micros);
      }
   }

   public long rebaseJulianToGregorianMicros(final long micros) {
      return this.rebaseJulianToGregorianMicros(TimeZone.getDefault().getID(), micros);
   }

   // $FF: synthetic method
   public static final void $anonfun$loadRebaseRecords$1(final HashMap hashMap$1, final RebaseDateTime.JsonRebaseRecord jsonRecord) {
      RebaseDateTime.RebaseInfo rebaseInfo = new RebaseDateTime.RebaseInfo(jsonRecord.switches(), jsonRecord.diffs());

      for(int i = 0; i < rebaseInfo.switches().length; ++i) {
         rebaseInfo.switches()[i] *= 1000000L;
         rebaseInfo.diffs()[i] *= 1000000L;
      }

      hashMap$1.update(jsonRecord.tz(), rebaseInfo);
   }

   // $FF: synthetic method
   public static final long $anonfun$getLastSwitchTs$1(final RebaseDateTime.RebaseInfo x$1) {
      return BoxesRunTime.unboxToLong(.MODULE$.last$extension(scala.Predef..MODULE$.longArrayOps(x$1.switches())));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLastSwitchTs$2(final RebaseDateTime.RebaseInfo x$2) {
      return BoxesRunTime.unboxToLong(.MODULE$.last$extension(scala.Predef..MODULE$.longArrayOps(x$2.diffs()))) == 0L;
   }

   private RebaseDateTime$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
