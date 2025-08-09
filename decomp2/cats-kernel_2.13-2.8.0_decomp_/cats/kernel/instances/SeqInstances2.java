package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u000e'\u0016\f\u0018J\\:uC:\u001cWm\u001d\u001a\u000b\u0005\u00151\u0011!C5ogR\fgnY3t\u0015\t9\u0001\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0013\u0005!1-\u0019;t'\t\u00011\u0002\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0003\u0005\u0002\r+%\u0011a#\u0004\u0002\u0005+:LG/A\u000bdCR\u001c8*\u001a:oK2\u001cF\u000fZ#r\r>\u00148+Z9\u0016\u0005eACC\u0001\u000e2!\rYBDH\u0007\u0002\r%\u0011QD\u0002\u0002\u0003\u000bF\u00042a\b\u0013'\u001b\u0005\u0001#BA\u0011#\u0003%IW.\\;uC\ndWM\u0003\u0002$\u001b\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0015\u0002#aA*fcB\u0011q\u0005\u000b\u0007\u0001\t\u0015I#A1\u0001+\u0005\u0005\t\u0015CA\u0016/!\taA&\u0003\u0002.\u001b\t9aj\u001c;iS:<\u0007C\u0001\u00070\u0013\t\u0001TBA\u0002B]fDqA\r\u0002\u0002\u0002\u0003\u000f1'\u0001\u0006fm&$WM\\2fIQ\u00022a\u0007\u000f'\u0001"
)
public interface SeqInstances2 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForSeq$(final SeqInstances2 $this, final Eq evidence$4) {
      return $this.catsKernelStdEqForSeq(evidence$4);
   }

   default Eq catsKernelStdEqForSeq(final Eq evidence$4) {
      return new SeqEq(evidence$4);
   }

   static void $init$(final SeqInstances2 $this) {
   }
}
