package org.apache.curator.shaded.com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class PairwiseEquivalence extends Equivalence implements Serializable {
   final Equivalence elementEquivalence;
   private static final long serialVersionUID = 1L;

   PairwiseEquivalence(Equivalence elementEquivalence) {
      this.elementEquivalence = (Equivalence)Preconditions.checkNotNull(elementEquivalence);
   }

   protected boolean doEquivalent(Iterable iterableA, Iterable iterableB) {
      Iterator<T> iteratorA = iterableA.iterator();
      Iterator<T> iteratorB = iterableB.iterator();

      while(iteratorA.hasNext() && iteratorB.hasNext()) {
         if (!this.elementEquivalence.equivalent(iteratorA.next(), iteratorB.next())) {
            return false;
         }
      }

      return !iteratorA.hasNext() && !iteratorB.hasNext();
   }

   protected int doHash(Iterable iterable) {
      int hash = 78721;

      for(Object element : iterable) {
         hash = hash * 24943 + this.elementEquivalence.hash(element);
      }

      return hash;
   }

   public boolean equals(@CheckForNull Object object) {
      if (object instanceof PairwiseEquivalence) {
         PairwiseEquivalence<Object, Object> that = (PairwiseEquivalence)object;
         return this.elementEquivalence.equals(that.elementEquivalence);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.elementEquivalence.hashCode() ^ 1185147655;
   }

   public String toString() {
      return this.elementEquivalence + ".pairwise()";
   }
}
