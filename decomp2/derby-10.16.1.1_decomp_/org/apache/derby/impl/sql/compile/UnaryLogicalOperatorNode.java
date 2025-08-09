package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public abstract class UnaryLogicalOperatorNode extends UnaryOperatorNode {
   UnaryLogicalOperatorNode(ValueNode var1, String var2, ContextManager var3) throws StandardException {
      super(var1, var2, var2, var3);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      if (!this.operand.getTypeServices().getTypeId().isBooleanTypeId()) {
         throw StandardException.newException("42X40", new Object[0]);
      } else {
         this.setFullTypeInfo();
         return this;
      }
   }

   protected void setFullTypeInfo() throws StandardException {
      boolean var1 = this.operand.getTypeServices().isNullable();
      this.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, var1));
   }
}
