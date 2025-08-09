package org.joda.time.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

public class DateTimeFormatterBuilder {
   private ArrayList iElementPairs = new ArrayList();
   private Object iFormatter;

   public DateTimeFormatter toFormatter() {
      Object var1 = this.getFormatter();
      InternalPrinter var2 = null;
      if (this.isPrinter(var1)) {
         var2 = (InternalPrinter)var1;
      }

      InternalParser var3 = null;
      if (this.isParser(var1)) {
         var3 = (InternalParser)var1;
      }

      if (var2 == null && var3 == null) {
         throw new UnsupportedOperationException("Both printing and parsing not supported");
      } else {
         return new DateTimeFormatter(var2, var3);
      }
   }

   public DateTimePrinter toPrinter() {
      Object var1 = this.getFormatter();
      if (this.isPrinter(var1)) {
         InternalPrinter var2 = (InternalPrinter)var1;
         return InternalPrinterDateTimePrinter.of(var2);
      } else {
         throw new UnsupportedOperationException("Printing is not supported");
      }
   }

   public DateTimeParser toParser() {
      Object var1 = this.getFormatter();
      if (this.isParser(var1)) {
         InternalParser var2 = (InternalParser)var1;
         return InternalParserDateTimeParser.of(var2);
      } else {
         throw new UnsupportedOperationException("Parsing is not supported");
      }
   }

   public boolean canBuildFormatter() {
      return this.isFormatter(this.getFormatter());
   }

   public boolean canBuildPrinter() {
      return this.isPrinter(this.getFormatter());
   }

   public boolean canBuildParser() {
      return this.isParser(this.getFormatter());
   }

   public void clear() {
      this.iFormatter = null;
      this.iElementPairs.clear();
   }

