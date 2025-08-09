package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.util.Localiser;

public class TooManyForeignKeysException extends NucleusDataStoreException {
   private static final long serialVersionUID = 927612239349408531L;

   public TooManyForeignKeysException(DatastoreAdapter dba, String table_name) {
      super(Localiser.msg("020015", new Object[]{"" + dba.getMaxForeignKeys(), table_name}));
      this.setFatal();
   }
}
