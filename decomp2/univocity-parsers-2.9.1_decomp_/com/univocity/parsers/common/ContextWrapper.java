package com.univocity.parsers.common;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordMetaData;

public abstract class ContextWrapper implements Context {
   protected final Context context;

   public ContextWrapper(Context context) {
      this.context = context;
   }

   public String[] headers() {
      return this.context.headers();
   }

   public int[] extractedFieldIndexes() {
      return this.context.extractedFieldIndexes();
   }

   public boolean columnsReordered() {
      return this.context.columnsReordered();
   }

   public int indexOf(String header) {
      return this.context.indexOf(header);
   }

   public int indexOf(Enum header) {
      return this.context.indexOf(header);
   }

   public int currentColumn() {
      return this.context.currentColumn();
   }

   public long currentRecord() {
      return this.context.currentRecord();
   }

   public void stop() {
      this.context.stop();
   }

   public boolean isStopped() {
      return this.context.isStopped();
   }

   public String[] selectedHeaders() {
      return this.context.selectedHeaders();
   }

   public int errorContentLength() {
      return this.context.errorContentLength();
   }

   public Record toRecord(String[] row) {
      return this.context.toRecord(row);
   }

   public RecordMetaData recordMetaData() {
      return this.context.recordMetaData();
   }
}
