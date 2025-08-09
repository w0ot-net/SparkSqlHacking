package org.apache.derby.impl.sql.compile;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

abstract class FromTable extends ResultSetNode implements Optimizable {
   Properties tableProperties;
   String correlationName;
   TableName corrTableName;
   int tableNumber;
   int level;
   int[] hashKeyColumns;
   int initialCapacity = -1;
   float loadFactor = -1.0F;
   int maxCapacity = -1;
   AccessPathImpl currentAccessPath;
   AccessPathImpl bestAccessPath;
   AccessPathImpl bestSortAvoidancePath;
   AccessPathImpl trulyTheBestAccessPath;
   private int joinStrategyNumber;
   protected String userSpecifiedJoinStrategy;
   protected CostEstimate bestCostEstimate;
   private double perRowUsage = (double)-1.0F;
   private boolean considerSortAvoidancePath;
   private HashMap bestPlanMap;
   protected static final short REMOVE_PLAN = 0;
   protected static final short ADD_PLAN = 1;
   protected static final short LOAD_PLAN = 2;
   protected TableName origTableName;
   private int _mergeTableID = 0;

   FromTable(String var1, Properties var2, ContextManager var3) {
      super(var3);
      this.correlationName = var1;
      this.tableProperties = var2;
      this.tableNumber = -1;
      this.bestPlanMap = null;
   }

   public String getCorrelationName() {
      return this.correlationName;
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      this.updateBestPlanMap((short)1, this);
      CostEstimate var5 = this.estimateCost(var2, (ConglomerateDescriptor)null, var3, var1, var4);
      this.getCostEstimate(var1);
      this.setCostEstimateCost(var5);
      this.optimizeSubqueries(this.getDataDictionary(), this.getCostEstimate().rowCount());
      this.getCurrentAccessPath().getJoinStrategy().estimateCost(this, var2, (ConglomerateDescriptor)null, var3, var1, this.getCostEstimate());
      var1.considerCost(this, var2, this.getCostEstimate(), var3);
      return this.getCostEstimate();
   }

   public boolean nextAccessPath(Optimizer var1, OptimizablePredicateList var2, RowOrdering var3) throws StandardException {
      int var4 = var1.getNumberOfJoinStrategies();
      boolean var5 = false;
      AccessPath var6 = this.getCurrentAccessPath();
      if (this.userSpecifiedJoinStrategy != null) {
         if (var6.getJoinStrategy() != null) {
            var6.setJoinStrategy((JoinStrategy)null);
            var5 = false;
         } else {
            var6.setJoinStrategy(var1.getJoinStrategy(this.userSpecifiedJoinStrategy));
            if (var6.getJoinStrategy() == null) {
               throw StandardException.newException("42Y56", new Object[]{this.userSpecifiedJoinStrategy, this.getBaseTableName()});
            }

            var5 = true;
         }
      } else if (this.joinStrategyNumber < var4) {
         var6.setJoinStrategy(var1.getJoinStrategy(this.joinStrategyNumber));
         ++this.joinStrategyNumber;
         var5 = true;
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceConsideringJoinStrategy(var6.getJoinStrategy(), this.tableNumber);
         }
      }

