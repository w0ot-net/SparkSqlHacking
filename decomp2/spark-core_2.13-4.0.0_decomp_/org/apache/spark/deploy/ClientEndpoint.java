package org.apache.spark.deploy;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.master.DriverState$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.util.SparkExitCode$;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.concurrent.ExecutionContextExecutor;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.sys.package.;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\t5b\u0001\u0002\u0013&\t9B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\t\r\u0002\u0011\t\u0011)A\u0005\u0007\"Aq\t\u0001B\u0001B\u0003%\u0001\n\u0003\u0005M\u0001\t\u0005\t\u0015!\u0003N\u0011!a\u0006A!A!\u0002\u0013i\u0006\"B1\u0001\t\u0003\u0011\u0007b\u00025\u0001\u0005\u0004%I!\u001b\u0005\u0007i\u0002\u0001\u000b\u0011\u00026\t\u000fU\u0004!\u0019!C\u0005m\"1A\u0010\u0001Q\u0001\n]Dq! \u0001C\u0002\u0013%a\u0010C\u0004\u0002\u0016\u0001\u0001\u000b\u0011B@\t\u0013\u0005]\u0001\u00011A\u0005\n\u0005e\u0001\"CA\u000e\u0001\u0001\u0007I\u0011BA\u000f\u0011\u001d\tI\u0003\u0001Q!\neC\u0011\"a\u000b\u0001\u0005\u0004%I!!\f\t\u0011\u0005U\u0002\u0001)A\u0005\u0003_A\u0011\"a\u000e\u0001\u0005\u0004%I!!\u000f\t\u0011\u0005\u0005\u0003\u0001)A\u0005\u0003wA\u0011\"a\u0011\u0001\u0001\u0004%I!!\u0012\t\u0013\u0005M\u0003\u00011A\u0005\n\u0005U\u0003\u0002CA-\u0001\u0001\u0006K!a\u0012\t\u0013\u0005m\u0003\u00011A\u0005\n\u00055\u0002\"CA/\u0001\u0001\u0007I\u0011BA0\u0011!\t\u0019\u0007\u0001Q!\n\u0005=\u0002bBA3\u0001\u0011%\u0011q\r\u0005\b\u0003\u0007\u0003A\u0011IAC\u0011\u001d\t9\t\u0001C\u0005\u0003\u0013Cq!!/\u0001\t\u0013\t)\tC\u0004\u0002<\u0002!\t!!0\t\u000f\t\r\u0001\u0001\"\u0011\u0003\u0006!9!Q\u0002\u0001\u0005B\t=\u0001b\u0002B\u000b\u0001\u0011\u0005#q\u0003\u0005\b\u0005K\u0001A\u0011\tB\u0014\u0011\u001d\u0011Y\u0003\u0001C!\u0003\u000b\u0013ab\u00117jK:$XI\u001c3q_&tGO\u0003\u0002'O\u00051A-\u001a9m_fT!\u0001K\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Z\u0013AB1qC\u000eDWMC\u0001-\u0003\ry'oZ\u0002\u0001'\u0011\u0001q&N\u001e\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\r\u0005s\u0017PU3g!\t1\u0014(D\u00018\u0015\tAt%A\u0002sa\u000eL!AO\u001c\u0003+QC'/Z1e'\u00064WM\u00159d\u000b:$\u0007o\\5oiB\u0011AhP\u0007\u0002{)\u0011ahJ\u0001\tS:$XM\u001d8bY&\u0011\u0001)\u0010\u0002\b\u0019><w-\u001b8h\u0003\u0019\u0011\boY#omV\t1\t\u0005\u00027\t&\u0011Qi\u000e\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wA\u0005QAM]5wKJ\f%oZ:\u0011\u0005%SU\"A\u0013\n\u0005-+#aD\"mS\u0016tG/\u0011:hk6,g\u000e^:\u0002\u001f5\f7\u000f^3s\u000b:$\u0007o\\5oiN\u00042A\u0014,Z\u001d\tyEK\u0004\u0002Q'6\t\u0011K\u0003\u0002S[\u00051AH]8pizJ\u0011AM\u0005\u0003+F\nq\u0001]1dW\u0006<W-\u0003\u0002X1\n\u00191+Z9\u000b\u0005U\u000b\u0004C\u0001\u001c[\u0013\tYvG\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002\t\r|gN\u001a\t\u0003=~k\u0011aJ\u0005\u0003A\u001e\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\u0015\u0019G-\u001a4h!\tI\u0005\u0001C\u0003B\r\u0001\u00071\tC\u0003H\r\u0001\u0007\u0001\nC\u0003M\r\u0001\u0007Q\nC\u0003]\r\u0001\u0007Q,\u0001\u000bg_J<\u0018M\u001d3NKN\u001c\u0018mZ3UQJ,\u0017\rZ\u000b\u0002UB\u00111N]\u0007\u0002Y*\u0011QN\\\u0001\u000bG>t7-\u001e:sK:$(BA8q\u0003\u0011)H/\u001b7\u000b\u0003E\fAA[1wC&\u00111\u000f\u001c\u0002\u0019'\u000eDW\rZ;mK\u0012,\u00050Z2vi>\u00148+\u001a:wS\u000e,\u0017!\u00064pe^\f'\u000fZ'fgN\fw-\u001a+ie\u0016\fG\rI\u0001\u001fM>\u0014x/\u0019:e\u001b\u0016\u001c8/Y4f\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR,\u0012a\u001e\t\u0003qjl\u0011!\u001f\u0006\u0003[FJ!a_=\u00031\u0015CXmY;uS>t7i\u001c8uKb$X\t_3dkR|'/A\u0010g_J<\u0018M\u001d3NKN\u001c\u0018mZ3Fq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yi\u0002\n1\u0002\\8ti6\u000b7\u000f^3sgV\tq\u0010\u0005\u0004\u0002\u0002\u0005-\u0011qB\u0007\u0003\u0003\u0007QA!!\u0002\u0002\b\u00059Q.\u001e;bE2,'bAA\u0005c\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u00055\u00111\u0001\u0002\b\u0011\u0006\u001c\bnU3u!\r1\u0014\u0011C\u0005\u0004\u0003'9$A\u0003*qG\u0006#GM]3tg\u0006aAn\\:u\u001b\u0006\u001cH/\u001a:tA\u0005!\u0012m\u0019;jm\u0016l\u0015m\u001d;fe\u0016sG\r]8j]R,\u0012!W\u0001\u0019C\u000e$\u0018N^3NCN$XM]#oIB|\u0017N\u001c;`I\u0015\fH\u0003BA\u0010\u0003K\u00012\u0001MA\u0011\u0013\r\t\u0019#\r\u0002\u0005+:LG\u000f\u0003\u0005\u0002(9\t\t\u00111\u0001Z\u0003\rAH%M\u0001\u0016C\u000e$\u0018N^3NCN$XM]#oIB|\u0017N\u001c;!\u0003E9\u0018-\u001b;BaB\u001cu.\u001c9mKRLwN\\\u000b\u0003\u0003_\u00012\u0001MA\u0019\u0013\r\t\u0019$\r\u0002\b\u0005>|G.Z1o\u0003I9\u0018-\u001b;BaB\u001cu.\u001c9mKRLwN\u001c\u0011\u0002;I+\u0005k\u0014*U?\u0012\u0013\u0016JV#S?N#\u0016\tV+T?&sE+\u0012*W\u00032+\"!a\u000f\u0011\u0007A\ni$C\u0002\u0002@E\u00121!\u00138u\u0003y\u0011V\tU(S)~#%+\u0013,F%~\u001bF+\u0011+V'~Ke\nV#S-\u0006c\u0005%A\ttk\nl\u0017\u000e\u001e;fI\u0012\u0013\u0018N^3s\u0013\u0012+\"!a\u0012\u0011\t\u0005%\u0013qJ\u0007\u0003\u0003\u0017R1!!\u0014q\u0003\u0011a\u0017M\\4\n\t\u0005E\u00131\n\u0002\u0007'R\u0014\u0018N\\4\u0002+M,(-\\5ui\u0016$GI]5wKJLEi\u0018\u0013fcR!\u0011qDA,\u0011%\t9#FA\u0001\u0002\u0004\t9%\u0001\ntk\nl\u0017\u000e\u001e;fI\u0012\u0013\u0018N^3s\u0013\u0012\u0003\u0013\u0001\u00063sSZ,'o\u0015;biV\u001c(+\u001a9peR,G-\u0001\ree&4XM]*uCR,8OU3q_J$X\rZ0%KF$B!a\b\u0002b!I\u0011q\u0005\r\u0002\u0002\u0003\u0007\u0011qF\u0001\u0016IJLg/\u001a:Ti\u0006$Xo\u001d*fa>\u0014H/\u001a3!\u0003-9W\r\u001e)s_B,'\u000f^=\u0015\r\u0005%\u0014QPAA!\u0015\u0001\u00141NA8\u0013\r\ti'\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005E\u0014\u0011\u0010\b\u0005\u0003g\n)\b\u0005\u0002Qc%\u0019\u0011qO\u0019\u0002\rA\u0013X\rZ3g\u0013\u0011\t\t&a\u001f\u000b\u0007\u0005]\u0014\u0007C\u0004\u0002\u0000i\u0001\r!a\u001c\u0002\u0007-,\u0017\u0010C\u0003]5\u0001\u0007Q,A\u0004p]N#\u0018M\u001d;\u0015\u0005\u0005}\u0011\u0001I1ts:\u001c7+\u001a8e)>l\u0015m\u001d;fe\u0006sGMR8so\u0006\u0014HMU3qYf,B!a#\u0002$R!\u0011QRA[)\u0011\ty\"a$\t\u0013\u0005EE$!AA\u0004\u0005M\u0015AC3wS\u0012,gnY3%cA1\u0011QSAN\u0003?k!!a&\u000b\u0007\u0005e\u0015'A\u0004sK\u001adWm\u0019;\n\t\u0005u\u0015q\u0013\u0002\t\u00072\f7o\u001d+bOB!\u0011\u0011UAR\u0019\u0001!q!!*\u001d\u0005\u0004\t9KA\u0001U#\u0011\tI+a,\u0011\u0007A\nY+C\u0002\u0002.F\u0012qAT8uQ&tw\rE\u00021\u0003cK1!a-2\u0005\r\te.\u001f\u0005\b\u0003oc\u0002\u0019AAX\u0003\u001diWm]:bO\u0016\f1#\\8oSR|'\u000f\u0012:jm\u0016\u00148\u000b^1ukN\f!C]3q_J$HI]5wKJ\u001cF/\u0019;vgRa\u0011qDA`\u0003\u0007\fy/a=\u0002x\"9\u0011\u0011\u0019\u0010A\u0002\u0005=\u0012!\u00024pk:$\u0007bBAc=\u0001\u0007\u0011qY\u0001\u0006gR\fG/\u001a\t\u0006a\u0005-\u0014\u0011\u001a\t\u0005\u0003\u0017\fIO\u0004\u0003\u0002N\u0006\rh\u0002BAh\u0003?tA!!5\u0002^:!\u00111[An\u001d\u0011\t).!7\u000f\u0007A\u000b9.C\u0001-\u0013\tQ3&\u0003\u0002)S%\u0011aeJ\u0005\u0004\u0003C,\u0013AB7bgR,'/\u0003\u0003\u0002f\u0006\u001d\u0018a\u0003#sSZ,'o\u0015;bi\u0016T1!!9&\u0013\u0011\tY/!<\u0003\u0017\u0011\u0013\u0018N^3s'R\fG/\u001a\u0006\u0005\u0003K\f9\u000fC\u0004\u0002rz\u0001\r!!\u001b\u0002\u0011]|'o[3s\u0013\u0012Dq!!>\u001f\u0001\u0004\tI'\u0001\bx_J\\WM\u001d%pgR\u0004vN\u001d;\t\u000f\u0005eh\u00041\u0001\u0002|\u0006IQ\r_2faRLwN\u001c\t\u0006a\u0005-\u0014Q \t\u0004\u001d\u0006}\u0018b\u0001B\u00011\nIQ\t_2faRLwN\\\u0001\be\u0016\u001cW-\u001b<f+\t\u00119\u0001E\u00041\u0005\u0013\ty+a\b\n\u0007\t-\u0011GA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u00039yg\u000eR5tG>tg.Z2uK\u0012$B!a\b\u0003\u0012!9!1\u0003\u0011A\u0002\u0005=\u0011!\u0004:f[>$X-\u00113ee\u0016\u001c8/\u0001\bp]:+Go^8sW\u0016\u0013(o\u001c:\u0015\r\u0005}!\u0011\u0004B\u0012\u0011\u001d\u0011Y\"\ta\u0001\u0005;\tQaY1vg\u0016\u00042A\u0014B\u0010\u0013\r\u0011\t\u0003\u0017\u0002\n)\"\u0014xn^1cY\u0016DqAa\u0005\"\u0001\u0004\ty!A\u0004p]\u0016\u0013(o\u001c:\u0015\t\u0005}!\u0011\u0006\u0005\b\u00057\u0011\u0003\u0019\u0001B\u000f\u0003\u0019ygn\u0015;pa\u0002"
)
public class ClientEndpoint implements ThreadSafeRpcEndpoint, Logging {
   private final RpcEnv rpcEnv;
   private final ClientArguments driverArgs;
   private final Seq masterEndpoints;
   private final SparkConf conf;
   private final ScheduledExecutorService forwardMessageThread;
   private final ExecutionContextExecutor forwardMessageExecutionContext;
   private final HashSet lostMasters;
   private RpcEndpointRef org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint;
   private final boolean waitAppCompletion;
   private final int REPORT_DRIVER_STATUS_INTERVAL;
   private String org$apache$spark$deploy$ClientEndpoint$$submittedDriverID;
   private boolean driverStatusReported;
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

