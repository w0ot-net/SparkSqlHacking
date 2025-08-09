package org.joda.time.format;

import java.util.Collection;
import java.util.HashSet;
import org.joda.time.DateTimeFieldType;

public class ISODateTimeFormat {
   protected ISODateTimeFormat() {
   }

   public static DateTimeFormatter forFields(Collection var0, boolean var1, boolean var2) {
      if (var0 != null && var0.size() != 0) {
         HashSet var3 = new HashSet(var0);
         int var4 = var3.size();
         boolean var5 = false;
         DateTimeFormatterBuilder var6 = new DateTimeFormatterBuilder();
         if (var3.contains(DateTimeFieldType.monthOfYear())) {
            var5 = dateByMonth(var6, var3, var1, var2);
         } else if (var3.contains(DateTimeFieldType.dayOfYear())) {
            var5 = dateByOrdinal(var6, var3, var1, var2);
         } else if (var3.contains(DateTimeFieldType.weekOfWeekyear())) {
            var5 = dateByWeek(var6, var3, var1, var2);
         } else if (var3.contains(DateTimeFieldType.dayOfMonth())) {
            var5 = dateByMonth(var6, var3, var1, var2);
         } else if (var3.contains(DateTimeFieldType.dayOfWeek())) {
            var5 = dateByWeek(var6, var3, var1, var2);
         } else if (var3.remove(DateTimeFieldType.year())) {
            var6.append(ISODateTimeFormat.Constants.ye);
            var5 = true;
         } else if (var3.remove(DateTimeFieldType.weekyear())) {
            var6.append(ISODateTimeFormat.Constants.we);
            var5 = true;
         }

         boolean var7 = var3.size() < var4;
         time(var6, var3, var1, var2, var5, var7);
         if (!var6.canBuildFormatter()) {
            throw new IllegalArgumentException("No valid format for fields: " + var0);
         } else {
            try {
               var0.retainAll(var3);
            } catch (UnsupportedOperationException var9) {
            }

            return var6.toFormatter();
         }
      } else {
         throw new IllegalArgumentException("The fields must not be null or empty");
      }
   }

   private static boolean dateByMonth(DateTimeFormatterBuilder var0, Collection var1, boolean var2, boolean var3) {
      boolean var4 = false;
      if (var1.remove(DateTimeFieldType.year())) {
         var0.append(ISODateTimeFormat.Constants.ye);
         if (var1.remove(DateTimeFieldType.monthOfYear())) {
            if (var1.remove(DateTimeFieldType.dayOfMonth())) {
               appendSeparator(var0, var2);
               var0.appendMonthOfYear(2);
               appendSeparator(var0, var2);
               var0.appendDayOfMonth(2);
            } else {
               var0.appendLiteral('-');
               var0.appendMonthOfYear(2);
               var4 = true;
            }
         } else if (var1.remove(DateTimeFieldType.dayOfMonth())) {
            checkNotStrictISO(var1, var3);
            var0.appendLiteral('-');
            var0.appendLiteral('-');
            var0.appendDayOfMonth(2);
         } else {
            var4 = true;
         }
      } else if (var1.remove(DateTimeFieldType.monthOfYear())) {
         var0.appendLiteral('-');
         var0.appendLiteral('-');
         var0.appendMonthOfYear(2);
         if (var1.remove(DateTimeFieldType.dayOfMonth())) {
            appendSeparator(var0, var2);
            var0.appendDayOfMonth(2);
         } else {
            var4 = true;
         }
      } else if (var1.remove(DateTimeFieldType.dayOfMonth())) {
         var0.appendLiteral('-');
         var0.appendLiteral('-');
         var0.appendLiteral('-');
         var0.appendDayOfMonth(2);
      }

      return var4;
   }

   private static boolean dateByOrdinal(DateTimeFormatterBuilder var0, Collection var1, boolean var2, boolean var3) {
      boolean var4 = false;
      if (var1.remove(DateTimeFieldType.year())) {
         var0.append(ISODateTimeFormat.Constants.ye);
         if (var1.remove(DateTimeFieldType.dayOfYear())) {
            appendSeparator(var0, var2);
            var0.appendDayOfYear(3);
         } else {
            var4 = true;
         }
      } else if (var1.remove(DateTimeFieldType.dayOfYear())) {
         var0.appendLiteral('-');
         var0.appendDayOfYear(3);
      }

      return var4;
   }

