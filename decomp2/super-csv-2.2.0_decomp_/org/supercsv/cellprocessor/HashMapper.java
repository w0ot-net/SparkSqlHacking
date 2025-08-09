package org.supercsv.cellprocessor;

import java.util.Map;
import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

public class HashMapper extends CellProcessorAdaptor implements BoolCellProcessor, DateCellProcessor, DoubleCellProcessor, LongCellProcessor, StringCellProcessor {
   private final Map mapping;
   private final Object defaultValue;

   public HashMapper(Map mapping) {
      this(mapping, (Object)null);
   }

   public HashMapper(Map mapping, Object defaultValue) {
      checkPreconditions(mapping);
      this.mapping = mapping;
      this.defaultValue = defaultValue;
   }

   public HashMapper(Map mapping, CellProcessor next) {
      this(mapping, (Object)null, next);
   }

   public HashMapper(Map mapping, Object defaultValue, CellProcessor next) {
      super(next);
      checkPreconditions(mapping);
      this.mapping = mapping;
      this.defaultValue = defaultValue;
   }

   private static void checkPreconditions(Map mapping) {
      if (mapping == null) {
         throw new NullPointerException("mapping should not be null");
      } else if (mapping.isEmpty()) {
         throw new IllegalArgumentException("mapping should not be empty");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      Object result = this.mapping.get(value);
      if (result == null) {
         result = this.defaultValue;
      }

      return this.next.execute(result, context);
   }
}
