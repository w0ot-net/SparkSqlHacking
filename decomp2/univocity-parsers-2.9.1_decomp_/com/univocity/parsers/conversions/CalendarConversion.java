package com.univocity.parsers.conversions;

import com.univocity.parsers.common.ArgumentUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarConversion extends ObjectConversion implements FormattedConversion {
   private final DateConversion dateConversion;

   public CalendarConversion(TimeZone timeZone, Locale locale, Calendar valueIfStringIsNull, String valueIfObjectIsNull, String... dateFormats) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      ArgumentUtils.noNulls("Date formats", dateFormats);
      this.dateConversion = new DateConversion(locale, dateFormats);
   }

   public CalendarConversion(Locale locale, Calendar valueIfStringIsNull, String valueIfObjectIsNull, String... dateFormats) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      ArgumentUtils.noNulls("Date formats", dateFormats);
      this.dateConversion = new DateConversion(locale, dateFormats);
   }

   public CalendarConversion(Calendar valueIfStringIsNull, String valueIfObjectIsNull, String... dateFormats) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      ArgumentUtils.noNulls("Date formats", dateFormats);
      this.dateConversion = new DateConversion(Locale.getDefault(), dateFormats);
   }

   public CalendarConversion(Locale locale, String... dateFormats) {
      this(locale, (Calendar)null, (String)null, dateFormats);
   }

   public CalendarConversion(String... dateFormats) {
      this(Locale.getDefault(), (Calendar)null, (String)null, dateFormats);
   }

   public String revert(Calendar input) {
      return input == null ? super.revert((Object)null) : this.dateConversion.revert(input.getTime());
   }

   protected Calendar fromString(String input) {
      Date date = (Date)this.dateConversion.execute(input);
      Calendar out = Calendar.getInstance();
      out.setTime(date);
      out.setTimeZone(this.dateConversion.getTimeZone());
      return out;
   }

   public SimpleDateFormat[] getFormatterObjects() {
      return this.dateConversion.getFormatterObjects();
   }
}
