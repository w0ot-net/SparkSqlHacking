package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HiveOperationType;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObject;
import org.apache.hadoop.hive.ql.security.authorization.plugin.HivePrivilegeObject.HivePrivilegeObjectType;
import org.apache.hive.service.cli.CLIServiceUtils;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.RowSet;
import org.apache.hive.service.cli.operation.GetColumnsOperation;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader;
import org.apache.spark.sql.types.AnsiIntervalType;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.CalendarIntervalType;
import org.apache.spark.sql.types.CharType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.IntegerType;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.VarcharType;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}e!B\n\u0015\u0001Y\u0001\u0003\u0002\u0003\u001c\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\t\u0011u\u0002!\u0011!Q\u0001\neB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\t\u0002\u0011\t\u0011)A\u0005\u000b\"A!\u000b\u0001B\u0001B\u0003%Q\t\u0003\u0005T\u0001\t\u0005\t\u0015!\u0003F\u0011!!\u0006A!A!\u0002\u0013)\u0005\"B+\u0001\t\u00031\u0006b\u00020\u0001\u0005\u0004%\ta\u0018\u0005\u0007O\u0002\u0001\u000b\u0011\u00021\t\u000b!\u0004A\u0011I5\t\u000b9\u0004A\u0011B8\t\u000by\u0004A\u0011B@\t\u000f\u0005\r\u0001\u0001\"\u0003\u0002\u0006!9\u0011\u0011\u0002\u0001\u0005\n\u0005-\u0001bBA\u0010\u0001\u0011%\u0011\u0011\u0005\u0005\b\u0003\u000f\u0002A\u0011BA%\u00111\ty\t\u0001I\u0001\u0002\u0003\u0005I\u0011AAI\u0005a\u0019\u0006/\u0019:l\u000f\u0016$8i\u001c7v[:\u001cx\n]3sCRLwN\u001c\u0006\u0003+Y\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!a\u0006\r\u0002\t!Lg/\u001a\u0006\u00033i\t1a]9m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7\u0003\u0002\u0001\"YA\u0002\"A\t\u0016\u000e\u0003\rR!\u0001J\u0013\u0002\u0013=\u0004XM]1uS>t'B\u0001\u0014(\u0003\r\u0019G.\u001b\u0006\u0003Q%\nqa]3sm&\u001cWM\u0003\u0002\u00189%\u00111f\t\u0002\u0014\u000f\u0016$8i\u001c7v[:\u001cx\n]3sCRLwN\u001c\t\u0003[9j\u0011\u0001F\u0005\u0003_Q\u0011ab\u00159be.|\u0005/\u001a:bi&|g\u000e\u0005\u00022i5\t!G\u0003\u000245\u0005A\u0011N\u001c;fe:\fG.\u0003\u00026e\t9Aj\\4hS:<\u0017aB:fgNLwN\\\u0002\u0001+\u0005I\u0004C\u0001\u001e<\u001b\u0005A\u0012B\u0001\u001f\u0019\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0003!\u0019Xm]:j_:\u0004\u0013!\u00049be\u0016tGoU3tg&|g\u000e\u0005\u0002A\u00056\t\u0011I\u0003\u00027K%\u00111)\u0011\u0002\f\u0011&4XmU3tg&|g.A\u0006dCR\fGn\\4OC6,\u0007C\u0001$P\u001d\t9U\n\u0005\u0002I\u00176\t\u0011J\u0003\u0002Ko\u00051AH]8pizR\u0011\u0001T\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001d.\u000ba\u0001\u0015:fI\u00164\u0017B\u0001)R\u0005\u0019\u0019FO]5oO*\u0011ajS\u0001\u000bg\u000eDW-\\1OC6,\u0017!\u0003;bE2,g*Y7f\u0003)\u0019w\u000e\\;n]:\u000bW.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000f]C\u0016LW.];B\u0011Q\u0006\u0001\u0005\u0006m!\u0001\r!\u000f\u0005\u0006}!\u0001\ra\u0010\u0005\u0006\t\"\u0001\r!\u0012\u0005\u0006%\"\u0001\r!\u0012\u0005\u0006'\"\u0001\r!\u0012\u0005\u0006)\"\u0001\r!R\u0001\bG\u0006$\u0018\r\\8h+\u0005\u0001\u0007CA1f\u001b\u0005\u0011'B\u00010d\u0015\t!\u0007$\u0001\u0005dCR\fG._:u\u0013\t1'M\u0001\bTKN\u001c\u0018n\u001c8DCR\fGn\\4\u0002\u0011\r\fG/\u00197pO\u0002\n1B];o\u0013:$XM\u001d8bYR\t!\u000e\u0005\u0002lY6\t1*\u0003\u0002n\u0017\n!QK\\5u\u000359W\r^\"pYVlgnU5{KR\u0011\u0001O\u001e\t\u0004WF\u001c\u0018B\u0001:L\u0005\u0019y\u0005\u000f^5p]B\u00111\u000e^\u0005\u0003k.\u00131!\u00138u\u0011\u00159H\u00021\u0001y\u0003\r!\u0018\u0010\u001d\t\u0003srl\u0011A\u001f\u0006\u0003wb\tQ\u0001^=qKNL!! >\u0003\u0011\u0011\u000bG/\u0019+za\u0016\f\u0001cZ3u\t\u0016\u001c\u0017.\\1m\t&<\u0017\u000e^:\u0015\u0007A\f\t\u0001C\u0003x\u001b\u0001\u0007\u00010A\bhKRtU/\u001c)sK\u000e\u0014\u0016\rZ5y)\r\u0001\u0018q\u0001\u0005\u0006o:\u0001\r\u0001_\u0001\u000ei>T\u0015M^1T#2#\u0016\u0010]3\u0015\t\u00055\u0011Q\u0004\t\u0005\u0003\u001f\tI\"\u0004\u0002\u0002\u0012)!\u00111CA\u000b\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0011\u0001\u00026bm\u0006LA!a\u0007\u0002\u0012\t9\u0011J\u001c;fO\u0016\u0014\b\"B<\u0010\u0001\u0004A\u0018aC1eIR{'k\\<TKR$\u0012B[A\u0012\u0003o\tY$!\u0010\t\u000f\u0005\u0015\u0002\u00031\u0001\u0002(\u0005i1m\u001c7v[:\u0004\u0016\r\u001e;fe:\u0004B!!\u000b\u000245\u0011\u00111\u0006\u0006\u0005\u0003[\ty#A\u0003sK\u001e,\u0007P\u0003\u0003\u00022\u0005U\u0011\u0001B;uS2LA!!\u000e\u0002,\t9\u0001+\u0019;uKJt\u0007BBA\u001d!\u0001\u0007Q)\u0001\u0004eE:\u000bW.\u001a\u0005\u0006'B\u0001\r!\u0012\u0005\b\u0003\u007f\u0001\u0002\u0019AA!\u0003\u0019\u00198\r[3nCB\u0019\u00110a\u0011\n\u0007\u0005\u0015#P\u0001\u0006TiJ,8\r\u001e+za\u0016\f1bZ3u!JLgo\u00142kgR!\u00111JA>!\u0019\ti%a\u0016\u0002^9!\u0011qJA*\u001d\rA\u0015\u0011K\u0005\u0002\u0019&\u0019\u0011QK&\u0002\u000fA\f7m[1hK&!\u0011\u0011LA.\u0005\r\u0019V-\u001d\u0006\u0004\u0003+Z\u0005\u0003BA0\u0003oj!!!\u0019\u000b\t\u0005\r\u0014QM\u0001\u0007a2,x-\u001b8\u000b\t\u0005\u001d\u0014\u0011N\u0001\u000eCV$\bn\u001c:ju\u0006$\u0018n\u001c8\u000b\t\u0005-\u0014QN\u0001\tg\u0016\u001cWO]5us*!\u0011qNA9\u0003\t\tHNC\u0002\u0018\u0003gR1!!\u001e\u001d\u0003\u0019A\u0017\rZ8pa&!\u0011\u0011PA1\u0005MA\u0015N^3Qe&4\u0018\u000e\\3hK>\u0013'.Z2u\u0011\u001d\ti(\u0005a\u0001\u0003\u007f\nq\u0001\u001a23)\u0006\u00147\u000f\u0005\u0004G\u0003\u0003+\u0015QQ\u0005\u0004\u0003\u0007\u000b&aA'baB1\u0011QJA,\u0003\u000f\u0003B!!#\u0002\f6\t1-C\u0002\u0002\u000e\u000e\u0014q\u0002V1cY\u0016LE-\u001a8uS\u001aLWM]\u0001\u0011aJ|G/Z2uK\u0012$#o\\<TKR$B!a%\u0002\u001cB!\u0011QSAL\u001b\u0005)\u0013bAAMK\t1!k\\<TKRD\u0001\"!(\u0013\u0003\u0003\u0005\raV\u0001\u0004q\u0012\n\u0004"
)
public class SparkGetColumnsOperation extends GetColumnsOperation implements SparkOperation {
   private final SparkSession session;
   private final HiveSession parentSession;
   private final String catalogName;
   private final String schemaName;
   private final String tableName;
   private final String columnName;
   private final SessionCatalog catalog;
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
   public RowSet protected$rowSet(final SparkGetColumnsOperation x$1) {
      return x$1.rowSet;
   }

