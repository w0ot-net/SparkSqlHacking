package org.datanucleus.api.jdo.exceptions;

import javax.jdo.JDOUserException;
import org.datanucleus.util.Localiser;

public class TransactionCommitingException extends JDOUserException {
   private static final long serialVersionUID = -1805767358303642401L;

   public TransactionCommitingException(Object failedObject) {
      super(Localiser.msg("015036"), failedObject);
   }
}
