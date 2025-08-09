package spire.std;

import cats.kernel.Eq;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u00021\u0019A\u0011\u0005\u0006\t\u00021\u0019!\u0012\u0005\u0006\u000f\u00021\u0019\u0001\u0013\u0005\u0006\u0015\u00021\u0019a\u0013\u0005\u0006\u001b\u0002!\tA\u0014\u0002\u000b\u000bF\u0004&o\u001c3vGR$$BA\u0005\u000b\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0017\u0005)1\u000f]5sKV)QB\u000b\u001b8uM\u0019\u0001A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\r)\"%\n\b\u0003-}q!aF\u000f\u000f\u0005aaR\"A\r\u000b\u0005iY\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003-I!A\b\u0006\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0001%I\u0001\ba\u0006\u001c7.Y4f\u0015\tq\"\"\u0003\u0002$I\t\u0011Q)\u001d\u0006\u0003A\u0005\u0002ba\u0004\u0014)gYJ\u0014BA\u0014\u0011\u0005\u0019!V\u000f\u001d7fiA\u0011\u0011F\u000b\u0007\u0001\t\u0015Y\u0003A1\u0001-\u0005\u0005\t\u0015CA\u00171!\tya&\u0003\u00020!\t9aj\u001c;iS:<\u0007CA\b2\u0013\t\u0011\u0004CA\u0002B]f\u0004\"!\u000b\u001b\u0005\u000bU\u0002!\u0019\u0001\u0017\u0003\u0003\t\u0003\"!K\u001c\u0005\u000ba\u0002!\u0019\u0001\u0017\u0003\u0003\r\u0003\"!\u000b\u001e\u0005\u000bm\u0002!\u0019\u0001\u0017\u0003\u0003\u0011\u000ba\u0001J5oSR$C#\u0001 \u0011\u0005=y\u0014B\u0001!\u0011\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001D!\r)\"\u0005K\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#\u0001$\u0011\u0007U\u00113'\u0001\u0006tiJ,8\r^;sKN*\u0012!\u0013\t\u0004+\t2\u0014AC:ueV\u001cG/\u001e:fiU\tA\nE\u0002\u0016Ee\n1!Z9w)\ry%\u000b\u0016\t\u0003\u001fAK!!\u0015\t\u0003\u000f\t{w\u000e\\3b]\")1K\u0002a\u0001K\u0005\u0011\u0001\u0010\r\u0005\u0006+\u001a\u0001\r!J\u0001\u0003qF\u0002"
)
public interface EqProduct4 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   Eq structure4();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct4 $this, final Tuple4 x0, final Tuple4 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple4 x0, final Tuple4 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4());
   }

   static void $init$(final EqProduct4 $this) {
   }
}
