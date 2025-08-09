package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.param.shared.HasRelativeError;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!4\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0011b\u0005\u0005\u0006e\u0001!\t\u0001\u000e\u0005\bq\u0001\u0011\r\u0011\"\u0002:\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u001dQ\u0005A1A\u0005\u0006-CQa\u0014\u0001\u0005\u0002ACa\u0001\u0016\u0001\u0005\u0002%)\u0006\"\u0002/\u0001\t#i&!D%naV$XM\u001d)be\u0006l7O\u0003\u0002\u000b\u0017\u00059a-Z1ukJ,'B\u0001\u0007\u000e\u0003\tiGN\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'!\u0001AC\u0007\u0011'S1z\u0003CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\r\u0005\u0002\u001c=5\tAD\u0003\u0002\u001e\u0017\u0005)\u0001/\u0019:b[&\u0011q\u0004\b\u0002\u0007!\u0006\u0014\u0018-\\:\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\rb\u0012AB:iCJ,G-\u0003\u0002&E\tY\u0001*Y:J]B,HoQ8m!\t\ts%\u0003\u0002)E\ta\u0001*Y:J]B,HoQ8mgB\u0011\u0011EK\u0005\u0003W\t\u0012A\u0002S1t\u001fV$\b/\u001e;D_2\u0004\"!I\u0017\n\u00059\u0012#!\u0004%bg>+H\u000f];u\u0007>d7\u000f\u0005\u0002\"a%\u0011\u0011G\t\u0002\u0011\u0011\u0006\u001c(+\u001a7bi&4X-\u0012:s_J\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002kA\u0011QCN\u0005\u0003oY\u0011A!\u00168ji\u0006A1\u000f\u001e:bi\u0016<\u00170F\u0001;!\rY2(P\u0005\u0003yq\u0011Q\u0001U1sC6\u0004\"AP#\u000f\u0005}\u001a\u0005C\u0001!\u0017\u001b\u0005\t%B\u0001\"4\u0003\u0019a$o\\8u}%\u0011AIF\u0001\u0007!J,G-\u001a4\n\u0005\u0019;%AB*ue&twM\u0003\u0002E-\u0005Yq-\u001a;TiJ\fG/Z4z+\u0005i\u0014\u0001D7jgNLgn\u001a,bYV,W#\u0001'\u0011\u0005mi\u0015B\u0001(\u001d\u0005-!u.\u001e2mKB\u000b'/Y7\u0002\u001f\u001d,G/T5tg&twMV1mk\u0016,\u0012!\u0015\t\u0003+IK!a\u0015\f\u0003\r\u0011{WO\u00197f\u000319W\r^%o\u001fV$8i\u001c7t)\u00051\u0006\u0003B\u000bX3fK!\u0001\u0017\f\u0003\rQ+\b\u000f\\33!\r)\",P\u0005\u00037Z\u0011Q!\u0011:sCf\f!D^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$\"A\u00184\u0011\u0005}#W\"\u00011\u000b\u0005\u0005\u0014\u0017!\u0002;za\u0016\u001c(BA2\u000e\u0003\r\u0019\u0018\u000f\\\u0005\u0003K\u0002\u0014!b\u0015;sk\u000e$H+\u001f9f\u0011\u00159w\u00011\u0001_\u0003\u0019\u00198\r[3nC\u0002"
)
public interface ImputerParams extends HasInputCol, HasInputCols, HasOutputCol, HasOutputCols, HasRelativeError {
   void org$apache$spark$ml$feature$ImputerParams$_setter_$strategy_$eq(final Param x$1);

   void org$apache$spark$ml$feature$ImputerParams$_setter_$missingValue_$eq(final DoubleParam x$1);

   Param strategy();

   // $FF: synthetic method
   static String getStrategy$(final ImputerParams $this) {
      return $this.getStrategy();
   }

   default String getStrategy() {
      return (String)this.$(this.strategy());
   }

   DoubleParam missingValue();

   // $FF: synthetic method
   static double getMissingValue$(final ImputerParams $this) {
      return $this.getMissingValue();
   }

   default double getMissingValue() {
      return BoxesRunTime.unboxToDouble(this.$(this.missingValue()));
   }

   // $FF: synthetic method
   static Tuple2 getInOutCols$(final ImputerParams $this) {
      return $this.getInOutCols();
   }

   default Tuple2 getInOutCols() {
      return this.isSet(this.inputCol()) ? new Tuple2((Object[])(new String[]{(String)this.$(this.inputCol())}), (Object[])(new String[]{(String)this.$(this.outputCol())})) : new Tuple2(this.$(this.inputCols()), this.$(this.outputCols()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final ImputerParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new .colon.colon(this.outputCol(), scala.collection.immutable.Nil..MODULE$), new .colon.colon(this.outputCols(), scala.collection.immutable.Nil..MODULE$));
      Tuple2 var4 = this.getInOutCols();
      if (var4 != null) {
         String[] inputColNames = (String[])var4._1();
         String[] outputColNames = (String[])var4._2();
         Tuple2 var3 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var3._1();
         String[] outputColNames = (String[])var3._2();
         scala.Predef..MODULE$.require(inputColNames.length > 0, () -> "inputCols cannot be empty");
         scala.Predef..MODULE$.require(inputColNames.length == ((String[])scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames))).length, () -> {
            ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray((Object[])inputColNames);
            return "inputCols contains duplicates: (" + var10000.mkString(", ") + ")";
         });
         scala.Predef..MODULE$.require(outputColNames.length == ((String[])scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputColNames))).length, () -> {
            ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray((Object[])outputColNames);
            return "outputCols contains duplicates: (" + var10000.mkString(", ") + ")";
         });
         scala.Predef..MODULE$.require(inputColNames.length == outputColNames.length, () -> "inputCols(" + inputColNames.length + ") and outputCols(" + outputColNames.length + ") should have the same length");
         StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames), scala.Predef..MODULE$.wrapRefArray((Object[])outputColNames))), (x0$1) -> {
            if (x0$1 != null) {
               String inputCol = (String)x0$1._1();
               String outputCol = (String)x0$1._2();
               StructField inputField = SchemaUtils$.MODULE$.getSchemaField(schema, inputCol);
               SchemaUtils$.MODULE$.checkNumericType(schema, inputCol, SchemaUtils$.MODULE$.checkNumericType$default$3());
               return new StructField(outputCol, inputField.dataType(), inputField.nullable(), org.apache.spark.sql.types.StructField..MODULE$.apply$default$4());
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return org.apache.spark.sql.types.StructType..MODULE$.apply((Seq)schema.$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])outputFields)));
      } else {
         throw new MatchError(var4);
      }
   }

   static void $init$(final ImputerParams $this) {
      $this.org$apache$spark$ml$feature$ImputerParams$_setter_$strategy_$eq(new Param($this, "strategy", "strategy for imputation. If " + Imputer$.MODULE$.mean() + ", then replace missing values using the mean value of the feature. If " + Imputer$.MODULE$.median() + ", then replace missing values using the median value of the feature. If " + Imputer$.MODULE$.mode() + ", then replace missing values using the most frequent value of the feature.", ParamValidators$.MODULE$.inArray((Object)Imputer$.MODULE$.supportedStrategies()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$ImputerParams$_setter_$missingValue_$eq(new DoubleParam($this, "missingValue", "The placeholder for the missing values. All occurrences of missingValue will be imputed"));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.strategy().$minus$greater(Imputer$.MODULE$.mean()), $this.missingValue().$minus$greater(BoxesRunTime.boxToDouble(Double.NaN))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
