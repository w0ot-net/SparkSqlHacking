package org.apache.derby.impl.sql.depend;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.depend.Dependency;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;

class BasicDependency implements Dependency {
   private final Provider provider;
   private final Dependent dependent;

   public UUID getProviderKey() {
      return this.provider.getObjectID();
   }

   public Provider getProvider() {
      return this.provider;
   }

   public Dependent getDependent() {
      return this.dependent;
   }

   BasicDependency(Dependent var1, Provider var2) {
      this.dependent = var1;
      this.provider = var2;
   }
}
