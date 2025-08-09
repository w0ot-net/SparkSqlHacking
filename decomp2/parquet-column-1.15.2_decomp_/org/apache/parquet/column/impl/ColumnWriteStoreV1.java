package org.apache.parquet.column.impl;

import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.page.PageWriteStore;
import org.apache.parquet.column.page.PageWriter;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriteStore;
import org.apache.parquet.column.values.bloomfilter.BloomFilterWriter;
import org.apache.parquet.schema.MessageType;

public class ColumnWriteStoreV1 extends ColumnWriteStoreBase {
   public ColumnWriteStoreV1(MessageType schema, PageWriteStore pageWriteStore, ParquetProperties props) {
      super(schema, pageWriteStore, props);
   }

   /** @deprecated */
   @Deprecated
   public ColumnWriteStoreV1(PageWriteStore pageWriteStore, ParquetProperties props) {
      super(pageWriteStore, props);
   }

   public ColumnWriteStoreV1(MessageType schema, PageWriteStore pageWriteStore, BloomFilterWriteStore bloomFilterWriteStore, ParquetProperties props) {
      super(schema, pageWriteStore, bloomFilterWriteStore, props);
   }

   ColumnWriterBase createColumnWriter(ColumnDescriptor path, PageWriter pageWriter, BloomFilterWriter bloomFilterWriter, ParquetProperties props) {
      return new ColumnWriterV1(path, pageWriter, bloomFilterWriter, props);
   }
}
