package org.apache.spark.sql.hive.orc;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat;
import org.apache.hadoop.hive.ql.io.orc.Reader;
import org.apache.hadoop.hive.ql.io.orc.SparkOrcNewRecordReader;
import org.apache.hadoop.hive.ql.io.sarg.SearchArgument;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.orc.OrcConf;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.OutputWriter;
import org.apache.spark.sql.execution.datasources.OutputWriterFactory;
import org.apache.spark.sql.execution.datasources.RecordReaderIterator;
import org.apache.spark.sql.execution.datasources.SchemaMergeUtils.;
import org.apache.spark.sql.execution.datasources.orc.OrcOptions;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.sources.DataSourceRegister;
import org.apache.spark.sql.types.AnsiIntervalType;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.AtomicType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.VariantType;
import org.apache.spark.util.SerializableConfiguration;
import scala.Function1;
import scala.Function3;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tma\u0001\u0002\n\u0014\u0001\u0001BQ!\u0011\u0001\u0005\u0002\tCQ!\u0012\u0001\u0005B\u0019CQa\u0014\u0001\u0005B\u0019CQ\u0001\u0015\u0001\u0005BECQa\u001d\u0001\u0005BQDq!!\u0003\u0001\t\u0003\nY\u0001C\u0004\u0002\"\u0001!\t%a\t\t\u000f\u00055\u0004\u0001\"\u0011\u0002p!9\u00111\u0010\u0001\u0005\n\u0005ut\u0001CAL'!\u00051#!'\u0007\u000fI\u0019\u0002\u0012A\n\u0002\u001c\"1\u0011i\u0003C\u0001\u0003{C!\"a0\f\u0005\u0004%\taEAa\u0011!\tim\u0003Q\u0001\n\u0005\r\u0007bBAh\u0017\u0011\u0005\u0011\u0011\u001b\u0005\b\u0003\u007f\\A\u0011\u0001B\u0001\u0011%\u0011\tbCA\u0001\n\u0013\u0011\u0019BA\u0007Pe\u000e4\u0015\u000e\\3G_Jl\u0017\r\u001e\u0006\u0003)U\t1a\u001c:d\u0015\t1r#\u0001\u0003iSZ,'B\u0001\r\u001a\u0003\r\u0019\u0018\u000f\u001c\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sO\u000e\u00011#\u0002\u0001\"O=*\u0004C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g\r\u0005\u0002)[5\t\u0011F\u0003\u0002+W\u0005YA-\u0019;bg>,(oY3t\u0015\tas#A\u0005fq\u0016\u001cW\u000f^5p]&\u0011a&\u000b\u0002\u000b\r&dWMR8s[\u0006$\bC\u0001\u00194\u001b\u0005\t$B\u0001\u001a\u0018\u0003\u001d\u0019x.\u001e:dKNL!\u0001N\u0019\u0003%\u0011\u000bG/Y*pkJ\u001cWMU3hSN$XM\u001d\t\u0003myr!a\u000e\u001f\u000f\u0005aZT\"A\u001d\u000b\u0005iz\u0012A\u0002\u001fs_>$h(C\u0001%\u0013\ti4%A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0002%\u0001D*fe&\fG.\u001b>bE2,'BA\u001f$\u0003\u0019a\u0014N\\5u}Q\t1\t\u0005\u0002E\u00015\t1#A\u0005tQ>\u0014HOT1nKR\tq\t\u0005\u0002I\u0019:\u0011\u0011J\u0013\t\u0003q\rJ!aS\u0012\u0002\rA\u0013X\rZ3g\u0013\tieJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0017\u000e\n\u0001\u0002^8TiJLgnZ\u0001\fS:4WM]*dQ\u0016l\u0017\r\u0006\u0003S7\u00064\u0007c\u0001\u0012T+&\u0011Ak\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005YKV\"A,\u000b\u0005a;\u0012!\u0002;za\u0016\u001c\u0018B\u0001.X\u0005)\u0019FO];diRK\b/\u001a\u0005\u00069\u0012\u0001\r!X\u0001\rgB\f'o[*fgNLwN\u001c\t\u0003=~k\u0011aF\u0005\u0003A^\u0011Ab\u00159be.\u001cVm]:j_:DQA\u0019\u0003A\u0002\r\fqa\u001c9uS>t7\u000f\u0005\u0003II\u001e;\u0015BA3O\u0005\ri\u0015\r\u001d\u0005\u0006O\u0012\u0001\r\u0001[\u0001\u0006M&dWm\u001d\t\u0004m%\\\u0017B\u00016A\u0005\r\u0019V-\u001d\t\u0003YFl\u0011!\u001c\u0006\u0003]>\f!AZ:\u000b\u0005A\\\u0012A\u00025bI>|\u0007/\u0003\u0002s[\nQa)\u001b7f'R\fG/^:\u0002\u0019A\u0014X\r]1sK^\u0013\u0018\u000e^3\u0015\u000fUD\u00180a\u0001\u0002\u0006A\u0011\u0001F^\u0005\u0003o&\u00121cT;uaV$xK]5uKJ4\u0015m\u0019;pefDQ\u0001X\u0003A\u0002uCQA_\u0003A\u0002m\f1A[8c!\tax0D\u0001~\u0015\tqx.A\u0005nCB\u0014X\rZ;dK&\u0019\u0011\u0011A?\u0003\u0007){'\rC\u0003c\u000b\u0001\u00071\r\u0003\u0004\u0002\b\u0015\u0001\r!V\u0001\u000bI\u0006$\u0018mU2iK6\f\u0017aC5t'Bd\u0017\u000e^1cY\u0016$\u0002\"!\u0004\u0002\u0014\u0005U\u0011q\u0003\t\u0004E\u0005=\u0011bAA\tG\t9!i\\8mK\u0006t\u0007\"\u0002/\u0007\u0001\u0004i\u0006\"\u00022\u0007\u0001\u0004\u0019\u0007bBA\r\r\u0001\u0007\u00111D\u0001\u0005a\u0006$\b\u000eE\u0002m\u0003;I1!a\bn\u0005\u0011\u0001\u0016\r\u001e5\u0002\u0017\t,\u0018\u000e\u001c3SK\u0006$WM\u001d\u000b\u0011\u0003K\t\u0019%!\u0012\u0002H\u0005-\u0013qJA.\u0003;\u0002rAIA\u0014\u0003W\t\t$C\u0002\u0002*\r\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0007!\ni#C\u0002\u00020%\u0012q\u0002U1si&$\u0018n\u001c8fI\u001aKG.\u001a\t\u0006m\u0005M\u0012qG\u0005\u0004\u0003k\u0001%\u0001C%uKJ\fGo\u001c:\u0011\t\u0005e\u0012qH\u0007\u0003\u0003wQ1!!\u0010\u0018\u0003!\u0019\u0017\r^1msN$\u0018\u0002BA!\u0003w\u00111\"\u00138uKJt\u0017\r\u001c*po\")Al\u0002a\u0001;\"1\u0011qA\u0004A\u0002UCa!!\u0013\b\u0001\u0004)\u0016a\u00049beRLG/[8o'\u000eDW-\\1\t\r\u00055s\u00011\u0001V\u00039\u0011X-];je\u0016$7k\u00195f[\u0006Dq!!\u0015\b\u0001\u0004\t\u0019&A\u0004gS2$XM]:\u0011\tYJ\u0017Q\u000b\t\u0004a\u0005]\u0013bAA-c\t1a)\u001b7uKJDQAY\u0004A\u0002\rDq!a\u0018\b\u0001\u0004\t\t'\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\u0004B!a\u0019\u0002j5\u0011\u0011Q\r\u0006\u0004\u0003Oz\u0017\u0001B2p]\u001aLA!a\u001b\u0002f\ti1i\u001c8gS\u001e,(/\u0019;j_:\fqb];qa>\u0014H\u000fR1uCRK\b/\u001a\u000b\u0005\u0003\u001b\t\t\bC\u0004\u0002t!\u0001\r!!\u001e\u0002\u0011\u0011\fG/\u0019+za\u0016\u00042AVA<\u0013\r\tIh\u0016\u0002\t\t\u0006$\u0018\rV=qK\u00061Ao\\&ss>$2aRA@\u0011\u001d\t\t)\u0003a\u0001\u0003\u0007\u000bAa]1sOB!\u0011QQAJ\u001b\t\t9I\u0003\u0003\u0002\u0002\u0006%%\u0002BAF\u0003\u001b\u000b!![8\u000b\t\u0005=\u0015\u0011S\u0001\u0003c2T!AF8\n\t\u0005U\u0015q\u0011\u0002\u000f'\u0016\f'o\u00195Be\u001e,X.\u001a8u\u00035y%o\u0019$jY\u00164uN]7biB\u0011AiC\n\t\u0017\u0005\ni*!*\u00022B!\u0011qTAQ\u001b\u0005)\u0012bAAR+\tq\u0001*\u001b<f\u0013:\u001c\b/Z2u_J\u001c\b\u0003BAT\u0003[k!!!+\u000b\u0007\u0005-\u0016$\u0001\u0005j]R,'O\\1m\u0013\u0011\ty+!+\u0003\u000f1{wmZ5oOB!\u00111WA^\u001b\t\t)L\u0003\u0003\u0002\f\u0006]&BAA]\u0003\u0011Q\u0017M^1\n\u0007}\n)\f\u0006\u0002\u0002\u001a\u0006i1+\u0011*H?B+6\u000b\u0013#P/:+\"!a1\u0011\t\u0005\u0015\u00171Z\u0007\u0003\u0003\u000fTA!!3\u00028\u0006!A.\u00198h\u0013\ri\u0015qY\u0001\u000f'\u0006\u0013vi\u0018)V'\"#uj\u0016(!\u0003A)hn\u001e:ba>\u00138m\u0015;sk\u000e$8\u000f\u0006\u0007\u00022\u0005M\u0017Q[Al\u00033\fy\u000fC\u0004\u0002h=\u0001\r!!\u0019\t\r\u0005\u001dq\u00021\u0001V\u0011\u0019\tie\u0004a\u0001+\"9\u00111\\\bA\u0002\u0005u\u0017!D7bs\n,7\u000b\u001e:vGR|\u0015\n\u0005\u0003#'\u0006}\u0007\u0003BAq\u0003Wl!!a9\u000b\t\u0005\u0015\u0018q]\u0001\u0010_\nTWm\u0019;j]N\u0004Xm\u0019;pe*!\u0011\u0011^AI\u0003\u0019\u0019XM\u001d3fe%!\u0011Q^Ar\u0005U\u0019FO];di>\u0013'.Z2u\u0013:\u001c\b/Z2u_JDq!!=\u0010\u0001\u0004\t\u00190\u0001\u0005ji\u0016\u0014\u0018\r^8s!\u00151\u00141GA{!\u0011\t90a?\u000e\u0005\u0005e(bAAF_&!\u0011Q`A}\u0005!9&/\u001b;bE2,\u0017AE:fiJ+\u0017/^5sK\u0012\u001cu\u000e\\;n]N$\u0002Ba\u0001\u0003\n\t-!Q\u0002\t\u0004E\t\u0015\u0011b\u0001B\u0004G\t!QK\\5u\u0011\u001d\t9\u0007\u0005a\u0001\u0003CBa!a\u0002\u0011\u0001\u0004)\u0006B\u0002B\b!\u0001\u0007Q+A\bsKF,Xm\u001d;fIN\u001b\u0007.Z7b\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011)\u0002\u0005\u0003\u0002F\n]\u0011\u0002\u0002B\r\u0003\u000f\u0014aa\u00142kK\u000e$\b"
)
public class OrcFileFormat implements FileFormat, DataSourceRegister, Serializable {
   public static void setRequiredColumns(final Configuration conf, final StructType dataSchema, final StructType requestedSchema) {
      OrcFileFormat$.MODULE$.setRequiredColumns(conf, dataSchema, requestedSchema);
   }

