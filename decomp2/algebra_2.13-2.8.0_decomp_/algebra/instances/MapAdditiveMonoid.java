package algebra.instances;

import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q3Aa\u0002\u0005\u0001\u001b!AA\u0007\u0001B\u0001B\u0003-Q\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003?\u0001\u0011\u0005q\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0003G\u0001\u0011\u0005s\tC\u0003P\u0001\u0011\u0005\u0003KA\tNCB\fE\rZ5uSZ,Wj\u001c8pS\u0012T!!\u0003\u0006\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"A\u0006\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001Qc\u0001\b)eM\u0019\u0001aD\u000b\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g!\r1\u0012dG\u0007\u0002/)\u0011\u0001DC\u0001\u0005e&tw-\u0003\u0002\u001b/\tq\u0011\t\u001a3ji&4X-T8o_&$\u0007\u0003\u0002\u000f$MEr!!H\u0011\u0011\u0005y\tR\"A\u0010\u000b\u0005\u0001b\u0011A\u0002\u001fs_>$h(\u0003\u0002##\u00051\u0001K]3eK\u001aL!\u0001J\u0013\u0003\u00075\u000b\u0007O\u0003\u0002##A\u0011q\u0005\u000b\u0007\u0001\t\u0015I\u0003A1\u0001+\u0005\u0005Y\u0015CA\u0016/!\t\u0001B&\u0003\u0002.#\t9aj\u001c;iS:<\u0007C\u0001\t0\u0013\t\u0001\u0014CA\u0002B]f\u0004\"a\n\u001a\u0005\u000bM\u0002!\u0019\u0001\u0016\u0003\u0003Y\u000b\u0011A\u0016\t\u0004-Y\n\u0014BA\u001c\u0018\u0005E\tE\rZ5uSZ,7+Z7jOJ|W\u000f]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\"\"aO\u001f\u0011\tq\u0002a%M\u0007\u0002\u0011!)AG\u0001a\u0002k\u0005!!0\u001a:p+\u0005Y\u0012\u0001\u00029mkN$2a\u0007\"E\u0011\u0015\u0019E\u00011\u0001\u001c\u0003\tA8\u000fC\u0003F\t\u0001\u00071$\u0001\u0002zg\u0006!1/^7O)\rY\u0002J\u0013\u0005\u0006\u0013\u0016\u0001\raG\u0001\u0002C\")1*\u0002a\u0001\u0019\u0006\ta\u000e\u0005\u0002\u0011\u001b&\u0011a*\u0005\u0002\u0004\u0013:$\u0018aA:v[R\u00111$\u0015\u0005\u0006%\u001a\u0001\raU\u0001\u0003CN\u00042\u0001V-\u001c\u001d\t)vK\u0004\u0002\u001f-&\t!#\u0003\u0002Y#\u00059\u0001/Y2lC\u001e,\u0017B\u0001.\\\u0005=!&/\u0019<feN\f'\r\\3P]\u000e,'B\u0001-\u0012\u0001"
)
public class MapAdditiveMonoid implements AdditiveMonoid {
   private final AdditiveSemigroup V;

   public Monoid additive() {
      return AdditiveMonoid.additive$(this);
   }

   public Monoid additive$mcD$sp() {
      return AdditiveMonoid.additive$mcD$sp$(this);
   }

   public Monoid additive$mcF$sp() {
      return AdditiveMonoid.additive$mcF$sp$(this);
   }

   public Monoid additive$mcI$sp() {
      return AdditiveMonoid.additive$mcI$sp$(this);
   }

   public Monoid additive$mcJ$sp() {
      return AdditiveMonoid.additive$mcJ$sp$(this);
   }

   public double zero$mcD$sp() {
      return AdditiveMonoid.zero$mcD$sp$(this);
   }

   public float zero$mcF$sp() {
      return AdditiveMonoid.zero$mcF$sp$(this);
   }

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero(final Object a, final Eq ev) {
      return AdditiveMonoid.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcI$sp$(this, as);
   }

