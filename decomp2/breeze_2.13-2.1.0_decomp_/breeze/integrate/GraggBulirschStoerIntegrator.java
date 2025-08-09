package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0004\b\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001a\u0011!\u0001\u0003A!A!\u0002\u0013\t\u0003\u0002C\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u000b!\u0002A\u0011A\u0015\u0006\t=\u0002\u0001\u0001\r\u0005\u0006\u007f\u0001!)\u0002Q\u0004\b\u0003:\t\t\u0011#\u0001C\r\u001dia\"!A\t\u0002\rCQ\u0001K\u0005\u0005\u0002\u001dCq\u0001S\u0005\u0012\u0002\u0013\u0005\u0011\nC\u0004U\u0013E\u0005I\u0011A%\u00039\u001d\u0013\u0018mZ4Ck2L'o]2i'R|WM]%oi\u0016<'/\u0019;pe*\u0011q\u0002E\u0001\nS:$Xm\u001a:bi\u0016T\u0011!E\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001\u0001\u0006\t\u0003+Yi\u0011AD\u0005\u0003/9\u0011A$\u00119bG\",\u0017\tZ1qi&4Xm\u0015;fa&sG/Z4sCR|'/A\u0004nS:\u001cF/\u001a9\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0011{WO\u00197f\u0003\u001di\u0017\r_*uKB\faA]3m)>d\u0007c\u0001\u0012&35\t1E\u0003\u0002%!\u00051A.\u001b8bY\u001eL!AJ\u0012\u0003\u0017\u0011+gn]3WK\u000e$xN]\u0001\u0007C\n\u001cHk\u001c7\u0002\rqJg.\u001b;?)\u0015Q3\u0006L\u0017/!\t)\u0002\u0001C\u0003\u0019\u000b\u0001\u0007\u0011\u0004C\u0003 \u000b\u0001\u0007\u0011\u0004C\u0004!\u000bA\u0005\t\u0019A\u0011\t\u000f\u001d*\u0001\u0013!a\u0001C\t\tA\u000b\u0005\u00022}5\t!G\u0003\u00024i\u0005Aan\u001c8ti&4gM\u0003\u00026m\u0005\u0019q\u000eZ3\u000b\u0005]B\u0014!B7bi\"\u001c$BA\u001d;\u0003\u001d\u0019w.\\7p]NT!a\u000f\u001f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0014aA8sO&\u0011QBM\u0001\u0007GJ,\u0017\r^3\u0016\u0003A\nAd\u0012:bO\u001e\u0014U\u000f\\5sg\u000eD7\u000b^8fe&sG/Z4sCR|'\u000f\u0005\u0002\u0016\u0013M\u0011\u0011\u0002\u0012\t\u00035\u0015K!AR\u000e\u0003\r\u0005s\u0017PU3g)\u0005\u0011\u0015a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'F\u0001KU\t\t3jK\u0001M!\ti%+D\u0001O\u0015\ty\u0005+A\u0005v]\u000eDWmY6fI*\u0011\u0011kG\u0001\u000bC:tw\u000e^1uS>t\u0017BA*O\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b"
)
public class GraggBulirschStoerIntegrator extends ApacheAdaptiveStepIntegrator {
   private final double minStep;
   private final double maxStep;

   public static DenseVector $lessinit$greater$default$4() {
      return GraggBulirschStoerIntegrator$.MODULE$.$lessinit$greater$default$4();
   }

   public static DenseVector $lessinit$greater$default$3() {
      return GraggBulirschStoerIntegrator$.MODULE$.$lessinit$greater$default$3();
   }

   public final org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator create() {
      return new org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator(this.minStep, this.maxStep, ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
   }

   public GraggBulirschStoerIntegrator(final double minStep, final double maxStep, final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
      this.minStep = minStep;
      this.maxStep = maxStep;
   }
}
