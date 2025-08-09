package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;

public class CompositeProcessor implements Processor {
   private final Processor[] processors;

   public CompositeProcessor(Processor... processors) {
      this.processors = processors;
   }

   public void processStarted(Context context) {
      for(int i = 0; i < this.processors.length; ++i) {
         this.processors[i].processStarted(context);
      }

   }

   public void rowProcessed(String[] row, Context context) {
      for(int i = 0; i < this.processors.length; ++i) {
         this.processors[i].rowProcessed(row, context);
      }

   }

   public void processEnded(Context context) {
      for(int i = 0; i < this.processors.length; ++i) {
         this.processors[i].processEnded(context);
      }

   }
}
