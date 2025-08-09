package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

class StringConverter extends AbstractConverter implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter {
   static final StringConverter INSTANCE = new StringConverter();

   protected StringConverter() {
   }

   public long getInstantMillis(Object var1, Chronology var2) {
      String var3 = (String)var1;
      DateTimeFormatter var4 = ISODateTimeFormat.dateTimeParser();
      return var4.withChronology(var2).parseMillis(var3);
   }

   public int[] getPartialValues(ReadablePartial var1, Object var2, Chronology var3, DateTimeFormatter var4) {
      if (var4.getZone() != null) {
         var3 = var3.withZone(var4.getZone());
      }

      long var5 = var4.withChronology(var3).parseMillis((String)var2);
      return var3.get(var1, var5);
   }

   public long getDurationMillis(Object var1) {
      String var2 = (String)var1;
      int var4 = var2.length();
      if (var4 >= 4 && (var2.charAt(0) == 'P' || var2.charAt(0) == 'p') && (var2.charAt(1) == 'T' || var2.charAt(1) == 't') && (var2.charAt(var4 - 1) == 'S' || var2.charAt(var4 - 1) == 's')) {
         String var3 = var2.substring(2, var4 - 1);
         int var5 = -1;
         boolean var6 = false;

         for(int var7 = 0; var7 < var3.length(); ++var7) {
            if (var3.charAt(var7) < '0' || var3.charAt(var7) > '9') {
               if (var7 == 0 && var3.charAt(0) == '-') {
                  var6 = true;
               } else {
                  if (var7 <= (var6 ? 1 : 0) || var3.charAt(var7) != '.' || var5 != -1) {
                     throw new IllegalArgumentException("Invalid format: \"" + var2 + '"');
                  }

                  var5 = var7;
               }
            }
         }

         long var13 = 0L;
         long var9 = 0L;
         int var11 = var6 ? 1 : 0;
         if (var5 > 0) {
            var9 = Long.parseLong(var3.substring(var11, var5));
            var3 = var3.substring(var5 + 1);
            if (var3.length() != 3) {
               var3 = (var3 + "000").substring(0, 3);
            }

            var13 = (long)Integer.parseInt(var3);
         } else if (var6) {
            var9 = Long.parseLong(var3.substring(var11, var3.length()));
         } else {
            var9 = Long.parseLong(var3);
         }

         if (var6) {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(-var9, 1000), -var13);
         } else {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(var9, 1000), var13);
         }
      } else {
         throw new IllegalArgumentException("Invalid format: \"" + var2 + '"');
      }
   }

   public void setInto(ReadWritablePeriod var1, Object var2, Chronology var3) {
      String var4 = (String)var2;
      PeriodFormatter var5 = ISOPeriodFormat.standard();
      var1.clear();
      int var6 = var5.parseInto(var1, var4, 0);
      if (var6 < var4.length()) {
         if (var6 < 0) {
            var5.withParseType(var1.getPeriodType()).parseMutablePeriod(var4);
         }

         throw new IllegalArgumentException("Invalid format: \"" + var4 + '"');
      }
   }

   public void setInto(ReadWritableInterval var1, Object var2, Chronology var3) {
      String var4 = (String)var2;
      int var5 = var4.indexOf(47);
      if (var5 < 0) {
         throw new IllegalArgumentException("Format requires a '/' separator: " + var4);
      } else {
         String var6 = var4.substring(0, var5);
         if (var6.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + var4);
         } else {
            String var7 = var4.substring(var5 + 1);
            if (var7.length() <= 0) {
               throw new IllegalArgumentException("Format invalid: " + var4);
            } else {
               DateTimeFormatter var8 = ISODateTimeFormat.dateTimeParser();
               var8 = var8.withChronology(var3);
               PeriodFormatter var9 = ISOPeriodFormat.standard();
               long var10 = 0L;
               long var12 = 0L;
               Period var14 = null;
               Chronology var15 = null;
               char var16 = var6.charAt(0);
               if (var16 != 'P' && var16 != 'p') {
                  DateTime var17 = var8.parseDateTime(var6);
                  var10 = var17.getMillis();
                  var15 = var17.getChronology();
               } else {
                  var14 = var9.withParseType(this.getPeriodType(var6)).parsePeriod(var6);
               }

               var16 = var7.charAt(0);
               if (var16 != 'P' && var16 != 'p') {
                  DateTime var24 = var8.parseDateTime(var7);
                  var12 = var24.getMillis();
                  var15 = var15 != null ? var15 : var24.getChronology();
                  var3 = var3 != null ? var3 : var15;
                  if (var14 != null) {
                     var10 = var3.add(var14, var12, -1);
                  }
               } else {
                  if (var14 != null) {
                     throw new IllegalArgumentException("Interval composed of two durations: " + var4);
                  }

                  var14 = var9.withParseType(this.getPeriodType(var7)).parsePeriod(var7);
                  var3 = var3 != null ? var3 : var15;
                  var12 = var3.add(var14, var10, 1);
               }

               var1.setInterval(var10, var12);
               var1.setChronology(var3);
            }
         }
      }
   }

   public Class getSupportedType() {
      return String.class;
   }
}
