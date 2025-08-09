package org.joda.time.format;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public class DateTimeFormat {
   static final int FULL = 0;
   static final int LONG = 1;
   static final int MEDIUM = 2;
   static final int SHORT = 3;
   static final int NONE = 4;
   static final int DATE = 0;
   static final int TIME = 1;
   static final int DATETIME = 2;
   private static final int PATTERN_CACHE_SIZE = 500;
   private static final ConcurrentHashMap cPatternCache = new ConcurrentHashMap();
   private static final AtomicReferenceArray cStyleCache = new AtomicReferenceArray(25);

   public static DateTimeFormatter forPattern(String var0) {
      return createFormatterForPattern(var0);
   }

   public static DateTimeFormatter forStyle(String var0) {
      return createFormatterForStyle(var0);
   }

   public static String patternForStyle(String var0, Locale var1) {
      DateTimeFormatter var2 = createFormatterForStyle(var0);
      if (var1 == null) {
         var1 = Locale.getDefault();
      }

      return ((StyleFormatter)var2.getPrinter0()).getPattern(var1);
   }

   public static DateTimeFormatter shortDate() {
      return createFormatterForStyleIndex(3, 4);
   }

   public static DateTimeFormatter shortTime() {
      return createFormatterForStyleIndex(4, 3);
   }

   public static DateTimeFormatter shortDateTime() {
      return createFormatterForStyleIndex(3, 3);
   }

   public static DateTimeFormatter mediumDate() {
      return createFormatterForStyleIndex(2, 4);
   }

   public static DateTimeFormatter mediumTime() {
      return createFormatterForStyleIndex(4, 2);
   }

   public static DateTimeFormatter mediumDateTime() {
      return createFormatterForStyleIndex(2, 2);
   }

   public static DateTimeFormatter longDate() {
      return createFormatterForStyleIndex(1, 4);
   }

   public static DateTimeFormatter longTime() {
      return createFormatterForStyleIndex(4, 1);
   }

   public static DateTimeFormatter longDateTime() {
      return createFormatterForStyleIndex(1, 1);
   }

   public static DateTimeFormatter fullDate() {
      return createFormatterForStyleIndex(0, 4);
   }

   public static DateTimeFormatter fullTime() {
      return createFormatterForStyleIndex(4, 0);
   }

   public static DateTimeFormatter fullDateTime() {
      return createFormatterForStyleIndex(0, 0);
   }

   static void appendPatternTo(DateTimeFormatterBuilder var0, String var1) {
      parsePatternTo(var0, var1);
   }

   protected DateTimeFormat() {
   }

   private static void parsePatternTo(DateTimeFormatterBuilder var0, String var1) {
      int var2 = var1.length();
      int[] var3 = new int[1];

      for(int var9 = 0; var9 < var2; ++var9) {
         var3[0] = var9;
         String var5 = parseToken(var1, var3);
         var9 = var3[0];
         int var6 = var5.length();
         if (var6 == 0) {
            break;
         }

         char var7 = var5.charAt(0);
         switch (var7) {
            case '\'':
               String var11 = var5.substring(1);
               if (var11.length() == 1) {
                  var0.appendLiteral(var11.charAt(0));
               } else {
                  var0.appendLiteral(new String(var11));
               }
               break;
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
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
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'A':
            case 'B':
            case 'F':
            case 'I':
            case 'J':
            case 'L':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'b':
            case 'c':
            case 'f':
            case 'g':
            case 'i':
            case 'j':
            case 'l':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 't':
            case 'u':
            case 'v':
            default:
               throw new IllegalArgumentException("Illegal pattern component: " + var5);
            case 'C':
               var0.appendCenturyOfEra(var6, var6);
               break;
            case 'D':
               var0.appendDayOfYear(var6);
               break;
            case 'E':
               if (var6 >= 4) {
                  var0.appendDayOfWeekText();
               } else {
                  var0.appendDayOfWeekShortText();
               }
               break;
            case 'G':
               var0.appendEraText();
               break;
            case 'H':
               var0.appendHourOfDay(var6);
               break;
            case 'K':
               var0.appendHourOfHalfday(var6);
               break;
            case 'M':
               if (var6 >= 3) {
                  if (var6 >= 4) {
                     var0.appendMonthOfYearText();
                  } else {
                     var0.appendMonthOfYearShortText();
                  }
               } else {
                  var0.appendMonthOfYear(var6);
               }
               break;
            case 'S':
               var0.appendFractionOfSecond(var6, var6);
               break;
            case 'Y':
            case 'x':
            case 'y':
               if (var6 == 2) {
                  boolean var8 = true;
                  if (var9 + 1 < var2) {
                     int var10002 = var3[0]++;
                     if (isNumericToken(parseToken(var1, var3))) {
                        var8 = false;
                     }

                     var10002 = var3[0]--;
                  }

                  switch (var7) {
                     case 'Y':
                     case 'y':
                     default:
                        var0.appendTwoDigitYear((new DateTime()).getYear() - 30, var8);
                        break;
                     case 'x':
                        var0.appendTwoDigitWeekyear((new DateTime()).getWeekyear() - 30, var8);
                  }
               } else {
                  int var10 = 9;
                  if (var9 + 1 < var2) {
                     int var13 = var3[0]++;
                     if (isNumericToken(parseToken(var1, var3))) {
                        var10 = var6;
                     }

                     var13 = var3[0]--;
                  }

                  switch (var7) {
                     case 'Y':
                        var0.appendYearOfEra(var6, var10);
                        break;
                     case 'x':
                        var0.appendWeekyear(var6, var10);
                        break;
                     case 'y':
                        var0.appendYear(var6, var10);
                  }
               }
               break;
            case 'Z':
               if (var6 == 1) {
                  var0.appendTimeZoneOffset((String)null, "Z", false, 2, 2);
               } else if (var6 == 2) {
                  var0.appendTimeZoneOffset((String)null, "Z", true, 2, 2);
               } else {
                  var0.appendTimeZoneId();
               }
               break;
            case 'a':
               var0.appendHalfdayOfDayText();
               break;
            case 'd':
               var0.appendDayOfMonth(var6);
               break;
            case 'e':
               var0.appendDayOfWeek(var6);
               break;
            case 'h':
               var0.appendClockhourOfHalfday(var6);
               break;
            case 'k':
               var0.appendClockhourOfDay(var6);
               break;
            case 'm':
               var0.appendMinuteOfHour(var6);
               break;
            case 's':
               var0.appendSecondOfMinute(var6);
               break;
            case 'w':
               var0.appendWeekOfWeekyear(var6);
               break;
            case 'z':
               if (var6 >= 4) {
                  var0.appendTimeZoneName();
               } else {
                  var0.appendTimeZoneShortName((Map)null);
               }
         }
      }

   }

   private static String parseToken(String var0, int[] var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = var1[0];
      int var4 = var0.length();
      char var5 = var0.charAt(var3);
      if (var5 >= 'A' && var5 <= 'Z' || var5 >= 'a' && var5 <= 'z') {
         var2.append(var5);

         while(var3 + 1 < var4) {
            char var8 = var0.charAt(var3 + 1);
            if (var8 != var5) {
               break;
            }

            var2.append(var5);
            ++var3;
         }
      } else {
         var2.append('\'');

         for(boolean var6 = false; var3 < var4; ++var3) {
            var5 = var0.charAt(var3);
            if (var5 == '\'') {
               if (var3 + 1 < var4 && var0.charAt(var3 + 1) == '\'') {
                  ++var3;
                  var2.append(var5);
               } else {
                  var6 = !var6;
               }
            } else {
               if (!var6 && (var5 >= 'A' && var5 <= 'Z' || var5 >= 'a' && var5 <= 'z')) {
                  --var3;
                  break;
               }

               var2.append(var5);
            }
         }
      }

      var1[0] = var3;
      return var2.toString();
   }

   private static boolean isNumericToken(String var0) {
      int var1 = var0.length();
      if (var1 > 0) {
         char var2 = var0.charAt(0);
         switch (var2) {
            case 'C':
            case 'D':
            case 'F':
            case 'H':
            case 'K':
            case 'S':
            case 'W':
            case 'Y':
            case 'c':
            case 'd':
            case 'e':
            case 'h':
            case 'k':
            case 'm':
            case 's':
            case 'w':
            case 'x':
            case 'y':
               return true;
            case 'E':
            case 'G':
            case 'I':
            case 'J':
            case 'L':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case 'X':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'f':
            case 'g':
            case 'i':
            case 'j':
            case 'l':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 't':
            case 'u':
            case 'v':
            default:
               break;
            case 'M':
               if (var1 <= 2) {
                  return true;
               }
         }
      }

      return false;
   }

   private static DateTimeFormatter createFormatterForPattern(String var0) {
      if (var0 != null && var0.length() != 0) {
         DateTimeFormatter var1 = (DateTimeFormatter)cPatternCache.get(var0);
         if (var1 == null) {
            DateTimeFormatterBuilder var2 = new DateTimeFormatterBuilder();
            parsePatternTo(var2, var0);
            var1 = var2.toFormatter();
            if (cPatternCache.size() < 500) {
               DateTimeFormatter var3 = (DateTimeFormatter)cPatternCache.putIfAbsent(var0, var1);
               if (var3 != null) {
                  var1 = var3;
               }
            }
         }

         return var1;
      } else {
         throw new IllegalArgumentException("Invalid pattern specification: Pattern is null or empty");
      }
   }

   private static DateTimeFormatter createFormatterForStyle(String var0) {
      if (var0 != null && var0.length() == 2) {
         int var1 = selectStyle(var0.charAt(0));
         int var2 = selectStyle(var0.charAt(1));
         if (var1 == 4 && var2 == 4) {
            throw new IllegalArgumentException("Style '--' is invalid");
         } else {
            return createFormatterForStyleIndex(var1, var2);
         }
      } else {
         throw new IllegalArgumentException("Invalid style specification: " + var0);
      }
   }

   private static DateTimeFormatter createFormatterForStyleIndex(int var0, int var1) {
      int var2 = (var0 << 2) + var0 + var1;
      if (var2 >= cStyleCache.length()) {
         return createDateTimeFormatter(var0, var1);
      } else {
         DateTimeFormatter var3 = (DateTimeFormatter)cStyleCache.get(var2);
         if (var3 == null) {
            var3 = createDateTimeFormatter(var0, var1);
            if (!cStyleCache.compareAndSet(var2, (Object)null, var3)) {
               var3 = (DateTimeFormatter)cStyleCache.get(var2);
            }
         }

         return var3;
      }
   }

   private static DateTimeFormatter createDateTimeFormatter(int var0, int var1) {
      byte var2 = 2;
      if (var0 == 4) {
         var2 = 1;
      } else if (var1 == 4) {
         var2 = 0;
      }

      StyleFormatter var3 = new StyleFormatter(var0, var1, var2);
      return new DateTimeFormatter(var3, var3);
   }

   private static int selectStyle(char var0) {
      switch (var0) {
         case '-':
            return 4;
         case 'F':
            return 0;
         case 'L':
            return 1;
         case 'M':
            return 2;
         case 'S':
            return 3;
         default:
            throw new IllegalArgumentException("Invalid style character: " + var0);
      }
   }

   static class StyleFormatter implements InternalPrinter, InternalParser {
      private static final ConcurrentHashMap cCache = new ConcurrentHashMap();
      private final int iDateStyle;
      private final int iTimeStyle;
      private final int iType;

      StyleFormatter(int var1, int var2, int var3) {
         this.iDateStyle = var1;
         this.iTimeStyle = var2;
         this.iType = var3;
      }

      public int estimatePrintedLength() {
         return 40;
      }

      public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
         InternalPrinter var8 = this.getFormatter(var7).getPrinter0();
         var8.printTo(var1, var2, var4, var5, var6, var7);
      }

      public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
         InternalPrinter var4 = this.getFormatter(var3).getPrinter0();
         var4.printTo(var1, var2, var3);
      }

      public int estimateParsedLength() {
         return 40;
      }

      public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
         InternalParser var4 = this.getFormatter(var1.getLocale()).getParser0();
         return var4.parseInto(var1, var2, var3);
      }

      private DateTimeFormatter getFormatter(Locale var1) {
         var1 = var1 == null ? Locale.getDefault() : var1;
         StyleFormatterCacheKey var2 = new StyleFormatterCacheKey(this.iType, this.iDateStyle, this.iTimeStyle, var1);
         DateTimeFormatter var3 = (DateTimeFormatter)cCache.get(var2);
         if (var3 == null) {
            var3 = DateTimeFormat.forPattern(this.getPattern(var1));
            DateTimeFormatter var4 = (DateTimeFormatter)cCache.putIfAbsent(var2, var3);
            if (var4 != null) {
               var3 = var4;
            }
         }

         return var3;
      }

      String getPattern(Locale var1) {
         DateFormat var2 = null;
         switch (this.iType) {
            case 0:
               var2 = DateFormat.getDateInstance(this.iDateStyle, var1);
               break;
            case 1:
               var2 = DateFormat.getTimeInstance(this.iTimeStyle, var1);
               break;
            case 2:
               var2 = DateFormat.getDateTimeInstance(this.iDateStyle, this.iTimeStyle, var1);
         }

         if (!(var2 instanceof SimpleDateFormat)) {
            throw new IllegalArgumentException("No datetime pattern for locale: " + var1);
         } else {
            return ((SimpleDateFormat)var2).toPattern();
         }
      }
   }

   static class StyleFormatterCacheKey {
      private final int combinedTypeAndStyle;
      private final Locale locale;

      public StyleFormatterCacheKey(int var1, int var2, int var3, Locale var4) {
         this.locale = var4;
         this.combinedTypeAndStyle = var1 + (var2 << 4) + (var3 << 8);
      }

      public int hashCode() {
         int var2 = 1;
         var2 = 31 * var2 + this.combinedTypeAndStyle;
         var2 = 31 * var2 + (this.locale == null ? 0 : this.locale.hashCode());
         return var2;
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (!(var1 instanceof StyleFormatterCacheKey)) {
            return false;
         } else {
            StyleFormatterCacheKey var2 = (StyleFormatterCacheKey)var1;
            if (this.combinedTypeAndStyle != var2.combinedTypeAndStyle) {
               return false;
            } else {
               if (this.locale == null) {
                  if (var2.locale != null) {
                     return false;
                  }
               } else if (!this.locale.equals(var2.locale)) {
                  return false;
               }

               return true;
            }
         }
      }
   }
}
