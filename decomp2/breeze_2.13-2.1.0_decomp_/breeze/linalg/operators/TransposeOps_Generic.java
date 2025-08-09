package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Transpose;
import breeze.linalg.support.CanTranspose;
import scala.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u00036\u0001\u0011\ra\u0007C\u0003R\u0001\u0011\r!K\u0001\u000bUe\u0006t7\u000f]8tK>\u00038oX$f]\u0016\u0014\u0018n\u0019\u0006\u0003\u000f!\t\u0011b\u001c9fe\u0006$xN]:\u000b\u0005%Q\u0011A\u00027j]\u0006dwMC\u0001\f\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003)Q\u0013\u0018M\\:q_N,w\n]:`\u0019><\bK]5p\u0003\u0019!\u0013N\\5uIQ\t!\u0004\u0005\u0002\u00107%\u0011A\u0004\u0005\u0002\u0005+:LG/\u0001\bdC:,f\u000e\u001e:b]N\u0004xn]3\u0016\u0005}aS#\u0001\u0011\u0011\t\u0005\"cEK\u0007\u0002E)\u00111\u0005C\u0001\bgV\u0004\bo\u001c:u\u0013\t)#E\u0001\u0007DC:$&/\u00198ta>\u001cX\rE\u0002(Q)j\u0011\u0001C\u0005\u0003S!\u0011\u0011\u0002\u0016:b]N\u0004xn]3\u0011\u0005-bC\u0002\u0001\u0003\u0006[\t\u0011\rA\f\u0002\u0002)F\u0011qF\r\t\u0003\u001fAJ!!\r\t\u0003\u000f9{G\u000f[5oOB\u0011qbM\u0005\u0003iA\u00111!\u00118z\u0003]!(/\u00198t)&lWm\u001d(pe6\fGN\u0012:p[\u0012{G/\u0006\u00038\t\u001aKEC\u0001\u001dL!\u0015IDHQ#I\u001d\t)\"(\u0003\u0002<\r\u0005Yq\n]'vY6\u000bGO]5y\u0013\tidHA\u0003J[Bd''\u0003\u0002@\u0001\n)QKR;oG*\u0011\u0011IC\u0001\bO\u0016tWM]5d!\r9\u0003f\u0011\t\u0003W\u0011#Q!L\u0002C\u00029\u0002\"a\u000b$\u0005\u000b\u001d\u001b!\u0019\u0001\u0018\u0003\u0003U\u0003\"aK%\u0005\u000b)\u001b!\u0019\u0001\u0018\u0003\u0003ICQ\u0001T\u0002A\u00045\u000b1\u0001Z8u!\u0015qEhQ#I\u001d\t)r*\u0003\u0002Q\r\u0005Qq\n]'vY&sg.\u001a:\u0002\u001fQ\u0014\u0018M\\:q_N,G+\u001a8t_J,BaU1e-R\u0011A\u000b\u0017\t\u0005C\u0011*v\u000b\u0005\u0002,-\u0012)Q\u0006\u0002b\u0001]A\u0019q\u0005K+\t\u000be#\u00019\u0001.\u0002\u0005\u00154\b\u0003B\b\\+vK!\u0001\u0018\t\u0003!\u0011bWm]:%G>dwN\u001c\u0013mKN\u001c\b\u0003B\u0014_A\u000eL!a\u0018\u0005\u0003\rQ+gn]8s!\tY\u0013\rB\u0003c\t\t\u0007aFA\u0001L!\tYC\rB\u0003f\t\t\u0007aFA\u0001W\u0001"
)
public interface TransposeOps_Generic extends TransposeOps_LowPrio {
   // $FF: synthetic method
   static CanTranspose canUntranspose$(final TransposeOps_Generic $this) {
      return $this.canUntranspose();
   }

   default CanTranspose canUntranspose() {
      return new CanTranspose() {
         public Object apply(final Transpose from) {
            return from.inner();
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 transTimesNormalFromDot$(final TransposeOps_Generic $this, final UFunc.UImpl2 dot) {
      return $this.transTimesNormalFromDot(dot);
   }

   default UFunc.UImpl2 transTimesNormalFromDot(final UFunc.UImpl2 dot) {
      return new UFunc.UImpl2(dot) {
         private final UFunc.UImpl2 dot$1;

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

         public Object apply(final Transpose v, final Object v2) {
            return this.dot$1.apply(v.inner(), v2);
         }

         public {
            this.dot$1 = dot$1;
         }
      };
   }

   // $FF: synthetic method
   static CanTranspose transposeTensor$(final TransposeOps_Generic $this, final .less.colon.less ev) {
      return $this.transposeTensor(ev);
   }

   default CanTranspose transposeTensor(final .less.colon.less ev) {
      return new CanTranspose() {
         public Transpose apply(final Object from) {
            return new Transpose(from);
         }
      };
   }

   static void $init$(final TransposeOps_Generic $this) {
   }
}
