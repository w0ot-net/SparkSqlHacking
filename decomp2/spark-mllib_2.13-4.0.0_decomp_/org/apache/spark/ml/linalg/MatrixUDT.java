package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeArrayData;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import scala.MatchError;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}4Q!\u0005\n\u0001-qAQ!\u000b\u0001\u0005\u0002-BQ!\f\u0001\u0005B9BQA\r\u0001\u0005BMBQ\u0001\u0010\u0001\u0005BuBQA\u0012\u0001\u0005B\u001dCQa\u0015\u0001\u0005BQCQA\u0017\u0001\u0005BmCQa\u0018\u0001\u0005B\u0001DQ\u0001\u001a\u0001\u0005B\u0001Da!\u001a\u0001\u0005BY1wAB4\u0013\u0011\u00031\u0002N\u0002\u0004\u0012%!\u0005a#\u001b\u0005\u0006S1!\t!\u001e\u0005\b[1\u0011\r\u0011\"\u0001/\u0011\u00191H\u0002)A\u0005_!9q\u000fDA\u0001\n\u0013A(!C'biJL\u00070\u0016#U\u0015\t\u0019B#\u0001\u0004mS:\fGn\u001a\u0006\u0003+Y\t!!\u001c7\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u001c\"\u0001A\u000f\u0011\u0007y\u0019S%D\u0001 \u0015\t\u0001\u0013%A\u0003usB,7O\u0003\u0002#-\u0005\u00191/\u001d7\n\u0005\u0011z\"aD+tKJ$UMZ5oK\u0012$\u0016\u0010]3\u0011\u0005\u0019:S\"\u0001\n\n\u0005!\u0012\"AB'biJL\u00070\u0001\u0004=S:LGOP\u0002\u0001)\u0005a\u0003C\u0001\u0014\u0001\u0003\u001d\u0019\u0018\u000f\u001c+za\u0016,\u0012a\f\t\u0003=AJ!!M\u0010\u0003\u0015M#(/^2u)f\u0004X-A\u0005tKJL\u0017\r\\5{KR\u0011AG\u000f\t\u0003kaj\u0011A\u000e\u0006\u0003o\u0005\n\u0001bY1uC2L8\u000f^\u0005\u0003sY\u00121\"\u00138uKJt\u0017\r\u001c*po\")1h\u0001a\u0001K\u0005\u0019qN\u00196\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0003KyBQa\u0010\u0003A\u0002\u0001\u000bQ\u0001Z1uk6\u0004\"!\u0011#\u000e\u0003\tS\u0011aQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000b\n\u00131!\u00118z\u0003%)8/\u001a:DY\u0006\u001c8/F\u0001I!\rI\u0005+\n\b\u0003\u0015:\u0003\"a\u0013\"\u000e\u00031S!!\u0014\u0016\u0002\rq\u0012xn\u001c;?\u0013\ty%)\u0001\u0004Qe\u0016$WMZ\u0005\u0003#J\u0013Qa\u00117bgNT!a\u0014\"\u0002\r\u0015\fX/\u00197t)\t)\u0006\f\u0005\u0002B-&\u0011qK\u0011\u0002\b\u0005>|G.Z1o\u0011\u0015If\u00011\u0001A\u0003\u0005y\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003q\u0003\"!Q/\n\u0005y\u0013%aA%oi\u0006AA/\u001f9f\u001d\u0006lW-F\u0001b!\tI%-\u0003\u0002d%\n11\u000b\u001e:j]\u001e\fQ\u0001]=V\tR\u000b!\"Y:Ok2d\u0017M\u00197f+\u0005a\u0013!C'biJL\u00070\u0016#U!\t1CbE\u0002\rU6\u0004\"!Q6\n\u00051\u0014%AB!osJ+g\r\u0005\u0002og6\tqN\u0003\u0002qc\u0006\u0011\u0011n\u001c\u0006\u0002e\u0006!!.\u0019<b\u0013\t!xN\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001i\u0003!\u0019\u0018\u000f\u001c+za\u0016\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,G#A=\u0011\u0005ilX\"A>\u000b\u0005q\f\u0018\u0001\u00027b]\u001eL!A`>\u0003\r=\u0013'.Z2u\u0001"
)
public class MatrixUDT extends UserDefinedType {
   public StructType sqlType() {
      return MatrixUDT$.MODULE$.sqlType();
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
         .MODULE$.require(var4.numFields() == 7, () -> "MatrixUDT.deserialize given row with length " + var4.numFields() + " but requires length == 7");
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
      return "pyspark.ml.linalg.MatrixUDT";
   }

   public MatrixUDT asNullable() {
      return this;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
