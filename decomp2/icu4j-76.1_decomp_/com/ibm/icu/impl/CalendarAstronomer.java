package com.ibm.icu.impl;

import java.util.Date;

public class CalendarAstronomer {
   public static final double SIDEREAL_DAY = 23.93446960027;
   public static final double SOLAR_DAY = 24.065709816;
   public static final double SYNODIC_MONTH = 29.530588853;
   public static final double SIDEREAL_MONTH = 27.32166;
   public static final double TROPICAL_YEAR = 365.242191;
   public static final double SIDEREAL_YEAR = 365.25636;
   public static final int SECOND_MS = 1000;
   public static final int MINUTE_MS = 60000;
   public static final int HOUR_MS = 3600000;
   public static final long DAY_MS = 86400000L;
   public static final long JULIAN_EPOCH_MS = -210866760000000L;
   static final long EPOCH_2000_MS = 946598400000L;
   private static final double PI = Math.PI;
   private static final double PI2 = (Math.PI * 2D);
   private static final double RAD_HOUR = 3.819718634205488;
   private static final double DEG_RAD = (Math.PI / 180D);
   private static final double RAD_DEG = (180D / Math.PI);
   static final double JD_EPOCH = (double)2447891.5F;
   static final double SUN_ETA_G = 4.87650757829735;
   static final double SUN_OMEGA_G = 4.935239984568769;
   static final double SUN_E = 0.016713;
   public static final SolarLongitude WINTER_SOLSTICE = new SolarLongitude((Math.PI * 1.5D));
   static final double moonL0 = 5.556284436750021;
   static final double moonP0 = 0.6342598060246725;
   static final double moonN0 = 5.559050068029439;
   static final double moonI = 0.08980357792017056;
   static final double moonE = 0.0549;
   static final double moonA = (double)384401.0F;
   static final double moonT0 = 0.009042550854582622;
   static final double moonPi = 0.016592845198710092;
   public static final MoonAge NEW_MOON = new MoonAge((double)0.0F);
   private long time;
   private static final double INVALID = Double.MIN_VALUE;
   private transient double julianDay;
   private transient double sunLongitude;
   private transient double meanAnomalySun;
   private transient double moonEclipLong;
   private transient Equatorial moonPosition;

   public CalendarAstronomer() {
      this(System.currentTimeMillis());
   }

   public CalendarAstronomer(long aTime) {
      this.julianDay = Double.MIN_VALUE;
      this.sunLongitude = Double.MIN_VALUE;
      this.meanAnomalySun = Double.MIN_VALUE;
      this.moonEclipLong = Double.MIN_VALUE;
      this.moonPosition = null;
      this.time = aTime;
   }

   public void setTime(long aTime) {
      this.time = aTime;
      this.clearCache();
   }

   public void setJulianDay(double jdn) {
      this.time = (long)(jdn * (double)8.64E7F) + -210866760000000L;
      this.clearCache();
      this.julianDay = jdn;
   }

   public long getTime() {
      return this.time;
   }

   public Date getDate() {
      return new Date(this.time);
   }

   public double getJulianDay() {
      if (this.julianDay == Double.MIN_VALUE) {
         this.julianDay = (double)(this.time - -210866760000000L) / (double)8.64E7F;
      }

      return this.julianDay;
   }

   public final Equatorial eclipticToEquatorial(double eclipLong, double eclipLat) {
      double obliq = this.eclipticObliquity();
      double sinE = Math.sin(obliq);
      double cosE = Math.cos(obliq);
      double sinL = Math.sin(eclipLong);
      double cosL = Math.cos(eclipLong);
      double sinB = Math.sin(eclipLat);
      double cosB = Math.cos(eclipLat);
      double tanB = Math.tan(eclipLat);
      return new Equatorial(Math.atan2(sinL * cosE - tanB * sinE, cosL), Math.asin(sinB * cosE + cosB * sinE * sinL));
   }