   private static boolean dateByWeek(DateTimeFormatterBuilder var0, Collection var1, boolean var2, boolean var3) {
      boolean var4 = false;
      if (var1.remove(DateTimeFieldType.weekyear())) {
         var0.append(ISODateTimeFormat.Constants.we);
         if (var1.remove(DateTimeFieldType.weekOfWeekyear())) {
            appendSeparator(var0, var2);
            var0.appendLiteral('W');
            var0.appendWeekOfWeekyear(2);
            if (var1.remove(DateTimeFieldType.dayOfWeek())) {
               appendSeparator(var0, var2);
               var0.appendDayOfWeek(1);
            } else {
               var4 = true;
            }
         } else if (var1.remove(DateTimeFieldType.dayOfWeek())) {
            checkNotStrictISO(var1, var3);
            appendSeparator(var0, var2);
            var0.appendLiteral('W');
            var0.appendLiteral('-');
            var0.appendDayOfWeek(1);
         } else {
            var4 = true;
         }
      } else if (var1.remove(DateTimeFieldType.weekOfWeekyear())) {
         var0.appendLiteral('-');
         var0.appendLiteral('W');
         var0.appendWeekOfWeekyear(2);
         if (var1.remove(DateTimeFieldType.dayOfWeek())) {
            appendSeparator(var0, var2);
            var0.appendDayOfWeek(1);
         } else {
            var4 = true;
         }
      } else if (var1.remove(DateTimeFieldType.dayOfWeek())) {
         var0.appendLiteral('-');
         var0.appendLiteral('W');
         var0.appendLiteral('-');
         var0.appendDayOfWeek(1);
      }

      return var4;
   }

   private static void time(DateTimeFormatterBuilder var0, Collection var1, boolean var2, boolean var3, boolean var4, boolean var5) {
      boolean var6 = var1.remove(DateTimeFieldType.hourOfDay());
      boolean var7 = var1.remove(DateTimeFieldType.minuteOfHour());
      boolean var8 = var1.remove(DateTimeFieldType.secondOfMinute());
      boolean var9 = var1.remove(DateTimeFieldType.millisOfSecond());
      if (var6 || var7 || var8 || var9) {
         if (var6 || var7 || var8 || var9) {
            if (var3 && var4) {
               throw new IllegalArgumentException("No valid ISO8601 format for fields because Date was reduced precision: " + var1);
            }

            if (var5) {
               var0.appendLiteral('T');
            }
         }

         if ((!var6 || !var7 || !var8) && (!var6 || var8 || var9)) {
            if (var3 && var5) {
               throw new IllegalArgumentException("No valid ISO8601 format for fields because Time was truncated: " + var1);
            }

            if ((var6 || (!var7 || !var8) && (!var7 || var9) && !var8) && var3) {
               throw new IllegalArgumentException("No valid ISO8601 format for fields: " + var1);
            }
         }

         if (var6) {
            var0.appendHourOfDay(2);
         } else if (var7 || var8 || var9) {
            var0.appendLiteral('-');
         }

         if (var2 && var6 && var7) {
            var0.appendLiteral(':');
         }

         if (var7) {
            var0.appendMinuteOfHour(2);
         } else if (var8 || var9) {
            var0.appendLiteral('-');
         }

         if (var2 && var7 && var8) {
            var0.appendLiteral(':');
         }

         if (var8) {
            var0.appendSecondOfMinute(2);
         } else if (var9) {
            var0.appendLiteral('-');
         }

         if (var9) {
            var0.appendLiteral('.');
            var0.appendMillisOfSecond(3);
         }

      }
   }

   private static void checkNotStrictISO(Collection var0, boolean var1) {
      if (var1) {
         throw new IllegalArgumentException("No valid ISO8601 format for fields: " + var0);
      }
   }

