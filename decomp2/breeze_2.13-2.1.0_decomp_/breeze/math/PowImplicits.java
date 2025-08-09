package breeze.math;

import breeze.numerics.IntMath$;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Qa\u0006\r\t\u0002u1Qa\b\r\t\u0002\u0001BQaJ\u0001\u0005\u0002!2A!K\u0001\u0002U!A1f\u0001B\u0001B\u0003%A\u0006C\u0003(\u0007\u0011\u0005q\u0006C\u00034\u0007\u0011\u0005A\u0007C\u00048\u0003\u0005\u0005I1\u0001\u001d\u0007\ti\n\u0011a\u000f\u0005\tW!\u0011\t\u0011)A\u0005y!)q\u0005\u0003C\u0001\u007f!)1\u0007\u0003C\u0001\u0005\"9A)AA\u0001\n\u0007)e\u0001B$\u0002\u0003!C\u0001bK\u0007\u0003\u0002\u0003\u0006I!\u0013\u0005\u0006O5!\t\u0001\u0014\u0005\u0006g5!\ta\u0014\u0005\b#\u0006\t\t\u0011b\u0001S\r\u0011!\u0016!A+\t\u0011-\u0012\"\u0011!Q\u0001\nYCQa\n\n\u0005\u0002eCQa\r\n\u0005\u0002qCqAX\u0001\u0002\u0002\u0013\rq,\u0001\u0007Q_^LU\u000e\u001d7jG&$8O\u0003\u0002\u001a5\u0005!Q.\u0019;i\u0015\u0005Y\u0012A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005y\tQ\"\u0001\r\u0003\u0019A{w/S7qY&\u001c\u0017\u000e^:\u0014\u0005\u0005\t\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002;\tIAi\\;cY\u0016\u0004vn^\n\u0003\u0007\u0005\n\u0011\u0001\u001f\t\u0003E5J!AL\u0012\u0003\r\u0011{WO\u00197f)\t\u0001$\u0007\u0005\u00022\u00075\t\u0011\u0001C\u0003,\u000b\u0001\u0007A&A\u0002q_^$\"\u0001L\u001b\t\u000bY2\u0001\u0019\u0001\u0017\u0002\u0003e\f\u0011\u0002R8vE2,\u0007k\\<\u0015\u0005AJ\u0004\"B\u0016\b\u0001\u0004a#\u0001\u0003$m_\u0006$\bk\\<\u0014\u0005!\t\u0003C\u0001\u0012>\u0013\tq4EA\u0003GY>\fG\u000f\u0006\u0002A\u0003B\u0011\u0011\u0007\u0003\u0005\u0006W)\u0001\r\u0001\u0010\u000b\u0003y\rCQAN\u0006A\u0002q\n\u0001B\u00127pCR\u0004vn\u001e\u000b\u0003\u0001\u001aCQa\u000b\u0007A\u0002q\u0012a!\u00138u!><8CA\u0007\"!\t\u0011#*\u0003\u0002LG\t\u0019\u0011J\u001c;\u0015\u00055s\u0005CA\u0019\u000e\u0011\u0015Ys\u00021\u0001J)\tI\u0005\u000bC\u00037!\u0001\u0007\u0011*\u0001\u0004J]R\u0004vn\u001e\u000b\u0003\u001bNCQaK\tA\u0002%\u0013q\u0001T8oOB{wo\u0005\u0002\u0013CA\u0011!eV\u0005\u00031\u000e\u0012A\u0001T8oOR\u0011!l\u0017\t\u0003cIAQa\u000b\u000bA\u0002Y#\"AV/\t\u000bY*\u0002\u0019\u0001,\u0002\u000f1{gn\u001a)poR\u0011!\f\u0019\u0005\u0006WY\u0001\rA\u0016"
)
public final class PowImplicits {
   public static LongPow LongPow(final long x) {
      return PowImplicits$.MODULE$.LongPow(x);
   }

   public static IntPow IntPow(final int x) {
      return PowImplicits$.MODULE$.IntPow(x);
   }

   public static FloatPow FloatPow(final float x) {
      return PowImplicits$.MODULE$.FloatPow(x);
   }

   public static DoublePow DoublePow(final double x) {
      return PowImplicits$.MODULE$.DoublePow(x);
   }

   public static class DoublePow {
      private final double x;

      public double pow(final double y) {
         return .MODULE$.pow(this.x, y);
      }

      public DoublePow(final double x) {
         this.x = x;
      }
   }

   public static class FloatPow {
      private final float x;

      public float pow(final float y) {
         return (float).MODULE$.pow((double)this.x, (double)y);
      }

      public FloatPow(final float x) {
         this.x = x;
      }
   }

   public static class IntPow {
      private final int x;

      public int pow(final int y) {
         return IntMath$.MODULE$.ipow(this.x, y);
      }

      public IntPow(final int x) {
         this.x = x;
      }
   }

   public static class LongPow {
      private final long x;

      public long pow(final long y) {
         return IntMath$.MODULE$.ipow(this.x, y);
      }

      public LongPow(final long x) {
         this.x = x;
      }
   }
}
