package org.apache.parquet.column.impl;

import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.PageWriteStore;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriteStore;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.schema.MessageType;

public class ColumnWriteStoreV2 extends ColumnWriteStoreBase {
   public ColumnWriteStoreV2(MessageType schema, PageWriteStore pageWriteStore, ParquetProperties props) {
      super(schema, pageWriteStore, props);
   }

   public ColumnWriteStoreV2(MessageType schema, PageWriteStore pageWriteStore, BloomFilterWriteStore bloomFilterWriteStore, ParquetProperties props) {
      super(schema, pageWriteStore, bloomFilterWriteStore, props);
   }

   ColumnWriterBase createColumnWriter(ColumnDescriptor path, PageWriter pageWriter, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      return new ColumnWriterV2(path, pageWriter, bloomFilterWriter, props);
   }
}
