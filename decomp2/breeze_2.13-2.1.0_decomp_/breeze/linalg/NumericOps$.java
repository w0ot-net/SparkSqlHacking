package breeze.linalg;

import breeze.generic.UFunc;

public final class NumericOps$ {
   public static final NumericOps$ MODULE$ = new NumericOps$();

   public UFunc.InPlaceImpl2 binaryUpdateOpFromDVVOp(final UFunc.InPlaceImpl2 op) {
      return new UFunc.InPlaceImpl2(op) {
         private final UFunc.InPlaceImpl2 op$8;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final Object a, final Object b) {
            this.op$8.apply(DenseVector$.MODULE$.apply(a), b);
         }

         public {
            this.op$8 = op$8;
         }
      };
   }

   private NumericOps$() {
   }
}
