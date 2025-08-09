package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;

class WindowList extends QueryTreeNodeVector {
   WindowList(ContextManager var1) {
      super(WindowDefinitionNode.class, var1);
   }

   public void addWindow(WindowDefinitionNode var1) {
      this.addElement(var1);
   }
}
