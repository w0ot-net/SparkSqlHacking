package org.apache.spark.metrics.sink;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Metered;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.net.NetUtils;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try.;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rc!B\u0011#\u0001\u0019b\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011\u0005\u0003!\u0011!Q\u0001\n\tC\u0001b\u0014\u0001\u0003\u0002\u0003\u0006I\u0001\u0015\u0005\t)\u0002\u0011\t\u0011)A\u0005\u0005\"AQ\u000b\u0001B\u0001B\u0003%a\u000b\u0003\u0005Z\u0001\t\u0005\t\u0015!\u0003[\u0011!!\u0007A!A!\u0002\u0013Q\u0006\"B3\u0001\t\u00031\u0007b\u00029\u0001\u0005\u0004%I!\u001d\u0005\u0007q\u0002\u0001\u000b\u0011\u0002:\t\u000fe\u0004!\u0019!C\u0005u\"9\u0011Q\u0001\u0001!\u0002\u0013Y\bbBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\b\u0003W\u0002A\u0011BA7\u0011\u001d\ti\t\u0001C\u0005\u0003\u001fCq!a'\u0001\t\u0013\ti\nC\u0004\u0002*\u0002!I!a+\t\u000f\u0005u\u0006\u0001\"\u0003\u0002@\"9\u00111\u001a\u0001\u0005\n\u00055\u0007bBAo\u0001\u0011%\u0011q\u001c\u0005\b\u0003W\u0004A\u0011BAw\u0011\u001d\t\u0019\u0010\u0001C\u0005\u0003kDq!a?\u0001\t\u0013\tip\u0002\u0006\u0003\b\t\n\t\u0011#\u0001'\u0005\u00131\u0011\"\t\u0012\u0002\u0002#\u0005aEa\u0003\t\r\u0015LB\u0011\u0001B\n\u0011%\u0011)\"GI\u0001\n\u0003\u00119\u0002C\u0005\u0003.e\t\n\u0011\"\u0001\u00030!I!1G\r\u0012\u0002\u0013\u0005!q\u0003\u0005\n\u0005kI\u0012\u0013!C\u0001\u0005oA\u0011Ba\u000f\u001a#\u0003%\tA!\u0010\t\u0013\t\u0005\u0013$%A\u0005\u0002\tu\"AD*uCR\u001cHMU3q_J$XM\u001d\u0006\u0003G\u0011\nAa]5oW*\u0011QEJ\u0001\b[\u0016$(/[2t\u0015\t9\u0003&A\u0003ta\u0006\u00148N\u0003\u0002*U\u00051\u0011\r]1dQ\u0016T\u0011aK\u0001\u0004_J<7c\u0001\u0001.mA\u0011a\u0006N\u0007\u0002_)\u0011Q\u0005\r\u0006\u0003cI\n\u0001bY8eC\"\fG.\u001a\u0006\u0002g\u0005\u00191m\\7\n\u0005Uz#!E*dQ\u0016$W\u000f\\3e%\u0016\u0004xN\u001d;feB\u0011qGO\u0007\u0002q)\u0011\u0011HJ\u0001\tS:$XM\u001d8bY&\u00111\b\u000f\u0002\b\u0019><w-\u001b8h\u0003!\u0011XmZ5tiJL8\u0001\u0001\t\u0003]}J!\u0001Q\u0018\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006!\u0001n\\:u!\t\u0019EJ\u0004\u0002E\u0015B\u0011Q\tS\u0007\u0002\r*\u0011q)P\u0001\u0007yI|w\u000e\u001e \u000b\u0003%\u000bQa]2bY\u0006L!a\u0013%\u0002\rA\u0013X\rZ3g\u0013\tieJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0017\"\u000bA\u0001]8siB\u0011\u0011KU\u0007\u0002\u0011&\u00111\u000b\u0013\u0002\u0004\u0013:$\u0018A\u00029sK\u001aL\u00070\u0001\u0004gS2$XM\u001d\t\u0003]]K!\u0001W\u0018\u0003\u00195+GO]5d\r&dG/\u001a:\u0002\u0011I\fG/Z+oSR\u0004\"a\u00172\u000e\u0003qS!!\u00180\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002`A\u0006!Q\u000f^5m\u0015\u0005\t\u0017\u0001\u00026bm\u0006L!a\u0019/\u0003\u0011QKW.Z+oSR\fA\u0002Z;sCRLwN\\+oSR\fa\u0001P5oSRtD\u0003C4jU.dWN\\8\u0011\u0005!\u0004Q\"\u0001\u0012\t\u000bqB\u0001\u0019\u0001 \t\u000f\u0005C\u0001\u0013!a\u0001\u0005\"9q\n\u0003I\u0001\u0002\u0004\u0001\u0006b\u0002+\t!\u0003\u0005\rA\u0011\u0005\b+\"\u0001\n\u00111\u0001W\u0011\u001dI\u0006\u0002%AA\u0002iCq\u0001\u001a\u0005\u0011\u0002\u0003\u0007!,A\u0004bI\u0012\u0014Xm]:\u0016\u0003I\u0004\"a\u001d<\u000e\u0003QT!!\u001e1\u0002\u00079,G/\u0003\u0002xi\n\t\u0012J\\3u'>\u001c7.\u001a;BI\u0012\u0014Xm]:\u0002\u0011\u0005$GM]3tg\u0002\n!b\u001e5ji\u0016\u001c\b/Y2f+\u0005Y\bc\u0001?\u0002\u00025\tQP\u0003\u0002\u007f\u007f\u0006AQ.\u0019;dQ&twM\u0003\u0002`\u0011&\u0019\u00111A?\u0003\u000bI+w-\u001a=\u0002\u0017]D\u0017\u000e^3ta\u0006\u001cW\rI\u0001\u0007e\u0016\u0004xN\u001d;\u0015\u0019\u0005-\u0011\u0011CA\u001e\u0003\u000f\n\u0019&a\u0018\u0011\u0007E\u000bi!C\u0002\u0002\u0010!\u0013A!\u00168ji\"9\u00111C\u0007A\u0002\u0005U\u0011AB4bk\u001e,7\u000fE\u0004\u0002\u0018\u0005e!)!\b\u000e\u0003yK1!a\u0007_\u0005%\u0019vN\u001d;fI6\u000b\u0007\u000f\r\u0003\u0002 \u0005%\u0002#\u0002\u0018\u0002\"\u0005\u0015\u0012bAA\u0012_\t)q)Y;hKB!\u0011qEA\u0015\u0019\u0001!A\"a\u000b\u0002\u0012\u0005\u0005\t\u0011!B\u0001\u0003[\u00111a\u0018\u00132#\u0011\ty#!\u000e\u0011\u0007E\u000b\t$C\u0002\u00024!\u0013qAT8uQ&tw\rE\u0002R\u0003oI1!!\u000fI\u0005\r\te.\u001f\u0005\b\u0003{i\u0001\u0019AA \u0003!\u0019w.\u001e8uKJ\u001c\bcBA\f\u00033\u0011\u0015\u0011\t\t\u0004]\u0005\r\u0013bAA#_\t91i\\;oi\u0016\u0014\bbBA%\u001b\u0001\u0007\u00111J\u0001\u000bQ&\u001cHo\\4sC6\u001c\bcBA\f\u00033\u0011\u0015Q\n\t\u0004]\u0005=\u0013bAA)_\tI\u0001*[:u_\u001e\u0014\u0018-\u001c\u0005\b\u0003+j\u0001\u0019AA,\u0003\u0019iW\r^3sgB9\u0011qCA\r\u0005\u0006e\u0003c\u0001\u0018\u0002\\%\u0019\u0011QL\u0018\u0003\u000b5+G/\u001a:\t\u000f\u0005\u0005T\u00021\u0001\u0002d\u00051A/[7feN\u0004r!a\u0006\u0002\u001a\t\u000b)\u0007E\u0002/\u0003OJ1!!\u001b0\u0005\u0015!\u0016.\\3s\u0003-\u0011X\r]8si\u001e\u000bWoZ3\u0015\r\u0005=\u00141PA@)\u0011\tY!!\u001d\t\u000f\u0005Md\u0002q\u0001\u0002v\u000511o\\2lKR\u00042a]A<\u0013\r\tI\b\u001e\u0002\u000f\t\u0006$\u0018m\u001a:b[N{7m[3u\u0011\u0019\tiH\u0004a\u0001\u0005\u0006!a.Y7f\u0011\u001d\t\tI\u0004a\u0001\u0003\u0007\u000bQaZ1vO\u0016\u0004D!!\"\u0002\nB)a&!\t\u0002\bB!\u0011qEAE\t1\tY)a \u0002\u0002\u0003\u0005)\u0011AA\u0017\u0005\ryFEM\u0001\u000ee\u0016\u0004xN\u001d;D_VtG/\u001a:\u0015\r\u0005E\u0015QSAL)\u0011\tY!a%\t\u000f\u0005Mt\u0002q\u0001\u0002v!1\u0011QP\bA\u0002\tCq!!'\u0010\u0001\u0004\t\t%A\u0004d_VtG/\u001a:\u0002\u001fI,\u0007o\u001c:u\u0011&\u001cHo\\4sC6$b!a(\u0002$\u0006\u0015F\u0003BA\u0006\u0003CCq!a\u001d\u0011\u0001\b\t)\b\u0003\u0004\u0002~A\u0001\rA\u0011\u0005\b\u0003O\u0003\u0002\u0019AA'\u0003%A\u0017n\u001d;pOJ\fW.A\u0007sKB|'\u000f^'fi\u0016\u0014X\r\u001a\u000b\u0007\u0003[\u000b\t,a-\u0015\t\u0005-\u0011q\u0016\u0005\b\u0003g\n\u00029AA;\u0011\u0019\ti(\u0005a\u0001\u0005\"9\u0011QW\tA\u0002\u0005]\u0016!B7fi\u0016\u0014\bc\u0001\u0018\u0002:&\u0019\u00111X\u0018\u0003\u000f5+G/\u001a:fI\u0006Y!/\u001a9peR$\u0016.\\3s)\u0019\t\t-!2\u0002HR!\u00111BAb\u0011\u001d\t\u0019H\u0005a\u0002\u0003kBa!! \u0013\u0001\u0004\u0011\u0005bBAe%\u0001\u0007\u0011QM\u0001\u0006i&lWM]\u0001\u0005g\u0016tG\r\u0006\u0005\u0002P\u0006M\u0017Q[Am)\u0011\tY!!5\t\u000f\u0005M4\u0003q\u0001\u0002v!1\u0011QP\nA\u0002\tCa!a6\u0014\u0001\u0004\u0011\u0015!\u0002<bYV,\u0007BBAn'\u0001\u0007!)\u0001\u0006nKR\u0014\u0018n\u0019+za\u0016\f\u0001BZ;mY:\u000bW.\u001a\u000b\u0004\u0005\u0006\u0005\bbBAr)\u0001\u0007\u0011Q]\u0001\u0006]\u0006lWm\u001d\t\u0005#\u0006\u001d()C\u0002\u0002j\"\u0013!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003!\u0019\u0018M\\5uSj,Gc\u0001\"\u0002p\"1\u0011\u0011_\u000bA\u0002\t\u000b\u0011a]\u0001\u0007M>\u0014X.\u0019;\u0015\u0007\t\u000b9\u0010C\u0004\u0002zZ\u0001\r!!\u000e\u0002\u0003Y\f\u0011BZ8s[\u0006$\u0018I\\=\u0015\t\u0005}(Q\u0001\t\u0005#\n\u0005!)C\u0002\u0003\u0004!\u0013aa\u00149uS>t\u0007bBA}/\u0001\u0007\u0011QG\u0001\u000f'R\fGo\u001d3SKB|'\u000f^3s!\tA\u0017dE\u0002\u001a\u0005\u001b\u00012!\u0015B\b\u0013\r\u0011\t\u0002\u0013\u0002\u0007\u0003:L(+\u001a4\u0015\u0005\t%\u0011a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u001a)\u001a!Ia\u0007,\u0005\tu\u0001\u0003\u0002B\u0010\u0005Si!A!\t\u000b\t\t\r\"QE\u0001\nk:\u001c\u0007.Z2lK\u0012T1Aa\nI\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005W\u0011\tCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTC\u0001B\u0019U\r\u0001&1D\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136+\t\u0011IDK\u0002W\u00057\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u00122TC\u0001B U\rQ&1D\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001c"
)
public class StatsdReporter extends ScheduledReporter implements Logging {
   public final String org$apache$spark$metrics$sink$StatsdReporter$$host;
   public final int org$apache$spark$metrics$sink$StatsdReporter$$port;
   private final String prefix;
   private final InetSocketAddress org$apache$spark$metrics$sink$StatsdReporter$$address;
   private final Regex whitespace;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static TimeUnit $lessinit$greater$default$7() {
      return StatsdReporter$.MODULE$.$lessinit$greater$default$7();
   }

