package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.DefaultConversionProcessor;

public abstract class AbstractObjectProcessor extends DefaultConversionProcessor implements Processor {
   public void rowProcessed(String[] row, Context context) {
      Object[] objectRow = this.applyConversions(row, context);
      if (objectRow != null) {
         this.rowProcessed(objectRow, context);
      }

   }

   public abstract void rowProcessed(Object[] var1, Context var2);

   public void processStarted(Context context) {
   }

   public void processEnded(Context context) {
   }
}
