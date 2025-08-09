package cats.kernel;

public interface Band$mcJ$sp extends Band, Semigroup$mcJ$sp {
   // $FF: synthetic method
   static long repeatedCombineN$(final Band$mcJ$sp $this, final long a, final int n) {
      return $this.repeatedCombineN(a, n);
   }

   default long repeatedCombineN(final long a, final int n) {
      return this.repeatedCombineN$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long repeatedCombineN$mcJ$sp$(final Band$mcJ$sp $this, final long a, final int n) {
      return $this.repeatedCombineN$mcJ$sp(a, n);
   }

   default long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return a;
   }
}
