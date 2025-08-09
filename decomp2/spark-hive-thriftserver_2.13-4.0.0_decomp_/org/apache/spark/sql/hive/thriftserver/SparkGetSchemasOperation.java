package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hive.service.cli.CLIServiceUtils;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.operation.GetSchemasOperation;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader;
import org.slf4j.Logger;
import scala.Function0;
import scala.PartialFunction;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y3Q!\u0003\u0006\u0001\u0019YA\u0001\u0002\f\u0001\u0003\u0006\u0004%\tA\f\u0005\tg\u0001\u0011\t\u0011)A\u0005_!AA\u0007\u0001B\u0001B\u0003%Q\u0007\u0003\u0005;\u0001\t\u0005\t\u0015!\u0003<\u0011!A\u0005A!A!\u0002\u0013Y\u0004\"B%\u0001\t\u0003Q\u0005\"\u0002)\u0001\t\u0003\n\u0006B\u0003,\u0001!\u0003\u0005\t\u0011!C\u0001/\nA2\u000b]1sW\u001e+GoU2iK6\f7o\u00149fe\u0006$\u0018n\u001c8\u000b\u0005-a\u0011\u0001\u0004;ie&4Go]3sm\u0016\u0014(BA\u0007\u000f\u0003\u0011A\u0017N^3\u000b\u0005=\u0001\u0012aA:rY*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0005\u0003\u0001/\t2\u0003C\u0001\r!\u001b\u0005I\"B\u0001\u000e\u001c\u0003%y\u0007/\u001a:bi&|gN\u0003\u0002\u001d;\u0005\u00191\r\\5\u000b\u0005yy\u0012aB:feZL7-\u001a\u0006\u0003\u001bII!!I\r\u0003'\u001d+GoU2iK6\f7o\u00149fe\u0006$\u0018n\u001c8\u0011\u0005\r\"S\"\u0001\u0006\n\u0005\u0015R!AD*qCJ\\w\n]3sCRLwN\u001c\t\u0003O)j\u0011\u0001\u000b\u0006\u0003SA\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003W!\u0012q\u0001T8hO&tw-A\u0004tKN\u001c\u0018n\u001c8\u0004\u0001U\tq\u0006\u0005\u00021c5\ta\"\u0003\u00023\u001d\ta1\u000b]1sWN+7o]5p]\u0006A1/Z:tS>t\u0007%A\u0007qCJ,g\u000e^*fgNLwN\u001c\t\u0003maj\u0011a\u000e\u0006\u0003YmI!!O\u001c\u0003\u0017!Kg/Z*fgNLwN\\\u0001\fG\u0006$\u0018\r\\8h\u001d\u0006lW\r\u0005\u0002=\u000b:\u0011Qh\u0011\t\u0003}\u0005k\u0011a\u0010\u0006\u0003\u00016\na\u0001\u0010:p_Rt$\"\u0001\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\u000b\u0015A\u0002)sK\u0012,g-\u0003\u0002G\u000f\n11\u000b\u001e:j]\u001eT!\u0001R!\u0002\u0015M\u001c\u0007.Z7b\u001d\u0006lW-\u0001\u0004=S:LGO\u0010\u000b\u0006\u00172kej\u0014\t\u0003G\u0001AQ\u0001\f\u0004A\u0002=BQ\u0001\u000e\u0004A\u0002UBQA\u000f\u0004A\u0002mBQ\u0001\u0013\u0004A\u0002m\n1B];o\u0013:$XM\u001d8bYR\t!\u000b\u0005\u0002T)6\t\u0011)\u0003\u0002V\u0003\n!QK\\5u\u0003A\u0001(o\u001c;fGR,G\r\n:poN+G\u000f\u0006\u0002Y9B\u0011\u0011LW\u0007\u00027%\u00111l\u0007\u0002\u0007%><8+\u001a;\t\u000fuC\u0011\u0011!a\u0001\u0017\u0006\u0019\u0001\u0010J\u0019"
)
public class SparkGetSchemasOperation extends GetSchemasOperation implements SparkOperation {
   private final SparkSession session;
   private final HiveSession parentSession;
   private final String catalogName;
   private final String schemaName;
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
   public RowSet protected$rowSet(final SparkGetSchemasOperation x$1) {
      return x$1.rowSet;
   }

   public SparkSession session() {
      return this.session;
   }

   public void runInternal() {
      String cmdStr = "catalog : " + this.catalogName + ", schemaPattern : " + this.schemaName;
      String logMsg = "Listing databases '" + cmdStr + "'";
      String catalogNameStr = this.catalogName == null ? "null" : this.catalogName;
      String schemaNameStr = this.schemaName == null ? "null" : this.schemaName;
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing databases 'catalog : ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CATALOG_NAME..MODULE$, catalogNameStr)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"schemaPattern : ", "' "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DATABASE_NAME..MODULE$, schemaNameStr)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      if (this.isAuthV2Enabled()) {
         this.authorizeMetaGets(HiveOperationType.GET_TABLES, (List)null, cmdStr);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMsg, this.statementId(), this.parentSession.getUsername());

      try {
         String schemaPattern = this.convertSchemaPattern(this.schemaName);
         this.session().sessionState().catalog().listDatabases(schemaPattern).foreach((dbName) -> this.protected$rowSet(this).addRow(new Object[]{dbName, ""}));
         String globalTempViewDb = this.session().sessionState().catalog().globalTempDatabase();
         Pattern databasePattern = Pattern.compile(CLIServiceUtils.patternToRegex(this.schemaName));
         if (this.schemaName != null && !this.schemaName.isEmpty() && !databasePattern.matcher(globalTempViewDb).matches()) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.rowSet.addRow(new Object[]{globalTempViewDb, ""});
         }

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

   public SparkGetSchemasOperation(final SparkSession session, final HiveSession parentSession, final String catalogName, final String schemaName) {
      super(parentSession, catalogName, schemaName);
      this.session = session;
      this.parentSession = parentSession;
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      Logging.$init$(this);
      SparkOperation.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
