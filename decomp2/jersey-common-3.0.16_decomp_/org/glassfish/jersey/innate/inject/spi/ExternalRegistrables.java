package org.glassfish.jersey.innate.inject.spi;

import jakarta.ws.rs.RuntimeType;
import java.util.List;

public interface ExternalRegistrables {
   List registrableContracts();

   public static final class ClassRuntimeTypePair {
      private final Class contract;
      private final RuntimeType runtimeType;

      public ClassRuntimeTypePair(Class contract, RuntimeType runtimeType) {
         this.contract = contract;
         this.runtimeType = runtimeType;
      }

      public Class getContract() {
         return this.contract;
      }

      public RuntimeType getRuntimeType() {
         return this.runtimeType;
      }
   }
}
