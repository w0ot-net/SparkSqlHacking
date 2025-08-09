package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class NormalizeResultSetNode extends SingleChildResultSetNode {
   private boolean forUpdate;

   NormalizeResultSetNode(ResultSetNode var1, ResultColumnList var2, Properties var3, boolean var4, ContextManager var5) throws StandardException {
      super(var1, var3, var5);
      this.forUpdate = var4;
      ResultColumnList var6 = var1.getResultColumns();
      ResultColumnList var7 = var2;
      var1.setResultColumns(var6.copyListAndObjects());
      var6.removeGeneratedGroupingColumns();
      var6.removeOrderByColumns();
      var6.genVirtualColumnNodes(var1, var1.getResultColumns());
      this.setResultColumns(var6);
      if (var1.getReferencedTableMap() != null) {
         this.setReferencedTableMap((JBitSet)this.getReferencedTableMap().clone());
      }

      if (var2 != null) {
         int var9 = Math.min(var2.size(), this.getResultColumns().size());

         for(int var10 = 0; var10 < var9; ++var10) {
            ResultColumn var11 = (ResultColumn)this.getResultColumns().elementAt(var10);
            ResultColumn var12 = (ResultColumn)var7.elementAt(var10);
            var11.setType(var12.getTypeServices());
         }
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      int var3 = var1.addItem(this.makeResultDescription());
      var1.pushGetResultSetFactoryExpression(var2);
      this.childResult.generate(var1, var2);
      var2.push(this.getResultSetNumber());
      var2.push(var3);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.push(this.forUpdate);
      var2.callMethod((short)185, (String)null, "getNormalizeResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 6);
   }

   void setRefActionInfo(long var1, int[] var3, String var4, boolean var5) {
      this.childResult.setRefActionInfo(var1, var3, var4, var5);
   }

   public void pushQueryExpressionSuffix() {
      this.childResult.pushQueryExpressionSuffix();
   }

   void pushOrderByList(OrderByList var1) {
      this.childResult.pushOrderByList(var1);
   }

   void pushOffsetFetchFirst(ValueNode var1, ValueNode var2, boolean var3) {
      this.childResult.pushOffsetFetchFirst(var1, var2, var3);
   }
}
