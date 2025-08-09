package org.apache.spark.ml.source.image;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.image.ImageSchema$;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder;
import org.apache.spark.sql.catalyst.expressions.AttributeReference;
import org.apache.spark.sql.catalyst.expressions.UnsafeRow;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.execution.datasources.OutputWriterFactory;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.sources.DataSourceRegister;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.SerializableConfiguration;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.Closeables;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb!\u0002\u0004\b\u0001\u001d\u0019\u0002\"\u0002\u0016\u0001\t\u0003a\u0003\"B\u0018\u0001\t\u0003\u0002\u0004\"B2\u0001\t\u0003\"\u0007\"\u0002;\u0001\t\u0003*\b\"\u0002<\u0001\t#:(aD%nC\u001e,g)\u001b7f\r>\u0014X.\u0019;\u000b\u0005!I\u0011!B5nC\u001e,'B\u0001\u0006\f\u0003\u0019\u0019x.\u001e:dK*\u0011A\"D\u0001\u0003[2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\n\u0005\u0001QQB\u0005\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00037\tj\u0011\u0001\b\u0006\u0003;y\t1\u0002Z1uCN|WO]2fg*\u0011q\u0004I\u0001\nKb,7-\u001e;j_:T!!I\u0007\u0002\u0007M\fH.\u0003\u0002$9\tQa)\u001b7f\r>\u0014X.\u0019;\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001d\u0002\u0013aB:pkJ\u001cWm]\u0005\u0003S\u0019\u0012!\u0003R1uCN{WO]2f%\u0016<\u0017n\u001d;fe\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001.!\tq\u0003!D\u0001\b\u0003-IgNZ3s'\u000eDW-\\1\u0015\tER\u0004\t\u0015\t\u0004+I\"\u0014BA\u001a\u0017\u0005\u0019y\u0005\u000f^5p]B\u0011Q\u0007O\u0007\u0002m)\u0011q\u0007I\u0001\u0006if\u0004Xm]\u0005\u0003sY\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015Y$\u00011\u0001=\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\tid(D\u0001!\u0013\ty\u0004E\u0001\u0007Ta\u0006\u00148nU3tg&|g\u000eC\u0003B\u0005\u0001\u0007!)A\u0004paRLwN\\:\u0011\t\rSU*\u0014\b\u0003\t\"\u0003\"!\u0012\f\u000e\u0003\u0019S!aR\u0016\u0002\rq\u0012xn\u001c;?\u0013\tIe#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00172\u00131!T1q\u0015\tIe\u0003\u0005\u0002D\u001d&\u0011q\n\u0014\u0002\u0007'R\u0014\u0018N\\4\t\u000bE\u0013\u0001\u0019\u0001*\u0002\u000b\u0019LG.Z:\u0011\u0007MC6L\u0004\u0002U-:\u0011Q)V\u0005\u0002/%\u0011qKF\u0001\ba\u0006\u001c7.Y4f\u0013\tI&LA\u0002TKFT!a\u0016\f\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016A\u00014t\u0015\t\u0001w\"\u0001\u0004iC\u0012|w\u000e]\u0005\u0003Ev\u0013!BR5mKN#\u0018\r^;t\u00031\u0001(/\u001a9be\u0016<&/\u001b;f)\u0015)\u0007.[9s!\tYb-\u0003\u0002h9\t\u0019r*\u001e;qkR<&/\u001b;fe\u001a\u000b7\r^8ss\")1h\u0001a\u0001y!)!n\u0001a\u0001W\u0006\u0019!n\u001c2\u0011\u00051|W\"A7\u000b\u00059|\u0016!C7baJ,G-^2f\u0013\t\u0001XNA\u0002K_\nDQ!Q\u0002A\u0002\tCQa]\u0002A\u0002Q\n!\u0002Z1uCN\u001b\u0007.Z7b\u0003%\u0019\bn\u001c:u\u001d\u0006lW\rF\u0001N\u0003-\u0011W/\u001b7e%\u0016\fG-\u001a:\u0015\u001fa\fy!!\u0005\u0002\u0014\u0005]\u00111DA\u0014\u0003S\u0001B!F=|}&\u0011!P\u0006\u0002\n\rVt7\r^5p]F\u0002\"a\u0007?\n\u0005ud\"a\u0004)beRLG/[8oK\u00124\u0015\u000e\\3\u0011\tM{\u00181A\u0005\u0004\u0003\u0003Q&\u0001C%uKJ\fGo\u001c:\u0011\t\u0005\u0015\u00111B\u0007\u0003\u0003\u000fQ1!!\u0003!\u0003!\u0019\u0017\r^1msN$\u0018\u0002BA\u0007\u0003\u000f\u00111\"\u00138uKJt\u0017\r\u001c*po\")1(\u0002a\u0001y!)1/\u0002a\u0001i!1\u0011QC\u0003A\u0002Q\nq\u0002]1si&$\u0018n\u001c8TG\",W.\u0019\u0005\u0007\u00033)\u0001\u0019\u0001\u001b\u0002\u001dI,\u0017/^5sK\u0012\u001c6\r[3nC\"9\u0011QD\u0003A\u0002\u0005}\u0011a\u00024jYR,'o\u001d\t\u0005'b\u000b\t\u0003E\u0002&\u0003GI1!!\n'\u0005\u00191\u0015\u000e\u001c;fe\")\u0011)\u0002a\u0001\u0005\"9\u00111F\u0003A\u0002\u00055\u0012A\u00035bI>|\u0007oQ8oMB!\u0011qFA\u001b\u001b\t\t\tDC\u0002\u00024}\u000bAaY8oM&!\u0011qGA\u0019\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0002"
)
public class ImageFileFormat implements FileFormat, DataSourceRegister {
   public boolean supportBatch(final SparkSession sparkSession, final StructType dataSchema) {
      return FileFormat.supportBatch$(this, sparkSession, dataSchema);
   }

