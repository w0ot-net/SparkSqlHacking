package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public abstract class WindowNode extends QueryTreeNode {
   private String windowName;

   WindowNode(String var1, ContextManager var2) throws StandardException {
      super(var2);
      this.windowName = var1;
   }

   public String getName() {
      return this.windowName;
   }
}
