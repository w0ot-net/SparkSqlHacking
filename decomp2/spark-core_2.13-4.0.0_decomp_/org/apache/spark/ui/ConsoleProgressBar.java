package org.apache.spark.ui;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!B\u000e\u001d\u0001y!\u0003\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u000b]\u0002A\u0011\u0001\u001d\t\u000fq\u0002!\u0019!C\u0005{!1\u0011\t\u0001Q\u0001\nyBqA\u0011\u0001C\u0002\u0013%1\t\u0003\u0004H\u0001\u0001\u0006I\u0001\u0012\u0005\b\u0011\u0002\u0011\r\u0011\"\u0003D\u0011\u0019I\u0005\u0001)A\u0005\t\"9!\n\u0001b\u0001\n\u0013Y\u0005BB(\u0001A\u0003%A\nC\u0004Q\u0001\u0001\u0007I\u0011B\"\t\u000fE\u0003\u0001\u0019!C\u0005%\"1\u0001\f\u0001Q!\n\u0011Cq!\u0017\u0001A\u0002\u0013%1\tC\u0004[\u0001\u0001\u0007I\u0011B.\t\ru\u0003\u0001\u0015)\u0003E\u0011\u001dq\u0006\u00011A\u0005\n}Cq\u0001\u001b\u0001A\u0002\u0013%\u0011\u000e\u0003\u0004l\u0001\u0001\u0006K\u0001\u0019\u0005\bY\u0002\u0011\r\u0011\"\u0003n\u0011\u00191\b\u0001)A\u0005]\")q\u000f\u0001C\u0005q\")\u0011\u0010\u0001C\u0005u\"1\u00111\u0006\u0001\u0005\naDa!!\f\u0001\t\u0003A\bBBA\u0018\u0001\u0011\u0005\u0001P\u0001\nD_:\u001cx\u000e\\3Qe><'/Z:t\u0005\u0006\u0014(BA\u000f\u001f\u0003\t)\u0018N\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h'\r\u0001Qe\u000b\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051zS\"A\u0017\u000b\u00059r\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005Aj#a\u0002'pO\u001eLgnZ\u0001\u0003g\u000e\u001c\u0001\u0001\u0005\u00025k5\ta$\u0003\u00027=\ta1\u000b]1sW\u000e{g\u000e^3yi\u00061A(\u001b8jiz\"\"!O\u001e\u0011\u0005i\u0002Q\"\u0001\u000f\t\u000bE\u0012\u0001\u0019A\u001a\u0002\u0005\r\u0013V#\u0001 \u0011\u0005\u0019z\u0014B\u0001!(\u0005\u0011\u0019\u0005.\u0019:\u0002\u0007\r\u0013\u0006%\u0001\tva\u0012\fG/\u001a)fe&|G-T*fGV\tA\t\u0005\u0002'\u000b&\u0011ai\n\u0002\u0005\u0019>tw-A\tva\u0012\fG/\u001a)fe&|G-T*fG\u0002\naBZ5sgR$U\r\\1z\u001bN+7-A\bgSJ\u001cH\u000fR3mCfl5+Z2!\u00035!VM]7j]\u0006dw+\u001b3uQV\tA\n\u0005\u0002'\u001b&\u0011aj\n\u0002\u0004\u0013:$\u0018A\u0004+fe6Lg.\u00197XS\u0012$\b\u000eI\u0001\u000fY\u0006\u001cHOR5oSNDG+[7f\u0003Ia\u0017m\u001d;GS:L7\u000f\u001b+j[\u0016|F%Z9\u0015\u0005M3\u0006C\u0001\u0014U\u0013\t)vE\u0001\u0003V]&$\bbB,\r\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\n\u0014a\u00047bgR4\u0015N\\5tQRKW.\u001a\u0011\u0002\u001d1\f7\u000f^+qI\u0006$X\rV5nK\u0006\u0011B.Y:u+B$\u0017\r^3US6,w\fJ3r)\t\u0019F\fC\u0004X\u001f\u0005\u0005\t\u0019\u0001#\u0002\u001f1\f7\u000f^+qI\u0006$X\rV5nK\u0002\nq\u0002\\1tiB\u0013xn\u001a:fgN\u0014\u0015M]\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005Y\u0006twMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0014'AB*ue&tw-A\nmCN$\bK]8he\u0016\u001c8OQ1s?\u0012*\u0017\u000f\u0006\u0002TU\"9qKEA\u0001\u0002\u0004\u0001\u0017\u0001\u00057bgR\u0004&o\\4sKN\u001c()\u0019:!\u0003\u0015!\u0018.\\3s+\u0005q\u0007CA8u\u001b\u0005\u0001(BA9s\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003g\u0012\fA!\u001e;jY&\u0011Q\u000f\u001d\u0002\u0019'\u000eDW\rZ;mK\u0012,\u00050Z2vi>\u00148+\u001a:wS\u000e,\u0017A\u0002;j[\u0016\u0014\b%A\u0004sK\u001a\u0014Xm\u001d5\u0015\u0003M\u000bAa\u001d5poR\u00191k_?\t\u000bq<\u0002\u0019\u0001#\u0002\u00079|w\u000fC\u0003\u007f/\u0001\u0007q0\u0001\u0004ti\u0006<Wm\u001d\t\u0007\u0003\u0003\t\t\"a\u0006\u000f\t\u0005\r\u0011Q\u0002\b\u0005\u0003\u000b\tY!\u0004\u0002\u0002\b)\u0019\u0011\u0011\u0002\u001a\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013bAA\bO\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\n\u0003+\u00111aU3r\u0015\r\tya\n\t\u0005\u00033\t9#\u0004\u0002\u0002\u001c)!\u0011QDA\u0010\u0003\t1\u0018G\u0003\u0003\u0002\"\u0005\r\u0012aA1qS*\u0019\u0011Q\u0005\u0010\u0002\rM$\u0018\r^;t\u0013\u0011\tI#a\u0007\u0003\u0013M#\u0018mZ3ECR\f\u0017!B2mK\u0006\u0014\u0018!\u00034j]&\u001c\b.\u00117m\u0003\u0011\u0019Ho\u001c9"
)
public class ConsoleProgressBar implements Logging {
   private final SparkContext sc;
   private final char CR;
   private final long updatePeriodMSec;
   private final long firstDelayMSec;
   private final int TerminalWidth;
   private long lastFinishTime;
   private long lastUpdateTime;
   private String lastProgressBar;
   private final ScheduledExecutorService timer;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private char CR() {
      return this.CR;
   }

