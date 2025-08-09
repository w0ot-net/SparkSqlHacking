package com.univocity.parsers.common.processor.core;

import com.univocity.parsers.common.Context;
import java.util.List;
import java.util.Map;

public abstract class AbstractObjectColumnProcessor extends AbstractObjectProcessor implements ColumnReader {
   private final ColumnSplitter splitter;

   public AbstractObjectColumnProcessor() {
      this(1000);
   }

   public AbstractObjectColumnProcessor(int expectedRowCount) {
      this.splitter = new ColumnSplitter(expectedRowCount);
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

   public void rowProcessed(Object[] row, Context context) {
      this.splitter.addValuesToColumns(row, context);
   }

   public void processStarted(Context context) {
      super.processStarted(context);
      this.splitter.reset();
   }

   public List getColumn(String columnName, Class columnType) {
      return this.splitter.getColumnValues(columnName, columnType);
   }

   public List getColumn(int columnIndex, Class columnType) {
      return this.splitter.getColumnValues(columnIndex, columnType);
   }

   public List getColumn(String columnName) {
      return this.splitter.getColumnValues(columnName, Object.class);
   }

   public List getColumn(int columnIndex) {
      return this.splitter.getColumnValues(columnIndex, Object.class);
   }
}
