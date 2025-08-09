package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

final class AggregateWindowFunctionNode extends WindowFunctionNode {
   private AggregateNode aggregateFunction;

   AggregateWindowFunctionNode(WindowNode var1, AggregateNode var2, ContextManager var3) throws StandardException {
      super((ValueNode)null, "?", var1, var3);
      this.aggregateFunction = var2;
      throw StandardException.newException("0A000.S", new Object[]{"WINDOW/" + this.aggregateFunction.getAggregateName()});
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.aggregateFunction.bindExpression(var1, var2, var3);
      return this;
   }

   public void printSubNodes(int var1) {
   }
}
