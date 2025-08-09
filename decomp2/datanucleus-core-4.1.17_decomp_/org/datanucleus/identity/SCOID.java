package org.datanucleus.identity;

public final class SCOID {
   public final String objClass;

   public SCOID(String objClass) {
      this.objClass = objClass;
   }

   public String getSCOClass() {
      return this.objClass;
   }
}
