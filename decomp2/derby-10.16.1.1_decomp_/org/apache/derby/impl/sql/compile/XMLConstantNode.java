package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class XMLConstantNode extends ConstantNode {
   XMLConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var1, true, 0, var2);
   }

   Object getConstantValueAsObject() throws StandardException {
      return this.value.getObject();
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var2.push(this.value.getString());
   }
}
