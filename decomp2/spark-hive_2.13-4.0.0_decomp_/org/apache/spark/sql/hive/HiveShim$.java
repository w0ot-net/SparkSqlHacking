package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.rmi.server.UID;
import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.ColumnProjectionUtils;
import org.apache.hadoop.hive.serde2.avro.AvroGenericRecordWritable;
import org.apache.hadoop.hive.serde2.avro.AvroSerdeUtils.AvroTableProperties;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.io.Writable;
import org.apache.spark.sql.types.Decimal;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.jdk.CollectionConverters.;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class HiveShim$ {
   public static final HiveShim$ MODULE$ = new HiveShim$();
   private static final String HIVE_GENERIC_UDF_MACRO_CLS = "org.apache.hadoop.hive.ql.udf.generic.GenericUDFMacro";

   public String HIVE_GENERIC_UDF_MACRO_CLS() {
      return HIVE_GENERIC_UDF_MACRO_CLS;
   }

   private void appendReadColumnNames(final Configuration conf, final Seq cols) {
      String old = conf.get("hive.io.file.readcolumn.names", "");
      StringBuilder result = new StringBuilder(old);
      BooleanRef first = BooleanRef.create(old.isEmpty());
      cols.foreach((col) -> {
         if (first.elem) {
            first.elem = false;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            result.append(',');
         }

         return result.append(col);
      });
      conf.set("hive.io.file.readcolumn.names", result.toString());
   }

   public void appendReadColumns(final Configuration conf, final Seq ids, final Seq names) {
      if (ids != null) {
         ColumnProjectionUtils.appendReadColumns(conf, .MODULE$.SeqHasAsJava(ids).asJava());
      }

      if (names != null) {
         this.appendReadColumnNames(conf, names);
      }
   }

   public Writable prepareWritable(final Writable w, final Seq serDeProps) {
      if (w instanceof AvroGenericRecordWritable var5) {
         var5.setRecordReaderID(new UID());
         if (var5.getFileSchema() == null) {
            serDeProps.find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$prepareWritable$1(x$1))).foreach((kv) -> {
               $anonfun$prepareWritable$2(var5, kv);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var6 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      return w;
   }

   public Decimal toCatalystDecimal(final HiveDecimalObjectInspector hdoi, final Object data) {
      if (hdoi.preferWritable()) {
         HiveDecimalWritable value = hdoi.getPrimitiveWritableObject(data);
         return value == null ? null : org.apache.spark.sql.types.Decimal..MODULE$.apply(value.getHiveDecimal().bigDecimalValue(), hdoi.precision(), hdoi.scale());
      } else {
         HiveDecimal value = hdoi.getPrimitiveJavaObject(data);
         return value == null ? null : org.apache.spark.sql.types.Decimal..MODULE$.apply(value.bigDecimalValue(), hdoi.precision(), hdoi.scale());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareWritable$1(final Tuple2 x$1) {
      boolean var2;
      label23: {
         Object var10000 = x$1._1();
         String var1 = AvroTableProperties.SCHEMA_LITERAL.getPropName();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareWritable$2(final AvroGenericRecordWritable x2$1, final Tuple2 kv) {
      x2$1.setFileSchema((new Schema.Parser()).parse((String)kv._2()));
   }

   private HiveShim$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