   public static TimeUnit $lessinit$greater$default$6() {
      return StatsdReporter$.MODULE$.$lessinit$greater$default$6();
   }

   public static MetricFilter $lessinit$greater$default$5() {
      return StatsdReporter$.MODULE$.$lessinit$greater$default$5();
   }

   public static String $lessinit$greater$default$4() {
      return StatsdReporter$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return StatsdReporter$.MODULE$.$lessinit$greater$default$3();
   }

   public static String $lessinit$greater$default$2() {
      return StatsdReporter$.MODULE$.$lessinit$greater$default$2();
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public InetSocketAddress org$apache$spark$metrics$sink$StatsdReporter$$address() {
      return this.org$apache$spark$metrics$sink$StatsdReporter$$address;
   }

   private Regex whitespace() {
      return this.whitespace;
   }

   public void report(final SortedMap gauges, final SortedMap counters, final SortedMap histograms, final SortedMap meters, final SortedMap timers) {
      boolean var7 = false;
      Failure var8 = null;
      Try var9 = .MODULE$.apply(() -> new DatagramSocket());
      if (var9 instanceof Failure) {
         var7 = true;
         var8 = (Failure)var9;
         Throwable ioe = var8.exception();
         if (ioe instanceof IOException) {
            IOException var11 = (IOException)ioe;
            this.logWarning((Function0)(() -> "StatsD datagram socket construction failed"), NetUtils.wrapException(this.org$apache$spark$metrics$sink$StatsdReporter$$host, this.org$apache$spark$metrics$sink$StatsdReporter$$port, NetUtils.getHostname(), 0, var11));
            BoxedUnit var19 = BoxedUnit.UNIT;
            return;
         }
      }

      if (var7) {
         Throwable e = var8.exception();
         this.logWarning((Function0)(() -> "StatsD datagram socket construction failed"), e);
         BoxedUnit var18 = BoxedUnit.UNIT;
      } else if (var9 instanceof Success) {
         Success var13 = (Success)var9;
         DatagramSocket s = (DatagramSocket)var13.value();
         String localAddress = (String).MODULE$.apply(() -> s.getLocalAddress()).map((x$1) -> x$1.getHostAddress()).getOrElse(() -> null);
         int localPort = s.getLocalPort();
         .MODULE$.apply((JFunction0.mcV.sp)() -> {
            scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(gauges.entrySet()).asScala().foreach((e) -> {
               $anonfun$report$8(this, s, e);
               return BoxedUnit.UNIT;
            });
            scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(counters.entrySet()).asScala().foreach((e) -> {
               $anonfun$report$9(this, s, e);
               return BoxedUnit.UNIT;
            });
            scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(histograms.entrySet()).asScala().foreach((e) -> {
               $anonfun$report$10(this, s, e);
               return BoxedUnit.UNIT;
            });
            scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(meters.entrySet()).asScala().foreach((e) -> {
               $anonfun$report$11(this, s, e);
               return BoxedUnit.UNIT;
            });
            scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(timers.entrySet()).asScala().foreach((e) -> {
               $anonfun$report$12(this, s, e);
               return BoxedUnit.UNIT;
            });
         }).recover(new Serializable(localAddress, localPort) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final StatsdReporter $outer;
            private final String localAddress$1;
            private final int localPort$1;

            public final Object applyOrElse(final Throwable x1, final Function1 default) {
               if (x1 instanceof IOException var5) {
                  this.$outer.logDebug((Function0)(() -> "Unable to send packets to StatsD"), NetUtils.wrapException(this.$outer.org$apache$spark$metrics$sink$StatsdReporter$$address().getHostString(), this.$outer.org$apache$spark$metrics$sink$StatsdReporter$$address().getPort(), this.localAddress$1, this.localPort$1, var5));
                  return BoxedUnit.UNIT;
               } else if (x1 != null) {
                  this.$outer.logDebug((Function0)(() -> "Unable to send packets to StatsD at '" + this.$outer.org$apache$spark$metrics$sink$StatsdReporter$$host + ":" + this.$outer.org$apache$spark$metrics$sink$StatsdReporter$$port + "'"), x1);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Throwable x1) {
               if (x1 instanceof IOException) {
                  return true;
               } else {
                  return x1 != null;
               }
            }

            public {
               if (StatsdReporter.this == null) {
                  throw null;
               } else {
                  this.$outer = StatsdReporter.this;
                  this.localAddress$1 = localAddress$1;
                  this.localPort$1 = localPort$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         });
         .MODULE$.apply((JFunction0.mcV.sp)() -> s.close()).recover(new Serializable(localAddress, localPort) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final StatsdReporter $outer;
            private final String localAddress$1;
            private final int localPort$1;

            public final Object applyOrElse(final Throwable x2, final Function1 default) {
               if (x2 instanceof IOException var5) {
                  this.$outer.logDebug((Function0)(() -> "Error when close socket to StatsD"), NetUtils.wrapException(this.$outer.org$apache$spark$metrics$sink$StatsdReporter$$address().getHostString(), this.$outer.org$apache$spark$metrics$sink$StatsdReporter$$address().getPort(), this.localAddress$1, this.localPort$1, var5));
                  return BoxedUnit.UNIT;
               } else if (x2 != null) {
                  this.$outer.logDebug((Function0)(() -> "Error when close socket to StatsD"), x2);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x2);
               }
            }

            public final boolean isDefinedAt(final Throwable x2) {
               if (x2 instanceof IOException) {
                  return true;
               } else {
                  return x2 != null;
               }
            }

            public {
               if (StatsdReporter.this == null) {
                  throw null;
               } else {
                  this.$outer = StatsdReporter.this;
                  this.localAddress$1 = localAddress$1;
                  this.localPort$1 = localPort$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var9);
      }
   }

   private void reportGauge(final String name, final Gauge gauge, final DatagramSocket socket) {
      this.formatAny(gauge.getValue()).foreach((v) -> {
         $anonfun$reportGauge$1(this, name, socket, v);
         return BoxedUnit.UNIT;
      });
   }

   private void reportCounter(final String name, final Counter counter, final DatagramSocket socket) {
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name}))), this.format(BoxesRunTime.boxToLong(counter.getCount())), StatsdMetricType$.MODULE$.COUNTER(), socket);
   }

   private void reportHistogram(final String name, final Histogram histogram, final DatagramSocket socket) {
      Snapshot snapshot = histogram.getSnapshot();
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "count"}))), this.format(BoxesRunTime.boxToLong(histogram.getCount())), StatsdMetricType$.MODULE$.GAUGE(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "max"}))), this.format(BoxesRunTime.boxToLong(snapshot.getMax())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "mean"}))), this.format(BoxesRunTime.boxToDouble(snapshot.getMean())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "min"}))), this.format(BoxesRunTime.boxToLong(snapshot.getMin())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "stddev"}))), this.format(BoxesRunTime.boxToDouble(snapshot.getStdDev())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p50"}))), this.format(BoxesRunTime.boxToDouble(snapshot.getMedian())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p75"}))), this.format(BoxesRunTime.boxToDouble(snapshot.get75thPercentile())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p95"}))), this.format(BoxesRunTime.boxToDouble(snapshot.get95thPercentile())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p98"}))), this.format(BoxesRunTime.boxToDouble(snapshot.get98thPercentile())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p99"}))), this.format(BoxesRunTime.boxToDouble(snapshot.get99thPercentile())), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p999"}))), this.format(BoxesRunTime.boxToDouble(snapshot.get999thPercentile())), StatsdMetricType$.MODULE$.TIMER(), socket);
   }

   private void reportMetered(final String name, final Metered meter, final DatagramSocket socket) {
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "count"}))), this.format(BoxesRunTime.boxToLong(meter.getCount())), StatsdMetricType$.MODULE$.GAUGE(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "m1_rate"}))), this.format(BoxesRunTime.boxToDouble(this.convertRate(meter.getOneMinuteRate()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "m5_rate"}))), this.format(BoxesRunTime.boxToDouble(this.convertRate(meter.getFiveMinuteRate()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "m15_rate"}))), this.format(BoxesRunTime.boxToDouble(this.convertRate(meter.getFifteenMinuteRate()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "mean_rate"}))), this.format(BoxesRunTime.boxToDouble(this.convertRate(meter.getMeanRate()))), StatsdMetricType$.MODULE$.TIMER(), socket);
   }

   private void reportTimer(final String name, final Timer timer, final DatagramSocket socket) {
      Snapshot snapshot = timer.getSnapshot();
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "max"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration((double)snapshot.getMax()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "mean"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.getMean()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "min"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration((double)snapshot.getMin()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "stddev"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.getStdDev()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p50"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.getMedian()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p75"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.get75thPercentile()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p95"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.get95thPercentile()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p98"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.get98thPercentile()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p99"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.get99thPercentile()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.send(this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name, "p999"}))), this.format(BoxesRunTime.boxToDouble(this.convertDuration(snapshot.get999thPercentile()))), StatsdMetricType$.MODULE$.TIMER(), socket);
      this.reportMetered(name, timer, socket);
   }

   private void send(final String name, final String value, final String metricType, final DatagramSocket socket) {
      byte[] bytes = this.sanitize(name + ":" + value + "|" + metricType).getBytes(StandardCharsets.UTF_8);
      DatagramPacket packet = new DatagramPacket(bytes, bytes.length, this.org$apache$spark$metrics$sink$StatsdReporter$$address());
      socket.send(packet);
   }

   private String fullName(final Seq names) {
      return MetricRegistry.name(this.prefix, (String[])names.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   private String sanitize(final String s) {
      return this.whitespace().replaceAllIn(s, "-");
   }

   private String format(final Object v) {
      return (String)this.formatAny(v).getOrElse(() -> "");
   }

   private Option formatAny(final Object v) {
      if (v instanceof Float) {
         float var4 = BoxesRunTime.unboxToFloat(v);
         return new Some(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%2.2f"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat(var4)})));
      } else if (v instanceof Double) {
         double var5 = BoxesRunTime.unboxToDouble(v);
         return new Some(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%2.2f"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(var5)})));
      } else if (v instanceof BigDecimal) {
         BigDecimal var7 = (BigDecimal)v;
         return new Some(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%2.2f"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var7})));
      } else {
         return (Option)(v instanceof Number ? new Some(v.toString()) : scala.None..MODULE$);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$report$8(final StatsdReporter $this, final DatagramSocket socket$1, final Map.Entry e) {
      $this.reportGauge((String)e.getKey(), (Gauge)e.getValue(), socket$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$report$9(final StatsdReporter $this, final DatagramSocket socket$1, final Map.Entry e) {
      $this.reportCounter((String)e.getKey(), (Counter)e.getValue(), socket$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$report$10(final StatsdReporter $this, final DatagramSocket socket$1, final Map.Entry e) {
      $this.reportHistogram((String)e.getKey(), (Histogram)e.getValue(), socket$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$report$11(final StatsdReporter $this, final DatagramSocket socket$1, final Map.Entry e) {
      $this.reportMetered((String)e.getKey(), (Metered)e.getValue(), socket$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$report$12(final StatsdReporter $this, final DatagramSocket socket$1, final Map.Entry e) {
      $this.reportTimer((String)e.getKey(), (Timer)e.getValue(), socket$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$reportGauge$1(final StatsdReporter $this, final String name$1, final DatagramSocket socket$2, final String v) {
      $this.send($this.fullName(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{name$1}))), v, StatsdMetricType$.MODULE$.GAUGE(), socket$2);
   }

   public StatsdReporter(final MetricRegistry registry, final String host, final int port, final String prefix, final MetricFilter filter, final TimeUnit rateUnit, final TimeUnit durationUnit) {
      super(registry, "statsd-reporter", filter, rateUnit, durationUnit);
      this.org$apache$spark$metrics$sink$StatsdReporter$$host = host;
      this.org$apache$spark$metrics$sink$StatsdReporter$$port = port;
      this.prefix = prefix;
      Logging.$init$(this);
      this.org$apache$spark$metrics$sink$StatsdReporter$$address = new InetSocketAddress(host, port);
      this.whitespace = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[\\s]+"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
