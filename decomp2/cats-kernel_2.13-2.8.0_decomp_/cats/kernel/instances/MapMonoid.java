package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import cats.kernel.Semigroup$;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005I3AAB\u0004\u0001\u001d!A1\u0007\u0001B\u0001B\u0003-A\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003>\u0001\u0011\u0005a\bC\u0003@\u0001\u0011\u0005\u0001\tC\u0003F\u0001\u0011\u0005cIA\u0005NCBluN\\8jI*\u0011\u0001\"C\u0001\nS:\u001cH/\u00198dKNT!AC\u0006\u0002\r-,'O\\3m\u0015\u0005a\u0011\u0001B2biN\u001c\u0001!F\u0002\u0010OE\u001a2\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0019q\u0003\u0007\u000e\u000e\u0003%I!!G\u0005\u0003\r5{gn\\5e!\u0011Y\"%\n\u0019\u000f\u0005q\u0001\u0003CA\u000f\u0013\u001b\u0005q\"BA\u0010\u000e\u0003\u0019a$o\\8u}%\u0011\u0011EE\u0001\u0007!J,G-\u001a4\n\u0005\r\"#aA'ba*\u0011\u0011E\u0005\t\u0003M\u001db\u0001\u0001B\u0003)\u0001\t\u0007\u0011FA\u0001L#\tQS\u0006\u0005\u0002\u0012W%\u0011AF\u0005\u0002\b\u001d>$\b.\u001b8h!\t\tb&\u0003\u00020%\t\u0019\u0011I\\=\u0011\u0005\u0019\nD!\u0002\u001a\u0001\u0005\u0004I#!\u0001,\u0002\u0003Y\u00032aF\u001b1\u0013\t1\u0014BA\u0005TK6LwM]8va\u00061A(\u001b8jiz\"\u0012!\u000f\u000b\u0003uq\u0002Ba\u000f\u0001&a5\tq\u0001C\u00034\u0005\u0001\u000fA'A\u0003f[B$\u00180F\u0001\u001b\u0003\u001d\u0019w.\u001c2j]\u0016$2AG!D\u0011\u0015\u0011E\u00011\u0001\u001b\u0003\tA8\u000fC\u0003E\t\u0001\u0007!$\u0001\u0002zg\u0006Q1m\\7cS:,\u0017\t\u001c7\u0015\u0005i9\u0005\"\u0002%\u0006\u0001\u0004I\u0015a\u0001=tgB\u0019!j\u0014\u000e\u000f\u0005-keBA\u000fM\u0013\u0005\u0019\u0012B\u0001(\u0013\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001U)\u0003\u0019%#XM]1cY\u0016|enY3\u000b\u00059\u0013\u0002"
)
public class MapMonoid implements Monoid {
   private final Semigroup V;

   public double empty$mcD$sp() {
      return Monoid.empty$mcD$sp$(this);
   }

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public int empty$mcI$sp() {
      return Monoid.empty$mcI$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
   }

   public boolean isEmpty(final Object a, final Eq ev) {
      return Monoid.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid.isEmpty$mcI$sp$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid.isEmpty$mcJ$sp$(this, a, ev);
   }

   public Object combineN(final Object a, final int n) {
      return Monoid.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Monoid.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Monoid.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Monoid.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Monoid.combineN$mcJ$sp$(this, a, n);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid.combineAll$mcD$sp$(this, as);
   }

   public float combineAll$mcF$sp(final IterableOnce as) {
      return Monoid.combineAll$mcF$sp$(this, as);
   }

   public int combineAll$mcI$sp(final IterableOnce as) {
      return Monoid.combineAll$mcI$sp$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid.combineAll$mcJ$sp$(this, as);
   }

   public Option combineAllOption(final IterableOnce as) {
      return Monoid.combineAllOption$(this, as);
   }

   public Monoid reverse() {
      return Monoid.reverse$(this);
   }

