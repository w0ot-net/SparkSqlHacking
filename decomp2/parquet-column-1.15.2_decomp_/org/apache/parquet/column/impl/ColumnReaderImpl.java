package org.apache.parquet.column.impl;

import org.apache.parquet.VersionParser;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.page.DataPage;
import org.apache.parquet.column.page.PageReader;
import org.apache.parquet.io.api.PrimitiveConverter;

public class ColumnReaderImpl extends ColumnReaderBase {
   public ColumnReaderImpl(ColumnDescriptor path, PageReader pageReader, PrimitiveConverter converter, VersionParser.ParsedVersion writerVersion) {
      super(path, pageReader, converter, writerVersion);
      this.consume();
   }

   boolean skipRL(int rl) {
      return false;
   }

   void newPageInitialized(DataPage page) {
   }
}
