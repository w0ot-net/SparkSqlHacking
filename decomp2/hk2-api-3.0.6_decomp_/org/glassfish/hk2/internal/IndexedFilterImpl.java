package org.glassfish.hk2.internal;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.IndexedFilter;

public class IndexedFilterImpl implements IndexedFilter {
   private final String contract;
   private final String name;

   public IndexedFilterImpl(String contract, String name) {
      this.contract = contract;
      this.name = name;
   }

   public boolean matches(Descriptor d) {
      return true;
   }

   public String getAdvertisedContract() {
      return this.contract;
   }

   public String getName() {
      return this.name;
   }

   public String toString() {
      String var10000 = this.contract;
      return "IndexedFilterImpl(" + var10000 + "," + this.name + "," + System.identityHashCode(this) + ")";
   }
}
