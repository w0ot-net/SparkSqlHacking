package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Functions {
   private Functions() {
   }

   public static Function toStringFunction() {
      return Functions.ToStringFunction.INSTANCE;
   }

   public static Function identity() {
      return Functions.IdentityFunction.INSTANCE;
   }

   public static Function forMap(Map map) {
      return new FunctionForMapNoDefault(map);
   }

   public static Function forMap(Map map, @ParametricNullness Object defaultValue) {
      return new ForMapWithDefault(map, defaultValue);
   }

   public static Function compose(Function g, Function f) {
      return new FunctionComposition(g, f);
   }

   public static Function forPredicate(Predicate predicate) {
      return new PredicateFunction(predicate);
   }

   public static Function constant(@ParametricNullness Object value) {
      return new ConstantFunction(value);
   }

   public static Function forSupplier(Supplier supplier) {
      return new SupplierFunction(supplier);
   }

   private static enum ToStringFunction implements Function {
      INSTANCE;

      public String apply(Object o) {
         Preconditions.checkNotNull(o);
         return o.toString();
      }

      public String toString() {
         return "Functions.toStringFunction()";
      }

      // $FF: synthetic method
      private static ToStringFunction[] $values() {
         return new ToStringFunction[]{INSTANCE};
      }
   }

   private static enum IdentityFunction implements Function {
      INSTANCE;

      @CheckForNull
      public Object apply(@CheckForNull Object o) {
         return o;
      }

      public String toString() {
         return "Functions.identity()";
      }

      // $FF: synthetic method
      private static IdentityFunction[] $values() {
         return new IdentityFunction[]{INSTANCE};
      }
   }

   private static class FunctionForMapNoDefault implements Function, Serializable {
      final Map map;
      private static final long serialVersionUID = 0L;

      FunctionForMapNoDefault(Map map) {
         this.map = (Map)Preconditions.checkNotNull(map);
      }

      @ParametricNullness
      public Object apply(@ParametricNullness Object key) {
         V result = (V)this.map.get(key);
         Preconditions.checkArgument(result != null || this.map.containsKey(key), "Key '%s' not present in map", key);
         return NullnessCasts.uncheckedCastNullableTToT(result);
      }

      public boolean equals(@CheckForNull Object o) {
         if (o instanceof FunctionForMapNoDefault) {
            FunctionForMapNoDefault<?, ?> that = (FunctionForMapNoDefault)o;
            return this.map.equals(that.map);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.map.hashCode();
      }

      public String toString() {
         return "Functions.forMap(" + this.map + ")";
      }
   }

   private static class ForMapWithDefault implements Function, Serializable {
      final Map map;
      @ParametricNullness
      final Object defaultValue;
      private static final long serialVersionUID = 0L;

      ForMapWithDefault(Map map, @ParametricNullness Object defaultValue) {
         this.map = (Map)Preconditions.checkNotNull(map);
         this.defaultValue = defaultValue;
      }

      @ParametricNullness
      public Object apply(@ParametricNullness Object key) {
         V result = (V)this.map.get(key);
         return result == null && !this.map.containsKey(key) ? this.defaultValue : NullnessCasts.uncheckedCastNullableTToT(result);
      }

      public boolean equals(@CheckForNull Object o) {
         if (!(o instanceof ForMapWithDefault)) {
            return false;
         } else {
            ForMapWithDefault<?, ?> that = (ForMapWithDefault)o;
            return this.map.equals(that.map) && Objects.equal(this.defaultValue, that.defaultValue);
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.map, this.defaultValue);
      }

      public String toString() {
         return "Functions.forMap(" + this.map + ", defaultValue=" + this.defaultValue + ")";
      }
   }

   private static class FunctionComposition implements Function, Serializable {
      private final Function g;
      private final Function f;
      private static final long serialVersionUID = 0L;

      public FunctionComposition(Function g, Function f) {
         this.g = (Function)Preconditions.checkNotNull(g);
         this.f = (Function)Preconditions.checkNotNull(f);
      }

      @ParametricNullness
      public Object apply(@ParametricNullness Object a) {
         return this.g.apply(this.f.apply(a));
      }

      public boolean equals(@CheckForNull Object obj) {
         if (!(obj instanceof FunctionComposition)) {
            return false;
         } else {
            FunctionComposition<?, ?, ?> that = (FunctionComposition)obj;
            return this.f.equals(that.f) && this.g.equals(that.g);
         }
      }

      public int hashCode() {
         return this.f.hashCode() ^ this.g.hashCode();
      }

      public String toString() {
         return this.g + "(" + this.f + ")";
      }
   }

   private static class PredicateFunction implements Function, Serializable {
      private final Predicate predicate;
      private static final long serialVersionUID = 0L;

      private PredicateFunction(Predicate predicate) {
         this.predicate = (Predicate)Preconditions.checkNotNull(predicate);
      }

      public Boolean apply(@ParametricNullness Object t) {
         return this.predicate.apply(t);
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof PredicateFunction) {
            PredicateFunction<?> that = (PredicateFunction)obj;
            return this.predicate.equals(that.predicate);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.predicate.hashCode();
      }

      public String toString() {
         return "Functions.forPredicate(" + this.predicate + ")";
      }
   }

   private static class ConstantFunction implements Function, Serializable {
      @ParametricNullness
      private final Object value;
      private static final long serialVersionUID = 0L;

      public ConstantFunction(@ParametricNullness Object value) {
         this.value = value;
      }

      @ParametricNullness
      public Object apply(@CheckForNull Object from) {
         return this.value;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof ConstantFunction) {
            ConstantFunction<?> that = (ConstantFunction)obj;
            return Objects.equal(this.value, that.value);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.value == null ? 0 : this.value.hashCode();
      }

      public String toString() {
         return "Functions.constant(" + this.value + ")";
      }
   }

   private static class SupplierFunction implements Function, Serializable {
      private final Supplier supplier;
      private static final long serialVersionUID = 0L;

      private SupplierFunction(Supplier supplier) {
         this.supplier = (Supplier)Preconditions.checkNotNull(supplier);
      }

      @ParametricNullness
      public Object apply(@ParametricNullness Object input) {
         return this.supplier.get();
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof SupplierFunction) {
            SupplierFunction<?, ?> that = (SupplierFunction)obj;
            return this.supplier.equals(that.supplier);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.supplier.hashCode();
      }

      public String toString() {
         return "Functions.forSupplier(" + this.supplier + ")";
      }
   }
}
