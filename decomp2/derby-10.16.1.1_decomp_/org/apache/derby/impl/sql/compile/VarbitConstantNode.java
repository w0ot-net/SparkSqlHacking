package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class VarbitConstantNode extends BitConstantNode {
   static final int K_VAR = 0;
   static final int K_LONGVAR = 1;
   static final int K_BLOB = 2;
   final int kind;

   VarbitConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var1, var2);
      switch (var1.getJDBCTypeId()) {
         case -4 -> this.kind = 1;
         case -3 -> this.kind = 0;
         case 2004 -> this.kind = 2;
         default -> this.kind = -1;
      }

   }

   VarbitConstantNode(String var1, int var2, ContextManager var3) throws StandardException {
      super(var1, var2, var3);
      this.kind = 0;
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((VarbitConstantNode)var1).kind == this.kind;
   }
}
