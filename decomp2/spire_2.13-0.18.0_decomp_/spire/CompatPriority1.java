package spire;

import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.math.Equiv;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import spire.math.ConvertableFrom;
import spire.math.ConvertableFrom$;
import spire.math.ScalaEquivWrapper;
import spire.math.ScalaNumericWrapper;
import spire.math.ScalaOrderingWrapper;
import spire.math.ScalaOrderingWrapperCompat;
import spire.math.ScalaPartialOrderingWrapper;

@ScalaSignature(
   bytes = "\u0006\u0005I4\u0001BB\u0004\u0011\u0002\u0007\u0005q!\u0003\u0005\u0006!\u0001!\tA\u0005\u0005\u0006-\u0001!\u0019a\u0006\u0005\u0006\u001b\u0002!\u0019A\u0014\u0005\u00061\u0002!\u0019!\u0017\u0005\u0006K\u0002!\u0019A\u001a\u0002\u0010\u0007>l\u0007/\u0019;Qe&|'/\u001b;zc)\t\u0001\"A\u0003ta&\u0014Xm\u0005\u0002\u0001\u0015A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002'A\u00111\u0002F\u0005\u0003+1\u0011A!\u00168ji\u00069a.^7fe&\u001cWC\u0001\r\")\u0015I\"\u0006P\"I!\rQRdH\u0007\u00027)\u0011A\u0004D\u0001\u0005[\u0006$\b.\u0003\u0002\u001f7\t9a*^7fe&\u001c\u0007C\u0001\u0011\"\u0019\u0001!QA\t\u0002C\u0002\r\u0012\u0011!Q\t\u0003I\u001d\u0002\"aC\u0013\n\u0005\u0019b!a\u0002(pi\"Lgn\u001a\t\u0003\u0017!J!!\u000b\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004,\u0005\u0005\u0005\t9\u0001\u0017\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002.s}q!A\f\u001c\u000f\u0005=\"dB\u0001\u00194\u001b\u0005\t$B\u0001\u001a\u0012\u0003\u0019a$o\\8u}%\t\u0001\"\u0003\u00026\u000f\u00059\u0011\r\\4fEJ\f\u0017BA\u001c9\u0003\u001d\u0001\u0018mY6bO\u0016T!!N\u0004\n\u0005iZ$\u0001\u0002*j]\u001eT!a\u000e\u001d\t\u000fu\u0012\u0011\u0011!a\u0002}\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\u0007}\nu$D\u0001A\u0015\tar!\u0003\u0002C\u0001\ny1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000eC\u0004E\u0005\u0005\u0005\t9A#\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007E\u0002.\r~I!aR\u001e\u0003\rMKwM\\3e\u0011\u001dI%!!AA\u0004)\u000b!\"\u001a<jI\u0016t7-\u001a\u00135!\ri3jH\u0005\u0003\u0019n\u0012Qa\u0014:eKJ\f\u0001b\u001c:eKJLgnZ\u000b\u0003\u001fR#\"\u0001U+\u0011\u0007i\t6+\u0003\u0002S7\tAqJ\u001d3fe&tw\r\u0005\u0002!)\u0012)!e\u0001b\u0001G!9akAA\u0001\u0002\b9\u0016AC3wS\u0012,gnY3%kA\u0019QfS*\u0002\u001fA\f'\u000f^5bY>\u0013H-\u001a:j]\u001e,\"AW0\u0015\u0005m\u0003\u0007c\u0001\u000e]=&\u0011Ql\u0007\u0002\u0010!\u0006\u0014H/[1m\u001fJ$WM]5oOB\u0011\u0001e\u0018\u0003\u0006E\u0011\u0011\ra\t\u0005\bC\u0012\t\t\u0011q\u0001c\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0004[\rt\u0016B\u00013<\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s\u0003\u0015)\u0017/^5w+\t9G\u000e\u0006\u0002i[B\u0019!$[6\n\u0005)\\\"!B#rk&4\bC\u0001\u0011m\t\u0015\u0011SA1\u0001$\u0011\u001dqW!!AA\u0004=\f!\"\u001a<jI\u0016t7-\u001a\u00138!\ri\u0003o[\u0005\u0003cn\u0012!!R9"
)
public interface CompatPriority1 {
   // $FF: synthetic method
   static Numeric numeric$(final CompatPriority1 $this, final Ring evidence$1, final ConvertableFrom evidence$2, final Signed evidence$3, final Order evidence$4) {
      return $this.numeric(evidence$1, evidence$2, evidence$3, evidence$4);
   }

