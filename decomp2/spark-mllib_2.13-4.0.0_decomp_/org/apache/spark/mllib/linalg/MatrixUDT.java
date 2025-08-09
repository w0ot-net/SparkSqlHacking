package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeArrayData;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.ByteType.;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054Qa\u0003\u0007\u0001!YAQa\t\u0001\u0005\u0002\u0015BQa\n\u0001\u0005B!BQ\u0001\f\u0001\u0005B5BQA\u000e\u0001\u0005B]BQ\u0001\u0011\u0001\u0005B\u0005CQ!\u0014\u0001\u0005B9CQ\u0001\u0016\u0001\u0005BUCQ!\u0017\u0001\u0005BiCQA\u0018\u0001\u0005BiCaa\u0018\u0001\u0005BA\u0001'!C'biJL\u00070\u0016#U\u0015\tia\"\u0001\u0004mS:\fGn\u001a\u0006\u0003\u001fA\tQ!\u001c7mS\nT!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\n\u0003\u0001]\u00012\u0001G\u000f \u001b\u0005I\"B\u0001\u000e\u001c\u0003\u0015!\u0018\u0010]3t\u0015\ta\u0002#A\u0002tc2L!AH\r\u0003\u001fU\u001bXM\u001d#fM&tW\r\u001a+za\u0016\u0004\"\u0001I\u0011\u000e\u00031I!A\t\u0007\u0003\r5\u000bGO]5y\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0014\u0011\u0005\u0001\u0002\u0011aB:rYRK\b/Z\u000b\u0002SA\u0011\u0001DK\u0005\u0003We\u0011!b\u0015;sk\u000e$H+\u001f9f\u0003%\u0019XM]5bY&TX\r\u0006\u0002/iA\u0011qFM\u0007\u0002a)\u0011\u0011gG\u0001\tG\u0006$\u0018\r\\=ti&\u00111\u0007\r\u0002\f\u0013:$XM\u001d8bYJ{w\u000fC\u00036\u0007\u0001\u0007q$A\u0002pE*\f1\u0002Z3tKJL\u0017\r\\5{KR\u0011q\u0004\u000f\u0005\u0006s\u0011\u0001\rAO\u0001\u0006I\u0006$X/\u001c\t\u0003wyj\u0011\u0001\u0010\u0006\u0002{\u0005)1oY1mC&\u0011q\b\u0010\u0002\u0004\u0003:L\u0018!C;tKJ\u001cE.Y:t+\u0005\u0011\u0005cA\"K?9\u0011A\t\u0013\t\u0003\u000brj\u0011A\u0012\u0006\u0003\u000f\u0012\na\u0001\u0010:p_Rt\u0014BA%=\u0003\u0019\u0001&/\u001a3fM&\u00111\n\u0014\u0002\u0006\u00072\f7o\u001d\u0006\u0003\u0013r\na!Z9vC2\u001cHCA(S!\tY\u0004+\u0003\u0002Ry\t9!i\\8mK\u0006t\u0007\"B*\u0007\u0001\u0004Q\u0014!A8\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A\u0016\t\u0003w]K!\u0001\u0017\u001f\u0003\u0007%sG/\u0001\u0005usB,g*Y7f+\u0005Y\u0006CA\"]\u0013\tiFJ\u0001\u0004TiJLgnZ\u0001\u0006af,F\tV\u0001\u000bCNtU\u000f\u001c7bE2,W#\u0001\u0014"
)
public class MatrixUDT extends UserDefinedType {
   public StructType sqlType() {
      return new StructType((StructField[])((Object[])(new StructField[]{new StructField("type", .MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("numRows", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("numCols", org.apache.spark.sql.types.IntegerType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("colPtrs", new ArrayType(org.apache.spark.sql.types.IntegerType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("rowIndices", new ArrayType(org.apache.spark.sql.types.IntegerType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("values", new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("isTransposed", org.apache.spark.sql.types.BooleanType..MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
   }

   public InternalRow serialize(final Matrix obj) {
      GenericInternalRow row = new GenericInternalRow(7);
      if (obj instanceof SparseMatrix var5) {
         row.setByte(0, (byte)0);
         row.setInt(1, var5.numRows());
         row.setInt(2, var5.numCols());
         row.update(3, UnsafeArrayData.fromPrimitiveArray(var5.colPtrs()));
         row.update(4, UnsafeArrayData.fromPrimitiveArray(var5.rowIndices()));
         row.update(5, UnsafeArrayData.fromPrimitiveArray(var5.values()));
         row.setBoolean(6, var5.isTransposed());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!(obj instanceof DenseMatrix)) {
            throw new MatchError(obj);
         }

         DenseMatrix var6 = (DenseMatrix)obj;
         row.setByte(0, (byte)1);
         row.setInt(1, var6.numRows());
         row.setInt(2, var6.numCols());
         row.setNullAt(3);
         row.setNullAt(4);
         row.update(5, UnsafeArrayData.fromPrimitiveArray(var6.values()));
         row.setBoolean(6, var6.isTransposed());
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      return row;
   }

   public Matrix deserialize(final Object datum) {
      if (datum instanceof InternalRow) {
         InternalRow var4 = (InternalRow)datum;
         scala.Predef..MODULE$.require(var4.numFields() == 7, () -> "MatrixUDT.deserialize given row with length " + var4.numFields() + " but requires length == 7");
         byte tpe = var4.getByte(0);
         int numRows = var4.getInt(1);
         int numCols = var4.getInt(2);
         double[] values = var4.getArray(5).toDoubleArray();
         boolean isTransposed = var4.getBoolean(6);
         switch (tpe) {
            case 0:
               int[] colPtrs = var4.getArray(3).toIntArray();
               int[] rowIndices = var4.getArray(4).toIntArray();
               return new SparseMatrix(numRows, numCols, colPtrs, rowIndices, values, isTransposed);
            case 1:
               return new DenseMatrix(numRows, numCols, values, isTransposed);
            default:
               throw new MatchError(BoxesRunTime.boxToByte(tpe));
         }
      } else {
         throw new MatchError(datum);
      }
   }

   public Class userClass() {
      return Matrix.class;
   }

   public boolean equals(final Object o) {
      return o instanceof MatrixUDT;
   }

   public int hashCode() {
      return MatrixUDT.class.getName().hashCode();
   }

   public String typeName() {
      return "matrix";
   }

   public String pyUDT() {
      return "pyspark.mllib.linalg.MatrixUDT";
   }

   public MatrixUDT asNullable() {
      return this;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
