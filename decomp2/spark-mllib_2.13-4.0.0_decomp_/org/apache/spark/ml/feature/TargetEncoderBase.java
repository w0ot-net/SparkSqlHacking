package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Array.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q4\u0001BC\u0006\u0011\u0002\u0007\u0005Q\"\u0006\u0005\u0006o\u0001!\t!\u000f\u0005\b{\u0001\u0011\r\u0011\"\u0011?\u0011\u001d1\u0006A1A\u0005\u0002yBQ\u0001\u0017\u0001\u0005\u0006eCqA\u0017\u0001C\u0002\u0013\u00051\fC\u0003a\u0001\u0011\u0015\u0011\r\u0003\u0004f\u0001\u0011\u00051B\u001a\u0005\u0007U\u0002!\ta\u00034\t\r-\u0004A\u0011A\u0006m\u0005E!\u0016M]4fi\u0016s7m\u001c3fe\n\u000b7/\u001a\u0006\u0003\u00195\tqAZ3biV\u0014XM\u0003\u0002\u000f\u001f\u0005\u0011Q\u000e\u001c\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sONI\u0001A\u0006\u000f#Q-r\u0013\u0007\u000e\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0005}i\u0011!\u00029be\u0006l\u0017BA\u0011\u001f\u0005\u0019\u0001\u0016M]1ngB\u00111EJ\u0007\u0002I)\u0011QEH\u0001\u0007g\"\f'/\u001a3\n\u0005\u001d\"#a\u0003%bg2\u000b'-\u001a7D_2\u0004\"aI\u0015\n\u0005)\"#a\u0003%bg&s\u0007/\u001e;D_2\u0004\"a\t\u0017\n\u00055\"#\u0001\u0004%bg&s\u0007/\u001e;D_2\u001c\bCA\u00120\u0013\t\u0001DE\u0001\u0007ICN|U\u000f\u001e9vi\u000e{G\u000e\u0005\u0002$e%\u00111\u0007\n\u0002\u000e\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\\:\u0011\u0005\r*\u0014B\u0001\u001c%\u0005AA\u0015m\u001d%b]\u0012dW-\u00138wC2LG-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005Q\u0004CA\f<\u0013\ta\u0004D\u0001\u0003V]&$\u0018!\u00045b]\u0012dW-\u00138wC2LG-F\u0001@!\ri\u0002IQ\u0005\u0003\u0003z\u0011Q\u0001U1sC6\u0004\"a\u0011&\u000f\u0005\u0011C\u0005CA#\u0019\u001b\u00051%BA$9\u0003\u0019a$o\\8u}%\u0011\u0011\nG\u0001\u0007!J,G-\u001a4\n\u0005-c%AB*ue&twM\u0003\u0002J1!\u001a!A\u0014+\u0011\u0005=\u0013V\"\u0001)\u000b\u0005E{\u0011AC1o]>$\u0018\r^5p]&\u00111\u000b\u0015\u0002\u0006'&t7-Z\u0011\u0002+\u0006)AG\f\u0019/a\u0005QA/\u0019:hKR$\u0016\u0010]3)\u0007\rqE+A\u0007hKR$\u0016M]4fiRK\b/Z\u000b\u0002\u0005\u0006I1/\\8pi\"LgnZ\u000b\u00029B\u0011Q$X\u0005\u0003=z\u00111\u0002R8vE2,\u0007+\u0019:b[\"\u001aQA\u0014+\u0002\u0019\u001d,GoU7p_RD\u0017N\\4\u0016\u0003\t\u0004\"aF2\n\u0005\u0011D\"A\u0002#pk\ndW-A\u0007j]B,HOR3biV\u0014Xm]\u000b\u0002OB\u0019q\u0003\u001b\"\n\u0005%D\"!B!se\u0006L\u0018AD8viB,HOR3biV\u0014Xm]\u0001\u000fm\u0006d\u0017\u000eZ1uKN\u001b\u0007.Z7b)\riWo\u001e\t\u0003]Nl\u0011a\u001c\u0006\u0003aF\fQ\u0001^=qKNT!A]\b\u0002\u0007M\fH.\u0003\u0002u_\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000bYL\u0001\u0019A7\u0002\rM\u001c\u0007.Z7b\u0011\u0015A\u0018\u00021\u0001z\u0003\u001d1\u0017\u000e\u001e;j]\u001e\u0004\"a\u0006>\n\u0005mD\"a\u0002\"p_2,\u0017M\u001c"
)
public interface TargetEncoderBase extends HasLabelCol, HasInputCol, HasInputCols, HasOutputCol, HasOutputCols, HasHandleInvalid {
   void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$handleInvalid_$eq(final Param x$1);