   private static void appendSeparator(DateTimeFormatterBuilder var0, boolean var1) {
      if (var1) {
         var0.appendLiteral('-');
      }

   }

   public static DateTimeFormatter dateParser() {
      return ISODateTimeFormat.Constants.dp;
   }

   public static DateTimeFormatter localDateParser() {
      return ISODateTimeFormat.Constants.ldp;
   }

   public static DateTimeFormatter dateElementParser() {
      return ISODateTimeFormat.Constants.dpe;
   }

   public static DateTimeFormatter timeParser() {
      return ISODateTimeFormat.Constants.tp;
   }

   public static DateTimeFormatter localTimeParser() {
      return ISODateTimeFormat.Constants.ltp;
   }

   public static DateTimeFormatter timeElementParser() {
      return ISODateTimeFormat.Constants.tpe;
   }

   public static DateTimeFormatter dateTimeParser() {
      return ISODateTimeFormat.Constants.dtp;
   }

   public static DateTimeFormatter dateOptionalTimeParser() {
      return ISODateTimeFormat.Constants.dotp;
   }

   public static DateTimeFormatter localDateOptionalTimeParser() {
      return ISODateTimeFormat.Constants.ldotp;
   }

   public static DateTimeFormatter date() {
      return yearMonthDay();
   }

   public static DateTimeFormatter time() {
      return ISODateTimeFormat.Constants.t;
   }

   public static DateTimeFormatter timeNoMillis() {
      return ISODateTimeFormat.Constants.tx;
   }

   public static DateTimeFormatter tTime() {
      return ISODateTimeFormat.Constants.tt;
   }

   public static DateTimeFormatter tTimeNoMillis() {
      return ISODateTimeFormat.Constants.ttx;
   }

   public static DateTimeFormatter dateTime() {
      return ISODateTimeFormat.Constants.dt;
   }

   public static DateTimeFormatter dateTimeNoMillis() {
      return ISODateTimeFormat.Constants.dtx;
   }

   public static DateTimeFormatter ordinalDate() {
      return ISODateTimeFormat.Constants.od;
   }

   public static DateTimeFormatter ordinalDateTime() {
      return ISODateTimeFormat.Constants.odt;
   }

   public static DateTimeFormatter ordinalDateTimeNoMillis() {
      return ISODateTimeFormat.Constants.odtx;
   }

   public static DateTimeFormatter weekDate() {
      return ISODateTimeFormat.Constants.wwd;
   }

   public static DateTimeFormatter weekDateTime() {
      return ISODateTimeFormat.Constants.wdt;
   }

   public static DateTimeFormatter weekDateTimeNoMillis() {
      return ISODateTimeFormat.Constants.wdtx;
   }

   public static DateTimeFormatter basicDate() {
      return ISODateTimeFormat.Constants.bd;
   }

   public static DateTimeFormatter basicTime() {
      return ISODateTimeFormat.Constants.bt;
   }

   public static DateTimeFormatter basicTimeNoMillis() {
      return ISODateTimeFormat.Constants.btx;
   }

   public static DateTimeFormatter basicTTime() {
      return ISODateTimeFormat.Constants.btt;
   }

   public static DateTimeFormatter basicTTimeNoMillis() {
      return ISODateTimeFormat.Constants.bttx;
   }

   public static DateTimeFormatter basicDateTime() {
      return ISODateTimeFormat.Constants.bdt;
   }

   public static DateTimeFormatter basicDateTimeNoMillis() {
      return ISODateTimeFormat.Constants.bdtx;
   }

   public static DateTimeFormatter basicOrdinalDate() {
      return ISODateTimeFormat.Constants.bod;
   }

   public static DateTimeFormatter basicOrdinalDateTime() {
      return ISODateTimeFormat.Constants.bodt;
   }

   public static DateTimeFormatter basicOrdinalDateTimeNoMillis() {
      return ISODateTimeFormat.Constants.bodtx;
   }

   public static DateTimeFormatter basicWeekDate() {
      return ISODateTimeFormat.Constants.bwd;
   }

   public static DateTimeFormatter basicWeekDateTime() {
      return ISODateTimeFormat.Constants.bwdt;
   }

