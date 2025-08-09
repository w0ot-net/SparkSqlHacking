package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y4q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003C\u0001\u0011\u00051\tC\u0003H\u0001\u0011\u0005\u0001jB\u0003K\u0013!\u00051JB\u0003\t\u0013!\u0005A\nC\u0003`\t\u0011\u0005\u0001\rC\u0003b\t\u0011\u0015!\rC\u0004w\t\u0005\u0005I\u0011B<\u0003\u000f1\u000bG\u000f^5dK*\u0011!bC\u0001\bY\u0006$H/[2f\u0015\u0005a\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\tyAd\u0005\u0003\u0001!Yy\u0004CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"aA!osB\u0019q\u0003\u0007\u000e\u000e\u0003%I!!G\u0005\u0003\u001f){\u0017N\\*f[&d\u0017\r\u001e;jG\u0016\u0004\"a\u0007\u000f\r\u0001\u0011IQ\u0004\u0001Q\u0001\u0002\u0003\u0015\rA\b\u0002\u0002\u0003F\u0011q\u0004\u0005\t\u0003#\u0001J!!\t\n\u0003\u000f9{G\u000f[5oO\"2Ad\t\u00141ki\u0002\"!\u0005\u0013\n\u0005\u0015\u0012\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\u0014)U%r!!\u0005\u0015\n\u0005%\u0012\u0012aA%oiF\"AeK\u0018\u0014\u001d\tas&D\u0001.\u0015\tqS\"\u0001\u0004=e>|GOP\u0005\u0002'E*1%\r\u001a5g9\u0011\u0011CM\u0005\u0003gI\tA\u0001T8oOF\"AeK\u0018\u0014c\u0015\u0019cgN\u001d9\u001d\t\tr'\u0003\u00029%\u0005)a\t\\8biF\"AeK\u0018\u0014c\u0015\u00193\b\u0010 >\u001d\t\tB(\u0003\u0002>%\u00051Ai\\;cY\u0016\fD\u0001J\u00160'A\u0019q\u0003\u0011\u000e\n\u0005\u0005K!aD'fKR\u001cV-\\5mCR$\u0018nY3\u0002\r\u0011Jg.\u001b;%)\u0005!\u0005CA\tF\u0013\t1%C\u0001\u0003V]&$\u0018\u0001\u00023vC2,\u0012!\u0013\t\u0004/\u0001Q\u0012a\u0002'biRL7-\u001a\t\u0003/\u0011\u0019R\u0001B'Q)^\u0003\"!\u0005(\n\u0005=\u0013\"AB!osJ+g\rE\u0002\u0018#NK!AU\u0005\u00031){\u0017N\\*f[&d\u0017\r\u001e;jG\u00164UO\\2uS>t7\u000f\u0005\u0002\u0018\u0001A\u0019q#V*\n\u0005YK!\u0001G'fKR\u001cV-\\5mCR$\u0018nY3Gk:\u001cG/[8ogB\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\u0003S>T\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_3\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012aS\u0001\u0006CB\u0004H._\u000b\u0003G\u001a$\"\u0001\u001a9\u0011\u0007]\u0001Q\r\u0005\u0002\u001cM\u0012IQD\u0002Q\u0001\u0002\u0003\u0015\rA\b\u0015\u0007M\u000eB'\u000e\u001c82\u000b\r:\u0003&[\u00152\t\u0011ZsfE\u0019\u0006GE\u00124nM\u0019\u0005I-z3#M\u0003$m]j\u0007(\r\u0003%W=\u001a\u0012'B\u0012<y=l\u0014\u0007\u0002\u0013,_MAQ!\u001d\u0004A\u0004\u0011\f!!\u001a<)\u0005\u0019\u0019\bCA\tu\u0013\t)(C\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002qB\u0011\u0011\u0010`\u0007\u0002u*\u00111pW\u0001\u0005Y\u0006tw-\u0003\u0002~u\n1qJ\u00196fGR\u0004"
)
public interface Lattice extends JoinSemilattice, MeetSemilattice {
   static Lattice apply(final Lattice ev) {
      return Lattice$.MODULE$.apply(ev);
   }

   // $FF: synthetic method
   static Lattice dual$(final Lattice $this) {
      return $this.dual();
   }

   default Lattice dual() {
      return new Lattice() {
         // $FF: synthetic field
         private final Lattice $outer;

         public Lattice dual$mcD$sp() {
            return Lattice.super.dual$mcD$sp();
         }

         public Lattice dual$mcF$sp() {
            return Lattice.super.dual$mcF$sp();
         }

         public Lattice dual$mcI$sp() {
            return Lattice.super.dual$mcI$sp();
         }

         public Lattice dual$mcJ$sp() {
            return Lattice.super.dual$mcJ$sp();
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
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

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
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

         public Object meet(final Object a, final Object b) {
            return this.$outer.join(a, b);
         }

         public Object join(final Object a, final Object b) {
            return this.$outer.meet(a, b);
         }

         public Lattice dual() {
            return this.$outer;
         }

         public {
            if (Lattice.this == null) {
               throw null;
            } else {
               this.$outer = Lattice.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
            }
         }
      };
   }

   default Lattice dual$mcD$sp() {
      return this.dual();
   }

   default Lattice dual$mcF$sp() {
      return this.dual();
   }

   default Lattice dual$mcI$sp() {
      return this.dual();
   }

   default Lattice dual$mcJ$sp() {
      return this.dual();
   }

   static void $init$(final Lattice $this) {
   }
}
