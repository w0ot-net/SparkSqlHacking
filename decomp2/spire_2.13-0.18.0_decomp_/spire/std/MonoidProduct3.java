package spire.std;

import cats.kernel.Monoid;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0003\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u00021\u0019A\u0011\u0005\u0006\t\u00021\u0019!\u0012\u0005\u0006\u000f\u00021\u0019\u0001\u0013\u0005\u0006\u0015\u0002!\ta\u0013\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;4\u0015\tA\u0011\"A\u0002ti\u0012T\u0011AC\u0001\u0006gBL'/Z\u000b\u0005\u0019%\u001adg\u0005\u0003\u0001\u001bMA\u0004C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\rE\u0002\u0015C\u0011r!!\u0006\u0010\u000f\u0005YabBA\f\u001c\u001b\u0005A\"BA\r\u001b\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0006\n\u0005uI\u0011aB1mO\u0016\u0014'/Y\u0005\u0003?\u0001\nq\u0001]1dW\u0006<WM\u0003\u0002\u001e\u0013%\u0011!e\t\u0002\u0007\u001b>tw.\u001b3\u000b\u0005}\u0001\u0003#\u0002\b&OI*\u0014B\u0001\u0014\u0010\u0005\u0019!V\u000f\u001d7fgA\u0011\u0001&\u000b\u0007\u0001\t\u0015Q\u0003A1\u0001,\u0005\u0005\t\u0015C\u0001\u00170!\tqQ&\u0003\u0002/\u001f\t9aj\u001c;iS:<\u0007C\u0001\b1\u0013\t\ttBA\u0002B]f\u0004\"\u0001K\u001a\u0005\u000bQ\u0002!\u0019A\u0016\u0003\u0003\t\u0003\"\u0001\u000b\u001c\u0005\u000b]\u0002!\u0019A\u0016\u0003\u0003\r\u0003R!\u000f\u001e(eUj\u0011aB\u0005\u0003w\u001d\u0011\u0011cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;4\u0003\u0019!\u0013N\\5uIQ\ta\b\u0005\u0002\u000f\u007f%\u0011\u0001i\u0004\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012a\u0011\t\u0004)\u0005:\u0013AC:ueV\u001cG/\u001e:feU\ta\tE\u0002\u0015CI\n!b\u001d;sk\u000e$XO]34+\u0005I\u0005c\u0001\u000b\"k\u0005)Q-\u001c9usV\tA\u0005"
)
public interface MonoidProduct3 extends Monoid, SemigroupProduct3 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   // $FF: synthetic method
   static Tuple3 empty$(final MonoidProduct3 $this) {
      return $this.empty();
   }

   default Tuple3 empty() {
      return new Tuple3(this.structure1().empty(), this.structure2().empty(), this.structure3().empty());
   }

   static void $init$(final MonoidProduct3 $this) {
   }
}
