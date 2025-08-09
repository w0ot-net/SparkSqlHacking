package cats.kernel;

public interface Eq$mcZ$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final boolean x, final boolean y) {
      return this.neqv$mcZ$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcZ$sp$(final Eq$mcZ$sp $this, final boolean x, final boolean y) {
      return $this.neqv$mcZ$sp(x, y);
   }

   default boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return !this.eqv$mcZ$sp(x, y);
   }
}
