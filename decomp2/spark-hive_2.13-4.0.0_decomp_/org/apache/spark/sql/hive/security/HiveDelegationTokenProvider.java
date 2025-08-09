package org.apache.spark.sql.hive.security;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.security.HadoopDelegationTokenProvider;
import org.apache.spark.sql.hive.client.HiveClientImpl$;
import org.slf4j.Logger;
import scala.Function0;
import scala.None;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-a!\u0002\u0006\f\u0001E9\u0002\"B\u0015\u0001\t\u0003Y\u0003\"\u0002\u0018\u0001\t\u0003z\u0003bB\u001e\u0001\u0005\u0004%I\u0001\u0010\u0005\u0007\u0001\u0002\u0001\u000b\u0011B\u001f\t\u000b\u0005\u0003A\u0011\u0002\"\t\u000b5\u0003A\u0011\t(\t\u000be\u0003A\u0011\t.\t\u000b)\u0004A\u0011B6\t\u000bu\u0004A\u0011\u0002@\u00037!Kg/\u001a#fY\u0016<\u0017\r^5p]R{7.\u001a8Qe>4\u0018\u000eZ3s\u0015\taQ\"\u0001\u0005tK\u000e,(/\u001b;z\u0015\tqq\"\u0001\u0003iSZ,'B\u0001\t\u0012\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003%M\tQa\u001d9be.T!\u0001F\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0012aA8sON!\u0001\u0001\u0007\u0010$!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019\te.\u001f*fMB\u0011q$I\u0007\u0002A)\u0011A\"E\u0005\u0003E\u0001\u0012Q\u0004S1e_>\u0004H)\u001a7fO\u0006$\u0018n\u001c8U_.,g\u000e\u0015:pm&$WM\u001d\t\u0003I\u001dj\u0011!\n\u0006\u0003ME\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003Q\u0015\u0012q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005a\u0003CA\u0017\u0001\u001b\u0005Y\u0011aC:feZL7-\u001a(b[\u0016,\u0012\u0001\r\t\u0003car!A\r\u001c\u0011\u0005MRR\"\u0001\u001b\u000b\u0005UR\u0013A\u0002\u001fs_>$h(\u0003\u000285\u00051\u0001K]3eK\u001aL!!\u000f\u001e\u0003\rM#(/\u001b8h\u0015\t9$$A\u000bdY\u0006\u001c8OT8u\r>,h\u000eZ#se>\u00148\u000b\u001e:\u0016\u0003u\u0002\"\u0001\n \n\u0005}*#AE'fgN\fw-Z,ji\"\u001cuN\u001c;fqR\fac\u00197bgNtu\u000e\u001e$pk:$WI\u001d:peN#(\u000fI\u0001\tQ&4XmQ8oMR\u00111i\u0013\t\u0003\t&k\u0011!\u0012\u0006\u0003\r\u001e\u000bAaY8oM*\u0011\u0001jE\u0001\u0007Q\u0006$wn\u001c9\n\u0005)+%!D\"p]\u001aLw-\u001e:bi&|g\u000eC\u0003M\u000b\u0001\u00071)\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\f\u0001\u0004Z3mK\u001e\fG/[8o)>\\WM\\:SKF,\u0018N]3e)\ry%\u000b\u0017\t\u00033AK!!\u0015\u000e\u0003\u000f\t{w\u000e\\3b]\")1K\u0002a\u0001)\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003+Zk\u0011!E\u0005\u0003/F\u0011\u0011b\u00159be.\u001cuN\u001c4\t\u000b13\u0001\u0019A\"\u0002-=\u0014G/Y5o\t\u0016dWmZ1uS>tGk\\6f]N$BaW1cGB\u0019\u0011\u0004\u00180\n\u0005uS\"AB(qi&|g\u000e\u0005\u0002\u001a?&\u0011\u0001M\u0007\u0002\u0005\u0019>tw\rC\u0003M\u000f\u0001\u00071\tC\u0003T\u000f\u0001\u0007A\u000bC\u0003e\u000f\u0001\u0007Q-A\u0003de\u0016$7\u000f\u0005\u0002gQ6\tqM\u0003\u0002\r\u000f&\u0011\u0011n\u001a\u0002\f\u0007J,G-\u001a8uS\u0006d7/\u0001\u0007e_\u0006\u001b(+Z1m+N,'/\u0006\u0002m_R\u0011Q\u000e\u001f\t\u0003]>d\u0001\u0001B\u0003q\u0011\t\u0007\u0011OA\u0001U#\t\u0011X\u000f\u0005\u0002\u001ag&\u0011AO\u0007\u0002\b\u001d>$\b.\u001b8h!\tIb/\u0003\u0002x5\t\u0019\u0011I\\=\t\reDA\u00111\u0001{\u0003\t1g\u000eE\u0002\u001aw6L!\u0001 \u000e\u0003\u0011q\u0012\u0017P\\1nKz\n!\u0002^8lK:\fE.[1t+\u0005y\b\u0003BA\u0001\u0003\u000fi!!a\u0001\u000b\u0007\u0005\u0015q)\u0001\u0002j_&!\u0011\u0011BA\u0002\u0005\u0011!V\r\u001f;"
)
public class HiveDelegationTokenProvider implements HadoopDelegationTokenProvider, Logging {
   private final MessageWithContext classNotFoundErrorStr;
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

