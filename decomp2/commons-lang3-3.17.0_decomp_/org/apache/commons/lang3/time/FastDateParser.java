package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.LocaleUtils;

public class FastDateParser implements DateParser, Serializable {
   private static final long serialVersionUID = 3L;
   static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
   private static final Comparator LONGER_FIRST_LOWERCASE = Comparator.reverseOrder();
   private static final ConcurrentMap[] caches = new ConcurrentMap[17];
   private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1) {
      int modify(FastDateParser parser, int iValue) {
         return iValue < 100 ? parser.adjustYear(iValue) : iValue;
      }
   };
   private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2) {
      int modify(FastDateParser parser, int iValue) {
         return iValue - 1;
      }
   };
   private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
   private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
   private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
   private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
   private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
   private static final Strategy DAY_OF_WEEK_STRATEGY = new NumberStrategy(7) {
      int modify(FastDateParser parser, int iValue) {
         return iValue == 7 ? 1 : iValue + 1;
      }
   };
   private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
   private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
   private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11) {
      int modify(FastDateParser parser, int iValue) {
         return iValue == 24 ? 0 : iValue;
      }
   };
   private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10) {
      int modify(FastDateParser parser, int iValue) {
         return iValue == 12 ? 0 : iValue;
      }
   };
   private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
   private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
   private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
   private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);
   private final String pattern;
   private final TimeZone timeZone;
   private final Locale locale;
   private final int century;
   private final int startYear;
   private transient List patterns;

   private static Map appendDisplayNames(Calendar calendar, Locale locale, int field, StringBuilder regex) {
      Objects.requireNonNull(calendar, "calendar");
      Map<String, Integer> values = new HashMap();
      Locale actualLocale = LocaleUtils.toLocale(locale);
      Map<String, Integer> displayNames = calendar.getDisplayNames(field, 0, actualLocale);
      TreeSet<String> sorted = new TreeSet(LONGER_FIRST_LOWERCASE);
      displayNames.forEach((k, v) -> {
         String keyLc = k.toLowerCase(actualLocale);
         if (sorted.add(keyLc)) {
            values.put(keyLc, v);
         }

      });
      sorted.forEach((symbol) -> simpleQuote(regex, symbol).append('|'));
      return values;
   }

   private static ConcurrentMap getCache(int field) {
      synchronized(caches) {
         if (caches[field] == null) {
            caches[field] = new ConcurrentHashMap(3);
         }

         return caches[field];
      }
   }

   private static boolean isFormatLetter(char c) {
      return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
   }

   private static StringBuilder simpleQuote(StringBuilder sb, String value) {
      for(int i = 0; i < value.length(); ++i) {
         char c = value.charAt(i);
         switch (c) {
            case '$':
            case '(':
            case ')':
            case '*':
            case '+':
            case '.':
            case '?':
            case '[':
            case '\\':
            case '^':
            case '{':
            case '|':
               sb.append('\\');
            default:
               sb.append(c);
         }
      }

      if (sb.charAt(sb.length() - 1) == '.') {
         sb.append('?');
      }

      return sb;
   }

   protected FastDateParser(String pattern, TimeZone timeZone, Locale locale) {
      this(pattern, timeZone, locale, (Date)null);
   }

   protected FastDateParser(String pattern, TimeZone timeZone, Locale locale, Date centuryStart) {
      this.pattern = (String)Objects.requireNonNull(pattern, "pattern");
      this.timeZone = (TimeZone)Objects.requireNonNull(timeZone, "timeZone");
      this.locale = LocaleUtils.toLocale(locale);
      Calendar definingCalendar = Calendar.getInstance(timeZone, this.locale);
      int centuryStartYear;
      if (centuryStart != null) {
         definingCalendar.setTime(centuryStart);
         centuryStartYear = definingCalendar.get(1);
      } else if (this.locale.equals(JAPANESE_IMPERIAL)) {
         centuryStartYear = 0;
      } else {
         definingCalendar.setTime(new Date());
         centuryStartYear = definingCalendar.get(1) - 80;
      }

      this.century = centuryStartYear / 100 * 100;
      this.startYear = centuryStartYear - this.century;
      this.init(definingCalendar);
   }

   private int adjustYear(int twoDigitYear) {
      int trial = this.century + twoDigitYear;
      return twoDigitYear >= this.startYear ? trial : trial + 100;
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof FastDateParser)) {
         return false;
      } else {
         FastDateParser other = (FastDateParser)obj;
         return this.pattern.equals(other.pattern) && this.timeZone.equals(other.timeZone) && this.locale.equals(other.locale);
      }
   }

   public Locale getLocale() {
      return this.locale;
   }

   private Strategy getLocaleSpecificStrategy(int field, Calendar definingCalendar) {
      ConcurrentMap<Locale, Strategy> cache = getCache(field);
      return (Strategy)cache.computeIfAbsent(this.locale, (k) -> (Strategy)(field == 15 ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(field, definingCalendar, this.locale)));
   }

   public String getPattern() {
      return this.pattern;
   }

   private Strategy getStrategy(char f, int width, Calendar definingCalendar) {
      switch (f) {
         case 'D':
            return DAY_OF_YEAR_STRATEGY;
         case 'E':
            return this.getLocaleSpecificStrategy(7, definingCalendar);
         case 'F':
            return DAY_OF_WEEK_IN_MONTH_STRATEGY;
         case 'G':
            return this.getLocaleSpecificStrategy(0, definingCalendar);
         case 'H':
            return HOUR_OF_DAY_STRATEGY;
         case 'I':
         case 'J':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'b':
         case 'c':
         case 'e':
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
         case 'v':
         case 'x':
         default:
            throw new IllegalArgumentException("Format '" + f + "' not supported");
         case 'K':
            return HOUR_STRATEGY;
         case 'L':
         case 'M':
            return width >= 3 ? this.getLocaleSpecificStrategy(2, definingCalendar) : NUMBER_MONTH_STRATEGY;
         case 'S':
            return MILLISECOND_STRATEGY;
         case 'W':
            return WEEK_OF_MONTH_STRATEGY;
         case 'X':
            return FastDateParser.ISO8601TimeZoneStrategy.getStrategy(width);
         case 'Y':
         case 'y':
            return width > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
         case 'Z':
            if (width == 2) {
               return FastDateParser.ISO8601TimeZoneStrategy.ISO_8601_3_STRATEGY;
            }
         case 'z':
            return this.getLocaleSpecificStrategy(15, definingCalendar);
         case 'a':
            return this.getLocaleSpecificStrategy(9, definingCalendar);
         case 'd':
            return DAY_OF_MONTH_STRATEGY;
         case 'h':
            return HOUR12_STRATEGY;
         case 'k':
            return HOUR24_OF_DAY_STRATEGY;
         case 'm':
            return MINUTE_STRATEGY;
         case 's':
            return SECOND_STRATEGY;
         case 'u':
            return DAY_OF_WEEK_STRATEGY;
         case 'w':
            return WEEK_OF_YEAR_STRATEGY;
      }
   }

   public TimeZone getTimeZone() {
      return this.timeZone;
   }

   public int hashCode() {
      return this.pattern.hashCode() + 13 * (this.timeZone.hashCode() + 13 * this.locale.hashCode());
   }

   private void init(Calendar definingCalendar) {
      this.patterns = new ArrayList();
      StrategyParser strategyParser = new StrategyParser(definingCalendar);

      while(true) {
         StrategyAndWidth field = strategyParser.getNextStrategy();
         if (field == null) {
            return;
         }

         this.patterns.add(field);
      }
   }

   public Date parse(String source) throws ParseException {
      ParsePosition pp = new ParsePosition(0);
      Date date = this.parse(source, pp);
      if (date == null) {
         if (this.locale.equals(JAPANESE_IMPERIAL)) {
            throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + source, pp.getErrorIndex());
         } else {
            throw new ParseException("Unparseable date: " + source, pp.getErrorIndex());
         }
      } else {
         return date;
      }
   }

   public Date parse(String source, ParsePosition pos) {
      Calendar cal = Calendar.getInstance(this.timeZone, this.locale);
      cal.clear();
      return this.parse(source, pos, cal) ? cal.getTime() : null;
   }

   public boolean parse(String source, ParsePosition pos, Calendar calendar) {
      ListIterator<StrategyAndWidth> lt = this.patterns.listIterator();

      while(lt.hasNext()) {
         StrategyAndWidth strategyAndWidth = (StrategyAndWidth)lt.next();
         int maxWidth = strategyAndWidth.getMaxWidth(lt);
         if (!strategyAndWidth.strategy.parse(this, calendar, source, pos, maxWidth)) {
            return false;
         }
      }

      return true;
   }

   public Object parseObject(String source) throws ParseException {
      return this.parse(source);
   }

   public Object parseObject(String source, ParsePosition pos) {
      return this.parse(source, pos);
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      Calendar definingCalendar = Calendar.getInstance(this.timeZone, this.locale);
      this.init(definingCalendar);
   }

   public String toString() {
      return "FastDateParser[" + this.pattern + ", " + this.locale + ", " + this.timeZone.getID() + "]";
   }

   public String toStringAll() {
      return "FastDateParser [pattern=" + this.pattern + ", timeZone=" + this.timeZone + ", locale=" + this.locale + ", century=" + this.century + ", startYear=" + this.startYear + ", patterns=" + this.patterns + "]";
   }

   private static final class CaseInsensitiveTextStrategy extends PatternStrategy {
      private final int field;
      final Locale locale;
      private final Map lKeyValues;

      CaseInsensitiveTextStrategy(int field, Calendar definingCalendar, Locale locale) {
         this.field = field;
         this.locale = LocaleUtils.toLocale(locale);
         StringBuilder regex = new StringBuilder();
         regex.append("((?iu)");
         this.lKeyValues = FastDateParser.appendDisplayNames(definingCalendar, locale, field, regex);
         regex.setLength(regex.length() - 1);
         regex.append(")");
         this.createPattern(regex);
      }

      void setCalendar(FastDateParser parser, Calendar calendar, String value) {
         String lowerCase = value.toLowerCase(this.locale);
         Integer iVal = (Integer)this.lKeyValues.get(lowerCase);
         if (iVal == null) {
            iVal = (Integer)this.lKeyValues.get(lowerCase + '.');
         }

         if (9 != this.field || iVal <= 1) {
            calendar.set(this.field, iVal);
         }

      }

      public String toString() {
         return "CaseInsensitiveTextStrategy [field=" + this.field + ", locale=" + this.locale + ", lKeyValues=" + this.lKeyValues + ", pattern=" + this.pattern + "]";
      }
   }

   private static final class CopyQuotedStrategy extends Strategy {
      private final String formatField;

      CopyQuotedStrategy(String formatField) {
         this.formatField = formatField;
      }

      boolean isNumber() {
         return false;
      }

      boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
         for(int idx = 0; idx < this.formatField.length(); ++idx) {
            int sIdx = idx + pos.getIndex();
            if (sIdx == source.length()) {
               pos.setErrorIndex(sIdx);
               return false;
            }

            if (this.formatField.charAt(idx) != source.charAt(sIdx)) {
               pos.setErrorIndex(sIdx);
               return false;
            }
         }

         pos.setIndex(this.formatField.length() + pos.getIndex());
         return true;
      }

      public String toString() {
         return "CopyQuotedStrategy [formatField=" + this.formatField + "]";
      }
   }

   private static final class ISO8601TimeZoneStrategy extends PatternStrategy {
      private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
      private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
      private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");

      static Strategy getStrategy(int tokenLen) {
         switch (tokenLen) {
            case 1:
               return ISO_8601_1_STRATEGY;
            case 2:
               return ISO_8601_2_STRATEGY;
            case 3:
               return ISO_8601_3_STRATEGY;
            default:
               throw new IllegalArgumentException("invalid number of X");
         }
      }

      ISO8601TimeZoneStrategy(String pattern) {
         this.createPattern(pattern);
      }

      void setCalendar(FastDateParser parser, Calendar calendar, String value) {
         calendar.setTimeZone(FastTimeZone.getGmtTimeZone(value));
      }
   }

   private static class NumberStrategy extends Strategy {
      private final int field;

      NumberStrategy(int field) {
         this.field = field;
      }

      boolean isNumber() {
         return true;
      }

      int modify(FastDateParser parser, int iValue) {
         return iValue;
      }

      boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
         int idx = pos.getIndex();
         int last = source.length();
         if (maxWidth != 0) {
            int end = idx + maxWidth;
            if (last > end) {
               last = end;
            }
         } else {
            while(true) {
               if (idx < last) {
                  char c = source.charAt(idx);
                  if (Character.isWhitespace(c)) {
                     ++idx;
                     continue;
                  }
               }

               pos.setIndex(idx);
               break;
            }
         }

         while(idx < last) {
            char c = source.charAt(idx);
            if (!Character.isDigit(c)) {
               break;
            }

            ++idx;
         }

         if (pos.getIndex() == idx) {
            pos.setErrorIndex(idx);
            return false;
         } else {
            int value = Integer.parseInt(source.substring(pos.getIndex(), idx));
            pos.setIndex(idx);
            calendar.set(this.field, this.modify(parser, value));
            return true;
         }
      }

      public String toString() {
         return "NumberStrategy [field=" + this.field + "]";
      }
   }

   private abstract static class PatternStrategy extends Strategy {
      Pattern pattern;

      private PatternStrategy() {
      }

      void createPattern(String regex) {
         this.pattern = Pattern.compile(regex);
      }

      void createPattern(StringBuilder regex) {
         this.createPattern(regex.toString());
      }

      boolean isNumber() {
         return false;
      }

      boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
         Matcher matcher = this.pattern.matcher(source.substring(pos.getIndex()));
         if (!matcher.lookingAt()) {
            pos.setErrorIndex(pos.getIndex());
            return false;
         } else {
            pos.setIndex(pos.getIndex() + matcher.end(1));
            this.setCalendar(parser, calendar, matcher.group(1));
            return true;
         }
      }

      abstract void setCalendar(FastDateParser var1, Calendar var2, String var3);

      public String toString() {
         return this.getClass().getSimpleName() + " [pattern=" + this.pattern + "]";
      }
   }

   private abstract static class Strategy {
      private Strategy() {
      }

      boolean isNumber() {
         return false;
      }

      abstract boolean parse(FastDateParser var1, Calendar var2, String var3, ParsePosition var4, int var5);
   }

   private static final class StrategyAndWidth {
      final Strategy strategy;
      final int width;

      StrategyAndWidth(Strategy strategy, int width) {
         this.strategy = (Strategy)Objects.requireNonNull(strategy, "strategy");
         this.width = width;
      }

      int getMaxWidth(ListIterator lt) {
         if (this.strategy.isNumber() && lt.hasNext()) {
            Strategy nextStrategy = ((StrategyAndWidth)lt.next()).strategy;
            lt.previous();
            return nextStrategy.isNumber() ? this.width : 0;
         } else {
            return 0;
         }
      }

      public String toString() {
         return "StrategyAndWidth [strategy=" + this.strategy + ", width=" + this.width + "]";
      }
   }

   private final class StrategyParser {
      private final Calendar definingCalendar;
      private int currentIdx;

      StrategyParser(Calendar definingCalendar) {
         this.definingCalendar = (Calendar)Objects.requireNonNull(definingCalendar, "definingCalendar");
      }

      StrategyAndWidth getNextStrategy() {
         if (this.currentIdx >= FastDateParser.this.pattern.length()) {
            return null;
         } else {
            char c = FastDateParser.this.pattern.charAt(this.currentIdx);
            return FastDateParser.isFormatLetter(c) ? this.letterPattern(c) : this.literal();
         }
      }

      private StrategyAndWidth letterPattern(char c) {
         int begin = this.currentIdx;

         while(++this.currentIdx < FastDateParser.this.pattern.length() && FastDateParser.this.pattern.charAt(this.currentIdx) == c) {
         }

         int width = this.currentIdx - begin;
         return new StrategyAndWidth(FastDateParser.this.getStrategy(c, width, this.definingCalendar), width);
      }

      private StrategyAndWidth literal() {
         boolean activeQuote = false;
         StringBuilder sb = new StringBuilder();

         while(this.currentIdx < FastDateParser.this.pattern.length()) {
            char c = FastDateParser.this.pattern.charAt(this.currentIdx);
            if (!activeQuote && FastDateParser.isFormatLetter(c)) {
               break;
            }

            if (c == '\'' && (++this.currentIdx == FastDateParser.this.pattern.length() || FastDateParser.this.pattern.charAt(this.currentIdx) != '\'')) {
               activeQuote = !activeQuote;
            } else {
               ++this.currentIdx;
               sb.append(c);
            }
         }

         if (activeQuote) {
            throw new IllegalArgumentException("Unterminated quote");
         } else {
            String formatField = sb.toString();
            return new StrategyAndWidth(new CopyQuotedStrategy(formatField), formatField.length());
         }
      }
   }

   static class TimeZoneStrategy extends PatternStrategy {
      private static final String RFC_822_TIME_ZONE = "[+-]\\d{4}";
      private static final String GMT_OPTION = "GMT[+-]\\d{1,2}:\\d{2}";
      private static final int ID = 0;
      private final Locale locale;
      private final Map tzNames = new HashMap();

      TimeZoneStrategy(Locale locale) {
         this.locale = LocaleUtils.toLocale(locale);
         StringBuilder sb = new StringBuilder();
         sb.append("((?iu)[+-]\\d{4}|GMT[+-]\\d{1,2}:\\d{2}");
         Set<String> sorted = new TreeSet(FastDateParser.LONGER_FIRST_LOWERCASE);
         String[][] zones = DateFormatSymbols.getInstance(locale).getZoneStrings();

         for(String[] zoneNames : zones) {
            String tzId = zoneNames[0];
            if (!tzId.equalsIgnoreCase("GMT")) {
               TimeZone tz = TimeZone.getTimeZone(tzId);
               TzInfo standard = new TzInfo(tz, false);
               TzInfo tzInfo = standard;

               for(int i = 1; i < zoneNames.length; ++i) {
                  switch (i) {
                     case 3:
                        tzInfo = new TzInfo(tz, true);
                        break;
                     case 5:
                        tzInfo = standard;
                  }

                  String zoneName = zoneNames[i];
                  if (zoneName != null) {
                     String key = zoneName.toLowerCase(locale);
                     if (sorted.add(key)) {
                        this.tzNames.put(key, tzInfo);
                     }
                  }
               }
            }
         }

         for(String tzId : TimeZone.getAvailableIDs()) {
            if (!tzId.equalsIgnoreCase("GMT")) {
               TimeZone tz = TimeZone.getTimeZone(tzId);
               String zoneName = tz.getDisplayName(locale);
               String key = zoneName.toLowerCase(locale);
               if (sorted.add(key)) {
                  this.tzNames.put(key, new TzInfo(tz, tz.observesDaylightTime()));
               }
            }
         }

         sorted.forEach((zoneNamex) -> FastDateParser.simpleQuote(sb.append('|'), zoneNamex));
         sb.append(")");
         this.createPattern(sb);
      }

      void setCalendar(FastDateParser parser, Calendar calendar, String timeZone) {
         TimeZone tz = FastTimeZone.getGmtTimeZone(timeZone);
         if (tz != null) {
            calendar.setTimeZone(tz);
         } else {
            String lowerCase = timeZone.toLowerCase(this.locale);
            TzInfo tzInfo = (TzInfo)this.tzNames.get(lowerCase);
            if (tzInfo == null) {
               tzInfo = (TzInfo)this.tzNames.get(lowerCase + '.');
            }

            calendar.set(16, tzInfo.dstOffset);
            calendar.set(15, tzInfo.zone.getRawOffset());
         }

      }

      public String toString() {
         return "TimeZoneStrategy [locale=" + this.locale + ", tzNames=" + this.tzNames + ", pattern=" + this.pattern + "]";
      }

      private static final class TzInfo {
         final TimeZone zone;
         final int dstOffset;

         TzInfo(TimeZone tz, boolean useDst) {
            this.zone = tz;
            this.dstOffset = useDst ? tz.getDSTSavings() : 0;
         }

         public String toString() {
            return "TzInfo [zone=" + this.zone + ", dstOffset=" + this.dstOffset + "]";
         }
      }
   }
}
