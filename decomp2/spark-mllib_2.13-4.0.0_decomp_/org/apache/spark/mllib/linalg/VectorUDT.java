package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.AlphaComponent;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.expressions.GenericInternalRow;
import org.apache.spark.sql.catalyst.expressions.UnsafeArrayData;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.UserDefinedType;
import org.apache.spark.sql.types.ByteType.;
import scala.MatchError;
import scala.Option;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@AlphaComponent
@ScalaSignature(
   bytes = "\u0006\u0005!4Aa\u0003\u0007\u0001/!)A\u0005\u0001C\u0001K!)q\u0005\u0001C!Q!)A\u0006\u0001C![!)a\u0007\u0001C!o!)\u0001\t\u0001C!\u0003\")Q\n\u0001C!\u001d\")!\u000b\u0001C!'\")\u0011\f\u0001C!5\")a\f\u0001C!\u0003\"1q\f\u0001C!!\u0001\u0014\u0011BV3di>\u0014X\u000b\u0012+\u000b\u00055q\u0011A\u00027j]\u0006dwM\u0003\u0002\u0010!\u0005)Q\u000e\u001c7jE*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001A\u0002cA\r\u001fA5\t!D\u0003\u0002\u001c9\u0005)A/\u001f9fg*\u0011Q\u0004E\u0001\u0004gFd\u0017BA\u0010\u001b\u0005=)6/\u001a:EK\u001aLg.\u001a3UsB,\u0007CA\u0011#\u001b\u0005a\u0011BA\u0012\r\u0005\u00191Vm\u0019;pe\u00061A(\u001b8jiz\"\u0012A\n\t\u0003C\u0001\tqa]9m)f\u0004X-F\u0001*!\tI\"&\u0003\u0002,5\tQ1\u000b\u001e:vGR$\u0016\u0010]3\u0002\u0013M,'/[1mSj,GC\u0001\u00185!\ty#'D\u00011\u0015\t\tD$\u0001\u0005dCR\fG._:u\u0013\t\u0019\u0004GA\u0006J]R,'O\\1m%><\b\"B\u001b\u0004\u0001\u0004\u0001\u0013aA8cU\u0006YA-Z:fe&\fG.\u001b>f)\t\u0001\u0003\bC\u0003:\t\u0001\u0007!(A\u0003eCR,X\u000e\u0005\u0002<}5\tAHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyDHA\u0002B]f\fQ\u0001]=V\tR+\u0012A\u0011\t\u0003\u0007*s!\u0001\u0012%\u0011\u0005\u0015cT\"\u0001$\u000b\u0005\u001d3\u0012A\u0002\u001fs_>$h(\u0003\u0002Jy\u00051\u0001K]3eK\u001aL!a\u0013'\u0003\rM#(/\u001b8h\u0015\tIE(A\u0005vg\u0016\u00148\t\\1tgV\tq\nE\u0002D!\u0002J!!\u0015'\u0003\u000b\rc\u0017m]:\u0002\r\u0015\fX/\u00197t)\t!v\u000b\u0005\u0002<+&\u0011a\u000b\u0010\u0002\b\u0005>|G.Z1o\u0011\u0015Av\u00011\u0001;\u0003\u0005y\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003m\u0003\"a\u000f/\n\u0005uc$aA%oi\u0006AA/\u001f9f\u001d\u0006lW-\u0001\u0006bg:+H\u000e\\1cY\u0016,\u0012A\n\u0015\u0003\u0001\t\u0004\"a\u00194\u000e\u0003\u0011T!!\u001a\t\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002hI\nq\u0011\t\u001c9iC\u000e{W\u000e]8oK:$\b"
)
public class VectorUDT extends UserDefinedType {
   public StructType sqlType() {
      return new StructType((StructField[])((Object[])(new StructField[]{new StructField("type", .MODULE$, false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("size", org.apache.spark.sql.types.IntegerType..MODULE$, true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("indices", new ArrayType(org.apache.spark.sql.types.IntegerType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), new StructField("values", new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), true, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4())})));
   }

   public InternalRow serialize(final Vector obj) {
      if (obj instanceof SparseVector var4) {
         Option var5 = SparseVector$.MODULE$.unapply(var4);
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
         Option var11 = DenseVector$.MODULE$.unapply(var10);
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
      return "pyspark.mllib.linalg.VectorUDT";
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
