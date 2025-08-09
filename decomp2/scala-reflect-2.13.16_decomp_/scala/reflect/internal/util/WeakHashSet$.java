package scala.reflect.internal.util;

public final class WeakHashSet$ {
   public static final WeakHashSet$ MODULE$ = new WeakHashSet$();
   private static final int defaultInitialCapacity = 16;
   private static final double defaultLoadFactor = (double)0.75F;

   public int defaultInitialCapacity() {
      return defaultInitialCapacity;
   }

   public double defaultLoadFactor() {
      return defaultLoadFactor;
   }

   public WeakHashSet apply(final int initialCapacity, final double loadFactor) {
      return new WeakHashSet(initialCapacity, this.defaultLoadFactor());
   }

   public int apply$default$1() {
      return this.defaultInitialCapacity();
   }

   public double apply$default$2() {
      return this.defaultLoadFactor();
   }

   public WeakHashSet empty() {
      return new WeakHashSet();
   }

   private WeakHashSet$() {
   }
}
