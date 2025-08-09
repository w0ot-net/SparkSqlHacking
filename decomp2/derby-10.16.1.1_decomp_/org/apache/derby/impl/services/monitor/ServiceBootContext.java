package org.apache.derby.impl.services.monitor;

import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;

final class ServiceBootContext extends ContextImpl {
   ServiceBootContext(ContextManager var1) {
      super(var1, "ServiceBoot");
   }

   public void cleanupOnError(Throwable var1) {
      this.popMe();
   }

   public boolean isLastHandler(int var1) {
      return var1 == 0 || var1 == 45000 || var1 == 50000;
   }
}
