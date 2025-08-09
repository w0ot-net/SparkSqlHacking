package cats.kernel;

import scala.Function1;
import scala.Function2;
import scala.Some;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rba\u0002\u000f\u001e!\u0003\r\tA\t\u0005\u0006u\u0001!\ta\u000f\u0005\u0006\u007f\u00011\t\u0001\u0011\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006-\u0002!\ta\u0016\u0005\u00065\u0002!\ta\u0017\u0005\u0006=\u0002!\te\u0018\u0005\u0006K\u0002!\tE\u001a\u0005\u0006S\u0002!\tE\u001b\u0005\u0006[\u0002!\tE\u001c\u0005\u0006c\u0002!\tE\u001d\u0005\u0006k\u0002!\tE\u001e\u0005\u0006s\u0002!\tA_\u0004\b\u0003\u001fi\u0002\u0012AA\t\r\u0019aR\u0004#\u0001\u0002\u0014!9\u00111G\b\u0005\u0002\u0005U\u0002bBA\u001c\u001f\u0011\u0015\u0011\u0011\b\u0005\b\u0003\u001fzA\u0011AA)\u0011\u001d\t)h\u0004C\u0001\u0003oBq!a\"\u0010\t\u0003\tI\tC\u0004\u0002\u001e>!\t!a(\t\u000f\u0005Mv\u0002\"\u0001\u00026\"9\u0011QY\b\u0005\u0002\u0005\u001d\u0007bBAi\u001f\u0011\u0005\u00111\u001b\u0005\b\u0003_|A\u0011AAy\u0011\u001d\typ\u0004C\u0001\u0005\u0003A\u0011B!\u0007\u0010\u0003\u0003%IAa\u0007\u0003\u000b=\u0013H-\u001a:\u000b\u0005yy\u0012AB6fe:,GNC\u0001!\u0003\u0011\u0019\u0017\r^:\u0004\u0001U\u00111\u0005M\n\u0004\u0001\u0011R\u0003CA\u0013)\u001b\u00051#\"A\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005%2#aA!osB\u00191\u0006\f\u0018\u000e\u0003uI!!L\u000f\u0003\u0019A\u000b'\u000f^5bY>\u0013H-\u001a:\u0011\u0005=\u0002D\u0002\u0001\u0003\nc\u0001\u0001\u000b\u0011!AC\u0002I\u0012\u0011!Q\t\u0003g\u0011\u0002\"!\n\u001b\n\u0005U2#a\u0002(pi\"Lgn\u001a\u0015\u0003a]\u0002\"!\n\u001d\n\u0005e2#aC:qK\u000eL\u0017\r\\5{K\u0012\fa\u0001J5oSR$C#\u0001\u001f\u0011\u0005\u0015j\u0014B\u0001 '\u0005\u0011)f.\u001b;\u0002\u000f\r|W\u000e]1sKR\u0019\u0011\t\u0012$\u0011\u0005\u0015\u0012\u0015BA\"'\u0005\rIe\u000e\u001e\u0005\u0006\u000b\n\u0001\rAL\u0001\u0002q\")qI\u0001a\u0001]\u0005\t\u00110\u0001\u0006d_6\u0004\u0018M]5t_:$2AS'O!\tY3*\u0003\u0002M;\tQ1i\\7qCJL7o\u001c8\t\u000b\u0015\u001b\u0001\u0019\u0001\u0018\t\u000b\u001d\u001b\u0001\u0019\u0001\u0018\u0002\u001dA\f'\u000f^5bY\u000e{W\u000e]1sKR\u0019\u0011\u000bV+\u0011\u0005\u0015\u0012\u0016BA*'\u0005\u0019!u.\u001e2mK\")Q\t\u0002a\u0001]!)q\t\u0002a\u0001]\u0005\u0019Q.\u001b8\u0015\u00079B\u0016\fC\u0003F\u000b\u0001\u0007a\u0006C\u0003H\u000b\u0001\u0007a&A\u0002nCb$2A\f/^\u0011\u0015)e\u00011\u0001/\u0011\u00159e\u00011\u0001/\u0003\r)\u0017O\u001e\u000b\u0004A\u000e$\u0007CA\u0013b\u0013\t\u0011gEA\u0004C_>dW-\u00198\t\u000b\u0015;\u0001\u0019\u0001\u0018\t\u000b\u001d;\u0001\u0019\u0001\u0018\u0002\t9,\u0017O\u001e\u000b\u0004A\u001eD\u0007\"B#\t\u0001\u0004q\u0003\"B$\t\u0001\u0004q\u0013!\u00027uKF4Hc\u00011lY\")Q)\u0003a\u0001]!)q)\u0003a\u0001]\u0005\u0011A\u000e\u001e\u000b\u0004A>\u0004\b\"B#\u000b\u0001\u0004q\u0003\"B$\u000b\u0001\u0004q\u0013!B4uKF4Hc\u00011ti\")Qi\u0003a\u0001]!)qi\u0003a\u0001]\u0005\u0011q\r\u001e\u000b\u0004A^D\b\"B#\r\u0001\u0004q\u0003\"B$\r\u0001\u0004q\u0013A\u0003;p\u001fJ$WM]5oOV\t1\u0010\u0005\u0003}\u0003\u0013qcbA?\u0002\u00069\u0019a0a\u0001\u000e\u0003}T1!!\u0001\"\u0003\u0019a$o\\8u}%\tq%C\u0002\u0002\b\u0019\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\f\u00055!\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0007\u0005\u001da%A\u0003Pe\u0012,'\u000f\u0005\u0002,\u001fM9q\"!\u0006\u0002\u001e\u0005\r\u0002#B\u0016\u0002\u0018\u0005m\u0011bAA\r;\tqqJ\u001d3fe\u001a+hn\u0019;j_:\u001c\bCA\u0016\u0001!\rY\u0013qD\u0005\u0004\u0003Ci\"!G(sI\u0016\u0014Hk\\(sI\u0016\u0014\u0018N\\4D_:4XM]:j_:\u0004B!!\n\u000205\u0011\u0011q\u0005\u0006\u0005\u0003S\tY#\u0001\u0002j_*\u0011\u0011QF\u0001\u0005U\u00064\u0018-\u0003\u0003\u00022\u0005\u001d\"\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\b\u0006\u0002\u0002\u0012\u0005)\u0011\r\u001d9msV!\u00111HA!)\u0011\ti$a\u0011\u0011\t-\u0002\u0011q\b\t\u0004_\u0005\u0005C!B\u0019\u0012\u0005\u0004\u0011\u0004bBA##\u0001\u000f\u0011QH\u0001\u0003KZD3!EA%!\r)\u00131J\u0005\u0004\u0003\u001b2#AB5oY&tW-\u0001\u0002csV1\u00111KA.\u0003K\"B!!\u0016\u0002lQ!\u0011qKA0!\u0011Y\u0003!!\u0017\u0011\u0007=\nY\u0006B\u00052%\u0001\u0006\t\u0011!b\u0001e!\u001a\u00111L\u001c\t\u000f\u0005\u0015#\u0003q\u0001\u0002bA!1\u0006AA2!\ry\u0013Q\r\u0003\u000b\u0003O\u0012\u0002\u0015!A\u0001\u0006\u0004\u0011$!\u0001\")\u0007\u0005\u0015t\u0007C\u0004\u0002nI\u0001\r!a\u001c\u0002\u0003\u0019\u0004r!JA9\u00033\n\u0019'C\u0002\u0002t\u0019\u0012\u0011BR;oGRLwN\\\u0019\u0002\u000fI,g/\u001a:tKV!\u0011\u0011PA@)\u0011\tY(a!\u0011\t-\u0002\u0011Q\u0010\t\u0004_\u0005}D!C\u0019\u0014A\u0003\u0005\tQ1\u00013Q\r\tyh\u000e\u0005\b\u0003\u000b\u001b\u0002\u0019AA>\u0003\u0015y'\u000fZ3s\u0003%9\b.\u001a8FcV\fG.\u0006\u0003\u0002\f\u0006EECBAG\u0003+\u000bI\n\u0005\u0003,\u0001\u0005=\u0005cA\u0018\u0002\u0012\u0012I\u0011\u0007\u0006Q\u0001\u0002\u0003\u0015\rA\r\u0015\u0004\u0003#;\u0004bBAL)\u0001\u0007\u0011QR\u0001\u0006M&\u00148\u000f\u001e\u0005\b\u00037#\u0002\u0019AAG\u0003\u0019\u0019XmY8oI\u0006!aM]8n+\u0011\t\t+a*\u0015\t\u0005\r\u00161\u0016\t\u0005W\u0001\t)\u000bE\u00020\u0003O#\u0011\"M\u000b!\u0002\u0003\u0005)\u0019\u0001\u001a)\u0007\u0005\u001dv\u0007C\u0004\u0002nU\u0001\r!!,\u0011\u0011\u0015\ny+!*\u0002&\u0006K1!!-'\u0005%1UO\\2uS>t''\u0001\u0007ge>lG*Z:t)\"\fg.\u0006\u0003\u00028\u0006uF\u0003BA]\u0003\u0003\u0004Ba\u000b\u0001\u0002<B\u0019q&!0\u0005\u0013E2\u0002\u0015!A\u0001\u0006\u0004\u0011\u0004fAA_o!9\u0011Q\u000e\fA\u0002\u0005\r\u0007\u0003C\u0013\u00020\u0006m\u00161\u00181\u0002\u0011\u0005dG.R9vC2,B!!3\u0002PV\u0011\u00111\u001a\t\u0005W\u0001\ti\rE\u00020\u0003\u001f$Q!M\fC\u0002I\nqb\u001e5f]\u0016\u000bX/\u00197N_:|\u0017\u000eZ\u000b\u0005\u0003+\f9/\u0006\u0002\u0002XJ1\u0011\u0011\\Ao\u0003S4a!a7\u0010\u0001\u0005]'\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004#B\u0016\u0002`\u0006\r\u0018bAAq;\t1Qj\u001c8pS\u0012\u0004Ba\u000b\u0001\u0002fB\u0019q&a:\u0005\u000bEB\"\u0019\u0001\u001a\u0011\u000b-\nY/a9\n\u0007\u00055XD\u0001\u0003CC:$\u0017\u0001\u00044s_6|%\u000fZ3sS:<W\u0003BAz\u0003s$B!!>\u0002|B!1\u0006AA|!\ry\u0013\u0011 \u0003\u0006ce\u0011\rA\r\u0005\b\u0003\u000bJ\u00029AA\u007f!\u0015a\u0018\u0011BA|\u000391'o\\7D_6\u0004\u0018M]1cY\u0016,BAa\u0001\u0003\nU\u0011!Q\u0001\t\u0005W\u0001\u00119\u0001E\u00020\u0005\u0013!a!\r\u000eC\u0002\t-\u0011cA\u001a\u0003\u000eA1!q\u0002B\u000b\u0005\u000fi!A!\u0005\u000b\t\tM\u00111F\u0001\u0005Y\u0006tw-\u0003\u0003\u0003\u0018\tE!AC\"p[B\f'/\u00192mK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!Q\u0004\t\u0005\u0005\u001f\u0011y\"\u0003\u0003\u0003\"\tE!AB(cU\u0016\u001cG\u000f"
)
public interface Order extends PartialOrder {
   static Order fromComparable() {
      return Order$.MODULE$.fromComparable();
   }

