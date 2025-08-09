package org.datanucleus.management.jmx;

public interface ManagementServer {
   void start();

   void stop();

   void registerMBean(Object var1, String var2);

   void unregisterMBean(String var1);
}
