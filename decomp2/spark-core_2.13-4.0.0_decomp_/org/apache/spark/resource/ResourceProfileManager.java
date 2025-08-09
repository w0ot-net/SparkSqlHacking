package org.apache.spark.resource;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.SparkListenerResourceProfileAdded;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud!B\u0011#\u0001\u0011R\u0003\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011u\u0002!\u0011!Q\u0001\nyBQ\u0001\u0012\u0001\u0005\u0002\u0015CqA\u0013\u0001C\u0002\u0013%1\n\u0003\u0004[\u0001\u0001\u0006I\u0001\u0014\u0005\u000b7\u0002\u0001\n\u0011aA!\u0002\u0013a\u0006b\u00029\u0001\u0005\u0004%I!\u001d\u0005\u0007e\u0002\u0001\u000b\u0011B0\t\u000fM\u0004!\u0019!C\u0005i\"1Q\u000f\u0001Q\u0001\n5DqA\u001e\u0001C\u0002\u0013%q\u000f\u0003\u0004|\u0001\u0001\u0006I\u0001\u001f\u0005\by\u0002\u0011\r\u0011\"\u0003~\u0011\u001d\tI\u0002\u0001Q\u0001\nyD\u0001\"a\u0007\u0001\u0005\u0004%Ia\u001e\u0005\b\u0003;\u0001\u0001\u0015!\u0003y\u0011!\ty\u0002\u0001b\u0001\n\u00139\bbBA\u0011\u0001\u0001\u0006I\u0001\u001f\u0005\t\u0003G\u0001!\u0019!C\u0005o\"9\u0011Q\u0005\u0001!\u0002\u0013A\b\u0002CA\u0014\u0001\t\u0007I\u0011B<\t\u000f\u0005%\u0002\u0001)A\u0005q\"A\u00111\u0006\u0001C\u0002\u0013%q\u000fC\u0004\u0002.\u0001\u0001\u000b\u0011\u0002=\t\u0013\u0005=\u0002A1A\u0005\n\u0005E\u0002bBA\u001a\u0001\u0001\u0006Ia\u0016\u0005\b\u0003k\u0001A\u0011AA\u0019\u0011!\t9\u0004\u0001C\u0001I\u0005e\u0002\u0002CA \u0001\u0011\u0005A%!\u0011\t\u000f\u0005-\u0003\u0001\"\u0001\u0002N!9\u0011q\u000b\u0001\u0005\u0002\u0005e\u0003bBA0\u0001\u0011\u0005\u0011\u0011\r\u0002\u0017%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK6\u000bg.Y4fe*\u00111\u0005J\u0001\te\u0016\u001cx.\u001e:dK*\u0011QEJ\u0001\u0006gB\f'o\u001b\u0006\u0003O!\na!\u00199bG\",'\"A\u0015\u0002\u0007=\u0014xmE\u0002\u0001WE\u0002\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012a!\u00118z%\u00164\u0007C\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b%\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001c4\u0005\u001daunZ4j]\u001e\f\u0011b\u001d9be.\u001cuN\u001c4\u0004\u0001A\u0011!hO\u0007\u0002I%\u0011A\b\n\u0002\n'B\f'o[\"p]\u001a\f1\u0002\\5ti\u0016tWM\u001d\"vgB\u0011qHQ\u0007\u0002\u0001*\u0011\u0011\tJ\u0001\ng\u000eDW\rZ;mKJL!a\u0011!\u0003\u001f1Kg/\u001a'jgR,g.\u001a:CkN\fa\u0001P5oSRtDc\u0001$I\u0013B\u0011q\tA\u0007\u0002E!)qg\u0001a\u0001s!)Qh\u0001a\u0001}\u0005\u0011#/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133U_J+7o\\;sG\u0016\u0004&o\u001c4jY\u0016,\u0012\u0001\u0014\t\u0005\u001bJ#v+D\u0001O\u0015\ty\u0005+A\u0004nkR\f'\r\\3\u000b\u0005Ek\u0013AC2pY2,7\r^5p]&\u00111K\u0014\u0002\b\u0011\u0006\u001c\b.T1q!\taS+\u0003\u0002W[\t\u0019\u0011J\u001c;\u0011\u0005\u001dC\u0016BA-#\u0005=\u0011Vm]8ve\u000e,\u0007K]8gS2,\u0017a\t:fg>,(oY3Qe>4\u0017\u000e\\3JIR{'+Z:pkJ\u001cW\r\u0015:pM&dW\rI\u0001\u0004q\u0012\n\u0004\u0003\u0002\u0017^?6L!AX\u0017\u0003\rQ+\b\u000f\\33!\t\u00017.D\u0001b\u0015\t\u00117-\u0001\fSK\u0016tGO]1oiJ+\u0017\rZ,sSR,Gj\\2l\u0015\t!W-A\u0003m_\u000e\\7O\u0003\u0002gO\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005!L\u0017\u0001B;uS2T\u0011A[\u0001\u0005U\u00064\u0018-\u0003\u0002mC\nA!+Z1e\u0019>\u001c7\u000e\u0005\u0002a]&\u0011q.\u0019\u0002\n/JLG/\u001a'pG.\f\u0001B]3bI2{7m[\u000b\u0002?\u0006I!/Z1e\u0019>\u001c7\u000eI\u0001\noJLG/\u001a'pG.,\u0012!\\\u0001\u000boJLG/\u001a'pG.\u0004\u0013A\u00043z]\u0006l\u0017nY#oC\ndW\rZ\u000b\u0002qB\u0011A&_\u0005\u0003u6\u0012qAQ8pY\u0016\fg.A\bes:\fW.[2F]\u0006\u0014G.\u001a3!\u0003\u0019i\u0017m\u001d;feV\ta\u0010\u0005\u0003-\u007f\u0006\r\u0011bAA\u0001[\t1q\n\u001d;j_:\u0004B!!\u0002\u0002\u00149!\u0011qAA\b!\r\tI!L\u0007\u0003\u0003\u0017Q1!!\u00049\u0003\u0019a$o\\8u}%\u0019\u0011\u0011C\u0017\u0002\rA\u0013X\rZ3g\u0013\u0011\t)\"a\u0006\u0003\rM#(/\u001b8h\u0015\r\t\t\"L\u0001\b[\u0006\u001cH/\u001a:!\u0003\u0019I7/W1s]\u00069\u0011n]-be:\u0004\u0013!B5t\u0017b\u001a\u0018AB5t\u0017b\u001a\b%\u0001\u000ejgN#\u0018M\u001c3bY>tWm\u0014:M_\u000e\fGn\u00117vgR,'/A\u000ejgN#\u0018M\u001c3bY>tWm\u0014:M_\u000e\fGn\u00117vgR,'\u000fI\u0001\u0014]>$(+\u001e8oS:<WK\\5u)\u0016\u001cHo]\u0001\u0015]>$(+\u001e8oS:<WK\\5u)\u0016\u001cHo\u001d\u0011\u0002'Q,7\u000f^#yG\u0016\u0004H/[8o)\"\u0014xn\u001e8\u0002)Q,7\u000f^#yG\u0016\u0004H/[8o)\"\u0014xn\u001e8!\u00039!WMZ1vYR\u0004&o\u001c4jY\u0016,\u0012aV\u0001\u0010I\u00164\u0017-\u001e7u!J|g-\u001b7fA\u00051B-\u001a4bk2$(+Z:pkJ\u001cW\r\u0015:pM&dW-A\u0006jgN+\b\u000f]8si\u0016$Gc\u0001=\u0002<!1\u0011Q\b\u000fA\u0002]\u000b!A\u001d9\u0002\u001d\r\fgNQ3TG\",G-\u001e7fIR)\u00010a\u0011\u0002H!1\u0011QI\u000fA\u0002Q\u000b\u0001\u0002^1tWJ\u0003\u0018\n\u001a\u0005\u0007\u0003\u0013j\u0002\u0019\u0001+\u0002\u0019\u0015DXmY;u_J\u0014\u0006/\u00133\u0002%\u0005$GMU3t_V\u00148-\u001a)s_\u001aLG.\u001a\u000b\u0005\u0003\u001f\n)\u0006E\u0002-\u0003#J1!a\u0015.\u0005\u0011)f.\u001b;\t\r\u0005ub\u00041\u0001X\u0003U\u0011Xm]8ve\u000e,\u0007K]8gS2,gI]8n\u0013\u0012$2aVA.\u0011\u0019\tif\ba\u0001)\u0006!!\u000f]%e\u0003Q9W\r^#rk&4\u0018\r\\3oiB\u0013xNZ5mKR!\u00111MA3!\rasp\u0016\u0005\u0007\u0003{\u0001\u0003\u0019A,)\u0007\u0001\tI\u0007\u0005\u0003\u0002l\u0005ETBAA7\u0015\r\ty\u0007J\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA:\u0003[\u0012\u0001\"\u0012<pYZLgn\u001a"
)
public class ResourceProfileManager implements Logging {
   private final SparkConf sparkConf;
   private final LiveListenerBus listenerBus;
   private final HashMap resourceProfileIdToResourceProfile;
   // $FF: synthetic field
   private final Tuple2 x$1;
   private final ReentrantReadWriteLock.ReadLock readLock;
   private final ReentrantReadWriteLock.WriteLock writeLock;
   private final boolean dynamicEnabled;
   private final Option master;
   private final boolean isYarn;
   private final boolean isK8s;
   private final boolean isStandaloneOrLocalCluster;
   private final boolean notRunningUnitTests;
   private final boolean testExceptionThrown;
   private final ResourceProfile defaultProfile;
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

