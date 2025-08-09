package spire.algebra.partial;

import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005A3q\u0001C\u0005\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003/\u0001\u0019\u0005qfB\u00039\u0013!\u0005\u0011HB\u0003\t\u0013!\u00051\bC\u0003C\u000b\u0011\u00051\tC\u0003E\u000b\u0011\u0015QI\u0001\u0007TK6LwM]8va>LGM\u0003\u0002\u000b\u0017\u00059\u0001/\u0019:uS\u0006d'B\u0001\u0007\u000e\u0003\u001d\tGnZ3ce\u0006T\u0011AD\u0001\u0006gBL'/Z\u0002\u0001+\t\tbe\u0005\u0002\u0001%A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t\u0019\u0011I\\=\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0002CA\n\u001c\u0013\taBC\u0001\u0003V]&$\u0018aC8q\u0013N$UMZ5oK\u0012$2a\b\u0012-!\t\u0019\u0002%\u0003\u0002\")\t9!i\\8mK\u0006t\u0007\"B\u0012\u0003\u0001\u0004!\u0013!\u0001=\u0011\u0005\u00152C\u0002\u0001\u0003\u0006O\u0001\u0011\r\u0001\u000b\u0002\u0002\u0003F\u0011\u0011F\u0005\t\u0003')J!a\u000b\u000b\u0003\u000f9{G\u000f[5oO\")QF\u0001a\u0001I\u0005\t\u00110A\u0005qCJ$\u0018.\u00197PaR\u0019\u0001GN\u001c\u0011\u0007E\"D%D\u00013\u0015\t\u0019T\"\u0001\u0003vi&d\u0017BA\u001b3\u0005\ry\u0005\u000f\u001e\u0005\u0006G\r\u0001\r\u0001\n\u0005\u0006[\r\u0001\r\u0001J\u0001\r'\u0016l\u0017n\u001a:pkB|\u0017\u000e\u001a\t\u0003u\u0015i\u0011!C\n\u0004\u000bqz\u0004CA\n>\u0013\tqDC\u0001\u0004B]f\u0014VM\u001a\t\u0003u\u0001K!!Q\u0005\u0003/M+W.[4s_V\u0004x.\u001b3M_^\u0004&/[8sSRL\u0018A\u0002\u001fj]&$h\bF\u0001:\u0003\u0015\t\u0007\u000f\u001d7z+\t1\u0015\n\u0006\u0002H\u0015B\u0019!\b\u0001%\u0011\u0005\u0015JE!B\u0014\b\u0005\u0004A\u0003\"B&\b\u0001\b9\u0015!A:)\u0005\u001di\u0005CA\nO\u0013\tyEC\u0001\u0004j]2Lg.\u001a"
)
public interface Semigroupoid {
   static Semigroupoid apply(final Semigroupoid s) {
      return Semigroupoid$.MODULE$.apply(s);
   }

   static Semigroupoid fromSemigroup(final Semigroup semigroup) {
      return Semigroupoid$.MODULE$.fromSemigroup(semigroup);
   }

   // $FF: synthetic method
   static boolean opIsDefined$(final Semigroupoid $this, final Object x, final Object y) {
      return $this.opIsDefined(x, y);
   }

   default boolean opIsDefined(final Object x, final Object y) {
      return .MODULE$.nonEmpty$extension(this.partialOp(x, y));
   }

   Object partialOp(final Object x, final Object y);

   static void $init$(final Semigroupoid $this) {
   }
}
