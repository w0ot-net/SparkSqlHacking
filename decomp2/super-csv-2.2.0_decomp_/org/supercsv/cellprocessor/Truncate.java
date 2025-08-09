package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

public class Truncate extends CellProcessorAdaptor implements BoolCellProcessor, DateCellProcessor, DoubleCellProcessor, LongCellProcessor, StringCellProcessor {
   private static final String EMPTY_STRING = "";
   private final int maxSize;
   private final String suffix;

   public Truncate(int maxSize) {
      this(maxSize, "");
   }

   public Truncate(int maxSize, String suffix) {
      checkPreconditions(maxSize, suffix);
      this.maxSize = maxSize;
      this.suffix = suffix;
   }

   public Truncate(int maxSize, String suffix, StringCellProcessor next) {
      super(next);
      checkPreconditions(maxSize, suffix);
      this.maxSize = maxSize;
      this.suffix = suffix;
   }

   public Truncate(int maxSize, StringCellProcessor next) {
      this(maxSize, "", next);
   }

   private static void checkPreconditions(int maxSize, String suffix) {
      if (maxSize <= 0) {
         throw new IllegalArgumentException(String.format("maxSize should be > 0 but was %d", maxSize));
      } else if (suffix == null) {
         throw new NullPointerException("suffix should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      String stringValue = value.toString();
      String result;
      if (stringValue.length() <= this.maxSize) {
         result = stringValue;
      } else {
         result = stringValue.substring(0, this.maxSize) + this.suffix;
      }

      return this.next.execute(result, context);
   }
}
