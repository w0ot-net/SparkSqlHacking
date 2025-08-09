package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;

public interface Processor {
   void processStarted(Context var1);

   void rowProcessed(String[] var1, Context var2);

   void processEnded(Context var1);
}
