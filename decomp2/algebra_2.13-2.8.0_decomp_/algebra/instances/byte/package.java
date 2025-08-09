package algebra.instances.byte;

import algebra.instances.ByteAlgebra;
import algebra.lattice.BoundedDistributiveLattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005!!-\u001f;f\u0015\t9\u0001\"A\u0005j]N$\u0018M\\2fg*\t\u0011\"A\u0004bY\u001e,'M]1\u0004\u0001A\u0011A\"A\u0007\u0002\t\t9\u0001/Y2lC\u001e,7cA\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"AF\f\u000e\u0003\u0019I!\u0001\u0007\u0004\u0003\u001b\tKH/Z%ogR\fgnY3t\u0003\u0019a\u0014N\\5u}Q\t1\u0002"
)
public final class package {
   public static BoundedDistributiveLattice ByteMinMaxLattice() {
      return package$.MODULE$.ByteMinMaxLattice();
   }

   public static ByteAlgebra byteAlgebra() {
      return package$.MODULE$.byteAlgebra();
   }

   public static CommutativeGroup catsKernelStdGroupForByte() {
      return package$.MODULE$.catsKernelStdGroupForByte();
   }

   public static Order catsKernelStdOrderForByte() {
      return package$.MODULE$.catsKernelStdOrderForByte();
   }
}