   private long updatePeriodMSec() {
      return this.updatePeriodMSec;
   }

   private long firstDelayMSec() {
      return this.firstDelayMSec;
   }

   private int TerminalWidth() {
      return this.TerminalWidth;
   }

   private long lastFinishTime() {
      return this.lastFinishTime;
   }

   private void lastFinishTime_$eq(final long x$1) {
      this.lastFinishTime = x$1;
   }

   private long lastUpdateTime() {
      return this.lastUpdateTime;
   }

   private void lastUpdateTime_$eq(final long x$1) {
      this.lastUpdateTime = x$1;
   }

   private String lastProgressBar() {
      return this.lastProgressBar;
   }

   private void lastProgressBar_$eq(final String x$1) {
      this.lastProgressBar = x$1;
   }

   private ScheduledExecutorService timer() {
      return this.timer;
   }

   private synchronized void refresh() {
      long now = System.currentTimeMillis();
      if (now - this.lastFinishTime() >= this.firstDelayMSec()) {
         Seq stages = (Seq)this.sc.statusStore().activeStages().filter((s) -> BoxesRunTime.boxToBoolean($anonfun$refresh$1(this, now, s)));
         if (stages.length() > 0) {
            this.show(now, (Seq)stages.take(3));
         }
      }
   }

   private void show(final long now, final Seq stages) {
      String bar;
      label18: {
         label17: {
            int width = this.TerminalWidth() / stages.length();
            bar = ((IterableOnceOps)stages.map((s) -> {
               int total = s.numTasks();
               String header = "[Stage " + s.stageId() + ":";
               int var10000 = s.numCompleteTasks();
               String tailer = "(" + var10000 + " + " + s.numActiveTasks() + ") / " + total + "]";
               int w = width - header.length() - tailer.length();
               String var8;
               if (w > 0) {
                  int percent = w * s.numCompleteTasks() / total;
                  var8 = .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), w).map((i) -> $anonfun$show$2(percent, BoxesRunTime.unboxToInt(i))).mkString("");
               } else {
                  var8 = "";
               }

               String bar = var8;
               return header + bar + tailer;
            })).mkString("");
            String var6 = this.lastProgressBar();
            if (bar == null) {
               if (var6 != null) {
                  break label17;
               }
            } else if (!bar.equals(var6)) {
               break label17;
            }

            if (now - this.lastUpdateTime() <= 60000L) {
               break label18;
            }
         }

         System.err.print(this.CR() + bar + this.CR());
         this.lastUpdateTime_$eq(now);
      }

      this.lastProgressBar_$eq(bar);
   }

   private void clear() {
      if (!this.lastProgressBar().isEmpty()) {
         PrintStream var10000 = System.err;
         char var10001 = this.CR();
         var10000.printf(var10001 + scala.collection.StringOps..MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), this.TerminalWidth()) + this.CR());
         this.lastProgressBar_$eq("");
      }
   }

   public synchronized void finishAll() {
      this.clear();
      this.lastFinishTime_$eq(System.currentTimeMillis());
   }

   public void stop() {
      ThreadUtils$.MODULE$.shutdown(this.timer(), ThreadUtils$.MODULE$.shutdown$default$2());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$refresh$1(final ConsoleProgressBar $this, final long now$1, final StageData s) {
      return now$1 - ((Date)s.submissionTime().get()).getTime() > $this.firstDelayMSec();
   }

   // $FF: synthetic method
   public static final String $anonfun$show$2(final int percent$1, final int i) {
      if (i < percent$1) {
         return "=";
      } else {
         return i == percent$1 ? ">" : " ";
      }
   }

   public ConsoleProgressBar(final SparkContext sc) {
      this.sc = sc;
      Logging.$init$(this);
      this.CR = '\r';
      this.updatePeriodMSec = BoxesRunTime.unboxToLong(sc.conf().get(UI$.MODULE$.UI_CONSOLE_PROGRESS_UPDATE_INTERVAL()));
      this.firstDelayMSec = 500L;
      this.TerminalWidth = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)scala.sys.package..MODULE$.env().getOrElse("COLUMNS", () -> "80")));
      this.lastFinishTime = 0L;
      this.lastUpdateTime = 0L;
      this.lastProgressBar = "";
      this.timer = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("refresh progress");
      this.timer().scheduleAtFixedRate(() -> this.refresh(), this.firstDelayMSec(), this.updatePeriodMSec(), TimeUnit.MILLISECONDS);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
