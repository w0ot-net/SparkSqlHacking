package org.apache.derby.mbeans;

import org.apache.derby.iapi.services.monitor.Monitor;

public class Management implements ManagementMBean {
   private ManagementMBean getManagementService() {
      return (ManagementMBean)getSystemModule("org.apache.derby.iapi.services.jmx.ManagementService");
   }

   public void startManagement() {
      ManagementMBean var1 = this.getManagementService();
      if (var1 != null) {
         var1.startManagement();
      }

   }

   public void stopManagement() {
      ManagementMBean var1 = this.getManagementService();
      if (var1 != null) {
         var1.stopManagement();
      }

   }

   public boolean isManagementActive() {
      ManagementMBean var1 = this.getManagementService();
      return var1 == null ? false : var1.isManagementActive();
   }

   public String getSystemIdentifier() {
      ManagementMBean var1 = this.getManagementService();
      return var1 == null ? null : var1.getSystemIdentifier();
   }

   private static Object getSystemModule(String var0) {
      return Monitor.getSystemModule(var0);
   }
}
