package org.datanucleus.api.jdo.exceptions;

import javax.jdo.JDOUserException;
import org.datanucleus.util.Localiser;

public class TransactionNotActiveException extends JDOUserException {
   private static final long serialVersionUID = -5645432840786957250L;

   public TransactionNotActiveException() {
      super(Localiser.msg("015035"));
   }

   public TransactionNotActiveException(String message, Object failedObject) {
      super(message, failedObject);
   }
}
