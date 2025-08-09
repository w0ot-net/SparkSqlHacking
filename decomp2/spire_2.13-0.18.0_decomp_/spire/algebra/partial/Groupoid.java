package spire.algebra.partial;

import cats.kernel.Eq;
import cats.kernel.Group;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005=4q\u0001D\u0007\u0011\u0002\u0007\u0005A\u0003C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u0003@\u0001\u0019\u0005\u0001\tC\u0003C\u0001\u0011\u00051\tC\u0003F\u0001\u0011\u0005a\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003U\u0001\u0011\u0005QkB\u0003Y\u001b!\u0005\u0011LB\u0003\r\u001b!\u0005!\fC\u0003b\u0013\u0011\u0005!\rC\u0003d\u0013\u0011\u0015AM\u0001\u0005He>,\bo\\5e\u0015\tqq\"A\u0004qCJ$\u0018.\u00197\u000b\u0005A\t\u0012aB1mO\u0016\u0014'/\u0019\u0006\u0002%\u0005)1\u000f]5sK\u000e\u0001QCA\u000b#'\r\u0001a\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0004\u0003:L\bcA\u000f\u001fA5\tQ\"\u0003\u0002 \u001b\ta1+Z7jOJ|W\u000f]8jIB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019\u0003A1\u0001%\u0005\u0005\t\u0015CA\u0013\u0017!\t9b%\u0003\u0002(1\t9aj\u001c;iS:<\u0017A\u0002\u0013j]&$H\u0005F\u0001+!\t92&\u0003\u0002-1\t!QK\\5u\u0003\u0011I7/\u00133\u0015\u0005=jDC\u0001\u00194!\t9\u0012'\u0003\u000231\t9!i\\8mK\u0006t\u0007\"\u0002\u001b\u0003\u0001\b)\u0014AA3w!\r1$\b\t\b\u0003oaj\u0011aD\u0005\u0003s=\tq\u0001]1dW\u0006<W-\u0003\u0002<y\t\u0011Q)\u001d\u0006\u0003s=AQA\u0010\u0002A\u0002\u0001\n\u0011!Y\u0001\bS:4XM]:f)\t\u0001\u0013\tC\u0003?\u0007\u0001\u0007\u0001%\u0001\u0004mK\u001a$\u0018\n\u001a\u000b\u0003A\u0011CQA\u0010\u0003A\u0002\u0001\nqA]5hQRLE\r\u0006\u0002!\u000f\")a(\u0002a\u0001A\u0005\u0001\u0002/\u0019:uS\u0006dw\n]%om\u0016\u00148/\u001a\u000b\u0004\u0015B\u0013\u0006cA&OA5\tAJ\u0003\u0002N#\u0005!Q\u000f^5m\u0013\tyEJA\u0002PaRDQ!\u0015\u0004A\u0002\u0001\n\u0011\u0001\u001f\u0005\u0006'\u001a\u0001\r\u0001I\u0001\u0002s\u0006\u0011r\u000e]%om\u0016\u00148/Z%t\t\u00164\u0017N\\3e)\r\u0001dk\u0016\u0005\u0006#\u001e\u0001\r\u0001\t\u0005\u0006'\u001e\u0001\r\u0001I\u0001\t\u000fJ|W\u000f]8jIB\u0011Q$C\n\u0004\u0013ms\u0006CA\f]\u0013\ti\u0006D\u0001\u0004B]f\u0014VM\u001a\t\u0003;}K!\u0001Y\u0007\u0003'\u001d\u0013x.\u001e9pS\u0012dun\u001e)sS>\u0014\u0018\u000e^=\u0002\rqJg.\u001b;?)\u0005I\u0016!B1qa2LXCA3i)\t1\u0017\u000eE\u0002\u001e\u0001\u001d\u0004\"!\t5\u0005\u000b\rZ!\u0019\u0001\u0013\t\u000b)\\\u00019\u00014\u0002\u0003\u001dD#a\u00037\u0011\u0005]i\u0017B\u00018\u0019\u0005\u0019Ig\u000e\\5oK\u0002"
)
public interface Groupoid extends Semigroupoid {
   static Groupoid apply(final Groupoid g) {
      return Groupoid$.MODULE$.apply(g);
   }

   static Groupoid fromGroup(final Group group) {
      return Groupoid$.MODULE$.fromGroup(group);
   }

   // $FF: synthetic method
   static boolean isId$(final Groupoid $this, final Object a, final Eq ev) {
      return $this.isId(a, ev);
   }

   default boolean isId(final Object a, final Eq ev) {
      return ev.eqv(a, this.leftId(a));
   }

   Object inverse(final Object a);

   // $FF: synthetic method
   static Object leftId$(final Groupoid $this, final Object a) {
      return $this.leftId(a);
   }

   default Object leftId(final Object a) {
      return .MODULE$.get$extension(this.partialOp(a, this.inverse(a)));
   }

   // $FF: synthetic method
   static Object rightId$(final Groupoid $this, final Object a) {
      return $this.rightId(a);
   }

   default Object rightId(final Object a) {
      return .MODULE$.get$extension(this.partialOp(this.inverse(a), a));
   }

   // $FF: synthetic method
   static Object partialOpInverse$(final Groupoid $this, final Object x, final Object y) {
      return $this.partialOpInverse(x, y);
   }

   default Object partialOpInverse(final Object x, final Object y) {
      return this.partialOp(x, this.inverse(y));
   }

   // $FF: synthetic method
   static boolean opInverseIsDefined$(final Groupoid $this, final Object x, final Object y) {
      return $this.opInverseIsDefined(x, y);
   }

   default boolean opInverseIsDefined(final Object x, final Object y) {
      return this.opIsDefined(x, this.inverse(y));
   }

   static void $init$(final Groupoid $this) {
   }
}
