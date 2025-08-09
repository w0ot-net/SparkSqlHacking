package org.apache.spark.ml.evaluation;

import java.io.IOException;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg\u0001\u0002\u000e\u001c\u0001\u0019B\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0005\u0011\u0005\t/\u0002\u0011\t\u0011)A\u0005\u0003\")\u0011\f\u0001C\u00015\")\u0011\f\u0001C\u0001?\")\u0011\r\u0001C!E\")!\u000e\u0001C!W\")\u0011\u000f\u0001C\u0001e\")q\u000f\u0001C\u0001q\")1\u0010\u0001C\u0001y\"I\u00111\u0001\u0001C\u0002\u0013\u0005\u0011Q\u0001\u0005\t\u0003\u001f\u0001\u0001\u0015!\u0003\u0002\b!1\u00111\u0003\u0001\u0005\u0002\u0001Cq!a\u0006\u0001\t\u0003\tI\u0002C\u0005\u0002 \u0001\u0011\r\u0011\"\u0001\u0002\u0006!A\u0011q\u0005\u0001!\u0002\u0013\t9\u0001\u0003\u0004\u0002,\u0001!\t\u0001\u0011\u0005\b\u0003_\u0001A\u0011AA\u0019\u0011\u001d\t9\u0004\u0001C!\u0003sAq!a\u001b\u0001\t\u0003\ti\u0007C\u0004\u0002\u0004\u0002!\t%!\"\b\u000f\u0005=5\u0004#\u0001\u0002\u0012\u001a1!d\u0007E\u0001\u0003'Ca!\u0017\f\u0005\u0002\u0005E\u0006bBAZ-\u0011\u0005\u0013Q\u0017\u0005\n\u0003{3\u0012\u0011!C\u0005\u0003\u007f\u00131c\u00117vgR,'/\u001b8h\u000bZ\fG.^1u_JT!\u0001H\u000f\u0002\u0015\u00154\u0018\r\\;bi&|gN\u0003\u0002\u001f?\u0005\u0011Q\u000e\u001c\u0006\u0003A\u0005\nQa\u001d9be.T!AI\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0013aA8sO\u000e\u00011C\u0002\u0001(WM2\u0014\b\u0005\u0002)S5\t1$\u0003\u0002+7\tIQI^1mk\u0006$xN\u001d\t\u0003YEj\u0011!\f\u0006\u0003]=\naa\u001d5be\u0016$'B\u0001\u0019\u001e\u0003\u0015\u0001\u0018M]1n\u0013\t\u0011TF\u0001\tICN\u0004&/\u001a3jGRLwN\\\"pYB\u0011A\u0006N\u0005\u0003k5\u0012a\u0002S1t\r\u0016\fG/\u001e:fg\u000e{G\u000e\u0005\u0002-o%\u0011\u0001(\f\u0002\r\u0011\u0006\u001cx+Z5hQR\u001cu\u000e\u001c\t\u0003uuj\u0011a\u000f\u0006\u0003yu\tA!\u001e;jY&\u0011ah\u000f\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002\u0003B\u0011!i\u0013\b\u0003\u0007&\u0003\"\u0001R$\u000e\u0003\u0015S!AR\u0013\u0002\rq\u0012xn\u001c;?\u0015\u0005A\u0015!B:dC2\f\u0017B\u0001&H\u0003\u0019\u0001&/\u001a3fM&\u0011A*\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005);\u0005fA\u0001P+B\u0011\u0001kU\u0007\u0002#*\u0011!kH\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001+R\u0005\u0015\u0019\u0016N\\2fC\u00051\u0016!\u0002\u001a/g9\u0002\u0014\u0001B;jI\u0002B3AA(V\u0003\u0019a\u0014N\\5u}Q\u00111\f\u0018\t\u0003Q\u0001AQaP\u0002A\u0002\u0005C3\u0001X(VQ\r\u0019q*\u0016\u000b\u00027\"\u001aAaT+\u0002\t\r|\u0007/\u001f\u000b\u00037\u000eDQ\u0001Z\u0003A\u0002\u0015\fA\u0001]'baB\u0011amZ\u0007\u0002_%\u0011\u0001n\f\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aQaT+\u0002\u001d%\u001cH*\u0019:hKJ\u0014U\r\u001e;feV\tA\u000e\u0005\u0002n]6\tq)\u0003\u0002p\u000f\n9!i\\8mK\u0006t\u0007f\u0001\u0004P+\u0006\u00012/\u001a;Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\u000b\u0003gRl\u0011\u0001\u0001\u0005\u0006k\u001e\u0001\r!Q\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u000f=+\u0016AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0003gfDQ!\u001e\u0005A\u0002\u0005C3\u0001C(V\u00031\u0019X\r^,fS\u001eDGoQ8m)\t\u0019X\u0010C\u0003v\u0013\u0001\u0007\u0011\tK\u0002\n\u001f~\f#!!\u0001\u0002\u000bMr\u0013G\f\u0019\u0002\u00155,GO]5d\u001d\u0006lW-\u0006\u0002\u0002\bA!a-!\u0003B\u0013\r\tYa\f\u0002\u0006!\u0006\u0014\u0018-\u001c\u0015\u0004\u0015=+\u0016aC7fiJL7MT1nK\u0002B3aC(V\u000359W\r^'fiJL7MT1nK\"\u001aAbT+\u0002\u001bM,G/T3ue&\u001cg*Y7f)\r\u0019\u00181\u0004\u0005\u0006k6\u0001\r!\u0011\u0015\u0004\u001b=+\u0016a\u00043jgR\fgnY3NK\u0006\u001cXO]3)\t9y\u00151E\u0011\u0003\u0003K\tQA\r\u00185]A\n\u0001\u0003Z5ti\u0006t7-Z'fCN,(/\u001a\u0011)\t=y\u00151E\u0001\u0013O\u0016$H)[:uC:\u001cW-T3bgV\u0014X\r\u000b\u0003\u0011\u001f\u0006\r\u0012AE:fi\u0012K7\u000f^1oG\u0016lU-Y:ve\u0016$2a]A\u001a\u0011\u0015)\u0018\u00031\u0001BQ\u0011\tr*a\t\u0002\u0011\u00154\u0018\r\\;bi\u0016$B!a\u000f\u0002BA\u0019Q.!\u0010\n\u0007\u0005}rI\u0001\u0004E_V\u0014G.\u001a\u0005\b\u0003\u0007\u0012\u0002\u0019AA#\u0003\u001d!\u0017\r^1tKR\u0004D!a\u0012\u0002XA1\u0011\u0011JA(\u0003'j!!a\u0013\u000b\u0007\u00055s$A\u0002tc2LA!!\u0015\u0002L\t9A)\u0019;bg\u0016$\b\u0003BA+\u0003/b\u0001\u0001\u0002\u0007\u0002Z\u0005\u0005\u0013\u0011!A\u0001\u0006\u0003\tYFA\u0002`IE\nB!!\u0018\u0002dA\u0019Q.a\u0018\n\u0007\u0005\u0005tIA\u0004O_RD\u0017N\\4\u0011\u00075\f)'C\u0002\u0002h\u001d\u00131!\u00118zQ\r\u0011r*V\u0001\u000bO\u0016$X*\u001a;sS\u000e\u001cH\u0003BA8\u0003k\u00022\u0001KA9\u0013\r\t\u0019h\u0007\u0002\u0012\u00072,8\u000f^3sS:<W*\u001a;sS\u000e\u001c\bbBA\"'\u0001\u0007\u0011q\u000f\u0019\u0005\u0003s\ni\b\u0005\u0004\u0002J\u0005=\u00131\u0010\t\u0005\u0003+\ni\b\u0002\u0007\u0002\u0000\u0005U\u0014\u0011!A\u0001\u0006\u0003\tYFA\u0002`IIB3aE(\u0000\u0003!!xn\u0015;sS:<G#A!)\tQy\u0015\u0011R\u0011\u0003\u0003\u0017\u000bQa\r\u00181]AB3\u0001A(V\u0003M\u0019E.^:uKJLgnZ#wC2,\u0018\r^8s!\tAccE\u0004\u0017\u0003+\u000bY*!)\u0011\u00075\f9*C\u0002\u0002\u001a\u001e\u0013a!\u00118z%\u00164\u0007\u0003\u0002\u001e\u0002\u001enK1!a(<\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!a)\u0002.6\u0011\u0011Q\u0015\u0006\u0005\u0003O\u000bI+\u0001\u0002j_*\u0011\u00111V\u0001\u0005U\u00064\u0018-\u0003\u0003\u00020\u0006\u0015&\u0001D*fe&\fG.\u001b>bE2,GCAAI\u0003\u0011aw.\u00193\u0015\u0007m\u000b9\f\u0003\u0004\u0002:b\u0001\r!Q\u0001\u0005a\u0006$\b\u000eK\u0002\u0019\u001fV\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!1\u0011\t\u0005\r\u0017\u0011Z\u0007\u0003\u0003\u000bTA!a2\u0002*\u0006!A.\u00198h\u0013\u0011\tY-!2\u0003\r=\u0013'.Z2uQ\r1r*\u0016\u0015\u0004+=+\u0006"
)
public class ClusteringEvaluator extends Evaluator implements HasPredictionCol, HasFeaturesCol, HasWeightCol, DefaultParamsWritable {
   private final String uid;
   private final Param metricName;
   private final Param distanceMeasure;
   private Param weightCol;
   private Param featuresCol;
   private Param predictionCol;

