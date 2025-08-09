package org.apache.spark.mllib.stat.test;

import org.apache.spark.internal.Logging;
import org.apache.spark.streaming.dstream.DStream;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m<a\u0001D\u0007\t\u0002=IbAB\u000e\u000e\u0011\u0003yA\u0004C\u0003-\u0003\u0011\u0005a\u0006C\u00040\u0003\t\u0007IQ\t\u0019\t\rQ\n\u0001\u0015!\u00042\u0011\u001d)\u0014A1A\u0005FYBaAO\u0001!\u0002\u001b9\u0004bB\u001e\u0002\u0005\u0004%i\u0001\u0010\u0005\u0007%\u0006\u0001\u000bQB\u001f\t\u000bM\u000bA\u0011\t+\t\u000b9\tA\u0011\u00024\t\u000fE\f\u0011\u0011!C\u0005e\u0006Qq+\u001a7dQR#Vm\u001d;\u000b\u00059y\u0011\u0001\u0002;fgRT!\u0001E\t\u0002\tM$\u0018\r\u001e\u0006\u0003%M\tQ!\u001c7mS\nT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'o\u001a\t\u00035\u0005i\u0011!\u0004\u0002\u000b/\u0016d7\r\u001b+UKN$8\u0003B\u0001\u001eG\u0019\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007C\u0001\u000e%\u0013\t)SBA\nTiJ,\u0017-\\5oOR+7\u000f^'fi\"|G\r\u0005\u0002(U5\t\u0001F\u0003\u0002*'\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002,Q\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003e\t!\"\\3uQ>$g*Y7f+\u0005\tt\"\u0001\u001a\"\u0003M\nqcV3mG\"<3\u000f\t\u001a.g\u0006l\u0007\u000f\\3!i6\"Xm\u001d;\u0002\u00175,G\u000f[8e\u001d\u0006lW\rI\u0001\u000f]VdG\u000eS=q_RDWm]5t+\u00059t\"\u0001\u001d\"\u0003e\n!DQ8uQ\u0002:'o\\;qg\u0002B\u0017M^3!g\u0006lW\rI7fC:\fqB\\;mY\"K\bo\u001c;iKNL7\u000fI\u0001\biR+7\u000f^3s+\u0005i\u0004c\u0001 F\u000f6\tqH\u0003\u0002A\u0003\u0006)1\r[5mY*\u0011!iQ\u0001\bi^LG\u000f^3s\u0015\u0005!\u0015aA2p[&\u0011ai\u0010\u0002\u000b\u001b\u0016\fG\u000fT8dW\u0016\u0014\bC\u0001%Q\u001b\u0005I%B\u0001&L\u0003%IgNZ3sK:\u001cWM\u0003\u0002\u0011\u0019*\u0011QJT\u0001\u0006[\u0006$\bn\r\u0006\u0003\u001fV\tqaY8n[>t7/\u0003\u0002R\u0013\n)A\u000bV3ti\u0006AA\u000fV3ti\u0016\u0014\b%\u0001\u0004e_R+7\u000f\u001e\u000b\u0003+\u0002\u00042AV.^\u001b\u00059&B\u0001-Z\u0003\u001d!7\u000f\u001e:fC6T!AW\n\u0002\u0013M$(/Z1nS:<\u0017B\u0001/X\u0005\u001d!5\u000b\u001e:fC6\u0004\"A\u00070\n\u0005}k!aE*ue\u0016\fW.\u001b8h)\u0016\u001cHOU3tk2$\b\"B1\n\u0001\u0004\u0011\u0017\u0001\u00023bi\u0006\u0004\"a\u00193\u000e\u0003\u0005I!!\u001a\u0013\u0003#M+X.\\1ssB\u000b\u0017N]*ue\u0016\fW\u000eF\u0002^O>DQ\u0001\u001b\u0006A\u0002%\faa\u001d;biN\f\u0005C\u00016n\u001b\u0005Y'B\u00017\u0014\u0003\u0011)H/\u001b7\n\u00059\\'aC*uCR\u001cu.\u001e8uKJDQ\u0001\u001d\u0006A\u0002%\faa\u001d;biN\u0014\u0015\u0001D<sSR,'+\u001a9mC\u000e,G#A:\u0011\u0005QLX\"A;\u000b\u0005Y<\u0018\u0001\u00027b]\u001eT\u0011\u0001_\u0001\u0005U\u00064\u0018-\u0003\u0002{k\n1qJ\u00196fGR\u0004"
)
public final class WelchTTest {
   public static DStream doTest(final DStream data) {
      return WelchTTest$.MODULE$.doTest(data);
   }

   public static String nullHypothesis() {
      return WelchTTest$.MODULE$.nullHypothesis();
   }

   public static String methodName() {
      return WelchTTest$.MODULE$.methodName();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return WelchTTest$.MODULE$.LogStringContext(sc);
   }
}