   public DateTimeFormatterBuilder append(DateTimeFormatter var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("No formatter supplied");
      } else {
         return this.append0(var1.getPrinter0(), var1.getParser0());
      }
   }

   public DateTimeFormatterBuilder append(DateTimePrinter var1) {
      this.checkPrinter(var1);
      return this.append0(DateTimePrinterInternalPrinter.of(var1), (InternalParser)null);
   }

   public DateTimeFormatterBuilder append(DateTimeParser var1) {
      this.checkParser(var1);
      return this.append0((InternalPrinter)null, DateTimeParserInternalParser.of(var1));
   }

   public DateTimeFormatterBuilder append(DateTimePrinter var1, DateTimeParser var2) {
      this.checkPrinter(var1);
      this.checkParser(var2);
      return this.append0(DateTimePrinterInternalPrinter.of(var1), DateTimeParserInternalParser.of(var2));
   }

   public DateTimeFormatterBuilder append(DateTimePrinter var1, DateTimeParser[] var2) {
      if (var1 != null) {
         this.checkPrinter(var1);
      }

      if (var2 == null) {
         throw new IllegalArgumentException("No parsers supplied");
      } else {
         int var3 = var2.length;
         if (var3 == 1) {
            if (var2[0] == null) {
               throw new IllegalArgumentException("No parser supplied");
            } else {
               return this.append0(DateTimePrinterInternalPrinter.of(var1), DateTimeParserInternalParser.of(var2[0]));
            }
         } else {
            InternalParser[] var4 = new InternalParser[var3];

            int var5;
            for(var5 = 0; var5 < var3 - 1; ++var5) {
               if ((var4[var5] = DateTimeParserInternalParser.of(var2[var5])) == null) {
                  throw new IllegalArgumentException("Incomplete parser array");
               }
            }

            var4[var5] = DateTimeParserInternalParser.of(var2[var5]);
            return this.append0(DateTimePrinterInternalPrinter.of(var1), new MatchingParser(var4));
         }
      }
   }

   public DateTimeFormatterBuilder appendOptional(DateTimeParser var1) {
      this.checkParser(var1);
      InternalParser[] var2 = new InternalParser[]{DateTimeParserInternalParser.of(var1), null};
      return this.append0((InternalPrinter)null, new MatchingParser(var2));
   }

   private void checkParser(DateTimeParser var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("No parser supplied");
      }
   }

   private void checkPrinter(DateTimePrinter var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("No printer supplied");
      }
   }

   private DateTimeFormatterBuilder append0(Object var1) {
      this.iFormatter = null;
      this.iElementPairs.add(var1);
      this.iElementPairs.add(var1);
      return this;
   }

   private DateTimeFormatterBuilder append0(InternalPrinter var1, InternalParser var2) {
      this.iFormatter = null;
      this.iElementPairs.add(var1);
      this.iElementPairs.add(var2);
      return this;
   }

   public DateTimeFormatterBuilder appendLiteral(char var1) {
      return this.append0(new CharacterLiteral(var1));
   }

   public DateTimeFormatterBuilder appendLiteral(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Literal must not be null");
      } else {
         switch (var1.length()) {
            case 0:
               return this;
            case 1:
               return this.append0(new CharacterLiteral(var1.charAt(0)));
            default:
               return this.append0(new StringLiteral(var1));
         }
      }
   }

   public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType var1, int var2, int var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else {
         if (var3 < var2) {
            var3 = var2;
         }

         if (var2 >= 0 && var3 > 0) {
            return var2 <= 1 ? this.append0(new UnpaddedNumber(var1, var3, false)) : this.append0(new PaddedNumber(var1, var3, false, var2));
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("Illegal number of digits: " + var2);
      } else {
         return this.append0(new FixedNumber(var1, var2, false));
      }
   }

   public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType var1, int var2, int var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else {
         if (var3 < var2) {
            var3 = var2;
         }

         if (var2 >= 0 && var3 > 0) {
            return var2 <= 1 ? this.append0(new UnpaddedNumber(var1, var3, true)) : this.append0(new PaddedNumber(var1, var3, true, var2));
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public DateTimeFormatterBuilder appendFixedSignedDecimal(DateTimeFieldType var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("Illegal number of digits: " + var2);
      } else {
         return this.append0(new FixedNumber(var1, var2, true));
      }
   }

   public DateTimeFormatterBuilder appendText(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else {
         return this.append0(new TextField(var1, false));
      }
   }

   public DateTimeFormatterBuilder appendShortText(DateTimeFieldType var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else {
         return this.append0(new TextField(var1, true));
      }
   }

   public DateTimeFormatterBuilder appendFraction(DateTimeFieldType var1, int var2, int var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Field type must not be null");
      } else {
         if (var3 < var2) {
            var3 = var2;
         }

         if (var2 >= 0 && var3 > 0) {
            return this.append0(new Fraction(var1, var2, var3));
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public DateTimeFormatterBuilder appendFractionOfSecond(int var1, int var2) {
      return this.appendFraction(DateTimeFieldType.secondOfDay(), var1, var2);
   }

   public DateTimeFormatterBuilder appendFractionOfMinute(int var1, int var2) {
      return this.appendFraction(DateTimeFieldType.minuteOfDay(), var1, var2);
   }

   public DateTimeFormatterBuilder appendFractionOfHour(int var1, int var2) {
      return this.appendFraction(DateTimeFieldType.hourOfDay(), var1, var2);
   }

   public DateTimeFormatterBuilder appendFractionOfDay(int var1, int var2) {
      return this.appendFraction(DateTimeFieldType.dayOfYear(), var1, var2);
   }

   public DateTimeFormatterBuilder appendMillisOfSecond(int var1) {
      return this.appendDecimal(DateTimeFieldType.millisOfSecond(), var1, 3);
   }

   public DateTimeFormatterBuilder appendMillisOfDay(int var1) {
      return this.appendDecimal(DateTimeFieldType.millisOfDay(), var1, 8);
   }

   public DateTimeFormatterBuilder appendSecondOfMinute(int var1) {
      return this.appendDecimal(DateTimeFieldType.secondOfMinute(), var1, 2);
   }

   public DateTimeFormatterBuilder appendSecondOfDay(int var1) {
      return this.appendDecimal(DateTimeFieldType.secondOfDay(), var1, 5);
   }

   public DateTimeFormatterBuilder appendMinuteOfHour(int var1) {
      return this.appendDecimal(DateTimeFieldType.minuteOfHour(), var1, 2);
   }

   public DateTimeFormatterBuilder appendMinuteOfDay(int var1) {
      return this.appendDecimal(DateTimeFieldType.minuteOfDay(), var1, 4);
   }

   public DateTimeFormatterBuilder appendHourOfDay(int var1) {
      return this.appendDecimal(DateTimeFieldType.hourOfDay(), var1, 2);
   }

   public DateTimeFormatterBuilder appendClockhourOfDay(int var1) {
      return this.appendDecimal(DateTimeFieldType.clockhourOfDay(), var1, 2);
   }

   public DateTimeFormatterBuilder appendHourOfHalfday(int var1) {
      return this.appendDecimal(DateTimeFieldType.hourOfHalfday(), var1, 2);
   }

   public DateTimeFormatterBuilder appendClockhourOfHalfday(int var1) {
      return this.appendDecimal(DateTimeFieldType.clockhourOfHalfday(), var1, 2);
   }

   public DateTimeFormatterBuilder appendDayOfWeek(int var1) {
      return this.appendDecimal(DateTimeFieldType.dayOfWeek(), var1, 1);
   }

   public DateTimeFormatterBuilder appendDayOfMonth(int var1) {
      return this.appendDecimal(DateTimeFieldType.dayOfMonth(), var1, 2);
   }

   public DateTimeFormatterBuilder appendDayOfYear(int var1) {
      return this.appendDecimal(DateTimeFieldType.dayOfYear(), var1, 3);
   }

   public DateTimeFormatterBuilder appendWeekOfWeekyear(int var1) {
      return this.appendDecimal(DateTimeFieldType.weekOfWeekyear(), var1, 2);
   }

   public DateTimeFormatterBuilder appendWeekyear(int var1, int var2) {
      return this.appendSignedDecimal(DateTimeFieldType.weekyear(), var1, var2);
   }

   public DateTimeFormatterBuilder appendMonthOfYear(int var1) {
      return this.appendDecimal(DateTimeFieldType.monthOfYear(), var1, 2);
   }

   public DateTimeFormatterBuilder appendYear(int var1, int var2) {
      return this.appendSignedDecimal(DateTimeFieldType.year(), var1, var2);
   }

   public DateTimeFormatterBuilder appendTwoDigitYear(int var1) {
      return this.appendTwoDigitYear(var1, false);
   }

   public DateTimeFormatterBuilder appendTwoDigitYear(int var1, boolean var2) {
      return this.append0(new TwoDigitYear(DateTimeFieldType.year(), var1, var2));
   }

   public DateTimeFormatterBuilder appendTwoDigitWeekyear(int var1) {
      return this.appendTwoDigitWeekyear(var1, false);
   }

   public DateTimeFormatterBuilder appendTwoDigitWeekyear(int var1, boolean var2) {
      return this.append0(new TwoDigitYear(DateTimeFieldType.weekyear(), var1, var2));
   }

   public DateTimeFormatterBuilder appendYearOfEra(int var1, int var2) {
      return this.appendDecimal(DateTimeFieldType.yearOfEra(), var1, var2);
   }

   public DateTimeFormatterBuilder appendYearOfCentury(int var1, int var2) {
      return this.appendDecimal(DateTimeFieldType.yearOfCentury(), var1, var2);
   }

   public DateTimeFormatterBuilder appendCenturyOfEra(int var1, int var2) {
      return this.appendSignedDecimal(DateTimeFieldType.centuryOfEra(), var1, var2);
   }

   public DateTimeFormatterBuilder appendHalfdayOfDayText() {
      return this.appendText(DateTimeFieldType.halfdayOfDay());
   }

   public DateTimeFormatterBuilder appendDayOfWeekText() {
      return this.appendText(DateTimeFieldType.dayOfWeek());
   }

   public DateTimeFormatterBuilder appendDayOfWeekShortText() {
      return this.appendShortText(DateTimeFieldType.dayOfWeek());
   }

   public DateTimeFormatterBuilder appendMonthOfYearText() {
      return this.appendText(DateTimeFieldType.monthOfYear());
   }

   public DateTimeFormatterBuilder appendMonthOfYearShortText() {
      return this.appendShortText(DateTimeFieldType.monthOfYear());
   }

   public DateTimeFormatterBuilder appendEraText() {
      return this.appendText(DateTimeFieldType.era());
   }

   public DateTimeFormatterBuilder appendTimeZoneName() {
      return this.append0(new TimeZoneName(0, (Map)null), (InternalParser)null);
   }

   public DateTimeFormatterBuilder appendTimeZoneName(Map var1) {
      TimeZoneName var2 = new TimeZoneName(0, var1);
      return this.append0(var2, var2);
   }

   public DateTimeFormatterBuilder appendTimeZoneShortName() {
      return this.append0(new TimeZoneName(1, (Map)null), (InternalParser)null);
   }

   public DateTimeFormatterBuilder appendTimeZoneShortName(Map var1) {
      TimeZoneName var2 = new TimeZoneName(1, var1);
      return this.append0(var2, var2);
   }

   public DateTimeFormatterBuilder appendTimeZoneId() {
      return this.append0(DateTimeFormatterBuilder.TimeZoneId.INSTANCE, DateTimeFormatterBuilder.TimeZoneId.INSTANCE);
   }

   public DateTimeFormatterBuilder appendTimeZoneOffset(String var1, boolean var2, int var3, int var4) {
      return this.append0(new TimeZoneOffset(var1, var1, var2, var3, var4));
   }

   public DateTimeFormatterBuilder appendTimeZoneOffset(String var1, String var2, boolean var3, int var4, int var5) {
      return this.append0(new TimeZoneOffset(var1, var2, var3, var4, var5));
   }

   public DateTimeFormatterBuilder appendPattern(String var1) {
      DateTimeFormat.appendPatternTo(this, var1);
      return this;
   }

   private Object getFormatter() {
      Object var1 = this.iFormatter;
      if (var1 == null) {
         if (this.iElementPairs.size() == 2) {
            Object var2 = this.iElementPairs.get(0);
            Object var3 = this.iElementPairs.get(1);
            if (var2 != null) {
               if (var2 == var3 || var3 == null) {
                  var1 = var2;
               }
            } else {
               var1 = var3;
            }
         }

         if (var1 == null) {
            var1 = new Composite(this.iElementPairs);
         }

         this.iFormatter = var1;
      }

      return var1;
   }

   private boolean isPrinter(Object var1) {
      if (var1 instanceof InternalPrinter) {
         return var1 instanceof Composite ? ((Composite)var1).isPrinter() : true;
      } else {
         return false;
      }
   }

   private boolean isParser(Object var1) {
      if (var1 instanceof InternalParser) {
         return var1 instanceof Composite ? ((Composite)var1).isParser() : true;
      } else {
         return false;
      }
   }

   private boolean isFormatter(Object var1) {
      return this.isPrinter(var1) || this.isParser(var1);
   }

   static void appendUnknownString(Appendable var0, int var1) throws IOException {
      int var2 = var1;

      while(true) {
         --var2;
         if (var2 < 0) {
            return;
         }

         var0.append('�');
      }
   }

   static boolean csStartsWith(CharSequence var0, int var1, String var2) {
      int var3 = var2.length();
      if (var0.length() - var1 < var3) {
         return false;
      } else {
         for(int var4 = 0; var4 < var3; ++var4) {
            if (var0.charAt(var1 + var4) != var2.charAt(var4)) {
               return false;
            }
         }

         return true;
      }
   }

   static boolean csStartsWithIgnoreCase(CharSequence var0, int var1, String var2) {
      int var3 = var2.length();
      if (var0.length() - var1 < var3) {
         return false;
      } else {
         for(int var4 = 0; var4 < var3; ++var4) {
            char var5 = var0.charAt(var1 + var4);
            char var6 = var2.charAt(var4);
            if (var5 != var6) {
               char var7 = Character.toUpperCase(var5);
               char var8 = Character.toUpperCase(var6);
               if (var7 != var8 && Character.toLowerCase(var7) != Character.toLowerCase(var8)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   static class CharacterLiteral implements InternalPrinter, InternalParser {
      private final char iValue;

      CharacterLiteral(char var1) {
         this.iValue = var1;
      }

      public int estimatePrintedLength() {
         return 1;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         var1.append(this.iValue);
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         var1.append(this.iValue);
      }

      public int estimateParsedLength() {
         return 1;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         if (var3 >= var2.length()) {
            return ~var3;
         } else {
            char var4 = var2.charAt(var3);
            char var5 = this.iValue;
            if (var4 != var5) {
               var4 = Character.toUpperCase(var4);
               var5 = Character.toUpperCase(var5);
               if (var4 != var5) {
                  var4 = Character.toLowerCase(var4);
                  var5 = Character.toLowerCase(var5);
                  if (var4 != var5) {
                     return ~var3;
                  }
               }
            }

            return var3 + 1;
         }
      }
   }

   static class StringLiteral implements InternalPrinter, InternalParser {
      private final String iValue;

      StringLiteral(String var1) {
         this.iValue = var1;
      }

      public int estimatePrintedLength() {
         return this.iValue.length();
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         var1.append(this.iValue);
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         var1.append(this.iValue);
      }

      public int estimateParsedLength() {
         return this.iValue.length();
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         return DateTimeFormatterBuilder.csStartsWithIgnoreCase(var2, var3, this.iValue) ? var3 + this.iValue.length() : ~var3;
      }
   }

   abstract static class NumberFormatter implements InternalPrinter, InternalParser {
      protected final DateTimeFieldType iFieldType;
      protected final int iMaxParsedDigits;
      protected final boolean iSigned;

      NumberFormatter(DateTimeFieldType var1, int var2, boolean var3) {
         this.iFieldType = var1;
         this.iMaxParsedDigits = var2;
         this.iSigned = var3;
      }

      public int estimateParsedLength() {
         return this.iMaxParsedDigits;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         int var4 = Math.min(this.iMaxParsedDigits, var2.length() - var3);
         boolean var5 = false;
         boolean var6 = false;
         int var7 = 0;

         while(var7 < var4) {
            char var8 = var2.charAt(var3 + var7);
            if (var7 == 0 && (var8 == '-' || var8 == '+') && this.iSigned) {
               var5 = var8 == '-';
               var6 = var8 == '+';
               if (var7 + 1 >= var4 || (var8 = var2.charAt(var3 + var7 + 1)) < '0' || var8 > '9') {
                  break;
               }

               ++var7;
               var4 = Math.min(var4 + 1, var2.length() - var3);
            } else {
               if (var8 < '0' || var8 > '9') {
                  break;
               }

               ++var7;
            }
         }

         if (var7 == 0) {
            return ~var3;
         } else {
            int var14;
            if (var7 >= 9) {
               if (var6) {
                  var14 = Integer.parseInt(var2.subSequence(var3 + 1, var3 = var3 + var7).toString());
               } else {
                  var14 = Integer.parseInt(var2.subSequence(var3, var3 = var3 + var7).toString());
               }
            } else {
               int var9 = var3;
               if (var5 || var6) {
                  var9 = var3 + 1;
               }

               try {
                  var14 = var2.charAt(var9++) - 48;
               } catch (StringIndexOutOfBoundsException var11) {
                  return ~var3;
               }

               for(var3 += var7; var9 < var3; var14 = (var14 << 3) + (var14 << 1) + var2.charAt(var9++) - 48) {
               }

               if (var5) {
                  var14 = -var14;
               }
            }

            var1.saveField(this.iFieldType, var14);
            return var3;
         }
      }
   }

   static class UnpaddedNumber extends NumberFormatter {
      protected UnpaddedNumber(DateTimeFieldType var1, int var2, boolean var3) {
         super(var1, var2, var3);
      }

      public int estimatePrintedLength() {
         return this.iMaxParsedDigits;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         try {
            DateTimeField var8 = this.iFieldType.getField(var4);
            FormatUtils.appendUnpaddedInteger(var1, var8.get(var2));
         } catch (RuntimeException var9) {
            var1.append('�');
         }

      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         if (var2.isSupported(this.iFieldType)) {
            try {
               FormatUtils.appendUnpaddedInteger(var1, var2.get(this.iFieldType));
            } catch (RuntimeException var5) {
               var1.append('�');
            }
         } else {
            var1.append('�');
         }

      }
   }

   static class PaddedNumber extends NumberFormatter {
      protected final int iMinPrintedDigits;

      protected PaddedNumber(DateTimeFieldType var1, int var2, boolean var3, int var4) {
         super(var1, var2, var3);
         this.iMinPrintedDigits = var4;
      }

      public int estimatePrintedLength() {
         return this.iMaxParsedDigits;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         try {
            DateTimeField var8 = this.iFieldType.getField(var4);
            FormatUtils.appendPaddedInteger(var1, var8.get(var2), this.iMinPrintedDigits);
         } catch (RuntimeException var9) {
            DateTimeFormatterBuilder.appendUnknownString(var1, this.iMinPrintedDigits);
         }

      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         if (var2.isSupported(this.iFieldType)) {
            try {
               FormatUtils.appendPaddedInteger(var1, var2.get(this.iFieldType), this.iMinPrintedDigits);
            } catch (RuntimeException var5) {
               DateTimeFormatterBuilder.appendUnknownString(var1, this.iMinPrintedDigits);
            }
         } else {
            DateTimeFormatterBuilder.appendUnknownString(var1, this.iMinPrintedDigits);
         }

      }
   }

   static class FixedNumber extends PaddedNumber {
      protected FixedNumber(DateTimeFieldType var1, int var2, boolean var3) {
         super(var1, var2, var3, var2);
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         int var4 = super.parseInto(var1, var2, var3);
         if (var4 < 0) {
            return var4;
         } else {
            int var5 = var3 + this.iMaxParsedDigits;
            if (var4 != var5) {
               if (this.iSigned) {
                  char var6 = var2.charAt(var3);
                  if (var6 == '-' || var6 == '+') {
                     ++var5;
                  }
               }

               if (var4 > var5) {
                  return ~(var5 + 1);
               }

               if (var4 < var5) {
                  return ~var4;
               }
            }

            return var4;
         }
      }
   }

   static class TwoDigitYear implements InternalPrinter, InternalParser {
      private final DateTimeFieldType iType;
      private final int iPivot;
      private final boolean iLenientParse;

      TwoDigitYear(DateTimeFieldType var1, int var2, boolean var3) {
         this.iType = var1;
         this.iPivot = var2;
         this.iLenientParse = var3;
      }

      public int estimateParsedLength() {
         return this.iLenientParse ? 4 : 2;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         int var4 = var2.length() - var3;
         if (!this.iLenientParse) {
            var4 = Math.min(2, var4);
            if (var4 < 2) {
               return ~var3;
            }
         } else {
            boolean var5 = false;
            boolean var6 = false;
            int var7 = 0;

            while(var7 < var4) {
               char var8 = var2.charAt(var3 + var7);
               if (var7 != 0 || var8 != '-' && var8 != '+') {
                  if (var8 < '0' || var8 > '9') {
                     break;
                  }

                  ++var7;
               } else {
                  var5 = true;
                  var6 = var8 == '-';
                  if (var6) {
                     ++var7;
                  } else {
                     ++var3;
                     --var4;
                  }
               }
            }

            if (var7 == 0) {
               return ~var3;
            }

            if (var5 || var7 != 2) {
               int var20;
               if (var7 >= 9) {
                  var20 = Integer.parseInt(var2.subSequence(var3, var3 = var3 + var7).toString());
               } else {
                  int var9 = var3;
                  if (var6) {
                     var9 = var3 + 1;
                  }

                  try {
                     var20 = var2.charAt(var9++) - 48;
                  } catch (StringIndexOutOfBoundsException var11) {
                     return ~var3;
                  }

                  for(var3 += var7; var9 < var3; var20 = (var20 << 3) + (var20 << 1) + var2.charAt(var9++) - 48) {
                  }

                  if (var6) {
                     var20 = -var20;
                  }
               }

               var1.saveField(this.iType, var20);
               return var3;
            }
         }

         char var17 = var2.charAt(var3);
         if (var17 >= '0' && var17 <= '9') {
            int var14 = var17 - 48;
            var17 = var2.charAt(var3 + 1);
            if (var17 >= '0' && var17 <= '9') {
               var14 = (var14 << 3) + (var14 << 1) + var17 - 48;
               int var19 = this.iPivot;
               if (var1.getPivotYear() != null) {
                  var19 = var1.getPivotYear();
               }

               int var21 = var19 - 50;
               int var23;
               if (var21 >= 0) {
                  var23 = var21 % 100;
               } else {
                  var23 = 99 + (var21 + 1) % 100;
               }

               var14 += var21 + (var14 < var23 ? 100 : 0) - var23;
               var1.saveField(this.iType, var14);
               return var3 + 2;
            } else {
               return ~var3;
            }
         } else {
            return ~var3;
         }
      }

      public int estimatePrintedLength() {
         return 2;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         int var8 = this.getTwoDigitYear(var2, var4);
         if (var8 < 0) {
            var1.append('�');
            var1.append('�');
         } else {
            FormatUtils.appendPaddedInteger((Appendable)var1, var8, 2);
         }

      }

      private int getTwoDigitYear(long var1, Chronology var3) {
         try {
            int var4 = this.iType.getField(var3).get(var1);
            if (var4 < 0) {
               var4 = -var4;
            }

            return var4 % 100;
         } catch (RuntimeException var5) {
            return -1;
         }
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         int var4 = this.getTwoDigitYear(var2);
         if (var4 < 0) {
            var1.append('�');
            var1.append('�');
         } else {
            FormatUtils.appendPaddedInteger((Appendable)var1, var4, 2);
         }

      }

      private int getTwoDigitYear(ReadablePartial var1) {
         if (var1.isSupported(this.iType)) {
            try {
               int var2 = var1.get(this.iType);
               if (var2 < 0) {
                  var2 = -var2;
               }

               return var2 % 100;
            } catch (RuntimeException var3) {
            }
         }

         return -1;
      }
   }

   static class TextField implements InternalPrinter, InternalParser {
      private static Map cParseCache = new ConcurrentHashMap();
      private final DateTimeFieldType iFieldType;
      private final boolean iShort;

      TextField(DateTimeFieldType var1, boolean var2) {
         this.iFieldType = var1;
         this.iShort = var2;
      }

      public int estimatePrintedLength() {
         return this.iShort ? 6 : 20;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         try {
            var1.append(this.print(var2, var4, var7));
         } catch (RuntimeException var9) {
            var1.append('�');
         }

      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         try {
            var1.append(this.print(var2, var3));
         } catch (RuntimeException var5) {
            var1.append('�');
         }

      }

      private String print(long var1, Chronology var3, Locale var4) {
         DateTimeField var5 = this.iFieldType.getField(var3);
         return this.iShort ? var5.getAsShortText(var1, var4) : var5.getAsText(var1, var4);
      }

      private String print(ReadablePartial var1, Locale var2) {
         if (var1.isSupported(this.iFieldType)) {
            DateTimeField var3 = this.iFieldType.getField(var1.getChronology());
            return this.iShort ? var3.getAsShortText(var1, var2) : var3.getAsText(var1, var2);
         } else {
            return "�";
         }
      }

      public int estimateParsedLength() {
         return this.estimatePrintedLength();
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         Locale var4 = var1.getLocale();
         Object var5 = null;
         int var6 = 0;
         Object var7 = (Map)cParseCache.get(var4);
         if (var7 == null) {
            var7 = new ConcurrentHashMap();
            cParseCache.put(var4, var7);
         }

         Object[] var8 = ((Map)var7).get(this.iFieldType);
         if (var8 == null) {
            var5 = new ConcurrentHashMap(32);
            MutableDateTime var9 = new MutableDateTime(0L, DateTimeZone.UTC);
            MutableDateTime.Property var10 = var9.property(this.iFieldType);
            int var11 = var10.getMinimumValueOverall();
            int var12 = var10.getMaximumValueOverall();
            if (var12 - var11 > 32) {
               return ~var3;
            }

            var6 = var10.getMaximumTextLength(var4);

            for(int var13 = var11; var13 <= var12; ++var13) {
               var10.set(var13);
               ((Map)var5).put(var10.getAsShortText(var4), Boolean.TRUE);
               ((Map)var5).put(var10.getAsShortText(var4).toLowerCase(var4), Boolean.TRUE);
               ((Map)var5).put(var10.getAsShortText(var4).toUpperCase(var4), Boolean.TRUE);
               ((Map)var5).put(var10.getAsText(var4), Boolean.TRUE);
               ((Map)var5).put(var10.getAsText(var4).toLowerCase(var4), Boolean.TRUE);
               ((Map)var5).put(var10.getAsText(var4).toUpperCase(var4), Boolean.TRUE);
            }

            if ("en".equals(var4.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
               ((Map)var5).put("BCE", Boolean.TRUE);
               ((Map)var5).put("bce", Boolean.TRUE);
               ((Map)var5).put("CE", Boolean.TRUE);
               ((Map)var5).put("ce", Boolean.TRUE);
               var6 = 3;
            }

            var8 = new Object[]{var5, var6};
            ((Map)var7).put(this.iFieldType, var8);
         } else {
            var5 = (Map)var8[0];
            var6 = (Integer)var8[1];
         }

         int var17 = Math.min(var2.length(), var3 + var6);

         for(int var18 = var17; var18 > var3; --var18) {
            String var19 = var2.subSequence(var3, var18).toString();
            if (((Map)var5).containsKey(var19)) {
               var1.saveField(this.iFieldType, var19, var4);
               return var18;
            }
         }

         return ~var3;
      }
   }

   static class Fraction implements InternalPrinter, InternalParser {
      private final DateTimeFieldType iFieldType;
      protected int iMinDigits;
      protected int iMaxDigits;

      protected Fraction(DateTimeFieldType var1, int var2, int var3) {
         this.iFieldType = var1;
         if (var3 > 18) {
            var3 = 18;
         }

         this.iMinDigits = var2;
         this.iMaxDigits = var3;
      }

      public int estimatePrintedLength() {
         return this.iMaxDigits;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         this.printTo(var1, var2, var4);
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         long var4 = var2.getChronology().set(var2, 0L);
         this.printTo(var1, var4, var2.getChronology());
      }

      protected void printTo(Appendable var1, long var2, Chronology var4) throws IOException {
         DateTimeField var5 = this.iFieldType.getField(var4);
         int var6 = this.iMinDigits;

         long var7;
         try {
            var7 = var5.remainder(var2);
         } catch (RuntimeException var17) {
            DateTimeFormatterBuilder.appendUnknownString(var1, var6);
            return;
         }

         if (var7 == 0L) {
            while(true) {
               --var6;
               if (var6 < 0) {
                  return;
               }

               var1.append('0');
            }
         } else {
            long[] var10 = this.getFractionData(var7, var5);
            long var11 = var10[0];
            int var13 = (int)var10[1];
            String var9;
            if ((var11 & 2147483647L) == var11) {
               var9 = Integer.toString((int)var11);
            } else {
               var9 = Long.toString(var11);
            }

            int var14 = var9.length();

            int var15;
            for(var15 = var13; var14 < var15; --var15) {
               var1.append('0');
               --var6;
            }

            if (var6 < var15) {
               while(var6 < var15 && var14 > 1 && var9.charAt(var14 - 1) == '0') {
                  --var15;
                  --var14;
               }

               if (var14 < var9.length()) {
                  for(int var16 = 0; var16 < var14; ++var16) {
                     var1.append(var9.charAt(var16));
                  }

                  return;
               }
            }

            var1.append(var9);
         }
      }

      private long[] getFractionData(long var1, DateTimeField var3) {
         long var4 = var3.getDurationField().getUnitMillis();
         int var8 = this.iMaxDigits;

         while(true) {
            long var6;
            switch (var8) {
               case 1:
                  var6 = 10L;
                  break;
               case 2:
                  var6 = 100L;
                  break;
               case 3:
                  var6 = 1000L;
                  break;
               case 4:
                  var6 = 10000L;
                  break;
               case 5:
                  var6 = 100000L;
                  break;
               case 6:
                  var6 = 1000000L;
                  break;
               case 7:
                  var6 = 10000000L;
                  break;
               case 8:
                  var6 = 100000000L;
                  break;
               case 9:
                  var6 = 1000000000L;
                  break;
               case 10:
                  var6 = 10000000000L;
                  break;
               case 11:
                  var6 = 100000000000L;
                  break;
               case 12:
                  var6 = 1000000000000L;
                  break;
               case 13:
                  var6 = 10000000000000L;
                  break;
               case 14:
                  var6 = 100000000000000L;
                  break;
               case 15:
                  var6 = 1000000000000000L;
                  break;
               case 16:
                  var6 = 10000000000000000L;
                  break;
               case 17:
                  var6 = 100000000000000000L;
                  break;
               case 18:
                  var6 = 1000000000000000000L;
                  break;
               default:
                  var6 = 1L;
            }

            if (var4 * var6 / var6 == var4) {
               return new long[]{var1 * var6 / var4, (long)var8};
            }

            --var8;
         }
      }

      public int estimateParsedLength() {
         return this.iMaxDigits;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         DateTimeField var4 = this.iFieldType.getField(var1.getChronology());
         int var5 = Math.min(this.iMaxDigits, var2.length() - var3);
         long var6 = 0L;
         long var8 = var4.getDurationField().getUnitMillis() * 10L;

         int var10;
         long var12;
         for(var10 = 0; var10 < var5; var8 = var12) {
            char var11 = var2.charAt(var3 + var10);
            if (var11 < '0' || var11 > '9') {
               break;
            }

            ++var10;
            var12 = var8 / 10L;
            var6 += (long)(var11 - 48) * var12;
         }

         var6 /= 10L;
         if (var10 == 0) {
            return ~var3;
         } else if (var6 > 2147483647L) {
            return ~var3;
         } else {
            PreciseDateTimeField var15 = new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, var4.getDurationField());
            var1.saveField((DateTimeField)var15, (int)var6);
            return var3 + var10;
         }
      }
   }

   static class TimeZoneOffset implements InternalPrinter, InternalParser {
      private final String iZeroOffsetPrintText;
      private final String iZeroOffsetParseText;
      private final boolean iShowSeparators;
      private final int iMinFields;
      private final int iMaxFields;

      TimeZoneOffset(String var1, String var2, boolean var3, int var4, int var5) {
         this.iZeroOffsetPrintText = var1;
         this.iZeroOffsetParseText = var2;
         this.iShowSeparators = var3;
         if (var4 > 0 && var5 >= var4) {
            if (var4 > 4) {
               var4 = 4;
               var5 = 4;
            }

            this.iMinFields = var4;
            this.iMaxFields = var5;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public int estimatePrintedLength() {
         int var1 = 1 + this.iMinFields << 1;
         if (this.iShowSeparators) {
            var1 += this.iMinFields - 1;
         }

         if (this.iZeroOffsetPrintText != null && this.iZeroOffsetPrintText.length() > var1) {
            var1 = this.iZeroOffsetPrintText.length();
         }

         return var1;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         if (var6 != null) {
            if (var5 == 0 && this.iZeroOffsetPrintText != null) {
               var1.append(this.iZeroOffsetPrintText);
            } else {
               if (var5 >= 0) {
                  var1.append('+');
               } else {
                  var1.append('-');
                  var5 = -var5;
               }

               int var8 = var5 / 3600000;
               FormatUtils.appendPaddedInteger((Appendable)var1, var8, 2);
               if (this.iMaxFields != 1) {
                  var5 -= var8 * 3600000;
                  if (var5 != 0 || this.iMinFields > 1) {
                     int var9 = var5 / '\uea60';
                     if (this.iShowSeparators) {
                        var1.append(':');
                     }

                     FormatUtils.appendPaddedInteger((Appendable)var1, var9, 2);
                     if (this.iMaxFields != 2) {
                        var5 -= var9 * '\uea60';
                        if (var5 != 0 || this.iMinFields > 2) {
                           int var10 = var5 / 1000;
                           if (this.iShowSeparators) {
                              var1.append(':');
                           }

                           FormatUtils.appendPaddedInteger((Appendable)var1, var10, 2);
                           if (this.iMaxFields != 3) {
                              var5 -= var10 * 1000;
                              if (var5 != 0 || this.iMinFields > 3) {
                                 if (this.iShowSeparators) {
                                    var1.append('.');
                                 }

                                 FormatUtils.appendPaddedInteger((Appendable)var1, var5, 3);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
      }

      public int estimateParsedLength() {
         return this.estimatePrintedLength();
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         label138: {
            int var4 = var2.length() - var3;
            if (this.iZeroOffsetParseText != null) {
               if (this.iZeroOffsetParseText.length() == 0) {
                  if (var4 <= 0) {
                     break label138;
                  }

                  char var5 = var2.charAt(var3);
                  if (var5 != '-' && var5 != '+') {
                     break label138;
                  }
               } else if (DateTimeFormatterBuilder.csStartsWithIgnoreCase(var2, var3, this.iZeroOffsetParseText)) {
                  var1.setOffset(0);
                  return var3 + this.iZeroOffsetParseText.length();
               }
            }

            if (var4 <= 1) {
               return ~var3;
            }

            char var6 = var2.charAt(var3);
            boolean var20;
            if (var6 == '-') {
               var20 = true;
            } else {
               if (var6 != '+') {
                  return ~var3;
               }

               var20 = false;
            }

            --var4;
            ++var3;
            if (this.digitCount(var2, var3, 2) < 2) {
               return ~var3;
            }

            int var8 = FormatUtils.parseTwoDigits(var2, var3);
            if (var8 > 23) {
               return ~var3;
            }

            int var7 = var8 * 3600000;
            var4 -= 2;
            var3 += 2;
            if (var4 > 0) {
               label139: {
                  var6 = var2.charAt(var3);
                  boolean var9;
                  if (var6 == ':') {
                     var9 = true;
                     --var4;
                     ++var3;
                  } else {
                     if (var6 < '0' || var6 > '9') {
                        break label139;
                     }

                     var9 = false;
                  }

                  int var10 = this.digitCount(var2, var3, 2);
                  if (var10 != 0 || var9) {
                     if (var10 < 2) {
                        return ~var3;
                     }

                     int var11 = FormatUtils.parseTwoDigits(var2, var3);
                     if (var11 > 59) {
                        return ~var3;
                     }

                     var7 += var11 * '\uea60';
                     var4 -= 2;
                     var3 += 2;
                     if (var4 > 0) {
                        label141: {
                           if (var9) {
                              if (var2.charAt(var3) != ':') {
                                 break label141;
                              }

                              --var4;
                              ++var3;
                           }

                           var10 = this.digitCount(var2, var3, 2);
                           if (var10 != 0 || var9) {
                              if (var10 < 2) {
                                 return ~var3;
                              }

                              int var12 = FormatUtils.parseTwoDigits(var2, var3);
                              if (var12 > 59) {
                                 return ~var3;
                              }

                              var7 += var12 * 1000;
                              var4 -= 2;
                              var3 += 2;
                              if (var4 > 0) {
                                 label142: {
                                    if (var9) {
                                       if (var2.charAt(var3) != '.' && var2.charAt(var3) != ',') {
                                          break label142;
                                       }

                                       --var4;
                                       ++var3;
                                    }

                                    var10 = this.digitCount(var2, var3, 3);
                                    if (var10 != 0 || var9) {
                                       if (var10 < 1) {
                                          return ~var3;
                                       }

                                       var7 += (var2.charAt(var3++) - 48) * 100;
                                       if (var10 > 1) {
                                          var7 += (var2.charAt(var3++) - 48) * 10;
                                          if (var10 > 2) {
                                             var7 += var2.charAt(var3++) - 48;
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            var1.setOffset(var20 ? -var7 : var7);
            return var3;
         }

         var1.setOffset(0);
         return var3;
      }

      private int digitCount(CharSequence var1, int var2, int var3) {
         int var4 = Math.min(var1.length() - var2, var3);

         for(var3 = 0; var4 > 0; --var4) {
            char var5 = var1.charAt(var2 + var3);
            if (var5 < '0' || var5 > '9') {
               break;
            }

            ++var3;
         }

         return var3;
      }
   }

   static class TimeZoneName implements InternalPrinter, InternalParser {
      static final int LONG_NAME = 0;
      static final int SHORT_NAME = 1;
      private final Map iParseLookup;
      private final int iType;

      TimeZoneName(int var1, Map var2) {
         this.iType = var1;
         this.iParseLookup = var2;
      }

      public int estimatePrintedLength() {
         return this.iType == 1 ? 4 : 20;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         var1.append(this.print(var2 - (long)var5, var6, var7));
      }

      private String print(long var1, DateTimeZone var3, Locale var4) {
         if (var3 == null) {
            return "";
         } else {
            switch (this.iType) {
               case 0:
                  return var3.getName(var1, var4);
               case 1:
                  return var3.getShortName(var1, var4);
               default:
                  return "";
            }
         }
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
      }

      public int estimateParsedLength() {
         return this.iType == 1 ? 4 : 20;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         Map var4 = this.iParseLookup;
         var4 = var4 != null ? var4 : DateTimeUtils.getDefaultTimeZoneNames();
         String var5 = null;

         for(String var7 : var4.keySet()) {
            if (DateTimeFormatterBuilder.csStartsWith(var2, var3, var7) && (var5 == null || var7.length() > var5.length())) {
               var5 = var7;
            }
         }

         if (var5 != null) {
            var1.setZone((DateTimeZone)var4.get(var5));
            return var3 + var5.length();
         } else {
            return ~var3;
         }
      }
   }

   static enum TimeZoneId implements InternalPrinter, InternalParser {
      INSTANCE;

      private static final List ALL_IDS = new ArrayList(DateTimeZone.getAvailableIDs());
      private static final Map GROUPED_IDS;
      private static final List BASE_GROUPED_IDS = new ArrayList();
      static final int MAX_LENGTH;
      static final int MAX_PREFIX_LENGTH;

      public int estimatePrintedLength() {
         return MAX_LENGTH;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         var1.append(var6 != null ? var6.getID() : "");
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
      }

      public int estimateParsedLength() {
         return MAX_LENGTH;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         List var4 = BASE_GROUPED_IDS;
         int var5 = var2.length();
         int var6 = Math.min(var5, var3 + MAX_PREFIX_LENGTH);
         int var7 = var3;
         String var8 = "";

         for(int var9 = var3; var9 < var6; ++var9) {
            if (var2.charAt(var9) == '/') {
               var8 = var2.subSequence(var3, var9 + 1).toString();
               var7 = var3 + var8.length();
               String var10 = var8;
               if (var9 < var5 - 1) {
                  var10 = var8 + var2.charAt(var9 + 1);
               }

               var4 = (List)GROUPED_IDS.get(var10);
               if (var4 == null) {
                  return ~var3;
               }
               break;
            }
         }

         String var12 = null;

         for(int var13 = 0; var13 < var4.size(); ++var13) {
            String var11 = (String)var4.get(var13);
            if (DateTimeFormatterBuilder.csStartsWith(var2, var7, var11) && (var12 == null || var11.length() > var12.length())) {
               var12 = var11;
            }
         }

         if (var12 != null) {
            var1.setZone(DateTimeZone.forID(var8 + var12));
            return var7 + var12.length();
         } else {
            return ~var3;
         }
      }

      static {
         Collections.sort(ALL_IDS);
         GROUPED_IDS = new HashMap();
         int var0 = 0;
         int var1 = 0;

         for(String var3 : ALL_IDS) {
            int var4 = var3.indexOf(47);
            if (var4 >= 0) {
               if (var4 < var3.length()) {
                  ++var4;
               }

               var1 = Math.max(var1, var4);
               String var5 = var3.substring(0, var4 + 1);
               String var6 = var3.substring(var4);
               if (!GROUPED_IDS.containsKey(var5)) {
                  GROUPED_IDS.put(var5, new ArrayList());
               }

               ((List)GROUPED_IDS.get(var5)).add(var6);
            } else {
               BASE_GROUPED_IDS.add(var3);
            }

            var0 = Math.max(var0, var3.length());
         }

         MAX_LENGTH = var0;
         MAX_PREFIX_LENGTH = var1;
      }
   }

   static class Composite implements InternalPrinter, InternalParser {
      private final InternalPrinter[] iPrinters;
      private final InternalParser[] iParsers;
      private final int iPrintedLengthEstimate;
      private final int iParsedLengthEstimate;

      Composite(List var1) {
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         this.decompose(var1, var2, var3);
         if (!var2.contains((Object)null) && !var2.isEmpty()) {
            int var4 = var2.size();
            this.iPrinters = new InternalPrinter[var4];
            int var5 = 0;

            for(int var6 = 0; var6 < var4; ++var6) {
               InternalPrinter var7 = (InternalPrinter)var2.get(var6);
               var5 += var7.estimatePrintedLength();
               this.iPrinters[var6] = var7;
            }

            this.iPrintedLengthEstimate = var5;
         } else {
            this.iPrinters = null;
            this.iPrintedLengthEstimate = 0;
         }

         if (!var3.contains((Object)null) && !var3.isEmpty()) {
            int var8 = var3.size();
            this.iParsers = new InternalParser[var8];
            int var9 = 0;

            for(int var10 = 0; var10 < var8; ++var10) {
               InternalParser var11 = (InternalParser)var3.get(var10);
               var9 += var11.estimateParsedLength();
               this.iParsers[var10] = var11;
            }

            this.iParsedLengthEstimate = var9;
         } else {
            this.iParsers = null;
            this.iParsedLengthEstimate = 0;
         }

      }

      public int estimatePrintedLength() {
         return this.iPrintedLengthEstimate;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         InternalPrinter[] var8 = this.iPrinters;
         if (var8 == null) {
            throw new UnsupportedOperationException();
         } else {
            if (var7 == null) {
               var7 = Locale.getDefault();
            }

            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               var8[var10].printTo(var1, var2, var4, var5, var6, var7);
            }

         }
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         InternalPrinter[] var4 = this.iPrinters;
         if (var4 == null) {
            throw new UnsupportedOperationException();
         } else {
            if (var3 == null) {
               var3 = Locale.getDefault();
            }

            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               var4[var6].printTo(var1, var2, var3);
            }

         }
      }

      public int estimateParsedLength() {
         return this.iParsedLengthEstimate;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         InternalParser[] var4 = this.iParsers;
         if (var4 == null) {
            throw new UnsupportedOperationException();
         } else {
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5 && var3 >= 0; ++var6) {
               var3 = var4[var6].parseInto(var1, var2, var3);
            }

            return var3;
         }
      }

      boolean isPrinter() {
         return this.iPrinters != null;
      }

      boolean isParser() {
         return this.iParsers != null;
      }

      private void decompose(List var1, List var2, List var3) {
         int var4 = var1.size();

         for(int var5 = 0; var5 < var4; var5 += 2) {
            Object var6 = var1.get(var5);
            if (var6 instanceof Composite) {
               this.addArrayToList(var2, ((Composite)var6).iPrinters);
            } else {
               var2.add(var6);
            }

            var6 = var1.get(var5 + 1);
            if (var6 instanceof Composite) {
               this.addArrayToList(var3, ((Composite)var6).iParsers);
            } else {
               var3.add(var6);
            }
         }

      }

      private void addArrayToList(List var1, Object[] var2) {
         if (var2 != null) {
            for(int var3 = 0; var3 < var2.length; ++var3) {
               var1.add(var2[var3]);
            }
         }

      }
   }

   static class MatchingParser implements InternalParser {
      private final InternalParser[] iParsers;
      private final int iParsedLengthEstimate;

      MatchingParser(InternalParser[] var1) {
         this.iParsers = var1;
         int var2 = 0;
         int var3 = var1.length;

         while(true) {
            --var3;
            if (var3 < 0) {
               this.iParsedLengthEstimate = var2;
               return;
            }

            InternalParser var4 = var1[var3];
            if (var4 != null) {
               int var5 = var4.estimateParsedLength();
               if (var5 > var2) {
                  var2 = var5;
               }
            }
         }
      }

      public int estimateParsedLength() {
         return this.iParsedLengthEstimate;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         InternalParser[] var4 = this.iParsers;
         int var5 = var4.length;
         Object var6 = var1.saveState();
         boolean var7 = false;
         int var8 = var3;
         Object var9 = null;
         int var10 = var3;
         int var11 = 0;

         while(true) {
            if (var11 < var5) {
               InternalParser var12 = var4[var11];
               if (var12 != null) {
                  int var13 = var12.parseInto(var1, var2, var3);
                  if (var13 >= var3) {
                     if (var13 > var8) {
                        if (var13 >= var2.length() || var11 + 1 >= var5 || var4[var11 + 1] == null) {
                           return var13;
                        }

                        var8 = var13;
                        var9 = var1.saveState();
                     }
                  } else if (var13 < 0) {
                     var13 = ~var13;
                     if (var13 > var10) {
                        var10 = var13;
                     }
                  }

                  var1.restoreState(var6);
                  ++var11;
                  continue;
               }

               if (var8 <= var3) {
                  return var3;
               }

               var7 = true;
            }

            if (var8 > var3 || var8 == var3 && var7) {
               if (var9 != null) {
                  var1.restoreState(var9);
               }

               return var8;
            }

            return ~var10;
         }
      }
   }
}
