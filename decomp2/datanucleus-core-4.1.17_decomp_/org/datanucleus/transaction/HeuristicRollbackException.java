package org.datanucleus.transaction;

public class HeuristicRollbackException extends NucleusTransactionException {
   private static final long serialVersionUID = 7937127770266014714L;

   public HeuristicRollbackException() {
   }

   public HeuristicRollbackException(String message, Throwable exception) {
      super(message, exception);
   }

   public HeuristicRollbackException(String message, Throwable[] exceptions) {
      super(message, exceptions);
   }

   public HeuristicRollbackException(String message) {
      super(message);
   }
}
