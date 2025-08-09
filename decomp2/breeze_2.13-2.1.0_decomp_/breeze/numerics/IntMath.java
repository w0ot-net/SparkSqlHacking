package breeze.numerics;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019:Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQaF\u0001\u0005\u0002\u0001\nq!\u00138u\u001b\u0006$\bN\u0003\u0002\b\u0011\u0005Aa.^7fe&\u001c7OC\u0001\n\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0007\u0002\u001b\u00051!aB%oi6\u000bG\u000f[\n\u0003\u0003=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\f\u0003\u0011I\u0007o\\<\u0015\u0007eab\u0004\u0005\u0002\u00115%\u00111$\u0005\u0002\u0004\u0013:$\b\"B\u000f\u0004\u0001\u0004I\u0012\u0001\u00022bg\u0016DQaH\u0002A\u0002e\t1!\u001a=q)\r\tC%\n\t\u0003!\tJ!aI\t\u0003\t1{gn\u001a\u0005\u0006;\u0011\u0001\r!\t\u0005\u0006?\u0011\u0001\r!\t"
)
public final class IntMath {
   public static long ipow(final long base, final long exp) {
      return IntMath$.MODULE$.ipow(base, exp);
   }

   public static int ipow(final int base, final int exp) {
      return IntMath$.MODULE$.ipow(base, exp);
   }
}
