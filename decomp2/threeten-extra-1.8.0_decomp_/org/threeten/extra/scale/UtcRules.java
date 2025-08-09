package org.threeten.extra.scale;

import java.time.DateTimeException;
import java.time.Instant;

public abstract class UtcRules {
   static final int OFFSET_MJD_EPOCH = 40587;
   static final int OFFSET_MJD_TAI = 36204;
   static final long SECS_PER_DAY = 86400L;
   static final long NANOS_PER_SECOND = 1000000000L;

   public static UtcRules system() {
      return SystemUtcRules.INSTANCE;
   }

   public static void registerLeapSecond(long mjDay, int leapAdjustment) {
      SystemUtcRules.INSTANCE.register(mjDay, leapAdjustment);
   }

   protected UtcRules() {
   }

   public abstract String getName();

   public abstract int getLeapSecondAdjustment(long var1);

   public abstract int getTaiOffset(long var1);

   public abstract long[] getLeapSecondDates();

   public void validateModifiedJulianDay(long mjDay, long nanoOfDay) {
      long leapSecs = (long)this.getLeapSecondAdjustment(mjDay);
      long maxNanos = (86400L + leapSecs) * 1000000000L;
      if (nanoOfDay < 0L || nanoOfDay >= maxNanos) {
         throw new DateTimeException("Nanosecond-of-day must be between 0 and " + maxNanos + " on date " + mjDay);
      }
   }

   public TaiInstant convertToTai(UtcInstant utcInstant) {
      long mjd = utcInstant.getModifiedJulianDay();
      long nod = utcInstant.getNanoOfDay();
      long taiUtcDaySeconds = Math.multiplyExact(Math.subtractExact(mjd, 36204L), 86400L);
      long taiSecs = Math.addExact(taiUtcDaySeconds, nod / 1000000000L + (long)this.getTaiOffset(mjd));
      int nos = (int)(nod % 1000000000L);
      return TaiInstant.ofTaiSeconds(taiSecs, (long)nos);
   }

   public abstract UtcInstant convertToUtc(TaiInstant var1);

   public Instant convertToInstant(UtcInstant utcInstant) {
      long mjd = utcInstant.getModifiedJulianDay();
      long utcNanos = utcInstant.getNanoOfDay();
      long epochDay = Math.subtractExact(mjd, 40587L);
      long epochSec = Math.multiplyExact(epochDay, 86400L);
      int leapAdj = this.getLeapSecondAdjustment(mjd);
      long startSlsNanos = (86400L + (long)leapAdj - 1000L) * 1000000000L;
      long slsNanos = utcNanos;
      if (leapAdj != 0 && utcNanos >= startSlsNanos) {
         slsNanos = utcNanos - (long)leapAdj * (utcNanos - startSlsNanos) / 1000L;
      }

      return Instant.ofEpochSecond(epochSec + slsNanos / 1000000000L, slsNanos % 1000000000L);
   }

   public UtcInstant convertToUtc(Instant instant) {
      long epochDay = Math.floorDiv(instant.getEpochSecond(), 86400L);
      long mjd = epochDay + 40587L;
      long slsNanos = Math.floorMod(instant.getEpochSecond(), 86400L) * 1000000000L + (long)instant.getNano();
      int leapAdj = this.getLeapSecondAdjustment(mjd);
      long startSlsNanos = (86400L + (long)leapAdj - 1000L) * 1000000000L;
      long utcNanos = slsNanos;
      if (leapAdj != 0 && slsNanos >= startSlsNanos) {
         utcNanos = startSlsNanos + (slsNanos - startSlsNanos) * 1000L / (long)(1000 - leapAdj);
      }

      return UtcInstant.ofModifiedJulianDay(mjd, utcNanos);
   }

   public Instant convertToInstant(TaiInstant taiInstant) {
      return this.convertToInstant(this.convertToUtc(taiInstant));
   }

   public TaiInstant convertToTai(Instant instant) {
      return this.convertToTai(this.convertToUtc(instant));
   }

   public String toString() {
      return "UtcRules[" + this.getName() + ']';
   }
}
