package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

class ScrollInsensitiveResultSetNode extends SingleChildResultSetNode {
   ScrollInsensitiveResultSetNode(ResultSetNode var1, ResultColumnList var2, Properties var3, ContextManager var4) {
      super(var1, var3, var4);
      this.setResultColumns(var2);
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      var1.addItem(this.makeResultDescription());
      var1.pushGetResultSetFactoryExpression(var2);
      this.childResult.generate(var1, var2);
      var1.pushThisAsActivation(var2);
      var2.push(this.getResultSetNumber());
      var2.push(this.getResultColumns().size());
      var2.pushThis();
      var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getScrollable", "boolean", 0);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getScrollInsensitiveResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 7);
   }
}
