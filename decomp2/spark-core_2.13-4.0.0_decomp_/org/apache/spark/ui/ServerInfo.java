package org.apache.spark.ui;

import jakarta.servlet.DispatcherType;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.EnumSet;
import java.util.Map;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.ContextHandlerCollection;
import org.sparkproject.jetty.server.handler.gzip.GzipHandler;
import org.sparkproject.jetty.servlet.FilterHolder;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.thread.QueuedThreadPool;
import org.sparkproject.jetty.util.thread.ThreadPool;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t=a!\u0002\u0015*\u0001.\n\u0004\u0002\u0003(\u0001\u0005+\u0007I\u0011A(\t\u0011e\u0003!\u0011#Q\u0001\nAC\u0001B\u0017\u0001\u0003\u0016\u0004%\ta\u0017\u0005\t?\u0002\u0011\t\u0012)A\u00059\"A\u0001\r\u0001BK\u0002\u0013\u0005\u0011\r\u0003\u0005f\u0001\tE\t\u0015!\u0003c\u0011!1\u0007A!b\u0001\n\u00139\u0007\u0002\u00037\u0001\u0005#\u0005\u000b\u0011\u00025\t\u00115\u0004!Q1A\u0005\n9D\u0001\"\u001e\u0001\u0003\u0012\u0003\u0006Ia\u001c\u0005\u0006m\u0002!\ta\u001e\u0005\u0007\u007f\u0002!\t!!\u0001\t\u000f\u0005\u0005\u0002\u0001\"\u0001\u0002$!9\u0011q\u0005\u0001\u0005\u0002\u0005%\u0002bBA\u0016\u0001\u0011%\u0011Q\u0006\u0005\n\u0003g\u0001\u0011\u0011!C\u0001\u0003kA\u0011\"!\u0011\u0001#\u0003%\t!a\u0011\t\u0013\u0005e\u0003!%A\u0005\u0002\u0005m\u0003\"CA0\u0001E\u0005I\u0011AA1\u0011%\t)\u0007AI\u0001\n\u0003\t9\u0007C\u0005\u0002l\u0001\t\n\u0011\"\u0001\u0002n!A\u0011\u0011\u000f\u0001\f\u0002\u0013\u0005q\r\u0003\u0005\u0002t\u0001Y\t\u0011\"\u0001o\u0011%\t)\bAA\u0001\n\u0003\n9\b\u0003\u0005\u0002\n\u0002\t\t\u0011\"\u0001\\\u0011%\tY\tAA\u0001\n\u0003\ti\tC\u0005\u0002\u001a\u0002\t\t\u0011\"\u0011\u0002\u001c\"I\u0011\u0011\u0016\u0001\u0002\u0002\u0013\u0005\u00111\u0016\u0005\n\u0003k\u0003\u0011\u0011!C!\u0003oC\u0011\"a/\u0001\u0003\u0003%\t%!0\t\u0013\u0005}\u0006!!A\u0005B\u0005\u0005\u0007\"CAb\u0001\u0005\u0005I\u0011IAc\u000f)\tI-KA\u0001\u0012\u0003Y\u00131\u001a\u0004\nQ%\n\t\u0011#\u0001,\u0003\u001bDaA\u001e\u0012\u0005\u0002\u0005\u0015\b\"CA`E\u0005\u0005IQIAa\u0011%\t9OIA\u0001\n\u0003\u000bI\u000fC\u0005\u0002v\n\n\t\u0011\"!\u0002x\"I!Q\u0001\u0012\u0002\u0002\u0013%!q\u0001\u0002\u000b'\u0016\u0014h/\u001a:J]\u001a|'B\u0001\u0016,\u0003\t)\u0018N\u0003\u0002-[\u0005)1\u000f]1sW*\u0011afL\u0001\u0007CB\f7\r[3\u000b\u0003A\n1a\u001c:h'\u0015\u0001!\u0007\u000f B!\t\u0019d'D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0019\te.\u001f*fMB\u0011\u0011\bP\u0007\u0002u)\u00111hK\u0001\tS:$XM\u001d8bY&\u0011QH\u000f\u0002\b\u0019><w-\u001b8h!\t\u0019t(\u0003\u0002Ai\t9\u0001K]8ek\u000e$\bC\u0001\"L\u001d\t\u0019\u0015J\u0004\u0002E\u00116\tQI\u0003\u0002G\u000f\u00061AH]8piz\u001a\u0001!C\u00016\u0013\tQE'A\u0004qC\u000e\\\u0017mZ3\n\u00051k%\u0001D*fe&\fG.\u001b>bE2,'B\u0001&5\u0003\u0019\u0019XM\u001d<feV\t\u0001\u000b\u0005\u0002R/6\t!K\u0003\u0002O'*\u0011A+V\u0001\u0006U\u0016$H/\u001f\u0006\u0003->\nq!Z2mSB\u001cX-\u0003\u0002Y%\n11+\u001a:wKJ\fqa]3sm\u0016\u0014\b%A\u0005c_VtG\rU8siV\tA\f\u0005\u00024;&\u0011a\f\u000e\u0002\u0004\u0013:$\u0018A\u00032pk:$\u0007k\u001c:uA\u0005Q1/Z2ve\u0016\u0004vN\u001d;\u0016\u0003\t\u00042aM2]\u0013\t!GG\u0001\u0004PaRLwN\\\u0001\fg\u0016\u001cWO]3Q_J$\b%\u0001\u0003d_:4W#\u00015\u0011\u0005%TW\"A\u0016\n\u0005-\\#!C*qCJ\\7i\u001c8g\u0003\u0015\u0019wN\u001c4!\u0003-\u0011xn\u001c;IC:$G.\u001a:\u0016\u0003=\u0004\"\u0001]:\u000e\u0003ET!A\u001d*\u0002\u000f!\fg\u000e\u001a7fe&\u0011A/\u001d\u0002\u0019\u0007>tG/\u001a=u\u0011\u0006tG\r\\3s\u0007>dG.Z2uS>t\u0017\u0001\u0004:p_RD\u0015M\u001c3mKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0004yundXP \t\u0003s\u0002i\u0011!\u000b\u0005\u0006\u001d.\u0001\r\u0001\u0015\u0005\u00065.\u0001\r\u0001\u0018\u0005\u0006A.\u0001\rA\u0019\u0005\u0006M.\u0001\r\u0001\u001b\u0005\u0006[.\u0001\ra\\\u0001\u000bC\u0012$\u0007*\u00198eY\u0016\u0014HCBA\u0002\u0003\u0013\t9\u0002E\u00024\u0003\u000bI1!a\u00025\u0005\u0011)f.\u001b;\t\rId\u0001\u0019AA\u0006!\u0011\ti!a\u0005\u000e\u0005\u0005=!bAA\t'\u000691/\u001a:wY\u0016$\u0018\u0002BA\u000b\u0003\u001f\u0011QcU3sm2,GoQ8oi\u0016DH\u000fS1oI2,'\u000fC\u0004\u0002\u001a1\u0001\r!a\u0007\u0002\u0017M,7-\u001e:jifluM\u001d\t\u0004S\u0006u\u0011bAA\u0010W\ty1+Z2ve&$\u00180T1oC\u001e,'/A\u0007sK6|g/\u001a%b]\u0012dWM\u001d\u000b\u0005\u0003\u0007\t)\u0003\u0003\u0004s\u001b\u0001\u0007\u00111B\u0001\u0005gR|\u0007\u000f\u0006\u0002\u0002\u0004\u0005Q\u0011\r\u001a3GS2$XM]:\u0015\r\u0005\r\u0011qFA\u0019\u0011\u0019\u0011x\u00021\u0001\u0002\f!9\u0011\u0011D\bA\u0002\u0005m\u0011\u0001B2paf$2\u0002_A\u001c\u0003s\tY$!\u0010\u0002@!9a\n\u0005I\u0001\u0002\u0004\u0001\u0006b\u0002.\u0011!\u0003\u0005\r\u0001\u0018\u0005\bAB\u0001\n\u00111\u0001c\u0011\u001d1\u0007\u0003%AA\u0002!Dq!\u001c\t\u0011\u0002\u0003\u0007q.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0015#f\u0001)\u0002H-\u0012\u0011\u0011\n\t\u0005\u0003\u0017\n)&\u0004\u0002\u0002N)!\u0011qJA)\u0003%)hn\u00195fG.,GMC\u0002\u0002TQ\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9&!\u0014\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005u#f\u0001/\u0002H\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA2U\r\u0011\u0017qI\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\tIGK\u0002i\u0003\u000f\nabY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0002p)\u001aq.a\u0012\u0002\u001b\r|gN\u001a\u0013bG\u000e,7o\u001d\u00134\u0003Q\u0011xn\u001c;IC:$G.\u001a:%C\u000e\u001cWm]:%i\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u001f\u0011\t\u0005m\u0014QQ\u0007\u0003\u0003{RA!a \u0002\u0002\u0006!A.\u00198h\u0015\t\t\u0019)\u0001\u0003kCZ\f\u0017\u0002BAD\u0003{\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u001f\u000b)\nE\u00024\u0003#K1!a%5\u0005\r\te.\u001f\u0005\t\u0003/S\u0012\u0011!a\u00019\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!(\u0011\r\u0005}\u0015QUAH\u001b\t\t\tKC\u0002\u0002$R\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9+!)\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003[\u000b\u0019\fE\u00024\u0003_K1!!-5\u0005\u001d\u0011un\u001c7fC:D\u0011\"a&\u001d\u0003\u0003\u0005\r!a$\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003s\nI\f\u0003\u0005\u0002\u0018v\t\t\u00111\u0001]\u0003!A\u0017m\u001d5D_\u0012,G#\u0001/\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u001f\u0002\r\u0015\fX/\u00197t)\u0011\ti+a2\t\u0013\u0005]\u0005%!AA\u0002\u0005=\u0015AC*feZ,'/\u00138g_B\u0011\u0011PI\n\u0006E\u0005=\u00171\u001c\t\u000b\u0003#\f9\u000e\u0015/cQ>DXBAAj\u0015\r\t)\u000eN\u0001\beVtG/[7f\u0013\u0011\tI.a5\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tW\u0007\u0005\u0003\u0002^\u0006\rXBAAp\u0015\u0011\t\t/!!\u0002\u0005%|\u0017b\u0001'\u0002`R\u0011\u00111Z\u0001\u0006CB\u0004H.\u001f\u000b\fq\u0006-\u0018Q^Ax\u0003c\f\u0019\u0010C\u0003OK\u0001\u0007\u0001\u000bC\u0003[K\u0001\u0007A\fC\u0003aK\u0001\u0007!\rC\u0003gK\u0001\u0007\u0001\u000eC\u0003nK\u0001\u0007q.A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005e(\u0011\u0001\t\u0005g\r\fY\u0010\u0005\u00054\u0003{\u0004FL\u00195p\u0013\r\ty\u0010\u000e\u0002\u0007)V\u0004H.Z\u001b\t\u0011\t\ra%!AA\u0002a\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011I\u0001\u0005\u0003\u0002|\t-\u0011\u0002\u0002B\u0007\u0003{\u0012aa\u00142kK\u000e$\b"
)
public class ServerInfo implements Logging, Product, Serializable {
   private final Server server;
   private final int boundPort;
   private final Option securePort;
   private final SparkConf org$apache$spark$ui$ServerInfo$$conf;
   private final ContextHandlerCollection org$apache$spark$ui$ServerInfo$$rootHandler;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option unapply(final ServerInfo x$0) {
      return ServerInfo$.MODULE$.unapply(x$0);
   }

   public static ServerInfo apply(final Server server, final int boundPort, final Option securePort, final SparkConf conf, final ContextHandlerCollection rootHandler) {
      return ServerInfo$.MODULE$.apply(server, boundPort, securePort, conf, rootHandler);
   }

   public static Function1 tupled() {
      return ServerInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ServerInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public SparkConf conf$access$3() {
      return this.org$apache$spark$ui$ServerInfo$$conf;
   }

   public ContextHandlerCollection rootHandler$access$4() {
      return this.org$apache$spark$ui$ServerInfo$$rootHandler;
   }

   public Server server() {
      return this.server;
   }

   public int boundPort() {
      return this.boundPort;
   }

   public Option securePort() {
      return this.securePort;
   }

   public SparkConf org$apache$spark$ui$ServerInfo$$conf() {
      return this.org$apache$spark$ui$ServerInfo$$conf;
   }

   public ContextHandlerCollection org$apache$spark$ui$ServerInfo$$rootHandler() {
      return this.org$apache$spark$ui$ServerInfo$$rootHandler;
   }

   public synchronized void addHandler(final ServletContextHandler handler, final SecurityManager securityMgr) {
      handler.setVirtualHosts(JettyUtils$.MODULE$.toVirtualHosts(.MODULE$.wrapRefArray((Object[])(new String[]{JettyUtils$.MODULE$.SPARK_CONNECTOR_NAME()}))));
      this.addFilters(handler, securityMgr);
      GzipHandler gzipHandler = new GzipHandler();
      gzipHandler.setHandler(handler);
      this.org$apache$spark$ui$ServerInfo$$rootHandler().addHandler(gzipHandler);
      if (!handler.isStarted()) {
         handler.start();
      }

      gzipHandler.start();
   }

   public synchronized void removeHandler(final ServletContextHandler handler) {
      scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.org$apache$spark$ui$ServerInfo$$rootHandler().getHandlers()), (h) -> BoxesRunTime.boxToBoolean($anonfun$removeHandler$1(handler, h))).foreach((h) -> {
         $anonfun$removeHandler$2(this, h);
         return BoxedUnit.UNIT;
      });
      if (handler.isStarted()) {
         handler.stop();
      }
   }

   public void stop() {
      ThreadPool threadPool = this.server().getThreadPool();
      if (threadPool instanceof QueuedThreadPool var4) {
         scala.util.Try..MODULE$.apply((JFunction0.mcV.sp)() -> var4.setIdleTimeout(0));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.server().stop();
      if (threadPool != null && threadPool instanceof LifeCycle) {
         ((LifeCycle)threadPool).stop();
      }
   }

   private void addFilters(final ServletContextHandler handler, final SecurityManager securityMgr) {
      ((IterableOnceOps)this.org$apache$spark$ui$ServerInfo$$conf().get(UI$.MODULE$.UI_FILTERS())).foreach((filter) -> {
         $anonfun$addFilters$1(this, handler, filter);
         return BoxedUnit.UNIT;
      });
      HttpSecurityFilter securityFilter = new HttpSecurityFilter(this.org$apache$spark$ui$ServerInfo$$conf(), securityMgr);
      FilterHolder holder = new FilterHolder(securityFilter);
      handler.addFilter(holder, "/*", EnumSet.allOf(DispatcherType.class));
   }

   public ServerInfo copy(final Server server, final int boundPort, final Option securePort, final SparkConf conf, final ContextHandlerCollection rootHandler) {
      return new ServerInfo(server, boundPort, securePort, conf, rootHandler);
   }

   public Server copy$default$1() {
      return this.server();
   }

   public int copy$default$2() {
      return this.boundPort();
   }

   public Option copy$default$3() {
      return this.securePort();
   }

   public SparkConf copy$default$4() {
      return this.org$apache$spark$ui$ServerInfo$$conf();
   }

   public ContextHandlerCollection copy$default$5() {
      return this.org$apache$spark$ui$ServerInfo$$rootHandler();
   }

   public String productPrefix() {
      return "ServerInfo";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.server();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.boundPort());
         }
         case 2 -> {
            return this.securePort();
         }
         case 3 -> {
            return this.conf$access$3();
         }
         case 4 -> {
            return this.rootHandler$access$4();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ServerInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "server";
         }
         case 1 -> {
            return "boundPort";
         }
         case 2 -> {
            return "securePort";
         }
         case 3 -> {
            return "conf";
         }
         case 4 -> {
            return "rootHandler";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.server()));
      var1 = Statics.mix(var1, this.boundPort());
      var1 = Statics.mix(var1, Statics.anyHash(this.securePort()));
      var1 = Statics.mix(var1, Statics.anyHash(this.conf$access$3()));
      var1 = Statics.mix(var1, Statics.anyHash(this.rootHandler$access$4()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof ServerInfo) {
               ServerInfo var4 = (ServerInfo)x$1;
               if (this.boundPort() == var4.boundPort()) {
                  label68: {
                     Server var10000 = this.server();
                     Server var5 = var4.server();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     Option var9 = this.securePort();
                     Option var6 = var4.securePort();
                     if (var9 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var9.equals(var6)) {
                        break label68;
                     }

                     SparkConf var10 = this.conf$access$3();
                     SparkConf var7 = var4.conf$access$3();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var10.equals(var7)) {
                        break label68;
                     }

                     ContextHandlerCollection var11 = this.rootHandler$access$4();
                     ContextHandlerCollection var8 = var4.rootHandler$access$4();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label68;
                        }
                     } else if (!var11.equals(var8)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeHandler$1(final ServletContextHandler handler$1, final Handler h) {
      boolean var3;
      label25: {
         if (h instanceof GzipHandler) {
            Handler var10000 = ((GzipHandler)h).getHandler();
            if (var10000 == null) {
               if (handler$1 == null) {
                  break label25;
               }
            } else if (var10000.equals(handler$1)) {
               break label25;
            }
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$removeHandler$2(final ServerInfo $this, final Handler h) {
      $this.org$apache$spark$ui$ServerInfo$$rootHandler().removeHandler(h);
      h.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$addFilters$1(final ServerInfo $this, final ServletContextHandler handler$2, final String filter) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Adding filter to"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" ", ":"})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVLET_CONTEXT_HANDLER_PATH..MODULE$, handler$2.getContextPath())})))).$plus($this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UI_FILTER..MODULE$, filter)}))))));
      scala.collection.immutable.Map oldParams = ((IterableOnceOps)((IterableOps)scala.Option..MODULE$.option2Iterable($this.org$apache$spark$ui$ServerInfo$$conf().getOption("spark." + filter + ".params")).toSeq().flatMap((str) -> Utils$.MODULE$.stringToSeq(str))).flatMap((param) -> {
         String[] parts = param.split("=");
         return (Option)(parts.length == 2 ? new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(parts[0]), parts[1])) : scala.None..MODULE$);
      })).toMap(scala..less.colon.less..MODULE$.refl());
      scala.collection.immutable.Map newParams = scala.Predef..MODULE$.wrapRefArray((Object[])$this.org$apache$spark$ui$ServerInfo$$conf().getAllWithPrefix("spark." + filter + ".param.")).toMap(scala..less.colon.less..MODULE$.refl());
      JettyUtils$.MODULE$.addFilter(handler$2, filter, (scala.collection.immutable.Map)oldParams.$plus$plus(newParams));
   }

   public ServerInfo(final Server server, final int boundPort, final Option securePort, final SparkConf conf, final ContextHandlerCollection rootHandler) {
      this.server = server;
      this.boundPort = boundPort;
      this.securePort = securePort;
      this.org$apache$spark$ui$ServerInfo$$conf = conf;
      this.org$apache$spark$ui$ServerInfo$$rootHandler = rootHandler;
      Logging.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
