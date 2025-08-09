package org.glassfish.jersey.internal.inject;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Set;

public class ServiceHolderImpl implements ServiceHolder {
   private final Object service;
   private final Class implementationClass;
   private final Set contractTypes;
   private final int rank;

   public ServiceHolderImpl(Object service, Set contractTypes) {
      this(service, service.getClass(), contractTypes, 0);
   }

   public ServiceHolderImpl(Object service, Class implementationClass, Set contractTypes, int rank) {
      this.service = service;
      this.implementationClass = implementationClass;
      this.contractTypes = contractTypes;
      this.rank = rank;
   }

   public Object getInstance() {
      return this.service;
   }

   public Class getImplementationClass() {
      return this.implementationClass;
   }

   public Set getContractTypes() {
      return this.contractTypes;
   }

   public int getRank() {
      return this.rank;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ServiceHolderImpl)) {
         return false;
      } else {
         ServiceHolderImpl<?> that = (ServiceHolderImpl)o;
         return this.rank == that.rank && Objects.equals(this.service, that.service) && Objects.equals(this.implementationClass, that.implementationClass) && Objects.equals(this.contractTypes, that.contractTypes);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.service, this.implementationClass, this.contractTypes, this.rank});
   }
}