   private HashMap resourceProfileIdToResourceProfile() {
      return this.resourceProfileIdToResourceProfile;
   }

   private ReentrantReadWriteLock.ReadLock readLock() {
      return this.readLock;
   }

   private ReentrantReadWriteLock.WriteLock writeLock() {
      return this.writeLock;
   }

   private boolean dynamicEnabled() {
      return this.dynamicEnabled;
   }

   private Option master() {
      return this.master;
   }

   private boolean isYarn() {
      return this.isYarn;
   }

   private boolean isK8s() {
      return this.isK8s;
   }

   private boolean isStandaloneOrLocalCluster() {
      return this.isStandaloneOrLocalCluster;
   }

   private boolean notRunningUnitTests() {
      return this.notRunningUnitTests;
   }

   private boolean testExceptionThrown() {
      return this.testExceptionThrown;
   }

   private ResourceProfile defaultProfile() {
      return this.defaultProfile;
   }

   public ResourceProfile defaultResourceProfile() {
      return this.defaultProfile();
   }

   public boolean isSupported(final ResourceProfile rp) {
      .MODULE$.assert(this.master() != null);
      if (rp instanceof TaskResourceProfile && !this.dynamicEnabled()) {
         if ((this.notRunningUnitTests() || this.testExceptionThrown()) && !this.isStandaloneOrLocalCluster() && !this.isYarn() && !this.isK8s()) {
            throw new SparkException("TaskResourceProfiles are only supported for Standalone, Yarn and Kubernetes cluster for now when dynamic allocation is disabled.");
         }
      } else {
         boolean isNotDefaultProfile = rp.id() != ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
         boolean notYarnOrK8sOrStandaloneAndNotDefaultProfile = isNotDefaultProfile && !this.isYarn() && !this.isK8s() && !this.isStandaloneOrLocalCluster();
         boolean YarnOrK8sOrStandaloneNotDynAllocAndNotDefaultProfile = isNotDefaultProfile && (this.isYarn() || this.isK8s() || this.isStandaloneOrLocalCluster()) && !this.dynamicEnabled();
         if ((this.notRunningUnitTests() || this.testExceptionThrown()) && (notYarnOrK8sOrStandaloneAndNotDefaultProfile || YarnOrK8sOrStandaloneNotDynAllocAndNotDefaultProfile)) {
            throw new SparkException("ResourceProfiles are only supported on YARN and Kubernetes and Standalone with dynamic allocation enabled.");
         }

         if (this.isStandaloneOrLocalCluster() && this.dynamicEnabled() && rp.getExecutorCores().isEmpty() && this.sparkConf.getOption(package$.MODULE$.EXECUTOR_CORES().key()).isEmpty()) {
            this.logWarning((Function0)(() -> "Neither executor cores is set for resource profile, nor spark.executor.cores is explicitly set, you may get more executors allocated than expected. It's recommended to set executor cores explicitly. Please check SPARK-30299 for more details."));
         }
      }

      return true;
   }

