package cats.kernel;

public interface Group$mcF$sp extends Group, Monoid$mcF$sp {
   // $FF: synthetic method
   static float remove$(final Group$mcF$sp $this, final float a, final float b) {
      return $this.remove(a, b);
   }

   default float remove(final float a, final float b) {
      return this.remove$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static float remove$mcF$sp$(final Group$mcF$sp $this, final float a, final float b) {
      return $this.remove$mcF$sp(a, b);
   }

   default float remove$mcF$sp(final float a, final float b) {
      return this.combine$mcF$sp(a, this.inverse$mcF$sp(b));
   }

   // $FF: synthetic method
   static float combineN$(final Group$mcF$sp $this, final float a, final int n) {
      return $this.combineN(a, n);
   }

   default float combineN(final float a, final int n) {
      return this.combineN$mcF$sp(a, n);
   }

   // $FF: synthetic method
   static float combineN$mcF$sp$(final Group$mcF$sp $this, final float a, final int n) {
      return $this.combineN$mcF$sp(a, n);
   }

   default float combineN$mcF$sp(final float a, final int n) {
      return n > 0 ? this.repeatedCombineN$mcF$sp(a, n) : (n == 0 ? this.empty$mcF$sp() : (n == Integer.MIN_VALUE ? this.combineN$mcF$sp(this.inverse$mcF$sp(this.combine$mcF$sp(a, a)), 1073741824) : this.repeatedCombineN$mcF$sp(this.inverse$mcF$sp(a), -n)));
   }
}
