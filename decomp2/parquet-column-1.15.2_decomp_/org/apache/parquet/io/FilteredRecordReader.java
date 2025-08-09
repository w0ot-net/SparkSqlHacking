package org.apache.parquet.io;

import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.column.impl.ColumnReadStoreImpl;
import org.apache.parquet.filter.RecordFilter;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.io.api.RecordMaterializer;

class FilteredRecordReader extends RecordReaderImplementation {
   private final RecordFilter recordFilter;
   private final long recordCount;
   private long recordsRead = 0L;

   public FilteredRecordReader(MessageColumnIO root, RecordMaterializer recordMaterializer, boolean validating, ColumnReadStoreImpl columnStore, UnboundRecordFilter unboundFilter, long recordCount) {
      super(root, recordMaterializer, validating, columnStore);
      this.recordCount = recordCount;
      if (unboundFilter != null) {
         this.recordFilter = unboundFilter.bind(this.getColumnReaders());
      } else {
         this.recordFilter = null;
      }

   }

   public Object read() {
      this.skipToMatch();
      if (this.recordsRead == this.recordCount) {
         return null;
      } else {
         ++this.recordsRead;
         return super.read();
      }
   }

   public boolean shouldSkipCurrentRecord() {
      return false;
   }

   private void skipToMatch() {
      for(; this.recordsRead < this.recordCount && !this.recordFilter.isMatch(); ++this.recordsRead) {
         RecordReaderImplementation.State currentState = this.getState(0);

         while(true) {
            ColumnReader columnReader = currentState.column;
            if (columnReader.getCurrentDefinitionLevel() >= currentState.maxDefinitionLevel) {
               columnReader.skip();
            }

            columnReader.consume();
            int nextR = currentState.maxRepetitionLevel == 0 ? 0 : columnReader.getCurrentRepetitionLevel();
            currentState = currentState.getNextState(nextR);
            if (currentState == null) {
               break;
            }
         }
      }

   }
}
