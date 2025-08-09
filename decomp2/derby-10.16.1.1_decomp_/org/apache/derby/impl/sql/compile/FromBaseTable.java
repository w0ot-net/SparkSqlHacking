package org.apache.derby.impl.sql.compile;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.FormatableIntHolder;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.services.daemon.IndexStatisticsDaemonImpl;
import org.apache.derby.shared.common.error.StandardException;

class FromBaseTable extends FromTable {
   static final int UNSET = -1;
   private boolean hasCheckedIndexStats;
   TableName tableName;
   TableDescriptor tableDescriptor;
   ConglomerateDescriptor baseConglomerateDescriptor;
   ConglomerateDescriptor[] conglomDescs;
   int updateOrDelete;
   int bulkFetch = -1;
   private String targetTableUUIDString;
   private boolean validatingCheckConstraint = false;
   boolean bulkFetchTurnedOff;
   boolean multiProbing = false;
   private double singleScanRowCount;
   private FormatableBitSet referencedCols;
   private ResultColumnList templateColumns;
   private String[] columnNames;
   private boolean specialMaxScan;
   private boolean distinctScan;
   private boolean raDependentScan;
   private String raParentResultSetId;
   private long fkIndexConglomId;
   private int[] fkColArray;
   PredicateList baseTableRestrictionList;
   PredicateList nonBaseTableRestrictionList;
   PredicateList restrictionList;
   PredicateList storeRestrictionList;
   PredicateList nonStoreRestrictionList;
   PredicateList requalificationRestrictionList;
   static final int UPDATE = 1;
   static final int DELETE = 2;
   private boolean existsBaseTable;
   private boolean isNotExists;
   private JBitSet dependencyMap;
   private boolean getUpdateLocks;
   private boolean authorizeSYSUSERS;
   private String rowLocationColumnName;
   private boolean gotRowCount = false;
   private long rowCount = 0L;

   FromBaseTable(TableName var1, String var2, ResultColumnList var3, Properties var4, ContextManager var5) {
      super(var2, var4, var5);
      this.tableName = var1;
      this.setResultColumns(var3);
      this.setOrigTableName(this.tableName);
      this.templateColumns = this.getResultColumns();
   }

   FromBaseTable(TableName var1, String var2, int var3, ResultColumnList var4, ContextManager var5) {
      super(var2, (Properties)null, var5);
      this.tableName = var1;
      this.updateOrDelete = var3;
      this.setResultColumns(var4);
      this.setOrigTableName(this.tableName);
      this.templateColumns = this.getResultColumns();
   }

   void setRowLocationColumnName(String var1) {
      this.rowLocationColumnName = var1;
   }

   boolean LOJ_reorderable(int var1) throws StandardException {
      return false;
   }

   JBitSet LOJgetReferencedTables(int var1) throws StandardException {
      JBitSet var2 = new JBitSet(var1);
      this.fillInReferencedTableMap(var2);
      return var2;
   }

   public boolean nextAccessPath(Optimizer var1, OptimizablePredicateList var2, RowOrdering var3) throws StandardException {
      String var4 = this.getUserSpecifiedIndexName();
      AccessPath var5 = this.getCurrentAccessPath();
      ConglomerateDescriptor var6 = var5.getConglomerateDescriptor();
      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceNextAccessPath(this.getExposedName(), var2 == null ? 0 : var2.size());
      }