   public static DateTimeFormatter basicWeekDateTimeNoMillis() {
      return ISODateTimeFormat.Constants.bwdtx;
   }

   public static DateTimeFormatter year() {
      return ISODateTimeFormat.Constants.ye;
   }

   public static DateTimeFormatter yearMonth() {
      return ISODateTimeFormat.Constants.ym;
   }

   public static DateTimeFormatter yearMonthDay() {
      return ISODateTimeFormat.Constants.ymd;
   }

   public static DateTimeFormatter weekyear() {
      return ISODateTimeFormat.Constants.we;
   }

   public static DateTimeFormatter weekyearWeek() {
      return ISODateTimeFormat.Constants.ww;
   }

   public static DateTimeFormatter weekyearWeekDay() {
      return ISODateTimeFormat.Constants.wwd;
   }

   public static DateTimeFormatter hour() {
      return ISODateTimeFormat.Constants.hde;
   }

   public static DateTimeFormatter hourMinute() {
      return ISODateTimeFormat.Constants.hm;
   }

   public static DateTimeFormatter hourMinuteSecond() {
      return ISODateTimeFormat.Constants.hms;
   }

   public static DateTimeFormatter hourMinuteSecondMillis() {
      return ISODateTimeFormat.Constants.hmsl;
   }

   public static DateTimeFormatter hourMinuteSecondFraction() {
      return ISODateTimeFormat.Constants.hmsf;
   }

   public static DateTimeFormatter dateHour() {
      return ISODateTimeFormat.Constants.dh;
   }

   public static DateTimeFormatter dateHourMinute() {
      return ISODateTimeFormat.Constants.dhm;
   }

   public static DateTimeFormatter dateHourMinuteSecond() {
      return ISODateTimeFormat.Constants.dhms;
   }

   public static DateTimeFormatter dateHourMinuteSecondMillis() {
      return ISODateTimeFormat.Constants.dhmsl;
   }

   public static DateTimeFormatter dateHourMinuteSecondFraction() {
      return ISODateTimeFormat.Constants.dhmsf;
   }

   static final class Constants {
      private static final DateTimeFormatter ye = yearElement();
      private static final DateTimeFormatter mye = monthElement();
      private static final DateTimeFormatter dme = dayOfMonthElement();
      private static final DateTimeFormatter we = weekyearElement();
      private static final DateTimeFormatter wwe = weekElement();
      private static final DateTimeFormatter dwe = dayOfWeekElement();
      private static final DateTimeFormatter dye = dayOfYearElement();
      private static final DateTimeFormatter hde = hourElement();
      private static final DateTimeFormatter mhe = minuteElement();
      private static final DateTimeFormatter sme = secondElement();
      private static final DateTimeFormatter fse = fractionElement();
      private static final DateTimeFormatter ze = offsetElement();
      private static final DateTimeFormatter lte = literalTElement();
      private static final DateTimeFormatter ym = yearMonth();
      private static final DateTimeFormatter ymd = yearMonthDay();
      private static final DateTimeFormatter ww = weekyearWeek();
      private static final DateTimeFormatter wwd = weekyearWeekDay();
      private static final DateTimeFormatter hm = hourMinute();
      private static final DateTimeFormatter hms = hourMinuteSecond();
      private static final DateTimeFormatter hmsl = hourMinuteSecondMillis();
      private static final DateTimeFormatter hmsf = hourMinuteSecondFraction();
      private static final DateTimeFormatter dh = dateHour();
      private static final DateTimeFormatter dhm = dateHourMinute();
      private static final DateTimeFormatter dhms = dateHourMinuteSecond();
      private static final DateTimeFormatter dhmsl = dateHourMinuteSecondMillis();
      private static final DateTimeFormatter dhmsf = dateHourMinuteSecondFraction();
      private static final DateTimeFormatter t = time();
      private static final DateTimeFormatter tx = timeNoMillis();
      private static final DateTimeFormatter tt = tTime();
      private static final DateTimeFormatter ttx = tTimeNoMillis();
      private static final DateTimeFormatter dt = dateTime();
      private static final DateTimeFormatter dtx = dateTimeNoMillis();
      private static final DateTimeFormatter wdt = weekDateTime();
      private static final DateTimeFormatter wdtx = weekDateTimeNoMillis();
      private static final DateTimeFormatter od = ordinalDate();
      private static final DateTimeFormatter odt = ordinalDateTime();
      private static final DateTimeFormatter odtx = ordinalDateTimeNoMillis();
      private static final DateTimeFormatter bd = basicDate();
      private static final DateTimeFormatter bt = basicTime();
      private static final DateTimeFormatter btx = basicTimeNoMillis();
      private static final DateTimeFormatter btt = basicTTime();
      private static final DateTimeFormatter bttx = basicTTimeNoMillis();
      private static final DateTimeFormatter bdt = basicDateTime();
      private static final DateTimeFormatter bdtx = basicDateTimeNoMillis();
      private static final DateTimeFormatter bod = basicOrdinalDate();
      private static final DateTimeFormatter bodt = basicOrdinalDateTime();
      private static final DateTimeFormatter bodtx = basicOrdinalDateTimeNoMillis();
      private static final DateTimeFormatter bwd = basicWeekDate();
      private static final DateTimeFormatter bwdt = basicWeekDateTime();
      private static final DateTimeFormatter bwdtx = basicWeekDateTimeNoMillis();
      private static final DateTimeFormatter dpe = dateElementParser();
      private static final DateTimeFormatter tpe = timeElementParser();
      private static final DateTimeFormatter dp = dateParser();
      private static final DateTimeFormatter ldp = localDateParser();
      private static final DateTimeFormatter tp = timeParser();
      private static final DateTimeFormatter ltp = localTimeParser();
      private static final DateTimeFormatter dtp = dateTimeParser();
      private static final DateTimeFormatter dotp = dateOptionalTimeParser();
      private static final DateTimeFormatter ldotp = localDateOptionalTimeParser();

