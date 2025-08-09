package com.ibm.icu.message2;

import com.ibm.icu.impl.JavaTimeConverters;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.SimpleTimeZone;
import com.ibm.icu.util.TimeZone;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DateTimeFormatterFactory implements FormatterFactory {
   private final String kind;
   private static final Pattern ISO_PATTERN = Pattern.compile("^(([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])){1}(T([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])(\\.[0-9]{1,3})?(Z|[+-]((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?$");

   DateTimeFormatterFactory(String kind) {
      switch (kind) {
         default:
            kind = "datetime";
         case "date":
         case "time":
         case "datetime":
            this.kind = kind;
      }
   }

   private static int stringToStyle(String option) {
      switch (option) {
         case "full":
            return 0;
         case "long":
            return 1;
         case "medium":
            return 2;
         case "short":
            return 3;
         default:
            throw new IllegalArgumentException("Invalid datetime style: " + option);
      }
   }

   public Formatter createFormatter(Locale locale, Map fixedOptions) {
      int dateStyle = -1;
      int timeStyle = -1;
      switch (this.kind) {
         case "date":
            dateStyle = getDateTimeStyle(fixedOptions, "style");
            break;
         case "time":
            timeStyle = getDateTimeStyle(fixedOptions, "style");
            break;
         case "datetime":
         default:
            dateStyle = getDateTimeStyle(fixedOptions, "dateStyle");
            timeStyle = getDateTimeStyle(fixedOptions, "timeStyle");
      }

      if (dateStyle == -1 && timeStyle == -1) {
         skeleton = "";
         switch (this.kind) {
            case "date":
               skeleton = getDateFieldOptions(fixedOptions);
               break;
            case "time":
               skeleton = getTimeFieldOptions(fixedOptions);
               break;
            case "datetime":
            default:
               skeleton = getDateFieldOptions(fixedOptions);
               skeleton = skeleton + getTimeFieldOptions(fixedOptions);
         }

         if (skeleton.isEmpty()) {
            skeleton = OptUtils.getString(fixedOptions, "icu:skeleton", "");
         }

         if (!skeleton.isEmpty()) {
            DateFormat df = DateFormat.getInstanceForSkeleton(skeleton, locale);
            return new DateTimeFormatter(locale, df);
         }

         switch (this.kind) {
            case "date":
               dateStyle = 3;
               timeStyle = -1;
               break;
            case "time":
               dateStyle = -1;
               timeStyle = 3;
               break;
            case "datetime":
            default:
               dateStyle = 3;
               timeStyle = 3;
         }
      }

      DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
      return new DateTimeFormatter(locale, df);
   }

   private static int getDateTimeStyle(Map options, String key) {
      String opt = OptUtils.getString(options, key);
      return opt != null ? stringToStyle(opt) : -1;
   }

   private static String getDateFieldOptions(Map options) {
      StringBuilder skeleton = new StringBuilder();
      switch (OptUtils.getString(options, "weekday", "")) {
         case "long":
            skeleton.append("EEEE");
            break;
         case "short":
            skeleton.append("E");
            break;
         case "narrow":
            skeleton.append("EEEEEE");
      }

      switch (OptUtils.getString(options, "era", "")) {
         case "long":
            skeleton.append("GGGG");
            break;
         case "short":
            skeleton.append("G");
            break;
         case "narrow":
            skeleton.append("GGGGG");
      }

      switch (OptUtils.getString(options, "year", "")) {
         case "numeric":
            skeleton.append("y");
            break;
         case "2-digit":
            skeleton.append("yy");
      }

      switch (OptUtils.getString(options, "month", "")) {
         case "numeric":
            skeleton.append("M");
            break;
         case "2-digit":
            skeleton.append("MM");
            break;
         case "long":
            skeleton.append("MMMM");
            break;
         case "short":
            skeleton.append("MMM");
            break;
         case "narrow":
            skeleton.append("MMMMM");
      }

      switch (OptUtils.getString(options, "day", "")) {
         case "numeric":
            skeleton.append("d");
            break;
         case "2-digit":
            skeleton.append("dd");
      }

      return skeleton.toString();
   }

   private static String getTimeFieldOptions(Map options) {
      StringBuilder skeleton = new StringBuilder();
      int showHour = 0;
      switch (OptUtils.getString(options, "hour", "")) {
         case "numeric":
            showHour = 1;
            break;
         case "2-digit":
            showHour = 2;
      }

      if (showHour > 0) {
         String hourCycle = "";
         switch (OptUtils.getString(options, "hourCycle", "")) {
            case "h11":
               hourCycle = "K";
               break;
            case "h12":
               hourCycle = "h";
               break;
            case "h23":
               hourCycle = "H";
               break;
            case "h24":
               hourCycle = "k";
               break;
            default:
               hourCycle = "j";
         }

         skeleton.append(hourCycle);
         if (showHour == 2) {
            skeleton.append(hourCycle);
         }
      }

      switch (OptUtils.getString(options, "minute", "")) {
         case "numeric":
            skeleton.append("m");
            break;
         case "2-digit":
            skeleton.append("mm");
      }

      switch (OptUtils.getString(options, "second", "")) {
         case "numeric":
            skeleton.append("s");
            break;
         case "2-digit":
            skeleton.append("ss");
      }

      switch (OptUtils.getString(options, "fractionalSecondDigits", "")) {
         case "1":
            skeleton.append("S");
            break;
         case "2":
            skeleton.append("SS");
            break;
         case "3":
            skeleton.append("SSS");
      }

      switch (OptUtils.getString(options, "timeZoneName", "")) {
         case "long":
            skeleton.append("z");
            break;
         case "short":
            skeleton.append("zzzz");
            break;
         case "shortOffset":
            skeleton.append("O");
            break;
         case "longOffset":
            skeleton.append("OOOO");
            break;
         case "shortGeneric":
            skeleton.append("v");
            break;
         case "longGeneric":
            skeleton.append("vvvv");
      }

      return skeleton.toString();
   }

   private static Integer safeParse(String str) {
      return str != null && !str.isEmpty() ? Integer.parseInt(str) : null;
   }

   private static Object parseIso8601(String text) {
      Matcher m = ISO_PATTERN.matcher(text);
      if (m.find() && m.groupCount() == 12 && !m.group().isEmpty()) {
         Integer year = safeParse(m.group(2));
         Integer month = safeParse(m.group(3));
         Integer day = safeParse(m.group(4));
         Integer hour = safeParse(m.group(6));
         Integer minute = safeParse(m.group(7));
         Integer second = safeParse(m.group(8));
         Integer millisecond = 0;
         if (m.group(9) != null) {
            String z = (m.group(9) + "000").substring(1, 4);
            millisecond = safeParse(z);
         } else {
            millisecond = 0;
         }

         String tzPart = m.group(10);
         if (hour == null) {
            hour = 0;
            minute = 0;
            second = 0;
         }

         GregorianCalendar gc = new GregorianCalendar(year, month - 1, day, hour, minute, second);
         gc.set(14, millisecond);
         if (tzPart != null) {
            if (tzPart.equals("Z")) {
               gc.setTimeZone(TimeZone.GMT_ZONE);
            } else {
               int sign = tzPart.startsWith("-") ? -1 : 1;
               String[] tzParts = tzPart.substring(1).split(":");
               if (tzParts.length == 2) {
                  Integer tzHour = safeParse(tzParts[0]);
                  Integer tzMin = safeParse(tzParts[1]);
                  if (tzHour != null && tzMin != null) {
                     int offset = sign * (tzHour * 60 + tzMin) * 60 * 1000;
                     gc.setTimeZone(new SimpleTimeZone(offset, "offset"));
                  }
               }
            }
         }

         return gc;
      } else {
         return text;
      }
   }

   private static class DateTimeFormatter implements Formatter {
      private final DateFormat icuFormatter;
      private final Locale locale;

      private DateTimeFormatter(Locale locale, DateFormat df) {
         this.locale = locale;
         this.icuFormatter = df;
      }

      public FormattedPlaceholder format(Object toFormat, Map variableOptions) {
         if (toFormat == null) {
            return null;
         } else {
            if (toFormat instanceof CharSequence) {
               toFormat = DateTimeFormatterFactory.parseIso8601(toFormat.toString());
               if (toFormat instanceof CharSequence) {
                  return new FormattedPlaceholder(toFormat, new PlainStringFormattedValue("{|" + toFormat + "|}"));
               }
            } else if (toFormat instanceof Temporal) {
               toFormat = JavaTimeConverters.temporalToCalendar((Temporal)toFormat);
            }

            if (toFormat instanceof Calendar) {
               java.util.TimeZone tz = ((Calendar)toFormat).getTimeZone();
               long milis = ((Calendar)toFormat).getTimeInMillis();
               TimeZone icuTz = TimeZone.getTimeZone(tz.getID());
               com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(icuTz, this.locale);
               calendar.setTimeInMillis(milis);
               toFormat = calendar;
            }

            String result = this.icuFormatter.format((Object)toFormat);
            return new FormattedPlaceholder(toFormat, new PlainStringFormattedValue(result));
         }
      }

      public String formatToString(Object toFormat, Map variableOptions) {
         FormattedPlaceholder result = this.format(toFormat, variableOptions);
         return result != null ? result.toString() : null;
      }
   }
}
