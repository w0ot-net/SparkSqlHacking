package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005-3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0012\"\u0012\u0005\u0006-\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0002\u0017gF,\u0018M]3e\t&\u001cH/\u00198dK2{w\u000f\u0015:j_*\u0011QAB\u0001\u0007Y&t\u0017\r\\4\u000b\u0003\u001d\taA\u0019:fKj,7\u0001A\n\u0004\u0001)\u0001\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012)5\t!C\u0003\u0002\u0014\r\u00059q-\u001a8fe&\u001c\u0017BA\u000b\u0013\u0005\u0015)f)\u001e8d\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0004\u0005\u0002\f3%\u0011!\u0004\u0004\u0002\u0005+:LG/A\u000beSN$\u0018M\\2f\rJ|W\u000eR8u\u0003:$7+\u001e2\u0016\tu!c&\u0010\u000b\u0004=Mz\u0004#B\u0010!E5\u0002T\"\u0001\u0001\n\u0005\u0005\"\"!B%na2\u0014\u0004CA\u0012%\u0019\u0001!Q!\n\u0002C\u0002\u0019\u0012\u0011\u0001V\t\u0003O)\u0002\"a\u0003\u0015\n\u0005%b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017-J!\u0001\f\u0007\u0003\u0007\u0005s\u0017\u0010\u0005\u0002$]\u0011)qF\u0001b\u0001M\t\tQ\u000b\u0005\u0002\fc%\u0011!\u0007\u0004\u0002\u0007\t>,(\r\\3\t\u000bQ\u0012\u00019A\u001b\u0002\u000fM,(-S7qYB)a\u0007\t\u0012.y9\u0011qGO\u0007\u0002q)\u0011\u0011\bB\u0001\n_B,'/\u0019;peNL!a\u000f\u001d\u0002\u000b=\u00038+\u001e2\u0011\u0005\rjD!\u0002 \u0003\u0005\u00041#!\u0001,\t\u000b\u0001\u0013\u00019A!\u0002\u000f\u0011|G/S7qYB)!\t\t\u001f=a9\u0011qgQ\u0005\u0003\tb\n!b\u00149Nk2LeN\\3s\u001d\t1u)D\u0001\u0005\u0013\tAE!A\btcV\f'/\u001a3ESN$\u0018M\\2fS\t\u0001!J\u0003\u0002I\t\u0001"
)
public interface squaredDistanceLowPrio extends UFunc {
   // $FF: synthetic method
   static UFunc.UImpl2 distanceFromDotAndSub$(final squaredDistanceLowPrio $this, final UFunc.UImpl2 subImpl, final UFunc.UImpl2 dotImpl) {
      return $this.distanceFromDotAndSub(subImpl, dotImpl);
   }

   default UFunc.UImpl2 distanceFromDotAndSub(final UFunc.UImpl2 subImpl, final UFunc.UImpl2 dotImpl) {
      return new UFunc.UImpl2(subImpl, dotImpl) {
         private final UFunc.UImpl2 subImpl$1;
         private final UFunc.UImpl2 dotImpl$1;

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
            Object diff = this.subImpl$1.apply(v, v2);
            return BoxesRunTime.unboxToDouble(this.dotImpl$1.apply(diff, diff));
         }

         public {
            this.subImpl$1 = subImpl$1;
            this.dotImpl$1 = dotImpl$1;
         }
      };
   }

   static void $init$(final squaredDistanceLowPrio $this) {
   }
}
