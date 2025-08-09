package org.apache.spark.metrics.sink;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.ui.JettyUtils;
import org.apache.spark.ui.JettyUtils$;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.MatchError;
import scala.collection.mutable.StringBuilder;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!B\t\u0013\u0001Ya\u0002\u0002C\u0014\u0001\u0005\u000b\u0007I\u0011A\u0015\t\u0011I\u0002!\u0011!Q\u0001\n)B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0001\u000e\u0005\t}\u0001\u0011\t\u0011)A\u0005k!)q\b\u0001C\u0001\u0001\"9A\t\u0001b\u0001\n\u0003)\u0005B\u0002'\u0001A\u0003%a\tC\u0004N\u0001\t\u0007I\u0011A#\t\r9\u0003\u0001\u0015!\u0003G\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u0015!\u0007\u0001\"\u0001f\u0011\u0015!\u0007\u0001\"\u0001|\u0011\u001d\tY\u0001\u0001C\u0005\u0003\u001bAq!a\u0005\u0001\t\u0003\n)\u0002C\u0004\u0002\u001e\u0001!\t%!\u0006\t\u000f\u0005}\u0001\u0001\"\u0011\u0002\u0016\t\t\u0002K]8nKRDW-^:TKJ4H.\u001a;\u000b\u0005M!\u0012\u0001B:j].T!!\u0006\f\u0002\u000f5,GO]5dg*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xmE\u0002\u0001;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007C\u0001\u0013&\u001b\u0005\u0011\u0012B\u0001\u0014\u0013\u0005\u0011\u0019\u0016N\\6\u0002\u0011A\u0014x\u000e]3sif\u001c\u0001!F\u0001+!\tY\u0003'D\u0001-\u0015\tic&\u0001\u0003vi&d'\"A\u0018\u0002\t)\fg/Y\u0005\u0003c1\u0012!\u0002\u0015:pa\u0016\u0014H/[3t\u0003%\u0001(o\u001c9feRL\b%\u0001\u0005sK\u001eL7\u000f\u001e:z+\u0005)\u0004C\u0001\u001c=\u001b\u00059$BA\u000b9\u0015\tI$(\u0001\u0005d_\u0012\f\u0007.\u00197f\u0015\u0005Y\u0014aA2p[&\u0011Qh\u000e\u0002\u000f\u001b\u0016$(/[2SK\u001eL7\u000f\u001e:z\u0003%\u0011XmZ5tiJL\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u0003\n\u001b\u0005C\u0001\u0013\u0001\u0011\u00159S\u00011\u0001+\u0011\u0015\u0019T\u00011\u00016\u0003A\u0019VI\u0015,M\u000bR{6*R-`!\u0006#\u0006*F\u0001G!\t9%*D\u0001I\u0015\tIe&\u0001\u0003mC:<\u0017BA&I\u0005\u0019\u0019FO]5oO\u0006\t2+\u0012*W\u0019\u0016#vlS#Z?B\u000bE\u000b\u0013\u0011\u0002\u0017M,'O\u001e7fiB\u000bG\u000f[\u0001\rg\u0016\u0014h\u000f\\3u!\u0006$\b\u000eI\u0001\fO\u0016$\b*\u00198eY\u0016\u00148\u000f\u0006\u0002R=B\u0019aD\u0015+\n\u0005M{\"!B!se\u0006L\bCA+]\u001b\u00051&BA,Y\u0003\u001d\u0019XM\u001d<mKRT!!\u0017.\u0002\u000b),G\u000f^=\u000b\u0005mS\u0012aB3dY&\u00048/Z\u0005\u0003;Z\u0013QcU3sm2,GoQ8oi\u0016DH\u000fS1oI2,'\u000fC\u0003`\u0015\u0001\u0007\u0001-\u0001\u0003d_:4\u0007CA1c\u001b\u00051\u0012BA2\u0017\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\nhKRlU\r\u001e:jGN\u001cf.\u00199tQ>$HC\u00014q!\t9gN\u0004\u0002iYB\u0011\u0011nH\u0007\u0002U*\u00111\u000eK\u0001\u0007yI|w\u000e\u001e \n\u00055|\u0012A\u0002)sK\u0012,g-\u0003\u0002L_*\u0011Qn\b\u0005\u0006c.\u0001\rA]\u0001\be\u0016\fX/Z:u!\t\u0019\u00180D\u0001u\u0015\t)h/\u0001\u0003iiR\u0004(BA,x\u0015\u0005A\u0018a\u00026bW\u0006\u0014H/Y\u0005\u0003uR\u0014!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3tiR\ta\r\u000b\u0003\r{\u0006\u001d\u0001c\u0001@\u0002\u00045\tqPC\u0002\u0002\u0002Y\t!\"\u00198o_R\fG/[8o\u0013\r\t)a \u0002\u0006'&t7-Z\u0011\u0003\u0003\u0013\tQ\u0001\u000e\u00181]A\nAB\\8s[\u0006d\u0017N_3LKf$2AZA\b\u0011\u0019\t\t\"\u0004a\u0001M\u0006\u00191.Z=\u0002\u000bM$\u0018M\u001d;\u0015\u0005\u0005]\u0001c\u0001\u0010\u0002\u001a%\u0019\u00111D\u0010\u0003\tUs\u0017\u000e^\u0001\u0005gR|\u0007/\u0001\u0004sKB|'\u000f\u001e\u0015\u0004\u0001\u0005\r\u0002c\u0001@\u0002&%\u0019\u0011qE@\u0003\u0011Us7\u000f^1cY\u0016D3\u0001AA\u0016!\rq\u0018QF\u0005\u0004\u0003_y(\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class PrometheusServlet implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final String SERVLET_KEY_PATH;
   private final String servletPath;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public String SERVLET_KEY_PATH() {
      return this.SERVLET_KEY_PATH;
   }

   public String servletPath() {
      return this.servletPath;
   }

   public ServletContextHandler[] getHandlers(final SparkConf conf) {
      return (ServletContextHandler[])(new ServletContextHandler[]{JettyUtils$.MODULE$.createServletHandler(this.servletPath(), new JettyUtils.ServletParams((request) -> this.getMetricsSnapshot(request), "text/plain", JettyUtils.ServletParams$.MODULE$.$lessinit$greater$default$3()), conf, JettyUtils$.MODULE$.createServletHandler$default$4())});
   }

   public String getMetricsSnapshot(final HttpServletRequest request) {
      return this.getMetricsSnapshot();
   }

   public String getMetricsSnapshot() {
      String gaugesLabel = "{type=\"gauges\"}";
      String countersLabel = "{type=\"counters\"}";
      String histogramslabels = "{type=\"histograms\"}";
      String timersLabels = "{type=\"timers\"}";
      StringBuilder sb = new StringBuilder();
      .MODULE$.MapHasAsScala(this.registry().getGauges()).asScala().foreach((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            Gauge v = (Gauge)x0$1._2();
            if (!(v.getValue() instanceof String)) {
               sb.append(this.normalizeKey(k) + "Number" + gaugesLabel + " " + v.getValue() + "\n");
               return sb.append(this.normalizeKey(k) + "Value" + gaugesLabel + " " + v.getValue() + "\n");
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x0$1);
         }
      });
      .MODULE$.MapHasAsScala(this.registry().getCounters()).asScala().foreach((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            Counter v = (Counter)x0$2._2();
            return sb.append(this.normalizeKey(k) + "Count" + countersLabel + " " + v.getCount() + "\n");
         } else {
            throw new MatchError(x0$2);
         }
      });
      .MODULE$.MapHasAsScala(this.registry().getHistograms()).asScala().foreach((x0$3) -> {
         if (x0$3 != null) {
            String k = (String)x0$3._1();
            Histogram h = (Histogram)x0$3._2();
            Snapshot snapshot = h.getSnapshot();
            String prefix = this.normalizeKey(k);
            sb.append(prefix + "Count" + histogramslabels + " " + h.getCount() + "\n");
            sb.append(prefix + "Max" + histogramslabels + " " + snapshot.getMax() + "\n");
            sb.append(prefix + "Mean" + histogramslabels + " " + snapshot.getMean() + "\n");
            sb.append(prefix + "Min" + histogramslabels + " " + snapshot.getMin() + "\n");
            sb.append(prefix + "50thPercentile" + histogramslabels + " " + snapshot.getMedian() + "\n");
            sb.append(prefix + "75thPercentile" + histogramslabels + " " + snapshot.get75thPercentile() + "\n");
            sb.append(prefix + "95thPercentile" + histogramslabels + " " + snapshot.get95thPercentile() + "\n");
            sb.append(prefix + "98thPercentile" + histogramslabels + " " + snapshot.get98thPercentile() + "\n");
            sb.append(prefix + "99thPercentile" + histogramslabels + " " + snapshot.get99thPercentile() + "\n");
            sb.append(prefix + "999thPercentile" + histogramslabels + " " + snapshot.get999thPercentile() + "\n");
            return sb.append(prefix + "StdDev" + histogramslabels + " " + snapshot.getStdDev() + "\n");
         } else {
            throw new MatchError(x0$3);
         }
      });
      .MODULE$.IteratorHasAsScala(this.registry().getMeters().entrySet().iterator()).asScala().foreach((kv) -> {
         String prefix = this.normalizeKey((String)kv.getKey());
         Meter meter = (Meter)kv.getValue();
         sb.append(prefix + "Count" + countersLabel + " " + meter.getCount() + "\n");
         sb.append(prefix + "MeanRate" + countersLabel + " " + meter.getMeanRate() + "\n");
         sb.append(prefix + "OneMinuteRate" + countersLabel + " " + meter.getOneMinuteRate() + "\n");
         sb.append(prefix + "FiveMinuteRate" + countersLabel + " " + meter.getFiveMinuteRate() + "\n");
         return sb.append(prefix + "FifteenMinuteRate" + countersLabel + " " + meter.getFifteenMinuteRate() + "\n");
      });
      .MODULE$.IteratorHasAsScala(this.registry().getTimers().entrySet().iterator()).asScala().foreach((kv) -> {
         String prefix = this.normalizeKey((String)kv.getKey());
         Timer timer = (Timer)kv.getValue();
         Snapshot snapshot = timer.getSnapshot();
         sb.append(prefix + "Count" + timersLabels + " " + timer.getCount() + "\n");
         sb.append(prefix + "Max" + timersLabels + " " + snapshot.getMax() + "\n");
         sb.append(prefix + "Mean" + timersLabels + " " + snapshot.getMean() + "\n");
         sb.append(prefix + "Min" + timersLabels + " " + snapshot.getMin() + "\n");
         sb.append(prefix + "50thPercentile" + timersLabels + " " + snapshot.getMedian() + "\n");
         sb.append(prefix + "75thPercentile" + timersLabels + " " + snapshot.get75thPercentile() + "\n");
         sb.append(prefix + "95thPercentile" + timersLabels + " " + snapshot.get95thPercentile() + "\n");
         sb.append(prefix + "98thPercentile" + timersLabels + " " + snapshot.get98thPercentile() + "\n");
         sb.append(prefix + "99thPercentile" + timersLabels + " " + snapshot.get99thPercentile() + "\n");
         sb.append(prefix + "999thPercentile" + timersLabels + " " + snapshot.get999thPercentile() + "\n");
         sb.append(prefix + "StdDev" + timersLabels + " " + snapshot.getStdDev() + "\n");
         sb.append(prefix + "FifteenMinuteRate" + timersLabels + " " + timer.getFifteenMinuteRate() + "\n");
         sb.append(prefix + "FiveMinuteRate" + timersLabels + " " + timer.getFiveMinuteRate() + "\n");
         sb.append(prefix + "OneMinuteRate" + timersLabels + " " + timer.getOneMinuteRate() + "\n");
         return sb.append(prefix + "MeanRate" + timersLabels + " " + timer.getMeanRate() + "\n");
      });
      return sb.toString();
   }

   private String normalizeKey(final String key) {
      return "metrics_" + key.replaceAll("[^a-zA-Z0-9]", "_") + "_";
   }

   public void start() {
   }

   public void stop() {
   }

   public void report() {
   }

   public PrometheusServlet(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.SERVLET_KEY_PATH = "path";
      this.servletPath = property.getProperty(this.SERVLET_KEY_PATH());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
