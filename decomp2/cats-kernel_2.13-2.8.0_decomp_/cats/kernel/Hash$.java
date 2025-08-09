package cats.kernel;

import java.io.Serializable;
import scala.Function1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.util.hashing.Hashing;

public final class Hash$ extends HashFunctions implements Serializable {
   public static final Hash$ MODULE$ = new Hash$();

   public final Hash apply(final Hash ev) {
      return ev;
   }

   public Hash by(final Function1 f, final Hash ev) {
      return new Hash(ev, f) {
         private final Hash ev$1;
         private final Function1 f$1;

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

         public int hash(final Object x) {
            return this.ev$1.hash(this.f$1.apply(x));
         }

         public boolean eqv(final Object x, final Object y) {
            return this.ev$1.eqv(this.f$1.apply(x), this.f$1.apply(y));
         }

         public {
            this.ev$1 = ev$1;
            this.f$1 = f$1;
            Eq.$init$(this);
         }
      };
   }

   public Hash fromHashing(final Hashing ev) {
      return new Hash(ev) {
         private final Hashing ev$2;

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

         public int hash(final Object x) {
            return this.ev$2.hash(x);
         }

         public boolean eqv(final Object x, final Object y) {
            return BoxesRunTime.equals(x, y);
         }

         public {
            this.ev$2 = ev$2;
            Eq.$init$(this);
         }
      };
   }