      var3.removeOptimizable(this.getTableNumber());
      if (var4 != null) {
         if (var6 != null) {
            if (!super.nextAccessPath(var1, var2, var3)) {
               var6 = null;
            }
         } else {
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceLookingForSpecifiedIndex(var4, this.tableNumber);
            }

            if (StringUtil.SQLToUpperCase(var4).equals("NULL")) {
               var6 = this.tableDescriptor.getConglomerateDescriptor(this.tableDescriptor.getHeapConglomerateId());
            } else {
               this.getConglomDescs();

               for(int var7 = 0; var7 < this.conglomDescs.length; ++var7) {
                  var6 = this.conglomDescs[var7];
                  String var8 = var6.getConglomerateName();
                  if (var8 != null && var8.equals(var4)) {
                     break;
                  }
               }
            }

            if (!super.nextAccessPath(var1, var2, var3)) {
            }
         }
      } else if (var6 != null) {
         if (!super.nextAccessPath(var1, var2, var3)) {
            var6 = this.getNextConglom(var6);
            this.resetJoinStrategies(var1);
            if (!super.nextAccessPath(var1, var2, var3)) {
            }
         }
      } else {
         var6 = this.getFirstConglom();
         if (!super.nextAccessPath(var1, var2, var3)) {
         }
      }

      if (var6 == null) {
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceNoMoreConglomerates(this.tableNumber);
         }
      } else {
         var6.setColumnNames(this.columnNames);
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceConsideringConglomerate(var6, this.tableNumber);
         }
      }

      if (var6 != null) {
         if (!var6.isIndex()) {
            if (!this.isOneRowResultSet(var2)) {
               if (this.optimizerTracingIsOn()) {
                  this.getOptimizerTracer().traceAddingUnorderedOptimizable(var2 == null ? 0 : var2.size());
               }

               var3.addUnorderedOptimizable(this);
            } else if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceScanningHeapWithUniqueKey();
            }
         } else {
            IndexRowGenerator var11 = var6.getIndexDescriptor();
            int[] var12 = var11.baseColumnPositions();
            boolean[] var9 = var11.isAscending();

            for(int var10 = 0; var10 < var12.length; ++var10) {
               if (!var3.orderedOnColumn(var9[var10] ? 1 : 2, this.getTableNumber(), var12[var10])) {
                  var3.nextOrderPosition(var9[var10] ? 1 : 2);
                  var3.addOrderedColumn(var9[var10] ? 1 : 2, this.getTableNumber(), var12[var10]);
               }
            }
         }
      }

      var5.setConglomerateDescriptor(var6);
      return var6 != null;
   }

   protected boolean canBeOrdered() {
      return true;
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      var1.costOptimizable(this, this.tableDescriptor, this.getCurrentAccessPath().getConglomerateDescriptor(), var2, var3);
      return this.getCurrentAccessPath().getCostEstimate();
   }

   public TableDescriptor getTableDescriptor() {
      return this.tableDescriptor;
   }

   public boolean isMaterializable() throws StandardException {
      return true;
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      this.restrictionList.addPredicate((Predicate)var1);
      return true;
   }

   public void pullOptPredicates(OptimizablePredicateList var1) throws StandardException {
      for(int var2 = this.restrictionList.size() - 1; var2 >= 0; --var2) {
         var1.addOptPredicate(this.restrictionList.getOptPredicate(var2));
         this.restrictionList.removeOptPredicate(var2);
      }

   }

   public boolean isCoveringIndex(ConglomerateDescriptor var1) throws StandardException {
      boolean var2 = true;
      if (!var1.isIndex()) {
         return false;
      } else {
         IndexRowGenerator var3 = var1.getIndexDescriptor();
         int[] var4 = var3.baseColumnPositions();

         for(ResultColumn var7 : this.getResultColumns()) {
            if (var7.isReferenced() && !(var7.getExpression() instanceof ConstantNode)) {
               var2 = false;
               int var5 = var7.getColumnPosition();

               for(int var8 = 0; var8 < var4.length; ++var8) {
                  if (var5 == var4[var8]) {
                     var2 = true;
                     break;
                  }
               }

               if (!var2) {
                  break;
               }
            }
         }

         return var2;
      }
   }

   public void verifyProperties(DataDictionary var1) throws StandardException {
      if (this.tableProperties != null) {
         boolean var2 = false;
         boolean var3 = false;
         ConstraintDescriptor var4 = null;
         Enumeration var5 = this.tableProperties.keys();
         StringUtil.SQLEqualsIgnoreCase(this.tableDescriptor.getSchemaName(), "SYS");

         while(var5.hasMoreElements()) {
            String var6 = (String)var5.nextElement();
            String var7 = (String)this.tableProperties.get(var6);
            if (!var6.equals("index")) {
               if (var6.equals("constraint")) {
                  if (var2) {
                     throw StandardException.newException("42Y50", new Object[]{this.getBaseTableName()});
                  }

                  var3 = true;
                  if (!StringUtil.SQLToUpperCase(var7).equals("NULL")) {
                     var4 = var1.getConstraintDescriptorByName(this.tableDescriptor, (SchemaDescriptor)null, var7, false);
                     if (var4 == null || !var4.hasBackingIndex()) {
                        throw StandardException.newException("42Y48", new Object[]{var7, this.getBaseTableName()});
                     }

                     this.getCompilerContext().createDependency(var4);
                  }
               } else if (var6.equals("joinStrategy")) {
                  this.userSpecifiedJoinStrategy = StringUtil.SQLToUpperCase(var7);
               } else if (var6.equals("hashInitialCapacity")) {
                  this.initialCapacity = this.getIntProperty(var7, var6);
                  if (this.initialCapacity <= 0) {
                     throw StandardException.newException("42Y59", new Object[]{String.valueOf(this.initialCapacity)});
                  }
               } else if (var6.equals("hashLoadFactor")) {
                  try {
                     this.loadFactor = Float.parseFloat(var7);
                  } catch (NumberFormatException var12) {
                     throw StandardException.newException("42Y58", new Object[]{var7, var6});
                  }

                  if ((double)this.loadFactor <= (double)0.0F || (double)this.loadFactor > (double)1.0F) {
                     throw StandardException.newException("42Y60", new Object[]{var7});
                  }
               } else if (var6.equals("hashMaxCapacity")) {
                  this.maxCapacity = this.getIntProperty(var7, var6);
                  if (this.maxCapacity <= 0) {
                     throw StandardException.newException("42Y61", new Object[]{String.valueOf(this.maxCapacity)});
                  }
               } else if (var6.equals("bulkFetch")) {
                  this.bulkFetch = this.getIntProperty(var7, var6);
                  if (this.bulkFetch <= 0) {
                     throw StandardException.newException("42Y64", new Object[]{String.valueOf(this.bulkFetch)});
                  }

                  if (this.forUpdate()) {
                     throw StandardException.newException("42Y66", new Object[0]);
                  }
               } else if (!var6.equals("validateCheckConstraint")) {
                  throw StandardException.newException("42Y44", new Object[]{var6, "index, constraint, joinStrategy"});
               }
            } else {
               if (var3) {
                  throw StandardException.newException("42Y50", new Object[]{this.getBaseTableName()});
               }

               var2 = true;
               if (!StringUtil.SQLToUpperCase(var7).equals("NULL")) {
                  ConglomerateDescriptor var8 = null;
                  ConglomerateDescriptor[] var9 = this.tableDescriptor.getConglomerateDescriptors();

                  for(int var10 = 0; var10 < var9.length; ++var10) {
                     var8 = var9[var10];
                     String var11 = var8.getConglomerateName();
                     if (var11 != null && var11.equals(var7)) {
                        break;
                     }

                     var8 = null;
                  }

                  if (var8 == null) {
                     throw StandardException.newException("42Y46", new Object[]{var7, this.getBaseTableName()});
                  }

                  this.getCompilerContext().createDependency(var8);
               }
            }
         }

         if (var3 && var4 != null) {
            ConglomerateDescriptor var13 = var1.getConglomerateDescriptor(var4.getConglomerateId());
            String var14 = var13.getConglomerateName();
            this.tableProperties.remove("constraint");
            this.tableProperties.put("index", var14);
         }

      }
   }

   private boolean isValidatingCheckConstraint() throws StandardException {
      if (this.tableProperties == null) {
         return false;
      } else {
         Enumeration var1 = this.tableProperties.keys();

         while(var1.hasMoreElements()) {
            String var2 = (String)var1.nextElement();
            String var3 = (String)this.tableProperties.get(var2);
            if (var2.equals("validateCheckConstraint")) {
               this.targetTableUUIDString = var3;
               this.validatingCheckConstraint = true;
               return true;
            }
         }

         return false;
      }
   }

   public String getBaseTableName() {
      return this.tableName.getTableName();
   }

   public void startOptimizing(Optimizer var1, RowOrdering var2) {
      AccessPath var3 = this.getCurrentAccessPath();
      AccessPath var4 = this.getBestAccessPath();
      AccessPath var5 = this.getBestSortAvoidancePath();
      var3.setConglomerateDescriptor((ConglomerateDescriptor)null);
      var4.setConglomerateDescriptor((ConglomerateDescriptor)null);
      var5.setConglomerateDescriptor((ConglomerateDescriptor)null);
      var3.setCoveringIndexScan(false);
      var4.setCoveringIndexScan(false);
      var5.setCoveringIndexScan(false);
      var3.setLockMode(0);
      var4.setLockMode(0);
      var5.setLockMode(0);
      CostEstimate var6 = this.getCostEstimate(var1);
      var3.setCostEstimate(var6);
      var6.setCost(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
      super.startOptimizing(var1, var2);
   }

   public int convertAbsoluteToRelativeColumnPosition(int var1) {
      return this.mapAbsoluteToRelativeColumnPosition(var1);
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      boolean var8 = false;
      boolean var9 = false;
      PredicateList var10 = null;
      if (var4.useStatistics() && var1 != null) {
         var9 = this.tableDescriptor.statisticsExist(var2);
         var8 = this.tableDescriptor.statisticsExist((ConglomerateDescriptor)null);
         var10 = new PredicateList(this.getContextManager());
         var1.copyPredicatesToOtherList(var10);
         if (!this.hasCheckedIndexStats) {
            this.hasCheckedIndexStats = true;
            if (this.qualifiesForStatisticsUpdateCheck(this.tableDescriptor)) {
               this.tableDescriptor.markForIndexStatsUpdate(this.baseRowCount());
            }
         }
      }

      AccessPath var11 = this.getCurrentAccessPath();
      JoinStrategy var12 = var11.getJoinStrategy();
      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceEstimatingCostOfConglomerate(var2, this.tableNumber);
      }

      double var13 = var4.uniqueJoinWithOuterTable(var1);
      boolean var15 = this.isOneRowResultSet(var1);
      this.baseTableRestrictionList.removeAllElements();
      var12.getBasePredicates(var1, this.baseTableRestrictionList, this);
      StoreCostController var16 = this.getStoreCostController(var2);
      CostEstimate var17 = this.getScratchCostEstimate(var4);
      if (this.isOneRowResultSet(var2, this.baseTableRestrictionList)) {
         var5.optimizableAlwaysOrdered(this);
         this.singleScanRowCount = (double)1.0F;
         double var6 = var16.getFetchFromFullKeyCost((FormatableBitSet)null, 0);
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceSingleMatchedRowCost(var6, this.tableNumber);
         }

         var17.setCost(var6, (double)1.0F, (double)1.0F);
         double var18 = var17.getEstimatedCost();
         if (var12.multiplyBaseCostByOuterRows()) {
            var18 *= var3.rowCount();
         }

         var17.setCost(var18, var17.rowCount() * var3.rowCount(), var17.singleScanRowCount());
         boolean var20 = true;

         for(int var21 = 0; var21 < var1.size(); ++var21) {
            OptimizablePredicate var22 = var1.getOptPredicate(var21);
            if (!var22.isStartKey() && !var22.isStopKey()) {
               break;
            }

            if (!var22.getReferencedMap().hasSingleBitSet()) {
               var20 = false;
               break;
            }
         }

         if (var20) {
            var11.setLockMode(6);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceConstantStartStopPositions();
            }
         } else {
            this.setLockingBasedOnThreshold(var4, var17.rowCount());
         }

         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceCostOfNScans(this.tableNumber, var3.rowCount(), var17);
         }

         if (var2.isIndex() && !this.isCoveringIndex(var2)) {
            double var77 = this.getBaseCostController().getFetchFromRowLocationCost((FormatableBitSet)null, 0);
            var6 = var77 * var17.rowCount();
            var17.setEstimatedCost(var17.getEstimatedCost() + var6);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceNonCoveringIndexCost(var6, this.tableNumber);
            }
         }
      } else {
         double var75 = (double)1.0F;
         double var76 = (double)1.0F;
         double var78 = (double)1.0F;
         double var24 = (double)1.0F;
         double var26 = (double)1.0F;
         double var28 = (double)1.0F;
         byte var30 = 0;
         int var31 = 0;
         int var32 = 0;
         int var33 = 0;
         boolean var34 = false;
         boolean var35 = false;
         boolean var36 = false;
         boolean var37 = true;
         boolean var38 = false;
         int var39 = 0;
         int var40 = 0;
         int var42;
         if (var1 != null) {
            var42 = this.baseTableRestrictionList.size();
         } else {
            var42 = 0;
         }

         int var43 = 0;
         ColumnReference var44 = null;

         for(int var45 = 0; var45 < var42; ++var45) {
            OptimizablePredicate var41 = this.baseTableRestrictionList.getOptPredicate(var45);
            boolean var46 = var41.isStartKey();
            boolean var47 = var41.isStopKey();
            if (!var46 && !var47) {
               if (!this.baseTableRestrictionList.isRedundantPredicate(var45)) {
                  if (var41 instanceof Predicate) {
                     ValueNode var89 = ((Predicate)var41).getAndNode().getLeftOperand();
                     if (var44 != null && var89 instanceof LikeEscapeOperatorNode) {
                        LikeEscapeOperatorNode var92 = (LikeEscapeOperatorNode)var89;
                        if (var92.getLeftOperand().requiresTypeFromContext()) {
                           ValueNode var50 = ((TernaryOperatorNode)var92).getReceiver();
                           if (var50 instanceof ColumnReference) {
                              ColumnReference var51 = (ColumnReference)var50;
                              if (var51.getTableNumber() == var44.getTableNumber() && var51.getColumnNumber() == var44.getColumnNumber()) {
                                 var75 *= 0.2;
                              }
                           }
                        }
                     }
                  }

                  if (var41.isQualifier()) {
                     var78 *= var41.selectivity(this);
                     ++var32;
                  } else {
                     var24 *= var41.selectivity(this);
                     ++var33;
                  }

                  var34 = true;
                  var35 = true;
               }
            } else {
               var38 = true;
               if (!var41.getReferencedMap().hasSingleBitSet()) {
                  var37 = false;
               }

               boolean var48 = var41.compareWithKnownConstant(this, true);
               if (var46) {
                  if (var48 && !var34) {
                     ++var39;
                     if (var10 != null) {
                        var10.removeOptPredicate(var41);
                     }
                  } else {
                     var34 = true;
                  }
               }

               if (var47) {
                  if (var48 && !var35) {
                     ++var40;
                     if (var10 != null) {
                        var10.removeOptPredicate(var41);
                     }
                  } else {
                     var35 = true;
                  }
               }

               if ((var34 || var35) && !this.baseTableRestrictionList.isRedundantPredicate(var45)) {
                  if (var46 && var47) {
                     ++var43;
                  }

                  if (var41.getIndexPosition() == 0) {
                     var75 *= var41.selectivity(this);
                     if (!var36) {
                        ValueNode var49 = ((Predicate)var41).getAndNode().getLeftOperand();
                        if (var49 instanceof BinaryRelationalOperatorNode) {
                           var44 = ((BinaryRelationalOperatorNode)var49).getColumnOperand(this);
                        }

                        var36 = true;
                     }
                  } else {
                     var76 *= var41.selectivity(this);
                     ++var31;
                  }
               }
            }
         }

         if (var10 != null) {
            var28 = var10.selectivity(this);
            if (var28 == (double)-1.0F) {
               var28 = (double)1.0F;
            }
         }

         if (var36 && var43 > 0) {
            if (var9) {
               var26 = this.tableDescriptor.selectivityForConglomerate(var2, var43);
            } else if (var2.isIndex()) {
               IndexRowGenerator var85 = var2.getIndexDescriptor();
               if (var85.isUnique() && var85.numberOfOrderedColumns() == 1 && var43 == 1) {
                  var26 = (double)1.0F / (double)this.baseRowCount();
               }
            }
         }

         var24 *= var12.nonBasePredicateSelectivity(this, var1);
         DataValueDescriptor[] var86;
         if (var39 > 0) {
            var86 = new DataValueDescriptor[var39];
         } else {
            var86 = null;
         }

         DataValueDescriptor[] var87;
         if (var40 > 0) {
            var87 = new DataValueDescriptor[var40];
         } else {
            var87 = null;
         }

         var39 = 0;
         var40 = 0;
         var34 = false;
         var35 = false;
         InListOperatorNode var88 = null;

         for(int var90 = 0; var90 < var42; ++var90) {
            OptimizablePredicate var84 = this.baseTableRestrictionList.getOptPredicate(var90);
            boolean var93 = var84.isStartKey();
            boolean var95 = var84.isStopKey();
            if (!var93 && !var95) {
               var34 = true;
               var35 = true;
            } else {
               var88 = ((Predicate)var84).getSourceInList(true);
               boolean var97 = var84.compareWithKnownConstant(this, true);
               if (var93) {
                  if (var97 && !var34) {
                     var86[var39] = var84.getCompareValue(this);
                     ++var39;
                  } else {
                     var34 = true;
                  }
               }

               if (var95) {
                  if (var97 && !var35) {
                     var87[var40] = var84.getCompareValue(this);
                     ++var40;
                  } else {
                     var35 = true;
                  }
               }
            }
         }

         int var91;
         int var94;
         if (this.baseTableRestrictionList != null) {
            var91 = this.baseTableRestrictionList.startOperator(this);
            var94 = this.baseTableRestrictionList.stopOperator(this);
         } else {
            var91 = 0;
            var94 = 0;
         }

         DataValueDescriptor[] var96 = this.getRowTemplate(var2, this.getBaseCostController());
         long var98 = var86 == null && var87 == null ? this.baseRowCount() + 5L : this.baseRowCount();
         var16.getScanCost(var12.scanCostType(), var98, 1, this.forUpdate(), (FormatableBitSet)null, var96, var86, var91, var87, var94, false, 0, var17);
         double var53 = (double)0.0F;
         if (var2.isIndex()) {
            var53 = var16.getFetchFromFullKeyCost((FormatableBitSet)null, 0);
            if (var15 && var17.rowCount() <= (double)1.0F) {
               var17.setCost(var17.getEstimatedCost() * (double)2.0F, var17.rowCount() + (double)2.0F, var17.singleScanRowCount() + (double)2.0F);
            }
         }

         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceCostOfConglomerateScan(this.tableNumber, var2, var17, var30, var75, var31, var76, var43, var26, var32, var78, var33, var24);
         }

         double var55 = var17.rowCount();
         if (var26 != (double)1.0F) {
            var17.setCost(this.scanCostAfterSelectivity(var17.getEstimatedCost(), var53, var26, var15), var17.rowCount() * var26, var17.singleScanRowCount() * var26);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceCostIncludingStatsForIndex(var17, this.tableNumber);
            }
         } else {
            if (var75 != (double)1.0F) {
               var17.setCost(this.scanCostAfterSelectivity(var17.getEstimatedCost(), var53, var75, var15), var17.rowCount() * var75, var17.singleScanRowCount() * var75);
               if (this.optimizerTracingIsOn()) {
                  this.getOptimizerTracer().traceCostIncludingExtra1stColumnSelectivity(var17, this.tableNumber);
               }
            }

            if (var76 != (double)1.0F) {
               var17.setCost(var17.getEstimatedCost(), var17.rowCount() * var76, var17.singleScanRowCount() * var76);
               if (this.optimizerTracingIsOn()) {
                  this.getOptimizerTracer().traceCostIncludingExtraStartStop(var17, this.tableNumber);
               }
            }
         }

         if (var88 != null) {
            int var57 = var88.getRightOperandList().size();
            double var58 = var17.rowCount() * (double)var57;
            double var60 = var17.singleScanRowCount() * (double)var57;
            var17.setCost(var17.getEstimatedCost() * (double)var57, var58 > var55 ? var55 : var58, var60 > var55 ? var55 : var60);
         }

         if (!var38) {
            var11.setLockMode(7);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceNoStartStopPosition();
            }
         } else {
            double var100 = var17.rowCount();
            if (!var37 && var12.multiplyBaseCostByOuterRows()) {
               double var59 = (double)this.baseRowCount();
               if (var59 > (double)0.0F) {
                  double var61 = var17.rowCount();
                  double var63 = var3.rowCount();
                  double var65 = (double)1.0F - var61 / var59;
                  double var67 = Math.pow(var65, var63);
                  double var69 = (double)1.0F - var67;
                  double var71 = var59 * var69;
                  var100 = var71;
               } else {
                  var100 = (double)(var4.tableLockThreshold() + 1);
               }
            }

            this.setLockingBasedOnThreshold(var4, var100);
         }

         if (var2.isIndex() && !this.isCoveringIndex(var2)) {
            double var101 = this.getBaseCostController().getFetchFromRowLocationCost((FormatableBitSet)null, 0);
            double var103 = var17.rowCount();
            if (var15) {
               var103 = Math.max((double)1.0F, var103);
            }

            double var74 = var101 * var103;
            var17.setEstimatedCost(var17.getEstimatedCost() + var74);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceCostOfNoncoveringIndex(var17, this.tableNumber);
            }
         }

         if (var78 != (double)1.0F) {
            var17.setCost(var17.getEstimatedCost(), var17.rowCount() * var78, var17.singleScanRowCount() * var78);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceCostIncludingExtraQualifierSelectivity(var17, this.tableNumber);
            }
         }

         this.singleScanRowCount = var17.singleScanRowCount();
         double var102 = var17.getEstimatedCost();
         double var104 = var17.rowCount();
         if (var12.multiplyBaseCostByOuterRows()) {
            var102 *= var3.rowCount();
         }

         var104 *= var3.rowCount();
         var55 *= var3.rowCount();
         if (var15 && var3.rowCount() < var104) {
            var104 = var3.rowCount();
         }

         if (var2.isIndex() && var38 && !var37) {
            double var106 = var4.uniqueJoinWithOuterTable(this.baseTableRestrictionList);
            if (var106 > (double)0.0F) {
               double var109 = (double)this.baseRowCount() / var106;
               if (var104 > var109) {
                  var102 *= var109 / var104;
               }
            }
         }

         if (var13 > (double)0.0F) {
            double var107 = (double)this.baseRowCount() / var13;
            if (var104 > var107) {
               var104 = var107;
            }
         }

         var17.setCost(var102, var104, var17.singleScanRowCount());
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceCostOfNScans(this.tableNumber, var3.rowCount(), var17);
         }

         double var108 = (double)-1.0F;
         double var110 = (double)-1.0F;
         if (this.existsBaseTable) {
            var110 = (double)1.0F;
            var108 = (double)1.0F;
         } else if (var24 != (double)1.0F) {
            var108 = var15 ? var17.rowCount() : var17.rowCount() * var24;
            var110 = var17.singleScanRowCount() * var24;
         }

         if (var108 != (double)-1.0F) {
            var17.setCost(var17.getEstimatedCost(), var108, var110);
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceCostIncludingExtraNonQualifierSelectivity(var17, this.tableNumber);
            }
         }

         if (var8 && !var15 && var28 != (double)1.0F) {
            double var111 = var55 * var28;
            if (this.optimizerTracingIsOn()) {
               this.getOptimizerTracer().traceCompositeSelectivityFromStatistics(var28);
            }

            if (!(var13 > (double)0.0F) || !(var111 > (double)this.baseRowCount() * var13)) {
               var17.setCost(var17.getEstimatedCost(), var111, this.existsBaseTable ? (double)1.0F : var111 / var3.rowCount());
               if (this.optimizerTracingIsOn()) {
                  this.getOptimizerTracer().traceCostIncludingCompositeSelectivityFromStats(var17, this.tableNumber);
               }
            }
         }
      }

      var12.putBasePredicates(var1, this.baseTableRestrictionList);
      return var17;
   }

   private double scanCostAfterSelectivity(double var1, double var3, double var5, boolean var7) throws StandardException {
      if (var7) {
         double var8 = (double)this.baseRowCount();
         if (var8 > (double)0.0F) {
            double var10 = (double)2.0F / var8;
            if (var10 > var5) {
               var5 = var10;
            }
         }
      }

      double var12 = (var1 - var3) * var5;
      if (var12 < (double)0.0F) {
         var12 = (double)0.0F;
      }

      return var3 + var12;
   }

   private void setLockingBasedOnThreshold(Optimizer var1, double var2) {
      this.getCurrentAccessPath().setLockMode(6);
   }

   public boolean isBaseTable() {
      return true;
   }

   public boolean forUpdate() {
      return this.updateOrDelete != 0 || this.isCursorTargetTable() || this.getUpdateLocks;
   }

   public int initialCapacity() {
      return this.initialCapacity;
   }

   public float loadFactor() {
      return this.loadFactor;
   }

   public boolean memoryUsageOK(double var1, int var3) throws StandardException {
      return super.memoryUsageOK(this.singleScanRowCount, var3);
   }

   public boolean isTargetTable() {
      return this.updateOrDelete != 0;
   }

   public double uniqueJoin(OptimizablePredicateList var1) throws StandardException {
      double var2 = (double)-1.0F;
      PredicateList var4 = (PredicateList)var1;
      int var5 = this.getTableDescriptor().getNumberOfColumns();
      int var6 = this.getTableNumber();
      int[] var7 = new int[0];
      JBitSet[] var8 = new JBitSet[]{new JBitSet(var5 + 1)};
      var4.checkTopPredicatesForEqualsConditions(var6, (boolean[])null, var7, var8, false);
      if (this.supersetOfUniqueIndex(var8)) {
         var2 = this.getBestAccessPath().getCostEstimate().singleScanRowCount();
      }

      return var2;
   }

   public boolean isOneRowScan() throws StandardException {
      return this.existsBaseTable ? false : super.isOneRowScan();
   }

   public boolean legalJoinOrder(JBitSet var1) {
      return this.existsBaseTable ? var1.contains(this.dependencyMap) : true;
   }

   public String toString() {
      return "";
   }

   boolean getExistsBaseTable() {
      return this.existsBaseTable;
   }

   void setExistsBaseTable(boolean var1, JBitSet var2, boolean var3) {
      this.existsBaseTable = var1;
      this.isNotExists = var3;
      if (var1) {
         this.dependencyMap = var2;
      } else {
         this.dependencyMap = null;
      }

   }

   void clearDependency(List var1) {
      if (this.dependencyMap != null) {
         for(int var2 = 0; var2 < var1.size(); ++var2) {
            this.dependencyMap.clear((Integer)var1.get(var2));
         }
      }

   }

   void setTableProperties(Properties var1) {
      this.tableProperties = var1;
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      this.tableName.bind();
      TableDescriptor var3 = this.bindTableDescriptor();
      if (var3.getTableType() == 5) {
         ResultSetNode var17 = this.mapTableAsVTI(var3, this.getCorrelationName(), this.getResultColumns(), this.getProperties(), this.getContextManager());
         return var17.bindNonVTITables(var1, var2);
      } else {
         ResultColumnList var4 = this.getResultColumns();
         this.restrictionList = new PredicateList(this.getContextManager());
         this.baseTableRestrictionList = new PredicateList(this.getContextManager());
         CompilerContext var5 = this.getCompilerContext();
         this.setResultColumns(this.genResultColList());
         this.templateColumns = this.getResultColumns();
         if (var3.getTableType() == 2) {
            ViewDescriptor var8 = var1.getViewDescriptor(var3);
            SchemaDescriptor var10 = var1.getSchemaDescriptor(var8.getCompSchemaId(), (TransactionController)null);
            var5.pushCompilationSchema(var10);

            ResultSetNode var23;
            try {
               var5.createDependency(var8);
               CreateViewNode var9 = (CreateViewNode)this.parseStatement(var8.getViewText(), false);
               ResultSetNode var19 = var9.getParsedQueryExpression();
               if (var19.getResultColumns().containsAllResultColumn()) {
                  this.getResultColumns().setCountMismatchAllowed(true);
               }

               for(ResultColumn var12 : this.getResultColumns()) {
                  if (this.isPrivilegeCollectionRequired()) {
                     var5.addRequiredColumnPriv(var12.getTableColumnDescriptor());
                  }
               }

               FromSubquery var18 = new FromSubquery(var19, var9.getOrderByList(), var9.getOffset(), var9.getFetchFirst(), var9.hasJDBClimitClause(), this.correlationName != null ? this.correlationName : this.getOrigTableName().getTableName(), this.getResultColumns(), this.tableProperties, this.getContextManager());
               var18.setLevel(this.level);
               CollectNodesVisitor var20 = new CollectNodesVisitor(QueryTreeNode.class);
               var18.accept(var20);

               for(QueryTreeNode var13 : var20.getList()) {
                  var13.disablePrivilegeCollection();
               }

               var18.setOrigTableName(this.getOrigTableName());
               var18.setOrigCompilationSchema(var10);
               ResultSetNode var22 = var18.bindNonVTITables(var1, var2);
               if (var4 != null) {
                  var22.getResultColumns().propagateDCLInfo(var4, this.origTableName.getFullTableName());
               }

               var23 = var22;
            } finally {
               var5.popCompilationSchema();
            }

            return var23;
         } else {
            var5.createDependency(var3);
            this.baseConglomerateDescriptor = var3.getConglomerateDescriptor(var3.getHeapConglomerateId());
            if (this.baseConglomerateDescriptor == null) {
               throw StandardException.newException("XSAI2.S", new Object[]{var3.getHeapConglomerateId()});
            } else {
               this.columnNames = this.getResultColumns().getColumnNames();
               if (var4 != null) {
                  this.getResultColumns().propagateDCLInfo(var4, this.origTableName.getFullTableName());
               }

               if (this.tableNumber == -1) {
                  this.tableNumber = var5.getNextTableNumber();
               }

               this.authorizeSYSUSERS = var1.usesSqlAuthorization() && var3.getUUID().toString().equals("9810800c-0134-14a5-40c1-000004f61f90");
               if (this.authorizeSYSUSERS) {
                  String var6 = var1.getAuthorizationDatabaseOwner();
                  String var7 = this.getLanguageConnectionContext().getStatementContext().getSQLSessionContext().getCurrentUser();
                  if (!var6.equals(var7)) {
                     throw StandardException.newException("4251D", new Object[0]);
                  }
               }

               return this;
            }
         }
      }
   }

   private ResultSetNode mapTableAsVTI(TableDescriptor var1, String var2, ResultColumnList var3, Properties var4, ContextManager var5) throws StandardException {
      List var6 = Collections.emptyList();
      NewInvocationNode var7 = new NewInvocationNode((TableName)null, var1, var6, false, var5);
      FromVTI var8;
      if (var2 != null) {
         var8 = new FromVTI(var7, var2, var3, var4, var5);
      } else {
         TableName var9 = ((MethodCallNode)var7).makeTableName(var1.getSchemaName(), var1.getDescriptorName());
         var8 = new FromVTI(var7, (String)null, var3, var4, var9, var5);
      }

      return var8;
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      String var4 = this.getOrigTableName().getSchemaName();
      String var5 = var2 != null ? var2 + "." + var1 : var1;
      if (var3) {
         if ((var2 == null || var4 != null) && (var2 != null || var4 == null)) {
            return this.getExposedName().equals(var5) ? this : null;
         } else {
            return null;
         }
      } else if (this.getExposedName().equals(var5)) {
         return this;
      } else if ((var2 == null || var4 == null) && (var2 != null || var4 != null)) {
         if (var2 != null && var4 == null) {
            if (this.tableName.equals(this.origTableName) && !var2.equals(this.tableDescriptor.getSchemaDescriptor().getSchemaName())) {
               return null;
            } else if (!this.getExposedName().equals(var1)) {
               return null;
            } else {
               return !this.getExposedName().equals(this.getOrigTableName().getTableName()) ? null : this;
            }
         } else {
            String var10000 = this.getExposedName();
            String var10001 = this.getOrigTableName().getSchemaName();
            return !var10000.equals(var10001 + "." + var1) ? null : this;
         }
      } else {
         return null;
      }
   }

   private TableDescriptor bindTableDescriptor() throws StandardException {
      String var1 = this.tableName.getSchemaName();
      SchemaDescriptor var2 = this.getSchemaDescriptor(var1);
      this.tableDescriptor = this.getTableDescriptor(this.tableName.getTableName(), var2);
      if (this.tableDescriptor == null) {
         TableName var3 = this.resolveTableToSynonym(this.tableName);
         if (var3 == null) {
            throw StandardException.newException("42X05", new Object[]{this.tableName});
         }

         this.tableName = var3;
         var2 = this.getSchemaDescriptor(this.tableName.getSchemaName());
         this.tableDescriptor = this.getTableDescriptor(var3.getTableName(), var2);
         if (this.tableDescriptor == null) {
            throw StandardException.newException("42X05", new Object[]{this.tableName});
         }
      }

      return this.tableDescriptor;
   }

   void bindExpressions(FromList var1) throws StandardException {
   }

   void bindResultColumns(FromList var1) throws StandardException {
   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      ResultColumn var2 = null;
      TableName var3 = var1.getQualifiedTableName();
      if (var3 != null && var3.getSchemaName() == null && this.correlationName == null) {
         var3.bind();
      }

      TableName var4 = this.getExposedTableName();
      if (var4.getSchemaName() == null && this.correlationName == null) {
         var4.bind();
      }

      if (var3 == null || var3.equals(var4)) {
         if (this.getResultColumns() == null) {
            throw StandardException.newException("42ZB7", new Object[]{var1.getColumnName()});
         }

         var2 = this.getResultColumns().getResultColumn(var1.getColumnName());
         if (var2 != null) {
            var1.setTableNumber(this.tableNumber);
            var1.setColumnNumber(var2.getColumnPosition());
            if (this.tableDescriptor != null && (this.rowLocationColumnName == null || !this.rowLocationColumnName.equals(var1.getColumnName()))) {
               if (var1.isPrivilegeCollectionRequired() && var1.taggedWith("updatePrivs")) {
                  this.getCompilerContext().addRequiredColumnPriv(this.tableDescriptor.getColumnDescriptor(var1.getColumnName()));
               }

               FormatableBitSet var5 = this.tableDescriptor.getReferencedColumnMap();
               if (var5 == null) {
                  var5 = new FormatableBitSet(this.tableDescriptor.getNumberOfColumns() + 1);
               }

               var5.set(var2.getColumnPosition());
               this.tableDescriptor.setReferencedColumnMap(var5);
            }
         }
      }

      return var2;
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      if (this.authorizeSYSUSERS) {
         byte var4 = 3;
         FormatableBitSet var5 = this.getResultColumns().getReferencedFormatableBitSet(false, true, false);
         if (var5.getLength() >= var4 && var5.isSet(var4 - 1)) {
            throw StandardException.newException("4251E", new Object[]{"SYSUSERS", "PASSWORD"});
         }
      }

      this.setReferencedTableMap(new JBitSet(var1));
      this.getReferencedTableMap().set(this.tableNumber);
      return this.genProjectRestrict(var1);
   }

   protected ResultSetNode genProjectRestrict(int var1) throws StandardException {
      ResultColumnList var2 = this.getResultColumns();
      this.setResultColumns(this.getResultColumns().copyListAndObjects());
      this.getResultColumns().setIndexRow(this.baseConglomerateDescriptor.getConglomerateNumber(), this.forUpdate());
      var2.genVirtualColumnNodes(this, this.getResultColumns(), false);
      var2.doProjection();
      ProjectRestrictNode var3 = new ProjectRestrictNode(this, var2, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
      if (this.isValidatingCheckConstraint()) {
         CompilerContext var4 = this.getCompilerContext();
         if ((var4.getReliability() & 1024) != 0) {
            throw StandardException.newException("42X01", new Object[]{"validateCheckConstraint"});
         }

         var3.setValidatingCheckConstraints(this.targetTableUUIDString);
      }

      return var3;
   }

   ResultSetNode changeAccessPath() throws StandardException {
      AccessPath var2 = this.getTrulyTheBestAccessPath();
      ConglomerateDescriptor var3 = var2.getConglomerateDescriptor();
      JoinStrategy var4 = var2.getJoinStrategy();
      Optimizer var5 = var2.getOptimizer();
      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceChangingAccessPathForTable(this.tableNumber);
      }

      if (this.bulkFetch != -1) {
         if (!var4.bulkFetchOK()) {
            throw StandardException.newException("42Y65", new Object[]{var4.getName()});
         }

         if (var4.ignoreBulkFetch()) {
            this.disableBulkFetch();
         } else if (this.isOneRowResultSet()) {
            this.disableBulkFetch();
         }
      }

      if (this.bulkFetch == 1) {
         this.disableBulkFetch();
      }

      this.restrictionList.removeRedundantPredicates();
      this.storeRestrictionList = new PredicateList(this.getContextManager());
      this.nonStoreRestrictionList = new PredicateList(this.getContextManager());
      this.requalificationRestrictionList = new PredicateList(this.getContextManager());
      var4.divideUpPredicateLists(this, this.restrictionList, this.storeRestrictionList, this.nonStoreRestrictionList, this.requalificationRestrictionList, this.getDataDictionary());

      for(Predicate var7 : this.restrictionList) {
         if (var7.isInListProbePredicate() && var7.isStartKey()) {
            this.disableBulkFetch();
            this.multiProbing = true;
            break;
         }
      }

      if (var4.bulkFetchOK() && !var4.ignoreBulkFetch() && !this.bulkFetchTurnedOff && this.bulkFetch == -1 && !this.forUpdate() && !this.isOneRowResultSet() && this.getLevel() == 0 && !this.validatingCheckConstraint) {
         this.bulkFetch = this.getDefaultBulkFetch();
      }

      this.getCompilerContext().createDependency(var3);
      if (!var3.isIndex()) {
         boolean var11 = this.tableName.equals("SYS", "SYSSTATEMENTS");
         this.templateColumns = this.getResultColumns();
         this.referencedCols = this.getResultColumns().getReferencedFormatableBitSet(this.isCursorTargetTable(), var11, false);
         this.setResultColumns(this.getResultColumns().compactColumns(this.isCursorTargetTable(), var11));
         return this;
      } else if (var2.getCoveringIndexScan() && !this.isCursorTargetTable()) {
         this.setResultColumns(this.newResultColumns(this.getResultColumns(), var3, this.baseConglomerateDescriptor, false));
         this.templateColumns = this.newResultColumns(this.getResultColumns(), var3, this.baseConglomerateDescriptor, false);
         this.templateColumns.addRCForRID();
         if (this.forUpdate()) {
            this.getResultColumns().addRCForRID();
         }

         this.referencedCols = this.getResultColumns().getReferencedFormatableBitSet(this.isCursorTargetTable(), true, false);
         this.setResultColumns(this.getResultColumns().compactColumns(this.isCursorTargetTable(), true));
         this.getResultColumns().setIndexRow(this.baseConglomerateDescriptor.getConglomerateNumber(), this.forUpdate());
         return this;
      } else {
         this.getCompilerContext().createDependency(this.baseConglomerateDescriptor);
         if (this.bulkFetch != -1) {
            this.restrictionList.copyPredicatesToOtherList(this.requalificationRestrictionList);
         }

         ResultColumnList var10 = this.newResultColumns(this.getResultColumns(), var3, this.baseConglomerateDescriptor, true);
         FormatableBitSet var12 = null;
         FormatableBitSet var8;
         if (this.bulkFetch == -1 && (this.requalificationRestrictionList == null || this.requalificationRestrictionList.size() == 0)) {
            var12 = this.getResultColumns().getReferencedFormatableBitSet(this.isCursorTargetTable(), true, false);
            var8 = this.getResultColumns().getReferencedFormatableBitSet(this.isCursorTargetTable(), true, true);
            if (var8 != null) {
               var12.xor(var8);
            }
         } else {
            var8 = this.getResultColumns().getReferencedFormatableBitSet(this.isCursorTargetTable(), true, false);
         }

         ResultColumnList var9 = this.getResultColumns().compactColumns(this.isCursorTargetTable(), false);
         var9.setIndexRow(this.baseConglomerateDescriptor.getConglomerateNumber(), this.forUpdate());
         IndexToBaseRowNode var1 = new IndexToBaseRowNode(this, this.baseConglomerateDescriptor, var9, this.isCursorTargetTable(), var8, var12, this.requalificationRestrictionList, this.forUpdate(), this.tableProperties, this.getContextManager());
         this.setResultColumns(var10);
         this.templateColumns = this.newResultColumns(this.getResultColumns(), var3, this.baseConglomerateDescriptor, false);
         if (this.bulkFetch != -1) {
            this.getResultColumns().markAllUnreferenced();
            this.storeRestrictionList.markReferencedColumns();
            if (this.nonStoreRestrictionList != null) {
               this.nonStoreRestrictionList.markReferencedColumns();
            }
         }

         this.getResultColumns().addRCForRID();
         this.templateColumns.addRCForRID();
         this.referencedCols = this.getResultColumns().getReferencedFormatableBitSet(this.isCursorTargetTable(), false, false);
         this.setResultColumns(this.getResultColumns().compactColumns(this.isCursorTargetTable(), false));
         this.getResultColumns().setIndexRow(this.baseConglomerateDescriptor.getConglomerateNumber(), this.forUpdate());
         this.getUpdateLocks = this.isCursorTargetTable();
         this.setCursorTargetTable(false);
         return var1;
      }
   }

   private ResultColumnList newResultColumns(ResultColumnList var1, ConglomerateDescriptor var2, ConglomerateDescriptor var3, boolean var4) throws StandardException {
      IndexRowGenerator var5 = var2.getIndexDescriptor();
      int[] var6 = var5.baseColumnPositions();
      ResultColumnList var7 = new ResultColumnList(this.getContextManager());

      for(int var8 = 0; var8 < var6.length; ++var8) {
         int var9 = var6[var8];
         ResultColumn var10 = var1.getResultColumn(var9);
         ResultColumn var11;
         if (var4) {
            var11 = var10.cloneMe();
            var10.setExpression(new VirtualColumnNode(this, var11, var10.getVirtualColumnId(), this.getContextManager()));
         } else {
            var11 = var10;
         }

         var7.addResultColumn(var11);
      }

      var7.setIndexRow(var3.getConglomerateNumber(), this.forUpdate());
      return var7;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.rowLocationColumnName != null) {
         this.getResultColumns().conglomerateId = this.tableDescriptor.getHeapConglomerateId();
      }

      this.generateResultSet(var1, var2);
      if (this.isCursorTargetTable()) {
         var1.rememberCursorTarget(var2);
      }

   }

   void generateResultSet(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      if (this.specialMaxScan) {
         this.generateMaxSpecialResultSet(var1, var2);
      } else if (this.distinctScan) {
         this.generateDistinctScan(var1, var2);
      } else if (this.raDependentScan) {
         this.generateRefActionDependentTableScan(var1, var2);
      } else {
         JoinStrategy var3 = this.getTrulyTheBestAccessPath().getJoinStrategy();
         var1.pushGetResultSetFactoryExpression(var2);
         int var4 = this.getScanArguments(var1, var2);
         var2.callMethod((short)185, (String)null, var3.resultSetMethodName(this.bulkFetch != -1, this.multiProbing, this.validatingCheckConstraint), "org.apache.derby.iapi.sql.execute.NoPutResultSet", var4);
         if (this.updateOrDelete == 1 || this.updateOrDelete == 2) {
            var2.cast("org.apache.derby.iapi.sql.execute.CursorResultSet");
            var2.putField(var1.getRowLocationScanResultSetName(), "org.apache.derby.iapi.sql.execute.CursorResultSet");
            var2.cast("org.apache.derby.iapi.sql.execute.NoPutResultSet");
         }

      }
   }

   CostEstimate getFinalCostEstimate() {
      return this.getTrulyTheBestAccessPath().getCostEstimate();
   }

   private void pushIndexName(ConglomerateDescriptor var1, MethodBuilder var2) throws StandardException {
      if (var1.isConstraint()) {
         DataDictionary var3 = this.getDataDictionary();
         ConstraintDescriptor var4 = var3.getConstraintDescriptor(this.tableDescriptor, var1.getUUID());
         var2.push(var4.getConstraintName());
      } else if (var1.isIndex()) {
         var2.push(var1.getConglomerateName());
      } else {
         var2.pushNull("java.lang.String");
      }

   }

   private void generateMaxSpecialResultSet(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      ConglomerateDescriptor var3 = this.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      CostEstimate var4 = this.getFinalCostEstimate();
      int var5 = this.referencedCols == null ? -1 : var1.addItem(this.referencedCols);
      boolean var6 = this.tableDescriptor.getLockGranularity() == 'T';
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      var2.push(this.getResultSetNumber());
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate(this.referencedCols, false)));
      var2.push(var3.getConglomerateNumber());
      var2.push(this.tableDescriptor.getName());
      if (this.tableProperties != null) {
         var2.push(PropertyUtil.sortProperties(this.tableProperties));
      } else {
         var2.pushNull("java.lang.String");
      }

      this.pushIndexName(var3, var2);
      var2.push(var5);
      var2.push(this.getTrulyTheBestAccessPath().getLockMode());
      var2.push(var6);
      var2.push(this.getCompilerContext().getScanIsolationLevel());
      var2.push(var4.singleScanRowCount());
      var2.push(var4.getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getLastIndexKeyResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 13);
   }

   private void generateDistinctScan(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      ConglomerateDescriptor var3 = this.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      CostEstimate var4 = this.getFinalCostEstimate();
      int var5 = this.referencedCols == null ? -1 : var1.addItem(this.referencedCols);
      boolean var6 = this.tableDescriptor.getLockGranularity() == 'T';
      int[] var7 = new int[this.getResultColumns().size()];
      if (this.referencedCols == null) {
         for(int var8 = 0; var8 < var7.length; var7[var8] = var8++) {
         }
      } else {
         int var14 = 0;

         for(int var9 = this.referencedCols.anySetBit(); var9 != -1; var9 = this.referencedCols.anySetBit(var9)) {
            var7[var14++] = var9;
         }
      }

      FormatableIntHolder[] var15 = FormatableIntHolder.getFormatableIntHolders(var7);
      FormatableArrayHolder var16 = new FormatableArrayHolder(var15);
      int var10 = var1.addItem(var16);
      long var11 = var3.getConglomerateNumber();
      StaticCompiledOpenConglomInfo var13 = this.getLanguageConnectionContext().getTransactionCompile().getStaticCompiledConglomInfo(var11);
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      var2.push(var11);
      var2.push(var1.addItem(var13));
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate(this.referencedCols, false)));
      var2.push(this.getResultSetNumber());
      var2.push(var10);
      var2.push(this.tableDescriptor.getName());
      if (this.tableProperties != null) {
         var2.push(PropertyUtil.sortProperties(this.tableProperties));
      } else {
         var2.pushNull("java.lang.String");
      }

      this.pushIndexName(var3, var2);
      var2.push(var3.isConstraint());
      var2.push(var5);
      var2.push(this.getTrulyTheBestAccessPath().getLockMode());
      var2.push(var6);
      var2.push(this.getCompilerContext().getScanIsolationLevel());
      var2.push(var4.singleScanRowCount());
      var2.push(var4.getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getDistinctScanResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 16);
   }

   private void generateRefActionDependentTableScan(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushGetResultSetFactoryExpression(var2);
      int var3 = this.getScanArguments(var1, var2);
      var2.push(this.raParentResultSetId);
      var2.push(this.fkIndexConglomId);
      var2.push(var1.addItem(this.fkColArray));
      var2.push(var1.addItem(this.getDataDictionary().getRowLocationTemplate(this.getLanguageConnectionContext(), this.tableDescriptor)));
      int var4 = var3 + 4;
      var2.callMethod((short)185, (String)null, "getRaDependentTableScanResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", var4);
      if (this.updateOrDelete == 1 || this.updateOrDelete == 2) {
         var2.cast("org.apache.derby.iapi.sql.execute.CursorResultSet");
         var2.putField(var1.getRowLocationScanResultSetName(), "org.apache.derby.iapi.sql.execute.CursorResultSet");
         var2.cast("org.apache.derby.iapi.sql.execute.NoPutResultSet");
      }

   }

   private int getScanArguments(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      int var3 = var1.addItem(this.getResultColumns().buildRowTemplate(this.referencedCols, false));
      int var4 = -1;
      if (this.referencedCols != null) {
         var4 = var1.addItem(this.referencedCols);
      }

      int var5 = -1;
      if (this.isCursorTargetTable() || this.getUpdateLocks) {
         ConglomerateDescriptor var6 = this.getTrulyTheBestAccessPath().getConglomerateDescriptor();
         if (var6.isIndex()) {
            int[] var7 = var6.getIndexDescriptor().baseColumnPositions();
            boolean[] var8 = var6.getIndexDescriptor().isAscending();
            int[] var9 = new int[var7.length];

            for(int var10 = 0; var10 < var9.length; ++var10) {
               var9[var10] = var8[var10] ? var7[var10] : -var7[var10];
            }

            var5 = var1.addItem(var9);
         }
      }

      AccessPath var11 = this.getTrulyTheBestAccessPath();
      JoinStrategy var12 = var11.getJoinStrategy();
      int var13 = var12.getScanArgs(this.getLanguageConnectionContext().getTransactionCompile(), var2, this, this.storeRestrictionList, this.nonStoreRestrictionList, var1, this.bulkFetch, var3, var4, var5, this.getTrulyTheBestAccessPath().getLockMode(), this.tableDescriptor.getLockGranularity() == 'T', this.getCompilerContext().getScanIsolationLevel(), var11.getOptimizer().getMaxMemoryPerTable(), this.multiProbing);
      return var13;
   }

   private int mapAbsoluteToRelativeColumnPosition(int var1) {
      if (this.referencedCols == null) {
         return var1;
      } else {
         int var2 = 0;

         for(int var3 = 0; var3 < this.referencedCols.size() && var3 < var1; ++var3) {
            if (this.referencedCols.get(var3)) {
               ++var2;
            }
         }

         return var2;
      }
   }

   String getExposedName() {
      return this.correlationName != null ? this.correlationName : this.getOrigTableName().getFullTableName();
   }

   TableName getExposedTableName() throws StandardException {
      return this.correlationName != null ? this.makeTableName((String)null, this.correlationName) : this.getOrigTableName();
   }

   TableName getTableNameField() {
      return this.tableName;
   }

   ResultColumnList getAllResultColumns(TableName var1) throws StandardException {
      return this.getResultColumnsForList(var1, this.getResultColumns(), this.getOrigTableName());
   }

   ResultColumnList genResultColList() throws StandardException {
      TableName var3 = this.getExposedTableName();
      ResultColumnList var4 = new ResultColumnList(this.getContextManager());
      ColumnDescriptorList var5 = this.tableDescriptor.getColumnDescriptorList();
      int var6 = var5.size();

      for(int var7 = 0; var7 < var6; ++var7) {
         ColumnDescriptor var8 = var5.elementAt(var7);
         var8.setTableDescriptor(this.tableDescriptor);
         BaseColumnNode var2 = new BaseColumnNode(var8.getColumnName(), var3, var8.getType(), this.getContextManager());
         ResultColumn var1 = new ResultColumn(var8, var2, this.getContextManager());
         var4.addResultColumn(var1);
      }

      if (this.rowLocationColumnName != null) {
         CurrentRowLocationNode var9 = new CurrentRowLocationNode(this.getContextManager());
         ResultColumn var10 = new ResultColumn(this.rowLocationColumnName, var9, this.getContextManager());
         var10.markGenerated();
         var9.bindExpression((FromList)null, (SubqueryList)null, (List)null);
         var10.bindResultColumnToExpression();
         var4.addResultColumn(var10);
      }

      return var4;
   }

   ResultColumnList addColsToList(ResultColumnList var1, FormatableBitSet var2) throws StandardException {
      TableName var4 = this.getExposedTableName();
      ResultColumnList var5 = new ResultColumnList(this.getContextManager());
      ColumnDescriptorList var6 = this.tableDescriptor.getColumnDescriptorList();
      int var7 = var6.size();

      for(int var8 = 0; var8 < var7; ++var8) {
         ColumnDescriptor var9 = var6.elementAt(var8);
         int var10 = var9.getPosition();
         if (var2.get(var10)) {
            ResultColumn var3;
            if ((var3 = var1.getResultColumn(var10)) == null) {
               ColumnReference var11 = new ColumnReference(var9.getColumnName(), var4, this.getContextManager());
               if (this.getMergeTableID() != 0) {
                  var11.setMergeTableID(this.getMergeTableID());
               }

               var3 = new ResultColumn(var9, var11, this.getContextManager());
            }

            var5.addResultColumn(var3);
         }
      }

      return var5;
   }

   TableName getTableName() throws StandardException {
      TableName var1 = super.getTableName();
      if (var1 != null && var1.getSchemaName() == null && this.correlationName == null) {
         var1.bind();
      }

      return var1 != null ? var1 : this.tableName;
   }

   boolean markAsCursorTargetTable() {
      this.setCursorTargetTable(true);
      return true;
   }

   protected boolean cursorTargetTable() {
      return this.isCursorTargetTable();
   }

   void markUpdated(ResultColumnList var1) {
      this.getResultColumns().markUpdated(var1);
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return var2 && var1.equals(this.getBaseTableName());
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.isSessionSchema(this.tableDescriptor.getSchemaDescriptor());
   }

   boolean isOneRowResultSet() throws StandardException {
      if (this.existsBaseTable) {
         return true;
      } else {
         AccessPath var1 = this.getTrulyTheBestAccessPath();
         JoinStrategy var2 = var1.getJoinStrategy();
         if (var2.isHashJoin()) {
            PredicateList var3 = new PredicateList(this.getContextManager());
            if (this.storeRestrictionList != null) {
               var3.nondestructiveAppend(this.storeRestrictionList);
            }

            if (this.nonStoreRestrictionList != null) {
               var3.nondestructiveAppend(this.nonStoreRestrictionList);
            }

            return this.isOneRowResultSet(var3);
         } else {
            return this.isOneRowResultSet(this.getTrulyTheBestAccessPath().getConglomerateDescriptor(), this.restrictionList);
         }
      }
   }

   boolean isNotExists() {
      return this.isNotExists;
   }

   boolean isOneRowResultSet(OptimizablePredicateList var1) throws StandardException {
      ConglomerateDescriptor[] var2 = this.tableDescriptor.getConglomerateDescriptors();

      for(int var3 = 0; var3 < var2.length; ++var3) {
         if (this.isOneRowResultSet(var2[var3], var1)) {
            return true;
         }
      }

      return false;
   }

   protected boolean supersetOfUniqueIndex(boolean[] var1) throws StandardException {
      ConglomerateDescriptor[] var2 = this.tableDescriptor.getConglomerateDescriptors();

      for(int var3 = 0; var3 < var2.length; ++var3) {
         ConglomerateDescriptor var4 = var2[var3];
         if (var4.isIndex()) {
            IndexRowGenerator var5 = var4.getIndexDescriptor();
            if (var5.isUnique()) {
               int[] var6 = var5.baseColumnPositions();

               int var7;
               for(var7 = 0; var7 < var6.length && var1[var6[var7]]; ++var7) {
               }

               if (var7 == var6.length) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   protected boolean supersetOfUniqueIndex(JBitSet[] var1) throws StandardException {
      ConglomerateDescriptor[] var2 = this.tableDescriptor.getConglomerateDescriptors();

      for(int var3 = 0; var3 < var2.length; ++var3) {
         ConglomerateDescriptor var4 = var2[var3];
         if (var4.isIndex()) {
            IndexRowGenerator var5 = var4.getIndexDescriptor();
            if (var5.isUnique()) {
               int[] var6 = var5.baseColumnPositions();
               int var7 = var1[0].size();
               JBitSet var8 = new JBitSet(var7);
               JBitSet var9 = new JBitSet(var7);

               for(int var10 = 0; var10 < var6.length; ++var10) {
                  var8.set(var6[var10]);
               }

               for(int var11 = 0; var11 < var1.length; ++var11) {
                  var9.setTo(var1[var11]);
                  var9.and(var8);
                  if (var8.equals(var9)) {
                     var1[var11].set(0);
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   int updateTargetLockMode() {
      if (this.getTrulyTheBestAccessPath().getConglomerateDescriptor().isIndex()) {
         return 6;
      } else {
         int var1 = this.getLanguageConnectionContext().getCurrentIsolationLevel();
         if (var1 != 4 && this.tableDescriptor.getLockGranularity() != 'T') {
            int var2 = this.getTrulyTheBestAccessPath().getLockMode();
            if (var2 != 6) {
               var2 = (var2 & 255) << 16;
            } else {
               var2 = 0;
            }

            var2 += 6;
            return var2;
         } else {
            return this.getTrulyTheBestAccessPath().getLockMode();
         }
      }
   }

   boolean isOrderedOn(ColumnReference[] var1, boolean var2, List var3) throws StandardException {
      for(int var4 = 0; var4 < var1.length; ++var4) {
         if (var1[var4].getTableNumber() != this.tableNumber) {
            return false;
         }
      }

      ConglomerateDescriptor var6 = this.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      if (!var6.isIndex()) {
         return false;
      } else {
         boolean var5;
         if (var2) {
            var5 = this.isOrdered(var1, var6);
         } else {
            var5 = this.isStrictlyOrdered(var1, var6);
         }

         if (var3 != null) {
            var3.add(this);
         }

         return var5;
      }
   }

   void disableBulkFetch() {
      this.bulkFetchTurnedOff = true;
      this.bulkFetch = -1;
   }

   void doSpecialMaxScan() {
      this.specialMaxScan = true;
   }

   boolean isPossibleDistinctScan(Set var1) {
      if (this.restrictionList != null && this.restrictionList.size() != 0) {
         return false;
      } else {
         HashSet var2 = new HashSet();

         for(ResultColumn var4 : this.getResultColumns()) {
            var2.add(var4.getExpression());
         }

         return var2.equals(var1);
      }
   }

   void markForDistinctScan() {
      this.distinctScan = true;
   }

   void adjustForSortElimination() {
   }

   void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException {
      if (this.restrictionList != null) {
         this.restrictionList.adjustForSortElimination(var1);
      }

   }

   private boolean isOrdered(ColumnReference[] var1, ConglomerateDescriptor var2) throws StandardException {
      boolean[] var3 = new boolean[var1.length];
      int var4 = 0;

      int[] var5;
      for(var5 = var2.getIndexDescriptor().baseColumnPositions(); var4 < var5.length; ++var4) {
         boolean var6 = false;

         for(int var7 = 0; var7 < var1.length; ++var7) {
            if (var1[var7].getColumnNumber() == var5[var4]) {
               var3[var7] = true;
               var6 = true;
               break;
            }
         }

         if (!var6 && !this.storeRestrictionList.hasOptimizableEqualityPredicate(this, var5[var4], true)) {
            break;
         }
      }

      int var8 = 0;

      for(int var9 = 0; var9 < var3.length; ++var9) {
         if (var3[var9]) {
            ++var8;
         }
      }

      if (var8 == var3.length) {
         return true;
      } else if (var4 == var5.length) {
         if (var2.getIndexDescriptor().isUnique()) {
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean isStrictlyOrdered(ColumnReference[] var1, ConglomerateDescriptor var2) throws StandardException {
      int var3 = 0;
      int var4 = 0;

      for(int[] var5 = var2.getIndexDescriptor().baseColumnPositions(); var3 < var1.length; ++var3) {
         if (var4 == var5.length) {
            if (!var2.getIndexDescriptor().isUnique()) {
               return false;
            }
            break;
         }

         if (var1[var3].getColumnNumber() == var5[var4]) {
            ++var4;
         } else {
            while(var1[var3].getColumnNumber() != var5[var4]) {
               if (!this.storeRestrictionList.hasOptimizableEqualityPredicate(this, var5[var4], true)) {
                  return false;
               }

               ++var4;
               if (var4 == var5.length) {
                  if (!var2.getIndexDescriptor().isUnique()) {
                     return false;
                  }
                  break;
               }
            }
         }
      }

      return true;
   }

   private boolean isOneRowResultSet(ConglomerateDescriptor var1, OptimizablePredicateList var2) throws StandardException {
      if (var2 == null) {
         return false;
      } else {
         PredicateList var3 = (PredicateList)var2;
         if (!var1.isIndex()) {
            return false;
         } else {
            IndexRowGenerator var4 = var1.getIndexDescriptor();
            if (!var4.isUnique()) {
               return false;
            } else {
               int[] var5 = var4.baseColumnPositions();

               for(int var6 = 0; var6 < var5.length; ++var6) {
                  int var7 = var5[var6];
                  if (!var3.hasOptimizableEqualityPredicate(this, var7, true)) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   private int getDefaultBulkFetch() throws StandardException {
      String var2 = org.apache.derby.iapi.services.property.PropertyUtil.getServiceProperty(this.getLanguageConnectionContext().getTransactionCompile(), "derby.language.bulkFetchDefault", "16");
      int var1 = this.getIntProperty(var2, "derby.language.bulkFetchDefault");
      if (var1 <= 0) {
         throw StandardException.newException("42Y64", new Object[]{String.valueOf(var1)});
      } else {
         return var1 <= 1 ? -1 : var1;
      }
   }

   private String getUserSpecifiedIndexName() {
      String var1 = null;
      if (this.tableProperties != null) {
         var1 = this.tableProperties.getProperty("index");
      }

      return var1;
   }

   private StoreCostController getStoreCostController(ConglomerateDescriptor var1) throws StandardException {
      return this.getCompilerContext().getStoreCostController(var1.getConglomerateNumber());
   }

   private StoreCostController getBaseCostController() throws StandardException {
      return this.getStoreCostController(this.baseConglomerateDescriptor);
   }

   private long baseRowCount() throws StandardException {
      if (!this.gotRowCount) {
         StoreCostController var1 = this.getBaseCostController();
         this.rowCount = var1.getEstimatedRowCount();
         this.gotRowCount = true;
      }

      return this.rowCount;
   }

   private DataValueDescriptor[] getRowTemplate(ConglomerateDescriptor var1, StoreCostController var2) throws StandardException {
      if (!var1.isIndex()) {
         return this.templateColumns.buildEmptyRow().getRowArray();
      } else {
         ExecRow var3 = this.templateColumns.buildEmptyIndexRow(this.tableDescriptor, var1, var2, this.getDataDictionary());
         return var3.getRowArray();
      }
   }

   private ConglomerateDescriptor getFirstConglom() throws StandardException {
      this.getConglomDescs();
      return this.conglomDescs[0];
   }

   private ConglomerateDescriptor getNextConglom(ConglomerateDescriptor var1) {
      int var2;
      for(var2 = 0; var2 < this.conglomDescs.length && var1 != this.conglomDescs[var2]; ++var2) {
      }

      return var2 < this.conglomDescs.length - 1 ? this.conglomDescs[var2 + 1] : null;
   }

   private void getConglomDescs() throws StandardException {
      if (this.conglomDescs == null) {
         this.conglomDescs = this.tableDescriptor.getConglomerateDescriptors();
      }

   }

   void setRefActionInfo(long var1, int[] var3, String var4, boolean var5) {
      this.fkIndexConglomId = var1;
      this.fkColArray = var3;
      this.raParentResultSetId = var4;
      this.raDependentScan = var5;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.nonStoreRestrictionList != null) {
         this.nonStoreRestrictionList.accept(var1);
      }

      if (this.restrictionList != null) {
         this.restrictionList.accept(var1);
      }

      if (this.nonBaseTableRestrictionList != null) {
         this.nonBaseTableRestrictionList.accept(var1);
      }

      if (this.requalificationRestrictionList != null) {
         this.requalificationRestrictionList.accept(var1);
      }

      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }

   private boolean qualifiesForStatisticsUpdateCheck(TableDescriptor var1) throws StandardException {
      int var2 = 0;
      if (var1.getTableType() == 0) {
         IndexStatisticsDaemonImpl var3 = (IndexStatisticsDaemonImpl)this.getDataDictionary().getIndexStatsRefresher(false);
         if (var3 == null) {
            var2 = 0;
         } else if (var3.skipDisposableStats) {
            var2 = var1.getQualifiedNumberOfIndexes(2, true);
         } else {
            var2 = var1.getTotalNumberOfIndexes();
         }
      }

      return var2 > 0;
   }
}
