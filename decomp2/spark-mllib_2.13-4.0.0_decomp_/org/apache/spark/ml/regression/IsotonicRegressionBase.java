package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DoubleType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005!\u0002\u0006\u0005\u0006m\u0001!\t\u0001\u000f\u0005\by\u0001\u0011\r\u0011\"\u0002>\u0011\u0015\t\u0005\u0001\"\u0002C\u0011\u001d1\u0005A1A\u0005\u0006\u001dCQa\u0013\u0001\u0005\u00061Ca\u0001\u0015\u0001\u0005\u0002)\u0011\u0005BB)\u0001\t#a!\u000b\u0003\u0004t\u0001\u0011EA\u0002\u001e\u0002\u0017\u0013N|Go\u001c8jGJ+wM]3tg&|gNQ1tK*\u00111\u0002D\u0001\u000be\u0016<'/Z:tS>t'BA\u0007\u000f\u0003\tiGN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h'!\u0001QcG\u0011(U5\u0002\u0004C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\u0019\u0005)\u0001/\u0019:b[&\u0011\u0001%\b\u0002\u0007!\u0006\u0014\u0018-\\:\u0011\u0005\t*S\"A\u0012\u000b\u0005\u0011j\u0012AB:iCJ,G-\u0003\u0002'G\tq\u0001*Y:GK\u0006$XO]3t\u0007>d\u0007C\u0001\u0012)\u0013\tI3EA\u0006ICNd\u0015MY3m\u0007>d\u0007C\u0001\u0012,\u0013\ta3E\u0001\tICN\u0004&/\u001a3jGRLwN\\\"pYB\u0011!EL\u0005\u0003_\r\u0012A\u0002S1t/\u0016Lw\r\u001b;D_2\u0004\"!\r\u001b\u000e\u0003IR!a\r\b\u0002\u0011%tG/\u001a:oC2L!!\u000e\u001a\u0003\u000f1{wmZ5oO\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001:!\t1\"(\u0003\u0002</\t!QK\\5u\u0003!I7o\u001c;p]&\u001cW#\u0001 \u0011\u0005qy\u0014B\u0001!\u001e\u00051\u0011un\u001c7fC:\u0004\u0016M]1n\u0003-9W\r^%t_R|g.[2\u0016\u0003\r\u0003\"A\u0006#\n\u0005\u0015;\"a\u0002\"p_2,\u0017M\\\u0001\rM\u0016\fG/\u001e:f\u0013:$W\r_\u000b\u0002\u0011B\u0011A$S\u0005\u0003\u0015v\u0011\u0001\"\u00138u!\u0006\u0014\u0018-\\\u0001\u0010O\u0016$h)Z1ukJ,\u0017J\u001c3fqV\tQ\n\u0005\u0002\u0017\u001d&\u0011qj\u0006\u0002\u0004\u0013:$\u0018\u0001\u00045bg^+\u0017n\u001a5u\u0007>d\u0017\u0001H3yiJ\f7\r^,fS\u001eDG/\u001a3MC\n,G.\u001a3Q_&tGo\u001d\u000b\u0003'~\u00032\u0001V,Z\u001b\u0005)&B\u0001,\u000f\u0003\r\u0011H\rZ\u0005\u00031V\u00131A\u0015#E!\u00151\"\f\u0018/]\u0013\tYvC\u0001\u0004UkBdWm\r\t\u0003-uK!AX\f\u0003\r\u0011{WO\u00197f\u0011\u0015\u0001w\u00011\u0001b\u0003\u001d!\u0017\r^1tKR\u0004$A\u00196\u0011\u0007\r4\u0007.D\u0001e\u0015\t)g\"A\u0002tc2L!a\u001a3\u0003\u000f\u0011\u000bG/Y:fiB\u0011\u0011N\u001b\u0007\u0001\t%Yw,!A\u0001\u0002\u000b\u0005ANA\u0002`IE\n\"!\u001c9\u0011\u0005Yq\u0017BA8\u0018\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AF9\n\u0005I<\"aA!os\u0006Qb/\u00197jI\u0006$X-\u00118e)J\fgn\u001d4pe6\u001c6\r[3nCR\u0019Qo_?\u0011\u0005YLX\"A<\u000b\u0005a$\u0017!\u0002;za\u0016\u001c\u0018B\u0001>x\u0005)\u0019FO];diRK\b/\u001a\u0005\u0006y\"\u0001\r!^\u0001\u0007g\u000eDW-\\1\t\u000byD\u0001\u0019A\"\u0002\u000f\u0019LG\u000f^5oO\u0002"
)
public interface IsotonicRegressionBase extends HasFeaturesCol, HasLabelCol, HasPredictionCol, HasWeightCol, Logging {
   void org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$isotonic_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$featureIndex_$eq(final IntParam x$1);

