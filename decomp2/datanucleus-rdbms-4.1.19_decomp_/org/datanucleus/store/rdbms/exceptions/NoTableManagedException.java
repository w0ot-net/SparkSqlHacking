package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class NoTableManagedException extends NucleusUserException {
   private static final long serialVersionUID = -1610018637755474684L;

   public NoTableManagedException(String className) {
      super(Localiser.msg("020000", new Object[]{className}));
   }
}