      private static DateTimeFormatter dateParser() {
         if (dp == null) {
            DateTimeParser var0 = (new DateTimeFormatterBuilder()).appendLiteral('T').append(offsetElement()).toParser();
            return (new DateTimeFormatterBuilder()).append(dateElementParser()).appendOptional(var0).toFormatter();
         } else {
            return dp;
         }
      }

      private static DateTimeFormatter localDateParser() {
         return ldp == null ? dateElementParser().withZoneUTC() : ldp;
      }

      private static DateTimeFormatter dateElementParser() {
         return dpe == null ? (new DateTimeFormatterBuilder()).append((DateTimePrinter)null, (DateTimeParser[])(new DateTimeParser[]{(new DateTimeFormatterBuilder()).append(yearElement()).appendOptional((new DateTimeFormatterBuilder()).append(monthElement()).appendOptional(dayOfMonthElement().getParser()).toParser()).toParser(), (new DateTimeFormatterBuilder()).append(weekyearElement()).append(weekElement()).appendOptional(dayOfWeekElement().getParser()).toParser(), (new DateTimeFormatterBuilder()).append(yearElement()).append(dayOfYearElement()).toParser()})).toFormatter() : dpe;
      }

      private static DateTimeFormatter timeParser() {
         return tp == null ? (new DateTimeFormatterBuilder()).appendOptional(literalTElement().getParser()).append(timeElementParser()).appendOptional(offsetElement().getParser()).toFormatter() : tp;
      }

      private static DateTimeFormatter localTimeParser() {
         return ltp == null ? (new DateTimeFormatterBuilder()).appendOptional(literalTElement().getParser()).append(timeElementParser()).toFormatter().withZoneUTC() : ltp;
      }

