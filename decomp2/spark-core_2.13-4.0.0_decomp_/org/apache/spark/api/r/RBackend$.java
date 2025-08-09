package org.apache.spark.api.r;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.R$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.runtime.BoxesRunTime;

public final class RBackend$ implements Logging {
   public static final RBackend$ MODULE$ = new RBackend$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      MODULE$.initializeLogIfNecessary(true);
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public void main(final String[] args) {
      if (args.length < 1) {
         System.err.println("Usage: RBackend <tempFilePath>");
         System.exit(-1);
      }

      RBackend sparkRBackend = new RBackend();

      try {
         Tuple2 var5 = sparkRBackend.init();
         if (var5 == null) {
            throw new MatchError(var5);
         }

         int boundPort = var5._1$mcI$sp();
         RAuthHelper authHelper = (RAuthHelper)var5._2();
         Tuple2 var4 = new Tuple2(BoxesRunTime.boxToInteger(boundPort), authHelper);
         int boundPort = var4._1$mcI$sp();
         RAuthHelper authHelper = (RAuthHelper)var4._2();
         ServerSocket serverSocket = new ServerSocket(0, 1, InetAddress.getByName("localhost"));
         int listenPort = serverSocket.getLocalPort();
         SparkConf conf = (SparkConf).MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$3) -> x$3.conf()).getOrElse(() -> new SparkConf());
         int backendConnectionTimeout = BoxesRunTime.unboxToInt(conf.get(R$.MODULE$.R_BACKEND_CONNECTION_TIMEOUT()));
         String path = args[0];
         File f = new File(path + ".tmp");
         DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
         dos.writeInt(boundPort);
         dos.writeInt(listenPort);
         SerDe$.MODULE$.writeString(dos, (String)RUtils$.MODULE$.rPackages().getOrElse(() -> ""));
         dos.writeInt(backendConnectionTimeout);
         SerDe$.MODULE$.writeString(dos, authHelper.secret());
         dos.close();
         f.renameTo(new File(path));
         (new Thread(serverSocket, authHelper, sparkRBackend) {
            private final ServerSocket serverSocket$1;
            private final RAuthHelper authHelper$2;
            private final RBackend sparkRBackend$1;

            public void run() {
               byte[] buf = new byte[1024];
               this.serverSocket$1.setSoTimeout(10000);

               try {
                  int remainingAttempts = 10;
                  Socket inSocket = null;

                  while(inSocket == null) {
                     inSocket = this.serverSocket$1.accept();

                     try {
                        this.authHelper$2.authClient(inSocket);
                     } catch (Exception var9) {
                        --remainingAttempts;
                        if (remainingAttempts == 0) {
                           String msg = "Too many failed authentication attempts.";
                           RBackend$.MODULE$.logError((Function0)(() -> msg));
                           throw new IllegalStateException(msg);
                        }

                        RBackend$.MODULE$.logInfo((Function0)(() -> "Client connection failed authentication."));
                        inSocket = null;
                     }
                  }

                  this.serverSocket$1.close();
                  inSocket.getInputStream().read(buf);
               } finally {
                  this.serverSocket$1.close();
                  this.sparkRBackend$1.close();
                  System.exit(0);
               }

            }

            public {
               this.serverSocket$1 = serverSocket$1;
               this.authHelper$2 = authHelper$2;
               this.sparkRBackend$1 = sparkRBackend$1;
               this.setDaemon(true);
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }).start();
         sparkRBackend.run();
      } catch (IOException var18) {
         this.logError((Function0)(() -> "Server shutting down: failed with exception "), var18);
         sparkRBackend.close();
         System.exit(1);
      }

      System.exit(0);
   }

   private RBackend$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
