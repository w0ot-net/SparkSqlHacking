package cats.kernel;

public interface Group$mcI$sp extends Group, Monoid$mcI$sp {
   // $FF: synthetic method
   static int remove$(final Group$mcI$sp $this, final int a, final int b) {
      return $this.remove(a, b);
   }

   default int remove(final int a, final int b) {
      return this.remove$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static int remove$mcI$sp$(final Group$mcI$sp $this, final int a, final int b) {
      return $this.remove$mcI$sp(a, b);
   }

   default int remove$mcI$sp(final int a, final int b) {
      return this.combine$mcI$sp(a, this.inverse$mcI$sp(b));
   }

   // $FF: synthetic method
   static int combineN$(final Group$mcI$sp $this, final int a, final int n) {
      return $this.combineN(a, n);
   }

   default int combineN(final int a, final int n) {
      return this.combineN$mcI$sp(a, n);
   }

   // $FF: synthetic method
   static int combineN$mcI$sp$(final Group$mcI$sp $this, final int a, final int n) {
      return $this.combineN$mcI$sp(a, n);
   }

   default int combineN$mcI$sp(final int a, final int n) {
      return n > 0 ? this.repeatedCombineN$mcI$sp(a, n) : (n == 0 ? this.empty$mcI$sp() : (n == Integer.MIN_VALUE ? this.combineN$mcI$sp(this.inverse$mcI$sp(this.combine$mcI$sp(a, a)), 1073741824) : this.repeatedCombineN$mcI$sp(this.inverse$mcI$sp(a), -n)));
   }
}