   public Hash fromUniversalHashCode() {
      return new Hash() {
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

         public int hash(final Object x) {
            return x.hashCode();
         }

         public boolean eqv(final Object x, final Object y) {
            return BoxesRunTime.equals(x, y);
         }

         public {
            Eq.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Hash$.class);
   }

   public Hash by$mZZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$3;
         private final Function1 f$2;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$3.hash$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$2.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$3.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$2.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToBoolean(this.f$2.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$3 = ev$3;
            this.f$2 = f$2;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$4;
         private final Function1 f$3;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$4.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$3.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$4.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$3.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToByte(this.f$3.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$4 = ev$4;
            this.f$3 = f$3;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$5;
         private final Function1 f$4;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$5.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$4.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$5.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$4.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToChar(this.f$4.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$5 = ev$5;
            this.f$4 = f$4;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$6;
         private final Function1 f$5;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$6.hash$mcD$sp(BoxesRunTime.unboxToDouble(this.f$5.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$6.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$5.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToDouble(this.f$5.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$6 = ev$6;
            this.f$5 = f$5;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$7;
         private final Function1 f$6;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$7.hash$mcF$sp(BoxesRunTime.unboxToFloat(this.f$6.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$7.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$6.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToFloat(this.f$6.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$7 = ev$7;
            this.f$6 = f$6;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$8;
         private final Function1 f$7;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$8.hash$mcI$sp(BoxesRunTime.unboxToInt(this.f$7.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$8.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$7.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToInt(this.f$7.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$8 = ev$8;
            this.f$7 = f$7;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$9;
         private final Function1 f$8;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$9.hash$mcJ$sp(BoxesRunTime.unboxToLong(this.f$8.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$9.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$8.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToLong(this.f$8.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$9 = ev$9;
            this.f$8 = f$8;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$10;
         private final Function1 f$9;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$10.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$9.apply(BoxesRunTime.boxToBoolean(x))));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$10.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$9.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToShort(this.f$9.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$10 = ev$10;
            this.f$9 = f$9;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mZVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcZ$sp(ev, f) {
         private final Hash ev$11;
         private final Function1 f$10;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final boolean x) {
            return this.hash$mcZ$sp(x);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return this.ev$11.hash$mcV$sp((BoxedUnit)this.f$10.apply(BoxesRunTime.boxToBoolean(x)));
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$11.eqv$mcV$sp((BoxedUnit)this.f$10.apply(BoxesRunTime.boxToBoolean(x)), (BoxedUnit)this.f$10.apply(BoxesRunTime.boxToBoolean(y)));
         }

         public {
            this.ev$11 = ev$11;
            this.f$10 = f$10;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$12;
         private final Function1 f$11;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$12.hash$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$11.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$12.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$11.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToBoolean(this.f$11.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$12 = ev$12;
            this.f$11 = f$11;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$13;
         private final Function1 f$12;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$13.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$12.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$13.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$12.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToByte(this.f$12.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$13 = ev$13;
            this.f$12 = f$12;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$14;
         private final Function1 f$13;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$14.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$13.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$14.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$13.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToChar(this.f$13.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$14 = ev$14;
            this.f$13 = f$13;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$15;
         private final Function1 f$14;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$15.hash$mcD$sp(BoxesRunTime.unboxToDouble(this.f$14.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$15.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$14.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToDouble(this.f$14.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$15 = ev$15;
            this.f$14 = f$14;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$16;
         private final Function1 f$15;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$16.hash$mcF$sp(BoxesRunTime.unboxToFloat(this.f$15.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$16.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$15.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToFloat(this.f$15.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$16 = ev$16;
            this.f$15 = f$15;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$17;
         private final Function1 f$16;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$17.hash$mcI$sp(BoxesRunTime.unboxToInt(this.f$16.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$17.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$16.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToInt(this.f$16.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$17 = ev$17;
            this.f$16 = f$16;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$18;
         private final Function1 f$17;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$18.hash$mcJ$sp(BoxesRunTime.unboxToLong(this.f$17.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$18.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$17.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToLong(this.f$17.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$18 = ev$18;
            this.f$17 = f$17;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$19;
         private final Function1 f$18;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$19.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$18.apply(BoxesRunTime.boxToByte(x))));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$19.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$18.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToShort(this.f$18.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$19 = ev$19;
            this.f$18 = f$18;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mBVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcB$sp(ev, f) {
         private final Hash ev$20;
         private final Function1 f$19;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
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

         public int hash(final byte x) {
            return this.hash$mcB$sp(x);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public int hash$mcB$sp(final byte x) {
            return this.ev$20.hash$mcV$sp((BoxedUnit)this.f$19.apply(BoxesRunTime.boxToByte(x)));
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$20.eqv$mcV$sp((BoxedUnit)this.f$19.apply(BoxesRunTime.boxToByte(x)), (BoxedUnit)this.f$19.apply(BoxesRunTime.boxToByte(y)));
         }

         public {
            this.ev$20 = ev$20;
            this.f$19 = f$19;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$21;
         private final Function1 f$20;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$21.hash$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$20.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$21.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$20.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToBoolean(this.f$20.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$21 = ev$21;
            this.f$20 = f$20;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$22;
         private final Function1 f$21;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$22.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$21.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$22.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$21.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToByte(this.f$21.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$22 = ev$22;
            this.f$21 = f$21;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$23;
         private final Function1 f$22;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$23.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$22.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$23.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$22.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToChar(this.f$22.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$23 = ev$23;
            this.f$22 = f$22;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$24;
         private final Function1 f$23;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$24.hash$mcD$sp(BoxesRunTime.unboxToDouble(this.f$23.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$24.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$23.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToDouble(this.f$23.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$24 = ev$24;
            this.f$23 = f$23;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$25;
         private final Function1 f$24;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$25.hash$mcF$sp(BoxesRunTime.unboxToFloat(this.f$24.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$25.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$24.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToFloat(this.f$24.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$25 = ev$25;
            this.f$24 = f$24;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$26;
         private final Function1 f$25;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$26.hash$mcI$sp(BoxesRunTime.unboxToInt(this.f$25.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$26.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$25.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToInt(this.f$25.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$26 = ev$26;
            this.f$25 = f$25;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$27;
         private final Function1 f$26;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$27.hash$mcJ$sp(BoxesRunTime.unboxToLong(this.f$26.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$27.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$26.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToLong(this.f$26.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$27 = ev$27;
            this.f$26 = f$26;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$28;
         private final Function1 f$27;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$28.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$27.apply(BoxesRunTime.boxToCharacter(x))));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$28.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$27.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToShort(this.f$27.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$28 = ev$28;
            this.f$27 = f$27;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mCVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcC$sp(ev, f) {
         private final Hash ev$29;
         private final Function1 f$28;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
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

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
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

         public int hash(final char x) {
            return this.hash$mcC$sp(x);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public int hash$mcC$sp(final char x) {
            return this.ev$29.hash$mcV$sp((BoxedUnit)this.f$28.apply(BoxesRunTime.boxToCharacter(x)));
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$29.eqv$mcV$sp((BoxedUnit)this.f$28.apply(BoxesRunTime.boxToCharacter(x)), (BoxedUnit)this.f$28.apply(BoxesRunTime.boxToCharacter(y)));
         }

         public {
            this.ev$29 = ev$29;
            this.f$28 = f$28;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$30;
         private final Function1 f$29;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$30.hash$mcZ$sp(this.f$29.apply$mcZD$sp(x));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$30.eqv$mcZ$sp(this.f$29.apply$mcZD$sp(x), this.f$29.apply$mcZD$sp(y));
         }

         public {
            this.ev$30 = ev$30;
            this.f$29 = f$29;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$31;
         private final Function1 f$30;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$31.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$30.apply(BoxesRunTime.boxToDouble(x))));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$31.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$30.apply(BoxesRunTime.boxToDouble(x))), BoxesRunTime.unboxToByte(this.f$30.apply(BoxesRunTime.boxToDouble(y))));
         }

         public {
            this.ev$31 = ev$31;
            this.f$30 = f$30;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$32;
         private final Function1 f$31;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$32.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$31.apply(BoxesRunTime.boxToDouble(x))));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$32.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$31.apply(BoxesRunTime.boxToDouble(x))), BoxesRunTime.unboxToChar(this.f$31.apply(BoxesRunTime.boxToDouble(y))));
         }

         public {
            this.ev$32 = ev$32;
            this.f$31 = f$31;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$33;
         private final Function1 f$32;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$33.hash$mcD$sp(this.f$32.apply$mcDD$sp(x));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$33.eqv$mcD$sp(this.f$32.apply$mcDD$sp(x), this.f$32.apply$mcDD$sp(y));
         }

         public {
            this.ev$33 = ev$33;
            this.f$32 = f$32;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$34;
         private final Function1 f$33;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$34.hash$mcF$sp(this.f$33.apply$mcFD$sp(x));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$34.eqv$mcF$sp(this.f$33.apply$mcFD$sp(x), this.f$33.apply$mcFD$sp(y));
         }

         public {
            this.ev$34 = ev$34;
            this.f$33 = f$33;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$35;
         private final Function1 f$34;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$35.hash$mcI$sp(this.f$34.apply$mcID$sp(x));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$35.eqv$mcI$sp(this.f$34.apply$mcID$sp(x), this.f$34.apply$mcID$sp(y));
         }

         public {
            this.ev$35 = ev$35;
            this.f$34 = f$34;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$36;
         private final Function1 f$35;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$36.hash$mcJ$sp(this.f$35.apply$mcJD$sp(x));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$36.eqv$mcJ$sp(this.f$35.apply$mcJD$sp(x), this.f$35.apply$mcJD$sp(y));
         }

         public {
            this.ev$36 = ev$36;
            this.f$35 = f$35;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$37;
         private final Function1 f$36;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            return this.ev$37.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$36.apply(BoxesRunTime.boxToDouble(x))));
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$37.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$36.apply(BoxesRunTime.boxToDouble(x))), BoxesRunTime.unboxToShort(this.f$36.apply(BoxesRunTime.boxToDouble(y))));
         }

         public {
            this.ev$37 = ev$37;
            this.f$36 = f$36;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mDVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcD$sp(ev, f) {
         private final Hash ev$38;
         private final Function1 f$37;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
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

         public int hash(final double x) {
            return this.hash$mcD$sp(x);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public int hash$mcD$sp(final double x) {
            Hash var10000 = this.ev$38;
            this.f$37.apply$mcVD$sp(x);
            return var10000.hash$mcV$sp(BoxedUnit.UNIT);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            Hash var10000 = this.ev$38;
            this.f$37.apply$mcVD$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$37.apply$mcVD$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$38 = ev$38;
            this.f$37 = f$37;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$39;
         private final Function1 f$38;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$39.hash$mcZ$sp(this.f$38.apply$mcZF$sp(x));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$39.eqv$mcZ$sp(this.f$38.apply$mcZF$sp(x), this.f$38.apply$mcZF$sp(y));
         }

         public {
            this.ev$39 = ev$39;
            this.f$38 = f$38;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$40;
         private final Function1 f$39;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$40.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$39.apply(BoxesRunTime.boxToFloat(x))));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$40.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$39.apply(BoxesRunTime.boxToFloat(x))), BoxesRunTime.unboxToByte(this.f$39.apply(BoxesRunTime.boxToFloat(y))));
         }

         public {
            this.ev$40 = ev$40;
            this.f$39 = f$39;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$41;
         private final Function1 f$40;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$41.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$40.apply(BoxesRunTime.boxToFloat(x))));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$41.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$40.apply(BoxesRunTime.boxToFloat(x))), BoxesRunTime.unboxToChar(this.f$40.apply(BoxesRunTime.boxToFloat(y))));
         }

         public {
            this.ev$41 = ev$41;
            this.f$40 = f$40;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$42;
         private final Function1 f$41;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$42.hash$mcD$sp(this.f$41.apply$mcDF$sp(x));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$42.eqv$mcD$sp(this.f$41.apply$mcDF$sp(x), this.f$41.apply$mcDF$sp(y));
         }

         public {
            this.ev$42 = ev$42;
            this.f$41 = f$41;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$43;
         private final Function1 f$42;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$43.hash$mcF$sp(this.f$42.apply$mcFF$sp(x));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$43.eqv$mcF$sp(this.f$42.apply$mcFF$sp(x), this.f$42.apply$mcFF$sp(y));
         }

         public {
            this.ev$43 = ev$43;
            this.f$42 = f$42;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$44;
         private final Function1 f$43;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$44.hash$mcI$sp(this.f$43.apply$mcIF$sp(x));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$44.eqv$mcI$sp(this.f$43.apply$mcIF$sp(x), this.f$43.apply$mcIF$sp(y));
         }

         public {
            this.ev$44 = ev$44;
            this.f$43 = f$43;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$45;
         private final Function1 f$44;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$45.hash$mcJ$sp(this.f$44.apply$mcJF$sp(x));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$45.eqv$mcJ$sp(this.f$44.apply$mcJF$sp(x), this.f$44.apply$mcJF$sp(y));
         }

         public {
            this.ev$45 = ev$45;
            this.f$44 = f$44;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$46;
         private final Function1 f$45;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            return this.ev$46.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$45.apply(BoxesRunTime.boxToFloat(x))));
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$46.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$45.apply(BoxesRunTime.boxToFloat(x))), BoxesRunTime.unboxToShort(this.f$45.apply(BoxesRunTime.boxToFloat(y))));
         }

         public {
            this.ev$46 = ev$46;
            this.f$45 = f$45;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mFVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcF$sp(ev, f) {
         private final Hash ev$47;
         private final Function1 f$46;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

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

         public int hash(final float x) {
            return this.hash$mcF$sp(x);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public int hash$mcF$sp(final float x) {
            Hash var10000 = this.ev$47;
            this.f$46.apply$mcVF$sp(x);
            return var10000.hash$mcV$sp(BoxedUnit.UNIT);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            Hash var10000 = this.ev$47;
            this.f$46.apply$mcVF$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$46.apply$mcVF$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$47 = ev$47;
            this.f$46 = f$46;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$48;
         private final Function1 f$47;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$48.hash$mcZ$sp(this.f$47.apply$mcZI$sp(x));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$48.eqv$mcZ$sp(this.f$47.apply$mcZI$sp(x), this.f$47.apply$mcZI$sp(y));
         }

         public {
            this.ev$48 = ev$48;
            this.f$47 = f$47;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$49;
         private final Function1 f$48;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$49.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$48.apply(BoxesRunTime.boxToInteger(x))));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$49.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$48.apply(BoxesRunTime.boxToInteger(x))), BoxesRunTime.unboxToByte(this.f$48.apply(BoxesRunTime.boxToInteger(y))));
         }

         public {
            this.ev$49 = ev$49;
            this.f$48 = f$48;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mICc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$50;
         private final Function1 f$49;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$50.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$49.apply(BoxesRunTime.boxToInteger(x))));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$50.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$49.apply(BoxesRunTime.boxToInteger(x))), BoxesRunTime.unboxToChar(this.f$49.apply(BoxesRunTime.boxToInteger(y))));
         }

         public {
            this.ev$50 = ev$50;
            this.f$49 = f$49;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$51;
         private final Function1 f$50;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$51.hash$mcD$sp(this.f$50.apply$mcDI$sp(x));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$51.eqv$mcD$sp(this.f$50.apply$mcDI$sp(x), this.f$50.apply$mcDI$sp(y));
         }

         public {
            this.ev$51 = ev$51;
            this.f$50 = f$50;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$52;
         private final Function1 f$51;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$52.hash$mcF$sp(this.f$51.apply$mcFI$sp(x));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$52.eqv$mcF$sp(this.f$51.apply$mcFI$sp(x), this.f$51.apply$mcFI$sp(y));
         }

         public {
            this.ev$52 = ev$52;
            this.f$51 = f$51;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$53;
         private final Function1 f$52;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$53.hash$mcI$sp(this.f$52.apply$mcII$sp(x));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$53.eqv$mcI$sp(this.f$52.apply$mcII$sp(x), this.f$52.apply$mcII$sp(y));
         }

         public {
            this.ev$53 = ev$53;
            this.f$52 = f$52;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$54;
         private final Function1 f$53;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$54.hash$mcJ$sp(this.f$53.apply$mcJI$sp(x));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$54.eqv$mcJ$sp(this.f$53.apply$mcJI$sp(x), this.f$53.apply$mcJI$sp(y));
         }

         public {
            this.ev$54 = ev$54;
            this.f$53 = f$53;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mISc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$55;
         private final Function1 f$54;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            return this.ev$55.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$54.apply(BoxesRunTime.boxToInteger(x))));
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$55.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$54.apply(BoxesRunTime.boxToInteger(x))), BoxesRunTime.unboxToShort(this.f$54.apply(BoxesRunTime.boxToInteger(y))));
         }

         public {
            this.ev$55 = ev$55;
            this.f$54 = f$54;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mIVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcI$sp(ev, f) {
         private final Hash ev$56;
         private final Function1 f$55;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

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

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public int hash(final int x) {
            return this.hash$mcI$sp(x);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public int hash$mcI$sp(final int x) {
            Hash var10000 = this.ev$56;
            this.f$55.apply$mcVI$sp(x);
            return var10000.hash$mcV$sp(BoxedUnit.UNIT);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            Hash var10000 = this.ev$56;
            this.f$55.apply$mcVI$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$55.apply$mcVI$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$56 = ev$56;
            this.f$55 = f$55;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$57;
         private final Function1 f$56;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$57.hash$mcZ$sp(this.f$56.apply$mcZJ$sp(x));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$57.eqv$mcZ$sp(this.f$56.apply$mcZJ$sp(x), this.f$56.apply$mcZJ$sp(y));
         }

         public {
            this.ev$57 = ev$57;
            this.f$56 = f$56;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$58;
         private final Function1 f$57;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$58.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$57.apply(BoxesRunTime.boxToLong(x))));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$58.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$57.apply(BoxesRunTime.boxToLong(x))), BoxesRunTime.unboxToByte(this.f$57.apply(BoxesRunTime.boxToLong(y))));
         }

         public {
            this.ev$58 = ev$58;
            this.f$57 = f$57;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$59;
         private final Function1 f$58;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$59.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$58.apply(BoxesRunTime.boxToLong(x))));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$59.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$58.apply(BoxesRunTime.boxToLong(x))), BoxesRunTime.unboxToChar(this.f$58.apply(BoxesRunTime.boxToLong(y))));
         }

         public {
            this.ev$59 = ev$59;
            this.f$58 = f$58;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$60;
         private final Function1 f$59;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$60.hash$mcD$sp(this.f$59.apply$mcDJ$sp(x));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$60.eqv$mcD$sp(this.f$59.apply$mcDJ$sp(x), this.f$59.apply$mcDJ$sp(y));
         }

         public {
            this.ev$60 = ev$60;
            this.f$59 = f$59;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$61;
         private final Function1 f$60;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$61.hash$mcF$sp(this.f$60.apply$mcFJ$sp(x));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$61.eqv$mcF$sp(this.f$60.apply$mcFJ$sp(x), this.f$60.apply$mcFJ$sp(y));
         }

         public {
            this.ev$61 = ev$61;
            this.f$60 = f$60;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$62;
         private final Function1 f$61;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$62.hash$mcI$sp(this.f$61.apply$mcIJ$sp(x));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$62.eqv$mcI$sp(this.f$61.apply$mcIJ$sp(x), this.f$61.apply$mcIJ$sp(y));
         }

         public {
            this.ev$62 = ev$62;
            this.f$61 = f$61;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$63;
         private final Function1 f$62;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$63.hash$mcJ$sp(this.f$62.apply$mcJJ$sp(x));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$63.eqv$mcJ$sp(this.f$62.apply$mcJJ$sp(x), this.f$62.apply$mcJJ$sp(y));
         }

         public {
            this.ev$63 = ev$63;
            this.f$62 = f$62;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$64;
         private final Function1 f$63;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            return this.ev$64.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$63.apply(BoxesRunTime.boxToLong(x))));
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$64.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$63.apply(BoxesRunTime.boxToLong(x))), BoxesRunTime.unboxToShort(this.f$63.apply(BoxesRunTime.boxToLong(y))));
         }

         public {
            this.ev$64 = ev$64;
            this.f$63 = f$63;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mJVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcJ$sp(ev, f) {
         private final Hash ev$65;
         private final Function1 f$64;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

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

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final long x) {
            return this.hash$mcJ$sp(x);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public int hash$mcJ$sp(final long x) {
            Hash var10000 = this.ev$65;
            this.f$64.apply$mcVJ$sp(x);
            return var10000.hash$mcV$sp(BoxedUnit.UNIT);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            Hash var10000 = this.ev$65;
            this.f$64.apply$mcVJ$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$64.apply$mcVJ$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$65 = ev$65;
            this.f$64 = f$64;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$66;
         private final Function1 f$65;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$66.hash$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$65.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$66.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$65.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToBoolean(this.f$65.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$66 = ev$66;
            this.f$65 = f$65;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$67;
         private final Function1 f$66;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$67.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$66.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$67.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$66.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToByte(this.f$66.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$67 = ev$67;
            this.f$66 = f$66;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$68;
         private final Function1 f$67;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$68.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$67.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$68.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$67.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToChar(this.f$67.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$68 = ev$68;
            this.f$67 = f$67;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$69;
         private final Function1 f$68;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$69.hash$mcD$sp(BoxesRunTime.unboxToDouble(this.f$68.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$69.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$68.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToDouble(this.f$68.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$69 = ev$69;
            this.f$68 = f$68;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$70;
         private final Function1 f$69;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$70.hash$mcF$sp(BoxesRunTime.unboxToFloat(this.f$69.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$70.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$69.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToFloat(this.f$69.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$70 = ev$70;
            this.f$69 = f$69;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$71;
         private final Function1 f$70;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$71.hash$mcI$sp(BoxesRunTime.unboxToInt(this.f$70.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$71.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$70.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToInt(this.f$70.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$71 = ev$71;
            this.f$70 = f$70;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$72;
         private final Function1 f$71;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$72.hash$mcJ$sp(BoxesRunTime.unboxToLong(this.f$71.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$72.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$71.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToLong(this.f$71.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$72 = ev$72;
            this.f$71 = f$71;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$73;
         private final Function1 f$72;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$73.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$72.apply(BoxesRunTime.boxToShort(x))));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$73.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$72.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToShort(this.f$72.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$73 = ev$73;
            this.f$72 = f$72;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mSVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcS$sp(ev, f) {
         private final Hash ev$74;
         private final Function1 f$73;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

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

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
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

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public int hash(final short x) {
            return this.hash$mcS$sp(x);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public int hash$mcS$sp(final short x) {
            return this.ev$74.hash$mcV$sp((BoxedUnit)this.f$73.apply(BoxesRunTime.boxToShort(x)));
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$74.eqv$mcV$sp((BoxedUnit)this.f$73.apply(BoxesRunTime.boxToShort(x)), (BoxedUnit)this.f$73.apply(BoxesRunTime.boxToShort(y)));
         }

         public {
            this.ev$74 = ev$74;
            this.f$73 = f$73;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVZc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$75;
         private final Function1 f$74;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$75.hash$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$74.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$75.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$74.apply(x)), BoxesRunTime.unboxToBoolean(this.f$74.apply(y)));
         }

         public {
            this.ev$75 = ev$75;
            this.f$74 = f$74;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVBc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$76;
         private final Function1 f$75;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$76.hash$mcB$sp(BoxesRunTime.unboxToByte(this.f$75.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$76.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$75.apply(x)), BoxesRunTime.unboxToByte(this.f$75.apply(y)));
         }

         public {
            this.ev$76 = ev$76;
            this.f$75 = f$75;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVCc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$77;
         private final Function1 f$76;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$77.hash$mcC$sp(BoxesRunTime.unboxToChar(this.f$76.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$77.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$76.apply(x)), BoxesRunTime.unboxToChar(this.f$76.apply(y)));
         }

         public {
            this.ev$77 = ev$77;
            this.f$76 = f$76;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVDc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$78;
         private final Function1 f$77;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$78.hash$mcD$sp(BoxesRunTime.unboxToDouble(this.f$77.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$78.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$77.apply(x)), BoxesRunTime.unboxToDouble(this.f$77.apply(y)));
         }

         public {
            this.ev$78 = ev$78;
            this.f$77 = f$77;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVFc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$79;
         private final Function1 f$78;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$79.hash$mcF$sp(BoxesRunTime.unboxToFloat(this.f$78.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$79.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$78.apply(x)), BoxesRunTime.unboxToFloat(this.f$78.apply(y)));
         }

         public {
            this.ev$79 = ev$79;
            this.f$78 = f$78;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVIc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$80;
         private final Function1 f$79;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$80.hash$mcI$sp(BoxesRunTime.unboxToInt(this.f$79.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$80.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$79.apply(x)), BoxesRunTime.unboxToInt(this.f$79.apply(y)));
         }

         public {
            this.ev$80 = ev$80;
            this.f$79 = f$79;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVJc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$81;
         private final Function1 f$80;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$81.hash$mcJ$sp(BoxesRunTime.unboxToLong(this.f$80.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$81.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$80.apply(x)), BoxesRunTime.unboxToLong(this.f$80.apply(y)));
         }

         public {
            this.ev$81 = ev$81;
            this.f$80 = f$80;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVSc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$82;
         private final Function1 f$81;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$82.hash$mcS$sp(BoxesRunTime.unboxToShort(this.f$81.apply(x)));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$82.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$81.apply(x)), BoxesRunTime.unboxToShort(this.f$81.apply(y)));
         }

         public {
            this.ev$82 = ev$82;
            this.f$81 = f$81;
            Eq.$init$(this);
         }
      };
   }

   public Hash by$mVVc$sp(final Function1 f, final Hash ev) {
      return new Hash$mcV$sp(ev, f) {
         private final Hash ev$83;
         private final Function1 f$82;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

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

         public int hash(final BoxedUnit x) {
            return this.hash$mcV$sp(x);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public int hash$mcV$sp(final BoxedUnit x) {
            return this.ev$83.hash$mcV$sp((BoxedUnit)this.f$82.apply(x));
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$83.eqv$mcV$sp((BoxedUnit)this.f$82.apply(x), (BoxedUnit)this.f$82.apply(y));
         }

         public {
            this.ev$83 = ev$83;
            this.f$82 = f$82;
            Eq.$init$(this);
         }
      };
   }

   private Hash$() {
   }
}