   public Monoid reverse$mcD$sp() {
      return Monoid.reverse$mcD$sp$(this);
   }

   public Monoid reverse$mcF$sp() {
      return Monoid.reverse$mcF$sp$(this);
   }

   public Monoid reverse$mcI$sp() {
      return Monoid.reverse$mcI$sp$(this);
   }

   public Monoid reverse$mcJ$sp() {
      return Monoid.reverse$mcJ$sp$(this);
   }

   public double combine$mcD$sp(final double x, final double y) {
      return Semigroup.combine$mcD$sp$(this, x, y);
   }

   public float combine$mcF$sp(final float x, final float y) {
      return Semigroup.combine$mcF$sp$(this, x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return Semigroup.combine$mcI$sp$(this, x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public Object repeatedCombineN(final Object a, final int n) {
      return Semigroup.repeatedCombineN$(this, a, n);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public Semigroup intercalate(final Object middle) {
      return Semigroup.intercalate$(this, middle);
   }

   public Semigroup intercalate$mcD$sp(final double middle) {
      return Semigroup.intercalate$mcD$sp$(this, middle);
   }

   public Semigroup intercalate$mcF$sp(final float middle) {
      return Semigroup.intercalate$mcF$sp$(this, middle);
   }

   public Semigroup intercalate$mcI$sp(final int middle) {
      return Semigroup.intercalate$mcI$sp$(this, middle);
   }

   public Semigroup intercalate$mcJ$sp(final long middle) {
      return Semigroup.intercalate$mcJ$sp$(this, middle);
   }

   public Map empty() {
      return .MODULE$.Map().empty();
   }

   public Map combine(final Map xs, final Map ys) {
      return xs.size() <= ys.size() ? (Map)xs.foldLeft(ys, (x0$1, x1$1) -> {
         Tuple2 var4 = new Tuple2(x0$1, x1$1);
         if (var4 != null) {
            Map my = (Map)var4._1();
            Tuple2 var6 = (Tuple2)var4._2();
            if (var6 != null) {
               Object k = var6._1();
               Object x = var6._2();
               Map var3 = (Map)my.updated(k, Semigroup$.MODULE$.maybeCombine(x, my.get(k), this.V));
               return var3;
            }
         }

         throw new MatchError(var4);
      }) : (Map)ys.foldLeft(xs, (x0$2, x1$2) -> {
         Tuple2 var4 = new Tuple2(x0$2, x1$2);
         if (var4 != null) {
            Map mx = (Map)var4._1();
            Tuple2 var6 = (Tuple2)var4._2();
            if (var6 != null) {
               Object k = var6._1();
               Object y = var6._2();
               Map var3 = (Map)mx.updated(k, Semigroup$.MODULE$.maybeCombine(mx.get(k), y, this.V));
               return var3;
            }
         }

         throw new MatchError(var4);
      });
   }

   public Map combineAll(final IterableOnce xss) {
      scala.collection.mutable.Map acc = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      xss.iterator().foreach((m) -> {
         $anonfun$combineAll$1(this, acc, m);
         return BoxedUnit.UNIT;
      });
      return StaticMethods$.MODULE$.wrapMutableMap(acc);
   }

   // $FF: synthetic method
   public static final void $anonfun$combineAll$1(final MapMonoid $this, final scala.collection.mutable.Map acc$1, final Map m) {
      Iterator it = m.iterator();

      while(it.hasNext()) {
         Tuple2 var6 = (Tuple2)it.next();
         if (var6 == null) {
            throw new MatchError(var6);
         }

         Object k = var6._1();
         Object v = var6._2();
         Tuple2 var3 = new Tuple2(k, v);
         Object k = var3._1();
         Object v = var3._2();
         acc$1.update(k, Semigroup$.MODULE$.maybeCombine(acc$1.get(k), v, $this.V));
      }

   }

   public MapMonoid(final Semigroup V) {
      this.V = V;
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
