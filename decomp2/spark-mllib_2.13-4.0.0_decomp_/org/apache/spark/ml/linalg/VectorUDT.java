package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.SparseVector.;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeArrayData;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import scala.MatchError;
import scala.Option;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}4Q!\u0005\n\u0001-qAQ!\u000b\u0001\u0005\u0002-BQ!\f\u0001\u0005F9BQA\r\u0001\u0005BMBQ\u0001\u0010\u0001\u0005BuBQA\u0012\u0001\u0005B\u001dCQa\u0015\u0001\u0005BQCQ\u0001\u0017\u0001\u0005BeCQa\u0018\u0001\u0005B\u0001DQ\u0001\u001a\u0001\u0005B\u001dCa!\u001a\u0001\u0005BY1wAB4\u0013\u0011\u00031\u0002N\u0002\u0004\u0012%!\u0005a#\u001b\u0005\u0006S1!\t!\u001e\u0005\b[1\u0011\r\u0011\"\u0001/\u0011\u00191H\u0002)A\u0005_!9q\u000fDA\u0001\n\u0013A(!\u0003,fGR|'/\u0016#U\u0015\t\u0019B#\u0001\u0004mS:\fGn\u001a\u0006\u0003+Y\t!!\u001c7\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u001c\"\u0001A\u000f\u0011\u0007y\u0019S%D\u0001 \u0015\t\u0001\u0013%A\u0003usB,7O\u0003\u0002#-\u0005\u00191/\u001d7\n\u0005\u0011z\"aD+tKJ$UMZ5oK\u0012$\u0016\u0010]3\u0011\u0005\u0019:S\"\u0001\n\n\u0005!\u0012\"A\u0002,fGR|'/\u0001\u0004=S:LGOP\u0002\u0001)\u0005a\u0003C\u0001\u0014\u0001\u0003\u001d\u0019\u0018\u000f\u001c+za\u0016,\u0012a\f\t\u0003=AJ!!M\u0010\u0003\u0015M#(/^2u)f\u0004X-A\u0005tKJL\u0017\r\\5{KR\u0011AG\u000f\t\u0003kaj\u0011A\u000e\u0006\u0003o\u0005\n\u0001bY1uC2L8\u000f^\u0005\u0003sY\u00121\"\u00138uKJt\u0017\r\u001c*po\")1h\u0001a\u0001K\u0005\u0019qN\u00196\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0003KyBQa\u0010\u0003A\u0002\u0001\u000bQ\u0001Z1uk6\u0004\"!\u0011#\u000e\u0003\tS\u0011aQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000b\n\u00131!\u00118z\u0003\u0015\u0001\u00180\u0016#U+\u0005A\u0005CA%Q\u001d\tQe\n\u0005\u0002L\u00056\tAJ\u0003\u0002NU\u00051AH]8pizJ!a\u0014\"\u0002\rA\u0013X\rZ3g\u0013\t\t&K\u0001\u0004TiJLgn\u001a\u0006\u0003\u001f\n\u000b\u0011\"^:fe\u000ec\u0017m]:\u0016\u0003U\u00032!\u0013,&\u0013\t9&KA\u0003DY\u0006\u001c8/\u0001\u0004fcV\fGn\u001d\u000b\u00035v\u0003\"!Q.\n\u0005q\u0013%a\u0002\"p_2,\u0017M\u001c\u0005\u0006=\u001e\u0001\r\u0001Q\u0001\u0002_\u0006A\u0001.Y:i\u0007>$W\rF\u0001b!\t\t%-\u0003\u0002d\u0005\n\u0019\u0011J\u001c;\u0002\u0011QL\b/\u001a(b[\u0016\f!\"Y:Ok2d\u0017M\u00197f+\u0005a\u0013!\u0003,fGR|'/\u0016#U!\t1CbE\u0002\rU6\u0004\"!Q6\n\u00051\u0014%AB!osJ+g\r\u0005\u0002og6\tqN\u0003\u0002qc\u0006\u0011\u0011n\u001c\u0006\u0002e\u0006!!.\u0019<b\u0013\t!xN\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001i\u0003!\u0019\u0018\u000f\u001c+za\u0016\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,G#A=\u0011\u0005ilX\"A>\u000b\u0005q\f\u0018\u0001\u00027b]\u001eL!A`>\u0003\r=\u0013'.Z2u\u0001"
)
public class VectorUDT extends UserDefinedType {
   public final StructType sqlType() {
      return VectorUDT$.MODULE$.sqlType();
   }

   public InternalRow serialize(final Vector obj) {
      if (obj instanceof SparseVector var4) {
         Option var5 = .MODULE$.unapply(var4);
         if (!var5.isEmpty()) {
            int size = BoxesRunTime.unboxToInt(((Tuple3)var5.get())._1());
            int[] indices = (int[])((Tuple3)var5.get())._2();
            double[] values = (double[])((Tuple3)var5.get())._3();
            GenericInternalRow row = new GenericInternalRow(4);
            row.setByte(0, (byte)0);
            row.setInt(1, size);
            row.update(2, UnsafeArrayData.fromPrimitiveArray(indices));
            row.update(3, UnsafeArrayData.fromPrimitiveArray(values));
            return row;
         }
      }

      if (obj instanceof DenseVector var10) {
         Option var11 = org.apache.spark.ml.linalg.DenseVector..MODULE$.unapply(var10);
         if (!var11.isEmpty()) {
            double[] values = (double[])var11.get();
            GenericInternalRow row = new GenericInternalRow(4);
            row.setByte(0, (byte)1);
            row.setNullAt(1);
            row.setNullAt(2);
            row.update(3, UnsafeArrayData.fromPrimitiveArray(values));
            return row;
         }
      }

      throw new IllegalArgumentException("Unknown vector type " + obj.getClass() + ".");
   }

   public Vector deserialize(final Object datum) {
      if (datum instanceof InternalRow) {
         InternalRow var4 = (InternalRow)datum;
         scala.Predef..MODULE$.require(var4.numFields() == 4, () -> "VectorUDT.deserialize given row with length " + var4.numFields() + " but requires length == 4");
         byte tpe = var4.getByte(0);
         switch (tpe) {
            case 0:
               int size = var4.getInt(1);
               int[] indices = var4.getArray(2).toIntArray();
               double[] values = var4.getArray(3).toDoubleArray();
               return new SparseVector(size, indices, values);
            case 1:
               double[] values = var4.getArray(3).toDoubleArray();
               return new DenseVector(values);
            default:
               throw new MatchError(BoxesRunTime.boxToByte(tpe));
         }
      } else {
         throw new MatchError(datum);
      }
   }

   public String pyUDT() {
      return "pyspark.ml.linalg.VectorUDT";
   }

   public Class userClass() {
      return Vector.class;
   }

   public boolean equals(final Object o) {
      return o instanceof VectorUDT;
   }

   public int hashCode() {
      return VectorUDT.class.getName().hashCode();
   }

   public String typeName() {
      return "vector";
   }

   public VectorUDT asNullable() {
      return this;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
