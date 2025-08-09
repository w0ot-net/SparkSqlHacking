package org.apache.derby.catalog;

public interface Statistics {
   long getRowEstimate();

   double selectivity(Object[] var1);
}
