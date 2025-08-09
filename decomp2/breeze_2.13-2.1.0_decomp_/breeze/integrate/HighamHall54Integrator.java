package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0004\b\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001a\u0011!\u0001\u0003A!A!\u0002\u0013\t\u0003\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u000b!\u0002A\u0011A\u0015\u0006\t=\u0002\u0001\u0001\r\u0005\u0006\u007f\u0001!)\u0002Q\u0004\b\u0003:\t\t\u0011#\u0001C\r\u001dia\"!A\t\u0002\rCQ\u0001K\u0005\u0005\u0002\u001dCq\u0001S\u0005\u0012\u0002\u0013\u0005\u0011\nC\u0004U\u0013E\u0005I\u0011A%\u0003-!Kw\r[1n\u0011\u0006dG.\u000e\u001bJ]R,wM]1u_JT!a\u0004\t\u0002\u0013%tG/Z4sCR,'\"A\t\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001\u0001\u000b\u0011\u0005U1R\"\u0001\b\n\u0005]q!\u0001H!qC\u000eDW-\u00113baRLg/Z*uKBLe\u000e^3he\u0006$xN]\u0001\b[&t7\u000b^3q!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019!u.\u001e2mK\u00069Q.\u0019=Ti\u0016\u0004\u0018A\u0002:fYR{G\u000eE\u0002#Kei\u0011a\t\u0006\u0003IA\ta\u0001\\5oC2<\u0017B\u0001\u0014$\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0002\r\u0005\u00147\u000fV8m\u0003\u0019a\u0014N\\5u}Q)!f\u000b\u0017.]A\u0011Q\u0003\u0001\u0005\u00061\u0015\u0001\r!\u0007\u0005\u0006?\u0015\u0001\r!\u0007\u0005\bA\u0015\u0001\n\u00111\u0001\"\u0011\u001d9S\u0001%AA\u0002\u0005\u0012\u0011\u0001\u0016\t\u0003cyj\u0011A\r\u0006\u0003gQ\n\u0001B\\8ogRLgM\u001a\u0006\u0003kY\n1a\u001c3f\u0015\t9\u0004(A\u0003nCRD7G\u0003\u0002:u\u000591m\\7n_:\u001c(BA\u001e=\u0003\u0019\t\u0007/Y2iK*\tQ(A\u0002pe\u001eL!!\u0004\u001a\u0002\r\r\u0014X-\u0019;f+\u0005\u0001\u0014A\u0006%jO\"\fW\u000eS1mYV\"\u0014J\u001c;fOJ\fGo\u001c:\u0011\u0005UI1CA\u0005E!\tQR)\u0003\u0002G7\t1\u0011I\\=SK\u001a$\u0012AQ\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003)S#!I&,\u00031\u0003\"!\u0014*\u000e\u00039S!a\u0014)\u0002\u0013Ut7\r[3dW\u0016$'BA)\u001c\u0003)\tgN\\8uCRLwN\\\u0005\u0003':\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0001"
)
public class HighamHall54Integrator extends ApacheAdaptiveStepIntegrator {
   private final double minStep;
   private final double maxStep;

   public static DenseVector $lessinit$greater$default$4() {
      return HighamHall54Integrator$.MODULE$.$lessinit$greater$default$4();
   }

   public static DenseVector $lessinit$greater$default$3() {
      return HighamHall54Integrator$.MODULE$.$lessinit$greater$default$3();
   }

   public final org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator create() {
      return new org.apache.commons.math3.ode.nonstiff.HighamHall54Integrator(this.minStep, this.maxStep, ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
   }

   public HighamHall54Integrator(final double minStep, final double maxStep, final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
      this.minStep = minStep;
      this.maxStep = maxStep;
   }
}
