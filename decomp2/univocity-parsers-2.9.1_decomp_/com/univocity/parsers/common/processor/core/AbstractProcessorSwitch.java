package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import com.univocity.parsers.common.processor.RowProcessor;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractProcessorSwitch implements Processor, ColumnOrderDependent {
   private Map processors;
   private Processor selectedProcessor;
   private Context contextForProcessor;

   protected abstract Processor switchRowProcessor(String[] var1, Context var2);

   public String[] getHeaders() {
      return null;
   }

   public int[] getIndexes() {
      return null;
   }

   public void processorSwitched(Processor from, Processor to) {
      if (from != null) {
         if (from instanceof RowProcessor && (to == null || to instanceof RowProcessor)) {
            this.rowProcessorSwitched((RowProcessor)from, (RowProcessor)to);
         }
      } else if (to != null && to instanceof RowProcessor) {
         this.rowProcessorSwitched((RowProcessor)from, (RowProcessor)to);
      }

   }

   public void rowProcessorSwitched(RowProcessor from, RowProcessor to) {
   }

   public void processStarted(Context context) {
      this.processors = new HashMap();
      this.selectedProcessor = NoopProcessor.instance;
   }

   protected abstract Context wrapContext(Context var1);

   public final void rowProcessed(String[] row, Context context) {
      Processor processor = this.switchRowProcessor(row, context);
      if (processor == null) {
         processor = NoopProcessor.instance;
      }

      if (processor != this.selectedProcessor) {
         this.contextForProcessor = (Context)this.processors.get(processor);
         if (processor != NoopProcessor.instance) {
            if (this.contextForProcessor == null) {
               this.contextForProcessor = this.wrapContext(context);
               processor.processStarted(this.contextForProcessor);
               this.processors.put(processor, this.contextForProcessor);
            }

            this.processorSwitched(this.selectedProcessor, processor);
            this.selectedProcessor = processor;
            if (this.getIndexes() != null) {
               int[] indexes = this.getIndexes();
               String[] tmp = new String[indexes.length];

               for(int i = 0; i < indexes.length; ++i) {
                  int index = indexes[i];
                  if (index < row.length) {
                     tmp[i] = row[index];
                  }
               }

               row = tmp;
            }

            this.selectedProcessor.rowProcessed(row, this.contextForProcessor);
         }
      } else {
         this.selectedProcessor.rowProcessed(row, this.contextForProcessor);
      }

   }

   public void processEnded(Context context) {
      this.processorSwitched(this.selectedProcessor, (Processor)null);
      this.selectedProcessor = NoopProcessor.instance;

      for(Map.Entry e : this.processors.entrySet()) {
         ((Processor)e.getKey()).processEnded((Context)e.getValue());
      }

   }

   public boolean preventColumnReordering() {
      return true;
   }
}
