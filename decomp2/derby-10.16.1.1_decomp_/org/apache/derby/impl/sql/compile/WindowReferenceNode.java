package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public final class WindowReferenceNode extends WindowNode {
   WindowReferenceNode(String var1, ContextManager var2) throws StandardException {
      super(var1, var2);
   }

   public String toString() {
      String var10000 = this.getName();
      return "referenced window: " + var10000 + "\n" + super.toString();
   }
}
