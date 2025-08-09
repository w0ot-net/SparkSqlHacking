package org.apache.spark.security;

import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.Python$;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.concurrent.Awaitable;
import scala.concurrent.Promise;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rcAB\u000b\u0017\u0003\u0003Ab\u0004\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003/\u0011!\u0011\u0004A!A!\u0002\u0013\u0019\u0004\"\u0002 \u0001\t\u0003y\u0004\"\u0002 \u0001\t\u0003q\u0005\"\u0002 \u0001\t\u00031\u0006b\u0002-\u0001\u0005\u0004%I!\u0017\u0005\u0007A\u0002\u0001\u000b\u0011\u0002.\t\u000b\u0005\u0004A\u0011\u00022\t\u0015%\u0004\u0001\u0013!A\u0002B\u0003%1\rC\u0004k\u0001\t\u0007I\u0011A6\t\r1\u0004\u0001\u0015!\u0003g\u0011\u001di\u0007A1A\u0005\u00029Daa\u001c\u0001!\u0002\u0013\u0019\u0004\"\u00029\u0001\r\u0003\t\b\"\u0002?\u0001\t\u0003i\b\"\u0002?\u0001\t\u0003qx\u0001CA\b-!\u0005\u0001$!\u0005\u0007\u000fU1\u0002\u0012\u0001\r\u0002\u0014!1aH\u0005C\u0001\u0003+Aq!a\u0006\u0013\t\u0003\tIB\u0001\tT_\u000e\\W\r^!vi\"\u001cVM\u001d<fe*\u0011q\u0003G\u0001\tg\u0016\u001cWO]5us*\u0011\u0011DG\u0001\u0006gB\f'o\u001b\u0006\u00037q\ta!\u00199bG\",'\"A\u000f\u0002\u0007=\u0014x-\u0006\u0002 \u0007N\u0019\u0001\u0001\t\u0014\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\r\u0005s\u0017PU3g!\t9#&D\u0001)\u0015\tI\u0003$\u0001\u0005j]R,'O\\1m\u0013\tY\u0003FA\u0004M_\u001e<\u0017N\\4\u0002\u0015\u0005,H\u000f\u001b%fYB,'o\u0001\u0001\u0011\u0005=\u0002T\"\u0001\f\n\u0005E2\"\u0001E*pG.,G/Q;uQ\"+G\u000e]3s\u0003)!\bN]3bI:\u000bW.\u001a\t\u0003imr!!N\u001d\u0011\u0005Y\u0012S\"A\u001c\u000b\u0005aj\u0013A\u0002\u001fs_>$h(\u0003\u0002;E\u00051\u0001K]3eK\u001aL!\u0001P\u001f\u0003\rM#(/\u001b8h\u0015\tQ$%\u0001\u0004=S:LGO\u0010\u000b\u0004\u00012k\u0005cA\u0018\u0001\u0003B\u0011!i\u0011\u0007\u0001\t\u0015!\u0005A1\u0001F\u0005\u0005!\u0016C\u0001$J!\t\ts)\u0003\u0002IE\t9aj\u001c;iS:<\u0007CA\u0011K\u0013\tY%EA\u0002B]fDQ\u0001L\u0002A\u00029BQAM\u0002A\u0002M\"2\u0001Q(V\u0011\u0015\u0001F\u00011\u0001R\u0003\r)gN\u001e\t\u0003%Nk\u0011\u0001G\u0005\u0003)b\u0011\u0001b\u00159be.,eN\u001e\u0005\u0006e\u0011\u0001\ra\r\u000b\u0003\u0001^CQAM\u0003A\u0002M\nq\u0001\u001d:p[&\u001cX-F\u0001[!\rYf,Q\u0007\u00029*\u0011QLI\u0001\u000bG>t7-\u001e:sK:$\u0018BA0]\u0005\u001d\u0001&o\\7jg\u0016\f\u0001\u0002\u001d:p[&\u001cX\rI\u0001\fgR\f'\u000f^*feZ,'\u000fF\u0001d!\u0011\tCMZ\u001a\n\u0005\u0015\u0014#A\u0002+va2,'\u0007\u0005\u0002\"O&\u0011\u0001N\t\u0002\u0004\u0013:$\u0018a\u0001=%c\u0005!\u0001o\u001c:u+\u00051\u0017!\u00029peR\u0004\u0013AB:fGJ,G/F\u00014\u0003\u001d\u0019Xm\u0019:fi\u0002\n\u0001\u0003[1oI2,7i\u001c8oK\u000e$\u0018n\u001c8\u0015\u0005\u0005\u0013\b\"B:\u000f\u0001\u0004!\u0018\u0001B:pG.\u0004\"!\u001e>\u000e\u0003YT!a\u001e=\u0002\u00079,GOC\u0001z\u0003\u0011Q\u0017M^1\n\u0005m4(AB*pG.,G/A\u0005hKR\u0014Vm];miR\t\u0011\t\u0006\u0002B\u007f\"9\u0011\u0011\u0001\tA\u0002\u0005\r\u0011\u0001B<bSR\u0004B!!\u0002\u0002\f5\u0011\u0011q\u0001\u0006\u0004\u0003\u0013a\u0016\u0001\u00033ve\u0006$\u0018n\u001c8\n\t\u00055\u0011q\u0001\u0002\t\tV\u0014\u0018\r^5p]\u0006\u00012k\\2lKR\fU\u000f\u001e5TKJ4XM\u001d\t\u0003_I\u0019\"A\u0005\u0011\u0015\u0005\u0005E\u0011!D:feZ,Gk\\*ue\u0016\fW\u000e\u0006\u0004\u0002\u001c\u0005}\u0012\u0011\t\u000b\u0005\u0003;\t\u0019\u0003\u0005\u0003\"\u0003?I\u0015bAA\u0011E\t)\u0011I\u001d:bs\"9\u0011Q\u0005\u000bA\u0002\u0005\u001d\u0012!C<sSR,g)\u001e8d!\u001d\t\u0013\u0011FA\u0017\u0003sI1!a\u000b#\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u00020\u0005URBAA\u0019\u0015\r\t\u0019\u0004_\u0001\u0003S>LA!a\u000e\u00022\taq*\u001e;qkR\u001cFO]3b[B\u0019\u0011%a\u000f\n\u0007\u0005u\"E\u0001\u0003V]&$\b\"\u0002\u001a\u0015\u0001\u0004\u0019\u0004\"\u0002\u0017\u0015\u0001\u0004q\u0003"
)
public abstract class SocketAuthServer implements Logging {
   public final SocketAuthHelper org$apache$spark$security$SocketAuthServer$$authHelper;
   public final String org$apache$spark$security$SocketAuthServer$$threadName;
   private final Promise org$apache$spark$security$SocketAuthServer$$promise;
   // $FF: synthetic field
   private final Tuple2 x$1;
   private final int port;
   private final String secret;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Object[] serveToStream(final String threadName, final SocketAuthHelper authHelper, final Function1 writeFunc) {
      return SocketAuthServer$.MODULE$.serveToStream(threadName, authHelper, writeFunc);
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

   public Promise org$apache$spark$security$SocketAuthServer$$promise() {
      return this.org$apache$spark$security$SocketAuthServer$$promise;
   }

   private Tuple2 startServer() {
      this.logTrace((Function0)(() -> "Creating listening socket"));
      InetAddress address = InetAddress.getLoopbackAddress();
      ServerSocket serverSocket = new ServerSocket(0, 1, address);
      int timeout = (int)BoxesRunTime.unboxToLong(this.org$apache$spark$security$SocketAuthServer$$authHelper.conf().get(Python$.MODULE$.PYTHON_AUTH_SOCKET_TIMEOUT()));
      this.logTrace((Function0)(() -> "Setting timeout to " + timeout + " sec"));
      serverSocket.setSoTimeout(timeout * 1000);
      (new Thread(address, serverSocket) {
         // $FF: synthetic field
         private final SocketAuthServer $outer;
         private final InetAddress address$1;
         private final ServerSocket serverSocket$1;

         public void run() {
            ObjectRef sock = ObjectRef.create((Object)null);

            try {
               this.$outer.logTrace((Function0)(() -> {
                  InetAddress var10000 = this.address$1;
                  return "Waiting for connection on " + var10000 + " with port " + this.serverSocket$1.getLocalPort();
               }));
               sock.elem = this.serverSocket$1.accept();
               this.$outer.logTrace((Function0)(() -> "Connection accepted from address " + ((Socket)sock.elem).getRemoteSocketAddress()));
               this.$outer.org$apache$spark$security$SocketAuthServer$$authHelper.authClient((Socket)sock.elem);
               this.$outer.logTrace((Function0)(() -> "Client authenticated"));
               this.$outer.org$apache$spark$security$SocketAuthServer$$promise().complete(.MODULE$.apply(() -> this.$outer.handleConnection((Socket)sock.elem)));
            } finally {
               this.$outer.logTrace((Function0)(() -> "Closing server"));
               JavaUtils.closeQuietly(this.serverSocket$1);
               JavaUtils.closeQuietly((Socket)sock.elem);
            }

         }

         public {
            if (SocketAuthServer.this == null) {
               throw null;
            } else {
               this.$outer = SocketAuthServer.this;
               this.address$1 = address$1;
               this.serverSocket$1 = serverSocket$1;
               this.setDaemon(true);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }).start();
      return new Tuple2(BoxesRunTime.boxToInteger(serverSocket.getLocalPort()), this.org$apache$spark$security$SocketAuthServer$$authHelper.secret());
   }

   public int port() {
      return this.port;
   }

   public String secret() {
      return this.secret;
   }

   public abstract Object handleConnection(final Socket sock);

   public Object getResult() {
      return this.getResult(scala.concurrent.duration.Duration..MODULE$.Inf());
   }

   public Object getResult(final Duration wait) {
      return ThreadUtils$.MODULE$.awaitResult((Awaitable)this.org$apache$spark$security$SocketAuthServer$$promise().future(), wait);
   }

   public SocketAuthServer(final SocketAuthHelper authHelper, final String threadName) {
      this.org$apache$spark$security$SocketAuthServer$$authHelper = authHelper;
      this.org$apache$spark$security$SocketAuthServer$$threadName = threadName;
      Logging.$init$(this);
      this.org$apache$spark$security$SocketAuthServer$$promise = scala.concurrent.Promise..MODULE$.apply();
      Tuple2 var4 = this.startServer();
      if (var4 != null) {
         int port = var4._1$mcI$sp();
         String secret = (String)var4._2();
         this.x$1 = new Tuple2(BoxesRunTime.boxToInteger(port), secret);
         this.port = this.x$1._1$mcI$sp();
         this.secret = (String)this.x$1._2();
      } else {
         throw new MatchError(var4);
      }
   }

   public SocketAuthServer(final SparkEnv env, final String threadName) {
      this(new SocketAuthHelper(env.conf()), threadName);
   }

   public SocketAuthServer(final String threadName) {
      this(SparkEnv$.MODULE$.get(), threadName);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
