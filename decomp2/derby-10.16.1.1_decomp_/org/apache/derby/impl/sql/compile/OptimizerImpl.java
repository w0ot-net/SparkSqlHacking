package org.apache.derby.impl.sql.compile;

import java.util.HashMap;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.OptTrace;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizableList;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.UniqueTupleDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

class OptimizerImpl implements Optimizer {
   private LanguageConnectionContext lcc;
   private DataDictionary dDictionary;
   private int numTablesInQuery;
   private int numOptimizables;
   private JBitSet assignedTableMap;
   private OptimizableList optimizableList;
   private OptimizerPlan overridingPlan;
   private OptimizerPlan currentPlan;
   private OptimizablePredicateList predicateList;
   private JBitSet nonCorrelatedTableMap;
   private int[] proposedJoinOrder;
   private int[] bestJoinOrder;
   private int joinPosition;
   private boolean desiredJoinOrderFound;
   private static final int NO_JUMP = 0;
   private static final int READY_TO_JUMP = 1;
   private static final int JUMPING = 2;
   private static final int WALK_HIGH = 3;
   private static final int WALK_LOW = 4;
   private int permuteState;
   private int[] firstLookOrder;
   private boolean ruleBasedOptimization;
   private CostEstimateImpl outermostCostEstimate = this.getNewCostEstimate((double)0.0F, (double)1.0F, (double)1.0F);
   private CostEstimateImpl currentCost = this.getNewCostEstimate((double)0.0F, (double)0.0F, (double)0.0F);
   private CostEstimateImpl currentSortAvoidanceCost = this.getNewCostEstimate((double)0.0F, (double)0.0F, (double)0.0F);
   private CostEstimateImpl bestCost = this.getNewCostEstimate(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
   private long timeOptimizationStarted;
   private long currentTime;
   private boolean timeExceeded;
   private boolean noTimeout;
   private boolean useStatistics;
   private int tableLockThreshold;
   private JoinStrategy[] joinStrategies;
   private RequiredRowOrdering requiredRowOrdering;
   private boolean foundABestPlan;
   private CostEstimate sortCost;
   private RowOrdering currentRowOrdering = new RowOrderingImpl();
   private RowOrdering bestRowOrdering = new RowOrderingImpl();
   private int maxMemoryPerTable;
   private boolean reloadBestPlan;
   private HashMap savedJoinOrders;
   private double timeLimit;
   private CostEstimate finalCostEstimate;
   private boolean usingPredsPushedFromAbove;
   private boolean bestJoinOrderUsedPredsFromAbove;

   OptimizerImpl(OptimizableList var1, OptimizablePredicateList var2, DataDictionary var3, boolean var4, boolean var5, boolean var6, int var7, JoinStrategy[] var8, int var9, RequiredRowOrdering var10, int var11, OptimizerPlan var12, LanguageConnectionContext var13) throws StandardException {
      var1.verifyProperties(var3);
      this.numTablesInQuery = var11;
      this.numOptimizables = var1.size();
      this.proposedJoinOrder = new int[this.numOptimizables];
      if (this.initJumpState() == 1) {
         this.firstLookOrder = new int[this.numOptimizables];
      }

      for(int var14 = 0; var14 < this.numOptimizables; ++var14) {
         this.proposedJoinOrder[var14] = -1;
      }

      this.bestJoinOrder = new int[this.numOptimizables];
      this.joinPosition = -1;
      this.optimizableList = var1;
      this.overridingPlan = var12;
      this.predicateList = var2;
      this.dDictionary = var3;
      this.ruleBasedOptimization = var4;
      this.noTimeout = var5;
      this.maxMemoryPerTable = var7;
      this.joinStrategies = var8;
      this.tableLockThreshold = var9;
      this.requiredRowOrdering = var10;
      this.useStatistics = var6;
      this.lcc = var13;
      this.assignedTableMap = new JBitSet(var11);
      this.nonCorrelatedTableMap = new JBitSet(var11);

      for(int var16 = 0; var16 < this.numOptimizables; ++var16) {
         Optimizable var15 = var1.getOptimizable(var16);
         this.nonCorrelatedTableMap.or(var15.getReferencedTableMap());
      }

      this.timeOptimizationStarted = System.currentTimeMillis();
      this.reloadBestPlan = false;
      this.savedJoinOrders = null;
      this.timeLimit = Double.MAX_VALUE;
      this.usingPredsPushedFromAbove = false;
      this.bestJoinOrderUsedPredsFromAbove = false;
      if (this.tracingIsOn()) {
         this.tracer().traceStartQueryBlock(this.timeOptimizationStarted, this.hashCode(), var1);
      }

      if (var12 != null) {
         if (!var12.isBound()) {
            throw StandardException.newException("42ZCE", new Object[0]);
         }

         int var17 = var1.size();
         int var18 = var12.countLeafNodes();
         if (var17 != var18) {
            throw StandardException.newException("42ZCC", new Object[]{var18, var17});
         }
      }

   }

   public void prepForNextRound() {
      this.reloadBestPlan = false;
      this.bestCost = this.getNewCostEstimate(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
      this.usingPredsPushedFromAbove = false;
      if (this.predicateList != null && this.predicateList.size() > 0) {
         for(int var1 = this.predicateList.size() - 1; var1 >= 0; --var1) {
            if (((Predicate)this.predicateList.getOptPredicate(var1)).isScopedForPush()) {
               this.usingPredsPushedFromAbove = true;
               break;
            }
         }
      }

      if (this.usingPredsPushedFromAbove) {
         this.timeOptimizationStarted = System.currentTimeMillis();
         this.timeExceeded = false;
      }

      this.desiredJoinOrderFound = false;
      this.initJumpState();
   }

   private int initJumpState() {
      this.permuteState = this.numTablesInQuery >= 6 ? 1 : 0;
      return this.permuteState;
   }

   private boolean tracingIsOn() {
      return this.lcc.optimizerTracingIsOn();
   }

   public int getMaxMemoryPerTable() {
      return this.maxMemoryPerTable;
   }

   public boolean getNextPermutation() throws StandardException {
      if (this.numOptimizables < 1) {
         if (this.tracingIsOn()) {
            this.tracer().traceVacuous();
         }

         this.endOfRoundCleanup();
         return false;
      } else {
         this.optimizableList.initAccessPaths(this);
         if (!this.timeExceeded && this.numTablesInQuery > 6 && !this.noTimeout) {
            this.currentTime = System.currentTimeMillis();
            this.timeExceeded = (double)(this.currentTime - this.timeOptimizationStarted) > this.timeLimit;
            if (this.tracingIsOn() && this.timeExceeded) {
               this.tracer().traceTimeout(this.currentTime, this.bestCost);
            }
         }

         if (this.bestCost.isUninitialized() && this.foundABestPlan && (!this.usingPredsPushedFromAbove && !this.bestJoinOrderUsedPredsFromAbove || this.timeExceeded)) {
            if (this.permuteState != 2) {
               if (this.firstLookOrder == null) {
                  this.firstLookOrder = new int[this.numOptimizables];
               }

               System.arraycopy(this.bestJoinOrder, 0, this.firstLookOrder, 0, this.numOptimizables);
               this.permuteState = 2;
               if (this.joinPosition >= 0) {
                  this.rewindJoinOrder();
                  this.joinPosition = -1;
               }
            }

            this.timeExceeded = false;
         }

         boolean var1 = false;
         boolean var2 = !this.bestCost.isUninitialized() && this.currentCost.compare(this.bestCost) > (double)0.0F && (this.requiredRowOrdering == null || this.currentSortAvoidanceCost.compare(this.bestCost) > (double)0.0F);
         if (this.joinPosition < this.numOptimizables - 1 && !var2 && !this.timeExceeded) {
            if (this.joinPosition < 0 || this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition]).getBestAccessPath().getCostEstimate() != null) {
               ++this.joinPosition;
               var1 = true;
               this.bestRowOrdering.copy(this.currentRowOrdering);
            }
         } else if (this.joinPosition < this.numOptimizables - 1) {
            if (this.tracingIsOn()) {
               this.tracer().traceShortCircuiting(this.timeExceeded, this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition]), this.joinPosition);
            }

            this.reloadBestPlan = true;
         }

