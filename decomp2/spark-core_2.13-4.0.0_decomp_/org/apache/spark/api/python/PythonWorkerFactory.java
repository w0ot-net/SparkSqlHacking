package org.apache.spark.api.python;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.Channels;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.SparkFiles$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.security.SocketAuthHelper;
import org.apache.spark.util.RedirectThread;
import org.apache.spark.util.RedirectThread$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.mutable.Queue;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005\tmb!B\u001c9\u0001q\u0012\u0005\u0002C(\u0001\u0005\u0003\u0005\u000b\u0011B)\t\u0011q\u0003!\u0011!Q\u0001\nEC\u0001\"\u0018\u0001\u0003\u0002\u0003\u0006I!\u0015\u0005\t=\u0002\u0011\t\u0011)A\u0005?\"A!\r\u0001BC\u0002\u0013\u00051\r\u0003\u0005h\u0001\t\u0005\t\u0015!\u0003e\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u0015A\u0007\u0001\"\u0001r\u0011\u001d1\bA1A\u0005\n\rDaa\u001e\u0001!\u0002\u0013!\u0007b\u0002=\u0001\u0005\u0004%I!\u001f\u0005\b\u0003\u0003\u0001\u0001\u0015!\u0003{\u0011%\t\u0019\u0001\u0001a\u0001\n\u0013\t)\u0001C\u0005\u0002\u0018\u0001\u0001\r\u0011\"\u0003\u0002\u001a!A\u0011Q\u0005\u0001!B\u0013\t9\u0001C\u0005\u0002D\u0001\u0011\r\u0011\"\u0001\u0002F!A\u00111\u000b\u0001!\u0002\u0013\t9\u0005C\u0005\u0002V\u0001\u0001\r\u0011\"\u0003\u0002X!I\u0011q\f\u0001A\u0002\u0013%\u0011\u0011\r\u0005\t\u0003K\u0002\u0001\u0015)\u0003\u0002Z!I\u0011\u0011\u000e\u0001C\u0002\u0013%\u00111\u000e\u0005\t\u0003\u0013\u0003\u0001\u0015!\u0003\u0002n!I\u0011Q\u0012\u0001C\u0002\u0013%\u0011q\u0012\u0005\t\u0003/\u0003\u0001\u0015!\u0003\u0002\u0012\"I\u00111\u0014\u0001A\u0002\u0013%\u0011Q\u0014\u0005\n\u0003K\u0003\u0001\u0019!C\u0005\u0003OC\u0001\"a+\u0001A\u0003&\u0011q\u0014\u0005\n\u0003_\u0003!\u0019!C\u0005\u0003cC\u0001\"!.\u0001A\u0003%\u00111\u0017\u0005\n\u0003s\u0003!\u0019!C\u0005\u0003wCq!!0\u0001A\u0003%\u0011\u000bC\u0004\u0002@\u0002!\t!!1\t\u000f\u0005=\u0007\u0001\"\u0003\u0002B\"A\u0011\u0011\u001b\u0001\u0005\u0002q\n\u0019\u000eC\u0004\u0002Z\u0002!I!a7\t\u000f\u0005u\u0007\u0001\"\u0003\u0002`\u001a1\u0011Q\u001f\u0001\u0005\u0003oDa\u0001[\u0013\u0005\u0002\u0005}\bb\u0002B\u0003K\u0011\u0005\u00131\u001c\u0005\b\u0005\u000f\u0001A\u0011BAn\u0011\u001d\u0011I\u0001\u0001C\u0005\u00037DqAa\u0003\u0001\t\u0003\tY\u000eC\u0004\u0003\u000e\u0001!\tAa\u0004\t\u000f\tU\u0001\u0001\"\u0001\u0003\u0018!9!1\u0004\u0001\u0005\u0002\tuq\u0001\u0003B\u0011q!\u0005AHa\t\u0007\u000f]B\u0004\u0012\u0001\u001f\u0003&!1\u0001n\fC\u0001\u0005OA\u0011B!\u000b0\u0005\u0004%\t!a\u0016\t\u0011\t-r\u0006)A\u0005\u00033B\u0011B!\f0\u0005\u0004%\t!!(\t\u0011\t=r\u0006)A\u0005\u0003?C!B!\r0\u0005\u0004%\t\u0001\u0010B\u001a\u0011!\u0011Id\fQ\u0001\n\tU\"a\u0005)zi\"|gnV8sW\u0016\u0014h)Y2u_JL(BA\u001d;\u0003\u0019\u0001\u0018\u0010\u001e5p]*\u00111\bP\u0001\u0004CBL'BA\u001f?\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0004)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0003\u0006\u0019qN]4\u0014\u0007\u0001\u0019\u0015\n\u0005\u0002E\u000f6\tQIC\u0001G\u0003\u0015\u00198-\u00197b\u0013\tAUI\u0001\u0004B]f\u0014VM\u001a\t\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019r\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u001d.\u0013q\u0001T8hO&tw-\u0001\u0006qsRDwN\\#yK\u000e\u001c\u0001\u0001\u0005\u0002S3:\u00111k\u0016\t\u0003)\u0016k\u0011!\u0016\u0006\u0003-B\u000ba\u0001\u0010:p_Rt\u0014B\u0001-F\u0003\u0019\u0001&/\u001a3fM&\u0011!l\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a+\u0015\u0001D<pe.,'/T8ek2,\u0017\u0001\u00043bK6|g.T8ek2,\u0017aB3omZ\u000b'o\u001d\t\u0005%\u0002\f\u0016+\u0003\u0002b7\n\u0019Q*\u00199\u0002!U\u001cX\rR1f[>tWI\\1cY\u0016$W#\u00013\u0011\u0005\u0011+\u0017B\u00014F\u0005\u001d\u0011un\u001c7fC:\f\u0011#^:f\t\u0006,Wn\u001c8F]\u0006\u0014G.\u001a3!\u0003\u0019a\u0014N\\5u}Q1!\u000e\\7o_B\u0004\"a\u001b\u0001\u000e\u0003aBQaT\u0004A\u0002ECQ\u0001X\u0004A\u0002ECQ!X\u0004A\u0002ECQAX\u0004A\u0002}CQAY\u0004A\u0002\u0011$RA\u001b:tiVDQa\u0014\u0005A\u0002ECQ\u0001\u0018\u0005A\u0002ECQA\u0018\u0005A\u0002}CQA\u0019\u0005A\u0002\u0011\f\u0011\"^:f\t\u0006,Wn\u001c8\u0002\u0015U\u001cX\rR1f[>t\u0007%\u0001\u0006bkRD\u0007*\u001a7qKJ,\u0012A\u001f\t\u0003wzl\u0011\u0001 \u0006\u0003{r\n\u0001b]3dkJLG/_\u0005\u0003\u007fr\u0014\u0001cU8dW\u0016$\u0018)\u001e;i\u0011\u0016d\u0007/\u001a:\u0002\u0017\u0005,H\u000f\u001b%fYB,'\u000fI\u0001\u0007I\u0006,Wn\u001c8\u0016\u0005\u0005\u001d\u0001\u0003BA\u0005\u0003'i!!a\u0003\u000b\t\u00055\u0011qB\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0012\u0005!!.\u0019<b\u0013\u0011\t)\"a\u0003\u0003\u000fA\u0013xnY3tg\u0006QA-Y3n_:|F%Z9\u0015\t\u0005m\u0011\u0011\u0005\t\u0004\t\u0006u\u0011bAA\u0010\u000b\n!QK\\5u\u0011%\t\u0019CDA\u0001\u0002\u0004\t9!A\u0002yIE\nq\u0001Z1f[>t\u0007\u0005K\u0004\u0010\u0003S\ti$a\u0010\u0011\t\u0005-\u0012\u0011H\u0007\u0003\u0003[QA!a\f\u00022\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\u0005M\u0012QG\u0001\u000bC:tw\u000e^1uS>t'BAA\u001c\u0003\u0015Q\u0017M^1y\u0013\u0011\tY$!\f\u0003\u0013\u001d+\u0018M\u001d3fI\nK\u0018!\u0002<bYV,\u0017EAA!\u0003\u0011\u0019X\r\u001c4\u0002\u0015\u0011\fW-\\8o\u0011>\u001cH/\u0006\u0002\u0002HA!\u0011\u0011JA(\u001b\t\tYE\u0003\u0003\u0002N\u0005=\u0011a\u00018fi&!\u0011\u0011KA&\u0005-Ie.\u001a;BI\u0012\u0014Xm]:\u0002\u0017\u0011\fW-\\8o\u0011>\u001cH\u000fI\u0001\u000bI\u0006,Wn\u001c8Q_J$XCAA-!\r!\u00151L\u0005\u0004\u0003;*%aA%oi\u0006qA-Y3n_:\u0004vN\u001d;`I\u0015\fH\u0003BA\u000e\u0003GB\u0011\"a\t\u0014\u0003\u0003\u0005\r!!\u0017\u0002\u0017\u0011\fW-\\8o!>\u0014H\u000f\t\u0015\b)\u0005%\u0012QHA \u00035!\u0017-Z7p]^{'o[3sgV\u0011\u0011Q\u000e\t\t\u0003_\nI(! \u0002\u00046\u0011\u0011\u0011\u000f\u0006\u0005\u0003g\n)(A\u0004nkR\f'\r\\3\u000b\u0007\u0005]T)\u0001\u0006d_2dWm\u0019;j_:LA!a\u001f\u0002r\tYq+Z1l\u0011\u0006\u001c\b.T1q!\rY\u0017qP\u0005\u0004\u0003\u0003C$\u0001\u0004)zi\"|gnV8sW\u0016\u0014\b\u0003BA\u0005\u0003\u000bKA!a\"\u0002\f\ti\u0001K]8dKN\u001c\b*\u00198eY\u0016\fa\u0002Z1f[>twk\u001c:lKJ\u001c\b\u0005K\u0004\u0017\u0003S\ti$a\u0010\u0002\u0017%$G.Z,pe.,'o]\u000b\u0003\u0003#\u0003b!a\u001c\u0002\u0014\u0006u\u0014\u0002BAK\u0003c\u0012Q!U;fk\u0016\fA\"\u001b3mK^{'o[3sg\u0002Bs\u0001GA\u0015\u0003{\ty$\u0001\bmCN$\u0018i\u0019;jm&$\u0018PT:\u0016\u0005\u0005}\u0005c\u0001#\u0002\"&\u0019\u00111U#\u0003\t1{gnZ\u0001\u0013Y\u0006\u001cH/Q2uSZLG/\u001f(t?\u0012*\u0017\u000f\u0006\u0003\u0002\u001c\u0005%\u0006\"CA\u00125\u0005\u0005\t\u0019AAP\u0003=a\u0017m\u001d;BGRLg/\u001b;z\u001dN\u0004\u0003fB\u000e\u0002*\u0005u\u0012qH\u0001\u000eg&l\u0007\u000f\\3X_J\\WM]:\u0016\u0005\u0005M\u0006\u0003CA8\u0003s\ni(a\u0002\u0002\u001dMLW\u000e\u001d7f/>\u00148.\u001a:tA!:Q$!\u000b\u0002>\u0005}\u0012A\u00039zi\"|g\u000eU1uQV\t\u0011+A\u0006qsRDwN\u001c)bi\"\u0004\u0013AB2sK\u0006$X\r\u0006\u0002\u0002DB9A)!2\u0002~\u0005%\u0017bAAd\u000b\n1A+\u001e9mKJ\u0002R\u0001RAf\u0003\u0007K1!!4F\u0005\u0019y\u0005\u000f^5p]\u0006\u00192M]3bi\u0016$\u0006N]8vO\"$\u0015-Z7p]\u0006\u00112M]3bi\u0016\u001c\u0016.\u001c9mK^{'o[3s)\u0011\t\u0019-!6\t\r\u0005]'\u00051\u0001e\u00031\u0011Gn\\2lS:<Wj\u001c3f\u0003-\u0019H/\u0019:u\t\u0006,Wn\u001c8\u0015\u0005\u0005m\u0011a\u0006:fI&\u0014Xm\u0019;TiJ,\u0017-\\:U_N#H-\u001a:s)\u0019\tY\"!9\u0002r\"9\u00111\u001d\u0013A\u0002\u0005\u0015\u0018AB:uI>,H\u000f\u0005\u0003\u0002h\u00065XBAAu\u0015\u0011\tY/a\u0004\u0002\u0005%|\u0017\u0002BAx\u0003S\u00141\"\u00138qkR\u001cFO]3b[\"9\u00111\u001f\u0013A\u0002\u0005\u0015\u0018AB:uI\u0016\u0014(OA\u0007N_:LGo\u001c:UQJ,\u0017\rZ\n\u0004K\u0005e\b\u0003BA\u0005\u0003wLA!!@\u0002\f\t1A\u000b\u001b:fC\u0012$\"A!\u0001\u0011\u0007\t\rQ%D\u0001\u0001\u0003\r\u0011XO\\\u0001\u0013G2,\u0017M\\;q\u0013\u0012dWmV8sW\u0016\u00148/\u0001\u0006ti>\u0004H)Y3n_:\fAa\u001d;pa\u0006Q1\u000f^8q/>\u00148.\u001a:\u0015\t\u0005m!\u0011\u0003\u0005\b\u0005'Y\u0003\u0019AA?\u0003\u00199xN]6fe\u0006i!/\u001a7fCN,wk\u001c:lKJ$B!a\u0007\u0003\u001a!9!1\u0003\u0017A\u0002\u0005u\u0014aD5t/>\u00148.\u001a:Ti>\u0004\b/\u001a3\u0015\u0007\u0011\u0014y\u0002C\u0004\u0003\u00145\u0002\r!! \u0002'AKH\u000f[8o/>\u00148.\u001a:GC\u000e$xN]=\u0011\u0005-|3CA\u0018D)\t\u0011\u0019#A\fQ%>\u001bUiU*`/\u0006KEk\u0018+J\u001b\u0016{U\u000bV0N'\u0006A\u0002KU(D\u000bN\u001bvlV!J)~#\u0016*T#P+R{Vj\u0015\u0011\u0002-%#E*R0X\u001fJ[UIU0U\u00136+u*\u0016+`\u001dN\u000bq#\u0013#M\u000b~;vJU&F%~#\u0016*T#P+R{fj\u0015\u0011\u0002'\u0011,g-Y;mi\u0012\u000bW-\\8o\u001b>$W\u000f\\3\u0016\u0005\tU\u0002\u0003BA\u0005\u0005oI1AWA\u0006\u0003Q!WMZ1vYR$\u0015-Z7p]6{G-\u001e7fA\u0001"
)
public class PythonWorkerFactory implements Logging {
   public final String org$apache$spark$api$python$PythonWorkerFactory$$pythonExec;
   private final String workerModule;
   private final String daemonModule;
   private final Map envVars;
   private final boolean useDaemonEnabled;
   private final boolean useDaemon;
   private final SocketAuthHelper authHelper;
   @GuardedBy("self")
   private Process daemon;
   private final InetAddress daemonHost;
   @GuardedBy("self")
   private int daemonPort;
   @GuardedBy("self")
   private final WeakHashMap daemonWorkers;
   @GuardedBy("self")
   private final Queue idleWorkers;
   @GuardedBy("self")
   private long org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs;
   @GuardedBy("self")
   private final WeakHashMap simpleWorkers;
   private final String pythonPath;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static long IDLE_WORKER_TIMEOUT_NS() {
      return PythonWorkerFactory$.MODULE$.IDLE_WORKER_TIMEOUT_NS();
   }

