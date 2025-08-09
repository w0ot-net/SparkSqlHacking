package org.apache.derby.impl.sql.compile;

import java.io.PrintWriter;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.util.JBitSet;

public class DefaultOptTrace implements OptTrace {
   private StringBuilder _buffer = new StringBuilder();

   public void traceStartStatement(String var1) {
      this.appendTraceString(var1);
   }

   public void traceStartQueryBlock(long var1, int var3, OptimizableList var4) {
      this.appendTraceString("Optimization started at time " + var1 + " using optimizer " + var3);
   }

   public void traceEndQueryBlock() {
   }

   public void traceTimeout(long var1, CostEstimate var3) {
      this.appendTraceString("Optimization time exceeded at time " + var1 + "\n" + var3);
   }

   public void traceVacuous() {
      this.appendTraceString("No tables to optimize.");
   }

   public void traceCompleteJoinOrder() {
      this.appendTraceString("We have a complete join order.");
   }

   public void traceSortCost(CostEstimate var1, CostEstimate var2) {
      this.appendTraceString("Cost of sorting is " + var1);
      this.appendTraceString("Total cost of non-sort-avoidance plan with sort cost added is " + var2);
   }

   public void traceNoBestPlan() {
      this.appendTraceString("No best plan found.");
   }

   public void traceModifyingAccessPaths(int var1) {
      this.appendTraceString("Modifying access paths using optimizer " + var1);
   }

   public void traceShortCircuiting(boolean var1, Optimizable var2, int var3) {
      String var4 = var1 ? "time exceeded" : "cost";
      if (var2.getBestAccessPath().getCostEstimate() == null) {
         var4 = "no best plan found";
      }

      this.appendTraceString("Short circuiting based on " + var4 + " at join position " + var3);
   }

   public void traceSkippingJoinOrder(int var1, int var2, int[] var3, JBitSet var4) {
      this.appendTraceString(this.reportJoinOrder("\n\nSkipping join order: ", true, var1, var2, var3, var4));
   }

   public void traceIllegalUserJoinOrder() {
      this.appendTraceString("User specified join order is not legal.");
   }

   public void traceUserJoinOrderOptimized() {
      this.appendTraceString("User-specified join order has now been optimized.");
   }

   public void traceJoinOrderConsideration(int var1, int[] var2, JBitSet var3) {
      this.appendTraceString(this.reportJoinOrder("\n\nConsidering join order: ", false, 0, var1, var2, var3));
   }

   public void traceCostWithoutSortAvoidance(CostEstimate var1) {
      this.appendTraceString("Total cost of non-sort-avoidance plan is " + var1);
   }

   public void traceCostWithSortAvoidance(CostEstimate var1) {
      this.appendTraceString("Total cost of sort avoidance plan is " + var1);
   }

   public void traceCurrentPlanAvoidsSort(CostEstimate var1, CostEstimate var2) {
      this.appendTraceString("Current plan is a sort avoidance plan.\n\tBest cost is : " + var1 + "\n\tThis cost is : " + var2);
   }

   public void traceCheapestPlanSoFar(int var1, CostEstimate var2) {
      this.appendTraceString("This is the cheapest plan so far.");
      this.appendTraceString("Plan is a " + (var1 == 1 ? "normal" : "sort avoidance") + " plan.");
      this.appendTraceString("Cost of cheapest plan is " + var2);
   }

   public void traceSortNeededForOrdering(int var1, RequiredRowOrdering var2) {
      this.appendTraceString("Sort needed for ordering: " + (var1 != 2) + "\n\tRow ordering: " + var2);
   }

   public void traceRememberingBestJoinOrder(int var1, int[] var2, int var3, CostEstimate var4, JBitSet var5) {
      this.appendTraceString(this.reportJoinOrder("\n\nRemembering join order as best: ", false, 0, var1, var2, var5));
   }

   public void traceSkippingBecauseTooMuchMemory(int var1) {
      this.appendTraceString("Skipping access path due to excess memory usage, maximum is " + var1);
   }

   public void traceCostOfNScans(int var1, double var2, CostEstimate var4) {
      this.appendTraceString("Cost of " + var2 + " scans is: " + var4 + " for table " + var1);
   }

