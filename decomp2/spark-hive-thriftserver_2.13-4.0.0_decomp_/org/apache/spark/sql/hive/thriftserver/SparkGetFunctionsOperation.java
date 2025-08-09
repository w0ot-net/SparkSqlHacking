package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObjectUtils;
import org.apache.hive.service.cli.CLIServiceUtils;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.operation.GetFunctionsOperation;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.FunctionIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.catalyst.expressions.ExpressionInfo;
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.PartialFunction;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054QAC\u0006\u0001\u001b]A\u0001\"\f\u0001\u0003\u0006\u0004%\ta\f\u0005\ti\u0001\u0011\t\u0011)A\u0005a!AQ\u0007\u0001B\u0001B\u0003%a\u0007\u0003\u0005<\u0001\t\u0005\t\u0015!\u0003=\u0011!I\u0005A!A!\u0002\u0013a\u0004\u0002\u0003&\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u000b-\u0003A\u0011\u0001'\t\u000bM\u0003A\u0011\t+\t\u0015e\u0003\u0001\u0013!A\u0001\u0002\u0013\u0005!L\u0001\u000eTa\u0006\u00148nR3u\rVt7\r^5p]N|\u0005/\u001a:bi&|gN\u0003\u0002\r\u001b\u0005aA\u000f\u001b:jMR\u001cXM\u001d<fe*\u0011abD\u0001\u0005Q&4XM\u0003\u0002\u0011#\u0005\u00191/\u001d7\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001cB\u0001\u0001\r$OA\u0011\u0011$I\u0007\u00025)\u00111\u0004H\u0001\n_B,'/\u0019;j_:T!!\b\u0010\u0002\u0007\rd\u0017N\u0003\u0002 A\u000591/\u001a:wS\u000e,'B\u0001\b\u0014\u0013\t\u0011#DA\u000bHKR4UO\\2uS>t7o\u00149fe\u0006$\u0018n\u001c8\u0011\u0005\u0011*S\"A\u0006\n\u0005\u0019Z!AD*qCJ\\w\n]3sCRLwN\u001c\t\u0003Q-j\u0011!\u000b\u0006\u0003UE\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003Y%\u0012q\u0001T8hO&tw-A\u0004tKN\u001c\u0018n\u001c8\u0004\u0001U\t\u0001\u0007\u0005\u00022e5\tq\"\u0003\u00024\u001f\ta1\u000b]1sWN+7o]5p]\u0006A1/Z:tS>t\u0007%A\u0007qCJ,g\u000e^*fgNLwN\u001c\t\u0003oej\u0011\u0001\u000f\u0006\u0003[qI!A\u000f\u001d\u0003\u0017!Kg/Z*fgNLwN\\\u0001\fG\u0006$\u0018\r\\8h\u001d\u0006lW\r\u0005\u0002>\r:\u0011a\b\u0012\t\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003:\na\u0001\u0010:p_Rt$\"A\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0013\u0015A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!\u0012\"\u0002\u0015M\u001c\u0007.Z7b\u001d\u0006lW-\u0001\u0007gk:\u001cG/[8o\u001d\u0006lW-\u0001\u0004=S:LGO\u0010\u000b\u0007\u001b:{\u0005+\u0015*\u0011\u0005\u0011\u0002\u0001\"B\u0017\b\u0001\u0004\u0001\u0004\"B\u001b\b\u0001\u00041\u0004\"B\u001e\b\u0001\u0004a\u0004\"B%\b\u0001\u0004a\u0004\"\u0002&\b\u0001\u0004a\u0014a\u0003:v]&sG/\u001a:oC2$\u0012!\u0016\t\u0003-^k\u0011AQ\u0005\u00031\n\u0013A!\u00168ji\u0006\u0001\u0002O]8uK\u000e$X\r\u001a\u0013s_^\u001cV\r\u001e\u000b\u00037~\u0003\"\u0001X/\u000e\u0003qI!A\u0018\u000f\u0003\rI{woU3u\u0011\u001d\u0001\u0017\"!AA\u00025\u000b1\u0001\u001f\u00132\u0001"
)
public class SparkGetFunctionsOperation extends GetFunctionsOperation implements SparkOperation {
   private final SparkSession session;
   private final HiveSession parentSession;
   private final String catalogName;
   private final String schemaName;
   private final String functionName;
   private String statementId;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   // $FF: synthetic method
   public void org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$run() {
      super.run();
   }

