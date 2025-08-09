package org.apache.zookeeper.server;

public class ServerCnxnHelper {
   public static int getMaxCnxns(ServerCnxnFactory secureServerCnxnFactory, ServerCnxnFactory serverCnxnFactory) {
      if (serverCnxnFactory != null) {
         return serverCnxnFactory.getMaxCnxns();
      } else {
         return secureServerCnxnFactory != null ? secureServerCnxnFactory.getMaxCnxns() : 0;
      }
   }
}
