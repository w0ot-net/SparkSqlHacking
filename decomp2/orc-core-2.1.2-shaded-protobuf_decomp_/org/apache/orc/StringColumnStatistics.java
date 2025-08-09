package org.apache.orc;

public interface StringColumnStatistics extends ColumnStatistics {
   String getMinimum();

   String getMaximum();

   String getLowerBound();

   String getUpperBound();

   long getSum();
}
