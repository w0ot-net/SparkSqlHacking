package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.CostEstimate;

class CostEstimateImpl implements CostEstimate {
   double cost;
   double rowCount;
   double singleScanRowCount;

   CostEstimateImpl() {
   }

   CostEstimateImpl(double var1, double var3, double var5) {
      this.cost = var1;
      this.rowCount = var3;
      this.singleScanRowCount = var5;
   }

   public void setCost(double var1, double var3, double var5) {
      this.cost = var1;
      this.rowCount = var3;
      this.singleScanRowCount = var5;
   }

   public void setCost(CostEstimate var1) {
      this.cost = var1.getEstimatedCost();
      this.rowCount = var1.rowCount();
      this.singleScanRowCount = var1.singleScanRowCount();
   }

   public void setSingleScanRowCount(double var1) {
      this.singleScanRowCount = var1;
   }

   public double compare(CostEstimate var1) {
      if (this.cost == Double.POSITIVE_INFINITY && var1.getEstimatedCost() == Double.POSITIVE_INFINITY) {
         if (this.rowCount == Double.POSITIVE_INFINITY && var1.rowCount() == Double.POSITIVE_INFINITY) {
            return this.singleScanRowCount == Double.POSITIVE_INFINITY && var1.singleScanRowCount() == Double.POSITIVE_INFINITY ? (double)0.0F : this.singleScanRowCount - var1.singleScanRowCount();
         } else {
            return this.rowCount - var1.rowCount();
         }
      } else {
         return this.cost - ((CostEstimateImpl)var1).cost;
      }
   }

   public CostEstimate add(CostEstimate var1, CostEstimate var2) {
      CostEstimateImpl var3 = (CostEstimateImpl)var1;
      double var4 = this.cost + var3.cost;
      double var6 = this.rowCount + var3.rowCount;
      return this.setState(var4, var6, (CostEstimateImpl)var2);
   }

   public CostEstimate multiply(double var1, CostEstimate var3) {
      double var4 = this.cost * var1;
      double var6 = this.rowCount * var1;
      return this.setState(var4, var6, (CostEstimateImpl)var3);
   }

   public CostEstimate divide(double var1, CostEstimate var3) {
      double var4 = this.cost / var1;
      double var6 = this.rowCount / var1;
      return this.setState(var4, var6, (CostEstimateImpl)var3);
   }

   public double rowCount() {
      return this.rowCount;
   }

   public double singleScanRowCount() {
      return this.singleScanRowCount;
   }

   public CostEstimate cloneMe() {
      return new CostEstimateImpl(this.cost, this.rowCount, this.singleScanRowCount);
   }

   public boolean isUninitialized() {
      return this.cost == Double.MAX_VALUE && this.rowCount == Double.MAX_VALUE && this.singleScanRowCount == Double.MAX_VALUE;
   }

   public double getEstimatedCost() {
      return this.cost;
   }

   public void setEstimatedCost(double var1) {
      this.cost = var1;
   }

   public long getEstimatedRowCount() {
      return (long)this.rowCount;
   }

   public void setEstimatedRowCount(long var1) {
      this.rowCount = (double)var1;
      this.singleScanRowCount = (double)var1;
   }

   CostEstimateImpl setState(double var1, double var3, CostEstimateImpl var5) {
      if (var5 == null) {
         var5 = new CostEstimateImpl();
      }

      var5.cost = var1;
      var5.rowCount = var3;
      return var5;
   }

   public String toString() {
      int var10000 = this.hashCode();
      return "CostEstimateImpl: at " + var10000 + ", cost == " + this.cost + ", rowCount == " + this.rowCount + ", singleScanRowCount == " + this.singleScanRowCount;
   }
}
