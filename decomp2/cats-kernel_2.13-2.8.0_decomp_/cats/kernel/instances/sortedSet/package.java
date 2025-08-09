package cats.kernel.instances.sortedSet;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Hash;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQaG\u0001\u0005\u0002q\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005I1o\u001c:uK\u0012\u001cV\r\u001e\u0006\u0003\u000f!\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005%Q\u0011AB6fe:,GNC\u0001\f\u0003\u0011\u0019\u0017\r^:\u0004\u0001A\u0011a\"A\u0007\u0002\t\t9\u0001/Y2lC\u001e,7cA\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\r\u000e\u0003\u0019I!A\u0007\u0004\u0003%M{'\u000f^3e'\u0016$\u0018J\\:uC:\u001cWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\u0001"
)
public final class package {
   public static Hash catsKernelStdHashForSortedSet(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForSortedSet(evidence$3);
   }

   /** @deprecated */
   public static Hash catsKernelStdHashForSortedSet(final Order evidence$1, final Hash evidence$2) {
      return package$.MODULE$.catsKernelStdHashForSortedSet(evidence$1, evidence$2);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForSortedSet(final Order evidence$5) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForSortedSet(evidence$5);
   }

   public static Order catsKernelStdOrderForSortedSet(final Order evidence$4) {
      return package$.MODULE$.catsKernelStdOrderForSortedSet(evidence$4);
   }
}
