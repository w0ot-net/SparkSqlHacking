package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class RowNumberFunctionNode extends WindowFunctionNode {
   RowNumberFunctionNode(ValueNode var1, WindowNode var2, ContextManager var3) throws StandardException {
      super(var1, "ROW_NUMBER", var2, var3);
      this.setType(TypeId.getBuiltInTypeId(-5), 19, 0, false, 8);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      return this;
   }
}
