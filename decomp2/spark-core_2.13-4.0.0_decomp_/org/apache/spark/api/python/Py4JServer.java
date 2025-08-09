package org.apache.spark.api.python;

import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import py4j.ClientServer;
import py4j.GatewayServer;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005q3Q\u0001D\u0007\u0001#]A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006IA\n\u0005\u0006U\u0001!\ta\u000b\u0005\t_\u0001\u0011\r\u0011\"\u0001\u0012a!1A\b\u0001Q\u0001\nEBq!\u0010\u0001C\u0002\u0013%a\b\u0003\u0004H\u0001\u0001\u0006Ia\u0010\u0005\t\u0011\u0002\u0011\r\u0011\"\u0001\u0012\u0013\"1\u0001\u000b\u0001Q\u0001\n)CQ!\u0015\u0001\u0005\u0002ICQA\u0016\u0001\u0005\u0002]CQa\u0017\u0001\u0005\u0002I\u0013!\u0002U=5\u0015N+'O^3s\u0015\tqq\"\u0001\u0004qsRDwN\u001c\u0006\u0003!E\t1!\u00199j\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7c\u0001\u0001\u0019=A\u0011\u0011\u0004H\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t1\u0011I\\=SK\u001a\u0004\"a\b\u0012\u000e\u0003\u0001R!!I\t\u0002\u0011%tG/\u001a:oC2L!a\t\u0011\u0003\u000f1{wmZ5oO\u0006I1\u000f]1sW\u000e{gNZ\u0002\u0001!\t9\u0003&D\u0001\u0012\u0013\tI\u0013CA\u0005Ta\u0006\u00148nQ8oM\u00061A(\u001b8jiz\"\"\u0001\f\u0018\u0011\u00055\u0002Q\"A\u0007\t\u000b\u0011\u0012\u0001\u0019\u0001\u0014\u0002\rM,7M]3u+\u0005\t\u0004C\u0001\u001a:\u001d\t\u0019t\u0007\u0005\u0002555\tQG\u0003\u00027K\u00051AH]8pizJ!\u0001\u000f\u000e\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003qi\tqa]3de\u0016$\b%A\u0005m_\u000e\fG\u000e[8tiV\tq\b\u0005\u0002A\u000b6\t\u0011I\u0003\u0002C\u0007\u0006\u0019a.\u001a;\u000b\u0003\u0011\u000bAA[1wC&\u0011a)\u0011\u0002\f\u0013:,G/\u00113ee\u0016\u001c8/\u0001\u0006m_\u000e\fG\u000e[8ti\u0002\naa]3sm\u0016\u0014X#\u0001&\u0011\u0005-sU\"\u0001'\u000b\u00055\u001b\u0015\u0001\u00027b]\u001eL!a\u0014'\u0003\r=\u0013'.Z2u\u0003\u001d\u0019XM\u001d<fe\u0002\nQa\u001d;beR$\u0012a\u0015\t\u00033QK!!\u0016\u000e\u0003\tUs\u0017\u000e^\u0001\u0011O\u0016$H*[:uK:Lgn\u001a)peR,\u0012\u0001\u0017\t\u00033eK!A\u0017\u000e\u0003\u0007%sG/\u0001\u0005tQV$Hm\\<o\u0001"
)
public class Py4JServer implements Logging {
   private final String secret;
   private final InetAddress localhost;
   private final Object server;
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

   public String secret() {
      return this.secret;
   }

   private InetAddress localhost() {
      return this.localhost;
   }

   public Object server() {
      return this.server;
   }

   public void start() {
      Object var2 = this.server();
      if (var2 instanceof ClientServer var3) {
         var3.startServer();
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else if (var2 instanceof GatewayServer var4) {
         var4.start();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw SparkCoreErrors$.MODULE$.unexpectedPy4JServerError(var2);
      }
   }

   public int getListeningPort() {
      Object var2 = this.server();
      if (var2 instanceof ClientServer var3) {
         return var3.getJavaServer().getListeningPort();
      } else if (var2 instanceof GatewayServer var4) {
         return var4.getListeningPort();
      } else {
         throw SparkCoreErrors$.MODULE$.unexpectedPy4JServerError(var2);
      }
   }

   public void shutdown() {
      Object var2 = this.server();
      if (var2 instanceof ClientServer var3) {
         var3.shutdown();
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else if (var2 instanceof GatewayServer var4) {
         var4.shutdown();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw SparkCoreErrors$.MODULE$.unexpectedPy4JServerError(var2);
      }
   }

   public Py4JServer(final SparkConf sparkConf) {
      Object var3;
      label17: {
         label16: {
            super();
            Logging.$init$(this);
            this.secret = Utils$.MODULE$.createSecret(sparkConf);
            this.localhost = InetAddress.getLoopbackAddress();
            String var10001 = ((String).MODULE$.env().getOrElse("PYSPARK_PIN_THREAD", () -> "true")).toLowerCase(Locale.ROOT);
            String var2 = "true";
            if (var10001 == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (var10001.equals(var2)) {
               break label16;
            }

            var3 = (new GatewayServer.GatewayServerBuilder()).authToken(this.secret()).javaPort(0).javaAddress(this.localhost()).callbackClient(25334, this.localhost(), this.secret()).build();
            break label17;
         }

         var3 = (new ClientServer.ClientServerBuilder()).authToken(this.secret()).javaPort(0).javaAddress(this.localhost()).build();
      }

      this.server = var3;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
