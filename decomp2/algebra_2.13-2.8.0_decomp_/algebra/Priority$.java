package algebra;

public final class Priority$ implements FindPreferred {
   public static final Priority$ MODULE$ = new Priority$();

   static {
      FindFallback.$init$(MODULE$);
      FindPreferred.$init$(MODULE$);
   }

   public Priority preferred(final Object ev) {
      return FindPreferred.preferred$(this, ev);
   }

   public Priority fallback(final Object ev) {
      return FindFallback.fallback$(this, ev);
   }

   public Priority apply(final Priority ev) {
      return ev;
   }

   private Priority$() {
   }
}
