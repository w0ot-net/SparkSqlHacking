package org.datanucleus.identity;

import java.io.Serializable;

public class IdentityReference implements Serializable {
   private static final long serialVersionUID = 2472281096825989665L;
   protected Object client;

   public IdentityReference(Object client) {
      this.client = client;
   }

   public int hashCode() {
      return System.identityHashCode(this.client);
   }

   public boolean equals(Object o) {
      if (o instanceof IdentityReference) {
         return this.client == ((IdentityReference)o).client;
      } else {
         return false;
      }
   }
}
