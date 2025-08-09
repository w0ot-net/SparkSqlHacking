package cats.kernel;

import cats.kernel.instances.seq.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\r\u000bFLen\u001d;b]\u000e,7\u000f\r\u0006\u0003\u000b\u0019\taa[3s]\u0016d'\"A\u0004\u0002\t\r\fGo]\n\u0003\u0001%\u0001\"AC\u0007\u000e\u0003-Q\u0011\u0001D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001d-\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003I\u0001\"AC\n\n\u0005QY!\u0001B+oSR\f!cY1ug.+'O\\3m\u000bF4uN]*fcV\u0011qC\n\u000b\u00031=\u00022!\u0007\u000e\u001d\u001b\u0005!\u0011BA\u000e\u0005\u0005\t)\u0015\u000fE\u0002\u001eE\u0011j\u0011A\b\u0006\u0003?\u0001\n\u0011\"[7nkR\f'\r\\3\u000b\u0005\u0005Z\u0011AC2pY2,7\r^5p]&\u00111E\b\u0002\u0004'\u0016\f\bCA\u0013'\u0019\u0001!Qa\n\u0002C\u0002!\u0012\u0011!Q\t\u0003S1\u0002\"A\u0003\u0016\n\u0005-Z!a\u0002(pi\"Lgn\u001a\t\u0003\u00155J!AL\u0006\u0003\u0007\u0005s\u0017\u0010C\u00041\u0005\u0005\u0005\t9A\u0019\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$C\u0007\r\t\u00043i!\u0003"
)
public interface EqInstances0 {
   // $FF: synthetic method
   static Eq catsKernelEqForSeq$(final EqInstances0 $this, final Eq evidence$40) {
      return $this.catsKernelEqForSeq(evidence$40);
   }

   default Eq catsKernelEqForSeq(final Eq evidence$40) {
      return package$.MODULE$.catsKernelStdEqForSeq(evidence$40);
   }

   static void $init$(final EqInstances0 $this) {
   }
}
