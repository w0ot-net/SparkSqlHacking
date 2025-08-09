package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseChar extends CellProcessorAdaptor implements StringCellProcessor {
   public ParseChar() {
   }

   public ParseChar(DoubleCellProcessor next) {
      super(next);
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      Character result;
      if (value instanceof Character) {
         result = (Character)value;
      } else {
         if (!(value instanceof String)) {
            String actualClassName = value.getClass().getName();
            throw new SuperCsvCellProcessorException(String.format("the input value should be of type Character or String but is of type %s", actualClassName), context, this);
         }

         String stringValue = (String)value;
         if (stringValue.length() != 1) {
            throw new SuperCsvCellProcessorException(String.format("'%s' cannot be parsed as a char as it is a String longer than 1 character", stringValue), context, this);
         }

         result = stringValue.charAt(0);
      }

      return this.next.execute(result, context);
   }
}
