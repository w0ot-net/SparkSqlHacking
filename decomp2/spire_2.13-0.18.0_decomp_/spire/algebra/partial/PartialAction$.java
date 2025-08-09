package spire.algebra.partial;

import spire.algebra.Action;
import spire.util.Opt.;

public final class PartialAction$ {
   public static final PartialAction$ MODULE$ = new PartialAction$();

   public final PartialAction apply(final PartialAction G) {
      return G;
   }

   public PartialAction fromAction(final Action G) {
      return new PartialAction(G) {
         private final Action G$3;

         public boolean actlIsDefined(final Object g, final Object p) {
            return true;
         }

         public Object partialActl(final Object g, final Object p) {
            return .MODULE$.apply(this.G$3.actl(g, p));
         }

         public boolean actrIsDefined(final Object p, final Object g) {
            return true;
         }

         public Object partialActr(final Object p, final Object g) {
            return .MODULE$.apply(this.G$3.actr(p, g));
         }

         public {
            this.G$3 = G$3;
            LeftPartialAction.$init$(this);
            RightPartialAction.$init$(this);
         }
      };
   }

   private PartialAction$() {
   }
}
