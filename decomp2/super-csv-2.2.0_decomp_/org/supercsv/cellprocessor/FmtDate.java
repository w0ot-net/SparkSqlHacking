package org.supercsv.cellprocessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class FmtDate extends CellProcessorAdaptor implements DateCellProcessor {
   private final String dateFormat;

   public FmtDate(String dateFormat) {
      checkPreconditions(dateFormat);
      this.dateFormat = dateFormat;
   }

   public FmtDate(String dateFormat, StringCellProcessor next) {
      super(next);
      checkPreconditions(dateFormat);
      this.dateFormat = dateFormat;
   }

   private static void checkPreconditions(String dateFormat) {
      if (dateFormat == null) {
         throw new NullPointerException("dateFormat should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      if (!(value instanceof Date)) {
         throw new SuperCsvCellProcessorException(Date.class, value, context, this);
      } else {
         SimpleDateFormat formatter;
         try {
            formatter = new SimpleDateFormat(this.dateFormat);
         } catch (IllegalArgumentException e) {
            throw new SuperCsvCellProcessorException(String.format("'%s' is not a valid date format", this.dateFormat), context, this, e);
         }

         String result = formatter.format((Date)value);
         return this.next.execute(result, context);
      }
   }
}
