package org.apache.parquet.column.impl;

import java.io.IOException;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.statistics.SizeStatistics;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.bitpacking.DevNullValuesWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridEncoder;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;

final class ColumnWriterV2 extends ColumnWriterBase {
   private static final ValuesWriter NULL_WRITER = new DevNullValuesWriter();

   ColumnWriterV2(ColumnDescriptor path, PageWriter pageWriter, ParquetProperties props) {
      super(path, pageWriter, props);
   }

   ColumnWriterV2(ColumnDescriptor path, PageWriter pageWriter, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      super(path, pageWriter, bloomFilterWriter, props);
   }

   ValuesWriter createRLWriter(ParquetProperties props, ColumnDescriptor path) {
      return (ValuesWriter)(path.getMaxRepetitionLevel() == 0 ? NULL_WRITER : new RLEWriterForV2(props.newRepetitionLevelEncoder(path)));
   }

   ValuesWriter createDLWriter(ParquetProperties props, ColumnDescriptor path) {
      return (ValuesWriter)(path.getMaxDefinitionLevel() == 0 ? NULL_WRITER : new RLEWriterForV2(props.newDefinitionLevelEncoder(path)));
   }

   void writePage(int rowCount, int valueCount, Statistics statistics, SizeStatistics sizeStatistics, ValuesWriter repetitionLevels, ValuesWriter definitionLevels, ValuesWriter values) throws IOException {
      BytesInput bytes = values.getBytes();
      Encoding encoding = values.getEncoding();
      this.pageWriter.writePageV2(rowCount, Math.toIntExact(statistics.getNumNulls()), valueCount, repetitionLevels.getBytes(), definitionLevels.getBytes(), encoding, bytes, statistics, sizeStatistics);
   }

   private static class RLEWriterForV2 extends RunLengthBitPackingHybridValuesWriter {
      public RLEWriterForV2(RunLengthBitPackingHybridEncoder encoder) {
         super(encoder);
      }

      public BytesInput getBytes() {
         try {
            return this.encoder.toBytes();
         } catch (IOException e) {
            throw new ParquetEncodingException(e);
         }
      }
   }
}
