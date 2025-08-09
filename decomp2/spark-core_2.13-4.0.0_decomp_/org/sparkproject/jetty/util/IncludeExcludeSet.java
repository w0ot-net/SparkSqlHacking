package org.sparkproject.jetty.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class IncludeExcludeSet implements Predicate {
   private final Set _includes;
   private final Predicate _includePredicate;
   private final Set _excludes;
   private final Predicate _excludePredicate;

   public IncludeExcludeSet() {
      this(HashSet.class);
   }

   public IncludeExcludeSet(Class setClass) {
      try {
         this._includes = (Set)setClass.getDeclaredConstructor().newInstance();
         this._excludes = (Set)setClass.getDeclaredConstructor().newInstance();
         if (this._includes instanceof Predicate) {
            this._includePredicate = (Predicate)this._includes;
         } else {
            this._includePredicate = new SetContainsPredicate(this._includes);
         }

         if (this._excludes instanceof Predicate) {
            this._excludePredicate = (Predicate)this._excludes;
         } else {
            this._excludePredicate = new SetContainsPredicate(this._excludes);
         }

      } catch (RuntimeException e) {
         throw e;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public IncludeExcludeSet(Set includeSet, Predicate includePredicate, Set excludeSet, Predicate excludePredicate) {
      Objects.requireNonNull(includeSet, "Include Set");
      Objects.requireNonNull(includePredicate, "Include Predicate");
      Objects.requireNonNull(excludeSet, "Exclude Set");
      Objects.requireNonNull(excludePredicate, "Exclude Predicate");
      this._includes = includeSet;
      this._includePredicate = includePredicate;
      this._excludes = excludeSet;
      this._excludePredicate = excludePredicate;
   }

   public void include(Object element) {
      this._includes.add(element);
   }

   public void include(Object... element) {
      this._includes.addAll(Arrays.asList(element));
   }

   public void exclude(Object element) {
      this._excludes.add(element);
   }

   public void exclude(Object... element) {
      this._excludes.addAll(Arrays.asList(element));
   }

   public boolean test(Object t) {
      if (!this._includes.isEmpty() && !this._includePredicate.test(t)) {
         return false;
      } else if (this._excludes.isEmpty()) {
         return true;
      } else {
         return !this._excludePredicate.test(t);
      }
   }

   public Boolean isIncludedAndNotExcluded(Object item) {
      if (!this._excludes.isEmpty() && this._excludePredicate.test(item)) {
         return Boolean.FALSE;
      } else {
         return !this._includes.isEmpty() && this._includePredicate.test(item) ? Boolean.TRUE : null;
      }
   }

   public boolean hasIncludes() {
      return !this._includes.isEmpty();
   }

   public boolean hasExcludes() {
      return !this._excludes.isEmpty();
   }

   public int size() {
      return this._includes.size() + this._excludes.size();
   }

   public Set getIncluded() {
      return this._includes;
   }

   public Set getExcluded() {
      return this._excludes;
   }

   public void clear() {
      this._includes.clear();
      this._excludes.clear();
   }

   public String toString() {
      return String.format("%s@%x{i=%s,ip=%s,e=%s,ep=%s}", this.getClass().getSimpleName(), this.hashCode(), this._includes, this._includePredicate == this._includes ? "SELF" : this._includePredicate, this._excludes, this._excludePredicate == this._excludes ? "SELF" : this._excludePredicate);
   }

   public boolean isEmpty() {
      return this._includes.isEmpty() && this._excludes.isEmpty();
   }

   private static class SetContainsPredicate implements Predicate {
      private final Set set;

      public SetContainsPredicate(Set set) {
         this.set = set;
      }

      public boolean test(Object item) {
         return this.set.contains(item);
      }

      public String toString() {
         return "CONTAINS";
      }
   }
}
