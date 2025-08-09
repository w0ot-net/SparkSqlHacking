package org.apache.parquet.column.values.factory;

import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.values.ValuesWriter;

public interface ValuesWriterFactory {
   void initialize(ParquetProperties var1);

   ValuesWriter newValuesWriter(ColumnDescriptor var1);
}
