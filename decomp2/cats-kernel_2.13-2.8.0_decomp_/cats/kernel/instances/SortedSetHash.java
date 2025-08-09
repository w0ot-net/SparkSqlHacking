package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Eq$;
import cats.kernel.Hash;
import cats.kernel.Hash$;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.SortedSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005]3AAB\u0004\u0001\u001d!AQ\u0006\u0001B\u0001B\u0003-a\u0006C\u00030\u0001\u0011\u0005\u0001\u0007\u0003\u00040\u0001\u0011\u0005q!\u000e\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u001d\u0002!\te\u0014\u0002\u000e'>\u0014H/\u001a3TKRD\u0015m\u001d5\u000b\u0005!I\u0011!C5ogR\fgnY3t\u0015\tQ1\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0019\u0005!1-\u0019;t\u0007\u0001)\"a\u0004\u0013\u0014\u0007\u0001\u0001b\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0004/aQR\"A\u0005\n\u0005eI!\u0001\u0002%bg\"\u00042a\u0007\u0011#\u001b\u0005a\"BA\u000f\u001f\u0003%IW.\\;uC\ndWM\u0003\u0002 %\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0005b\"!C*peR,GmU3u!\t\u0019C\u0005\u0004\u0001\u0005\u000b\u0015\u0002!\u0019\u0001\u0014\u0003\u0003\u0005\u000b\"a\n\u0016\u0011\u0005EA\u0013BA\u0015\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u0016\n\u00051\u0012\"aA!os\u0006)\u0001.Y:i\u0003B\u0019q\u0003\u0007\u0012\u0002\rqJg.\u001b;?)\u0005\tDC\u0001\u001a5!\r\u0019\u0004AI\u0007\u0002\u000f!)QF\u0001a\u0002]Q\u0019!GN\u001e\t\u000b]\u001a\u0001\u0019\u0001\u001d\u0002\u0003=\u00042aF\u001d#\u0013\tQ\u0014BA\u0003Pe\u0012,'\u000fC\u0003=\u0007\u0001\u0007a&A\u0001iQ\u0019\u0019a(\u0011\"E\u000bB\u0011\u0011cP\u0005\u0003\u0001J\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013aQ\u0001I+N,\u0007\u0005\u001e5fA\r|gn\u001d;sk\u000e$xN\u001d\u0011`o&$\bn\\;u?\u0002z%\u000fZ3sA%t7\u000f^3bI2\u00023/\u001b8dK\u0002z%\u000fZ3sA%\u001c\bE\\8uAI,\u0017/^5sK\u0012\fQa]5oG\u0016\f\u0013AR\u0001\u0006e9\nd\u0006M\u0001\u0005Q\u0006\u001c\b\u000e\u0006\u0002J\u0019B\u0011\u0011CS\u0005\u0003\u0017J\u00111!\u00138u\u0011\u0015iE\u00011\u0001\u001b\u0003\tA8/A\u0002fcZ$2\u0001U*V!\t\t\u0012+\u0003\u0002S%\t9!i\\8mK\u0006t\u0007\"\u0002+\u0006\u0001\u0004Q\u0012AA:2\u0011\u00151V\u00011\u0001\u001b\u0003\t\u0019(\u0007"
)
public class SortedSetHash implements Hash {
   private final Hash hashA;

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

   public int hash(final SortedSet xs) {
      IntRef a = IntRef.create(0);
      IntRef b = IntRef.create(0);
      IntRef n = IntRef.create(0);
      IntRef c = IntRef.create(1);
      xs.foreach((x) -> {
         $anonfun$hash$1(this, a, b, c, n, x);
         return BoxedUnit.UNIT;
      });
      int h = .MODULE$.setSeed();
      h = .MODULE$.mix(h, a.elem);
      h = .MODULE$.mix(h, b.elem);
      h = .MODULE$.mixLast(h, c.elem);
      return .MODULE$.finalizeHash(h, n.elem);
   }

   public boolean eqv(final SortedSet s1, final SortedSet s2) {
      return StaticMethods$.MODULE$.iteratorEq(s1.iterator(), s2.iterator(), Eq$.MODULE$.apply(this.hashA));
   }

   // $FF: synthetic method
   public static final void $anonfun$hash$1(final SortedSetHash $this, final IntRef a$1, final IntRef b$1, final IntRef c$1, final IntRef n$1, final Object x) {
      int h = Hash$.MODULE$.apply($this.hashA).hash(x);
      a$1.elem += h;
      b$1.elem ^= h;
      c$1.elem = StaticMethods$.MODULE$.updateUnorderedHashC(c$1.elem, h);
      ++n$1.elem;
   }

   public SortedSetHash(final Hash hashA) {
      this.hashA = hashA;
      Eq.$init$(this);
   }

   /** @deprecated */
   public SortedSetHash(final Order o, final Hash h) {
      this(h);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
