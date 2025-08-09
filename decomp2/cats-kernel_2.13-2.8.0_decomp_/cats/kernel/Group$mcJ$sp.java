package cats.kernel;

public interface Group$mcJ$sp extends Group, Monoid$mcJ$sp {
   // $FF: synthetic method
   static long remove$(final Group$mcJ$sp $this, final long a, final long b) {
      return $this.remove(a, b);
   }

   default long remove(final long a, final long b) {
      return this.remove$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static long remove$mcJ$sp$(final Group$mcJ$sp $this, final long a, final long b) {
      return $this.remove$mcJ$sp(a, b);
   }

   default long remove$mcJ$sp(final long a, final long b) {
      return this.combine$mcJ$sp(a, this.inverse$mcJ$sp(b));
   }

   // $FF: synthetic method
   static long combineN$(final Group$mcJ$sp $this, final long a, final int n) {
      return $this.combineN(a, n);
   }

   default long combineN(final long a, final int n) {
      return this.combineN$mcJ$sp(a, n);
   }

   // $FF: synthetic method
   static long combineN$mcJ$sp$(final Group$mcJ$sp $this, final long a, final int n) {
      return $this.combineN$mcJ$sp(a, n);
   }

   default long combineN$mcJ$sp(final long a, final int n) {
      return n > 0 ? this.repeatedCombineN$mcJ$sp(a, n) : (n == 0 ? this.empty$mcJ$sp() : (n == Integer.MIN_VALUE ? this.combineN$mcJ$sp(this.inverse$mcJ$sp(this.combine$mcJ$sp(a, a)), 1073741824) : this.repeatedCombineN$mcJ$sp(this.inverse$mcJ$sp(a), -n)));
   }
}
