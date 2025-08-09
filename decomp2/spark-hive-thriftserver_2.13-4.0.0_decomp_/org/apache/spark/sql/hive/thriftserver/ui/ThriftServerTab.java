package org.apache.spark.sql.hive.thriftserver.ui;

import java.util.Date;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.ui.SparkUITab;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q!\u0005\n\u0001)\u0001B\u0001\u0002\f\u0001\u0003\u0006\u0004%\tA\f\u0005\tg\u0001\u0011\t\u0011)A\u0005_!AA\u0007\u0001B\u0001B\u0003%Q\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0004>\u0001\t\u0007I\u0011\t \t\r\u001d\u0003\u0001\u0015!\u0003@\u0011\u001dA\u0005A1A\u0005\u0002%CaA\u0013\u0001!\u0002\u0013)\u0004bB&\u0001\u0005\u0004%\t\u0001\u0014\u0005\u0007'\u0002\u0001\u000b\u0011B'\t\u000bQ\u0003A\u0011A+\t\u000bq\u0003A\u0011I/\b\r\u0005\u0014\u0002\u0012\u0001\u000bc\r\u0019\t\"\u0003#\u0001\u0015G\")\u0001H\u0004C\u0001O\")\u0001N\u0004C\u0001S\nyA\u000b\u001b:jMR\u001cVM\u001d<feR\u000b'M\u0003\u0002\u0014)\u0005\u0011Q/\u001b\u0006\u0003+Y\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!a\u0006\r\u0002\t!Lg/\u001a\u0006\u00033i\t1a]9m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7c\u0001\u0001\"MA\u0011!\u0005J\u0007\u0002G)\u00111CG\u0005\u0003K\r\u0012!b\u00159be.,\u0016\nV1c!\t9#&D\u0001)\u0015\tI#$\u0001\u0005j]R,'O\\1m\u0013\tY\u0003FA\u0004M_\u001e<\u0017N\\4\u0002\u000bM$xN]3\u0004\u0001U\tq\u0006\u0005\u00021c5\t!#\u0003\u00023%\ty\u0002*\u001b<f)\"\u0014\u0018N\u001a;TKJ4XM\u001d\u001aBaB\u001cF/\u0019;vgN#xN]3\u0002\rM$xN]3!\u0003\u001d\u0019\b/\u0019:l+&\u0003\"A\t\u001c\n\u0005]\u001a#aB*qCJ\\W+S\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007iZD\b\u0005\u00021\u0001!)A\u0006\u0002a\u0001_!)A\u0007\u0002a\u0001k\u0005!a.Y7f+\u0005y\u0004C\u0001!F\u001b\u0005\t%B\u0001\"D\u0003\u0011a\u0017M\\4\u000b\u0003\u0011\u000bAA[1wC&\u0011a)\u0011\u0002\u0007'R\u0014\u0018N\\4\u0002\u000b9\fW.\u001a\u0011\u0002\rA\f'/\u001a8u+\u0005)\u0014a\u00029be\u0016tG\u000fI\u0001\ngR\f'\u000f\u001e+j[\u0016,\u0012!\u0014\t\u0003\u001dFk\u0011a\u0014\u0006\u0003!\u000e\u000bA!\u001e;jY&\u0011!k\u0014\u0002\u0005\t\u0006$X-\u0001\u0006ti\u0006\u0014H\u000fV5nK\u0002\na\u0001Z3uC\u000eDG#\u0001,\u0011\u0005]SV\"\u0001-\u000b\u0003e\u000bQa]2bY\u0006L!a\u0017-\u0003\tUs\u0017\u000e^\u0001\rI&\u001c\b\u000f\\1z\u001fJ$WM]\u000b\u0002=B\u0011qkX\u0005\u0003Ab\u00131!\u00138u\u0003=!\u0006N]5giN+'O^3s)\u0006\u0014\u0007C\u0001\u0019\u000f'\tqA\r\u0005\u0002XK&\u0011a\r\u0017\u0002\u0007\u0003:L(+\u001a4\u0015\u0003\t\f!bZ3u'B\f'o[+J)\t)$\u000eC\u0003l!\u0001\u0007A.\u0001\u0007ta\u0006\u00148nQ8oi\u0016DH\u000f\u0005\u0002n]6\t!$\u0003\u0002p5\ta1\u000b]1sW\u000e{g\u000e^3yi\u0002"
)
public class ThriftServerTab extends SparkUITab implements Logging {
   private final HiveThriftServer2AppStatusStore store;
   private final SparkUI sparkUI;
   private final String name;
   private final SparkUI parent;
   private final Date startTime;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static SparkUI getSparkUI(final SparkContext sparkContext) {
      return ThriftServerTab$.MODULE$.getSparkUI(sparkContext);
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

   public HiveThriftServer2AppStatusStore store() {
      return this.store;
   }

   public String name() {
      return this.name;
   }

   public SparkUI parent() {
      return this.parent;
   }

   public Date startTime() {
      return this.startTime;
   }

   public void detach() {
      this.sparkUI.detachTab(this);
   }

   public int displayOrder() {
      return 1;
   }

   public ThriftServerTab(final HiveThriftServer2AppStatusStore store, final SparkUI sparkUI) {
      super(sparkUI, "sqlserver");
      this.store = store;
      this.sparkUI = sparkUI;
      Logging.$init$(this);
      this.name = "JDBC/ODBC Server";
      this.parent = sparkUI;
      this.startTime = ((ApplicationAttemptInfo)sparkUI.store().applicationInfo().attempts().head()).startTime();
      this.attachPage(new ThriftServerPage(this));
      this.attachPage(new ThriftServerSessionPage(this));
      this.parent().attachTab(this);
   }
}
