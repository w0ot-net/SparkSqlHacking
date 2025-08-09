package spire.algebra.partial;

import spire.algebra.RightAction;
import spire.util.Opt.;

public final class RightPartialAction$ {
   public static final RightPartialAction$ MODULE$ = new RightPartialAction$();

   public final RightPartialAction apply(final RightPartialAction G) {
      return G;
   }

   public RightPartialAction fromRightAction(final RightAction G) {
      return new RightPartialAction(G) {
         private final RightAction G$2;

         public boolean actrIsDefined(final Object p, final Object g) {
            return true;
         }

         public Object partialActr(final Object p, final Object g) {
            return .MODULE$.apply(this.G$2.actr(p, g));
         }

         public {
            this.G$2 = G$2;
            RightPartialAction.$init$(this);
         }
      };
   }

   private RightPartialAction$() {
   }
}