   public long sum$mcJ$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcJ$sp$(this, as);
   }

   public Option trySum(final IterableOnce as) {
      return AdditiveMonoid.trySum$(this, as);
   }

   public double plus$mcD$sp(final double x, final double y) {
      return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
   }

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public Object positiveSumN(final Object a, final int n) {
      return AdditiveSemigroup.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public Map zero() {
      return .MODULE$.Map().empty();
   }

   public Map plus(final Map xs, final Map ys) {
      return xs.size() <= ys.size() ? (Map)xs.foldLeft(ys, (x0$1, x1$1) -> {
         Tuple2 var5 = new Tuple2(x0$1, x1$1);
         if (var5 != null) {
            Map my = (Map)var5._1();
            Tuple2 var7 = (Tuple2)var5._2();
            if (var7 != null) {
               Object k = var7._1();
               Object x = var7._2();
               Option var10 = my.get(k);
               Object var4;
               if (var10 instanceof Some) {
                  Some var11 = (Some)var10;
                  Object y = var11.value();
                  var4 = this.V.plus(x, y);
               } else {
                  if (!scala.None..MODULE$.equals(var10)) {
                     throw new MatchError(var10);
                  }

                  var4 = x;
               }

               Map var3 = (Map)my.updated(k, var4);
               return var3;
            }
         }

         throw new MatchError(var5);
      }) : (Map)ys.foldLeft(xs, (x0$2, x1$2) -> {
         Tuple2 var5 = new Tuple2(x0$2, x1$2);
         if (var5 != null) {
            Map mx = (Map)var5._1();
            Tuple2 var7 = (Tuple2)var5._2();
            if (var7 != null) {
               Object k = var7._1();
               Object y = var7._2();
               Option var10 = mx.get(k);
               Object var4;
               if (var10 instanceof Some) {
                  Some var11 = (Some)var10;
                  Object x = var11.value();
                  var4 = this.V.plus(x, y);
               } else {
                  if (!scala.None..MODULE$.equals(var10)) {
                     throw new MatchError(var10);
                  }

                  var4 = y;
               }

               Map var3 = (Map)mx.updated(k, var4);
               return var3;
            }
         }

         throw new MatchError(var5);
      });
   }

   public Map sumN(final Map a, final int n) {
      Map var10000;
      if (n > 0) {
         var10000 = (Map)a.map((x0$1) -> {
            if (x0$1 != null) {
               Object k = x0$1._1();
               Object v = x0$1._2();
               Tuple2 var3 = new Tuple2(k, this.V.sumN(v, n));
               return var3;
            } else {
               throw new MatchError(x0$1);
            }
         });
      } else {
         if (n != 0) {
            throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString("Illegal negative exponent to sumN: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
         }

         var10000 = this.zero();
      }

      return var10000;
   }

   public Map sum(final IterableOnce as) {
      scala.collection.mutable.Map acc = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      scala.collection.IterableOnceExtensionMethods..MODULE$.foreach$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), (m) -> {
         $anonfun$sum$1(this, acc, m);
         return BoxedUnit.UNIT;
      });
      return cats.kernel.instances.StaticMethods..MODULE$.wrapMutableMap(acc);
   }

   // $FF: synthetic method
   public static final void $anonfun$sum$1(final MapAdditiveMonoid $this, final scala.collection.mutable.Map acc$1, final Map m) {
      Iterator it = m.iterator();

      while(it.hasNext()) {
         Tuple2 var7 = (Tuple2)it.next();
         if (var7 == null) {
            throw new MatchError(var7);
         }

         Object k = var7._1();
         Object y = var7._2();
         Tuple2 var4 = new Tuple2(k, y);
         Object k = var4._1();
         Object y = var4._2();
         Option var12 = acc$1.get(k);
         if (scala.None..MODULE$.equals(var12)) {
            acc$1.update(k, y);
            BoxedUnit var3 = BoxedUnit.UNIT;
         } else {
            if (!(var12 instanceof Some)) {
               throw new MatchError(var12);
            }

            Some var13 = (Some)var12;
            Object x = var13.value();
            acc$1.update(k, $this.V.plus(x, y));
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
      }

   }

   public MapAdditiveMonoid(final AdditiveSemigroup V) {
      this.V = V;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
