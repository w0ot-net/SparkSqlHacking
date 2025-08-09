package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001C\u0005\u000b!\u0003\r\tA\u0003\u000b\t\u000bM\u0002A\u0011A\u001b\t\u000fe\u0002!\u0019!C!u!9!\u000b\u0001b\u0001\n\u000bQ\u0004\"\u0002,\u0001\t\u00039\u0006BB-\u0001\t\u0003Q!\fC\u0003b\u0001\u0011%!\rC\u0003z\u0001\u0011E!\u0010C\u0005\u0002\u0004\u0001\t\n\u0011\"\u0005\u0002\u0006\t\t2\u000b\u001e:j]\u001eLe\u000eZ3yKJ\u0014\u0015m]3\u000b\u0005-a\u0011a\u00024fCR,(/\u001a\u0006\u0003\u001b9\t!!\u001c7\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0002\u0002A\u000b\u001cC\u001dRS\u0006\r\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005qyR\"A\u000f\u000b\u0005ya\u0011!\u00029be\u0006l\u0017B\u0001\u0011\u001e\u0005\u0019\u0001\u0016M]1ngB\u0011!%J\u0007\u0002G)\u0011A%H\u0001\u0007g\"\f'/\u001a3\n\u0005\u0019\u001a#\u0001\u0005%bg\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5e!\t\u0011\u0003&\u0003\u0002*G\tY\u0001*Y:J]B,HoQ8m!\t\u00113&\u0003\u0002-G\ta\u0001*Y:PkR\u0004X\u000f^\"pYB\u0011!EL\u0005\u0003_\r\u0012A\u0002S1t\u0013:\u0004X\u000f^\"pYN\u0004\"AI\u0019\n\u0005I\u001a#!\u0004%bg>+H\u000f];u\u0007>d7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u00051\u0004C\u0001\f8\u0013\tAtC\u0001\u0003V]&$\u0018!\u00045b]\u0012dW-\u00138wC2LG-F\u0001<!\raBHP\u0005\u0003{u\u0011Q\u0001U1sC6\u0004\"a\u0010$\u000f\u0005\u0001#\u0005CA!\u0018\u001b\u0005\u0011%BA\"5\u0003\u0019a$o\\8u}%\u0011QiF\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%AB*ue&twM\u0003\u0002F/!\u001a!A\u0013)\u0011\u0005-sU\"\u0001'\u000b\u00055s\u0011AC1o]>$\u0018\r^5p]&\u0011q\n\u0014\u0002\u0006'&t7-Z\u0011\u0002#\u0006)\u0011G\f\u001c/a\u0005y1\u000f\u001e:j]\u001e|%\u000fZ3s)f\u0004X\rK\u0002\u0004\u0015R\u000b\u0013!V\u0001\u0006e9\u001ad\u0006M\u0001\u0013O\u0016$8\u000b\u001e:j]\u001e|%\u000fZ3s)f\u0004X-F\u0001?Q\r!!\nV\u0001\rO\u0016$\u0018J\\(vi\u000e{Gn\u001d\u000b\u00027B!a\u0003\u00180_\u0013\tivC\u0001\u0004UkBdWM\r\t\u0004-}s\u0014B\u00011\u0018\u0005\u0015\t%O]1z\u0003e1\u0018\r\\5eCR,\u0017I\u001c3Ue\u0006t7OZ8s[\u001aKW\r\u001c3\u0015\u000b\r\\\u0007O]<\u0011\u0005\u0011LW\"A3\u000b\u0005\u0019<\u0017!\u0002;za\u0016\u001c(B\u00015\u000f\u0003\r\u0019\u0018\u000f\\\u0005\u0003U\u0016\u00141b\u0015;sk\u000e$h)[3mI\")AN\u0002a\u0001[\u000611o\u00195f[\u0006\u0004\"\u0001\u001a8\n\u0005=,'AC*ueV\u001cG\u000fV=qK\")\u0011O\u0002a\u0001}\u0005a\u0011N\u001c9vi\u000e{GNT1nK\")1O\u0002a\u0001i\u0006i\u0011N\u001c9vi\u0012\u000bG/\u0019+za\u0016\u0004\"\u0001Z;\n\u0005Y,'\u0001\u0003#bi\u0006$\u0016\u0010]3\t\u000ba4\u0001\u0019\u0001 \u0002\u001b=,H\u000f];u\u0007>dg*Y7f\u0003i1\u0018\r\\5eCR,\u0017I\u001c3Ue\u0006t7OZ8s[N\u001b\u0007.Z7b)\ri7\u0010 \u0005\u0006Y\u001e\u0001\r!\u001c\u0005\b{\u001e\u0001\n\u00111\u0001\u007f\u0003A\u00198.\u001b9O_:,\u00050[:ug\u000e{G\u000e\u0005\u0002\u0017\u007f&\u0019\u0011\u0011A\f\u0003\u000f\t{w\u000e\\3b]\u0006!c/\u00197jI\u0006$X-\u00118e)J\fgn\u001d4pe6\u001c6\r[3nC\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\b)\u001aa0!\u0003,\u0005\u0005-\u0001\u0003BA\u0007\u0003+i!!a\u0004\u000b\t\u0005E\u00111C\u0001\nk:\u001c\u0007.Z2lK\u0012T!!T\f\n\t\u0005]\u0011q\u0002\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public interface StringIndexerBase extends HasHandleInvalid, HasInputCol, HasOutputCol, HasInputCols, HasOutputCols {
   void org$apache$spark$ml$feature$StringIndexerBase$_setter_$handleInvalid_$eq(final Param x$1);

   void org$apache$spark$ml$feature$StringIndexerBase$_setter_$stringOrderType_$eq(final Param x$1);

   Param handleInvalid();

   Param stringOrderType();

   // $FF: synthetic method
   static String getStringOrderType$(final StringIndexerBase $this) {
      return $this.getStringOrderType();
   }

   default String getStringOrderType() {
      return (String)this.$(this.stringOrderType());
   }

   // $FF: synthetic method
   static Tuple2 getInOutCols$(final StringIndexerBase $this) {
      return $this.getInOutCols();
   }

   default Tuple2 getInOutCols() {
      ParamValidators$.MODULE$.checkSingleVsMultiColumnParams(this, new .colon.colon(this.outputCol(), scala.collection.immutable.Nil..MODULE$), new .colon.colon(this.outputCols(), scala.collection.immutable.Nil..MODULE$));
      if (this.isSet(this.inputCol())) {
         return new Tuple2((Object[])(new String[]{(String)this.$(this.inputCol())}), (Object[])(new String[]{(String)this.$(this.outputCol())}));
      } else {
         scala.Predef..MODULE$.require(((String[])this.$(this.inputCols())).length == ((String[])this.$(this.outputCols())).length, () -> "The number of input columns does not match output columns");
         return new Tuple2(this.$(this.inputCols()), this.$(this.outputCols()));
      }
   }

   private StructField validateAndTransformField(final StructType schema, final String inputColName, final DataType inputDataType, final String outputColName) {
      Predef var10000;
      boolean var10001;
      label19: {
         label18: {
            var10000 = scala.Predef..MODULE$;
            StringType var5 = org.apache.spark.sql.types.StringType..MODULE$;
            if (inputDataType == null) {
               if (var5 == null) {
                  break label18;
               }
            } else if (inputDataType.equals(var5)) {
               break label18;
            }

            if (!(inputDataType instanceof NumericType)) {
               var10001 = false;
               break label19;
            }
         }

         var10001 = true;
      }

      var10000.require(var10001, () -> "The input column " + inputColName + " must be either string type or numeric type, but got " + inputDataType + ".");
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$validateAndTransformField$2(outputColName, x$1))), () -> "Output column " + outputColName + " already exists.");
      return NominalAttribute$.MODULE$.defaultAttr().withName(outputColName).toStructField();
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final StringIndexerBase $this, final StructType schema, final boolean skipNonExistsCol) {
      return $this.validateAndTransformSchema(schema, skipNonExistsCol);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean skipNonExistsCol) {
      Tuple2 var5 = this.getInOutCols();
      if (var5 != null) {
         String[] inputColNames = (String[])var5._1();
         String[] outputColNames = (String[])var5._2();
         Tuple2 var4 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var4._1();
         String[] outputColNames = (String[])var4._2();
         scala.Predef..MODULE$.require(((String[])scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputColNames))).length == outputColNames.length, () -> "Output columns should not be duplicate.");
         StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames), scala.Predef..MODULE$.wrapRefArray((Object[])outputColNames))), (x0$1) -> {
            if (x0$1 != null) {
               String inputColName = (String)x0$1._1();
               String outputColName = (String)x0$1._2();

               Object var15;
               try {
                  DataType dtype = SchemaUtils$.MODULE$.getSchemaFieldType(schema, inputColName);
                  var15 = new Some(this.validateAndTransformField(schema, inputColName, dtype, outputColName));
               } catch (Throwable var14) {
                  if (var14 instanceof SparkIllegalArgumentException) {
                     SparkIllegalArgumentException var12 = (SparkIllegalArgumentException)var14;
                     String var10000 = var12.getCondition();
                     String var13 = "FIELD_NOT_FOUND";
                     if (var10000 == null) {
                        if (var13 != null) {
                           throw var14;
                        }
                     } else if (!var10000.equals(var13)) {
                        throw var14;
                     }

                     if (!skipNonExistsCol) {
                        throw new SparkException("Input column " + inputColName + " does not exist.");
                     }

                     var15 = scala.None..MODULE$;
                     return (Option)var15;
                  }

                  throw var14;
               }

               return (Option)var15;
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType((StructField[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), outputFields, scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static boolean validateAndTransformSchema$default$2$(final StringIndexerBase $this) {
      return $this.validateAndTransformSchema$default$2();
   }

   default boolean validateAndTransformSchema$default$2() {
      return false;
   }

   // $FF: synthetic method
   static boolean $anonfun$validateAndTransformField$2(final String outputColName$1, final StructField x$1) {
      boolean var3;
      label23: {
         String var10000 = x$1.name();
         if (var10000 == null) {
            if (outputColName$1 != null) {
               break label23;
            }
         } else if (!var10000.equals(outputColName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   static void $init$(final StringIndexerBase $this) {
      $this.org$apache$spark$ml$feature$StringIndexerBase$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "How to handle invalid data (unseen labels or NULL values). Options are 'skip' (filter out rows with invalid data), error (throw an error), or 'keep' (put invalid data in a special additional bucket, at index numLabels).", ParamValidators$.MODULE$.inArray((Object)StringIndexer$.MODULE$.supportedHandleInvalids()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$StringIndexerBase$_setter_$stringOrderType_$eq(new Param($this, "stringOrderType", "How to order labels of string column. The first label after ordering is assigned an index of 0. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])StringIndexer$.MODULE$.supportedStringOrderType()).mkString(", ") + ".", ParamValidators$.MODULE$.inArray((Object)StringIndexer$.MODULE$.supportedStringOrderType()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.handleInvalid().$minus$greater(StringIndexer$.MODULE$.ERROR_INVALID()), $this.stringOrderType().$minus$greater(StringIndexer$.MODULE$.frequencyDesc())}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
