package org.apache.spark.sql.hive.execution;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.FileSinkOperator;
import org.apache.hadoop.hive.ql.io.HiveFileFormatUtils;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.Serializer;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorUtils.ObjectInspectorCopyOption;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reporter;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.execution.datasources.OutputWriter;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d\u0001\u0002\r\u001a\u0001\u0019B\u0001B\r\u0001\u0003\u0006\u0004%\ta\r\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005i!A!\t\u0001B\u0001B\u0003%1\t\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003P\u0011!)\u0006A!A!\u0002\u00131\u0006\"\u0002/\u0001\t\u0003i\u0006\"\u00023\u0001\t\u0013)\u0007bB5\u0001\u0005\u0004%IA\u001b\u0005\u0007c\u0002\u0001\u000b\u0011B6\t\u000fI\u0004!\u0019!C\u0005g\"1A\u0010\u0001Q\u0001\nQDq! \u0001C\u0002\u0013%a\u0010C\u0004\u0002\f\u0001\u0001\u000b\u0011B@\t\u0013\u00055\u0001A1A\u0005\n\u0005=\u0001\u0002CA\u0010\u0001\u0001\u0006I!!\u0005\t\u0013\u0005\u0005\u0002A1A\u0005\n\u0005\r\u0002\u0002CA\u0017\u0001\u0001\u0006I!!\n\t\u0013\u0005=\u0002A1A\u0005\n\u0005E\u0002\u0002CA!\u0001\u0001\u0006I!a\r\t\u0013\u0005\r\u0003A1A\u0005\n\u0005\u0015\u0003\u0002CA%\u0001\u0001\u0006I!a\u0012\t\u000f\u0005-\u0003\u0001\"\u0011\u0002N!9\u0011Q\r\u0001\u0005B\u0005\u001d$\u0001\u0005%jm\u0016|U\u000f\u001e9vi^\u0013\u0018\u000e^3s\u0015\tQ2$A\u0005fq\u0016\u001cW\u000f^5p]*\u0011A$H\u0001\u0005Q&4XM\u0003\u0002\u001f?\u0005\u00191/\u001d7\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c\u0001aE\u0002\u0001O9\u0002\"\u0001\u000b\u0017\u000e\u0003%R!AK\u0016\u0002\u0017\u0011\fG/Y:pkJ\u001cWm\u001d\u0006\u00035uI!!L\u0015\u0003\u0019=+H\u000f];u/JLG/\u001a:\u0011\u0005=\u0002T\"A\u000e\n\u0005EZ\"A\u0004%jm\u0016Len\u001d9fGR|'o]\u0001\u0005a\u0006$\b.F\u00015!\t)dH\u0004\u00027yA\u0011qGO\u0007\u0002q)\u0011\u0011(J\u0001\u0007yI|w\u000e\u001e \u000b\u0003m\nQa]2bY\u0006L!!\u0010\u001e\u0002\rA\u0013X\rZ3g\u0013\ty\u0004I\u0001\u0004TiJLgn\u001a\u0006\u0003{i\nQ\u0001]1uQ\u0002\nABZ5mKNKgn[\"p]\u001a\u0004\"\u0001\u0012'\u000e\u0003\u0015S!AR$\u0002\tAd\u0017M\u001c\u0006\u0003\u0011&\u000b!!\u001d7\u000b\u0005qQ%BA&\"\u0003\u0019A\u0017\rZ8pa&\u0011Q*\u0012\u0002\r\r&dWmU5oW\u0012+7oY\u0001\bU>\u00147i\u001c8g!\t\u00016+D\u0001R\u0015\t\u0011&*\u0001\u0004nCB\u0014X\rZ\u0005\u0003)F\u0013qAS8c\u0007>tg-\u0001\u0006eCR\f7k\u00195f[\u0006\u0004\"a\u0016.\u000e\u0003aS!!W\u000f\u0002\u000bQL\b/Z:\n\u0005mC&AC*ueV\u001cG\u000fV=qK\u00061A(\u001b8jiz\"RA\u00181bE\u000e\u0004\"a\u0018\u0001\u000e\u0003eAQA\r\u0004A\u0002QBQA\u0011\u0004A\u0002\rCQA\u0014\u0004A\u0002=CQ!\u0016\u0004A\u0002Y\u000b\u0011\u0002^1cY\u0016$Um]2\u0016\u0003\u0019\u0004\"\u0001R4\n\u0005!,%!\u0003+bE2,G)Z:d\u0003)\u0019XM]5bY&TXM]\u000b\u0002WB\u0011An\\\u0007\u0002[*\u0011a.S\u0001\u0007g\u0016\u0014H-\u001a\u001a\n\u0005Al'AC*fe&\fG.\u001b>fe\u0006Y1/\u001a:jC2L'0\u001a:!\u0003)A\u0017N^3Xe&$XM]\u000b\u0002iB\u0011QO_\u0007\u0002m*\u0011q\u000f_\u0001\u0011\r&dWmU5oW>\u0003XM]1u_JT!!_$\u0002\t\u0015DXmY\u0005\u0003wZ\u0014ABU3d_J$wK]5uKJ\f1\u0002[5wK^\u0013\u0018\u000e^3sA\u0005Q1\u000f^1oI\u0006\u0014HmT%\u0016\u0003}\u0004B!!\u0001\u0002\b5\u0011\u00111\u0001\u0006\u0004\u0003\u000bi\u0017aD8cU\u0016\u001cG/\u001b8ta\u0016\u001cGo\u001c:\n\t\u0005%\u00111\u0001\u0002\u0016'R\u0014Xo\u0019;PE*,7\r^%ogB,7\r^8s\u0003-\u0019H/\u00198eCJ$w*\u0013\u0011\u0002\u0011\u0019LW\r\u001c3P\u0013N,\"!!\u0005\u0011\r\u0005M\u0011QCA\r\u001b\u0005Q\u0014bAA\fu\t)\u0011I\u001d:bsB!\u0011\u0011AA\u000e\u0013\u0011\ti\"a\u0001\u0003\u001f=\u0013'.Z2u\u0013:\u001c\b/Z2u_J\f\u0011BZ5fY\u0012|\u0015j\u001d\u0011\u0002\u0013\u0011\fG/\u0019+za\u0016\u001cXCAA\u0013!\u0019\t\u0019\"!\u0006\u0002(A\u0019q+!\u000b\n\u0007\u0005-\u0002L\u0001\u0005ECR\fG+\u001f9f\u0003)!\u0017\r^1UsB,7\u000fI\u0001\toJ\f\u0007\u000f]3sgV\u0011\u00111\u0007\t\u0007\u0003'\t)\"!\u000e\u0011\u0011\u0005M\u0011qGA\u001e\u0003wI1!!\u000f;\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0014\u0005u\u0012bAA u\t\u0019\u0011I\\=\u0002\u0013]\u0014\u0018\r\u001d9feN\u0004\u0013AC8viB,H\u000fR1uCV\u0011\u0011q\t\t\u0007\u0003'\t)\"a\u000f\u0002\u0017=,H\u000f];u\t\u0006$\u0018\rI\u0001\u0006oJLG/\u001a\u000b\u0005\u0003\u001f\n)\u0006\u0005\u0003\u0002\u0014\u0005E\u0013bAA*u\t!QK\\5u\u0011\u001d\t9F\u0006a\u0001\u00033\n1A]8x!\u0011\tY&!\u0019\u000e\u0005\u0005u#bAA0;\u0005A1-\u0019;bYf\u001cH/\u0003\u0003\u0002d\u0005u#aC%oi\u0016\u0014h.\u00197S_^\fQa\u00197pg\u0016$\"!a\u0014"
)
public class HiveOutputWriter extends OutputWriter implements HiveInspectors {
   private final String path;
   private final FileSinkDesc fileSinkConf;
   private final Serializer serializer;
   private final FileSinkOperator.RecordWriter hiveWriter;
   private final StructObjectInspector standardOI;
   private final ObjectInspector[] fieldOIs;
   private final DataType[] dataTypes;
   private final Function1[] wrappers;
   private final Object[] outputData;

