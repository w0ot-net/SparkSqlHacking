package cats.kernel;

public interface Band$mcF$sp extends Band, Semigroup$mcF$sp {
   // $FF: synthetic method
   static float repeatedCombineN$(final Band$mcF$sp $this, final float a, final int n) {
      return $this.repeatedCombineN(a, n);
   }

   default float repeatedCombineN(final float a, final int n) {
      return this.repeatedCombineN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float repeatedCombineN$mcF$sp$(final Band$mcF$sp $this, final float a, final int n) {
      return $this.repeatedCombineN$mcF$sp(a, n);
   }

   default float repeatedCombineN$mcF$sp(final float a, final int n) {
      return a;
   }
}
