package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class NotATableException extends DatastoreValidationException {
   private static final long serialVersionUID = 8257695149631939361L;

   public NotATableException(String tableName, String type) {
      super(Localiser.msg("020012", new Object[]{tableName, type}));
   }
}
