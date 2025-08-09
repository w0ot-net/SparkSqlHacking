package org.apache.derby.impl.sql.compile;

import org.apache.derby.vti.DeferModification;

class DefaultVTIModDeferPolicy implements DeferModification {
   private final String targetVTIClassName;
   private final boolean VTIResultSetIsSensitive;

   DefaultVTIModDeferPolicy(String var1, boolean var2) {
      this.targetVTIClassName = var1;
      this.VTIResultSetIsSensitive = var2;
   }

   public boolean alwaysDefer(int var1) {
      return false;
   }

   public boolean columnRequiresDefer(int var1, String var2, boolean var3) {
      switch (var1) {
         case 1 -> {
            return false;
         }
         case 2 -> {
            return this.VTIResultSetIsSensitive && var3;
         }
         case 3 -> {
            return false;
         }
         default -> {
            return false;
         }
      }
   }

   public boolean subselectRequiresDefer(int var1, String var2, String var3) {
      return false;
   }

   public boolean subselectRequiresDefer(int var1, String var2) {
      return this.targetVTIClassName.equals(var2);
   }

   public void modificationNotify(int var1, boolean var2) {
   }
}
