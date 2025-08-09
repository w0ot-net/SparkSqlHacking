package org.apache.spark.ml.linalg;

import java.io.Serializable;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.ByteType.;
import scala.runtime.ModuleSerializationProxy;

public final class VectorUDT$ implements Serializable {
   public static final VectorUDT$ MODULE$ = new VectorUDT$();
   private static final StructType sqlType;

   static {
      sqlType = new StructType((StructField[])((Object[])(new StructField[]{new StructField("type", .MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("size", org.apache.spark.sql.types.IntegerType..MODULE$, true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("indices", new ArrayType(org.apache.spark.sql.types.IntegerType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("values", new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
   }

   public StructType sqlType() {
      return sqlType;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VectorUDT$.class);
   }

   private VectorUDT$() {
   }
}
