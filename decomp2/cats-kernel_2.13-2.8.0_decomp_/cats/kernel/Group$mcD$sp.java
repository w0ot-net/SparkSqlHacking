package cats.kernel;

public interface Group$mcD$sp extends Group, Monoid$mcD$sp {
   // $FF: synthetic method
   static double remove$(final Group$mcD$sp $this, final double a, final double b) {
      return $this.remove(a, b);
   }

   default double remove(final double a, final double b) {
      return this.remove$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static double remove$mcD$sp$(final Group$mcD$sp $this, final double a, final double b) {
      return $this.remove$mcD$sp(a, b);
   }

   default double remove$mcD$sp(final double a, final double b) {
      return this.combine$mcD$sp(a, this.inverse$mcD$sp(b));
   }

   // $FF: synthetic method
   static double combineN$(final Group$mcD$sp $this, final double a, final int n) {
      return $this.combineN(a, n);
   }

   default double combineN(final double a, final int n) {
      return this.combineN$mcD$sp(a, n);
   }

   // $FF: synthetic method
   static double combineN$mcD$sp$(final Group$mcD$sp $this, final double a, final int n) {
      return $this.combineN$mcD$sp(a, n);
   }

   default double combineN$mcD$sp(final double a, final int n) {
      return n > 0 ? this.repeatedCombineN$mcD$sp(a, n) : (n == 0 ? this.empty$mcD$sp() : (n == Integer.MIN_VALUE ? this.combineN$mcD$sp(this.inverse$mcD$sp(this.combine$mcD$sp(a, a)), 1073741824) : this.repeatedCombineN$mcD$sp(this.inverse$mcD$sp(a), -n)));
   }
}
