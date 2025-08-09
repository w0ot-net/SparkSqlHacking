package org.apache.spark.ml.evaluation;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%h\u0001\u0002\u000e\u001c\u0005\u0019B\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0005\u0011\u0005\t/\u0002\u0011\t\u0011)A\u0005\u0003\")\u0011\f\u0001C\u00015\")\u0011\f\u0001C\u0001?\"9\u0011\r\u0001b\u0001\n\u0003\u0011\u0007B\u00025\u0001A\u0003%1\rC\u0003k\u0001\u0011\u0005\u0001\tC\u0003m\u0001\u0011\u0005Q\u000eC\u0004s\u0001\t\u0007I\u0011A:\t\ri\u0004\u0001\u0015!\u0003u\u0011\u0015a\b\u0001\"\u0001~\u0011\u001d\t9\u0001\u0001C\u0001\u0003\u0013Aq!a\u0004\u0001\t\u0003\t\t\u0002C\u0004\u0002\u0018\u0001!\t!!\u0007\t\u000f\u0005}\u0001\u0001\"\u0001\u0002\"!9\u0011q\u0005\u0001\u0005B\u0005%\u0002bBA0\u0001\u0011\u0005\u0011\u0011\r\u0005\u0007\u0003\u0007\u0003A\u0011I?\t\u000f\u0005\u001d\u0005\u0001\"\u0011\u0002\n\"9\u00111\u0014\u0001\u0005B\u0005uuaBAR7!\u0005\u0011Q\u0015\u0004\u00075mA\t!a*\t\re3B\u0011AAc\u0011\u001d\t9M\u0006C!\u0003\u0013D\u0011\"!6\u0017\u0003\u0003%I!a6\u0003'I+wM]3tg&|g.\u0012<bYV\fGo\u001c:\u000b\u0005qi\u0012AC3wC2,\u0018\r^5p]*\u0011adH\u0001\u0003[2T!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\u0002\u0001'\u0019\u0001qeK\u001a7sA\u0011\u0001&K\u0007\u00027%\u0011!f\u0007\u0002\n\u000bZ\fG.^1u_J\u0004\"\u0001L\u0019\u000e\u00035R!AL\u0018\u0002\rMD\u0017M]3e\u0015\t\u0001T$A\u0003qCJ\fW.\u0003\u00023[\t\u0001\u0002*Y:Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\t\u0003YQJ!!N\u0017\u0003\u0017!\u000b7\u000fT1cK2\u001cu\u000e\u001c\t\u0003Y]J!\u0001O\u0017\u0003\u0019!\u000b7oV3jO\"$8i\u001c7\u0011\u0005ijT\"A\u001e\u000b\u0005qj\u0012\u0001B;uS2L!AP\u001e\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003\u0005\u0003\"AQ&\u000f\u0005\rK\u0005C\u0001#H\u001b\u0005)%B\u0001$&\u0003\u0019a$o\\8u})\t\u0001*A\u0003tG\u0006d\u0017-\u0003\u0002K\u000f\u00061\u0001K]3eK\u001aL!\u0001T'\u0003\rM#(/\u001b8h\u0015\tQu\tK\u0002\u0002\u001fV\u0003\"\u0001U*\u000e\u0003ES!AU\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U#\n)1+\u001b8dK\u0006\na+A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002P+\u00061A(\u001b8jiz\"\"a\u0017/\u0011\u0005!\u0002\u0001\"B \u0004\u0001\u0004\t\u0005f\u0001/P+\"\u001a1aT+\u0015\u0003mC3\u0001B(V\u0003)iW\r\u001e:jG:\u000bW.Z\u000b\u0002GB\u0019A-Z!\u000e\u0003=J!AZ\u0018\u0003\u000bA\u000b'/Y7)\u0007\u0015yU+A\u0006nKR\u0014\u0018n\u0019(b[\u0016\u0004\u0003f\u0001\u0004P+\u0006iq-\u001a;NKR\u0014\u0018n\u0019(b[\u0016D3aB(V\u00035\u0019X\r^'fiJL7MT1nKR\u0011an\\\u0007\u0002\u0001!)\u0001\u000f\u0003a\u0001\u0003\u0006)a/\u00197vK\"\u001a\u0001bT+\u0002\u001bQD'o\\;hQ>\u0013\u0018nZ5o+\u0005!\bC\u00013v\u0013\t1xF\u0001\u0007C_>dW-\u00198QCJ\fW\u000eK\u0002\n\u001fb\f\u0013!_\u0001\u0006g9\u0002d\u0006M\u0001\u000fi\"\u0014x.^4i\u001fJLw-\u001b8!Q\rQq\n_\u0001\u0011O\u0016$H\u000b\u001b:pk\u001eDwJ]5hS:,\u0012A \t\u0004\u007f\u0006\u0005Q\"A$\n\u0007\u0005\rqIA\u0004C_>dW-\u00198)\u0007-y\u00050\u0001\ttKR$\u0006N]8vO\"|%/[4j]R\u0019a.a\u0003\t\u000bAd\u0001\u0019\u0001@)\u00071y\u00050\u0001\ttKR\u0004&/\u001a3jGRLwN\\\"pYR\u0019a.a\u0005\t\u000bAl\u0001\u0019A!)\u00075yU+A\u0006tKRd\u0015MY3m\u0007>dGc\u00018\u0002\u001c!)\u0001O\u0004a\u0001\u0003\"\u001aabT+\u0002\u0019M,GoV3jO\"$8i\u001c7\u0015\u00079\f\u0019\u0003C\u0003q\u001f\u0001\u0007\u0011\tK\u0002\u0010\u001fb\f\u0001\"\u001a<bYV\fG/\u001a\u000b\u0005\u0003W\t\t\u0004E\u0002\u0000\u0003[I1!a\fH\u0005\u0019!u.\u001e2mK\"9\u00111\u0007\tA\u0002\u0005U\u0012a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003o\t9\u0005\u0005\u0004\u0002:\u0005}\u00121I\u0007\u0003\u0003wQ1!!\u0010 \u0003\r\u0019\u0018\u000f\\\u0005\u0005\u0003\u0003\nYDA\u0004ECR\f7/\u001a;\u0011\t\u0005\u0015\u0013q\t\u0007\u0001\t1\tI%!\r\u0002\u0002\u0003\u0005)\u0011AA&\u0005\ryF%M\t\u0005\u0003\u001b\n\u0019\u0006E\u0002\u0000\u0003\u001fJ1!!\u0015H\u0005\u001dqu\u000e\u001e5j]\u001e\u00042a`A+\u0013\r\t9f\u0012\u0002\u0004\u0003:L\b\u0006\u0002\tP\u00037\n#!!\u0018\u0002\u000bIr\u0003G\f\u0019\u0002\u0015\u001d,G/T3ue&\u001c7\u000f\u0006\u0003\u0002d\u0005E\u0004\u0003BA3\u0003[j!!a\u001a\u000b\u0007q\tIGC\u0002\u0002l}\tQ!\u001c7mS\nLA!a\u001c\u0002h\t\t\"+Z4sKN\u001c\u0018n\u001c8NKR\u0014\u0018nY:\t\u000f\u0005M\u0012\u00031\u0001\u0002tA\"\u0011QOA=!\u0019\tI$a\u0010\u0002xA!\u0011QIA=\t1\tY(!\u001d\u0002\u0002\u0003\u0005)\u0011AA&\u0005\ryFE\r\u0015\u0005#=\u000by(\t\u0002\u0002\u0002\u0006)1GL\u0019/a\u0005q\u0011n\u001d'be\u001e,'OQ3ui\u0016\u0014\bf\u0001\nP+\u0006!1m\u001c9z)\rY\u00161\u0012\u0005\b\u0003\u001b\u001b\u0002\u0019AAH\u0003\u0015)\u0007\u0010\u001e:b!\r!\u0017\u0011S\u0005\u0004\u0003'{#\u0001\u0003)be\u0006lW*\u00199)\tMy\u0015qS\u0011\u0003\u00033\u000bQ!\r\u00186]A\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0003\"\u001aAc\u0014=)\u0007\u0001yU+A\nSK\u001e\u0014Xm]:j_:,e/\u00197vCR|'\u000f\u0005\u0002)-M9a#!+\u00020\u0006U\u0006cA@\u0002,&\u0019\u0011QV$\u0003\r\u0005s\u0017PU3g!\u0011Q\u0014\u0011W.\n\u0007\u0005M6HA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u0011\t\u0005]\u0016\u0011Y\u0007\u0003\u0003sSA!a/\u0002>\u0006\u0011\u0011n\u001c\u0006\u0003\u0003\u007f\u000bAA[1wC&!\u00111YA]\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t)+\u0001\u0003m_\u0006$GcA.\u0002L\"1\u0011Q\u001a\rA\u0002\u0005\u000bA\u0001]1uQ\"\"\u0001dTAiC\t\t\u0019.A\u00032]Yr\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002ZB!\u00111\\Aq\u001b\t\tiN\u0003\u0003\u0002`\u0006u\u0016\u0001\u00027b]\u001eLA!a9\u0002^\n1qJ\u00196fGRDCAF(\u0002R\"\"QcTAi\u0001"
)
public final class RegressionEvaluator extends Evaluator implements HasPredictionCol, HasLabelCol, HasWeightCol, DefaultParamsWritable {
   private final String uid;
   private final Param metricName;
   private final BooleanParam throughOrigin;
   private Param weightCol;
   private Param labelCol;
   private Param predictionCol;

