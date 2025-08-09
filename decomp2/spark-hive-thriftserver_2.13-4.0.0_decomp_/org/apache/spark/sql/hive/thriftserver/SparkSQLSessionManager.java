package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.cli.session.SessionManager;
import org.apache.hive.service.rpc.thrift.TProtocolVersion;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalog.Catalog;
import org.apache.spark.sql.hive.thriftserver.server.SparkSQLOperationManager;
import org.apache.spark.sql.internal.SQLConf.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db!B\u0005\u000b\u000111\u0002\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011Q\u0002!\u0011!Q\u0001\nUBQ!\u000f\u0001\u0005\u0002iB\u0001B\u0010\u0001\t\u0006\u0004%Ia\u0010\u0005\u0006\u000b\u0002!\tE\u0012\u0005\u00061\u0002!\t%\u0017\u0005\b\u0003+\u0001A\u0011IA\f\u0011\u001d\ti\u0002\u0001C\u0001\u0003?\u0011ac\u00159be.\u001c\u0016\u000bT*fgNLwN\\'b]\u0006<WM\u001d\u0006\u0003\u00171\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!!\u0004\b\u0002\t!Lg/\u001a\u0006\u0003\u001fA\t1a]9m\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7\u0003\u0002\u0001\u0018E\u0019\u0002\"\u0001\u0007\u0011\u000e\u0003eQ!AG\u000e\u0002\u000fM,7o]5p]*\u0011A$H\u0001\u0004G2L'B\u0001\u0010 \u0003\u001d\u0019XM\u001d<jG\u0016T!!\u0004\n\n\u0005\u0005J\"AD*fgNLwN\\'b]\u0006<WM\u001d\t\u0003G\u0011j\u0011AC\u0005\u0003K)\u0011\u0011DU3gY\u0016\u001cG/\u001a3D_6\u0004xn]5uKN+'O^5dKB\u0011qEK\u0007\u0002Q)\u0011\u0011\u0006E\u0001\tS:$XM\u001d8bY&\u00111\u0006\u000b\u0002\b\u0019><w-\u001b8h\u0003)A\u0017N^3TKJ4XM]\u0002\u0001!\ty#'D\u00011\u0015\t\tT$\u0001\u0004tKJ4XM]\u0005\u0003gA\u00121\u0002S5wKN+'O^3se\u0005a1\u000f]1sWN+7o]5p]B\u0011agN\u0007\u0002\u001d%\u0011\u0001H\u0004\u0002\r'B\f'o[*fgNLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007mbT\b\u0005\u0002$\u0001!)Af\u0001a\u0001]!)Ag\u0001a\u0001k\u0005A2\u000f]1sWN\u000bHn\u00149fe\u0006$\u0018n\u001c8NC:\fw-\u001a:\u0016\u0003\u0001\u0003\"!Q\"\u000e\u0003\tS!!\r\u0006\n\u0005\u0011\u0013%\u0001G*qCJ\\7+\u0015'Pa\u0016\u0014\u0018\r^5p]6\u000bg.Y4fe\u0006!\u0011N\\5u)\t9U\n\u0005\u0002I\u00176\t\u0011JC\u0001K\u0003\u0015\u00198-\u00197b\u0013\ta\u0015J\u0001\u0003V]&$\b\"\u0002(\u0006\u0001\u0004y\u0015\u0001\u00035jm\u0016\u001cuN\u001c4\u0011\u0005A3V\"A)\u000b\u0005I\u001b\u0016\u0001B2p]\u001aT!!\u0004+\u000b\u0005U\u0013\u0012A\u00025bI>|\u0007/\u0003\u0002X#\nA\u0001*\u001b<f\u0007>tg-A\u0006pa\u0016t7+Z:tS>tGC\u0003._QV<\u00180a\u0002\u0002\u0012A\u00111\fX\u0007\u00027%\u0011Ql\u0007\u0002\u000e'\u0016\u001c8/[8o\u0011\u0006tG\r\\3\t\u000b}3\u0001\u0019\u00011\u0002\u0011A\u0014x\u000e^8d_2\u0004\"!\u00194\u000e\u0003\tT!a\u00193\u0002\rQD'/\u001b4u\u0015\t)W$A\u0002sa\u000eL!a\u001a2\u0003!Q\u0003&o\u001c;pG>dg+\u001a:tS>t\u0007\"B5\u0007\u0001\u0004Q\u0017\u0001C;tKJt\u0017-\\3\u0011\u0005-\u0014hB\u00017q!\ti\u0017*D\u0001o\u0015\tyW&\u0001\u0004=e>|GOP\u0005\u0003c&\u000ba\u0001\u0015:fI\u00164\u0017BA:u\u0005\u0019\u0019FO]5oO*\u0011\u0011/\u0013\u0005\u0006m\u001a\u0001\rA[\u0001\u0007a\u0006\u001c8o\u001e3\t\u000ba4\u0001\u0019\u00016\u0002\u0013%\u0004\u0018\t\u001a3sKN\u001c\b\"\u0002>\u0007\u0001\u0004Y\u0018aC:fgNLwN\\\"p]\u001a\u0004R\u0001`A\u0002U*l\u0011! \u0006\u0003}~\fA!\u001e;jY*\u0011\u0011\u0011A\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0006u\u00141!T1q\u0011\u001d\tIA\u0002a\u0001\u0003\u0017\t\u0011c^5uQ&k\u0007/\u001a:t_:\fG/[8o!\rA\u0015QB\u0005\u0004\u0003\u001fI%a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u0003'1\u0001\u0019\u00016\u0002\u001f\u0011,G.Z4bi&|g\u000eV8lK:\fAb\u00197pg\u0016\u001cVm]:j_:$2aRA\r\u0011\u0019\tYb\u0002a\u00015\u0006i1/Z:tS>t\u0007*\u00198eY\u0016\f!b]3u\u0007>tg-T1q)\u00159\u0015\u0011EA\u0012\u0011\u0015!\u0004\u00021\u00016\u0011\u0019\t)\u0003\u0003a\u0001w\u000691m\u001c8g\u001b\u0006\u0004\b"
)
public class SparkSQLSessionManager extends SessionManager implements ReflectedCompositeService, Logging {
   private SparkSQLOperationManager sparkSqlOperationManager;
   private final SparkSession sparkSession;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo;
   private Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError;
   private volatile boolean bitmap$0;

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

