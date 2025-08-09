package cats.kernel.instances;

import cats.kernel.Hash;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A\u0001B\u0003\u0001\u0019!A!\u0007\u0001B\u0001B\u0003-1\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003:\u0001\u0011\u0005!HA\u0004NCBD\u0015m\u001d5\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001)2!\u0004\u000b\"'\r\u0001ab\t\t\u0005\u001fA\u0011\u0002%D\u0001\u0006\u0013\t\tRAA\u0003NCB,\u0015\u000f\u0005\u0002\u0014)1\u0001A!B\u000b\u0001\u0005\u00041\"!A&\u0012\u0005]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"a\u0002(pi\"Lgn\u001a\t\u00031yI!aH\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0014C\u0011)!\u0005\u0001b\u0001-\t\ta\u000bE\u0002%K\u001dj\u0011aB\u0005\u0003M\u001d\u0011A\u0001S1tQB!\u0001f\f\n!\u001d\tIS\u0006\u0005\u0002+35\t1F\u0003\u0002-\u0017\u00051AH]8pizJ!AL\r\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014GA\u0002NCBT!AL\r\u0002\u0003Y\u00032\u0001J\u0013!\u0003\u0019a\u0014N\\5u}Q\ta\u0007\u0006\u00028qA!q\u0002\u0001\n!\u0011\u0015\u0011$\u0001q\u00014\u0003\u0011A\u0017m\u001d5\u0015\u0005mr\u0004C\u0001\r=\u0013\ti\u0014DA\u0002J]RDQaP\u0002A\u0002\u001d\n\u0011\u0001\u001f"
)
public class MapHash extends MapEq implements Hash {
   private final Hash V;

   public int hash$mcZ$sp(final boolean x) {
      return Hash.hash$mcZ$sp$(this, x);
   }

   public int hash$mcB$sp(final byte x) {
      return Hash.hash$mcB$sp$(this, x);
   }

   public int hash$mcC$sp(final char x) {
      return Hash.hash$mcC$sp$(this, x);
   }

   public int hash$mcD$sp(final double x) {
      return Hash.hash$mcD$sp$(this, x);
   }

   public int hash$mcF$sp(final float x) {
      return Hash.hash$mcF$sp$(this, x);
   }

   public int hash$mcI$sp(final int x) {
      return Hash.hash$mcI$sp$(this, x);
   }

   public int hash$mcJ$sp(final long x) {
      return Hash.hash$mcJ$sp$(this, x);
   }

   public int hash$mcS$sp(final short x) {
      return Hash.hash$mcS$sp$(this, x);
   }

   public int hash$mcV$sp(final BoxedUnit x) {
      return Hash.hash$mcV$sp$(this, x);
   }

   public int hash(final Map x) {
      IntRef a = IntRef.create(0);
      IntRef b = IntRef.create(0);
      IntRef n = IntRef.create(0);
      IntRef c = IntRef.create(1);
      x.foreach((x0$1) -> {
         $anonfun$hash$1(this, a, b, c, n, x0$1);
         return BoxedUnit.UNIT;
      });
      int h = .MODULE$.mapSeed();
      h = .MODULE$.mix(h, a.elem);
      h = .MODULE$.mix(h, b.elem);
      h = .MODULE$.mixLast(h, c.elem);
      return .MODULE$.finalizeHash(h, n.elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$hash$1(final MapHash $this, final IntRef a$1, final IntRef b$1, final IntRef c$1, final IntRef n$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v = x0$1._2();
         int h = StaticMethods$.MODULE$.product2HashWithPrefix(k.hashCode(), $this.V.hash(v), "Tuple2");
         a$1.elem += h;
         b$1.elem ^= h;
         c$1.elem = StaticMethods$.MODULE$.updateUnorderedHashC(c$1.elem, h);
         ++n$1.elem;
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public MapHash(final Hash V) {
      super(V);
      this.V = V;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
