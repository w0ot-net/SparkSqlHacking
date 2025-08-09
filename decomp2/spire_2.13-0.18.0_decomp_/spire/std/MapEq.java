package spire.std;

import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]3A\u0001B\u0003\u0001\u0015!A\u0001\t\u0001B\u0001B\u0003-\u0011\tC\u0003C\u0001\u0011\u00051\tC\u0003I\u0001\u0011\u0005\u0011JA\u0003NCB,\u0015O\u0003\u0002\u0007\u000f\u0005\u00191\u000f\u001e3\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001!F\u0002\fYY\u001aB\u0001\u0001\u0007\u0013qA\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042aE\u0010#\u001d\t!BD\u0004\u0002\u001659\u0011a#G\u0007\u0002/)\u0011\u0001$C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!aG\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011QDH\u0001\ba\u0006\u001c7.Y4f\u0015\tYr!\u0003\u0002!C\t\u0011Q)\u001d\u0006\u0003;y\u0001BaI\u0014+k9\u0011A%\n\t\u0003-9I!A\n\b\u0002\rA\u0013X\rZ3g\u0013\tA\u0013FA\u0002NCBT!A\n\b\u0011\u0005-bC\u0002\u0001\u0003\u0006[\u0001\u0011\rA\f\u0002\u0002\u0017F\u0011qF\r\t\u0003\u001bAJ!!\r\b\u0003\u000f9{G\u000f[5oOB\u0011QbM\u0005\u0003i9\u00111!\u00118z!\tYc\u0007B\u00038\u0001\t\u0007aFA\u0001W!\tITH\u0004\u0002;y9\u0011acO\u0005\u0002\u001f%\u0011QDD\u0005\u0003}}\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!\b\b\u0002\u0003Y\u00032aE\u00106\u0003\u0019a\u0014N\\5u}Q\tA\t\u0006\u0002F\u000fB!a\t\u0001\u00166\u001b\u0005)\u0001\"\u0002!\u0003\u0001\b\t\u0015aA3rmR\u0019!*T(\u0011\u00055Y\u0015B\u0001'\u000f\u0005\u001d\u0011un\u001c7fC:DQAT\u0002A\u0002\t\n\u0011\u0001\u001f\u0005\u0006!\u000e\u0001\rAI\u0001\u0002s\"\"\u0001AU+W!\ti1+\u0003\u0002U\u001d\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0001\u0001"
)
public class MapEq implements Eq {
   private static final long serialVersionUID = 0L;
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
      return x.size() != y.size() ? false : x.forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$eqv$1(this, y, x0$1)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eqv$1(final MapEq $this, final Map y$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         Object k = x0$1._1();
         Object v = x0$1._2();
         Option var8 = y$1.get(k);
         boolean var4;
         if (var8 instanceof Some) {
            Some var9 = (Some)var8;
            Object e = var9.value();
            if ($this.V.eqv(e, v)) {
               var4 = true;
               return var4;
            }
         }

         var4 = false;
         return var4;
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
