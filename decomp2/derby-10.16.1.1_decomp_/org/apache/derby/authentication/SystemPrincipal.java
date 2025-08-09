package org.apache.derby.authentication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.Principal;

public final class SystemPrincipal implements Principal, Serializable {
   static final long serialVersionUID = 925380094921530190L;
   private final String name;

   public SystemPrincipal(String var1) {
      validateName(var1);
      this.name = var1;
   }

   private static void validateName(String var0) {
      if (var0 == null) {
         throw new NullPointerException("name can't be null");
      } else if (var0.length() == 0) {
         throw new IllegalArgumentException("name can't be empty");
      }
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (!(var1 instanceof SystemPrincipal)) {
         return false;
      } else {
         SystemPrincipal var2 = (SystemPrincipal)var1;
         return this.name.equals(var2.name);
      }
   }

   public String getName() {
      return this.name;
   }

   public int hashCode() {
      return this.name.hashCode();
   }

   public String toString() {
      String var10000 = this.getClass().getName();
      return var10000 + "(" + this.name + ")";
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      validateName(this.name);
   }
}
