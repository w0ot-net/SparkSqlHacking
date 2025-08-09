package org.apache.spark.deploy.yarn;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Collections;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.client.api.NMClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.ipc.YarnRPC;
import org.apache.hadoop.yarn.util.Records;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.network.util.JavaUtils;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf!B\u0010!\u0001\u0001R\u0003\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011\u001d\u0003!\u0011!Q\u0001\n!C\u0001\"\u0014\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005'\"Aa\f\u0001B\u0001B\u0003%1\u000b\u0003\u0005`\u0001\t\u0005\t\u0015!\u0003T\u0011!\u0001\u0007A!A!\u0002\u0013\t\u0007\u0002\u00033\u0001\u0005\u0003\u0005\u000b\u0011B1\t\u0011\u0015\u0004!\u0011!Q\u0001\nMC\u0001B\u001a\u0001\u0003\u0002\u0003\u0006Ia\u001a\u0005\tU\u0002\u0011\t\u0011)A\u0005W\"A\u0011\u000f\u0001B\u0001B\u0003%\u0011\rC\u0003s\u0001\u0011\u00051\u000fC\u0005\u0002\u0006\u0001\u0001\r\u0011\"\u0001\u0002\b!I\u0011Q\u0003\u0001A\u0002\u0013\u0005\u0011q\u0003\u0005\t\u0003G\u0001\u0001\u0015)\u0003\u0002\n!Y\u0011Q\u0005\u0001A\u0002\u0003\u0007I\u0011AA\u0014\u0011-\t9\u0004\u0001a\u0001\u0002\u0004%\t!!\u000f\t\u0017\u0005u\u0002\u00011A\u0001B\u0003&\u0011\u0011\u0006\u0005\b\u0003\u007f\u0001A\u0011AA!\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBq!!\u0014\u0001\t\u0003\ty\u0005\u0003\u0005\u0002l\u0001!\t\u0001IA7\u0011\u001d\tI\b\u0001C\u0005\u0003wBq!a$\u0001\t\u0013\t\tj\u0002\u0005\u0002$\u0002B\t\u0001IAS\r\u001dy\u0002\u0005#\u0001!\u0003OCaA]\u000e\u0005\u0002\u0005%\u0006BCAV7\t\u0007I\u0011\u0001\u0011\u0002.\"A\u0011\u0011X\u000e!\u0002\u0013\tyK\u0001\tFq\u0016\u001cW\u000f^8s%Vtg.\u00192mK*\u0011\u0011EI\u0001\u0005s\u0006\u0014hN\u0003\u0002$I\u00051A-\u001a9m_fT!!\n\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dB\u0013AB1qC\u000eDWMC\u0001*\u0003\ry'oZ\n\u0004\u0001-\n\u0004C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#AB!osJ+g\r\u0005\u00023k5\t1G\u0003\u00025I\u0005A\u0011N\u001c;fe:\fG.\u0003\u00027g\t9Aj\\4hS:<\u0017!C2p]R\f\u0017N\\3s\u0007\u0001\u00012\u0001\f\u001e=\u0013\tYTF\u0001\u0004PaRLwN\u001c\t\u0003{\u0015k\u0011A\u0010\u0006\u0003\u007f\u0001\u000bqA]3d_J$7O\u0003\u0002B\u0005\u0006\u0019\u0011\r]5\u000b\u0005\u0005\u001a%B\u0001#'\u0003\u0019A\u0017\rZ8pa&\u0011aI\u0010\u0002\n\u0007>tG/Y5oKJ\fAaY8oMB\u0011\u0011jS\u0007\u0002\u0015*\u0011qIQ\u0005\u0003\u0019*\u0013\u0011#W1s]\u000e{gNZ5hkJ\fG/[8o\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0002P!6\tA%\u0003\u0002RI\tI1\u000b]1sW\u000e{gNZ\u0001\u000e[\u0006\u001cH/\u001a:BI\u0012\u0014Xm]:\u0011\u0005Q[fBA+Z!\t1V&D\u0001X\u0015\tA\u0006(\u0001\u0004=e>|GOP\u0005\u000356\na\u0001\u0015:fI\u00164\u0017B\u0001/^\u0005\u0019\u0019FO]5oO*\u0011!,L\u0001\u000bKb,7-\u001e;pe&#\u0017\u0001\u00035pgRt\u0017-\\3\u0002\u001d\u0015DXmY;u_JlU-\\8ssB\u0011AFY\u0005\u0003G6\u00121!\u00138u\u00035)\u00070Z2vi>\u00148i\u001c:fg\u0006)\u0011\r\u001d9JI\u0006Y1/Z2ve&$\u00180T4s!\ty\u0005.\u0003\u0002jI\ty1+Z2ve&$\u00180T1oC\u001e,'/\u0001\bm_\u000e\fGNU3t_V\u00148-Z:\u0011\tQc7K\\\u0005\u0003[v\u00131!T1q!\tit.\u0003\u0002q}\tiAj\\2bYJ+7o\\;sG\u0016\f\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z%e\u0003\u0019a\u0014N\\5u}QyAO^<ysj\\H0 @\u0000\u0003\u0003\t\u0019\u0001\u0005\u0002v\u00015\t\u0001\u0005C\u00038\u001b\u0001\u0007\u0011\bC\u0003H\u001b\u0001\u0007\u0001\nC\u0003N\u001b\u0001\u0007a\nC\u0003S\u001b\u0001\u00071\u000bC\u0003_\u001b\u0001\u00071\u000bC\u0003`\u001b\u0001\u00071\u000bC\u0003a\u001b\u0001\u0007\u0011\rC\u0003e\u001b\u0001\u0007\u0011\rC\u0003f\u001b\u0001\u00071\u000bC\u0003g\u001b\u0001\u0007q\rC\u0003k\u001b\u0001\u00071\u000eC\u0003r\u001b\u0001\u0007\u0011-A\u0002sa\u000e,\"!!\u0003\u0011\t\u0005-\u0011\u0011C\u0007\u0003\u0003\u001bQ1!a\u0004C\u0003\rI\u0007oY\u0005\u0005\u0003'\tiAA\u0004ZCJt'\u000bU\"\u0002\u000fI\u00048m\u0018\u0013fcR!\u0011\u0011DA\u0010!\ra\u00131D\u0005\u0004\u0003;i#\u0001B+oSRD\u0011\"!\t\u0010\u0003\u0003\u0005\r!!\u0003\u0002\u0007a$\u0013'\u0001\u0003sa\u000e\u0004\u0013\u0001\u00038n\u00072LWM\u001c;\u0016\u0005\u0005%\u0002\u0003BA\u0016\u0003gi!!!\f\u000b\u0007\u0005\u000byCC\u0002\u00022\t\u000baa\u00197jK:$\u0018\u0002BA\u001b\u0003[\u0011\u0001BT'DY&,g\u000e^\u0001\r]6\u001cE.[3oi~#S-\u001d\u000b\u0005\u00033\tY\u0004C\u0005\u0002\"I\t\t\u00111\u0001\u0002*\u0005Ia.\\\"mS\u0016tG\u000fI\u0001\u0004eVtGCAA\r\u0003Ya\u0017-\u001e8dQ\u000e{g\u000e^3yi\u0012+'-^4J]\u001a|GCAA$!\r\u0011\u0014\u0011J\u0005\u0004\u0003\u0017\u001a$AE'fgN\fw-Z,ji\"\u001cuN\u001c;fqR\fab\u001d;beR\u001cuN\u001c;bS:,'\u000f\u0006\u0002\u0002RA9\u00111KA/'\u0006}SBAA+\u0015\u0011\t9&!\u0017\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u00037\nAA[1wC&\u0019Q.!\u0016\u0011\t\u0005\u0005\u0014qM\u0007\u0003\u0003GRA!!\u001a\u0002Z\u0005\u0019a.[8\n\t\u0005%\u00141\r\u0002\u000b\u0005f$XMQ;gM\u0016\u0014\u0018\u0001F2p]\u001aLw-\u001e:f'\u0016\u0014h/[2f\t\u0006$\u0018\r\u0006\u0003\u0002\u001a\u0005=\u0004bBA9/\u0001\u0007\u00111O\u0001\u0004GRD\bcA\u001f\u0002v%\u0019\u0011q\u000f \u0003-\r{g\u000e^1j]\u0016\u0014H*Y;oG\"\u001cuN\u001c;fqR\fa\u0002\u001d:fa\u0006\u0014XmQ8n[\u0006tG\r\u0006\u0002\u0002~A)\u0011qPAE':!\u0011\u0011QAC\u001d\r1\u00161Q\u0005\u0002]%\u0019\u0011qQ\u0017\u0002\u000fA\f7m[1hK&!\u00111RAG\u0005\u0011a\u0015n\u001d;\u000b\u0007\u0005\u001dU&\u0001\nqe\u0016\u0004\u0018M]3F]ZL'o\u001c8nK:$HCAAJ!\u0019\t)*a(T'6\u0011\u0011q\u0013\u0006\u0005\u00033\u000bY*A\u0004nkR\f'\r\\3\u000b\u0007\u0005uU&\u0001\u0006d_2dWm\u0019;j_:LA!!)\u0002\u0018\n9\u0001*Y:i\u001b\u0006\u0004\u0018\u0001E#yK\u000e,Ho\u001c:Sk:t\u0017M\u00197f!\t)8d\u0005\u0002\u001cWQ\u0011\u0011QU\u0001\u000b'\u0016\u001b%+\u0012+`\u0017\u0016KVCAAX!\u0011\t\t,a.\u000e\u0005\u0005M&\u0002BA[\u00033\nA\u0001\\1oO&\u0019A,a-\u0002\u0017M+5IU#U?.+\u0015\f\t"
)
public class ExecutorRunnable implements Logging {
   private final Option container;
   private final YarnConfiguration conf;
   private final SparkConf sparkConf;
   private final String masterAddress;
   private final String executorId;
   private final String hostname;
   private final int executorMemory;
   private final int executorCores;
   private final String appId;
   private final SecurityManager securityMgr;
   private final Map localResources;
   private final int resourceProfileId;
   private YarnRPC rpc;
   private NMClient nmClient;
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

