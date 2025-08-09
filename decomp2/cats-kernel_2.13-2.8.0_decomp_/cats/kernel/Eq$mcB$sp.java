package cats.kernel;

public interface Eq$mcB$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcB$sp $this, final byte x, final byte y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final byte x, final byte y) {
      return this.neqv$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcB$sp$(final Eq$mcB$sp $this, final byte x, final byte y) {
      return $this.neqv$mcB$sp(x, y);
   }

   default boolean neqv$mcB$sp(final byte x, final byte y) {
      return !this.eqv$mcB$sp(x, y);
   }
}
