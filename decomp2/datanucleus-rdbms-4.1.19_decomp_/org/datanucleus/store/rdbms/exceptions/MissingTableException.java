package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class MissingTableException extends DatastoreValidationException {
   private static final long serialVersionUID = 8360855107029754952L;

   public MissingTableException(String catalogName, String schemaName, String tableName) {
      super(Localiser.msg("020011", new Object[]{catalogName, schemaName, tableName}));
   }
}
