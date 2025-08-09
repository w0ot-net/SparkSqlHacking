package org.apache.parquet.column.impl;

import java.io.IOException;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;

final class ColumnWriterV1 extends ColumnWriterBase {
   ColumnWriterV1(ColumnDescriptor path, PageWriter pageWriter, ParquetProperties props) {
      super(path, pageWriter, props);
   }

   public ColumnWriterV1(ColumnDescriptor path, PageWriter pageWriter, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      super(path, pageWriter, bloomFilterWriter, props);
   }

   ValuesWriter createRLWriter(ParquetProperties props, ColumnDescriptor path) {
      return props.newRepetitionLevelWriter(path);
   }

   ValuesWriter createDLWriter(ParquetProperties props, ColumnDescriptor path) {
      return props.newDefinitionLevelWriter(path);
   }

   void writePage(int rowCount, int valueCount, Statistics statistics, SizeStatistics sizeStatistics, ValuesWriter repetitionLevels, ValuesWriter definitionLevels, ValuesWriter values) throws IOException {
      this.pageWriter.writePage(BytesInput.concat(new BytesInput[]{repetitionLevels.getBytes(), definitionLevels.getBytes(), values.getBytes()}), valueCount, rowCount, statistics, sizeStatistics, repetitionLevels.getEncoding(), definitionLevels.getEncoding(), values.getEncoding());
   }
}
