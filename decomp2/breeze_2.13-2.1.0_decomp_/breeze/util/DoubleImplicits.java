package breeze.util;

import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3q\u0001E\t\u0011\u0002\u0007\u0005a\u0003C\u0003\u001e\u0001\u0011\u0005aD\u0002\u0003#\u0001\u0005\u0019\u0003\u0002\u0003\u0013\u0003\u0005\u0003\u0005\u000b\u0011B\u0013\t\u000b!\u0012A\u0011A\u0015\t\u000b5\u0012A\u0011\u0001\u0018\t\u000fY\u0012\u0011\u0013!C\u0001o!)!I\u0001C\u0001\u0007\"9A\tAA\u0001\n\u0007)e\u0001B$\u0001\u0003!C\u0001\u0002J\u0005\u0003\u0002\u0003\u0006I!\u0013\u0005\u0006Q%!\t\u0001\u0014\u0005\u0006[%!\ta\u0014\u0005\bm%\t\n\u0011\"\u00018\u0011\u0015\u0011\u0015\u0002\"\u0001D\u0011\u001d\u0011\u0006!!A\u0005\u0004M\u0013q\u0002R8vE2,\u0017*\u001c9mS\u000eLGo\u001d\u0006\u0003%M\tA!\u001e;jY*\tA#\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001q\u0003\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0007\u0011\n\u0005\u0005J\"\u0001B+oSR\u0014!BU5dQ\u0012{WO\u00197f'\t\u0011q#A\u0001y!\tAb%\u0003\u0002(3\t1Ai\\;cY\u0016\fa\u0001P5oSRtDC\u0001\u0016-!\tY#!D\u0001\u0001\u0011\u0015!C\u00011\u0001&\u0003\u001d\u0019Gn\\:f)>$2a\f\u001a5!\tA\u0002'\u0003\u000223\t9!i\\8mK\u0006t\u0007\"B\u001a\u0006\u0001\u0004)\u0013!A=\t\u000fU*\u0001\u0013!a\u0001K\u0005\u0019Ao\u001c7\u0002#\rdwn]3U_\u0012\"WMZ1vYR$#'F\u00019U\t)\u0013hK\u0001;!\tY\u0004)D\u0001=\u0015\tid(A\u0005v]\u000eDWmY6fI*\u0011q(G\u0001\u000bC:tw\u000e^1uS>t\u0017BA!=\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\fSN$\u0015M\\4fe>,8/F\u00010\u0003)\u0011\u0016n\u00195E_V\u0014G.\u001a\u000b\u0003U\u0019CQ\u0001\n\u0005A\u0002\u0015\u0012\u0011BU5dQ\u001acw.\u0019;\u0014\u0005%9\u0002C\u0001\rK\u0013\tY\u0015DA\u0003GY>\fG\u000f\u0006\u0002N\u001dB\u00111&\u0003\u0005\u0006I-\u0001\r!\u0013\u000b\u0004_A\u000b\u0006\"B\u001a\r\u0001\u0004I\u0005bB\u001b\r!\u0003\u0005\r!J\u0001\n%&\u001c\u0007N\u00127pCR$\"!\u0014+\t\u000b\u0011z\u0001\u0019A%"
)
public interface DoubleImplicits {
   // $FF: synthetic method
   static RichDouble RichDouble$(final DoubleImplicits $this, final double x) {
      return $this.RichDouble(x);
   }

   default RichDouble RichDouble(final double x) {
      return new RichDouble(x);
   }

   // $FF: synthetic method
   static RichFloat RichFloat$(final DoubleImplicits $this, final float x) {
      return $this.RichFloat(x);
   }

   default RichFloat RichFloat(final float x) {
      return new RichFloat(x);
   }

   static void $init$(final DoubleImplicits $this) {
   }

   public class RichDouble {
      private final double x;
      // $FF: synthetic field
      public final DoubleImplicits $outer;

      public boolean closeTo(final double y, final double tol) {
         return .MODULE$.abs(this.x - y) / (.MODULE$.abs(this.x) + .MODULE$.abs(y) + 1.0E-10) < tol;
      }

      public double closeTo$default$2() {
         return 1.0E-5;
      }

      public boolean isDangerous() {
         return Double.isNaN(this.x) || Double.isInfinite(this.x);
      }

      // $FF: synthetic method
      public DoubleImplicits breeze$util$DoubleImplicits$RichDouble$$$outer() {
         return this.$outer;
      }

      public RichDouble(final double x) {
         this.x = x;
         if (DoubleImplicits.this == null) {
            throw null;
         } else {
            this.$outer = DoubleImplicits.this;
            super();
         }
      }
   }

   public class RichFloat {
      private final float x;
      // $FF: synthetic field
      public final DoubleImplicits $outer;

      public boolean closeTo(final float y, final double tol) {
         return (double).MODULE$.abs(this.x - y) / ((double)(.MODULE$.abs(this.x) + .MODULE$.abs(y)) + 1.0E-10) < tol;
      }

      public double closeTo$default$2() {
         return 1.0E-5;
      }

      public boolean isDangerous() {
         return Float.isNaN(this.x) || Float.isInfinite(this.x);
      }

      // $FF: synthetic method
      public DoubleImplicits breeze$util$DoubleImplicits$RichFloat$$$outer() {
         return this.$outer;
      }

      public RichFloat(final float x) {
         this.x = x;
         if (DoubleImplicits.this == null) {
            throw null;
         } else {
            this.$outer = DoubleImplicits.this;
            super();
         }
      }
   }
}
