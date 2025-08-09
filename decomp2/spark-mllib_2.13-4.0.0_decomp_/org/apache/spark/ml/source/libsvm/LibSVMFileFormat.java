package org.apache.spark.ml.source.libsvm;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.catalyst.expressions.ExprId;
import org.apache.spark.sql.catalyst.expressions.UnsafeProjection;
import org.apache.spark.sql.catalyst.types.DataTypeUtils.;
import org.apache.spark.sql.execution.datasources.HadoopFileLinesReader;
import org.apache.spark.sql.execution.datasources.OutputWriter;
import org.apache.spark.sql.execution.datasources.OutputWriterFactory;
import org.apache.spark.sql.execution.datasources.TextBasedFileFormat;
import org.apache.spark.sql.sources.DataSourceRegister;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.SerializableConfiguration;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc!\u0002\u0005\n\u0001%)\u0002\"\u0002\u0017\u0001\t\u0003q\u0003\"B\u0019\u0001\t\u0003\u0012\u0004\"\u0002!\u0001\t\u0003\u0012\u0004\"B!\u0001\t\u0013\u0011\u0005\"\u0002+\u0001\t\u0003*\u0006\"B<\u0001\t\u0003B\bbBA\b\u0001\u0011\u0005\u0013\u0011\u0003\u0002\u0011\u0019&\u00147KV'GS2,gi\u001c:nCRT!AC\u0006\u0002\r1L'm\u001d<n\u0015\taQ\"\u0001\u0004t_V\u00148-\u001a\u0006\u0003\u001d=\t!!\u001c7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001cB\u0001\u0001\f!MA\u0011qCH\u0007\u00021)\u0011\u0011DG\u0001\fI\u0006$\u0018m]8ve\u000e,7O\u0003\u0002\u001c9\u0005IQ\r_3dkRLwN\u001c\u0006\u0003;=\t1a]9m\u0013\ty\u0002DA\nUKb$()Y:fI\u001aKG.\u001a$pe6\fG\u000f\u0005\u0002\"I5\t!E\u0003\u0002$9\u000591o\\;sG\u0016\u001c\u0018BA\u0013#\u0005I!\u0015\r^1T_V\u00148-\u001a*fO&\u001cH/\u001a:\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0005%z\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005-B#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\u0006\u0005\u00021\u00015\t\u0011\"A\u0005tQ>\u0014HOT1nKR\t1\u0007\u0005\u00025{9\u0011Qg\u000f\t\u0003mej\u0011a\u000e\u0006\u0003q5\na\u0001\u0010:p_Rt$\"\u0001\u001e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qJ\u0014A\u0002)sK\u0012,g-\u0003\u0002?\u007f\t11\u000b\u001e:j]\u001eT!\u0001P\u001d\u0002\u0011Q|7\u000b\u001e:j]\u001e\fAB^3sS\u001aL8k\u00195f[\u0006$2aQ$P!\t!U)D\u0001:\u0013\t1\u0015H\u0001\u0003V]&$\b\"\u0002%\u0005\u0001\u0004I\u0015A\u00033bi\u0006\u001c6\r[3nCB\u0011!*T\u0007\u0002\u0017*\u0011A\nH\u0001\u0006if\u0004Xm]\u0005\u0003\u001d.\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015\u0001F\u00011\u0001R\u0003)1wN],sSRLgn\u001a\t\u0003\tJK!aU\u001d\u0003\u000f\t{w\u000e\\3b]\u0006Y\u0011N\u001c4feN\u001b\u0007.Z7b)\u00111\u0016l\u00183\u0011\u0007\u0011;\u0016*\u0003\u0002Ys\t1q\n\u001d;j_:DQAW\u0003A\u0002m\u000bAb\u001d9be.\u001cVm]:j_:\u0004\"\u0001X/\u000e\u0003qI!A\u0018\u000f\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\t\u000b\u0001,\u0001\u0019A1\u0002\u000f=\u0004H/[8ogB!AGY\u001a4\u0013\t\u0019wHA\u0002NCBDQ!Z\u0003A\u0002\u0019\fQAZ5mKN\u00042a\u001a7p\u001d\tA'N\u0004\u00027S&\t!(\u0003\u0002ls\u00059\u0001/Y2lC\u001e,\u0017BA7o\u0005\r\u0019V-\u001d\u0006\u0003Wf\u0002\"\u0001];\u000e\u0003ET!A]:\u0002\u0005\u0019\u001c(B\u0001;\u0012\u0003\u0019A\u0017\rZ8pa&\u0011a/\u001d\u0002\u000b\r&dWm\u0015;biV\u001c\u0018\u0001\u00049sKB\f'/Z,sSR,GcB=}{\u0006-\u0011Q\u0002\t\u0003/iL!a\u001f\r\u0003'=+H\u000f];u/JLG/\u001a:GC\u000e$xN]=\t\u000bi3\u0001\u0019A.\t\u000by4\u0001\u0019A@\u0002\u0007)|'\r\u0005\u0003\u0002\u0002\u0005\u001dQBAA\u0002\u0015\r\t)a]\u0001\n[\u0006\u0004(/\u001a3vG\u0016LA!!\u0003\u0002\u0004\t\u0019!j\u001c2\t\u000b\u00014\u0001\u0019A1\t\u000b!3\u0001\u0019A%\u0002\u0017\t,\u0018\u000e\u001c3SK\u0006$WM\u001d\u000b\u0011\u0003'\t\t$a\r\u00026\u0005e\u0012QHA%\u0003\u0017\u0002r\u0001RA\u000b\u00033\ty\"C\u0002\u0002\u0018e\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0007]\tY\"C\u0002\u0002\u001ea\u0011q\u0002U1si&$\u0018n\u001c8fI\u001aKG.\u001a\t\u0006O\u0006\u0005\u0012QE\u0005\u0004\u0003Gq'\u0001C%uKJ\fGo\u001c:\u0011\t\u0005\u001d\u0012QF\u0007\u0003\u0003SQ1!a\u000b\u001d\u0003!\u0019\u0017\r^1msN$\u0018\u0002BA\u0018\u0003S\u00111\"\u00138uKJt\u0017\r\u001c*po\")!l\u0002a\u00017\")\u0001j\u0002a\u0001\u0013\"1\u0011qG\u0004A\u0002%\u000bq\u0002]1si&$\u0018n\u001c8TG\",W.\u0019\u0005\u0007\u0003w9\u0001\u0019A%\u0002\u001dI,\u0017/^5sK\u0012\u001c6\r[3nC\"9\u0011qH\u0004A\u0002\u0005\u0005\u0013a\u00024jYR,'o\u001d\t\u0005O2\f\u0019\u0005E\u0002\"\u0003\u000bJ1!a\u0012#\u0005\u00191\u0015\u000e\u001c;fe\")\u0001m\u0002a\u0001C\"9\u0011QJ\u0004A\u0002\u0005=\u0013A\u00035bI>|\u0007oQ8oMB!\u0011\u0011KA,\u001b\t\t\u0019FC\u0002\u0002VM\fAaY8oM&!\u0011\u0011LA*\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0002"
)
public class LibSVMFileFormat extends TextBasedFileFormat implements DataSourceRegister, Logging {
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

