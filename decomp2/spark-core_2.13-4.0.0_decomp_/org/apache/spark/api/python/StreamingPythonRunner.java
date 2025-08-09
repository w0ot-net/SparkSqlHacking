package org.apache.spark.api.python;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkPythonException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.Python$;
import org.apache.spark.internal.config.package$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%uA\u0002\u0013&\u0011\u0003IsF\u0002\u00042K!\u0005\u0011F\r\u0005\u0006s\u0005!\ta\u000f\u0005\u0006y\u0005!\t!\u0010\u0004\u0006c\u0015\u0002\u0011f\u0010\u0005\t\r\u0012\u0011\t\u0011)A\u0005\u000f\"A!\n\u0002B\u0001B\u0003%1\n\u0003\u0005W\t\t\u0005\t\u0015!\u0003L\u0011!9FA!A!\u0002\u0013Y\u0005\"B\u001d\u0005\t\u0003A\u0006bB/\u0005\u0005\u0004%IA\u0018\u0005\u0007G\u0012\u0001\u000b\u0011B0\t\u000f\u0011$!\u0019!C\tK\"1\u0011\u000e\u0002Q\u0001\n\u0019DqA\u001b\u0003C\u0002\u0013E1\u000e\u0003\u0004p\t\u0001\u0006I\u0001\u001c\u0005\ba\u0012\u0011\r\u0011\"\u0005r\u0011\u0019QH\u0001)A\u0005e\"91\u0010\u0002b\u0001\n#a\bBB?\u0005A\u0003%1\nC\u0004\u007f\t\u0001\u0007I\u0011C@\t\u0013\u00055A\u00011A\u0005\u0012\u0005=\u0001\u0002CA\u000e\t\u0001\u0006K!!\u0001\t\u0013\u0005uA\u00011A\u0005\u0012\u0005}\u0001\"CA\u0015\t\u0001\u0007I\u0011CA\u0016\u0011!\ty\u0003\u0002Q!\n\u0005\u0005\u0002\u0002CA\u0019\t\t\u0007I\u0011\u0003?\t\u000f\u0005MB\u0001)A\u0005\u0017\"9\u0011Q\u0007\u0003\u0005\u0002\u0005]\u0002bBA)\t\u0011\u0005\u00111\u000b\u0004\u0007\u00033\"\u0001!a\u0017\t\u0013\u0005\rdD!A!\u0002\u00131\u0007\"CA3=\t\u0005\t\u0015!\u0003L\u0011\u0019Id\u0004\"\u0001\u0002h!9\u0011\u0011\u000f\u0003\u0005\u0002\u0005M\u0004bBA;\t\u0011\u0005\u0011qO\u0001\u0016'R\u0014X-Y7j]\u001e\u0004\u0016\u0010\u001e5p]J+hN\\3s\u0015\t1s%\u0001\u0004qsRDwN\u001c\u0006\u0003Q%\n1!\u00199j\u0015\tQ3&A\u0003ta\u0006\u00148N\u0003\u0002-[\u00051\u0011\r]1dQ\u0016T\u0011AL\u0001\u0004_J<\u0007C\u0001\u0019\u0002\u001b\u0005)#!F*ue\u0016\fW.\u001b8h!f$\bn\u001c8Sk:tWM]\n\u0003\u0003M\u0002\"\u0001N\u001c\u000e\u0003UR\u0011AN\u0001\u0006g\u000e\fG.Y\u0005\u0003qU\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003=\nQ!\u00199qYf$\u0012BPAA\u0003\u0007\u000b))a\"\u0011\u0005A\"1c\u0001\u00034\u0001B\u0011\u0011\tR\u0007\u0002\u0005*\u00111)K\u0001\tS:$XM\u001d8bY&\u0011QI\u0011\u0002\b\u0019><w-\u001b8h\u0003\u00111WO\\2\u0011\u0005AB\u0015BA%&\u00059\u0001\u0016\u0010\u001e5p]\u001a+hn\u0019;j_:\f!bY8o]\u0016\u001cG/\u0016:m!\ta5K\u0004\u0002N#B\u0011a*N\u0007\u0002\u001f*\u0011\u0001KO\u0001\u0007yI|w\u000e\u001e \n\u0005I+\u0014A\u0002)sK\u0012,g-\u0003\u0002U+\n11\u000b\u001e:j]\u001eT!AU\u001b\u0002\u0013M,7o]5p]&#\u0017\u0001D<pe.,'/T8ek2,G#\u0002 Z5nc\u0006\"\u0002$\n\u0001\u00049\u0005\"\u0002&\n\u0001\u0004Y\u0005\"\u0002,\n\u0001\u0004Y\u0005\"B,\n\u0001\u0004Y\u0015\u0001B2p]\u001a,\u0012a\u0018\t\u0003A\u0006l\u0011!K\u0005\u0003E&\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u000b\r|gN\u001a\u0011\u0002\u0015\t,hMZ3s'&TX-F\u0001g!\t!t-\u0003\u0002ik\t\u0019\u0011J\u001c;\u0002\u0017\t,hMZ3s'&TX\rI\u0001\u0012CV$\bnU8dW\u0016$H+[7f_V$X#\u00017\u0011\u0005Qj\u0017B\u000186\u0005\u0011auN\\4\u0002%\u0005,H\u000f[*pG.,G\u000fV5nK>,H\u000fI\u0001\bK:4h+\u0019:t+\u0005\u0011\b\u0003B:y\u0017.k\u0011\u0001\u001e\u0006\u0003kZ\fA!\u001e;jY*\tq/\u0001\u0003kCZ\f\u0017BA=u\u0005\ri\u0015\r]\u0001\tK:4h+\u0019:tA\u0005Q\u0001/\u001f;i_:,\u00050Z2\u0016\u0003-\u000b1\u0002]=uQ>tW\t_3dA\u0005a\u0001/\u001f;i_:<vN]6feV\u0011\u0011\u0011\u0001\t\u0006i\u0005\r\u0011qA\u0005\u0004\u0003\u000b)$AB(qi&|g\u000eE\u00021\u0003\u0013I1!a\u0003&\u00051\u0001\u0016\u0010\u001e5p]^{'o[3s\u0003A\u0001\u0018\u0010\u001e5p]^{'o[3s?\u0012*\u0017\u000f\u0006\u0003\u0002\u0012\u0005]\u0001c\u0001\u001b\u0002\u0014%\u0019\u0011QC\u001b\u0003\tUs\u0017\u000e\u001e\u0005\n\u00033)\u0012\u0011!a\u0001\u0003\u0003\t1\u0001\u001f\u00132\u00035\u0001\u0018\u0010\u001e5p]^{'o[3sA\u0005\u0019\u0002/\u001f;i_:<vN]6fe\u001a\u000b7\r^8ssV\u0011\u0011\u0011\u0005\t\u0006i\u0005\r\u00111\u0005\t\u0004a\u0005\u0015\u0012bAA\u0014K\t\u0019\u0002+\u001f;i_:<vN]6fe\u001a\u000b7\r^8ss\u00069\u0002/\u001f;i_:<vN]6fe\u001a\u000b7\r^8ss~#S-\u001d\u000b\u0005\u0003#\ti\u0003C\u0005\u0002\u001aa\t\t\u00111\u0001\u0002\"\u0005!\u0002/\u001f;i_:<vN]6fe\u001a\u000b7\r^8ss\u0002\n\u0011\u0002]=uQ>tg+\u001a:\u0002\u0015ALH\u000f[8o-\u0016\u0014\b%\u0001\u0003j]&$HCAA\u001d!\u001d!\u00141HA \u0003\u0017J1!!\u00106\u0005\u0019!V\u000f\u001d7feA!\u0011\u0011IA$\u001b\t\t\u0019EC\u0002\u0002FY\f!![8\n\t\u0005%\u00131\t\u0002\u0011\t\u0006$\u0018mT;uaV$8\u000b\u001e:fC6\u0004B!!\u0011\u0002N%!\u0011qJA\"\u0005=!\u0015\r^1J]B,Ho\u0015;sK\u0006l\u0017AK:ue\u0016\fW.\u001b8h!f$\bn\u001c8Sk:tWM]%oSRL\u0017\r\\5{CRLwN\u001c$bS2,(/\u001a\u000b\u0007\u0003+\ni'a\u001c\u0011\u0007\u0005]c$D\u0001\u0005\u00051\u001aFO]3b[&tw\rU=uQ>t'+\u001e8oKJLe.\u001b;jC2L'0\u0019;j_:,\u0005pY3qi&|gnE\u0002\u001f\u0003;\u00022\u0001YA0\u0013\r\t\t'\u000b\u0002\u0015'B\f'o\u001b)zi\"|g.\u0012=dKB$\u0018n\u001c8\u0002\u001bI,7O\u0012:p[BKH\u000f[8o\u0003))'O]'fgN\fw-\u001a\u000b\u0007\u0003+\nI'a\u001b\t\r\u0005\r\u0014\u00051\u0001g\u0011\u0019\t)'\ta\u0001\u0017\"1\u00111M\u000fA\u0002\u0019Da!!\u001a\u001e\u0001\u0004Y\u0015\u0001B:u_B$\"!!\u0005\u0002\u001f%\u001cxk\u001c:lKJ\u001cFo\u001c9qK\u0012$\"!!\u001f\u0011\u000bQ\n\u0019!a\u001f\u0011\u0007Q\ni(C\u0002\u0002\u0000U\u0012qAQ8pY\u0016\fg\u000eC\u0003G\u0007\u0001\u0007q\tC\u0003K\u0007\u0001\u00071\nC\u0003W\u0007\u0001\u00071\nC\u0003X\u0007\u0001\u00071\n"
)
public class StreamingPythonRunner implements Logging {
   private final PythonFunction func;
   private final String connectUrl;
   private final String sessionId;
   private final String workerModule;
   private final SparkConf conf;
   private final int bufferSize;
   private final long authSocketTimeout;
   private final Map envVars;
   private final String pythonExec;
   private Option pythonWorker;
   private Option pythonWorkerFactory;
   private final String pythonVer;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static StreamingPythonRunner apply(final PythonFunction func, final String connectUrl, final String sessionId, final String workerModule) {
      return StreamingPythonRunner$.MODULE$.apply(func, connectUrl, sessionId, workerModule);
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

   private SparkConf conf() {
      return this.conf;
   }

   public int bufferSize() {
      return this.bufferSize;
   }

   public long authSocketTimeout() {
      return this.authSocketTimeout;
   }

   public Map envVars() {
      return this.envVars;
   }

   public String pythonExec() {
      return this.pythonExec;
   }

   public Option pythonWorker() {
      return this.pythonWorker;
   }

   public void pythonWorker_$eq(final Option x$1) {
      this.pythonWorker = x$1;
   }

   public Option pythonWorkerFactory() {
      return this.pythonWorkerFactory;
   }

   public void pythonWorkerFactory_$eq(final Option x$1) {
      this.pythonWorkerFactory = x$1;
   }

   public String pythonVer() {
      return this.pythonVer;
   }

   public Tuple2 init() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initializing Python runner (session: ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SESSION_ID..MODULE$, this.sessionId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" pythonExec: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_EXEC..MODULE$, this.pythonExec())}))))));
      SparkEnv env = SparkEnv$.MODULE$.get();
      String localdir = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])env.blockManager().diskBlockManager().localDirs()), (f) -> f.getPath(), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(",");
      this.envVars().put("SPARK_LOCAL_DIRS", localdir);
      this.envVars().put("SPARK_AUTH_SOCKET_TIMEOUT", Long.toString(this.authSocketTimeout()));
      this.envVars().put("SPARK_BUFFER_SIZE", Integer.toString(this.bufferSize()));
      if (!this.connectUrl.isEmpty()) {
         this.envVars().put("SPARK_CONNECT_LOCAL_URL", this.connectUrl);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      PythonWorkerFactory workerFactory = new PythonWorkerFactory(this.pythonExec(), this.workerModule, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.envVars()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), false);
      Tuple2 var6 = workerFactory.createSimpleWorker(true);
      if (var6 != null) {
         PythonWorker worker = (PythonWorker)var6._1();
         if (worker != null) {
            this.pythonWorker_$eq(new Some(worker));
            this.pythonWorkerFactory_$eq(new Some(workerFactory));
            BufferedOutputStream stream = new BufferedOutputStream(((PythonWorker)this.pythonWorker().get()).channel().socket().getOutputStream(), this.bufferSize());
            DataOutputStream dataOut = new DataOutputStream(stream);
            PythonWorkerUtils$.MODULE$.writePythonVersion(this.pythonVer(), dataOut);
            if (!this.sessionId.isEmpty()) {
               PythonRDD$.MODULE$.writeUTF(this.sessionId, dataOut);
            }

            PythonWorkerUtils$.MODULE$.writePythonFunction(this.func, dataOut);
            dataOut.flush();
            DataInputStream dataIn = new DataInputStream(new BufferedInputStream(((PythonWorker)this.pythonWorker().get()).channel().socket().getInputStream(), this.bufferSize()));
            int resFromPython = dataIn.readInt();
            if (resFromPython != 0) {
               String errMessage = PythonWorkerUtils$.MODULE$.readUTF(dataIn);
               throw this.streamingPythonRunnerInitializationFailure(resFromPython, errMessage);
            }

            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Runner initialization succeeded (returned"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_RESPONSE..MODULE$, BoxesRunTime.boxToInteger(resFromPython))}))))));
            return new Tuple2(dataOut, dataIn);
         }
      }

      throw new MatchError(var6);
   }

   public StreamingPythonRunnerInitializationException streamingPythonRunnerInitializationFailure(final int resFromPython, final String errMessage) {
      return new StreamingPythonRunnerInitializationException(resFromPython, errMessage);
   }

   public void stop() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stopping streaming runner for sessionId: ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SESSION_ID..MODULE$, this.sessionId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" module: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_MODULE..MODULE$, this.workerModule)}))))));

      try {
         this.pythonWorkerFactory().foreach((factory) -> {
            $anonfun$stop$2(this, factory);
            return BoxedUnit.UNIT;
         });
      } catch (Exception var2) {
         this.logError((Function0)(() -> "Exception when trying to kill worker"), var2);
      }

   }

   public Option isWorkerStopped() {
      return this.pythonWorkerFactory().flatMap((factory) -> this.pythonWorker().map((worker) -> BoxesRunTime.boxToBoolean($anonfun$isWorkerStopped$2(factory, worker))));
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$3(final PythonWorkerFactory factory$1, final PythonWorker worker) {
      factory$1.stopWorker(worker);
      factory$1.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$2(final StreamingPythonRunner $this, final PythonWorkerFactory factory) {
      $this.pythonWorker().foreach((worker) -> {
         $anonfun$stop$3(factory, worker);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isWorkerStopped$2(final PythonWorkerFactory factory$2, final PythonWorker worker) {
      return factory$2.isWorkerStopped(worker);
   }

   public StreamingPythonRunner(final PythonFunction func, final String connectUrl, final String sessionId, final String workerModule) {
      this.func = func;
      this.connectUrl = connectUrl;
      this.sessionId = sessionId;
      this.workerModule = workerModule;
      Logging.$init$(this);
      this.conf = SparkEnv$.MODULE$.get().conf();
      this.bufferSize = BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.BUFFER_SIZE()));
      this.authSocketTimeout = BoxesRunTime.unboxToLong(this.conf().get(Python$.MODULE$.PYTHON_AUTH_SOCKET_TIMEOUT()));
      this.envVars = func.envVars();
      this.pythonExec = func.pythonExec();
      this.pythonWorker = scala.None..MODULE$;
      this.pythonWorkerFactory = scala.None..MODULE$;
      this.pythonVer = func.pythonVer();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class StreamingPythonRunnerInitializationException extends SparkPythonException {
      // $FF: synthetic field
      public final StreamingPythonRunner $outer;

      // $FF: synthetic method
      public StreamingPythonRunner org$apache$spark$api$python$StreamingPythonRunner$StreamingPythonRunnerInitializationException$$$outer() {
         return this.$outer;
      }

      public StreamingPythonRunnerInitializationException(final int resFromPython, final String errMessage) {
         if (StreamingPythonRunner.this == null) {
            throw null;
         } else {
            this.$outer = StreamingPythonRunner.this;
            super("STREAMING_PYTHON_RUNNER_INITIALIZATION_FAILURE", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resFromPython"), Integer.toString(resFromPython)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("msg"), errMessage)}))), org.apache.spark.SparkPythonException..MODULE$.$lessinit$greater$default$3(), org.apache.spark.SparkPythonException..MODULE$.$lessinit$greater$default$4(), org.apache.spark.SparkPythonException..MODULE$.$lessinit$greater$default$5());
         }
      }
   }
}
