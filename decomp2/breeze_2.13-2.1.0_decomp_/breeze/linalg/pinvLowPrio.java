package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTranspose;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0011B\u0018\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\fa&tg\u000fT8x!JLwN\u0003\u0002\u0006\r\u00051A.\u001b8bY\u001eT\u0011aB\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0002CA\u0006\u0014\u0013\t!BB\u0001\u0003V]&$\u0018!G5na24%o\\7Ue\u0006t7\u000f]8tK\u0006sGmU8mm\u0016,Ra\u0006\u0012A%2\"r\u0001\u0007\u00188\u0005\u001a#\u0006\f\u0005\u0003\u001a5\u0001ZS\"\u0001\u0001\n\u0005ma\"\u0001B%na2L!!\b\u0010\u0003\u000bU3UO\\2\u000b\u0005}1\u0011aB4f]\u0016\u0014\u0018n\u0019\t\u0003C\tb\u0001\u0001B\u0003$\u0005\t\u0007AEA\u0001U#\t)\u0003\u0006\u0005\u0002\fM%\u0011q\u0005\u0004\u0002\b\u001d>$\b.\u001b8h!\tY\u0011&\u0003\u0002+\u0019\t\u0019\u0011I\\=\u0011\u0005\u0005bC!B\u0017\u0003\u0005\u0004!#A\u0002*fgVdG\u000fC\u00030\u0005\u0001\u000f\u0001'\u0001\u0005ok6,'/[2U!\u0011Y\u0011\u0007I\u001a\n\u0005Ib!!\u0003$v]\u000e$\u0018n\u001c82!\r!T\u0007I\u0007\u0002\t%\u0011a\u0007\u0002\u0002\u000b\u001dVlWM]5d\u001fB\u001c\b\"\u0002\u001d\u0003\u0001\bI\u0014!\u0002;sC:\u001c\b\u0003\u0002\u001e>A}j\u0011a\u000f\u0006\u0003y\u0011\tqa];qa>\u0014H/\u0003\u0002?w\ta1)\u00198Ue\u0006t7\u000f]8tKB\u0011\u0011\u0005\u0011\u0003\u0006\u0003\n\u0011\r\u0001\n\u0002\u0007)J\fgn\u001d+\t\u000b\r\u0013\u00019\u0001#\u0002\u00199,X.\u001a:jGR\u0013\u0018M\\:\u0011\t-\tt(\u0012\t\u0004iUz\u0004\"B$\u0003\u0001\bA\u0015aA7vYB)\u0011jT !#:\u0011!*T\u0007\u0002\u0017*\u0011A\nB\u0001\n_B,'/\u0019;peNL!AT&\u0002\u0017=\u0003X*\u001e7NCR\u0014\u0018\u000e_\u0005\u0003!r\u0011Q!S7qYJ\u0002\"!\t*\u0005\u000bM\u0013!\u0019\u0001\u0013\u0003\r5+HNU3t\u0011\u0015)&\u0001q\u0001W\u00035qW/\\3sS\u000elU\u000f\u001c*fgB!1\"M)X!\r!T'\u0015\u0005\u00063\n\u0001\u001dAW\u0001\u0006g>dg/\u001a\t\u00067>\u000bvh\u000b\b\u0003\u0015rK!!X&\u0002\u001f=\u00038k\u001c7wK6\u000bGO]5y\u0005ft!\u0001N0\n\u0005\u0001$\u0011\u0001\u00029j]Z\u0004"
)
public interface pinvLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl implFromTransposeAndSolve$(final pinvLowPrio $this, final Function1 numericT, final CanTranspose trans, final Function1 numericTrans, final UFunc.UImpl2 mul, final Function1 numericMulRes, final UFunc.UImpl2 solve) {
      return $this.implFromTransposeAndSolve(numericT, trans, numericTrans, mul, numericMulRes, solve);
   }

   default UFunc.UImpl implFromTransposeAndSolve(final Function1 numericT, final CanTranspose trans, final Function1 numericTrans, final UFunc.UImpl2 mul, final Function1 numericMulRes, final UFunc.UImpl2 solve) {
      return new UFunc.UImpl(numericMulRes, numericTrans, numericT, trans, mul, solve) {
         private final Function1 numericMulRes$1;
         private final Function1 numericTrans$1;
         private final Function1 numericT$1;
         private final CanTranspose trans$1;
         private final UFunc.UImpl2 mul$1;
         private final UFunc.UImpl2 solve$1;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Object apply(final Object X) {
            return ((ImmutableNumericOps)this.numericMulRes$1.apply(((ImmutableNumericOps)this.numericTrans$1.apply(((ImmutableNumericOps)this.numericT$1.apply(X)).t(this.trans$1))).$times(X, this.mul$1))).$bslash(((ImmutableNumericOps)this.numericT$1.apply(X)).t(this.trans$1), this.solve$1);
         }

         public {
            this.numericMulRes$1 = numericMulRes$1;
            this.numericTrans$1 = numericTrans$1;
            this.numericT$1 = numericT$1;
            this.trans$1 = trans$1;
            this.mul$1 = mul$1;
            this.solve$1 = solve$1;
         }
      };
   }

   static void $init$(final pinvLowPrio $this) {
   }
}
