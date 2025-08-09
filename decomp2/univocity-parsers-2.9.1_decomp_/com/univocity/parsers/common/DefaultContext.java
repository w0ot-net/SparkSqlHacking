package com.univocity.parsers.common;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordFactory;
import com.univocity.parsers.common.record.RecordMetaData;

public class DefaultContext implements Context {
   protected boolean stopped;
   final ParserOutput output;
   final ColumnMap columnMap;
   final int errorContentLength;
   protected RecordFactory recordFactory;
   private String[] headers;

   public DefaultContext(int errorContentLength) {
      this((ParserOutput)null, errorContentLength);
   }

   public DefaultContext(ParserOutput output, int errorContentLength) {
      this.stopped = false;
      this.output = output;
      this.errorContentLength = errorContentLength;
      this.columnMap = new ColumnMap(this, output);
   }

   public String[] headers() {
      if (this.headers == null) {
         if (this.output == null) {
            this.headers = ArgumentUtils.EMPTY_STRING_ARRAY;
         }

         this.headers = this.output.getHeaderAsStringArray();
      }

      return this.headers;
   }

   public String[] selectedHeaders() {
      if (this.headers == null) {
         this.headers();
      }

      int[] extractedFieldIndexes = this.extractedFieldIndexes();
      if (extractedFieldIndexes == null) {
         return this.headers();
      } else {
         String[] extractedFields = new String[extractedFieldIndexes.length];
         String[] headers = this.headers();

         for(int i = 0; i < extractedFieldIndexes.length; ++i) {
            extractedFields[i] = headers[extractedFieldIndexes[i]];
         }

         return extractedFields;
      }
   }

   public int[] extractedFieldIndexes() {
      return this.output == null ? null : this.output.getSelectedIndexes();
   }

   public boolean columnsReordered() {
      return this.output == null ? false : this.output.isColumnReorderingEnabled();
   }

   public int indexOf(String header) {
      return this.columnMap.indexOf(header);
   }

   public int indexOf(Enum header) {
      return this.columnMap.indexOf(header);
   }

   void reset() {
      if (this.output != null) {
         this.output.reset();
      }

      this.recordFactory = null;
      this.columnMap.reset();
   }

   public int currentColumn() {
      return this.output == null ? -1 : this.output.getCurrentColumn();
   }

   public long currentRecord() {
      return this.output == null ? -1L : this.output.getCurrentRecord();
   }

   public void stop() {
      this.stopped = true;
   }

   public boolean isStopped() {
      return this.stopped;
   }

   public int errorContentLength() {
      return this.errorContentLength;
   }

   public Record toRecord(String[] row) {
      if (this.recordFactory == null) {
         this.recordFactory = new RecordFactory(this);
      }

      return this.recordFactory.newRecord(row);
   }

   public RecordMetaData recordMetaData() {
      if (this.recordFactory == null) {
         this.recordFactory = new RecordFactory(this);
      }

      return this.recordFactory.getRecordMetaData();
   }
}
