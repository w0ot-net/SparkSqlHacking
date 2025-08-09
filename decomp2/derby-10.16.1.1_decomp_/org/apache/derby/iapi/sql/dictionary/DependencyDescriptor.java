package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;

public class DependencyDescriptor extends UniqueTupleDescriptor {
   private final UUID dependentID;
   private final DependableFinder dependentBloodhound;
   private final UUID providerID;
   private final DependableFinder providerBloodhound;

   public DependencyDescriptor(Dependent var1, Provider var2) {
      this.dependentID = var1.getObjectID();
      this.dependentBloodhound = var1.getDependableFinder();
      this.providerID = var2.getObjectID();
      this.providerBloodhound = var2.getDependableFinder();
   }

   public DependencyDescriptor(UUID var1, DependableFinder var2, UUID var3, DependableFinder var4) {
      this.dependentID = var1;
      this.dependentBloodhound = var2;
      this.providerID = var3;
      this.providerBloodhound = var4;
   }

   public UUID getUUID() {
      return this.dependentID;
   }

   public DependableFinder getDependentFinder() {
      return this.dependentBloodhound;
   }

   public UUID getProviderID() {
      return this.providerID;
   }

   public DependableFinder getProviderFinder() {
      return this.providerBloodhound;
   }
}
