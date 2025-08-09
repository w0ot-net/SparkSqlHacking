package cats.kernel;

import scala.Option;
import scala.runtime.BoxedUnit;

public interface Semilattice$mcD$sp extends Semilattice, CommutativeSemigroup$mcD$sp, Band$mcD$sp {
   // $FF: synthetic method
   static PartialOrder asMeetPartialOrder$(final Semilattice$mcD$sp $this, final Eq ev) {
      return $this.asMeetPartialOrder(ev);
   }

   default PartialOrder asMeetPartialOrder(final Eq ev) {
      return this.asMeetPartialOrder$mcD$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder asMeetPartialOrder$mcD$sp$(final Semilattice$mcD$sp $this, final Eq ev) {
      return $this.asMeetPartialOrder$mcD$sp(ev);
   }

   default PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
      return new PartialOrder$mcD$sp(ev) {
         // $FF: synthetic field
         private final Semilattice$mcD$sp $outer;
         private final Eq ev$3;

         public Option partialComparison(final double x, final double y) {
            return PartialOrder$mcD$sp.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.partialComparison$mcD$sp$(this, x, y);
         }

         public Option tryCompare(final double x, final double y) {
            return PartialOrder$mcD$sp.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.tryCompare$mcD$sp$(this, x, y);
         }

         public Option pmin(final double x, final double y) {
            return PartialOrder$mcD$sp.pmin$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.pmin$mcD$sp$(this, x, y);
         }

         public Option pmax(final double x, final double y) {
            return PartialOrder$mcD$sp.pmax$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.pmax$mcD$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return PartialOrder$mcD$sp.eqv$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.eqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv(final double x, final double y) {
            return PartialOrder$mcD$sp.lteqv$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lt(final double x, final double y) {
            return PartialOrder$mcD$sp.lt$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.lt$mcD$sp$(this, x, y);
         }

         public boolean gteqv(final double x, final double y) {
            return PartialOrder$mcD$sp.gteqv$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gt(final double x, final double y) {
            return PartialOrder$mcD$sp.gt$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.gt$mcD$sp$(this, x, y);
         }

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialCompare$mcV$sp$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return PartialOrder.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return PartialOrder.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return PartialOrder.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return PartialOrder.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.eqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return PartialOrder.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return PartialOrder.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return PartialOrder.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return PartialOrder.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return PartialOrder.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return PartialOrder.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return PartialOrder.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return PartialOrder.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return PartialOrder.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return PartialOrder.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return PartialOrder.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return PartialOrder.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return PartialOrder.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return PartialOrder.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return PartialOrder.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return PartialOrder.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return PartialOrder.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return PartialOrder.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.gt$mcV$sp$(this, x, y);
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

         public double partialCompare(final double x, final double y) {
            return this.partialCompare$mcD$sp(x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            double var10000;
            if (this.ev$3.eqv$mcD$sp(x, y)) {
               var10000 = (double)0.0F;
            } else {
               double z = this.$outer.combine$mcD$sp(x, y);
               var10000 = this.ev$3.eqv$mcD$sp(x, z) ? (double)-1.0F : (this.ev$3.eqv$mcD$sp(y, z) ? (double)1.0F : Double.NaN);
            }

            return var10000;
         }

         public {
            if (Semilattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semilattice$mcD$sp.this;
               this.ev$3 = ev$3;
               Eq.$init$(this);
               PartialOrder.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder asJoinPartialOrder$(final Semilattice$mcD$sp $this, final Eq ev) {
      return $this.asJoinPartialOrder(ev);
   }

   default PartialOrder asJoinPartialOrder(final Eq ev) {
      return this.asJoinPartialOrder$mcD$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder asJoinPartialOrder$mcD$sp$(final Semilattice$mcD$sp $this, final Eq ev) {
      return $this.asJoinPartialOrder$mcD$sp(ev);
   }

   default PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
      return new PartialOrder$mcD$sp(ev) {
         // $FF: synthetic field
         private final Semilattice$mcD$sp $outer;
         private final Eq ev$4;

         public Option partialComparison(final double x, final double y) {
            return PartialOrder$mcD$sp.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.partialComparison$mcD$sp$(this, x, y);
         }

         public Option tryCompare(final double x, final double y) {
            return PartialOrder$mcD$sp.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.tryCompare$mcD$sp$(this, x, y);
         }

         public Option pmin(final double x, final double y) {
            return PartialOrder$mcD$sp.pmin$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.pmin$mcD$sp$(this, x, y);
         }

         public Option pmax(final double x, final double y) {
            return PartialOrder$mcD$sp.pmax$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.pmax$mcD$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return PartialOrder$mcD$sp.eqv$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.eqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv(final double x, final double y) {
            return PartialOrder$mcD$sp.lteqv$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lt(final double x, final double y) {
            return PartialOrder$mcD$sp.lt$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.lt$mcD$sp$(this, x, y);
         }

         public boolean gteqv(final double x, final double y) {
            return PartialOrder$mcD$sp.gteqv$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gt(final double x, final double y) {
            return PartialOrder$mcD$sp.gt$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return PartialOrder$mcD$sp.gt$mcD$sp$(this, x, y);
         }

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialCompare$mcV$sp$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return PartialOrder.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return PartialOrder.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return PartialOrder.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return PartialOrder.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.eqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return PartialOrder.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return PartialOrder.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return PartialOrder.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return PartialOrder.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return PartialOrder.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return PartialOrder.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return PartialOrder.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return PartialOrder.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return PartialOrder.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return PartialOrder.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return PartialOrder.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return PartialOrder.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return PartialOrder.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return PartialOrder.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return PartialOrder.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return PartialOrder.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return PartialOrder.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return PartialOrder.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return PartialOrder.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return PartialOrder.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.gt$mcV$sp$(this, x, y);
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

         public double partialCompare(final double x, final double y) {
            return this.partialCompare$mcD$sp(x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            double var10000;
            if (this.ev$4.eqv$mcD$sp(x, y)) {
               var10000 = (double)0.0F;
            } else {
               double z = this.$outer.combine$mcD$sp(x, y);
               var10000 = this.ev$4.eqv$mcD$sp(y, z) ? (double)-1.0F : (this.ev$4.eqv$mcD$sp(x, z) ? (double)1.0F : Double.NaN);
            }

            return var10000;
         }

         public {
            if (Semilattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semilattice$mcD$sp.this;
               this.ev$4 = ev$4;
               Eq.$init$(this);
               PartialOrder.$init$(this);
            }
         }
      };
   }
}
