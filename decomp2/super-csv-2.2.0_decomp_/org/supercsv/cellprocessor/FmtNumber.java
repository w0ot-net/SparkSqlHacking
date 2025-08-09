package org.supercsv.cellprocessor;

import java.text.DecimalFormat;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class FmtNumber extends CellProcessorAdaptor implements DoubleCellProcessor, LongCellProcessor {
   private final String decimalFormat;
   private final DecimalFormat formatter;

   public FmtNumber(String decimalFormat) {
      checkPreconditions(decimalFormat);
      this.decimalFormat = decimalFormat;
      this.formatter = null;
   }

   public FmtNumber(String decimalFormat, StringCellProcessor next) {
      super(next);
      checkPreconditions(decimalFormat);
      this.decimalFormat = decimalFormat;
      this.formatter = null;
   }

   public FmtNumber(DecimalFormat formatter) {
      checkPreconditions(formatter);
      this.formatter = formatter;
      this.decimalFormat = null;
   }

   public FmtNumber(DecimalFormat formatter, StringCellProcessor next) {
      super(next);
      checkPreconditions(formatter);
      this.formatter = formatter;
      this.decimalFormat = null;
   }

   private static void checkPreconditions(String dateFormat) {
      if (dateFormat == null) {
         throw new NullPointerException("dateFormat should not be null");
      }
   }

   private static void checkPreconditions(DecimalFormat formatter) {
      if (formatter == null) {
         throw new NullPointerException("formatter should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      if (!(value instanceof Number)) {
         throw new SuperCsvCellProcessorException(Number.class, value, context, this);
      } else {
         DecimalFormat decimalFormatter;
         try {
            decimalFormatter = this.formatter != null ? this.formatter : new DecimalFormat(this.decimalFormat);
         } catch (IllegalArgumentException e) {
            throw new SuperCsvCellProcessorException(String.format("'%s' is not a valid decimal format", this.decimalFormat), context, this, e);
         }

         String result = decimalFormatter.format(value);
         return this.next.execute(result, context);
      }
   }
}