      private static DateTimeFormatter timeElementParser() {
         if (tpe == null) {
            DateTimeParser var0 = (new DateTimeFormatterBuilder()).append((DateTimePrinter)null, (DateTimeParser[])(new DateTimeParser[]{(new DateTimeFormatterBuilder()).appendLiteral('.').toParser(), (new DateTimeFormatterBuilder()).appendLiteral(',').toParser()})).toParser();
            return (new DateTimeFormatterBuilder()).append(hourElement()).append((DateTimePrinter)null, (DateTimeParser[])(new DateTimeParser[]{(new DateTimeFormatterBuilder()).append(minuteElement()).append((DateTimePrinter)null, (DateTimeParser[])(new DateTimeParser[]{(new DateTimeFormatterBuilder()).append(secondElement()).appendOptional((new DateTimeFormatterBuilder()).append(var0).appendFractionOfSecond(1, 9).toParser()).toParser(), (new DateTimeFormatterBuilder()).append(var0).appendFractionOfMinute(1, 9).toParser(), null})).toParser(), (new DateTimeFormatterBuilder()).append(var0).appendFractionOfHour(1, 9).toParser(), null})).toFormatter();
         } else {
            return tpe;
         }
      }

      private static DateTimeFormatter dateTimeParser() {
         if (dtp == null) {
            DateTimeParser var0 = (new DateTimeFormatterBuilder()).appendLiteral('T').append(timeElementParser()).appendOptional(offsetElement().getParser()).toParser();
            return (new DateTimeFormatterBuilder()).append((DateTimePrinter)null, (DateTimeParser[])(new DateTimeParser[]{var0, dateOptionalTimeParser().getParser()})).toFormatter();
         } else {
            return dtp;
         }
      }

      private static DateTimeFormatter dateOptionalTimeParser() {
         if (dotp == null) {
            DateTimeParser var0 = (new DateTimeFormatterBuilder()).appendLiteral('T').appendOptional(timeElementParser().getParser()).appendOptional(offsetElement().getParser()).toParser();
            return (new DateTimeFormatterBuilder()).append(dateElementParser()).appendOptional(var0).toFormatter();
         } else {
            return dotp;
         }
      }

      private static DateTimeFormatter localDateOptionalTimeParser() {
         if (ldotp == null) {
            DateTimeParser var0 = (new DateTimeFormatterBuilder()).appendLiteral('T').append(timeElementParser()).toParser();
            return (new DateTimeFormatterBuilder()).append(dateElementParser()).appendOptional(var0).toFormatter().withZoneUTC();
         } else {
            return ldotp;
         }
      }

      private static DateTimeFormatter time() {
         return t == null ? (new DateTimeFormatterBuilder()).append(hourMinuteSecondFraction()).append(offsetElement()).toFormatter() : t;
      }

      private static DateTimeFormatter timeNoMillis() {
         return tx == null ? (new DateTimeFormatterBuilder()).append(hourMinuteSecond()).append(offsetElement()).toFormatter() : tx;
      }

      private static DateTimeFormatter tTime() {
         return tt == null ? (new DateTimeFormatterBuilder()).append(literalTElement()).append(time()).toFormatter() : tt;
      }

      private static DateTimeFormatter tTimeNoMillis() {
         return ttx == null ? (new DateTimeFormatterBuilder()).append(literalTElement()).append(timeNoMillis()).toFormatter() : ttx;
      }

      private static DateTimeFormatter dateTime() {
         return dt == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(tTime()).toFormatter() : dt;
      }

      private static DateTimeFormatter dateTimeNoMillis() {
         return dtx == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(tTimeNoMillis()).toFormatter() : dtx;
      }

      private static DateTimeFormatter ordinalDate() {
         return od == null ? (new DateTimeFormatterBuilder()).append(yearElement()).append(dayOfYearElement()).toFormatter() : od;
      }

      private static DateTimeFormatter ordinalDateTime() {
         return odt == null ? (new DateTimeFormatterBuilder()).append(ordinalDate()).append(tTime()).toFormatter() : odt;
      }

      private static DateTimeFormatter ordinalDateTimeNoMillis() {
         return odtx == null ? (new DateTimeFormatterBuilder()).append(ordinalDate()).append(tTimeNoMillis()).toFormatter() : odtx;
      }

      private static DateTimeFormatter weekDateTime() {
         return wdt == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.weekDate()).append(tTime()).toFormatter() : wdt;
      }

