package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.status.AppStatusUtils$;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.math.Ordering.DeprecatedDoubleOrdering.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@JsonSerialize(
   using = ExecutorPeakMetricsDistributionsJsonSerializer.class
)
@ScalaSignature(
   bytes = "\u0006\u0005y3Aa\u0002\u0005\u0001+!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003\u001f\u0011!q\u0003A!b\u0001\n\u0003y\u0003\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\t\ra\u0002A\u0011\u0001\b:\u0011\u0015q\u0004\u0001\"\u0001@\u0005\u0001*\u00050Z2vi>\u0014\b+Z1l\u001b\u0016$(/[2t\t&\u001cHO]5ckRLwN\\:\u000b\u0005%Q\u0011A\u0001<2\u0015\tYA\"A\u0002ba&T!!\u0004\b\u0002\rM$\u0018\r^;t\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001A\n\u0003\u0001Y\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0017!C9vC:$\u0018\u000e\\3t+\u0005q\u0002cA\u0010(U9\u0011\u0001%\n\b\u0003C\u0011j\u0011A\t\u0006\u0003GQ\ta\u0001\u0010:p_Rt\u0014\"A\r\n\u0005\u0019B\u0012a\u00029bG.\fw-Z\u0005\u0003Q%\u0012!\"\u00138eKb,GmU3r\u0015\t1\u0003\u0004\u0005\u0002\u0018W%\u0011A\u0006\u0007\u0002\u0007\t>,(\r\\3\u0002\u0015E,\u0018M\u001c;jY\u0016\u001c\b%A\bfq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t+\u0005\u0001\u0004cA\u0010(cA\u0011!'N\u0007\u0002g)\u0011AGD\u0001\tKb,7-\u001e;pe&\u0011ag\r\u0002\u0010\u000bb,7-\u001e;pe6+GO]5dg\u0006\u0001R\r_3dkR|'/T3ue&\u001c7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007ibT\b\u0005\u0002<\u00015\t\u0001\u0002C\u0003\u001d\u000b\u0001\u0007a\u0004C\u0003/\u000b\u0001\u0007\u0001'A\u000bhKRlU\r\u001e:jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u0015\u0005y\u0001\u0005\"B!\u0007\u0001\u0004\u0011\u0015AC7fiJL7MT1nKB\u00111i\u0012\b\u0003\t\u0016\u0003\"!\t\r\n\u0005\u0019C\u0012A\u0002)sK\u0012,g-\u0003\u0002I\u0013\n11\u000b\u001e:j]\u001eT!A\u0012\r)\t\u0001Y\u0015L\u0017\t\u0003\u0019^k\u0011!\u0014\u0006\u0003\u001d>\u000b!\"\u00198o_R\fG/[8o\u0015\t\u0001\u0016+\u0001\u0005eCR\f'-\u001b8e\u0015\t\u00116+A\u0004kC\u000e\\7o\u001c8\u000b\u0005Q+\u0016!\u00034bgR,'\u000f_7m\u0015\u00051\u0016aA2p[&\u0011\u0001,\u0014\u0002\u000e\u0015N|gnU3sS\u0006d\u0017N_3\u0002\u000bU\u001c\u0018N\\4$\u0003m\u0003\"a\u000f/\n\u0005uC!AL#yK\u000e,Ho\u001c:QK\u0006\\W*\u001a;sS\u000e\u001cH)[:ue&\u0014W\u000f^5p]NT5o\u001c8TKJL\u0017\r\\5{KJ\u0004"
)
public class ExecutorPeakMetricsDistributions {
   private final IndexedSeq quantiles;
   private final IndexedSeq executorMetrics;

   public IndexedSeq quantiles() {
      return this.quantiles;
   }

   public IndexedSeq executorMetrics() {
      return this.executorMetrics;
   }

   public IndexedSeq getMetricDistribution(final String metricName) {
      IndexedSeq sorted = (IndexedSeq)((SeqOps)this.executorMetrics().map((x$1) -> BoxesRunTime.boxToDouble($anonfun$getMetricDistribution$1(metricName, x$1)))).sorted(.MODULE$);
      return AppStatusUtils$.MODULE$.getQuantilesValue(sorted, (double[])this.quantiles().toArray(scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   public static final double $anonfun$getMetricDistribution$1(final String metricName$1, final ExecutorMetrics x$1) {
      return (double)x$1.getMetricValue(metricName$1);
   }

   public ExecutorPeakMetricsDistributions(final IndexedSeq quantiles, final IndexedSeq executorMetrics) {
      this.quantiles = quantiles;
      this.executorMetrics = executorMetrics;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
