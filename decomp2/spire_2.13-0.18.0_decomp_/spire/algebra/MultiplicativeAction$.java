package spire.algebra;

import algebra.ring.AdditiveGroup;
import algebra.ring.Signed;
import algebra.ring.Signed.Positive.;
import scala.MatchError;

public final class MultiplicativeAction$ {
   public static final MultiplicativeAction$ MODULE$ = new MultiplicativeAction$();

   public MultiplicativeAction SignAction(final AdditiveGroup A) {
      return new MultiplicativeAction(A) {
         private final AdditiveGroup A$1;

         public Action multiplicative() {
            return MultiplicativeAction.multiplicative$(this);
         }

         public Action multiplicative$mcI$sp() {
            return MultiplicativeAction.multiplicative$mcI$sp$(this);
         }

         public int gtimesl$mcI$sp(final Object g, final int p) {
            return MultiplicativeAction.gtimesl$mcI$sp$(this, g, p);
         }

         public int gtimesr$mcI$sp(final int p, final Object g) {
            return MultiplicativeAction.gtimesr$mcI$sp$(this, p, g);
         }

         public Object gtimesl(final Signed.Sign s, final Object a) {
            Object var3;
            if (.MODULE$.equals(s)) {
               var3 = a;
            } else if (algebra.ring.Signed.Negative..MODULE$.equals(s)) {
               var3 = this.A$1.negate(a);
            } else {
               if (!algebra.ring.Signed.Zero..MODULE$.equals(s)) {
                  throw new MatchError(s);
               }

               var3 = this.A$1.zero();
            }

            return var3;
         }

         public Object gtimesr(final Object a, final Signed.Sign s) {
            return this.gtimesl(s, a);
         }

         public {
            this.A$1 = A$1;
            MultiplicativeAction.$init$(this);
         }
      };
   }

   private MultiplicativeAction$() {
   }
}
