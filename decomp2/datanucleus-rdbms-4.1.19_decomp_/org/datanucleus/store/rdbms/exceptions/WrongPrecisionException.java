package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class WrongPrecisionException extends DatastoreValidationException {
   private static final long serialVersionUID = -4580250551015816508L;

   public WrongPrecisionException(String columnName, int expectedPrecision, int actualPrecision) {
      super(Localiser.msg("020018", new Object[]{columnName, "" + actualPrecision, "" + expectedPrecision}));
   }

   public WrongPrecisionException(String columnName, int expectedPrecision, int actualPrecision, String fieldName) {
      super(Localiser.msg("020019", new Object[]{columnName, "" + actualPrecision, "" + expectedPrecision, fieldName}));
   }
}
