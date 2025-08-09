package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionNotActiveException extends NucleusUserException {
   private static final long serialVersionUID = -3462236079972766332L;

   public TransactionNotActiveException() {
      super(Localiser.msg("015035"));
   }

   public TransactionNotActiveException(String message, Object failedObject) {
      super(message, failedObject);
   }
}
