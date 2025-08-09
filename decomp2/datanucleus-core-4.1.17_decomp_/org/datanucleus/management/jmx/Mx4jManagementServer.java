package org.datanucleus.management.jmx;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import mx4j.tools.naming.NamingService;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.NucleusLogger;

public class Mx4jManagementServer implements ManagementServer {
   MBeanServer server;
   JMXConnectorServer jmxServer;
   NamingService naming;

   public void start() {
      if (NucleusLogger.GENERAL.isDebugEnabled()) {
         NucleusLogger.GENERAL.debug("Starting ManagementServer");
      }

      int port = 1199;

      try {
         this.naming = new NamingService(port);
         this.naming.start();
         this.server = MBeanServerFactory.createMBeanServer();
         String hostName = InetAddress.getLocalHost().getHostName();
         Map<String, String> env = new HashMap();
         env.put("java.naming.factory.initial", "com.sun.jndi.rmi.registry.RegistryContextFactory");
         env.put("java.naming.provider.url", "rmi://" + hostName + ":" + port);
         JMXServiceURL address = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + hostName + ":" + port + "/datanucleus");
         this.jmxServer = JMXConnectorServerFactory.newJMXConnectorServer(address, env, this.server);
         this.jmxServer.start();
         if (NucleusLogger.GENERAL.isDebugEnabled()) {
            NucleusLogger.GENERAL.debug("MBeanServer listening at " + this.jmxServer.getAddress().toString());
         }

      } catch (Exception e) {
         throw new NucleusException(e.getMessage(), e);
      }
   }

   public void stop() {
      if (NucleusLogger.GENERAL.isDebugEnabled()) {
         NucleusLogger.GENERAL.debug("Stopping ManagementServer");
      }

      if (this.jmxServer != null) {
         try {
            this.jmxServer.stop();
         } catch (IOException e) {
            NucleusLogger.GENERAL.error(e);
         }
      }

      if (this.naming != null) {
         try {
            this.naming.stop();
         } catch (NoSuchObjectException e) {
            NucleusLogger.GENERAL.error(e);
         }
      }

      this.jmxServer = null;
      this.naming = null;
      this.server = null;
   }

   public void registerMBean(Object mbean, String name) {
      try {
         ObjectName objName = new ObjectName(name);
         this.server.registerMBean(mbean, objName);
      } catch (Exception e) {
         throw new NucleusException(e.getMessage(), e);
      }
   }

   public void unregisterMBean(String name) {
      try {
         ObjectName objName = new ObjectName(name);
         this.server.unregisterMBean(objName);
      } catch (Exception e) {
         throw new NucleusException(e.getMessage(), e);
      }
   }

   public Object getMBeanServer() {
      return this.server;
   }
}
