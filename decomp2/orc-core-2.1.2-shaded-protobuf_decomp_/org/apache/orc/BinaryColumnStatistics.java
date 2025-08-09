package org.apache.orc;

public interface BinaryColumnStatistics extends ColumnStatistics {
   long getSum();
}
