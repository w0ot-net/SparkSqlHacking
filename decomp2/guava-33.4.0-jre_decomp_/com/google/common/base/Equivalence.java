package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.ForOverride;
import java.io.Serializable;
import java.util.function.BiPredicate;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class Equivalence implements BiPredicate {
   protected Equivalence() {
   }

   public final boolean equivalent(@CheckForNull Object a, @CheckForNull Object b) {
      if (a == b) {
         return true;
      } else {
         return a != null && b != null ? this.doEquivalent(a, b) : false;
      }
   }

   /** @deprecated */
   @Deprecated
   public final boolean test(@CheckForNull Object t, @CheckForNull Object u) {
      return this.equivalent(t, u);
   }

   @ForOverride
   protected abstract boolean doEquivalent(Object a, Object b);

   public final int hash(@CheckForNull Object t) {
      return t == null ? 0 : this.doHash(t);
   }

   @ForOverride
   protected abstract int doHash(Object t);

   public final Equivalence onResultOf(Function function) {
      return new FunctionalEquivalence(function, this);
   }

   public final Wrapper wrap(@ParametricNullness Object reference) {
      return new Wrapper(this, reference);
   }

   @GwtCompatible(
      serializable = true
   )
   public final Equivalence pairwise() {
      return new PairwiseEquivalence(this);
   }

   public final Predicate equivalentTo(@CheckForNull Object target) {
      return new EquivalentToPredicate(this, target);
   }

   public static Equivalence equals() {
      return Equivalence.Equals.INSTANCE;
   }

   public static Equivalence identity() {
      return Equivalence.Identity.INSTANCE;
   }

   public static final class Wrapper implements Serializable {
      private final Equivalence equivalence;
      @ParametricNullness
      private final Object reference;
      private static final long serialVersionUID = 0L;

      private Wrapper(Equivalence equivalence, @ParametricNullness Object reference) {
         this.equivalence = (Equivalence)Preconditions.checkNotNull(equivalence);
         this.reference = reference;
      }

      @ParametricNullness
      public Object get() {
         return this.reference;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj == this) {
            return true;
         } else {
            if (obj instanceof Wrapper) {
               Wrapper<?> that = (Wrapper)obj;
               if (this.equivalence.equals(that.equivalence)) {
                  Equivalence<Object> equivalence = this.equivalence;
                  return equivalence.equivalent(this.reference, that.reference);
               }
            }

            return false;
         }
      }

      public int hashCode() {
         return this.equivalence.hash(this.reference);
      }

      public String toString() {
         return this.equivalence + ".wrap(" + this.reference + ")";
      }
   }

   private static final class EquivalentToPredicate implements Predicate, Serializable {
      private final Equivalence equivalence;
      @CheckForNull
      private final Object target;
      private static final long serialVersionUID = 0L;

      EquivalentToPredicate(Equivalence equivalence, @CheckForNull Object target) {
         this.equivalence = (Equivalence)Preconditions.checkNotNull(equivalence);
         this.target = target;
      }

      public boolean apply(@CheckForNull Object input) {
         return this.equivalence.equivalent(input, this.target);
      }

      public boolean equals(@CheckForNull Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof EquivalentToPredicate)) {
            return false;
         } else {
            EquivalentToPredicate<?> that = (EquivalentToPredicate)obj;
            return this.equivalence.equals(that.equivalence) && Objects.equal(this.target, that.target);
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.equivalence, this.target);
      }

      public String toString() {
         return this.equivalence + ".equivalentTo(" + this.target + ")";
      }
   }

   static final class Equals extends Equivalence implements Serializable {
      static final Equals INSTANCE = new Equals();
      private static final long serialVersionUID = 1L;

      protected boolean doEquivalent(Object a, Object b) {
         return a.equals(b);
      }

      protected int doHash(Object o) {
         return o.hashCode();
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }

   static final class Identity extends Equivalence implements Serializable {
      static final Identity INSTANCE = new Identity();
      private static final long serialVersionUID = 1L;

      protected boolean doEquivalent(Object a, Object b) {
         return false;
      }

      protected int doHash(Object o) {
         return System.identityHashCode(o);
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }
}
