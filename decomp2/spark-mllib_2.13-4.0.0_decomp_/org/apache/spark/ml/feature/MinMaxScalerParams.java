package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001B\u0005\u0005\u0006Q\u0001!\tA\u000b\u0005\b]\u0001\u0011\r\u0011\"\u00010\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u001dA\u0004A1A\u0005\u0002=BQ!\u000f\u0001\u0005\u0002QBQA\u000f\u0001\u0005\u0012m\u0012!#T5o\u001b\u0006D8kY1mKJ\u0004\u0016M]1ng*\u0011\u0011BC\u0001\bM\u0016\fG/\u001e:f\u0015\tYA\"\u0001\u0002nY*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xmE\u0003\u0001'eyR\u0005\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035ui\u0011a\u0007\u0006\u00039)\tQ\u0001]1sC6L!AH\u000e\u0003\rA\u000b'/Y7t!\t\u00013%D\u0001\"\u0015\t\u00113$\u0001\u0004tQ\u0006\u0014X\rZ\u0005\u0003I\u0005\u00121\u0002S1t\u0013:\u0004X\u000f^\"pYB\u0011\u0001EJ\u0005\u0003O\u0005\u0012A\u0002S1t\u001fV$\b/\u001e;D_2\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002WA\u0011A\u0003L\u0005\u0003[U\u0011A!\u00168ji\u0006\u0019Q.\u001b8\u0016\u0003A\u0002\"AG\u0019\n\u0005IZ\"a\u0003#pk\ndW\rU1sC6\faaZ3u\u001b&tW#A\u001b\u0011\u0005Q1\u0014BA\u001c\u0016\u0005\u0019!u.\u001e2mK\u0006\u0019Q.\u0019=\u0002\r\u001d,G/T1y\u0003i1\u0018\r\\5eCR,\u0017I\u001c3Ue\u0006t7OZ8s[N\u001b\u0007.Z7b)\taD\t\u0005\u0002>\u00056\taH\u0003\u0002@\u0001\u0006)A/\u001f9fg*\u0011\u0011\tD\u0001\u0004gFd\u0017BA\"?\u0005)\u0019FO];diRK\b/\u001a\u0005\u0006\u000b\u001a\u0001\r\u0001P\u0001\u0007g\u000eDW-\\1"
)
public interface MinMaxScalerParams extends HasInputCol, HasOutputCol {
   void org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$min_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$max_$eq(final DoubleParam x$1);

   DoubleParam min();

   // $FF: synthetic method
   static double getMin$(final MinMaxScalerParams $this) {
      return $this.getMin();
   }

   default double getMin() {
      return BoxesRunTime.unboxToDouble(this.$(this.min()));
   }

   DoubleParam max();

   // $FF: synthetic method
   static double getMax$(final MinMaxScalerParams $this) {
      return $this.getMax();
   }

   default double getMax() {
      return BoxesRunTime.unboxToDouble(this.$(this.max()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final MinMaxScalerParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      .MODULE$.require(BoxesRunTime.unboxToDouble(this.$(this.min())) < BoxesRunTime.unboxToDouble(this.$(this.max())), () -> {
         Object var10000 = this.$(this.min());
         return "The specified min(" + var10000 + ") is larger or equal to max(" + this.$(this.max()) + ")";
      });
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol())), () -> "Output column " + this.$(this.outputCol()) + " already exists.");
      StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.outputCol()), new VectorUDT(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      return new StructType(outputFields);
   }

   static void $init$(final MinMaxScalerParams $this) {
      $this.org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$min_$eq(new DoubleParam($this, "min", "lower bound of the output feature range"));
      $this.org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$max_$eq(new DoubleParam($this, "max", "upper bound of the output feature range"));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.min().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.max().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
