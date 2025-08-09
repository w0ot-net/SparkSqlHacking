package org.apache.orc;

public interface DoubleColumnStatistics extends ColumnStatistics {
   double getMinimum();

   double getMaximum();

   double getSum();
}
