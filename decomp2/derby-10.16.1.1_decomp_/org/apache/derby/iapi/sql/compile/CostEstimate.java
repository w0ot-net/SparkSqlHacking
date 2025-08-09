package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.store.access.StoreCostResult;

public interface CostEstimate extends StoreCostResult {
   void setCost(double var1, double var3, double var5);

   void setCost(CostEstimate var1);

   void setSingleScanRowCount(double var1);

   double compare(CostEstimate var1);

   CostEstimate add(CostEstimate var1, CostEstimate var2);

   CostEstimate multiply(double var1, CostEstimate var3);

   CostEstimate divide(double var1, CostEstimate var3);

   double rowCount();

   double singleScanRowCount();

   CostEstimate cloneMe();

   boolean isUninitialized();
}
