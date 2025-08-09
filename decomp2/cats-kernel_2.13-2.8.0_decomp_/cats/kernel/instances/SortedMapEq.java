package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.SortedMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005E3A!\u0002\u0004\u0001\u001b!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007C\u00032\u0001\u0011\u0005!\u0007\u0003\u00042\u0001\u0011\u0005aa\u000e\u0005\u0006\u0011\u0002!\t!\u0013\u0002\f'>\u0014H/\u001a3NCB,\u0015O\u0003\u0002\b\u0011\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0013)\taa[3s]\u0016d'\"A\u0006\u0002\t\r\fGo]\u0002\u0001+\rq1%L\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\rE\u0002\u0017/ei\u0011\u0001C\u0005\u00031!\u0011!!R9\u0011\tiy\u0012\u0005L\u0007\u00027)\u0011A$H\u0001\nS6lW\u000f^1cY\u0016T!AH\t\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002!7\tI1k\u001c:uK\u0012l\u0015\r\u001d\t\u0003E\rb\u0001\u0001B\u0003%\u0001\t\u0007QEA\u0001L#\t1\u0013\u0006\u0005\u0002\u0011O%\u0011\u0001&\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0001\"&\u0003\u0002,#\t\u0019\u0011I\\=\u0011\u0005\tjC!\u0002\u0018\u0001\u0005\u0004)#!\u0001,\u0002\u0003Y\u00032AF\f-\u0003\u0019a\u0014N\\5u}Q\t1\u0007\u0006\u00025mA!Q\u0007A\u0011-\u001b\u00051\u0001\"B\u0018\u0003\u0001\b\u0001Dc\u0001\u001b9s!)qf\u0001a\u0001a!)!h\u0001a\u0001w\u0005\tq\nE\u0002\u0017y\u0005J!!\u0010\u0005\u0003\u000b=\u0013H-\u001a:)\r\ry$iQ#G!\t\u0001\u0002)\u0003\u0002B#\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\nA)\u0001%Vg\u0016\u0004C\u000f[3!G>t7\u000f\u001e:vGR|'\u000fI0xSRDw.\u001e;`A=\u0013H-\u001a:!S:\u001cH/Z1eY\u0001\u001a\u0018N\\2fA=\u0013H-\u001a:!SN\u0004cn\u001c;!e\u0016\fX/\u001b:fI\u0006)1/\u001b8dK\u0006\nq)A\u00033]Ir\u0003'A\u0002fcZ$2AS'P!\t\u00012*\u0003\u0002M#\t9!i\\8mK\u0006t\u0007\"\u0002(\u0005\u0001\u0004I\u0012!\u0001=\t\u000bA#\u0001\u0019A\r\u0002\u0003e\u0004"
)
public class SortedMapEq implements Eq {
   private final Eq V;

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

   public boolean eqv(final SortedMap x, final SortedMap y) {
      return x == y ? true : x.size() == y.size() && x.forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$eqv$1(this, y, x0$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$1(final SortedMapEq $this, final SortedMap y$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v1 = x0$1._2();
         Option var8 = y$1.get(k);
         boolean var4;
         if (var8 instanceof Some) {
            Some var9 = (Some)var8;
            Object v2 = var9.value();
            var4 = $this.V.eqv(v1, v2);
         } else {
            if (!.MODULE$.equals(var8)) {
               throw new MatchError(var8);
            }

            var4 = false;
         }

         return var4;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public SortedMapEq(final Eq V) {
      this.V = V;
      Eq.$init$(this);
   }

   /** @deprecated */
   public SortedMapEq(final Eq V, final Order O) {
      this(V);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
