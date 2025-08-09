package org.apache.spark.ml.evaluation;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.evaluation.MultilabelMetrics;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Predef;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005h\u0001B\u000e\u001d\u0001\u001dB\u0001\"\u0010\u0001\u0003\u0006\u0004%\tE\u0010\u0005\t+\u0002\u0011\t\u0011)A\u0005\u007f!)q\u000b\u0001C\u00011\")q\u000b\u0001C\u0001;\"9q\f\u0001b\u0001\n\u000b\u0001\u0007B\u00024\u0001A\u00035\u0011\rC\u0003i\u0001\u0011\u0005a\bC\u0003k\u0001\u0011\u00051\u000eC\u0004q\u0001\t\u0007IQA9\t\rY\u0004\u0001\u0015!\u0004s\u0011\u0015A\b\u0001\"\u0001z\u0011\u0019y\b\u0001\"\u0001\u0002\u0002!9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003+\u0001A\u0011IA\f\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBq!a\u001a\u0001\t\u0003\nI\u0007C\u0004\u0002t\u0001!\t%!\u001e\t\u000f\u0005\r\u0005\u0001\"\u0011\u0002\u0006\u001e9\u00111\u0013\u000f\t\u0002\u0005UeAB\u000e\u001d\u0011\u0003\t9\n\u0003\u0004X+\u0011\u0005\u0011Q\u0017\u0005\n\u0003o+\"\u0019!C\u0005\u0003sC\u0001\"!1\u0016A\u0003%\u00111\u0018\u0005\b\u0003\u0007,B\u0011IAc\u0011%\ti-FA\u0001\n\u0013\tyMA\u0011Nk2$\u0018\u000e\\1cK2\u001cE.Y:tS\u001aL7-\u0019;j_:,e/\u00197vCR|'O\u0003\u0002\u001e=\u0005QQM^1mk\u0006$\u0018n\u001c8\u000b\u0005}\u0001\u0013AA7m\u0015\t\t#%A\u0003ta\u0006\u00148N\u0003\u0002$I\u00051\u0011\r]1dQ\u0016T\u0011!J\u0001\u0004_J<7\u0001A\n\u0006\u0001!bCg\u000e\t\u0003S)j\u0011\u0001H\u0005\u0003Wq\u0011\u0011\"\u0012<bYV\fGo\u001c:\u0011\u00055\u0012T\"\u0001\u0018\u000b\u0005=\u0002\u0014AB:iCJ,GM\u0003\u00022=\u0005)\u0001/\u0019:b[&\u00111G\f\u0002\u0011\u0011\u0006\u001c\bK]3eS\u000e$\u0018n\u001c8D_2\u0004\"!L\u001b\n\u0005Yr#a\u0003%bg2\u000b'-\u001a7D_2\u0004\"\u0001O\u001e\u000e\u0003eR!A\u000f\u0010\u0002\tU$\u0018\u000e\\\u0005\u0003ye\u0012Q\u0003R3gCVdG\u000fU1sC6\u001cxK]5uC\ndW-A\u0002vS\u0012,\u0012a\u0010\t\u0003\u0001&s!!Q$\u0011\u0005\t+U\"A\"\u000b\u0005\u00113\u0013A\u0002\u001fs_>$hHC\u0001G\u0003\u0015\u00198-\u00197b\u0013\tAU)\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0015.\u0013aa\u0015;sS:<'B\u0001%FQ\r\tQj\u0015\t\u0003\u001dFk\u0011a\u0014\u0006\u0003!\u0002\n!\"\u00198o_R\fG/[8o\u0013\t\u0011vJA\u0003TS:\u001cW-I\u0001U\u0003\u0015\u0019d\u0006\r\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\ti5+\u0001\u0004=S:LGO\u0010\u000b\u00033j\u0003\"!\u000b\u0001\t\u000bu\u001a\u0001\u0019A )\u0007ik5\u000bK\u0002\u0004\u001bN#\u0012!\u0017\u0015\u0004\t5\u001b\u0016AC7fiJL7MT1nKV\t\u0011\rE\u0002cG~j\u0011\u0001M\u0005\u0003IB\u0012Q\u0001U1sC6D3!B'T\u0003-iW\r\u001e:jG:\u000bW.\u001a\u0011)\u0007\u0019i5+A\u0007hKRlU\r\u001e:jG:\u000bW.\u001a\u0015\u0004\u000f5\u001b\u0016!D:fi6+GO]5d\u001d\u0006lW\r\u0006\u0002m[6\t\u0001\u0001C\u0003o\u0011\u0001\u0007q(A\u0003wC2,X\rK\u0002\t\u001bN\u000b1\"\\3ue&\u001cG*\u00192fYV\t!\u000f\u0005\u0002cg&\u0011A\u000f\r\u0002\f\t>,(\r\\3QCJ\fW\u000eK\u0002\n\u001bN\u000bA\"\\3ue&\u001cG*\u00192fY\u0002B3AC'T\u000399W\r^'fiJL7\rT1cK2,\u0012A\u001f\t\u0003wrl\u0011!R\u0005\u0003{\u0016\u0013a\u0001R8vE2,\u0007fA\u0006N'\u0006q1/\u001a;NKR\u0014\u0018n\u0019'bE\u0016dGc\u00017\u0002\u0004!)a\u000e\u0004a\u0001u\u0006\u00012/\u001a;Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\u000b\u0004Y\u0006%\u0001\"\u00028\u000e\u0001\u0004y\u0004fA\u0007N'\u0006Y1/\u001a;MC\n,GnQ8m)\ra\u0017\u0011\u0003\u0005\u0006]:\u0001\ra\u0010\u0015\u0004\u001d5\u001b\u0016\u0001C3wC2,\u0018\r^3\u0015\u0007i\fI\u0002C\u0004\u0002\u001c=\u0001\r!!\b\u0002\u000f\u0011\fG/Y:fiB\"\u0011qDA\u0018!\u0019\t\t#a\n\u0002,5\u0011\u00111\u0005\u0006\u0004\u0003K\u0001\u0013aA:rY&!\u0011\u0011FA\u0012\u0005\u001d!\u0015\r^1tKR\u0004B!!\f\u000201\u0001A\u0001DA\u0019\u00033\t\t\u0011!A\u0003\u0002\u0005M\"aA0%cE!\u0011QGA\u001e!\rY\u0018qG\u0005\u0004\u0003s)%a\u0002(pi\"Lgn\u001a\t\u0004w\u0006u\u0012bAA \u000b\n\u0019\u0011I\\=)\u0007=i5+\u0001\u0006hKRlU\r\u001e:jGN$B!a\u0012\u0002VA!\u0011\u0011JA)\u001b\t\tYEC\u0002\u001e\u0003\u001bR1!a\u0014!\u0003\u0015iG\u000e\\5c\u0013\u0011\t\u0019&a\u0013\u0003#5+H\u000e^5mC\n,G.T3ue&\u001c7\u000fC\u0004\u0002\u001cA\u0001\r!a\u00161\t\u0005e\u0013Q\f\t\u0007\u0003C\t9#a\u0017\u0011\t\u00055\u0012Q\f\u0003\r\u0003?\n)&!A\u0001\u0002\u000b\u0005\u00111\u0007\u0002\u0004?\u0012\u0012\u0004\u0006\u0002\tN\u0003G\n#!!\u001a\u0002\u000bMr\u0013G\f\u0019\u0002\u001d%\u001cH*\u0019:hKJ\u0014U\r\u001e;feV\u0011\u00111\u000e\t\u0004w\u00065\u0014bAA8\u000b\n9!i\\8mK\u0006t\u0007fA\tN'\u0006!1m\u001c9z)\rI\u0016q\u000f\u0005\b\u0003s\u0012\u0002\u0019AA>\u0003\u0015)\u0007\u0010\u001e:b!\r\u0011\u0017QP\u0005\u0004\u0003\u007f\u0002$\u0001\u0003)be\u0006lW*\u00199)\u0007Ii5+\u0001\u0005u_N#(/\u001b8h)\u0005y\u0004fA\nN'\"\u001a\u0001!T*)\u0007\u0001\ti\tE\u0002O\u0003\u001fK1!!%P\u00051)\u0005\u0010]3sS6,g\u000e^1m\u0003\u0005jU\u000f\u001c;jY\u0006\u0014W\r\\\"mCN\u001c\u0018NZ5dCRLwN\\#wC2,\u0018\r^8s!\tIScE\u0004\u0016\u00033\u000by*!*\u0011\u0007m\fY*C\u0002\u0002\u001e\u0016\u0013a!\u00118z%\u00164\u0007\u0003\u0002\u001d\u0002\"fK1!a):\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!a*\u000226\u0011\u0011\u0011\u0016\u0006\u0005\u0003W\u000bi+\u0001\u0002j_*\u0011\u0011qV\u0001\u0005U\u00064\u0018-\u0003\u0003\u00024\u0006%&\u0001D*fe&\fG.\u001b>bE2,GCAAK\u0003Q\u0019X\u000f\u001d9peR,G-T3ue&\u001cg*Y7fgV\u0011\u00111\u0018\t\u0005w\u0006uv(C\u0002\u0002@\u0016\u0013Q!\u0011:sCf\fQc];qa>\u0014H/\u001a3NKR\u0014\u0018n\u0019(b[\u0016\u001c\b%\u0001\u0003m_\u0006$GcA-\u0002H\"1\u0011\u0011Z\rA\u0002}\nA\u0001]1uQ\"\u001a\u0011$T*\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0007\u0003BAj\u00033l!!!6\u000b\t\u0005]\u0017QV\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\\\u0006U'AB(cU\u0016\u001cG\u000fK\u0002\u0016\u001bNC3\u0001F'T\u0001"
)
public class MultilabelClassificationEvaluator extends Evaluator implements HasPredictionCol, HasLabelCol, DefaultParamsWritable {
   private final String uid;
   private final Param metricName;
   private final DoubleParam metricLabel;
   private Param labelCol;
   private Param predictionCol;

