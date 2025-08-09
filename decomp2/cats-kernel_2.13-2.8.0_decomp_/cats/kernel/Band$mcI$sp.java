package cats.kernel;

public interface Band$mcI$sp extends Band, Semigroup$mcI$sp {
   // $FF: synthetic method
   static int repeatedCombineN$(final Band$mcI$sp $this, final int a, final int n) {
      return $this.repeatedCombineN(a, n);
   }

   default int repeatedCombineN(final int a, final int n) {
      return this.repeatedCombineN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int repeatedCombineN$mcI$sp$(final Band$mcI$sp $this, final int a, final int n) {
      return $this.repeatedCombineN$mcI$sp(a, n);
   }

   default int repeatedCombineN$mcI$sp(final int a, final int n) {
      return a;
   }
}