   public void traceSkipUnmaterializableHashJoin() {
      this.appendTraceString("Skipping HASH JOIN because optimizable is not materializable");
   }

   public void traceSkipHashJoinNoHashKeys() {
      this.appendTraceString("Skipping HASH JOIN because there are no hash key columns");
   }

   public void traceHashKeyColumns(int[] var1) {
      String var2 = "# hash key columns = " + var1.length;

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2 = "\n" + var2 + "hashKeyColumns[" + var3 + "] = " + var1[var3];
      }

      this.appendTraceString(var2);
   }

   public void traceOptimizingJoinNode() {
      this.appendTraceString("Calling optimizeIt() for join node");
   }

   public void traceConsideringJoinStrategy(JoinStrategy var1, int var2) {
      this.appendTraceString("\nConsidering join strategy " + var1 + " for table " + var2);
   }

   public void traceRememberingBestAccessPath(AccessPath var1, int var2, int var3) {
      this.appendTraceString("Remembering access path " + var1 + " as truly the best for table " + var2 + " for plan type " + (var3 == 1 ? " normal " : "sort avoidance") + "\n");
   }

   public void traceNoMoreConglomerates(int var1) {
      this.appendTraceString("No more conglomerates to consider for table " + var1);
   }

   public void traceConsideringConglomerate(ConglomerateDescriptor var1, int var2) {
      String var10001 = this.reportConglomerateDescriptor(var1);
      this.appendTraceString("\nConsidering conglomerate " + var10001 + " for table " + var2);
   }

   public void traceScanningHeapWithUniqueKey() {
      this.appendTraceString("Scanning heap, but we have a full match on a unique key.");
   }

   public void traceAddingUnorderedOptimizable(int var1) {
      this.appendTraceString("Adding unordered optimizable, # of predicates = " + var1);
   }

   public void traceChangingAccessPathForTable(int var1) {
      this.appendTraceString("Changing access path for table " + var1);
   }

   public void traceNoStartStopPosition() {
      this.appendTraceString("Lock mode set to MODE_TABLE because no start or stop position");
   }

   public void traceNonCoveringIndexCost(double var1, int var3) {
      this.appendTraceString("Index does not cover query - cost including base row fetch is: " + var1 + " for table " + var3);
   }

   public void traceConstantStartStopPositions() {
      this.appendTraceString("Lock mode set to MODE_RECORD because all start and stop positions are constant");
   }

   public void traceEstimatingCostOfConglomerate(ConglomerateDescriptor var1, int var2) {
      String var3 = this.reportConglomerateDescriptor(var1);
      String var10001 = this.reportCostForTable(var3, var2);
      this.appendTraceString("Estimating cost of conglomerate: " + var10001);
   }

   public void traceLookingForSpecifiedIndex(String var1, int var2) {
      this.appendTraceString("Looking for user-specified index: " + var1 + " for table " + var2);
   }

   public void traceSingleMatchedRowCost(double var1, int var3) {
      this.appendTraceString("Guaranteed to match a single row - cost is: " + var1 + " for table " + var3);
   }

   public void traceCostIncludingExtra1stColumnSelectivity(CostEstimate var1, int var2) {
      this.appendTraceString("Cost including extra first column selectivity is : " + var1 + " for table " + var2);
   }

   public void traceNextAccessPath(String var1, int var2) {
      this.appendTraceString("Calling nextAccessPath() for base table " + var1 + " with " + var2 + " predicates.");
   }

   public void traceCostIncludingExtraStartStop(CostEstimate var1, int var2) {
      this.appendTraceString(this.reportCostIncluding("start/stop", var1, var2));
   }

   public void traceCostIncludingExtraQualifierSelectivity(CostEstimate var1, int var2) {
      this.appendTraceString(this.reportCostIncluding("qualifier", var1, var2));
   }

   public void traceCostIncludingExtraNonQualifierSelectivity(CostEstimate var1, int var2) {
      this.appendTraceString(this.reportCostIncluding("non-qualifier", var1, var2));
   }

   public void traceCostOfNoncoveringIndex(CostEstimate var1, int var2) {
      String var10001 = this.reportCostForTable(var1, var2);
      this.appendTraceString("Index does not cover query: cost including row fetch is: " + var10001);
   }

   public void traceRememberingJoinStrategy(JoinStrategy var1, int var2) {
      this.appendTraceString("\nRemembering join strategy " + var1 + " as best for table " + var2);
   }

   public void traceRememberingBestAccessPathSubstring(AccessPath var1, int var2) {
      this.appendTraceString("in best access path");
   }

   public void traceRememberingBestSortAvoidanceAccessPathSubstring(AccessPath var1, int var2) {
      this.appendTraceString("in best sort avoidance access path");
   }

   public void traceRememberingBestUnknownAccessPathSubstring(AccessPath var1, int var2) {
      this.appendTraceString("in best unknown access path");
   }

   public void traceCostOfConglomerateScan(int var1, ConglomerateDescriptor var2, CostEstimate var3, int var4, double var5, int var7, double var8, int var10, double var11, int var13, double var14, int var16, double var17) {
      String var10001 = this.reportConglomerateDescriptor(var2);
      this.appendTraceString("Cost of conglomerate " + var10001 + " scan for table number " + var1 + " is : ");
      this.appendTraceString(var3.toString());
      this.appendTraceString("\tNumber of extra first column predicates is : " + var4 + ", extra first column selectivity is : " + var5);
      this.appendTraceString("\tNumber of extra start/stop predicates is : " + var7 + ", extra start/stop selectivity is : " + var8);
      this.appendTraceString("\tNumber of start/stop statistics predicates is : " + var10 + ", statistics start/stop selectivity is : " + var11);
      this.appendTraceString("\tNumber of extra qualifiers is : " + var13 + ", extra qualifier selectivity is : " + var14);
      this.appendTraceString("\tNumber of extra non-qualifiers is : " + var16 + ", extra non-qualifier selectivity is : " + var17);
   }

   public void traceCostIncludingCompositeSelectivityFromStats(CostEstimate var1, int var2) {
      this.appendTraceString(this.reportCostIncluding("selectivity from statistics", var1, var2));
   }

   public void traceCompositeSelectivityFromStatistics(double var1) {
      this.appendTraceString("Selectivity from statistics found. It is " + var1);
   }

   public void traceCostIncludingStatsForIndex(CostEstimate var1, int var2) {
      this.appendTraceString(this.reportCostIncluding("statistics for index being considered", var1, var2));
   }

   public void printToWriter(PrintWriter var1) {
      var1.println(this._buffer.toString());
   }

   private String reportJoinOrder(String var1, boolean var2, int var3, int var4, int[] var5, JBitSet var6) {
      StringBuilder var7 = new StringBuilder();
      var7.append(var1);

      for(int var8 = 0; var8 <= var4; ++var8) {
         var7.append(" ").append(var5[var8]);
      }

      if (var2) {
         var7.append(" ").append(var3);
      }

      var7.append(" with assignedTableMap = ").append(var6).append("\n\n");
      return var7.toString();
   }

   private String reportConglomerateDescriptor(ConglomerateDescriptor var1) {
      String var2 = "";
      String[] var3 = var1.getColumnNames();
      if (var1.isIndex() && var3 != null) {
         IndexRowGenerator var4 = var1.getIndexDescriptor();
         int[] var5 = var4.baseColumnPositions();
         var2 = ", key columns = {" + var3[var5[0] - 1];

         for(int var6 = 1; var6 < var5.length; ++var6) {
            var2 = var2 + ", " + var3[var5[var6] - 1];
         }

         var2 = var2 + "}";
      }

      long var10000 = var1.getConglomerateNumber();
      return "CD: conglomerateNumber = " + var10000 + " name = " + var1.getConglomerateName() + " uuid = " + var1.getUUID() + " indexable = " + var1.isIndex() + var2;
   }

   private String reportCostForTable(Object var1, int var2) {
      return var1 + " for table " + var2;
   }

   private String reportCostIncluding(String var1, CostEstimate var2, int var3) {
      return "Cost including extra " + var1 + " start/stop selectivity is : " + this.reportCostForTable(var2, var3);
   }

   private void appendTraceString(String var1) {
      this._buffer.append(var1);
      this._buffer.append("\n");
   }
}