   public String shortName() {
      return "libsvm";
   }

   public String toString() {
      return "LibSVM";
   }

   private void verifySchema(final StructType dataSchema, final boolean forWriting) {
      if (dataSchema.size() != 2 || !.MODULE$.sameType(dataSchema.apply(0).dataType(), DataTypes.DoubleType) || !.MODULE$.sameType(dataSchema.apply(1).dataType(), new VectorUDT()) || !forWriting && (int)dataSchema.apply(1).metadata().getLong(LibSVMOptions$.MODULE$.NUM_FEATURES()) <= 0) {
         throw new IOException("Illegal schema for libsvm data, schema=" + dataSchema);
      }
   }

   public Option inferSchema(final SparkSession sparkSession, final scala.collection.immutable.Map options, final Seq files) {
      LibSVMOptions libSVMOptions = new LibSVMOptions(options);
      int numFeatures = BoxesRunTime.unboxToInt(libSVMOptions.numFeatures().getOrElse((JFunction0.mcI.sp)() -> {
         scala.Predef..MODULE$.require(files.nonEmpty(), () -> "No input path specified for libsvm data");
         this.logWarning((Function0)(() -> "'numFeatures' option not specified, determining the number of features by going though the input. If you know the number in advance, please specify it via 'numFeatures' option to avoid the extra scan."));
         Seq paths = (Seq)files.map((x$1) -> x$1.getPath().toString());
         RDD parsed = MLUtils$.MODULE$.parseLibSVMFile(sparkSession, paths, options);
         return MLUtils$.MODULE$.computeNumFeatures(parsed);
      }));
      StructField labelField = new StructField("label", org.apache.spark.sql.types.DoubleType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4());
      Metadata extraMetadata = (new MetadataBuilder()).putLong(LibSVMOptions$.MODULE$.NUM_FEATURES(), (long)numFeatures).build();
      AttributeGroup attrGroup = new AttributeGroup("features", numFeatures);
      StructField featuresField = attrGroup.toStructField(extraMetadata);
      return new Some(new StructType((StructField[])((Object[])(new StructField[]{labelField, featuresField}))));
   }