   BooleanParam isotonic();

   // $FF: synthetic method
   static boolean getIsotonic$(final IsotonicRegressionBase $this) {
      return $this.getIsotonic();
   }

   default boolean getIsotonic() {
      return BoxesRunTime.unboxToBoolean(this.$(this.isotonic()));
   }

   IntParam featureIndex();

   // $FF: synthetic method
   static int getFeatureIndex$(final IsotonicRegressionBase $this) {
      return $this.getFeatureIndex();
   }

   default int getFeatureIndex() {
      return BoxesRunTime.unboxToInt(this.$(this.featureIndex()));
   }

   // $FF: synthetic method
   static boolean hasWeightCol$(final IsotonicRegressionBase $this) {
      return $this.hasWeightCol();
   }

   default boolean hasWeightCol() {
      return this.isDefined(this.weightCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.weightCol())));
   }

   // $FF: synthetic method
   static RDD extractWeightedLabeledPoints$(final IsotonicRegressionBase $this, final Dataset dataset) {
      return $this.extractWeightedLabeledPoints(dataset);
   }

   default RDD extractWeightedLabeledPoints(final Dataset dataset) {
      Column l = DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol()));
      Column var9;
      if (dataset.schema().apply((String)this.$(this.featuresCol())).dataType() instanceof VectorUDT) {
         int idx = BoxesRunTime.unboxToInt(this.$(this.featureIndex()));
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (v) -> BoxesRunTime.boxToDouble($anonfun$extractWeightedLabeledPoints$1(idx, v));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IsotonicRegressionBase.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         UserDefinedFunction extract = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
         var9 = extract.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))})));
      } else {
         var9 = DatasetUtils$.MODULE$.checkNonNanValues((String)this.$(this.featuresCol()), "Features");
      }

      Column f = var9;
      Column w = DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()));
      return dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{l, f, w}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object label = ((SeqOps)var3.get()).apply(0);
               Object feature = ((SeqOps)var3.get()).apply(1);
               Object weight = ((SeqOps)var3.get()).apply(2);
               if (label instanceof Double) {
                  double var7 = BoxesRunTime.unboxToDouble(label);
                  if (feature instanceof Double) {
                     double var9 = BoxesRunTime.unboxToDouble(feature);
                     if (weight instanceof Double) {
                        double var11 = BoxesRunTime.unboxToDouble(weight);
                        return new Tuple3(BoxesRunTime.boxToDouble(var7), BoxesRunTime.boxToDouble(var9), BoxesRunTime.boxToDouble(var11));
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final IsotonicRegressionBase $this, final StructType schema, final boolean fitting) {
      return $this.validateAndTransformSchema(schema, fitting);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting) {
      if (fitting) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
         if (this.hasWeightCol()) {
            SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.weightCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
         } else {
            this.logInfo(() -> "The weight column is not defined. Treat all instance weights as 1.0.");
         }
      }

      Predef var10000;
      boolean var10001;
      label25: {
         label24: {
            DataType featuresType = schema.apply((String)this.$(this.featuresCol())).dataType();
            var10000 = scala.Predef..MODULE$;
            DoubleType var4 = org.apache.spark.sql.types.DoubleType..MODULE$;
            if (featuresType == null) {
               if (var4 == null) {
                  break label24;
               }
            } else if (featuresType.equals(var4)) {
               break label24;
            }

            if (!(featuresType instanceof VectorUDT)) {
               var10001 = false;
               break label25;
            }
         }

         var10001 = true;
      }

      var10000.require(var10001);
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.predictionCol()), org.apache.spark.sql.types.DoubleType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   // $FF: synthetic method
   static double $anonfun$extractWeightedLabeledPoints$1(final int idx$1, final Vector v) {
      return v.apply(idx$1);
   }

   static void $init$(final IsotonicRegressionBase $this) {
      $this.org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$isotonic_$eq(new BooleanParam($this, "isotonic", "whether the output sequence should be isotonic/increasing (true) or antitonic/decreasing (false)"));
      $this.org$apache$spark$ml$regression$IsotonicRegressionBase$_setter_$featureIndex_$eq(new IntParam($this, "featureIndex", "The index of the feature if featuresCol is a vector column, no effect otherwise (>= 0)", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.isotonic().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.featureIndex().$minus$greater(BoxesRunTime.boxToInteger(0))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
