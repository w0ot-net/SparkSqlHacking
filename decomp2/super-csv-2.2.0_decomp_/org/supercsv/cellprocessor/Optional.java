package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.CellProcessor;

public class Optional extends ConvertNullTo {
   public Optional() {
      super((Object)null);
   }

   public Optional(CellProcessor next) {
      super((Object)null, next);
   }
}
