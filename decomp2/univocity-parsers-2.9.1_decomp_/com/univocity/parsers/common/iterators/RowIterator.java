package com.univocity.parsers.common.iterators;

import com.univocity.parsers.common.AbstractParser;

public abstract class RowIterator extends ParserIterator {
   public RowIterator(AbstractParser parser) {
      super(parser);
   }

   protected final String[] nextResult() {
      return this.parser.parseNext();
   }
}
