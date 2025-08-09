package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class NaturalOrdering extends Ordering implements Serializable {
   static final NaturalOrdering INSTANCE = new NaturalOrdering();
   @LazyInit
   @CheckForNull
   private transient Ordering nullsFirst;
   @LazyInit
   @CheckForNull
   private transient Ordering nullsLast;
   private static final long serialVersionUID = 0L;

   public int compare(Comparable left, Comparable right) {
      Preconditions.checkNotNull(left);
      Preconditions.checkNotNull(right);
      return left.compareTo(right);
   }

   public Ordering nullsFirst() {
      Ordering<Comparable<?>> result = this.nullsFirst;
      if (result == null) {
         result = this.nullsFirst = super.nullsFirst();
      }

      return result;
   }

   public Ordering nullsLast() {
      Ordering<Comparable<?>> result = this.nullsLast;
      if (result == null) {
         result = this.nullsLast = super.nullsLast();
      }

      return result;
   }

   public Ordering reverse() {
      return ReverseNaturalOrdering.INSTANCE;
   }

   private Object readResolve() {
      return INSTANCE;
   }

   public String toString() {
      return "Ordering.natural()";
   }

   private NaturalOrdering() {
   }
}
