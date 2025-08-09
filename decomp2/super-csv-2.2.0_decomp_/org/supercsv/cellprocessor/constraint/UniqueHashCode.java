package org.supercsv.cellprocessor.constraint;

import java.util.HashSet;
import java.util.Set;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.util.CsvContext;

public class UniqueHashCode extends CellProcessorAdaptor {
   private final Set uniqueSet = new HashSet();

   public UniqueHashCode() {
   }

   public UniqueHashCode(CellProcessor next) {
      super(next);
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      int hash = value.hashCode();
      if (!this.uniqueSet.add(hash)) {
         throw new SuperCsvConstraintViolationException(String.format("duplicate value '%s' encountered with hashcode %d", value, hash), context, this);
      } else {
         return this.next.execute(value, context);
      }
   }
}
