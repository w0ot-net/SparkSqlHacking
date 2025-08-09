package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class WrongScaleException extends DatastoreValidationException {
   private static final long serialVersionUID = 87315294180371240L;

   public WrongScaleException(String columnName, int expectedScale, int actualScale) {
      super(Localiser.msg("020021", new Object[]{columnName, "" + actualScale, "" + expectedScale}));
   }

   public WrongScaleException(String columnName, int expectedScale, int actualScale, String fieldName) {
      super(Localiser.msg("020022", new Object[]{columnName, "" + actualScale, "" + expectedScale, fieldName}));
   }
}
