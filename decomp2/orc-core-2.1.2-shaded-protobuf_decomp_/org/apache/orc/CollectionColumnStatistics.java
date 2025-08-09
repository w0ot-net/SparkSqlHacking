package org.apache.orc;

public interface CollectionColumnStatistics extends ColumnStatistics {
   long getMinimumChildren();

   long getMaximumChildren();

   long getTotalChildren();
}
