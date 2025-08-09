package org.apache.spark.ml;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.StringContext;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b!\u0002\u0006\f\u0003\u0003!\u0002\"\u0002\u0019\u0001\t\u0003\t\u0004\"\u0002\u001a\u0001\t\u0003\u0019\u0004\"B!\u0001\t\u0003\u0011\u0005\"\u0002#\u0001\t\u0003)\u0005\"\u0002*\u0001\t#\u0019\u0006\"\u0002/\u0001\t\u0003j\u0006\"B2\u0001\t\u0003\"\u0007\"\u0002@\u0001\t#y\bbBA\u0007\u0001\u0019\u0005\u0011q\u0002\u0002\u0010!J,G-[2uS>tWj\u001c3fY*\u0011A\"D\u0001\u0003[2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001+\r)r\u0005H\n\u0004\u0001Yi\u0003cA\f\u001955\t1\"\u0003\u0002\u001a\u0017\t)Qj\u001c3fYB\u00111\u0004\b\u0007\u0001\t\u0015i\u0002A1\u0001\u001f\u0005\u0005i\u0015CA\u0010&!\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004Ba\u0006\u0001'5A\u00111d\n\u0003\u0006Q\u0001\u0011\r!\u000b\u0002\r\r\u0016\fG/\u001e:fgRK\b/Z\t\u0003?)\u0002\"\u0001I\u0016\n\u00051\n#aA!osB\u0011qCL\u0005\u0003_-\u0011q\u0002\u0015:fI&\u001cGo\u001c:QCJ\fWn]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0015\nab]3u\r\u0016\fG/\u001e:fg\u000e{G\u000e\u0006\u0002\u001bi!)QG\u0001a\u0001m\u0005)a/\u00197vKB\u0011qG\u0010\b\u0003qq\u0002\"!O\u0011\u000e\u0003iR!aO\n\u0002\rq\u0012xn\u001c;?\u0013\ti\u0014%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u007f\u0001\u0013aa\u0015;sS:<'BA\u001f\"\u0003A\u0019X\r\u001e)sK\u0012L7\r^5p]\u000e{G\u000e\u0006\u0002\u001b\u0007\")Qg\u0001a\u0001m\u0005Ya.^7GK\u0006$XO]3t+\u00051\u0005C\u0001\u0011H\u0013\tA\u0015EA\u0002J]RD3\u0001\u0002&Q!\tYe*D\u0001M\u0015\tiU\"\u0001\u0006b]:|G/\u0019;j_:L!a\u0014'\u0003\u000bMKgnY3\"\u0003E\u000bQ!\r\u00187]A\n\u0001CZ3biV\u0014Xm\u001d#bi\u0006$\u0016\u0010]3\u0016\u0003Q\u0003\"!\u0016.\u000e\u0003YS!a\u0016-\u0002\u000bQL\b/Z:\u000b\u0005ek\u0011aA:rY&\u00111L\u0016\u0002\t\t\u0006$\u0018\rV=qK\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0002_CB\u0011QkX\u0005\u0003AZ\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015\u0011g\u00011\u0001_\u0003\u0019\u00198\r[3nC\u0006IAO]1og\u001a|'/\u001c\u000b\u0003KR\u0004\"AZ9\u000f\u0005\u001d|gB\u00015o\u001d\tIWN\u0004\u0002kY:\u0011\u0011h[\u0005\u0002%%\u0011\u0001#E\u0005\u0003\u001d=I!!W\u0007\n\u0005AD\u0016a\u00029bG.\fw-Z\u0005\u0003eN\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0005AD\u0006\"B;\b\u0001\u00041\u0018a\u00023bi\u0006\u001cX\r\u001e\u0019\u0003or\u00042\u0001_=|\u001b\u0005A\u0016B\u0001>Y\u0005\u001d!\u0015\r^1tKR\u0004\"a\u0007?\u0005\u0013u$\u0018\u0011!A\u0001\u0006\u0003I#aA0%g\u0005iAO]1og\u001a|'/\\%na2$2!ZA\u0001\u0011\u0019)\b\u00021\u0001\u0002\u0004A\"\u0011QAA\u0005!\u0011A\u00180a\u0002\u0011\u0007m\tI\u0001B\u0006\u0002\f\u0005\u0005\u0011\u0011!A\u0001\u0006\u0003I#aA0%i\u00059\u0001O]3eS\u000e$H\u0003BA\t\u0003/\u00012\u0001IA\n\u0013\r\t)\"\t\u0002\u0007\t>,(\r\\3\t\r\u0005e\u0011\u00021\u0001'\u0003!1W-\u0019;ve\u0016\u001c\b\u0006B\u0005K\u0003;\t#!a\b\u0002\u000bIrCG\f\u0019"
)
public abstract class PredictionModel extends Model implements PredictorParams {
   private Param predictionCol;
   private Param featuresCol;
   private Param labelCol;

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public PredictionModel setFeaturesCol(final String value) {
      return (PredictionModel)this.set(this.featuresCol(), value);
   }

   public PredictionModel setPredictionCol(final String value) {
      return (PredictionModel)this.set(this.predictionCol(), value);
   }

   public int numFeatures() {
      return -1;
   }

   public DataType featuresDataType() {
      return new VectorUDT();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema, false, this.featuresDataType());
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumeric(outputSchema, (String)this.$(this.predictionCol()));
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         return this.transformImpl(dataset);
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": Predictor.transform() does nothing because "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return dataset.toDF();
      }
   }

   public Dataset transformImpl(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      UserDefinedFunction predictUDF = org.apache.spark.sql.functions..MODULE$.udf((features) -> BoxesRunTime.boxToDouble($anonfun$transformImpl$1(this, features)), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any());
      return dataset.withColumn((String)this.$(this.predictionCol()), predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
   }

   public abstract double predict(final Object features);

   // $FF: synthetic method
   public static final double $anonfun$transformImpl$1(final PredictionModel $this, final Object features) {
      return $this.predict(features);
   }

   public PredictionModel() {
      HasLabelCol.$init$(this);
      HasFeaturesCol.$init$(this);
      HasPredictionCol.$init$(this);
      PredictorParams.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
