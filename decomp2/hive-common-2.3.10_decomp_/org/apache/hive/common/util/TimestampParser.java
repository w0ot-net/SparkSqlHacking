package org.apache.hive.common.util;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimeParserBucket;
import org.joda.time.format.DateTimePrinter;

public class TimestampParser {
   protected static final String[] stringArray = new String[0];
   protected static final String millisFormatString = "millis";
   protected static final DateTime startingDateValue = new DateTime(1970, 1, 1, 0, 0, 0, 0);
   protected String[] formatStrings;
   protected DateTimeFormatter fmt;

   public TimestampParser() {
      this.formatStrings = null;
      this.fmt = null;
   }

   public TimestampParser(TimestampParser tsParser) {
      this(tsParser.formatStrings == null ? null : (String[])Arrays.copyOf(tsParser.formatStrings, tsParser.formatStrings.length));
   }

   public TimestampParser(List formatStrings) {
      this(formatStrings == null ? null : (String[])formatStrings.toArray(stringArray));
   }

   public TimestampParser(String[] formatStrings) {
      this.formatStrings = null;
      this.fmt = null;
      this.formatStrings = formatStrings;
      if (formatStrings != null && formatStrings.length > 0) {
         DateTimeParser[] parsers = new DateTimeParser[formatStrings.length];

         for(int idx = 0; idx < formatStrings.length; ++idx) {
            String formatString = formatStrings[idx];
            if (formatString.equalsIgnoreCase("millis")) {
               parsers[idx] = new MillisDateFormatParser();
            } else {
               parsers[idx] = DateTimeFormat.forPattern(formatString).getParser();
            }
         }

         this.fmt = (new DateTimeFormatterBuilder()).append((DateTimePrinter)null, parsers).toFormatter();
      }

   }

   public Timestamp parseTimestamp(String strValue) throws IllegalArgumentException {
      if (this.fmt != null) {
         MutableDateTime mdt = new MutableDateTime(startingDateValue);
         int ret = this.fmt.parseInto(mdt, strValue, 0);
         if (ret == strValue.length()) {
            return new Timestamp(mdt.getMillis());
         }
      }

      return Timestamp.valueOf(strValue);
   }

   public static class MillisDateFormatParser implements DateTimeParser {
      private static final ThreadLocal numericMatcher = new ThreadLocal() {
         protected Matcher initialValue() {
            return Pattern.compile("(-?\\d+)(\\.\\d+)?$").matcher("");
         }
      };
      private static final DateTimeFieldType[] dateTimeFields = new DateTimeFieldType[]{DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};

      public int estimateParsedLength() {
         return 13;
      }

      public int parseInto(DateTimeParserBucket bucket, String text, int position) {
         String substr = text.substring(position);
         Matcher matcher = (Matcher)numericMatcher.get();
         matcher.reset(substr);
         if (!matcher.matches()) {
            return -1;
         } else {
            long millis = Long.parseLong(matcher.group(1));
            DateTime dt = new DateTime(millis);

            for(DateTimeFieldType field : dateTimeFields) {
               bucket.saveField(field, dt.get(field));
            }

            return substr.length();
         }
      }
   }
}
