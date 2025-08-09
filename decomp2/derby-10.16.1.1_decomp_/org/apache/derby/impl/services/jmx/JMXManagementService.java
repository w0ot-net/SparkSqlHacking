package org.apache.derby.impl.services.jmx;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import javax.management.JMException;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import org.apache.derby.iapi.services.jmx.ManagementService;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.mbeans.ManagementMBean;
import org.apache.derby.mbeans.Version;
import org.apache.derby.mbeans.VersionMBean;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.security.SystemPermission;

public final class JMXManagementService implements ManagementService, ModuleControl {
   private MBeanServer mbeanServer;
   private Map registeredMbeans;
   private ObjectName myManagementBean;
   private MBeanServer myManagementServer;
   private String systemIdentifier;
   private static final SystemPermission CONTROL = new SystemPermission("jmx", "control");

   public synchronized void boot(boolean var1, Properties var2) throws StandardException {
      this.registeredMbeans = new HashMap();
      this.systemIdentifier = getMonitor().getUUIDFactory().createUUID().toString();
      this.findServer();
      this.myManagementBean = (ObjectName)this.registerMBean(this, ManagementMBean.class, "type=Management");
      this.myManagementServer = this.mbeanServer;
      this.registerMBean(new Version(getMonitor().getEngineVersion(), "engine"), VersionMBean.class, "type=Version,jar=derby.jar");
   }

   public synchronized void stop() {
      if (this.mbeanServer == null && this.myManagementBean != null) {
         this.mbeanServer = this.myManagementServer;
         this.unregisterMBean(this.myManagementBean);
         this.mbeanServer = null;
      }

      for(ObjectName var2 : new HashSet(this.registeredMbeans.keySet())) {
         this.unregisterMBean(var2);
      }

      this.mbeanServer = null;
      this.registeredMbeans = null;
      this.myManagementServer = null;
      this.systemIdentifier = null;
   }

   private synchronized void findServer() {
      this.mbeanServer = ManagementFactory.getPlatformMBeanServer();
   }

   public synchronized Object registerMBean(Object var1, final Class var2, String var3) throws StandardException {
      try {
         ObjectName var4 = new ObjectName("org.apache.derby:" + var3 + ",system=" + this.systemIdentifier);
         StandardMBean var5 = new StandardMBean(var1, var2) {
            protected String getClassName(MBeanInfo var1) {
               return var2.getName();
            }
         };
         this.registeredMbeans.put(var4, var5);
         if (this.mbeanServer != null) {
            this.jmxRegister(var5, var4);
         }

         return var4;
      } catch (JMException var6) {
         throw StandardException.plainWrapException(var6);
      }
   }

   private void jmxRegister(StandardMBean var1, ObjectName var2) throws JMException {
      if (!this.mbeanServer.isRegistered(var2)) {
         this.mbeanServer.registerMBean(var1, var2);
      }
   }

   public void unregisterMBean(Object var1) {
      if (var1 != null) {
         this.unregisterMBean((ObjectName)var1);
      }
   }

   private synchronized void unregisterMBean(ObjectName var1) {
      if (this.registeredMbeans != null) {
         if (this.registeredMbeans.remove(var1) != null) {
            if (this.mbeanServer != null) {
               this.jmxUnregister(var1);
            }
         }
      }
   }

   private void jmxUnregister(ObjectName var1) {
      if (this.mbeanServer.isRegistered(var1)) {
         try {
            this.mbeanServer.unregisterMBean(var1);
         } catch (Exception var3) {
         }

      }
   }

   public synchronized boolean isManagementActive() {
      return this.mbeanServer != null;
   }

   public synchronized void startManagement() {
      if (this.registeredMbeans != null) {
         this.checkJMXControl();
         if (!this.isManagementActive()) {
            this.findServer();
            if (this.mbeanServer != null) {
               for(ObjectName var2 : this.registeredMbeans.keySet()) {
                  if (!var2.equals(this.myManagementBean) || !this.mbeanServer.isRegistered(this.myManagementBean)) {
                     try {
                        this.jmxRegister((StandardMBean)this.registeredMbeans.get(var2), var2);
                     } catch (JMException var4) {
                     }
                  }
               }

            }
         }
      }
   }

   public synchronized void stopManagement() {
      if (this.registeredMbeans != null) {
         this.checkJMXControl();
         if (this.isManagementActive()) {
            for(ObjectName var2 : this.registeredMbeans.keySet()) {
               if (!var2.equals(this.myManagementBean)) {
                  this.jmxUnregister(var2);
               }
            }

            this.mbeanServer = null;
         }

      }
   }

   private void checkJMXControl() {
   }

   public synchronized String getSystemIdentifier() {
      return this.systemIdentifier;
   }

   public String quotePropertyValue(String var1) {
      return ObjectName.quote(var1);
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
