package cats.kernel;

import cats.kernel.instances.function.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006)\u0001!\tA\u0006\u0005\u00065\u0001!\u0019a\u0007\u0005\u0006c\u0001!\u0019A\r\u0002\u000f\u000fJ|W\u000f]%ogR\fgnY3t\u0015\t1q!\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0011\u0005!1-\u0019;t'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"A\u0003\n\u0005M)!a\u0007\"pk:$W\rZ*f[&d\u0017\r\u001e;jG\u0016Len\u001d;b]\u000e,7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u00059\u0002CA\u0006\u0019\u0013\tIBB\u0001\u0003V]&$\u0018aG2biN\\UM\u001d8fY\u001e\u0013x.\u001e9G_J4UO\\2uS>t\u0007'\u0006\u0002\u001dKQ\u0011QD\f\t\u0004#y\u0001\u0013BA\u0010\u0006\u0005\u00159%o\\;q!\rY\u0011eI\u0005\u0003E1\u0011\u0011BR;oGRLwN\u001c\u0019\u0011\u0005\u0011*C\u0002\u0001\u0003\u0006M\t\u0011\ra\n\u0002\u0002\u0003F\u0011\u0001f\u000b\t\u0003\u0017%J!A\u000b\u0007\u0003\u000f9{G\u000f[5oOB\u00111\u0002L\u0005\u0003[1\u00111!\u00118z\u0011\u001dy#!!AA\u0004A\n!\"\u001a<jI\u0016t7-\u001a\u00139!\r\tbdI\u0001\u001cG\u0006$8oS3s]\u0016dwI]8va\u001a{'OR;oGRLwN\\\u0019\u0016\u0007MJ4\b\u0006\u00025{A\u0019\u0011CH\u001b\u0011\t-1\u0004HO\u0005\u0003o1\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005\u0011JD!\u0002\u0014\u0004\u0005\u00049\u0003C\u0001\u0013<\t\u0015a4A1\u0001(\u0005\u0005\u0011\u0005b\u0002 \u0004\u0003\u0003\u0005\u001daP\u0001\u000bKZLG-\u001a8dK\u0012J\u0004cA\t\u001fu\u0001"
)
public interface GroupInstances extends BoundedSemilatticeInstances {
   // $FF: synthetic method
   static Group catsKernelGroupForFunction0$(final GroupInstances $this, final Group evidence$8) {
      return $this.catsKernelGroupForFunction0(evidence$8);
   }

   default Group catsKernelGroupForFunction0(final Group evidence$8) {
      return package$.MODULE$.catsKernelGroupForFunction0(evidence$8);
   }

   // $FF: synthetic method
   static Group catsKernelGroupForFunction1$(final GroupInstances $this, final Group evidence$9) {
      return $this.catsKernelGroupForFunction1(evidence$9);
   }

   default Group catsKernelGroupForFunction1(final Group evidence$9) {
      return package$.MODULE$.catsKernelGroupForFunction1(evidence$9);
   }

   static void $init$(final GroupInstances $this) {
   }
}
