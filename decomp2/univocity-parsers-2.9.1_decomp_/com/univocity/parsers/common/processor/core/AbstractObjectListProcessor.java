package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractObjectListProcessor extends AbstractObjectProcessor {
   private List rows;
   private String[] headers;
   private final int expectedRowCount;

   public AbstractObjectListProcessor() {
      this(0);
   }

   public AbstractObjectListProcessor(int expectedRowCount) {
      this.expectedRowCount = expectedRowCount <= 0 ? 10000 : expectedRowCount;
   }

   public void processStarted(Context context) {
      super.processStarted(context);
      this.rows = new ArrayList(this.expectedRowCount);
   }

   public void rowProcessed(Object[] row, Context context) {
      this.rows.add(row);
   }

   public void processEnded(Context context) {
      super.processEnded(context);
      this.headers = context.headers();
   }

   public List getRows() {
      return this.rows == null ? Collections.emptyList() : this.rows;
   }

   public String[] getHeaders() {
      return this.headers;
   }
}
