package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnvConfig;
import org.apache.spark.rpc.RpcEnvFactory;
import org.apache.spark.serializer.JavaSerializer;
import org.apache.spark.serializer.JavaSerializerInstance;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u000592Qa\u0001\u0003\u0001\r9AQa\b\u0001\u0005\u0002\u0005BQ\u0001\n\u0001\u0005\u0002\u0015\u0012!CT3uif\u0014\u0006oY#om\u001a\u000b7\r^8ss*\u0011QAB\u0001\u0006]\u0016$H/\u001f\u0006\u0003\u000f!\t1A\u001d9d\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7\u0003\u0002\u0001\u0010+e\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u00051\u0011B\u0001\r\u0007\u00055\u0011\u0006oY#om\u001a\u000b7\r^8ssB\u0011!$H\u0007\u00027)\u0011A\u0004C\u0001\tS:$XM\u001d8bY&\u0011ad\u0007\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0012\u0011\u0005\r\u0002Q\"\u0001\u0003\u0002\r\r\u0014X-\u0019;f)\t1\u0013\u0006\u0005\u0002\u0017O%\u0011\u0001F\u0002\u0002\u0007%B\u001cWI\u001c<\t\u000b)\u0012\u0001\u0019A\u0016\u0002\r\r|gNZ5h!\t1B&\u0003\u0002.\r\ta!\u000b]2F]Z\u001cuN\u001c4jO\u0002"
)
public class NettyRpcEnvFactory implements RpcEnvFactory, Logging {
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

   public RpcEnv create(final RpcEnvConfig config) {
      SparkConf sparkConf = config.conf();
      JavaSerializerInstance javaSerializerInstance = (JavaSerializerInstance)(new JavaSerializer(sparkConf)).newInstance();
      NettyRpcEnv nettyEnv = new NettyRpcEnv(sparkConf, javaSerializerInstance, config.advertiseAddress(), config.securityManager(), config.numUsableCores());
      if (!config.clientMode()) {
         Function1 startNettyRpcEnv = (actualPort) -> $anonfun$create$1(nettyEnv, config, BoxesRunTime.unboxToInt(actualPort));

         try {
            Utils$.MODULE$.startServiceOnPort(config.port(), startNettyRpcEnv, sparkConf, config.name())._1();
         } catch (Throwable var10) {
            if (var10 != null && .MODULE$.apply(var10)) {
               nettyEnv.shutdown();
               throw var10;
            }

            throw var10;
         }
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return nettyEnv;
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$create$1(final NettyRpcEnv nettyEnv$1, final RpcEnvConfig config$1, final int actualPort) {
      nettyEnv$1.startServer(config$1.bindAddress(), actualPort);
      return new Tuple2(nettyEnv$1, BoxesRunTime.boxToInteger(nettyEnv$1.address().port()));
   }

   public NettyRpcEnvFactory() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
