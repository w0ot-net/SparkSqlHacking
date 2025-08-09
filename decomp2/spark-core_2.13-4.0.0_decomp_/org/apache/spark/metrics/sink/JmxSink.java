package org.apache.spark.metrics.sink;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import java.util.Properties;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593Qa\u0003\u0007\u0001!YA\u0001\"\t\u0001\u0003\u0006\u0004%\ta\t\u0005\tY\u0001\u0011\t\u0011)A\u0005I!AQ\u0006\u0001BC\u0002\u0013\u0005a\u0006\u0003\u00059\u0001\t\u0005\t\u0015!\u00030\u0011\u0015I\u0004\u0001\"\u0001;\u0011\u001dq\u0004A1A\u0005\u0002}BaA\u0012\u0001!\u0002\u0013\u0001\u0005\"B$\u0001\t\u0003B\u0005\"\u0002'\u0001\t\u0003B\u0005\"B'\u0001\t\u0003B%a\u0002&nqNKgn\u001b\u0006\u0003\u001b9\tAa]5oW*\u0011q\u0002E\u0001\b[\u0016$(/[2t\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0010\u000e\u00031I!\u0001\t\u0007\u0003\tMKgn[\u0001\taJ|\u0007/\u001a:us\u000e\u0001Q#\u0001\u0013\u0011\u0005\u0015RS\"\u0001\u0014\u000b\u0005\u001dB\u0013\u0001B;uS2T\u0011!K\u0001\u0005U\u00064\u0018-\u0003\u0002,M\tQ\u0001K]8qKJ$\u0018.Z:\u0002\u0013A\u0014x\u000e]3sif\u0004\u0013\u0001\u0003:fO&\u001cHO]=\u0016\u0003=\u0002\"\u0001\r\u001c\u000e\u0003ER!a\u0004\u001a\u000b\u0005M\"\u0014\u0001C2pI\u0006D\u0017\r\\3\u000b\u0003U\n1aY8n\u0013\t9\u0014G\u0001\bNKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0002\u0013I,w-[:uef\u0004\u0013A\u0002\u001fj]&$h\bF\u0002<yu\u0002\"A\b\u0001\t\u000b\u0005*\u0001\u0019\u0001\u0013\t\u000b5*\u0001\u0019A\u0018\u0002\u0011I,\u0007o\u001c:uKJ,\u0012\u0001\u0011\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007F\n1A[7y\u0013\t)%IA\u0006K[b\u0014V\r]8si\u0016\u0014\u0018!\u0003:fa>\u0014H/\u001a:!\u0003\u0015\u0019H/\u0019:u)\u0005I\u0005C\u0001\rK\u0013\tY\u0015D\u0001\u0003V]&$\u0018\u0001B:u_B\faA]3q_J$\b"
)
public class JmxSink implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final JmxReporter reporter;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public JmxReporter reporter() {
      return this.reporter;
   }

   public void start() {
      this.reporter().start();
   }

   public void stop() {
      this.reporter().stop();
   }

   public void report() {
   }

   public JmxSink(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.reporter = JmxReporter.forRegistry(registry).build();
   }
}
