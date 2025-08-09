package spire.std;

import cats.kernel.Monoid;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u00021\u0019\u0001\u0014\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u0002!\tA\u0015\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;5\u0015\tI!\"A\u0002ti\u0012T\u0011aC\u0001\u0006gBL'/Z\u000b\u0006\u001b)\"tGO\n\u0005\u00019!B\b\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0004+\t*cB\u0001\f \u001d\t9RD\u0004\u0002\u001995\t\u0011D\u0003\u0002\u001b7\u00051AH]8piz\u001a\u0001!C\u0001\f\u0013\tq\"\"A\u0004bY\u001e,'M]1\n\u0005\u0001\n\u0013a\u00029bG.\fw-\u001a\u0006\u0003=)I!a\t\u0013\u0003\r5{gn\\5e\u0015\t\u0001\u0013\u0005\u0005\u0004\u0010M!\u001ad'O\u0005\u0003OA\u0011a\u0001V;qY\u0016$\u0004CA\u0015+\u0019\u0001!Qa\u000b\u0001C\u00021\u0012\u0011!Q\t\u0003[A\u0002\"a\u0004\u0018\n\u0005=\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001fEJ!A\r\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002*i\u0011)Q\u0007\u0001b\u0001Y\t\t!\t\u0005\u0002*o\u0011)\u0001\b\u0001b\u0001Y\t\t1\t\u0005\u0002*u\u0011)1\b\u0001b\u0001Y\t\tA\t\u0005\u0004>}!\u001ad'O\u0007\u0002\u0011%\u0011q\b\u0003\u0002\u0012'\u0016l\u0017n\u001a:pkB\u0004&o\u001c3vGR$\u0014A\u0002\u0013j]&$H\u0005F\u0001C!\ty1)\u0003\u0002E!\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002\u000fB\u0019QC\t\u0015\u0002\u0015M$(/^2ukJ,''F\u0001K!\r)\"eM\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A'\u0011\u0007U\u0011c'\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u0015\t\u0004+\tJ\u0014!B3naRLX#A\u0013"
)
public interface MonoidProduct4 extends Monoid, SemigroupProduct4 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   // $FF: synthetic method
   static Tuple4 empty$(final MonoidProduct4 $this) {
      return $this.empty();
   }

   default Tuple4 empty() {
      return new Tuple4(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty());
   }

   static void $init$(final MonoidProduct4 $this) {
   }
}
