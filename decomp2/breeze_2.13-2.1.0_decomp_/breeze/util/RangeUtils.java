package breeze.util;

import scala.Tuple3;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y:Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQ\u0001L\u0001\u0005\u00025\n!BU1oO\u0016,F/\u001b7t\u0015\t9\u0001\"\u0001\u0003vi&d'\"A\u0005\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"\u0001D\u0001\u000e\u0003\u0019\u0011!BU1oO\u0016,F/\u001b7t'\t\tq\u0002\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\t\u0001b\u001c<fe2\f\u0007o\u001d\u000b\u00043qQ\u0003C\u0001\t\u001b\u0013\tY\u0012CA\u0004C_>dW-\u00198\t\u000bu\u0019\u0001\u0019\u0001\u0010\u0002\u0003\u0005\u0004\"aH\u0014\u000f\u0005\u0001*cBA\u0011%\u001b\u0005\u0011#BA\u0012\u000b\u0003\u0019a$o\\8u}%\t!#\u0003\u0002'#\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0015*\u0005\u0015\u0011\u0016M\\4f\u0015\t1\u0013\u0003C\u0003,\u0007\u0001\u0007a$A\u0001c\u0003i)\u0007\u0010^3oI\u0016$W)^2mS\u0012,\u0017M\\!mO>\u0014\u0018\u000e\u001e5n)\rqC'\u000e\t\u0006!=\n\u0014'M\u0005\u0003aE\u0011a\u0001V;qY\u0016\u001c\u0004C\u0001\t3\u0013\t\u0019\u0014CA\u0002J]RDQ!\b\u0003A\u0002EBQa\u000b\u0003A\u0002E\u0002"
)
public final class RangeUtils {
   public static Tuple3 extendedEuclideanAlgorithm(final int a, final int b) {
      return RangeUtils$.MODULE$.extendedEuclideanAlgorithm(a, b);
   }

   public static boolean overlaps(final Range a, final Range b) {
      return RangeUtils$.MODULE$.overlaps(a, b);
   }
}