   public static int PROCESS_WAIT_TIMEOUT_MS() {
      return PythonWorkerFactory$.MODULE$.PROCESS_WAIT_TIMEOUT_MS();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public boolean useDaemonEnabled() {
      return this.useDaemonEnabled;
   }

   private boolean useDaemon() {
      return this.useDaemon;
   }

   private SocketAuthHelper authHelper() {
      return this.authHelper;
   }

   private Process daemon() {
      return this.daemon;
   }

   private void daemon_$eq(final Process x$1) {
      this.daemon = x$1;
   }

   public InetAddress daemonHost() {
      return this.daemonHost;
   }

   private int daemonPort() {
      return this.daemonPort;
   }

   private void daemonPort_$eq(final int x$1) {
      this.daemonPort = x$1;
   }

   private WeakHashMap daemonWorkers() {
      return this.daemonWorkers;
   }

   private Queue idleWorkers() {
      return this.idleWorkers;
   }

   public long org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs() {
      return this.org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs;
   }

   public void org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs_$eq(final long x$1) {
      this.org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs = x$1;
   }

   private WeakHashMap simpleWorkers() {
      return this.simpleWorkers;
   }

   private String pythonPath() {
      return this.pythonPath;
   }

   public Tuple2 create() {
      Object var1 = new Object();

      Tuple2 var10000;
      try {
         if (this.useDaemon()) {
            synchronized(this){}

            try {
               while(this.idleWorkers().nonEmpty()) {
                  PythonWorker worker = (PythonWorker)this.idleWorkers().dequeue();
                  ProcessHandle workerHandle = (ProcessHandle)this.daemonWorkers().apply(worker);
                  if (workerHandle.isAlive()) {
                     liftedTree1$1(var1, worker, workerHandle);
                  }

                  this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Worker ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER..MODULE$, worker)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"process from idle queue is dead, discarding."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  this.stopWorker(worker);
               }
            } catch (Throwable var8) {
               throw var8;
            }

            var10000 = this.createThroughDaemon();
         } else {
            var10000 = this.createSimpleWorker(false);
         }
      } catch (NonLocalReturnControl var9) {
         if (var9.key() != var1) {
            throw var9;
         }

         var10000 = (Tuple2)var9.value();
      }

      return var10000;
   }

