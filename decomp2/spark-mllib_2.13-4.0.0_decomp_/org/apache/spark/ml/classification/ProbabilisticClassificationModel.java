package org.apache.spark.ml.classification;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.PredictionModel;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasThresholds;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.StringContext;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re!B\t\u0013\u0003\u0003i\u0002\"B\u001d\u0001\t\u0003Q\u0004\"B\u001e\u0001\t\u0003a\u0004\"\u0002&\u0001\t\u0003Y\u0005\"B*\u0001\t\u0003\"\u0006\"B0\u0001\t\u0003\u0002\u0007\"\u0002>\u0001\r#Y\bbBA\u0005\u0001\u0011E\u00111\u0002\u0005\b\u0003\u001f\u0001A\u0011KA\t\u0011\u001d\t)\u0002\u0001C\u0001\u0003/Aq!a\f\u0001\t#\t\t\u0004\u0003\u0005\u00028\u0001!\tEEA\u001d\u000f!\t\tE\u0005E\u0001)\u0005\rcaB\t\u0013\u0011\u0003!\u0012Q\t\u0005\u0007s5!\t!!\u0018\t\u000f\u0005}S\u0002\"\u0001\u0002b!I\u00111O\u0007\u0002\u0002\u0013%\u0011Q\u000f\u0002!!J|'-\u00192jY&\u001cH/[2DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,GN\u0003\u0002\u0014)\u0005q1\r\\1tg&4\u0017nY1uS>t'BA\u000b\u0017\u0003\tiGN\u0003\u0002\u00181\u0005)1\u000f]1sW*\u0011\u0011DG\u0001\u0007CB\f7\r[3\u000b\u0003m\t1a\u001c:h\u0007\u0001)2AH\u00133'\r\u0001qD\u000e\t\u0005A\u0005\u001a\u0013'D\u0001\u0013\u0013\t\u0011#CA\nDY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u00049#\u0001\u0004$fCR,(/Z:UsB,\u0017C\u0001\u0015/!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!K\u0018\n\u0005AR#aA!osB\u0011AE\r\u0003\u0006g\u0001\u0011\r\u0001\u000e\u0002\u0002\u001bF\u0011\u0001&\u000e\t\u0005A\u0001\u0019\u0013\u0007\u0005\u0002!o%\u0011\u0001H\u0005\u0002\u001e!J|'-\u00192jY&\u001cH/[2DY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ng\u00061A(\u001b8jiz\"\u0012!N\u0001\u0012g\u0016$\bK]8cC\nLG.\u001b;z\u0007>dGCA\u0019>\u0011\u0015q$\u00011\u0001@\u0003\u00151\u0018\r\\;f!\t\u0001uI\u0004\u0002B\u000bB\u0011!IK\u0007\u0002\u0007*\u0011A\tH\u0001\u0007yI|w\u000e\u001e \n\u0005\u0019S\u0013A\u0002)sK\u0012,g-\u0003\u0002I\u0013\n11\u000b\u001e:j]\u001eT!A\u0012\u0016\u0002\u001bM,G\u000f\u00165sKNDw\u000e\u001c3t)\t\tD\nC\u0003?\u0007\u0001\u0007Q\nE\u0002*\u001dBK!a\u0014\u0016\u0003\u000b\u0005\u0013(/Y=\u0011\u0005%\n\u0016B\u0001*+\u0005\u0019!u.\u001e2mK\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0002V;B\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\u0006if\u0004Xm\u001d\u0006\u00035Z\t1a]9m\u0013\tavK\u0001\u0006TiJ,8\r\u001e+za\u0016DQA\u0018\u0003A\u0002U\u000baa]2iK6\f\u0017!\u0003;sC:\u001chm\u001c:n)\t\t\u0007\u000f\u0005\u0002c[:\u00111m\u001b\b\u0003I*t!!Z5\u000f\u0005\u0019DgB\u0001\"h\u0013\u0005Y\u0012BA\r\u001b\u0013\t9\u0002$\u0003\u0002[-%\u0011A.W\u0001\ba\u0006\u001c7.Y4f\u0013\tqwNA\u0005ECR\fgI]1nK*\u0011A.\u0017\u0005\u0006c\u0016\u0001\rA]\u0001\bI\u0006$\u0018m]3ua\t\u0019\b\u0010E\u0002uk^l\u0011!W\u0005\u0003mf\u0013q\u0001R1uCN,G\u000f\u0005\u0002%q\u0012I\u0011\u0010]A\u0001\u0002\u0003\u0015\ta\n\u0002\u0004?\u0012\n\u0014A\u0006:boJ\u0002(o\u001c2bE&d\u0017\u000e^=J]Bc\u0017mY3\u0015\u0007q\f)\u0001E\u0002~\u0003\u0003i\u0011A \u0006\u0003\u007fR\ta\u0001\\5oC2<\u0017bAA\u0002}\n1a+Z2u_JDa!a\u0002\u0007\u0001\u0004a\u0018!\u0004:boB\u0013X\rZ5di&|g.A\bsC^\u0014\u0004O]8cC\nLG.\u001b;z)\ra\u0018Q\u0002\u0005\u0007\u0003\u000f9\u0001\u0019\u0001?\u0002\u001dI\fwO\r9sK\u0012L7\r^5p]R\u0019\u0001+a\u0005\t\r\u0005\u001d\u0001\u00021\u0001}\u0003I\u0001(/\u001a3jGR\u0004&o\u001c2bE&d\u0017\u000e^=\u0015\u0007q\fI\u0002\u0003\u0004\u0002\u001c%\u0001\raI\u0001\tM\u0016\fG/\u001e:fg\"*\u0011\"a\b\u0002,A!\u0011\u0011EA\u0014\u001b\t\t\u0019CC\u0002\u0002&Y\t!\"\u00198o_R\fG/[8o\u0013\u0011\tI#a\t\u0003\u000bMKgnY3\"\u0005\u00055\u0012!B\u001a/a9\u0002\u0014A\u00069s_\n\f'-\u001b7jif\u0014\u0004O]3eS\u000e$\u0018n\u001c8\u0015\u0007A\u000b\u0019\u0004\u0003\u0004\u00026)\u0001\r\u0001`\u0001\faJ|'-\u00192jY&$\u00180\u0001\tgS:$7+^7nCJLXj\u001c3fYR\u0011\u00111\b\t\u0007S\u0005uRgP \n\u0007\u0005}\"F\u0001\u0004UkBdWmM\u0001!!J|'-\u00192jY&\u001cH/[2DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002!\u001bM)Q\"a\u0012\u0002NA\u0019\u0011&!\u0013\n\u0007\u0005-#F\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003\u001f\nI&\u0004\u0002\u0002R)!\u00111KA+\u0003\tIwN\u0003\u0002\u0002X\u0005!!.\u0019<b\u0013\u0011\tY&!\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\r\u0013a\b8pe6\fG.\u001b>f)>\u0004&o\u001c2bE&d\u0017\u000e^5fg&s\u0007\u000b\\1dKR!\u00111MA5!\rI\u0013QM\u0005\u0004\u0003OR#\u0001B+oSRDq!a\u001b\u0010\u0001\u0004\ti'A\u0001w!\ri\u0018qN\u0005\u0004\u0003cr(a\u0003#f]N,g+Z2u_J\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u001e\u0011\t\u0005e\u0014qP\u0007\u0003\u0003wRA!! \u0002V\u0005!A.\u00198h\u0013\u0011\t\t)a\u001f\u0003\r=\u0013'.Z2u\u0001"
)
public abstract class ProbabilisticClassificationModel extends ClassificationModel implements ProbabilisticClassifierParams {
   private DoubleArrayParam thresholds;
   private Param probabilityCol;

