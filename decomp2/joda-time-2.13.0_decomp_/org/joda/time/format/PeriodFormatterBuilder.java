package org.joda.time.format;

import [Ljava.lang.String;;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatterBuilder {
   private static final int PRINT_ZERO_RARELY_FIRST = 1;
   private static final int PRINT_ZERO_RARELY_LAST = 2;
   private static final int PRINT_ZERO_IF_SUPPORTED = 3;
   private static final int PRINT_ZERO_ALWAYS = 4;
   private static final int PRINT_ZERO_NEVER = 5;
   private static final int YEARS = 0;
   private static final int MONTHS = 1;
   private static final int WEEKS = 2;
   private static final int DAYS = 3;
   private static final int HOURS = 4;
   private static final int MINUTES = 5;
   private static final int SECONDS = 6;
   private static final int MILLIS = 7;
   private static final int SECONDS_MILLIS = 8;
   private static final int SECONDS_OPTIONAL_MILLIS = 9;
   private static final int MAX_FIELD = 9;
   private static final ConcurrentMap PATTERNS = new ConcurrentHashMap();
   private int iMinPrintedDigits;
   private int iPrintZeroSetting;
   private int iMaxParsedDigits;
   private boolean iRejectSignedValues;
   private PeriodFieldAffix iPrefix;
   private List iElementPairs;
   private boolean iNotPrinter;
   private boolean iNotParser;
   private FieldFormatter[] iFieldFormatters;

   public PeriodFormatterBuilder() {
      this.clear();
   }

   public PeriodFormatter toFormatter() {
      PeriodFormatter var1 = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);

      for(FieldFormatter var5 : this.iFieldFormatters) {
         if (var5 != null) {
            var5.finish(this.iFieldFormatters);
         }
      }

      this.iFieldFormatters = (FieldFormatter[])this.iFieldFormatters.clone();
      return var1;
   }

   public PeriodPrinter toPrinter() {
      return this.iNotPrinter ? null : this.toFormatter().getPrinter();
   }

   public PeriodParser toParser() {
      return this.iNotParser ? null : this.toFormatter().getParser();
   }

   public void clear() {
      this.iMinPrintedDigits = 1;
      this.iPrintZeroSetting = 2;
      this.iMaxParsedDigits = 10;
      this.iRejectSignedValues = false;
      this.iPrefix = null;
      if (this.iElementPairs == null) {
         this.iElementPairs = new ArrayList();
      } else {
         this.iElementPairs.clear();
      }

      this.iNotPrinter = false;
      this.iNotParser = false;
      this.iFieldFormatters = new FieldFormatter[10];
   }

   public PeriodFormatterBuilder append(PeriodFormatter var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("No formatter supplied");
      } else {
         this.clearPrefix();
         this.append0(var1.getPrinter(), var1.getParser());
         return this;
      }
   }

   public PeriodFormatterBuilder append(PeriodPrinter var1, PeriodParser var2) {
      if (var1 == null && var2 == null) {
         throw new IllegalArgumentException("No printer or parser supplied");
      } else {
         this.clearPrefix();
         this.append0(var1, var2);
         return this;
      }
   }

   public PeriodFormatterBuilder appendLiteral(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Literal must not be null");
      } else {
         this.clearPrefix();
         Literal var2 = new Literal(var1);
         this.append0(var2, var2);
         return this;
      }
   }

   public PeriodFormatterBuilder minimumPrintedDigits(int var1) {
      this.iMinPrintedDigits = var1;
      return this;
   }

   public PeriodFormatterBuilder maximumParsedDigits(int var1) {
      this.iMaxParsedDigits = var1;
      return this;
   }

   public PeriodFormatterBuilder rejectSignedValues(boolean var1) {
      this.iRejectSignedValues = var1;
      return this;
   }

   public PeriodFormatterBuilder printZeroRarelyLast() {
      this.iPrintZeroSetting = 2;
      return this;
   }

   public PeriodFormatterBuilder printZeroRarelyFirst() {
      this.iPrintZeroSetting = 1;
      return this;
   }

   public PeriodFormatterBuilder printZeroIfSupported() {
      this.iPrintZeroSetting = 3;
      return this;
   }

   public PeriodFormatterBuilder printZeroAlways() {
      this.iPrintZeroSetting = 4;
      return this;
   }

   public PeriodFormatterBuilder printZeroNever() {
      this.iPrintZeroSetting = 5;
      return this;
   }

   public PeriodFormatterBuilder appendPrefix(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.appendPrefix((PeriodFieldAffix)(new SimpleAffix(var1)));
      }
   }

   public PeriodFormatterBuilder appendPrefix(String var1, String var2) {
      if (var1 != null && var2 != null) {
         return this.appendPrefix((PeriodFieldAffix)(new PluralAffix(var1, var2)));
      } else {
         throw new IllegalArgumentException();
      }
   }

   public PeriodFormatterBuilder appendPrefix(String[] var1, String[] var2) {
      if (var1 != null && var2 != null && var1.length >= 1 && var1.length == var2.length) {
         return this.appendPrefix((PeriodFieldAffix)(new RegExAffix(var1, var2)));
      } else {
         throw new IllegalArgumentException();
      }
   }

   private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.iPrefix != null) {
            var1 = new CompositeAffix(this.iPrefix, (PeriodFieldAffix)var1);
         }

         this.iPrefix = (PeriodFieldAffix)var1;
         return this;
      }
   }

   public PeriodFormatterBuilder appendYears() {
      this.appendField(0);
      return this;
   }

   public PeriodFormatterBuilder appendMonths() {
      this.appendField(1);
      return this;
   }

   public PeriodFormatterBuilder appendWeeks() {
      this.appendField(2);
      return this;
   }

   public PeriodFormatterBuilder appendDays() {
      this.appendField(3);
      return this;
   }

   public PeriodFormatterBuilder appendHours() {
      this.appendField(4);
      return this;
   }

   public PeriodFormatterBuilder appendMinutes() {
      this.appendField(5);
      return this;
   }

   public PeriodFormatterBuilder appendSeconds() {
      this.appendField(6);
      return this;
   }

   public PeriodFormatterBuilder appendSecondsWithMillis() {
      this.appendField(8);
      return this;
   }

   public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
      this.appendField(9);
      return this;
   }

   public PeriodFormatterBuilder appendMillis() {
      this.appendField(7);
      return this;
   }

   public PeriodFormatterBuilder appendMillis3Digit() {
      this.appendField(7, 3);
      return this;
   }

   private void appendField(int var1) {
      this.appendField(var1, this.iMinPrintedDigits);
   }

   private void appendField(int var1, int var2) {
      FieldFormatter var3 = new FieldFormatter(var2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, var1, this.iFieldFormatters, this.iPrefix, (PeriodFieldAffix)null);
      this.append0(var3, var3);
      this.iFieldFormatters[var1] = var3;
      this.iPrefix = null;
   }

   public PeriodFormatterBuilder appendSuffix(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.appendSuffix((PeriodFieldAffix)(new SimpleAffix(var1)));
      }
   }

   public PeriodFormatterBuilder appendSuffix(String var1, String var2) {
      if (var1 != null && var2 != null) {
         return this.appendSuffix((PeriodFieldAffix)(new PluralAffix(var1, var2)));
      } else {
         throw new IllegalArgumentException();
      }
   }

   public PeriodFormatterBuilder appendSuffix(String[] var1, String[] var2) {
      if (var1 != null && var2 != null && var1.length >= 1 && var1.length == var2.length) {
         return this.appendSuffix((PeriodFieldAffix)(new RegExAffix(var1, var2)));
      } else {
         throw new IllegalArgumentException();
      }
   }

   private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix var1) {
      Object var2;
      Object var3;
      if (this.iElementPairs.size() > 0) {
         var2 = this.iElementPairs.get(this.iElementPairs.size() - 2);
         var3 = this.iElementPairs.get(this.iElementPairs.size() - 1);
      } else {
         var2 = null;
         var3 = null;
      }

      if (var2 != null && var3 != null && var2 == var3 && var2 instanceof FieldFormatter) {
         this.clearPrefix();
         FieldFormatter var4 = new FieldFormatter((FieldFormatter)var2, var1);
         this.iElementPairs.set(this.iElementPairs.size() - 2, var4);
         this.iElementPairs.set(this.iElementPairs.size() - 1, var4);
         this.iFieldFormatters[var4.getFieldType()] = var4;
         return this;
      } else {
         throw new IllegalStateException("No field to apply suffix to");
      }
   }

   public PeriodFormatterBuilder appendSeparator(String var1) {
      return this.appendSeparator(var1, var1, (String[])null, true, true);
   }

   public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String var1) {
      return this.appendSeparator(var1, var1, (String[])null, false, true);
   }

   public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String var1) {
      return this.appendSeparator(var1, var1, (String[])null, true, false);
   }

   public PeriodFormatterBuilder appendSeparator(String var1, String var2) {
      return this.appendSeparator(var1, var2, (String[])null, true, true);
   }

   public PeriodFormatterBuilder appendSeparator(String var1, String var2, String[] var3) {
      return this.appendSeparator(var1, var2, var3, true, true);
   }

   private PeriodFormatterBuilder appendSeparator(String var1, String var2, String[] var3, boolean var4, boolean var5) {
      if (var1 != null && var2 != null) {
         this.clearPrefix();
         List var6 = this.iElementPairs;
         if (var6.size() == 0) {
            if (var5 && !var4) {
               Separator var12 = new Separator(var1, var2, var3, PeriodFormatterBuilder.Literal.EMPTY, PeriodFormatterBuilder.Literal.EMPTY, var4, var5);
               this.append0(var12, var12);
            }

            return this;
         } else {
            Separator var8 = null;
            int var7 = var6.size();

            while(true) {
               --var7;
               if (var7 < 0) {
                  break;
               }

               if (var6.get(var7) instanceof Separator) {
                  var8 = (Separator)var6.get(var7);
                  var6 = var6.subList(var7 + 1, var6.size());
                  break;
               }

               --var7;
            }

            if (var8 != null && var6.size() == 0) {
               throw new IllegalStateException("Cannot have two adjacent separators");
            } else {
               Object[] var9 = createComposite(var6);
               var6.clear();
               Separator var10 = new Separator(var1, var2, var3, (PeriodPrinter)var9[0], (PeriodParser)var9[1], var4, var5);
               var6.add(var10);
               var6.add(var10);
               return this;
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   private void clearPrefix() throws IllegalStateException {
      if (this.iPrefix != null) {
         throw new IllegalStateException("Prefix not followed by field");
      } else {
         this.iPrefix = null;
      }
   }

   private PeriodFormatterBuilder append0(PeriodPrinter var1, PeriodParser var2) {
      this.iElementPairs.add(var1);
      this.iElementPairs.add(var2);
      this.iNotPrinter |= var1 == null;
      this.iNotParser |= var2 == null;
      return this;
   }

   private static PeriodFormatter toFormatter(List var0, boolean var1, boolean var2) {
      if (var1 && var2) {
         throw new IllegalStateException("Builder has created neither a printer nor a parser");
      } else {
         int var3 = var0.size();
         if (var3 >= 2 && var0.get(0) instanceof Separator) {
            Separator var4 = (Separator)var0.get(0);
            if (var4.iAfterParser == null && var4.iAfterPrinter == null) {
               PeriodFormatter var5 = toFormatter(var0.subList(2, var3), var1, var2);
               var4 = var4.finish(var5.getPrinter(), var5.getParser());
               return new PeriodFormatter(var4, var4);
            }
         }

         Object[] var6 = createComposite(var0);
         if (var1) {
            return new PeriodFormatter((PeriodPrinter)null, (PeriodParser)var6[1]);
         } else {
            return var2 ? new PeriodFormatter((PeriodPrinter)var6[0], (PeriodParser)null) : new PeriodFormatter((PeriodPrinter)var6[0], (PeriodParser)var6[1]);
         }
      }
   }

   private static Object[] createComposite(List var0) {
      switch (var0.size()) {
         case 0:
            return new Object[]{PeriodFormatterBuilder.Literal.EMPTY, PeriodFormatterBuilder.Literal.EMPTY};
         case 1:
            return new Object[]{var0.get(0), var0.get(1)};
         default:
            Composite var1 = new Composite(var0);
            return new Object[]{var1, var1};
      }
   }

   abstract static class IgnorableAffix implements PeriodFieldAffix {
      private volatile String[] iOtherAffixes;

      public void finish(Set var1) {
         if (this.iOtherAffixes == null) {
            int var2 = Integer.MAX_VALUE;
            String var3 = null;

            for(String var7 : this.getAffixes()) {
               if (var7.length() < var2) {
                  var2 = var7.length();
                  var3 = var7;
               }
            }

            HashSet var11 = new HashSet();

            for(PeriodFieldAffix var13 : var1) {
               if (var13 != null) {
                  for(String var10 : var13.getAffixes()) {
                     if (var10.length() > var2 || var10.equalsIgnoreCase(var3) && !var10.equals(var3)) {
                        var11.add(var10);
                     }
                  }
               }
            }

            this.iOtherAffixes = (String[])var11.toArray(new String[var11.size()]);
         }

      }

      protected boolean matchesOtherAffix(int var1, String var2, int var3) {
         if (this.iOtherAffixes != null) {
            for(String var7 : this.iOtherAffixes) {
               int var8 = var7.length();
               if (var1 < var8 && var2.regionMatches(true, var3, var7, 0, var8) || var1 == var8 && var2.regionMatches(false, var3, var7, 0, var8)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   static class SimpleAffix extends IgnorableAffix {
      private final String iText;

      SimpleAffix(String var1) {
         this.iText = var1;
      }

      public int calculatePrintedLength(int var1) {
         return this.iText.length();
      }

      public void printTo(StringBuffer var1, int var2) {
         var1.append(this.iText);
      }

      public void printTo(Writer var1, int var2) throws IOException {
         var1.write(this.iText);
      }

      public int parse(String var1, int var2) {
         String var3 = this.iText;
         int var4 = var3.length();
         return var1.regionMatches(true, var2, var3, 0, var4) && !this.matchesOtherAffix(var4, var1, var2) ? var2 + var4 : ~var2;
      }

      public int scan(String var1, int var2) {
         String var3 = this.iText;
         int var4 = var3.length();
         int var5 = var1.length();

         for(int var6 = var2; var6 < var5; ++var6) {
            if (var1.regionMatches(true, var6, var3, 0, var4) && !this.matchesOtherAffix(var4, var1, var6)) {
               return var6;
            }

            switch (var1.charAt(var6)) {
               case '+':
               case ',':
               case '-':
               case '.':
               case '0':
               case '1':
               case '2':
               case '3':
               case '4':
               case '5':
               case '6':
               case '7':
               case '8':
               case '9':
               case '/':
               default:
                  return ~var2;
            }
         }

         return ~var2;
      }

      public String[] getAffixes() {
         return new String[]{this.iText};
      }
   }

   static class PluralAffix extends IgnorableAffix {
      private final String iSingularText;
      private final String iPluralText;

      PluralAffix(String var1, String var2) {
         this.iSingularText = var1;
         this.iPluralText = var2;
      }

      public int calculatePrintedLength(int var1) {
         return (var1 == 1 ? this.iSingularText : this.iPluralText).length();
      }

      public void printTo(StringBuffer var1, int var2) {
         var1.append(var2 == 1 ? this.iSingularText : this.iPluralText);
      }

      public void printTo(Writer var1, int var2) throws IOException {
         var1.write(var2 == 1 ? this.iSingularText : this.iPluralText);
      }

      public int parse(String var1, int var2) {
         String var3 = this.iPluralText;
         String var4 = this.iSingularText;
         if (var3.length() < var4.length()) {
            String var5 = var3;
            var3 = var4;
            var4 = var5;
         }

         if (var1.regionMatches(true, var2, var3, 0, var3.length()) && !this.matchesOtherAffix(var3.length(), var1, var2)) {
            return var2 + var3.length();
         } else {
            return var1.regionMatches(true, var2, var4, 0, var4.length()) && !this.matchesOtherAffix(var4.length(), var1, var2) ? var2 + var4.length() : ~var2;
         }
      }

      public int scan(String var1, int var2) {
         String var3 = this.iPluralText;
         String var4 = this.iSingularText;
         if (var3.length() < var4.length()) {
            String var5 = var3;
            var3 = var4;
            var4 = var5;
         }

         int var9 = var3.length();
         int var6 = var4.length();
         int var7 = var1.length();

         for(int var8 = var2; var8 < var7; ++var8) {
            if (var1.regionMatches(true, var8, var3, 0, var9) && !this.matchesOtherAffix(var3.length(), var1, var8)) {
               return var8;
            }

            if (var1.regionMatches(true, var8, var4, 0, var6) && !this.matchesOtherAffix(var4.length(), var1, var8)) {
               return var8;
            }
         }

         return ~var2;
      }

      public String[] getAffixes() {
         return new String[]{this.iSingularText, this.iPluralText};
      }
   }

   static class RegExAffix extends IgnorableAffix {
      private static final Comparator LENGTH_DESC_COMPARATOR = new Comparator() {
         public int compare(String var1, String var2) {
            return var2.length() - var1.length();
         }
      };
      private final String[] iSuffixes;
      private final Pattern[] iPatterns;
      private final String[] iSuffixesSortedDescByLength;

      RegExAffix(String[] var1, String[] var2) {
         this.iSuffixes = (String[])((String;)var2).clone();
         this.iPatterns = new Pattern[var1.length];

         for(int var3 = 0; var3 < var1.length; ++var3) {
            Pattern var4 = (Pattern)PeriodFormatterBuilder.PATTERNS.get(var1[var3]);
            if (var4 == null) {
               var4 = Pattern.compile(var1[var3]);
               PeriodFormatterBuilder.PATTERNS.putIfAbsent(var1[var3], var4);
            }

            this.iPatterns[var3] = var4;
         }

         this.iSuffixesSortedDescByLength = (String[])this.iSuffixes.clone();
         Arrays.sort(this.iSuffixesSortedDescByLength, LENGTH_DESC_COMPARATOR);
      }

      private int selectSuffixIndex(int var1) {
         String var2 = String.valueOf(var1);

         for(int var3 = 0; var3 < this.iPatterns.length; ++var3) {
            if (this.iPatterns[var3].matcher(var2).matches()) {
               return var3;
            }
         }

         return this.iPatterns.length - 1;
      }

      public int calculatePrintedLength(int var1) {
         return this.iSuffixes[this.selectSuffixIndex(var1)].length();
      }

      public void printTo(StringBuffer var1, int var2) {
         var1.append(this.iSuffixes[this.selectSuffixIndex(var2)]);
      }

      public void printTo(Writer var1, int var2) throws IOException {
         var1.write(this.iSuffixes[this.selectSuffixIndex(var2)]);
      }

      public int parse(String var1, int var2) {
         for(String var6 : this.iSuffixesSortedDescByLength) {
            if (var1.regionMatches(true, var2, var6, 0, var6.length()) && !this.matchesOtherAffix(var6.length(), var1, var2)) {
               return var2 + var6.length();
            }
         }

         return ~var2;
      }

      public int scan(String var1, int var2) {
         int var3 = var1.length();

         for(int var4 = var2; var4 < var3; ++var4) {
            for(String var8 : this.iSuffixesSortedDescByLength) {
               if (var1.regionMatches(true, var4, var8, 0, var8.length()) && !this.matchesOtherAffix(var8.length(), var1, var4)) {
                  return var4;
               }
            }
         }

         return ~var2;
      }

      public String[] getAffixes() {
         return (String[])this.iSuffixes.clone();
      }
   }

   static class CompositeAffix extends IgnorableAffix {
      private final PeriodFieldAffix iLeft;
      private final PeriodFieldAffix iRight;
      private final String[] iLeftRightCombinations;

      CompositeAffix(PeriodFieldAffix var1, PeriodFieldAffix var2) {
         this.iLeft = var1;
         this.iRight = var2;
         HashSet var3 = new HashSet();

         for(String var7 : this.iLeft.getAffixes()) {
            for(String var11 : this.iRight.getAffixes()) {
               var3.add(var7 + var11);
            }
         }

         this.iLeftRightCombinations = (String[])var3.toArray(new String[var3.size()]);
      }

      public int calculatePrintedLength(int var1) {
         return this.iLeft.calculatePrintedLength(var1) + this.iRight.calculatePrintedLength(var1);
      }

      public void printTo(StringBuffer var1, int var2) {
         this.iLeft.printTo(var1, var2);
         this.iRight.printTo(var1, var2);
      }

      public void printTo(Writer var1, int var2) throws IOException {
         this.iLeft.printTo(var1, var2);
         this.iRight.printTo(var1, var2);
      }

      public int parse(String var1, int var2) {
         int var3 = this.iLeft.parse(var1, var2);
         if (var3 >= 0) {
            var3 = this.iRight.parse(var1, var3);
            if (var3 >= 0 && this.matchesOtherAffix(this.parse(var1, var3) - var3, var1, var2)) {
               return ~var2;
            }
         }

         return var3;
      }

      public int scan(String var1, int var2) {
         int var3 = this.iLeft.scan(var1, var2);
         if (var3 >= 0) {
            int var4 = this.iRight.scan(var1, this.iLeft.parse(var1, var3));
            if (var4 < 0 || !this.matchesOtherAffix(this.iRight.parse(var1, var4) - var3, var1, var2)) {
               if (var3 > 0) {
                  return var3;
               }

               return var4;
            }
         }

         return ~var2;
      }

      public String[] getAffixes() {
         return (String[])this.iLeftRightCombinations.clone();
      }
   }

   static class FieldFormatter implements PeriodPrinter, PeriodParser {
      private final int iMinPrintedDigits;
      private final int iPrintZeroSetting;
      private final int iMaxParsedDigits;
      private final boolean iRejectSignedValues;
      private final int iFieldType;
      private final FieldFormatter[] iFieldFormatters;
      private final PeriodFieldAffix iPrefix;
      private final PeriodFieldAffix iSuffix;

      FieldFormatter(int var1, int var2, int var3, boolean var4, int var5, FieldFormatter[] var6, PeriodFieldAffix var7, PeriodFieldAffix var8) {
         this.iMinPrintedDigits = var1;
         this.iPrintZeroSetting = var2;
         this.iMaxParsedDigits = var3;
         this.iRejectSignedValues = var4;
         this.iFieldType = var5;
         this.iFieldFormatters = var6;
         this.iPrefix = var7;
         this.iSuffix = var8;
      }

      FieldFormatter(FieldFormatter var1, PeriodFieldAffix var2) {
         this.iMinPrintedDigits = var1.iMinPrintedDigits;
         this.iPrintZeroSetting = var1.iPrintZeroSetting;
         this.iMaxParsedDigits = var1.iMaxParsedDigits;
         this.iRejectSignedValues = var1.iRejectSignedValues;
         this.iFieldType = var1.iFieldType;
         this.iFieldFormatters = var1.iFieldFormatters;
         this.iPrefix = var1.iPrefix;
         if (var1.iSuffix != null) {
            var2 = new CompositeAffix(var1.iSuffix, (PeriodFieldAffix)var2);
         }

         this.iSuffix = (PeriodFieldAffix)var2;
      }

      public void finish(FieldFormatter[] var1) {
         HashSet var2 = new HashSet();
         HashSet var3 = new HashSet();

         for(FieldFormatter var7 : var1) {
            if (var7 != null && !this.equals(var7)) {
               var2.add(var7.iPrefix);
               var3.add(var7.iSuffix);
            }
         }

         if (this.iPrefix != null) {
            this.iPrefix.finish(var2);
         }

         if (this.iSuffix != null) {
            this.iSuffix.finish(var3);
         }

      }

      public int countFieldsToPrint(ReadablePeriod var1, int var2, Locale var3) {
         if (var2 <= 0) {
            return 0;
         } else {
            return this.iPrintZeroSetting != 4 && this.getFieldValue(var1) == Long.MAX_VALUE ? 0 : 1;
         }
      }

      public int calculatePrintedLength(ReadablePeriod var1, Locale var2) {
         long var3 = this.getFieldValue(var1);
         if (var3 == Long.MAX_VALUE) {
            return 0;
         } else {
            int var7 = Math.max(FormatUtils.calculateDigitCount(var3), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
               var7 = var3 < 0L ? Math.max(var7, 5) : Math.max(var7, 4);
               ++var7;
               if (this.iFieldType == 9 && Math.abs(var3) % 1000L == 0L) {
                  var7 -= 4;
               }

               var3 /= 1000L;
            }

            int var6 = (int)var3;
            if (this.iPrefix != null) {
               var7 += this.iPrefix.calculatePrintedLength(var6);
            }

            if (this.iSuffix != null) {
               var7 += this.iSuffix.calculatePrintedLength(var6);
            }

            return var7;
         }
      }

      public void printTo(StringBuffer var1, ReadablePeriod var2, Locale var3) {
         long var4 = this.getFieldValue(var2);
         if (var4 != Long.MAX_VALUE) {
            int var6 = (int)var4;
            if (this.iFieldType >= 8) {
               var6 = (int)(var4 / 1000L);
            }

            if (this.iPrefix != null) {
               this.iPrefix.printTo(var1, var6);
            }

            int var7 = var1.length();
            int var8 = this.iMinPrintedDigits;
            if (var8 <= 1) {
               FormatUtils.appendUnpaddedInteger(var1, var6);
            } else {
               FormatUtils.appendPaddedInteger(var1, var6, var8);
            }

            if (this.iFieldType >= 8) {
               int var9 = (int)(Math.abs(var4) % 1000L);
               if (this.iFieldType == 8 || var9 > 0) {
                  if (var4 < 0L && var4 > -1000L) {
                     var1.insert(var7, '-');
                  }

                  var1.append('.');
                  FormatUtils.appendPaddedInteger((StringBuffer)var1, var9, 3);
               }
            }

            if (this.iSuffix != null) {
               this.iSuffix.printTo(var1, var6);
            }

         }
      }

      public void printTo(Writer var1, ReadablePeriod var2, Locale var3) throws IOException {
         long var4 = this.getFieldValue(var2);
         if (var4 != Long.MAX_VALUE) {
            int var6 = (int)var4;
            if (this.iFieldType >= 8) {
               var6 = (int)(var4 / 1000L);
            }

            if (this.iPrefix != null) {
               this.iPrefix.printTo(var1, var6);
            }

            int var7 = this.iMinPrintedDigits;
            if (var7 <= 1) {
               FormatUtils.writeUnpaddedInteger(var1, var6);
            } else {
               FormatUtils.writePaddedInteger(var1, var6, var7);
            }

            if (this.iFieldType >= 8) {
               int var8 = (int)(Math.abs(var4) % 1000L);
               if (this.iFieldType == 8 || var8 > 0) {
                  var1.write(46);
                  FormatUtils.writePaddedInteger(var1, var8, 3);
               }
            }

            if (this.iSuffix != null) {
               this.iSuffix.printTo(var1, var6);
            }

         }
      }

      public int parseInto(ReadWritablePeriod var1, String var2, int var3, Locale var4) {
         boolean var5 = this.iPrintZeroSetting == 4;
         if (var3 >= var2.length()) {
            return var5 ? ~var3 : var3;
         } else {
            if (this.iPrefix != null) {
               var3 = this.iPrefix.parse(var2, var3);
               if (var3 < 0) {
                  if (!var5) {
                     return ~var3;
                  }

                  return var3;
               }

               var5 = true;
            }

            int var6 = -1;
            if (this.iSuffix != null && !var5) {
               var6 = this.iSuffix.scan(var2, var3);
               if (var6 < 0) {
                  if (!var5) {
                     return ~var6;
                  }

                  return var6;
               }

               var5 = true;
            }

            if (!var5 && !this.isSupported(var1.getPeriodType(), this.iFieldType)) {
               return var3;
            } else {
               int var7;
               if (var6 > 0) {
                  var7 = Math.min(this.iMaxParsedDigits, var6 - var3);
               } else {
                  var7 = Math.min(this.iMaxParsedDigits, var2.length() - var3);
               }

               int var8 = 0;
               int var9 = -1;
               boolean var10 = false;
               boolean var11 = false;

               while(var8 < var7) {
                  char var12 = var2.charAt(var3 + var8);
                  if (var8 == 0 && (var12 == '-' || var12 == '+') && !this.iRejectSignedValues) {
                     var11 = var12 == '-';
                     if (var8 + 1 >= var7 || (var12 = var2.charAt(var3 + var8 + 1)) < '0' || var12 > '9') {
                        break;
                     }

                     if (var11) {
                        ++var8;
                     } else {
                        ++var3;
                     }

                     var7 = Math.min(var7 + 1, var2.length() - var3);
                  } else {
                     if (var12 >= '0' && var12 <= '9') {
                        var10 = true;
                     } else {
                        if (var12 != '.' && var12 != ',' || this.iFieldType != 8 && this.iFieldType != 9 || var9 >= 0) {
                           break;
                        }

                        var9 = var3 + var8 + 1;
                        var7 = Math.min(var7 + 1, var2.length() - var3);
                     }

                     ++var8;
                  }
               }

               if (!var10) {
                  return ~var3;
               } else if (var6 >= 0 && var3 + var8 != var6) {
                  return var3;
               } else {
                  if (this.iFieldType != 8 && this.iFieldType != 9) {
                     this.setFieldValue(var1, this.iFieldType, this.parseInt(var2, var3, var8));
                  } else if (var9 < 0) {
                     this.setFieldValue(var1, 6, this.parseInt(var2, var3, var8));
                     this.setFieldValue(var1, 7, 0);
                  } else {
                     int var17 = this.parseInt(var2, var3, var9 - var3 - 1);
                     this.setFieldValue(var1, 6, var17);
                     int var13 = var3 + var8 - var9;
                     int var14;
                     if (var13 <= 0) {
                        var14 = 0;
                     } else {
                        if (var13 >= 3) {
                           var14 = this.parseInt(var2, var9, 3);
                        } else {
                           var14 = this.parseInt(var2, var9, var13);
                           if (var13 == 1) {
                              var14 *= 100;
                           } else {
                              var14 *= 10;
                           }
                        }

                        if (var11 || var17 < 0) {
                           var14 = -var14;
                        }
                     }

                     this.setFieldValue(var1, 7, var14);
                  }

                  var3 += var8;
                  if (var3 >= 0 && this.iSuffix != null) {
                     var3 = this.iSuffix.parse(var2, var3);
                  }

                  return var3;
               }
            }
         }
      }

      private int parseInt(String var1, int var2, int var3) {
         if (var3 >= 10) {
            return Integer.parseInt(var1.substring(var2, var2 + var3));
         } else if (var3 <= 0) {
            return 0;
         } else {
            int var4 = var1.charAt(var2++);
            --var3;
            boolean var5;
            if (var4 == 45) {
               --var3;
               if (var3 < 0) {
                  return 0;
               }

               var5 = true;
               var4 = var1.charAt(var2++);
            } else {
               var5 = false;
            }

            for(var4 -= 48; var3-- > 0; var4 = (var4 << 3) + (var4 << 1) + var1.charAt(var2++) - 48) {
            }

            return var5 ? -var4 : var4;
         }
      }

      long getFieldValue(ReadablePeriod var1) {
         PeriodType var2;
         if (this.iPrintZeroSetting == 4) {
            var2 = null;
         } else {
            var2 = var1.getPeriodType();
         }

         if (var2 != null && !this.isSupported(var2, this.iFieldType)) {
            return Long.MAX_VALUE;
         } else {
            long var3;
            switch (this.iFieldType) {
               case 0:
                  var3 = (long)var1.get(DurationFieldType.years());
                  break;
               case 1:
                  var3 = (long)var1.get(DurationFieldType.months());
                  break;
               case 2:
                  var3 = (long)var1.get(DurationFieldType.weeks());
                  break;
               case 3:
                  var3 = (long)var1.get(DurationFieldType.days());
                  break;
               case 4:
                  var3 = (long)var1.get(DurationFieldType.hours());
                  break;
               case 5:
                  var3 = (long)var1.get(DurationFieldType.minutes());
                  break;
               case 6:
                  var3 = (long)var1.get(DurationFieldType.seconds());
                  break;
               case 7:
                  var3 = (long)var1.get(DurationFieldType.millis());
                  break;
               case 8:
               case 9:
                  int var5 = var1.get(DurationFieldType.seconds());
                  int var6 = var1.get(DurationFieldType.millis());
                  var3 = (long)var5 * 1000L + (long)var6;
                  break;
               default:
                  return Long.MAX_VALUE;
            }

            if (var3 == 0L) {
               switch (this.iPrintZeroSetting) {
                  case 1:
                     if (!this.isZero(var1) || this.iFieldFormatters[this.iFieldType] != this) {
                        return Long.MAX_VALUE;
                     }

                     int var8 = Math.min(this.iFieldType, 8);
                     --var8;

                     while(var8 >= 0) {
                        if (this.isSupported(var2, var8) && this.iFieldFormatters[var8] != null) {
                           return Long.MAX_VALUE;
                        }

                        --var8;
                     }
                     break;
                  case 2:
                     if (!this.isZero(var1) || this.iFieldFormatters[this.iFieldType] != this) {
                        return Long.MAX_VALUE;
                     }

                     for(int var7 = this.iFieldType + 1; var7 <= 9; ++var7) {
                        if (this.isSupported(var2, var7) && this.iFieldFormatters[var7] != null) {
                           return Long.MAX_VALUE;
                        }
                     }
                  case 3:
                  case 4:
                  default:
                     break;
                  case 5:
                     return Long.MAX_VALUE;
               }
            }

            return var3;
         }
      }

      boolean isZero(ReadablePeriod var1) {
         int var2 = 0;

         for(int var3 = var1.size(); var2 < var3; ++var2) {
            if (var1.getValue(var2) != 0) {
               return false;
            }
         }

         return true;
      }

      boolean isSupported(PeriodType var1, int var2) {
         switch (var2) {
            case 0:
               return var1.isSupported(DurationFieldType.years());
            case 1:
               return var1.isSupported(DurationFieldType.months());
            case 2:
               return var1.isSupported(DurationFieldType.weeks());
            case 3:
               return var1.isSupported(DurationFieldType.days());
            case 4:
               return var1.isSupported(DurationFieldType.hours());
            case 5:
               return var1.isSupported(DurationFieldType.minutes());
            case 6:
               return var1.isSupported(DurationFieldType.seconds());
            case 7:
               return var1.isSupported(DurationFieldType.millis());
            case 8:
            case 9:
               return var1.isSupported(DurationFieldType.seconds()) || var1.isSupported(DurationFieldType.millis());
            default:
               return false;
         }
      }

      void setFieldValue(ReadWritablePeriod var1, int var2, int var3) {
         switch (var2) {
            case 0:
               var1.setYears(var3);
               break;
            case 1:
               var1.setMonths(var3);
               break;
            case 2:
               var1.setWeeks(var3);
               break;
            case 3:
               var1.setDays(var3);
               break;
            case 4:
               var1.setHours(var3);
               break;
            case 5:
               var1.setMinutes(var3);
               break;
            case 6:
               var1.setSeconds(var3);
               break;
            case 7:
               var1.setMillis(var3);
         }

      }

      int getFieldType() {
         return this.iFieldType;
      }
   }

   static class Literal implements PeriodPrinter, PeriodParser {
      static final Literal EMPTY = new Literal("");
      private final String iText;

      Literal(String var1) {
         this.iText = var1;
      }

      public int countFieldsToPrint(ReadablePeriod var1, int var2, Locale var3) {
         return 0;
      }

      public int calculatePrintedLength(ReadablePeriod var1, Locale var2) {
         return this.iText.length();
      }

      public void printTo(StringBuffer var1, ReadablePeriod var2, Locale var3) {
         var1.append(this.iText);
      }

      public void printTo(Writer var1, ReadablePeriod var2, Locale var3) throws IOException {
         var1.write(this.iText);
      }

      public int parseInto(ReadWritablePeriod var1, String var2, int var3, Locale var4) {
         return var2.regionMatches(true, var3, this.iText, 0, this.iText.length()) ? var3 + this.iText.length() : ~var3;
      }
   }

   static class Separator implements PeriodPrinter, PeriodParser {
      private final String iText;
      private final String iFinalText;
      private final String[] iParsedForms;
      private final boolean iUseBefore;
      private final boolean iUseAfter;
      private final PeriodPrinter iBeforePrinter;
      private volatile PeriodPrinter iAfterPrinter;
      private final PeriodParser iBeforeParser;
      private volatile PeriodParser iAfterParser;

      Separator(String var1, String var2, String[] var3, PeriodPrinter var4, PeriodParser var5, boolean var6, boolean var7) {
         this.iText = var1;
         this.iFinalText = var2;
         if (var2 != null && !var1.equals(var2) || var3 != null && var3.length != 0) {
            TreeSet var8 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            var8.add(var1);
            var8.add(var2);
            if (var3 != null) {
               int var9 = var3.length;

               while(true) {
                  --var9;
                  if (var9 < 0) {
                     break;
                  }

                  var8.add(var3[var9]);
               }
            }

            ArrayList var10 = new ArrayList(var8);
            Collections.reverse(var10);
            this.iParsedForms = (String[])var10.toArray(new String[var10.size()]);
         } else {
            this.iParsedForms = new String[]{var1};
         }

         this.iBeforePrinter = var4;
         this.iBeforeParser = var5;
         this.iUseBefore = var6;
         this.iUseAfter = var7;
      }

      public int countFieldsToPrint(ReadablePeriod var1, int var2, Locale var3) {
         int var4 = this.iBeforePrinter.countFieldsToPrint(var1, var2, var3);
         if (var4 < var2) {
            var4 += this.iAfterPrinter.countFieldsToPrint(var1, var2, var3);
         }

         return var4;
      }

      public int calculatePrintedLength(ReadablePeriod var1, Locale var2) {
         PeriodPrinter var3 = this.iBeforePrinter;
         PeriodPrinter var4 = this.iAfterPrinter;
         int var5 = var3.calculatePrintedLength(var1, var2) + var4.calculatePrintedLength(var1, var2);
         if (this.iUseBefore) {
            if (var3.countFieldsToPrint(var1, 1, var2) > 0) {
               if (this.iUseAfter) {
                  int var6 = var4.countFieldsToPrint(var1, 2, var2);
                  if (var6 > 0) {
                     var5 += (var6 > 1 ? this.iText : this.iFinalText).length();
                  }
               } else {
                  var5 += this.iText.length();
               }
            }
         } else if (this.iUseAfter && var4.countFieldsToPrint(var1, 1, var2) > 0) {
            var5 += this.iText.length();
         }

         return var5;
      }

      public void printTo(StringBuffer var1, ReadablePeriod var2, Locale var3) {
         PeriodPrinter var4 = this.iBeforePrinter;
         PeriodPrinter var5 = this.iAfterPrinter;
         var4.printTo(var1, var2, var3);
         if (this.iUseBefore) {
            if (var4.countFieldsToPrint(var2, 1, var3) > 0) {
               if (this.iUseAfter) {
                  int var6 = var5.countFieldsToPrint(var2, 2, var3);
                  if (var6 > 0) {
                     var1.append(var6 > 1 ? this.iText : this.iFinalText);
                  }
               } else {
                  var1.append(this.iText);
               }
            }
         } else if (this.iUseAfter && var5.countFieldsToPrint(var2, 1, var3) > 0) {
            var1.append(this.iText);
         }

         var5.printTo(var1, var2, var3);
      }

      public void printTo(Writer var1, ReadablePeriod var2, Locale var3) throws IOException {
         PeriodPrinter var4 = this.iBeforePrinter;
         PeriodPrinter var5 = this.iAfterPrinter;
         var4.printTo(var1, var2, var3);
         if (this.iUseBefore) {
            if (var4.countFieldsToPrint(var2, 1, var3) > 0) {
               if (this.iUseAfter) {
                  int var6 = var5.countFieldsToPrint(var2, 2, var3);
                  if (var6 > 0) {
                     var1.write(var6 > 1 ? this.iText : this.iFinalText);
                  }
               } else {
                  var1.write(this.iText);
               }
            }
         } else if (this.iUseAfter && var5.countFieldsToPrint(var2, 1, var3) > 0) {
            var1.write(this.iText);
         }

         var5.printTo(var1, var2, var3);
      }

      public int parseInto(ReadWritablePeriod var1, String var2, int var3, Locale var4) {
         var3 = this.iBeforeParser.parseInto(var1, var2, var3, var4);
         if (var3 < 0) {
            return var3;
         } else {
            boolean var6 = false;
            int var7 = -1;
            if (var3 > var3) {
               for(String var11 : this.iParsedForms) {
                  if (var11 == null || var11.length() == 0 || var2.regionMatches(true, var3, var11, 0, var11.length())) {
                     var7 = var11 == null ? 0 : var11.length();
                     var3 += var7;
                     var6 = true;
                     break;
                  }
               }
            }

            var3 = this.iAfterParser.parseInto(var1, var2, var3, var4);
            if (var3 < 0) {
               return var3;
            } else if (var6 && var3 == var3 && var7 > 0) {
               return ~var3;
            } else {
               return var3 > var3 && !var6 && !this.iUseBefore ? ~var3 : var3;
            }
         }
      }

      Separator finish(PeriodPrinter var1, PeriodParser var2) {
         this.iAfterPrinter = var1;
         this.iAfterParser = var2;
         return this;
      }
   }

   static class Composite implements PeriodPrinter, PeriodParser {
      private final PeriodPrinter[] iPrinters;
      private final PeriodParser[] iParsers;

      Composite(List var1) {
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         this.decompose(var1, var2, var3);
         if (var2.size() <= 0) {
            this.iPrinters = null;
         } else {
            this.iPrinters = (PeriodPrinter[])var2.toArray(new PeriodPrinter[var2.size()]);
         }

         if (var3.size() <= 0) {
            this.iParsers = null;
         } else {
            this.iParsers = (PeriodParser[])var3.toArray(new PeriodParser[var3.size()]);
         }

      }

      public int countFieldsToPrint(ReadablePeriod var1, int var2, Locale var3) {
         int var4 = 0;
         PeriodPrinter[] var5 = this.iPrinters;

         for(int var6 = var5.length; var4 < var2; var4 += var5[var6].countFieldsToPrint(var1, Integer.MAX_VALUE, var3)) {
            --var6;
            if (var6 < 0) {
               break;
            }
         }

         return var4;
      }

      public int calculatePrintedLength(ReadablePeriod var1, Locale var2) {
         int var3 = 0;
         PeriodPrinter[] var4 = this.iPrinters;
         int var5 = var4.length;

         while(true) {
            --var5;
            if (var5 < 0) {
               return var3;
            }

            var3 += var4[var5].calculatePrintedLength(var1, var2);
         }
      }

      public void printTo(StringBuffer var1, ReadablePeriod var2, Locale var3) {
         PeriodPrinter[] var4 = this.iPrinters;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            var4[var6].printTo(var1, var2, var3);
         }

      }

      public void printTo(Writer var1, ReadablePeriod var2, Locale var3) throws IOException {
         PeriodPrinter[] var4 = this.iPrinters;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            var4[var6].printTo(var1, var2, var3);
         }

      }

      public int parseInto(ReadWritablePeriod var1, String var2, int var3, Locale var4) {
         PeriodParser[] var5 = this.iParsers;
         if (var5 == null) {
            throw new UnsupportedOperationException();
         } else {
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6 && var3 >= 0; ++var7) {
               var3 = var5[var7].parseInto(var1, var2, var3, var4);
            }

            return var3;
         }
      }

      private void decompose(List var1, List var2, List var3) {
         int var4 = var1.size();

         for(int var5 = 0; var5 < var4; var5 += 2) {
            Object var6 = var1.get(var5);
            if (var6 instanceof PeriodPrinter) {
               if (var6 instanceof Composite) {
                  this.addArrayToList(var2, ((Composite)var6).iPrinters);
               } else {
                  var2.add(var6);
               }
            }

            var6 = var1.get(var5 + 1);
            if (var6 instanceof PeriodParser) {
               if (var6 instanceof Composite) {
                  this.addArrayToList(var3, ((Composite)var6).iParsers);
               } else {
                  var3.add(var6);
               }
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

   interface PeriodFieldAffix {
      int calculatePrintedLength(int var1);

      void printTo(StringBuffer var1, int var2);

      void printTo(Writer var1, int var2) throws IOException;

      int parse(String var1, int var2);

      int scan(String var1, int var2);

      String[] getAffixes();

      void finish(Set var1);
   }
}
