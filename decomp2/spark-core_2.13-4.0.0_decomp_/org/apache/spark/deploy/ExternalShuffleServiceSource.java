package org.apache.spark.deploy;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.spark.metrics.source.Source;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3Aa\u0002\u0005\u0005#!)\u0001\u0005\u0001C\u0001C!9A\u0005\u0001b\u0001\n\u0003*\u0003BB\u0018\u0001A\u0003%a\u0005C\u00041\u0001\t\u0007I\u0011I\u0019\t\ri\u0002\u0001\u0015!\u00033\u0011\u0015Y\u0004\u0001\"\u0001=\u0005q)\u0005\u0010^3s]\u0006d7\u000b[;gM2,7+\u001a:wS\u000e,7k\\;sG\u0016T!!\u0003\u0006\u0002\r\u0011,\u0007\u000f\\8z\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7\u0001A\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a=5\t!D\u0003\u0002\u001c9\u000511o\\;sG\u0016T!!\b\u0006\u0002\u000f5,GO]5dg&\u0011qD\u0007\u0002\u0007'>,(oY3\u0002\rqJg.\u001b;?)\u0005\u0011\u0003CA\u0012\u0001\u001b\u0005A\u0011AD7fiJL7MU3hSN$(/_\u000b\u0002MA\u0011q%L\u0007\u0002Q)\u0011Q$\u000b\u0006\u0003U-\n\u0001bY8eC\"\fG.\u001a\u0006\u0002Y\u0005\u00191m\\7\n\u00059B#AD'fiJL7MU3hSN$(/_\u0001\u0010[\u0016$(/[2SK\u001eL7\u000f\u001e:zA\u0005Q1o\\;sG\u0016t\u0015-\\3\u0016\u0003I\u0002\"a\r\u001d\u000e\u0003QR!!\u000e\u001c\u0002\t1\fgn\u001a\u0006\u0002o\u0005!!.\u0019<b\u0013\tIDG\u0001\u0004TiJLgnZ\u0001\fg>,(oY3OC6,\u0007%A\tsK\u001eL7\u000f^3s\u001b\u0016$(/[2TKR$\"!\u0010!\u0011\u0005Mq\u0014BA \u0015\u0005\u0011)f.\u001b;\t\u000b\u00053\u0001\u0019\u0001\"\u0002\u00135,GO]5d'\u0016$\bCA\u0014D\u0013\t!\u0005FA\u0005NKR\u0014\u0018nY*fi\"\u0012\u0001A\u0012\t\u0003\u000f:k\u0011\u0001\u0013\u0006\u0003\u0013*\u000b!bY8oGV\u0014(/\u001a8u\u0015\tYE*\u0001\u0006b]:|G/\u0019;j_:T\u0011!T\u0001\u0006U\u00064\u0018\r_\u0005\u0003\u001f\"\u0013!\u0002\u00165sK\u0006$7+\u00194f\u0001"
)
@ThreadSafe
public class ExternalShuffleServiceSource implements Source {
   private final MetricRegistry metricRegistry = new MetricRegistry();
   private final String sourceName = "shuffleService";

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public void registerMetricSet(final MetricSet metricSet) {
      this.metricRegistry().registerAll(metricSet);
   }
}
