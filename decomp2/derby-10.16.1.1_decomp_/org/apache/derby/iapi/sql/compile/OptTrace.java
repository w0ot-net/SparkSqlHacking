package org.apache.derby.iapi.sql.compile;

import java.io.PrintWriter;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.util.JBitSet;

public interface OptTrace {
   void traceStartStatement(String var1);

   void traceStartQueryBlock(long var1, int var3, OptimizableList var4);

   void traceEndQueryBlock();

   void traceTimeout(long var1, CostEstimate var3);

   void traceVacuous();

   void traceCompleteJoinOrder();

   void traceSortCost(CostEstimate var1, CostEstimate var2);

   void traceNoBestPlan();

   void traceModifyingAccessPaths(int var1);

   void traceShortCircuiting(boolean var1, Optimizable var2, int var3);

   void traceSkippingJoinOrder(int var1, int var2, int[] var3, JBitSet var4);

   void traceIllegalUserJoinOrder();

   void traceUserJoinOrderOptimized();

   void traceJoinOrderConsideration(int var1, int[] var2, JBitSet var3);

   void traceCostWithoutSortAvoidance(CostEstimate var1);

   void traceCostWithSortAvoidance(CostEstimate var1);

   void traceCurrentPlanAvoidsSort(CostEstimate var1, CostEstimate var2);

   void traceCheapestPlanSoFar(int var1, CostEstimate var2);

   void traceSortNeededForOrdering(int var1, RequiredRowOrdering var2);

   void traceRememberingBestJoinOrder(int var1, int[] var2, int var3, CostEstimate var4, JBitSet var5);

   void traceSkippingBecauseTooMuchMemory(int var1);

   void traceCostOfNScans(int var1, double var2, CostEstimate var4);

   void traceSkipUnmaterializableHashJoin();

   void traceSkipHashJoinNoHashKeys();

   void traceHashKeyColumns(int[] var1);

   void traceOptimizingJoinNode();

   void traceConsideringJoinStrategy(JoinStrategy var1, int var2);

   void traceRememberingBestAccessPath(AccessPath var1, int var2, int var3);

   void traceNoMoreConglomerates(int var1);

   void traceConsideringConglomerate(ConglomerateDescriptor var1, int var2);

   void traceScanningHeapWithUniqueKey();

   void traceAddingUnorderedOptimizable(int var1);

   void traceChangingAccessPathForTable(int var1);

   void traceNoStartStopPosition();

   void traceNonCoveringIndexCost(double var1, int var3);

   void traceConstantStartStopPositions();

   void traceEstimatingCostOfConglomerate(ConglomerateDescriptor var1, int var2);

   void traceLookingForSpecifiedIndex(String var1, int var2);

   void traceSingleMatchedRowCost(double var1, int var3);

   void traceCostIncludingExtra1stColumnSelectivity(CostEstimate var1, int var2);

   void traceNextAccessPath(String var1, int var2);

   void traceCostIncludingExtraStartStop(CostEstimate var1, int var2);

   void traceCostIncludingExtraQualifierSelectivity(CostEstimate var1, int var2);

   void traceCostIncludingExtraNonQualifierSelectivity(CostEstimate var1, int var2);

   void traceCostOfNoncoveringIndex(CostEstimate var1, int var2);

   void traceRememberingJoinStrategy(JoinStrategy var1, int var2);

   void traceRememberingBestAccessPathSubstring(AccessPath var1, int var2);

   void traceRememberingBestSortAvoidanceAccessPathSubstring(AccessPath var1, int var2);

   void traceRememberingBestUnknownAccessPathSubstring(AccessPath var1, int var2);

   void traceCostOfConglomerateScan(int var1, ConglomerateDescriptor var2, CostEstimate var3, int var4, double var5, int var7, double var8, int var10, double var11, int var13, double var14, int var16, double var17);

   void traceCostIncludingCompositeSelectivityFromStats(CostEstimate var1, int var2);

   void traceCompositeSelectivityFromStatistics(double var1);

   void traceCostIncludingStatsForIndex(CostEstimate var1, int var2);

   void printToWriter(PrintWriter var1);
}
