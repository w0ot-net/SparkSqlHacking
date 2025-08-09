package org.apache.zookeeper.server.admin;

import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.server.ZooKeeperServer;

@Public
public interface AdminServer {
   void start() throws AdminServerException;

   void shutdown() throws AdminServerException;

   void setZooKeeperServer(ZooKeeperServer var1);

   @Public
   public static class AdminServerException extends Exception {
      private static final long serialVersionUID = 1L;

      public AdminServerException(String message, Throwable cause) {
         super(message, cause);
      }

      public AdminServerException(Throwable cause) {
         super(cause);
      }
   }
}
