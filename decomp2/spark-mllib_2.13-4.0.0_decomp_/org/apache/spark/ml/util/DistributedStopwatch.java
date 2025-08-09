package org.apache.spark.ml.util;

import org.apache.spark.SparkContext;
import org.apache.spark.util.LongAccumulator;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q!\u0003\u0006\u0001\u001dQA\u0001\"\u0007\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\t?\u0001\u0011)\u0019!C!A!Aa\u0006\u0001B\u0001B\u0003%\u0011\u0005C\u00030\u0001\u0011\u0005\u0001\u0007C\u00045\u0001\t\u0007I\u0011B\u001b\t\rm\u0002\u0001\u0015!\u00037\u0011\u0015a\u0004\u0001\"\u0011>\u0011\u0015\u0011\u0005\u0001\"\u0015D\u0005Q!\u0015n\u001d;sS\n,H/\u001a3Ti>\u0004x/\u0019;dQ*\u00111\u0002D\u0001\u0005kRLGN\u0003\u0002\u000e\u001d\u0005\u0011Q\u000e\u001c\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sON\u0011\u0001!\u0006\t\u0003-]i\u0011AC\u0005\u00031)\u0011\u0011b\u0015;pa^\fGo\u00195\u0002\u0005M\u001c7\u0001\u0001\t\u00039ui\u0011AD\u0005\u0003=9\u0011Ab\u00159be.\u001cuN\u001c;fqR\fAA\\1nKV\t\u0011\u0005\u0005\u0002#W9\u00111%\u000b\t\u0003I\u001dj\u0011!\n\u0006\u0003Mi\ta\u0001\u0010:p_Rt$\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):\u0013A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!AK\u0014\u0002\u000b9\fW.\u001a\u0011\u0002\rqJg.\u001b;?)\r\t$g\r\t\u0003-\u0001AQ!\u0007\u0003A\u0002mAQa\b\u0003A\u0002\u0005\n1\"\u001a7baN,G\rV5nKV\ta\u0007\u0005\u00028s5\t\u0001H\u0003\u0002\f\u001d%\u0011!\b\u000f\u0002\u0010\u0019>tw-Q2dk6,H.\u0019;pe\u0006aQ\r\\1qg\u0016$G+[7fA\u00059Q\r\\1qg\u0016$G#\u0001 \u0011\u0005}\u0002U\"A\u0014\n\u0005\u0005;#\u0001\u0002'p]\u001e\f1!\u00193e)\t!u\t\u0005\u0002@\u000b&\u0011ai\n\u0002\u0005+:LG\u000fC\u0003I\u0011\u0001\u0007a(\u0001\u0005ekJ\fG/[8o\u0001"
)
public class DistributedStopwatch extends Stopwatch {
   private final String name;
   private final LongAccumulator elapsedTime;

   public String name() {
      return this.name;
   }

   private LongAccumulator elapsedTime() {
      return this.elapsedTime;
   }

   public long elapsed() {
      return .MODULE$.Long2long(this.elapsedTime().value());
   }

   public void add(final long duration) {
      this.elapsedTime().add(duration);
   }

   public DistributedStopwatch(final SparkContext sc, final String name) {
      this.name = name;
      this.elapsedTime = sc.longAccumulator("DistributedStopwatch(" + name + ")");
   }
}
