package breeze.integrate;

import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3QAC\u0006\u0002\u0002AA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\tE\u0001\u0011\t\u0011)A\u0005-!)1\u0005\u0001C\u0001I\u0011)\u0001\u0006\u0001B\u0001S\u001d9QhCA\u0001\u0012\u0003qda\u0002\u0006\f\u0003\u0003E\ta\u0010\u0005\u0006G\u0019!\ta\u0011\u0005\b\t\u001a\t\n\u0011\"\u0001F\u0011\u001d\u0001f!%A\u0005\u0002\u0015\u0013Q#\u00119bG\",\u0017\tZ1ng&sG/Z4sCR|'O\u0003\u0002\r\u001b\u0005I\u0011N\u001c;fOJ\fG/\u001a\u0006\u0002\u001d\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0002\u0001#A\u0011!cE\u0007\u0002\u0017%\u0011Ac\u0003\u0002\u001d\u0003B\f7\r[3BI\u0006\u0004H/\u001b<f'R,\u0007/\u00138uK\u001e\u0014\u0018\r^8s\u0003\u0019\u0011X\r\u001c+pYB\u0019qC\u0007\u000f\u000e\u0003aQ!!G\u0007\u0002\r1Lg.\u00197h\u0013\tY\u0002DA\u0006EK:\u001cXMV3di>\u0014\bCA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"A\u0002#pk\ndW-\u0001\u0004bEN$v\u000e\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u00152s\u0005\u0005\u0002\u0013\u0001!9Qc\u0001I\u0001\u0002\u00041\u0002b\u0002\u0012\u0004!\u0003\u0005\rA\u0006\u0002\u0002)F\u0011!&\f\t\u0003;-J!\u0001\f\u0010\u0003\u000f9{G\u000f[5oOB\u0011afO\u0007\u0002_)\u0011\u0001'M\u0001\t]>t7\u000f^5gM*\u0011!gM\u0001\u0004_\u0012,'B\u0001\u001b6\u0003\u0015i\u0017\r\u001e54\u0015\t1t'A\u0004d_6lwN\\:\u000b\u0005aJ\u0014AB1qC\u000eDWMC\u0001;\u0003\ry'oZ\u0005\u0003y=\u0012q\"\u00113b[NLe\u000e^3he\u0006$xN]\u0001\u0016\u0003B\f7\r[3BI\u0006l7/\u00138uK\u001e\u0014\u0018\r^8s!\t\u0011ba\u0005\u0002\u0007\u0001B\u0011Q$Q\u0005\u0003\u0005z\u0011a!\u00118z%\u00164G#\u0001 \u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\u00051%F\u0001\fHW\u0005A\u0005CA%O\u001b\u0005Q%BA&M\u0003%)hn\u00195fG.,GM\u0003\u0002N=\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005=S%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII\u0002"
)
public abstract class ApacheAdamsIntegrator extends ApacheAdaptiveStepIntegrator {
   public static DenseVector $lessinit$greater$default$2() {
      return ApacheAdamsIntegrator$.MODULE$.$lessinit$greater$default$2();
   }

   public static DenseVector $lessinit$greater$default$1() {
      return ApacheAdamsIntegrator$.MODULE$.$lessinit$greater$default$1();
   }

   public ApacheAdamsIntegrator(final DenseVector relTol, final DenseVector absTol) {
      super(relTol, absTol);
   }
}