         if (this.permuteState == 2 && !var1 && this.joinPosition >= 0) {
            this.reloadBestPlan = true;
            this.rewindJoinOrder();
            this.permuteState = 0;
         }

         while(true) {
            int var3;
            label234:
            while(true) {
               if (this.joinPosition < 0) {
                  this.endOfRoundCleanup();
                  return false;
               }

               var3 = this.proposedJoinOrder[this.joinPosition] + 1;
               if (this.proposedJoinOrder[this.joinPosition] >= 0) {
                  this.pullOptimizableFromJoinOrder();
               }

               if (!this.desiredJoinOrderFound && !this.timeExceeded) {
                  if (this.permuteState == 2) {
                     int var10 = this.firstLookOrder[this.joinPosition];
                     var3 = var10;
                     int var14 = this.numOptimizables;
                     int var6 = -1;

                     for(Optimizable var7 = this.optimizableList.getOptimizable(var10); !var7.legalJoinOrder(this.assignedTableMap); var7 = this.optimizableList.getOptimizable(var6)) {
                        if (var6 >= 0) {
                           this.firstLookOrder[this.joinPosition] = var10;
                           this.firstLookOrder[var14] = var6;
                        }

                        if (var14 <= this.joinPosition + 1) {
                           if (this.joinPosition > 0) {
                              --this.joinPosition;
                              this.reloadBestPlan = true;
                              this.rewindJoinOrder();
                           }

                           this.permuteState = 0;
                           break;
                        }

                        --var14;
                        var6 = this.firstLookOrder[var14];
                        this.firstLookOrder[this.joinPosition] = var6;
                        this.firstLookOrder[var14] = var10;
                        var3 = var6;
                     }

                     if (this.permuteState == 0) {
                        continue;
                     }

                     if (this.joinPosition == this.numOptimizables - 1) {
                        this.permuteState = 3;
                     }
                     break;
                  }

                  while(true) {
                     if (var3 >= this.numOptimizables) {
                        break label234;
                     }

                     boolean var4 = false;

                     for(int var5 = 0; var5 < this.joinPosition; ++var5) {
                        if (this.proposedJoinOrder[var5] == var3) {
                           var4 = true;
                           break;
                        }
                     }

                     if (!var4) {
                        if (var3 >= this.numOptimizables || this.joinOrderMeetsDependencies(var3)) {
                           break label234;
                        }

                        if (this.tracingIsOn()) {
                           this.tracer().traceSkippingJoinOrder(var3, this.joinPosition, ArrayUtil.copy(this.proposedJoinOrder), (JBitSet)this.assignedTableMap.clone());
                        }

                        if (!this.optimizableList.optimizeJoinOrder()) {
                           if (this.tracingIsOn()) {
                              this.tracer().traceIllegalUserJoinOrder();
                           }

                           throw StandardException.newException("42Y70", new Object[0]);
                        }
                     }

                     ++var3;
                  }
               }

               var3 = this.numOptimizables;
               break;
            }

            if (var3 < this.numOptimizables) {
               this.proposedJoinOrder[this.joinPosition] = var3;
               if (this.permuteState == 4) {
                  boolean var12 = true;

                  for(int var17 = 0; var17 < this.numOptimizables; ++var17) {
                     if (this.proposedJoinOrder[var17] < this.firstLookOrder[var17]) {
                        var12 = false;
                        break;
                     }

                     if (this.proposedJoinOrder[var17] > this.firstLookOrder[var17]) {
                        break;
                     }
                  }

                  if (var12) {
                     this.proposedJoinOrder[this.joinPosition] = -1;
                     --this.joinPosition;
                     if (this.joinPosition >= 0) {
                        this.reloadBestPlan = true;
                        this.rewindJoinOrder();
                        this.joinPosition = -1;
                     }

                     this.permuteState = 1;
                     this.endOfRoundCleanup();
                     return false;
                  }
               }

               this.optimizableList.getOptimizable(var3).getBestAccessPath().setCostEstimate((CostEstimate)null);
               if (this.tracingIsOn()) {
                  this.tracer().traceJoinOrderConsideration(this.joinPosition, ArrayUtil.copy(this.proposedJoinOrder), (JBitSet)this.assignedTableMap.clone());
               }

               Optimizable var13 = this.optimizableList.getOptimizable(var3);
               this.assignedTableMap.or(var13.getReferencedTableMap());
               var13.startOptimizing(this, this.currentRowOrdering);
               this.pushPredicates(this.optimizableList.getOptimizable(var3), this.assignedTableMap);
               return true;
            }

            if (!this.optimizableList.optimizeJoinOrder()) {
               if (!this.optimizableList.legalJoinOrder(this.numTablesInQuery)) {
                  if (this.tracingIsOn()) {
                     this.tracer().traceIllegalUserJoinOrder();
                  }

                  throw StandardException.newException("42Y70", new Object[0]);
               }

               if (this.tracingIsOn()) {
                  this.tracer().traceUserJoinOrderOptimized();
               }

               this.desiredJoinOrderFound = true;
            }

            if (this.permuteState == 1 && this.joinPosition > 0 && this.joinPosition == this.numOptimizables - 1) {
               this.permuteState = 2;
               double[] var11 = new double[this.numOptimizables];

               for(int var15 = 0; var15 < this.numOptimizables; ++var15) {
                  this.firstLookOrder[var15] = var15;
                  CostEstimate var18 = this.optimizableList.getOptimizable(var15).getBestAccessPath().getCostEstimate();
                  if (var18 == null) {
                     this.permuteState = 1;
                     break;
                  }

                  var11[var15] = var18.singleScanRowCount();
               }

               if (this.permuteState == 2) {
                  boolean var16 = false;

                  for(int var20 = 0; var20 < this.numOptimizables; ++var20) {
                     int var8 = var20;

                     for(int var9 = var20 + 1; var9 < this.numOptimizables; ++var9) {
                        if (var11[var9] < var11[var8]) {
                           var8 = var9;
                        }
                     }

                     if (var8 != var20) {
                        var11[var8] = var11[var20];
                        int var19 = this.firstLookOrder[var20];
                        this.firstLookOrder[var20] = this.firstLookOrder[var8];
                        this.firstLookOrder[var8] = var19;
                        var16 = true;
                     }
                  }

                  if (var16) {
                     --this.joinPosition;
                     this.rewindJoinOrder();
                     continue;
                  }

                  this.permuteState = 0;
               }
            }

            --this.joinPosition;
            if (this.joinPosition < 0 && this.permuteState == 3) {
               this.joinPosition = 0;
               this.permuteState = 4;
            }
         }
      }
   }

   private void rewindJoinOrder() throws StandardException {
      while(true) {
         Optimizable var1 = this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition]);
         var1.pullOptPredicates(this.predicateList);
         if (this.reloadBestPlan) {
            var1.updateBestPlanMap((short)2, this);
         }

         this.proposedJoinOrder[this.joinPosition] = -1;
         if (this.joinPosition == 0) {
            this.currentCost.setCost((double)0.0F, (double)0.0F, (double)0.0F);
            this.currentSortAvoidanceCost.setCost((double)0.0F, (double)0.0F, (double)0.0F);
            this.assignedTableMap.clearAll();
            return;
         }

         --this.joinPosition;
      }
   }

   private void endOfRoundCleanup() throws StandardException {
      for(int var1 = 0; var1 < this.numOptimizables; ++var1) {
         this.optimizableList.getOptimizable(var1).updateBestPlanMap((short)0, this);
      }

   }

   private double recoverCostFromProposedJoinOrder(boolean var1) throws StandardException {
      double var2 = (double)0.0F;

      for(int var4 = 0; var4 < this.joinPosition; ++var4) {
         if (var1) {
            var2 += this.optimizableList.getOptimizable(this.proposedJoinOrder[var4]).getBestSortAvoidancePath().getCostEstimate().getEstimatedCost();
         } else {
            var2 += this.optimizableList.getOptimizable(this.proposedJoinOrder[var4]).getBestAccessPath().getCostEstimate().getEstimatedCost();
         }
      }

      return var2;
   }

   private boolean joinOrderMeetsDependencies(int var1) throws StandardException {
      Optimizable var2 = this.optimizableList.getOptimizable(var1);
      return var2.legalJoinOrder(this.assignedTableMap);
   }

   private void pullOptimizableFromJoinOrder() throws StandardException {
      Optimizable var1 = this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition]);
      int var6 = 0;
      double var2;
      double var4;
      if (this.joinPosition == 0) {
         var2 = this.outermostCostEstimate.rowCount();
         var4 = this.outermostCostEstimate.singleScanRowCount();
      } else {
         var6 = this.proposedJoinOrder[this.joinPosition - 1];
         CostEstimate var7 = this.optimizableList.getOptimizable(var6).getBestAccessPath().getCostEstimate();
         var2 = var7.rowCount();
         var4 = var7.singleScanRowCount();
      }

      double var16 = this.currentCost.getEstimatedCost();
      CostEstimate var9 = var1.getBestAccessPath().getCostEstimate();
      if (var9 != null) {
         double var10 = var9.getEstimatedCost();
         var16 -= var10;
         if (var16 <= (double)0.0F) {
            if (this.joinPosition == 0) {
               var16 = (double)0.0F;
            } else {
               var16 = this.recoverCostFromProposedJoinOrder(false);
            }
         }
      }

      if (this.joinPosition == 0) {
         if (this.outermostCostEstimate != null) {
            var16 = this.outermostCostEstimate.getEstimatedCost();
         } else {
            var16 = (double)0.0F;
         }
      }

      this.currentCost.setCost(var16, var2, var4);
      if (this.requiredRowOrdering != null && var1.considerSortAvoidancePath()) {
         AccessPath var17 = var1.getBestSortAvoidancePath();
         double var11;
         if (this.joinPosition == 0) {
            var2 = this.outermostCostEstimate.rowCount();
            var4 = this.outermostCostEstimate.singleScanRowCount();
            var11 = this.outermostCostEstimate.getEstimatedCost();
         } else {
            CostEstimate var13 = this.optimizableList.getOptimizable(var6).getBestSortAvoidancePath().getCostEstimate();
            var2 = var13.rowCount();
            var4 = var13.singleScanRowCount();
            var11 = this.currentSortAvoidanceCost.getEstimatedCost() - var17.getCostEstimate().getEstimatedCost();
         }

         if (var11 <= (double)0.0F) {
            if (this.joinPosition == 0) {
               var11 = (double)0.0F;
            } else {
               var11 = this.recoverCostFromProposedJoinOrder(true);
            }
         }

         this.currentSortAvoidanceCost.setCost(var11, var2, var4);
         this.bestRowOrdering.removeOptimizable(var1.getTableNumber());
         this.bestRowOrdering.copy(this.currentRowOrdering);
      }

      var1.pullOptPredicates(this.predicateList);
      if (this.reloadBestPlan) {
         var1.updateBestPlanMap((short)2, this);
      }

      this.proposedJoinOrder[this.joinPosition] = -1;
      this.assignedTableMap.xor(var1.getReferencedTableMap());
   }

   void pushPredicates(Optimizable var1, JBitSet var2) throws StandardException {
      int var3 = this.predicateList.size();
      JBitSet var4 = new JBitSet(this.numTablesInQuery);
      JBitSet var5 = null;
      BaseTableNumbersVisitor var6 = null;

      for(int var9 = var3 - 1; var9 >= 0; --var9) {
         Predicate var8 = (Predicate)this.predicateList.getOptPredicate(var9);
         if (this.isPushable(var8)) {
            var4.setTo(var8.getReferencedMap());

            for(int var10 = 0; var10 < var4.size(); ++var10) {
               if (var2.get(var10)) {
                  var4.clear(var10);
               }
            }

            var4.and(this.nonCorrelatedTableMap);
            boolean var11 = var4.getFirstSetBit() == -1;
            if (var11 && var8.isScopedForPush() && this.numOptimizables > 1) {
               if (var6 == null) {
                  var5 = new JBitSet(this.numTablesInQuery);
                  var6 = new BaseTableNumbersVisitor(var5);
               }

               int var7 = ((FromTable)var1).getTableNumber();
               var5.clearAll();
               var6.setTableMap(var5);
               ((FromTable)var1).accept(var6);
               if (var7 >= 0) {
                  var5.set(var7);
               }

               var6.setTableMap(var4);
               var8.accept(var6);
               var4.and(var5);
               if (var4.getFirstSetBit() == -1) {
                  var11 = false;
               }
            }

            if (var11 && var1.pushOptPredicate(var8)) {
               this.predicateList.removeOptPredicate(var9);
            }
         }
      }

   }

   public boolean getNextDecoratedPermutation() throws StandardException {
      Optimizable var2 = this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition]);
      double var3 = (double)0.0F;

      boolean var1;
      while(true) {
         var1 = var2.nextAccessPath(this, (OptimizablePredicateList)null, this.currentRowOrdering);
         if (this.overridingPlan == null || !var1) {
            break;
         }

         if (this.currentPlan != null && this.currentPlan.countLeafNodes() == this.joinPosition + 1) {
            var1 = false;
            break;
         }

         Object var5 = OptimizerPlan.makeRowSource(this.getTupleDescriptor(var2), this.dDictionary);
         if (var5 == null) {
            var1 = false;
            break;
         }

         if (this.currentPlan != null) {
            var5 = new OptimizerPlan.Join(var2.getCurrentAccessPath().getJoinStrategy(), this.currentPlan, (OptimizerPlan)var5);
         }

         if (((OptimizerPlan)var5).isLeftPrefixOf(this.overridingPlan)) {
            this.currentPlan = (OptimizerPlan)var5;
            break;
         }
      }

      if (var2.getBestAccessPath().getCostEstimate() != null && var2.getCurrentAccessPath().getCostEstimate() != null) {
         if (var2.getBestAccessPath().getCostEstimate().compare(var2.getCurrentAccessPath().getCostEstimate()) != (double)0.0F) {
            var2.updateBestPlanMap((short)2, var2);
         } else if (var2.getBestAccessPath().getCostEstimate().rowCount() < var2.getCurrentAccessPath().getCostEstimate().rowCount()) {
            var2.updateBestPlanMap((short)2, var2);
         }
      }

      var2.updateBestPlanMap((short)0, var2);
      CostEstimate var9 = var2.getBestAccessPath().getCostEstimate();
      if (!var1 && var9 != null) {
         this.currentCost.setCost(this.currentCost.getEstimatedCost() + var9.getEstimatedCost(), var9.rowCount(), var9.singleScanRowCount());
         if (var2.considerSortAvoidancePath() && this.requiredRowOrdering != null) {
            var9 = var2.getBestSortAvoidancePath().getCostEstimate();
            this.currentSortAvoidanceCost.setCost(this.currentSortAvoidanceCost.getEstimatedCost() + var9.getEstimatedCost(), var9.rowCount(), var9.singleScanRowCount());
         }

         if (this.tracingIsOn()) {
            this.tracer().traceCostWithoutSortAvoidance(this.currentCost);
            if (var2.considerSortAvoidancePath()) {
               this.tracer().traceCostWithSortAvoidance(this.currentSortAvoidanceCost);
            }
         }

         if (this.joinPosition == this.numOptimizables - 1) {
            if (this.tracingIsOn()) {
               this.tracer().traceCompleteJoinOrder();
            }

            if (this.requiredRowOrdering != null) {
               boolean var6 = false;
               if (this.sortCost == null) {
                  this.sortCost = this.newCostEstimate();
               } else if (this.requiredRowOrdering.getSortNeeded()) {
                  if (this.bestCost.rowCount() > this.currentCost.rowCount()) {
                     this.requiredRowOrdering.estimateCost(this.bestCost.rowCount(), this.bestRowOrdering, this.sortCost);
                     double var7 = this.sortCost.getEstimatedCost();
                     this.requiredRowOrdering.estimateCost(this.currentCost.rowCount(), this.bestRowOrdering, this.sortCost);
                     var6 = true;
                     this.bestCost.setCost(this.bestCost.getEstimatedCost() - var7 + this.sortCost.getEstimatedCost(), this.sortCost.rowCount(), this.currentCost.singleScanRowCount());
                  } else if (this.bestCost.rowCount() < this.currentCost.rowCount()) {
                     this.currentCost.setCost(this.currentCost.getEstimatedCost(), this.bestCost.rowCount(), this.currentCost.singleScanRowCount());
                  }
               }

               if (!var6) {
                  this.requiredRowOrdering.estimateCost(this.currentCost.rowCount(), this.bestRowOrdering, this.sortCost);
               }

               var3 = this.currentCost.rowCount();
               this.currentCost.setCost(this.currentCost.getEstimatedCost() + this.sortCost.getEstimatedCost(), this.sortCost.rowCount(), this.currentCost.singleScanRowCount());
               if (this.tracingIsOn()) {
                  this.tracer().traceSortCost(this.sortCost, this.currentCost);
               }
            }

            if (this.foundABestPlan && !(this.currentCost.compare(this.bestCost) < (double)0.0F) && !this.bestCost.isUninitialized()) {
               this.reloadBestPlan = true;
            } else {
               this.rememberBestCost(this.currentCost, 1);
               this.reloadBestPlan = false;
            }

            if (this.requiredRowOrdering != null) {
               double var11 = this.currentCost.getEstimatedCost() - this.sortCost.getEstimatedCost();
               if (var11 < (double)0.0F) {
                  var11 = (double)0.0F;
               }

               this.currentCost.setCost(var11, var3, this.currentCost.singleScanRowCount());
            }

            if (this.requiredRowOrdering != null && var2.considerSortAvoidancePath() && this.requiredRowOrdering.sortRequired(this.bestRowOrdering, this.optimizableList, this.proposedJoinOrder) == 3) {
               if (this.tracingIsOn()) {
                  this.tracer().traceCurrentPlanAvoidsSort(this.bestCost, this.currentSortAvoidanceCost);
               }

               if (this.currentSortAvoidanceCost.compare(this.bestCost) <= (double)0.0F || this.bestCost.isUninitialized()) {
                  this.rememberBestCost(this.currentSortAvoidanceCost, 2);
               }
            }
         }
      }

      return var1;
   }

   private UniqueTupleDescriptor getTupleDescriptor(Optimizable var1) throws StandardException {
      if (isTableFunction(var1)) {
         ProjectRestrictNode var2 = (ProjectRestrictNode)var1;
         return ((StaticMethodCallNode)((FromVTI)var2.getChildResult()).getMethodCall()).ad;
      } else {
         return var1.getCurrentAccessPath().getConglomerateDescriptor();
      }
   }

   static boolean isTableFunction(Optimizable var0) {
      if (!(var0 instanceof ProjectRestrictNode)) {
         return false;
      } else {
         ResultSetNode var1 = ((ProjectRestrictNode)var0).getChildResult();
         return !(var1 instanceof FromVTI) ? false : ((FromVTI)var1).getMethodCall() instanceof StaticMethodCallNode;
      }
   }

   private void rememberBestCost(CostEstimate var1, int var2) throws StandardException {
      this.foundABestPlan = true;
      if (this.tracingIsOn()) {
         this.tracer().traceCheapestPlanSoFar(var2, var1);
      }

      this.bestCost.setCost(var1);
      if (this.bestCost.getEstimatedCost() < this.timeLimit) {
         this.timeLimit = this.bestCost.getEstimatedCost();
      }

      this.bestJoinOrderUsedPredsFromAbove = this.usingPredsPushedFromAbove;
      System.arraycopy(this.proposedJoinOrder, 0, this.bestJoinOrder, 0, this.numOptimizables);

      for(int var3 = 0; var3 < this.numOptimizables; ++var3) {
         this.optimizableList.getOptimizable(this.bestJoinOrder[var3]).rememberAsBest(var2, this);
      }

      if (this.requiredRowOrdering != null) {
         if (var2 == 2) {
            this.requiredRowOrdering.sortNotNeeded();
         } else {
            this.requiredRowOrdering.sortNeeded();
         }
      }

      if (this.tracingIsOn()) {
         if (this.requiredRowOrdering != null) {
            this.tracer().traceSortNeededForOrdering(var2, this.requiredRowOrdering);
         }

         this.tracer().traceRememberingBestJoinOrder(this.joinPosition, ArrayUtil.copy(this.bestJoinOrder), var2, var1, (JBitSet)this.assignedTableMap.clone());
      }

   }

   public void costPermutation() throws StandardException {
      Object var1;
      if (this.joinPosition == 0) {
         var1 = this.outermostCostEstimate;
      } else {
         var1 = this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition - 1]).getBestAccessPath().getCostEstimate();
      }

      Optimizable var2 = this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition]);
      if (var2.feasibleJoinStrategy(this.predicateList, this)) {
         var2.optimizeIt(this, this.predicateList, (CostEstimate)var1, this.currentRowOrdering);
      }
   }

   public void costOptimizable(Optimizable var1, TableDescriptor var2, ConglomerateDescriptor var3, OptimizablePredicateList var4, CostEstimate var5) throws StandardException {
      if (var1.feasibleJoinStrategy(var4, this)) {
         if (this.ruleBasedOptimization) {
            this.ruleBasedCostOptimizable(var1, var2, var3, var4, var5);
         } else {
            this.costBasedCostOptimizable(var1, var2, var3, var4, var5);
         }

      }
   }

   private void ruleBasedCostOptimizable(Optimizable var1, TableDescriptor var2, ConglomerateDescriptor var3, OptimizablePredicateList var4, CostEstimate var5) throws StandardException {
      AccessPath var6 = var1.getBestAccessPath();
      if (var4 != null && var4.useful(var1, var3)) {
         boolean var8 = var1.isCoveringIndex(var3);
         if (!var6.getCoveringIndexScan() || var6.getNonMatchingIndexScan() || var8) {
            var6.setCostEstimate(this.estimateTotalCost(var4, var3, var5, var1));
            var6.setConglomerateDescriptor(var3);
            var6.setNonMatchingIndexScan(false);
            var6.setCoveringIndexScan(var8);
            var6.setLockMode(var1.getCurrentAccessPath().getLockMode());
            var1.rememberJoinStrategyAsBest(var6);
         }

      } else if (var1.isCoveringIndex(var3)) {
         var6.setCostEstimate(this.estimateTotalCost(var4, var3, var5, var1));
         var6.setConglomerateDescriptor(var3);
         var6.setNonMatchingIndexScan(true);
         var6.setCoveringIndexScan(true);
         var6.setLockMode(var1.getCurrentAccessPath().getLockMode());
         var1.rememberJoinStrategyAsBest(var6);
      } else if (!var6.getCoveringIndexScan() && var6.getNonMatchingIndexScan() && !var3.isIndex()) {
         var6.setCostEstimate(this.estimateTotalCost(var4, var3, var5, var1));
         var6.setConglomerateDescriptor(var3);
         var6.setLockMode(var1.getCurrentAccessPath().getLockMode());
         var1.rememberJoinStrategyAsBest(var6);
      } else {
         ConglomerateDescriptor var7 = var6.getConglomerateDescriptor();
         if (var7 == null) {
            var6.setCostEstimate(this.estimateTotalCost(var4, var3, var5, var1));
            var6.setConglomerateDescriptor(var3);
            var6.setCoveringIndexScan(false);
            var6.setNonMatchingIndexScan(var3.isIndex());
            var6.setLockMode(var1.getCurrentAccessPath().getLockMode());
            var1.rememberJoinStrategyAsBest(var6);
         }

      }
   }

   private void costBasedCostOptimizable(Optimizable var1, TableDescriptor var2, ConglomerateDescriptor var3, OptimizablePredicateList var4, CostEstimate var5) throws StandardException {
      CostEstimate var6 = this.estimateTotalCost(var4, var3, var5, var1);
      var1.getCurrentAccessPath().setCostEstimate(var6);
      if (!var1.memoryUsageOK(var6.rowCount() / var5.rowCount(), this.maxMemoryPerTable)) {
         if (this.tracingIsOn()) {
            this.tracer().traceSkippingBecauseTooMuchMemory(this.maxMemoryPerTable);
         }

      } else {
         AccessPath var7 = var1.getBestAccessPath();
         CostEstimate var8 = var7.getCostEstimate();
         if (var8 == null || var8.isUninitialized() || var6.compare(var8) < (double)0.0F) {
            var7.setConglomerateDescriptor(var3);
            var7.setCostEstimate(var6);
            var7.setCoveringIndexScan(var1.isCoveringIndex(var3));
            var7.setNonMatchingIndexScan(var4 == null || !var4.useful(var1, var3));
            var7.setLockMode(var1.getCurrentAccessPath().getLockMode());
            var1.rememberJoinStrategyAsBest(var7);
         }

         if (this.requiredRowOrdering != null && (this.joinPosition == 0 || this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition - 1]).considerSortAvoidancePath()) && this.requiredRowOrdering.sortRequired(this.currentRowOrdering, this.assignedTableMap, this.optimizableList, this.proposedJoinOrder) == 3) {
            var7 = var1.getBestSortAvoidancePath();
            var8 = var7.getCostEstimate();
            if (var8 == null || var8.isUninitialized() || var6.compare(var8) < (double)0.0F) {
               var7.setConglomerateDescriptor(var3);
               var7.setCostEstimate(var6);
               var7.setCoveringIndexScan(var1.isCoveringIndex(var3));
               var7.setNonMatchingIndexScan(var4 == null || !var4.useful(var1, var3));
               var7.setLockMode(var1.getCurrentAccessPath().getLockMode());
               var1.rememberJoinStrategyAsBest(var7);
               var1.rememberSortAvoidancePath();
               this.currentRowOrdering.copy(this.bestRowOrdering);
            }
         }

      }
   }

   public void considerCost(Optimizable var1, OptimizablePredicateList var2, CostEstimate var3, CostEstimate var4) throws StandardException {
      if (var1.feasibleJoinStrategy(var2, this)) {
         var1.getCurrentAccessPath().setCostEstimate(var3);
         if (!var1.memoryUsageOK(var3.rowCount() / var4.rowCount(), this.maxMemoryPerTable)) {
            if (this.tracingIsOn()) {
               this.tracer().traceSkippingBecauseTooMuchMemory(this.maxMemoryPerTable);
            }

         } else {
            AccessPath var5 = var1.getBestAccessPath();
            CostEstimate var6 = var5.getCostEstimate();
            if (var6 == null || var6.isUninitialized() || var3.compare(var6) <= (double)0.0F) {
               var5.setCostEstimate(var3);
               var1.rememberJoinStrategyAsBest(var5);
            }

            if (this.requiredRowOrdering != null && (this.joinPosition == 0 || this.optimizableList.getOptimizable(this.proposedJoinOrder[this.joinPosition - 1]).considerSortAvoidancePath()) && this.requiredRowOrdering.sortRequired(this.currentRowOrdering, this.assignedTableMap, this.optimizableList, this.proposedJoinOrder) == 3) {
               var5 = var1.getBestSortAvoidancePath();
               var6 = var5.getCostEstimate();
               if (var6 == null || var6.isUninitialized() || var3.compare(var6) < (double)0.0F) {
                  var5.setCostEstimate(var3);
                  var1.rememberJoinStrategyAsBest(var5);
                  var1.rememberSortAvoidancePath();
                  this.currentRowOrdering.copy(this.bestRowOrdering);
               }
            }

         }
      }
   }

   public DataDictionary getDataDictionary() {
      return this.dDictionary;
   }

   public void modifyAccessPaths() throws StandardException {
      if (this.tracingIsOn()) {
         this.tracer().traceModifyingAccessPaths(this.hashCode());
      }

      if (!this.foundABestPlan) {
         if (this.tracingIsOn()) {
            this.tracer().traceNoBestPlan();
         }

         throw StandardException.newException("42Y69", new Object[0]);
      } else {
         this.optimizableList.reOrder(this.bestJoinOrder);
         JBitSet var1 = new JBitSet(this.numOptimizables);

         for(int var2 = 0; var2 < this.numOptimizables; ++var2) {
            Optimizable var3 = this.optimizableList.getOptimizable(var2);
            var1.or(var3.getReferencedTableMap());
            this.pushPredicates(var3, var1);
            this.optimizableList.setOptimizable(var2, var3.modifyAccessPath(var1));
         }

      }
   }

   private CostEstimate newCostEstimate() {
      return new CostEstimateImpl();
   }

   public CostEstimate getOptimizedCost() {
      return this.bestCost;
   }

   public CostEstimate getFinalCost() {
      if (this.finalCostEstimate != null) {
         return this.finalCostEstimate;
      } else {
         this.finalCostEstimate = this.getNewCostEstimate((double)0.0F, (double)0.0F, (double)0.0F);

         for(int var1 = 0; var1 < this.bestJoinOrder.length; ++var1) {
            CostEstimate var2 = this.optimizableList.getOptimizable(this.bestJoinOrder[var1]).getTrulyTheBestAccessPath().getCostEstimate();
            this.finalCostEstimate.setCost(this.finalCostEstimate.getEstimatedCost() + var2.getEstimatedCost(), var2.rowCount(), var2.singleScanRowCount());
         }

         return this.finalCostEstimate;
      }
   }

   public void setOuterRows(double var1) {
      this.outermostCostEstimate.setCost(this.outermostCostEstimate.getEstimatedCost(), var1, this.outermostCostEstimate.singleScanRowCount());
   }

   public int tableLockThreshold() {
      return this.tableLockThreshold;
   }

   public int getNumberOfJoinStrategies() {
      return this.joinStrategies.length;
   }

   public JoinStrategy getJoinStrategy(int var1) {
      return this.joinStrategies[var1];
   }

   public JoinStrategy getJoinStrategy(String var1) {
      JoinStrategy var2 = null;
      String var3 = StringUtil.SQLToUpperCase(var1);

      for(int var4 = 0; var4 < this.joinStrategies.length; ++var4) {
         if (var3.equals(this.joinStrategies[var4].getName())) {
            var2 = this.joinStrategies[var4];
         }
      }

      return var2;
   }

   public double uniqueJoinWithOuterTable(OptimizablePredicateList var1) throws StandardException {
      double var2 = (double)-1.0F;
      double var4 = (double)1.0F;
      double var6 = this.currentCost.rowCount();
      if (var1 != null) {
         for(int var8 = this.joinPosition - 1; var8 >= 0; --var8) {
            Optimizable var9 = this.optimizableList.getOptimizable(this.proposedJoinOrder[var8]);
            double var10 = var9.uniqueJoin(var1);
            if (var10 > (double)0.0F) {
               var4 *= var9.uniqueJoin(var1);
            }
         }
      }

      if (var4 != (double)1.0F) {
         var2 = var4 / var6;
      }

      return var2;
   }

   private boolean isPushable(OptimizablePredicate var1) {
      return !var1.hasSubquery();
   }

   private CostEstimate estimateTotalCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizable var4) throws StandardException {
      CostEstimate var5 = var4.estimateCost(var1, var2, var3, this, this.currentRowOrdering);
      return var5;
   }

   public int getLevel() {
      return 2;
   }

   CostEstimateImpl getNewCostEstimate(double var1, double var3, double var5) {
      return new CostEstimateImpl(var1, var3, var5);
   }

   public boolean useStatistics() {
      return this.useStatistics && this.optimizableList.useStatistics();
   }

   public void updateBestPlanMaps(short var1, Object var2) throws StandardException {
      if (this.numOptimizables > 1) {
         int[] var3 = null;
         if (var1 == 0) {
            if (this.savedJoinOrders != null) {
               this.savedJoinOrders.remove(var2);
               if (this.savedJoinOrders.isEmpty()) {
                  this.savedJoinOrders = null;
               }
            }
         } else if (var1 == 1) {
            if (this.savedJoinOrders == null) {
               this.savedJoinOrders = new HashMap();
            } else {
               var3 = (int[])this.savedJoinOrders.get(var2);
            }

            if (var3 == null) {
               var3 = new int[this.numOptimizables];
            }

            System.arraycopy(this.bestJoinOrder, 0, var3, 0, this.bestJoinOrder.length);
            this.savedJoinOrders.put(var2, var3);
         } else if (this.savedJoinOrders != null) {
            var3 = (int[])this.savedJoinOrders.get(var2);
            if (var3 != null) {
               System.arraycopy(var3, 0, this.bestJoinOrder, 0, var3.length);
            }
         }
      }

      for(int var5 = this.optimizableList.size() - 1; var5 >= 0; --var5) {
         this.optimizableList.getOptimizable(var5).updateBestPlanMap(var1, var2);
      }

   }

   void addScopedPredicatesToList(PredicateList var1, ContextManager var2) throws StandardException {
      if (var1 != null && var1 != this.predicateList) {
         if (this.predicateList == null) {
            this.predicateList = new PredicateList(var2);
         }

         for(int var4 = this.predicateList.size() - 1; var4 >= 0; --var4) {
            Predicate var3 = (Predicate)this.predicateList.getOptPredicate(var4);
            if (var3.isScopedForPush()) {
               this.predicateList.removeOptPredicate(var4);
            }
         }

         for(int var6 = var1.size() - 1; var6 >= 0; --var6) {
            Predicate var5 = (Predicate)var1.getOptPredicate(var6);
            if (var5.isScopedToSourceResultSet()) {
               var5.clearScanFlags();
               this.predicateList.addOptPredicate(var5);
               var1.removeOptPredicate(var6);
            }
         }

      }
   }

   private OptTrace tracer() {
      return this.lcc.getOptimizerTracer();
   }

   public int getOptimizableCount() {
      return this.optimizableList.size();
   }

   public Optimizable getOptimizable(int var1) {
      return this.optimizableList.getOptimizable(var1);
   }
}
