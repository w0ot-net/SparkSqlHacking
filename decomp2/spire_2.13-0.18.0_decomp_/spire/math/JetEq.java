package spire.math;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005Q3Q!\u0002\u0004\u0001\r)A\u0001B\u000f\u0001\u0003\u0004\u0003\u0006Ya\u000f\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u0015\u0002!\te\u0013\u0002\u0006\u0015\u0016$X)\u001d\u0006\u0003\u000f!\tA!\\1uQ*\t\u0011\"A\u0003ta&\u0014X-\u0006\u0002\fSM!\u0001\u0001\u0004\n3!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191\u0003I\u0012\u000f\u0005QibBA\u000b\u001c\u001d\t1\"$D\u0001\u0018\u0015\tA\u0012$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005I\u0011B\u0001\u000f\t\u0003\u001d\tGnZ3ce\u0006L!AH\u0010\u0002\u000fA\f7m[1hK*\u0011A\u0004C\u0005\u0003C\t\u0012!!R9\u000b\u0005yy\u0002c\u0001\u0013&O5\ta!\u0003\u0002'\r\t\u0019!*\u001a;\u0011\u0005!JC\u0002\u0001\u0003\u0006U\u0001\u0011\ra\u000b\u0002\u0002)F\u0011Af\f\t\u0003\u001b5J!A\f\b\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002M\u0005\u0003c9\u00111!\u00118z!\t\u0019tG\u0004\u00025m9\u0011a#N\u0005\u0002\u001f%\u0011aDD\u0005\u0003qe\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\b\b\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002\u0014A\u001d\na\u0001P5oSRtD#\u0001 \u0015\u0005}\u0002\u0005c\u0001\u0013\u0001O!)!H\u0001a\u0002w\u0005\u0019Q-\u001d<\u0015\u0007\r3\u0005\n\u0005\u0002\u000e\t&\u0011QI\u0004\u0002\b\u0005>|G.Z1o\u0011\u001595\u00011\u0001$\u0003\u0005A\b\"B%\u0004\u0001\u0004\u0019\u0013!A=\u0002\t9,\u0017O\u001e\u000b\u0004\u00072k\u0005\"B$\u0005\u0001\u0004\u0019\u0003\"B%\u0005\u0001\u0004\u0019\u0003\u0006\u0002\u0001P%N\u0003\"!\u0004)\n\u0005Es!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class JetEq implements Eq {
   private static final long serialVersionUID = 0L;
   private final Eq evidence$2;

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

   public boolean eqv(final Jet x, final Jet y) {
      return x.eqv(y, this.evidence$2);
   }

   public boolean neqv(final Jet x, final Jet y) {
      return x.neqv(y, this.evidence$2);
   }

   public JetEq(final Eq evidence$2) {
      this.evidence$2 = evidence$2;
      Eq.$init$(this);
   }
}
