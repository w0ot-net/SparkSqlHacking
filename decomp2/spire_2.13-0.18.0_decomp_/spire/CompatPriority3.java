package spire;

import algebra.ring.EuclideanRing;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableFrom;
import spire.math.ConvertableFrom$;
import spire.math.ScalaIntegralWrapper;
import spire.math.ScalaNumericWrapper;
import spire.math.ScalaOrderingWrapper;
import spire.math.ScalaOrderingWrapperCompat;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0002\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0010\u0007>l\u0007/\u0019;Qe&|'/\u001b;zg)\tQ!A\u0003ta&\u0014XmE\u0002\u0001\u000f5\u0001\"\u0001C\u0006\u000e\u0003%Q\u0011AC\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0019%\u0011a!\u00118z%\u00164\u0007C\u0001\b\u0010\u001b\u0005!\u0011B\u0001\t\u0005\u0005=\u0019u.\u001c9biB\u0013\u0018n\u001c:jif\u0014\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003Q\u0001\"\u0001C\u000b\n\u0005YI!\u0001B+oSR\f\u0001\"\u001b8uK\u001e\u0014\u0018\r\\\u000b\u00033\t\"RAG\u0016>\t&\u00032a\u0007\u0010!\u001b\u0005a\"BA\u000f\n\u0003\u0011i\u0017\r\u001e5\n\u0005}a\"\u0001C%oi\u0016<'/\u00197\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\t\u0011\r\u0001\n\u0002\u0002\u0003F\u0011Q\u0005\u000b\t\u0003\u0011\u0019J!aJ\u0005\u0003\u000f9{G\u000f[5oOB\u0011\u0001\"K\u0005\u0003U%\u00111!\u00118z\u0011\u001da#!!AA\u00045\n1\"\u001a<jI\u0016t7-\u001a\u00132eA\u0019aF\u000f\u0011\u000f\u0005=:dB\u0001\u00196\u001d\t\tD'D\u00013\u0015\t\u0019$#\u0001\u0004=e>|GOP\u0005\u0002\u000b%\u0011a\u0007B\u0001\bC2<WM\u0019:b\u0013\tA\u0014(A\u0004qC\u000e\\\u0017mZ3\u000b\u0005Y\"\u0011BA\u001e=\u00055)Uo\u00197jI\u0016\fgNU5oO*\u0011\u0001(\u000f\u0005\b}\t\t\t\u0011q\u0001@\u0003-)g/\u001b3f]\u000e,G%M\u001a\u0011\u0007\u0001\u0013\u0005%D\u0001B\u0015\tiB!\u0003\u0002D\u0003\ny1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000eC\u0004F\u0005\u0005\u0005\t9\u0001$\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u000e\t\u0004]\u001d\u0003\u0013B\u0001%=\u0005\u0019\u0019\u0016n\u001a8fI\"9!JAA\u0001\u0002\bY\u0015aC3wS\u0012,gnY3%cU\u00022A\f'!\u0013\tiEHA\u0003Pe\u0012,'\u000f"
)
public interface CompatPriority3 extends CompatPriority2 {
   // $FF: synthetic method
   static Integral integral$(final CompatPriority3 $this, final EuclideanRing evidence$12, final ConvertableFrom evidence$13, final Signed evidence$14, final Order evidence$15) {
      return $this.integral(evidence$12, evidence$13, evidence$14, evidence$15);
   }

   default Integral integral(final EuclideanRing evidence$12, final ConvertableFrom evidence$13, final Signed evidence$14, final Order evidence$15) {
      return new ScalaIntegralWrapper(evidence$15, evidence$12, evidence$13, evidence$14) {
         private final Order order;
         private final EuclideanRing structure;
         private final ConvertableFrom conversions;
         private final Signed signed;

         public Object quot(final Object x, final Object y) {
            return ScalaIntegralWrapper.quot$(this, x, y);
         }

         public Object rem(final Object x, final Object y) {
            return ScalaIntegralWrapper.rem$(this, x, y);
         }

         public Integral.IntegralOps mkNumericOps(final Object lhs) {
            return Integral.mkNumericOps$(this, lhs);
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

         public EuclideanRing structure() {
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
            Integral.$init$(this);
            ScalaIntegralWrapper.$init$(this);
            this.order = spire.algebra.package$.MODULE$.Order().apply(evidence$15$1);
            this.structure = spire.algebra.package$.MODULE$.EuclideanRing().apply(evidence$12$1);
            this.conversions = ConvertableFrom$.MODULE$.apply(evidence$13$1);
            this.signed = spire.algebra.package$.MODULE$.Signed().apply(evidence$14$1);
         }
      };
   }

   static void $init$(final CompatPriority3 $this) {
   }
}
