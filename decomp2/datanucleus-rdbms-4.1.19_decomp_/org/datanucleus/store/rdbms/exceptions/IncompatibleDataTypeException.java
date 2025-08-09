package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;

public class IncompatibleDataTypeException extends DatastoreValidationException {
   private static final long serialVersionUID = 5393213700816758070L;

   public IncompatibleDataTypeException(Column column, int expectedType, int actualType) {
      super(Localiser.msg("020009", new Object[]{column, column.getStoreManager().getDatastoreAdapter().getNameForJDBCType(actualType), column.getStoreManager().getDatastoreAdapter().getNameForJDBCType(expectedType)}));
   }
}
