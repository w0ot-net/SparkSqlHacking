package org.apache.spark.sql.hive.thriftserver.server;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hive.service.cli.operation.ExecuteStatementOperation;
import org.apache.hive.service.cli.operation.GetCatalogsOperation;
import org.apache.hive.service.cli.operation.GetColumnsOperation;
import org.apache.hive.service.cli.operation.GetFunctionsOperation;
import org.apache.hive.service.cli.operation.GetSchemasOperation;
import org.apache.hive.service.cli.operation.GetTableTypesOperation;
import org.apache.hive.service.cli.operation.GetTypeInfoOperation;
import org.apache.hive.service.cli.operation.MetadataOperation;
import org.apache.hive.service.cli.operation.OperationManager;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.thriftserver.ReflectionUtils$;
import org.apache.spark.sql.hive.thriftserver.SparkExecuteStatementOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetCatalogsOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetColumnsOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetFunctionsOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetSchemasOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetTableTypesOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetTablesOperation;
import org.apache.spark.sql.hive.thriftserver.SparkGetTypeInfoOperation;
import org.apache.spark.sql.internal.SQLConf;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e!\u0002\b\u0010\u0001Ei\u0002\"B\u0018\u0001\t\u0003\t\u0004b\u0002\u001b\u0001\u0005\u0004%\t!\u000e\u0005\u0007\u000b\u0002\u0001\u000b\u0011\u0002\u001c\t\u000f\u0019\u0003!\u0019!C\u0001\u000f\"1Q\u000b\u0001Q\u0001\n!CQA\u0016\u0001\u0005B]Cq!!\u0001\u0001\t\u0003\n\u0019\u0001C\u0004\u0002\u000e\u0001!\t%a\u0004\t\u000f\u0005\u0005\u0002\u0001\"\u0011\u0002$!9\u0011q\b\u0001\u0005B\u0005\u0005\u0003bBA+\u0001\u0011\u0005\u0013q\u000b\u0005\b\u0003C\u0002A\u0011IA2\u0011\u001d\t)\b\u0001C!\u0003o\u0012\u0001d\u00159be.\u001c\u0016\u000bT(qKJ\fG/[8o\u001b\u0006t\u0017mZ3s\u0015\t\u0001\u0012#\u0001\u0004tKJ4XM\u001d\u0006\u0003%M\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!\u0001F\u000b\u0002\t!Lg/\u001a\u0006\u0003-]\t1a]9m\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<7c\u0001\u0001\u001fSA\u0011qdJ\u0007\u0002A)\u0011\u0011EI\u0001\n_B,'/\u0019;j_:T!a\t\u0013\u0002\u0007\rd\u0017N\u0003\u0002&M\u000591/\u001a:wS\u000e,'B\u0001\u000b\u001a\u0013\tA\u0003E\u0001\tPa\u0016\u0014\u0018\r^5p]6\u000bg.Y4feB\u0011!&L\u0007\u0002W)\u0011AfF\u0001\tS:$XM\u001d8bY&\u0011af\u000b\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u001a\u0011\u0005M\u0002Q\"A\b\u0002#!\fg\u000e\u001a7f)>|\u0005/\u001a:bi&|g.F\u00017!\u00119DH\u0010\"\u000e\u0003aR!!\u000f\u001e\u0002\tU$\u0018\u000e\u001c\u0006\u0002w\u0005!!.\u0019<b\u0013\ti\u0004HA\u0002NCB\u0004\"a\u0010!\u000e\u0003\tJ!!\u0011\u0012\u0003\u001f=\u0003XM]1uS>t\u0007*\u00198eY\u0016\u0004\"aH\"\n\u0005\u0011\u0003#!C(qKJ\fG/[8o\u0003IA\u0017M\u001c3mKR{w\n]3sCRLwN\u001c\u0011\u0002#M,7o]5p]R{7i\u001c8uKb$8/F\u0001I!\u0011IEJT)\u000e\u0003)S!a\u0013\u001d\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002N\u0015\n\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\u0005}z\u0015B\u0001)#\u00055\u0019Vm]:j_:D\u0015M\u001c3mKB\u0011!kU\u0007\u0002+%\u0011A+\u0006\u0002\r'B\f'o[*fgNLwN\\\u0001\u0013g\u0016\u001c8/[8o)>\u001cuN\u001c;fqR\u001c\b%\u0001\u000foK^,\u00050Z2vi\u0016\u001cF/\u0019;f[\u0016tGo\u00149fe\u0006$\u0018n\u001c8\u0015\ra[6M];|!\ty\u0012,\u0003\u0002[A\tIR\t_3dkR,7\u000b^1uK6,g\u000e^(qKJ\fG/[8o\u0011\u0015af\u00011\u0001^\u00035\u0001\u0018M]3oiN+7o]5p]B\u0011a,Y\u0007\u0002?*\u0011\u0001MI\u0001\bg\u0016\u001c8/[8o\u0013\t\u0011wLA\u0006ISZ,7+Z:tS>t\u0007\"\u00023\u0007\u0001\u0004)\u0017!C:uCR,W.\u001a8u!\t1wN\u0004\u0002h[B\u0011\u0001n[\u0007\u0002S*\u0011!\u000eM\u0001\u0007yI|w\u000e\u001e \u000b\u00031\fQa]2bY\u0006L!A\\6\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0018O\u0001\u0004TiJLgn\u001a\u0006\u0003].DQa\u001d\u0004A\u0002Q\f1bY8oM>3XM\u001d7bsB!q\u0007P3f\u0011\u00151h\u00011\u0001x\u0003\u0015\t7/\u001f8d!\tA\u00180D\u0001l\u0013\tQ8NA\u0004C_>dW-\u00198\t\u000bq4\u0001\u0019A?\u0002\u0019E,XM]=US6,w.\u001e;\u0011\u0005at\u0018BA@l\u0005\u0011auN\\4\u0002/9,woR3u\u0007\u0006$\u0018\r\\8hg>\u0003XM]1uS>tG\u0003BA\u0003\u0003\u0017\u00012aHA\u0004\u0013\r\tI\u0001\t\u0002\u0015\u000f\u0016$8)\u0019;bY><7o\u00149fe\u0006$\u0018n\u001c8\t\u000bq;\u0001\u0019A/\u0002-9,woR3u'\u000eDW-\\1t\u001fB,'/\u0019;j_:$\u0002\"!\u0005\u0002\u0018\u0005e\u0011Q\u0004\t\u0004?\u0005M\u0011bAA\u000bA\t\u0019r)\u001a;TG\",W.Y:Pa\u0016\u0014\u0018\r^5p]\")A\f\u0003a\u0001;\"1\u00111\u0004\u0005A\u0002\u0015\f1bY1uC2|wMT1nK\"1\u0011q\u0004\u0005A\u0002\u0015\f!b]2iK6\fg*Y7f\u0003UqWm^$fiR\u000b'\r\\3t\u001fB,'/\u0019;j_:$B\"!\n\u0002,\u00055\u0012qFA\u0019\u0003k\u00012aHA\u0014\u0013\r\tI\u0003\t\u0002\u0012\u001b\u0016$\u0018\rZ1uC>\u0003XM]1uS>t\u0007\"\u0002/\n\u0001\u0004i\u0006BBA\u000e\u0013\u0001\u0007Q\r\u0003\u0004\u0002 %\u0001\r!\u001a\u0005\u0007\u0003gI\u0001\u0019A3\u0002\u0013Q\f'\r\\3OC6,\u0007bBA\u001c\u0013\u0001\u0007\u0011\u0011H\u0001\u000bi\u0006\u0014G.\u001a+za\u0016\u001c\b\u0003B\u001c\u0002<\u0015L1!!\u00109\u0005\u0011a\u0015n\u001d;\u0002-9,woR3u\u0007>dW/\u001c8t\u001fB,'/\u0019;j_:$B\"a\u0011\u0002J\u0005-\u0013QJA(\u0003#\u00022aHA#\u0013\r\t9\u0005\t\u0002\u0014\u000f\u0016$8i\u001c7v[:\u001cx\n]3sCRLwN\u001c\u0005\u00069*\u0001\r!\u0018\u0005\u0007\u00037Q\u0001\u0019A3\t\r\u0005}!\u00021\u0001f\u0011\u0019\t\u0019D\u0003a\u0001K\"1\u00111\u000b\u0006A\u0002\u0015\f!bY8mk6tg*Y7f\u0003eqWm^$fiR\u000b'\r\\3UsB,7o\u00149fe\u0006$\u0018n\u001c8\u0015\t\u0005e\u0013q\f\t\u0004?\u0005m\u0013bAA/A\t1r)\u001a;UC\ndW\rV=qKN|\u0005/\u001a:bi&|g\u000eC\u0003]\u0017\u0001\u0007Q,\u0001\roK^<U\r\u001e$v]\u000e$\u0018n\u001c8t\u001fB,'/\u0019;j_:$\"\"!\u001a\u0002l\u00055\u0014qNA9!\ry\u0012qM\u0005\u0004\u0003S\u0002#!F$fi\u001a+hn\u0019;j_:\u001cx\n]3sCRLwN\u001c\u0005\u000692\u0001\r!\u0018\u0005\u0007\u00037a\u0001\u0019A3\t\r\u0005}A\u00021\u0001f\u0011\u0019\t\u0019\b\u0004a\u0001K\u0006aa-\u001e8di&|gNT1nK\u00069b.Z<HKR$\u0016\u0010]3J]\u001a|w\n]3sCRLwN\u001c\u000b\u0005\u0003s\ny\bE\u0002 \u0003wJ1!! !\u0005Q9U\r\u001e+za\u0016LeNZ8Pa\u0016\u0014\u0018\r^5p]\")A,\u0004a\u0001;\u0002"
)
public class SparkSQLOperationManager extends OperationManager implements Logging {
   private final Map handleToOperation;
   private final ConcurrentHashMap sessionToContexts;
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

