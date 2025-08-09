package org.joda.time;

import java.lang.reflect.Method;
import java.text.DateFormatSymbols;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.time.chrono.ISOChronology;

public class DateTimeUtils {
   public static final MillisProvider SYSTEM_MILLIS_PROVIDER = new SystemMillisProvider();
   private static volatile MillisProvider cMillisProvider;
   private static final AtomicReference cZoneNames;

   protected DateTimeUtils() {
   }

   public static final long currentTimeMillis() {
      return cMillisProvider.getMillis();
   }

   public static final void setCurrentMillisSystem() throws SecurityException {
      checkPermission();
      cMillisProvider = SYSTEM_MILLIS_PROVIDER;
   }

   public static final void setCurrentMillisFixed(long var0) throws SecurityException {
      checkPermission();
      cMillisProvider = new FixedMillisProvider(var0);
   }

   public static final void setCurrentMillisOffset(long var0) throws SecurityException {
      checkPermission();
      if (var0 == 0L) {
         cMillisProvider = SYSTEM_MILLIS_PROVIDER;
      } else {
         cMillisProvider = new OffsetMillisProvider(var0);
      }

   }

   public static final void setCurrentMillisProvider(MillisProvider var0) throws SecurityException {
      if (var0 == null) {
         throw new IllegalArgumentException("The MillisProvider must not be null");
      } else {
         checkPermission();
         cMillisProvider = var0;
      }
   }

   private static void checkPermission() throws SecurityException {
      SecurityManager var0 = System.getSecurityManager();
      if (var0 != null) {
         var0.checkPermission(new JodaTimePermission("CurrentTime.setProvider"));
      }

   }

   public static final long getInstantMillis(ReadableInstant var0) {
      return var0 == null ? currentTimeMillis() : var0.getMillis();
   }

   public static final Chronology getInstantChronology(ReadableInstant var0) {
      if (var0 == null) {
         return ISOChronology.getInstance();
      } else {
         Chronology var1 = var0.getChronology();
         return (Chronology)(var1 == null ? ISOChronology.getInstance() : var1);
      }
   }

   public static final Chronology getIntervalChronology(ReadableInstant var0, ReadableInstant var1) {
      Object var2 = null;
      if (var0 != null) {
         var2 = var0.getChronology();
      } else if (var1 != null) {
         var2 = var1.getChronology();
      }

      if (var2 == null) {
         var2 = ISOChronology.getInstance();
      }

      return (Chronology)var2;
   }

   public static final Chronology getIntervalChronology(ReadableInterval var0) {
      if (var0 == null) {
         return ISOChronology.getInstance();
      } else {
         Chronology var1 = var0.getChronology();
         return (Chronology)(var1 == null ? ISOChronology.getInstance() : var1);
      }
   }

   public static final ReadableInterval getReadableInterval(ReadableInterval var0) {
      if (var0 == null) {
         long var1 = currentTimeMillis();
         var0 = new Interval(var1, var1);
      }

      return (ReadableInterval)var0;
   }

   public static final Chronology getChronology(Chronology var0) {
      return (Chronology)(var0 == null ? ISOChronology.getInstance() : var0);
   }

   public static final DateTimeZone getZone(DateTimeZone var0) {
      return var0 == null ? DateTimeZone.getDefault() : var0;
   }

   public static final PeriodType getPeriodType(PeriodType var0) {
      return var0 == null ? PeriodType.standard() : var0;
   }

   public static final long getDurationMillis(ReadableDuration var0) {
      return var0 == null ? 0L : var0.getMillis();
   }

   public static final boolean isContiguous(ReadablePartial var0) {
      if (var0 == null) {
         throw new IllegalArgumentException("Partial must not be null");
      } else {
         DurationFieldType var1 = null;

         for(int var2 = 0; var2 < var0.size(); ++var2) {
            DateTimeField var3 = var0.getField(var2);
            if (var2 > 0 && (var3.getRangeDurationField() == null || var3.getRangeDurationField().getType() != var1)) {
               return false;
            }

            var1 = var3.getDurationField().getType();
         }

         return true;
      }
   }

   public static final DateFormatSymbols getDateFormatSymbols(Locale var0) {
      try {
         Method var1 = DateFormatSymbols.class.getMethod("getInstance", Locale.class);
         return (DateFormatSymbols)var1.invoke((Object)null, var0);
      } catch (Exception var2) {
         return new DateFormatSymbols(var0);
      }
   }

   public static final Map getDefaultTimeZoneNames() {
      Map var0 = (Map)cZoneNames.get();
      if (var0 == null) {
         var0 = buildDefaultTimeZoneNames();
         if (!cZoneNames.compareAndSet((Object)null, var0)) {
            var0 = (Map)cZoneNames.get();
         }
      }

      return var0;
   }

   public static final void setDefaultTimeZoneNames(Map var0) {
      cZoneNames.set(Collections.unmodifiableMap(new HashMap(var0)));
   }

   private static Map buildDefaultTimeZoneNames() {
      LinkedHashMap var0 = new LinkedHashMap();
      var0.put("UT", DateTimeZone.UTC);
      var0.put("UTC", DateTimeZone.UTC);
      var0.put("GMT", DateTimeZone.UTC);
      put(var0, "EST", "America/New_York");
      put(var0, "EDT", "America/New_York");
      put(var0, "CST", "America/Chicago");
      put(var0, "CDT", "America/Chicago");
      put(var0, "MST", "America/Denver");
      put(var0, "MDT", "America/Denver");
      put(var0, "PST", "America/Los_Angeles");
      put(var0, "PDT", "America/Los_Angeles");
      return Collections.unmodifiableMap(var0);
   }

   private static void put(Map var0, String var1, String var2) {
      try {
         var0.put(var1, DateTimeZone.forID(var2));
      } catch (RuntimeException var4) {
      }

   }

   public static final double toJulianDay(long var0) {
      double var2 = (double)var0 / (double)8.64E7F;
      return var2 + (double)2440587.5F;
   }

   public static final long toJulianDayNumber(long var0) {
      return (long)Math.floor(toJulianDay(var0) + (double)0.5F);
   }

   public static final long fromJulianDay(double var0) {
      double var2 = var0 - (double)2440587.5F;
      return (long)(var2 * (double)8.64E7F);
   }

   static {
      cMillisProvider = SYSTEM_MILLIS_PROVIDER;
      cZoneNames = new AtomicReference();
   }

   static class SystemMillisProvider implements MillisProvider {
      public long getMillis() {
         return System.currentTimeMillis();
      }
   }

   static class FixedMillisProvider implements MillisProvider {
      private final long iMillis;

      FixedMillisProvider(long var1) {
         this.iMillis = var1;
      }

      public long getMillis() {
         return this.iMillis;
      }
   }

   static class OffsetMillisProvider implements MillisProvider {
      private final long iMillis;

      OffsetMillisProvider(long var1) {
         this.iMillis = var1;
      }

      public long getMillis() {
         return System.currentTimeMillis() + this.iMillis;
      }
   }

   public interface MillisProvider {
      long getMillis();
   }
}
