package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192qAA\u0002\u0011\u0002G\u0005\u0001\u0002C\u0003\u0005\u0001\u0019\u0005qBA\u0007PI\u0016Le\u000e^3he\u0006$xN\u001d\u0006\u0003\t\u0015\t\u0011\"\u001b8uK\u001e\u0014\u0018\r^3\u000b\u0003\u0019\taA\u0019:fKj,7\u0001A\n\u0003\u0001%\u0001\"AC\u0007\u000e\u0003-Q\u0011\u0001D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001d-\u0011a!\u00118z%\u00164G\u0003\u0002\t\u001dC\r\u00022AC\t\u0014\u0013\t\u00112BA\u0003BeJ\f\u0017\u0010E\u0002\u0015/ei\u0011!\u0006\u0006\u0003-\u0015\ta\u0001\\5oC2<\u0017B\u0001\r\u0016\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005)Q\u0012BA\u000e\f\u0005\u0019!u.\u001e2mK\")Q$\u0001a\u0001=\u0005\ta\rE\u0003\u000b?MI2#\u0003\u0002!\u0017\tIa)\u001e8di&|gN\r\u0005\u0006E\u0005\u0001\raE\u0001\u0003sBBQ\u0001J\u0001A\u0002\u0015\n\u0011\u0001\u001e\t\u0004\u0015EI\u0002"
)
public interface OdeIntegrator {
   DenseVector[] integrate(final Function2 f, final DenseVector y0, final double[] t);
}
