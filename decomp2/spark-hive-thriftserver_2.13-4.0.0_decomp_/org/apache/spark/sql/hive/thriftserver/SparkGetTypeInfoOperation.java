package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.operation.GetTypeInfoOperation;
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
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000553Qa\u0002\u0005\u0001\u0015QA\u0001B\u000b\u0001\u0003\u0006\u0004%\t\u0001\f\u0005\tc\u0001\u0011\t\u0011)A\u0005[!A!\u0007\u0001B\u0001B\u0003%1\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003>\u0001\u0011\u0005c\b\u0003\u0006F\u0001A\u0005\t\u0011!A\u0005\u0002\u0019\u0013\u0011d\u00159be.<U\r\u001e+za\u0016LeNZ8Pa\u0016\u0014\u0018\r^5p]*\u0011\u0011BC\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003\u00171\tA\u0001[5wK*\u0011QBD\u0001\u0004gFd'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0014\t\u0001)\u0002\u0005\n\t\u0003-yi\u0011a\u0006\u0006\u00031e\t\u0011b\u001c9fe\u0006$\u0018n\u001c8\u000b\u0005iY\u0012aA2mS*\u0011A$H\u0001\bg\u0016\u0014h/[2f\u0015\tY\u0001#\u0003\u0002 /\t!r)\u001a;UsB,\u0017J\u001c4p\u001fB,'/\u0019;j_:\u0004\"!\t\u0012\u000e\u0003!I!a\t\u0005\u0003\u001dM\u0003\u0018M]6Pa\u0016\u0014\u0018\r^5p]B\u0011Q\u0005K\u0007\u0002M)\u0011qED\u0001\tS:$XM\u001d8bY&\u0011\u0011F\n\u0002\b\u0019><w-\u001b8h\u0003\u001d\u0019Xm]:j_:\u001c\u0001!F\u0001.!\tqs&D\u0001\r\u0013\t\u0001DB\u0001\u0007Ta\u0006\u00148nU3tg&|g.\u0001\u0005tKN\u001c\u0018n\u001c8!\u00035\u0001\u0018M]3oiN+7o]5p]B\u0011AGN\u0007\u0002k)\u0011!&G\u0005\u0003oU\u00121\u0002S5wKN+7o]5p]\u00061A(\u001b8jiz\"2AO\u001e=!\t\t\u0003\u0001C\u0003+\t\u0001\u0007Q\u0006C\u00033\t\u0001\u00071'A\u0006sk:Le\u000e^3s]\u0006dG#A \u0011\u0005\u0001\u001bU\"A!\u000b\u0003\t\u000bQa]2bY\u0006L!\u0001R!\u0003\tUs\u0017\u000e^\u0001\u0011aJ|G/Z2uK\u0012$#o\\<TKR$\"aR&\u0011\u0005!KU\"A\r\n\u0005)K\"A\u0002*poN+G\u000fC\u0004M\r\u0005\u0005\t\u0019\u0001\u001e\u0002\u0007a$\u0013\u0007"
)
public class SparkGetTypeInfoOperation extends GetTypeInfoOperation implements SparkOperation {
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
   public RowSet protected$rowSet(final SparkGetTypeInfoOperation x$1) {
      return x$1.rowSet;
   }

   public SparkSession session() {
      return this.session;
   }

   public void runInternal() {
      this.statementId_$eq(UUID.randomUUID().toString());
      String logMsg = "Listing type info";
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing type info with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      if (this.isAuthV2Enabled()) {
         this.authorizeMetaGets(HiveOperationType.GET_TYPEINFO, (List)null);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMsg, this.statementId(), this.parentSession.getUsername());

      try {
         SparkGetTypeInfoUtil$.MODULE$.supportedType().foreach((typeInfo) -> {
            Object[] rowData = new Object[]{typeInfo.getName(), BoxesRunTime.boxToInteger(typeInfo.toJavaSQLType()), typeInfo.getMaxPrecision(), typeInfo.getLiteralPrefix(), typeInfo.getLiteralSuffix(), typeInfo.getCreateParams(), typeInfo.getNullable(), typeInfo.isCaseSensitive(), typeInfo.getSearchable(), typeInfo.isUnsignedAttribute(), typeInfo.isFixedPrecScale(), typeInfo.isAutoIncrement(), typeInfo.getLocalizedName(), typeInfo.getMinimumScale(), typeInfo.getMaximumScale(), null, null, typeInfo.getNumPrecRadix()};
            return this.protected$rowSet(this).addRow(rowData);
         });
         this.setState(OperationState.FINISHED);
      } catch (Throwable var5) {
         PartialFunction catchExpr$1 = this.onError();
         if (!catchExpr$1.isDefinedAt(var5)) {
            throw var5;
         }

         catchExpr$1.apply(var5);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementFinish(this.statementId());
   }

   public SparkGetTypeInfoOperation(final SparkSession session, final HiveSession parentSession) {
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
