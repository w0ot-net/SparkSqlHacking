package org.apache.spark.ml.classification;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.PredictionModel;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.param.shared.HasRawPredictionCol;
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
import scala.collection.StringOps.;
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
   bytes = "\u0006\u0005\u0005]b!B\u0006\r\u0003\u00039\u0002\"\u0002\u001b\u0001\t\u0003)\u0004\"\u0002\u001c\u0001\t\u00039\u0004\"B#\u0001\r\u00031\u0005\"\u0002&\u0001\t\u0003Z\u0005\"\u0002,\u0001\t\u0003:\u0006\"B9\u0001\t\u000b\u0012\b\"B=\u0001\t\u0003R\bbBA\u0001\u0001\u0019\u0005\u00111\u0001\u0005\b\u0003K\u0001A\u0011CA\u0014\u0011!\ti\u0003\u0001C\u0001\u0019\u0005=\"aE\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d'BA\u0007\u000f\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!a\u0004\t\u0002\u00055d'BA\t\u0013\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019B#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002+\u0005\u0019qN]4\u0004\u0001U\u0019\u0001d\b\u0017\u0014\u0007\u0001I\u0012\u0007\u0005\u0003\u001b7uYS\"\u0001\b\n\u0005qq!a\u0004)sK\u0012L7\r^5p]6{G-\u001a7\u0011\u0005yyB\u0002\u0001\u0003\u0006A\u0001\u0011\r!\t\u0002\r\r\u0016\fG/\u001e:fgRK\b/Z\t\u0003E!\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012qAT8uQ&tw\r\u0005\u0002$S%\u0011!\u0006\n\u0002\u0004\u0003:L\bC\u0001\u0010-\t\u0015i\u0003A1\u0001/\u0005\u0005i\u0015C\u0001\u00120!\u0011\u0001\u0004!H\u0016\u000e\u00031\u0001\"\u0001\r\u001a\n\u0005Mb!\u0001E\"mCN\u001c\u0018NZ5feB\u000b'/Y7t\u0003\u0019a\u0014N\\5u}Q\tq&A\ntKR\u0014\u0016m\u001e)sK\u0012L7\r^5p]\u000e{G\u000e\u0006\u0002,q!)\u0011H\u0001a\u0001u\u0005)a/\u00197vKB\u00111H\u0011\b\u0003y\u0001\u0003\"!\u0010\u0013\u000e\u0003yR!a\u0010\f\u0002\rq\u0012xn\u001c;?\u0013\t\tE%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0007\u0012\u0013aa\u0015;sS:<'BA!%\u0003)qW/\\\"mCN\u001cXm]\u000b\u0002\u000fB\u00111\u0005S\u0005\u0003\u0013\u0012\u00121!\u00138u\u0003=!(/\u00198tM>\u0014XnU2iK6\fGC\u0001'U!\ti%+D\u0001O\u0015\ty\u0005+A\u0003usB,7O\u0003\u0002R!\u0005\u00191/\u001d7\n\u0005Ms%AC*ueV\u001cG\u000fV=qK\")Q\u000b\u0002a\u0001\u0019\u000611o\u00195f[\u0006\f\u0011\u0002\u001e:b]N4wN]7\u0015\u0005a;\u0007CA-e\u001d\tQ&M\u0004\u0002\\C:\u0011A\f\u0019\b\u0003;~s!!\u00100\n\u0003UI!a\u0005\u000b\n\u0005E\u0011\u0012BA)\u0011\u0013\t\u0019\u0007+A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00154'!\u0003#bi\u00064%/Y7f\u0015\t\u0019\u0007\u000bC\u0003i\u000b\u0001\u0007\u0011.A\u0004eCR\f7/\u001a;1\u0005)|\u0007cA6m]6\t\u0001+\u0003\u0002n!\n9A)\u0019;bg\u0016$\bC\u0001\u0010p\t%\u0001x-!A\u0001\u0002\u000b\u0005\u0011EA\u0002`II\nQ\u0002\u001e:b]N4wN]7J[BdGC\u0001-t\u0011\u0015Ag\u00011\u0001ua\t)x\u000fE\u0002lYZ\u0004\"AH<\u0005\u0013a\u001c\u0018\u0011!A\u0001\u0006\u0003\t#aA0%g\u00059\u0001O]3eS\u000e$HCA>\u007f!\t\u0019C0\u0003\u0002~I\t1Ai\\;cY\u0016DQa`\u0004A\u0002u\t\u0001BZ3biV\u0014Xm]\u0001\u000baJ,G-[2u%\u0006<H\u0003BA\u0003\u0003#\u0001B!a\u0002\u0002\u000e5\u0011\u0011\u0011\u0002\u0006\u0004\u0003\u0017q\u0011A\u00027j]\u0006dw-\u0003\u0003\u0002\u0010\u0005%!A\u0002,fGR|'\u000fC\u0003\u0000\u0011\u0001\u0007Q\u0004K\u0003\t\u0003+\t\t\u0003\u0005\u0003\u0002\u0018\u0005uQBAA\r\u0015\r\tY\u0002E\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0010\u00033\u0011QaU5oG\u0016\f#!a\t\u0002\u000bMr\u0003G\f\u0019\u0002\u001dI\fwO\r9sK\u0012L7\r^5p]R\u001910!\u000b\t\u000f\u0005-\u0012\u00021\u0001\u0002\u0006\u0005i!/Y<Qe\u0016$\u0017n\u0019;j_:\f\u0001CZ5oIN+X.\\1ss6{G-\u001a7\u0015\u0005\u0005E\u0002CB\u0012\u00024=R$(C\u0002\u00026\u0011\u0012a\u0001V;qY\u0016\u001c\u0004"
)
public abstract class ClassificationModel extends PredictionModel implements ClassifierParams {
   private Param rawPredictionCol;

