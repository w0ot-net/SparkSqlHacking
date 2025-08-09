package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.core.Processor;

public interface RowProcessor extends Processor {
   void processStarted(ParsingContext var1);

   void rowProcessed(String[] var1, ParsingContext var2);

   void processEnded(ParsingContext var1);
}
