package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCopy;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0017\u0001\u0011\u0005q\u0003C\u0003\u001c\u0001\u0011\rADA\tHK:,'/[2PaNdun\u001e)sS>T!!\u0002\u0004\u0002\u0013=\u0004XM]1u_J\u001c(BA\u0004\t\u0003\u0019a\u0017N\\1mO*\t\u0011\"\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u0001AB\u0005\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005M!R\"\u0001\u0003\n\u0005U!!aB\"bgR|\u0005o]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003a\u0001\"!D\r\n\u0005iq!\u0001B+oSR\fa\u0002];sK\u001a\u0013x.\\+qI\u0006$X-\u0006\u0003\u001eimRCc\u0001\u0010>\u0005B1q$\n\u00154uMr!\u0001I\u0012\u000e\u0003\u0005R!A\t\u0005\u0002\u000f\u001d,g.\u001a:jG&\u0011A%I\u0001\u0006+\u001a+hnY\u0005\u0003M\u001d\u0012a!V%na2\u0014$B\u0001\u0013\"!\tI#\u0006\u0004\u0001\u0005\u000b-\u0012!\u0019\u0001\u0017\u0003\u0005=\u0003\u0018CA\u00171!\tia&\u0003\u00020\u001d\t9aj\u001c;iS:<\u0007CA\n2\u0013\t\u0011DA\u0001\u0004PaRK\b/\u001a\t\u0003SQ\"Q!\u000e\u0002C\u0002Y\u0012\u0011\u0001V\t\u0003[]\u0002\"!\u0004\u001d\n\u0005er!aA!osB\u0011\u0011f\u000f\u0003\u0006y\t\u0011\rA\u000e\u0002\u0006\u001fRDWM\u001d\u0005\u0006}\t\u0001\u001daP\u0001\u0003_B\u0004Ra\b!)giJ!!Q\u0014\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\u001c\u001a\t\u000b\r\u0013\u00019\u0001#\u0002\t\r|\u0007/\u001f\t\u0004\u000b\"\u001bT\"\u0001$\u000b\u0005\u001d3\u0011aB:vaB|'\u000f^\u0005\u0003\u0013\u001a\u0013qaQ1o\u0007>\u0004\u0018\u0010"
)
public interface GenericOpsLowPrio extends CastOps {
   // $FF: synthetic method
   static UFunc.UImpl2 pureFromUpdate$(final GenericOpsLowPrio $this, final UFunc.InPlaceImpl2 op, final CanCopy copy) {
      return $this.pureFromUpdate(op, copy);
   }

   default UFunc.UImpl2 pureFromUpdate(final UFunc.InPlaceImpl2 op, final CanCopy copy) {
      return (a, b) -> {
         Object c = copy.apply(a);
         op.apply(c, b);
         return c;
      };
   }

   static void $init$(final GenericOpsLowPrio $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
