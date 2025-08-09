package algebra.instances;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Semiring;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.mutable.Shrinkable;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005i3AAB\u0004\u0001\u0019!AA\u0007\u0001B\u0001B\u0003-Q\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003<\u0001\u0011\u0005A\bC\u0003B\u0001\u0011\u0005#\tC\u0003K\u0001\u0011\u00053JA\u0006NCB\u001cV-\\5sS:<'B\u0001\u0005\n\u0003%Ign\u001d;b]\u000e,7OC\u0001\u000b\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!F\u0002\u000e)\u0005\u001a2\u0001\u0001\b$!\u0011y\u0001C\u0005\u0011\u000e\u0003\u001dI!!E\u0004\u0003#5\u000b\u0007/\u00113eSRLg/Z'p]>LG\r\u0005\u0002\u0014)1\u0001A!B\u000b\u0001\u0005\u00041\"!A&\u0012\u0005]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"a\u0002(pi\"Lgn\u001a\t\u00031yI!aH\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0014C\u0011)!\u0005\u0001b\u0001-\t\ta\u000bE\u0002%O%j\u0011!\n\u0006\u0003M%\tAA]5oO&\u0011\u0001&\n\u0002\t'\u0016l\u0017N]5oOB!!&\r\n!\u001d\tYs\u0006\u0005\u0002-35\tQF\u0003\u0002/\u0017\u00051AH]8pizJ!\u0001M\r\u0002\rA\u0013X\rZ3g\u0013\t\u00114GA\u0002NCBT!\u0001M\r\u0002\u0003Y\u00032\u0001J\u0014!\u0003\u0019a\u0014N\\5u}Q\t\u0001\b\u0006\u0002:uA!q\u0002\u0001\n!\u0011\u0015!$\u0001q\u00016\u0003\u0015!\u0018.\\3t)\rISh\u0010\u0005\u0006}\r\u0001\r!K\u0001\u0003qNDQ\u0001Q\u0002A\u0002%\n!!_:\u0002\u0007A|w\u000fF\u0002*\u0007\u0016CQ\u0001\u0012\u0003A\u0002%\n\u0011\u0001\u001f\u0005\u0006\r\u0012\u0001\raR\u0001\u0002]B\u0011\u0001\u0004S\u0005\u0003\u0013f\u00111!\u00138u\u0003)!(/\u001f)s_\u0012,8\r\u001e\u000b\u0003\u0019>\u00032\u0001G'*\u0013\tq\u0015D\u0001\u0004PaRLwN\u001c\u0005\u0006!\u0016\u0001\r!U\u0001\u0003CN\u00042AU,*\u001d\t\u0019VK\u0004\u0002-)&\t!$\u0003\u0002W3\u00059\u0001/Y2lC\u001e,\u0017B\u0001-Z\u0005=!&/\u0019<feN\f'\r\\3P]\u000e,'B\u0001,\u001a\u0001"
)
public class MapSemiring extends MapAdditiveMonoid implements Semiring {
   private final Semiring V;

   public Semigroup multiplicative() {
      return MultiplicativeSemigroup.multiplicative$(this);
   }

   public Semigroup multiplicative$mcD$sp() {
      return MultiplicativeSemigroup.multiplicative$mcD$sp$(this);
   }

   public Semigroup multiplicative$mcF$sp() {
      return MultiplicativeSemigroup.multiplicative$mcF$sp$(this);
   }

   public Semigroup multiplicative$mcI$sp() {
      return MultiplicativeSemigroup.multiplicative$mcI$sp$(this);
   }

   public Semigroup multiplicative$mcJ$sp() {
      return MultiplicativeSemigroup.multiplicative$mcJ$sp$(this);
   }

