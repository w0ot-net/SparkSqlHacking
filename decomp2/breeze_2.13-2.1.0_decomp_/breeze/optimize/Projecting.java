package breeze.optimize;

import breeze.math.Module;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0019\u0005\u0001\u0004C\u0003(\u0001\u0011\u0005\u0001F\u0001\u0006Qe>TWm\u0019;j]\u001eT!AB\u0004\u0002\u0011=\u0004H/[7ju\u0016T\u0011\u0001C\u0001\u0007EJ,WM_3\u0004\u0001U\u00111BH\n\u0003\u00011\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0015!\tiQ#\u0003\u0002\u0017\u001d\t!QK\\5u\u0003)\u0001(o\u001c6fGRLwN\\\u000b\u00023A!QB\u0007\u000f\u001d\u0013\tYbBA\u0005Gk:\u001cG/[8ocA\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001!\u0005\u0005!\u0016CA\u0011%!\ti!%\u0003\u0002$\u001d\t9aj\u001c;iS:<\u0007CA\u0007&\u0013\t1cBA\u0002B]f\fq\u0002\u001d:pU\u0016\u001cG/\u001a3WK\u000e$xN\u001d\u000b\u0004SU:DC\u0001\u000f+\u0011\u0015Y3\u0001q\u0001-\u0003\u001918\u000f]1dKB!Q\u0006\r\u000f3\u001b\u0005q#BA\u0018\b\u0003\u0011i\u0017\r\u001e5\n\u0005Er#AB'pIVdW\r\u0005\u0002\u000eg%\u0011AG\u0004\u0002\u0007\t>,(\r\\3\t\u000bY\u001a\u0001\u0019\u0001\u000f\u0002\u0003aDQ\u0001O\u0002A\u0002q\t\u0011a\u001a"
)
public interface Projecting {
   Function1 projection();

   // $FF: synthetic method
   static Object projectedVector$(final Projecting $this, final Object x, final Object g, final Module vspace) {
      return $this.projectedVector(x, g, vspace);
   }

   default Object projectedVector(final Object x, final Object g, final Module vspace) {
      return vspace.subVV().apply(this.projection().apply(vspace.addVV().apply(x, g)), x);
   }

   static void $init$(final Projecting $this) {
   }
}
