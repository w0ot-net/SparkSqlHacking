package org.apache.orc;

public interface ColumnStatistics {
   long getNumberOfValues();

   boolean hasNull();

   long getBytesOnDisk();
}
