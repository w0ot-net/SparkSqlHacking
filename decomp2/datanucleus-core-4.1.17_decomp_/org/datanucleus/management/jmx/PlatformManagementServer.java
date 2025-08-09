package org.datanucleus.management.jmx;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.NucleusLogger;

public class PlatformManagementServer implements ManagementServer {
   MBeanServer mbeanServer;

   public void start() {
      if (NucleusLogger.GENERAL.isDebugEnabled()) {
         NucleusLogger.GENERAL.debug("Starting ManagementServer");
      }

      this.mbeanServer = ManagementFactory.getPlatformMBeanServer();
   }

   public void stop() {
      if (NucleusLogger.GENERAL.isDebugEnabled()) {
         NucleusLogger.GENERAL.debug("Stopping ManagementServer");
      }

      this.mbeanServer = null;
   }

   public void registerMBean(Object mbean, String name) {
      try {
         ObjectName objName = new ObjectName(name);
         this.mbeanServer.registerMBean(mbean, objName);
      } catch (Exception e) {
         throw new NucleusException(e.getMessage(), e);
      }
   }

   public void unregisterMBean(String name) {
      try {
         ObjectName objName = new ObjectName(name);
         this.mbeanServer.unregisterMBean(objName);
      } catch (Exception e) {
         throw new NucleusException(e.getMessage(), e);
      }
   }
}
