package org.apache.spark.api.r;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkFiles$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.R$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

public final class BaseRRunner$ {
   public static final BaseRRunner$ MODULE$ = new BaseRRunner$();
   private static RAuthHelper org$apache$spark$api$r$BaseRRunner$$authHelper;
   private static BufferedStreamThread errThread;
   private static DataOutputStream daemonChannel;
   private static volatile boolean bitmap$0;

   private RAuthHelper authHelper$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            SparkConf conf = (SparkConf).MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$6) -> x$6.conf()).getOrElse(() -> new SparkConf());
            org$apache$spark$api$r$BaseRRunner$$authHelper = new RAuthHelper(conf);
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return org$apache$spark$api$r$BaseRRunner$$authHelper;
   }

   public RAuthHelper org$apache$spark$api$r$BaseRRunner$$authHelper() {
      return !bitmap$0 ? this.authHelper$lzycompute() : org$apache$spark$api$r$BaseRRunner$$authHelper;
   }

   private BufferedStreamThread startStdoutThread(final Process proc) {
      int BUFFER_SIZE = 100;
      BufferedStreamThread thread = new BufferedStreamThread(proc.getInputStream(), "stdout reader for R", BUFFER_SIZE);
      thread.setDaemon(true);
      thread.start();
      return thread;
   }

   public String getROptions(final String rCommand) {
      return (String)scala.util.Try..MODULE$.apply(() -> {
         String result = scala.sys.process.Process..MODULE$.apply(new scala.collection.immutable..colon.colon(rCommand, new scala.collection.immutable..colon.colon("--version", scala.collection.immutable.Nil..MODULE$))).$bang$bang();
         return (String)scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([0-9]+)\\.([0-9]+)\\.([0-9]+)")).findFirstMatchIn(result).map((m) -> {
            int major = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(m.group(1)));
            int minor = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(m.group(2)));
            boolean shouldUseNoRestore = major > 4 || major == 4 && minor >= 2;
            return shouldUseNoRestore ? "--no-restore" : "--vanilla";
         }).getOrElse(() -> "--vanilla");
      }).getOrElse(() -> "--vanilla");
   }

   private BufferedStreamThread createRProcess(final int port, final String script) {
      SparkConf sparkConf = SparkEnv$.MODULE$.get().conf();
      ObjectRef rCommand = ObjectRef.create((String)sparkConf.get(R$.MODULE$.SPARKR_COMMAND()));
      rCommand.elem = (String)((Option)sparkConf.get((ConfigEntry)R$.MODULE$.R_COMMAND())).orElse(() -> new Some((String)rCommand.elem)).get();
      int rConnectionTimeout = BoxesRunTime.unboxToInt(sparkConf.get(R$.MODULE$.R_BACKEND_CONNECTION_TIMEOUT()));
      String rOptions = this.getROptions((String)rCommand.elem);
      Seq rLibDir = RUtils$.MODULE$.sparkRPackagePath(false);
      String var10000 = (String)rLibDir.apply(0);
      String rExecScript = var10000 + "/SparkR/worker/" + script;
      ProcessBuilder pb = new ProcessBuilder(Arrays.asList((Object[])(new String[]{(String)rCommand.elem, rOptions, rExecScript})));
      pb.environment().put("R_TESTS", "");
      pb.environment().put("SPARKR_RLIBDIR", rLibDir.mkString(","));
      pb.environment().put("SPARKR_WORKER_PORT", Integer.toString(port));
      pb.environment().put("SPARKR_BACKEND_CONNECTION_TIMEOUT", Integer.toString(rConnectionTimeout));
      pb.environment().put("SPARKR_SPARKFILES_ROOT_DIR", SparkFiles$.MODULE$.getRootDirectory());
      pb.environment().put("SPARKR_IS_RUNNING_ON_WORKER", "TRUE");
      pb.environment().put("SPARKR_WORKER_SECRET", this.org$apache$spark$api$r$BaseRRunner$$authHelper().secret());
      pb.redirectErrorStream(true);
      Process proc = pb.start();
      BufferedStreamThread errThread = this.startStdoutThread(proc);
      return errThread;
   }

   public BufferedStreamThread createRWorker(final int port) {
      boolean useDaemon = SparkEnv$.MODULE$.get().conf().getBoolean("spark.sparkr.use.daemon", true);
      if (!Utils$.MODULE$.isWindows() && useDaemon) {
         synchronized(this){}

         BufferedStreamThread var4;
         try {
            if (daemonChannel == null) {
               ServerSocket serverSocket = new ServerSocket(0, 1, InetAddress.getByName("localhost"));
               int daemonPort = serverSocket.getLocalPort();
               errThread = this.createRProcess(daemonPort, "daemon.R");
               serverSocket.setSoTimeout(10000);
               Socket sock = serverSocket.accept();

               try {
                  this.org$apache$spark$api$r$BaseRRunner$$authHelper().authClient(sock);
                  daemonChannel = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
               } finally {
                  serverSocket.close();
               }
            }

            this.liftedTree1$1(port);
            var4 = errThread;
         } catch (Throwable var14) {
            throw var14;
         }

         return var4;
      } else {
         return this.createRProcess(port, "worker.R");
      }
   }

   // $FF: synthetic method
   private final void liftedTree1$1(final int port$1) {
      try {
         daemonChannel.writeInt(port$1);
         daemonChannel.flush();
      } catch (IOException var3) {
         daemonChannel.close();
         daemonChannel = null;
         errThread = null;
         throw var3;
      }
   }

   private BaseRRunner$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