   public double times$mcD$sp(final double x, final double y) {
      return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
   }

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
   }

   public Object positivePow(final Object a, final int n) {
      return MultiplicativeSemigroup.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
   }

   public long positivePow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
   }

   public CommutativeMonoid additive() {
      return AdditiveCommutativeMonoid.additive$(this);
   }

   public CommutativeMonoid additive$mcD$sp() {
      return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
   }

   public CommutativeMonoid additive$mcF$sp() {
      return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
   }

   public CommutativeMonoid additive$mcI$sp() {
      return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
   }

   public CommutativeMonoid additive$mcJ$sp() {
      return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
   }

   public Map times(final Map xs, final Map ys) {
      return xs.size() <= ys.size() ? (Map)xs.foldLeft(.MODULE$.Map().empty(), (x0$1, x1$1) -> {
         Tuple2 var6 = new Tuple2(x0$1, x1$1);
         if (var6 != null) {
            Map m = (Map)var6._1();
            Tuple2 var8 = (Tuple2)var6._2();
            if (var8 != null) {
               Object k = var8._1();
               Object x = var8._2();
               Option var11 = ys.get(k);
               Map var5;
               if (var11 instanceof Some) {
                  Some var12 = (Some)var11;
                  Object y = var12.value();
                  var5 = (Map)m.updated(k, this.V.times(x, y));
               } else {
                  if (!scala.None..MODULE$.equals(var11)) {
                     throw new MatchError(var11);
                  }

                  var5 = m;
               }

               return var5;
            }
         }

         throw new MatchError(var6);
      }) : (Map)ys.foldLeft(.MODULE$.Map().empty(), (x0$2, x1$2) -> {
         Tuple2 var6 = new Tuple2(x0$2, x1$2);
         if (var6 != null) {
            Map m = (Map)var6._1();
            Tuple2 var8 = (Tuple2)var6._2();
            if (var8 != null) {
               Object k = var8._1();
               Object y = var8._2();
               Option var11 = xs.get(k);
               Map var5;
               if (var11 instanceof Some) {
                  Some var12 = (Some)var11;
                  Object x = var12.value();
                  var5 = (Map)m.updated(k, this.V.times(x, y));
               } else {
                  if (!scala.None..MODULE$.equals(var11)) {
                     throw new MatchError(var11);
                  }

                  var5 = m;
               }

               return var5;
            }
         }

         throw new MatchError(var6);
      });
   }

   public Map pow(final Map x, final int n) {
      if (n < 1) {
         throw new IllegalArgumentException((new StringBuilder(23)).append("non-positive exponent: ").append(n).toString());
      } else {
         return n == 1 ? x : (Map)x.map((x0$1) -> {
            if (x0$1 != null) {
               Object k = x0$1._1();
               Object v = x0$1._2();
               Tuple2 var3 = new Tuple2(k, this.V.pow(v, n));
               return var3;
            } else {
               throw new MatchError(x0$1);
            }
         });
      }
   }

   public Option tryProduct(final IterableOnce as) {
      Object var10000;
      if (scala.collection.IterableOnceExtensionMethods..MODULE$.isEmpty$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as))) {
         var10000 = scala.None..MODULE$;
      } else {
         scala.collection.mutable.Map acc = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
         BooleanRef ready = BooleanRef.create(false);
         scala.collection.IterableOnceExtensionMethods..MODULE$.foreach$extension(scala.collection.IterableOnce..MODULE$.iterableOnceExtensionMethods(as), (m) -> {
            $anonfun$tryProduct$1(this, ready, acc, m);
            return BoxedUnit.UNIT;
         });
         var10000 = new Some(cats.kernel.instances.StaticMethods..MODULE$.wrapMutableMap(acc));
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$tryProduct$1(final MapSemiring $this, final BooleanRef ready$1, final scala.collection.mutable.Map acc$2, final Map m) {
      if (ready$1.elem) {
         Iterator it = acc$2.iterator();

         while(it.hasNext()) {
            Tuple2 var8 = (Tuple2)it.next();
            if (var8 == null) {
               throw new MatchError(var8);
            }

            Object k = var8._1();
            Object x = var8._2();
            Tuple2 var5 = new Tuple2(k, x);
            Object k = var5._1();
            Object x = var5._2();
            Option var13 = m.get(k);
            if (scala.None..MODULE$.equals(var13)) {
               Shrinkable var4 = acc$2.$minus$eq(k);
            } else {
               if (!(var13 instanceof Some)) {
                  throw new MatchError(var13);
               }

               Some var14 = (Some)var13;
               Object y = var14.value();
               acc$2.update(k, $this.V.times(x, y));
               BoxedUnit var17 = BoxedUnit.UNIT;
            }
         }
      } else {
         Iterator it = m.iterator();

         while(it.hasNext()) {
            acc$2.$plus$eq(it.next());
         }

         ready$1.elem = true;
      }

   }

   public MapSemiring(final Semiring V) {
      super(V);
      this.V = V;
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
