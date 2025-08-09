package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3AAD\b\u0001)!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\"\u0011!!\u0003A!A!\u0002\u0013\t\u0003\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u00111\u0002!\u0011!Q\u0001\n\u0019BQ!\f\u0001\u0005\u00029*A!\u000e\u0001\u0001m!)Q\t\u0001C\u000b\r\u001e9qiDA\u0001\u0012\u0003Aea\u0002\b\u0010\u0003\u0003E\t!\u0013\u0005\u0006[)!\t!\u0014\u0005\b\u001d*\t\n\u0011\"\u0001P\u0011\u001dQ&\"%A\u0005\u0002=\u0013a#\u00113b[Nlu.\u001e7u_:Le\u000e^3he\u0006$xN\u001d\u0006\u0003!E\t\u0011\"\u001b8uK\u001e\u0014\u0018\r^3\u000b\u0003I\taA\u0019:fKj,7\u0001A\n\u0003\u0001U\u0001\"AF\f\u000e\u0003=I!\u0001G\b\u0003+\u0005\u0003\u0018m\u00195f\u0003\u0012\fWn]%oi\u0016<'/\u0019;pe\u0006)qN\u001d3feB\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t\u0019\u0011J\u001c;\u0002\u000f5Lgn\u0015;faB\u00111DI\u0005\u0003Gq\u0011a\u0001R8vE2,\u0017aB7bqN#X\r]\u0001\u0007e\u0016dGk\u001c7\u0011\u0007\u001dR\u0013%D\u0001)\u0015\tI\u0013#\u0001\u0004mS:\fGnZ\u0005\u0003W!\u00121\u0002R3og\u00164Vm\u0019;pe\u00061\u0011MY:U_2\fa\u0001P5oSRtDCB\u00181cI\u001aD\u0007\u0005\u0002\u0017\u0001!)\u0011D\u0002a\u00015!)\u0001E\u0002a\u0001C!)AE\u0002a\u0001C!9QE\u0002I\u0001\u0002\u00041\u0003b\u0002\u0017\u0007!\u0003\u0005\rA\n\u0002\u0002)B\u0011q\u0007R\u0007\u0002q)\u0011\u0011HO\u0001\t]>t7\u000f^5gM*\u00111\bP\u0001\u0004_\u0012,'BA\u001f?\u0003\u0015i\u0017\r\u001e54\u0015\ty\u0004)A\u0004d_6lwN\\:\u000b\u0005\u0005\u0013\u0015AB1qC\u000eDWMC\u0001D\u0003\ry'oZ\u0005\u0003\u001da\naa\u0019:fCR,W#\u0001\u001c\u0002-\u0005#\u0017-\\:N_VdGo\u001c8J]R,wM]1u_J\u0004\"A\u0006\u0006\u0014\u0005)Q\u0005CA\u000eL\u0013\taED\u0001\u0004B]f\u0014VM\u001a\u000b\u0002\u0011\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*\u0012\u0001\u0015\u0016\u0003ME[\u0013A\u0015\t\u0003'bk\u0011\u0001\u0016\u0006\u0003+Z\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005]c\u0012AC1o]>$\u0018\r^5p]&\u0011\u0011\f\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S\u0007"
)
public class AdamsMoultonIntegrator extends ApacheAdamsIntegrator {
   private final int order;
   private final double minStep;
   private final double maxStep;

   public static DenseVector $lessinit$greater$default$5() {
      return AdamsMoultonIntegrator$.MODULE$.$lessinit$greater$default$5();
   }

   public static DenseVector $lessinit$greater$default$4() {
      return AdamsMoultonIntegrator$.MODULE$.$lessinit$greater$default$4();
   }

   public final org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator create() {
      return new org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator(this.order - 1, this.minStep, this.maxStep, ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
   }

   public AdamsMoultonIntegrator(final int order, final double minStep, final double maxStep, final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
      this.order = order;
      this.minStep = minStep;
      this.maxStep = maxStep;
   }
}
