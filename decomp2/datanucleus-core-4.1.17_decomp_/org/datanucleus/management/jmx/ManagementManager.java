package org.datanucleus.management.jmx;

import org.datanucleus.NucleusContext;
import org.datanucleus.NucleusContextHelper;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ManagementManager {
   private final NucleusContext nucleusContext;
   private boolean closed = false;
   private ManagementServer mgmtServer;
   private String domainName;
   private String instanceName;

   public ManagementManager(NucleusContext ctxt) {
      this.nucleusContext = ctxt;
      this.domainName = ctxt.getConfiguration().getStringProperty("datanucleus.PersistenceUnitName");
      if (this.domainName == null) {
         this.domainName = "datanucleus";
      }

      this.instanceName = "datanucleus-" + NucleusContextHelper.random.nextInt();
      this.startManagementServer();
   }

   public String getInstanceName() {
      return this.instanceName;
   }

   public String getDomainName() {
      return this.domainName;
   }

   public void registerMBean(Object mbean, String name) {
      this.mgmtServer.registerMBean(mbean, name);
   }

   public void deregisterMBean(String name) {
      this.mgmtServer.unregisterMBean(name);
   }

   public boolean isOpen() {
      return !this.closed;
   }

   public synchronized void close() {
      this.assertNotClosed();
      this.stopManagementServer();
      this.closed = true;
   }

   private void assertNotClosed() {
      if (this.closed) {
         throw (new NucleusException("Management instance is closed and cannot be used. You must adquire a new context")).setFatal();
      }
   }

   private void startManagementServer() {
      if (this.mgmtServer == null) {
         String jmxType = this.nucleusContext.getConfiguration().getStringProperty("datanucleus.jmxType");

         try {
            this.mgmtServer = (ManagementServer)this.nucleusContext.getPluginManager().createExecutableExtension("org.datanucleus.management_server", (String)"name", (String)jmxType, "class", (Class[])null, (Object[])null);
            if (this.mgmtServer != null) {
               NucleusLogger.GENERAL.info("Starting Management Server");
               this.mgmtServer.start();
            }
         } catch (Exception e) {
            this.mgmtServer = null;
            NucleusLogger.GENERAL.error("Error instantiating or connecting to Management Server : " + StringUtils.getStringFromStackTrace(e));
         }
      }

   }

   private void stopManagementServer() {
      if (this.mgmtServer != null) {
         NucleusLogger.GENERAL.info("Stopping Management Server");
         this.mgmtServer.stop();
      }

   }
}
