package org.datanucleus.api.jdo.exceptions;

import javax.jdo.JDOUserException;
import org.datanucleus.util.Localiser;

public class TransactionActiveException extends JDOUserException {
   private static final long serialVersionUID = 6273891154508609229L;

   public TransactionActiveException(Object failedObject) {
      super(Localiser.msg("015032"), failedObject);
   }
}
