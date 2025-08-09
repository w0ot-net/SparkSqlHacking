package org.apache.parquet.column.impl;

import java.io.IOException;
import java.util.Objects;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ColumnWriter;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.io.ParquetEncodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ColumnWriterBase implements ColumnWriter {
   private static final Logger LOG = LoggerFactory.getLogger(ColumnWriterBase.class);
   private static final boolean DEBUG = false;
   final ColumnDescriptor path;
   final PageWriter pageWriter;
   private ValuesWriter repetitionLevelColumn;
   private ValuesWriter definitionLevelColumn;
   private ValuesWriter dataColumn;
   private int valueCount;
   private long rowsWrittenSoFar;
   private int pageRowCount;
   private StatusManager statusManager;
   private final ColumnValueCollector collector;

   ColumnWriterBase(ColumnDescriptor path, PageWriter pageWriter, ParquetProperties props) {
      this(path, pageWriter, (BloomFilterWriter)null, props);
   }

   ColumnWriterBase(ColumnDescriptor path, PageWriter pageWriter, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      this.rowsWrittenSoFar = 0L;
      this.statusManager = StatusManager.create();
      this.path = path;
      this.pageWriter = pageWriter;
      this.repetitionLevelColumn = this.createRLWriter(props, path);
      this.definitionLevelColumn = this.createDLWriter(props, path);
      this.dataColumn = props.newValuesWriter(path);
      this.collector = new ColumnValueCollector(path, bloomFilterWriter, props);
   }

   void initStatusManager(StatusManager statusManager) {
      this.statusManager = (StatusManager)Objects.requireNonNull(statusManager);
   }

   abstract ValuesWriter createRLWriter(ParquetProperties var1, ColumnDescriptor var2);

   abstract ValuesWriter createDLWriter(ParquetProperties var1, ColumnDescriptor var2);

   private void log(Object value, int r, int d) {
      LOG.debug("{} {} r:{} d:{}", new Object[]{this.path, value, r, d});
   }

   private void definitionLevel(int definitionLevel) {
      this.definitionLevelColumn.writeInteger(definitionLevel);
   }

   private void repetitionLevel(int repetitionLevel) {
      this.repetitionLevelColumn.writeInteger(repetitionLevel);

      assert this.pageRowCount != 0 || repetitionLevel == 0 : "Every page shall start on record boundaries";

      if (repetitionLevel == 0) {
         ++this.pageRowCount;
      }

   }

   public void writeNull(int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.collector.writeNull(repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   public void close() {
      this.repetitionLevelColumn.close();
      this.definitionLevelColumn.close();
      this.dataColumn.close();
   }

   public long getBufferedSizeInMemory() {
      return this.repetitionLevelColumn.getBufferedSize() + this.definitionLevelColumn.getBufferedSize() + this.dataColumn.getBufferedSize() + this.pageWriter.getMemSize();
   }

   public void write(double value, int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.dataColumn.writeDouble(value);
         this.collector.write(value, repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   public void write(float value, int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.dataColumn.writeFloat(value);
         this.collector.write(value, repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   public void write(Binary value, int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.dataColumn.writeBytes(value);
         this.collector.write(value, repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   public void write(boolean value, int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.dataColumn.writeBoolean(value);
         this.collector.write(value, repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   public void write(int value, int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.dataColumn.writeInteger(value);
         this.collector.write(value, repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   public void write(long value, int repetitionLevel, int definitionLevel) {
      try {
         this.repetitionLevel(repetitionLevel);
         this.definitionLevel(definitionLevel);
         this.dataColumn.writeLong(value);
         this.collector.write(value, repetitionLevel, definitionLevel);
         ++this.valueCount;
      } catch (Throwable e) {
         this.statusManager.abort();
         throw e;
      }
   }

   void finalizeColumnChunk() {
      if (!this.statusManager.isAborted()) {
         try {
            DictionaryPage dictionaryPage = this.dataColumn.toDictPageAndClose();
            if (dictionaryPage != null) {
               try {
                  this.pageWriter.writeDictionaryPage(dictionaryPage);
               } catch (IOException e) {
                  throw new ParquetEncodingException("could not write dictionary page for " + this.path, e);
               }

               this.dataColumn.resetDictionary();
            }

            this.collector.finalizeColumnChunk();
         } catch (Throwable t) {
            this.statusManager.abort();
            throw t;
         }
      }
   }

   long getCurrentPageBufferedSize() {
      return this.repetitionLevelColumn.getBufferedSize() + this.definitionLevelColumn.getBufferedSize() + this.dataColumn.getBufferedSize();
   }

   long getTotalBufferedSize() {
      return this.repetitionLevelColumn.getBufferedSize() + this.definitionLevelColumn.getBufferedSize() + this.dataColumn.getBufferedSize() + this.pageWriter.getMemSize();
   }

   long allocatedSize() {
      return this.repetitionLevelColumn.getAllocatedSize() + this.definitionLevelColumn.getAllocatedSize() + this.dataColumn.getAllocatedSize() + this.pageWriter.allocatedSize();
   }

   String memUsageString(String indent) {
      StringBuilder b = (new StringBuilder(indent)).append(this.path).append(" {\n");
      b.append(indent).append(" r:").append(this.repetitionLevelColumn.getAllocatedSize()).append(" bytes\n");
      b.append(indent).append(" d:").append(this.definitionLevelColumn.getAllocatedSize()).append(" bytes\n");
      b.append(this.dataColumn.memUsageString(indent + "  data:")).append("\n");
      b.append(this.pageWriter.memUsageString(indent + "  pages:")).append("\n");
      b.append(indent).append(String.format("  total: %,d/%,d", this.getTotalBufferedSize(), this.allocatedSize())).append("\n");
      b.append(indent).append("}\n");
      return b.toString();
   }

   long getRowsWrittenSoFar() {
      return this.rowsWrittenSoFar;
   }

   int getValueCount() {
      return this.valueCount;
   }

   void writePage() {
      if (this.valueCount == 0) {
         throw new ParquetEncodingException("writing empty page");
      } else if (!this.statusManager.isAborted()) {
         try {
            this.rowsWrittenSoFar += (long)this.pageRowCount;

            try {
               this.writePage(this.pageRowCount, this.valueCount, this.collector.getStatistics(), this.collector.getSizeStatistics(), this.repetitionLevelColumn, this.definitionLevelColumn, this.dataColumn);
            } catch (IOException e) {
               throw new ParquetEncodingException("could not write page for " + this.path, e);
            }

            this.repetitionLevelColumn.reset();
            this.definitionLevelColumn.reset();
            this.dataColumn.reset();
            this.valueCount = 0;
            this.collector.resetPageStatistics();
            this.pageRowCount = 0;
         } catch (Throwable t) {
            this.statusManager.abort();
            throw t;
         }
      }
   }

   abstract void writePage(int var1, int var2, Statistics var3, SizeStatistics var4, ValuesWriter var5, ValuesWriter var6, ValuesWriter var7) throws IOException;
}
