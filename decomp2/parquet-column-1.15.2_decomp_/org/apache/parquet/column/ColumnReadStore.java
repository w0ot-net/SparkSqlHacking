package org.apache.parquet.column;

public interface ColumnReadStore {
   ColumnReader getColumnReader(ColumnDescriptor var1);
}
