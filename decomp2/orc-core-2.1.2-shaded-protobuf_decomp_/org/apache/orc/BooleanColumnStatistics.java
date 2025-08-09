package org.apache.orc;

public interface BooleanColumnStatistics extends ColumnStatistics {
   long getFalseCount();

   long getTrueCount();
}