   // $FF: synthetic method
   public StructType org$apache$spark$ml$classification$ClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getRawPredictionCol() {
      return HasRawPredictionCol.getRawPredictionCol$(this);
   }

   public final Param rawPredictionCol() {
      return this.rawPredictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasRawPredictionCol$_setter_$rawPredictionCol_$eq(final Param x$1) {
      this.rawPredictionCol = x$1;
   }

   public ClassificationModel setRawPredictionCol(final String value) {
      return (ClassificationModel)this.set(this.rawPredictionCol(), value);
   }

   public abstract int numClasses();

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumValues(schema, (String)this.$(this.predictionCol()), this.numClasses());
      }

      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.rawPredictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.rawPredictionCol()), this.numClasses());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema;
      Dataset outputData;
      int numColsOutput;
      label43: {
         outputSchema = this.transformSchema(dataset.schema(), true);
         outputData = dataset;
         numColsOutput = 0;
         String var10000 = this.getRawPredictionCol();
         String var5 = "";
         if (var10000 == null) {
            if (var5 == null) {
               break label43;
            }
         } else if (var10000.equals(var5)) {
            break label43;
         }

         functions var15 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> this.predictRaw(features);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ClassificationModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         UserDefinedFunction predictRawUDF = var15.udf(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any());
         outputData = dataset.withColumn(this.getRawPredictionCol(), predictRawUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(this.getFeaturesCol())}))), outputSchema.apply((String)this.$(this.rawPredictionCol())).metadata());
         ++numColsOutput;
      }

      label46: {
         String var16 = this.getPredictionCol();
         String var9 = "";
         if (var16 == null) {
            if (var9 == null) {
               break label46;
            }
         } else if (var16.equals(var9)) {
            break label46;
         }

         label31: {
            label30: {
               var16 = this.getRawPredictionCol();
               String var11 = "";
               if (var16 == null) {
                  if (var11 != null) {
                     break label30;
                  }
               } else if (!var16.equals(var11)) {
                  break label30;
               }

               UserDefinedFunction predictUDF = org.apache.spark.sql.functions..MODULE$.udf((features) -> BoxesRunTime.boxToDouble($anonfun$transform$3(this, features)), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any());
               var18 = predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(this.getFeaturesCol())})));
               break label31;
            }

            functions var19 = org.apache.spark.sql.functions..MODULE$;
            Function1 var20 = (rawPrediction) -> BoxesRunTime.boxToDouble($anonfun$transform$2(this, rawPrediction));
            TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(ClassificationModel.class.getClassLoader());

            final class $typecreator2$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator2$1() {
               }
            }

            var18 = var19.udf(var20, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(this.getRawPredictionCol())})));
         }

         Column predCol = var18;
         outputData = outputData.withColumn(this.getPredictionCol(), predCol, outputSchema.apply((String)this.$(this.predictionCol())).metadata());
         ++numColsOutput;
      }

      if (numColsOutput == 0) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": ClassificationModel.transform() does nothing "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return outputData.toDF();
   }

   public final Dataset transformImpl(final Dataset dataset) {
      throw new UnsupportedOperationException("transformImpl is not supported in " + this.getClass());
   }

   public double predict(final Object features) {
      return this.raw2prediction(this.predictRaw(features));
   }

   public abstract Vector predictRaw(final Object features);

   public double raw2prediction(final Vector rawPrediction) {
      return (double)rawPrediction.argmax();
   }

   public Tuple3 findSummaryModel() {
      ClassificationModel model = ((String)this.$(this.rawPredictionCol())).isEmpty() && ((String)this.$(this.predictionCol())).isEmpty() ? (ClassificationModel)((ClassificationModel)this.copy(ParamMap$.MODULE$.empty())).setRawPredictionCol("rawPrediction_" + UUID.randomUUID().toString()).setPredictionCol("prediction_" + UUID.randomUUID().toString()) : (((String)this.$(this.rawPredictionCol())).isEmpty() ? ((ClassificationModel)this.copy(ParamMap$.MODULE$.empty())).setRawPredictionCol("rawPrediction_" + UUID.randomUUID().toString()) : (((String)this.$(this.predictionCol())).isEmpty() ? (ClassificationModel)((PredictionModel)this.copy(ParamMap$.MODULE$.empty())).setPredictionCol("prediction_" + UUID.randomUUID().toString()) : this));
      return new Tuple3(model, model.getRawPredictionCol(), model.getPredictionCol());
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$2(final ClassificationModel $this, final Vector rawPrediction) {
      return $this.raw2prediction(rawPrediction);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$3(final ClassificationModel $this, final Object features) {
      return $this.predict(features);
   }

   public ClassificationModel() {
      HasRawPredictionCol.$init$(this);
      ClassifierParams.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
