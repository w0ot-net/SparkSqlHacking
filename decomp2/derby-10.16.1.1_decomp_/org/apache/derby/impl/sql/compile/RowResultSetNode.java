package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class RowResultSetNode extends FromTable {
   SubqueryList subquerys;
   private List aggregates;
   ResultSetNode.QueryExpressionClauses qec = new ResultSetNode.QueryExpressionClauses();

   RowResultSetNode(ResultColumnList var1, Properties var2, ContextManager var3) {
      super((String)null, var2, var3);
      this.setResultColumns(var1);
      if (this.getResultColumns() != null) {
         this.getResultColumns().markInitialSize();
      }

   }

   String statementToString() {
      return "VALUES";
   }

   void printSubNodes(int var1) {
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.subquerys != null && this.subquerys.referencesSessionSchema();
   }

   ResultSetNode enhanceRCLForInsert(InsertNode var1, boolean var2, int[] var3) throws StandardException {
      if (!var2 || this.getResultColumns().size() < var1.resultColumnList.size()) {
         this.setResultColumns(this.getRCLForInsert(var1, var3));
      }

      return this;
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      if (this.getCostEstimate() == null) {
         this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      }

      this.getCostEstimate().setCost((double)0.0F, (double)1.0F, (double)1.0F);
      var5.optimizableAlwaysOrdered(this);
      return this.getCostEstimate();
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      if (this.tableNumber == -1) {
         this.tableNumber = this.getCompilerContext().getNextTableNumber();
      }

      return this;
   }

   void bindExpressions(FromList var1) throws StandardException {
      this.subquerys = new SubqueryList(this.getContextManager());
      this.aggregates = new ArrayList();
      this.getResultColumns().checkForInvalidDefaults();
      int var2;
      if (var1.size() == 0) {
         var2 = 0;
      } else {
         var2 = ((FromTable)var1.elementAt(0)).getLevel() + 1;
      }

      this.setLevel(var2);
      var1.insertElementAt(this, 0);
      this.getResultColumns().bindExpressions(var1, this.subquerys, this.aggregates);
      var1.removeElementAt(0);
      if (!this.aggregates.isEmpty()) {
         throw StandardException.newException("42903", new Object[0]);
      } else {
         SelectNode.checkNoWindowFunctions(this.getResultColumns(), "VALUES");

         for(int var3 = 0; var3 < this.qec.size(); ++var3) {
            OrderByList var4 = this.qec.getOrderByList(var3);
            if (var4 != null) {
               var4.pullUpOrderByColumns(this);
               var4.bindOrderByColumns(this);
            }

            bindOffsetFetch(this.qec.getOffset(var3), this.qec.getFetchFirst(var3));
         }

      }
   }

   void bindExpressionsWithTables(FromList var1) throws StandardException {
   }

   void bindTargetExpressions(FromList var1) throws StandardException {
      this.bindExpressions(var1);
   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      if (var1 == null) {
         var1 = this.getResultColumns();
      }

      this.getResultColumns().bindUntypedNullsToResultColumns(var1);
   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      return null;
   }

   String getExposedName() throws StandardException {
      return null;
   }

   void verifySelectStarSubquery(FromList var1, int var2) throws StandardException {
   }

   public void pushQueryExpressionSuffix() {
      this.qec.push();
   }

   void pushOrderByList(OrderByList var1) {
      this.qec.setOrderByList(var1);
   }

   void pushOffsetFetchFirst(ValueNode var1, ValueNode var2, boolean var3) {
      this.qec.setOffset(var1);
      this.qec.setFetchFirst(var2);
      this.qec.setHasJDBCLimitClause(var3);
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.getResultColumns().preprocess(var1, var3, this.subquerys, new PredicateList(this.getContextManager()));
      this.setReferencedTableMap(new JBitSet(var1));
      this.getReferencedTableMap().set(this.tableNumber);

      for(int var4 = 0; var4 < this.qec.size(); ++var4) {
         OrderByList var5 = this.qec.getOrderByList(var4);
         if (var5 != null && var5.size() > 1) {
            var5.removeDupColumns();
         }
      }

      return this;
   }

   ResultSetNode ensurePredicateList(int var1) throws StandardException {
      return this.genProjectRestrict(var1);
   }

   ResultSetNode addNewPredicate(Predicate var1) throws StandardException {
      ResultColumnList var3 = this.getResultColumns();
      this.setResultColumns(this.getResultColumns().copyListAndObjects());
      var3.genVirtualColumnNodes(this, this.getResultColumns());
      PredicateList var2 = new PredicateList(this.getContextManager());
      var2.addPredicate(var1);
      return new ProjectRestrictNode(this, var3, (ValueNode)null, var2, (SubqueryList)null, (SubqueryList)null, this.tableProperties, this.getContextManager());
   }

   boolean flattenableInFromSubquery(FromList var1) {
      if (this.subquerys != null && this.subquerys.size() > 0) {
         return false;
      } else if (this.aggregates != null && !this.aggregates.isEmpty()) {
         return false;
      } else if (!this.getResultColumns().isCloneable()) {
         return false;
      } else {
         boolean var2 = false;
         int var3 = var1.size();

         for(int var4 = 0; var4 < var3; ++var4) {
            FromTable var5 = (FromTable)var1.elementAt(var4);
            if (!(var5 instanceof FromSubquery)) {
               var2 = true;
               break;
            }

            ResultSetNode var6 = ((FromSubquery)var5).getSubquery();
            if (!(var6 instanceof RowResultSetNode)) {
               var2 = true;
               break;
            }
         }

         return var2;
      }
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      this.getCostEstimate().setCost((double)0.0F, var3, var3);
      this.subquerys.optimize(var1, var3);
      return this;
   }

   public Optimizable modifyAccessPath(JBitSet var1) throws StandardException {
      return (Optimizable)this.modifyAccessPaths();
   }

   ResultSetNode modifyAccessPaths() throws StandardException {
      Object var1 = this;
      this.subquerys.modifyAccessPaths();

      for(int var2 = 0; var2 < this.qec.size(); ++var2) {
         OrderByList var3 = this.qec.getOrderByList(var2);
         if (var3 != null) {
            var1 = new OrderByNode((ResultSetNode)var1, var3, this.tableProperties, this.getContextManager());
         }

         ValueNode var4 = this.qec.getOffset(var2);
         ValueNode var5 = this.qec.getFetchFirst(var2);
         Boolean var6 = this.qec.getHasJDBCLimitClause()[var2];
         if (var4 != null || var5 != null) {
            ResultColumnList var7 = ((ResultSetNode)var1).getResultColumns().copyListAndObjects();
            var7.genVirtualColumnNodes((ResultSetNode)var1, ((ResultSetNode)var1).getResultColumns());
            var1 = new RowCountNode((ResultSetNode)var1, var7, var4, var5, var6, this.getContextManager());
         }
      }

      return (ResultSetNode)var1;
   }

   boolean returnsAtMostOneRow() {
      return true;
   }

   void setTableConstructorTypes(ResultColumnList var1) throws StandardException {
      int var2 = this.getResultColumns().size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ResultColumn var4 = (ResultColumn)this.getResultColumns().elementAt(var3);
         ValueNode var5 = var4.getExpression();
         if (var5.requiresTypeFromContext()) {
            ResultColumn var6 = (ResultColumn)var1.elementAt(var3);
            var5.setType(var6.getTypeServices());
         } else if (var5 instanceof CharConstantNode) {
            ResultColumn var9 = (ResultColumn)var1.elementAt(var3);
            TypeId var7 = var9.getTypeId();
            if (var7.isStringTypeId()) {
               if (var7.getJDBCTypeId() != 1) {
                  int var8 = var5.getTypeServices().getMaximumWidth();
                  var5.setType(new DataTypeDescriptor(var7, true, var8));
               }
            } else if (var7.isBitTypeId()) {
               if (var7.getJDBCTypeId() == -3) {
                  TypeId var12 = TypeId.getBuiltInTypeId(12);
                  var5.setType(new DataTypeDescriptor(var12, true));
                  var1.setElementAt(var9, var3);
               } else if (var7.getJDBCTypeId() == -4) {
                  TypeId var13 = TypeId.getBuiltInTypeId(-1);
                  var5.setType(new DataTypeDescriptor(var13, true));
                  var1.setElementAt(var9, var3);
               }
            }
         } else if (var5 instanceof BitConstantNode) {
            ResultColumn var10 = (ResultColumn)var1.elementAt(var3);
            TypeId var11 = var10.getTypeId();
            if (var11.isBitTypeId()) {
               if (var11.getJDBCTypeId() != -2 && var11.getJDBCTypeId() != 2004) {
                  int var14 = var5.getTypeServices().getMaximumWidth();
                  var5.setType(new DataTypeDescriptor(var11, true, var14));
               }
            } else if (var11.isStringTypeId()) {
               if (var11.getJDBCTypeId() == 12) {
                  TypeId var15 = TypeId.getBuiltInTypeId(-3);
                  var5.setType(new DataTypeDescriptor(var15, true));
                  var1.setElementAt(var10, var3);
               } else if (var11.getJDBCTypeId() == -1) {
                  TypeId var16 = TypeId.getBuiltInTypeId(-4);
                  var5.setType(new DataTypeDescriptor(var16, true));
                  var1.setElementAt(var10, var3);
               }
            }
         }
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.setCostEstimate(this.getFinalCostEstimate());
      boolean var3 = this.canWeCacheResults();
      this.assignResultSetNumber();
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      this.getResultColumns().generate(var1, var2);
      var2.push(var3);
      var2.push(this.getResultSetNumber());
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getRowResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 6);
   }

   void replaceOrForbidDefaults(TableDescriptor var1, ResultColumnList var2, boolean var3) throws StandardException {
      this.getResultColumns().replaceOrForbidDefaults(var1, var2, var3);
   }

   void optimizeSubqueries(DataDictionary var1, double var2) throws StandardException {
      this.subquerys.optimize(var1, var2);
   }

   void adjustForSortElimination() {
   }

   private boolean canWeCacheResults() throws StandardException {
      HasVariantValueNodeVisitor var1 = new HasVariantValueNodeVisitor(2, true);
      super.accept(var1);
      boolean var2 = !var1.hasVariant();
      return var2;
   }
}
