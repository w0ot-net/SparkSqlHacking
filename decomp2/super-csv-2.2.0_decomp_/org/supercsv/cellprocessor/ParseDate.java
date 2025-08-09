package org.supercsv.cellprocessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseDate extends CellProcessorAdaptor implements StringCellProcessor {
   private final String dateFormat;
   private final boolean lenient;
   private final Locale locale;

   public ParseDate(String dateFormat) {
      this(dateFormat, false);
   }

   public ParseDate(String dateFormat, boolean lenient) {
      checkPreconditions(dateFormat);
      this.dateFormat = dateFormat;
      this.lenient = lenient;
      this.locale = null;
   }

   public ParseDate(String dateFormat, boolean lenient, Locale locale) {
      checkPreconditions(dateFormat, locale);
      this.dateFormat = dateFormat;
      this.lenient = lenient;
      this.locale = locale;
   }

   public ParseDate(String dateFormat, DateCellProcessor next) {
      this(dateFormat, false, next);
   }

   public ParseDate(String dateFormat, boolean lenient, DateCellProcessor next) {
      super(next);
      checkPreconditions(dateFormat);
      this.dateFormat = dateFormat;
      this.lenient = lenient;
      this.locale = null;
   }

   public ParseDate(String dateFormat, boolean lenient, Locale locale, DateCellProcessor next) {
      super(next);
      checkPreconditions(dateFormat, locale);
      this.dateFormat = dateFormat;
      this.lenient = lenient;
      this.locale = locale;
   }

   private static void checkPreconditions(String dateFormat) {
      if (dateFormat == null) {
         throw new NullPointerException("dateFormat should not be null");
      }
   }

   private static void checkPreconditions(String dateFormat, Locale locale) {
      if (dateFormat == null) {
         throw new NullPointerException("dateFormat should not be null");
      } else if (locale == null) {
         throw new NullPointerException("locale should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      if (!(value instanceof String)) {
         throw new SuperCsvCellProcessorException(String.class, value, context, this);
      } else {
         try {
            SimpleDateFormat formatter;
            if (this.locale == null) {
               formatter = new SimpleDateFormat(this.dateFormat);
            } else {
               formatter = new SimpleDateFormat(this.dateFormat, this.locale);
            }

            formatter.setLenient(this.lenient);
            Date result = formatter.parse((String)value);
            return this.next.execute(result, context);
         } catch (ParseException e) {
            throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as a Date", value), context, this, e);
         }
      }
   }
}