   public static Iterator unwrapOrcStructs(final Configuration conf, final StructType dataSchema, final StructType requiredSchema, final Option maybeStructOI, final Iterator iterator) {
      return OrcFileFormat$.MODULE$.unwrapOrcStructs(conf, dataSchema, requiredSchema, maybeStructOI, iterator);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return OrcFileFormat$.MODULE$.LogStringContext(sc);
   }

   public static HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return OrcFileFormat$.MODULE$.typeInfoConversions(dt);
   }

   public static DataType inspectorToDataType(final ObjectInspector inspector) {
      return OrcFileFormat$.MODULE$.inspectorToDataType(inspector);
   }

   public static ObjectInspector toInspector(final Expression expr) {
      return OrcFileFormat$.MODULE$.toInspector(expr);
   }

   public static ObjectInspector toInspector(final DataType dataType) {
      return OrcFileFormat$.MODULE$.toInspector(dataType);
   }

   public static Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return OrcFileFormat$.MODULE$.wrap(row, wrappers, cache);
   }

   public static Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return OrcFileFormat$.MODULE$.wrap(row, wrappers, cache, dataTypes);
   }

   public static Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return OrcFileFormat$.MODULE$.wrap(a, oi, dataType);
   }

   public static Function3 unwrapperFor(final StructField field) {
      return OrcFileFormat$.MODULE$.unwrapperFor(field);
   }

   public static Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return OrcFileFormat$.MODULE$.unwrapperFor(objectInspector);
   }

   public static DataType javaTypeToDataType(final Type clz) {
      return OrcFileFormat$.MODULE$.javaTypeToDataType(clz);
   }

   public boolean supportBatch(final SparkSession sparkSession, final StructType dataSchema) {
      return FileFormat.supportBatch$(this, sparkSession, dataSchema);
   }

   public Option vectorTypes(final StructType requiredSchema, final StructType partitionSchema, final SQLConf sqlConf) {
      return FileFormat.vectorTypes$(this, requiredSchema, partitionSchema, sqlConf);
   }

   public Function1 buildReaderWithPartitionValues(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final Map options, final Configuration hadoopConf) {
      return FileFormat.buildReaderWithPartitionValues$(this, sparkSession, dataSchema, partitionSchema, requiredSchema, filters, options, hadoopConf);
   }

   public AttributeReference createFileMetadataCol() {
      return FileFormat.createFileMetadataCol$(this);
   }

   public boolean supportFieldName(final String name) {
      return FileFormat.supportFieldName$(this, name);
   }

   public boolean allowDuplicatedColumnNames() {
      return FileFormat.allowDuplicatedColumnNames$(this);
   }

   public Seq metadataSchemaFields() {
      return FileFormat.metadataSchemaFields$(this);
   }

   public Map fileConstantMetadataExtractors() {
      return FileFormat.fileConstantMetadataExtractors$(this);
   }

   public String shortName() {
      return "orc";
   }

   public String toString() {
      return "ORC";
   }

   public Option inferSchema(final SparkSession sparkSession, final Map options, final Seq files) {
      OrcOptions orcOptions = new OrcOptions(options, sparkSession.sessionState().conf());
      return orcOptions.mergeSchema() ? .MODULE$.mergeSchemasInParallel(sparkSession, options, files, (partFiles, conf, ignoreCorruptFiles) -> $anonfun$inferSchema$1(partFiles, conf, BoxesRunTime.unboxToBoolean(ignoreCorruptFiles))) : OrcFileOperator$.MODULE$.readSchema((Seq)files.map((x$1) -> x$1.getPath().toString()), new Some(sparkSession.sessionState().newHadoopConfWithOptions(options)), orcOptions.ignoreCorruptFiles());
   }

   public OutputWriterFactory prepareWrite(final SparkSession sparkSession, final Job job, final Map options, final StructType dataSchema) {
      OrcOptions orcOptions = new OrcOptions(options, sparkSession.sessionState().conf());
      Configuration configuration = job.getConfiguration();
      configuration.set(OrcConf.COMPRESS.getAttribute(), orcOptions.compressionCodec());
      if (configuration instanceof JobConf var9) {
         var9.setOutputFormat(OrcOutputFormat.class);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         configuration.setClass("mapred.output.format.class", OrcOutputFormat.class, OutputFormat.class);
         BoxedUnit var10 = BoxedUnit.UNIT;
      }

      return new OutputWriterFactory() {
         public OutputWriter newInstance(final String path, final StructType dataSchema, final TaskAttemptContext context) {
            return new OrcOutputWriter(path, dataSchema, context);
         }

         public String getFileExtension(final TaskAttemptContext context) {
            String name = context.getConfiguration().get(OrcConf.COMPRESS.getAttribute());
            String compressionExtension = (String)org.apache.spark.sql.execution.datasources.orc.OrcUtils..MODULE$.extensionsForCompressionCodecNames().getOrElse(name, () -> "");
            return compressionExtension + ".orc";
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public boolean isSplitable(final SparkSession sparkSession, final Map options, final Path path) {
      return true;
   }

   public Function1 buildReader(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final Map options, final Configuration hadoopConf) {
      if (sparkSession.sessionState().conf().orcFilterPushDown()) {
         org.apache.spark.sql.execution.datasources.orc.OrcFilters..MODULE$.createFilter(requiredSchema, filters).foreach((f) -> {
            $anonfun$buildReader$1(this, hadoopConf, f);
            return BoxedUnit.UNIT;
         });
      }

      Broadcast broadcastedHadoopConf = sparkSession.sparkContext().broadcast(new SerializableConfiguration(hadoopConf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      boolean ignoreCorruptFiles = (new OrcOptions(options, sparkSession.sessionState().conf())).ignoreCorruptFiles();
      return (file) -> {
         Configuration conf = ((SerializableConfiguration)broadcastedHadoopConf.value()).value();
         Path filePath = file.toPath();
         boolean isEmptyFile = OrcFileOperator$.MODULE$.readSchema(new scala.collection.immutable..colon.colon(filePath.toString(), scala.collection.immutable.Nil..MODULE$), new Some(conf), ignoreCorruptFiles).isEmpty();
         if (isEmptyFile) {
            return scala.package..MODULE$.Iterator().empty();
         } else {
            OrcFileFormat$.MODULE$.setRequiredColumns(conf, dataSchema, requiredSchema);
            Job job = Job.getInstance(conf);
            FileInputFormat.setInputPaths(job, file.urlEncodedPath());
            Reader orcReader = OrcFile.createReader(filePath, OrcFile.readerOptions(conf));
            SparkOrcNewRecordReader orcRecordReader = new SparkOrcNewRecordReader(orcReader, conf, file.start(), file.length());
            RecordReaderIterator recordsIterator = new RecordReaderIterator(orcRecordReader);
            scala.Option..MODULE$.apply(org.apache.spark.TaskContext..MODULE$.get()).foreach((x$2) -> x$2.addTaskCompletionListener((x$3) -> {
                  $anonfun$buildReader$4(recordsIterator, x$3);
                  return BoxedUnit.UNIT;
               }));
            return OrcFileFormat$.MODULE$.unwrapOrcStructs(conf, dataSchema, requiredSchema, new Some((StructObjectInspector)orcRecordReader.getObjectInspector()), recordsIterator);
         }
      };
   }

   public boolean supportDataType(final DataType dataType) {
      if (dataType instanceof VariantType) {
         return false;
      } else if (dataType instanceof AnsiIntervalType) {
         return false;
      } else if (dataType instanceof AtomicType) {
         return true;
      } else if (dataType instanceof StructType) {
         StructType var4 = (StructType)dataType;
         return var4.forall((f) -> BoxesRunTime.boxToBoolean($anonfun$supportDataType$1(this, f)));
      } else if (dataType instanceof ArrayType) {
         ArrayType var5 = (ArrayType)dataType;
         DataType elementType = var5.elementType();
         return this.supportDataType(elementType);
      } else if (!(dataType instanceof MapType)) {
         if (dataType instanceof UserDefinedType) {
            UserDefinedType var10 = (UserDefinedType)dataType;
            return this.supportDataType(var10.sqlType());
         } else {
            return false;
         }
      } else {
         MapType var7 = (MapType)dataType;
         DataType keyType = var7.keyType();
         DataType valueType = var7.valueType();
         return this.supportDataType(keyType) && this.supportDataType(valueType);
      }
   }

   private String toKryo(final SearchArgument sarg) {
      Kryo kryo = new Kryo();
      Output out = new Output(4096, 10485760);
      kryo.writeObject(out, sarg);
      out.close();
      return Base64.encodeBase64String(out.toBytes());
   }

   // $FF: synthetic method
   public static final Seq $anonfun$inferSchema$1(final Seq partFiles, final Configuration conf, final boolean ignoreCorruptFiles) {
      return OrcFileOperator$.MODULE$.readOrcSchemasInParallel(partFiles, conf, ignoreCorruptFiles);
   }

   // $FF: synthetic method
   public static final void $anonfun$buildReader$1(final OrcFileFormat $this, final Configuration hadoopConf$1, final SearchArgument f) {
      hadoopConf$1.set(OrcFileFormat$.MODULE$.SARG_PUSHDOWN(), $this.toKryo(f));
      hadoopConf$1.setBoolean("hive.optimize.index.filter", true);
   }

   // $FF: synthetic method
   public static final void $anonfun$buildReader$4(final RecordReaderIterator recordsIterator$1, final TaskContext x$3) {
      recordsIterator$1.close();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$supportDataType$1(final OrcFileFormat $this, final org.apache.spark.sql.types.StructField f) {
      return $this.supportDataType(f.dataType());
   }

   public OrcFileFormat() {
      FileFormat.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
