package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.apache.spark.SparkThrowable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.plans.logical.CommandResult;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.execution.QueryExecution;
import org.apache.spark.sql.execution.QueryExecutionException;
import org.apache.spark.sql.internal.SessionState;
import org.apache.spark.sql.internal.VariableSubstitution;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Some;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud!B\u000b\u0017\u0001a\u0011\u0003\u0002\u0003\u001a\u0001\u0005\u000b\u0007I\u0011\u0001\u001b\t\u0011e\u0002!\u0011!Q\u0001\nUBQA\u000f\u0001\u0005\u0002mB!b\u0010\u0001A\u0002\u0003\u0007I\u0011\u0001\rA\u0011)I\u0005\u00011AA\u0002\u0013\u0005\u0001D\u0013\u0005\n'\u0002\u0001\r\u0011!Q!\n\u0005C!\u0002\u0016\u0001A\u0002\u0003\u0007I\u0011\u0001\rV\u0011)Q\u0007\u00011AA\u0002\u0013\u0005\u0001d\u001b\u0005\n[\u0002\u0001\r\u0011!Q!\nYCQA\u001c\u0001\u0005B=DQ\u0001\u001d\u0001\u0005\nEDQA\u001f\u0001\u0005BmDq!!\u0003\u0001\t\u0003\nY\u0001C\u0004\u0002\u0014\u0001!\t%!\u0006\t\u000f\u0005%\u0003\u0001\"\u0011\u0002L!1\u0011Q\n\u0001\u0005B=<!\"a\u0014\u0017\u0003\u0003E\t\u0001GA)\r%)b#!A\t\u0002a\t\u0019\u0006\u0003\u0004;%\u0011\u0005\u00111\f\u0005\n\u0003;\u0012\u0012\u0013!C\u0001\u0003?\u0012ab\u00159be.\u001c\u0016\u000b\u0014#sSZ,'O\u0003\u0002\u00181\u0005aA\u000f\u001b:jMR\u001cXM\u001d<fe*\u0011\u0011DG\u0001\u0005Q&4XM\u0003\u0002\u001c9\u0005\u00191/\u001d7\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c2\u0001A\u0012-!\t!#&D\u0001&\u0015\t1s%\u0001\u0002rY*\u0011\u0011\u0004\u000b\u0006\u0003Sy\ta\u0001[1e_>\u0004\u0018BA\u0016&\u0005\u0019!%/\u001b<feB\u0011Q\u0006M\u0007\u0002])\u0011q\u0006H\u0001\tS:$XM\u001d8bY&\u0011\u0011G\f\u0002\b\u0019><w-\u001b8h\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o\u0007\u0001)\u0012!\u000e\t\u0003m]j\u0011AG\u0005\u0003qi\u0011Ab\u00159be.\u001cVm]:j_:\fQb\u001d9be.\u001cVm]:j_:\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002=}A\u0011Q\bA\u0007\u0002-!9!g\u0001I\u0001\u0002\u0004)\u0014a\u0003;bE2,7k\u00195f[\u0006,\u0012!\u0011\t\u0003\u0005\u001ek\u0011a\u0011\u0006\u0003\t\u0016\u000b1!\u00199j\u0015\t1u%A\u0005nKR\f7\u000f^8sK&\u0011\u0001j\u0011\u0002\u0007'\u000eDW-\\1\u0002\u001fQ\f'\r\\3TG\",W.Y0%KF$\"aS)\u0011\u00051{U\"A'\u000b\u00039\u000bQa]2bY\u0006L!\u0001U'\u0003\tUs\u0017\u000e\u001e\u0005\b%\u0016\t\t\u00111\u0001B\u0003\rAH%M\u0001\ri\u0006\u0014G.Z*dQ\u0016l\u0017\rI\u0001\rQ&4XMU3ta>t7/Z\u000b\u0002-B\u0019qk\u00182\u000f\u0005akfBA-]\u001b\u0005Q&BA.4\u0003\u0019a$o\\8u}%\ta*\u0003\u0002_\u001b\u00069\u0001/Y2lC\u001e,\u0017B\u00011b\u0005\r\u0019V-\u001d\u0006\u0003=6\u0003\"aY4\u000f\u0005\u0011,\u0007CA-N\u0013\t1W*\u0001\u0004Qe\u0016$WMZ\u0005\u0003Q&\u0014aa\u0015;sS:<'B\u00014N\u0003AA\u0017N^3SKN\u0004xN\\:f?\u0012*\u0017\u000f\u0006\u0002LY\"9!\u000bCA\u0001\u0002\u00041\u0016!\u00045jm\u0016\u0014Vm\u001d9p]N,\u0007%\u0001\u0003j]&$H#A&\u0002%\u001d,GOU3tk2$8+\u001a;TG\",W.\u0019\u000b\u0003\u0003JDQa]\u0006A\u0002Q\fQ!];fef\u0004\"!\u001e=\u000e\u0003YT!a\u001e\u000e\u0002\u0013\u0015DXmY;uS>t\u0017BA=w\u00059\tV/\u001a:z\u000bb,7-\u001e;j_:\f1A];o)\ra\u0018Q\u0001\t\u0004{\u0006\u0005Q\"\u0001@\u000b\u0005},\u0013A\u00039s_\u000e,7o]8sg&\u0019\u00111\u0001@\u00031\r{W.\\1oIB\u0013xnY3tg>\u0014(+Z:q_:\u001cX\r\u0003\u0004\u0002\b1\u0001\rAY\u0001\bG>lW.\u00198e\u0003\u0015\u0019Gn\\:f)\t\ti\u0001E\u0002M\u0003\u001fI1!!\u0005N\u0005\rIe\u000e^\u0001\u000bO\u0016$(+Z:vYR\u001cH\u0003BA\f\u0003;\u00012\u0001TA\r\u0013\r\tY\"\u0014\u0002\b\u0005>|G.Z1o\u0011\u001d\tyB\u0004a\u0001\u0003C\t1A]3ta\u0011\t\u0019#a\u000e\u0011\r\u0005\u0015\u0012qFA\u001a\u001b\t\t9C\u0003\u0003\u0002*\u0005-\u0012\u0001B;uS2T!!!\f\u0002\t)\fg/Y\u0005\u0005\u0003c\t9C\u0001\u0003MSN$\b\u0003BA\u001b\u0003oa\u0001\u0001\u0002\u0007\u0002:\u0005u\u0011\u0011!A\u0001\u0006\u0003\tYDA\u0002`IE\nB!!\u0010\u0002DA\u0019A*a\u0010\n\u0007\u0005\u0005SJA\u0004O_RD\u0017N\\4\u0011\u00071\u000b)%C\u0002\u0002H5\u00131!\u00118z\u0003%9W\r^*dQ\u0016l\u0017\rF\u0001B\u0003\u001d!Wm\u001d;s_f\fab\u00159be.\u001c\u0016\u000b\u0014#sSZ,'\u000f\u0005\u0002>%M\u0019!#!\u0016\u0011\u00071\u000b9&C\u0002\u0002Z5\u0013a!\u00118z%\u00164GCAA)\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\r\u0016\u0004k\u0005\r4FAA3!\u0011\t9'!\u001d\u000e\u0005\u0005%$\u0002BA6\u0003[\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=T*\u0001\u0006b]:|G/\u0019;j_:LA!a\u001d\u0002j\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class SparkSQLDriver extends Driver implements Logging {
   private final SparkSession sparkSession;
   private Schema tableSchema;
   private Seq hiveResponse;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static SparkSession $lessinit$greater$default$1() {
      return SparkSQLDriver$.MODULE$.$lessinit$greater$default$1();
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

   public SparkSession sparkSession() {
      return this.sparkSession;
   }

   public Schema tableSchema() {
      return this.tableSchema;
   }

   public void tableSchema_$eq(final Schema x$1) {
      this.tableSchema = x$1;
   }

   public Seq hiveResponse() {
      return this.hiveResponse;
   }

   public void hiveResponse_$eq(final Seq x$1) {
      this.hiveResponse = x$1;
   }

   public void init() {
   }

   private Schema getResultSetSchema(final QueryExecution query) {
      LogicalPlan analyzed = query.analyzed();
      this.logDebug((Function0)(() -> "Result Schema: " + analyzed.output()));
      if (analyzed.output().isEmpty()) {
         return new Schema(Arrays.asList((Object[])(new FieldSchema[]{new FieldSchema("Response code", "string", "")})), (Map)null);
      } else {
         Seq fieldSchemas = (Seq)analyzed.output().map((attr) -> new FieldSchema(attr.name(), attr.dataType().catalogString(), ""));
         return new Schema(.MODULE$.SeqHasAsJava(fieldSchemas).asJava(), (Map)null);
      }
   }

   public CommandProcessorResponse run(final String command) {
      try {
         String substitutorCommand = (String)org.apache.spark.sql.internal.SQLConf..MODULE$.withExistingConf(this.sparkSession().sessionState().conf(), () -> (new VariableSubstitution()).substitute(command));
         this.sparkSession().sparkContext().setJobDescription(substitutorCommand);
         SessionState qual$1 = this.sparkSession().sessionState();
         LogicalPlan x$1 = org.apache.spark.sql.classic.ClassicConversions..MODULE$.castToImpl(this.sparkSession().sql(command)).logicalPlan();
         Enumeration.Value x$2 = qual$1.executePlan$default$2();
         QueryExecution execution = qual$1.executePlan(x$1, x$2);
         LogicalPlan var9 = execution.logical();
         if (var9 instanceof CommandResult) {
            this.hiveResponse_$eq(org.apache.spark.sql.execution.HiveResult..MODULE$.hiveResultString(execution.executedPlan()));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.hiveResponse_$eq((Seq)org.apache.spark.sql.execution.SQLExecution..MODULE$.withNewExecutionId(execution, new Some("cli"), () -> org.apache.spark.sql.execution.HiveResult..MODULE$.hiveResultString(execution.executedPlan())));
            BoxedUnit var15 = BoxedUnit.UNIT;
         }

         this.tableSchema_$eq(this.getResultSetSchema(execution));
         return new CommandProcessorResponse(0);
      } catch (Throwable var14) {
         if (var14 instanceof SparkThrowable) {
            this.logDebug((Function0)(() -> "Failed in [" + command + "]"), var14);
            throw var14;
         } else if (var14 != null) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed in [", "]"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMMAND..MODULE$, command)})))), var14);
            throw new QueryExecutionException(ExceptionUtils.getStackTrace(var14), org.apache.spark.sql.execution.QueryExecutionException..MODULE$.$lessinit$greater$default$2());
         } else {
            throw var14;
         }
      }
   }

   public int close() {
      this.hiveResponse_$eq((Seq)null);
      this.tableSchema_$eq((Schema)null);
      return 0;
   }

   public boolean getResults(final List res) {
      if (this.hiveResponse() == null) {
         return false;
      } else {
         ((ArrayList)res).addAll(.MODULE$.SeqHasAsJava(this.hiveResponse()).asJava());
         this.hiveResponse_$eq((Seq)null);
         return true;
      }
   }

   public Schema getSchema() {
      return this.tableSchema();
   }

   public void destroy() {
      super.destroy();
      this.hiveResponse_$eq((Seq)null);
      this.tableSchema_$eq((Schema)null);
   }

   public SparkSQLDriver(final SparkSession sparkSession) {
      this.sparkSession = sparkSession;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