   void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$targetType_$eq(final Param x$1);

   void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$smoothing_$eq(final DoubleParam x$1);

   Param handleInvalid();

   Param targetType();

   // $FF: synthetic method
   static String getTargetType$(final TargetEncoderBase $this) {
      return $this.getTargetType();
   }

   default String getTargetType() {
      return (String)this.$(this.targetType());
   }

   DoubleParam smoothing();

   // $FF: synthetic method
   static double getSmoothing$(final TargetEncoderBase $this) {
      return $this.getSmoothing();
   }

   default double getSmoothing() {
      return BoxesRunTime.unboxToDouble(this.$(this.smoothing()));
   }

   // $FF: synthetic method
   static String[] inputFeatures$(final TargetEncoderBase $this) {
      return $this.inputFeatures();
   }

   default String[] inputFeatures() {
      if (this.isSet(this.inputCol())) {
         return (String[])((Object[])(new String[]{(String)this.$(this.inputCol())}));
      } else {
         return this.isSet(this.inputCols()) ? (String[])this.$(this.inputCols()) : (String[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
      }
   }

   // $FF: synthetic method
   static String[] outputFeatures$(final TargetEncoderBase $this) {
      return $this.outputFeatures();
   }

   default String[] outputFeatures() {
      if (this.isSet(this.outputCol())) {
         return (String[])((Object[])(new String[]{(String)this.$(this.outputCol())}));
      } else {
         return this.isSet(this.outputCols()) ? (String[])this.$(this.outputCols()) : (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.inputFeatures()), (field) -> field + "_indexed", scala.reflect.ClassTag..MODULE$.apply(String.class));
      }
   }

   // $FF: synthetic method
   static StructType validateSchema$(final TargetEncoderBase $this, final StructType schema, final boolean fitting) {
      return $this.validateSchema(schema, fitting);
   }

   default StructType validateSchema(final StructType schema, final boolean fitting) {
      scala.Predef..MODULE$.require(this.inputFeatures().length > 0, () -> "At least one input column must be specified.");
      scala.Predef..MODULE$.require(this.inputFeatures().length == this.outputFeatures().length, () -> {
         int var10000 = this.inputFeatures().length;
         return "The number of input columns " + var10000 + " must be the same as the number of output columns " + this.outputFeatures().length + ".";
      });
      String[] features = fitting ? (String[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.inputFeatures()), this.$(this.labelCol()), scala.reflect.ClassTag..MODULE$.apply(String.class)) : this.inputFeatures();
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])features), (feature) -> {
         $anonfun$validateSchema$3(schema, feature);
         return BoxedUnit.UNIT;
      });
      return schema;
   }

   // $FF: synthetic method
   static void $anonfun$validateSchema$3(final StructType schema$1, final String feature) {
      try {
         StructField field = schema$1.apply(feature);
         if (!(field.dataType() instanceof NumericType)) {
            throw new SparkException("Data type for column " + feature + " is " + field.dataType() + ", but a subclass of " + org.apache.spark.sql.types.NumericType..MODULE$ + " is required.");
         }
      } catch (IllegalArgumentException var4) {
         throw new SparkException("No column named " + feature + " found on dataset.");
      }
   }

   static void $init$(final TargetEncoderBase $this) {
      $this.org$apache$spark$ml$feature$TargetEncoderBase$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "How to handle invalid data during transform(). Options are 'keep' (invalid data presented as an extra categorical feature) or 'error' (throw an error). Note that this Param is only used during transform; during fitting, invalid data will result in an error.", ParamValidators$.MODULE$.inArray((Object)TargetEncoder$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.handleInvalid().$minus$greater(TargetEncoder$.MODULE$.ERROR_INVALID())}));
      $this.org$apache$spark$ml$feature$TargetEncoderBase$_setter_$targetType_$eq(new Param($this, "targetType", "Type of label considered during fit(). Options are 'binary' and 'continuous'. When 'binary', estimates are calculated as conditional probability of the target given each category. When 'continuous', estimates are calculated as the average of the target given each categoryNote that this Param is only used during fitting.", ParamValidators$.MODULE$.inArray((Object)TargetEncoder$.MODULE$.supportedTargetTypes()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.targetType().$minus$greater(TargetEncoder$.MODULE$.TARGET_BINARY())}));
      $this.org$apache$spark$ml$feature$TargetEncoderBase$_setter_$smoothing_$eq(new DoubleParam($this, "smoothing", "Smoothing factor for encodings. Smoothing blends in-class estimates with overall estimates according to the relative size of the particular class on the whole dataset, reducing the risk of overfitting due to unreliable estimates", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.smoothing().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