   public double getSunLongitude() {
      if (this.sunLongitude == Double.MIN_VALUE) {
         double[] result = this.getSunLongitude(this.getJulianDay());
         this.sunLongitude = result[0];
         this.meanAnomalySun = result[1];
      }

      return this.sunLongitude;
   }

   double[] getSunLongitude(double julian) {
      double day = julian - (double)2447891.5F;
      double epochAngle = norm2PI(0.017202791632524146 * day);
      double meanAnomaly = norm2PI(epochAngle + 4.87650757829735 - 4.935239984568769);
      return new double[]{norm2PI(this.trueAnomaly(meanAnomaly, 0.016713) + 4.935239984568769), meanAnomaly};
   }

   public long getSunTime(double desired, boolean next) {
      return this.timeOfAngle(new AngleFunc() {
         public double eval() {
            return CalendarAstronomer.this.getSunLongitude();
         }
      }, desired, 365.242191, 60000L, next);
   }

   public long getSunTime(SolarLongitude desired, boolean next) {
      return this.getSunTime(desired.value, next);
   }

   public Equatorial getMoonPosition() {
      if (this.moonPosition == null) {
         double sunLong = this.getSunLongitude();
         double day = this.getJulianDay() - (double)2447891.5F;
         double meanLongitude = norm2PI(0.22997150421858628 * day + 5.556284436750021);
         double meanAnomalyMoon = norm2PI(meanLongitude - 0.001944368345221015 * day - 0.6342598060246725);
         double evection = 0.022233749341155764 * Math.sin((double)2.0F * (meanLongitude - sunLong) - meanAnomalyMoon);
         double annual = 0.003242821750205464 * Math.sin(this.meanAnomalySun);
         double a3 = 0.00645771823237902 * Math.sin(this.meanAnomalySun);
         meanAnomalyMoon += evection - annual - a3;
         double center = 0.10975677534091541 * Math.sin(meanAnomalyMoon);
         double a4 = 0.0037350045992678655 * Math.sin((double)2.0F * meanAnomalyMoon);
         double moonLongitude = meanLongitude + evection + center - annual + a4;
         double variation = 0.011489502465878671 * Math.sin((double)2.0F * (moonLongitude - sunLong));
         moonLongitude += variation;
         double nodeLongitude = norm2PI(5.559050068029439 - 9.242199067718253E-4 * day);
         nodeLongitude -= 0.0027925268031909274 * Math.sin(this.meanAnomalySun);
         double y = Math.sin(moonLongitude - nodeLongitude);
         double x = Math.cos(moonLongitude - nodeLongitude);
         this.moonEclipLong = Math.atan2(y * Math.cos(0.08980357792017056), x) + nodeLongitude;
         double moonEclipLat = Math.asin(y * Math.sin(0.08980357792017056));
         this.moonPosition = this.eclipticToEquatorial(this.moonEclipLong, moonEclipLat);
      }

      return this.moonPosition;
   }

   public double getMoonAge() {
      this.getMoonPosition();
      return norm2PI(this.moonEclipLong - this.sunLongitude);
   }

   public long getMoonTime(double desired, boolean next) {
      return this.timeOfAngle(new AngleFunc() {
         public double eval() {
            return CalendarAstronomer.this.getMoonAge();
         }
      }, desired, 29.530588853, 60000L, next);
   }

   public long getMoonTime(MoonAge desired, boolean next) {
      return this.getMoonTime(desired.value, next);
   }

   private long timeOfAngle(AngleFunc func, double desired, double periodDays, long epsilon, boolean next) {
      double lastAngle = func.eval();
      double deltaAngle = norm2PI(desired - lastAngle);
      double deltaT = (deltaAngle + (next ? (double)0.0F : (-Math.PI * 2D))) * periodDays * (double)8.64E7F / (Math.PI * 2D);
      double lastDeltaT = deltaT;
      long startTime = this.time;
      this.setTime(this.time + (long)deltaT);

      do {
         double angle = func.eval();
         double factor = Math.abs(deltaT / normPI(angle - lastAngle));
         deltaT = normPI(desired - angle) * factor;
         if (Math.abs(deltaT) > Math.abs(lastDeltaT)) {
            long delta = (long)(periodDays * (double)8.64E7F / (double)8.0F);
            this.setTime(startTime + (next ? delta : -delta));
            return this.timeOfAngle(func, desired, periodDays, epsilon, next);
         }

         lastDeltaT = deltaT;
         lastAngle = angle;
         this.setTime(this.time + (long)deltaT);
      } while(Math.abs(deltaT) > (double)epsilon);

      return this.time;
   }

