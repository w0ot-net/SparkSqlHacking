package org.apache.spark.deploy.security;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.security.PrivilegedExceptionAction;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.security.HadoopDelegationTokenProvider;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.Map;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ef!\u0002\u0013&\u0001%z\u0003\u0002\u0003\u001f\u0001\u0005\u000b\u0007I\u0011\u0003 \t\u0011\r\u0003!\u0011!Q\u0001\n}B\u0001\u0002\u0012\u0001\u0003\u0006\u0004%\t\"\u0012\u0005\t\u001d\u0002\u0011\t\u0011)A\u0005\r\"Aq\n\u0001BC\u0002\u0013E\u0001\u000b\u0003\u0005X\u0001\t\u0005\t\u0015!\u0003R\u0011\u0015A\u0006\u0001\"\u0001Z\u0011\u001dy\u0006A1A\u0005\n\u0001Da\u0001\u001c\u0001!\u0002\u0013\t\u0007bB7\u0001\u0005\u0004%IA\u001c\u0005\u0007m\u0002\u0001\u000b\u0011B8\t\u000f]\u0004!\u0019!C\u0005q\"9\u00111\u0001\u0001!\u0002\u0013I\bbCA\u0003\u0001\u0001\u0007\t\u0019!C\u0005\u0003\u000fA1\"!\u0007\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\u001c!Y\u0011q\u0005\u0001A\u0002\u0003\u0005\u000b\u0015BA\u0005\u0011\u001d\tI\u0003\u0001C\u0001\u0003WAq!a\r\u0001\t\u0003\t)\u0004C\u0004\u0002D\u0001!\t!!\u0012\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002J!9\u0011q\t\u0001\u0005\n\u0005e\u0003bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003_\u0002A\u0011BA9\u0011\u001d\t9\b\u0001C\u0005\u0003kAq!!\u001f\u0001\t\u0013\tY\bC\u0004\u0002\b\u0002!I!!#\t\u000f\u0005-\u0005\u0001\"\u0003\u0002\u000e\u001eA\u0011qR\u0013\t\u0002%\n\tJB\u0004%K!\u0005\u0011&a%\t\rakB\u0011AAK\u0011!\t9*\bb\u0001\n\u0013q\u0007bBAM;\u0001\u0006Ia\u001c\u0005\n\u00037k\"\u0019!C\u0005\u0003;C\u0001\"a,\u001eA\u0003%\u0011q\u0014\u0005\b\u0003ckB\u0011AAZ\u0005qA\u0015\rZ8pa\u0012+G.Z4bi&|g\u000eV8lK:l\u0015M\\1hKJT!AJ\u0014\u0002\u0011M,7-\u001e:jifT!\u0001K\u0015\u0002\r\u0011,\u0007\u000f\\8z\u0015\tQ3&A\u0003ta\u0006\u00148N\u0003\u0002-[\u00051\u0011\r]1dQ\u0016T\u0011AL\u0001\u0004_J<7c\u0001\u00011mA\u0011\u0011\u0007N\u0007\u0002e)\t1'A\u0003tG\u0006d\u0017-\u0003\u00026e\t1\u0011I\\=SK\u001a\u0004\"a\u000e\u001e\u000e\u0003aR!!O\u0015\u0002\u0011%tG/\u001a:oC2L!a\u000f\u001d\u0003\u000f1{wmZ5oO\u0006I1\u000f]1sW\u000e{gNZ\u0002\u0001+\u0005y\u0004C\u0001!B\u001b\u0005I\u0013B\u0001\"*\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006ta\u0006\u00148nQ8oM\u0002\n!\u0002[1e_>\u00048i\u001c8g+\u00051\u0005CA$M\u001b\u0005A%BA%K\u0003\u0011\u0019wN\u001c4\u000b\u0005-[\u0013A\u00025bI>|\u0007/\u0003\u0002N\u0011\ni1i\u001c8gS\u001e,(/\u0019;j_:\f1\u0002[1e_>\u00048i\u001c8gA\u0005a1o\u00195fIVdWM\u001d*fMV\t\u0011\u000b\u0005\u0002S+6\t1K\u0003\u0002US\u0005\u0019!\u000f]2\n\u0005Y\u001b&A\u0004*qG\u0016sG\r]8j]R\u0014VMZ\u0001\u000eg\u000eDW\rZ;mKJ\u0014VM\u001a\u0011\u0002\rqJg.\u001b;?)\u0011QF,\u00180\u0011\u0005m\u0003Q\"A\u0013\t\u000bq:\u0001\u0019A \t\u000b\u0011;\u0001\u0019\u0001$\t\u000b=;\u0001\u0019A)\u0002\u0013A\u0014\u0018N\\2ja\u0006dW#A1\u0011\u0005\tLgBA2h!\t!''D\u0001f\u0015\t1W(\u0001\u0004=e>|GOP\u0005\u0003QJ\na\u0001\u0015:fI\u00164\u0017B\u00016l\u0005\u0019\u0019FO]5oO*\u0011\u0001NM\u0001\u000baJLgnY5qC2\u0004\u0013AB6fsR\f'-F\u0001p!\t\u0001X/D\u0001r\u0015\t\u00118/\u0001\u0003mC:<'\"\u0001;\u0002\t)\fg/Y\u0005\u0003UF\fqa[3zi\u0006\u0014\u0007%\u0001\reK2,w-\u0019;j_:$vn[3o!J|g/\u001b3feN,\u0012!\u001f\t\u0005Ej\fG0\u0003\u0002|W\n\u0019Q*\u00199\u0011\u0005u|X\"\u0001@\u000b\u0005\u0019J\u0013bAA\u0001}\ni\u0002*\u00193p_B$U\r\\3hCRLwN\u001c+pW\u0016t\u0007K]8wS\u0012,'/A\reK2,w-\u0019;j_:$vn[3o!J|g/\u001b3feN\u0004\u0013a\u0004:f]\u0016<\u0018\r\\#yK\u000e,Ho\u001c:\u0016\u0005\u0005%\u0001\u0003BA\u0006\u0003+i!!!\u0004\u000b\t\u0005=\u0011\u0011C\u0001\u000bG>t7-\u001e:sK:$(bAA\ng\u0006!Q\u000f^5m\u0013\u0011\t9\"!\u0004\u00031M\u001b\u0007.\u001a3vY\u0016$W\t_3dkR|'oU3sm&\u001cW-A\nsK:,w/\u00197Fq\u0016\u001cW\u000f^8s?\u0012*\u0017\u000f\u0006\u0003\u0002\u001e\u0005\r\u0002cA\u0019\u0002 %\u0019\u0011\u0011\u0005\u001a\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003Ky\u0011\u0011!a\u0001\u0003\u0013\t1\u0001\u001f\u00132\u0003A\u0011XM\\3xC2,\u00050Z2vi>\u0014\b%\u0001\bsK:,w/\u00197F]\u0006\u0014G.\u001a3\u0016\u0005\u00055\u0002cA\u0019\u00020%\u0019\u0011\u0011\u0007\u001a\u0003\u000f\t{w\u000e\\3b]\u0006)1\u000f^1siR\u0011\u0011q\u0007\t\u0006c\u0005e\u0012QH\u0005\u0004\u0003w\u0011$!B!se\u0006L\bcA\u0019\u0002@%\u0019\u0011\u0011\t\u001a\u0003\t\tKH/Z\u0001\u0005gR|\u0007\u000f\u0006\u0002\u0002\u001e\u00051rN\u0019;bS:$U\r\\3hCRLwN\u001c+pW\u0016t7\u000f\u0006\u0003\u0002\u001e\u0005-\u0003bBA')\u0001\u0007\u0011qJ\u0001\u0006GJ,Gm\u001d\t\u0005\u0003#\n)&\u0004\u0002\u0002T)\u0011aES\u0005\u0005\u0003/\n\u0019FA\u0006De\u0016$WM\u001c;jC2\u001cHCAA.!\u001d\t\u0014QLA(\u0003CJ1!a\u00183\u0005\u0019!V\u000f\u001d7feA\u0019\u0011'a\u0019\n\u0007\u0005\u0015$G\u0001\u0003M_:<\u0017\u0001E5t!J|g/\u001b3fe2{\u0017\rZ3e)\u0011\ti#a\u001b\t\r\u00055d\u00031\u0001b\u0003-\u0019XM\u001d<jG\u0016t\u0015-\\3\u0002\u001fM\u001c\u0007.\u001a3vY\u0016\u0014VM\\3xC2$B!!\b\u0002t!9\u0011QO\fA\u0002\u0005\u0005\u0014!\u00023fY\u0006L\u0018\u0001E;qI\u0006$X\rV8lK:\u001cH+Y:l\u0003yy'\r^1j]R{7.\u001a8t\u0003:$7k\u00195fIVdWMU3oK^\fG\u000e\u0006\u0003\u0002P\u0005u\u0004bBA@3\u0001\u0007\u0011\u0011Q\u0001\u0004k\u001eL\u0007\u0003BA)\u0003\u0007KA!!\"\u0002T\t!Rk]3s\u000fJ|W\u000f]%oM>\u0014X.\u0019;j_:\fq\u0001Z8M_\u001eLg\u000e\u0006\u0002\u0002\u0002\u0006iAn\\1e!J|g/\u001b3feN$\u0012!_\u0001\u001d\u0011\u0006$wn\u001c9EK2,w-\u0019;j_:$vn[3o\u001b\u0006t\u0017mZ3s!\tYVdE\u0002\u001eaY\"\"!!%\u0002+A\u0014xN^5eKJ,e.\u00192mK\u0012\u001cuN\u001c4jO\u00061\u0002O]8wS\u0012,'/\u00128bE2,GmQ8oM&<\u0007%\u0001\u0011eKB\u0014XmY1uK\u0012\u0004&o\u001c<jI\u0016\u0014XI\\1cY\u0016$7i\u001c8gS\u001e\u001cXCAAP!\u0015\t\t+a+p\u001b\t\t\u0019K\u0003\u0003\u0002&\u0006\u001d\u0016!C5n[V$\u0018M\u00197f\u0015\r\tIKM\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAW\u0003G\u0013A\u0001T5ti\u0006\tC-\u001a9sK\u000e\fG/\u001a3Qe>4\u0018\u000eZ3s\u000b:\f'\r\\3e\u0007>tg-[4tA\u0005\u0001\u0012n]*feZL7-Z#oC\ndW\r\u001a\u000b\u0007\u0003[\t),a.\t\u000bq\u001a\u0003\u0019A \t\r\u000554\u00051\u0001b\u0001"
)
public class HadoopDelegationTokenManager implements Logging {
   private final SparkConf sparkConf;
   private final Configuration hadoopConf;
   private final RpcEndpointRef schedulerRef;
   private final String principal;
   private final String keytab;
   private final Map delegationTokenProviders;
   private ScheduledExecutorService renewalExecutor;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean isServiceEnabled(final SparkConf sparkConf, final String serviceName) {
      return HadoopDelegationTokenManager$.MODULE$.isServiceEnabled(sparkConf, serviceName);
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

   public SparkConf sparkConf() {
      return this.sparkConf;
   }

   public Configuration hadoopConf() {
      return this.hadoopConf;
   }

   public RpcEndpointRef schedulerRef() {
      return this.schedulerRef;
   }

   private String principal() {
      return this.principal;
   }

   private String keytab() {
      return this.keytab;
   }

   private Map delegationTokenProviders() {
      return this.delegationTokenProviders;
   }

   private ScheduledExecutorService renewalExecutor() {
      return this.renewalExecutor;
   }

   private void renewalExecutor_$eq(final ScheduledExecutorService x$1) {
      this.renewalExecutor = x$1;
   }

   public boolean renewalEnabled() {
      String var2 = (String)this.sparkConf().get(package$.MODULE$.KERBEROS_RENEWAL_CREDENTIALS());
      switch (var2 == null ? 0 : var2.hashCode()) {
         case -1366264577:
            if ("ccache".equals(var2)) {
               return UserGroupInformation.getCurrentUser().hasKerberosCredentials();
            }
            break;
         case -1134653226:
            if ("keytab".equals(var2)) {
               return this.principal() != null;
            }
      }

      return false;
   }

   public byte[] start() {
      .MODULE$.require(this.renewalEnabled(), () -> "Token renewal must be enabled to start the renewer.");
      .MODULE$.require(this.schedulerRef() != null, () -> "Token renewal requires a scheduler endpoint.");
      this.renewalExecutor_$eq(ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("Credential Renewal Thread"));
      UserGroupInformation ugi = UserGroupInformation.getCurrentUser();
      if (ugi.isFromKeytab()) {
         Runnable tgtRenewalTask = new Runnable(ugi) {
            private final UserGroupInformation ugi$1;

            public void run() {
               this.ugi$1.checkTGTAndReloginFromKeytab();
            }

            public {
               this.ugi$1 = ugi$1;
            }
         };
         long tgtRenewalPeriod = BoxesRunTime.unboxToLong(this.sparkConf().get(package$.MODULE$.KERBEROS_RELOGIN_PERIOD()));
         this.renewalExecutor().scheduleAtFixedRate(tgtRenewalTask, tgtRenewalPeriod, tgtRenewalPeriod, TimeUnit.SECONDS);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.org$apache$spark$deploy$security$HadoopDelegationTokenManager$$updateTokensTask();
   }

   public void stop() {
      if (this.renewalExecutor() != null) {
         this.renewalExecutor().shutdownNow();
      }
   }

   public void obtainDelegationTokens(final Credentials creds) {
      UserGroupInformation currentUser = UserGroupInformation.getCurrentUser();
      boolean hasKerberosCreds = this.principal() != null || ((UserGroupInformation)scala.Option..MODULE$.apply(currentUser.getRealUser()).getOrElse(() -> currentUser)).hasKerberosCredentials();
      if (hasKerberosCreds) {
         UserGroupInformation freshUGI = this.doLogin();
         freshUGI.doAs(new PrivilegedExceptionAction(creds) {
            // $FF: synthetic field
            private final HadoopDelegationTokenManager $outer;
            private final Credentials creds$1;

            public void run() {
               Tuple2 var3 = this.$outer.org$apache$spark$deploy$security$HadoopDelegationTokenManager$$obtainDelegationTokens();
               if (var3 != null) {
                  Credentials newTokens = (Credentials)var3._1();
                  this.creds$1.addAll(newTokens);
               } else {
                  throw new MatchError(var3);
               }
            }

            public {
               if (HadoopDelegationTokenManager.this == null) {
                  throw null;
               } else {
                  this.$outer = HadoopDelegationTokenManager.this;
                  this.creds$1 = creds$1;
               }
            }
         });
         if (!currentUser.equals(freshUGI)) {
            FileSystem.closeAllForUGI(freshUGI);
         }
      }
   }

   public Tuple2 org$apache$spark$deploy$security$HadoopDelegationTokenManager$$obtainDelegationTokens() {
      Credentials creds = new Credentials();
      long nextRenewal = BoxesRunTime.unboxToLong(((IterableOnceOps)this.delegationTokenProviders().values().flatMap((provider) -> {
         if (provider.delegationTokensRequired(this.sparkConf(), this.hadoopConf())) {
            return provider.obtainDelegationTokens(this.hadoopConf(), this.sparkConf(), creds);
         } else {
            this.logDebug((Function0)(() -> "Service " + provider.serviceName() + " does not require a token. Check your configuration to see if security is disabled or not."));
            return scala.None..MODULE$;
         }
      })).foldLeft(BoxesRunTime.boxToLong(Long.MAX_VALUE), (JFunction2.mcJJJ.sp)(x, y) -> scala.math.package..MODULE$.min(x, y)));
      return new Tuple2(creds, BoxesRunTime.boxToLong(nextRenewal));
   }

   public boolean isProviderLoaded(final String serviceName) {
      return this.delegationTokenProviders().contains(serviceName);
   }

   public void org$apache$spark$deploy$security$HadoopDelegationTokenManager$$scheduleRenewal(final long delay) {
      long _delay = scala.math.package..MODULE$.max(0L, delay);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Scheduling renewal in ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, UIUtils$.MODULE$.formatDuration(_delay))})))));
      Runnable renewalTask = new Runnable() {
         // $FF: synthetic field
         private final HadoopDelegationTokenManager $outer;

         public void run() {
            this.$outer.org$apache$spark$deploy$security$HadoopDelegationTokenManager$$updateTokensTask();
         }

         public {
            if (HadoopDelegationTokenManager.this == null) {
               throw null;
            } else {
               this.$outer = HadoopDelegationTokenManager.this;
            }
         }
      };
      this.renewalExecutor().schedule(renewalTask, _delay, TimeUnit.MILLISECONDS);
   }

   public byte[] org$apache$spark$deploy$security$HadoopDelegationTokenManager$$updateTokensTask() {
      byte[] var10000;
      try {
         UserGroupInformation freshUGI = this.doLogin();
         Credentials creds = this.obtainTokensAndScheduleRenewal(freshUGI);
         byte[] tokens = SparkHadoopUtil$.MODULE$.get().serialize(creds);
         this.logInfo((Function0)(() -> "Updating delegation tokens."));
         this.schedulerRef().send(new CoarseGrainedClusterMessages.UpdateDelegationTokens(tokens));
         var10000 = tokens;
      } catch (InterruptedException var7) {
         var10000 = null;
      } catch (Exception var8) {
         long delay = TimeUnit.SECONDS.toMillis(BoxesRunTime.unboxToLong(this.sparkConf().get(package$.MODULE$.CREDENTIALS_RENEWAL_RETRY_WAIT())));
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to update tokens, will try again in "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "!"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, UIUtils$.MODULE$.formatDuration(delay))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" If this happens too often tasks will fail."})))).log(scala.collection.immutable.Nil..MODULE$))), var8);
         this.org$apache$spark$deploy$security$HadoopDelegationTokenManager$$scheduleRenewal(delay);
         var10000 = null;
      }

      return var10000;
   }

   private Credentials obtainTokensAndScheduleRenewal(final UserGroupInformation ugi) {
      return (Credentials)ugi.doAs(new PrivilegedExceptionAction() {
         // $FF: synthetic field
         private final HadoopDelegationTokenManager $outer;

         public Credentials run() {
            Tuple2 var3 = this.$outer.org$apache$spark$deploy$security$HadoopDelegationTokenManager$$obtainDelegationTokens();
            if (var3 != null) {
               Credentials creds = (Credentials)var3._1();
               long nextRenewal = var3._2$mcJ$sp();
               Tuple2 var2 = new Tuple2(creds, BoxesRunTime.boxToLong(nextRenewal));
               Credentials creds = (Credentials)var2._1();
               long nextRenewal = var2._2$mcJ$sp();
               long now = System.currentTimeMillis();
               double ratio = BoxesRunTime.unboxToDouble(this.$outer.sparkConf().get(package$.MODULE$.CREDENTIALS_RENEWAL_INTERVAL_RATIO()));
               long delay = (long)(ratio * (double)(nextRenewal - now));
               this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Calculated delay on renewal is ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DELAY..MODULE$, BoxesRunTime.boxToLong(delay))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" based on next renewal ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NEXT_RENEWAL_TIME..MODULE$, BoxesRunTime.boxToLong(nextRenewal))})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" and the ratio ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CREDENTIALS_RENEWAL_INTERVAL_RATIO..MODULE$, BoxesRunTime.boxToDouble(ratio))})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" and current time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_TIME..MODULE$, BoxesRunTime.boxToLong(now))}))))));
               this.$outer.org$apache$spark$deploy$security$HadoopDelegationTokenManager$$scheduleRenewal(delay);
               return creds;
            } else {
               throw new MatchError(var3);
            }
         }

         public {
            if (HadoopDelegationTokenManager.this == null) {
               throw null;
            } else {
               this.$outer = HadoopDelegationTokenManager.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   private UserGroupInformation doLogin() {
      if (this.principal() != null) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempting to login to KDC using principal: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PRINCIPAL..MODULE$, this.principal())})))));
         .MODULE$.require((new File(this.keytab())).isFile(), () -> "Cannot find keytab at " + this.keytab() + ".");
         UserGroupInformation ugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI(this.principal(), this.keytab());
         this.logInfo((Function0)(() -> "Successfully logged into KDC."));
         return ugi;
      } else if (!SparkHadoopUtil$.MODULE$.get().isProxyUser(UserGroupInformation.getCurrentUser())) {
         this.logInfo((Function0)(() -> "Attempting to load user's ticket cache."));
         String ccache = this.sparkConf().getenv("KRB5CCNAME");
         String user = (String)scala.Option..MODULE$.apply(this.sparkConf().getenv("KRB5PRINCIPAL")).getOrElse(() -> UserGroupInformation.getCurrentUser().getUserName());
         return UserGroupInformation.getUGIFromTicketCache(ccache, user);
      } else {
         return UserGroupInformation.getCurrentUser();
      }
   }

   private Map loadProviders() {
      ServiceLoader loader = ServiceLoader.load(HadoopDelegationTokenProvider.class, Utils$.MODULE$.getContextOrSparkClassLoader());
      ArrayBuffer providers = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Iterator iterator = loader.iterator();

      while(iterator.hasNext()) {
         try {
            providers.$plus$eq(iterator.next());
         } catch (Throwable var5) {
            this.logDebug((Function0)(() -> "Failed to load built in provider."), var5);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      return ((IterableOnceOps)((StrictOptimizedIterableOps)providers.filter((p) -> BoxesRunTime.boxToBoolean($anonfun$loadProviders$2(this, p)))).map((p) -> new Tuple2(p.serviceName(), p))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadProviders$2(final HadoopDelegationTokenManager $this, final HadoopDelegationTokenProvider p) {
      return HadoopDelegationTokenManager$.MODULE$.isServiceEnabled($this.sparkConf(), p.serviceName());
   }

   public HadoopDelegationTokenManager(final SparkConf sparkConf, final Configuration hadoopConf, final RpcEndpointRef schedulerRef) {
      this.sparkConf = sparkConf;
      this.hadoopConf = hadoopConf;
      this.schedulerRef = schedulerRef;
      Logging.$init$(this);
      this.principal = (String)((Option)sparkConf.get((ConfigEntry)package$.MODULE$.PRINCIPAL())).orNull(scala..less.colon.less..MODULE$.refl());
      this.keytab = (String)((Option)sparkConf.get((ConfigEntry)package$.MODULE$.KEYTAB())).map((uri) -> (new URI(uri)).getPath()).orNull(scala..less.colon.less..MODULE$.refl());
      .MODULE$.require(this.principal() == null == (this.keytab() == null), () -> "Both principal and keytab must be defined, or neither.");
      this.delegationTokenProviders = this.loadProviders();
      this.logDebug((Function0)(() -> {
         Iterable var10000 = this.delegationTokenProviders().keys();
         return "Using the following builtin delegation token providers: " + var10000.mkString(", ") + ".";
      }));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
