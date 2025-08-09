package spire.std;

import cats.kernel.Eq;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001B\u0003\u0001\u0015!A\u0001\b\u0001B\u0002B\u0003-\u0011\bC\u0003;\u0001\u0011\u00051\bC\u0003A\u0001\u0011\u0005\u0011I\u0001\u0005PaRLwN\\#r\u0015\t1q!A\u0002ti\u0012T\u0011\u0001C\u0001\u0006gBL'/Z\u0002\u0001+\tYqe\u0005\u0003\u0001\u0019I\u0001\u0004CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\rE\u0002\u0014?\tr!\u0001\u0006\u000f\u000f\u0005UQbB\u0001\f\u001a\u001b\u00059\"B\u0001\r\n\u0003\u0019a$o\\8u}%\t\u0001\"\u0003\u0002\u001c\u000f\u00059\u0011\r\\4fEJ\f\u0017BA\u000f\u001f\u0003\u001d\u0001\u0018mY6bO\u0016T!aG\u0004\n\u0005\u0001\n#AA#r\u0015\tib\u0004E\u0002\u000eG\u0015J!\u0001\n\b\u0003\r=\u0003H/[8o!\t1s\u0005\u0004\u0001\u0005\u000b!\u0002!\u0019A\u0015\u0003\u0003\u0005\u000b\"AK\u0017\u0011\u00055Y\u0013B\u0001\u0017\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u0018\n\u0005=r!aA!osB\u0011\u0011'\u000e\b\u0003eQr!AF\u001a\n\u0003=I!!\b\b\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'BA\u000f\u000f\u0003))g/\u001b3f]\u000e,G%\u000e\t\u0004'})\u0013A\u0002\u001fj]&$h\bF\u0001=)\tit\bE\u0002?\u0001\u0015j\u0011!\u0002\u0005\u0006q\t\u0001\u001d!O\u0001\u0004KF4Hc\u0001\"F\u000fB\u0011QbQ\u0005\u0003\t:\u0011qAQ8pY\u0016\fg\u000eC\u0003G\u0007\u0001\u0007!%A\u0001y\u0011\u0015A5\u00011\u0001#\u0003\u0005I\b\u0006\u0002\u0001K\u001b:\u0003\"!D&\n\u00051s!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class OptionEq implements Eq {
   private static final long serialVersionUID = 0L;
   private final Eq evidence$5;

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

   public boolean eqv(final Option x, final Option y) {
      Tuple2 var4 = new Tuple2(x, y);
      boolean var3;
      if (var4 != null) {
         Option var5 = (Option)var4._1();
         Option var6 = (Option)var4._2();
         if (var5 instanceof Some) {
            Some var7 = (Some)var5;
            Object x = var7.value();
            if (var6 instanceof Some) {
               Some var9 = (Some)var6;
               Object y = var9.value();
               var3 = spire.algebra.package$.MODULE$.Eq().apply(this.evidence$5).eqv(x, y);
               return var3;
            }
         }
      }

      if (var4 != null) {
         Option var11 = (Option)var4._1();
         Option var12 = (Option)var4._2();
         if (.MODULE$.equals(var11) && .MODULE$.equals(var12)) {
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   public OptionEq(final Eq evidence$5) {
      this.evidence$5 = evidence$5;
      Eq.$init$(this);
   }
}