      this.tellRowOrderingAboutConstantColumns(var3, var2);
      return var5;
   }

   protected boolean canBeOrdered() {
      return false;
   }

   public AccessPath getCurrentAccessPath() {
      return this.currentAccessPath;
   }

   public AccessPath getBestAccessPath() {
      return this.bestAccessPath;
   }

   public AccessPath getBestSortAvoidancePath() {
      return this.bestSortAvoidancePath;
   }

   public AccessPath getTrulyTheBestAccessPath() {
      return this.trulyTheBestAccessPath;
   }

   public void rememberSortAvoidancePath() {
      this.considerSortAvoidancePath = true;
   }

   public boolean considerSortAvoidancePath() {
      return this.considerSortAvoidancePath;
   }

   public void rememberJoinStrategyAsBest(AccessPath var1) {
      Optimizer var2 = var1.getOptimizer();
      var1.setJoinStrategy(this.getCurrentAccessPath().getJoinStrategy());
      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceRememberingJoinStrategy(this.getCurrentAccessPath().getJoinStrategy(), this.tableNumber);
      }

      if (var1 == this.bestAccessPath) {
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceRememberingBestAccessPathSubstring(var1, this.tableNumber);
         }
      } else if (var1 == this.bestSortAvoidancePath) {
         if (this.optimizerTracingIsOn()) {
            this.getOptimizerTracer().traceRememberingBestSortAvoidanceAccessPathSubstring(var1, this.tableNumber);
         }
      } else if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceRememberingBestUnknownAccessPathSubstring(var1, this.tableNumber);
      }

   }

   public TableDescriptor getTableDescriptor() {
      return null;
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      return false;
   }

   public void pullOptPredicates(OptimizablePredicateList var1) throws StandardException {
   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      return this;
   }

   public boolean isCoveringIndex(ConglomerateDescriptor var1) throws StandardException {
      return false;
   }

   public Properties getProperties() {
      return this.tableProperties;
   }

   public void setProperties(Properties var1) {
      this.tableProperties = var1;
   }

   public void verifyProperties(DataDictionary var1) throws StandardException {
      if (this.tableProperties != null) {
         Enumeration var2 = this.tableProperties.keys();

         while(var2.hasMoreElements()) {
            String var3 = (String)var2.nextElement();
            String var4 = (String)this.tableProperties.get(var3);
            if (!var3.equals("joinStrategy")) {
               if (!var3.equals("hashInitialCapacity")) {
                  if (var3.equals("hashLoadFactor")) {
                     try {
                        this.loadFactor = Float.parseFloat(var4);
                     } catch (NumberFormatException var6) {
                        throw StandardException.newException("42Y58", new Object[]{var4, var3});
                     }

                     if ((double)this.loadFactor <= (double)0.0F || (double)this.loadFactor > (double)1.0F) {
                        throw StandardException.newException("42Y60", new Object[]{var4});
                     }
                  } else {
                     if (!var3.equals("hashMaxCapacity")) {
                        throw StandardException.newException("42Y44", new Object[]{var3, "joinStrategy"});
                     }

                     this.maxCapacity = this.getIntProperty(var4, var3);
                     if (this.maxCapacity <= 0) {
                        throw StandardException.newException("42Y61", new Object[]{String.valueOf(this.maxCapacity)});
                     }
                  }
               } else {
                  this.initialCapacity = this.getIntProperty(var4, var3);
                  if (this.initialCapacity <= 0) {
                     throw StandardException.newException("42Y59", new Object[]{String.valueOf(this.initialCapacity)});
                  }
               }
            } else {
               this.userSpecifiedJoinStrategy = StringUtil.SQLToUpperCase(var4);
            }
         }

      }
   }

   public String getName() throws StandardException {
      return this.getExposedName();
   }

   public String getBaseTableName() {
      return "";
   }

   public int convertAbsoluteToRelativeColumnPosition(int var1) {
      return var1;
   }

   public void updateBestPlanMap(short var1, Object var2) throws StandardException {
      if (var1 == 0) {
         if (this.bestPlanMap != null) {
            this.bestPlanMap.remove(var2);
            if (this.bestPlanMap.isEmpty()) {
               this.bestPlanMap = null;
            }
         }

      } else {
         AccessPath var3 = this.getTrulyTheBestAccessPath();
         AccessPathImpl var4 = null;
         if (var1 == 1) {
            if (var3 != null) {
               if (this.bestPlanMap == null) {
                  this.bestPlanMap = new HashMap();
               } else {
                  var4 = (AccessPathImpl)this.bestPlanMap.get(var2);
               }

               if (var4 == null) {
                  if (var2 instanceof Optimizer) {
                     var4 = new AccessPathImpl((Optimizer)var2);
                  } else {
                     var4 = new AccessPathImpl((Optimizer)null);
                  }
               }

               var4.copy(var3);
               this.bestPlanMap.put(var2, var4);
            }
         } else if (this.bestPlanMap != null) {
            var4 = (AccessPathImpl)this.bestPlanMap.get(var2);
            if (var4 != null && var4.getCostEstimate() != null) {
               var3.copy(var4);
            }
         }
      }
   }

   public void rememberAsBest(int var1, Optimizer var2) throws StandardException {
      AccessPath var3 = null;
      switch (var1) {
         case 1 -> var3 = this.getBestAccessPath();
         case 2 -> var3 = this.getBestSortAvoidancePath();
      }

      this.getTrulyTheBestAccessPath().copy(var3);
      if (!(this instanceof ProjectRestrictNode var4)) {
         this.updateBestPlanMap((short)1, var2);
      } else if (!(var4.getChildResult() instanceof Optimizable)) {
         this.updateBestPlanMap((short)1, var2);
      }

      if (this.isBaseTable()) {
         DataDictionary var6 = this.getDataDictionary();
         TableDescriptor var5 = this.getTableDescriptor();
         this.getTrulyTheBestAccessPath().initializeAccessPathName(var6, var5);
      }

      this.setCostEstimateCost(var3.getCostEstimate());
      if (this.optimizerTracingIsOn()) {
         this.getOptimizerTracer().traceRememberingBestAccessPath(var3, this.tableNumber, var1);
      }

   }

   public void startOptimizing(Optimizer var1, RowOrdering var2) {
      this.resetJoinStrategies(var1);
      this.considerSortAvoidancePath = false;
      CostEstimate var3 = this.getBestAccessPath().getCostEstimate();
      if (var3 != null) {
         var3.setCost(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
      }

      var3 = this.getBestSortAvoidancePath().getCostEstimate();
      if (var3 != null) {
         var3.setCost(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
      }

      if (!this.canBeOrdered()) {
         var2.addUnorderedOptimizable(this);
      }

   }

   protected void resetJoinStrategies(Optimizer var1) {
      this.joinStrategyNumber = 0;
      this.getCurrentAccessPath().setJoinStrategy((JoinStrategy)null);
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      return null;
   }

   CostEstimate getFinalCostEstimate() throws StandardException {
      if (this.getCandidateFinalCostEstimate() != null) {
         return this.getCandidateFinalCostEstimate();
      } else {
         if (this.getTrulyTheBestAccessPath() == null) {
            this.setCandidateFinalCostEstimate(this.getCostEstimate());
         } else {
            this.setCandidateFinalCostEstimate(this.getTrulyTheBestAccessPath().getCostEstimate());
         }

         return this.getCandidateFinalCostEstimate();
      }
   }

   public boolean isBaseTable() {
      return false;
   }

   public boolean hasLargeObjectColumns() {
      for(ResultColumn var2 : this.getResultColumns()) {
         if (var2.isReferenced()) {
            DataTypeDescriptor var3 = var2.getType();
            if (var3 != null && var3.getTypeId().isLOBTypeId()) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isMaterializable() throws StandardException {
      HasCorrelatedCRsVisitor var1 = new HasCorrelatedCRsVisitor();
      this.accept(var1);
      return !var1.hasCorrelatedCRs();
   }

   public boolean supportsMultipleInstantiations() {
      return true;
   }

   public int getTableNumber() {
      return this.tableNumber;
   }

   public boolean hasTableNumber() {
      return this.tableNumber >= 0;
   }

   public boolean forUpdate() {
      return false;
   }

   public int initialCapacity() {
      return 0;
   }

   public float loadFactor() {
      return 0.0F;
   }

   public int maxCapacity(JoinStrategy var1, int var2) throws StandardException {
      return var1.maxCapacity(this.maxCapacity, var2, this.getPerRowUsage());
   }

   private double getPerRowUsage() throws StandardException {
      if (this.perRowUsage < (double)0.0F) {
         FormatableBitSet var1 = this.getResultColumns().getReferencedFormatableBitSet(this.cursorTargetTable(), true, false);
         this.perRowUsage = (double)0.0F;

         for(int var2 = 0; var2 < var1.size(); ++var2) {
            if (var1.isSet(var2)) {
               ResultColumn var3 = (ResultColumn)this.getResultColumns().elementAt(var2);
               DataTypeDescriptor var4 = var3.getExpression().getTypeServices();
               if (var4 != null) {
                  this.perRowUsage += var4.estimatedMemoryUsage();
               }
            }
         }

         ConglomerateDescriptor var5 = this.getCurrentAccessPath().getConglomerateDescriptor();
         if (var5 != null && var5.isIndex() && !this.isCoveringIndex(var5)) {
            this.perRowUsage += (double)12.0F;
         }
      }

      return this.perRowUsage;
   }

   public int[] hashKeyColumns() {
      return this.hashKeyColumns;
   }

   public void setHashKeyColumns(int[] var1) {
      this.hashKeyColumns = var1;
   }

   public boolean feasibleJoinStrategy(OptimizablePredicateList var1, Optimizer var2) throws StandardException {
      return this.getCurrentAccessPath().getJoinStrategy().feasible(this, var1, var2);
   }

   public boolean memoryUsageOK(double var1, int var3) throws StandardException {
      if (this.userSpecifiedJoinStrategy != null) {
         return true;
      } else {
         int var4 = var1 > (double)Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)var1;
         return var4 <= this.maxCapacity(this.getCurrentAccessPath().getJoinStrategy(), var3);
      }
   }

   void isJoinColumnForRightOuterJoin(ResultColumn var1) {
   }

   public boolean legalJoinOrder(JBitSet var1) {
      return true;
   }

   public int getNumColumnsReturned() {
      return this.getResultColumns().size();
   }

   public boolean isTargetTable() {
      return false;
   }

   public boolean isOneRowScan() throws StandardException {
      return this.isOneRowResultSet();
   }

   public void initAccessPaths(Optimizer var1) {
      if (this.currentAccessPath == null) {
         this.currentAccessPath = new AccessPathImpl(var1);
      }

      if (this.bestAccessPath == null) {
         this.bestAccessPath = new AccessPathImpl(var1);
      }

      if (this.bestSortAvoidancePath == null) {
         this.bestSortAvoidancePath = new AccessPathImpl(var1);
      }

      if (this.trulyTheBestAccessPath == null) {
         this.trulyTheBestAccessPath = new AccessPathImpl(var1);
      }

   }

   public double uniqueJoin(OptimizablePredicateList var1) throws StandardException {
      return (double)-1.0F;
   }

   String getUserSpecifiedJoinStrategy() {
      return this.tableProperties == null ? null : this.tableProperties.getProperty("joinStrategy");
   }

   protected boolean cursorTargetTable() {
      return false;
   }

   protected CostEstimate getCostEstimate(Optimizer var1) {
      if (this.getCostEstimate() == null) {
         this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      }

      return this.getCostEstimate();
   }

   protected CostEstimate getScratchCostEstimate(Optimizer var1) {
      if (this.getScratchCostEstimate() == null) {
         this.setScratchCostEstimate(this.getOptimizerFactory().getCostEstimate());
      }

      return this.getScratchCostEstimate();
   }

   protected void setCostEstimateCost(CostEstimate var1) {
      this.getCostEstimate().setCost(var1);
   }

   protected void assignCostEstimate(CostEstimate var1) {
      this.setCostEstimate(var1);
   }

   public String toString() {
      return "";
   }

   ResultColumnList getResultColumnsForList(TableName var1, ResultColumnList var2, TableName var3) throws StandardException {
      TableName var5;
      if (this.correlationName == null) {
         var5 = var3;
      } else if (var1 != null) {
         var5 = this.makeTableName(var1.getSchemaName(), this.correlationName);
      } else {
         var5 = this.makeTableName((String)null, this.correlationName);
      }

      if (var1 != null && !var1.equals(var5)) {
         return null;
      } else {
         TableName var4;
         if (this.correlationName == null) {
            var4 = var3;
         } else {
            var4 = this.makeTableName((String)null, this.correlationName);
         }

         ContextManager var6 = this.getContextManager();
         ResultColumnList var7 = new ResultColumnList(var6);

         for(ResultColumn var9 : var2) {
            ColumnReference var10 = var9.getReference();
            if (var10 != null && var10.getMergeTableID() != 0) {
               var4 = var10.getQualifiedTableName();
            }

            ColumnReference var11 = new ColumnReference(var9.getName(), var4, var6);
            if (var10 != null && var10.getMergeTableID() != 0) {
               var11.setMergeTableID(var10.getMergeTableID());
            }

            ResultColumn var12 = new ResultColumn(var9.getName(), var11, var6);
            var7.addResultColumn(var12);
         }

         return var7;
      }
   }

   void pushExpressions(PredicateList var1) throws StandardException {
   }

   String getExposedName() throws StandardException {
      return null;
   }

   void setTableNumber(int var1) {
      this.tableNumber = var1;
   }

   TableName getTableName() throws StandardException {
      if (this.correlationName == null) {
         return null;
      } else {
         if (this.corrTableName == null) {
            this.corrTableName = this.makeTableName((String)null, this.correlationName);
         }

         return this.corrTableName;
      }
   }

   void setLevel(int var1) {
      this.level = var1;
   }

   int getLevel() {
      return this.level;
   }

   void decrementLevel(int var1) {
      if (this.level > 0) {
         this.level -= var1;
      }

   }

   SchemaDescriptor getSchemaDescriptor() throws StandardException {
      return this.getSchemaDescriptor(this.corrTableName);
   }

   SchemaDescriptor getSchemaDescriptor(TableName var1) throws StandardException {
      SchemaDescriptor var2 = this.getSchemaDescriptor(var1.getSchemaName());
      return var2;
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      if (var2 != null) {
         return null;
      } else {
         return this.getExposedName().equals(var1) ? this : null;
      }
   }

   boolean isFlattenableJoinNode() {
      return false;
   }

   boolean LOJ_reorderable(int var1) throws StandardException {
      return false;
   }

   FromTable transformOuterJoins(ValueNode var1, int var2) throws StandardException {
      return this;
   }

   void fillInReferencedTableMap(JBitSet var1) {
      if (this.tableNumber != -1) {
         var1.set(this.tableNumber);
      }

   }

   protected void markUpdatableByCursor(List var1) {
      this.getResultColumns().markUpdatableByCursor(var1);
   }

   boolean columnsAreUpdatable() {
      return this.getResultColumns().columnsAreUpdatable();
   }

   FromList flatten(ResultColumnList var1, PredicateList var2, SubqueryList var3, GroupByList var4, ValueNode var5) throws StandardException {
      return null;
   }

   void optimizeSubqueries(DataDictionary var1, double var2) throws StandardException {
   }

   protected void tellRowOrderingAboutConstantColumns(RowOrdering var1, OptimizablePredicateList var2) {
      if (var2 != null) {
         for(int var3 = 0; var3 < var2.size(); ++var3) {
            Predicate var4 = (Predicate)var2.getOptPredicate(var3);
            if (var4.equalsComparisonWithConstantExpression(this)) {
               ColumnReference var5 = var4.getRelop().getColumnOperand(this);
               if (var5 != null) {
                  var1.columnAlwaysOrdered(this, var5.getColumnNumber());
               }
            }
         }
      }

   }

   boolean needsSpecialRCLBinding() {
      return false;
   }

   void setOrigTableName(TableName var1) {
      this.origTableName = var1;
   }

   TableName getOrigTableName() {
      return this.origTableName;
   }

   void setMergeTableID(int var1) {
      this._mergeTableID = var1;
   }

   int getMergeTableID() {
      return this._mergeTableID;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.origTableName != null) {
         this.origTableName = (TableName)this.origTableName.accept(var1);
      }

      if (this.corrTableName != null) {
         this.corrTableName = (TableName)this.corrTableName.accept(var1);
      }

   }
}
