package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.execute.ExecutionContext;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.shared.common.error.StandardException;

class GenericExecutionContext extends ContextImpl implements ExecutionContext {
   private ExecutionFactory execFactory;

   public ExecutionFactory getExecutionFactory() {
      return this.execFactory;
   }

   public void cleanupOnError(Throwable var1) throws StandardException {
      if (var1 instanceof StandardException var2) {
         int var3 = var2.getSeverity();
         if (var3 >= 40000) {
            this.popMe();
         } else if (var3 <= 20000) {
            ;
         }
      }
   }

   GenericExecutionContext(ContextManager var1, ExecutionFactory var2) {
      super(var1, "ExecutionContext");
      this.execFactory = var2;
   }
}