   public YarnRPC rpc() {
      return this.rpc;
   }

   public void rpc_$eq(final YarnRPC x$1) {
      this.rpc = x$1;
   }

   public NMClient nmClient() {
      return this.nmClient;
   }

   public void nmClient_$eq(final NMClient x$1) {
      this.nmClient = x$1;
   }

   public void run() {
      this.logDebug((Function0)(() -> "Starting Executor Container"));
      this.nmClient_$eq(NMClient.createNMClient());
      this.nmClient().init(this.conf);
      this.nmClient().start();
      this.startContainer();
   }

   public MessageWithContext launchContextDebugInfo() {
      List commands = this.prepareCommand();
      HashMap env = this.prepareEnvironment();
      return this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"\n        |===============================================================================\n        |Default YARN executor launch context:\n        |  env:\n        |", "\n        |  command:\n        |    ", "\n        |\n        |  resources:\n        |", "\n        |==============================================================================="})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ENVS..MODULE$, ((IterableOnceOps)org.apache.spark.util.Utils..MODULE$.redact(this.sparkConf, env.toSeq()).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return "    " + k + " -> " + v + "\n";
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString()), new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_LAUNCH_COMMANDS..MODULE$, org.apache.spark.util.Utils..MODULE$.redactCommandLineArgs(this.sparkConf, commands).mkString(" \\ \n      ")), new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_RESOURCES..MODULE$, ((IterableOnceOps)this.localResources.map((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            LocalResource v = (LocalResource)x0$2._2();
            return "    " + k + " -> " + v + "\n";
         } else {
            throw new MatchError(x0$2);
         }
      })).mkString())}))).stripMargin();
   }

   public java.util.Map startContainer() {
      ContainerLaunchContext ctx = (ContainerLaunchContext)Records.newRecord(ContainerLaunchContext.class);
      java.util.Map env = scala.jdk.CollectionConverters..MODULE$.MutableMapHasAsJava(this.prepareEnvironment()).asJava();
      ctx.setLocalResources(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.localResources).asJava());
      ctx.setEnvironment(env);
      Credentials credentials = UserGroupInformation.getCurrentUser().getCredentials();
      DataOutputBuffer dob = new DataOutputBuffer();
      credentials.writeTokenStorageToStream(dob);
      ctx.setTokens(ByteBuffer.wrap(dob.getData()));
      List commands = this.prepareCommand();
      ctx.setCommands(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(commands).asJava());
      ctx.setApplicationACLs(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(YarnSparkHadoopUtil$.MODULE$.getApplicationAclsForYarn(this.securityMgr)).asJava());
      if (BoxesRunTime.unboxToBoolean(this.sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVICE_ENABLED()))) {
         this.configureServiceData(ctx);
      }

      try {
         return this.nmClient().startContainer((Container)this.container.get(), ctx);
      } catch (Exception var7) {
         throw new SparkException("Exception while starting container " + ((Container)this.container.get()).getId() + " on host " + this.hostname, var7);
      }
   }

   public void configureServiceData(final ContainerLaunchContext ctx) {
      String secretString = this.securityMgr.getSecretKey();
      String serviceName = (String)this.sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVICE_NAME());
      if (!BoxesRunTime.unboxToBoolean(this.sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVER_RECOVERY_DISABLED()))) {
         ByteBuffer secretBytes = secretString != null ? JavaUtils.stringToBytes(secretString) : ByteBuffer.allocate(0);
         ctx.setServiceData(Collections.singletonMap(serviceName, secretBytes));
      } else {
         HashMap payload = new HashMap();
         payload.put(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVER_RECOVERY_DISABLED().key(), Boolean.TRUE);
         if (secretString != null) {
            payload.put(ExecutorRunnable$.MODULE$.SECRET_KEY(), secretString);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         ObjectMapper mapper = new ObjectMapper();
         mapper.registerModule(com.fasterxml.jackson.module.scala.DefaultScalaModule..MODULE$);
         String jsonString = mapper.writeValueAsString(payload);
         ctx.setServiceData(Collections.singletonMap(serviceName, JavaUtils.stringToBytes(jsonString)));
      }
   }

   private List prepareCommand() {
      ListBuffer javaOpts = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      String executorMemoryString = this.executorMemory + "m";
      javaOpts.$plus$eq("-Xmx" + executorMemoryString);
      ((Option)this.sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_JAVA_OPTIONS())).foreach((opts) -> {
         String subsOpt = org.apache.spark.util.Utils..MODULE$.substituteAppNExecIds(opts, this.appId, this.executorId);
         return (ListBuffer)javaOpts.$plus$plus$eq((IterableOnce)org.apache.spark.util.Utils..MODULE$.splitCommandString(subsOpt).map((arg) -> YarnSparkHadoopUtil$.MODULE$.escapeForShell(arg)));
      });
      Option prefixEnv = ((Option)this.sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_LIBRARY_PATH())).map((libPath) -> Client$.MODULE$.createLibraryPathPrefix(libPath, this.sparkConf));
      Path var10001 = new Path(Environment.PWD.$$(), "./tmp");
      javaOpts.$plus$eq("-Djava.io.tmpdir=" + var10001);
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.sparkConf.getAll()), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$prepareCommand$4(x0$1)))), (x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return (ListBuffer)javaOpts.$plus$eq(YarnSparkHadoopUtil$.MODULE$.escapeForShell("-D" + k + "=" + v));
         } else {
            throw new MatchError(x0$2);
         }
      });
      javaOpts.$plus$eq("-Dspark.yarn.app.container.log.dir=<LOG_DIR>");
      YarnSparkHadoopUtil$.MODULE$.addOutOfMemoryErrorArgument(javaOpts);
      Iterable commands = (Iterable)((IterableOps)((IterableOps)((IterableOps)scala.Option..MODULE$.option2Iterable(prefixEnv).$plus$plus(new scala.collection.immutable..colon.colon(Environment.JAVA_HOME.$$() + "/bin/java", new scala.collection.immutable..colon.colon("-server", scala.collection.immutable.Nil..MODULE$)))).$plus$plus(javaOpts)).$plus$plus(scala.package..MODULE$.Seq().apply(.MODULE$.wrapRefArray((Object[])(new String[]{"org.apache.spark.executor.YarnCoarseGrainedExecutorBackend", "--driver-url", this.masterAddress, "--executor-id", this.executorId, "--hostname", this.hostname, "--cores", Integer.toString(this.executorCores), "--app-id", this.appId, "--resourceProfileId", Integer.toString(this.resourceProfileId)}))))).$plus$plus(new scala.collection.immutable..colon.colon("1><LOG_DIR>/stdout", new scala.collection.immutable..colon.colon("2><LOG_DIR>/stderr", scala.collection.immutable.Nil..MODULE$)));
      return ((IterableOnceOps)commands.map((s) -> s == null ? "null" : s)).toList();
   }

   private HashMap prepareEnvironment() {
      HashMap env = new HashMap();
      Client$.MODULE$.populateClasspath((ClientArguments)null, this.conf, this.sparkConf, env, (Option)this.sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_CLASS_PATH()));
      ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(System.getenv()).asScala().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$prepareEnvironment$1(x0$1)))).foreach((x0$2) -> {
         $anonfun$prepareEnvironment$2(env, x0$2);
         return BoxedUnit.UNIT;
      });
      this.sparkConf.getExecutorEnv().foreach((x0$3) -> {
         $anonfun$prepareEnvironment$3(env, x0$3);
         return BoxedUnit.UNIT;
      });
      return env;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareCommand$4(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return org.apache.spark.SparkConf..MODULE$.isExecutorStartupConf(k);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareEnvironment$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith("SPARK");
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareEnvironment$2(final HashMap env$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         String v = (String)x0$2._2();
         env$1.update(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareEnvironment$3(final HashMap env$1, final Tuple2 x0$3) {
      if (x0$3 == null) {
         throw new MatchError(x0$3);
      } else {
         String key;
         String value;
         label19: {
            key = (String)x0$3._1();
            value = (String)x0$3._2();
            String var6 = Environment.CLASSPATH.name();
            if (key == null) {
               if (var6 == null) {
                  break label19;
               }
            } else if (key.equals(var6)) {
               break label19;
            }

            env$1.update(key, value);
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }

         YarnSparkHadoopUtil$.MODULE$.addPathToEnvironment(env$1, key, value);
         BoxedUnit var7 = BoxedUnit.UNIT;
      }
   }

   public ExecutorRunnable(final Option container, final YarnConfiguration conf, final SparkConf sparkConf, final String masterAddress, final String executorId, final String hostname, final int executorMemory, final int executorCores, final String appId, final SecurityManager securityMgr, final Map localResources, final int resourceProfileId) {
      this.container = container;
      this.conf = conf;
      this.sparkConf = sparkConf;
      this.masterAddress = masterAddress;
      this.executorId = executorId;
      this.hostname = hostname;
      this.executorMemory = executorMemory;
      this.executorCores = executorCores;
      this.appId = appId;
      this.securityMgr = securityMgr;
      this.localResources = localResources;
      this.resourceProfileId = resourceProfileId;
      Logging.$init$(this);
      this.rpc = YarnRPC.create(conf);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
