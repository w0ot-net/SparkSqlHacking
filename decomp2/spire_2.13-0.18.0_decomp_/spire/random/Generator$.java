package spire.random;

public final class Generator$ {
   public static final Generator$ MODULE$ = new Generator$();
   private static final Generator rng;

   static {
      rng = GlobalRng$.MODULE$;
   }

   public Generator rng() {
      return rng;
   }

   private Generator$() {
   }
}
