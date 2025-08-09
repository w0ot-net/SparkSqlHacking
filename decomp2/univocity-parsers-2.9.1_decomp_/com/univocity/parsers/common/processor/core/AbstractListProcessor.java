package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractListProcessor implements Processor {
   private List rows;
   private String[] headers;
   private final int expectedRowCount;

   public AbstractListProcessor() {
      this(0);
   }

   public AbstractListProcessor(int expectedRowCount) {
      this.expectedRowCount = expectedRowCount <= 0 ? 10000 : expectedRowCount;
   }

   public void processStarted(Context context) {
      this.rows = new ArrayList(this.expectedRowCount);
   }

   public void rowProcessed(String[] row, Context context) {
      this.rows.add(row);
   }

   public void processEnded(Context context) {
      this.headers = context.headers();
   }

   public List getRows() {
      return this.rows == null ? Collections.emptyList() : this.rows;
   }

   public String[] getHeaders() {
      return this.headers;
   }
}
