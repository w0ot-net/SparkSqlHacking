package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public class DateTimeFormatter {
   private final InternalPrinter iPrinter;
   private final InternalParser iParser;
   private final Locale iLocale;
   private final boolean iOffsetParsed;
   private final Chronology iChrono;
   private final DateTimeZone iZone;
   private final Integer iPivotYear;
   private final int iDefaultYear;

   public DateTimeFormatter(DateTimePrinter var1, DateTimeParser var2) {
      this(DateTimePrinterInternalPrinter.of(var1), DateTimeParserInternalParser.of(var2));
   }

   DateTimeFormatter(InternalPrinter var1, InternalParser var2) {
      this.iPrinter = var1;
      this.iParser = var2;
      this.iLocale = null;
      this.iOffsetParsed = false;
      this.iChrono = null;
      this.iZone = null;
      this.iPivotYear = null;
      this.iDefaultYear = 2000;
   }

   private DateTimeFormatter(InternalPrinter var1, InternalParser var2, Locale var3, boolean var4, Chronology var5, DateTimeZone var6, Integer var7, int var8) {
      this.iPrinter = var1;
      this.iParser = var2;
      this.iLocale = var3;
      this.iOffsetParsed = var4;
      this.iChrono = var5;
      this.iZone = var6;
      this.iPivotYear = var7;
      this.iDefaultYear = var8;
   }

   public boolean isPrinter() {
      return this.iPrinter != null;
   }

   public DateTimePrinter getPrinter() {
      return InternalPrinterDateTimePrinter.of(this.iPrinter);
   }

   InternalPrinter getPrinter0() {
      return this.iPrinter;
   }

   public boolean isParser() {
      return this.iParser != null;
   }

   public DateTimeParser getParser() {
      return InternalParserDateTimeParser.of(this.iParser);
   }

   InternalParser getParser0() {
      return this.iParser;
   }

   public DateTimeFormatter withLocale(Locale var1) {
      return var1 != this.getLocale() && (var1 == null || !var1.equals(this.getLocale())) ? new DateTimeFormatter(this.iPrinter, this.iParser, var1, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, this.iDefaultYear) : this;
   }

   public Locale getLocale() {
      return this.iLocale;
   }

   public DateTimeFormatter withOffsetParsed() {
      return this.iOffsetParsed ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, (DateTimeZone)null, this.iPivotYear, this.iDefaultYear);
   }

   public boolean isOffsetParsed() {
      return this.iOffsetParsed;
   }

   public DateTimeFormatter withChronology(Chronology var1) {
      return this.iChrono == var1 ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, var1, this.iZone, this.iPivotYear, this.iDefaultYear);
   }

   public Chronology getChronology() {
      return this.iChrono;
   }

   /** @deprecated */
   @Deprecated
   public Chronology getChronolgy() {
      return this.iChrono;
   }

   public DateTimeFormatter withZoneUTC() {
      return this.withZone(DateTimeZone.UTC);
   }

   public DateTimeFormatter withZone(DateTimeZone var1) {
      return this.iZone == var1 ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, var1, this.iPivotYear, this.iDefaultYear);
   }

   public DateTimeZone getZone() {
      return this.iZone;
   }

   public DateTimeFormatter withPivotYear(Integer var1) {
      long var2 = this.iPivotYear == null ? Long.MIN_VALUE : (long)this.iPivotYear;
      long var4 = var1 == null ? Long.MIN_VALUE : (long)var1;
      return var2 == var4 ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, var1, this.iDefaultYear);
   }

   public DateTimeFormatter withPivotYear(int var1) {
      return this.withPivotYear(var1);
   }

   public Integer getPivotYear() {
      return this.iPivotYear;
   }

   public DateTimeFormatter withDefaultYear(int var1) {
      return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, var1);
   }

   public int getDefaultYear() {
      return this.iDefaultYear;
   }

   public void printTo(StringBuffer var1, ReadableInstant var2) {
      try {
         this.printTo((Appendable)var1, (ReadableInstant)var2);
      } catch (IOException var4) {
      }

   }

   public void printTo(StringBuilder var1, ReadableInstant var2) {
      try {
         this.printTo((Appendable)var1, (ReadableInstant)var2);
      } catch (IOException var4) {
      }

   }

   public void printTo(Writer var1, ReadableInstant var2) throws IOException {
      this.printTo((Appendable)var1, (ReadableInstant)var2);
   }

   public void printTo(Appendable var1, ReadableInstant var2) throws IOException {
      long var3 = DateTimeUtils.getInstantMillis(var2);
      Chronology var5 = DateTimeUtils.getInstantChronology(var2);
      this.printTo(var1, var3, var5);
   }

   public void printTo(StringBuffer var1, long var2) {
      try {
         this.printTo((Appendable)var1, var2);
      } catch (IOException var5) {
      }

   }

   public void printTo(StringBuilder var1, long var2) {
      try {
         this.printTo((Appendable)var1, var2);
      } catch (IOException var5) {
      }

   }

   public void printTo(Writer var1, long var2) throws IOException {
      this.printTo((Appendable)var1, var2);
   }

   public void printTo(Appendable var1, long var2) throws IOException {
      this.printTo(var1, var2, (Chronology)null);
   }

   public void printTo(StringBuffer var1, ReadablePartial var2) {
      try {
         this.printTo((Appendable)var1, (ReadablePartial)var2);
      } catch (IOException var4) {
      }

   }

   public void printTo(StringBuilder var1, ReadablePartial var2) {
      try {
         this.printTo((Appendable)var1, (ReadablePartial)var2);
      } catch (IOException var4) {
      }

   }

   public void printTo(Writer var1, ReadablePartial var2) throws IOException {
      this.printTo((Appendable)var1, (ReadablePartial)var2);
   }

   public void printTo(Appendable var1, ReadablePartial var2) throws IOException {
      InternalPrinter var3 = this.requirePrinter();
      if (var2 == null) {
         throw new IllegalArgumentException("The partial must not be null");
      } else {
         var3.printTo(var1, var2, this.iLocale);
      }
   }

   public String print(ReadableInstant var1) {
      StringBuilder var2 = new StringBuilder(this.requirePrinter().estimatePrintedLength());

      try {
         this.printTo((Appendable)var2, (ReadableInstant)var1);
      } catch (IOException var4) {
      }

      return var2.toString();
   }

   public String print(long var1) {
      StringBuilder var3 = new StringBuilder(this.requirePrinter().estimatePrintedLength());

      try {
         this.printTo((Appendable)var3, var1);
      } catch (IOException var5) {
      }

      return var3.toString();
   }

   public String print(ReadablePartial var1) {
      StringBuilder var2 = new StringBuilder(this.requirePrinter().estimatePrintedLength());

      try {
         this.printTo((Appendable)var2, (ReadablePartial)var1);
      } catch (IOException var4) {
      }

      return var2.toString();
   }

   private void printTo(Appendable var1, long var2, Chronology var4) throws IOException {
      InternalPrinter var5 = this.requirePrinter();
      var4 = this.selectChronology(var4);
      DateTimeZone var6 = var4.getZone();
      int var7 = var6.getOffset(var2);
      long var8 = var2 + (long)var7;
      if ((var2 ^ var8) < 0L && (var2 ^ (long)var7) >= 0L) {
         var6 = DateTimeZone.UTC;
         var7 = 0;
         var8 = var2;
      }

      var5.printTo(var1, var8, var4.withUTC(), var7, var6, this.iLocale);
   }

   private InternalPrinter requirePrinter() {
      InternalPrinter var1 = this.iPrinter;
      if (var1 == null) {
         throw new UnsupportedOperationException("Printing not supported");
      } else {
         return var1;
      }
   }

   public int parseInto(ReadWritableInstant var1, String var2, int var3) {
      InternalParser var4 = this.requireParser();
      if (var1 == null) {
         throw new IllegalArgumentException("Instant must not be null");
      } else {
         long var5 = var1.getMillis();
         Chronology var7 = var1.getChronology();
         int var8 = DateTimeUtils.getChronology(var7).year().get(var5);
         long var9 = var5 + (long)var7.getZone().getOffset(var5);
         var7 = this.selectChronology(var7);
         DateTimeParserBucket var11 = new DateTimeParserBucket(var9, var7, this.iLocale, this.iPivotYear, var8);
         int var12 = var4.parseInto(var11, var2, var3);
         var1.setMillis(var11.computeMillis(false, var2));
         if (this.iOffsetParsed && var11.getOffsetInteger() != null) {
            int var13 = var11.getOffsetInteger();
            DateTimeZone var14 = DateTimeZone.forOffsetMillis(var13);
            var7 = var7.withZone(var14);
         } else if (var11.getZone() != null) {
            var7 = var7.withZone(var11.getZone());
         }

         var1.setChronology(var7);
         if (this.iZone != null) {
            var1.setZone(this.iZone);
         }

         return var12;
      }
   }

   public long parseMillis(String var1) {
      InternalParser var2 = this.requireParser();
      Chronology var3 = this.selectChronology(this.iChrono);
      DateTimeParserBucket var4 = new DateTimeParserBucket(0L, var3, this.iLocale, this.iPivotYear, this.iDefaultYear);
      return var4.doParseMillis(var2, var1);
   }

   public LocalDate parseLocalDate(String var1) {
      return this.parseLocalDateTime(var1).toLocalDate();
   }

   public LocalTime parseLocalTime(String var1) {
      return this.parseLocalDateTime(var1).toLocalTime();
   }

   public LocalDateTime parseLocalDateTime(String var1) {
      InternalParser var2 = this.requireParser();
      Chronology var3 = this.selectChronology((Chronology)null).withUTC();
      DateTimeParserBucket var4 = new DateTimeParserBucket(0L, var3, this.iLocale, this.iPivotYear, this.iDefaultYear);
      int var5 = var2.parseInto(var4, var1, 0);
      if (var5 >= 0) {
         if (var5 >= var1.length()) {
            long var6 = var4.computeMillis(true, var1);
            if (var4.getOffsetInteger() != null) {
               int var8 = var4.getOffsetInteger();
               DateTimeZone var9 = DateTimeZone.forOffsetMillis(var8);
               var3 = var3.withZone(var9);
            } else if (var4.getZone() != null) {
               var3 = var3.withZone(var4.getZone());
            }

            return new LocalDateTime(var6, var3);
         }
      } else {
         var5 = ~var5;
      }

      throw new IllegalArgumentException(FormatUtils.createErrorMessage(var1, var5));
   }

   public DateTime parseDateTime(String var1) {
      InternalParser var2 = this.requireParser();
      Chronology var3 = this.selectChronology((Chronology)null);
      DateTimeParserBucket var4 = new DateTimeParserBucket(0L, var3, this.iLocale, this.iPivotYear, this.iDefaultYear);
      int var5 = var2.parseInto(var4, var1, 0);
      if (var5 >= 0) {
         if (var5 >= var1.length()) {
            long var6 = var4.computeMillis(true, var1);
            if (this.iOffsetParsed && var4.getOffsetInteger() != null) {
               int var8 = var4.getOffsetInteger();
               DateTimeZone var9 = DateTimeZone.forOffsetMillis(var8);
               var3 = var3.withZone(var9);
            } else if (var4.getZone() != null) {
               var3 = var3.withZone(var4.getZone());
            }

            DateTime var10 = new DateTime(var6, var3);
            if (this.iZone != null) {
               var10 = var10.withZone(this.iZone);
            }

            return var10;
         }
      } else {
         var5 = ~var5;
      }

      throw new IllegalArgumentException(FormatUtils.createErrorMessage(var1, var5));
   }

   public MutableDateTime parseMutableDateTime(String var1) {
      InternalParser var2 = this.requireParser();
      Chronology var3 = this.selectChronology((Chronology)null);
      DateTimeParserBucket var4 = new DateTimeParserBucket(0L, var3, this.iLocale, this.iPivotYear, this.iDefaultYear);
      int var5 = var2.parseInto(var4, var1, 0);
      if (var5 >= 0) {
         if (var5 >= var1.length()) {
            long var6 = var4.computeMillis(true, var1);
            if (this.iOffsetParsed && var4.getOffsetInteger() != null) {
               int var8 = var4.getOffsetInteger();
               DateTimeZone var9 = DateTimeZone.forOffsetMillis(var8);
               var3 = var3.withZone(var9);
            } else if (var4.getZone() != null) {
               var3 = var3.withZone(var4.getZone());
            }

            MutableDateTime var10 = new MutableDateTime(var6, var3);
            if (this.iZone != null) {
               var10.setZone(this.iZone);
            }

            return var10;
         }
      } else {
         var5 = ~var5;
      }

      throw new IllegalArgumentException(FormatUtils.createErrorMessage(var1, var5));
   }

   private InternalParser requireParser() {
      InternalParser var1 = this.iParser;
      if (var1 == null) {
         throw new UnsupportedOperationException("Parsing not supported");
      } else {
         return var1;
      }
   }

   private Chronology selectChronology(Chronology var1) {
      var1 = DateTimeUtils.getChronology(var1);
      if (this.iChrono != null) {
         var1 = this.iChrono;
      }

      if (this.iZone != null) {
         var1 = var1.withZone(this.iZone);
      }

      return var1;
   }
}