   // $FF: synthetic method
   public void org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$close() {
      super.close();
   }

   // $FF: synthetic method
   public OperationState org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$setState(final OperationState newState) {
      return super.setState(newState);
   }

   public void cleanup() {
      SparkOperation.cleanup$(this);
   }

   public void run() {
      SparkOperation.run$(this);
   }

   public void close() {
      SparkOperation.close$(this);
   }

   public Object withLocalProperties(final Function0 f) {
      return SparkOperation.withLocalProperties$(this, f);
   }

   public String tableTypeString(final CatalogTableType tableType) {
      return SparkOperation.tableTypeString$(this, tableType);
   }

   public PartialFunction onError() {
      return SparkOperation.onError$(this);
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

   public String statementId() {
      return this.statementId;
   }

   public void statementId_$eq(final String x$1) {
      this.statementId = x$1;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   // $FF: synthetic method
   public RowSet protected$rowSet(final SparkGetFunctionsOperation x$1) {
      return x$1.rowSet;
   }

   public SparkSession session() {
      return this.session;
   }

   public void runInternal() {
      MessageWithContext cmdMDC = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"catalog : ", ", "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CATALOG_NAME..MODULE$, this.catalogName)}))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"schemaPattern : ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DATABASE_NAME..MODULE$, this.schemaName)}))));
      MessageWithContext logMDC = this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Listing functions '"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(cmdMDC).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{", functionName : ", "'"})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FUNCTION_NAME..MODULE$, this.functionName)}))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> logMDC.$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{" with ", ""})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      SessionCatalog catalog = this.session().sessionState().catalog();
      String schemaPattern = this.convertSchemaPattern(this.schemaName);
      Seq matchingDbs = catalog.listDatabases(schemaPattern);
      String functionPattern = CLIServiceUtils.patternToRegex(this.functionName);
      if (this.isAuthV2Enabled()) {
         List privObjs = HivePrivilegeObjectUtils.getHivePrivDbObjects(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(matchingDbs).asJava());
         this.authorizeMetaGets(HiveOperationType.GET_FUNCTIONS, privObjs, cmdMDC.message());
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMDC.message(), this.statementId(), this.parentSession.getUsername());

      try {
         matchingDbs.foreach((db) -> {
            $anonfun$runInternal$2(this, catalog, functionPattern, db);
            return BoxedUnit.UNIT;
         });
         this.setState(OperationState.FINISHED);
      } catch (Throwable var11) {
         PartialFunction catchExpr$1 = this.onError();
         if (!catchExpr$1.isDefinedAt(var11)) {
            throw var11;
         }

         catchExpr$1.apply(var11);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementFinish(this.statementId());
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$2(final SparkGetFunctionsOperation $this, final SessionCatalog catalog$1, final String functionPattern$1, final String db) {
      catalog$1.listFunctions(db, functionPattern$1).foreach((x0$1) -> {
         if (x0$1 != null) {
            FunctionIdentifier funcIdentifier = (FunctionIdentifier)x0$1._1();
            ExpressionInfo info = catalog$1.lookupFunctionInfo(funcIdentifier);
            Object[] var10000 = new Object[]{"", db, funcIdentifier.funcName(), null, null, null};
            String var10003 = info.getUsage();
            var10000[3] = "Usage: " + var10003 + "\nExtended Usage:" + info.getExtended();
            var10000[4] = BoxesRunTime.boxToInteger(0);
            var10000[5] = info.getClassName();
            Object[] rowData = var10000;
            return $this.protected$rowSet($this).addRow(rowData);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public SparkGetFunctionsOperation(final SparkSession session, final HiveSession parentSession, final String catalogName, final String schemaName, final String functionName) {
      super(parentSession, catalogName, schemaName, functionName);
      this.session = session;
      this.parentSession = parentSession;
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.functionName = functionName;
      Logging.$init$(this);
      SparkOperation.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
