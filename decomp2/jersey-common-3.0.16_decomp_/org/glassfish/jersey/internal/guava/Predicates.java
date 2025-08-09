package org.glassfish.jersey.internal.guava;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Predicates {
   private static final Joiner COMMA_JOINER = Joiner.on();

   private Predicates() {
   }

   public static Predicate alwaysTrue() {
      return Predicates.ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
   }

   private static Predicate isNull() {
      return Predicates.ObjectPredicate.IS_NULL.withNarrowedType();
   }

   public static Predicate not(Predicate predicate) {
      return new NotPredicate(predicate);
   }

   public static Predicate equalTo(Object target) {
      return (Predicate)(target == null ? isNull() : new IsEqualToPredicate(target));
   }

   public static Predicate in(Collection target) {
      return new InPredicate(target);
   }

   public static Predicate compose(Predicate predicate, Function function) {
      return new CompositionPredicate(predicate, function);
   }

   static enum ObjectPredicate implements Predicate {
      ALWAYS_TRUE {
         public boolean test(Object o) {
            return true;
         }

         public String toString() {
            return "Predicates.alwaysTrue()";
         }
      },
      IS_NULL {
         public boolean test(Object o) {
            return o == null;
         }

         public String toString() {
            return "Predicates.isNull()";
         }
      };

      private ObjectPredicate() {
      }

      Predicate withNarrowedType() {
         return this;
      }
   }

   private static class NotPredicate implements Predicate, Serializable {
      private static final long serialVersionUID = 0L;
      final Predicate predicate;

      NotPredicate(Predicate predicate) {
         this.predicate = (Predicate)Preconditions.checkNotNull(predicate);
      }

      public boolean test(Object t) {
         return !this.predicate.test(t);
      }

      public int hashCode() {
         return ~this.predicate.hashCode();
      }

      public boolean equals(Object obj) {
         if (obj instanceof NotPredicate) {
            NotPredicate<?> that = (NotPredicate)obj;
            return this.predicate.equals(that.predicate);
         } else {
            return false;
         }
      }

      public String toString() {
         return "Predicates.not(" + this.predicate.toString() + ")";
      }
   }

   private static class IsEqualToPredicate implements Predicate, Serializable {
      private static final long serialVersionUID = 0L;
      private final Object target;

      private IsEqualToPredicate(Object target) {
         this.target = target;
      }

      public boolean test(Object t) {
         return this.target.equals(t);
      }

      public int hashCode() {
         return this.target.hashCode();
      }

      public boolean equals(Object obj) {
         if (obj instanceof IsEqualToPredicate) {
            IsEqualToPredicate<?> that = (IsEqualToPredicate)obj;
            return this.target.equals(that.target);
         } else {
            return false;
         }
      }

      public String toString() {
         return "Predicates.equalTo(" + this.target + ")";
      }
   }

   private static class InPredicate implements Predicate, Serializable {
      private static final long serialVersionUID = 0L;
      private final Collection target;

      private InPredicate(Collection target) {
         this.target = (Collection)Preconditions.checkNotNull(target);
      }

      public boolean test(Object t) {
         try {
            return this.target.contains(t);
         } catch (NullPointerException var3) {
            return false;
         } catch (ClassCastException var4) {
            return false;
         }
      }

      public boolean equals(Object obj) {
         if (obj instanceof InPredicate) {
            InPredicate<?> that = (InPredicate)obj;
            return this.target.equals(that.target);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.target.hashCode();
      }

      public String toString() {
         return "Predicates.in(" + this.target + ")";
      }
   }

   private static class CompositionPredicate implements Predicate, Serializable {
      private static final long serialVersionUID = 0L;
      final Predicate p;
      final Function f;

      private CompositionPredicate(Predicate p, Function f) {
         this.p = (Predicate)Preconditions.checkNotNull(p);
         this.f = (Function)Preconditions.checkNotNull(f);
      }

      public boolean test(Object a) {
         return this.p.test(this.f.apply(a));
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof CompositionPredicate)) {
            return false;
         } else {
            CompositionPredicate<?, ?> that = (CompositionPredicate)obj;
            return this.f.equals(that.f) && this.p.equals(that.p);
         }
      }

      public int hashCode() {
         return this.f.hashCode() ^ this.p.hashCode();
      }

      public String toString() {
         return this.p.toString() + "(" + this.f.toString() + ")";
      }
   }
}