   public boolean canBeScheduled(final int taskRpId, final int executorRpId) {
      .MODULE$.assert(this.resourceProfileIdToResourceProfile().contains(BoxesRunTime.boxToInteger(taskRpId)) && this.resourceProfileIdToResourceProfile().contains(BoxesRunTime.boxToInteger(executorRpId)), () -> "Tasks and executors must have valid resource profile id");
      ResourceProfile taskRp = this.resourceProfileFromId(taskRpId);
      return taskRpId == executorRpId || !this.dynamicEnabled() && taskRp instanceof TaskResourceProfile;
   }

   public void addResourceProfile(final ResourceProfile rp) {
      this.isSupported(rp);
      boolean putNewProfile = false;
      this.writeLock().lock();

      try {
         if (!this.resourceProfileIdToResourceProfile().contains(BoxesRunTime.boxToInteger(rp.id()))) {
            Option prev = this.resourceProfileIdToResourceProfile().put(BoxesRunTime.boxToInteger(rp.id()), rp);
            if (prev.isEmpty()) {
               putNewProfile = true;
            }
         }
      } finally {
         this.writeLock().unlock();
      }

      if (putNewProfile) {
         rp.limitingResource(this.sparkConf);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added ResourceProfile id: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rp.id()))})))));
         this.listenerBus.post(new SparkListenerResourceProfileAdded(rp));
      }
   }

   public ResourceProfile resourceProfileFromId(final int rpId) {
      this.readLock().lock();

      ResourceProfile var10000;
      try {
         var10000 = (ResourceProfile)this.resourceProfileIdToResourceProfile().getOrElse(BoxesRunTime.boxToInteger(rpId), () -> {
            throw new SparkException("ResourceProfileId " + rpId + " not found!");
         });
      } finally {
         this.readLock().unlock();
      }

      return var10000;
   }

   public Option getEquivalentProfile(final ResourceProfile rp) {
      this.readLock().lock();

      Option var10000;
      try {
         var10000 = this.resourceProfileIdToResourceProfile().find((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getEquivalentProfile$1(rp, x0$1))).map((x$2) -> (ResourceProfile)x$2._2());
      } finally {
         this.readLock().unlock();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getEquivalentProfile$1(final ResourceProfile rp$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         ResourceProfile rpEntry = (ResourceProfile)x0$1._2();
         return rpEntry.resourcesEqual(rp$2);
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ResourceProfileManager(final SparkConf sparkConf, final LiveListenerBus listenerBus) {
      this.sparkConf = sparkConf;
      this.listenerBus = listenerBus;
      Logging.$init$(this);
      this.resourceProfileIdToResourceProfile = new HashMap();
      ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
      Tuple2 var4 = new Tuple2(lock.readLock(), lock.writeLock());
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         ReentrantReadWriteLock.ReadLock readLock = (ReentrantReadWriteLock.ReadLock)var4._1();
         ReentrantReadWriteLock.WriteLock writeLock = (ReentrantReadWriteLock.WriteLock)var4._2();
         this.x$1 = new Tuple2(readLock, writeLock);
         this.readLock = (ReentrantReadWriteLock.ReadLock)this.x$1._1();
         this.writeLock = (ReentrantReadWriteLock.WriteLock)this.x$1._2();
         this.dynamicEnabled = Utils$.MODULE$.isDynamicAllocationEnabled(sparkConf);
         this.master = sparkConf.getOption("spark.master");
         this.isYarn = this.master().isDefined() && ((String)this.master().get()).equals("yarn");
         this.isK8s = this.master().isDefined() && ((String)this.master().get()).startsWith("k8s://");
         this.isStandaloneOrLocalCluster = this.master().isDefined() && (((String)this.master().get()).startsWith("spark://") || ((String)this.master().get()).startsWith("local-cluster"));
         this.notRunningUnitTests = !Utils$.MODULE$.isTesting();
         this.testExceptionThrown = BoxesRunTime.unboxToBoolean(sparkConf.get(Tests$.MODULE$.RESOURCE_PROFILE_MANAGER_TESTING()));
         this.defaultProfile = ResourceProfile$.MODULE$.getOrCreateDefaultProfile(sparkConf);
         this.addResourceProfile(this.defaultProfile());
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
