package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0004\b\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001a\u0011!\u0001\u0003A!A!\u0002\u0013\t\u0003\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u000b!\u0002A\u0011A\u0015\u0006\t=\u0002\u0001\u0001\r\u0005\u0006\u007f\u0001!)\u0002Q\u0004\b\u0003:\t\t\u0011#\u0001C\r\u001dia\"!A\t\u0002\rCQ\u0001K\u0005\u0005\u0002\u001dCq\u0001S\u0005\u0012\u0002\u0013\u0005\u0011\nC\u0004U\u0013E\u0005I\u0011A%\u00035\u0011{'/\\1oIB\u0013\u0018N\\2fqU\u001a\u0014J\u001c;fOJ\fGo\u001c:\u000b\u0005=\u0001\u0012!C5oi\u0016<'/\u0019;f\u0015\u0005\t\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001!\u0002CA\u000b\u0017\u001b\u0005q\u0011BA\f\u000f\u0005q\t\u0005/Y2iK\u0006#\u0017\r\u001d;jm\u0016\u001cF/\u001a9J]R,wM]1u_J\fq!\\5o'R,\u0007\u000f\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004E_V\u0014G.Z\u0001\b[\u0006D8\u000b^3q\u0003\u0019\u0011X\r\u001c+pYB\u0019!%J\r\u000e\u0003\rR!\u0001\n\t\u0002\r1Lg.\u00197h\u0013\t13EA\u0006EK:\u001cXMV3di>\u0014\u0018AB1cgR{G.\u0001\u0004=S:LGO\u0010\u000b\u0006U-bSF\f\t\u0003+\u0001AQ\u0001G\u0003A\u0002eAQaH\u0003A\u0002eAq\u0001I\u0003\u0011\u0002\u0003\u0007\u0011\u0005C\u0004(\u000bA\u0005\t\u0019A\u0011\u0003\u0003Q\u0003\"!\r \u000e\u0003IR!a\r\u001b\u0002\u00119|gn\u001d;jM\u001aT!!\u000e\u001c\u0002\u0007=$WM\u0003\u00028q\u0005)Q.\u0019;ig)\u0011\u0011HO\u0001\bG>lWn\u001c8t\u0015\tYD(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002{\u0005\u0019qN]4\n\u00055\u0011\u0014AB2sK\u0006$X-F\u00011\u0003i!uN]7b]\u0012\u0004&/\u001b8dKb*4'\u00138uK\u001e\u0014\u0018\r^8s!\t)\u0012b\u0005\u0002\n\tB\u0011!$R\u0005\u0003\rn\u0011a!\u00118z%\u00164G#\u0001\"\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\u0005Q%FA\u0011LW\u0005a\u0005CA'S\u001b\u0005q%BA(Q\u0003%)hn\u00195fG.,GM\u0003\u0002R7\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Ms%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ\u0002"
)
public class DormandPrince853Integrator extends ApacheAdaptiveStepIntegrator {
   private final double minStep;
   private final double maxStep;

   public static DenseVector $lessinit$greater$default$4() {
      return DormandPrince853Integrator$.MODULE$.$lessinit$greater$default$4();
   }

   public static DenseVector $lessinit$greater$default$3() {
      return DormandPrince853Integrator$.MODULE$.$lessinit$greater$default$3();
   }

   public final org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator create() {
      return new org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator(this.minStep, this.maxStep, ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
   }

   public DormandPrince853Integrator(final double minStep, final double maxStep, final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
      this.minStep = minStep;
      this.maxStep = maxStep;
   }
}
