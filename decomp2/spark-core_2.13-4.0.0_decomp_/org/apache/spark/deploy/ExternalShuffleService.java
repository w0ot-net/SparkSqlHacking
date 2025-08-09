package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.metrics.MetricsSystem$;
import org.apache.spark.metrics.MetricsSystemInstances$;
import org.apache.spark.network.TransportContext;
import org.apache.spark.network.crypto.AuthServerBootstrap;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.server.TransportServer;
import org.apache.spark.network.shuffle.ExternalBlockHandler;
import org.apache.spark.network.shuffledb.DBBackend;
import org.apache.spark.network.util.TransportConf;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Array.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg!\u0002\u0016,\u0001-\u001a\u0004\u0002\u0003!\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011\u0019\u0003!\u0011!Q\u0001\n\u001dCQA\u0013\u0001\u0005\u0002-Cq\u0001\u0015\u0001C\u0002\u0013E\u0011\u000b\u0003\u0004Y\u0001\u0001\u0006IA\u0015\u0005\b3\u0002\u0011\r\u0011\"\u0003[\u0011\u0019q\u0006\u0001)A\u00057\"9q\f\u0001b\u0001\n\u0013\u0001\u0007B\u00023\u0001A\u0003%\u0011\rC\u0004f\u0001\t\u0007I\u0011\u00024\t\r=\u0004\u0001\u0015!\u0003h\u0011\u001d\u0001\bA1A\u0005\nEDaA\u001f\u0001!\u0002\u0013\u0011\bbB>\u0001\u0005\u0004%I\u0001 \u0005\b\u0003\u000f\u0001\u0001\u0015!\u0003~\u0011-\tI\u0001\u0001a\u0001\u0002\u0004%I!a\u0003\t\u0017\u0005U\u0001\u00011AA\u0002\u0013%\u0011q\u0003\u0005\f\u0003G\u0001\u0001\u0019!A!B\u0013\ti\u0001C\u0006\u0002&\u0001\u0001\r\u00111A\u0005\n\u0005\u001d\u0002bCA\u001a\u0001\u0001\u0007\t\u0019!C\u0005\u0003kA1\"!\u000f\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002*!I\u00111\b\u0001C\u0002\u0013%\u0011Q\b\u0005\t\u0003\u000b\u0002\u0001\u0015!\u0003\u0002@!9\u0011q\t\u0001\u0005\u0012\u0005%\u0003BBA8\u0001\u0011\u0005A\u0010C\u0004\u0002r\u0001!\t\"a\u001d\t\u000f\u0005e\u0004\u0001\"\u0001\u0002|!9\u0011Q\u0010\u0001\u0005\u0002\u0005m\u0004bBA@\u0001\u0011\u0005\u0011\u0011\u0011\u0005\b\u0003\u000f\u0003A\u0011AAE\u0011\u001d\t\t\n\u0001C\u0001\u0003w:q!a%,\u0011\u0003\t)J\u0002\u0004+W!\u0005\u0011q\u0013\u0005\u0007\u0015\u0006\"\t!!'\t\u0017\u0005\u0015\u0012\u00051AA\u0002\u0013%\u00111\u0014\u0005\f\u0003g\t\u0003\u0019!a\u0001\n\u0013\ti\n\u0003\u0006\u0002:\u0005\u0002\r\u0011!Q!\n1C\u0011\"!+\"\u0005\u0004%I!a+\t\u0011\u0005m\u0016\u0005)A\u0005\u0003[Cq!!0\"\t\u0003\ty\f\u0003\u0005\u0002>\u0006\"\t!LAf\u0005Y)\u0005\u0010^3s]\u0006d7\u000b[;gM2,7+\u001a:wS\u000e,'B\u0001\u0017.\u0003\u0019!W\r\u001d7ps*\u0011afL\u0001\u0006gB\f'o\u001b\u0006\u0003aE\na!\u00199bG\",'\"\u0001\u001a\u0002\u0007=\u0014xmE\u0002\u0001ii\u0002\"!\u000e\u001d\u000e\u0003YR\u0011aN\u0001\u0006g\u000e\fG.Y\u0005\u0003sY\u0012a!\u00118z%\u00164\u0007CA\u001e?\u001b\u0005a$BA\u001f.\u0003!Ig\u000e^3s]\u0006d\u0017BA =\u0005\u001daunZ4j]\u001e\f\u0011b\u001d9be.\u001cuN\u001c4\u0004\u0001A\u00111\tR\u0007\u0002[%\u0011Q)\f\u0002\n'B\f'o[\"p]\u001a\fqb]3dkJLG/_'b]\u0006<WM\u001d\t\u0003\u0007\"K!!S\u0017\u0003\u001fM+7-\u001e:jifl\u0015M\\1hKJ\fa\u0001P5oSRtDc\u0001'O\u001fB\u0011Q\nA\u0007\u0002W!)\u0001i\u0001a\u0001\u0005\")ai\u0001a\u0001\u000f\u0006\u0019R.Y:uKJlU\r\u001e:jGN\u001c\u0016p\u001d;f[V\t!\u000b\u0005\u0002T-6\tAK\u0003\u0002V[\u00059Q.\u001a;sS\u000e\u001c\u0018BA,U\u00055iU\r\u001e:jGN\u001c\u0016p\u001d;f[\u0006!R.Y:uKJlU\r\u001e:jGN\u001c\u0016p\u001d;f[\u0002\nq!\u001a8bE2,G-F\u0001\\!\t)D,\u0003\u0002^m\t9!i\\8mK\u0006t\u0017\u0001C3oC\ndW\r\u001a\u0011\u0002\tA|'\u000f^\u000b\u0002CB\u0011QGY\u0005\u0003GZ\u00121!\u00138u\u0003\u0015\u0001xN\u001d;!\u0003U\u0011XmZ5ti\u0016\u0014X\rZ#yK\u000e,Ho\u001c:t\t\n+\u0012a\u001a\t\u0003Q6l\u0011!\u001b\u0006\u0003U.\fA\u0001\\1oO*\tA.\u0001\u0003kCZ\f\u0017B\u00018j\u0005\u0019\u0019FO]5oO\u00061\"/Z4jgR,'/\u001a3Fq\u0016\u001cW\u000f^8sg\u0012\u0013\u0005%A\u0007ue\u0006t7\u000f]8si\u000e{gNZ\u000b\u0002eB\u00111\u000f_\u0007\u0002i*\u0011QO^\u0001\u0005kRLGN\u0003\u0002x[\u00059a.\u001a;x_J\\\u0017BA=u\u00055!&/\u00198ta>\u0014HoQ8oM\u0006qAO]1ogB|'\u000f^\"p]\u001a\u0004\u0013\u0001\u00042m_\u000e\\\u0007*\u00198eY\u0016\u0014X#A?\u0011\u0007y\f\u0019!D\u0001\u0000\u0015\r\t\tA^\u0001\bg\",hM\u001a7f\u0013\r\t)a \u0002\u0015\u000bb$XM\u001d8bY\ncwnY6IC:$G.\u001a:\u0002\u001b\tdwnY6IC:$G.\u001a:!\u0003A!(/\u00198ta>\u0014HoQ8oi\u0016DH/\u0006\u0002\u0002\u000eA!\u0011qBA\t\u001b\u00051\u0018bAA\nm\n\u0001BK]1ogB|'\u000f^\"p]R,\u0007\u0010^\u0001\u0015iJ\fgn\u001d9peR\u001cuN\u001c;fqR|F%Z9\u0015\t\u0005e\u0011q\u0004\t\u0004k\u0005m\u0011bAA\u000fm\t!QK\\5u\u0011%\t\t#EA\u0001\u0002\u0004\ti!A\u0002yIE\n\u0011\u0003\u001e:b]N\u0004xN\u001d;D_:$X\r\u001f;!\u0003\u0019\u0019XM\u001d<feV\u0011\u0011\u0011\u0006\t\u0005\u0003W\ty#\u0004\u0002\u0002.)\u0019\u0011Q\u0005<\n\t\u0005E\u0012Q\u0006\u0002\u0010)J\fgn\u001d9peR\u001cVM\u001d<fe\u0006Q1/\u001a:wKJ|F%Z9\u0015\t\u0005e\u0011q\u0007\u0005\n\u0003C!\u0012\u0011!a\u0001\u0003S\tqa]3sm\u0016\u0014\b%\u0001\u000btQV4g\r\\3TKJ4\u0018nY3T_V\u00148-Z\u000b\u0003\u0003\u007f\u00012!TA!\u0013\r\t\u0019e\u000b\u0002\u001d\u000bb$XM\u001d8bYNCWO\u001a4mKN+'O^5dKN{WO]2f\u0003U\u0019\b.\u001e4gY\u0016\u001cVM\u001d<jG\u0016\u001cv.\u001e:dK\u0002\nQDZ5oIJ+w-[:uKJ,G-\u0012=fGV$xN]:E\u0005\u001aKG.\u001a\u000b\u0005\u0003\u0017\n9\u0006\u0005\u0003\u0002N\u0005MSBAA(\u0015\r\t\tf[\u0001\u0003S>LA!!\u0016\u0002P\t!a)\u001b7f\u0011\u001d\tI\u0006\u0007a\u0001\u00037\na\u0001\u001a2OC6,\u0007\u0003BA/\u0003WrA!a\u0018\u0002hA\u0019\u0011\u0011\r\u001c\u000e\u0005\u0005\r$bAA3\u0003\u00061AH]8pizJ1!!\u001b7\u0003\u0019\u0001&/\u001a3fM&\u0019a.!\u001c\u000b\u0007\u0005%d'A\bhKR\u0014En\\2l\u0011\u0006tG\r\\3s\u0003YqWm^*ik\u001a4G.\u001a\"m_\u000e\\\u0007*\u00198eY\u0016\u0014HcA?\u0002v!1\u0011q\u000f\u000eA\u0002I\fAaY8oM\u0006q1\u000f^1si&3WI\\1cY\u0016$GCAA\r\u0003\u0015\u0019H/\u0019:u\u0003I\t\u0007\u000f\u001d7jG\u0006$\u0018n\u001c8SK6|g/\u001a3\u0015\t\u0005e\u00111\u0011\u0005\b\u0003\u000bk\u0002\u0019AA.\u0003\u0015\t\u0007\u000f]%e\u0003=)\u00070Z2vi>\u0014(+Z7pm\u0016$GCBA\r\u0003\u0017\u000by\tC\u0004\u0002\u000ez\u0001\r!a\u0017\u0002\u0015\u0015DXmY;u_JLE\rC\u0004\u0002\u0006z\u0001\r!a\u0017\u0002\tM$x\u000e]\u0001\u0017\u000bb$XM\u001d8bYNCWO\u001a4mKN+'O^5dKB\u0011Q*I\n\u0004CQRDCAAK+\u0005aE\u0003BA\r\u0003?C\u0001\"!\t%\u0003\u0003\u0005\r\u0001\u0014\u0015\u0004K\u0005\r\u0006cA\u001b\u0002&&\u0019\u0011q\u0015\u001c\u0003\u0011Y|G.\u0019;jY\u0016\fqAY1se&,'/\u0006\u0002\u0002.B!\u0011qVA\\\u001b\t\t\tL\u0003\u0003\u00024\u0006U\u0016AC2p]\u000e,(O]3oi*\u0011Qo[\u0005\u0005\u0003s\u000b\tL\u0001\bD_VtG\u000fR8x]2\u000bGo\u00195\u0002\u0011\t\f'O]5fe\u0002\nA!\\1j]R!\u0011\u0011DAa\u0011\u001d\t\u0019\r\u000ba\u0001\u0003\u000b\fA!\u0019:hgB)Q'a2\u0002\\%\u0019\u0011\u0011\u001a\u001c\u0003\u000b\u0005\u0013(/Y=\u0015\r\u0005e\u0011QZAh\u0011\u001d\t\u0019-\u000ba\u0001\u0003\u000bDq!!5*\u0001\u0004\t\u0019.A\toK^\u001c\u0006.\u001e4gY\u0016\u001cVM\u001d<jG\u0016\u0004b!NAk\u0005\u001ec\u0015bAAlm\tIa)\u001e8di&|gN\r"
)
public class ExternalShuffleService implements Logging {
   private final SparkConf sparkConf;
   private final SecurityManager securityManager;
   private final MetricsSystem masterMetricsSystem;
   private final boolean enabled;
   private final int port;
   private final String registeredExecutorsDB;
   private final TransportConf transportConf;
   private final ExternalBlockHandler blockHandler;
   private TransportContext transportContext;
   private TransportServer server;
   private final ExternalShuffleServiceSource shuffleServiceSource;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void main(final String[] args) {
      ExternalShuffleService$.MODULE$.main(args);
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

   public MetricsSystem masterMetricsSystem() {
      return this.masterMetricsSystem;
   }

   private boolean enabled() {
      return this.enabled;
   }

   private int port() {
      return this.port;
   }

   private String registeredExecutorsDB() {
      return this.registeredExecutorsDB;
   }

   private TransportConf transportConf() {
      return this.transportConf;
   }

   private ExternalBlockHandler blockHandler() {
      return this.blockHandler;
   }

   private TransportContext transportContext() {
      return this.transportContext;
   }

   private void transportContext_$eq(final TransportContext x$1) {
      this.transportContext = x$1;
   }

   private TransportServer server() {
      return this.server;
   }

   private void server_$eq(final TransportServer x$1) {
      this.server = x$1;
   }

   private ExternalShuffleServiceSource shuffleServiceSource() {
      return this.shuffleServiceSource;
   }

   public File findRegisteredExecutorsDBFile(final String dbName) {
      String[] localDirs = (String[])this.sparkConf.getOption("spark.local.dir").map((x$3) -> x$3.split(",")).getOrElse(() -> (String[]).MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(String.class)));
      if (localDirs.length >= 1) {
         return new File((String)scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])localDirs), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$findRegisteredExecutorsDBFile$3(dbName, x$4))).getOrElse(() -> localDirs[0]), dbName);
      } else {
         this.logWarning((Function0)(() -> "'spark.local.dir' should be set first when we use db in ExternalShuffleService. Note that this only affects standalone mode."));
         return null;
      }
   }

   public ExternalBlockHandler getBlockHandler() {
      return this.blockHandler();
   }

   public ExternalBlockHandler newShuffleBlockHandler(final TransportConf conf) {
      if (BoxesRunTime.unboxToBoolean(this.sparkConf.get(package$.MODULE$.SHUFFLE_SERVICE_DB_ENABLED())) && this.enabled()) {
         String shuffleDBName = (String)this.sparkConf.get(package$.MODULE$.SHUFFLE_SERVICE_DB_BACKEND());
         DBBackend dbBackend = DBBackend.byName(shuffleDBName);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Use ", " as the implementation of "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_DB_BACKEND_NAME..MODULE$, dbBackend.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_DB_BACKEND_KEY..MODULE$, package$.MODULE$.SHUFFLE_SERVICE_DB_BACKEND().key())}))))));
         return new ExternalBlockHandler(conf, this.findRegisteredExecutorsDBFile(dbBackend.fileName(this.registeredExecutorsDB())));
      } else {
         return new ExternalBlockHandler(conf, (File)null);
      }
   }

   public void startIfEnabled() {
      if (this.enabled()) {
         this.start();
      }
   }

   public void start() {
      scala.Predef..MODULE$.require(this.server() == null, () -> "Shuffle server already started");
      boolean authEnabled = this.securityManager.isAuthenticationEnabled();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting shuffle service on port ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(this.port()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (auth enabled = ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.AUTH_ENABLED..MODULE$, BoxesRunTime.boxToBoolean(authEnabled))}))))));
      Seq bootstraps = (Seq)(authEnabled ? new scala.collection.immutable..colon.colon(new AuthServerBootstrap(this.transportConf(), this.securityManager), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$);
      this.transportContext_$eq(new TransportContext(this.transportConf(), this.blockHandler(), true));
      this.server_$eq(this.transportContext().createServer(this.port(), scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(bootstraps).asJava()));
      this.shuffleServiceSource().registerMetricSet(this.server().getAllMetrics());
      this.blockHandler().getAllMetrics().getMetrics().put("numRegisteredConnections", this.server().getRegisteredConnections());
      this.shuffleServiceSource().registerMetricSet(this.blockHandler().getAllMetrics());
      this.masterMetricsSystem().registerSource(this.shuffleServiceSource());
      this.masterMetricsSystem().start(this.masterMetricsSystem().start$default$1());
   }

   public void applicationRemoved(final String appId) {
      this.blockHandler().applicationRemoved(appId, true);
   }

   public void executorRemoved(final String executorId, final String appId) {
      this.blockHandler().executorRemoved(executorId, appId);
   }

   public void stop() {
      if (this.server() != null) {
         this.server().close();
         this.server_$eq((TransportServer)null);
      }

      if (this.transportContext() != null) {
         this.transportContext().close();
         this.transportContext_$eq((TransportContext)null);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findRegisteredExecutorsDBFile$3(final String dbName$1, final String x$4) {
      return (new File(x$4, dbName$1)).exists();
   }

   public ExternalShuffleService(final SparkConf sparkConf, final SecurityManager securityManager) {
      this.sparkConf = sparkConf;
      this.securityManager = securityManager;
      Logging.$init$(this);
      this.masterMetricsSystem = MetricsSystem$.MODULE$.createMetricsSystem(MetricsSystemInstances$.MODULE$.SHUFFLE_SERVICE(), sparkConf);
      this.enabled = BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.SHUFFLE_SERVICE_ENABLED()));
      this.port = BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.SHUFFLE_SERVICE_PORT()));
      this.registeredExecutorsDB = "registeredExecutors";
      String x$2 = "shuffle";
      int x$3 = 0;
      Some x$4 = new Some(securityManager.getRpcSSLOptions());
      Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
      this.transportConf = SparkTransportConf$.MODULE$.fromSparkConf(sparkConf, "shuffle", 0, x$5, x$4);
      this.blockHandler = this.newShuffleBlockHandler(this.transportConf());
      this.shuffleServiceSource = new ExternalShuffleServiceSource();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
