package org.apache.orc.impl.filter;

import org.apache.orc.OrcFilterContext;

public class OrFilter implements VectorFilter {
   public final VectorFilter[] filters;
   private final Selected orOut = new Selected();
   private final Selected orBound = new Selected();

   public OrFilter(VectorFilter[] filters) {
      this.filters = filters;
   }

   public void filter(OrcFilterContext fc, Selected bound, Selected selOut) {
      this.orOut.ensureSize(bound.selSize);
      this.orBound.set(bound);

      for(VectorFilter f : this.filters) {
         this.orOut.clear();
         f.filter(fc, this.orBound, this.orOut);
         selOut.unionDisjoint(this.orOut);
         this.orBound.minus(this.orOut);
      }

   }
}
