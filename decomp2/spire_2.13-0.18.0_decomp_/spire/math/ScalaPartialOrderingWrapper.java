package spire.math;

import cats.kernel.PartialOrder;
import scala.Option;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0006Y\u00011\t!\f\u0005\u0006}\u0001!\ta\u0010\u0005\u0006\u0015\u0002!\te\u0013\u0005\u0006#\u0002!\tE\u0015\u0005\u0006+\u0002!\tE\u0016\u0005\u00063\u0002!\tE\u0017\u0005\u0006;\u0002!\tE\u0018\u0002\u001c'\u000e\fG.\u0019)beRL\u0017\r\\(sI\u0016\u0014\u0018N\\4Xe\u0006\u0004\b/\u001a:\u000b\u0005-a\u0011\u0001B7bi\"T\u0011!D\u0001\u0006gBL'/Z\u000b\u0003\u001fu\u00192\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0019q#G\u000e\u000e\u0003aQ!a\u0003\n\n\u0005iA\"a\u0004)beRL\u0017\r\\(sI\u0016\u0014\u0018N\\4\u0011\u0005qiB\u0002\u0001\u0003\u0006=\u0001\u0011\r\u0001\t\u0002\u0002\u0003\u000e\u0001\u0011CA\u0011%!\t\t\"%\u0003\u0002$%\t9aj\u001c;iS:<\u0007CA\t&\u0013\t1#CA\u0002B]f\fa\u0001J5oSR$C#A\u0015\u0011\u0005EQ\u0013BA\u0016\u0013\u0005\u0011)f.\u001b;\u0002\u0019A\f'\u000f^5bY>\u0013H-\u001a:\u0016\u00039\u00022aL\u001e\u001c\u001d\t\u0001\u0004H\u0004\u00022m9\u0011!'N\u0007\u0002g)\u0011AgH\u0001\u0007yI|w\u000e\u001e \n\u00035I!a\u000e\u0007\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011HO\u0001\ba\u0006\u001c7.Y4f\u0015\t9D\"\u0003\u0002={\ta\u0001+\u0019:uS\u0006dwJ\u001d3fe*\u0011\u0011HO\u0001\u000biJL8i\\7qCJ,Gc\u0001!G\u0011B\u0019\u0011#Q\"\n\u0005\t\u0013\"AB(qi&|g\u000e\u0005\u0002\u0012\t&\u0011QI\u0005\u0002\u0004\u0013:$\b\"B$\u0004\u0001\u0004Y\u0012!\u0001=\t\u000b%\u001b\u0001\u0019A\u000e\u0002\u0003e\fQ!Z9vSZ$2\u0001T(Q!\t\tR*\u0003\u0002O%\t9!i\\8mK\u0006t\u0007\"B$\u0005\u0001\u0004Y\u0002\"B%\u0005\u0001\u0004Y\u0012AA4u)\ra5\u000b\u0016\u0005\u0006\u000f\u0016\u0001\ra\u0007\u0005\u0006\u0013\u0016\u0001\raG\u0001\u0005OR,\u0017\u000fF\u0002M/bCQa\u0012\u0004A\u0002mAQ!\u0013\u0004A\u0002m\t!\u0001\u001c;\u0015\u00071[F\fC\u0003H\u000f\u0001\u00071\u0004C\u0003J\u000f\u0001\u00071$\u0001\u0003mi\u0016\fHc\u0001'`A\")q\t\u0003a\u00017!)\u0011\n\u0003a\u00017\u0001"
)
public interface ScalaPartialOrderingWrapper extends PartialOrdering {
   PartialOrder partialOrder();

   // $FF: synthetic method
   static Option tryCompare$(final ScalaPartialOrderingWrapper $this, final Object x, final Object y) {
      return $this.tryCompare(x, y);
   }

   default Option tryCompare(final Object x, final Object y) {
      return this.partialOrder().tryCompare(x, y);
   }

   // $FF: synthetic method
   static boolean equiv$(final ScalaPartialOrderingWrapper $this, final Object x, final Object y) {
      return $this.equiv(x, y);
   }

   default boolean equiv(final Object x, final Object y) {
      return this.partialOrder().eqv(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final ScalaPartialOrderingWrapper $this, final Object x, final Object y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Object x, final Object y) {
      return this.partialOrder().gt(x, y);
   }

   // $FF: synthetic method
   static boolean gteq$(final ScalaPartialOrderingWrapper $this, final Object x, final Object y) {
      return $this.gteq(x, y);
   }

   default boolean gteq(final Object x, final Object y) {
      return this.partialOrder().gteqv(x, y);
   }

   // $FF: synthetic method
   static boolean lt$(final ScalaPartialOrderingWrapper $this, final Object x, final Object y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Object x, final Object y) {
      return this.partialOrder().lt(x, y);
   }

   // $FF: synthetic method
   static boolean lteq$(final ScalaPartialOrderingWrapper $this, final Object x, final Object y) {
      return $this.lteq(x, y);
   }

   default boolean lteq(final Object x, final Object y) {
      return this.partialOrder().lteqv(x, y);
   }

   static void $init$(final ScalaPartialOrderingWrapper $this) {
   }
}