   private static final double normalize(double value, double range) {
      return value - range * Math.floor(value / range);
   }

   private static final double norm2PI(double angle) {
      return normalize(angle, (Math.PI * 2D));
   }

   private static final double normPI(double angle) {
      return normalize(angle + Math.PI, (Math.PI * 2D)) - Math.PI;
   }

   private double trueAnomaly(double meanAnomaly, double eccentricity) {
      double E = meanAnomaly;

      double delta;
      do {
         delta = E - eccentricity * Math.sin(E) - meanAnomaly;
         E -= delta / ((double)1.0F - eccentricity * Math.cos(E));
      } while(Math.abs(delta) > 1.0E-5);

      return (double)2.0F * Math.atan(Math.tan(E / (double)2.0F) * Math.sqrt(((double)1.0F + eccentricity) / ((double)1.0F - eccentricity)));
   }

   private double eclipticObliquity() {
      double epoch = (double)2451545.0F;
      double T = (this.getJulianDay() - (double)2451545.0F) / (double)36525.0F;
      double eclipObliquity = 23.439292 - 0.013004166666666666 * T - 1.6666666666666665E-7 * T * T + 5.027777777777778E-7 * T * T * T;
      return eclipObliquity * (Math.PI / 180D);
   }

   private void clearCache() {
      this.julianDay = Double.MIN_VALUE;
      this.sunLongitude = Double.MIN_VALUE;
      this.meanAnomalySun = Double.MIN_VALUE;
      this.moonEclipLong = Double.MIN_VALUE;
      this.moonPosition = null;
   }

   private static String radToHms(double angle) {
      int hrs = (int)(angle * 3.819718634205488);
      int min = (int)((angle * 3.819718634205488 - (double)hrs) * (double)60.0F);
      int sec = (int)((angle * 3.819718634205488 - (double)hrs - (double)min / (double)60.0F) * (double)3600.0F);
      return Integer.toString(hrs) + "h" + min + "m" + sec + "s";
   }

   private static String radToDms(double angle) {
      int deg = (int)(angle * (180D / Math.PI));
      int min = (int)((angle * (180D / Math.PI) - (double)deg) * (double)60.0F);
      int sec = (int)((angle * (180D / Math.PI) - (double)deg - (double)min / (double)60.0F) * (double)3600.0F);
      return Integer.toString(deg) + "°" + min + "'" + sec + "\"";
   }

   private static class SolarLongitude {
      double value;

      SolarLongitude(double val) {
         this.value = val;
      }
   }

   private static class MoonAge {
      double value;

      MoonAge(double val) {
         this.value = val;
      }
   }

   public static final class Ecliptic {
      public final double latitude;
      public final double longitude;

      public Ecliptic(double lat, double lon) {
         this.latitude = lat;
         this.longitude = lon;
      }

      public String toString() {
         return Double.toString(this.longitude * (180D / Math.PI)) + "," + this.latitude * (180D / Math.PI);
      }
   }

   public static final class Equatorial {
      public final double ascension;
      public final double declination;

      public Equatorial(double asc, double dec) {
         this.ascension = asc;
         this.declination = dec;
      }

      public String toString() {
         return Double.toString(this.ascension * (180D / Math.PI)) + "," + this.declination * (180D / Math.PI);
      }

      public String toHmsString() {
         return CalendarAstronomer.radToHms(this.ascension) + "," + CalendarAstronomer.radToDms(this.declination);
      }
   }

   private interface AngleFunc {
      double eval();
   }
}
