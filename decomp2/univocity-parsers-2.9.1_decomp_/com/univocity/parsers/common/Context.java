package com.univocity.parsers.common;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.common.record.RecordMetaData;

public interface Context {
   String[] headers();

   String[] selectedHeaders();

   int[] extractedFieldIndexes();

   boolean columnsReordered();

   int indexOf(String var1);

   int indexOf(Enum var1);

   int currentColumn();

   long currentRecord();

   void stop();

   boolean isStopped();

   int errorContentLength();

   Record toRecord(String[] var1);

   RecordMetaData recordMetaData();
}
