package org.apache.spark.deploy.rest;

import jakarta.servlet.DispatcherType;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.server.ConnectionFactory;
import org.sparkproject.jetty.server.HttpConfiguration;
import org.sparkproject.jetty.server.HttpConnectionFactory;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.ServerConnector;
import org.sparkproject.jetty.servlet.FilterHolder;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.servlet.ServletHolder;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001deAB\u0010!\u0003\u0003!#\u0006\u0003\u00058\u0001\t\u0015\r\u0011\"\u0001:\u0011!)\u0005A!A!\u0002\u0013Q\u0004\u0002\u0003$\u0001\u0005\u000b\u0007I\u0011A$\t\u0011-\u0003!\u0011!Q\u0001\n!C\u0001\u0002\u0014\u0001\u0003\u0006\u0004%\t!\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005\u001d\")1\u000b\u0001C\u0001)\"9!\f\u0001b\u0001\u000e#Y\u0006bB0\u0001\u0005\u00045\t\u0002\u0019\u0005\bI\u0002\u0011\rQ\"\u0005f\u0011\u001dI\u0007A1A\u0007\u0012)DqA\u001c\u0001C\u0002\u001bEq\u000eC\u0004t\u0001\t\u0007i\u0011\u0003;\t\u0011a\u0004\u0001\u0019!C\u0001AeD!\"a\u0004\u0001\u0001\u0004%\t\u0001IA\t\u0011\u001d\ti\u0002\u0001Q!\niD\u0011\"a\b\u0001\u0005\u0004%\t\"!\t\t\u0011\u0005E\u0002\u0001)A\u0005\u0003GA!\"a\r\u0001\u0011\u000b\u0007I\u0011CA\u001b\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u001fBq!!\u0015\u0001\t\u0013\t\u0019\u0006C\u0004\u0002`\u0001!I!!\u0019\t\u000f\u0005M\u0004\u0001\"\u0001\u0002v\u001dA\u0011q\u000f\u0011\t\u0002\u0001\nIHB\u0004 A!\u0005\u0001%a\u001f\t\rMKB\u0011AA?\u0011%\ty(\u0007b\u0001\n\u0003\t\t\u0003\u0003\u0005\u0002\u0002f\u0001\u000b\u0011BA\u0012\u0011!\t\u0019)\u0007b\u0001\n\u00039\u0005bBAC3\u0001\u0006I\u0001\u0013\u0002\u0015%\u0016\u001cHoU;c[&\u001c8/[8o'\u0016\u0014h/\u001a:\u000b\u0005\u0005\u0012\u0013\u0001\u0002:fgRT!a\t\u0013\u0002\r\u0011,\u0007\u000f\\8z\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7c\u0001\u0001,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t1\u0011I\\=SK\u001a\u0004\"AM\u001b\u000e\u0003MR!\u0001\u000e\u0013\u0002\u0011%tG/\u001a:oC2L!AN\u001a\u0003\u000f1{wmZ5oO\u0006!\u0001n\\:u\u0007\u0001)\u0012A\u000f\t\u0003w\ts!\u0001\u0010!\u0011\u0005ujS\"\u0001 \u000b\u0005}B\u0014A\u0002\u001fs_>$h(\u0003\u0002B[\u00051\u0001K]3eK\u001aL!a\u0011#\u0003\rM#(/\u001b8h\u0015\t\tU&A\u0003i_N$\b%A\u0007sKF,Xm\u001d;fIB{'\u000f^\u000b\u0002\u0011B\u0011A&S\u0005\u0003\u00156\u00121!\u00138u\u00039\u0011X-];fgR,G\rU8si\u0002\n!\"\\1ti\u0016\u00148i\u001c8g+\u0005q\u0005CA(Q\u001b\u0005!\u0013BA)%\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0006nCN$XM]\"p]\u001a\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003V/bK\u0006C\u0001,\u0001\u001b\u0005\u0001\u0003\"B\u001c\b\u0001\u0004Q\u0004\"\u0002$\b\u0001\u0004A\u0005\"\u0002'\b\u0001\u0004q\u0015\u0001F:vE6LGOU3rk\u0016\u001cHoU3sm2,G/F\u0001]!\t1V,\u0003\u0002_A\t!2+\u001e2nSR\u0014V-];fgR\u001cVM\u001d<mKR\f!c[5mYJ+\u0017/^3tiN+'O\u001e7fiV\t\u0011\r\u0005\u0002WE&\u00111\r\t\u0002\u0013\u0017&dGNU3rk\u0016\u001cHoU3sm2,G/A\u000blS2d\u0017\t\u001c7SKF,Xm\u001d;TKJ4H.\u001a;\u0016\u0003\u0019\u0004\"AV4\n\u0005!\u0004#!F&jY2\fE\u000e\u001c*fcV,7\u000f^*feZdW\r^\u0001\u0015gR\fG/^:SKF,Xm\u001d;TKJ4H.\u001a;\u0016\u0003-\u0004\"A\u00167\n\u00055\u0004#\u0001F*uCR,8OU3rk\u0016\u001cHoU3sm2,G/A\ndY\u0016\f'OU3rk\u0016\u001cHoU3sm2,G/F\u0001q!\t1\u0016/\u0003\u0002sA\t\u00192\t\\3beJ+\u0017/^3tiN+'O\u001e7fi\u0006!\"/Z1esj\u0014V-];fgR\u001cVM\u001d<mKR,\u0012!\u001e\t\u0003-ZL!a\u001e\u0011\u0003)I+\u0017\rZ={%\u0016\fX/Z:u'\u0016\u0014h\u000f\\3u\u0003\u001dy6/\u001a:wKJ,\u0012A\u001f\t\u0004Yml\u0018B\u0001?.\u0005\u0019y\u0005\u000f^5p]B\u0019a0a\u0003\u000e\u0003}TA!!\u0001\u0002\u0004\u000511/\u001a:wKJTA!!\u0002\u0002\b\u0005)!.\u001a;us*\u0019\u0011\u0011\u0002\u0015\u0002\u000f\u0015\u001cG.\u001b9tK&\u0019\u0011QB@\u0003\rM+'O^3s\u0003-y6/\u001a:wKJ|F%Z9\u0015\t\u0005M\u0011\u0011\u0004\t\u0004Y\u0005U\u0011bAA\f[\t!QK\\5u\u0011!\tYbDA\u0001\u0002\u0004Q\u0018a\u0001=%c\u0005Aql]3sm\u0016\u0014\b%A\u0006cCN,7i\u001c8uKb$XCAA\u0012!\u0011\t)#a\f\u000e\u0005\u0005\u001d\"\u0002BA\u0015\u0003W\tA\u0001\\1oO*\u0011\u0011QF\u0001\u0005U\u00064\u0018-C\u0002D\u0003O\tABY1tK\u000e{g\u000e^3yi\u0002\n\u0001cY8oi\u0016DH\u000fV8TKJ4H.\u001a;\u0016\u0005\u0005]\u0002cBA\u001d\u0003\u0007R\u0014qI\u0007\u0003\u0003wQA!!\u0010\u0002@\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0004\u0003\u0003j\u0013AC2pY2,7\r^5p]&!\u0011QIA\u001e\u0005\ri\u0015\r\u001d\t\u0004-\u0006%\u0013bAA&A\tY!+Z:u'\u0016\u0014h\u000f\\3u\u0003\u0015\u0019H/\u0019:u)\u0005A\u0015a\u00023p'R\f'\u000f\u001e\u000b\u0005\u0003+\nY\u0006E\u0003-\u0003/j\b*C\u0002\u0002Z5\u0012a\u0001V;qY\u0016\u0014\u0004BBA/+\u0001\u0007\u0001*A\u0005ti\u0006\u0014H\u000fU8si\u0006Q\u0011\r\u001a3GS2$XM]:\u0015\t\u0005M\u00111\r\u0005\b\u0003K2\u0002\u0019AA4\u0003\u001dA\u0017M\u001c3mKJ\u0004B!!\u001b\u0002p5\u0011\u00111\u000e\u0006\u0005\u0003[\n\u0019!A\u0004tKJ4H.\u001a;\n\t\u0005E\u00141\u000e\u0002\u0016'\u0016\u0014h\u000f\\3u\u0007>tG/\u001a=u\u0011\u0006tG\r\\3s\u0003\u0011\u0019Ho\u001c9\u0015\u0005\u0005M\u0011\u0001\u0006*fgR\u001cVOY7jgNLwN\\*feZ,'\u000f\u0005\u0002W3M\u0011\u0011d\u000b\u000b\u0003\u0003s\n\u0001\u0003\u0015*P)>\u001bu\nT0W\u000bJ\u001b\u0016j\u0014(\u0002#A\u0013v\nV(D\u001f2{f+\u0012*T\u0013>s\u0005%A\u000eT\u0007~+fj\u0013(P/:{\u0006KU(U\u001f\u000e{Ej\u0018,F%NKuJT\u0001\u001d'\u000e{VKT&O\u001f^su\f\u0015*P)>\u001bu\nT0W\u000bJ\u001b\u0016j\u0014(!\u0001"
)
public abstract class RestSubmissionServer implements Logging {
   private Map contextToServlet;
   private final String host;
   private final int requestedPort;
   private final SparkConf masterConf;
   private Option _server;
   private final String baseContext;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static int SC_UNKNOWN_PROTOCOL_VERSION() {
      return RestSubmissionServer$.MODULE$.SC_UNKNOWN_PROTOCOL_VERSION();
   }

