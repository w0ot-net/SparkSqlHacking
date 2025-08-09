package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMultiBeanRowProcessor extends AbstractMultiBeanProcessor {
   private final HashMap row = new HashMap();
   private long record = -1L;

   public AbstractMultiBeanRowProcessor(Class... beanTypes) {
      super(beanTypes);
   }

   public void processStarted(Context context) {
      this.record = -1L;
      this.row.clear();
      super.processStarted(context);
   }

   public final void beanProcessed(Class beanType, Object beanInstance, Context context) {
      if (this.record != context.currentRecord() && this.record != -1L) {
         this.submitRow(context);
      }

      this.record = context.currentRecord();
      this.row.put(beanType, beanInstance);
   }

   private void submitRow(Context context) {
      if (!this.row.isEmpty()) {
         this.rowProcessed(this.row, context);
         this.row.clear();
      }

   }

   public void processEnded(Context context) {
      this.submitRow(context);
      super.processEnded(context);
   }

   protected abstract void rowProcessed(Map var1, Context var2);
}