   public DataType javaTypeToDataType(final Type clz) {
      return HiveInspectors.javaTypeToDataType$(this, clz);
   }

   public Function1 wrapperFor(final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrapperFor$(this, oi, dataType);
   }

   public Function1 unwrapperFor(final ObjectInspector objectInspector) {
      return HiveInspectors.unwrapperFor$(this, (ObjectInspector)objectInspector);
   }

   public Function3 unwrapperFor(final StructField field) {
      return HiveInspectors.unwrapperFor$(this, (StructField)field);
   }

   public Object wrap(final Object a, final ObjectInspector oi, final DataType dataType) {
      return HiveInspectors.wrap$(this, (Object)a, (ObjectInspector)oi, (DataType)dataType);
   }

   public Object[] wrap(final InternalRow row, final Function1[] wrappers, final Object[] cache, final DataType[] dataTypes) {
      return HiveInspectors.wrap$(this, row, wrappers, cache, dataTypes);
   }

   public Object[] wrap(final Seq row, final Function1[] wrappers, final Object[] cache) {
      return HiveInspectors.wrap$(this, (Seq)row, (Function1[])wrappers, (Object[])cache);
   }

   public ObjectInspector toInspector(final DataType dataType) {
      return HiveInspectors.toInspector$(this, (DataType)dataType);
   }

