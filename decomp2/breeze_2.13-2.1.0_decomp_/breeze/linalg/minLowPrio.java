package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.ScalarOf;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0012\"\u0007\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u000b[&tGj\\<Qe&|'BA\u0003\u0007\u0003\u0019a\u0017N\\1mO*\tq!\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001!\u0002\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0001\"aC\n\n\u0005Qa!\u0001B+oSR\fQ!\\5o-N+baF\u00133{=:E\u0003\u0002\r5\u007f\t\u0003R!G\u000f$]Er!AG\u000e\u000e\u0003\u0011I!\u0001\b\u0003\u0002\u00075Lg.\u0003\u0002\u001f?\t)\u0011*\u001c9me%\u0011\u0001%\t\u0002\u0006+\u001a+hn\u0019\u0006\u0003E\u0019\tqaZ3oKJL7\r\u0005\u0002%K1\u0001A!\u0002\u0014\u0003\u0005\u00049#!\u0001+\u0012\u0005!Z\u0003CA\u0006*\u0013\tQCBA\u0004O_RD\u0017N\\4\u0011\u0005-a\u0013BA\u0017\r\u0005\r\te.\u001f\t\u0003I=\"Q\u0001\r\u0002C\u0002\u001d\u00121A\u0015%T!\t!#\u0007B\u00034\u0005\t\u0007qEA\u0001V\u0011\u0015)$\u0001q\u00017\u0003\u0011\u0019WN\u001e%\u0011\t]R4\u0005P\u0007\u0002q)\u0011\u0011\bB\u0001\bgV\u0004\bo\u001c:u\u0013\tY\u0004H\u0001\u0005TG\u0006d\u0017M](g!\t!S\bB\u0003?\u0005\t\u0007qEA\u0002M\u0011NCQ\u0001\u0011\u0002A\u0004\u0005\u000bq!\\5o\u00136\u0004H\u000eE\u0003\u001a;qrC\bC\u0003D\u0005\u0001\u000fA)A\u0002d[Z\u0004baN#$yq\n\u0014B\u0001$9\u00051\u0019\u0015M\\'baZ\u000bG.^3t\t\u0015A%A1\u0001(\u0005\t\u0011f+\u000b\u0002\u0001\u0015*\u0011A\u0004\u0002"
)
public interface minLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl2 minVS$(final minLowPrio $this, final ScalarOf cmvH, final UFunc.UImpl2 minImpl, final CanMapValues cmv) {
      return $this.minVS(cmvH, minImpl, cmv);
   }

   default UFunc.UImpl2 minVS(final ScalarOf cmvH, final UFunc.UImpl2 minImpl, final CanMapValues cmv) {
      return new UFunc.UImpl2(cmv, minImpl) {
         private final CanMapValues cmv$2;
         private final UFunc.UImpl2 minImpl$1;

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

         public Object apply(final Object v, final Object v2) {
            return this.cmv$2.map(v, (x$2) -> this.minImpl$1.apply(x$2, v2));
         }

         public {
            this.cmv$2 = cmv$2;
            this.minImpl$1 = minImpl$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final minLowPrio $this) {
   }
}
