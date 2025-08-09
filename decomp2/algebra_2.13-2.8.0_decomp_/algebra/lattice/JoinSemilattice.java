package algebra.lattice;

import cats.kernel.Band;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import java.io.Serializable;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015ba\u0002\u0006\f!\u0003\r\t\u0001\u0005\u0005\u0006I\u0001!\t!\n\u0005\u0006S\u00011\tA\u000b\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u00061\u0002!\t!W\u0004\u0006E.A\ta\u0019\u0004\u0006\u0015-A\t!\u001a\u0005\u0006i\u001a!\t!\u001e\u0005\u0006m\u001a!)a\u001e\u0005\n\u0003+1\u0011\u0011!C\u0005\u0003/\u0011qBS8j]N+W.\u001b7biRL7-\u001a\u0006\u0003\u00195\tq\u0001\\1ui&\u001cWMC\u0001\u000f\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u0012[M\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001aC9\u0011!d\b\b\u00037yi\u0011\u0001\b\u0006\u0003;=\ta\u0001\u0010:p_Rt\u0014\"A\u000b\n\u0005\u0001\"\u0012a\u00029bG.\fw-Z\u0005\u0003E\r\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\t\u000b\u0002\r\u0011Jg.\u001b;%)\u00051\u0003CA\n(\u0013\tACC\u0001\u0003V]&$\u0018\u0001\u00026pS:$2aK&N!\taS\u0006\u0004\u0001\u0005\u00139\u0002\u0001\u0015!A\u0001\u0006\u0004y#!A!\u0012\u0005A\u0012\u0002CA\n2\u0013\t\u0011DCA\u0004O_RD\u0017N\\4)\r5\"t\u0007P!G!\t\u0019R'\u0003\u00027)\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019\u0003(O\u001e;\u001d\t\u0019\u0012(\u0003\u0002;)\u0005\u0019\u0011J\u001c;2\t\u0011Rb$F\u0019\u0006Gur\u0004i\u0010\b\u0003'yJ!a\u0010\u000b\u0002\t1{gnZ\u0019\u0005IiqR#M\u0003$\u0005\u000e+EI\u0004\u0002\u0014\u0007&\u0011A\tF\u0001\u0006\r2|\u0017\r^\u0019\u0005IiqR#M\u0003$\u000f\"S\u0015J\u0004\u0002\u0014\u0011&\u0011\u0011\nF\u0001\u0007\t>,(\r\\32\t\u0011Rb$\u0006\u0005\u0006\u0019\n\u0001\raK\u0001\u0004Y\"\u001c\b\"\u0002(\u0003\u0001\u0004Y\u0013a\u0001:ig\u0006y!n\\5o'\u0016l\u0017\u000e\\1ui&\u001cW-F\u0001R!\r\u0011Vk\u000b\b\u0003'Rk\u0011!D\u0005\u0003A5I!AV,\u0003\u0017M+W.\u001b7biRL7-\u001a\u0006\u0003A5\t\u0001C[8j]B\u000b'\u000f^5bY>\u0013H-\u001a:\u0015\u0005ik\u0006c\u0001*\\W%\u0011Al\u0016\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d\u0005\u0006=\u0012\u0001\u001daX\u0001\u0003KZ\u00042A\u00151,\u0013\t\twK\u0001\u0002Fc\u0006y!j\\5o'\u0016l\u0017\u000e\\1ui&\u001cW\r\u0005\u0002e\r5\t1b\u0005\u0003\u0007M&l\u0007CA\nh\u0013\tAGC\u0001\u0004B]f\u0014VM\u001a\t\u0004I*d\u0017BA6\f\u0005aQu.\u001b8TK6LG.\u0019;uS\u000e,g)\u001e8di&|gn\u001d\t\u0003I\u0002\u0001\"A\\:\u000e\u0003=T!\u0001]9\u0002\u0005%|'\"\u0001:\u0002\t)\fg/Y\u0005\u0003E=\fa\u0001P5oSRtD#A2\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0005a\\HcA=\u0002\fA\u0019A\r\u0001>\u0011\u00051ZH!\u0003\u0018\tA\u0003\u0005\tQ1\u00010Q!YH'`@\u0002\u0004\u0005\u001d\u0011'B\u00129syT\u0014\u0007\u0002\u0013\u001b=U\tdaI\u001f?\u0003\u0003y\u0014\u0007\u0002\u0013\u001b=U\tda\t\"D\u0003\u000b!\u0015\u0007\u0002\u0013\u001b=U\tdaI$I\u0003\u0013I\u0015\u0007\u0002\u0013\u001b=UAQA\u0018\u0005A\u0004eD3\u0001CA\b!\r\u0019\u0012\u0011C\u0005\u0004\u0003'!\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u001aA!\u00111DA\u0011\u001b\t\tiBC\u0002\u0002 E\fA\u0001\\1oO&!\u00111EA\u000f\u0005\u0019y%M[3di\u0002"
)
public interface JoinSemilattice extends Serializable {
   static JoinSemilattice apply(final JoinSemilattice ev) {
      return JoinSemilattice$.MODULE$.apply(ev);
   }

