package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M4\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!B\u0005\u0005\u0006c\u0001!\ta\r\u0005\bo\u0001\u0011\r\u0011\"\u00119\u0011\u001d\u0001\u0006A1A\u0005\u0006ECQA\u0016\u0001\u0005\u0002]Ca\u0001\u0018\u0001\u0005\u0002!i\u0006\"\u00023\u0001\t#)'!E(oK\"{G/\u00128d_\u0012,'OQ1tK*\u0011\u0011BC\u0001\bM\u0016\fG/\u001e:f\u0015\tYA\"\u0001\u0002nY*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xm\u0005\u0005\u0001'eyR\u0005K\u0016/!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!$H\u0007\u00027)\u0011ADC\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003=m\u0011a\u0001U1sC6\u001c\bC\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u001c\u0003\u0019\u0019\b.\u0019:fI&\u0011A%\t\u0002\u0011\u0011\u0006\u001c\b*\u00198eY\u0016LeN^1mS\u0012\u0004\"\u0001\t\u0014\n\u0005\u001d\n#a\u0003%bg&s\u0007/\u001e;D_2\u0004\"\u0001I\u0015\n\u0005)\n#\u0001\u0004%bg&s\u0007/\u001e;D_2\u001c\bC\u0001\u0011-\u0013\ti\u0013E\u0001\u0007ICN|U\u000f\u001e9vi\u000e{G\u000e\u0005\u0002!_%\u0011\u0001'\t\u0002\u000e\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\\:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\u000e\t\u0003)UJ!AN\u000b\u0003\tUs\u0017\u000e^\u0001\u000eQ\u0006tG\r\\3J]Z\fG.\u001b3\u0016\u0003e\u00022A\u0007\u001e=\u0013\tY4DA\u0003QCJ\fW\u000e\u0005\u0002>\t:\u0011aH\u0011\t\u0003\u007fUi\u0011\u0001\u0011\u0006\u0003\u0003J\na\u0001\u0010:p_Rt\u0014BA\"\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011QI\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\r+\u0002f\u0001\u0002I\u001dB\u0011\u0011\nT\u0007\u0002\u0015*\u00111\nD\u0001\u000bC:tw\u000e^1uS>t\u0017BA'K\u0005\u0015\u0019\u0016N\\2fC\u0005y\u0015!\u0002\u001a/g9\u0002\u0014\u0001\u00033s_Bd\u0015m\u001d;\u0016\u0003I\u0003\"AG*\n\u0005Q[\"\u0001\u0004\"p_2,\u0017M\u001c)be\u0006l\u0007fA\u0002I\u001d\u0006Yq-\u001a;Ee>\u0004H*Y:u+\u0005A\u0006C\u0001\u000bZ\u0013\tQVCA\u0004C_>dW-\u00198)\u0007\u0011Ae*\u0001\u0007hKRLenT;u\u0007>d7\u000fF\u0001_!\u0011!r,Y1\n\u0005\u0001,\"A\u0002+va2,'\u0007E\u0002\u0015ErJ!aY\u000b\u0003\u000b\u0005\u0013(/Y=\u00025Y\fG.\u001b3bi\u0016\fe\u000e\u001a+sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0019t\u0007/\u001d\t\u0003O2l\u0011\u0001\u001b\u0006\u0003S*\fQ\u0001^=qKNT!a\u001b\u0007\u0002\u0007M\fH.\u0003\u0002nQ\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000b=4\u0001\u0019\u00014\u0002\rM\u001c\u0007.Z7b\u0011\u0015\u0001f\u00011\u0001Y\u0011\u0015\u0011h\u00011\u0001Y\u0003-YW-\u001a9J]Z\fG.\u001b3"
)
public interface OneHotEncoderBase extends HasHandleInvalid, HasInputCol, HasInputCols, HasOutputCol, HasOutputCols {
   void org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$handleInvalid_$eq(final Param x$1);

   void org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$dropLast_$eq(final BooleanParam x$1);

   Param handleInvalid();

   BooleanParam dropLast();

   // $FF: synthetic method
   static boolean getDropLast$(final OneHotEncoderBase $this) {
      return $this.getDropLast();
   }

   default boolean getDropLast() {
      return BoxesRunTime.unboxToBoolean(this.$(this.dropLast()));
   }

   // $FF: synthetic method
   static Tuple2 getInOutCols$(final OneHotEncoderBase $this) {
      return $this.getInOutCols();
   }

   default Tuple2 getInOutCols() {
      return this.isSet(this.inputCol()) ? new Tuple2((Object[])(new String[]{(String)this.$(this.inputCol())}), (Object[])(new String[]{(String)this.$(this.outputCol())})) : new Tuple2(this.$(this.inputCols()), this.$(this.outputCols()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final OneHotEncoderBase $this, final StructType schema, final boolean dropLast, final boolean keepInvalid) {
      return $this.validateAndTransformSchema(schema, dropLast, keepInvalid);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean dropLast, final boolean keepInvalid) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new .colon.colon(this.outputCol(), scala.collection.immutable.Nil..MODULE$), new .colon.colon(this.outputCols(), scala.collection.immutable.Nil..MODULE$));
      Tuple2 var6 = this.getInOutCols();
      if (var6 != null) {
         String[] inputColNames = (String[])var6._1();
         String[] outputColNames = (String[])var6._2();
         Tuple2 var5 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var5._1();
         String[] outputColNames = (String[])var5._2();
         scala.Predef..MODULE$.require(inputColNames.length == outputColNames.length, () -> "The number of input columns " + inputColNames.length + " must be the same as the number of output columns " + outputColNames.length + ".");
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames), (colName) -> {
            $anonfun$validateAndTransformSchema$2(schema, colName);
            return BoxedUnit.UNIT;
         });
         StructField[] inputFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames), (x$2) -> SchemaUtils$.MODULE$.getSchemaField(schema, x$2), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputFields), scala.Predef..MODULE$.wrapRefArray((Object[])outputColNames))), (x0$1) -> {
            if (x0$1 != null) {
               StructField inputField = (StructField)x0$1._1();
               String outputColName = (String)x0$1._2();
               return OneHotEncoderCommon$.MODULE$.transformOutputColumnSchema(inputField, outputColName, dropLast, keepInvalid);
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return (StructType)scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputFields), schema, (x0$2, x1$1) -> {
            Tuple2 var3 = new Tuple2(x0$2, x1$1);
            if (var3 != null) {
               StructType newSchema = (StructType)var3._1();
               StructField outputField = (StructField)var3._2();
               return SchemaUtils$.MODULE$.appendColumn(newSchema, outputField);
            } else {
               throw new MatchError(var3);
            }
         });
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   static void $anonfun$validateAndTransformSchema$2(final StructType schema$1, final String colName) {
      SchemaUtils$.MODULE$.checkNumericType(schema$1, colName, SchemaUtils$.MODULE$.checkNumericType$default$3());
   }

   static void $init$(final OneHotEncoderBase $this) {
      $this.org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "How to handle invalid data during transform(). Options are 'keep' (invalid data presented as an extra categorical feature) or error (throw an error). Note that this Param is only used during transform; during fitting, invalid data will result in an error.", ParamValidators$.MODULE$.inArray((Object)OneHotEncoder$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$dropLast_$eq(new BooleanParam($this, "dropLast", "whether to drop the last category"));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.handleInvalid().$minus$greater(OneHotEncoder$.MODULE$.ERROR_INVALID()), $this.dropLast().$minus$greater(BoxesRunTime.boxToBoolean(true))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
