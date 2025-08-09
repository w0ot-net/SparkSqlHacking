package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import java.util.List;
import java.util.Map;

public abstract class AbstractColumnProcessor implements Processor, ColumnReader {
   private final ColumnSplitter splitter;

   public AbstractColumnProcessor() {
      this(1000);
   }

   public AbstractColumnProcessor(int expectedRowCount) {
      this.splitter = new ColumnSplitter(expectedRowCount);
   }

   public void processStarted(Context context) {
      this.splitter.reset();
   }

   public void rowProcessed(String[] row, Context context) {
      this.splitter.addValuesToColumns(row, context);
   }

   public void processEnded(Context context) {
   }

   public final String[] getHeaders() {
      return this.splitter.getHeaders();
   }

   public final List getColumnValuesAsList() {
      return this.splitter.getColumnValues();
   }

   public final void putColumnValuesInMapOfNames(Map map) {
      this.splitter.putColumnValuesInMapOfNames(map);
   }

   public final void putColumnValuesInMapOfIndexes(Map map) {
      this.splitter.putColumnValuesInMapOfIndexes(map);
   }

   public final Map getColumnValuesAsMapOfNames() {
      return this.splitter.getColumnValuesAsMapOfNames();
   }

   public final Map getColumnValuesAsMapOfIndexes() {
      return this.splitter.getColumnValuesAsMapOfIndexes();
   }

   public List getColumn(String columnName) {
      return this.splitter.getColumnValues(columnName, String.class);
   }

   public List getColumn(int columnIndex) {
      return this.splitter.getColumnValues(columnIndex, String.class);
   }
}
