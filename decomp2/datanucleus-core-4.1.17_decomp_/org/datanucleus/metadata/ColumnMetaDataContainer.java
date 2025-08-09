package org.datanucleus.metadata;

public interface ColumnMetaDataContainer {
   ColumnMetaData[] getColumnMetaData();

   void addColumn(ColumnMetaData var1);
}
