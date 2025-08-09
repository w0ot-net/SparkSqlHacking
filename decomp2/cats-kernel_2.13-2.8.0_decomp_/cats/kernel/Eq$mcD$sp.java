package cats.kernel;

public interface Eq$mcD$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcD$sp $this, final double x, final double y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final double x, final double y) {
      return this.neqv$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcD$sp$(final Eq$mcD$sp $this, final double x, final double y) {
      return $this.neqv$mcD$sp(x, y);
   }

   default boolean neqv$mcD$sp(final double x, final double y) {
      return !this.eqv$mcD$sp(x, y);
   }
}