   public ObjectInspector toInspector(final Expression expr) {
      return HiveInspectors.toInspector$(this, (Expression)expr);
   }

   public DataType inspectorToDataType(final ObjectInspector inspector) {
      return HiveInspectors.inspectorToDataType$(this, inspector);
   }

   public HiveInspectors.typeInfoConversions typeInfoConversions(final DataType dt) {
      return HiveInspectors.typeInfoConversions$(this, dt);
   }

   public String path() {
      return this.path;
   }

   private TableDesc tableDesc() {
      return this.fileSinkConf.getTableInfo();
   }

   private Serializer serializer() {
      return this.serializer;
   }

   private FileSinkOperator.RecordWriter hiveWriter() {
      return this.hiveWriter;
   }

   private StructObjectInspector standardOI() {
      return this.standardOI;
   }

   private ObjectInspector[] fieldOIs() {
      return this.fieldOIs;
   }

   private DataType[] dataTypes() {
      return this.dataTypes;
   }

   private Function1[] wrappers() {
      return this.wrappers;
   }

   private Object[] outputData() {
      return this.outputData;
   }

   public void write(final InternalRow row) {
      for(int i = 0; i < this.fieldOIs().length; ++i) {
         this.outputData()[i] = row.isNullAt(i) ? null : this.wrappers()[i].apply(row.get(i, this.dataTypes()[i]));
      }

      this.hiveWriter().write(this.serializer().serialize(this.outputData(), this.standardOI()));
   }

   public void close() {
      this.hiveWriter().close(false);
   }

   public HiveOutputWriter(final String path, final FileSinkDesc fileSinkConf, final JobConf jobConf, final StructType dataSchema) {
      this.path = path;
      this.fileSinkConf = fileSinkConf;
      HiveInspectors.$init$(this);
      Serializer serializer = (Serializer)this.tableDesc().getDeserializerClass().getConstructor().newInstance();
      serializer.initialize(jobConf, this.tableDesc().getProperties());
      this.serializer = serializer;
      this.hiveWriter = HiveFileFormatUtils.getHiveRecordWriter(jobConf, this.tableDesc(), this.serializer().getSerializedClass(), fileSinkConf, new Path(path), Reporter.NULL);
      this.standardOI = (StructObjectInspector)ObjectInspectorUtils.getStandardObjectInspector(this.tableDesc().getDeserializer(jobConf).getObjectInspector(), ObjectInspectorCopyOption.DEFAULT);
      this.fieldOIs = (ObjectInspector[])((IterableOnceOps).MODULE$.ListHasAsScala(this.standardOI().getAllStructFieldRefs()).asScala().map((x$1) -> x$1.getFieldObjectInspector())).toArray(scala.reflect.ClassTag..MODULE$.apply(ObjectInspector.class));
      this.dataTypes = (DataType[])((IterableOnceOps)dataSchema.map((x$2) -> x$2.dataType())).toArray(scala.reflect.ClassTag..MODULE$.apply(DataType.class));
      this.wrappers = (Function1[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.fieldOIs()), scala.Predef..MODULE$.wrapRefArray((Object[])this.dataTypes()))), (x0$1) -> {
         if (x0$1 != null) {
            ObjectInspector f = (ObjectInspector)x0$1._1();
            DataType dt = (DataType)x0$1._2();
            return this.wrapperFor(f, dt);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Function1.class));
      this.outputData = new Object[this.fieldOIs().length];
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
