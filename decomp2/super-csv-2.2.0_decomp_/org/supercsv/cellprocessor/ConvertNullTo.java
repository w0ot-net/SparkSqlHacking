package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

public class ConvertNullTo extends CellProcessorAdaptor implements BoolCellProcessor, DateCellProcessor, DoubleCellProcessor, LongCellProcessor, StringCellProcessor {
   private final Object returnValue;

   public ConvertNullTo(Object returnValue) {
      this.returnValue = returnValue;
   }

   public ConvertNullTo(Object returnValue, CellProcessor next) {
      super(next);
      this.returnValue = returnValue;
   }

   public Object execute(Object value, CsvContext context) {
      return value == null ? this.returnValue : this.next.execute(value, context);
   }
}