   public String serviceName() {
      return "hive";
   }

   private MessageWithContext classNotFoundErrorStr() {
      return this.classNotFoundErrorStr;
   }

   private Configuration hiveConf(final Configuration hadoopConf) {
      Object var10000;
      try {
         var10000 = new HiveConf(hadoopConf, HiveConf.class);
      } catch (Throwable var7) {
         if (var7 != null && .MODULE$.apply(var7)) {
            this.logWarning((Function0)(() -> "Fail to create Hive Configuration"), var7);
            var10000 = hadoopConf;
         } else {
            if (!(var7 instanceof NoClassDefFoundError)) {
               throw var7;
            }

            NoClassDefFoundError var6 = (NoClassDefFoundError)var7;
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.classNotFoundErrorStr()), var6);
            var10000 = hadoopConf;
         }
      }

      return (Configuration)var10000;
   }

   public boolean delegationTokensRequired(final SparkConf sparkConf, final Configuration hadoopConf) {
      Token currentToken = UserGroupInformation.getCurrentUser().getCredentials().getToken(this.tokenAlias());
      return currentToken == null && UserGroupInformation.isSecurityEnabled() && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(this.hiveConf(hadoopConf).getTrimmed("hive.metastore.uris", ""))) && (org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().isProxyUser(UserGroupInformation.getCurrentUser()) || !org.apache.spark.util.Utils..MODULE$.isClientMode(sparkConf) && !sparkConf.contains(org.apache.spark.internal.config.package..MODULE$.KEYTAB()));
   }

   public Option obtainDelegationTokens(final Configuration hadoopConf, final SparkConf sparkConf, final Credentials creds) {
      None var10000;
      try {
         Configuration conf = this.hiveConf(hadoopConf);
         String principalKey = "hive.metastore.kerberos.principal";
         String principal = conf.getTrimmed(principalKey, "");
         scala.Predef..MODULE$.require(scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(principal)), () -> "Hive principal " + principalKey + " undefined");
         String metastoreUri = conf.getTrimmed("hive.metastore.uris", "");
         scala.Predef..MODULE$.require(scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(metastoreUri)), () -> "Hive metastore uri undefined");
         UserGroupInformation currentUser = UserGroupInformation.getCurrentUser();
         this.logDebug((Function0)(() -> "Getting Hive delegation token for " + currentUser.getUserName() + " against " + principal + " at " + metastoreUri));
         this.doAsRealUser((JFunction0.mcV.sp)() -> {
            Hive hive = HiveClientImpl$.MODULE$.getHive(conf);
            String tokenStr = hive.getDelegationToken(currentUser.getUserName(), principal);
            Token hive2Token = new Token();
            hive2Token.decodeFromUrlString(tokenStr);
            this.logDebug((Function0)(() -> "Get Token from hive metastore: " + hive2Token.toString()));
            creds.addToken(this.tokenAlias(), hive2Token);
         });
         var10000 = scala.None..MODULE$;
      } catch (Throwable var16) {
         if (var16 != null && .MODULE$.apply(var16)) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> org.apache.spark.util.Utils..MODULE$.createFailedToGetTokenMessage(this.serviceName(), var16)));
            var10000 = scala.None..MODULE$;
         } else {
            if (!(var16 instanceof NoClassDefFoundError)) {
               throw var16;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.classNotFoundErrorStr()));
            var10000 = scala.None..MODULE$;
         }
      } finally {
         org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> Hive.closeCurrent());
      }

      return var10000;
   }

   private Object doAsRealUser(final Function0 fn) {
      UserGroupInformation currentUser = UserGroupInformation.getCurrentUser();
      UserGroupInformation realUser = (UserGroupInformation)scala.Option..MODULE$.apply(currentUser.getRealUser()).getOrElse(() -> currentUser);

      try {
         return realUser.doAs(new PrivilegedExceptionAction(fn) {
            private final Function0 fn$1;

            public Object run() {
               return this.fn$1.apply();
            }

            public {
               this.fn$1 = fn$1;
            }
         });
      } catch (UndeclaredThrowableException var5) {
         throw (Throwable)scala.Option..MODULE$.apply(var5.getCause()).getOrElse(() -> var5);
      }
   }

   private Text tokenAlias() {
      return new Text("hive.server2.delegation.token");
   }

   public HiveDelegationTokenProvider() {
      Logging.$init$(this);
      this.classNotFoundErrorStr = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"You are attempting to use the ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, this.getClass().getCanonicalName())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but your Spark distribution is not built with Hive libraries."})))).log(scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