   public static MultilabelClassificationEvaluator load(final String path) {
      return MultilabelClassificationEvaluator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MultilabelClassificationEvaluator$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
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

   public final Param metricName() {
      return this.metricName;
   }

   public String getMetricName() {
      return (String)this.$(this.metricName());
   }

   public MultilabelClassificationEvaluator setMetricName(final String value) {
      return (MultilabelClassificationEvaluator)this.set(this.metricName(), value);
   }

   public final DoubleParam metricLabel() {
      return this.metricLabel;
   }

   public double getMetricLabel() {
      return BoxesRunTime.unboxToDouble(this.$(this.metricLabel()));
   }

   public MultilabelClassificationEvaluator setMetricLabel(final double value) {
      return (MultilabelClassificationEvaluator)this.set(this.metricLabel(), BoxesRunTime.boxToDouble(value));
   }

   public MultilabelClassificationEvaluator setPredictionCol(final String value) {
      return (MultilabelClassificationEvaluator)this.set(this.predictionCol(), value);
   }

   public MultilabelClassificationEvaluator setLabelCol(final String value) {
      return (MultilabelClassificationEvaluator)this.set(this.labelCol(), value);
   }

   public double evaluate(final Dataset dataset) {
      MultilabelMetrics metrics = this.getMetrics(dataset);
      String var5 = (String)this.$(this.metricName());
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -2131707655:
            if ("accuracy".equals(var5)) {
               return metrics.accuracy();
            }
            break;
         case -1870393773:
            if ("f1Measure".equals(var5)) {
               return metrics.f1Measure();
            }
            break;
         case -1408591233:
            if ("precisionByLabel".equals(var5)) {
               return metrics.precision(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case -1376177026:
            if ("precision".equals(var5)) {
               return metrics.precision();
            }
            break;
         case -1313128715:
            if ("microRecall".equals(var5)) {
               return metrics.microRecall();
            }
            break;
         case -934922479:
            if ("recall".equals(var5)) {
               return metrics.recall();
            }
            break;
         case -592254036:
            if ("hammingLoss".equals(var5)) {
               return metrics.hammingLoss();
            }
            break;
         case 128186122:
            if ("f1MeasureByLabel".equals(var5)) {
               return metrics.f1Measure(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case 969153676:
            if ("recallByLabel".equals(var5)) {
               return metrics.recall(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case 981814255:
            if ("microF1Measure".equals(var5)) {
               return metrics.microF1Measure();
            }
            break;
         case 1476031002:
            if ("microPrecision".equals(var5)) {
               return metrics.microPrecision();
            }
            break;
         case 1644704059:
            if ("subsetAccuracy".equals(var5)) {
               return metrics.subsetAccuracy();
            }
      }

      throw new MatchError(var5);
   }

   public MultilabelMetrics getMetrics(final Dataset dataset) {
      StructType schema = dataset.schema();
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.predictionCol()), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, true), scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.labelCol()), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, true), scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      RDD predictionAndLabels = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.predictionCol())), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol()))}))).rdd().map((row) -> new Tuple2(row.getSeq(0).toArray(scala.reflect.ClassTag..MODULE$.Double()), row.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new MultilabelMetrics(predictionAndLabels);
   }

   public boolean isLargerBetter() {
      String var2 = (String)this.$(this.metricName());
      switch (var2 == null ? 0 : var2.hashCode()) {
         case -592254036:
            if ("hammingLoss".equals(var2)) {
               return false;
            }
         default:
            return true;
      }
   }

   public MultilabelClassificationEvaluator copy(final ParamMap extra) {
      return (MultilabelClassificationEvaluator)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "MultilabelClassificationEvaluator: uid=" + var10000 + ", metricName=" + this.$(this.metricName()) + ", metricLabel=" + this.$(this.metricLabel());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$metricLabel$1(final String x$1) {
      return x$1.endsWith("ByLabel");
   }

   public MultilabelClassificationEvaluator(final String uid) {
      this.uid = uid;
      HasPredictionCol.$init$(this);
      HasLabelCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)MultilabelClassificationEvaluator$.MODULE$.org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames());
      this.metricName = new Param(this, "metricName", "metric name in evaluation " + scala.Predef..MODULE$.wrapRefArray((Object[])MultilabelClassificationEvaluator$.MODULE$.org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames()).mkString("(", "|", ")"), allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class));
      Predef var10005 = scala.Predef..MODULE$;
      ArrayOps var10006 = scala.collection.ArrayOps..MODULE$;
      this.metricLabel = new DoubleParam(this, "metricLabel", "The class whose metric will be computed in " + var10005.wrapRefArray(var10006.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])MultilabelClassificationEvaluator$.MODULE$.org$apache$spark$ml$evaluation$MultilabelClassificationEvaluator$$supportedMetricNames()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$metricLabel$1(x$1)))).mkString("(", "|", ")") + ". Must be >= 0. The default value is 0.", ParamValidators$.MODULE$.gtEq((double)0.0F));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.metricLabel().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), this.metricName().$minus$greater("f1Measure")}));
      Statics.releaseFence();
   }

   public MultilabelClassificationEvaluator() {
      this(Identifiable$.MODULE$.randomUID("mlcEval"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