   public OutputWriterFactory prepareWrite(final SparkSession sparkSession, final Job job, final scala.collection.immutable.Map options, final StructType dataSchema) {
      this.verifySchema(dataSchema, true);
      return new OutputWriterFactory() {
         public OutputWriter newInstance(final String path, final StructType dataSchema, final TaskAttemptContext context) {
            return new LibSVMOutputWriter(path, dataSchema, context);
         }

         public String getFileExtension(final TaskAttemptContext context) {
            return ".libsvm" + org.apache.spark.sql.execution.datasources.CodecStreams..MODULE$.getCompressionExtension(context);
         }
      };
   }

   public Function1 buildReader(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final scala.collection.immutable.Map options, final Configuration hadoopConf) {
      this.verifySchema(dataSchema, false);
      int numFeatures = (int)dataSchema.apply("features").metadata().getLong(LibSVMOptions$.MODULE$.NUM_FEATURES());
      scala.Predef..MODULE$.assert(numFeatures > 0);
      LibSVMOptions libSVMOptions = new LibSVMOptions(options);
      boolean isSparse = libSVMOptions.isSparse();
      Broadcast broadcastedHadoopConf = sparkSession.sparkContext().broadcast(new SerializableConfiguration(hadoopConf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      return (file) -> {
         HadoopFileLinesReader linesReader = (HadoopFileLinesReader)org.apache.spark.util.Utils..MODULE$.createResourceUninterruptiblyIfInTaskThread(() -> new HadoopFileLinesReader(file, ((SerializableConfiguration)broadcastedHadoopConf.value()).value()));
         scala.Option..MODULE$.apply(org.apache.spark.TaskContext..MODULE$.get()).foreach((x$2) -> x$2.addTaskCompletionListener((x$3) -> {
               $anonfun$buildReader$4(linesReader, x$3);
               return BoxedUnit.UNIT;
            }));
         Iterator points = linesReader.map((x$4) -> x$4.toString().trim()).filterNot((line) -> BoxesRunTime.boxToBoolean($anonfun$buildReader$6(line))).map((line) -> {
            Tuple3 var4 = MLUtils$.MODULE$.parseLibSVMRecord(line);
            if (var4 != null) {
               double label = BoxesRunTime.unboxToDouble(var4._1());
               int[] indices = (int[])var4._2();
               double[] values = (double[])var4._3();
               Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble(label), indices, values);
               double label = BoxesRunTime.unboxToDouble(var3._1());
               int[] indices = (int[])var3._2();
               double[] values = (double[])var3._3();
               return new LabeledPoint(label, org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(numFeatures, indices, values));
            } else {
               throw new MatchError(var4);
            }
         });
         ExpressionEncoder.Serializer toRow = org.apache.spark.sql.catalyst.encoders.ExpressionEncoder..MODULE$.apply(dataSchema).createSerializer();
         Seq fullOutput = (Seq)dataSchema.map((f) -> {
            String x$1 = f.name();
            DataType x$2 = f.dataType();
            boolean x$3 = f.nullable();
            Metadata x$4 = f.metadata();
            ExprId x$5 = org.apache.spark.sql.catalyst.expressions.AttributeReference..MODULE$.apply$default$5(x$1, x$2, x$3, x$4);
            Seq x$6 = org.apache.spark.sql.catalyst.expressions.AttributeReference..MODULE$.apply$default$6(x$1, x$2, x$3, x$4);
            return new AttributeReference(x$1, x$2, x$3, x$4, x$5, x$6);
         });
         Seq requiredOutput = (Seq)fullOutput.filter((a) -> BoxesRunTime.boxToBoolean($anonfun$buildReader$9(requiredSchema, a)));
         UnsafeProjection requiredColumns = (UnsafeProjection)org.apache.spark.sql.catalyst.expressions.codegen.GenerateUnsafeProjection..MODULE$.generate(requiredOutput, fullOutput);
         return points.map((pt) -> {
            Vector features = (Vector)(isSparse ? pt.features().toSparse() : pt.features().toDense());
            return requiredColumns.apply(toRow.apply(org.apache.spark.sql.Row..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(pt.label()), features}))));
         });
      };
   }

   // $FF: synthetic method
   public static final void $anonfun$buildReader$4(final HadoopFileLinesReader linesReader$1, final TaskContext x$3) {
      linesReader$1.close();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildReader$6(final String line) {
      return line.isEmpty() || line.startsWith("#");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildReader$9(final StructType requiredSchema$1, final AttributeReference a) {
      return scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])requiredSchema$1.fieldNames()), a.name());
   }

   public LibSVMFileFormat() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