   public final RpcEndpointRef self() {
      return RpcEndpoint.self$(this);
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return RpcEndpoint.receiveAndReply$(this, context);
   }

   public void onConnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onConnected$(this, remoteAddress);
   }

   public final void stop() {
      RpcEndpoint.stop$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   private ScheduledExecutorService forwardMessageThread() {
      return this.forwardMessageThread;
   }

   private ExecutionContextExecutor forwardMessageExecutionContext() {
      return this.forwardMessageExecutionContext;
   }

   private HashSet lostMasters() {
      return this.lostMasters;
   }

   private RpcEndpointRef activeMasterEndpoint() {
      return this.org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint;
   }

   public void org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint_$eq(final RpcEndpointRef x$1) {
      this.org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint = x$1;
   }

   private boolean waitAppCompletion() {
      return this.waitAppCompletion;
   }

   private int REPORT_DRIVER_STATUS_INTERVAL() {
      return this.REPORT_DRIVER_STATUS_INTERVAL;
   }

   private String submittedDriverID() {
      return this.org$apache$spark$deploy$ClientEndpoint$$submittedDriverID;
   }

   public void org$apache$spark$deploy$ClientEndpoint$$submittedDriverID_$eq(final String x$1) {
      this.org$apache$spark$deploy$ClientEndpoint$$submittedDriverID = x$1;
   }

   private boolean driverStatusReported() {
      return this.driverStatusReported;
   }

   private void driverStatusReported_$eq(final boolean x$1) {
      this.driverStatusReported = x$1;
   }

   private Option getProperty(final String key, final SparkConf conf) {
      return .MODULE$.props().get(key).orElse(() -> conf.getOption(key));
   }

   public void onStart() {
      String var1 = this.driverArgs.cmd();
      switch (var1 == null ? 0 : var1.hashCode()) {
         case -1109843021:
            if (!"launch".equals(var1)) {
               throw new MatchError(var1);
            }

            String mainClass = "org.apache.spark.deploy.worker.DriverWrapper";
            String classPathConf = package$.MODULE$.DRIVER_CLASS_PATH().key();
            Seq classPathEntries = (Seq)scala.Option..MODULE$.option2Iterable(this.getProperty(classPathConf, this.conf)).toSeq().flatMap((cp) -> scala.Predef..MODULE$.wrapRefArray((Object[])cp.split(File.pathSeparator)));
            String libraryPathConf = package$.MODULE$.DRIVER_LIBRARY_PATH().key();
            Seq libraryPathEntries = (Seq)scala.Option..MODULE$.option2Iterable(this.getProperty(libraryPathConf, this.conf)).toSeq().flatMap((cp) -> scala.Predef..MODULE$.wrapRefArray((Object[])cp.split(File.pathSeparator)));
            String extraJavaOptsConf = package$.MODULE$.DRIVER_JAVA_OPTIONS().key();
            Seq extraJavaOpts = (Seq)this.getProperty(extraJavaOptsConf, this.conf).map((s) -> org.apache.spark.util.Utils$.MODULE$.splitCommandString(s)).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
            Seq sparkJavaOpts = org.apache.spark.util.Utils$.MODULE$.sparkJavaOpts(this.conf, org.apache.spark.util.Utils$.MODULE$.sparkJavaOpts$default$2());
            Seq javaOpts = (Seq)sparkJavaOpts.$plus$plus(extraJavaOpts);
            Command command = new Command(mainClass, (Seq)(new scala.collection.immutable..colon.colon("{{WORKER_URL}}", new scala.collection.immutable..colon.colon("{{USER_JAR}}", new scala.collection.immutable..colon.colon(this.driverArgs.mainClass(), scala.collection.immutable.Nil..MODULE$)))).$plus$plus(this.driverArgs.driverOptions()), .MODULE$.env(), classPathEntries, libraryPathEntries, javaOpts);
            Seq driverResourceReqs = ResourceUtils$.MODULE$.parseResourceRequirements(this.conf, package$.MODULE$.SPARK_DRIVER_PREFIX());
            DriverDescription driverDescription = new DriverDescription(this.driverArgs.jarUrl(), this.driverArgs.memory(), this.driverArgs.cores(), this.driverArgs.supervise(), command, driverResourceReqs);
            this.asyncSendToMasterAndForwardReply(new DeployMessages.RequestSubmitDriver(driverDescription), scala.reflect.ClassTag..MODULE$.apply(DeployMessages.SubmitDriverResponse.class));
            break;
         case 3291998:
            if ("kill".equals(var1)) {
               String driverId = this.driverArgs.driverId();
               this.org$apache$spark$deploy$ClientEndpoint$$submittedDriverID_$eq(driverId);
               this.asyncSendToMasterAndForwardReply(new DeployMessages.RequestKillDriver(driverId), scala.reflect.ClassTag..MODULE$.apply(DeployMessages.KillDriverResponse.class));
               break;
            }

            throw new MatchError(var1);
         default:
            throw new MatchError(var1);
      }

      this.logInfo((Function0)(() -> "... waiting before polling master for driver state"));
      this.forwardMessageThread().scheduleAtFixedRate(() -> org.apache.spark.util.Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.monitorDriverStatus()), 5000L, (long)this.REPORT_DRIVER_STATUS_INTERVAL(), TimeUnit.MILLISECONDS);
   }

   private void asyncSendToMasterAndForwardReply(final Object message, final ClassTag evidence$1) {
      this.masterEndpoints.foreach((masterEndpoint) -> {
         $anonfun$asyncSendToMasterAndForwardReply$1(this, message, evidence$1, masterEndpoint);
         return BoxedUnit.UNIT;
      });
   }

   private void monitorDriverStatus() {
      label14: {
         String var10000 = this.submittedDriverID();
         String var1 = "";
         if (var10000 == null) {
            if (var1 != null) {
               break label14;
            }
         } else if (!var10000.equals(var1)) {
            break label14;
         }

         return;
      }

      this.asyncSendToMasterAndForwardReply(new DeployMessages.RequestDriverStatus(this.submittedDriverID()), scala.reflect.ClassTag..MODULE$.apply(DeployMessages.DriverStatusResponse.class));
   }

   public void reportDriverStatus(final boolean found, final Option state, final Option workerId, final Option workerHostPort, final Option exception) {
      if (!found) {
         if (exception.exists((ex) -> BoxesRunTime.boxToBoolean($anonfun$reportDriverStatus$7(ex)))) {
            this.logDebug((Function0)(() -> "The status response is reported from a backup spark instance. So, ignored."));
         } else {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ERROR: Cluster master did not recognize ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, this.submittedDriverID())})))));
            System.exit(-1);
         }
      } else {
         if (!this.driverStatusReported()) {
            label106: {
               String id;
               String hostPort;
               label105: {
                  this.driverStatusReported_$eq(true);
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"State of ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, this.submittedDriverID())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" is ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_STATE..MODULE$, state.get())}))))));
                  Tuple3 var10 = new Tuple3(workerId, workerHostPort, state);
                  if (var10 != null) {
                     Option var11 = (Option)var10._1();
                     Option var12 = (Option)var10._2();
                     Option var13 = (Option)var10._3();
                     if (var11 instanceof Some) {
                        Some var14 = (Some)var11;
                        id = (String)var14.value();
                        if (var12 instanceof Some) {
                           Some var16 = (Some)var12;
                           hostPort = (String)var16.value();
                           if (var13 instanceof Some) {
                              Some var18 = (Some)var13;
                              Enumeration.Value var19 = (Enumeration.Value)var18.value();
                              Enumeration.Value var10000 = DriverState$.MODULE$.RUNNING();
                              if (var10000 == null) {
                                 if (var19 == null) {
                                    break label105;
                                 }
                              } else if (var10000.equals(var19)) {
                                 break label105;
                              }
                           }
                        }
                     }
                  }

                  BoxedUnit var29 = BoxedUnit.UNIT;
                  break label106;
               }

               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver running on ", " (", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, hostPort), new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, id)})))));
               BoxedUnit var30 = BoxedUnit.UNIT;
            }
         }

         if (exception instanceof Some) {
            Some var22 = (Some)exception;
            Exception e = (Exception)var22.value();
            this.logError((Function0)(() -> "Exception from cluster"), e);
            System.exit(-1);
            BoxedUnit var40 = BoxedUnit.UNIT;
         } else {
            boolean var35;
            label93: {
               label111: {
                  Enumeration.Value var24 = (Enumeration.Value)state.get();
                  Enumeration.Value var31 = DriverState$.MODULE$.FINISHED();
                  if (var31 == null) {
                     if (var24 == null) {
                        break label111;
                     }
                  } else if (var31.equals(var24)) {
                     break label111;
                  }

                  label112: {
                     var31 = DriverState$.MODULE$.FAILED();
                     if (var31 == null) {
                        if (var24 == null) {
                           break label112;
                        }
                     } else if (var31.equals(var24)) {
                        break label112;
                     }

                     label113: {
                        var31 = DriverState$.MODULE$.ERROR();
                        if (var31 == null) {
                           if (var24 == null) {
                              break label113;
                           }
                        } else if (var31.equals(var24)) {
                           break label113;
                        }

                        label71: {
                           var31 = DriverState$.MODULE$.KILLED();
                           if (var31 == null) {
                              if (var24 == null) {
                                 break label71;
                              }
                           } else if (var31.equals(var24)) {
                              break label71;
                           }

                           var35 = false;
                           break label93;
                        }

                        var35 = true;
                        break label93;
                     }

                     var35 = true;
                     break label93;
                  }

                  var35 = true;
                  break label93;
               }

               var35 = true;
            }

            if (var35) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"State of driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, this.submittedDriverID())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" is ", ", exiting spark-submit JVM."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_STATE..MODULE$, state.get())}))))));
               System.exit(0);
               BoxedUnit var36 = BoxedUnit.UNIT;
            } else if (!this.waitAppCompletion()) {
               this.logInfo((Function0)(() -> "spark-submit not configured to wait for completion,  exiting spark-submit JVM."));
               System.exit(0);
               BoxedUnit var37 = BoxedUnit.UNIT;
            } else {
               this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"State of driver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_ID..MODULE$, this.submittedDriverID())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" is ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_STATE..MODULE$, state.get())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"continue monitoring driver status."})))).log(scala.collection.immutable.Nil..MODULE$))));
               BoxedUnit var38 = BoxedUnit.UNIT;
            }

            BoxedUnit var39 = BoxedUnit.UNIT;
         }
      }
   }

   public PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final ClientEndpoint $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof DeployMessages.SubmitDriverResponse var5) {
               RpcEndpointRef master = var5.master();
               boolean success = var5.success();
               Option driverId = var5.driverId();
               String message = var5.message();
               this.$outer.logInfo((Function0)(() -> message));
               if (success) {
                  this.$outer.org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint_$eq(master);
                  this.$outer.org$apache$spark$deploy$ClientEndpoint$$submittedDriverID_$eq((String)driverId.get());
                  return BoxedUnit.UNIT;
               } else if (!org.apache.spark.util.Utils$.MODULE$.responseFromBackup(message)) {
                  System.exit(-1);
                  return BoxedUnit.UNIT;
               } else {
                  return BoxedUnit.UNIT;
               }
            } else if (x1 instanceof DeployMessages.KillDriverResponse var10) {
               RpcEndpointRef master = var10.master();
               boolean success = var10.success();
               String message = var10.message();
               this.$outer.logInfo((Function0)(() -> message));
               if (success) {
                  this.$outer.org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint_$eq(master);
                  return BoxedUnit.UNIT;
               } else if (!org.apache.spark.util.Utils$.MODULE$.responseFromBackup(message)) {
                  System.exit(-1);
                  return BoxedUnit.UNIT;
               } else {
                  return BoxedUnit.UNIT;
               }
            } else if (x1 instanceof DeployMessages.DriverStatusResponse var14) {
               boolean found = var14.found();
               Option state = var14.state();
               Option workerId = var14.workerId();
               Option workerHostPort = var14.workerHostPort();
               Option exception = var14.exception();
               this.$outer.reportDriverStatus(found, state, workerId, workerHostPort, exception);
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (x1 instanceof DeployMessages.SubmitDriverResponse) {
               return true;
            } else if (x1 instanceof DeployMessages.KillDriverResponse) {
               return true;
            } else {
               return x1 instanceof DeployMessages.DriverStatusResponse;
            }
         }

         public {
            if (ClientEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = ClientEndpoint.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void onDisconnected(final RpcAddress remoteAddress) {
      if (!this.lostMasters().contains(remoteAddress)) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error connecting to master ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)})))));
         this.lostMasters().$plus$eq(remoteAddress);
         if (this.lostMasters().size() >= this.masterEndpoints.size()) {
            this.logError((Function0)(() -> "No master is available, exiting."));
            System.exit(-1);
         }
      }
   }

   public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
      if (!this.lostMasters().contains(remoteAddress)) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error connecting to master (", ")."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)})))), cause);
         this.lostMasters().$plus$eq(remoteAddress);
         if (this.lostMasters().size() >= this.masterEndpoints.size()) {
            this.logError((Function0)(() -> "No master is available, exiting."));
            System.exit(-1);
         }
      }
   }

   public void onError(final Throwable cause) {
      this.logError((Function0)(() -> "Error processing messages, exiting."), cause);
      System.exit(-1);
   }

   public void onStop() {
      this.forwardMessageThread().shutdownNow();
   }

   // $FF: synthetic method
   public static final void $anonfun$forwardMessageExecutionContext$1(final ClientEndpoint $this, final Throwable t) {
      if (t instanceof InterruptedException) {
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else if (t != null) {
         $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, t.getMessage())})))), t);
         System.exit(SparkExitCode$.MODULE$.UNCAUGHT_EXCEPTION());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(t);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$asyncSendToMasterAndForwardReply$2(final ClientEndpoint $this, final RpcEndpointRef masterEndpoint$1, final Try x0$1) {
      if (x0$1 instanceof Success var5) {
         Object v = var5.value();
         $this.self().send(v);
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (x0$1 instanceof Failure var7) {
         Throwable e = var7.exception();
         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error sending messages to master "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterEndpoint$1)}))))), e);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$asyncSendToMasterAndForwardReply$1(final ClientEndpoint $this, final Object message$1, final ClassTag evidence$1$1, final RpcEndpointRef masterEndpoint) {
      masterEndpoint.ask(message$1, evidence$1$1).onComplete((x0$1) -> {
         $anonfun$asyncSendToMasterAndForwardReply$2($this, masterEndpoint, x0$1);
         return BoxedUnit.UNIT;
      }, $this.forwardMessageExecutionContext());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reportDriverStatus$7(final Exception e) {
      return org.apache.spark.util.Utils$.MODULE$.responseFromBackup(e.getMessage());
   }

   public ClientEndpoint(final RpcEnv rpcEnv, final ClientArguments driverArgs, final Seq masterEndpoints, final SparkConf conf) {
      this.rpcEnv = rpcEnv;
      this.driverArgs = driverArgs;
      this.masterEndpoints = masterEndpoints;
      this.conf = conf;
      RpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.forwardMessageThread = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("client-forward-message");
      this.forwardMessageExecutionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutor(this.forwardMessageThread(), (t) -> {
         $anonfun$forwardMessageExecutionContext$1(this, t);
         return BoxedUnit.UNIT;
      });
      this.lostMasters = new HashSet();
      this.org$apache$spark$deploy$ClientEndpoint$$activeMasterEndpoint = null;
      this.waitAppCompletion = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.STANDALONE_SUBMIT_WAIT_APP_COMPLETION()));
      this.REPORT_DRIVER_STATUS_INTERVAL = 10000;
      this.org$apache$spark$deploy$ClientEndpoint$$submittedDriverID = "";
      this.driverStatusReported = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
