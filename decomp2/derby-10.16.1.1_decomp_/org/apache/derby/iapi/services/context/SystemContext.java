package org.apache.derby.iapi.services.context;

import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.error.StandardException;

final class SystemContext extends ContextImpl {
   SystemContext(ContextManager var1) {
      super(var1, "SystemContext");
   }

   public void cleanupOnError(Throwable var1) {
      boolean var2 = false;
      if (var1 instanceof StandardException var3) {
         int var4 = var3.getSeverity();
         if (var4 < 40000) {
            return;
         }

         this.popMe();
         if (var4 >= 50000) {
            var2 = true;
         }
      } else if (!(var1 instanceof ShutdownException) && var1 instanceof ThreadDeath) {
      }

      if (!var2) {
         this.getContextManager().owningCsf.removeContext(this.getContextManager());
      } else {
         try {
            System.err.println("Shutting down due to severe error.");
            Monitor.getStream().printlnWithHeader("Shutting down due to severe error." + var1.getMessage());
         } finally {
            getMonitor().shutdown();
         }

      }
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
