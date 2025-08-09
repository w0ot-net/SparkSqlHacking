package org.datanucleus.store.rdbms.connectionpool;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;

public class DatastoreDriverNotFoundException extends NucleusException {
   private static final long serialVersionUID = 2004483035679438362L;

   public DatastoreDriverNotFoundException(String driverClassName) {
      super(Localiser.msg("047000", new Object[]{driverClassName}));
   }
}
