package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public final class RowCountNode extends SingleChildResultSetNode {
   private ValueNode offset;
   private ValueNode fetchFirst;
   private boolean hasJDBClimitClause;

   RowCountNode(ResultSetNode var1, ResultColumnList var2, ValueNode var3, ValueNode var4, boolean var5, ContextManager var6) throws StandardException {
      super(var1, (Properties)null, var6);
      this.setResultColumns(var2);
      this.offset = var3;
      this.fetchFirst = var4;
      this.hasJDBClimitClause = var5;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      var1.pushGetResultSetFactoryExpression(var2);
      this.childResult.generate(var1, var2);
      var1.pushThisAsActivation(var2);
      var2.push(this.getResultSetNumber());
      boolean var3 = false;
      boolean var4 = false;
      if (this.offset != null) {
         this.generateExprFun(var1, var2, this.offset);
      } else {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      }

      if (this.fetchFirst != null) {
         this.generateExprFun(var1, var2, this.fetchFirst);
      } else {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      }

      var2.push(this.hasJDBClimitClause);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getRowCountResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 8);
   }

   private void generateExprFun(ExpressionClassBuilder var1, MethodBuilder var2, ValueNode var3) throws StandardException {
      MethodBuilder var4 = var1.newExprFun();
      var3.generateExpression(var1, var4);
      var4.methodReturn();
      var4.complete();
      var1.pushMethodReference(var2, var4);
   }

   public String toString() {
      return "";
   }
}
