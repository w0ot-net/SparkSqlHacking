package org.datanucleus.api.jdo.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionNotWritableException extends TransactionNotActiveException {
   private static final long serialVersionUID = -8641406435403033411L;

   public TransactionNotWritableException() {
      super(Localiser.msg("015041"), (Object)null);
   }

   public TransactionNotWritableException(String message, Object failedObject) {
      super(message, failedObject);
   }
}
