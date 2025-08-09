package com.univocity.parsers.conversions;

import com.univocity.parsers.common.DataProcessingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormattedDateConversion implements Conversion {
   private final SimpleDateFormat dateFormat;
   private final String valueIfObjectIsNull;

   public FormattedDateConversion(String format, Locale locale, String valueIfObjectIsNull) {
      this.valueIfObjectIsNull = valueIfObjectIsNull;
      locale = locale == null ? Locale.getDefault() : locale;
      this.dateFormat = new SimpleDateFormat(format, locale);
   }

   public String execute(Object input) {
      if (input == null) {
         return this.valueIfObjectIsNull;
      } else {
         Date date = null;
         if (input instanceof Date) {
            date = (Date)input;
         } else if (input instanceof Calendar) {
            date = ((Calendar)input).getTime();
         }

         if (date != null) {
            return this.dateFormat.format(date);
         } else {
            DataProcessingException exception = new DataProcessingException("Cannot format '{value}' to a date. Not an instance of java.util.Date or java.util.Calendar");
            exception.setValue(input);
            throw exception;
         }
      }
   }

   public Object revert(String input) {
      throw new UnsupportedOperationException("Can't convert an input string into date type");
   }
}
