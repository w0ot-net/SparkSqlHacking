package org.sparkproject.guava.base;

import java.io.Serializable;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class FunctionalEquivalence extends Equivalence implements Serializable {
   private static final long serialVersionUID = 0L;
   private final Function function;
   private final Equivalence resultEquivalence;

   FunctionalEquivalence(Function function, Equivalence resultEquivalence) {
      this.function = (Function)Preconditions.checkNotNull(function);
      this.resultEquivalence = (Equivalence)Preconditions.checkNotNull(resultEquivalence);
   }

   protected boolean doEquivalent(Object a, Object b) {
      return this.resultEquivalence.equivalent(this.function.apply(a), this.function.apply(b));
   }

   protected int doHash(Object a) {
      return this.resultEquivalence.hash(this.function.apply(a));
   }

   public boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof FunctionalEquivalence)) {
         return false;
      } else {
         FunctionalEquivalence<?, ?> that = (FunctionalEquivalence)obj;
         return this.function.equals(that.function) && this.resultEquivalence.equals(that.resultEquivalence);
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.function, this.resultEquivalence);
   }

   public String toString() {
      return this.resultEquivalence + ".onResultOf(" + this.function + ")";
   }
}
