package org.glassfish.jersey.model.internal;

import java.lang.reflect.Type;
import java.util.Set;
import org.glassfish.jersey.JerseyPriorities;

public class RankedProvider {
   private final Object provider;
   private final int rank;
   private final Set contractTypes;

   public RankedProvider(Object provider) {
      this.provider = provider;
      this.rank = this.computeRank(provider, -1);
      this.contractTypes = null;
   }

   public RankedProvider(Object provider, int rank) {
      this(provider, rank, (Set)null);
   }

   public RankedProvider(Object provider, int rank, Set contracts) {
      this.provider = provider;
      this.rank = this.computeRank(provider, rank);
      this.contractTypes = contracts;
   }

   private int computeRank(Object provider, int rank) {
      if (rank > 0) {
         return rank;
      } else {
         Class<?> clazz;
         for(clazz = provider.getClass(); clazz.isSynthetic(); clazz = clazz.getSuperclass()) {
         }

         return JerseyPriorities.getPriorityValue(clazz, 5000);
      }
   }

   public Object getProvider() {
      return this.provider;
   }

   public int getRank() {
      return this.rank;
   }

   public Set getContractTypes() {
      return this.contractTypes;
   }

   public String toString() {
      return this.provider.getClass().getName();
   }
}
