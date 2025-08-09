package org.apache.spark.deploy;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.master.Master$;
import org.apache.spark.deploy.worker.Worker$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEnv;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c!B\u000f\u001f\u0001\u00012\u0003\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011a\u0002!\u0011!Q\u0001\nUB\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005w!)q\b\u0001C\u0005\u0001\"9q\t\u0001b\u0001\n\u0013A\u0005B\u0002+\u0001A\u0003%\u0011\nC\u0004V\u0001\t\u0007I\u0011\u0002,\t\r\u0015\u0004\u0001\u0015!\u0003X\u0011\u001d1\u0007A1A\u0005\nYCaa\u001a\u0001!\u0002\u00139\u0006b\u00025\u0001\u0001\u0004%\t!\u001b\u0005\bU\u0002\u0001\r\u0011\"\u0001l\u0011\u0019\t\b\u0001)Q\u0005k!9!\u000f\u0001b\u0001\n\u0013\u0019\bBB;\u0001A\u0003%A\u000fC\u0003w\u0001\u0011\u0005q\u000fC\u0003|\u0001\u0011\u0005A\u0010C\u0004\u0002\u001e\u0001!\t!a\b\b\u0011\u0005\u0005b\u0004#\u0001!\u0003G1q!\b\u0010\t\u0002\u0001\n)\u0003\u0003\u0004@+\u0011\u0005\u0011q\u0005\u0005\n\u0003S)\u0002\u0019!C\u0005\u0003WA\u0011\"a\r\u0016\u0001\u0004%I!!\u000e\t\u0011\u0005eR\u0003)Q\u0005\u0003[A\u0001\"a\u000f\u0016\t\u0003\u0001\u00131\u0006\u0005\b\u0003{)B\u0011BA\u0010\u0011\u001d\ty$\u0006C\u0001\u0003\u0003\u0012\u0011\u0003T8dC2\u001c\u0006/\u0019:l\u00072,8\u000f^3s\u0015\ty\u0002%\u0001\u0004eKBdw.\u001f\u0006\u0003C\t\nQa\u001d9be.T!a\t\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0013aA8sON\u0019\u0001aJ\u0017\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\r\u0005s\u0017PU3g!\tq\u0013'D\u00010\u0015\t\u0001\u0004%\u0001\u0005j]R,'O\\1m\u0013\t\u0011tFA\u0004M_\u001e<\u0017N\\4\u0002\u00159,XnV8sW\u0016\u00148o\u0001\u0001\u0011\u0005!2\u0014BA\u001c*\u0005\rIe\u000e^\u0001\u000fG>\u0014Xm\u001d)fe^{'o[3s\u0003=iW-\\8ssB+'oV8sW\u0016\u0014\u0018\u0001B2p]\u001a\u0004\"\u0001P\u001f\u000e\u0003\u0001J!A\u0010\u0011\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u0002\u001fj]&$h\bF\u0003B\u0007\u0012+e\t\u0005\u0002C\u00015\ta\u0004C\u00034\u000b\u0001\u0007Q\u0007C\u00039\u000b\u0001\u0007Q\u0007C\u0003:\u000b\u0001\u0007Q\u0007C\u0003;\u000b\u0001\u00071(A\u0007m_\u000e\fG\u000eS8ti:\fW.Z\u000b\u0002\u0013B\u0011!*\u0015\b\u0003\u0017>\u0003\"\u0001T\u0015\u000e\u00035S!A\u0014\u001b\u0002\rq\u0012xn\u001c;?\u0013\t\u0001\u0016&\u0001\u0004Qe\u0016$WMZ\u0005\u0003%N\u0013aa\u0015;sS:<'B\u0001)*\u00039awnY1m\u0011>\u001cHO\\1nK\u0002\nQ\"\\1ti\u0016\u0014(\u000b]2F]Z\u001cX#A,\u0011\u0007akv,D\u0001Z\u0015\tQ6,A\u0004nkR\f'\r\\3\u000b\u0005qK\u0013AC2pY2,7\r^5p]&\u0011a,\u0017\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u0002aG6\t\u0011M\u0003\u0002cA\u0005\u0019!\u000f]2\n\u0005\u0011\f'A\u0002*qG\u0016sg/\u0001\bnCN$XM\u001d*qG\u0016sgo\u001d\u0011\u0002\u001b]|'o[3s%B\u001cWI\u001c<t\u000399xN]6feJ\u00038-\u00128wg\u0002\nq\"\\1ti\u0016\u0014x+\u001a2V\u0013B{'\u000f^\u000b\u0002k\u0005\u0019R.Y:uKJ<VMY+J!>\u0014Ho\u0018\u0013fcR\u0011An\u001c\t\u0003Q5L!A\\\u0015\u0003\tUs\u0017\u000e\u001e\u0005\ba6\t\t\u00111\u00016\u0003\rAH%M\u0001\u0011[\u0006\u001cH/\u001a:XK\n,\u0016\nU8si\u0002\n!b^8sW\u0016\u0014H)\u001b:t+\u0005!\bc\u0001-^\u0013\u0006Yqo\u001c:lKJ$\u0015N]:!\u0003\u0015\u0019H/\u0019:u)\u0005A\bc\u0001\u0015z\u0013&\u0011!0\u000b\u0002\u0006\u0003J\u0014\u0018-_\u0001\u000fo>\u00148.\u001a:M_\u001e4\u0017\u000e\\3t)\u0005i\b#\u0002@\u0002\b\u00055abA@\u0002\u00049\u0019A*!\u0001\n\u0003)J1!!\u0002*\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0003\u0002\f\t\u00191+Z9\u000b\u0007\u0005\u0015\u0011\u0006\u0005\u0003\u0002\u0010\u0005eQBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\u0005%|'BAA\f\u0003\u0011Q\u0017M^1\n\t\u0005m\u0011\u0011\u0003\u0002\u0005\r&dW-\u0001\u0003ti>\u0004H#\u00017\u0002#1{7-\u00197Ta\u0006\u00148n\u00117vgR,'\u000f\u0005\u0002C+M\u0011Qc\n\u000b\u0003\u0003G\tA\u0002\\8dC2\u001cE.^:uKJ,\"!!\f\u0011\t!\ny#Q\u0005\u0004\u0003cI#AB(qi&|g.\u0001\tm_\u000e\fGn\u00117vgR,'o\u0018\u0013fcR\u0019A.a\u000e\t\u0011AD\u0012\u0011!a\u0001\u0003[\tQ\u0002\\8dC2\u001cE.^:uKJ\u0004\u0013aA4fi\u0006)1\r\\3be\u0006)\u0011\r\u001d9msRI\u0011)a\u0011\u0002F\u0005\u001d\u0013\u0011\n\u0005\u0006gq\u0001\r!\u000e\u0005\u0006qq\u0001\r!\u000e\u0005\u0006sq\u0001\r!\u000e\u0005\u0006uq\u0001\ra\u000f"
)
public class LocalSparkCluster implements Logging {
   private final int numWorkers;
   private final int coresPerWorker;
   private final int memoryPerWorker;
   private final SparkConf conf;
   private final String localHostname;
   private final ArrayBuffer masterRpcEnvs;
   private final ArrayBuffer workerRpcEnvs;
   private int masterWebUIPort;
   private final ArrayBuffer workerDirs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static LocalSparkCluster apply(final int numWorkers, final int coresPerWorker, final int memoryPerWorker, final SparkConf conf) {
      return LocalSparkCluster$.MODULE$.apply(numWorkers, coresPerWorker, memoryPerWorker, conf);
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

   private String localHostname() {
      return this.localHostname;
   }

   private ArrayBuffer masterRpcEnvs() {
      return this.masterRpcEnvs;
   }

   private ArrayBuffer workerRpcEnvs() {
      return this.workerRpcEnvs;
   }

   public int masterWebUIPort() {
      return this.masterWebUIPort;
   }

   public void masterWebUIPort_$eq(final int x$1) {
      this.masterWebUIPort = x$1;
   }

   private ArrayBuffer workerDirs() {
      return this.workerDirs;
   }

   public String[] start() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting a local Spark cluster with "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " workers."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_WORKERS..MODULE$, BoxesRunTime.boxToInteger(this.numWorkers))}))))));
      SparkConf _conf = this.conf.clone().setIfMissing((ConfigEntry)package$.MODULE$.MASTER_REST_SERVER_ENABLED(), (Object)BoxesRunTime.boxToBoolean(false)).set((ConfigEntry)package$.MODULE$.SHUFFLE_SERVICE_ENABLED(), (Object)BoxesRunTime.boxToBoolean(false));
      Tuple3 var4 = Master$.MODULE$.startRpcEnvAndEndpoint(this.localHostname(), 0, 0, _conf);
      if (var4 != null) {
         RpcEnv rpcEnv = (RpcEnv)var4._1();
         int webUiPort = BoxesRunTime.unboxToInt(var4._2());
         Tuple2 var3 = new Tuple2(rpcEnv, BoxesRunTime.boxToInteger(webUiPort));
         RpcEnv rpcEnv = (RpcEnv)var3._1();
         int webUiPort = var3._2$mcI$sp();
         this.masterWebUIPort_$eq(webUiPort);
         this.masterRpcEnvs().$plus$eq(rpcEnv);
         String var10000 = org.apache.spark.util.Utils$.MODULE$.localHostNameForURI();
         String masterUrl = "spark://" + var10000 + ":" + rpcEnv.address().port();
         String[] masters = (String[])((Object[])(new String[]{masterUrl}));
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), this.numWorkers).foreach((workerNum) -> $anonfun$start$2(this, masters, _conf, BoxesRunTime.unboxToInt(workerNum)));
         return masters;
      } else {
         throw new MatchError(var4);
      }
   }

   public Seq workerLogfiles() {
      return (Seq)this.workerDirs().toSeq().flatMap((dir) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])org.apache.spark.util.Utils$.MODULE$.recursiveList(new File(dir))), (f) -> BoxesRunTime.boxToBoolean($anonfun$workerLogfiles$2(f)))));
   }

   public void stop() {
      this.logInfo((Function0)(() -> "Shutting down local Spark cluster."));
      this.workerRpcEnvs().foreach((x$2) -> {
         $anonfun$stop$2(x$2);
         return BoxedUnit.UNIT;
      });
      this.workerRpcEnvs().foreach((x$3) -> {
         $anonfun$stop$3(x$3);
         return BoxedUnit.UNIT;
      });
      this.masterRpcEnvs().foreach((x$4) -> {
         $anonfun$stop$4(x$4);
         return BoxedUnit.UNIT;
      });
      this.masterRpcEnvs().foreach((x$5) -> {
         $anonfun$stop$5(x$5);
         return BoxedUnit.UNIT;
      });
      this.masterRpcEnvs().clear();
      this.workerRpcEnvs().clear();
      this.workerDirs().clear();
      LocalSparkCluster$.MODULE$.org$apache$spark$deploy$LocalSparkCluster$$clear();
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$start$2(final LocalSparkCluster $this, final String[] masters$1, final SparkConf _conf$1, final int workerNum) {
      String var10000;
      if (org.apache.spark.util.Utils$.MODULE$.isTesting()) {
         String x$1 = "worker";
         String x$2 = org.apache.spark.util.Utils$.MODULE$.createTempDir$default$1();
         var10000 = org.apache.spark.util.Utils$.MODULE$.createTempDir(x$2, "worker").getAbsolutePath();
      } else {
         var10000 = null;
      }

      String workDir = var10000;
      if (org.apache.spark.util.Utils$.MODULE$.isTesting()) {
         $this.workerDirs().$plus$eq(workDir);
      } else {
         BoxedUnit var8 = BoxedUnit.UNIT;
      }

      RpcEnv workerEnv = Worker$.MODULE$.startRpcEnvAndEndpoint($this.localHostname(), 0, 0, $this.coresPerWorker, $this.memoryPerWorker, masters$1, workDir, new Some(BoxesRunTime.boxToInteger(workerNum)), _conf$1, (Option)$this.conf.get((ConfigEntry)org.apache.spark.internal.config.Worker$.MODULE$.SPARK_WORKER_RESOURCE_FILE()));
      return (ArrayBuffer)$this.workerRpcEnvs().$plus$eq(workerEnv);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$workerLogfiles$2(final File f) {
      return f.isFile() && scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString(".*\\.log$")).findFirstMatchIn(f.getName()).isDefined();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final RpcEnv x$2) {
      x$2.shutdown();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$3(final RpcEnv x$3) {
      x$3.awaitTermination();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$4(final RpcEnv x$4) {
      x$4.shutdown();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$5(final RpcEnv x$5) {
      x$5.awaitTermination();
   }

   public LocalSparkCluster(final int numWorkers, final int coresPerWorker, final int memoryPerWorker, final SparkConf conf) {
      this.numWorkers = numWorkers;
      this.coresPerWorker = coresPerWorker;
      this.memoryPerWorker = memoryPerWorker;
      this.conf = conf;
      Logging.$init$(this);
      this.localHostname = org.apache.spark.util.Utils$.MODULE$.localHostName();
      this.masterRpcEnvs = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.workerRpcEnvs = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.masterWebUIPort = -1;
      this.workerDirs = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