   public static ClusteringEvaluator load(final String path) {
      return ClusteringEvaluator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return ClusteringEvaluator$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public ClusteringEvaluator copy(final ParamMap pMap) {
      return (ClusteringEvaluator)this.defaultCopy(pMap);
   }

   public boolean isLargerBetter() {
      return true;
   }

   public ClusteringEvaluator setPredictionCol(final String value) {
      return (ClusteringEvaluator)this.set(this.predictionCol(), value);
   }

   public ClusteringEvaluator setFeaturesCol(final String value) {
      return (ClusteringEvaluator)this.set(this.featuresCol(), value);
   }

   public ClusteringEvaluator setWeightCol(final String value) {
      return (ClusteringEvaluator)this.set(this.weightCol(), value);
   }

   public Param metricName() {
      return this.metricName;
   }

   public String getMetricName() {
      return (String)this.$(this.metricName());
   }

   public ClusteringEvaluator setMetricName(final String value) {
      return (ClusteringEvaluator)this.set(this.metricName(), value);
   }

   public Param distanceMeasure() {
      return this.distanceMeasure;
   }

   public String getDistanceMeasure() {
      return (String)this.$(this.distanceMeasure());
   }

   public ClusteringEvaluator setDistanceMeasure(final String value) {
      return (ClusteringEvaluator)this.set(this.distanceMeasure(), value);
   }

   public double evaluate(final Dataset dataset) {
      ClusteringMetrics metrics = this.getMetrics(dataset);
      String var5 = (String)this.$(this.metricName());
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -1583304936:
            if ("silhouette".equals(var5)) {
               return metrics.silhouette();
            }
         default:
            throw new IllegalArgumentException("No support for metric " + var5);
      }
   }

