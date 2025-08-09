package org.apache.derby.iapi.sql.depend;

import org.apache.derby.catalog.UUID;

public interface Dependency {
   UUID getProviderKey();

   Provider getProvider();

   Dependent getDependent();
}
