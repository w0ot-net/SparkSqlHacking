package org.apache.orc;

public interface IntegerColumnStatistics extends ColumnStatistics {
   long getMinimum();

   long getMaximum();

   boolean isSumDefined();

   long getSum();
}