   Object join(final Object lhs, final Object rhs);

   // $FF: synthetic method
   static Semilattice joinSemilattice$(final JoinSemilattice $this) {
      return $this.joinSemilattice();
   }

   default Semilattice joinSemilattice() {
      return new Semilattice() {
         // $FF: synthetic field
         private final JoinSemilattice $outer;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Object combine(final Object x, final Object y) {
            return this.$outer.join(x, y);
         }

         public {
            if (JoinSemilattice.this == null) {
               throw null;
            } else {
               this.$outer = JoinSemilattice.this;
               Semigroup.$init$(this);
               Band.$init$(this);
               CommutativeSemigroup.$init$(this);
               Semilattice.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$(final JoinSemilattice $this, final Eq ev) {
      return $this.joinPartialOrder(ev);
   }

   default PartialOrder joinPartialOrder(final Eq ev) {
      return this.joinSemilattice().asJoinPartialOrder(ev);
   }

   // $FF: synthetic method
   static double join$mcD$sp$(final JoinSemilattice $this, final double lhs, final double rhs) {
      return $this.join$mcD$sp(lhs, rhs);
   }

   default double join$mcD$sp(final double lhs, final double rhs) {
      return BoxesRunTime.unboxToDouble(this.join(BoxesRunTime.boxToDouble(lhs), BoxesRunTime.boxToDouble(rhs)));
   }

   // $FF: synthetic method
   static float join$mcF$sp$(final JoinSemilattice $this, final float lhs, final float rhs) {
      return $this.join$mcF$sp(lhs, rhs);
   }

   default float join$mcF$sp(final float lhs, final float rhs) {
      return BoxesRunTime.unboxToFloat(this.join(BoxesRunTime.boxToFloat(lhs), BoxesRunTime.boxToFloat(rhs)));
   }

   // $FF: synthetic method
   static int join$mcI$sp$(final JoinSemilattice $this, final int lhs, final int rhs) {
      return $this.join$mcI$sp(lhs, rhs);
   }

   default int join$mcI$sp(final int lhs, final int rhs) {
      return BoxesRunTime.unboxToInt(this.join(BoxesRunTime.boxToInteger(lhs), BoxesRunTime.boxToInteger(rhs)));
   }

   // $FF: synthetic method
   static long join$mcJ$sp$(final JoinSemilattice $this, final long lhs, final long rhs) {
      return $this.join$mcJ$sp(lhs, rhs);
   }

   default long join$mcJ$sp(final long lhs, final long rhs) {
      return BoxesRunTime.unboxToLong(this.join(BoxesRunTime.boxToLong(lhs), BoxesRunTime.boxToLong(rhs)));
   }

   // $FF: synthetic method
   static Semilattice joinSemilattice$mcD$sp$(final JoinSemilattice $this) {
      return $this.joinSemilattice$mcD$sp();
   }

   default Semilattice joinSemilattice$mcD$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static Semilattice joinSemilattice$mcF$sp$(final JoinSemilattice $this) {
      return $this.joinSemilattice$mcF$sp();
   }

   default Semilattice joinSemilattice$mcF$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static Semilattice joinSemilattice$mcI$sp$(final JoinSemilattice $this) {
      return $this.joinSemilattice$mcI$sp();
   }

   default Semilattice joinSemilattice$mcI$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static Semilattice joinSemilattice$mcJ$sp$(final JoinSemilattice $this) {
      return $this.joinSemilattice$mcJ$sp();
   }

   default Semilattice joinSemilattice$mcJ$sp() {
      return this.joinSemilattice();
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$mcD$sp$(final JoinSemilattice $this, final Eq ev) {
      return $this.joinPartialOrder$mcD$sp(ev);
   }

   default PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
      return this.joinPartialOrder(ev);
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$mcF$sp$(final JoinSemilattice $this, final Eq ev) {
      return $this.joinPartialOrder$mcF$sp(ev);
   }

   default PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
      return this.joinPartialOrder(ev);
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$mcI$sp$(final JoinSemilattice $this, final Eq ev) {
      return $this.joinPartialOrder$mcI$sp(ev);
   }

   default PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
      return this.joinPartialOrder(ev);
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$mcJ$sp$(final JoinSemilattice $this, final Eq ev) {
      return $this.joinPartialOrder$mcJ$sp(ev);
   }

   default PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return this.joinPartialOrder(ev);
   }

   static void $init$(final JoinSemilattice $this) {
   }
}