   public Option vectorTypes(final StructType requiredSchema, final StructType partitionSchema, final SQLConf sqlConf) {
      return FileFormat.vectorTypes$(this, requiredSchema, partitionSchema, sqlConf);
   }

   public boolean isSplitable(final SparkSession sparkSession, final Map options, final Path path) {
      return FileFormat.isSplitable$(this, sparkSession, options, path);
   }

   public Function1 buildReaderWithPartitionValues(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final Map options, final Configuration hadoopConf) {
      return FileFormat.buildReaderWithPartitionValues$(this, sparkSession, dataSchema, partitionSchema, requiredSchema, filters, options, hadoopConf);
   }

   public AttributeReference createFileMetadataCol() {
      return FileFormat.createFileMetadataCol$(this);
   }

   public boolean supportDataType(final DataType dataType) {
      return FileFormat.supportDataType$(this, dataType);
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

   public Option inferSchema(final SparkSession sparkSession, final Map options, final Seq files) {
      return new Some(ImageSchema$.MODULE$.imageSchema());
   }

   public OutputWriterFactory prepareWrite(final SparkSession sparkSession, final Job job, final Map options, final StructType dataSchema) {
      throw new UnsupportedOperationException("Write is not supported for image data source");
   }

   public String shortName() {
      return "image";
   }

   public Function1 buildReader(final SparkSession sparkSession, final StructType dataSchema, final StructType partitionSchema, final StructType requiredSchema, final Seq filters, final Map options, final Configuration hadoopConf) {
      .MODULE$.assert(requiredSchema.length() <= 1, () -> "Image data source only produces a single data column named \"image\".");
      Broadcast broadcastedHadoopConf = sparkSession.sparkContext().broadcast(new SerializableConfiguration(hadoopConf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class));
      ImageOptions imageSourceOptions = new ImageOptions(options);
      return (file) -> {
         UnsafeRow emptyUnsafeRow = new UnsafeRow(0);
         if (!imageSourceOptions.dropInvalid() && requiredSchema.isEmpty()) {
            return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new UnsafeRow[]{emptyUnsafeRow})));
         } else {
            String origin = file.urlEncodedPath();
            Path path = file.toPath();
            FileSystem fs = path.getFileSystem(((SerializableConfiguration)broadcastedHadoopConf.value()).value());
            FSDataInputStream stream = fs.open(path);

            byte[] var10000;
            try {
               var10000 = ByteStreams.toByteArray(stream);
            } finally {
               Closeables.close(stream, true);
            }

            byte[] bytes = var10000;
            Option resultOpt = ImageSchema$.MODULE$.decode(origin, bytes);
            Iterator filteredResult = imageSourceOptions.dropInvalid() ? resultOpt.iterator() : scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Row[]{(Row)resultOpt.getOrElse(() -> ImageSchema$.MODULE$.invalidImageRow(origin))})));
            if (requiredSchema.isEmpty()) {
               return filteredResult.map((x$1) -> emptyUnsafeRow);
            } else {
               ExpressionEncoder.Serializer toRow = org.apache.spark.sql.catalyst.encoders.ExpressionEncoder..MODULE$.apply(requiredSchema).createSerializer();
               return filteredResult.map((row) -> toRow.apply(row));
            }
         }
      };
   }

   public ImageFileFormat() {
      FileFormat.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
