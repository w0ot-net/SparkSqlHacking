package org.supercsv.cellprocessor.constraint;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.util.CsvContext;

public class DMinMax extends CellProcessorAdaptor implements StringCellProcessor {
   public static final double MAX_DOUBLE = Double.MAX_VALUE;
   public static final double MIN_DOUBLE = Double.MIN_VALUE;
   public static final double MAX_SHORT = (double)32767.0F;
   public static final double MIN_SHORT = (double)-32768.0F;
   public static final double MAX_CHAR = (double)65535.0F;
   public static final double MIN_CHAR = (double)0.0F;
   public static final int MAX_8_BIT_UNSIGNED = 255;
   public static final int MIN_8_BIT_UNSIGNED = 0;
   public static final int MAX_8_BIT_SIGNED = 127;
   public static final int MIN_8_BIT_SIGNED = -128;
   private final double min;
   private final double max;

   public DMinMax(double min, double max) {
      checkPreconditions(min, max);
      this.min = min;
      this.max = max;
   }

   public DMinMax(double min, double max, DoubleCellProcessor next) {
      super(next);
      checkPreconditions(min, max);
      this.min = min;
      this.max = max;
   }

   private static void checkPreconditions(double min, double max) {
      if (max < min) {
         throw new IllegalArgumentException(String.format("max (%f) should not be < min (%f)", max, min));
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      Double result;
      if (value instanceof Double) {
         result = (Double)value;
      } else {
         try {
            result = Double.parseDouble(value.toString());
         } catch (NumberFormatException e) {
            throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as a Double", value), context, this, e);
         }
      }

      if (!(result < this.min) && !(result > this.max)) {
         return this.next.execute(result, context);
      } else {
         throw new SuperCsvConstraintViolationException(String.format("%f does not lie between the min (%f) and max (%f) values (inclusive)", result, this.min, this.max), context, this);
      }
   }
}
