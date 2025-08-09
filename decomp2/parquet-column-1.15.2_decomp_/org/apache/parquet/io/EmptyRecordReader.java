package org.apache.parquet.io;

import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.RecordMaterializer;

class EmptyRecordReader extends RecordReader {
   private final GroupConverter recordConsumer;
   private final RecordMaterializer recordMaterializer;

   public EmptyRecordReader(RecordMaterializer recordMaterializer) {
      this.recordMaterializer = recordMaterializer;
      this.recordConsumer = recordMaterializer.getRootConverter();
   }

   public Object read() {
      this.recordConsumer.start();
      this.recordConsumer.end();
      return this.recordMaterializer.getCurrentRecord();
   }
}
