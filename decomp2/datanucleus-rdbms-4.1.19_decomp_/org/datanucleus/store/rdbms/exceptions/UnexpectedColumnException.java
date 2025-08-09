package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class UnexpectedColumnException extends DatastoreValidationException {
   private static final long serialVersionUID = 804974383075712052L;

   public UnexpectedColumnException(String table_name, String column_name, String schema_name, String catalog_name) {
      super(Localiser.msg("020024", new Object[]{column_name, table_name, schema_name, catalog_name}));
   }
}
