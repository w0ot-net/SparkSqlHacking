package cats.kernel.instances;

import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t3A\u0001B\u0003\u0001\u0019!A\u0011\u0007\u0001B\u0001B\u0003-!\u0007C\u00034\u0001\u0011\u0005A\u0007C\u0003:\u0001\u0011\u0005!HA\u0003NCB,\u0015O\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001+\riQeL\n\u0004\u00019!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u0016-ai\u0011aB\u0005\u0003/\u001d\u0011!!R9\u0011\te\u00013E\f\b\u00035y\u0001\"a\u0007\t\u000e\u0003qQ!!H\u0006\u0002\rq\u0012xn\u001c;?\u0013\ty\u0002#\u0001\u0004Qe\u0016$WMZ\u0005\u0003C\t\u00121!T1q\u0015\ty\u0002\u0003\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u00049#!A&\u0012\u0005!Z\u0003CA\b*\u0013\tQ\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=a\u0013BA\u0017\u0011\u0005\r\te.\u001f\t\u0003I=\"Q\u0001\r\u0001C\u0002\u001d\u0012\u0011AV\u0001\u0002-B\u0019QC\u0006\u0018\u0002\rqJg.\u001b;?)\u0005)DC\u0001\u001c9!\u00119\u0004a\t\u0018\u000e\u0003\u0015AQ!\r\u0002A\u0004I\n1!Z9w)\rYd\b\u0011\t\u0003\u001fqJ!!\u0010\t\u0003\u000f\t{w\u000e\\3b]\")qh\u0001a\u00011\u0005\t\u0001\u0010C\u0003B\u0007\u0001\u0007\u0001$A\u0001z\u0001"
)
public class MapEq implements Eq {
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

   public boolean eqv(final Map x, final Map y) {
      return x == y ? true : x.size() == y.size() && x.forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$eqv$1(this, y, x0$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$1(final MapEq $this, final Map y$1, final Tuple2 x0$1) {
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

   public MapEq(final Eq V) {
      this.V = V;
      Eq.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
