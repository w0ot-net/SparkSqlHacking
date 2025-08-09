package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006%\u0001!\t\u0001\u0006\u0005\u00061\u0001!\u0019!\u0007\u0005\u00061\u0001!\t\u0001\u000f\u0002\u0014'>\u0014H/\u001a3NCBLen\u001d;b]\u000e,7/\r\u0006\u0003\r\u001d\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005!I\u0011AB6fe:,GNC\u0001\u000b\u0003\u0011\u0019\u0017\r^:\u0014\u0005\u0001a\u0001CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005)\u0002CA\u0007\u0017\u0013\t9bB\u0001\u0003V]&$\u0018aG2biN\\UM\u001d8fYN#H-R9G_J\u001cvN\u001d;fI6\u000b\u0007/F\u0002\u001bSM\"\"aG\u001b\u0011\u0007qir$D\u0001\b\u0013\tqrA\u0001\u0002FcB!\u0001%J\u00143\u001b\u0005\t#B\u0001\u0012$\u0003%IW.\\;uC\ndWM\u0003\u0002%\u001d\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0019\n#!C*peR,G-T1q!\tA\u0013\u0006\u0004\u0001\u0005\u000b)\u0012!\u0019A\u0016\u0003\u0003-\u000b\"\u0001L\u0018\u0011\u00055i\u0013B\u0001\u0018\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u0019\n\u0005Er!aA!osB\u0011\u0001f\r\u0003\u0006i\t\u0011\ra\u000b\u0002\u0002-\"9aGAA\u0001\u0002\b9\u0014AC3wS\u0012,gnY3%mA\u0019A$\b\u001a\u0016\u0007ejt\bF\u0002;\u0001\u0016\u00032\u0001H\u000f<!\u0011\u0001S\u0005\u0010 \u0011\u0005!jD!\u0002\u0016\u0004\u0005\u0004Y\u0003C\u0001\u0015@\t\u0015!4A1\u0001,\u0011\u0015\t5\u00011\u0001C\u0003\u0019y'\u000fZ3s\u0017B\u0019Ad\u0011\u001f\n\u0005\u0011;!!B(sI\u0016\u0014\b\"\u0002$\u0004\u0001\u00049\u0015aA3r-B\u0019A$\b )\r\rIE*T(Q!\ti!*\u0003\u0002L\u001d\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\na*\u0001\u001cVg\u0016\u00043-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012,\u0015OR8s'>\u0014H/\u001a3NCB\u0004sN^3se&$W\rI<ji\"|W\u000f\u001e\u0011Pe\u0012,'/A\u0003tS:\u001cW-I\u0001R\u0003!\u0011dF\r\u00181[5\u001b\u0004"
)
public interface SortedMapInstances1 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForSortedMap$(final SortedMapInstances1 $this, final Eq evidence$6) {
      return $this.catsKernelStdEqForSortedMap(evidence$6);
   }

   default Eq catsKernelStdEqForSortedMap(final Eq evidence$6) {
      return new SortedMapEq(evidence$6);
   }

   // $FF: synthetic method
   static Eq catsKernelStdEqForSortedMap$(final SortedMapInstances1 $this, final Order orderK, final Eq eqV) {
      return $this.catsKernelStdEqForSortedMap(orderK, eqV);
   }

   /** @deprecated */
   default Eq catsKernelStdEqForSortedMap(final Order orderK, final Eq eqV) {
      return new SortedMapEq(eqV);
   }

   static void $init$(final SortedMapInstances1 $this) {
   }
}
