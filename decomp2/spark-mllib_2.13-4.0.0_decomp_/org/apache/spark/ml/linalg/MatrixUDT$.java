package org.apache.spark.ml.linalg;

import java.io.Serializable;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.ByteType.;
import scala.runtime.ModuleSerializationProxy;

public final class MatrixUDT$ implements Serializable {
   public static final MatrixUDT$ MODULE$ = new MatrixUDT$();
   private static final StructType sqlType;

   static {
      sqlType = new StructType((StructField[])((Object[])(new StructField[]{new StructField("type", .MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("numRows", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("numCols", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("colPtrs", new ArrayType(org.apache.spark.sql.types.IntegerType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("rowIndices", new ArrayType(org.apache.spark.sql.types.IntegerType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("values", new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("isTransposed", org.apache.spark.sql.types.BooleanType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
   }

   public StructType sqlType() {
      return sqlType;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MatrixUDT$.class);
   }

   private MatrixUDT$() {
   }
}
