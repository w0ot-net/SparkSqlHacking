package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.operation.GetCatalogsOperation;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u00113QAB\u0004\u0001\u0013MA\u0001\"\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\ta\u0001\u0011\t\u0011)A\u0005Y!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007C\u00038\u0001\u0011\u0005\u0001\bC\u0003=\u0001\u0011\u0005SHA\rTa\u0006\u00148nR3u\u0007\u0006$\u0018\r\\8hg>\u0003XM]1uS>t'B\u0001\u0005\n\u00031!\bN]5giN,'O^3s\u0015\tQ1\"\u0001\u0003iSZ,'B\u0001\u0007\u000e\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sON!\u0001\u0001F\u0010$!\t)R$D\u0001\u0017\u0015\t9\u0002$A\u0005pa\u0016\u0014\u0018\r^5p]*\u0011\u0011DG\u0001\u0004G2L'BA\u000e\u001d\u0003\u001d\u0019XM\u001d<jG\u0016T!AC\b\n\u0005y1\"\u0001F$fi\u000e\u000bG/\u00197pON|\u0005/\u001a:bi&|g\u000e\u0005\u0002!C5\tq!\u0003\u0002#\u000f\tq1\u000b]1sW>\u0003XM]1uS>t\u0007C\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u000e\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0015&\u0005\u001daunZ4j]\u001e\fqa]3tg&|gn\u0001\u0001\u0016\u00031\u0002\"!\f\u0018\u000e\u0003-I!aL\u0006\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0002\u0011M,7o]5p]\u0002\nQ\u0002]1sK:$8+Z:tS>t\u0007CA\u001a6\u001b\u0005!$BA\u0015\u0019\u0013\t1DGA\u0006ISZ,7+Z:tS>t\u0017A\u0002\u001fj]&$h\bF\u0002:um\u0002\"\u0001\t\u0001\t\u000b%\"\u0001\u0019\u0001\u0017\t\u000bE\"\u0001\u0019\u0001\u001a\u0002\u0017I,h.\u00138uKJt\u0017\r\u001c\u000b\u0002}A\u0011qHQ\u0007\u0002\u0001*\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\u0001\n!QK\\5u\u0001"
)
public class SparkGetCatalogsOperation extends GetCatalogsOperation implements SparkOperation {
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

   public SparkSession session() {
      return this.session;
   }

   public void runInternal() {
      String logMsg = "Listing catalogs";
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing catalogs with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMsg, this.statementId(), this.parentSession.getUsername());

      try {
         if (this.isAuthV2Enabled()) {
            this.authorizeMetaGets(HiveOperationType.GET_CATALOGS, (List)null);
         }

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

   public SparkGetCatalogsOperation(final SparkSession session, final HiveSession parentSession) {
      super(parentSession);
      this.session = session;
      this.parentSession = parentSession;
      Logging.$init$(this);
      SparkOperation.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
