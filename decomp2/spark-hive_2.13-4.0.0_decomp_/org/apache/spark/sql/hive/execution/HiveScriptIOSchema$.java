package org.apache.spark.sql.hive.execution;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.exec.RecordReader;
import org.apache.hadoop.hive.ql.exec.RecordWriter;
import org.apache.hadoop.hive.serde2.AbstractSerDe;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StandardStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.execution.ScriptTransformationIOSchema;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.types.DataType;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;

public final class HiveScriptIOSchema$ implements HiveInspectors {
   public static final HiveScriptIOSchema$ MODULE$ = new HiveScriptIOSchema$();

   static {
      HiveInspectors.$init$(MODULE$);
   }

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

   public Option initInputSerDe(final ScriptTransformationIOSchema ioschema, final Seq input) {
      return ioschema.inputSerdeClass().map((serdeClass) -> {
         Tuple2 var5 = MODULE$.parseAttrs(input);
         if (var5 != null) {
            Seq columns = (Seq)var5._1();
            Seq columnTypes = (Seq)var5._2();
            Tuple2 var4 = new Tuple2(columns, columnTypes);
            Seq columnsx = (Seq)var4._1();
            Seq columnTypesx = (Seq)var4._2();
            AbstractSerDe serde = MODULE$.initSerDe(serdeClass, columnsx, columnTypesx, ioschema.inputSerdeProps());
            Seq fieldObjectInspectors = (Seq)columnTypesx.map((dataType) -> MODULE$.toInspector(dataType));
            StandardStructObjectInspector objectInspector = ObjectInspectorFactory.getStandardStructObjectInspector(.MODULE$.SeqHasAsJava(columnsx).asJava(), .MODULE$.SeqHasAsJava(fieldObjectInspectors).asJava());
            return new Tuple2(serde, objectInspector);
         } else {
            throw new MatchError(var5);
         }
      });
   }

   public Option initOutputSerDe(final ScriptTransformationIOSchema ioschema, final Seq output) {
      return ioschema.outputSerdeClass().map((serdeClass) -> {
         Tuple2 var5 = MODULE$.parseAttrs(output);
         if (var5 != null) {
            Seq columns = (Seq)var5._1();
            Seq columnTypes = (Seq)var5._2();
            Tuple2 var4 = new Tuple2(columns, columnTypes);
            Seq columnsx = (Seq)var4._1();
            Seq columnTypesx = (Seq)var4._2();
            AbstractSerDe serde = MODULE$.initSerDe(serdeClass, columnsx, columnTypesx, ioschema.outputSerdeProps());
            StructObjectInspector structObjectInspector = (StructObjectInspector)serde.getObjectInspector();
            return new Tuple2(serde, structObjectInspector);
         } else {
            throw new MatchError(var5);
         }
      });
   }

   private Tuple2 parseAttrs(final Seq attrs) {
      Seq columns = (Seq)((IterableOps)attrs.zipWithIndex()).map((e) -> {
         String var10000 = ((Expression)e._1()).prettyName();
         return var10000 + "_" + e._2$mcI$sp();
      });
      Seq columnTypes = (Seq)attrs.map((x$9) -> x$9.dataType());
      return new Tuple2(columns, columnTypes);
   }

   public AbstractSerDe initSerDe(final String serdeClassName, final Seq columns, final Seq columnTypes, final Seq serdeProps) {
      AbstractSerDe serde = (AbstractSerDe)org.apache.spark.util.Utils..MODULE$.classForName(serdeClassName, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getConstructor().newInstance();
      String columnTypesNames = ((IterableOnceOps)columnTypes.map((x$10) -> MODULE$.typeInfoConversions(x$10).toTypeInfo().getTypeName())).mkString(",");
      Map propsMap = (Map)serdeProps.toMap(scala..less.colon.less..MODULE$.refl()).$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("columns"), columns.mkString(",")));
      propsMap = (Map)propsMap.$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("columns.types"), columnTypesNames));
      Properties properties = new Properties();
      properties.putAll(.MODULE$.MapHasAsJava(propsMap).asJava());
      serde.initialize((Configuration)null, properties);
      return serde;
   }

   public Option recordReader(final ScriptTransformationIOSchema ioschema, final InputStream inputStream, final Configuration conf) {
      return ioschema.recordReaderClass().map((klass) -> {
         RecordReader instance = (RecordReader)org.apache.spark.util.Utils..MODULE$.classForName(klass, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getConstructor().newInstance();
         Properties props = new Properties();
         props.putAll(.MODULE$.MapHasAsJava(ioschema.outputSerdeProps().toMap(scala..less.colon.less..MODULE$.refl())).asJava());
         instance.initialize(inputStream, conf, props);
         return instance;
      });
   }

   public Option recordWriter(final ScriptTransformationIOSchema ioschema, final OutputStream outputStream, final Configuration conf) {
      return ioschema.recordWriterClass().map((klass) -> {
         RecordWriter instance = (RecordWriter)org.apache.spark.util.Utils..MODULE$.classForName(klass, org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getConstructor().newInstance();
         instance.initialize(outputStream, conf);
         return instance;
      });
   }

   private HiveScriptIOSchema$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
