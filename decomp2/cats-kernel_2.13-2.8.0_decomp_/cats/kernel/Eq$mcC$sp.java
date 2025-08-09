package cats.kernel;

public interface Eq$mcC$sp extends Eq {
   // $FF: synthetic method
   static boolean neqv$(final Eq$mcC$sp $this, final char x, final char y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final char x, final char y) {
      return this.neqv$mcC$sp(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcC$sp$(final Eq$mcC$sp $this, final char x, final char y) {
      return $this.neqv$mcC$sp(x, y);
   }

   default boolean neqv$mcC$sp(final char x, final char y) {
      return !this.eqv$mcC$sp(x, y);
   }
}
