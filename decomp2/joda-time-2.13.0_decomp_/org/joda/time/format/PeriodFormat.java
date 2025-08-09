package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormat {
   private static final String BUNDLE_NAME = "org.joda.time.format.messages";
   private static final ConcurrentMap FORMATTERS = new ConcurrentHashMap();

   protected PeriodFormat() {
   }

   public static PeriodFormatter getDefault() {
      return wordBased(Locale.ENGLISH);
   }

   public static PeriodFormatter wordBased() {
      return wordBased(Locale.getDefault());
   }

   public static PeriodFormatter wordBased(Locale var0) {
      PeriodFormatter var1 = (PeriodFormatter)FORMATTERS.get(var0);
      if (var1 == null) {
         DynamicWordBased var2 = new DynamicWordBased(buildWordBased(var0));
         var1 = new PeriodFormatter(var2, var2, var0, (PeriodType)null);
         PeriodFormatter var3 = (PeriodFormatter)FORMATTERS.putIfAbsent(var0, var1);
         if (var3 != null) {
            var1 = var3;
         }
      }

      return var1;
   }

   private static PeriodFormatter buildWordBased(Locale var0) {
      ResourceBundle var1 = ResourceBundle.getBundle("org.joda.time.format.messages", var0);
      return containsKey(var1, "PeriodFormat.regex.separator") ? buildRegExFormatter(var1, var0) : buildNonRegExFormatter(var1, var0);
   }

   private static PeriodFormatter buildRegExFormatter(ResourceBundle var0, Locale var1) {
      String[] var2 = retrieveVariants(var0);
      String var3 = var0.getString("PeriodFormat.regex.separator");
      PeriodFormatterBuilder var4 = new PeriodFormatterBuilder();
      var4.appendYears();
      if (containsKey(var0, "PeriodFormat.years.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.years.regex").split(var3), var0.getString("PeriodFormat.years.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.year"), var0.getString("PeriodFormat.years"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendMonths();
      if (containsKey(var0, "PeriodFormat.months.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.months.regex").split(var3), var0.getString("PeriodFormat.months.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.month"), var0.getString("PeriodFormat.months"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendWeeks();
      if (containsKey(var0, "PeriodFormat.weeks.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.weeks.regex").split(var3), var0.getString("PeriodFormat.weeks.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.week"), var0.getString("PeriodFormat.weeks"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendDays();
      if (containsKey(var0, "PeriodFormat.days.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.days.regex").split(var3), var0.getString("PeriodFormat.days.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.day"), var0.getString("PeriodFormat.days"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendHours();
      if (containsKey(var0, "PeriodFormat.hours.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.hours.regex").split(var3), var0.getString("PeriodFormat.hours.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.hour"), var0.getString("PeriodFormat.hours"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendMinutes();
      if (containsKey(var0, "PeriodFormat.minutes.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.minutes.regex").split(var3), var0.getString("PeriodFormat.minutes.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.minute"), var0.getString("PeriodFormat.minutes"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendSeconds();
      if (containsKey(var0, "PeriodFormat.seconds.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.seconds.regex").split(var3), var0.getString("PeriodFormat.seconds.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.second"), var0.getString("PeriodFormat.seconds"));
      }

      var4.appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2);
      var4.appendMillis();
      if (containsKey(var0, "PeriodFormat.milliseconds.regex")) {
         var4.appendSuffix(var0.getString("PeriodFormat.milliseconds.regex").split(var3), var0.getString("PeriodFormat.milliseconds.list").split(var3));
      } else {
         var4.appendSuffix(var0.getString("PeriodFormat.millisecond"), var0.getString("PeriodFormat.milliseconds"));
      }

      return var4.toFormatter().withLocale(var1);
   }

   private static PeriodFormatter buildNonRegExFormatter(ResourceBundle var0, Locale var1) {
      String[] var2 = retrieveVariants(var0);
      return (new PeriodFormatterBuilder()).appendYears().appendSuffix(var0.getString("PeriodFormat.year"), var0.getString("PeriodFormat.years")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendMonths().appendSuffix(var0.getString("PeriodFormat.month"), var0.getString("PeriodFormat.months")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendWeeks().appendSuffix(var0.getString("PeriodFormat.week"), var0.getString("PeriodFormat.weeks")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendDays().appendSuffix(var0.getString("PeriodFormat.day"), var0.getString("PeriodFormat.days")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendHours().appendSuffix(var0.getString("PeriodFormat.hour"), var0.getString("PeriodFormat.hours")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendMinutes().appendSuffix(var0.getString("PeriodFormat.minute"), var0.getString("PeriodFormat.minutes")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendSeconds().appendSuffix(var0.getString("PeriodFormat.second"), var0.getString("PeriodFormat.seconds")).appendSeparator(var0.getString("PeriodFormat.commaspace"), var0.getString("PeriodFormat.spaceandspace"), var2).appendMillis().appendSuffix(var0.getString("PeriodFormat.millisecond"), var0.getString("PeriodFormat.milliseconds")).toFormatter().withLocale(var1);
   }

   private static String[] retrieveVariants(ResourceBundle var0) {
      return new String[]{var0.getString("PeriodFormat.space"), var0.getString("PeriodFormat.comma"), var0.getString("PeriodFormat.commandand"), var0.getString("PeriodFormat.commaspaceand")};
   }

   private static boolean containsKey(ResourceBundle var0, String var1) {
      Enumeration var2 = var0.getKeys();

      while(var2.hasMoreElements()) {
         if (((String)var2.nextElement()).equals(var1)) {
            return true;
         }
      }

      return false;
   }

   static class DynamicWordBased implements PeriodPrinter, PeriodParser {
      private final PeriodFormatter iFormatter;

      DynamicWordBased(PeriodFormatter var1) {
         this.iFormatter = var1;
      }

      public int countFieldsToPrint(ReadablePeriod var1, int var2, Locale var3) {
         return this.getPrinter(var3).countFieldsToPrint(var1, var2, var3);
      }

      public int calculatePrintedLength(ReadablePeriod var1, Locale var2) {
         return this.getPrinter(var2).calculatePrintedLength(var1, var2);
      }

      public void printTo(StringBuffer var1, ReadablePeriod var2, Locale var3) {
         this.getPrinter(var3).printTo(var1, var2, var3);
      }

      public void printTo(Writer var1, ReadablePeriod var2, Locale var3) throws IOException {
         this.getPrinter(var3).printTo(var1, var2, var3);
      }

      private PeriodPrinter getPrinter(Locale var1) {
         return var1 != null && !var1.equals(this.iFormatter.getLocale()) ? PeriodFormat.wordBased(var1).getPrinter() : this.iFormatter.getPrinter();
      }

      public int parseInto(ReadWritablePeriod var1, String var2, int var3, Locale var4) {
         return this.getParser(var4).parseInto(var1, var2, var3, var4);
      }

      private PeriodParser getParser(Locale var1) {
         return var1 != null && !var1.equals(this.iFormatter.getLocale()) ? PeriodFormat.wordBased(var1).getParser() : this.iFormatter.getParser();
      }
   }
}
