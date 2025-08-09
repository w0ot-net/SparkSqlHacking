package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005a\u0001\u0004\u0005\u0006/\u0001!\t!\u0007\u0005\u0006;\u0001!\u0019A\b\u0005\u0006{\u0001!\u0019A\u0010\u0005\u0006!\u0002!\u0019!\u0015\u0002\u0014'>\u0014H/\u001a3NCBLen\u001d;b]\u000e,7O\r\u0006\u0003\u000f!\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005%Q\u0011AB6fe:,GNC\u0001\f\u0003\u0011\u0019\u0017\r^:\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011AB\u0005\u0003-\u0019\u00111cU8si\u0016$W*\u00199J]N$\u0018M\\2fgF\na\u0001J5oSR$3\u0001\u0001\u000b\u00025A\u0011abG\u0005\u00039=\u0011A!\u00168ji\u0006\u00113-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012\u001cV-\\5he>,\bOR8s'>\u0014H/\u001a3NCB,2a\b\u00189)\t\u0001#\bE\u0002\"E\u0011j\u0011\u0001C\u0005\u0003G!\u0011\u0011bU3nS\u001e\u0014x.\u001e9\u0011\t\u0015RCfN\u0007\u0002M)\u0011q\u0005K\u0001\nS6lW\u000f^1cY\u0016T!!K\b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002,M\tI1k\u001c:uK\u0012l\u0015\r\u001d\t\u0003[9b\u0001\u0001B\u00030\u0005\t\u0007\u0001GA\u0001L#\t\tD\u0007\u0005\u0002\u000fe%\u00111g\u0004\u0002\b\u001d>$\b.\u001b8h!\tqQ'\u0003\u00027\u001f\t\u0019\u0011I\\=\u0011\u00055BD!B\u001d\u0003\u0005\u0004\u0001$!\u0001,\t\u000fm\u0012\u0011\u0011!a\u0002y\u0005QQM^5eK:\u001cW\rJ\u001c\u0011\u0007\u0005\u0012s'A\u0010dCR\u001c8*\u001a:oK2\u001cF\u000fZ'p]>LGMR8s'>\u0014H/\u001a3NCB,2aP#H)\r\u0001\u0005*\u0014\t\u0004C\u0005\u001b\u0015B\u0001\"\t\u0005\u0019iuN\\8jIB!QE\u000b#G!\tiS\tB\u00030\u0007\t\u0007\u0001\u0007\u0005\u0002.\u000f\u0012)\u0011h\u0001b\u0001a!9\u0011jAA\u0001\u0002\bQ\u0015AC3wS\u0012,gnY3%qA\u0019\u0011e\u0013#\n\u00051C!!B(sI\u0016\u0014\bb\u0002(\u0004\u0003\u0003\u0005\u001daT\u0001\u000bKZLG-\u001a8dK\u0012J\u0004cA\u0011#\r\u0006)3-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012\u0004\u0016M\u001d;jC2|%\u000fZ3s\r>\u00148k\u001c:uK\u0012l\u0015\r]\u000b\u0004%bSFCA*\\!\r\tCKV\u0005\u0003+\"\u0011A\u0002U1si&\fGn\u0014:eKJ\u0004B!\n\u0016X3B\u0011Q\u0006\u0017\u0003\u0006_\u0011\u0011\r\u0001\r\t\u0003[i#Q!\u000f\u0003C\u0002ABq\u0001\u0018\u0003\u0002\u0002\u0003\u000fQ,A\u0006fm&$WM\\2fIE\u0002\u0004cA\u0011U3\u0002"
)
public interface SortedMapInstances2 extends SortedMapInstances1 {
   // $FF: synthetic method
   static Semigroup catsKernelStdSemigroupForSortedMap$(final SortedMapInstances2 $this, final Semigroup evidence$7) {
      return $this.catsKernelStdSemigroupForSortedMap(evidence$7);
   }

   default Semigroup catsKernelStdSemigroupForSortedMap(final Semigroup evidence$7) {
      return new SortedMapSemigroup(evidence$7);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForSortedMap$(final SortedMapInstances2 $this, final Order evidence$8, final Semigroup evidence$9) {
      return $this.catsKernelStdMonoidForSortedMap(evidence$8, evidence$9);
   }

   default Monoid catsKernelStdMonoidForSortedMap(final Order evidence$8, final Semigroup evidence$9) {
      return new SortedMapMonoid(evidence$9, evidence$8);
   }

   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForSortedMap$(final SortedMapInstances2 $this, final PartialOrder evidence$10) {
      return $this.catsKernelStdPartialOrderForSortedMap(evidence$10);
   }

   default PartialOrder catsKernelStdPartialOrderForSortedMap(final PartialOrder evidence$10) {
      return new SortedMapPartialOrder(evidence$10);
   }

   static void $init$(final SortedMapInstances2 $this) {
   }
}
