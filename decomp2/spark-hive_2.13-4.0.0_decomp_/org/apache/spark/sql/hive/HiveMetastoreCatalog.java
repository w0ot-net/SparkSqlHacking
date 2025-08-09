package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.QualifiedTableName;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.QualifiedTableName.;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.classic.SparkSession;
import org.apache.spark.sql.execution.datasources.CatalogFileIndex;
import org.apache.spark.sql.execution.datasources.DataSource;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.FileIndex;
import org.apache.spark.sql.execution.datasources.HadoopFsRelation;
import org.apache.spark.sql.execution.datasources.InMemoryFileIndex;
import org.apache.spark.sql.execution.datasources.LogicalRelation;
import org.apache.spark.sql.execution.datasources.orc.OrcFileFormat;
import org.apache.spark.sql.execution.datasources.parquet.ParquetFileFormat;
import org.apache.spark.sql.internal.SessionState;
import org.apache.spark.sql.sources.BaseRelation;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import org.sparkproject.guava.util.concurrent.Striped;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.MapOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tUd!B\f\u0019\u0001a\u0011\u0003\u0002C\u0018\u0001\u0005\u0003\u0005\u000b\u0011B\u0019\t\u000b]\u0002A\u0011\u0001\u001d\t\u000bq\u0002A\u0011B\u001f\t\u000b\r\u0003A\u0011\u0002#\t\u000f5\u0003!\u0019!C\u0005\u001d\"1q\r\u0001Q\u0001\n=CQ\u0001\u001b\u0001\u0005\n%D\u0001\"a\u0001\u0001\t\u0003A\u0012Q\u0001\u0005\b\u0003C\u0001A\u0011BA\u0012\u0011\u001d\t)\u000b\u0001C\u0005\u0003OCq!a2\u0001\t\u0013\tI\rC\u0004\u0002V\u0002!I!a6\t\u000f\u0005m\u0007\u0001\"\u0001\u0002^\"9\u0011Q\u001e\u0001\u0005\u0002\u0005=\bbBA~\u0001\u0011%\u0011Q \u0005\b\u0005?\u0001A\u0011\u0002B\u0011\u0011%\u0011i\u0004AI\u0001\n\u0013\u0011y\u0004C\u0004\u0003V\u0001!IAa\u0016\b\u0011\t\u0005\u0004\u0004#\u0001\u0019\u0005G2qa\u0006\r\t\u0002a\u0011)\u0007\u0003\u00048)\u0011\u0005!q\r\u0005\b\u0005S\"B\u0011\u0001B6\u0005QA\u0015N^3NKR\f7\u000f^8sK\u000e\u000bG/\u00197pO*\u0011\u0011DG\u0001\u0005Q&4XM\u0003\u0002\u001c9\u0005\u00191/\u001d7\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c2\u0001A\u0012*!\t!s%D\u0001&\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0005\u0019\te.\u001f*fMB\u0011!&L\u0007\u0002W)\u0011A\u0006H\u0001\tS:$XM\u001d8bY&\u0011af\u000b\u0002\b\u0019><w-\u001b8h\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o\u0007\u0001\u0001\"AM\u001b\u000e\u0003MR!\u0001\u000e\u000e\u0002\u000f\rd\u0017m]:jG&\u0011ag\r\u0002\r'B\f'o[*fgNLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005eZ\u0004C\u0001\u001e\u0001\u001b\u0005A\u0002\"B\u0018\u0003\u0001\u0004\t\u0014\u0001D:fgNLwN\\*uCR,W#\u0001 \u0011\u0005}\nU\"\u0001!\u000b\u00051R\u0012B\u0001\"A\u00051\u0019Vm]:j_:\u001cF/\u0019;f\u00031\u0019\u0017\r^1m_\u001e\u0004&o\u001c=z+\u0005)\u0005C\u0001$L\u001b\u00059%B\u0001%J\u0003\u001d\u0019\u0017\r^1m_\u001eT!A\u0013\u000e\u0002\u0011\r\fG/\u00197zgRL!\u0001T$\u0003\u001dM+7o]5p]\u000e\u000bG/\u00197pO\u0006\u0011B/\u00192mK\u000e\u0013X-\u0019;j_:dunY6t+\u0005y\u0005c\u0001)\\;6\t\u0011K\u0003\u0002S'\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005Q+\u0016\u0001B;uS2T!AV,\u0002\r\r|W.\\8o\u0015\tA\u0016,\u0001\u0004h_><G.\u001a\u0006\u00025\u0006\u00191m\\7\n\u0005q\u000b&aB*ue&\u0004X\r\u001a\t\u0003=\u0016l\u0011a\u0018\u0006\u0003A\u0006\fQ\u0001\\8dWNT!A\u00152\u000b\u0005Q\u001b'\"\u00013\u0002\t)\fg/Y\u0005\u0003M~\u0013A\u0001T8dW\u0006\u0019B/\u00192mK\u000e\u0013X-\u0019;j_:dunY6tA\u0005)r/\u001b;i)\u0006\u0014G.Z\"sK\u0006$\u0018n\u001c8M_\u000e\\WC\u00016n)\rYg\u000f \t\u0003Y6d\u0001\u0001B\u0003o\u000f\t\u0007qNA\u0001B#\t\u00018\u000f\u0005\u0002%c&\u0011!/\n\u0002\b\u001d>$\b.\u001b8h!\t!C/\u0003\u0002vK\t\u0019\u0011I\\=\t\u000b]<\u0001\u0019\u0001=\u0002\u0013Q\f'\r\\3OC6,\u0007CA={\u001b\u0005I\u0015BA>J\u0005I\tV/\u00197jM&,G\rV1cY\u0016t\u0015-\\3\t\ru<A\u00111\u0001\u007f\u0003\u00051\u0007c\u0001\u0013\u0000W&\u0019\u0011\u0011A\u0013\u0003\u0011q\u0012\u0017P\\1nKz\n\u0001dZ3u\u0007\u0006\u001c\u0007.\u001a3ECR\f7k\\;sG\u0016$\u0016M\u00197f)\u0011\t9!a\u0006\u0011\t\u0005%\u00111C\u0007\u0003\u0003\u0017QA!!\u0004\u0002\u0010\u00059An\\4jG\u0006d'bAA\t\u0013\u0006)\u0001\u000f\\1og&!\u0011QCA\u0006\u0005-aunZ5dC2\u0004F.\u00198\t\u000f\u0005e\u0001\u00021\u0001\u0002\u001c\u0005)A/\u00192mKB\u0019\u00110!\b\n\u0007\u0005}\u0011JA\bUC\ndW-\u00133f]RLg-[3s\u0003%9W\r^\"bG\",G\r\u0006\u0007\u0002&\u0005m\u0012qHA6\u0003w\ny\nE\u0003%\u0003O\tY#C\u0002\u0002*\u0015\u0012aa\u00149uS>t\u0007\u0003BA\u0017\u0003oi!!a\f\u000b\t\u0005E\u00121G\u0001\fI\u0006$\u0018m]8ve\u000e,7OC\u0002\u00026i\t\u0011\"\u001a=fGV$\u0018n\u001c8\n\t\u0005e\u0012q\u0006\u0002\u0010\u0019><\u0017nY1m%\u0016d\u0017\r^5p]\"1\u0011QH\u0005A\u0002a\fq\u0002^1cY\u0016LE-\u001a8uS\u001aLWM\u001d\u0005\b\u0003\u0003J\u0001\u0019AA\"\u0003A\u0001\u0018\r\u001e5t\u0013:lU\r^1ti>\u0014X\r\u0005\u0004\u0002F\u0005U\u00131\f\b\u0005\u0003\u000f\n\tF\u0004\u0003\u0002J\u0005=SBAA&\u0015\r\ti\u0005M\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019J1!a\u0015&\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0016\u0002Z\t\u00191+Z9\u000b\u0007\u0005MS\u0005\u0005\u0003\u0002^\u0005\u001dTBAA0\u0015\u0011\t\t'a\u0019\u0002\u0005\u0019\u001c(bAA3=\u00051\u0001.\u00193p_BLA!!\u001b\u0002`\t!\u0001+\u0019;i\u0011\u001d\ti'\u0003a\u0001\u0003_\n\u0011c]2iK6\f\u0017J\\'fi\u0006\u001cHo\u001c:f!\u0011\t\t(a\u001e\u000e\u0005\u0005M$bAA;5\u0005)A/\u001f9fg&!\u0011\u0011PA:\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003{J\u0001\u0019AA@\u0003I)\u0007\u0010]3di\u0016$g)\u001b7f\r>\u0014X.\u0019;1\t\u0005\u0005\u00151\u0013\t\u0007\u0003\u0007\u000bY)!%\u000f\t\u0005\u0015\u0015q\u0011\t\u0004\u0003\u0013*\u0013bAAEK\u00051\u0001K]3eK\u001aLA!!$\u0002\u0010\n)1\t\\1tg*\u0019\u0011\u0011R\u0013\u0011\u00071\f\u0019\n\u0002\u0007\u0002\u0016\u0006m\u0014\u0011!A\u0001\u0006\u0003\t9JA\u0002`IE\n2\u0001]AM!\u0011\ti#a'\n\t\u0005u\u0015q\u0006\u0002\u000b\r&dWMR8s[\u0006$\bbBAQ\u0013\u0001\u0007\u00111U\u0001\u0010a\u0006\u0014H/\u001b;j_:\u001c6\r[3nCB)A%a\n\u0002p\u0005qBn\\4XCJt\u0017N\\4V]\u0016D\b/Z2uK\u00124\u0015\u000e\\3G_Jl\u0017\r\u001e\u000b\t\u0003S\u000by+!-\u0002>B\u0019A%a+\n\u0007\u00055VE\u0001\u0003V]&$\bBBA\u001f\u0015\u0001\u0007\u0001\u0010C\u0004\u0002~)\u0001\r!a-1\t\u0005U\u0016\u0011\u0018\t\u0007\u0003\u0007\u000bY)a.\u0011\u00071\fI\f\u0002\u0007\u0002<\u0006E\u0016\u0011!A\u0001\u0006\u0003\t9JA\u0002`IIBq!a0\u000b\u0001\u0004\t\t-\u0001\tbGR,\u0018\r\u001c$jY\u00164uN]7biB!\u00111QAb\u0013\u0011\t)-a$\u0003\rM#(/\u001b8h\u00035I7o\u0014:d!J|\u0007/\u001a:usR!\u00111ZAi!\r!\u0013QZ\u0005\u0004\u0003\u001f,#a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003'\\\u0001\u0019AAa\u0003\rYW-_\u0001\u0012SN\u0004\u0016M]9vKR\u0004&o\u001c9feRLH\u0003BAf\u00033Dq!a5\r\u0001\u0004\t\t-A\u0004d_:4XM\u001d;\u0015\r\u0005-\u0012q\\Au\u0011\u001d\t\t/\u0004a\u0001\u0003G\f\u0001B]3mCRLwN\u001c\t\u0004\r\u0006\u0015\u0018bAAt\u000f\n\t\u0002*\u001b<f)\u0006\u0014G.\u001a*fY\u0006$\u0018n\u001c8\t\u000f\u0005-X\u00021\u0001\u0002L\u00069\u0011n],sSR,\u0017\u0001F2p]Z,'\u000f^*u_J\fw-\u001a$pe6\fG\u000f\u0006\u0003\u0002r\u0006]\bc\u0001$\u0002t&\u0019\u0011Q_$\u0003)\r\u000bG/\u00197pON#xN]1hK\u001a{'/\\1u\u0011\u001d\tIP\u0004a\u0001\u0003c\fqa\u001d;pe\u0006<W-\u0001\rd_:4XM\u001d;U_2{w-[2bYJ+G.\u0019;j_:$B\"a\u000b\u0002\u0000\n\u0005!1\u0002B\r\u0005;Aq!!9\u0010\u0001\u0004\t\u0019\u000fC\u0004\u0003\u0004=\u0001\rA!\u0002\u0002\u000f=\u0004H/[8ogBA\u00111\u0011B\u0004\u0003\u0003\f\t-\u0003\u0003\u0003\n\u0005=%aA'ba\"9!QB\bA\u0002\t=\u0011a\u00044jY\u00164uN]7bi\u000ec\u0017m]:1\t\tE!Q\u0003\t\u0007\u0003\u0007\u000bYIa\u0005\u0011\u00071\u0014)\u0002\u0002\u0007\u0003\u0018\t-\u0011\u0011!A\u0001\u0006\u0003\t9JA\u0002`IMBqAa\u0007\u0010\u0001\u0004\t\t-\u0001\u0005gS2,G+\u001f9f\u0011\u001d\tYo\u0004a\u0001\u0003\u0017\fQ\"\u001b8gKJLeMT3fI\u0016$GC\u0003B\u0012\u0005S\u0011YC!\f\u00032A\u0019aI!\n\n\u0007\t\u001drI\u0001\u0007DCR\fGn\\4UC\ndW\rC\u0004\u0002bB\u0001\r!a9\t\u000f\t\r\u0001\u00031\u0001\u0003\u0006!9!q\u0006\tA\u0002\u0005e\u0015A\u00034jY\u00164uN]7bi\"I!1\u0007\t\u0011\u0002\u0003\u0007!QG\u0001\rM&dW-\u00138eKb|\u0005\u000f\u001e\t\u0006I\u0005\u001d\"q\u0007\t\u0005\u0003[\u0011I$\u0003\u0003\u0003<\u0005=\"!\u0003$jY\u0016Le\u000eZ3y\u0003]IgNZ3s\u0013\u001atU-\u001a3fI\u0012\"WMZ1vYR$C'\u0006\u0002\u0003B)\"!Q\u0007B\"W\t\u0011)\u0005\u0005\u0003\u0003H\tESB\u0001B%\u0015\u0011\u0011YE!\u0014\u0002\u0013Ut7\r[3dW\u0016$'b\u0001B(K\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tM#\u0011\n\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001E;qI\u0006$X\rR1uCN\u001b\u0007.Z7b)\u0019\tIK!\u0017\u0003^!9!1\f\nA\u0002\u0005m\u0011AC5eK:$\u0018NZ5fe\"9!q\f\nA\u0002\u0005=\u0014!\u00048fo\u0012\u000bG/Y*dQ\u0016l\u0017-\u0001\u000bISZ,W*\u001a;bgR|'/Z\"bi\u0006dwn\u001a\t\u0003uQ\u0019\"\u0001F\u0012\u0015\u0005\t\r\u0014\u0001G7fe\u001e,w+\u001b;i\u001b\u0016$\u0018m\u001d;pe\u0016\u001c6\r[3nCR1\u0011q\u000eB7\u0005cBqAa\u001c\u0017\u0001\u0004\ty'A\bnKR\f7\u000f^8sKN\u001b\u0007.Z7b\u0011\u001d\u0011\u0019H\u0006a\u0001\u0003_\na\"\u001b8gKJ\u0014X\rZ*dQ\u0016l\u0017\r"
)
public class HiveMetastoreCatalog implements Logging {
   private final SparkSession sparkSession;
   private final Striped tableCreationLocks;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static StructType mergeWithMetastoreSchema(final StructType metastoreSchema, final StructType inferredSchema) {
      return HiveMetastoreCatalog$.MODULE$.mergeWithMetastoreSchema(metastoreSchema, inferredSchema);
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private SessionState sessionState() {
      return this.sparkSession.sessionState();
   }

   private SessionCatalog catalogProxy() {
      return this.sparkSession.sessionState().catalog();
   }

   private Striped tableCreationLocks() {
      return this.tableCreationLocks;
   }

   private Object withTableCreationLock(final QualifiedTableName tableName, final Function0 f) {
      Lock lock = (Lock)this.tableCreationLocks().get(tableName);
      lock.lock();

      Object var10000;
      try {
         var10000 = f.apply();
      } finally {
         lock.unlock();
      }

      return var10000;
   }

   public LogicalPlan getCachedDataSourceTable(final TableIdentifier table) {
      QualifiedTableName key = .MODULE$.apply(((String)table.catalog().getOrElse(() -> org.apache.spark.sql.connector.catalog.CatalogManager..MODULE$.SESSION_CATALOG_NAME())).toLowerCase(), ((String)table.database().getOrElse(() -> this.sessionState().catalog().getCurrentDatabase())).toLowerCase(), table.table().toLowerCase());
      return this.catalogProxy().getCachedTable(key);
   }

   private Option getCached(final QualifiedTableName tableIdentifier, final Seq pathsInMetastore, final StructType schemaInMetastore, final Class expectedFileFormat, final Option partitionSchema) {
      LogicalPlan var8 = this.catalogProxy().getCachedTable(tableIdentifier);
      if (var8 == null) {
         return scala.None..MODULE$;
      } else {
         if (var8 instanceof LogicalRelation) {
            LogicalRelation var9 = (LogicalRelation)var8;
            Option var10 = org.apache.spark.sql.execution.datasources.LogicalRelationWithTable..MODULE$.unapply(var9);
            if (!var10.isEmpty()) {
               BaseRelation relation = (BaseRelation)((Tuple2)var10.get())._1();
               if (relation instanceof HadoopFsRelation) {
                  HadoopFsRelation var12;
                  label66: {
                     var12 = (HadoopFsRelation)relation;
                     Class cachedRelationFileFormatClass = var12.fileFormat().getClass();
                     if (cachedRelationFileFormatClass == null) {
                        if (expectedFileFormat != null) {
                           break label66;
                        }
                     } else if (!cachedRelationFileFormatClass.equals(expectedFileFormat)) {
                        break label66;
                     }

                     boolean var20;
                     label51: {
                        label50: {
                           label49: {
                              Set var10000 = var12.location().rootPaths().toSet();
                              Set var17 = pathsInMetastore.toSet();
                              if (var10000 == null) {
                                 if (var17 != null) {
                                    break label49;
                                 }
                              } else if (!var10000.equals(var17)) {
                                 break label49;
                              }

                              if (org.apache.spark.sql.catalyst.types.DataTypeUtils..MODULE$.sameType(var9.schema(), schemaInMetastore) && var12.bucketSpec().isEmpty()) {
                                 StructType var19 = var12.partitionSchema();
                                 Object var18 = partitionSchema.getOrElse(() -> org.apache.spark.sql.types.StructType..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
                                 if (var19 == null) {
                                    if (var18 == null) {
                                       break label50;
                                    }
                                 } else if (var19.equals(var18)) {
                                    break label50;
                                 }
                              }
                           }

                           var20 = false;
                           break label51;
                        }

                        var20 = true;
                     }

                     boolean useCached = var20;
                     if (useCached) {
                        return new Some(var9);
                     }

                     this.catalogProxy().invalidateCachedTable(tableIdentifier);
                     return scala.None..MODULE$;
                  }

                  this.logWarningUnexpectedFileFormat(tableIdentifier, expectedFileFormat, var12.fileFormat().toString());
                  this.catalogProxy().invalidateCachedTable(tableIdentifier);
                  return scala.None..MODULE$;
               }
            }
         }

         this.logWarningUnexpectedFileFormat(tableIdentifier, expectedFileFormat, var8.toString());
         this.catalogProxy().invalidateCachedTable(tableIdentifier);
         return scala.None..MODULE$;
      }
   }

   private void logWarningUnexpectedFileFormat(final QualifiedTableName tableIdentifier, final Class expectedFileFormat, final String actualFileFormat) {
      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Table ", " should be stored as "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, tableIdentifier)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". However, we are getting a "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_FORMAT..MODULE$, expectedFileFormat)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " from the metastore cache. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_FORMAT2..MODULE$, actualFileFormat)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"This cached entry will be invalidated."})))).log(scala.collection.immutable.Nil..MODULE$))));
   }

   private boolean isOrcProperty(final String key) {
      return key.startsWith("orc.") || key.contains(".orc.");
   }

   private boolean isParquetProperty(final String key) {
      return key.startsWith("parquet.") || key.contains(".parquet.");
   }

   public LogicalRelation convert(final HiveTableRelation relation, final boolean isWrite) {
      String serde = ((String)relation.tableMeta().storage().serde().getOrElse(() -> "")).toLowerCase(Locale.ROOT);
      if (serde.contains("parquet")) {
         scala.collection.immutable.Map options = (scala.collection.immutable.Map)((MapOps)((scala.collection.MapOps)relation.tableMeta().properties().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$convert$2(this, x0$1)))).$plus$plus(relation.tableMeta().storage().properties())).$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.sql.execution.datasources.parquet.ParquetOptions..MODULE$.MERGE_SCHEMA()), org.apache.spark.sql.internal.SQLConf..MODULE$.get().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_PARQUET_WITH_SCHEMA_MERGING()).toString()));
         return this.convertToLogicalRelation(relation, options, ParquetFileFormat.class, "parquet", isWrite);
      } else {
         scala.collection.immutable.Map options = (scala.collection.immutable.Map)((scala.collection.MapOps)relation.tableMeta().properties().filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$convert$3(this, x0$2)))).$plus$plus(relation.tableMeta().storage().properties());
         Object var10000 = org.apache.spark.sql.internal.SQLConf..MODULE$.get().getConf(org.apache.spark.sql.internal.SQLConf..MODULE$.ORC_IMPLEMENTATION());
         String var6 = "native";
         if (var10000 == null) {
            if (var6 == null) {
               return this.convertToLogicalRelation(relation, options, OrcFileFormat.class, "orc", isWrite);
            }
         } else if (var10000.equals(var6)) {
            return this.convertToLogicalRelation(relation, options, OrcFileFormat.class, "orc", isWrite);
         }

         return this.convertToLogicalRelation(relation, options, org.apache.spark.sql.hive.orc.OrcFileFormat.class, "orc", isWrite);
      }
   }

   public CatalogStorageFormat convertStorageFormat(final CatalogStorageFormat storage) {
      String serde = ((String)storage.serde().getOrElse(() -> "")).toLowerCase(Locale.ROOT);
      if (serde.contains("parquet")) {
         scala.collection.immutable.Map options = (scala.collection.immutable.Map)storage.properties().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.sql.execution.datasources.parquet.ParquetOptions..MODULE$.MERGE_SCHEMA()), org.apache.spark.sql.internal.SQLConf..MODULE$.get().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_PARQUET_WITH_SCHEMA_MERGING()).toString()));
         None x$1 = scala.None..MODULE$;
         Option x$3 = storage.copy$default$1();
         Option x$4 = storage.copy$default$2();
         Option x$5 = storage.copy$default$3();
         boolean x$6 = storage.copy$default$5();
         return storage.copy(x$3, x$4, x$5, x$1, x$6, options);
      } else {
         scala.collection.immutable.Map options;
         label18: {
            options = storage.properties();
            Object var10000 = org.apache.spark.sql.internal.SQLConf..MODULE$.get().getConf(org.apache.spark.sql.internal.SQLConf..MODULE$.ORC_IMPLEMENTATION());
            String var11 = "native";
            if (var10000 == null) {
               if (var11 == null) {
                  break label18;
               }
            } else if (var10000.equals(var11)) {
               break label18;
            }

            None x$13 = scala.None..MODULE$;
            Option x$15 = storage.copy$default$1();
            Option x$16 = storage.copy$default$2();
            Option x$17 = storage.copy$default$3();
            boolean x$18 = storage.copy$default$5();
            return storage.copy(x$15, x$16, x$17, x$13, x$18, options);
         }

         None x$7 = scala.None..MODULE$;
         Option x$9 = storage.copy$default$1();
         Option x$10 = storage.copy$default$2();
         Option x$11 = storage.copy$default$3();
         boolean x$12 = storage.copy$default$5();
         return storage.copy(x$9, x$10, x$11, x$7, x$12, options);
      }
   }

   private LogicalRelation convertToLogicalRelation(final HiveTableRelation relation, final scala.collection.immutable.Map options, final Class fileFormatClass, final String fileType, final boolean isWrite) {
      StructType metastoreSchema = relation.tableMeta().schema();
      QualifiedTableName tableIdentifier = .MODULE$.apply((String)relation.tableMeta().identifier().catalog().get(), relation.tableMeta().database(), relation.tableMeta().identifier().table());
      boolean lazyPruningEnabled = this.sparkSession.sessionState().conf().manageFilesourcePartitions();
      Path tablePath = new Path(relation.tableMeta().location());
      FileFormat fileFormat = (FileFormat)fileFormatClass.getConstructor().newInstance();
      Option bucketSpec = relation.tableMeta().bucketSpec();
      Tuple2 var14 = isWrite ? new Tuple2(options.updated(org.apache.spark.sql.execution.datasources.BucketingUtils..MODULE$.optionForHiveCompatibleBucketWrite(), "true"), bucketSpec) : new Tuple2(options, scala.None..MODULE$);
      if (var14 != null) {
         scala.collection.immutable.Map hiveOptions = (scala.collection.immutable.Map)var14._1();
         Option hiveBucketSpec = (Option)var14._2();
         Tuple2 var13 = new Tuple2(hiveOptions, hiveBucketSpec);
         scala.collection.immutable.Map hiveOptions = (scala.collection.immutable.Map)var13._1();
         Option hiveBucketSpec = (Option)var13._2();
         LogicalRelation var30;
         if (relation.isPartitioned()) {
            StructType partitionSchema = relation.tableMeta().partitionSchema();
            Object var10000;
            if (lazyPruningEnabled) {
               var10000 = new scala.collection.immutable..colon.colon(tablePath, scala.collection.immutable.Nil..MODULE$);
            } else {
               Seq paths = (Seq)this.sparkSession.sharedState().externalCatalog().listPartitions(tableIdentifier.database(), tableIdentifier.name(), this.sparkSession.sharedState().externalCatalog().listPartitions$default$3()).map((p) -> new Path((URI)p.storage().locationUri().get()));
               var10000 = paths.isEmpty() ? new scala.collection.immutable..colon.colon(tablePath, scala.collection.immutable.Nil..MODULE$) : paths;
            }

            Seq rootPaths = (Seq)var10000;
            var30 = (LogicalRelation)this.withTableCreationLock(tableIdentifier, () -> {
               Option cached = this.getCached(tableIdentifier, rootPaths, metastoreSchema, fileFormatClass, new Some(partitionSchema));
               LogicalRelation logicalRelation = (LogicalRelation)cached.getOrElse(() -> {
                  long sizeInBytes = relation.stats().sizeInBytes().toLong();
                  CatalogFileIndex index = new CatalogFileIndex(this.sparkSession, relation.tableMeta(), sizeInBytes);
                  FileIndex fileIndex = (FileIndex)(lazyPruningEnabled ? index : index.filterPartitions(scala.collection.immutable.Nil..MODULE$));
                  CatalogTable updatedTable = this.inferIfNeeded(relation, hiveOptions, fileFormat, scala.Option..MODULE$.apply(fileIndex));
                  scala.collection.immutable.Map enableDynamicPartition = (scala.collection.immutable.Map)hiveOptions.updated(org.apache.spark.sql.execution.datasources.DataSourceUtils..MODULE$.PARTITION_OVERWRITE_MODE(), org.apache.spark.sql.internal.SQLConf.PartitionOverwriteMode..MODULE$.DYNAMIC().toString());
                  HadoopFsRelation fsRelation = new HadoopFsRelation(fileIndex, partitionSchema, updatedTable.dataSchema(), hiveBucketSpec, fileFormat, enableDynamicPartition, this.sparkSession);
                  LogicalRelation created = org.apache.spark.sql.execution.datasources.LogicalRelation..MODULE$.apply(fsRelation, updatedTable);
                  this.catalogProxy().cacheTable(tableIdentifier, created);
                  return created;
               });
               return logicalRelation;
            });
         } else {
            var30 = (LogicalRelation)this.withTableCreationLock(tableIdentifier, () -> {
               Option cached = this.getCached(tableIdentifier, new scala.collection.immutable..colon.colon(tablePath, scala.collection.immutable.Nil..MODULE$), metastoreSchema, fileFormatClass, scala.None..MODULE$);
               LogicalRelation logicalRelation = (LogicalRelation)cached.getOrElse(() -> {
                  CatalogTable updatedTable = this.inferIfNeeded(relation, hiveOptions, fileFormat, this.inferIfNeeded$default$4());
                  LogicalRelation var10000 = org.apache.spark.sql.execution.datasources.LogicalRelation..MODULE$;
                  SparkSession x$1 = this.sparkSession;
                  String var13 = tablePath.toString();
                  List x$2 = scala.collection.immutable.Nil..MODULE$.$colon$colon(var13);
                  Option x$3 = scala.Option..MODULE$.apply(updatedTable.dataSchema());
                  scala.collection.immutable.Map x$5 = (scala.collection.immutable.Map)hiveOptions.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$convertToLogicalRelation$6(x0$1)));
                  Seq x$7 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.apply$default$5();
                  Option x$8 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.apply$default$8();
                  DataSource qual$1 = new DataSource(x$1, fileType, x$2, x$3, x$7, hiveBucketSpec, x$5, x$8);
                  boolean x$9 = qual$1.resolveRelation$default$1();
                  LogicalRelation created = var10000.apply(qual$1.resolveRelation(x$9), updatedTable);
                  this.catalogProxy().cacheTable(tableIdentifier, created);
                  return created;
               });
               return logicalRelation;
            });
         }

         LogicalRelation result = var30;
         if (result.output().length() != relation.output().length()) {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3096", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("resLen"), Integer.toString(result.output().length())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relLen"), Integer.toString(relation.output().length())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("key"), HiveUtils$.MODULE$.CONVERT_METASTORE_PARQUET().key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("ident"), relation.tableMeta().identifier().toString())}))));
         } else if (!((IterableOnceOps)result.output().zip(relation.output())).forall((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$convertToLogicalRelation$7(x0$2)))) {
            throw new AnalysisException("_LEGACY_ERROR_TEMP_3097", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("key"), HiveUtils$.MODULE$.CONVERT_METASTORE_PARQUET().key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("ident"), relation.tableMeta().identifier().toString())}))));
         } else {
            Seq newOutput = (Seq)((IterableOps)result.output().zip(relation.output())).map((x0$3) -> {
               if (x0$3 != null) {
                  AttributeReference a1 = (AttributeReference)x0$3._1();
                  AttributeReference a2 = (AttributeReference)x0$3._2();
                  return a1.withExprId(a2.exprId());
               } else {
                  throw new MatchError(x0$3);
               }
            });
            BaseRelation x$11 = result.copy$default$1();
            Option x$12 = result.copy$default$3();
            boolean x$13 = result.copy$default$4();
            Option x$14 = result.copy$default$5();
            return result.copy(x$11, newOutput, x$12, x$13, x$14);
         }
      } else {
         throw new MatchError(var14);
      }
   }

   private CatalogTable inferIfNeeded(final HiveTableRelation relation, final scala.collection.immutable.Map options, final FileFormat fileFormat, final Option fileIndexOpt) {
      Enumeration.Value inferenceMode;
      boolean var10000;
      label44: {
         label43: {
            inferenceMode = this.sparkSession.sessionState().conf().caseSensitiveInferenceMode();
            Enumeration.Value var8 = org.apache.spark.sql.internal.SQLConf.HiveCaseSensitiveInferenceMode..MODULE$.NEVER_INFER();
            if (inferenceMode == null) {
               if (var8 == null) {
                  break label43;
               }
            } else if (inferenceMode.equals(var8)) {
               break label43;
            }

            if (!relation.tableMeta().schemaPreservesCase()) {
               var10000 = true;
               break label44;
            }
         }

         var10000 = false;
      }

      boolean shouldInfer = var10000;
      String tableName = relation.tableMeta().identifier().unquotedString();
      if (shouldInfer) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Inferring case-sensitive schema for table ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, tableName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(inference mode:  ", ")})"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INFERENCE_MODE..MODULE$, inferenceMode)}))))));
         FileIndex fileIndex = (FileIndex)fileIndexOpt.getOrElse(() -> {
            Path rootPath = new Path(relation.tableMeta().location());
            return new InMemoryFileIndex(this.sparkSession, new scala.collection.immutable..colon.colon(rootPath, scala.collection.immutable.Nil..MODULE$), options, scala.None..MODULE$, org.apache.spark.sql.execution.datasources.InMemoryFileIndex..MODULE$.$lessinit$greater$default$5(), org.apache.spark.sql.execution.datasources.InMemoryFileIndex..MODULE$.$lessinit$greater$default$6(), org.apache.spark.sql.execution.datasources.InMemoryFileIndex..MODULE$.$lessinit$greater$default$7());
         });
         Option inferredSchema = fileFormat.inferSchema(this.sparkSession, options, (Seq)((IterableOps)fileIndex.listFiles(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$).flatMap((x$2x) -> x$2x.files())).map((x$3x) -> x$3x.fileStatus())).map((x$4x) -> HiveMetastoreCatalog$.MODULE$.mergeWithMetastoreSchema(relation.tableMeta().dataSchema(), x$4x));
         if (!(inferredSchema instanceof Some)) {
            if (scala.None..MODULE$.equals(inferredSchema)) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to infer schema for table ", " from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, tableName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"file format ", " (inference mode: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_FORMAT..MODULE$, fileFormat)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "). Using metastore schema."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INFERENCE_MODE..MODULE$, inferenceMode)}))))));
               return relation.tableMeta();
            } else {
               throw new MatchError(inferredSchema);
            }
         } else {
            StructType dataSchema;
            label32: {
               Some var13 = (Some)inferredSchema;
               dataSchema = (StructType)var13.value();
               Enumeration.Value var15 = org.apache.spark.sql.internal.SQLConf.HiveCaseSensitiveInferenceMode..MODULE$.INFER_AND_SAVE();
               if (inferenceMode == null) {
                  if (var15 != null) {
                     break label32;
                  }
               } else if (!inferenceMode.equals(var15)) {
                  break label32;
               }

               this.updateDataSchema(relation.tableMeta().identifier(), dataSchema);
            }

            StructType newSchema = org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)dataSchema.$plus$plus(relation.tableMeta().partitionSchema()));
            TableIdentifier x$2 = relation.tableMeta().copy$default$1();
            CatalogTableType x$3 = relation.tableMeta().copy$default$2();
            CatalogStorageFormat x$4 = relation.tableMeta().copy$default$3();
            Option x$5 = relation.tableMeta().copy$default$5();
            Seq x$6 = relation.tableMeta().copy$default$6();
            Option x$7 = relation.tableMeta().copy$default$7();
            String x$8 = relation.tableMeta().copy$default$8();
            long x$9 = relation.tableMeta().copy$default$9();
            long x$10 = relation.tableMeta().copy$default$10();
            String x$11 = relation.tableMeta().copy$default$11();
            scala.collection.immutable.Map x$12 = relation.tableMeta().copy$default$12();
            Option x$13 = relation.tableMeta().copy$default$13();
            Option x$14 = relation.tableMeta().copy$default$14();
            Option x$15 = relation.tableMeta().copy$default$15();
            Option x$16 = relation.tableMeta().copy$default$16();
            Seq x$17 = relation.tableMeta().copy$default$17();
            boolean x$18 = relation.tableMeta().copy$default$18();
            boolean x$19 = relation.tableMeta().copy$default$19();
            scala.collection.immutable.Map x$20 = relation.tableMeta().copy$default$20();
            Option x$21 = relation.tableMeta().copy$default$21();
            return relation.tableMeta().copy(x$2, x$3, x$4, newSchema, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
         }
      } else {
         return relation.tableMeta();
      }
   }

   private Option inferIfNeeded$default$4() {
      return scala.None..MODULE$;
   }

   private void updateDataSchema(final TableIdentifier identifier, final StructType newDataSchema) {
      try {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Saving case-sensitive schema for table ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, identifier.unquotedString())})))));
         this.sparkSession.sessionState().catalog().alterTableDataSchema(identifier, newDataSchema);
      } catch (Throwable var7) {
         if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
            throw var7;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to save case-sensitive schema for table "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TABLE_NAME..MODULE$, identifier.unquotedString())}))))), var7);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$convert$2(final HiveMetastoreCatalog $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return $this.isParquetProperty(k);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convert$3(final HiveMetastoreCatalog $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         return $this.isOrcProperty(k);
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertToLogicalRelation$6(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return !k.equalsIgnoreCase("path");
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertToLogicalRelation$7(final Tuple2 x0$2) {
      if (x0$2 == null) {
         throw new MatchError(x0$2);
      } else {
         boolean var6;
         label30: {
            AttributeReference a1 = (AttributeReference)x0$2._1();
            AttributeReference a2 = (AttributeReference)x0$2._2();
            DataType var10000 = a1.dataType();
            DataType var5 = a2.dataType();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public HiveMetastoreCatalog(final SparkSession sparkSession) {
      this.sparkSession = sparkSession;
      Logging.$init$(this);
      this.tableCreationLocks = Striped.lazyWeakLock(100);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
