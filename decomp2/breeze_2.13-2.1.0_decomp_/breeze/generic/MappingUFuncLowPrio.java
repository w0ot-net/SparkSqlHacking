package breeze.generic;

import breeze.linalg.operators.GenericOps;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.ScalarOf;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0012\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\raDA\nNCB\u0004\u0018N\\4V\rVt7\rT8x!JLwN\u0003\u0002\u0006\r\u00059q-\u001a8fe&\u001c'\"A\u0004\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CF\u0007\u0002%)\u00111\u0003F\u0001\n_B,'/\u0019;peNT!!\u0006\u0004\u0002\r1Lg.\u00197h\u0013\t9\"C\u0001\u0006HK:,'/[2PaN\fa\u0001J5oSR$C#\u0001\u000e\u0011\u0005-Y\u0012B\u0001\u000f\r\u0005\u0011)f.\u001b;\u0002\u001d\r\fg.T1q-J2\u0016\r\\;fgV9qDK\u001e5\u0013>sD\u0003\u0002\u0011A\u0017F\u0003b!I\u0013)gijdB\u0001\u0012$\u001b\u0005!\u0011B\u0001\u0013\u0005\u0003\u0015)f)\u001e8d\u0013\t1sE\u0001\u0004V\u00136\u0004HN\r\u0006\u0003I\u0011\u0001\"!\u000b\u0016\r\u0001\u0011)1F\u0001b\u0001Y\t\u0011q\n]\t\u0003[A\u0002\"a\u0003\u0018\n\u0005=b!a\u0002(pi\"Lgn\u001a\t\u0003EEJ!A\r\u0003\u0003\u00195\u000b\u0007\u000f]5oOV3UO\\2\u0011\u0005%\"D!B\u001b\u0003\u0005\u00041$A\u0001,2#\tis\u0007\u0005\u0002\fq%\u0011\u0011\b\u0004\u0002\u0004\u0003:L\bCA\u0015<\t\u0015a$A1\u00017\u0005\u0005!\u0006CA\u0015?\t\u0015y$A1\u00017\u0005\u0005)\u0006\"B!\u0003\u0001\b\u0011\u0015\u0001\u00035b]\u0012Dw\u000e\u001c3\u0011\t\r3%\bS\u0007\u0002\t*\u0011Q\tF\u0001\bgV\u0004\bo\u001c:u\u0013\t9EI\u0001\u0005TG\u0006d\u0017M](g!\tI\u0013\nB\u0003K\u0005\t\u0007aG\u0001\u0002We!)AJ\u0001a\u0002\u001b\u0006!\u0011.\u001c9m!\u0019\tS\u0005K\u001aI\u001dB\u0011\u0011f\u0014\u0003\u0006!\n\u0011\rA\u000e\u0002\u0003-JCQA\u0015\u0002A\u0004M\u000bAbY1o\u001b\u0006\u0004h+\u00197vKN\u0004ba\u0011+;\u0011:k\u0014BA+E\u00051\u0019\u0015M\\'baZ\u000bG.^3tS\t\u0001q+\u0003\u0002Y\t\tyQ*\u00199qS:<WKR;oG>\u00038\u000f"
)
public interface MappingUFuncLowPrio extends GenericOps {
   // $FF: synthetic method
   static UFunc.UImpl2 canMapV2Values$(final MappingUFuncLowPrio $this, final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return $this.canMapV2Values(handhold, impl, canMapValues);
   }

   default UFunc.UImpl2 canMapV2Values(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanMapValues canMapValues) {
      return new UFunc.UImpl2(canMapValues, impl) {
         private final CanMapValues canMapValues$3;
         private final UFunc.UImpl2 impl$6;

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

         public Object apply(final Object v1, final Object v2) {
            return this.canMapValues$3.map(v2, (x$3) -> this.impl$6.apply(v1, x$3));
         }

         public {
            this.canMapValues$3 = canMapValues$3;
            this.impl$6 = impl$6;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final MappingUFuncLowPrio $this) {
   }
}
