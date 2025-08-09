package org.apache.zookeeper.cli;

import org.apache.zookeeper.KeeperException;

public class CliWrapperException extends CliException {
   public CliWrapperException(Throwable cause) {
      super(getMessage(cause), cause);
   }

   private static String getMessage(Throwable cause) {
      if (cause instanceof KeeperException) {
         KeeperException keeperException = (KeeperException)cause;
         if (keeperException instanceof KeeperException.NoNodeException) {
            return "Node does not exist: " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.NoChildrenForEphemeralsException) {
            return "Ephemerals cannot have children: " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.NodeExistsException) {
            return "Node already exists: " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.NotEmptyException) {
            return "Node not empty: " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.NotReadOnlyException) {
            return "Not a read-only call: " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.InvalidACLException) {
            return "Acl is not valid : " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.NoAuthException) {
            return "Insufficient permission : " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.BadArgumentsException) {
            return "Arguments are not valid : " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.BadVersionException) {
            return "version No is not valid : " + keeperException.getPath();
         }

         if (keeperException instanceof KeeperException.ReconfigInProgress) {
            return "Another reconfiguration is in progress -- concurrent reconfigs not supported (yet)";
         }

         if (keeperException instanceof KeeperException.NewConfigNoQuorum) {
            return "No quorum of new config is connected and up-to-date with the leader of last commmitted config - try invoking reconfiguration after new servers are connected and synced";
         }

         if (keeperException instanceof KeeperException.QuotaExceededException) {
            return "Quota has exceeded : " + keeperException.getPath();
         }
      }

      return cause.getMessage();
   }
}
