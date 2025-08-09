package org.apache.orc.impl.filter;

import org.apache.orc.OrcFilterContext;

public class AndFilter implements VectorFilter {
   public final VectorFilter[] filters;
   private final Selected andBound = new Selected();
   private final Selected andOut = new Selected();

   public AndFilter(VectorFilter[] filters) {
      this.filters = filters;
   }

   public void filter(OrcFilterContext fc, Selected bound, Selected selOut) {
      this.andBound.set(bound);
      this.andOut.ensureSize(bound.selSize);

      for(VectorFilter f : this.filters) {
         this.andOut.clear();
         f.filter(fc, this.andBound, this.andOut);
         this.andBound.set(this.andOut);
      }

      selOut.set(this.andOut);
   }
}
