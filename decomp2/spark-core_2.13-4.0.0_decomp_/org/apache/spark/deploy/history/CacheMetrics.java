package org.apache.spark.deploy.history;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.source.Source;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua!B\f\u0019\u0001a\u0011\u0003\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u000by\u0002A\u0011A \t\u000f\r\u0003!\u0019!C\u0001\t\"1a\n\u0001Q\u0001\n\u0015Cqa\u0014\u0001C\u0002\u0013\u0005A\t\u0003\u0004Q\u0001\u0001\u0006I!\u0012\u0005\b#\u0002\u0011\r\u0011\"\u0001E\u0011\u0019\u0011\u0006\u0001)A\u0005\u000b\"91\u000b\u0001b\u0001\n\u0003!\u0005B\u0002+\u0001A\u0003%Q\tC\u0004V\u0001\t\u0007I\u0011\u0001,\t\ri\u0003\u0001\u0015!\u0003X\u0011\u001dY\u0006A1A\u0005\nqCaa\u001c\u0001!\u0002\u0013i\u0006b\u00029\u0001\u0005\u0004%I!\u001d\u0005\u0007{\u0002\u0001\u000b\u0011\u0002:\t\u000fy\u0004!\u0019!C!\u007f\"9\u0011\u0011\u0001\u0001!\u0002\u0013A\u0007\"CA\u0002\u0001\t\u0007I\u0011IA\u0003\u0011!\ti\u0001\u0001Q\u0001\n\u0005\u001d\u0001bBA\b\u0001\u0011%\u0011\u0011\u0003\u0005\b\u00033\u0001A\u0011IA\u000e\u00051\u0019\u0015m\u00195f\u001b\u0016$(/[2t\u0015\tI\"$A\u0004iSN$xN]=\u000b\u0005ma\u0012A\u00023fa2|\u0017P\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h'\r\u00011%\u000b\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)zS\"A\u0016\u000b\u00051j\u0013AB:pkJ\u001cWM\u0003\u0002/9\u00059Q.\u001a;sS\u000e\u001c\u0018B\u0001\u0019,\u0005\u0019\u0019v.\u001e:dK\u00061\u0001O]3gSb\u001c\u0001\u0001\u0005\u00025w9\u0011Q'\u000f\t\u0003m\u0015j\u0011a\u000e\u0006\u0003qI\na\u0001\u0010:p_Rt\u0014B\u0001\u001e&\u0003\u0019\u0001&/\u001a3fM&\u0011A(\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005i*\u0013A\u0002\u001fj]&$h\b\u0006\u0002A\u0005B\u0011\u0011\tA\u0007\u00021!)\u0011G\u0001a\u0001g\u0005YAn\\8lkB\u001cu.\u001e8u+\u0005)\u0005C\u0001$M\u001b\u00059%B\u0001\u0018I\u0015\tI%*\u0001\u0005d_\u0012\f\u0007.\u00197f\u0015\u0005Y\u0015aA2p[&\u0011Qj\u0012\u0002\b\u0007>,h\u000e^3s\u00031awn\\6va\u000e{WO\u001c;!\u0003Iawn\\6va\u001a\u000b\u0017\u000e\\;sK\u000e{WO\u001c;\u0002'1|wn[;q\r\u0006LG.\u001e:f\u0007>,h\u000e\u001e\u0011\u0002\u001b\u00154\u0018n\u0019;j_:\u001cu.\u001e8u\u00039)g/[2uS>t7i\\;oi\u0002\n\u0011\u0002\\8bI\u000e{WO\u001c;\u0002\u00151|\u0017\rZ\"pk:$\b%A\u0005m_\u0006$G+[7feV\tq\u000b\u0005\u0002G1&\u0011\u0011l\u0012\u0002\u0006)&lWM]\u0001\u000bY>\fG\rV5nKJ\u0004\u0013\u0001C2pk:$XM]:\u0016\u0003u\u00032AX2f\u001b\u0005y&B\u00011b\u0003%IW.\\;uC\ndWM\u0003\u0002cK\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0011|&aA*fcB!AE\u001a5F\u0013\t9WE\u0001\u0004UkBdWM\r\t\u0003S:l\u0011A\u001b\u0006\u0003W2\fA\u0001\\1oO*\tQ.\u0001\u0003kCZ\f\u0017B\u0001\u001fk\u0003%\u0019w.\u001e8uKJ\u001c\b%\u0001\u0006bY2lU\r\u001e:jGN,\u0012A\u001d\t\u0004=\u000e\u001c\b\u0003\u0002\u0013gQR\u00142!^<{\r\u00111\b\u0001\u0001;\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005\u0019C\u0018BA=H\u0005\u0019iU\r\u001e:jGB\u0011ai_\u0005\u0003y\u001e\u0013\u0001bQ8v]RLgnZ\u0001\fC2dW*\u001a;sS\u000e\u001c\b%\u0001\u0006t_V\u00148-\u001a(b[\u0016,\u0012\u0001[\u0001\fg>,(oY3OC6,\u0007%\u0001\bnKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0016\u0005\u0005\u001d\u0001c\u0001$\u0002\n%\u0019\u00111B$\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006yQ.\u001a;sS\u000e\u0014VmZ5tiJL\b%\u0001\u0003j]&$HCAA\n!\r!\u0013QC\u0005\u0004\u0003/)#\u0001B+oSR\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002g\u0001"
)
public class CacheMetrics implements Source {
   private final String prefix;
   private final Counter lookupCount;
   private final Counter lookupFailureCount;
   private final Counter evictionCount;
   private final Counter loadCount;
   private final Timer loadTimer;
   private final Seq counters;
   private final Seq allMetrics;
   private final String sourceName;
   private final MetricRegistry metricRegistry;

