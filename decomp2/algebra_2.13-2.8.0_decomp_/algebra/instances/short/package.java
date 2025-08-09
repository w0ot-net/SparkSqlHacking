package algebra.instances.short;

import algebra.instances.ShortAlgebra;
import algebra.lattice.BoundedDistributiveLattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005)1\u000f[8si*\u0011q\u0001C\u0001\nS:\u001cH/\u00198dKNT\u0011!C\u0001\bC2<WM\u0019:b\u0007\u0001\u0001\"\u0001D\u0001\u000e\u0003\u0011\u0011q\u0001]1dW\u0006<WmE\u0002\u0002\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u00051\u0011B\u0001\r\u0007\u00059\u0019\u0006n\u001c:u\u0013:\u001cH/\u00198dKN\fa\u0001P5oSRtD#A\u0006"
)
public final class package {
   public static BoundedDistributiveLattice ShortMinMaxLattice() {
      return package$.MODULE$.ShortMinMaxLattice();
   }

   public static ShortAlgebra shortAlgebra() {
      return package$.MODULE$.shortAlgebra();
   }

   public static CommutativeGroup catsKernelStdGroupForShort() {
      return package$.MODULE$.catsKernelStdGroupForShort();
   }

   public static Order catsKernelStdOrderForShort() {
      return package$.MODULE$.catsKernelStdOrderForShort();
   }
}
