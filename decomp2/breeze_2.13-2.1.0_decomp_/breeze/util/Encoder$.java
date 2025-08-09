package breeze.util;

public final class Encoder$ {
   public static final Encoder$ MODULE$ = new Encoder$();

   public Encoder fromIndex(final Index ind) {
      return new Encoder.SimpleEncoder(ind);
   }

   private Encoder$() {
   }
}
