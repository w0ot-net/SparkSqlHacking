package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionIsolationNotSupportedException extends NucleusUserException {
   private static final long serialVersionUID = 6916529341358743847L;

   public TransactionIsolationNotSupportedException(String level) {
      super(Localiser.msg("015043", level));
   }
}
