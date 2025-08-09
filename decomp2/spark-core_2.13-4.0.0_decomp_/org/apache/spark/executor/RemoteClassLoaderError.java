package org.apache.spark.executor;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2Q\u0001B\u0003\u0001\u000b5A\u0001\"\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\tM\u0001\u0011\t\u0011)A\u0005O!)!\u0006\u0001C\u0001W\t1\"+Z7pi\u0016\u001cE.Y:t\u0019>\fG-\u001a:FeJ|'O\u0003\u0002\u0007\u000f\u0005AQ\r_3dkR|'O\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\t\u0001a\u0002\u0005\u0002\u001059\u0011\u0001c\u0006\b\u0003#Ui\u0011A\u0005\u0006\u0003'Q\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002-\u0005)1oY1mC&\u0011\u0001$G\u0001\ba\u0006\u001c7.Y4f\u0015\u00051\u0012BA\u000e\u001d\u0005\u0015)%O]8s\u0015\tA\u0012$A\u0005dY\u0006\u001c8OT1nKB\u0011qd\t\b\u0003A\u0005\u0002\"!E\r\n\u0005\tJ\u0012A\u0002)sK\u0012,g-\u0003\u0002%K\t11\u000b\u001e:j]\u001eT!AI\r\u0002\u000b\r\fWo]3\u0011\u0005=A\u0013BA\u0015\u001d\u0005%!\u0006N]8xC\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0004Y9z\u0003CA\u0017\u0001\u001b\u0005)\u0001\"B\u000f\u0004\u0001\u0004q\u0002\"\u0002\u0014\u0004\u0001\u00049\u0003"
)
public class RemoteClassLoaderError extends Error {
   public RemoteClassLoaderError(final String className, final Throwable cause) {
      super(className, cause);
   }
}
