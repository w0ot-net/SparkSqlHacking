package org.apache.curator.framework.recipes.leader;

public class CancelLeadershipException extends RuntimeException {
   public CancelLeadershipException() {
   }

   public CancelLeadershipException(String message) {
      super(message);
   }

   public CancelLeadershipException(String message, Throwable cause) {
      super(message, cause);
   }

   public CancelLeadershipException(Throwable cause) {
      super(cause);
   }
}