   static Order fromOrdering(final Ordering ev) {
      return Order$.MODULE$.fromOrdering(ev);
   }

   static Monoid whenEqualMonoid() {
      return Order$.MODULE$.whenEqualMonoid();
   }

   static Order allEqual() {
      return Order$.MODULE$.allEqual();
   }

   static Order fromLessThan(final Function2 f) {
      return Order$.MODULE$.fromLessThan(f);
   }

   static Order from(final Function2 f) {
      return Order$.MODULE$.from(f);
   }

   static Order whenEqual(final Order first, final Order second) {
      return Order$.MODULE$.whenEqual(first, second);
   }

   static Order reverse(final Order order) {
      return Order$.MODULE$.reverse(order);
   }

   static Order by(final Function1 f, final Order ev) {
      return Order$.MODULE$.by(f, ev);
   }

   static Order apply(final Order ev) {
      return Order$.MODULE$.apply(ev);
   }

   static Ordering catsKernelOrderingForOrder(final Order ev) {
      return Order$.MODULE$.catsKernelOrderingForOrder(ev);
   }

   int compare(final Object x, final Object y);

   // $FF: synthetic method
   static Comparison comparison$(final Order $this, final Object x, final Object y) {
      return $this.comparison(x, y);
   }

   default Comparison comparison(final Object x, final Object y) {
      return Comparison$.MODULE$.fromInt(this.compare(x, y));
   }

