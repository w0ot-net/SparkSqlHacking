package org.apache.hive.jdbc;

public class ZooKeeperHiveClientException extends Exception {
   private static final long serialVersionUID = 0L;

   public ZooKeeperHiveClientException(Throwable cause) {
      super(cause);
   }

   public ZooKeeperHiveClientException(String msg) {
      super(msg);
   }

   public ZooKeeperHiveClientException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
