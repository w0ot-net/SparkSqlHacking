package cats.kernel;

public interface Eq$mcS$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcS$sp $this, final short x, final short y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final short x, final short y) {
      return this.neqv$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcS$sp$(final Eq$mcS$sp $this, final short x, final short y) {
      return $this.neqv$mcS$sp(x, y);
   }

   default boolean neqv$mcS$sp(final short x, final short y) {
      return !this.eqv$mcS$sp(x, y);
   }
}
