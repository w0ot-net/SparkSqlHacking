package org.apache.spark;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005q2Qa\u0001\u0003\u0002\u0002-AQa\b\u0001\u0005\u0002\u0001BQA\f\u0001\u0007\u0002=\u0012!\u0002R3qK:$WM\\2z\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u00051\u0011\r]1dQ\u0016T\u0011!C\u0001\u0004_J<7\u0001A\u000b\u0003\u0019\u0015\u001a2\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0011A\u0003\b\b\u0003+iq!AF\r\u000e\u0003]Q!\u0001\u0007\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012BA\u000e\u0010\u0003\u001d\u0001\u0018mY6bO\u0016L!!\b\u0010\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005my\u0011A\u0002\u001fj]&$h\bF\u0001\"!\r\u0011\u0003aI\u0007\u0002\tA\u0011A%\n\u0007\u0001\t\u00151\u0003A1\u0001(\u0005\u0005!\u0016C\u0001\u0015,!\tq\u0011&\u0003\u0002+\u001f\t9aj\u001c;iS:<\u0007C\u0001\b-\u0013\tisBA\u0002B]f\f1A\u001d3e+\u0005\u0001\u0004cA\u00194G5\t!G\u0003\u0002/\t%\u0011AG\r\u0002\u0004%\u0012#\u0005F\u0001\u00017!\t9$(D\u00019\u0015\tID!\u0001\u0006b]:|G/\u0019;j_:L!a\u000f\u001d\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public abstract class Dependency implements Serializable {
   public abstract RDD rdd();
}