   private Tuple2 createThroughDaemon() {
      synchronized(this){}

      Tuple2 var2;
      try {
         this.startDaemon();
         var2 = this.liftedTree2$1();
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public Tuple2 createSimpleWorker(final boolean blockingMode) {
      ServerSocketChannel serverSocketChannel = null;

      Tuple2 var27;
      try {
         ProcessBuilder pb;
         label185: {
            String jobArtifactUUID;
            label184: {
               serverSocketChannel = ServerSocketChannel.open();
               serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 1);
               pb = new ProcessBuilder(Arrays.asList((Object[])(new String[]{this.org$apache$spark$api$python$PythonWorkerFactory$$pythonExec, "-m", this.workerModule})));
               jobArtifactUUID = (String)this.envVars.getOrElse("SPARK_JOB_ARTIFACT_UUID", () -> "default");
               String var5 = "default";
               if (jobArtifactUUID == null) {
                  if (var5 != null) {
                     break label184;
                  }
               } else if (!jobArtifactUUID.equals(var5)) {
                  break label184;
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
               break label185;
            }

            File f = new File(SparkFiles$.MODULE$.getRootDirectory(), jobArtifactUUID);
            f.mkdir();
            pb.directory(f);
         }

         java.util.Map workerEnv = pb.environment();
         workerEnv.putAll(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.envVars).asJava());
         workerEnv.put("PYTHONPATH", this.pythonPath());
         workerEnv.put("PYTHONUNBUFFERED", "YES");
         workerEnv.put("PYTHON_WORKER_FACTORY_PORT", Integer.toString(serverSocketChannel.socket().getLocalPort()));
         workerEnv.put("PYTHON_WORKER_FACTORY_SECRET", this.authHelper().secret());
         if (Utils$.MODULE$.preferIPv6()) {
            workerEnv.put("SPARK_PREFER_IPV6", "True");
         } else {
            BoxedUnit var25 = BoxedUnit.UNIT;
         }

         Process workerProcess = pb.start();
         this.redirectStreamsToStderr(workerProcess.getInputStream(), workerProcess.getErrorStream());

         try {
            serverSocketChannel.configureBlocking(false);
            Selector serverSelector = Selector.open();
            serverSocketChannel.register(serverSelector, 16);
            if (serverSelector.select(10000L) <= 0) {
               throw new SocketTimeoutException("Timed out while waiting for the Python worker to connect back");
            }

            SocketChannel socketChannel = serverSocketChannel.accept();
            this.authHelper().authClient(socketChannel.socket());
            int pid = (new DataInputStream(Channels.newInputStream(socketChannel))).readInt();
            if (pid < 0) {
               throw new IllegalStateException("Python failed to launch worker with code " + pid);
            }

            if (!blockingMode) {
               socketChannel.configureBlocking(false);
            } else {
               BoxedUnit var26 = BoxedUnit.UNIT;
            }

            PythonWorker worker = new PythonWorker(socketChannel);
            synchronized(this){}

            try {
               this.simpleWorkers().put(worker, workerProcess);
            } catch (Throwable var22) {
               throw var22;
            }

            var27 = new Tuple2(worker.refresh(), scala.jdk.OptionConverters.RichOptional..MODULE$.toScala$extension(scala.jdk.OptionConverters..MODULE$.RichOptional(ProcessHandle.of((long)pid))));
         } catch (Exception var23) {
            throw new SparkException("Python worker failed to connect back.", var23);
         }
      } finally {
         if (serverSocketChannel != null) {
            serverSocketChannel.close();
         }

      }

      return var27;
   }

   private synchronized void startDaemon() {
      if (this.daemon() == null) {
         try {
            List command;
            ProcessBuilder pb;
            label63: {
               String jobArtifactUUID;
               label62: {
                  command = Arrays.asList((Object[])(new String[]{this.org$apache$spark$api$python$PythonWorkerFactory$$pythonExec, "-m", this.daemonModule, this.workerModule}));
                  pb = new ProcessBuilder(command);
                  jobArtifactUUID = (String)this.envVars.getOrElse("SPARK_JOB_ARTIFACT_UUID", () -> "default");
                  String var4 = "default";
                  if (jobArtifactUUID == null) {
                     if (var4 != null) {
                        break label62;
                     }
                  } else if (!jobArtifactUUID.equals(var4)) {
                     break label62;
                  }

                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  break label63;
               }

               File f = new File(SparkFiles$.MODULE$.getRootDirectory(), jobArtifactUUID);
               f.mkdir();
               pb.directory(f);
            }

            java.util.Map workerEnv = pb.environment();
            workerEnv.putAll(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.envVars).asJava());
            workerEnv.put("PYTHONPATH", this.pythonPath());
            workerEnv.put("PYTHON_WORKER_FACTORY_SECRET", this.authHelper().secret());
            if (Utils$.MODULE$.preferIPv6()) {
               workerEnv.put("SPARK_PREFER_IPV6", "True");
            } else {
               BoxedUnit var20 = BoxedUnit.UNIT;
            }

            workerEnv.put("PYTHONUNBUFFERED", "YES");
            this.daemon_$eq(pb.start());
            DataInputStream in = new DataInputStream(this.daemon().getInputStream());

            try {
               this.daemonPort_$eq(in.readInt());
            } catch (EOFException var17) {
               if (this.daemon().isAlive()) {
                  throw SparkCoreErrors$.MODULE$.eofExceptionWhileReadPortNumberError(this.daemonModule, SparkCoreErrors$.MODULE$.eofExceptionWhileReadPortNumberError$default$2());
               }

               throw SparkCoreErrors$.MODULE$.eofExceptionWhileReadPortNumberError(this.daemonModule, new Some(BoxesRunTime.boxToInteger(this.daemon().exitValue())));
            } catch (Throwable var18) {
               throw var18;
            }

            if (this.daemonPort() >= 1 && this.daemonPort() <= 65535) {
               this.redirectStreamsToStderr(in, this.daemon().getErrorStream());
            } else {
               String exceptionMessage = scala.collection.StringOps..MODULE$.format$extension("\n            |Bad data in %s's standard output. Invalid port number:\n            |  %s (0x%08x)\n            |Python command to execute the daemon was:\n            |  %s\n            |Check that you don't have any unexpected modules or libraries in\n            |your PYTHONPATH:\n            |  %s\n            |Also, check if you have a sitecustomize.py module in your python path,\n            |or in your python installation, that is printing to standard output", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.daemonModule, BoxesRunTime.boxToInteger(this.daemonPort()), BoxesRunTime.boxToInteger(this.daemonPort()), scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(command).asScala().mkString(" "), this.pythonPath()}));
               throw new SparkException(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString(exceptionMessage)));
            }
         } catch (Exception var19) {
            String stderr;
            label49: {
               stderr = (String)scala.Option..MODULE$.apply(this.daemon()).flatMap((d) -> Utils$.MODULE$.getStderr(d, (long)PythonWorkerFactory$.MODULE$.PROCESS_WAIT_TIMEOUT_MS())).getOrElse(() -> "");
               this.stopDaemon();
               String var13 = "";
               if (stderr == null) {
                  if (var13 != null) {
                     break label49;
                  }
               } else if (!stderr.equals(var13)) {
                  break label49;
               }

               throw var19;
            }

            String formattedStderr = stderr.replace("\n", "\n  ");
            String errorMessage = "\n              |Error from python worker:\n              |  " + formattedStderr + "\n              |PYTHONPATH was:\n              |  " + this.pythonPath() + "\n              |" + var19;
            SparkException wrappedException = new SparkException(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString(errorMessage)));
            wrappedException.setStackTrace(var19.getStackTrace());
            throw wrappedException;
         }
      }
   }

   private void redirectStreamsToStderr(final InputStream stdout, final InputStream stderr) {
      try {
         (new RedirectThread(stdout, System.err, "stdout reader for " + this.org$apache$spark$api$python$PythonWorkerFactory$$pythonExec, RedirectThread$.MODULE$.$lessinit$greater$default$4())).start();
         (new RedirectThread(stderr, System.err, "stderr reader for " + this.org$apache$spark$api$python$PythonWorkerFactory$$pythonExec, RedirectThread$.MODULE$.$lessinit$greater$default$4())).start();
      } catch (Exception var4) {
         this.logError((Function0)(() -> "Exception in redirecting streams"), var4);
      }

   }

   public void org$apache$spark$api$python$PythonWorkerFactory$$cleanupIdleWorkers() {
      while(this.idleWorkers().nonEmpty()) {
         PythonWorker worker = (PythonWorker)this.idleWorkers().dequeue();

         try {
            worker.stop();
         } catch (Exception var3) {
            this.logWarning((Function0)(() -> "Failed to stop worker socket"), var3);
         }
      }

   }

   private synchronized void stopDaemon() {
      if (this.useDaemon()) {
         this.org$apache$spark$api$python$PythonWorkerFactory$$cleanupIdleWorkers();
         if (this.daemon() != null) {
            this.daemon().destroy();
         }

         this.daemon_$eq((Process)null);
         this.daemonPort_$eq(0);
      } else {
         this.simpleWorkers().values().foreach((x$4) -> {
            $anonfun$stopDaemon$1(x$4);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void stop() {
      this.stopDaemon();
   }

   public void stopWorker(final PythonWorker worker) {
      synchronized(this){}

      try {
         if (this.useDaemon()) {
            if (this.daemon() != null) {
               this.daemonWorkers().get(worker).foreach((processHandle) -> {
                  $anonfun$stopWorker$1(this, processHandle);
                  return BoxedUnit.UNIT;
               });
            }
         } else {
            this.simpleWorkers().get(worker).foreach((x$5) -> {
               $anonfun$stopWorker$2(x$5);
               return BoxedUnit.UNIT;
            });
         }
      } catch (Throwable var4) {
         throw var4;
      }

      worker.stop();
   }

   public void releaseWorker(final PythonWorker worker) {
      if (this.useDaemon()) {
         synchronized(this){}

         try {
            this.org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs_$eq(System.nanoTime());
            this.idleWorkers().enqueue(worker);
         } catch (Throwable var6) {
            throw var6;
         }

      } else {
         try {
            worker.stop();
         } catch (Exception var7) {
            this.logWarning((Function0)(() -> "Failed to close worker"), var7);
         }

      }
   }

   public boolean isWorkerStopped(final PythonWorker worker) {
      scala.Predef..MODULE$.assert(!this.useDaemon(), () -> "isWorkerStopped() is not supported for daemon mode");
      return this.simpleWorkers().get(worker).exists((x$6) -> BoxesRunTime.boxToBoolean($anonfun$isWorkerStopped$2(x$6)));
   }

   // $FF: synthetic method
   private static final void liftedTree1$1(final Object nonLocalReturnKey1$1, final PythonWorker worker$1, final ProcessHandle workerHandle$1) {
      try {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Tuple2(worker$1.refresh(), new Some(workerHandle$1)));
      } catch (CancelledKeyException var4) {
      }
   }

   private final Tuple2 createWorker$1() {
      SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(this.daemonHost(), this.daemonPort()));
      int pid = (new DataInputStream(Channels.newInputStream(socketChannel))).readInt();
      if (pid < 0) {
         throw new IllegalStateException("Python daemon failed to launch worker with code " + pid);
      } else {
         ProcessHandle processHandle = (ProcessHandle)ProcessHandle.of((long)pid).orElseThrow(() -> new IllegalStateException("Python daemon failed to launch worker."));
         this.authHelper().authToServer(socketChannel.socket());
         socketChannel.configureBlocking(false);
         PythonWorker worker = new PythonWorker(socketChannel);
         this.daemonWorkers().put(worker, processHandle);
         return new Tuple2(worker.refresh(), new Some(processHandle));
      }
   }

   // $FF: synthetic method
   private final Tuple2 liftedTree2$1() {
      Tuple2 var10000;
      try {
         var10000 = this.createWorker$1();
      } catch (SocketException var2) {
         this.logWarning((Function0)(() -> "Failed to open socket to Python daemon:"), var2);
         this.logWarning((Function0)(() -> "Assuming that daemon unexpectedly quit, attempting to restart"));
         this.stopDaemon();
         this.startDaemon();
         var10000 = this.createWorker$1();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$stopDaemon$1(final Process x$4) {
      x$4.destroy();
   }

   // $FF: synthetic method
   public static final void $anonfun$stopWorker$1(final PythonWorkerFactory $this, final ProcessHandle processHandle) {
      DataOutputStream output = new DataOutputStream($this.daemon().getOutputStream());
      output.writeInt((int)processHandle.pid());
      output.flush();
      $this.daemon().getOutputStream().flush();
   }

   // $FF: synthetic method
   public static final void $anonfun$stopWorker$2(final Process x$5) {
      x$5.destroy();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isWorkerStopped$2(final Process x$6) {
      return !x$6.isAlive();
   }

   public PythonWorkerFactory(final String pythonExec, final String workerModule, final String daemonModule, final Map envVars, final boolean useDaemonEnabled) {
      this.org$apache$spark$api$python$PythonWorkerFactory$$pythonExec = pythonExec;
      this.workerModule = workerModule;
      this.daemonModule = daemonModule;
      this.envVars = envVars;
      this.useDaemonEnabled = useDaemonEnabled;
      Logging.$init$(this);
      this.useDaemon = !System.getProperty("os.name").startsWith("Windows") && useDaemonEnabled;
      this.authHelper = new SocketAuthHelper(SparkEnv$.MODULE$.get().conf());
      this.daemon = null;
      this.daemonHost = InetAddress.getLoopbackAddress();
      this.daemonPort = 0;
      this.daemonWorkers = new WeakHashMap();
      this.idleWorkers = new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      this.org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs = 0L;
      (new MonitorThread()).start();
      this.simpleWorkers = new WeakHashMap();
      this.pythonPath = PythonUtils$.MODULE$.mergePythonPaths(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{PythonUtils$.MODULE$.sparkPythonPath(), (String)envVars.getOrElse("PYTHONPATH", () -> ""), (String)scala.sys.package..MODULE$.env().getOrElse("PYTHONPATH", () -> "")})));
   }

   public PythonWorkerFactory(final String pythonExec, final String workerModule, final Map envVars, final boolean useDaemonEnabled) {
      this(pythonExec, workerModule, PythonWorkerFactory$.MODULE$.defaultDaemonModule(), envVars, useDaemonEnabled);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class MonitorThread extends Thread {
      // $FF: synthetic field
      public final PythonWorkerFactory $outer;

      public void run() {
         while(true) {
            synchronized(this.org$apache$spark$api$python$PythonWorkerFactory$MonitorThread$$$outer()){}

            try {
               if (PythonWorkerFactory$.MODULE$.IDLE_WORKER_TIMEOUT_NS() < System.nanoTime() - this.org$apache$spark$api$python$PythonWorkerFactory$MonitorThread$$$outer().org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs()) {
                  this.org$apache$spark$api$python$PythonWorkerFactory$MonitorThread$$$outer().org$apache$spark$api$python$PythonWorkerFactory$$cleanupIdleWorkers();
                  this.org$apache$spark$api$python$PythonWorkerFactory$MonitorThread$$$outer().org$apache$spark$api$python$PythonWorkerFactory$$lastActivityNs_$eq(System.nanoTime());
               }
            } catch (Throwable var3) {
               throw var3;
            }

            Thread.sleep(10000L);
         }
      }

      // $FF: synthetic method
      public PythonWorkerFactory org$apache$spark$api$python$PythonWorkerFactory$MonitorThread$$$outer() {
         return this.$outer;
      }

      public MonitorThread() {
         if (PythonWorkerFactory.this == null) {
            throw null;
         } else {
            this.$outer = PythonWorkerFactory.this;
            super("Idle Worker Monitor for " + PythonWorkerFactory.this.org$apache$spark$api$python$PythonWorkerFactory$$pythonExec);
            this.setDaemon(true);
         }
      }
   }
}
