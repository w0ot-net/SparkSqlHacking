package org.apache.derby.impl.sql.compile;

import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

class DistinctNode extends SingleChildResultSetNode {
   boolean inSortedOrder;

   DistinctNode(ResultSetNode var1, boolean var2, Properties var3, ContextManager var4) throws StandardException {
      super(var1, var3, var4);
      ResultColumnList var5 = this.childResult.getResultColumns().copyListAndObjects();
      this.setResultColumns(this.childResult.getResultColumns());
      this.childResult.setResultColumns(var5);
      this.getResultColumns().genVirtualColumnNodes(this, var5);
      this.getResultColumns().verifyAllOrderable();
      this.inSortedOrder = var2;
   }

   public CostEstimate optimizeIt(Optimizer var1, OptimizablePredicateList var2, CostEstimate var3, RowOrdering var4) throws StandardException {
      ((Optimizable)this.childResult).optimizeIt(var1, var2, var3, var4);
      return super.optimizeIt(var1, var2, var3, var4);
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      CostEstimate var6 = ((Optimizable)this.childResult).estimateCost(var1, var2, var3, var4, var5);
      this.setCostEstimate(this.getCostEstimate(var4));
      this.getCostEstimate().setCost(var6.getEstimatedCost(), var6.rowCount(), var6.singleScanRowCount());
      return this.getCostEstimate();
   }

   public boolean pushOptPredicate(OptimizablePredicate var1) throws StandardException {
      return false;
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.childResult = this.childResult.optimize(var1, var2, var3);
      this.setCostEstimate(this.getOptimizerFactory().getCostEstimate());
      this.getCostEstimate().setCost(this.childResult.getCostEstimate().getEstimatedCost(), this.childResult.getCostEstimate().rowCount(), this.childResult.getCostEstimate().singleScanRowCount());
      return this;
   }

   boolean isOrderedOn(ColumnReference[] var1, boolean var2, List var3) {
      return false;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      int var3 = var1.addItem(var1.getColumnOrdering(this.getResultColumns()));
      var1.pushGetResultSetFactoryExpression(var2);
      this.childResult.generate(var1, var2);
      var2.push(true);
      var2.push(this.inSortedOrder);
      var2.push(var3);
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate()));
      var2.push(this.getResultColumns().getTotalColumnSize());
      var2.push(this.getResultSetNumber());
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getSortResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 9);
   }
}
