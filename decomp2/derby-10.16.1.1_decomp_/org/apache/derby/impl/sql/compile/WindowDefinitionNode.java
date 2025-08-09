package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

public final class WindowDefinitionNode extends WindowNode {
   private boolean inlined;
   private OrderByList orderByList;

   WindowDefinitionNode(String var1, OrderByList var2, ContextManager var3) throws StandardException {
      super(var1 != null ? var1 : "IN-LINE", var3);
      this.orderByList = var2;
      if (var1 != null) {
         this.inlined = false;
      } else {
         this.inlined = true;
      }

      if (var2 != null) {
         throw StandardException.newException("0A000.S", new Object[]{"WINDOW/ORDER BY"});
      }
   }

   public String toString() {
      String var10000 = this.getName();
      return "name: " + var10000 + "\ninlined: " + this.inlined + "\n()\n";
   }

   public void printSubNodes(int var1) {
   }

   WindowDefinitionNode findEquivalentWindow(WindowList var1) {
      for(WindowDefinitionNode var3 : var1) {
         if (this.isEquivalent(var3)) {
            return var3;
         }
      }

      return null;
   }

   private boolean isEquivalent(WindowDefinitionNode var1) {
      return this.orderByList == null && var1.getOrderByList() == null;
   }

   OrderByList getOrderByList() {
      return this.orderByList;
   }
}
