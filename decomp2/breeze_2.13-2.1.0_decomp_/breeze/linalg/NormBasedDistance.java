package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003H\u0001\u0019E\u0001JA\tO_Jl')Y:fI\u0012K7\u000f^1oG\u0016T!AB\u0004\u0002\r1Lg.\u00197h\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001Y\u0011\u0003\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0003%Ui\u0011a\u0005\u0006\u0003)\u001d\tqaZ3oKJL7-\u0003\u0002\u0017'\t)QKR;oG\u00061A%\u001b8ji\u0012\"\u0012!\u0007\t\u0003\u0019iI!aG\u0007\u0003\tUs\u0017\u000e^\u0001\u0017I&\u001cH/\u00198dK\u001a\u0013x.\u001c(pe6\fe\u000eZ*vEV!a$J\u0018?)\ryB\u0007\u0011\t\u0006A\u0005\u001ac&M\u0007\u0002\u0001%\u0011!%\u0006\u0002\u0006\u00136\u0004HN\r\t\u0003I\u0015b\u0001\u0001B\u0003'\u0005\t\u0007qEA\u0001U#\tA3\u0006\u0005\u0002\rS%\u0011!&\u0004\u0002\b\u001d>$\b.\u001b8h!\taA&\u0003\u0002.\u001b\t\u0019\u0011I\\=\u0011\u0005\u0011zC!\u0002\u0019\u0003\u0005\u00049#!A+\u0011\u00051\u0011\u0014BA\u001a\u000e\u0005\u0019!u.\u001e2mK\")QG\u0001a\u0002m\u000591/\u001e2J[Bd\u0007#B\u001c\"G9jdB\u0001\u001d<\u001b\u0005I$B\u0001\u001e\u0006\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0002=s\u0005)q\n]*vEB\u0011AE\u0010\u0003\u0006\u007f\t\u0011\ra\n\u0002\u0002-\")\u0011I\u0001a\u0002\u0005\u0006Aan\u001c:n\u00136\u0004H\u000eE\u0003DCu\n\u0014G\u0004\u0002E\u000b6\tQ!\u0003\u0002G\u000b\u0005!an\u001c:n\u00031qwN]7D_:\u001cH/\u00198u+\u0005\t\u0004"
)
public interface NormBasedDistance extends UFunc {
   // $FF: synthetic method
   static UFunc.UImpl2 distanceFromNormAndSub$(final NormBasedDistance $this, final UFunc.UImpl2 subImpl, final UFunc.UImpl2 normImpl) {
      return $this.distanceFromNormAndSub(subImpl, normImpl);
   }

   default UFunc.UImpl2 distanceFromNormAndSub(final UFunc.UImpl2 subImpl, final UFunc.UImpl2 normImpl) {
      return new UFunc.UImpl2(subImpl, normImpl) {
         // $FF: synthetic field
         private final NormBasedDistance $outer;
         private final UFunc.UImpl2 subImpl$1;
         private final UFunc.UImpl2 normImpl$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public double apply(final Object v, final Object v2) {
            return BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(this.subImpl$1.apply(v, v2), BoxesRunTime.boxToDouble(this.$outer.normConstant()), this.normImpl$1));
         }

         public {
            if (NormBasedDistance.this == null) {
               throw null;
            } else {
               this.$outer = NormBasedDistance.this;
               this.subImpl$1 = subImpl$1;
               this.normImpl$1 = normImpl$1;
            }
         }
      };
   }

   double normConstant();

   static void $init$(final NormBasedDistance $this) {
   }
}
