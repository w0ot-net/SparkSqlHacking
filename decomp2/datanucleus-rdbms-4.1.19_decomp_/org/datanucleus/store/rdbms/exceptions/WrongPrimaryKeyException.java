package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class WrongPrimaryKeyException extends DatastoreValidationException {
   private static final long serialVersionUID = 8688198223306432767L;

   public WrongPrimaryKeyException(String table_name, String expected_pk, String actual_pks) {
      super(Localiser.msg("020020", new Object[]{table_name, expected_pk, actual_pks}));
   }
}
