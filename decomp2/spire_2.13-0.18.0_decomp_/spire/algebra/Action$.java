package spire.algebra;

public final class Action$ {
   public static final Action$ MODULE$ = new Action$();

   public Action apply(final Action G) {
      return G;
   }

   public Action additive(final AdditiveAction G) {
      return G.additive();
   }

   public Action multiplicative(final MultiplicativeAction G) {
      return G.multiplicative();
   }

   private Action$() {
   }
}
