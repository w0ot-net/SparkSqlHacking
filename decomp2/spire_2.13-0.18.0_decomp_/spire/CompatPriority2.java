package spire;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.math.Fractional;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableFrom;
import spire.math.ConvertableFrom$;
import spire.math.ScalaFractionalWrapper;
import spire.math.ScalaNumericWrapper;
import spire.math.ScalaOrderingWrapper;
import spire.math.ScalaOrderingWrapperCompat;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0002\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0010\u0007>l\u0007/\u0019;Qe&|'/\u001b;ze)\tQ!A\u0003ta&\u0014XmE\u0002\u0001\u000f5\u0001\"\u0001C\u0006\u000e\u0003%Q\u0011AC\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0019%\u0011a!\u00118z%\u00164\u0007C\u0001\b\u0010\u001b\u0005!\u0011B\u0001\t\u0005\u0005=\u0019u.\u001c9biB\u0013\u0018n\u001c:jif\f\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003Q\u0001\"\u0001C\u000b\n\u0005YI!\u0001B+oSR\f!B\u001a:bGRLwN\\1m+\tI\"\u0005F\u0003\u001bWu\"\u0015\nE\u0002\u001c=\u0001j\u0011\u0001\b\u0006\u0003;%\tA!\\1uQ&\u0011q\u0004\b\u0002\u000b\rJ\f7\r^5p]\u0006d\u0007CA\u0011#\u0019\u0001!Qa\t\u0002C\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"\u0001\u0003\u0014\n\u0005\u001dJ!a\u0002(pi\"Lgn\u001a\t\u0003\u0011%J!AK\u0005\u0003\u0007\u0005s\u0017\u0010C\u0004-\u0005\u0005\u0005\t9A\u0017\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\bE\u0002/u\u0001r!aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a\u0013\u0003\u0019a$o\\8u}%\tQ!\u0003\u00027\t\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u001d:\u0003\u001d\u0001\u0018mY6bO\u0016T!A\u000e\u0003\n\u0005mb$!\u0002$jK2$'B\u0001\u001d:\u0011\u001dq$!!AA\u0004}\n!\"\u001a<jI\u0016t7-\u001a\u0013:!\r\u0001%\tI\u0007\u0002\u0003*\u0011Q\u0004B\u0005\u0003\u0007\u0006\u0013qbQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\u001c\u0005\b\u000b\n\t\t\u0011q\u0001G\u0003-)g/\u001b3f]\u000e,G%\r\u0019\u0011\u00079:\u0005%\u0003\u0002Iy\t11+[4oK\u0012DqA\u0013\u0002\u0002\u0002\u0003\u000f1*A\u0006fm&$WM\\2fIE\n\u0004c\u0001\u0018MA%\u0011Q\n\u0010\u0002\u0006\u001fJ$WM\u001d"
)
public interface CompatPriority2 extends CompatPriority1 {
   // $FF: synthetic method
   static Fractional fractional$(final CompatPriority2 $this, final Field evidence$8, final ConvertableFrom evidence$9, final Signed evidence$10, final Order evidence$11) {
      return $this.fractional(evidence$8, evidence$9, evidence$10, evidence$11);
   }

   default Fractional fractional(final Field evidence$8, final ConvertableFrom evidence$9, final Signed evidence$10, final Order evidence$11) {
      return new ScalaFractionalWrapper(evidence$11, evidence$8, evidence$9, evidence$10) {
         private final Order order;
         private final Field structure;
         private final ConvertableFrom conversions;
         private final Signed signed;

         public Object div(final Object x, final Object y) {
            return ScalaFractionalWrapper.div$(this, x, y);
         }

         public Fractional.FractionalOps mkNumericOps(final Object lhs) {
            return Fractional.mkNumericOps$(this, lhs);
         }

         public Object fromInt(final int x) {
            return ScalaNumericWrapper.fromInt$(this, x);
         }

         public Object negate(final Object x) {
            return ScalaNumericWrapper.negate$(this, x);
         }

         public Object minus(final Object x, final Object y) {
            return ScalaNumericWrapper.minus$(this, x, y);
         }

         public Object plus(final Object x, final Object y) {
            return ScalaNumericWrapper.plus$(this, x, y);
         }

         public Object times(final Object x, final Object y) {
            return ScalaNumericWrapper.times$(this, x, y);
         }

         public Object zero() {
            return ScalaNumericWrapper.zero$(this);
         }

         public Object one() {
            return ScalaNumericWrapper.one$(this);
         }

         public double toDouble(final Object x) {
            return ScalaNumericWrapper.toDouble$(this, x);
         }

         public float toFloat(final Object x) {
            return ScalaNumericWrapper.toFloat$(this, x);
         }

         public int toInt(final Object x) {
            return ScalaNumericWrapper.toInt$(this, x);
         }

         public long toLong(final Object x) {
            return ScalaNumericWrapper.toLong$(this, x);
         }

         public int signum(final Object x) {
            return ScalaNumericWrapper.signum$(this, x);
         }

         public Object abs(final Object x) {
            return ScalaNumericWrapper.abs$(this, x);
         }

         public Option parseString(final String str) {
            return ScalaNumericWrapper.parseString$(this, str);
         }

         public int compare(final Object x, final Object y) {
            return ScalaOrderingWrapper.compare$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return ScalaOrderingWrapper.equiv$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return ScalaOrderingWrapper.gt$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return ScalaOrderingWrapper.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return ScalaOrderingWrapper.lt$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return ScalaOrderingWrapper.lteq$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return ScalaOrderingWrapperCompat.min$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return ScalaOrderingWrapperCompat.max$(this, x, y);
         }

         public Object sign(final Object x) {
            return Numeric.sign$(this, x);
         }

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public Order order() {
            return this.order;
         }

         public Field structure() {
            return this.structure;
         }

         public ConvertableFrom conversions() {
            return this.conversions;
         }

         public Signed signed() {
            return this.signed;
         }

         public {
            PartialOrdering.$init$(this);
            Ordering.$init$(this);
            Numeric.$init$(this);
            ScalaOrderingWrapperCompat.$init$(this);
            ScalaOrderingWrapper.$init$(this);
            ScalaNumericWrapper.$init$(this);
            Fractional.$init$(this);
            ScalaFractionalWrapper.$init$(this);
            this.order = spire.algebra.package$.MODULE$.Order().apply(evidence$11$1);
            this.structure = spire.algebra.package$.MODULE$.Field().apply(evidence$8$1);
            this.conversions = ConvertableFrom$.MODULE$.apply(evidence$9$1);
            this.signed = spire.algebra.package$.MODULE$.Signed().apply(evidence$10$1);
         }
      };
   }

   static void $init$(final CompatPriority2 $this) {
   }
}
