package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001D\u0001\nHK:,'/[2PaNdun\u001e)sS>\u001c$BA\u0003\u0007\u0003%y\u0007/\u001a:bi>\u00148O\u0003\u0002\b\u0011\u00051A.\u001b8bY\u001eT\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005!\u0002CA\u0007\u0016\u0013\t1bB\u0001\u0003V]&$\u0018!G5na2|FkX*`KF|Vk\u00184s_6|&,\u001b9NCB,b!\u0007\u00141}\u0011\u001bD\u0003\u0002\u000e6\u0001\u001a\u0003baG\u0011%_=\u0012dB\u0001\u000f \u001b\u0005i\"B\u0001\u0010\t\u0003\u001d9WM\\3sS\u000eL!\u0001I\u000f\u0002\u000bU3UO\\2\n\u0005\t\u001a#AB+J[Bd'G\u0003\u0002!;A\u0011QE\n\u0007\u0001\t\u00159#A1\u0001)\u0005\r!\u0016mZ\t\u0003S1\u0002\"!\u0004\u0016\n\u0005-r!a\u0002(pi\"Lgn\u001a\t\u0003\u001b5J!A\f\b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002&a\u0011)\u0011G\u0001b\u0001Q\t\tA\u000b\u0005\u0002&g\u0011)AG\u0001b\u0001Q\t\tQ\u000bC\u00037\u0005\u0001\u000fq'\u0001\u0005iC:$\u0007n\u001c7e!\u0011A4hL\u001f\u000e\u0003eR!A\u000f\u0004\u0002\u000fM,\b\u000f]8si&\u0011A(\u000f\u0002\t'\u000e\fG.\u0019:PMB\u0011QE\u0010\u0003\u0006\u007f\t\u0011\r\u0001\u000b\u0002\u0003-FBQ!\u0011\u0002A\u0004\t\u000bA![7qYB11$\t\u0013>{\r\u0003\"!\n#\u0005\u000b\u0015\u0013!\u0019\u0001\u0015\u0003\u0005Y\u0013\u0006\"B$\u0003\u0001\bA\u0015aD2b]jK\u0007/T1q-\u0006dW/Z:\u0011\raJu&P\"3\u0013\tQ\u0015HA\bDC:T\u0016\u000e]'baZ\u000bG.^3t\u0001"
)
public interface GenericOpsLowPrio3 {
   // $FF: synthetic method
   static UFunc.UImpl2 impl_T_S_eq_U_from_ZipMap$(final GenericOpsLowPrio3 $this, final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return $this.impl_T_S_eq_U_from_ZipMap(handhold, impl, canZipMapValues);
   }

   default UFunc.UImpl2 impl_T_S_eq_U_from_ZipMap(final ScalarOf handhold, final UFunc.UImpl2 impl, final CanZipMapValues canZipMapValues) {
      return (v1, v2) -> canZipMapValues.map(v1, v2, (v, v2x) -> impl.apply(v, v2x));
   }

   static void $init$(final GenericOpsLowPrio3 $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
