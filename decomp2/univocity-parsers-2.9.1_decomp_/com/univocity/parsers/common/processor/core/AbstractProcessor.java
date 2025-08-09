package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;

public abstract class AbstractProcessor implements Processor {
   public void processStarted(Context context) {
   }

   public void rowProcessed(String[] row, Context context) {
   }

   public void processEnded(Context context) {
   }
}
