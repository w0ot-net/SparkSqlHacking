package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.SortedMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005Q3AAB\u0004\u0001\u001d!A\u0011\u0007\u0001B\u0001B\u0003-!\u0007\u0003\u00054\u0001\t\u0005\t\u0015a\u00035\u0011\u0015)\u0004\u0001\"\u00017\u0011\u0019)\u0004\u0001\"\u0001\bw!)Q\n\u0001C\u0001\u001d\ni1k\u001c:uK\u0012l\u0015\r\u001d%bg\"T!\u0001C\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0006\f\u0003\u0019YWM\u001d8fY*\tA\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0004\u001fY\u00193c\u0001\u0001\u0011KA!\u0011C\u0005\u000b#\u001b\u00059\u0011BA\n\b\u0005-\u0019vN\u001d;fI6\u000b\u0007/R9\u0011\u0005U1B\u0002\u0001\u0003\u0006/\u0001\u0011\r\u0001\u0007\u0002\u0002\u0017F\u0011\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\b\u001d>$\b.\u001b8h!\tQ\u0002%\u0003\u0002\"7\t\u0019\u0011I\\=\u0011\u0005U\u0019C!\u0002\u0013\u0001\u0005\u0004A\"!\u0001,\u0011\u0007\u0019:\u0013&D\u0001\n\u0013\tA\u0013B\u0001\u0003ICND\u0007\u0003\u0002\u00160)\tj\u0011a\u000b\u0006\u0003Y5\n\u0011\"[7nkR\f'\r\\3\u000b\u00059Z\u0012AC2pY2,7\r^5p]&\u0011\u0001g\u000b\u0002\n'>\u0014H/\u001a3NCB\f\u0011A\u0016\t\u0004M\u001d\u0012\u0013!A&\u0011\u0007\u0019:C#\u0001\u0004=S:LGO\u0010\u000b\u0002oQ\u0019\u0001(\u000f\u001e\u0011\tE\u0001AC\t\u0005\u0006c\r\u0001\u001dA\r\u0005\u0006g\r\u0001\u001d\u0001\u000e\u000b\u0005qqj$\tC\u00032\t\u0001\u0007!\u0007C\u0003?\t\u0001\u0007q(A\u0001P!\r1\u0003\tF\u0005\u0003\u0003&\u0011Qa\u0014:eKJDQa\r\u0003A\u0002QBc\u0001\u0002#H\u0011*[\u0005C\u0001\u000eF\u0013\t15D\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-I\u0001J\u0003!+6/\u001a\u0011uQ\u0016\u00043m\u001c8tiJ,8\r^8sA};\u0018\u000e\u001e5pkR|\u0006e\u0014:eKJ\u0004\u0013N\\:uK\u0006$G\u0006I:j]\u000e,\u0007e\u0014:eKJ\u0004\u0013n\u001d\u0011o_R\u0004#/Z9vSJ,G-A\u0003tS:\u001cW-I\u0001M\u0003!\u0011dF\r\u00181[5\u001b\u0014\u0001\u00025bg\"$\"a\u0014*\u0011\u0005i\u0001\u0016BA)\u001c\u0005\rIe\u000e\u001e\u0005\u0006'\u0016\u0001\r!K\u0001\u0002q\u0002"
)
public class SortedMapHash extends SortedMapEq implements Hash {
   private final Hash V;
   private final Hash K;

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

   public int hash(final SortedMap x) {
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
   public static final void $anonfun$hash$1(final SortedMapHash $this, final IntRef a$1, final IntRef b$1, final IntRef c$1, final IntRef n$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v = x0$1._2();
         int h = StaticMethods$.MODULE$.product2HashWithPrefix($this.K.hash(k), $this.V.hash(v), "Tuple2");
         a$1.elem += h;
         b$1.elem ^= h;
         c$1.elem = StaticMethods$.MODULE$.updateUnorderedHashC(c$1.elem, h);
         ++n$1.elem;
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public SortedMapHash(final Hash V, final Hash K) {
      super(V);
      this.V = V;
      this.K = K;
   }

   /** @deprecated */
   public SortedMapHash(final Hash V, final Order O, final Hash K) {
      this(V, K);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
