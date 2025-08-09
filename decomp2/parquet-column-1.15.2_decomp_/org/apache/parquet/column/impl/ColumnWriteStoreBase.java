package org.apache.parquet.column.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ColumnWriteStore;
import org.apache.parquet.column.ColumnWriter;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.PageWriteStore;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriteStore;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.schema.MessageType;

abstract class ColumnWriteStoreBase implements ColumnWriteStore {
   private final ColumnWriterProvider columnWriterProvider;
   private static final float THRESHOLD_TOLERANCE_RATIO = 0.1F;
   private final Map columns;
   private final ParquetProperties props;
   private final long thresholdTolerance;
   private long rowCount;
   private long rowCountForNextSizeCheck;
   private StatusManager statusManager = StatusManager.create();

   /** @deprecated */
   @Deprecated
   ColumnWriteStoreBase(final PageWriteStore pageWriteStore, final ParquetProperties props) {
      this.props = props;
      this.thresholdTolerance = (long)((float)props.getPageSizeThreshold() * 0.1F);
      this.columns = new TreeMap();
      this.rowCountForNextSizeCheck = (long)Math.min(props.getMinRowCountForPageSizeCheck(), props.getPageRowCountLimit());
      this.columnWriterProvider = new ColumnWriterProvider() {
         public ColumnWriter getColumnWriter(ColumnDescriptor path) {
            ColumnWriterBase column = (ColumnWriterBase)ColumnWriteStoreBase.this.columns.get(path);
            if (column == null) {
               column = ColumnWriteStoreBase.this.createColumnWriterBase(path, pageWriteStore.getPageWriter(path), (BloomFilterWriter)null, props);
               ColumnWriteStoreBase.this.columns.put(path, column);
            }

            return column;
         }
      };
   }

   ColumnWriteStoreBase(MessageType schema, PageWriteStore pageWriteStore, ParquetProperties props) {
      this.props = props;
      this.thresholdTolerance = (long)((float)props.getPageSizeThreshold() * 0.1F);
      Map<ColumnDescriptor, ColumnWriterBase> mcolumns = new TreeMap();

      for(ColumnDescriptor path : schema.getColumns()) {
         PageWriter pageWriter = pageWriteStore.getPageWriter(path);
         mcolumns.put(path, this.createColumnWriterBase(path, pageWriter, (BloomFilterWriter)null, props));
      }

      this.columns = Collections.unmodifiableMap(mcolumns);
      this.rowCountForNextSizeCheck = (long)Math.min(props.getMinRowCountForPageSizeCheck(), props.getPageRowCountLimit());
      this.columnWriterProvider = new ColumnWriterProvider() {
         public ColumnWriter getColumnWriter(ColumnDescriptor path) {
            return (ColumnWriter)ColumnWriteStoreBase.this.columns.get(path);
         }
      };
   }

   ColumnWriteStoreBase(MessageType schema, PageWriteStore pageWriteStore, BloomFilterWriteStore bloomFilterWriteStore, ParquetProperties props) {
      this.props = props;
      this.thresholdTolerance = (long)((float)props.getPageSizeThreshold() * 0.1F);
      Map<ColumnDescriptor, ColumnWriterBase> mcolumns = new TreeMap();

      for(ColumnDescriptor path : schema.getColumns()) {
         PageWriter pageWriter = pageWriteStore.getPageWriter(path);
         if (props.isBloomFilterEnabled(path)) {
            BloomFilterWriter bloomFilterWriter = bloomFilterWriteStore.getBloomFilterWriter(path);
            mcolumns.put(path, this.createColumnWriterBase(path, pageWriter, bloomFilterWriter, props));
         } else {
            mcolumns.put(path, this.createColumnWriterBase(path, pageWriter, (BloomFilterWriter)null, props));
         }
      }

      this.columns = Collections.unmodifiableMap(mcolumns);
      this.rowCountForNextSizeCheck = (long)props.getMinRowCountForPageSizeCheck();
      this.columnWriterProvider = new ColumnWriterProvider() {
         public ColumnWriter getColumnWriter(ColumnDescriptor path) {
            return (ColumnWriter)ColumnWriteStoreBase.this.columns.get(path);
         }
      };
   }

   private ColumnWriterBase createColumnWriterBase(ColumnDescriptor path, PageWriter pageWriter, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      ColumnWriterBase columnWriterBase = this.createColumnWriter(path, pageWriter, bloomFilterWriter, props);
      columnWriterBase.initStatusManager(this.statusManager);
      return columnWriterBase;
   }

