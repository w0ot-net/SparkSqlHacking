package org.apache.spark.ml.fpm;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Q4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005AB\u0006\u0005\u0006S\u0001!\ta\u000b\u0005\b_\u0001\u0011\r\u0011\"\u00011\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u001dY\u0005A1A\u0005\u00021CQ!\u0015\u0001\u0005\u0002ICqa\u0016\u0001C\u0002\u0013\u0005\u0001\fC\u0003^\u0001\u0011\u0005a\fC\u0004d\u0001\t\u0007I\u0011\u0001'\t\u000b\u0015\u0004A\u0011\u0001*\t\u000b\u001d\u0004A\u0011\u00035\u0003\u001d\u0019\u0003vI]8xi\"\u0004\u0016M]1ng*\u0011QBD\u0001\u0004MBl'BA\b\u0011\u0003\tiGN\u0003\u0002\u0012%\u0005)1\u000f]1sW*\u00111\u0003F\u0001\u0007CB\f7\r[3\u000b\u0003U\t1a\u001c:h'\u0011\u0001q#H\u0012\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\tq\u0012%D\u0001 \u0015\t\u0001c\"A\u0003qCJ\fW.\u0003\u0002#?\t1\u0001+\u0019:b[N\u0004\"\u0001J\u0014\u000e\u0003\u0015R!AJ\u0010\u0002\rMD\u0017M]3e\u0013\tASE\u0001\tICN\u0004&/\u001a3jGRLwN\\\"pY\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001-!\tAR&\u0003\u0002/3\t!QK\\5u\u0003!IG/Z7t\u0007>dW#A\u0019\u0011\u0007y\u0011D'\u0003\u00024?\t)\u0001+\u0019:b[B\u0011Q\u0007\u0010\b\u0003mi\u0002\"aN\r\u000e\u0003aR!!\u000f\u0016\u0002\rq\u0012xn\u001c;?\u0013\tY\u0014$\u0001\u0004Qe\u0016$WMZ\u0005\u0003{y\u0012aa\u0015;sS:<'BA\u001e\u001aQ\r\u0011\u0001I\u0012\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007B\t!\"\u00198o_R\fG/[8o\u0013\t)%IA\u0003TS:\u001cW-I\u0001H\u0003\u0015\u0011dF\r\u00181\u0003-9W\r^%uK6\u001c8i\u001c7\u0016\u0003QB3a\u0001!G\u0003)i\u0017N\\*vaB|'\u000f^\u000b\u0002\u001bB\u0011aDT\u0005\u0003\u001f~\u00111\u0002R8vE2,\u0007+\u0019:b[\"\u001aA\u0001\u0011$\u0002\u001b\u001d,G/T5o'V\u0004\bo\u001c:u+\u0005\u0019\u0006C\u0001\rU\u0013\t)\u0016D\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000b\u00013\u0015!\u00048v[B\u000b'\u000f^5uS>t7/F\u0001Z!\tq\",\u0003\u0002\\?\tA\u0011J\u001c;QCJ\fW\u000eK\u0002\u0007\u0001\u001a\u000b\u0001cZ3u\u001dVl\u0007+\u0019:uSRLwN\\:\u0016\u0003}\u0003\"\u0001\u00071\n\u0005\u0005L\"aA%oi\"\u001aq\u0001\u0011$\u0002\u001b5LgnQ8oM&$WM\\2fQ\rA\u0001IR\u0001\u0011O\u0016$X*\u001b8D_:4\u0017\u000eZ3oG\u0016D3!\u0003!G\u0003i1\u0018\r\\5eCR,\u0017I\u001c3Ue\u0006t7OZ8s[N\u001b\u0007.Z7b)\tI\u0017\u000f\u0005\u0002k_6\t1N\u0003\u0002m[\u0006)A/\u001f9fg*\u0011a\u000eE\u0001\u0004gFd\u0017B\u00019l\u0005)\u0019FO];diRK\b/\u001a\u0005\u0006e*\u0001\r![\u0001\u0007g\u000eDW-\\1)\u0007)\u0001e\t"
)
public interface FPGrowthParams extends HasPredictionCol {
   void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$itemsCol_$eq(final Param x$1);

   void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minSupport_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$numPartitions_$eq(final IntParam x$1);

   void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minConfidence_$eq(final DoubleParam x$1);

   Param itemsCol();

   // $FF: synthetic method
   static String getItemsCol$(final FPGrowthParams $this) {
      return $this.getItemsCol();
   }

   default String getItemsCol() {
      return (String)this.$(this.itemsCol());
   }

   DoubleParam minSupport();

   // $FF: synthetic method
   static double getMinSupport$(final FPGrowthParams $this) {
      return $this.getMinSupport();
   }

   default double getMinSupport() {
      return BoxesRunTime.unboxToDouble(this.$(this.minSupport()));
   }

   IntParam numPartitions();

   // $FF: synthetic method
   static int getNumPartitions$(final FPGrowthParams $this) {
      return $this.getNumPartitions();
   }

   default int getNumPartitions() {
      return BoxesRunTime.unboxToInt(this.$(this.numPartitions()));
   }

   DoubleParam minConfidence();

   // $FF: synthetic method
   static double getMinConfidence$(final FPGrowthParams $this) {
      return $this.getMinConfidence();
   }

   default double getMinConfidence() {
      return BoxesRunTime.unboxToDouble(this.$(this.minConfidence()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final FPGrowthParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      DataType inputType = schema.apply((String)this.$(this.itemsCol())).dataType();
      .MODULE$.require(inputType instanceof ArrayType, () -> {
         String var10000 = org.apache.spark.sql.types.ArrayType..MODULE$.simpleString();
         return "The input column must be " + var10000 + ", but got " + inputType.catalogString() + ".";
      });
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), schema.apply((String)this.$(this.itemsCol())).dataType(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final FPGrowthParams $this) {
      $this.org$apache$spark$ml$fpm$FPGrowthParams$_setter_$itemsCol_$eq(new Param($this, "itemsCol", "items column name", scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minSupport_$eq(new DoubleParam($this, "minSupport", "the minimal support level of a frequent pattern", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.org$apache$spark$ml$fpm$FPGrowthParams$_setter_$numPartitions_$eq(new IntParam($this, "numPartitions", "Number of partitions used by parallel FP-growth", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minConfidence_$eq(new DoubleParam($this, "minConfidence", "minimal confidence for generating Association Rule", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.minSupport().$minus$greater(BoxesRunTime.boxToDouble(0.3)), $this.itemsCol().$minus$greater("items"), $this.minConfidence().$minus$greater(BoxesRunTime.boxToDouble(0.8))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
