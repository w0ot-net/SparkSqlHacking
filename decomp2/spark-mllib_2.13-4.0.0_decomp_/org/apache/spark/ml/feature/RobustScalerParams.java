package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasRelativeError;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3\u0001b\u0003\u0007\u0011\u0002\u0007\u0005AB\u0006\u0005\u0006_\u0001!\t!\r\u0005\bk\u0001\u0011\r\u0011\"\u00017\u0011\u0015Q\u0004\u0001\"\u0001<\u0011\u001dy\u0004A1A\u0005\u0002YBQ\u0001\u0011\u0001\u0005\u0002mBq!\u0011\u0001C\u0002\u0013\u0005!\tC\u0003G\u0001\u0011\u0005q\tC\u0004L\u0001\t\u0007I\u0011\u0001\"\t\u000b1\u0003A\u0011A$\t\u000b5\u0003A\u0011\u0003(\u0003%I{'-^:u'\u000e\fG.\u001a:QCJ\fWn\u001d\u0006\u0003\u001b9\tqAZ3biV\u0014XM\u0003\u0002\u0010!\u0005\u0011Q\u000e\u001c\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sON1\u0001aF\u000f$S1\u0002\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007C\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u000f\u0003\u0015\u0001\u0018M]1n\u0013\t\u0011sD\u0001\u0004QCJ\fWn\u001d\t\u0003I\u001dj\u0011!\n\u0006\u0003M}\taa\u001d5be\u0016$\u0017B\u0001\u0015&\u0005-A\u0015m]%oaV$8i\u001c7\u0011\u0005\u0011R\u0013BA\u0016&\u00051A\u0015m](viB,HoQ8m!\t!S&\u0003\u0002/K\t\u0001\u0002*Y:SK2\fG/\u001b<f\u000bJ\u0014xN]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t!\u0007\u0005\u0002\u0019g%\u0011A'\u0007\u0002\u0005+:LG/A\u0003m_^,'/F\u00018!\tq\u0002(\u0003\u0002:?\tYAi\\;cY\u0016\u0004\u0016M]1n\u0003!9W\r\u001e'po\u0016\u0014X#\u0001\u001f\u0011\u0005ai\u0014B\u0001 \u001a\u0005\u0019!u.\u001e2mK\u0006)Q\u000f\u001d9fe\u0006Aq-\u001a;VaB,'/A\u0007xSRD7)\u001a8uKJLgnZ\u000b\u0002\u0007B\u0011a\u0004R\u0005\u0003\u000b~\u0011ABQ8pY\u0016\fg\u000eU1sC6\f\u0001cZ3u/&$\bnQ3oi\u0016\u0014\u0018N\\4\u0016\u0003!\u0003\"\u0001G%\n\u0005)K\"a\u0002\"p_2,\u0017M\\\u0001\fo&$\bnU2bY&tw-\u0001\bhKR<\u0016\u000e\u001e5TG\u0006d\u0017N\\4\u00025Y\fG.\u001b3bi\u0016\fe\u000e\u001a+sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\u0005=;\u0006C\u0001)V\u001b\u0005\t&B\u0001*T\u0003\u0015!\u0018\u0010]3t\u0015\t!\u0006#A\u0002tc2L!AV)\u0003\u0015M#(/^2u)f\u0004X\rC\u0003Y\u0015\u0001\u0007q*\u0001\u0004tG\",W.\u0019"
)
public interface RobustScalerParams extends HasInputCol, HasOutputCol, HasRelativeError {
   void org$apache$spark$ml$feature$RobustScalerParams$_setter_$lower_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$RobustScalerParams$_setter_$upper_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$RobustScalerParams$_setter_$withCentering_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$feature$RobustScalerParams$_setter_$withScaling_$eq(final BooleanParam x$1);

   DoubleParam lower();

   // $FF: synthetic method
   static double getLower$(final RobustScalerParams $this) {
      return $this.getLower();
   }

   default double getLower() {
      return BoxesRunTime.unboxToDouble(this.$(this.lower()));
   }

   DoubleParam upper();

   // $FF: synthetic method
   static double getUpper$(final RobustScalerParams $this) {
      return $this.getUpper();
   }

   default double getUpper() {
      return BoxesRunTime.unboxToDouble(this.$(this.upper()));
   }

   BooleanParam withCentering();

   // $FF: synthetic method
   static boolean getWithCentering$(final RobustScalerParams $this) {
      return $this.getWithCentering();
   }

   default boolean getWithCentering() {
      return BoxesRunTime.unboxToBoolean(this.$(this.withCentering()));
   }

   BooleanParam withScaling();

   // $FF: synthetic method
   static boolean getWithScaling$(final RobustScalerParams $this) {
      return $this.getWithScaling();
   }

   default boolean getWithScaling() {
      return BoxesRunTime.unboxToBoolean(this.$(this.withScaling()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final RobustScalerParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      .MODULE$.require(BoxesRunTime.unboxToDouble(this.$(this.lower())) < BoxesRunTime.unboxToDouble(this.$(this.upper())), () -> {
         Object var10000 = this.$(this.lower());
         return "The specified lower quantile(" + var10000 + ") is larger or equal to upper quantile(" + this.$(this.upper()) + ")";
      });
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol())), () -> "Output column " + this.$(this.outputCol()) + " already exists.");
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final RobustScalerParams $this) {
      $this.org$apache$spark$ml$feature$RobustScalerParams$_setter_$lower_$eq(new DoubleParam($this, "lower", "Lower quantile to calculate quantile range", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, false)));
      $this.org$apache$spark$ml$feature$RobustScalerParams$_setter_$upper_$eq(new DoubleParam($this, "upper", "Upper quantile to calculate quantile range", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, false)));
      $this.org$apache$spark$ml$feature$RobustScalerParams$_setter_$withCentering_$eq(new BooleanParam($this, "withCentering", "Whether to center data with median"));
      $this.org$apache$spark$ml$feature$RobustScalerParams$_setter_$withScaling_$eq(new BooleanParam($this, "withScaling", "Whether to scale the data to quantile range"));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.withScaling().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.lower().$minus$greater(BoxesRunTime.boxToDouble((double)0.25F)), $this.upper().$minus$greater(BoxesRunTime.boxToDouble((double)0.75F)), $this.withCentering().$minus$greater(BoxesRunTime.boxToBoolean(false))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
