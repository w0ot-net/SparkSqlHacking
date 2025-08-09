package org.apache.spark.sql.hive.execution;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.io.HiveOutputFormat;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.errors.QueryExecutionErrors.;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.OutputWriter;
import org.apache.spark.sql.execution.datasources.OutputWriterFactory;
import org.apache.spark.sql.hive.HiveTableUtil$;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.sources.DataSourceRegister;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.SerializableJobConf;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b\u0001B\u0005\u000b\u0001]A\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\u0006{\u0001!\tA\u0010\u0005\u0006{\u0001!\tA\u0011\u0005\u0006\u0007\u0002!\t\u0005\u0012\u0005\u0006!\u0002!\t\u0005\u0012\u0005\u0006#\u0002!\tE\u0015\u0005\u0006q\u0002!\t%\u001f\u0005\b\u0003'\u0001A\u0011IA\u000b\u00059A\u0015N^3GS2,gi\u001c:nCRT!a\u0003\u0007\u0002\u0013\u0015DXmY;uS>t'BA\u0007\u000f\u0003\u0011A\u0017N^3\u000b\u0005=\u0001\u0012aA:rY*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001Ab$J\u0016\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\ty2%D\u0001!\u0015\t\t#%A\u0006eCR\f7o\\;sG\u0016\u001c(BA\u0006\u000f\u0013\t!\u0003E\u0001\u0006GS2,gi\u001c:nCR\u0004\"AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\b\u0002\u000fM|WO]2fg&\u0011!f\n\u0002\u0013\t\u0006$\u0018mU8ve\u000e,'+Z4jgR,'\u000f\u0005\u0002-_5\tQF\u0003\u0002/!\u0005A\u0011N\u001c;fe:\fG.\u0003\u00021[\t9Aj\\4hS:<\u0017\u0001\u00044jY\u0016\u001c\u0016N\\6D_:4\u0007CA\u001a<\u001b\u0005!$BA\u001b7\u0003\u0011\u0001H.\u00198\u000b\u0005]B\u0014AA9m\u0015\ti\u0011H\u0003\u0002;%\u00051\u0001.\u00193p_BL!\u0001\u0010\u001b\u0003\u0019\u0019KG.Z*j].$Um]2\u0002\rqJg.\u001b;?)\ty\u0014\t\u0005\u0002A\u00015\t!\u0002C\u00032\u0005\u0001\u0007!\u0007F\u0001@\u0003%\u0019\bn\u001c:u\u001d\u0006lW\rF\u0001F!\t1UJ\u0004\u0002H\u0017B\u0011\u0001JG\u0007\u0002\u0013*\u0011!JF\u0001\u0007yI|w\u000e\u001e \n\u00051S\u0012A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001\u0014\u000e\u0002\u0011Q|7\u000b\u001e:j]\u001e\f1\"\u001b8gKJ\u001c6\r[3nCR!1\u000b\u00182h!\rIBKV\u0005\u0003+j\u0011aa\u00149uS>t\u0007CA,[\u001b\u0005A&BA-\u000f\u0003\u0015!\u0018\u0010]3t\u0013\tY\u0006L\u0001\u0006TiJ,8\r\u001e+za\u0016DQ!\u0018\u0004A\u0002y\u000bAb\u001d9be.\u001cVm]:j_:\u0004\"a\u00181\u000e\u00039I!!\u0019\b\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\t\u000b\r4\u0001\u0019\u00013\u0002\u000f=\u0004H/[8ogB!a)Z#F\u0013\t1wJA\u0002NCBDQ\u0001\u001b\u0004A\u0002%\fQAZ5mKN\u00042A[8s\u001d\tYWN\u0004\u0002IY&\t1$\u0003\u0002o5\u00059\u0001/Y2lC\u001e,\u0017B\u00019r\u0005\r\u0019V-\u001d\u0006\u0003]j\u0001\"a\u001d<\u000e\u0003QT!!^\u001d\u0002\u0005\u0019\u001c\u0018BA<u\u0005)1\u0015\u000e\\3Ti\u0006$Xo]\u0001\raJ,\u0007/\u0019:f/JLG/\u001a\u000b\buvt\u0018QBA\b!\ty20\u0003\u0002}A\t\u0019r*\u001e;qkR<&/\u001b;fe\u001a\u000b7\r^8ss\")Ql\u0002a\u0001=\"1qp\u0002a\u0001\u0003\u0003\t1A[8c!\u0011\t\u0019!!\u0003\u000e\u0005\u0005\u0015!bAA\u0004s\u0005IQ.\u00199sK\u0012,8-Z\u0005\u0005\u0003\u0017\t)AA\u0002K_\nDQaY\u0004A\u0002\u0011Da!!\u0005\b\u0001\u00041\u0016A\u00033bi\u0006\u001c6\r[3nC\u0006\u00012/\u001e9q_J$h)[3mI:\u000bW.\u001a\u000b\u0005\u0003/\ti\u0002E\u0002\u001a\u00033I1!a\u0007\u001b\u0005\u001d\u0011un\u001c7fC:Da!a\b\t\u0001\u0004)\u0015\u0001\u00028b[\u0016\u0004"
)
public class HiveFileFormat implements FileFormat, DataSourceRegister, Logging {
   private final FileSinkDesc fileSinkConf;
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

   public boolean supportBatch(final SparkSession sparkSession, final StructType dataSchema) {
      return FileFormat.supportBatch$(this, sparkSession, dataSchema);
   }

   public Option vectorTypes(final StructType requiredSchema, final StructType partitionSchema, final SQLConf sqlConf) {
      return FileFormat.vectorTypes$(this, requiredSchema, partitionSchema, sqlConf);
   }

   public boolean isSplitable(final SparkSession sparkSession, final scala.collection.immutable.Map options, final Path path) {
      return FileFormat.isSplitable$(this, sparkSession, options, path);
   }

   public Function1 buildReader(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final scala.collection.immutable.Map options, final Configuration hadoopConf) {
      return FileFormat.buildReader$(this, sparkSession, dataSchema, partitionSchema, requiredSchema, filters, options, hadoopConf);
   }

   public Function1 buildReaderWithPartitionValues(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final scala.collection.immutable.Map options, final Configuration hadoopConf) {
      return FileFormat.buildReaderWithPartitionValues$(this, sparkSession, dataSchema, partitionSchema, requiredSchema, filters, options, hadoopConf);
   }

   public AttributeReference createFileMetadataCol() {
      return FileFormat.createFileMetadataCol$(this);
   }

   public boolean supportDataType(final DataType dataType) {
      return FileFormat.supportDataType$(this, dataType);
   }

   public boolean allowDuplicatedColumnNames() {
      return FileFormat.allowDuplicatedColumnNames$(this);
   }

   public Seq metadataSchemaFields() {
      return FileFormat.metadataSchemaFields$(this);
   }

   public scala.collection.immutable.Map fileConstantMetadataExtractors() {
      return FileFormat.fileConstantMetadataExtractors$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String shortName() {
      return "hive";
   }

   public String toString() {
      return "Hive";
   }

   public Option inferSchema(final SparkSession sparkSession, final scala.collection.immutable.Map options, final Seq files) {
      throw .MODULE$.inferSchemaUnsupportedForHiveError();
   }

   public OutputWriterFactory prepareWrite(final SparkSession sparkSession, final Job job, final scala.collection.immutable.Map options, final StructType dataSchema) {
      Configuration conf = job.getConfiguration();
      TableDesc tableDesc = this.fileSinkConf.getTableInfo();
      conf.set("mapred.output.format.class", tableDesc.getOutputFileFormatClassName());
      boolean speculationEnabled = BoxesRunTime.unboxToBoolean(sparkSession.sparkContext().conf().get(org.apache.spark.internal.config.package..MODULE$.SPECULATION_ENABLED()));
      String outputCommitterClass = conf.get("mapred.output.committer.class", "");
      if (speculationEnabled && outputCommitterClass.contains("Direct")) {
         MessageWithContext warningMessage = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " may be an output committer that writes data "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, outputCommitterClass)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"directly to the final location. Because speculation is enabled, this output "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"committer may cause data loss (see the case in SPARK-10063). If possible, please "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"use an output committer that does not have this behavior (e.g. FileOutputCommitter)."})))).log(scala.collection.immutable.Nil..MODULE$));
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warningMessage));
      }

      HiveTableUtil$.MODULE$.configureJobPropertiesForStorageHandler(tableDesc, conf, false);
      Utilities.copyTableJobPropertiesToConf(tableDesc, conf);
      FileSinkDesc fileSinkConfSer = this.fileSinkConf;
      return new OutputWriterFactory(conf, fileSinkConfSer) {
         private transient HiveOutputFormat outputFormat;
         private final SerializableJobConf jobConf;
         private transient volatile boolean bitmap$trans$0;
         private final FileSinkDesc fileSinkConfSer$1;

         private SerializableJobConf jobConf() {
            return this.jobConf;
         }

         private HiveOutputFormat outputFormat$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$trans$0) {
                  this.outputFormat = (HiveOutputFormat)this.jobConf().value().getOutputFormat();
                  this.bitmap$trans$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            return this.outputFormat;
         }

         private HiveOutputFormat outputFormat() {
            return !this.bitmap$trans$0 ? this.outputFormat$lzycompute() : this.outputFormat;
         }

         public String getFileExtension(final TaskAttemptContext context) {
            return Utilities.getFileExtension(this.jobConf().value(), this.fileSinkConfSer$1.getCompressed(), this.outputFormat());
         }

         public OutputWriter newInstance(final String path, final StructType dataSchema, final TaskAttemptContext context) {
            return new HiveOutputWriter(path, this.fileSinkConfSer$1, this.jobConf().value(), dataSchema);
         }

         public {
            this.fileSinkConfSer$1 = fileSinkConfSer$1;
            this.jobConf = new SerializableJobConf(new JobConf(conf$1));
         }
      };
   }

   public boolean supportFieldName(final String name) {
      String var3 = this.fileSinkConf.getTableInfo().getOutputFileFormatClassName();
      switch (var3 == null ? 0 : var3.hashCode()) {
         case -1729396387:
            if ("org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat".equals(var3)) {
               return !name.matches(".*[ ,;{}()\n\t=].*");
            }
            break;
         case 1024582826:
            if ("org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat".equals(var3)) {
               boolean var10000;
               try {
                  TypeInfoUtils.getTypeInfoFromTypeString("struct<" + name + ":int>");
                  var10000 = true;
               } catch (IllegalArgumentException var4) {
                  var10000 = false;
               }

               return var10000;
            }
      }

      return true;
   }

   public HiveFileFormat(final FileSinkDesc fileSinkConf) {
      this.fileSinkConf = fileSinkConf;
      FileFormat.$init$(this);
      Logging.$init$(this);
   }

   public HiveFileFormat() {
      this((FileSinkDesc)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