   abstract ColumnWriterBase createColumnWriter(ColumnDescriptor var1, PageWriter var2, BloomFilterWriter var3, ParquetProperties var4);

   public ColumnWriter getColumnWriter(ColumnDescriptor path) {
      return this.columnWriterProvider.getColumnWriter(path);
   }

   public Set getColumnDescriptors() {
      return this.columns.keySet();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(Map.Entry entry : this.columns.entrySet()) {
         sb.append(Arrays.toString(((ColumnDescriptor)entry.getKey()).getPath())).append(": ");
         sb.append(((ColumnWriterBase)entry.getValue()).getTotalBufferedSize()).append(" bytes");
         sb.append("\n");
      }

      return sb.toString();
   }

   public long getAllocatedSize() {
      long total = 0L;

      for(ColumnWriterBase memColumn : this.columns.values()) {
         total += memColumn.allocatedSize();
      }

      return total;
   }

   public long getBufferedSize() {
      long total = 0L;

      for(ColumnWriterBase memColumn : this.columns.values()) {
         total += memColumn.getTotalBufferedSize();
      }

      return total;
   }

   public void flush() {
      for(ColumnWriterBase memColumn : this.columns.values()) {
         long rows = this.rowCount - memColumn.getRowsWrittenSoFar();
         if (rows > 0L) {
            memColumn.writePage();
         }

         memColumn.finalizeColumnChunk();
      }

   }

   public String memUsageString() {
      StringBuilder b = new StringBuilder("Store {\n");

      for(ColumnWriterBase memColumn : this.columns.values()) {
         b.append(memColumn.memUsageString(" "));
      }

      b.append("}\n");
      return b.toString();
   }

   public long maxColMemSize() {
      long max = 0L;

      for(ColumnWriterBase memColumn : this.columns.values()) {
         max = Math.max(max, memColumn.getBufferedSizeInMemory());
      }

      return max;
   }

   public void close() {
      this.flush();

      for(ColumnWriterBase memColumn : this.columns.values()) {
         memColumn.close();
      }

   }

   public void endRecord() {
      ++this.rowCount;
      if (this.rowCount >= this.rowCountForNextSizeCheck) {
         this.sizeCheck();
      }

   }

   private void sizeCheck() {
      long minRecordToWait = Long.MAX_VALUE;
      int pageRowCountLimit = this.props.getPageRowCountLimit();
      long rowCountForNextRowCountCheck = this.rowCount + (long)pageRowCountLimit;

      for(ColumnWriterBase writer : this.columns.values()) {
         long usedMem = writer.getCurrentPageBufferedSize();
         long rows = this.rowCount - writer.getRowsWrittenSoFar();
         long remainingMem = (long)this.props.getPageSizeThreshold() - usedMem;
         if (remainingMem > this.thresholdTolerance && rows < (long)pageRowCountLimit && writer.getValueCount() < this.props.getPageValueCountThreshold()) {
            rowCountForNextRowCountCheck = Math.min(rowCountForNextRowCountCheck, writer.getRowsWrittenSoFar() + (long)pageRowCountLimit);
         } else {
            writer.writePage();
            remainingMem = (long)this.props.getPageSizeThreshold();
         }

         long rowsToFillPage = usedMem == 0L ? (long)this.props.getMaxRowCountForPageSizeCheck() : rows * remainingMem / usedMem;
         if (rowsToFillPage < minRecordToWait) {
            minRecordToWait = rowsToFillPage;
         }
      }

      if (minRecordToWait == Long.MAX_VALUE) {
         minRecordToWait = (long)this.props.getMinRowCountForPageSizeCheck();
      }

      if (this.props.estimateNextSizeCheck()) {
         this.rowCountForNextSizeCheck = this.rowCount + Math.min(Math.max(minRecordToWait / 2L, (long)this.props.getMinRowCountForPageSizeCheck()), (long)this.props.getMaxRowCountForPageSizeCheck());
      } else {
         this.rowCountForNextSizeCheck = this.rowCount + (long)this.props.getMinRowCountForPageSizeCheck();
      }

      if (rowCountForNextRowCountCheck < this.rowCountForNextSizeCheck) {
         this.rowCountForNextSizeCheck = rowCountForNextRowCountCheck;
      }

   }

   public boolean isColumnFlushNeeded() {
      return this.rowCount + 1L >= this.rowCountForNextSizeCheck;
   }

   private interface ColumnWriterProvider {
      ColumnWriter getColumnWriter(ColumnDescriptor var1);
   }
}
