package org.apache.derby.iapi.store.access;

public interface StoreCostResult {
   long getEstimatedRowCount();

   void setEstimatedRowCount(long var1);

   double getEstimatedCost();

   void setEstimatedCost(double var1);
}
