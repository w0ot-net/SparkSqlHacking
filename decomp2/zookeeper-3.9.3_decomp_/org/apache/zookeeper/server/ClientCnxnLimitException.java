package org.apache.zookeeper.server;

public class ClientCnxnLimitException extends Exception {
   private static final long serialVersionUID = -8655587505476768446L;

   public ClientCnxnLimitException() {
      super("Connection throttle rejected connection");
   }
}
