package com.univocity.parsers.common;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordFactory;
import com.univocity.parsers.common.record.RecordMetaData;
import java.util.Collections;
import java.util.Map;

class NoopParsingContext implements ParsingContext {
   static final NoopParsingContext instance = new NoopParsingContext();
   private RecordMetaData recordMetaData;

   private NoopParsingContext() {
   }

   public void stop() {
   }

   public boolean isStopped() {
      return false;
   }

   public long currentLine() {
      return 0L;
   }

   public long currentChar() {
      return 0L;
   }

   public int currentColumn() {
      return 0;
   }

   public long currentRecord() {
      return 0L;
   }

   public void skipLines(long lines) {
   }

   public String[] parsedHeaders() {
      return null;
   }

   public String currentParsedContent() {
      return null;
   }

   public int currentParsedContentLength() {
      return 0;
   }

   public Map comments() {
      return Collections.emptyMap();
   }

   public String lastComment() {
      return null;
   }

   public char[] lineSeparator() {
      return Format.getSystemLineSeparator();
   }

   public String[] headers() {
      return null;
   }

   public String[] selectedHeaders() {
      return null;
   }

   public int[] extractedFieldIndexes() {
      return null;
   }

   public boolean columnsReordered() {
      return true;
   }

   public int indexOf(String header) {
      return -1;
   }

   public int indexOf(Enum header) {
      return -1;
   }

   public String fieldContentOnError() {
      return null;
   }

   public int errorContentLength() {
      return -1;
   }

   public Record toRecord(String[] row) {
      return null;
   }

   public RecordMetaData recordMetaData() {
      if (this.recordMetaData == null) {
         this.recordMetaData = (new RecordFactory(this)).getRecordMetaData();
      }

      return this.recordMetaData;
   }
}
