package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.operation.GetTableTypesOperation;
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
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553Qa\u0002\u0005\u0001\u0015QA\u0001B\u000b\u0001\u0003\u0006\u0004%\t\u0001\f\u0005\tc\u0001\u0011\t\u0011)A\u0005[!A!\u0007\u0001B\u0001B\u0003%1\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003>\u0001\u0011\u0005c\b\u0003\u0006F\u0001A\u0005\t\u0011!A\u0005\u0002\u0019\u00131d\u00159be.<U\r\u001e+bE2,G+\u001f9fg>\u0003XM]1uS>t'BA\u0005\u000b\u00031!\bN]5giN,'O^3s\u0015\tYA\"\u0001\u0003iSZ,'BA\u0007\u000f\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sON!\u0001!\u0006\u0011%!\t1b$D\u0001\u0018\u0015\tA\u0012$A\u0005pa\u0016\u0014\u0018\r^5p]*\u0011!dG\u0001\u0004G2L'B\u0001\u000f\u001e\u0003\u001d\u0019XM\u001d<jG\u0016T!a\u0003\t\n\u0005}9\"AF$fiR\u000b'\r\\3UsB,7o\u00149fe\u0006$\u0018n\u001c8\u0011\u0005\u0005\u0012S\"\u0001\u0005\n\u0005\rB!AD*qCJ\\w\n]3sCRLwN\u001c\t\u0003K!j\u0011A\n\u0006\u0003O9\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003S\u0019\u0012q\u0001T8hO&tw-A\u0004tKN\u001c\u0018n\u001c8\u0004\u0001U\tQ\u0006\u0005\u0002/_5\tA\"\u0003\u00021\u0019\ta1\u000b]1sWN+7o]5p]\u0006A1/Z:tS>t\u0007%A\u0007qCJ,g\u000e^*fgNLwN\u001c\t\u0003iYj\u0011!\u000e\u0006\u0003UeI!aN\u001b\u0003\u0017!Kg/Z*fgNLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007iZD\b\u0005\u0002\"\u0001!)!\u0006\u0002a\u0001[!)!\u0007\u0002a\u0001g\u0005Y!/\u001e8J]R,'O\\1m)\u0005y\u0004C\u0001!D\u001b\u0005\t%\"\u0001\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\u000b%\u0001B+oSR\f\u0001\u0003\u001d:pi\u0016\u001cG/\u001a3%e><8+\u001a;\u0015\u0005\u001d[\u0005C\u0001%J\u001b\u0005I\u0012B\u0001&\u001a\u0005\u0019\u0011vn^*fi\"9AJBA\u0001\u0002\u0004Q\u0014a\u0001=%c\u0001"
)
public class SparkGetTableTypesOperation extends GetTableTypesOperation implements SparkOperation {
   private final SparkSession session;
   private final HiveSession parentSession;
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
   public RowSet protected$rowSet(final SparkGetTableTypesOperation x$1) {
      return x$1.rowSet;
   }

   public SparkSession session() {
      return this.session;
   }

   public void runInternal() {
      this.statementId_$eq(UUID.randomUUID().toString());
      String logMsg = "Listing table types";
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing table types with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      if (this.isAuthV2Enabled()) {
         this.authorizeMetaGets(HiveOperationType.GET_TABLETYPES, (List)null);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMsg, this.statementId(), this.parentSession.getUsername());

      try {
         Set tableTypes = ((IterableOnceOps)org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.tableTypes().map((tableType) -> this.tableTypeString(tableType))).toSet();
         tableTypes.foreach((tableType) -> this.protected$rowSet(this).addRow(new Object[]{tableType}));
         this.setState(OperationState.FINISHED);
      } catch (Throwable var6) {
         PartialFunction catchExpr$1 = this.onError();
         if (!catchExpr$1.isDefinedAt(var6)) {
            throw var6;
         }

         catchExpr$1.apply(var6);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementFinish(this.statementId());
   }

   public SparkGetTableTypesOperation(final SparkSession session, final HiveSession parentSession) {
      super(parentSession);
      this.session = session;
      this.parentSession = parentSession;
      Logging.$init$(this);
      SparkOperation.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