   public ClusteringMetrics getMetrics(final Dataset dataset) {
      StructType schema = dataset.schema();
      SchemaUtils$.MODULE$.validateVectorCompatibleColumn(schema, (String)this.$(this.featuresCol()));
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.predictionCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      if (this.isDefined(this.weightCol())) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.weightCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      }

      Dataset df = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.predictionCol())), DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol())).as((String)this.$(this.featuresCol()), dataset.schema().apply((String)this.$(this.featuresCol())).metadata()), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())).as(!this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol()))})));
      ClusteringMetrics metrics = new ClusteringMetrics(df);
      metrics.setDistanceMeasure((String)this.$(this.distanceMeasure()));
      return metrics;
   }

   public String toString() {
      String var10000 = this.uid();
      return "ClusteringEvaluator: uid=" + var10000 + ", metricName=" + this.$(this.metricName()) + ", distanceMeasure=" + this.$(this.distanceMeasure());
   }

   public ClusteringEvaluator(final String uid) {
      this.uid = uid;
      HasPredictionCol.$init$(this);
      HasFeaturesCol.$init$(this);
      HasWeightCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"silhouette"})));
      this.metricName = new Param(this, "metricName", "metric name in evaluation (silhouette)", allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class));
      String[] availableValues = (String[])((Object[])(new String[]{"squaredEuclidean", "cosine"}));
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)availableValues);
      this.distanceMeasure = new Param(this, "distanceMeasure", "distance measure in evaluation. Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])availableValues).mkString("'", "', '", "'"), allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{this.metricName().$minus$greater("silhouette"), this.distanceMeasure().$minus$greater("squaredEuclidean")}));
      Statics.releaseFence();
   }

   public ClusteringEvaluator() {
      this(Identifiable$.MODULE$.randomUID("cluEval"));
   }
}
