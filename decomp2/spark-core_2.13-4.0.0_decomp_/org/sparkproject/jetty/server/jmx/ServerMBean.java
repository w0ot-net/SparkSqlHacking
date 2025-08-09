package org.sparkproject.jetty.server.jmx;

import org.sparkproject.jetty.jmx.ObjectMBean;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject("MBean Wrapper for Server")
public class ServerMBean extends ObjectMBean {
   private final long startupTime = System.currentTimeMillis();
   private final Server server;

   public ServerMBean(Object managedObject) {
      super(managedObject);
      this.server = (Server)managedObject;
   }

   @ManagedAttribute("contexts on this server")
   public Handler[] getContexts() {
      return this.server.getChildHandlersByClass(ContextHandler.class);
   }

   @ManagedAttribute("the startup time since January 1st, 1970 (in ms)")
   public long getStartupTime() {
      return this.startupTime;
   }
}
