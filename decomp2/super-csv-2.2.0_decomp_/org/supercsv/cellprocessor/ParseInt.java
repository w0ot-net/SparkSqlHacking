package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseInt extends CellProcessorAdaptor implements StringCellProcessor {
   public ParseInt() {
   }

   public ParseInt(LongCellProcessor next) {
      super(next);
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      Integer result;
      if (value instanceof Integer) {
         result = (Integer)value;
      } else {
         if (!(value instanceof String)) {
            String actualClassName = value.getClass().getName();
            throw new SuperCsvCellProcessorException(String.format("the input value should be of type Integer or String but is of type %s", actualClassName), context, this);
         }

         try {
            result = Integer.valueOf((String)value);
         } catch (NumberFormatException e) {
            throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as an Integer", value), context, this, e);
         }
      }

      return this.next.execute(result, context);
   }
}
