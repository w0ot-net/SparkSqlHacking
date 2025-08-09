package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class NoDatastoreMappingException extends NucleusUserException {
   private static final long serialVersionUID = -4514927315711485556L;

   public NoDatastoreMappingException(String fieldName) {
      super(Localiser.msg("020001", new Object[]{fieldName}));
   }
}