   public static String PROTOCOL_VERSION() {
      return RestSubmissionServer$.MODULE$.PROTOCOL_VERSION();
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

   public String host() {
      return this.host;
   }

   public int requestedPort() {
      return this.requestedPort;
   }

   public SparkConf masterConf() {
      return this.masterConf;
   }

   public abstract SubmitRequestServlet submitRequestServlet();

   public abstract KillRequestServlet killRequestServlet();

   public abstract KillAllRequestServlet killAllRequestServlet();

   public abstract StatusRequestServlet statusRequestServlet();

   public abstract ClearRequestServlet clearRequestServlet();

   public abstract ReadyzRequestServlet readyzRequestServlet();

   public Option _server() {
      return this._server;
   }

   public void _server_$eq(final Option x$1) {
      this._server = x$1;
   }

   public String baseContext() {
      return this.baseContext;
   }

   private Map contextToServlet$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.contextToServlet = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.baseContext() + "/create/*"), this.submitRequestServlet()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.baseContext() + "/kill/*"), this.killRequestServlet()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.baseContext() + "/killall/*"), this.killAllRequestServlet()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.baseContext() + "/status/*"), this.statusRequestServlet()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.baseContext() + "/clear/*"), this.clearRequestServlet()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.baseContext() + "/readyz/*"), this.readyzRequestServlet()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("/*"), new ErrorServlet())})));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.contextToServlet;
   }

   public Map contextToServlet() {
      return !this.bitmap$0 ? this.contextToServlet$lzycompute() : this.contextToServlet;
   }

   public int start() {
      Tuple2 var3 = Utils$.MODULE$.startServiceOnPort(this.requestedPort(), (startPort) -> $anonfun$start$1(this, BoxesRunTime.unboxToInt(startPort)), this.masterConf(), Utils$.MODULE$.startServiceOnPort$default$4());
      if (var3 != null) {
         Server server = (Server)var3._1();
         int boundPort = var3._2$mcI$sp();
         Tuple2 var2 = new Tuple2(server, BoxesRunTime.boxToInteger(boundPort));
         Server server = (Server)var2._1();
         int boundPort = var2._2$mcI$sp();
         this._server_$eq(new Some(server));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Started REST server for submitting applications on ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, this.host())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with port ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(boundPort))}))))));
         return boundPort;
      } else {
         throw new MatchError(var3);
      }
   }

   private Tuple2 doStart(final int startPort) {
      QueuedThreadPool threadPool = new QueuedThreadPool(BoxesRunTime.unboxToInt(this.masterConf().get(package$.MODULE$.MASTER_REST_SERVER_MAX_THREADS())));
      threadPool.setName(this.getClass().getSimpleName());
      if (Utils$.MODULE$.isJavaVersionAtLeast21() && BoxesRunTime.unboxToBoolean(this.masterConf().get(package$.MODULE$.MASTER_REST_SERVER_VIRTUAL_THREADS()))) {
         Method newVirtualThreadPerTaskExecutor = Executors.class.getMethod("newVirtualThreadPerTaskExecutor");
         ExecutorService service = (ExecutorService)newVirtualThreadPerTaskExecutor.invoke((Object)null);
         threadPool.setVirtualThreadsExecutor(service);
      }

      threadPool.setDaemon(true);
      Server server = new Server(threadPool);
      HttpConfiguration httpConfig = new HttpConfiguration();
      this.logDebug((Function0)(() -> "Using setSendServerVersion: false"));
      httpConfig.setSendServerVersion(false);
      this.logDebug((Function0)(() -> "Using setSendXPoweredBy: false"));
      httpConfig.setSendXPoweredBy(false);
      ServerConnector connector = new ServerConnector(server, (Executor)null, new ScheduledExecutorScheduler("RestSubmissionServer-JettyScheduler", true), (ByteBufferPool)null, -1, -1, new ConnectionFactory[]{new HttpConnectionFactory(httpConfig)});
      connector.setHost(this.host());
      connector.setPort(startPort);
      connector.setReuseAddress(!Utils$.MODULE$.isWindows());
      server.addConnector(connector);
      ServletContextHandler mainHandler = new ServletContextHandler();
      mainHandler.setServer(server);
      mainHandler.setContextPath("/");
      this.contextToServlet().foreach((x0$1) -> {
         $anonfun$doStart$3(mainHandler, x0$1);
         return BoxedUnit.UNIT;
      });
      this.addFilters(mainHandler);
      server.setHandler(mainHandler);
      server.start();
      int boundPort = connector.getLocalPort();
      return new Tuple2(server, BoxesRunTime.boxToInteger(boundPort));
   }

   private void addFilters(final ServletContextHandler handler) {
      ((IterableOnceOps)this.masterConf().get(package$.MODULE$.MASTER_REST_SERVER_FILTERS())).foreach((filter) -> {
         $anonfun$addFilters$1(this, handler, filter);
         return BoxedUnit.UNIT;
      });
   }

   public void stop() {
      this._server().foreach((x$2) -> {
         $anonfun$stop$1(x$2);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$start$1(final RestSubmissionServer $this, final int startPort) {
      return $this.doStart(startPort);
   }

   // $FF: synthetic method
   public static final void $anonfun$doStart$3(final ServletContextHandler mainHandler$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String prefix = (String)x0$1._1();
         RestServlet servlet = (RestServlet)x0$1._2();
         mainHandler$1.addServlet(new ServletHolder(servlet), prefix);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$addFilters$2(final FilterHolder holder$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         holder$1.setInitParameter(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$addFilters$1(final RestSubmissionServer $this, final ServletContextHandler handler$1, final String filter) {
      Map params = .MODULE$.wrapRefArray((Object[])$this.masterConf().getAllWithPrefix("spark." + filter + ".param.")).toMap(scala..less.colon.less..MODULE$.refl());
      FilterHolder holder = new FilterHolder();
      holder.setClassName(filter);
      params.foreach((x0$1) -> {
         $anonfun$addFilters$2(holder, x0$1);
         return BoxedUnit.UNIT;
      });
      handler$1.addFilter(holder, "/*", EnumSet.allOf(DispatcherType.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final Server x$2) {
      x$2.stop();
   }

   public RestSubmissionServer(final String host, final int requestedPort, final SparkConf masterConf) {
      this.host = host;
      this.requestedPort = requestedPort;
      this.masterConf = masterConf;
      Logging.$init$(this);
      this._server = scala.None..MODULE$;
      this.baseContext = "/" + RestSubmissionServer$.MODULE$.PROTOCOL_VERSION() + "/submissions";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