   public void initCompositeService(final HiveConf hiveConf) {
      ReflectedCompositeService.initCompositeService$(this, hiveConf);
   }

   public void startCompositeService() {
      ReflectedCompositeService.startCompositeService$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo() {
      return this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo;
   }

   public Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError() {
      return this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError;
   }

   public final void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo_$eq(final Function1 x$1) {
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo = x$1;
   }

   public final void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError_$eq(final Function2 x$1) {
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError = x$1;
   }

   private SparkSQLOperationManager sparkSqlOperationManager$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.sparkSqlOperationManager = new SparkSQLOperationManager();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sparkSqlOperationManager;
   }

   private SparkSQLOperationManager sparkSqlOperationManager() {
      return !this.bitmap$0 ? this.sparkSqlOperationManager$lzycompute() : this.sparkSqlOperationManager;
   }

   public void init(final HiveConf hiveConf) {
      ReflectionUtils$.MODULE$.setSuperField(this, "operationManager", this.sparkSqlOperationManager());
      super.init(hiveConf);
   }

   public SessionHandle openSession(final TProtocolVersion protocol, final String username, final String passwd, final String ipAddress, final Map sessionConf, final boolean withImpersonation, final String delegationToken) {
      SessionHandle sessionHandle = super.openSession(protocol, username, passwd, ipAddress, sessionConf, withImpersonation, delegationToken);

      try {
         HiveSession hiveSession = super.getSession(sessionHandle);
         HiveThriftServer2$.MODULE$.eventManager().onSessionCreated(hiveSession.getIpAddress(), sessionHandle.getSessionId().toString(), hiveSession.getUsername());
         SparkSession session = this.sparkSession.sessionState().conf().hiveThriftServerSingleSession() ? this.sparkSession : this.sparkSession.newSession();
         session.sessionState().conf().setConf(.MODULE$.DATETIME_JAVA8API_ENABLED(), BoxesRunTime.boxToBoolean(true));
         SessionState hiveSessionState = hiveSession.getSessionState();
         this.setConfMap(session, hiveSessionState.getOverriddenConfigurations());
         this.setConfMap(session, hiveSessionState.getHiveVariables());
         if (sessionConf != null && sessionConf.containsKey("use:database")) {
            session.sql("use " + sessionConf.get("use:database"));
         } else {
            BoxedUnit var22 = BoxedUnit.UNIT;
         }

         this.sparkSqlOperationManager().sessionToContexts().put(sessionHandle, session);
         return sessionHandle;
      } catch (Throwable var21) {
         if (var21 != null && scala.util.control.NonFatal..MODULE$.apply(var21)) {
            try {
               this.closeSession(sessionHandle);
            } catch (Throwable var20) {
               if (var20 == null || !scala.util.control.NonFatal..MODULE$.apply(var20)) {
                  throw var20;
               }

               this.logWarning((Function0)(() -> "Error closing session"), var20);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            throw HiveThriftServerErrors$.MODULE$.failedToOpenNewSessionError(var21);
         } else {
            throw var21;
         }
      }
   }

   public void closeSession(final SessionHandle sessionHandle) {
      HiveThriftServer2$.MODULE$.eventManager().onSessionClosed(sessionHandle.getSessionId().toString());
      SparkSession session = (SparkSession)this.sparkSqlOperationManager().sessionToContexts().getOrDefault(sessionHandle, this.sparkSession);
      Seq var10000 = session.sessionState().catalog().getTempViewNames();
      Catalog var3 = session.catalog();
      var10000.foreach((tableName) -> {
         $anonfun$closeSession$1(var3, tableName);
         return BoxedUnit.UNIT;
      });
      super.closeSession(sessionHandle);
      this.sparkSqlOperationManager().sessionToContexts().remove(sessionHandle);
   }

   public void setConfMap(final SparkSession sparkSession, final Map confMap) {
      for(Map.Entry kv : confMap.entrySet()) {
         sparkSession.conf().set((String)kv.getKey(), (String)kv.getValue());
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$closeSession$1(final Catalog eta$0$1$1, final String tableName) {
      eta$0$1$1.uncacheTable(tableName);
   }

   public SparkSQLSessionManager(final HiveServer2 hiveServer, final SparkSession sparkSession) {
      super(hiveServer);
      this.sparkSession = sparkSession;
      ReflectedCompositeService.$init$(this);
      Logging.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
