package org.apache.derby.impl.services.jmxnone;

import org.apache.derby.iapi.services.jmx.ManagementService;

public final class NoManagementService implements ManagementService {
   public Object registerMBean(Object var1, Class var2, String var3) {
      return null;
   }

   public void unregisterMBean(Object var1) {
   }

   public boolean isManagementActive() {
      return false;
   }

   public void startManagement() {
   }

   public void stopManagement() {
   }

   public String getSystemIdentifier() {
      return null;
   }

   public String quotePropertyValue(String var1) {
      return null;
   }
}
