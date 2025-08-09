package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObjectUtils;
import org.apache.hive.service.cli.CLIServiceUtils;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.operation.GetTablesOperation;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005A4Qa\u0003\u0007\u0001\u001daA\u0001B\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\tk\u0001\u0011\t\u0011)A\u0005c!Aa\u0007\u0001B\u0001B\u0003%q\u0007\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003>\u0011!Q\u0005A!A!\u0002\u0013i\u0004\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u00111\u0003!\u0011!Q\u0001\n5CQ!\u0016\u0001\u0005\u0002YCQA\u0018\u0001\u0005B}CQ\u0001\u001a\u0001\u0005\n\u0015\u0014qc\u00159be.<U\r\u001e+bE2,7o\u00149fe\u0006$\u0018n\u001c8\u000b\u00055q\u0011\u0001\u0004;ie&4Go]3sm\u0016\u0014(BA\b\u0011\u0003\u0011A\u0017N^3\u000b\u0005E\u0011\u0012aA:rY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xm\u0005\u0003\u00013\u0011B\u0003C\u0001\u000e#\u001b\u0005Y\"B\u0001\u000f\u001e\u0003%y\u0007/\u001a:bi&|gN\u0003\u0002\u001f?\u0005\u00191\r\\5\u000b\u0005\u0001\n\u0013aB:feZL7-\u001a\u0006\u0003\u001fQI!aI\u000e\u0003%\u001d+G\u000fV1cY\u0016\u001cx\n]3sCRLwN\u001c\t\u0003K\u0019j\u0011\u0001D\u0005\u0003O1\u0011ab\u00159be.|\u0005/\u001a:bi&|g\u000e\u0005\u0002*Y5\t!F\u0003\u0002,%\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002.U\t9Aj\\4hS:<\u0017aB:fgNLwN\\\u0002\u0001+\u0005\t\u0004C\u0001\u001a4\u001b\u0005\u0001\u0012B\u0001\u001b\u0011\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0003!\u0019Xm]:j_:\u0004\u0013!\u00049be\u0016tGoU3tg&|g\u000e\u0005\u00029u5\t\u0011H\u0003\u0002/;%\u00111(\u000f\u0002\f\u0011&4XmU3tg&|g.A\u0006dCR\fGn\\4OC6,\u0007C\u0001 H\u001d\tyT\t\u0005\u0002A\u00076\t\u0011I\u0003\u0002C_\u00051AH]8pizR\u0011\u0001R\u0001\u0006g\u000e\fG.Y\u0005\u0003\r\u000e\u000ba\u0001\u0015:fI\u00164\u0017B\u0001%J\u0005\u0019\u0019FO]5oO*\u0011aiQ\u0001\u000bg\u000eDW-\\1OC6,\u0017!\u0003;bE2,g*Y7f\u0003)!\u0018M\u00197f)f\u0004Xm\u001d\t\u0004\u001dNkT\"A(\u000b\u0005A\u000b\u0016\u0001B;uS2T\u0011AU\u0001\u0005U\u00064\u0018-\u0003\u0002U\u001f\n!A*[:u\u0003\u0019a\u0014N\\5u}Q9q\u000bW-[7rk\u0006CA\u0013\u0001\u0011\u0015q\u0003\u00021\u00012\u0011\u00151\u0004\u00021\u00018\u0011\u0015a\u0004\u00021\u0001>\u0011\u0015Q\u0005\u00021\u0001>\u0011\u0015Y\u0005\u00021\u0001>\u0011\u0015a\u0005\u00021\u0001N\u0003-\u0011XO\\%oi\u0016\u0014h.\u00197\u0015\u0003\u0001\u0004\"!\u00192\u000e\u0003\rK!aY\"\u0003\tUs\u0017\u000e^\u0001\fC\u0012$Gk\u001c*poN+G\u000fF\u0003aM\"L7\u000eC\u0003h\u0015\u0001\u0007Q(\u0001\u0004eE:\u000bW.\u001a\u0005\u0006\u0017*\u0001\r!\u0010\u0005\u0006U*\u0001\r!P\u0001\ni\u0006\u0014G.\u001a+za\u0016DQ\u0001\u001c\u0006A\u00025\fqaY8n[\u0016tG\u000fE\u0002b]vJ!a\\\"\u0003\r=\u0003H/[8o\u0001"
)
public class SparkGetTablesOperation extends GetTablesOperation implements SparkOperation {
   private final SparkSession session;
   private final HiveSession parentSession;
   private final String catalogName;
   private final String schemaName;
   private final String tableName;
   private final List tableTypes;
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
      String cmdStr = "catalog : " + this.catalogName + ", schemaPattern : " + this.schemaName;
      String tableTypesStr = this.tableTypes == null ? "null" : .MODULE$.ListHasAsScala(this.tableTypes).asScala().mkString(",");
      String logMsg = "Listing tables '" + cmdStr + ", tableTypes : " + tableTypesStr + ", tableName : " + this.tableName + "'";
      String catalogNameStr = this.catalogName == null ? "null" : this.catalogName;
      String schemaNameStr = this.schemaName == null ? "null" : this.schemaName;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing tables 'catalog: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CATALOG_NAME..MODULE$, catalogNameStr)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"schemaPattern: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DATABASE_NAME..MODULE$, schemaNameStr)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"tableTypes: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_TYPES..MODULE$, tableTypesStr)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"tableName: ", "' "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, this.tableName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      SessionCatalog catalog = this.session().sessionState().catalog();
      String schemaPattern = this.convertSchemaPattern(this.schemaName);
      String tablePattern = this.convertIdentifierPattern(this.tableName, true);
      Seq matchingDbs = catalog.listDatabases(schemaPattern);
      if (this.isAuthV2Enabled()) {
         List privObjs = HivePrivilegeObjectUtils.getHivePrivDbObjects(.MODULE$.SeqHasAsJava(matchingDbs).asJava());
         this.authorizeMetaGets(HiveOperationType.GET_TABLES, privObjs, cmdStr);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMsg, this.statementId(), this.parentSession.getUsername());

