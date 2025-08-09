package org.supercsv.cellprocessor.constraint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.util.CsvContext;

public class IsIncludedIn extends CellProcessorAdaptor implements BoolCellProcessor, DateCellProcessor, DoubleCellProcessor, LongCellProcessor, StringCellProcessor {
   private final Set possibleValues = new HashSet();

   public IsIncludedIn(Set possibleValues) {
      checkPreconditions(possibleValues);
      this.possibleValues.addAll(possibleValues);
   }

   public IsIncludedIn(Set possibleValues, CellProcessor next) {
      super(next);
      checkPreconditions(possibleValues);
      this.possibleValues.addAll(possibleValues);
   }

   public IsIncludedIn(Object[] possibleValues) {
      checkPreconditions(possibleValues);
      Collections.addAll(this.possibleValues, possibleValues);
   }

   public IsIncludedIn(Object[] possibleValues, CellProcessor next) {
      super(next);
      checkPreconditions(possibleValues);
      Collections.addAll(this.possibleValues, possibleValues);
   }

   private static void checkPreconditions(Set possibleValues) {
      if (possibleValues == null) {
         throw new NullPointerException("possibleValues Set should not be null");
      } else if (possibleValues.isEmpty()) {
         throw new IllegalArgumentException("possibleValues Set should not be empty");
      }
   }

   private static void checkPreconditions(Object... possibleValues) {
      if (possibleValues == null) {
         throw new NullPointerException("possibleValues array should not be null");
      } else if (possibleValues.length == 0) {
         throw new IllegalArgumentException("possibleValues array should not be empty");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      if (!this.possibleValues.contains(value)) {
         throw new SuperCsvConstraintViolationException(String.format("'%s' is not included in the allowed set of values", value), context, this);
      } else {
         return this.next.execute(value, context);
      }
   }
}
