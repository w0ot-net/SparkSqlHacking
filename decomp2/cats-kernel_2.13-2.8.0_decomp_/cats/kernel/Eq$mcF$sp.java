package cats.kernel;

public interface Eq$mcF$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcF$sp $this, final float x, final float y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final float x, final float y) {
      return this.neqv$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcF$sp$(final Eq$mcF$sp $this, final float x, final float y) {
      return $this.neqv$mcF$sp(x, y);
   }

   default boolean neqv$mcF$sp(final float x, final float y) {
      return !this.eqv$mcF$sp(x, y);
   }
}
