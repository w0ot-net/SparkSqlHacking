package scala.collection;

public final class IterableOnce$ {
   public static final IterableOnce$ MODULE$ = new IterableOnce$();

   public IterableOnce iterableOnceExtensionMethods(final IterableOnce it) {
      return it;
   }

   public int elemsToCopyToArray(final int srcLen, final int destLen, final int start, final int len) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      var10000 = scala.math.package$.MODULE$;
      var10000 = scala.math.package$.MODULE$;
      return Math.max(Math.min(Math.min(len, srcLen), destLen - start), 0);
   }

   public int copyElemsToArray(final IterableOnce elems, final Object xs, final int start, final int len) {
      return elems instanceof Iterable ? ((Iterable)elems).copyToArray(xs, start, len) : elems.iterator().copyToArray(xs, start, len);
   }

   public int copyElemsToArray$default$3() {
      return 0;
   }

   public int copyElemsToArray$default$4() {
      return Integer.MAX_VALUE;
   }

   private IterableOnce$() {
   }
}