   public static void normalizeToProbabilitiesInPlace(final DenseVector v) {
      ProbabilisticClassificationModel$.MODULE$.normalizeToProbabilitiesInPlace(v);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$classification$ProbabilisticClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public double[] getThresholds() {
      return HasThresholds.getThresholds$(this);
   }

   public final String getProbabilityCol() {
      return HasProbabilityCol.getProbabilityCol$(this);
   }

   public DoubleArrayParam thresholds() {
      return this.thresholds;
   }

   public void org$apache$spark$ml$param$shared$HasThresholds$_setter_$thresholds_$eq(final DoubleArrayParam x$1) {
      this.thresholds = x$1;
   }

   public final Param probabilityCol() {
      return this.probabilityCol;
   }

   public final void org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(final Param x$1) {
      this.probabilityCol = x$1;
   }

   public ProbabilisticClassificationModel setProbabilityCol(final String value) {
      return (ProbabilisticClassificationModel)this.set(this.probabilityCol(), value);
   }

   public ProbabilisticClassificationModel setThresholds(final double[] value) {
      .MODULE$.require(value.length == this.numClasses(), () -> {
         String var10000 = this.getClass().getSimpleName();
         return var10000 + ".setThresholds() called with non-matching numClasses and thresholds.length. numClasses=" + this.numClasses() + ", but thresholds has length " + value.length;
      });
      return (ProbabilisticClassificationModel)this.set(this.thresholds(), value);
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.probabilityCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.probabilityCol()), this.numClasses());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      if (this.isDefined(this.thresholds())) {
         .MODULE$.require(((double[])this.$(this.thresholds())).length == this.numClasses(), () -> {
            String var10000 = this.getClass().getSimpleName();
            return var10000 + ".transform() called with non-matching numClasses and thresholds.length. numClasses=" + this.numClasses() + ", but thresholds has length " + ((double[])this.$(this.thresholds())).length;
         });
      }

