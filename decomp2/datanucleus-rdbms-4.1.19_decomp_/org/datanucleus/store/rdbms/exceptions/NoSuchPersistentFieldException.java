package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class NoSuchPersistentFieldException extends NucleusUserException {
   private static final long serialVersionUID = 2364470194200034650L;

   public NoSuchPersistentFieldException(String className, String fieldName) {
      super(Localiser.msg("018009", new Object[]{fieldName, className}));
   }

   public NoSuchPersistentFieldException(String className, int fieldNumber) {
      super(Localiser.msg("018010", new Object[]{"" + fieldNumber, className}));
   }
}
