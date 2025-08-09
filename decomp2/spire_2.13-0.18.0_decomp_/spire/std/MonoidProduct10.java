package spire.std;

import cats.kernel.Monoid;
import scala.Tuple10;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4\u0001\"\u0004\b\u0011\u0002\u0007\u0005\u0001C\u0005\u0005\u00061\u0002!\t!\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u00021\u0019a\u001a\u0005\u0006S\u00021\u0019A\u001b\u0005\u0006Y\u00021\u0019!\u001c\u0005\u0006_\u00021\u0019\u0001\u001d\u0005\u0006e\u00021\u0019a\u001d\u0005\u0006k\u00021\u0019A\u001e\u0005\u0006q\u00021\u0019!\u001f\u0005\u0006w\u0002!\t\u0001 \u0002\u0010\u001b>tw.\u001b3Qe>$Wo\u0019;2a)\u0011q\u0002E\u0001\u0004gR$'\"A\t\u0002\u000bM\u0004\u0018N]3\u0016\u0017M\u0001$(\u0010!D\r&cuJU\n\u0005\u0001QQB\u000b\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00047!ZcB\u0001\u000f&\u001d\ti2E\u0004\u0002\u001fE5\tqD\u0003\u0002!C\u00051AH]8piz\u001a\u0001!C\u0001\u0012\u0013\t!\u0003#A\u0004bY\u001e,'M]1\n\u0005\u0019:\u0013a\u00029bG.\fw-\u001a\u0006\u0003IAI!!\u000b\u0016\u0003\r5{gn\\5e\u0015\t1s\u0005\u0005\u0007\u0016Y9JDh\u0010\"F\u0011.s\u0015+\u0003\u0002.-\t9A+\u001e9mKF\u0002\u0004CA\u00181\u0019\u0001!Q!\r\u0001C\u0002I\u0012\u0011!Q\t\u0003gY\u0002\"!\u0006\u001b\n\u0005U2\"a\u0002(pi\"Lgn\u001a\t\u0003+]J!\u0001\u000f\f\u0003\u0007\u0005s\u0017\u0010\u0005\u00020u\u0011)1\b\u0001b\u0001e\t\t!\t\u0005\u00020{\u0011)a\b\u0001b\u0001e\t\t1\t\u0005\u00020\u0001\u0012)\u0011\t\u0001b\u0001e\t\tA\t\u0005\u00020\u0007\u0012)A\t\u0001b\u0001e\t\tQ\t\u0005\u00020\r\u0012)q\t\u0001b\u0001e\t\ta\t\u0005\u00020\u0013\u0012)!\n\u0001b\u0001e\t\tq\t\u0005\u00020\u0019\u0012)Q\n\u0001b\u0001e\t\t\u0001\n\u0005\u00020\u001f\u0012)\u0001\u000b\u0001b\u0001e\t\t\u0011\n\u0005\u00020%\u0012)1\u000b\u0001b\u0001e\t\t!\n\u0005\u0007V-:JDh\u0010\"F\u0011.s\u0015+D\u0001\u000f\u0013\t9fB\u0001\nTK6LwM]8vaB\u0013x\u000eZ;diF\u0002\u0014A\u0002\u0013j]&$H\u0005F\u0001[!\t)2,\u0003\u0002]-\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002?B\u00191\u0004\u000b\u0018\u0002\u0015M$(/^2ukJ,''F\u0001c!\rY\u0002&O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A3\u0011\u0007mAC(\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u001b\t\u00047!z\u0014AC:ueV\u001cG/\u001e:fkU\t1\u000eE\u0002\u001cQ\t\u000b!b\u001d;sk\u000e$XO]37+\u0005q\u0007cA\u000e)\u000b\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003E\u00042a\u0007\u0015I\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002iB\u00191\u0004K&\u0002\u0015M$(/^2ukJ,\u0017(F\u0001x!\rY\u0002FT\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'F\u0001{!\rY\u0002&U\u0001\u0006K6\u0004H/_\u000b\u0002W\u0001"
)
public interface MonoidProduct10 extends Monoid, SemigroupProduct10 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   Monoid structure9();

   Monoid structure10();

   // $FF: synthetic method
   static Tuple10 empty$(final MonoidProduct10 $this) {
      return $this.empty();
   }

   default Tuple10 empty() {
      return new Tuple10(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty());
   }

   static void $init$(final MonoidProduct10 $this) {
   }
}