   // $FF: synthetic method
   static double partialCompare$(final Order $this, final Object x, final Object y) {
      return $this.partialCompare(x, y);
   }

   default double partialCompare(final Object x, final Object y) {
      return (double)this.compare(x, y);
   }

   // $FF: synthetic method
   static Object min$(final Order $this, final Object x, final Object y) {
      return $this.min(x, y);
   }

   default Object min(final Object x, final Object y) {
      return this.lt(x, y) ? x : y;
   }

   // $FF: synthetic method
   static Object max$(final Order $this, final Object x, final Object y) {
      return $this.max(x, y);
   }

   default Object max(final Object x, final Object y) {
      return this.gt(x, y) ? x : y;
   }

   // $FF: synthetic method
   static boolean eqv$(final Order $this, final Object x, final Object y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Object x, final Object y) {
      return this.compare(x, y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final Order $this, final Object x, final Object y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Object x, final Object y) {
      return !this.eqv(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final Order $this, final Object x, final Object y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final Object x, final Object y) {
      return this.compare(x, y) <= 0;
   }

   // $FF: synthetic method
   static boolean lt$(final Order $this, final Object x, final Object y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Object x, final Object y) {
      return this.compare(x, y) < 0;
   }

   // $FF: synthetic method
   static boolean gteqv$(final Order $this, final Object x, final Object y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final Object x, final Object y) {
      return this.compare(x, y) >= 0;
   }

   // $FF: synthetic method
   static boolean gt$(final Order $this, final Object x, final Object y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Object x, final Object y) {
      return this.compare(x, y) > 0;
   }

   // $FF: synthetic method
   static Ordering toOrdering$(final Order $this) {
      return $this.toOrdering();
   }

   default Ordering toOrdering() {
      return new Ordering() {
         // $FF: synthetic field
         private final Order $outer;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return Ordering.lteq$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return Ordering.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Ordering.lt$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Ordering.gt$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Object x, final Object y) {
            return this.$outer.compare(x, y);
         }

         public {
            if (Order.this == null) {
               throw null;
            } else {
               this.$outer = Order.this;
               PartialOrdering.$init$(this);
               Ordering.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static int compare$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.compare$mcZ$sp(x, y);
   }

   default int compare$mcZ$sp(final boolean x, final boolean y) {
      return this.compare(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static int compare$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.compare$mcB$sp(x, y);
   }

   default int compare$mcB$sp(final byte x, final byte y) {
      return this.compare(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static int compare$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.compare$mcC$sp(x, y);
   }

   default int compare$mcC$sp(final char x, final char y) {
      return this.compare(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static int compare$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.compare$mcD$sp(x, y);
   }

   default int compare$mcD$sp(final double x, final double y) {
      return this.compare(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static int compare$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.compare$mcF$sp(x, y);
   }

   default int compare$mcF$sp(final float x, final float y) {
      return this.compare(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static int compare$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.compare$mcI$sp(x, y);
   }

   default int compare$mcI$sp(final int x, final int y) {
      return this.compare(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static int compare$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.compare$mcJ$sp(x, y);
   }

   default int compare$mcJ$sp(final long x, final long y) {
      return this.compare(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static int compare$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.compare$mcS$sp(x, y);
   }

   default int compare$mcS$sp(final short x, final short y) {
      return this.compare(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static int compare$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.compare$mcV$sp(x, y);
   }

   default int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.compare(x, y);
   }

   // $FF: synthetic method
   static Comparison comparison$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.comparison$mcZ$sp(x, y);
   }

   default Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return this.comparison(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.comparison$mcB$sp(x, y);
   }

   default Comparison comparison$mcB$sp(final byte x, final byte y) {
      return this.comparison(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.comparison$mcC$sp(x, y);
   }

   default Comparison comparison$mcC$sp(final char x, final char y) {
      return this.comparison(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.comparison$mcD$sp(x, y);
   }

   default Comparison comparison$mcD$sp(final double x, final double y) {
      return this.comparison(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.comparison$mcF$sp(x, y);
   }

   default Comparison comparison$mcF$sp(final float x, final float y) {
      return this.comparison(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.comparison$mcI$sp(x, y);
   }

   default Comparison comparison$mcI$sp(final int x, final int y) {
      return this.comparison(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.comparison$mcJ$sp(x, y);
   }

   default Comparison comparison$mcJ$sp(final long x, final long y) {
      return this.comparison(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.comparison$mcS$sp(x, y);
   }

   default Comparison comparison$mcS$sp(final short x, final short y) {
      return this.comparison(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static Comparison comparison$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.comparison$mcV$sp(x, y);
   }

   default Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.comparison(x, y);
   }

   // $FF: synthetic method
   static double partialCompare$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.partialCompare$mcZ$sp(x, y);
   }

   default double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return this.partialCompare(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.partialCompare$mcB$sp(x, y);
   }

   default double partialCompare$mcB$sp(final byte x, final byte y) {
      return this.partialCompare(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.partialCompare$mcC$sp(x, y);
   }

   default double partialCompare$mcC$sp(final char x, final char y) {
      return this.partialCompare(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.partialCompare$mcD$sp(x, y);
   }

   default double partialCompare$mcD$sp(final double x, final double y) {
      return this.partialCompare(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.partialCompare$mcF$sp(x, y);
   }

   default double partialCompare$mcF$sp(final float x, final float y) {
      return this.partialCompare(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.partialCompare$mcI$sp(x, y);
   }

   default double partialCompare$mcI$sp(final int x, final int y) {
      return this.partialCompare(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.partialCompare$mcJ$sp(x, y);
   }

   default double partialCompare$mcJ$sp(final long x, final long y) {
      return this.partialCompare(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.partialCompare$mcS$sp(x, y);
   }

   default double partialCompare$mcS$sp(final short x, final short y) {
      return this.partialCompare(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static double partialCompare$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.partialCompare$mcV$sp(x, y);
   }

   default double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.partialCompare(x, y);
   }

   // $FF: synthetic method
   static boolean min$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.min$mcZ$sp(x, y);
   }

   default boolean min$mcZ$sp(final boolean x, final boolean y) {
      return BoxesRunTime.unboxToBoolean(this.min(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y)));
   }

   // $FF: synthetic method
   static byte min$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.min$mcB$sp(x, y);
   }

   default byte min$mcB$sp(final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(this.min(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   static char min$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.min$mcC$sp(x, y);
   }

   default char min$mcC$sp(final char x, final char y) {
      return BoxesRunTime.unboxToChar(this.min(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y)));
   }

   // $FF: synthetic method
   static double min$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.min$mcD$sp(x, y);
   }

   default double min$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.min(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float min$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.min$mcF$sp(x, y);
   }

   default float min$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.min(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int min$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.min$mcI$sp(x, y);
   }

   default int min$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.min(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long min$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.min$mcJ$sp(x, y);
   }

   default long min$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.min(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static short min$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.min$mcS$sp(x, y);
   }

   default short min$mcS$sp(final short x, final short y) {
      return BoxesRunTime.unboxToShort(this.min(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   static void min$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      $this.min$mcV$sp(x, y);
   }

   default void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      this.min(x, y);
   }

   // $FF: synthetic method
   static boolean max$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.max$mcZ$sp(x, y);
   }

   default boolean max$mcZ$sp(final boolean x, final boolean y) {
      return BoxesRunTime.unboxToBoolean(this.max(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y)));
   }

   // $FF: synthetic method
   static byte max$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.max$mcB$sp(x, y);
   }

   default byte max$mcB$sp(final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(this.max(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   static char max$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.max$mcC$sp(x, y);
   }

   default char max$mcC$sp(final char x, final char y) {
      return BoxesRunTime.unboxToChar(this.max(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y)));
   }

   // $FF: synthetic method
   static double max$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.max$mcD$sp(x, y);
   }

   default double max$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.max(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float max$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.max$mcF$sp(x, y);
   }

   default float max$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.max(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int max$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.max$mcI$sp(x, y);
   }

   default int max$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.max(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long max$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.max$mcJ$sp(x, y);
   }

   default long max$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.max(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static short max$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.max$mcS$sp(x, y);
   }

   default short max$mcS$sp(final short x, final short y) {
      return BoxesRunTime.unboxToShort(this.max(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   static void max$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      $this.max$mcV$sp(x, y);
   }

   default void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      this.max(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.eqv$mcZ$sp(x, y);
   }

   default boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return this.eqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.eqv$mcB$sp(x, y);
   }

   default boolean eqv$mcB$sp(final byte x, final byte y) {
      return this.eqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.eqv$mcC$sp(x, y);
   }

   default boolean eqv$mcC$sp(final char x, final char y) {
      return this.eqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final double x, final double y) {
      return this.eqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.eqv$mcF$sp(x, y);
   }

   default boolean eqv$mcF$sp(final float x, final float y) {
      return this.eqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.eqv$mcI$sp(x, y);
   }

   default boolean eqv$mcI$sp(final int x, final int y) {
      return this.eqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.eqv$mcJ$sp(x, y);
   }

   default boolean eqv$mcJ$sp(final long x, final long y) {
      return this.eqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.eqv$mcS$sp(x, y);
   }

   default boolean eqv$mcS$sp(final short x, final short y) {
      return this.eqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv$mcV$sp(x, y);
   }

   default boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.neqv$mcZ$sp(x, y);
   }

   default boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return this.neqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.neqv$mcB$sp(x, y);
   }

   default boolean neqv$mcB$sp(final byte x, final byte y) {
      return this.neqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.neqv$mcC$sp(x, y);
   }

   default boolean neqv$mcC$sp(final char x, final char y) {
      return this.neqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.neqv$mcD$sp(x, y);
   }

   default boolean neqv$mcD$sp(final double x, final double y) {
      return this.neqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.neqv$mcF$sp(x, y);
   }

   default boolean neqv$mcF$sp(final float x, final float y) {
      return this.neqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.neqv$mcI$sp(x, y);
   }

   default boolean neqv$mcI$sp(final int x, final int y) {
      return this.neqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.neqv$mcJ$sp(x, y);
   }

   default boolean neqv$mcJ$sp(final long x, final long y) {
      return this.neqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.neqv$mcS$sp(x, y);
   }

   default boolean neqv$mcS$sp(final short x, final short y) {
      return this.neqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv$mcV$sp(x, y);
   }

   default boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.neqv(x, y);
   }

   // $FF: synthetic method
   static boolean lteqv$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.lteqv$mcZ$sp(x, y);
   }

   default boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.lteqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.lteqv$mcB$sp(x, y);
   }

   default boolean lteqv$mcB$sp(final byte x, final byte y) {
      return this.lteqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.lteqv$mcC$sp(x, y);
   }

   default boolean lteqv$mcC$sp(final char x, final char y) {
      return this.lteqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.lteqv$mcD$sp(x, y);
   }

   default boolean lteqv$mcD$sp(final double x, final double y) {
      return this.lteqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.lteqv$mcF$sp(x, y);
   }

   default boolean lteqv$mcF$sp(final float x, final float y) {
      return this.lteqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.lteqv$mcI$sp(x, y);
   }

   default boolean lteqv$mcI$sp(final int x, final int y) {
      return this.lteqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.lteqv$mcJ$sp(x, y);
   }

   default boolean lteqv$mcJ$sp(final long x, final long y) {
      return this.lteqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.lteqv$mcS$sp(x, y);
   }

   default boolean lteqv$mcS$sp(final short x, final short y) {
      return this.lteqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean lteqv$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lteqv$mcV$sp(x, y);
   }

   default boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.lteqv(x, y);
   }

   // $FF: synthetic method
   static boolean lt$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.lt$mcZ$sp(x, y);
   }

   default boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return this.lt(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean lt$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.lt$mcB$sp(x, y);
   }

   default boolean lt$mcB$sp(final byte x, final byte y) {
      return this.lt(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean lt$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.lt$mcC$sp(x, y);
   }

   default boolean lt$mcC$sp(final char x, final char y) {
      return this.lt(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean lt$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.lt$mcD$sp(x, y);
   }

   default boolean lt$mcD$sp(final double x, final double y) {
      return this.lt(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean lt$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.lt$mcF$sp(x, y);
   }

   default boolean lt$mcF$sp(final float x, final float y) {
      return this.lt(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean lt$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.lt$mcI$sp(x, y);
   }

   default boolean lt$mcI$sp(final int x, final int y) {
      return this.lt(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean lt$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.lt$mcJ$sp(x, y);
   }

   default boolean lt$mcJ$sp(final long x, final long y) {
      return this.lt(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean lt$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.lt$mcS$sp(x, y);
   }

   default boolean lt$mcS$sp(final short x, final short y) {
      return this.lt(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean lt$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.lt$mcV$sp(x, y);
   }

   default boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.lt(x, y);
   }

   // $FF: synthetic method
   static boolean gteqv$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.gteqv$mcZ$sp(x, y);
   }

   default boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return this.gteqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.gteqv$mcB$sp(x, y);
   }

   default boolean gteqv$mcB$sp(final byte x, final byte y) {
      return this.gteqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.gteqv$mcC$sp(x, y);
   }

   default boolean gteqv$mcC$sp(final char x, final char y) {
      return this.gteqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.gteqv$mcD$sp(x, y);
   }

   default boolean gteqv$mcD$sp(final double x, final double y) {
      return this.gteqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.gteqv$mcF$sp(x, y);
   }

   default boolean gteqv$mcF$sp(final float x, final float y) {
      return this.gteqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.gteqv$mcI$sp(x, y);
   }

   default boolean gteqv$mcI$sp(final int x, final int y) {
      return this.gteqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.gteqv$mcJ$sp(x, y);
   }

   default boolean gteqv$mcJ$sp(final long x, final long y) {
      return this.gteqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.gteqv$mcS$sp(x, y);
   }

   default boolean gteqv$mcS$sp(final short x, final short y) {
      return this.gteqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean gteqv$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gteqv$mcV$sp(x, y);
   }

   default boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.gteqv(x, y);
   }

   // $FF: synthetic method
   static boolean gt$mcZ$sp$(final Order $this, final boolean x, final boolean y) {
      return $this.gt$mcZ$sp(x, y);
   }

   default boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return this.gt(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean gt$mcB$sp$(final Order $this, final byte x, final byte y) {
      return $this.gt$mcB$sp(x, y);
   }

   default boolean gt$mcB$sp(final byte x, final byte y) {
      return this.gt(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean gt$mcC$sp$(final Order $this, final char x, final char y) {
      return $this.gt$mcC$sp(x, y);
   }

   default boolean gt$mcC$sp(final char x, final char y) {
      return this.gt(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean gt$mcD$sp$(final Order $this, final double x, final double y) {
      return $this.gt$mcD$sp(x, y);
   }

   default boolean gt$mcD$sp(final double x, final double y) {
      return this.gt(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean gt$mcF$sp$(final Order $this, final float x, final float y) {
      return $this.gt$mcF$sp(x, y);
   }

   default boolean gt$mcF$sp(final float x, final float y) {
      return this.gt(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean gt$mcI$sp$(final Order $this, final int x, final int y) {
      return $this.gt$mcI$sp(x, y);
   }

   default boolean gt$mcI$sp(final int x, final int y) {
      return this.gt(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean gt$mcJ$sp$(final Order $this, final long x, final long y) {
      return $this.gt$mcJ$sp(x, y);
   }

   default boolean gt$mcJ$sp(final long x, final long y) {
      return this.gt(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean gt$mcS$sp$(final Order $this, final short x, final short y) {
      return $this.gt$mcS$sp(x, y);
   }

   default boolean gt$mcS$sp(final short x, final short y) {
      return this.gt(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean gt$mcV$sp$(final Order $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.gt$mcV$sp(x, y);
   }

   default boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.gt(x, y);
   }

   static void $init$(final Order $this) {
   }
}
