package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

public class Token extends CellProcessorAdaptor implements BoolCellProcessor, DateCellProcessor, DoubleCellProcessor, LongCellProcessor, StringCellProcessor {
   private final Object returnValue;
   private final Object token;

   public Token(Object token, Object returnValue) {
      this.token = token;
      this.returnValue = returnValue;
   }

   public Token(Object token, Object returnValue, CellProcessor next) {
      super(next);
      this.token = token;
      this.returnValue = returnValue;
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      return value.equals(this.token) ? this.returnValue : this.next.execute(value, context);
   }
}