   public static RegressionEvaluator load(final String path) {
      return RegressionEvaluator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RegressionEvaluator$.MODULE$.read();
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

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
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

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
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

   public Param metricName() {
      return this.metricName;
   }

   public String getMetricName() {
      return (String)this.$(this.metricName());
   }

   public RegressionEvaluator setMetricName(final String value) {
      return (RegressionEvaluator)this.set(this.metricName(), value);
   }

   public BooleanParam throughOrigin() {
      return this.throughOrigin;
   }

   public boolean getThroughOrigin() {
      return BoxesRunTime.unboxToBoolean(this.$(this.throughOrigin()));
   }

   public RegressionEvaluator setThroughOrigin(final boolean value) {
      return (RegressionEvaluator)this.set(this.throughOrigin(), BoxesRunTime.boxToBoolean(value));
   }

   public RegressionEvaluator setPredictionCol(final String value) {
      return (RegressionEvaluator)this.set(this.predictionCol(), value);
   }

   public RegressionEvaluator setLabelCol(final String value) {
      return (RegressionEvaluator)this.set(this.labelCol(), value);
   }

   public RegressionEvaluator setWeightCol(final String value) {
      return (RegressionEvaluator)this.set(this.weightCol(), value);
   }

   public double evaluate(final Dataset dataset) {
      RegressionMetrics metrics = this.getMetrics(dataset);
      String var5 = (String)this.$(this.metricName());
      switch (var5 == null ? 0 : var5.hashCode()) {
         case 3584:
            if ("r2".equals(var5)) {
               return metrics.r2();
            }
            break;
         case 107857:
            if ("mae".equals(var5)) {
               return metrics.meanAbsoluteError();
            }
            break;
         case 108415:
            if ("mse".equals(var5)) {
               return metrics.meanSquaredError();
            }
            break;
         case 116519:
            if ("var".equals(var5)) {
               return metrics.explainedVariance();
            }
            break;
         case 3504589:
            if ("rmse".equals(var5)) {
               return metrics.rootMeanSquaredError();
            }
      }

      throw new MatchError(var5);
   }

   public RegressionMetrics getMetrics(final Dataset dataset) {
      StructType schema = dataset.schema();
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.predictionCol()), new .colon.colon(org.apache.spark.sql.types.DoubleType..MODULE$, new .colon.colon(org.apache.spark.sql.types.FloatType..MODULE$, scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      RDD predictionAndLabelsWithWeights = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.predictionCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object prediction = ((SeqOps)var3.get()).apply(0);
               Object label = ((SeqOps)var3.get()).apply(1);
               Object weight = ((SeqOps)var3.get()).apply(2);
               if (prediction instanceof Double) {
                  double var7 = BoxesRunTime.unboxToDouble(prediction);
                  if (label instanceof Double) {
                     double var9 = BoxesRunTime.unboxToDouble(label);
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
      return new RegressionMetrics(predictionAndLabelsWithWeights, BoxesRunTime.unboxToBoolean(this.$(this.throughOrigin())));
   }

   public boolean isLargerBetter() {
      String var2 = (String)this.$(this.metricName());
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 3584:
            if ("r2".equals(var2)) {
               return true;
            }
            break;
         case 116519:
            if ("var".equals(var2)) {
               return true;
            }
      }

      return false;
   }

   public RegressionEvaluator copy(final ParamMap extra) {
      return (RegressionEvaluator)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "RegressionEvaluator: uid=" + var10000 + ", metricName=" + this.$(this.metricName()) + ", throughOrigin=" + this.$(this.throughOrigin());
   }

   public RegressionEvaluator(final String uid) {
      this.uid = uid;
      HasPredictionCol.$init$(this);
      HasLabelCol.$init$(this);
      HasWeightCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"mse", "rmse", "r2", "mae", "var"})));
      this.metricName = new Param(this, "metricName", "metric name in evaluation (mse|rmse|r2|mae|var)", allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.throughOrigin = new BooleanParam(this, "throughOrigin", "Whether the regression is through the origin.");
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.metricName().$minus$greater("rmse"), this.throughOrigin().$minus$greater(BoxesRunTime.boxToBoolean(false))}));
      Statics.releaseFence();
   }

   public RegressionEvaluator() {
      this(Identifiable$.MODULE$.randomUID("regEval"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
