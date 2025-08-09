package cats.kernel;

import scala.Option;
import scala.runtime.BoxedUnit;

public interface Semilattice$mcI$sp extends Semilattice, CommutativeSemigroup$mcI$sp, Band$mcI$sp {
   // $FF: synthetic method
   static PartialOrder asMeetPartialOrder$(final Semilattice$mcI$sp $this, final Eq ev) {
      return $this.asMeetPartialOrder(ev);
   }

   default PartialOrder asMeetPartialOrder(final Eq ev) {
      return this.asMeetPartialOrder$mcI$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder asMeetPartialOrder$mcI$sp$(final Semilattice$mcI$sp $this, final Eq ev) {
      return $this.asMeetPartialOrder$mcI$sp(ev);
   }

   default PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
      return new PartialOrder$mcI$sp(ev) {
         // $FF: synthetic field
         private final Semilattice$mcI$sp $outer;
         private final Eq ev$7;

         public Option partialComparison(final int x, final int y) {
            return PartialOrder$mcI$sp.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.partialComparison$mcI$sp$(this, x, y);
         }

         public Option tryCompare(final int x, final int y) {
            return PartialOrder$mcI$sp.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.tryCompare$mcI$sp$(this, x, y);
         }

         public Option pmin(final int x, final int y) {
            return PartialOrder$mcI$sp.pmin$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.pmin$mcI$sp$(this, x, y);
         }

         public Option pmax(final int x, final int y) {
            return PartialOrder$mcI$sp.pmax$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.pmax$mcI$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return PartialOrder$mcI$sp.eqv$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.eqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv(final int x, final int y) {
            return PartialOrder$mcI$sp.lteqv$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lt(final int x, final int y) {
            return PartialOrder$mcI$sp.lt$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.lt$mcI$sp$(this, x, y);
         }

         public boolean gteqv(final int x, final int y) {
            return PartialOrder$mcI$sp.gteqv$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gt(final int x, final int y) {
            return PartialOrder$mcI$sp.gt$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.gt$mcI$sp$(this, x, y);
         }

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
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

         public double partialCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.partialCompare$mcF$sp$(this, x, y);
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

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
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

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
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

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
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

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
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

         public boolean eqv$mcD$sp(final double x, final double y) {
            return PartialOrder.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return PartialOrder.eqv$mcF$sp$(this, x, y);
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

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return PartialOrder.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.lteqv$mcF$sp$(this, x, y);
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

         public boolean lt$mcD$sp(final double x, final double y) {
            return PartialOrder.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return PartialOrder.lt$mcF$sp$(this, x, y);
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

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return PartialOrder.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.gteqv$mcF$sp$(this, x, y);
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

         public boolean gt$mcD$sp(final double x, final double y) {
            return PartialOrder.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return PartialOrder.gt$mcF$sp$(this, x, y);
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

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
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

         public double partialCompare(final int x, final int y) {
            return this.partialCompare$mcI$sp(x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            double var10000;
            if (this.ev$7.eqv$mcI$sp(x, y)) {
               var10000 = (double)0.0F;
            } else {
               int z = this.$outer.combine$mcI$sp(x, y);
               var10000 = this.ev$7.eqv$mcI$sp(x, z) ? (double)-1.0F : (this.ev$7.eqv$mcI$sp(y, z) ? (double)1.0F : Double.NaN);
            }

            return var10000;
         }

         public {
            if (Semilattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semilattice$mcI$sp.this;
               this.ev$7 = ev$7;
               Eq.$init$(this);
               PartialOrder.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder asJoinPartialOrder$(final Semilattice$mcI$sp $this, final Eq ev) {
      return $this.asJoinPartialOrder(ev);
   }

   default PartialOrder asJoinPartialOrder(final Eq ev) {
      return this.asJoinPartialOrder$mcI$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder asJoinPartialOrder$mcI$sp$(final Semilattice$mcI$sp $this, final Eq ev) {
      return $this.asJoinPartialOrder$mcI$sp(ev);
   }

   default PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
      return new PartialOrder$mcI$sp(ev) {
         // $FF: synthetic field
         private final Semilattice$mcI$sp $outer;
         private final Eq ev$8;

         public Option partialComparison(final int x, final int y) {
            return PartialOrder$mcI$sp.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.partialComparison$mcI$sp$(this, x, y);
         }

         public Option tryCompare(final int x, final int y) {
            return PartialOrder$mcI$sp.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.tryCompare$mcI$sp$(this, x, y);
         }

         public Option pmin(final int x, final int y) {
            return PartialOrder$mcI$sp.pmin$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.pmin$mcI$sp$(this, x, y);
         }

         public Option pmax(final int x, final int y) {
            return PartialOrder$mcI$sp.pmax$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.pmax$mcI$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return PartialOrder$mcI$sp.eqv$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.eqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv(final int x, final int y) {
            return PartialOrder$mcI$sp.lteqv$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lt(final int x, final int y) {
            return PartialOrder$mcI$sp.lt$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.lt$mcI$sp$(this, x, y);
         }

         public boolean gteqv(final int x, final int y) {
            return PartialOrder$mcI$sp.gteqv$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gt(final int x, final int y) {
            return PartialOrder$mcI$sp.gt$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return PartialOrder$mcI$sp.gt$mcI$sp$(this, x, y);
         }

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
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

         public double partialCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.partialCompare$mcF$sp$(this, x, y);
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

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
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

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
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

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
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

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
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

         public boolean eqv$mcD$sp(final double x, final double y) {
            return PartialOrder.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return PartialOrder.eqv$mcF$sp$(this, x, y);
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

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return PartialOrder.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.lteqv$mcF$sp$(this, x, y);
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

         public boolean lt$mcD$sp(final double x, final double y) {
            return PartialOrder.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return PartialOrder.lt$mcF$sp$(this, x, y);
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

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return PartialOrder.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return PartialOrder.gteqv$mcF$sp$(this, x, y);
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

         public boolean gt$mcD$sp(final double x, final double y) {
            return PartialOrder.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return PartialOrder.gt$mcF$sp$(this, x, y);
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

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
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

         public double partialCompare(final int x, final int y) {
            return this.partialCompare$mcI$sp(x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            double var10000;
            if (this.ev$8.eqv$mcI$sp(x, y)) {
               var10000 = (double)0.0F;
            } else {
               int z = this.$outer.combine$mcI$sp(x, y);
               var10000 = this.ev$8.eqv$mcI$sp(y, z) ? (double)-1.0F : (this.ev$8.eqv$mcI$sp(x, z) ? (double)1.0F : Double.NaN);
            }

            return var10000;
         }

         public {
            if (Semilattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = Semilattice$mcI$sp.this;
               this.ev$8 = ev$8;
               Eq.$init$(this);
               PartialOrder.$init$(this);
            }
         }
      };
   }
}
