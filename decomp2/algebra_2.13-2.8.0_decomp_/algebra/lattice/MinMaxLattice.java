package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]3A!\u0002\u0004\u0001\u0017!Aq\b\u0001B\u0001B\u0003-\u0001\tC\u0003I\u0001\u0011\u0005\u0011\nC\u0003N\u0001\u0011\u0005a\nC\u0003T\u0001\u0011\u0005AKA\u0007NS:l\u0015\r\u001f'biRL7-\u001a\u0006\u0003\u000f!\tq\u0001\\1ui&\u001cWMC\u0001\n\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\r3M\u0019\u0001!D\n\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!RcF\u0007\u0002\r%\u0011aC\u0002\u0002\u0014\t&\u001cHO]5ckRLg/\u001a'biRL7-\u001a\t\u00031ea\u0001\u0001B\u0005\u001b\u0001\u0001\u0006\t\u0011!b\u00017\t\t\u0011)\u0005\u0002\u001d?A\u0011a\"H\u0005\u0003==\u0011qAT8uQ&tw\r\u0005\u0002\u000fA%\u0011\u0011e\u0004\u0002\u0004\u0003:L\bFB\r$MA*$\b\u0005\u0002\u000fI%\u0011Qe\u0004\u0002\fgB,7-[1mSj,G-M\u0003$O!R\u0013F\u0004\u0002\u000fQ%\u0011\u0011fD\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013,_Aq!\u0001L\u0018\u000e\u00035R!A\f\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012'B\u00122eQ\u001adB\u0001\b3\u0013\t\u0019t\"\u0001\u0003M_:<\u0017\u0007\u0002\u0013,_A\tTa\t\u001c8sar!AD\u001c\n\u0005az\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013,_A\tTaI\u001e=}ur!A\u0004\u001f\n\u0005uz\u0011A\u0002#pk\ndW-\r\u0003%W=\u0002\u0012!B8sI\u0016\u0014\bcA!F/9\u0011!iQ\u0007\u0002\u0011%\u0011A\tC\u0001\ba\u0006\u001c7.Y4f\u0013\t1uIA\u0003Pe\u0012,'O\u0003\u0002E\u0011\u00051A(\u001b8jiz\"\u0012A\u0013\u000b\u0003\u00172\u00032\u0001\u0006\u0001\u0018\u0011\u0015y$\u0001q\u0001A\u0003\u0011Qw.\u001b8\u0015\u0007]y\u0015\u000bC\u0003Q\u0007\u0001\u0007q#A\u0001y\u0011\u0015\u00116\u00011\u0001\u0018\u0003\u0005I\u0018\u0001B7fKR$2aF+W\u0011\u0015\u0001F\u00011\u0001\u0018\u0011\u0015\u0011F\u00011\u0001\u0018\u0001"
)
public class MinMaxLattice implements DistributiveLattice {
   public final Order order;

   public Lattice dual() {
      return Lattice.dual$(this);
   }

   public Lattice dual$mcD$sp() {
      return Lattice.dual$mcD$sp$(this);
   }

   public Lattice dual$mcF$sp() {
      return Lattice.dual$mcF$sp$(this);
   }

   public Lattice dual$mcI$sp() {
      return Lattice.dual$mcI$sp$(this);
   }

   public Lattice dual$mcJ$sp() {
      return Lattice.dual$mcJ$sp$(this);
   }

   public Semilattice meetSemilattice() {
      return MeetSemilattice.meetSemilattice$(this);
   }

   public Semilattice meetSemilattice$mcD$sp() {
      return MeetSemilattice.meetSemilattice$mcD$sp$(this);
   }

   public Semilattice meetSemilattice$mcF$sp() {
      return MeetSemilattice.meetSemilattice$mcF$sp$(this);
   }

   public Semilattice meetSemilattice$mcI$sp() {
      return MeetSemilattice.meetSemilattice$mcI$sp$(this);
   }

   public Semilattice meetSemilattice$mcJ$sp() {
      return MeetSemilattice.meetSemilattice$mcJ$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
   }

   public Semilattice joinSemilattice() {
      return JoinSemilattice.joinSemilattice$(this);
   }

   public Semilattice joinSemilattice$mcD$sp() {
      return JoinSemilattice.joinSemilattice$mcD$sp$(this);
   }

   public Semilattice joinSemilattice$mcF$sp() {
      return JoinSemilattice.joinSemilattice$mcF$sp$(this);
   }

   public Semilattice joinSemilattice$mcI$sp() {
      return JoinSemilattice.joinSemilattice$mcI$sp$(this);
   }

   public Semilattice joinSemilattice$mcJ$sp() {
      return JoinSemilattice.joinSemilattice$mcJ$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
   }

   public Object join(final Object x, final Object y) {
      return this.order.max(x, y);
   }

   public Object meet(final Object x, final Object y) {
      return this.order.min(x, y);
   }

   public double join$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.join(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   public float join$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.join(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   public int join$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.join(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   public long join$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.join(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   public double meet$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.meet(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   public float meet$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.meet(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   public int meet$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.meet(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   public long meet$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.meet(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   public MinMaxLattice(final Order order) {
      this.order = order;
      JoinSemilattice.$init$(this);
      MeetSemilattice.$init$(this);
      Lattice.$init$(this);
   }
}