   default Numeric numeric(final Ring evidence$1, final ConvertableFrom evidence$2, final Signed evidence$3, final Order evidence$4) {
      return new ScalaNumericWrapper(evidence$4, evidence$1, evidence$2, evidence$3) {
         private final Order order;
         private final Ring structure;
         private final ConvertableFrom conversions;
         private final Signed signed;

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

         public Numeric.NumericOps mkNumericOps(final Object lhs) {
            return Numeric.mkNumericOps$(this, lhs);
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

         public Ring structure() {
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
            this.order = spire.algebra.package$.MODULE$.Order().apply(evidence$4$1);
            this.structure = spire.algebra.package$.MODULE$.Ring().apply(evidence$1$1);
            this.conversions = ConvertableFrom$.MODULE$.apply(evidence$2$1);
            this.signed = spire.algebra.package$.MODULE$.Signed().apply(evidence$3$1);
         }
      };
   }

   // $FF: synthetic method
   static Ordering ordering$(final CompatPriority1 $this, final Order evidence$5) {
      return $this.ordering(evidence$5);
   }

   default Ordering ordering(final Order evidence$5) {
      return new ScalaOrderingWrapper(evidence$5) {
         private final Order order;

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

         public {
            PartialOrdering.$init$(this);
            Ordering.$init$(this);
            ScalaOrderingWrapperCompat.$init$(this);
            ScalaOrderingWrapper.$init$(this);
            this.order = spire.algebra.package$.MODULE$.Order().apply(evidence$5$1);
         }
      };
   }

   // $FF: synthetic method
   static PartialOrdering partialOrdering$(final CompatPriority1 $this, final PartialOrder evidence$6) {
      return $this.partialOrdering(evidence$6);
   }

   default PartialOrdering partialOrdering(final PartialOrder evidence$6) {
      return new ScalaPartialOrderingWrapper(evidence$6) {
         private final PartialOrder partialOrder;

         public Option tryCompare(final Object x, final Object y) {
            return ScalaPartialOrderingWrapper.tryCompare$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return ScalaPartialOrderingWrapper.equiv$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return ScalaPartialOrderingWrapper.gt$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return ScalaPartialOrderingWrapper.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return ScalaPartialOrderingWrapper.lt$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return ScalaPartialOrderingWrapper.lteq$(this, x, y);
         }

         public PartialOrdering reverse() {
            return PartialOrdering.reverse$(this);
         }

         public PartialOrder partialOrder() {
            return this.partialOrder;
         }

         public {
            PartialOrdering.$init$(this);
            ScalaPartialOrderingWrapper.$init$(this);
            this.partialOrder = spire.algebra.package$.MODULE$.PartialOrder().apply(evidence$6$1);
         }
      };
   }

   // $FF: synthetic method
   static Equiv equiv$(final CompatPriority1 $this, final Eq evidence$7) {
      return $this.equiv(evidence$7);
   }

   default Equiv equiv(final Eq evidence$7) {
      return new ScalaEquivWrapper(evidence$7) {
         private final Eq eq;

         public boolean equiv(final Object x, final Object y) {
            return ScalaEquivWrapper.equiv$(this, x, y);
         }

         public Eq eq() {
            return this.eq;
         }

         public {
            ScalaEquivWrapper.$init$(this);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$7$1);
         }
      };
   }

   static void $init$(final CompatPriority1 $this) {
   }
}
