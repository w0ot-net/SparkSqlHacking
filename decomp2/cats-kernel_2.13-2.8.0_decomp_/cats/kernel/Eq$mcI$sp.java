package cats.kernel;

public interface Eq$mcI$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcI$sp $this, final int x, final int y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final int x, final int y) {
      return this.neqv$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcI$sp$(final Eq$mcI$sp $this, final int x, final int y) {
      return $this.neqv$mcI$sp(x, y);
   }

   default boolean neqv$mcI$sp(final int x, final int y) {
      return !this.eqv$mcI$sp(x, y);
   }
}
