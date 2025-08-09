package cats.kernel;

public interface Eq$mcJ$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcJ$sp $this, final long x, final long y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final long x, final long y) {
      return this.neqv$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcJ$sp$(final Eq$mcJ$sp $this, final long x, final long y) {
      return $this.neqv$mcJ$sp(x, y);
   }

   default boolean neqv$mcJ$sp(final long x, final long y) {
      return !this.eqv$mcJ$sp(x, y);
   }
}
