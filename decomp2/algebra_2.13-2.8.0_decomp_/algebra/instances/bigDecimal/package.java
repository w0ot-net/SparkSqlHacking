package algebra.instances.bigDecimal;

import algebra.instances.BigDecimalAlgebra;
import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002-1Q!\u0004\u0003\t\u00029AQ!G\u0001\u0005\u0002i\tq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005Q!-[4EK\u000eLW.\u00197\u000b\u0005\u001dA\u0011!C5ogR\fgnY3t\u0015\u0005I\u0011aB1mO\u0016\u0014'/Y\u0002\u0001!\ta\u0011!D\u0001\u0005\u0005\u001d\u0001\u0018mY6bO\u0016\u001c2!A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011acF\u0007\u0002\r%\u0011\u0001D\u0002\u0002\u0014\u0005&<G)Z2j[\u0006d\u0017J\\:uC:\u001cWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0001"
)
public final class package {
   public static BigDecimalAlgebra bigDecimalAlgebra() {
      return package$.MODULE$.bigDecimalAlgebra();
   }

   public static CommutativeGroup catsKernelStdGroupForBigDecimal() {
      return package$.MODULE$.catsKernelStdGroupForBigDecimal();
   }

   public static Order catsKernelStdOrderForBigDecimal() {
      return package$.MODULE$.catsKernelStdOrderForBigDecimal();
   }
}
