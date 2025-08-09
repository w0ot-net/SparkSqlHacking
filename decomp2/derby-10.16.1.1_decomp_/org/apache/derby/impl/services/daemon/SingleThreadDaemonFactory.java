package org.apache.derby.impl.services.daemon;

import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.daemon.DaemonFactory;
import org.apache.derby.iapi.services.daemon.DaemonService;

public class SingleThreadDaemonFactory implements DaemonFactory {
   private final ContextService contextService = getContextService();

   public DaemonService createNewDaemon(String var1) {
      BasicDaemon var2 = new BasicDaemon(this.contextService);
      Thread var3 = BasicDaemon.getMonitor().getDaemonThread(var2, var1, false);
      var3.setContextClassLoader((ClassLoader)null);
      var3.start();
      return var2;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }
}
