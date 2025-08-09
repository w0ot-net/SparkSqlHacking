package org.apache.spark.deploy.security;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.Master;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenIdentifier;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.security.HadoopDelegationTokenProvider;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!B\t\u0013\u0001Qa\u0002\"\u0002\u0018\u0001\t\u0003\u0001\u0004bB\u001a\u0001\u0001\u0004%I\u0001\u000e\u0005\bw\u0001\u0001\r\u0011\"\u0003=\u0011\u0019\u0011\u0005\u0001)Q\u0005k!91\t\u0001b\u0001\n\u0003\"\u0005B\u0002)\u0001A\u0003%Q\tC\u0003R\u0001\u0011\u0005#\u000bC\u0003k\u0001\u0011\u00053\u000eC\u0003r\u0001\u0011%!\u000fC\u0003v\u0001\u0011%a\u000fC\u0004\u0002\u0012\u0001!I!a\u0005\t\u000f\u0005e\u0001\u0001\"\u0003\u0002\u001c\u001dA\u0011Q\u0007\n\t\u0002Q\t9DB\u0004\u0012%!\u0005A#!\u000f\t\r9rA\u0011AA\u001e\u0011\u001d\tiD\u0004C\u0001\u0003\u007f\u0011q\u0004S1e_>\u0004hi\u0015#fY\u0016<\u0017\r^5p]R{7.\u001a8Qe>4\u0018\u000eZ3s\u0015\t\u0019B#\u0001\u0005tK\u000e,(/\u001b;z\u0015\t)b#\u0001\u0004eKBdw.\u001f\u0006\u0003/a\tQa\u001d9be.T!!\u0007\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0012aA8sON!\u0001!H\u0012)!\tq\u0012%D\u0001 \u0015\u0005\u0001\u0013!B:dC2\f\u0017B\u0001\u0012 \u0005\u0019\te.\u001f*fMB\u0011AEJ\u0007\u0002K)\u00111CF\u0005\u0003O\u0015\u0012Q\u0004S1e_>\u0004H)\u001a7fO\u0006$\u0018n\u001c8U_.,g\u000e\u0015:pm&$WM\u001d\t\u0003S1j\u0011A\u000b\u0006\u0003WY\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003[)\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\t\u0004C\u0001\u001a\u0001\u001b\u0005\u0011\u0012\u0001\u0006;pW\u0016t'+\u001a8fo\u0006d\u0017J\u001c;feZ\fG.F\u00016!\rqb\u0007O\u0005\u0003o}\u0011aa\u00149uS>t\u0007C\u0001\u0010:\u0013\tQtD\u0001\u0003M_:<\u0017\u0001\u0007;pW\u0016t'+\u001a8fo\u0006d\u0017J\u001c;feZ\fGn\u0018\u0013fcR\u0011Q\b\u0011\t\u0003=yJ!aP\u0010\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003\u000e\t\t\u00111\u00016\u0003\rAH%M\u0001\u0016i>\\WM\u001c*f]\u0016<\u0018\r\\%oi\u0016\u0014h/\u00197!\u0003-\u0019XM\u001d<jG\u0016t\u0015-\\3\u0016\u0003\u0015\u0003\"AR'\u000f\u0005\u001d[\u0005C\u0001% \u001b\u0005I%B\u0001&0\u0003\u0019a$o\\8u}%\u0011AjH\u0001\u0007!J,G-\u001a4\n\u00059{%AB*ue&twM\u0003\u0002M?\u0005a1/\u001a:wS\u000e,g*Y7fA\u00051rN\u0019;bS:$U\r\\3hCRLwN\u001c+pW\u0016t7\u000f\u0006\u00036'v\u001b\u0007\"\u0002+\b\u0001\u0004)\u0016A\u00035bI>|\u0007oQ8oMB\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\u0005G>tgM\u0003\u0002[1\u00051\u0001.\u00193p_BL!\u0001X,\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0011\u0015qv\u00011\u0001`\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0002aC6\ta#\u0003\u0002c-\tI1\u000b]1sW\u000e{gN\u001a\u0005\u0006I\u001e\u0001\r!Z\u0001\u0006GJ,Gm\u001d\t\u0003M\"l\u0011a\u001a\u0006\u0003'eK!![4\u0003\u0017\r\u0013X\rZ3oi&\fGn]\u0001\u0019I\u0016dWmZ1uS>tGk\\6f]N\u0014V-];je\u0016$Gc\u00017paB\u0011a$\\\u0005\u0003]~\u0011qAQ8pY\u0016\fg\u000eC\u0003_\u0011\u0001\u0007q\fC\u0003U\u0011\u0001\u0007Q+A\bhKR$vn[3o%\u0016tWm^3s)\r)5\u000f\u001e\u0005\u0006=&\u0001\ra\u0018\u0005\u0006)&\u0001\r!V\u0001\u0016M\u0016$8\r\u001b#fY\u0016<\u0017\r^5p]R{7.\u001a8t)\u001d)w/_A\u0005\u0003\u0017AQ\u0001\u001f\u0006A\u0002\u0015\u000bqA]3oK^,'\u000fC\u0003{\u0015\u0001\u000710A\u0006gS2,7/_:uK6\u001c\bc\u0001$}}&\u0011Qp\u0014\u0002\u0004'\u0016$\bcA@\u0002\u00065\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u0007I\u0016A\u00014t\u0013\u0011\t9!!\u0001\u0003\u0015\u0019KG.Z*zgR,W\u000eC\u0003e\u0015\u0001\u0007Q\rC\u0004\u0002\u000e)\u0001\r!a\u0004\u0002\u0017\u0019\u001cHk\\#yG2,H-\u001a\t\u0004\rr,\u0015aF4fiR{7.\u001a8SK:,w/\u00197J]R,'O^1m)\u0015)\u0014QCA\f\u0011\u0015!6\u00021\u0001V\u0011\u0015Q8\u00021\u0001|\u000319W\r^%tgV,G)\u0019;f)\u0015A\u0014QDA\u0011\u0011\u0019\ty\u0002\u0004a\u0001\u000b\u0006!1.\u001b8e\u0011\u001d\t\u0019\u0003\u0004a\u0001\u0003K\t!\"\u001b3f]RLg-[3s!\u0011\t9#!\r\u000e\u0005\u0005%\"\u0002BA\u0016\u0003[\t!\u0002Z3mK\u001e\fG/[8o\u0015\r\tycZ\u0001\u0006i>\\WM\\\u0005\u0005\u0003g\tICA\u0011BEN$(/Y2u\t\u0016dWmZ1uS>tGk\\6f]&#WM\u001c;jM&,'/A\u0010IC\u0012|w\u000e\u001d$T\t\u0016dWmZ1uS>tGk\\6f]B\u0013xN^5eKJ\u0004\"A\r\b\u0014\u00059iBCAA\u001c\u0003EA\u0017\rZ8pa\u001a\u001b6\u000fV8BG\u000e,7o\u001d\u000b\u0006w\u0006\u0005\u00131\t\u0005\u0006=B\u0001\ra\u0018\u0005\u0006)B\u0001\r!\u0016"
)
public class HadoopFSDelegationTokenProvider implements HadoopDelegationTokenProvider, Logging {
   private Option tokenRenewalInterval;
   private final String serviceName;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Set hadoopFSsToAccess(final SparkConf sparkConf, final Configuration hadoopConf) {
      return HadoopFSDelegationTokenProvider$.MODULE$.hadoopFSsToAccess(sparkConf, hadoopConf);
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

   private Option tokenRenewalInterval() {
      return this.tokenRenewalInterval;
   }

   private void tokenRenewalInterval_$eq(final Option x$1) {
      this.tokenRenewalInterval = x$1;
   }

   public String serviceName() {
      return this.serviceName;
   }

   public Option obtainDelegationTokens(final Configuration hadoopConf, final SparkConf sparkConf, final Credentials creds) {
      Object var10000;
      try {
         Set fileSystems = HadoopFSDelegationTokenProvider$.MODULE$.hadoopFSsToAccess(sparkConf, hadoopConf);
         Set fsToExclude = ((IterableOnceOps)((IterableOps)sparkConf.get(package$.MODULE$.YARN_KERBEROS_FILESYSTEM_RENEWAL_EXCLUDE())).map((x$1) -> (new Path(x$1)).getFileSystem(hadoopConf).getUri().getHost())).toSet();
         Credentials fetchCreds = this.fetchDelegationTokens(this.getTokenRenewer(sparkConf, hadoopConf), fileSystems, creds, fsToExclude);
         if (this.tokenRenewalInterval() == null) {
            this.tokenRenewalInterval_$eq(this.getTokenRenewalInterval(hadoopConf, fileSystems));
         }

         Option nextRenewalDate = this.tokenRenewalInterval().flatMap((interval) -> $anonfun$obtainDelegationTokens$2(this, fetchCreds, BoxesRunTime.unboxToLong(interval)));
         var10000 = nextRenewalDate;
      } catch (Throwable var12) {
         if (var12 == null || !.MODULE$.apply(var12)) {
            throw var12;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to get token from service ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, this.serviceName())})))), var12);
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public boolean delegationTokensRequired(final SparkConf sparkConf, final Configuration hadoopConf) {
      return UserGroupInformation.isSecurityEnabled();
   }

   private String getTokenRenewer(final SparkConf sparkConf, final Configuration hadoopConf) {
      String master = sparkConf.get("spark.master", (String)null);
      String tokenRenewer = master != null && master.contains("yarn") ? Master.getMasterPrincipal(hadoopConf) : UserGroupInformation.getCurrentUser().getUserName();
      this.logDebug((Function0)(() -> "Delegation token renewer is: " + tokenRenewer));
      if (tokenRenewer != null && tokenRenewer.length() != 0) {
         return tokenRenewer;
      } else {
         String errorMessage = "Can't get Master Kerberos principal for use as renewer.";
         this.logError((Function0)(() -> errorMessage));
         throw new SparkException(errorMessage);
      }
   }

   private Credentials fetchDelegationTokens(final String renewer, final Set filesystems, final Credentials creds, final Set fsToExclude) {
      filesystems.foreach((fs) -> {
         $anonfun$fetchDelegationTokens$1(this, fsToExclude, creds, renewer, fs);
         return BoxedUnit.UNIT;
      });
      return creds;
   }

   private Option getTokenRenewalInterval(final Configuration hadoopConf, final Set filesystems) {
      String renewer = UserGroupInformation.getCurrentUser().getUserName();
      Credentials creds = new Credentials();
      this.fetchDelegationTokens(renewer, filesystems, creds, scala.Predef..MODULE$.Set().empty());
      Iterable renewIntervals = (Iterable)((IterableOps)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(creds.getAllTokens()).asScala().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getTokenRenewalInterval$1(x$3)))).flatMap((token) -> scala.util.Try..MODULE$.apply((JFunction0.mcJ.sp)() -> {
            long newExpiration = token.renew(hadoopConf);
            AbstractDelegationTokenIdentifier identifier = (AbstractDelegationTokenIdentifier)token.decodeIdentifier();
            String tokenKind = token.getKind().toString();
            long interval = newExpiration - this.getIssueDate(tokenKind, identifier);
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Renewal interval is ", " for"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(interval))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" token ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN_KIND..MODULE$, tokenKind)}))))));
            token.cancel(hadoopConf);
            return interval;
         }).toOption());
      return (Option)(renewIntervals.isEmpty() ? scala.None..MODULE$ : new Some(renewIntervals.min(scala.math.Ordering.Long..MODULE$)));
   }

   private long getIssueDate(final String kind, final AbstractDelegationTokenIdentifier identifier) {
      long now = System.currentTimeMillis();
      long issueDate = identifier.getIssueDate();
      if (issueDate > now) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Token ", " has set up issue date later than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN_KIND..MODULE$, kind)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"current time (provided: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " / current timestamp: ", "). "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ISSUE_DATE..MODULE$, BoxesRunTime.boxToLong(issueDate)), new MDC(org.apache.spark.internal.LogKeys.CURRENT_TIME..MODULE$, BoxesRunTime.boxToLong(now))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please make sure clocks are in sync between "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"machines. If the issue is not a clock mismatch, consult token implementor to check "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"whether issue date is valid."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return issueDate;
      } else if (issueDate > 0L) {
         return issueDate;
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Token ", " has not set up issue date properly "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN_KIND..MODULE$, kind)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(provided: ", "). "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ISSUE_DATE..MODULE$, BoxesRunTime.boxToLong(issueDate))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using current timestamp (", " as issue date instead. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_TIME..MODULE$, BoxesRunTime.boxToLong(now))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Consult token implementor to fix the behavior."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return now;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$obtainDelegationTokens$3(final Token x$2) {
      return x$2.decodeIdentifier() instanceof AbstractDelegationTokenIdentifier;
   }

   // $FF: synthetic method
   public static final long $anonfun$obtainDelegationTokens$4(final HadoopFSDelegationTokenProvider $this, final long interval$1, final Token token) {
      AbstractDelegationTokenIdentifier identifier = (AbstractDelegationTokenIdentifier)token.decodeIdentifier();
      String tokenKind = token.getKind().toString();
      return $this.getIssueDate(tokenKind, identifier) + interval$1;
   }

   // $FF: synthetic method
   public static final Option $anonfun$obtainDelegationTokens$2(final HadoopFSDelegationTokenProvider $this, final Credentials fetchCreds$1, final long interval) {
      Iterable nextRenewalDates = (Iterable)((IterableOps)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(fetchCreds$1.getAllTokens()).asScala().filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$obtainDelegationTokens$3(x$2)))).map((token) -> BoxesRunTime.boxToLong($anonfun$obtainDelegationTokens$4($this, interval, token)));
      return (Option)(nextRenewalDates.isEmpty() ? scala.None..MODULE$ : new Some(nextRenewalDates.min(scala.math.Ordering.Long..MODULE$)));
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchDelegationTokens$1(final HadoopFSDelegationTokenProvider $this, final Set fsToExclude$1, final Credentials creds$1, final String renewer$1, final FileSystem fs) {
      if (fsToExclude$1.contains(fs.getUri().getHost())) {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"getting token for: ", " with empty renewer to skip renewal"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_SYSTEM..MODULE$, fs)})))));
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> fs.addDelegationTokens("", creds$1));
      } else {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"getting token for: ", " with"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_SYSTEM..MODULE$, fs)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" renewer ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN_RENEWER..MODULE$, renewer$1)}))))));
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> fs.addDelegationTokens(renewer$1, creds$1));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getTokenRenewalInterval$1(final Token x$3) {
      return x$3.decodeIdentifier() instanceof AbstractDelegationTokenIdentifier;
   }

   public HadoopFSDelegationTokenProvider() {
      Logging.$init$(this);
      this.tokenRenewalInterval = null;
      this.serviceName = "hadoopfs";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
