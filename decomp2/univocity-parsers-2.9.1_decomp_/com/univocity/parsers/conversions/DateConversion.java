package com.univocity.parsers.conversions;

import [Ljava.lang.String;;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataProcessingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateConversion extends ObjectConversion implements FormattedConversion {
   private final Locale locale;
   private final TimeZone timeZone;
   private final SimpleDateFormat[] parsers;
   private final String[] formats;

   public DateConversion(TimeZone timeZone, Locale locale, Date valueIfStringIsNull, String valueIfObjectIsNull, String... dateFormats) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      ArgumentUtils.noNulls("Date formats", dateFormats);
      this.timeZone = timeZone == null ? TimeZone.getDefault() : timeZone;
      this.locale = locale == null ? Locale.getDefault() : locale;
      this.formats = (String[])((String;)dateFormats).clone();
      this.parsers = new SimpleDateFormat[dateFormats.length];

      for(int i = 0; i < dateFormats.length; ++i) {
         String dateFormat = dateFormats[i];
         this.parsers[i] = new SimpleDateFormat(dateFormat, this.locale);
         this.parsers[i].setTimeZone(this.timeZone);
      }

   }

   public DateConversion(Locale locale, Date valueIfStringIsNull, String valueIfObjectIsNull, String... dateFormats) {
      this(TimeZone.getDefault(), locale, valueIfStringIsNull, valueIfObjectIsNull, dateFormats);
   }

   public DateConversion(Date valueIfStringIsNull, String valueIfObjectIsNull, String... dateFormats) {
      this(Locale.getDefault(), valueIfStringIsNull, valueIfObjectIsNull, dateFormats);
   }

   public DateConversion(Locale locale, String... dateFormats) {
      this(locale, (Date)null, (String)null, dateFormats);
   }

   public DateConversion(String... dateFormats) {
      this(Locale.getDefault(), (Date)null, (String)null, dateFormats);
   }

   public String revert(Date input) {
      return input == null ? super.revert((Object)null) : this.parsers[0].format(input);
   }

   protected Date fromString(String input) {
      for(SimpleDateFormat formatter : this.parsers) {
         try {
            synchronized(formatter) {
               return formatter.parse(input);
            }
         } catch (ParseException var9) {
         }
      }

      DataProcessingException exception = new DataProcessingException("Cannot parse '{value}' as a valid date of locale '" + this.locale + "'. Supported formats are: " + Arrays.toString(this.formats));
      exception.setValue(input);
      throw exception;
   }

   public SimpleDateFormat[] getFormatterObjects() {
      return this.parsers;
   }

   public TimeZone getTimeZone() {
      return this.timeZone;
   }
}
