package org.sparkproject.guava.base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.annotations.VisibleForTesting;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Suppliers {
   private Suppliers() {
   }

   public static Supplier compose(Function function, Supplier supplier) {
      return new SupplierComposition(function, supplier);
   }

   public static Supplier memoize(Supplier delegate) {
      if (!(delegate instanceof NonSerializableMemoizingSupplier) && !(delegate instanceof MemoizingSupplier)) {
         return (Supplier)(delegate instanceof Serializable ? new MemoizingSupplier(delegate) : new NonSerializableMemoizingSupplier(delegate));
      } else {
         return delegate;
      }
   }

   public static Supplier memoizeWithExpiration(Supplier delegate, long duration, TimeUnit unit) {
      Preconditions.checkNotNull(delegate);
      Preconditions.checkArgument(duration > 0L, "duration (%s %s) must be > 0", duration, unit);
      return new ExpiringMemoizingSupplier(delegate, unit.toNanos(duration));
   }

   @J2ktIncompatible
   @GwtIncompatible
   @IgnoreJRERequirement
   public static Supplier memoizeWithExpiration(Supplier delegate, Duration duration) {
      Preconditions.checkNotNull(delegate);
      Preconditions.checkArgument(!duration.isNegative() && !duration.isZero(), "duration (%s) must be > 0", (Object)duration);
      return new ExpiringMemoizingSupplier(delegate, Internal.toNanosSaturated(duration));
   }

   public static Supplier ofInstance(@ParametricNullness Object instance) {
      return new SupplierOfInstance(instance);
   }

   @J2ktIncompatible
   public static Supplier synchronizedSupplier(Supplier delegate) {
      return new ThreadSafeSupplier(delegate);
   }

   public static Function supplierFunction() {
      SupplierFunction<T> sf = Suppliers.SupplierFunctionImpl.INSTANCE;
      return sf;
   }

   private static class SupplierComposition implements Supplier, Serializable {
      final Function function;
      final Supplier supplier;
      private static final long serialVersionUID = 0L;

      SupplierComposition(Function function, Supplier supplier) {
         this.function = (Function)Preconditions.checkNotNull(function);
         this.supplier = (Supplier)Preconditions.checkNotNull(supplier);
      }

      @ParametricNullness
      public Object get() {
         return this.function.apply(this.supplier.get());
      }

      public boolean equals(@CheckForNull Object obj) {
         if (!(obj instanceof SupplierComposition)) {
            return false;
         } else {
            SupplierComposition<?, ?> that = (SupplierComposition)obj;
            return this.function.equals(that.function) && this.supplier.equals(that.supplier);
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.function, this.supplier);
      }

      public String toString() {
         return "Suppliers.compose(" + this.function + ", " + this.supplier + ")";
      }
   }

   @VisibleForTesting
   static class MemoizingSupplier implements Supplier, Serializable {
      private transient Object lock = new Object();
      final Supplier delegate;
      transient volatile boolean initialized;
      @CheckForNull
      transient Object value;
      private static final long serialVersionUID = 0L;

      MemoizingSupplier(Supplier delegate) {
         this.delegate = (Supplier)Preconditions.checkNotNull(delegate);
      }

      @ParametricNullness
      public Object get() {
         if (!this.initialized) {
            synchronized(this.lock) {
               if (!this.initialized) {
                  T t = (T)this.delegate.get();
                  this.value = t;
                  this.initialized = true;
                  return t;
               }
            }
         }

         return NullnessCasts.uncheckedCastNullableTToT(this.value);
      }

      public String toString() {
         return "Suppliers.memoize(" + (this.initialized ? "<supplier that returned " + this.value + ">" : this.delegate) + ")";
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         this.lock = new Object();
      }
   }

   @VisibleForTesting
   static class NonSerializableMemoizingSupplier implements Supplier {
      private final Object lock = new Object();
      private static final Supplier SUCCESSFULLY_COMPUTED = () -> {
         throw new IllegalStateException();
      };
      private volatile Supplier delegate;
      @CheckForNull
      private Object value;

      NonSerializableMemoizingSupplier(Supplier delegate) {
         this.delegate = (Supplier)Preconditions.checkNotNull(delegate);
      }

      @ParametricNullness
      public Object get() {
         if (this.delegate != SUCCESSFULLY_COMPUTED) {
            synchronized(this.lock) {
               if (this.delegate != SUCCESSFULLY_COMPUTED) {
                  T t = (T)this.delegate.get();
                  this.value = t;
                  this.delegate = SUCCESSFULLY_COMPUTED;
                  return t;
               }
            }
         }

         return NullnessCasts.uncheckedCastNullableTToT(this.value);
      }

      public String toString() {
         Supplier<T> delegate = this.delegate;
         return "Suppliers.memoize(" + (delegate == SUCCESSFULLY_COMPUTED ? "<supplier that returned " + this.value + ">" : delegate) + ")";
      }
   }

   @VisibleForTesting
   static class ExpiringMemoizingSupplier implements Supplier, Serializable {
      private transient Object lock = new Object();
      final Supplier delegate;
      final long durationNanos;
      @CheckForNull
      transient volatile Object value;
      transient volatile long expirationNanos;
      private static final long serialVersionUID = 0L;

      ExpiringMemoizingSupplier(Supplier delegate, long durationNanos) {
         this.delegate = delegate;
         this.durationNanos = durationNanos;
      }

      @ParametricNullness
      public Object get() {
         long nanos = this.expirationNanos;
         long now = System.nanoTime();
         if (nanos == 0L || now - nanos >= 0L) {
            synchronized(this.lock) {
               if (nanos == this.expirationNanos) {
                  T t = (T)this.delegate.get();
                  this.value = t;
                  nanos = now + this.durationNanos;
                  this.expirationNanos = nanos == 0L ? 1L : nanos;
                  return t;
               }
            }
         }

         return NullnessCasts.uncheckedCastNullableTToT(this.value);
      }

      public String toString() {
         return "Suppliers.memoizeWithExpiration(" + this.delegate + ", " + this.durationNanos + ", NANOS)";
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         this.lock = new Object();
      }
   }

   private static class SupplierOfInstance implements Supplier, Serializable {
      @ParametricNullness
      final Object instance;
      private static final long serialVersionUID = 0L;

      SupplierOfInstance(@ParametricNullness Object instance) {
         this.instance = instance;
      }

      @ParametricNullness
      public Object get() {
         return this.instance;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj instanceof SupplierOfInstance) {
            SupplierOfInstance<?> that = (SupplierOfInstance)obj;
            return Objects.equal(this.instance, that.instance);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.instance);
      }

      public String toString() {
         return "Suppliers.ofInstance(" + this.instance + ")";
      }
   }

   @J2ktIncompatible
   private static class ThreadSafeSupplier implements Supplier, Serializable {
      final Supplier delegate;
      private static final long serialVersionUID = 0L;

      ThreadSafeSupplier(Supplier delegate) {
         this.delegate = (Supplier)Preconditions.checkNotNull(delegate);
      }

      @ParametricNullness
      public Object get() {
         synchronized(this.delegate) {
            return this.delegate.get();
         }
      }

      public String toString() {
         return "Suppliers.synchronizedSupplier(" + this.delegate + ")";
      }
   }

   private static enum SupplierFunctionImpl implements SupplierFunction {
      INSTANCE;

      @CheckForNull
      public Object apply(Supplier input) {
         return input.get();
      }

      public String toString() {
         return "Suppliers.supplierFunction()";
      }

      // $FF: synthetic method
      private static SupplierFunctionImpl[] $values() {
         return new SupplierFunctionImpl[]{INSTANCE};
      }
   }

   private interface SupplierFunction extends Function {
   }
}
