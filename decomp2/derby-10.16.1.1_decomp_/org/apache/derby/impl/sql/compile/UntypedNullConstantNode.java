package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public final class UntypedNullConstantNode extends ConstantNode {
   UntypedNullConstantNode(ContextManager var1) {
      super(var1);
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) {
   }

   DataValueDescriptor convertDefaultNode(DataTypeDescriptor var1) throws StandardException {
      return var1.getNull();
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) {
      return this;
   }
}
