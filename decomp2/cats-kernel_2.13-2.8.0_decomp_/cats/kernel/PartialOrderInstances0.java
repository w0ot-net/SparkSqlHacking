package cats.kernel;

import cats.kernel.instances.seq.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006'\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0002\u0017!\u0006\u0014H/[1m\u001fJ$WM]%ogR\fgnY3ta)\u0011QAB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003\u001d\tAaY1ugN\u0019\u0001!C\b\u0011\u0005)iQ\"A\u0006\u000b\u00031\tQa]2bY\u0006L!AD\u0006\u0003\r\u0005s\u0017PU3g!\t\u0001\u0012#D\u0001\u0005\u0013\t\u0011BAA\u0007ICND\u0017J\\:uC:\u001cWm]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\ta\u0003\u0005\u0002\u000b/%\u0011\u0001d\u0003\u0002\u0005+:LG/\u0001\u000fdCR\u001c8*\u001a:oK2\u0004\u0016M\u001d;jC2|%\u000fZ3s\r>\u00148+Z9\u0016\u0005mICC\u0001\u000f3!\r\u0001RdH\u0005\u0003=\u0011\u0011A\u0002U1si&\fGn\u0014:eKJ\u00042\u0001I\u0013(\u001b\u0005\t#B\u0001\u0012$\u0003%IW.\\;uC\ndWM\u0003\u0002%\u0017\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0019\n#aA*fcB\u0011\u0001&\u000b\u0007\u0001\t\u0015Q#A1\u0001,\u0005\u0005\t\u0015C\u0001\u00170!\tQQ&\u0003\u0002/\u0017\t9aj\u001c;iS:<\u0007C\u0001\u00061\u0013\t\t4BA\u0002B]fDqa\r\u0002\u0002\u0002\u0003\u000fA'A\u0006fm&$WM\\2fIE2\u0004c\u0001\t\u001eO\u0001"
)
public interface PartialOrderInstances0 extends HashInstances {
   // $FF: synthetic method
   static PartialOrder catsKernelPartialOrderForSeq$(final PartialOrderInstances0 $this, final PartialOrder evidence$16) {
      return $this.catsKernelPartialOrderForSeq(evidence$16);
   }

   default PartialOrder catsKernelPartialOrderForSeq(final PartialOrder evidence$16) {
      return package$.MODULE$.catsKernelStdPartialOrderForSeq(evidence$16);
   }

   static void $init$(final PartialOrderInstances0 $this) {
   }
}
