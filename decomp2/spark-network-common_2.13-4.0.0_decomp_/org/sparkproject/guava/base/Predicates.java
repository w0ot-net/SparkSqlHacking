package org.sparkproject.guava.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Predicates {
   private Predicates() {
   }

   @GwtCompatible(
      serializable = true
   )
   public static Predicate alwaysTrue() {
      return Predicates.ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
   }

   @GwtCompatible(
      serializable = true
   )
   public static Predicate alwaysFalse() {
      return Predicates.ObjectPredicate.ALWAYS_FALSE.withNarrowedType();
   }

   @GwtCompatible(
      serializable = true
   )
   public static Predicate isNull() {
      return Predicates.ObjectPredicate.IS_NULL.withNarrowedType();
   }

   @GwtCompatible(
      serializable = true
   )
   public static Predicate notNull() {
      return Predicates.ObjectPredicate.NOT_NULL.withNarrowedType();
   }

   public static Predicate not(Predicate predicate) {
      return new NotPredicate(predicate);
   }

   public static Predicate and(Iterable components) {
      return new AndPredicate(defensiveCopy(components));
   }

   @SafeVarargs
   public static Predicate and(Predicate... components) {
      return new AndPredicate(defensiveCopy((Object[])components));
   }

   public static Predicate and(Predicate first, Predicate second) {
      return new AndPredicate(asList((Predicate)Preconditions.checkNotNull(first), (Predicate)Preconditions.checkNotNull(second)));
   }

   public static Predicate or(Iterable components) {
      return new OrPredicate(defensiveCopy(components));
   }

   @SafeVarargs
   public static Predicate or(Predicate... components) {
      return new OrPredicate(defensiveCopy((Object[])components));
   }

   public static Predicate or(Predicate first, Predicate second) {
      return new OrPredicate(asList((Predicate)Preconditions.checkNotNull(first), (Predicate)Preconditions.checkNotNull(second)));
   }

   public static Predicate equalTo(@ParametricNullness Object target) {
      return target == null ? isNull() : (new IsEqualToPredicate(target)).withNarrowedType();
   }

   @GwtIncompatible
   public static Predicate instanceOf(Class clazz) {
      return new InstanceOfPredicate(clazz);
   }

   @J2ktIncompatible
   @GwtIncompatible
   public static Predicate subtypeOf(Class clazz) {
      return new SubtypeOfPredicate(clazz);
   }

   public static Predicate in(Collection target) {
      return new InPredicate(target);
   }

   public static Predicate compose(Predicate predicate, Function function) {
      return new CompositionPredicate(predicate, function);
   }

   @GwtIncompatible
   public static Predicate containsPattern(String pattern) {
      return new ContainsPatternFromStringPredicate(pattern);
   }

   @GwtIncompatible("java.util.regex.Pattern")
   public static Predicate contains(Pattern pattern) {
      return new ContainsPatternPredicate(new JdkPattern(pattern));
   }

   private static String toStringHelper(String methodName, Iterable components) {
      StringBuilder builder = (new StringBuilder("Predicates.")).append(methodName).append('(');
      boolean first = true;

      for(Object o : components) {
         if (!first) {
            builder.append(',');
         }

         builder.append(o);
         first = false;
      }

      return builder.append(')').toString();
   }

   private static List asList(Predicate first, Predicate second) {
      return Arrays.asList(first, second);
   }

   private static List defensiveCopy(Object... array) {
      return defensiveCopy((Iterable)Arrays.asList(array));
   }

   static List defensiveCopy(Iterable iterable) {
      ArrayList<T> list = new ArrayList();

      for(Object element : iterable) {
         list.add(Preconditions.checkNotNull(element));
      }

      return list;
   }

   static enum ObjectPredicate implements Predicate {
      ALWAYS_TRUE {
         public boolean apply(@CheckForNull Object o) {
            return true;
         }

         public String toString() {
            return "Predicates.alwaysTrue()";
         }
      },
      ALWAYS_FALSE {
         public boolean apply(@CheckForNull Object o) {
            return false;
         }

         public String toString() {
            return "Predicates.alwaysFalse()";
         }
      },
      IS_NULL {
         public boolean apply(@CheckForNull Object o) {
            return o == null;
         }

         public String toString() {
            return "Predicates.isNull()";
         }
      },
      NOT_NULL {
         public boolean apply(@CheckForNull Object o) {
            return o != null;
         }

         public String toString() {
            return "Predicates.notNull()";
         }
      };

      private ObjectPredicate() {
      }

      Predicate withNarrowedType() {
         return this;
      }

      // $FF: synthetic method
      private static ObjectPredicate[] $values() {
         return new ObjectPredicate[]{ALWAYS_TRUE, ALWAYS_FALSE, IS_NULL, NOT_NULL};
      }
   }

   private static class NotPredicate implements Predicate, Serializable {
      final Predicate predicate;
      private static final long serialVersionUID = 0L;

      NotPredicate(Predicate predicate) {
         this.predicate = (Predicate)Preconditions.checkNotNull(predicate);
      }

      public boolean apply(@ParametricNullness Object t) {
         return !this.predicate.apply(t);
      }

      public int hashCode() {
         return ~this.predicate.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof NotPredicate) {
            NotPredicate<?> that = (NotPredicate)obj;
            return this.predicate.equals(that.predicate);
         } else {
            return false;
         }
      }

      public String toString() {
         return "Predicates.not(" + this.predicate + ")";
      }
   }

   private static class AndPredicate implements Predicate, Serializable {
      private final List components;
      private static final long serialVersionUID = 0L;

      private AndPredicate(List components) {
         this.components = components;
      }

      public boolean apply(@ParametricNullness Object t) {
         for(int i = 0; i < this.components.size(); ++i) {
            if (!((Predicate)this.components.get(i)).apply(t)) {
               return false;
            }
         }

         return true;
      }

      public int hashCode() {
         return this.components.hashCode() + 306654252;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof AndPredicate) {
            AndPredicate<?> that = (AndPredicate)obj;
            return this.components.equals(that.components);
         } else {
            return false;
         }
      }

      public String toString() {
         return Predicates.toStringHelper("and", this.components);
      }
   }

   private static class OrPredicate implements Predicate, Serializable {
      private final List components;
      private static final long serialVersionUID = 0L;

      private OrPredicate(List components) {
         this.components = components;
      }

      public boolean apply(@ParametricNullness Object t) {
         for(int i = 0; i < this.components.size(); ++i) {
            if (((Predicate)this.components.get(i)).apply(t)) {
               return true;
            }
         }

         return false;
      }

      public int hashCode() {
         return this.components.hashCode() + 87855567;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof OrPredicate) {
            OrPredicate<?> that = (OrPredicate)obj;
            return this.components.equals(that.components);
         } else {
            return false;
         }
      }

      public String toString() {
         return Predicates.toStringHelper("or", this.components);
      }
   }

   private static class IsEqualToPredicate implements Predicate, Serializable {
      private final Object target;
      private static final long serialVersionUID = 0L;

      private IsEqualToPredicate(Object target) {
         this.target = target;
      }

      public boolean apply(@CheckForNull Object o) {
         return this.target.equals(o);
      }

      public int hashCode() {
         return this.target.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof IsEqualToPredicate) {
            IsEqualToPredicate that = (IsEqualToPredicate)obj;
            return this.target.equals(that.target);
         } else {
            return false;
         }
      }

      public String toString() {
         return "Predicates.equalTo(" + this.target + ")";
      }

      Predicate withNarrowedType() {
         return this;
      }
   }

   @GwtIncompatible
   private static class InstanceOfPredicate implements Predicate, Serializable {
      private final Class clazz;
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      private InstanceOfPredicate(Class clazz) {
         this.clazz = (Class)Preconditions.checkNotNull(clazz);
      }

      public boolean apply(@ParametricNullness Object o) {
         return this.clazz.isInstance(o);
      }

      public int hashCode() {
         return this.clazz.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof InstanceOfPredicate) {
            InstanceOfPredicate<?> that = (InstanceOfPredicate)obj;
            return this.clazz == that.clazz;
         } else {
            return false;
         }
      }

      public String toString() {
         return "Predicates.instanceOf(" + this.clazz.getName() + ")";
      }
   }

   @J2ktIncompatible
   @GwtIncompatible
   private static class SubtypeOfPredicate implements Predicate, Serializable {
      private final Class clazz;
      private static final long serialVersionUID = 0L;

      private SubtypeOfPredicate(Class clazz) {
         this.clazz = (Class)Preconditions.checkNotNull(clazz);
      }

      public boolean apply(Class input) {
         return this.clazz.isAssignableFrom(input);
      }

      public int hashCode() {
         return this.clazz.hashCode();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof SubtypeOfPredicate) {
            SubtypeOfPredicate that = (SubtypeOfPredicate)obj;
            return this.clazz == that.clazz;
         } else {
            return false;
         }
      }

      public String toString() {
         return "Predicates.subtypeOf(" + this.clazz.getName() + ")";
      }
   }

   private static class InPredicate implements Predicate, Serializable {
      private final Collection target;
      private static final long serialVersionUID = 0L;

      private InPredicate(Collection target) {
         this.target = (Collection)Preconditions.checkNotNull(target);
      }

      public boolean apply(@ParametricNullness Object t) {
         try {
            return this.target.contains(t);
         } catch (ClassCastException | NullPointerException var3) {
            return false;
         }
      }

      public boolean equals(@CheckForNull Object obj) {
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
      final Predicate p;
      final Function f;
      private static final long serialVersionUID = 0L;

      private CompositionPredicate(Predicate p, Function f) {
         this.p = (Predicate)Preconditions.checkNotNull(p);
         this.f = (Function)Preconditions.checkNotNull(f);
      }

      public boolean apply(@ParametricNullness Object a) {
         return this.p.apply(this.f.apply(a));
      }

      public boolean equals(@CheckForNull Object obj) {
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
         return this.p + "(" + this.f + ")";
      }
   }

   @GwtIncompatible
   private static class ContainsPatternPredicate implements Predicate, Serializable {
      final CommonPattern pattern;
      private static final long serialVersionUID = 0L;

      ContainsPatternPredicate(CommonPattern pattern) {
         this.pattern = (CommonPattern)Preconditions.checkNotNull(pattern);
      }

      public boolean apply(CharSequence t) {
         return this.pattern.matcher(t).find();
      }

      public int hashCode() {
         return Objects.hashCode(this.pattern.pattern(), this.pattern.flags());
      }

      public boolean equals(@CheckForNull Object obj) {
         if (!(obj instanceof ContainsPatternPredicate)) {
            return false;
         } else {
            ContainsPatternPredicate that = (ContainsPatternPredicate)obj;
            return Objects.equal(this.pattern.pattern(), that.pattern.pattern()) && this.pattern.flags() == that.pattern.flags();
         }
      }

      public String toString() {
         String patternString = MoreObjects.toStringHelper((Object)this.pattern).add("pattern", this.pattern.pattern()).add("pattern.flags", this.pattern.flags()).toString();
         return "Predicates.contains(" + patternString + ")";
      }
   }

   @GwtIncompatible
   private static class ContainsPatternFromStringPredicate extends ContainsPatternPredicate {
      private static final long serialVersionUID = 0L;

      ContainsPatternFromStringPredicate(String string) {
         super(Platform.compilePattern(string));
      }

      public String toString() {
         return "Predicates.containsPattern(" + this.pattern.pattern() + ")";
      }
   }
}
