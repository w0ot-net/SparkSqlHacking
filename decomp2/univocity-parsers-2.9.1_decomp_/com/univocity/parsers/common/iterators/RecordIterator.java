package com.univocity.parsers.common.iterators;

import com.univocity.parsers.common.AbstractParser;
import com.univocity.parsers.common.record.Record;

public abstract class RecordIterator extends ParserIterator {
   public RecordIterator(AbstractParser parser) {
      super(parser);
   }

   protected final Record nextResult() {
      return this.parser.parseNextRecord();
   }
}
