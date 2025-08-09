package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionNotWritableException extends TransactionNotActiveException {
   private static final long serialVersionUID = 7749963017601389361L;

   public TransactionNotWritableException() {
      super(Localiser.msg("015041"), (Object)null);
   }

   public TransactionNotWritableException(String message, Object failedObject) {
      super(message, failedObject);
   }
}
