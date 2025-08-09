package org.datanucleus.store.rdbms.connectionpool;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;

public class DatastorePoolException extends NucleusException {
   private static final long serialVersionUID = -7936864514567835075L;

   public DatastorePoolException(String poolName, String driverName, String url, Exception nested) {
      super(Localiser.msg("047002", new Object[]{poolName, driverName, url, nested.getMessage()}), nested);
   }
}