      private static DateTimeFormatter weekDateTimeNoMillis() {
         return wdtx == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.weekDate()).append(tTimeNoMillis()).toFormatter() : wdtx;
      }

      private static DateTimeFormatter basicDate() {
         return bd == null ? (new DateTimeFormatterBuilder()).appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2).toFormatter() : bd;
      }

      private static DateTimeFormatter basicTime() {
         return bt == null ? (new DateTimeFormatterBuilder()).appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendLiteral('.').appendFractionOfSecond(3, 9).appendTimeZoneOffset("Z", false, 2, 2).toFormatter() : bt;
      }

      private static DateTimeFormatter basicTimeNoMillis() {
         return btx == null ? (new DateTimeFormatterBuilder()).appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendTimeZoneOffset("Z", false, 2, 2).toFormatter() : btx;
      }

      private static DateTimeFormatter basicTTime() {
         return btt == null ? (new DateTimeFormatterBuilder()).append(literalTElement()).append(basicTime()).toFormatter() : btt;
      }

      private static DateTimeFormatter basicTTimeNoMillis() {
         return bttx == null ? (new DateTimeFormatterBuilder()).append(literalTElement()).append(basicTimeNoMillis()).toFormatter() : bttx;
      }

      private static DateTimeFormatter basicDateTime() {
         return bdt == null ? (new DateTimeFormatterBuilder()).append(basicDate()).append(basicTTime()).toFormatter() : bdt;
      }

      private static DateTimeFormatter basicDateTimeNoMillis() {
         return bdtx == null ? (new DateTimeFormatterBuilder()).append(basicDate()).append(basicTTimeNoMillis()).toFormatter() : bdtx;
      }

      private static DateTimeFormatter basicOrdinalDate() {
         return bod == null ? (new DateTimeFormatterBuilder()).appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.dayOfYear(), 3).toFormatter() : bod;
      }

      private static DateTimeFormatter basicOrdinalDateTime() {
         return bodt == null ? (new DateTimeFormatterBuilder()).append(basicOrdinalDate()).append(basicTTime()).toFormatter() : bodt;
      }

      private static DateTimeFormatter basicOrdinalDateTimeNoMillis() {
         return bodtx == null ? (new DateTimeFormatterBuilder()).append(basicOrdinalDate()).append(basicTTimeNoMillis()).toFormatter() : bodtx;
      }

      private static DateTimeFormatter basicWeekDate() {
         return bwd == null ? (new DateTimeFormatterBuilder()).appendWeekyear(4, 4).appendLiteral('W').appendFixedDecimal(DateTimeFieldType.weekOfWeekyear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfWeek(), 1).toFormatter() : bwd;
      }

      private static DateTimeFormatter basicWeekDateTime() {
         return bwdt == null ? (new DateTimeFormatterBuilder()).append(basicWeekDate()).append(basicTTime()).toFormatter() : bwdt;
      }

      private static DateTimeFormatter basicWeekDateTimeNoMillis() {
         return bwdtx == null ? (new DateTimeFormatterBuilder()).append(basicWeekDate()).append(basicTTimeNoMillis()).toFormatter() : bwdtx;
      }

      private static DateTimeFormatter yearMonth() {
         return ym == null ? (new DateTimeFormatterBuilder()).append(yearElement()).append(monthElement()).toFormatter() : ym;
      }

      private static DateTimeFormatter yearMonthDay() {
         return ymd == null ? (new DateTimeFormatterBuilder()).append(yearElement()).append(monthElement()).append(dayOfMonthElement()).toFormatter() : ymd;
      }

      private static DateTimeFormatter weekyearWeek() {
         return ww == null ? (new DateTimeFormatterBuilder()).append(weekyearElement()).append(weekElement()).toFormatter() : ww;
      }

      private static DateTimeFormatter weekyearWeekDay() {
         return wwd == null ? (new DateTimeFormatterBuilder()).append(weekyearElement()).append(weekElement()).append(dayOfWeekElement()).toFormatter() : wwd;
      }

      private static DateTimeFormatter hourMinute() {
         return hm == null ? (new DateTimeFormatterBuilder()).append(hourElement()).append(minuteElement()).toFormatter() : hm;
      }

      private static DateTimeFormatter hourMinuteSecond() {
         return hms == null ? (new DateTimeFormatterBuilder()).append(hourElement()).append(minuteElement()).append(secondElement()).toFormatter() : hms;
      }

      private static DateTimeFormatter hourMinuteSecondMillis() {
         return hmsl == null ? (new DateTimeFormatterBuilder()).append(hourElement()).append(minuteElement()).append(secondElement()).appendLiteral('.').appendFractionOfSecond(3, 3).toFormatter() : hmsl;
      }

      private static DateTimeFormatter hourMinuteSecondFraction() {
         return hmsf == null ? (new DateTimeFormatterBuilder()).append(hourElement()).append(minuteElement()).append(secondElement()).append(fractionElement()).toFormatter() : hmsf;
      }

      private static DateTimeFormatter dateHour() {
         return dh == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(literalTElement()).append(ISODateTimeFormat.hour()).toFormatter() : dh;
      }

      private static DateTimeFormatter dateHourMinute() {
         return dhm == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinute()).toFormatter() : dhm;
      }

      private static DateTimeFormatter dateHourMinuteSecond() {
         return dhms == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinuteSecond()).toFormatter() : dhms;
      }

      private static DateTimeFormatter dateHourMinuteSecondMillis() {
         return dhmsl == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinuteSecondMillis()).toFormatter() : dhmsl;
      }

      private static DateTimeFormatter dateHourMinuteSecondFraction() {
         return dhmsf == null ? (new DateTimeFormatterBuilder()).append(ISODateTimeFormat.date()).append(literalTElement()).append(hourMinuteSecondFraction()).toFormatter() : dhmsf;
      }

      private static DateTimeFormatter yearElement() {
         return ye == null ? (new DateTimeFormatterBuilder()).appendYear(4, 9).toFormatter() : ye;
      }

      private static DateTimeFormatter monthElement() {
         return mye == null ? (new DateTimeFormatterBuilder()).appendLiteral('-').appendMonthOfYear(2).toFormatter() : mye;
      }

      private static DateTimeFormatter dayOfMonthElement() {
         return dme == null ? (new DateTimeFormatterBuilder()).appendLiteral('-').appendDayOfMonth(2).toFormatter() : dme;
      }

      private static DateTimeFormatter weekyearElement() {
         return we == null ? (new DateTimeFormatterBuilder()).appendWeekyear(4, 9).toFormatter() : we;
      }

      private static DateTimeFormatter weekElement() {
         return wwe == null ? (new DateTimeFormatterBuilder()).appendLiteral("-W").appendWeekOfWeekyear(2).toFormatter() : wwe;
      }

      private static DateTimeFormatter dayOfWeekElement() {
         return dwe == null ? (new DateTimeFormatterBuilder()).appendLiteral('-').appendDayOfWeek(1).toFormatter() : dwe;
      }

      private static DateTimeFormatter dayOfYearElement() {
         return dye == null ? (new DateTimeFormatterBuilder()).appendLiteral('-').appendDayOfYear(3).toFormatter() : dye;
      }

      private static DateTimeFormatter literalTElement() {
         return lte == null ? (new DateTimeFormatterBuilder()).appendLiteral('T').toFormatter() : lte;
      }

      private static DateTimeFormatter hourElement() {
         return hde == null ? (new DateTimeFormatterBuilder()).appendHourOfDay(2).toFormatter() : hde;
      }

      private static DateTimeFormatter minuteElement() {
         return mhe == null ? (new DateTimeFormatterBuilder()).appendLiteral(':').appendMinuteOfHour(2).toFormatter() : mhe;
      }

      private static DateTimeFormatter secondElement() {
         return sme == null ? (new DateTimeFormatterBuilder()).appendLiteral(':').appendSecondOfMinute(2).toFormatter() : sme;
      }

      private static DateTimeFormatter fractionElement() {
         return fse == null ? (new DateTimeFormatterBuilder()).appendLiteral('.').appendFractionOfSecond(3, 9).toFormatter() : fse;
      }

      private static DateTimeFormatter offsetElement() {
         return ze == null ? (new DateTimeFormatterBuilder()).appendTimeZoneOffset("Z", true, 2, 4).toFormatter() : ze;
      }
   }
}