      Dataset outputData = dataset;
      int numColsOutput = 0;
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.rawPredictionCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> this.predictRaw(features);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ProbabilisticClassificationModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         UserDefinedFunction predictRawUDF = var10000.udf(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any());
         outputData = dataset.withColumn(this.getRawPredictionCol(), predictRawUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(this.getFeaturesCol())}))), outputSchema.apply((String)this.$(this.rawPredictionCol())).metadata());
         ++numColsOutput;
      }

      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.probabilityCol())))) {
         Column var23;
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.rawPredictionCol())))) {
            functions var22 = org.apache.spark.sql.functions..MODULE$;
            Function1 var28 = (rawPrediction) -> this.raw2probability(rawPrediction);
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ProbabilisticClassificationModel.class.getClassLoader());

            final class $typecreator2$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator2$1() {
               }
            }

            TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1());
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ProbabilisticClassificationModel.class.getClassLoader());

            final class $typecreator3$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator3$1() {
               }
            }

            var23 = var22.udf(var28, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.rawPredictionCol()))})));
         } else {
            functions var24 = org.apache.spark.sql.functions..MODULE$;
            Function1 var29 = (features) -> this.predictProbability(features);
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ProbabilisticClassificationModel.class.getClassLoader());

            final class $typecreator4$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator4$1() {
               }
            }

            UserDefinedFunction probabilityUDF = var24.udf(var29, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any());
            var23 = probabilityUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))})));
         }

         Column probCol = var23;
         outputData = outputData.withColumn((String)this.$(this.probabilityCol()), probCol, outputSchema.apply((String)this.$(this.probabilityCol())).metadata());
         ++numColsOutput;
      }

      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         Column var26;
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.rawPredictionCol())))) {
            functions var25 = org.apache.spark.sql.functions..MODULE$;
            Function1 var30 = (rawPrediction) -> BoxesRunTime.boxToDouble($anonfun$transform$5(this, rawPrediction));
            TypeTags.TypeTag var32 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ProbabilisticClassificationModel.class.getClassLoader());

            final class $typecreator5$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator5$1() {
               }
            }

            var26 = var25.udf(var30, var32, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.rawPredictionCol()))})));
         } else if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString((String)this.$(this.probabilityCol())))) {
            functions var27 = org.apache.spark.sql.functions..MODULE$;
            Function1 var31 = (probability) -> BoxesRunTime.boxToDouble($anonfun$transform$6(this, probability));
            TypeTags.TypeTag var33 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ProbabilisticClassificationModel.class.getClassLoader());

            final class $typecreator6$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator6$1() {
               }
            }

            var26 = var27.udf(var31, var33, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator6$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.probabilityCol()))})));
         } else {
            UserDefinedFunction predictUDF = org.apache.spark.sql.functions..MODULE$.udf((features) -> BoxesRunTime.boxToDouble($anonfun$transform$7(this, features)), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any());
            var26 = predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))})));
         }

         Column predCol = var26;
         outputData = outputData.withColumn((String)this.$(this.predictionCol()), predCol, outputSchema.apply((String)this.$(this.predictionCol())).metadata());
         ++numColsOutput;
      }

      if (numColsOutput == 0) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": ProbabilisticClassificationModel.transform()"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" does nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return outputData.toDF();
   }

   public abstract Vector raw2probabilityInPlace(final Vector rawPrediction);

   public Vector raw2probability(final Vector rawPrediction) {
      Vector probs = rawPrediction.copy();
      return this.raw2probabilityInPlace(probs);
   }

   public double raw2prediction(final Vector rawPrediction) {
      return !this.isDefined(this.thresholds()) ? (double)rawPrediction.argmax() : this.probability2prediction(this.raw2probability(rawPrediction));
   }

   public Vector predictProbability(final Object features) {
      Vector rawPreds = this.predictRaw(features);
      return this.raw2probabilityInPlace(rawPreds);
   }

   public double probability2prediction(final Vector probability) {
      if (!this.isDefined(this.thresholds())) {
         return (double)probability.argmax();
      } else {
         double[] thresholds = this.getThresholds();
         int argMax = 0;
         double max = Double.NEGATIVE_INFINITY;
         int i = 0;

         for(int probabilitySize = probability.size(); i < probabilitySize; ++i) {
            double scaled = probability.apply(i) / thresholds[i];
            if (scaled > max) {
               max = scaled;
               argMax = i;
            }
         }

         return (double)argMax;
      }
   }

   public Tuple3 findSummaryModel() {
      ProbabilisticClassificationModel model = ((String)this.$(this.probabilityCol())).isEmpty() && ((String)this.$(this.predictionCol())).isEmpty() ? (ProbabilisticClassificationModel)((ProbabilisticClassificationModel)this.copy(ParamMap$.MODULE$.empty())).setProbabilityCol("probability_" + UUID.randomUUID().toString()).setPredictionCol("prediction_" + UUID.randomUUID().toString()) : (((String)this.$(this.probabilityCol())).isEmpty() ? ((ProbabilisticClassificationModel)this.copy(ParamMap$.MODULE$.empty())).setProbabilityCol("probability_" + UUID.randomUUID().toString()) : (((String)this.$(this.predictionCol())).isEmpty() ? (ProbabilisticClassificationModel)((PredictionModel)this.copy(ParamMap$.MODULE$.empty())).setPredictionCol("prediction_" + UUID.randomUUID().toString()) : this));
      return new Tuple3(model, model.getProbabilityCol(), model.getPredictionCol());
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$5(final ProbabilisticClassificationModel $this, final Vector rawPrediction) {
      return $this.raw2prediction(rawPrediction);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$6(final ProbabilisticClassificationModel $this, final Vector probability) {
      return $this.probability2prediction(probability);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$7(final ProbabilisticClassificationModel $this, final Object features) {
      return $this.predict(features);
   }

   public ProbabilisticClassificationModel() {
      HasProbabilityCol.$init$(this);
      HasThresholds.$init$(this);
      ProbabilisticClassifierParams.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
