package scala.reflect.internal.util;

public final class HashSet$ {
   public static final HashSet$ MODULE$ = new HashSet$();

   public HashSet apply(final int initialCapacity) {
      String apply_label = "No Label";
      return new HashSet(apply_label, initialCapacity);
   }

   public HashSet apply(final String label, final int initialCapacity) {
      return new HashSet(label, initialCapacity);
   }

   private HashSet$() {
   }
}
