package org.supercsv.cellprocessor;

import java.util.Collection;
import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.DoubleCellProcessor;
import org.supercsv.cellprocessor.ift.LongCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

public class Collector extends CellProcessorAdaptor implements BoolCellProcessor, DateCellProcessor, DoubleCellProcessor, LongCellProcessor, StringCellProcessor {
   private final Collection collection;

   public Collector(Collection collection) {
      checkPreconditions(collection);
      this.collection = collection;
   }

   public Collector(Collection collection, CellProcessor next) {
      super(next);
      checkPreconditions(collection);
      this.collection = collection;
   }

   private static void checkPreconditions(Collection collection) {
      if (collection == null) {
         throw new NullPointerException("collection should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.collection.add(value);
      return this.next.execute(value, context);
   }

   public Collection getCollection() {
      return this.collection;
   }
}
