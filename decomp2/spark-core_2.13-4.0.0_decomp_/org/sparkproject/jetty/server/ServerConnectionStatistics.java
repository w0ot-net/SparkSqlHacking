package org.sparkproject.jetty.server;

import org.sparkproject.jetty.io.ConnectionStatistics;

/** @deprecated */
@Deprecated
public class ServerConnectionStatistics extends ConnectionStatistics {
   /** @deprecated */
   public static void addToAllConnectors(Server server) {
      for(Connector connector : server.getConnectors()) {
         connector.addBean(new ConnectionStatistics());
      }

   }
}
