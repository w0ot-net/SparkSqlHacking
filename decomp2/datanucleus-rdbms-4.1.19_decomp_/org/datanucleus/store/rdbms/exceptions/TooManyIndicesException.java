package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.util.Localiser;

public class TooManyIndicesException extends NucleusDataStoreException {
   private static final long serialVersionUID = 3906217271154531757L;

   public TooManyIndicesException(DatastoreAdapter dba, String tableName) {
      super(Localiser.msg("020016", new Object[]{"" + dba.getMaxIndexes(), tableName}));
      this.setFatal();
   }
}
