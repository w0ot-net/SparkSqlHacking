package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3AAD\b\u0001)!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\"\u0011!!\u0003A!A!\u0002\u0013\t\u0003\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u00111\u0002!\u0011!Q\u0001\n\u0019BQ!\f\u0001\u0005\u00029*A!\u000e\u0001\u0001m!)Q\t\u0001C\u000b\r\u001e9qiDA\u0001\u0012\u0003Aea\u0002\b\u0010\u0003\u0003E\t!\u0013\u0005\u0006[)!\t!\u0014\u0005\b\u001d*\t\n\u0011\"\u0001P\u0011\u001dQ&\"%A\u0005\u0002=\u0013\u0001$\u00113b[N\u0014\u0015m\u001d5g_J$\b.\u00138uK\u001e\u0014\u0018\r^8s\u0015\t\u0001\u0012#A\u0005j]R,wM]1uK*\t!#\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u0017/5\tq\"\u0003\u0002\u0019\u001f\t)\u0012\t]1dQ\u0016\fE-Y7t\u0013:$Xm\u001a:bi>\u0014\u0018!B8sI\u0016\u0014\bCA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"aA%oi\u00069Q.\u001b8Ti\u0016\u0004\bCA\u000e#\u0013\t\u0019CD\u0001\u0004E_V\u0014G.Z\u0001\b[\u0006D8\u000b^3q\u0003\u0019\u0011X\r\u001c+pYB\u0019qEK\u0011\u000e\u0003!R!!K\t\u0002\r1Lg.\u00197h\u0013\tY\u0003FA\u0006EK:\u001cXMV3di>\u0014\u0018AB1cgR{G.\u0001\u0004=S:LGO\u0010\u000b\u0007_A\n$g\r\u001b\u0011\u0005Y\u0001\u0001\"B\r\u0007\u0001\u0004Q\u0002\"\u0002\u0011\u0007\u0001\u0004\t\u0003\"\u0002\u0013\u0007\u0001\u0004\t\u0003bB\u0013\u0007!\u0003\u0005\rA\n\u0005\bY\u0019\u0001\n\u00111\u0001'\u0005\u0005!\u0006CA\u001cE\u001b\u0005A$BA\u001d;\u0003!qwN\\:uS\u001a4'BA\u001e=\u0003\ryG-\u001a\u0006\u0003{y\nQ!\\1uQNR!a\u0010!\u0002\u000f\r|W.\\8og*\u0011\u0011IQ\u0001\u0007CB\f7\r[3\u000b\u0003\r\u000b1a\u001c:h\u0013\tq\u0001(\u0001\u0004de\u0016\fG/Z\u000b\u0002m\u0005A\u0012\tZ1ng\n\u000b7\u000f\u001b4peRD\u0017J\u001c;fOJ\fGo\u001c:\u0011\u0005YQ1C\u0001\u0006K!\tY2*\u0003\u0002M9\t1\u0011I\\=SK\u001a$\u0012\u0001S\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0003AS#AJ),\u0003I\u0003\"a\u0015-\u000e\u0003QS!!\u0016,\u0002\u0013Ut7\r[3dW\u0016$'BA,\u001d\u0003)\tgN\\8uCRLwN\\\u0005\u00033R\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0001"
)
public class AdamsBashforthIntegrator extends ApacheAdamsIntegrator {
   private final int order;
   private final double minStep;
   private final double maxStep;

   public static DenseVector $lessinit$greater$default$5() {
      return AdamsBashforthIntegrator$.MODULE$.$lessinit$greater$default$5();
   }

   public static DenseVector $lessinit$greater$default$4() {
      return AdamsBashforthIntegrator$.MODULE$.$lessinit$greater$default$4();
   }

   public final org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator create() {
      return new org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator(this.order, this.minStep, this.maxStep, ApacheAdaptiveStepIntegrator$.MODULE$.defaultAbsTol(), ApacheAdaptiveStepIntegrator$.MODULE$.defaultRelTol());
   }

   public AdamsBashforthIntegrator(final int order, final double minStep, final double maxStep, final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
      this.order = order;
      this.minStep = minStep;
      this.maxStep = maxStep;
   }
}
