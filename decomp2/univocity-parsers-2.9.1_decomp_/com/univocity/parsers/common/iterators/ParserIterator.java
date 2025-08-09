package com.univocity.parsers.common.iterators;

import com.univocity.parsers.common.AbstractParser;
import com.univocity.parsers.common.IterableResult;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.ResultIterator;

abstract class ParserIterator implements IterableResult {
   protected final AbstractParser parser;

   protected ParserIterator(AbstractParser parser) {
      this.parser = parser;
   }

   public final ParsingContext getContext() {
      if (this.parser.getContext() == null) {
         this.beginParsing();
      }

      return this.parser.getContext();
   }

   protected abstract void beginParsing();

   public final ResultIterator iterator() {
      // $FF: Couldn't be decompiled
   }

   protected abstract Object nextResult();
}