   public Map handleToOperation() {
      return this.handleToOperation;
   }

   public ConcurrentHashMap sessionToContexts() {
      return this.sessionToContexts;
   }

   public synchronized ExecuteStatementOperation newExecuteStatementOperation(final HiveSession parentSession, final String statement, final Map confOverlay, final boolean async, final long queryTimeout) {
      SparkSession sparkSession = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(sparkSession != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SQLConf conf = sparkSession.sessionState().conf();
      boolean runInBackground = async && BoxesRunTime.unboxToBoolean(conf.getConf(org.apache.spark.sql.hive.HiveUtils..MODULE$.HIVE_THRIFT_SERVER_ASYNC()));
      SparkExecuteStatementOperation operation = new SparkExecuteStatementOperation(sparkSession, parentSession, statement, confOverlay, runInBackground, queryTimeout);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created Operation for " + statement + " with session=" + parentSession + ", runInBackground=" + runInBackground));
      return operation;
   }

   public synchronized GetCatalogsOperation newGetCatalogsOperation(final HiveSession parentSession) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetCatalogsOperation operation = new SparkGetCatalogsOperation(session, parentSession);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetCatalogsOperation with session=" + parentSession + "."));
      return operation;
   }

   public synchronized GetSchemasOperation newGetSchemasOperation(final HiveSession parentSession, final String catalogName, final String schemaName) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetSchemasOperation operation = new SparkGetSchemasOperation(session, parentSession, catalogName, schemaName);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetSchemasOperation with session=" + parentSession + "."));
      return operation;
   }

   public synchronized MetadataOperation newGetTablesOperation(final HiveSession parentSession, final String catalogName, final String schemaName, final String tableName, final List tableTypes) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetTablesOperation operation = new SparkGetTablesOperation(session, parentSession, catalogName, schemaName, tableName, tableTypes);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetTablesOperation with session=" + parentSession + "."));
      return operation;
   }

   public synchronized GetColumnsOperation newGetColumnsOperation(final HiveSession parentSession, final String catalogName, final String schemaName, final String tableName, final String columnName) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetColumnsOperation operation = new SparkGetColumnsOperation(session, parentSession, catalogName, schemaName, tableName, columnName);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetColumnsOperation with session=" + parentSession + "."));
      return operation;
   }

   public synchronized GetTableTypesOperation newGetTableTypesOperation(final HiveSession parentSession) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetTableTypesOperation operation = new SparkGetTableTypesOperation(session, parentSession);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetTableTypesOperation with session=" + parentSession + "."));
      return operation;
   }

   public synchronized GetFunctionsOperation newGetFunctionsOperation(final HiveSession parentSession, final String catalogName, final String schemaName, final String functionName) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetFunctionsOperation operation = new SparkGetFunctionsOperation(session, parentSession, catalogName, schemaName, functionName);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetFunctionsOperation with session=" + parentSession + "."));
      return operation;
   }

   public synchronized GetTypeInfoOperation newGetTypeInfoOperation(final HiveSession parentSession) {
      SparkSession session = (SparkSession)this.sessionToContexts().get(parentSession.getSessionHandle());
      .MODULE$.require(session != null, () -> "Session handle: " + parentSession.getSessionHandle() + " has not been initialized or had already closed.");
      SparkGetTypeInfoOperation operation = new SparkGetTypeInfoOperation(session, parentSession);
      this.handleToOperation().put(operation.getHandle(), operation);
      this.logDebug((Function0)(() -> "Created GetTypeInfoOperation with session=" + parentSession + "."));
      return operation;
   }

   public SparkSQLOperationManager() {
      Logging.$init$(this);
      this.handleToOperation = (Map)ReflectionUtils$.MODULE$.getSuperField(this, "handleToOperation");
      this.sessionToContexts = new ConcurrentHashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
