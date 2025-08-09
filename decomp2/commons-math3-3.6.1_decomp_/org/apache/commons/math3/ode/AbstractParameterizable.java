package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractParameterizable implements Parameterizable {
   private final Collection parametersNames = new ArrayList();

   protected AbstractParameterizable(String... names) {
      for(String name : names) {
         this.parametersNames.add(name);
      }

   }

   protected AbstractParameterizable(Collection names) {
      this.parametersNames.addAll(names);
   }

   public Collection getParametersNames() {
      return this.parametersNames;
   }

   public boolean isSupported(String name) {
      for(String supportedName : this.parametersNames) {
         if (supportedName.equals(name)) {
            return true;
         }
      }

      return false;
   }

   public void complainIfNotSupported(String name) throws UnknownParameterException {
      if (!this.isSupported(name)) {
         throw new UnknownParameterException(name);
      }
   }
}
