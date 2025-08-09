package org.apache.spark.status;

import com.codahale.metrics.Gauge;
import java.util.concurrent.atomic.AtomicLong;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2Q!\u0002\u0004\u0001\u00119A\u0001b\n\u0001\u0003\u0006\u0004%\t!\u000b\u0005\ti\u0001\u0011\t\u0011)A\u0005U!)Q\u0007\u0001C\u0001m!)!\b\u0001C!w\tY!j\u001c2EkJ\fG/[8o\u0015\t9\u0001\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sON\u0019\u0001aD\f\u0011\u0005A)R\"A\t\u000b\u0005I\u0019\u0012\u0001\u00027b]\u001eT\u0011\u0001F\u0001\u0005U\u00064\u0018-\u0003\u0002\u0017#\t1qJ\u00196fGR\u00042\u0001G\u0010\"\u001b\u0005I\"B\u0001\u000e\u001c\u0003\u001diW\r\u001e:jGNT!\u0001H\u000f\u0002\u0011\r|G-\u00195bY\u0016T\u0011AH\u0001\u0004G>l\u0017B\u0001\u0011\u001a\u0005\u00159\u0015-^4f!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0011auN\\4\u0002\u000bY\fG.^3\u0004\u0001U\t!\u0006\u0005\u0002,e5\tAF\u0003\u0002.]\u00051\u0011\r^8nS\u000eT!a\f\u0019\u0002\u0015\r|gnY;se\u0016tGO\u0003\u00022'\u0005!Q\u000f^5m\u0013\t\u0019DF\u0001\u0006Bi>l\u0017n\u0019'p]\u001e\faA^1mk\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u00028sA\u0011\u0001\bA\u0007\u0002\r!)qe\u0001a\u0001U\u0005Aq-\u001a;WC2,X\rF\u0001\"\u0001"
)
public class JobDuration implements Gauge {
   private final AtomicLong value;

   public AtomicLong value() {
      return this.value;
   }

   public long getValue() {
      return this.value().get();
   }

   public JobDuration(final AtomicLong value) {
      this.value = value;
   }
}