   public Counter lookupCount() {
      return this.lookupCount;
   }

   public Counter lookupFailureCount() {
      return this.lookupFailureCount;
   }

   public Counter evictionCount() {
      return this.evictionCount;
   }

   public Counter loadCount() {
      return this.loadCount;
   }

   public Timer loadTimer() {
      return this.loadTimer;
   }

   private Seq counters() {
      return this.counters;
   }

   private Seq allMetrics() {
      return this.allMetrics;
   }

   public String sourceName() {
      return this.sourceName;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   private void init() {
      this.allMetrics().foreach((x0$1) -> {
         if (x0$1 != null) {
            String name = (String)x0$1._1();
            Metric metric = (Metric)x0$1._2();
            return this.metricRegistry().register(MetricRegistry.name(this.prefix, new String[]{name}), metric);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      this.counters().foreach((x0$1) -> {
         if (x0$1 != null) {
            String name = (String)x0$1._1();
            Counter counter = (Counter)x0$1._2();
            return sb.append(name).append(" = ").append(counter.getCount()).append('\n');
         } else {
            throw new MatchError(x0$1);
         }
      });
      return sb.toString();
   }

   public CacheMetrics(final String prefix) {
      this.prefix = prefix;
      this.lookupCount = new Counter();
      this.lookupFailureCount = new Counter();
      this.evictionCount = new Counter();
      this.loadCount = new Counter();
      this.loadTimer = new Timer();
      this.counters = new .colon.colon(new Tuple2("lookup.count", this.lookupCount()), new .colon.colon(new Tuple2("lookup.failure.count", this.lookupFailureCount()), new .colon.colon(new Tuple2("eviction.count", this.evictionCount()), new .colon.colon(new Tuple2("load.count", this.loadCount()), scala.collection.immutable.Nil..MODULE$))));
      this.allMetrics = (Seq)this.counters().$plus$plus(new .colon.colon(new Tuple2("load.timer", this.loadTimer()), scala.collection.immutable.Nil..MODULE$));
      this.sourceName = "ApplicationCache";
      this.metricRegistry = new MetricRegistry();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
