package spire.algebra.partial;

import spire.algebra.LeftAction;
import spire.util.Opt.;

public final class LeftPartialAction$ {
   public static final LeftPartialAction$ MODULE$ = new LeftPartialAction$();

   public final LeftPartialAction apply(final LeftPartialAction G) {
      return G;
   }

   public LeftPartialAction fromLeftAction(final LeftAction G) {
      return new LeftPartialAction(G) {
         private final LeftAction G$1;

         public boolean actlIsDefined(final Object g, final Object p) {
            return true;
         }

         public Object partialActl(final Object g, final Object p) {
            return .MODULE$.apply(this.G$1.actl(g, p));
         }

         public {
            this.G$1 = G$1;
            LeftPartialAction.$init$(this);
         }
      };
   }

   private LeftPartialAction$() {
   }
}
