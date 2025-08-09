package org.apache.spark.sql.hive.orc;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.ql.io.orc.OrcSerde;
import org.apache.hadoop.hive.ql.io.orc.OrcStruct;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.SettableStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.Writable;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.hive.HiveInspectors;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Function3;
import scala.MatchError;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m4Qa\u0003\u0007\u0001\u0019aA\u0001b\t\u0001\u0003\u0002\u0003\u0006I!\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!)1\u0007\u0001C\u0001i!)\u0011\b\u0001C\u0001u!1\u0011\n\u0001Q\u0001\n)Cqa\u0015\u0001C\u0002\u0013\u0005A\u000b\u0003\u0004^\u0001\u0001\u0006I!\u0016\u0005\u0007=\u0002\u0001\u000b\u0011B0\t\r\t\u0004\u0001\u0015!\u0003d\u0011\u0019\t\b\u0001)C\u0005e\niqJ]2TKJL\u0017\r\\5{KJT!!\u0004\b\u0002\u0007=\u00148M\u0003\u0002\u0010!\u0005!\u0001.\u001b<f\u0015\t\t\"#A\u0002tc2T!a\u0005\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U1\u0012AB1qC\u000eDWMC\u0001\u0018\u0003\ry'oZ\n\u0004\u0001ey\u0002C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"AB!osJ+g\r\u0005\u0002!C5\ta\"\u0003\u0002#\u001d\tq\u0001*\u001b<f\u0013:\u001c\b/Z2u_J\u001c\u0018A\u00033bi\u0006\u001c6\r[3nC\u000e\u0001\u0001C\u0001\u0014*\u001b\u00059#B\u0001\u0015\u0011\u0003\u0015!\u0018\u0010]3t\u0013\tQsE\u0001\u0006TiJ,8\r\u001e+za\u0016\fAaY8oMB\u0011Q&M\u0007\u0002])\u00111f\f\u0006\u0003aQ\ta\u0001[1e_>\u0004\u0018B\u0001\u001a/\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"2!N\u001c9!\t1\u0004!D\u0001\r\u0011\u0015\u00193\u00011\u0001&\u0011\u0015Y3\u00011\u0001-\u0003%\u0019XM]5bY&TX\r\u0006\u0002<\u0003B\u0011AhP\u0007\u0002{)\u0011ahL\u0001\u0003S>L!\u0001Q\u001f\u0003\u0011]\u0013\u0018\u000e^1cY\u0016DQA\u0011\u0003A\u0002\r\u000b1A]8x!\t!u)D\u0001F\u0015\t1\u0005#\u0001\u0005dCR\fG._:u\u0013\tAUIA\u0006J]R,'O\\1m%><\u0018AC:fe&\fG.\u001b>feB\u00111*U\u0007\u0002\u0019*\u0011Q\"\u0014\u0006\u0003}9S!a\u0014)\u0002\u0005Ed'BA\b0\u0013\t\u0011FJ\u0001\u0005Pe\u000e\u001cVM\u001d3f\u0003!\u0019HO];di>KU#A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016aD8cU\u0016\u001cG/\u001b8ta\u0016\u001cGo\u001c:\u000b\u0005i\u0003\u0016AB:fe\u0012,''\u0003\u0002]/\ni2+\u001a;uC\ndWm\u0015;sk\u000e$xJ\u00196fGRLen\u001d9fGR|'/A\u0005tiJ,8\r^(JA\u0005y1-Y2iK\u0012|%oY*ueV\u001cG\u000f\u0005\u0002LA&\u0011\u0011\r\u0014\u0002\n\u001fJ\u001c7\u000b\u001e:vGR\f\u0001b\u001e:baB,'o\u001d\t\u0004I&\\W\"A3\u000b\u0005\u0019<\u0017!C5n[V$\u0018M\u00197f\u0015\tA7$\u0001\u0006d_2dWm\u0019;j_:L!A[3\u0003\u0007M+\u0017\u000f\u0005\u0003\u001bY:t\u0017BA7\u001c\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u001b_&\u0011\u0001o\u0007\u0002\u0004\u0003:L\u0018!D<sCB|%oY*ueV\u001cG\u000f\u0006\u0003tmbT\bC\u0001\u000eu\u0013\t)8D\u0001\u0003V]&$\b\"B<\u000b\u0001\u0004y\u0016AB:ueV\u001cG\u000fC\u0003z\u0015\u0001\u0007Q+\u0001\u0002pS\")!I\u0003a\u0001\u0007\u0002"
)
public class OrcSerializer implements HiveInspectors {
   private final StructType dataSchema;
   private final OrcSerde serializer;
   private final SettableStructObjectInspector structOI;
   private final OrcStruct cachedOrcStruct;
   private final Seq wrappers;

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

   public Writable serialize(final InternalRow row) {
      this.wrapOrcStruct(this.cachedOrcStruct, this.structOI(), row);
      return this.serializer.serialize(this.cachedOrcStruct, this.structOI());
   }

   public SettableStructObjectInspector structOI() {
      return this.structOI;
   }

   private void wrapOrcStruct(final OrcStruct struct, final SettableStructObjectInspector oi, final InternalRow row) {
      List fieldRefs = oi.getAllStructFieldRefs();
      int i = 0;

      for(int size = fieldRefs.size(); i < size; ++i) {
         oi.setStructFieldData(struct, (StructField)fieldRefs.get(i), ((Function1)this.wrappers.apply(i)).apply(row.get(i, this.dataSchema.apply(i).dataType())));
      }

   }

   public OrcSerializer(final StructType dataSchema, final Configuration conf) {
      this.dataSchema = dataSchema;
      HiveInspectors.$init$(this);
      Properties table = new Properties();
      table.setProperty("columns", .MODULE$.wrapRefArray((Object[])dataSchema.fieldNames()).mkString(","));
      table.setProperty("columns.types", ((IterableOnceOps)dataSchema.map((x$4) -> x$4.dataType().catalogString())).mkString(":"));
      OrcSerde serde = new OrcSerde();
      serde.initialize(conf, table);
      this.serializer = serde;
      TypeInfo typeInfo = TypeInfoUtils.getTypeInfoFromTypeString(dataSchema.catalogString());
      this.structOI = (SettableStructObjectInspector)OrcStruct.createObjectInspector((StructTypeInfo)typeInfo);
      this.cachedOrcStruct = (OrcStruct)this.structOI().create();
      this.wrappers = (Seq)((IterableOps)dataSchema.zip(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.structOI().getAllStructFieldRefs()).asScala().toSeq())).map((x0$1) -> {
         if (x0$1 != null) {
            org.apache.spark.sql.types.StructField f = (org.apache.spark.sql.types.StructField)x0$1._1();
            StructField i = (StructField)x0$1._2();
            return this.wrapperFor(i.getFieldObjectInspector(), f.dataType());
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
