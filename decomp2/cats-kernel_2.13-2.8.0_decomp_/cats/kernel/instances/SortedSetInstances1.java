package cats.kernel.instances;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006%\u0001!\t\u0001\u0006\u0005\u00061\u0001!\u0019!\u0007\u0005\u0006k\u0001!\u0019A\u000e\u0002\u0014'>\u0014H/\u001a3TKRLen\u001d;b]\u000e,7/\r\u0006\u0003\r\u001d\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005!I\u0011AB6fe:,GNC\u0001\u000b\u0003\u0011\u0019\u0017\r^:\u0014\u0005\u0001a\u0001CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005)\u0002CA\u0007\u0017\u0013\t9bB\u0001\u0003V]&$\u0018AH2biN\\UM\u001d8fYN#Hm\u0014:eKJ4uN]*peR,GmU3u+\tQ\u0012\u0006\u0006\u0002\u001ceA\u0019A$H\u0010\u000e\u0003\u001dI!AH\u0004\u0003\u000b=\u0013H-\u001a:\u0011\u0007\u0001*s%D\u0001\"\u0015\t\u00113%A\u0005j[6,H/\u00192mK*\u0011AED\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u0014\"\u0005%\u0019vN\u001d;fIN+G\u000f\u0005\u0002)S1\u0001A!\u0002\u0016\u0003\u0005\u0004Y#!A!\u0012\u00051z\u0003CA\u0007.\u0013\tqcBA\u0004O_RD\u0017N\\4\u0011\u00055\u0001\u0014BA\u0019\u000f\u0005\r\te.\u001f\u0005\bg\t\t\t\u0011q\u00015\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u00049u9\u0013aK2biN\\UM\u001d8fYN#HMQ8v]\u0012,GmU3nS2\fG\u000f^5dK\u001a{'oU8si\u0016$7+\u001a;\u0016\u0005]jDC\u0001\u001d?!\ra\u0012hO\u0005\u0003u\u001d\u0011!CQ8v]\u0012,GmU3nS2\fG\u000f^5dKB\u0019\u0001%\n\u001f\u0011\u0005!jD!\u0002\u0016\u0004\u0005\u0004Y\u0003bB \u0004\u0003\u0003\u0005\u001d\u0001Q\u0001\u000bKZLG-\u001a8dK\u0012*\u0004c\u0001\u000f\u001ey\u0001"
)
public interface SortedSetInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForSortedSet$(final SortedSetInstances1 $this, final Order evidence$4) {
      return $this.catsKernelStdOrderForSortedSet(evidence$4);
   }

   default Order catsKernelStdOrderForSortedSet(final Order evidence$4) {
      return new SortedSetOrder(evidence$4);
   }

   // $FF: synthetic method
   static BoundedSemilattice catsKernelStdBoundedSemilatticeForSortedSet$(final SortedSetInstances1 $this, final Order evidence$5) {
      return $this.catsKernelStdBoundedSemilatticeForSortedSet(evidence$5);
   }

   default BoundedSemilattice catsKernelStdBoundedSemilatticeForSortedSet(final Order evidence$5) {
      return new SortedSetSemilattice(evidence$5);
   }

   static void $init$(final SortedSetInstances1 $this) {
   }
}