   public SparkSession session() {
      return this.session;
   }

   public SessionCatalog catalog() {
      return this.catalog;
   }

   public void runInternal() {
      String cmdStr = "catalog : " + this.catalogName + ", schemaPattern : " + this.schemaName + ", tablePattern : " + this.tableName;
      String logMsg = "Listing columns '" + cmdStr + ", columnName : " + this.columnName + "'";
      String catalogNameStr = this.catalogName == null ? "null" : this.catalogName;
      String schemaNameStr = this.schemaName == null ? "null" : this.schemaName;
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing columns 'catalog : ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CATALOG_NAME..MODULE$, catalogNameStr)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"schemaPattern : ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DATABASE_NAME..MODULE$, schemaNameStr)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"tablePattern : ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, this.tableName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"columnName : ", "' "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COLUMN_NAME..MODULE$, this.columnName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))))));
      this.setState(OperationState.RUNNING);
      NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
      Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), logMsg, this.statementId(), this.parentSession.getUsername());
      String schemaPattern = this.convertSchemaPattern(this.schemaName);
      String tablePattern = this.convertIdentifierPattern(this.tableName, true);
      ObjectRef columnPattern = ObjectRef.create((Object)null);
      if (this.columnName != null) {
         columnPattern.elem = Pattern.compile(this.convertIdentifierPattern(this.columnName, false));
      }

      scala.collection.immutable.Map db2Tabs = ((IterableOnceOps)this.catalog().listDatabases(schemaPattern).map((dbName) -> new Tuple2(dbName, this.catalog().listTables(dbName, tablePattern, false)))).toMap(scala..less.colon.less..MODULE$.refl());
      if (this.isAuthV2Enabled()) {
         List privObjs = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(this.getPrivObjs(db2Tabs)).asJava();
         this.authorizeMetaGets(HiveOperationType.GET_COLUMNS, privObjs, cmdStr);
      }

      try {
         db2Tabs.foreach((x0$1) -> {
            $anonfun$runInternal$3(this, columnPattern, x0$1);
            return BoxedUnit.UNIT;
         });
         String globalTempViewDb = this.catalog().globalTempDatabase();
         Pattern databasePattern = Pattern.compile(CLIServiceUtils.patternToRegex(this.schemaName));
         if (databasePattern.matcher(globalTempViewDb).matches()) {
            this.catalog().globalTempViewManager().listViewNames(tablePattern).foreach((globalTempView) -> {
               $anonfun$runInternal$5(this, columnPattern, globalTempViewDb, globalTempView);
               return BoxedUnit.UNIT;
            });
         }

         this.catalog().listLocalTempViews(tablePattern).foreach((localTempView) -> {
            $anonfun$runInternal$8(this, columnPattern, localTempView);
            return BoxedUnit.UNIT;
         });
         this.setState(OperationState.FINISHED);
      } catch (Throwable var15) {
         PartialFunction catchExpr$1 = this.onError();
         if (!catchExpr$1.isDefinedAt(var15)) {
            throw var15;
         }

         catchExpr$1.apply(var15);
      }

      HiveThriftServer2$.MODULE$.eventManager().onStatementFinish(this.statementId());
   }

   private Option getColumnSize(final DataType typ) {
      if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(typ) ? true : (typ instanceof NumericType ? true : (org.apache.spark.sql.types.DateType..MODULE$.equals(typ) ? true : (org.apache.spark.sql.types.TimestampType..MODULE$.equals(typ) ? true : (org.apache.spark.sql.types.TimestampNTZType..MODULE$.equals(typ) ? true : (org.apache.spark.sql.types.CalendarIntervalType..MODULE$.equals(typ) ? true : (org.apache.spark.sql.types.NullType..MODULE$.equals(typ) ? true : typ instanceof AnsiIntervalType))))))) {
         return new Some(BoxesRunTime.boxToInteger(typ.defaultSize()));
      } else if (typ instanceof CharType) {
         CharType var5 = (CharType)typ;
         int n = var5.length();
         return new Some(BoxesRunTime.boxToInteger(n));
      } else if (typ instanceof StructType) {
         StructType var7 = (StructType)typ;
         StructField[] fields = var7.fields();
         Option[] sizeArr = (Option[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fields), (f) -> this.getColumnSize(f.dataType()), scala.reflect.ClassTag..MODULE$.apply(Option.class));
         return (Option)(scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])sizeArr), scala.None..MODULE$) ? scala.None..MODULE$ : new Some(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])sizeArr), (x$3) -> BoxesRunTime.boxToInteger($anonfun$getColumnSize$2(x$3)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$)));
      } else {
         return scala.None..MODULE$;
      }
   }

   private Option getDecimalDigits(final DataType typ) {
      if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(typ) ? true : typ instanceof IntegerType) {
         return new Some(BoxesRunTime.boxToInteger(0));
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(typ)) {
         return new Some(BoxesRunTime.boxToInteger(7));
      } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(typ)) {
         return new Some(BoxesRunTime.boxToInteger(15));
      } else if (typ instanceof DecimalType) {
         DecimalType var6 = (DecimalType)typ;
         return new Some(BoxesRunTime.boxToInteger(var6.scale()));
      } else {
         return (Option)((org.apache.spark.sql.types.TimestampType..MODULE$.equals(typ) ? true : org.apache.spark.sql.types.TimestampNTZType..MODULE$.equals(typ)) ? new Some(BoxesRunTime.boxToInteger(6)) : scala.None..MODULE$);
      }
   }

   private Option getNumPrecRadix(final DataType typ) {
      return (Option)(typ instanceof NumericType ? new Some(BoxesRunTime.boxToInteger(10)) : scala.None..MODULE$);
   }

   private Integer toJavaSQLType(final DataType typ) {
      if (org.apache.spark.sql.types.NullType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(0);
      } else if (org.apache.spark.sql.types.BooleanType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(16);
      } else if (org.apache.spark.sql.types.ByteType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(-6);
      } else if (org.apache.spark.sql.types.ShortType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(5);
      } else if (org.apache.spark.sql.types.IntegerType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(4);
      } else if (org.apache.spark.sql.types.LongType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(-5);
      } else if (org.apache.spark.sql.types.FloatType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(6);
      } else if (org.apache.spark.sql.types.DoubleType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(8);
      } else if (typ instanceof DecimalType) {
         return scala.Predef..MODULE$.int2Integer(3);
      } else if (typ instanceof VarcharType) {
         return scala.Predef..MODULE$.int2Integer(12);
      } else if (typ instanceof CharType) {
         return scala.Predef..MODULE$.int2Integer(1);
      } else if (typ instanceof StringType) {
         return scala.Predef..MODULE$.int2Integer(12);
      } else if (org.apache.spark.sql.types.BinaryType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(-2);
      } else if (org.apache.spark.sql.types.DateType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(91);
      } else if (org.apache.spark.sql.types.TimestampType..MODULE$.equals(typ) ? true : org.apache.spark.sql.types.TimestampNTZType..MODULE$.equals(typ)) {
         return scala.Predef..MODULE$.int2Integer(93);
      } else if (typ instanceof ArrayType) {
         return scala.Predef..MODULE$.int2Integer(2003);
      } else if (typ instanceof MapType) {
         return scala.Predef..MODULE$.int2Integer(2000);
      } else if (typ instanceof StructType) {
         return scala.Predef..MODULE$.int2Integer(2002);
      } else if (typ instanceof CalendarIntervalType ? true : typ instanceof AnsiIntervalType) {
         return scala.Predef..MODULE$.int2Integer(1111);
      } else {
         throw new IllegalArgumentException("Unrecognized type name: " + typ.sql());
      }
   }

   private void addToRowSet(final Pattern columnPattern, final String dbName, final String tableName, final StructType schema) {
      ((IterableOnceOps)schema.zipWithIndex()).foreach((x0$1) -> {
         if (x0$1 != null) {
            StructField column = (StructField)x0$1._1();
            int pos = x0$1._2$mcI$sp();
            if (columnPattern != null && !columnPattern.matcher(column.name()).matches()) {
               return BoxedUnit.UNIT;
            } else {
               Object[] rowData = new Object[]{null, dbName, tableName, column.name(), this.toJavaSQLType(column.dataType()), column.dataType().sql(), this.getColumnSize(column.dataType()).map((x$4) -> $anonfun$addToRowSet$2(BoxesRunTime.unboxToInt(x$4))).orNull(scala..less.colon.less..MODULE$.refl()), null, this.getDecimalDigits(column.dataType()).map((x$5) -> $anonfun$addToRowSet$3(BoxesRunTime.unboxToInt(x$5))).orNull(scala..less.colon.less..MODULE$.refl()), this.getNumPrecRadix(column.dataType()).map((x$6) -> $anonfun$addToRowSet$4(BoxesRunTime.unboxToInt(x$6))).orNull(scala..less.colon.less..MODULE$.refl()), column.nullable() ? BoxesRunTime.boxToInteger(1) : BoxesRunTime.boxToInteger(0), column.getComment().getOrElse(() -> ""), null, null, null, null, BoxesRunTime.boxToInteger(pos), "YES", null, null, null, null, "NO"};
               return this.protected$rowSet(this).addRow(rowData);
            }
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private Seq getPrivObjs(final scala.collection.immutable.Map db2Tabs) {
      return (Seq)db2Tabs.foldLeft(scala.package..MODULE$.Seq().empty(), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            Seq i = (Seq)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var5 != null) {
               String dbName = (String)var5._1();
               Seq tables = (Seq)var5._2();
               return (Seq)i.$plus$plus((IterableOnce)tables.map((tableId) -> new HivePrivilegeObject(HivePrivilegeObjectType.TABLE_OR_VIEW, dbName, tableId.table())));
            }
         }

         throw new MatchError(var3);
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$4(final SparkGetColumnsOperation $this, final ObjectRef columnPattern$1, final String dbName$1, final CatalogTable catalogTable) {
      $this.addToRowSet((Pattern)columnPattern$1.elem, dbName$1, catalogTable.identifier().table(), catalogTable.schema());
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$3(final SparkGetColumnsOperation $this, final ObjectRef columnPattern$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String dbName = (String)x0$1._1();
         Seq tables = (Seq)x0$1._2();
         $this.catalog().getTablesByName(tables).foreach((catalogTable) -> {
            $anonfun$runInternal$4($this, columnPattern$1, dbName, catalogTable);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$7(final SparkGetColumnsOperation $this, final ObjectRef columnPattern$1, final String globalTempViewDb$1, final String globalTempView$1, final StructType schema) {
      $this.addToRowSet((Pattern)columnPattern$1.elem, globalTempViewDb$1, globalTempView$1, schema);
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$5(final SparkGetColumnsOperation $this, final ObjectRef columnPattern$1, final String globalTempViewDb$1, final String globalTempView) {
      $this.catalog().getRawGlobalTempView(globalTempView).map((x$1) -> x$1.tableMeta().schema()).foreach((schema) -> {
         $anonfun$runInternal$7($this, columnPattern$1, globalTempViewDb$1, globalTempView, schema);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$10(final SparkGetColumnsOperation $this, final ObjectRef columnPattern$1, final TableIdentifier localTempView$1, final StructType schema) {
      $this.addToRowSet((Pattern)columnPattern$1.elem, (String)null, localTempView$1.table(), schema);
   }

   // $FF: synthetic method
   public static final void $anonfun$runInternal$8(final SparkGetColumnsOperation $this, final ObjectRef columnPattern$1, final TableIdentifier localTempView) {
      $this.catalog().getRawTempView(localTempView.table()).map((x$2) -> x$2.tableMeta().schema()).foreach((schema) -> {
         $anonfun$runInternal$10($this, columnPattern$1, localTempView, schema);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final int $anonfun$getColumnSize$2(final Option x$3) {
      return BoxesRunTime.unboxToInt(x$3.get());
   }

   // $FF: synthetic method
   public static final Object $anonfun$addToRowSet$2(final int x$4) {
      return BoxesRunTime.boxToInteger(x$4);
   }

   // $FF: synthetic method
   public static final Object $anonfun$addToRowSet$3(final int x$5) {
      return BoxesRunTime.boxToInteger(x$5);
   }

   // $FF: synthetic method
   public static final Object $anonfun$addToRowSet$4(final int x$6) {
      return BoxesRunTime.boxToInteger(x$6);
   }

   public SparkGetColumnsOperation(final SparkSession session, final HiveSession parentSession, final String catalogName, final String schemaName, final String tableName, final String columnName) {
      super(parentSession, catalogName, schemaName, tableName, columnName);
      this.session = session;
      this.parentSession = parentSession;
      this.catalogName = catalogName;
      this.schemaName = schemaName;
      this.tableName = tableName;
      this.columnName = columnName;
      Logging.$init$(this);
      SparkOperation.$init$(this);
      this.catalog = session.sessionState().catalog();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