      try {
         matchingDbs.foreach((dbName) -> {
            $anonfun$runInternal$2(this, catalog, tablePattern, dbName);
            return BoxedUnit.UNIT;
         });
         if (this.tableTypes == null || this.tableTypes.isEmpty() || this.tableTypes.contains(org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW().name())) {
            String globalTempViewDb = catalog.globalTempDatabase();
            Pattern databasePattern = Pattern.compile(CLIServiceUtils.patternToRegex(this.schemaName));
            Seq tempViews = databasePattern.matcher(globalTempViewDb).matches() ? catalog.listTables(globalTempViewDb, tablePattern, true) : catalog.listLocalTempViews(tablePattern);
            tempViews.foreach((view) -> {
               $anonfun$runInternal$4(this, view);
               return BoxedUnit.UNIT;
            });
         }

         this.setState(OperationState.FINISHED);
      } catch (Throwable var17) {
         PartialFunction catchExpr$1 = this.onError();
         if (!catchExpr$1.isDefinedAt(var17)) {
            throw var17;
         }

         catchExpr$1.apply(var17);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementFinish(this.statementId());
   }

   private void addToRowSet(final String dbName, final String tableName, final String tableType, final Option comment) {
      Object[] rowData = new Object[]{"", dbName, tableName, tableType, comment.getOrElse(() -> "")};
      this.rowSet.addRow(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(rowData), scala.Array..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Null[]{null, null, null, null, null}), scala.reflect.ClassTag..MODULE$.Null()), scala.reflect.ClassTag..MODULE$.AnyRef()));
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$3(final SparkGetTablesOperation $this, final CatalogTable table) {
      String tableType = $this.tableTypeString(table.tableType());
      if ($this.tableTypes == null || $this.tableTypes.isEmpty() || $this.tableTypes.contains(tableType)) {
         $this.addToRowSet(table.database(), table.identifier().table(), tableType, table.comment());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$2(final SparkGetTablesOperation $this, final SessionCatalog catalog$1, final String tablePattern$1, final String dbName) {
      Seq tables = catalog$1.listTables(dbName, tablePattern$1, false);
      catalog$1.getTablesByName(tables).foreach((table) -> {
         $anonfun$runInternal$3($this, table);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$4(final SparkGetTablesOperation $this, final TableIdentifier view) {
      $this.addToRowSet((String)view.database().orNull(scala..less.colon.less..MODULE$.refl()), view.table(), org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW().name(), scala.None..MODULE$);
   }

   public SparkGetTablesOperation(final SparkSession session, final HiveSession parentSession, final String catalogName, final String schemaName, final String tableName, final List tableTypes) {
      super(parentSession, catalogName, schemaName, tableName, tableTypes);
      this.session = session;
      this.parentSession = parentSession;
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.tableName = tableName;
      this.tableTypes = tableTypes;
      Logging.$init$(this);
      SparkOperation.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
