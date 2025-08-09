package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006{\u0001!\u0019A\u0010\u0002\u0013\u0019\u0006T\u0018\u0010T5ti&s7\u000f^1oG\u0016\u001c\u0018G\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\n\u0004\u00011\u0011\u0002CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\r\u0005\u0002\u0014)5\tQ!\u0003\u0002\u0016\u000b\t\u0011B*\u0019>z\u0019&\u001cH/\u00138ti\u0006t7-Z:3\u0003\u0019!\u0013N\\5uI\r\u0001A#A\r\u0011\u00055Q\u0012BA\u000e\u000f\u0005\u0011)f.\u001b;\u0002I\r\fGo]&fe:,Gn\u0015;e!\u0006\u0014H/[1m\u001fJ$WM\u001d$pe2\u000b'0\u001f'jgR,\"AH\u0019\u0015\u0005}Q\u0004c\u0001\u0011\"G5\tq!\u0003\u0002#\u000f\ta\u0001+\u0019:uS\u0006dwJ\u001d3feB\u0019A\u0005L\u0018\u000f\u0005\u0015RcB\u0001\u0014*\u001b\u00059#B\u0001\u0015\u0018\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002,\u001d\u00059\u0001/Y2lC\u001e,\u0017BA\u0017/\u0005!a\u0015M_=MSN$(BA\u0016\u000f!\t\u0001\u0014\u0007\u0004\u0001\u0005\u000bI\u0012!\u0019A\u001a\u0003\u0003\u0005\u000b\"\u0001N\u001c\u0011\u00055)\u0014B\u0001\u001c\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u001d\n\u0005er!aA!os\"91HAA\u0001\u0002\ba\u0014AC3wS\u0012,gnY3%eA\u0019\u0001%I\u0018\u00029\r\fGo]&fe:,Gn\u0015;e\u0011\u0006\u001c\bNR8s\u0019\u0006T\u0018\u0010T5tiV\u0011q(\u0012\u000b\u0003\u0001\u001a\u00032\u0001I!D\u0013\t\u0011uA\u0001\u0003ICND\u0007c\u0001\u0013-\tB\u0011\u0001'\u0012\u0003\u0006e\r\u0011\ra\r\u0005\b\u000f\u000e\t\t\u0011q\u0001I\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004A\u0005#\u0005"
)
public interface LazyListInstances1 extends LazyListInstances2 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForLazyList$(final LazyListInstances1 $this, final PartialOrder evidence$2) {
      return $this.catsKernelStdPartialOrderForLazyList(evidence$2);
   }

   default PartialOrder catsKernelStdPartialOrderForLazyList(final PartialOrder evidence$2) {
      return new LazyListPartialOrder(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForLazyList$(final LazyListInstances1 $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForLazyList(evidence$3);
   }

   default Hash catsKernelStdHashForLazyList(final Hash evidence$3) {
      return new LazyListHash(evidence$3);
   }

   static void $init$(final LazyListInstances1 $this) {
   }
}
