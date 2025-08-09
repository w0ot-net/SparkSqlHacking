package spire.std;

import algebra.ring.AdditiveMonoid;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u3A!\u0002\u0004\u0001\u0017!A\u0011\t\u0001B\u0001B\u0003-!\t\u0003\u0005D\u0001\t\u0005\t\u0015a\u0003E\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015q\u0005\u0001\"\u0001P\u0005-i\u0015\r\u001d,fGR|'/R9\u000b\u0005\u001dA\u0011aA:uI*\t\u0011\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00071isg\u0005\u0003\u0001\u001bMI\u0004C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\rE\u0002\u0015A\rr!!F\u000f\u000f\u0005YYbBA\f\u001b\u001b\u0005A\"BA\r\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002\u001d\u0011\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0010 \u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\b\u0005\n\u0005\u0005\u0012#AA#r\u0015\tqr\u0004\u0005\u0003%Q-2dBA\u0013'!\t9r\"\u0003\u0002(\u001f\u00051\u0001K]3eK\u001aL!!\u000b\u0016\u0003\u00075\u000b\u0007O\u0003\u0002(\u001fA\u0011A&\f\u0007\u0001\t\u0015q\u0003A1\u00010\u0005\u0005Y\u0015C\u0001\u00194!\tq\u0011'\u0003\u00023\u001f\t9aj\u001c;iS:<\u0007C\u0001\b5\u0013\t)tBA\u0002B]f\u0004\"\u0001L\u001c\u0005\u000ba\u0002!\u0019A\u0018\u0003\u0003Y\u0003\"A\u000f \u000f\u0005mjdBA\f=\u0013\u0005\u0001\u0012B\u0001\u0010\u0010\u0013\ty\u0004I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u001f\u001f\u0005\ta\u000bE\u0002\u0015AY\naa]2bY\u0006\u0014\bc\u0001\u000bFm%\u0011aI\t\u0002\u000f\u0003\u0012$\u0017\u000e^5wK6{gn\\5e\u0003\u0019a\u0014N\\5u}Q\t\u0011\nF\u0002K\u00196\u0003Ba\u0013\u0001,m5\ta\u0001C\u0003B\u0007\u0001\u000f!\tC\u0003D\u0007\u0001\u000fA)A\u0002fcZ$2\u0001U*V!\tq\u0011+\u0003\u0002S\u001f\t9!i\\8mK\u0006t\u0007\"\u0002+\u0005\u0001\u0004\u0019\u0013!\u0001=\t\u000bY#\u0001\u0019A\u0012\u0002\u0003eDC\u0001\u0001-\\9B\u0011a\"W\u0005\u00035>\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0001\u0001"
)
public class MapVectorEq implements Eq {
   private static final long serialVersionUID = 0L;
   private final Eq V;
   private final AdditiveMonoid scalar;

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Eq.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Eq.eqv$mcC$sp$(this, x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Eq.eqv$mcD$sp$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return Eq.eqv$mcF$sp$(this, x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return Eq.eqv$mcI$sp$(this, x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return Eq.eqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return Eq.eqv$mcS$sp$(this, x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.eqv$mcV$sp$(this, x, y);
   }

   public boolean neqv(final Object x, final Object y) {
      return Eq.neqv$(this, x, y);
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Eq.neqv$mcZ$sp$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return Eq.neqv$mcB$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return Eq.neqv$mcC$sp$(this, x, y);
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return Eq.neqv$mcD$sp$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return Eq.neqv$mcF$sp$(this, x, y);
   }

   public boolean neqv$mcI$sp(final int x, final int y) {
      return Eq.neqv$mcI$sp$(this, x, y);
   }

   public boolean neqv$mcJ$sp(final long x, final long y) {
      return Eq.neqv$mcJ$sp$(this, x, y);
   }

   public boolean neqv$mcS$sp(final short x, final short y) {
      return Eq.neqv$mcS$sp$(this, x, y);
   }

   public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Eq.neqv$mcV$sp$(this, x, y);
   }

   public boolean eqv(final Map x, final Map y) {
      return this.loop$1(x, y.iterator());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$2(final MapVectorEq $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object v = x0$1._2();
         boolean var2 = $this.V.eqv(v, $this.scalar.zero());
         return var2;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final boolean loop$1(final Map acc, final Iterator it) {
      while(true) {
         boolean var10000;
         if (it.hasNext()) {
            Tuple2 var7 = (Tuple2)it.next();
            if (var7 == null) {
               throw new MatchError(var7);
            }

            Object k = var7._1();
            Object v0 = var7._2();
            Tuple2 var5 = new Tuple2(k, v0);
            Object k = var5._1();
            Object v0 = var5._2();
            Option var12 = acc.get(k);
            if (var12 instanceof Some) {
               Some var13 = (Some)var12;
               Object v1 = var13.value();
               if (this.V.eqv(v0, v1)) {
                  Map var16 = (Map)acc.$minus(k);
                  it = it;
                  acc = var16;
                  continue;
               }
            }

            if (.MODULE$.equals(var12) && this.V.eqv(v0, this.scalar.zero())) {
               Map var15 = (Map)acc.$minus(k);
               it = it;
               acc = var15;
               continue;
            }

            boolean var4 = false;
            var10000 = var4;
         } else {
            var10000 = acc.forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$eqv$2(this, x0$1)));
         }

         return var10000;
      }
   }

   public MapVectorEq(final Eq V, final AdditiveMonoid scalar) {
      this.V = V;
      this.scalar = scalar;
      Eq.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
