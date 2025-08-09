package org.apache.spark.ml.evaluation;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.DoubleType.;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.ArrayOps;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rc\u0001B\u0013'\u0001EB\u0001\"\u0014\u0001\u0003\u0006\u0004%\tE\u0014\u0005\tK\u0002\u0011\t\u0011)A\u0005\u001f\")q\r\u0001C\u0001Q\")q\r\u0001C\u0001[\"9q\u000e\u0001b\u0001\n\u0003\u0001\bB\u0002<\u0001A\u0003%\u0011\u000fC\u0003y\u0001\u0011\u0005a\nC\u0003{\u0001\u0011\u00051\u0010C\u0004\u0002\u0002\u0001!\t!a\u0001\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0001bBA\u000f\u0001\u0011\u0005\u0011q\u0004\u0005\n\u0003K\u0001!\u0019!C\u0003\u0003OA\u0001\"!\r\u0001A\u00035\u0011\u0011\u0006\u0005\b\u0003k\u0001A\u0011AA\u001c\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bB\u0011\"a\u0013\u0001\u0005\u0004%)!a\n\t\u0011\u0005=\u0003\u0001)A\u0007\u0003SAq!a\u0015\u0001\t\u0003\t9\u0004C\u0004\u0002X\u0001!\t!!\u0017\t\u0013\u0005}\u0003A1A\u0005\u0006\u0005\u001d\u0002\u0002CA2\u0001\u0001\u0006i!!\u000b\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u00028!9\u00111\u000e\u0001\u0005\u0002\u00055\u0004bBA:\u0001\u0011\u0005\u0013Q\u000f\u0005\b\u0003K\u0003A\u0011AAT\u0011\u001d\tI\r\u0001C!\u0003\u0017Dq!!6\u0001\t\u0003\n9\u000eC\u0004\u0002f\u0002!\t%a:\b\u000f\u00055h\u0005#\u0001\u0002p\u001a1QE\nE\u0001\u0003cDaaZ\u0010\u0005\u0002\t=\u0001\"\u0003B\t?\t\u0007I\u0011\u0002B\n\u0011!\u0011)c\bQ\u0001\n\tU\u0001b\u0002B\u0014?\u0011\u0005#\u0011\u0006\u0005\n\u0005ky\u0012\u0011!C\u0005\u0005o\u0011\u0011%T;mi&\u001cG.Y:t\u00072\f7o]5gS\u000e\fG/[8o\u000bZ\fG.^1u_JT!a\n\u0015\u0002\u0015\u00154\u0018\r\\;bi&|gN\u0003\u0002*U\u0005\u0011Q\u000e\u001c\u0006\u0003W1\nQa\u001d9be.T!!\f\u0018\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0013aA8sO\u000e\u00011c\u0002\u00013my\nEi\u0012\t\u0003gQj\u0011AJ\u0005\u0003k\u0019\u0012\u0011\"\u0012<bYV\fGo\u001c:\u0011\u0005]bT\"\u0001\u001d\u000b\u0005eR\u0014AB:iCJ,GM\u0003\u0002<Q\u0005)\u0001/\u0019:b[&\u0011Q\b\u000f\u0002\u0011\u0011\u0006\u001c\bK]3eS\u000e$\u0018n\u001c8D_2\u0004\"aN \n\u0005\u0001C$a\u0003%bg2\u000b'-\u001a7D_2\u0004\"a\u000e\"\n\u0005\rC$\u0001\u0004%bg^+\u0017n\u001a5u\u0007>d\u0007CA\u001cF\u0013\t1\u0005HA\tICN\u0004&o\u001c2bE&d\u0017\u000e^=D_2\u0004\"\u0001S&\u000e\u0003%S!A\u0013\u0015\u0002\tU$\u0018\u000e\\\u0005\u0003\u0019&\u0013Q\u0003R3gCVdG\u000fU1sC6\u001cxK]5uC\ndW-A\u0002vS\u0012,\u0012a\u0014\t\u0003!fs!!U,\u0011\u0005I+V\"A*\u000b\u0005Q\u0003\u0014A\u0002\u001fs_>$hHC\u0001W\u0003\u0015\u00198-\u00197b\u0013\tAV+\u0001\u0004Qe\u0016$WMZ\u0005\u00035n\u0013aa\u0015;sS:<'B\u0001-VQ\r\tQl\u0019\t\u0003=\u0006l\u0011a\u0018\u0006\u0003A*\n!\"\u00198o_R\fG/[8o\u0013\t\u0011wLA\u0003TS:\u001cW-I\u0001e\u0003\u0015\td&\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\ti6-\u0001\u0004=S:LGO\u0010\u000b\u0003S*\u0004\"a\r\u0001\t\u000b5\u001b\u0001\u0019A()\u0007)l6\rK\u0002\u0004;\u000e$\u0012!\u001b\u0015\u0004\tu\u001b\u0017AC7fiJL7MT1nKV\t\u0011\u000fE\u0002sg>k\u0011AO\u0005\u0003ij\u0012Q\u0001U1sC6D3!B/d\u0003-iW\r\u001e:jG:\u000bW.\u001a\u0011)\u0007\u0019i6-A\u0007hKRlU\r\u001e:jG:\u000bW.\u001a\u0015\u0004\u000fu\u001b\u0017!D:fi6+GO]5d\u001d\u0006lW\r\u0006\u0002}{6\t\u0001\u0001C\u0003\u007f\u0011\u0001\u0007q*A\u0003wC2,X\rK\u0002\t;\u000e\f\u0001c]3u!J,G-[2uS>t7i\u001c7\u0015\u0007q\f)\u0001C\u0003\u007f\u0013\u0001\u0007q\nK\u0002\n;\u000e\f1b]3u\u0019\u0006\u0014W\r\\\"pYR\u0019A0!\u0004\t\u000byT\u0001\u0019A()\u0007)i6-\u0001\u0007tKR<V-[4ii\u000e{G\u000eF\u0002}\u0003+AQA`\u0006A\u0002=CCaC/\u0002\u001a\u0005\u0012\u00111D\u0001\u0006g9\u0002d\u0006M\u0001\u0012g\u0016$\bK]8cC\nLG.\u001b;z\u0007>dGc\u0001?\u0002\"!)a\u0010\u0004a\u0001\u001f\"\"A\"XA\r\u0003-iW\r\u001e:jG2\u000b'-\u001a7\u0016\u0005\u0005%\u0002c\u0001:\u0002,%\u0019\u0011Q\u0006\u001e\u0003\u0017\u0011{WO\u00197f!\u0006\u0014\u0018-\u001c\u0015\u0005\u001bu\u000bI\"\u0001\u0007nKR\u0014\u0018n\u0019'bE\u0016d\u0007\u0005\u000b\u0003\u000f;\u0006e\u0011AD4fi6+GO]5d\u0019\u0006\u0014W\r\\\u000b\u0003\u0003s\u0001B!a\u000f\u0002>5\tQ+C\u0002\u0002@U\u0013a\u0001R8vE2,\u0007\u0006B\b^\u00033\tab]3u\u001b\u0016$(/[2MC\n,G\u000eF\u0002}\u0003\u000fBaA \tA\u0002\u0005e\u0002\u0006\u0002\t^\u00033\tAAY3uC\"\"\u0011#XA\r\u0003\u0015\u0011W\r^1!Q\u0011\u0011R,!\u0007\u0002\u000f\u001d,GOQ3uC\"\"1#XA\r\u0003\u001d\u0019X\r\u001e\"fi\u0006$2\u0001`A.\u0011\u0019qH\u00031\u0001\u0002:!\"A#XA\r\u0003\r)\u0007o\u001d\u0015\u0005+u\u000bI\"\u0001\u0003faN\u0004\u0003\u0006\u0002\f^\u00033\taaZ3u\u000bB\u001c\b\u0006B\f^\u00033\taa]3u\u000bB\u001cHc\u0001?\u0002p!1a\u0010\u0007a\u0001\u0003sAC\u0001G/\u0002\u001a\u0005AQM^1mk\u0006$X\r\u0006\u0003\u0002:\u0005]\u0004bBA=3\u0001\u0007\u00111P\u0001\bI\u0006$\u0018m]3ua\u0011\ti(!$\u0011\r\u0005}\u0014QQAE\u001b\t\t\tIC\u0002\u0002\u0004*\n1a]9m\u0013\u0011\t9)!!\u0003\u000f\u0011\u000bG/Y:fiB!\u00111RAG\u0019\u0001!A\"a$\u0002x\u0005\u0005\t\u0011!B\u0001\u0003#\u00131a\u0018\u00132#\u0011\t\u0019*!'\u0011\t\u0005m\u0012QS\u0005\u0004\u0003/+&a\u0002(pi\"Lgn\u001a\t\u0005\u0003w\tY*C\u0002\u0002\u001eV\u00131!\u00118zQ\u0011IR,!)\"\u0005\u0005\r\u0016!\u0002\u001a/a9\u0002\u0014AC4fi6+GO]5dgR!\u0011\u0011VA\\!\u0011\tY+a-\u000e\u0005\u00055&bA\u0014\u00020*\u0019\u0011\u0011\u0017\u0016\u0002\u000b5dG.\u001b2\n\t\u0005U\u0016Q\u0016\u0002\u0012\u001bVdG/[2mCN\u001cX*\u001a;sS\u000e\u001c\bbBA=5\u0001\u0007\u0011\u0011\u0018\u0019\u0005\u0003w\u000by\f\u0005\u0004\u0002\u0000\u0005\u0015\u0015Q\u0018\t\u0005\u0003\u0017\u000by\f\u0002\u0007\u0002B\u0006]\u0016\u0011!A\u0001\u0006\u0003\t\tJA\u0002`IIBCAG/\u0002F\u0006\u0012\u0011qY\u0001\u0006g9\nd\u0006M\u0001\u000fSNd\u0015M]4fe\n+G\u000f^3s+\t\ti\r\u0005\u0003\u0002<\u0005=\u0017bAAi+\n9!i\\8mK\u0006t\u0007fA\u000e^G\u0006!1m\u001c9z)\rI\u0017\u0011\u001c\u0005\b\u00037d\u0002\u0019AAo\u0003\u0015)\u0007\u0010\u001e:b!\r\u0011\u0018q\\\u0005\u0004\u0003CT$\u0001\u0003)be\u0006lW*\u00199)\u0007qi6-\u0001\u0005u_N#(/\u001b8h)\u0005y\u0005\u0006B\u000f^\u00033A3\u0001A/d\u0003\u0005jU\u000f\u001c;jG2\f7o]\"mCN\u001c\u0018NZ5dCRLwN\\#wC2,\u0018\r^8s!\t\u0019tdE\u0004 \u0003g\fI0a@\u0011\t\u0005m\u0012Q_\u0005\u0004\u0003o,&AB!osJ+g\r\u0005\u0003I\u0003wL\u0017bAA\u007f\u0013\n)B)\u001a4bk2$\b+\u0019:b[N\u0014V-\u00193bE2,\u0007\u0003\u0002B\u0001\u0005\u0017i!Aa\u0001\u000b\t\t\u0015!qA\u0001\u0003S>T!A!\u0003\u0002\t)\fg/Y\u0005\u0005\u0005\u001b\u0011\u0019A\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002p\u0006!2/\u001e9q_J$X\rZ'fiJL7MT1nKN,\"A!\u0006\u0011\r\u0005m\"q\u0003B\u000e\u0013\r\u0011I\"\u0016\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u0005;\u0011\u0019#\u0004\u0002\u0003 )!!\u0011\u0005B\u0004\u0003\u0011a\u0017M\\4\n\u0007i\u0013y\"A\u000btkB\u0004xN\u001d;fI6+GO]5d\u001d\u0006lWm\u001d\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004S\n-\u0002B\u0002B\u0017G\u0001\u0007q*\u0001\u0003qCRD\u0007\u0006B\u0012^\u0005c\t#Aa\r\u0002\u000bErcG\f\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\te\u0002\u0003\u0002B\u000f\u0005wIAA!\u0010\u0003 \t1qJ\u00196fGRDCaH/\u00032!\"a$\u0018B\u0019\u0001"
)
public class MulticlassClassificationEvaluator extends Evaluator implements HasPredictionCol, HasLabelCol, HasWeightCol, HasProbabilityCol, DefaultParamsWritable {
   private final String uid;
   private final Param metricName;
   private final DoubleParam metricLabel;
   private final DoubleParam beta;
   private final DoubleParam eps;
   private Param probabilityCol;
   private Param weightCol;
   private Param labelCol;
   private Param predictionCol;

   public static MulticlassClassificationEvaluator load(final String path) {
      return MulticlassClassificationEvaluator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MulticlassClassificationEvaluator$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getProbabilityCol() {
      return HasProbabilityCol.getProbabilityCol$(this);
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

   public final Param probabilityCol() {
      return this.probabilityCol;
   }

   public final void org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(final Param x$1) {
      this.probabilityCol = x$1;
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

   public MulticlassClassificationEvaluator setMetricName(final String value) {
      return (MulticlassClassificationEvaluator)this.set(this.metricName(), value);
   }

   public MulticlassClassificationEvaluator setPredictionCol(final String value) {
      return (MulticlassClassificationEvaluator)this.set(this.predictionCol(), value);
   }

   public MulticlassClassificationEvaluator setLabelCol(final String value) {
      return (MulticlassClassificationEvaluator)this.set(this.labelCol(), value);
   }

   public MulticlassClassificationEvaluator setWeightCol(final String value) {
      return (MulticlassClassificationEvaluator)this.set(this.weightCol(), value);
   }

   public MulticlassClassificationEvaluator setProbabilityCol(final String value) {
      return (MulticlassClassificationEvaluator)this.set(this.probabilityCol(), value);
   }

   public final DoubleParam metricLabel() {
      return this.metricLabel;
   }

   public double getMetricLabel() {
      return BoxesRunTime.unboxToDouble(this.$(this.metricLabel()));
   }

   public MulticlassClassificationEvaluator setMetricLabel(final double value) {
      return (MulticlassClassificationEvaluator)this.set(this.metricLabel(), BoxesRunTime.boxToDouble(value));
   }

   public final DoubleParam beta() {
      return this.beta;
   }

   public double getBeta() {
      return BoxesRunTime.unboxToDouble(this.$(this.beta()));
   }

   public MulticlassClassificationEvaluator setBeta(final double value) {
      return (MulticlassClassificationEvaluator)this.set(this.beta(), BoxesRunTime.boxToDouble(value));
   }

   public final DoubleParam eps() {
      return this.eps;
   }

   public double getEps() {
      return BoxesRunTime.unboxToDouble(this.$(this.eps()));
   }

   public MulticlassClassificationEvaluator setEps(final double value) {
      return (MulticlassClassificationEvaluator)this.set(this.eps(), BoxesRunTime.boxToDouble(value));
   }

   public double evaluate(final Dataset dataset) {
      MulticlassMetrics metrics = this.getMetrics(dataset);
      String var5 = (String)this.$(this.metricName());
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -2131707655:
            if ("accuracy".equals(var5)) {
               return metrics.accuracy();
            }
            break;
         case -2067421467:
            if ("weightedFalsePositiveRate".equals(var5)) {
               return metrics.weightedFalsePositiveRate();
            }
            break;
         case -1408591233:
            if ("precisionByLabel".equals(var5)) {
               return metrics.precision(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case -1041068123:
            if ("fMeasureByLabel".equals(var5)) {
               return metrics.fMeasure(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())), BoxesRunTime.unboxToDouble(this.$(this.beta())));
            }
            break;
         case -905554431:
            if ("falsePositiveRateByLabel".equals(var5)) {
               return metrics.falsePositiveRate(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case -873183480:
            if ("weightedRecall".equals(var5)) {
               return metrics.weightedRecall();
            }
            break;
         case -817361841:
            if ("weightedFMeasure".equals(var5)) {
               return metrics.weightedFMeasure(BoxesRunTime.unboxToDouble(this.$(this.beta())));
            }
            break;
         case -592254036:
            if ("hammingLoss".equals(var5)) {
               return metrics.hammingLoss();
            }
            break;
         case -355660505:
            if ("weightedPrecision".equals(var5)) {
               return metrics.weightedPrecision();
            }
            break;
         case 3211:
            if ("f1".equals(var5)) {
               return metrics.weightedFMeasure();
            }
            break;
         case 341482631:
            if ("logLoss".equals(var5)) {
               return metrics.logLoss(BoxesRunTime.unboxToDouble(this.$(this.eps())));
            }
            break;
         case 594154422:
            if ("truePositiveRateByLabel".equals(var5)) {
               return metrics.truePositiveRate(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case 969153676:
            if ("recallByLabel".equals(var5)) {
               return metrics.recall(BoxesRunTime.unboxToDouble(this.$(this.metricLabel())));
            }
            break;
         case 1987142238:
            if ("weightedTruePositiveRate".equals(var5)) {
               return metrics.weightedTruePositiveRate();
            }
      }

      throw new MatchError(var5);
   }

   public MulticlassMetrics getMetrics(final Dataset dataset) {
      StructType schema;
      label19: {
         schema = dataset.schema();
         SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.predictionCol()), .MODULE$, SchemaUtils$.MODULE$.checkColumnType$default$4());
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
         Object var10000 = this.$(this.metricName());
         String var3 = "logLoss";
         if (var10000 == null) {
            if (var3 != null) {
               break label19;
            }
         } else if (!var10000.equals(var3)) {
            break label19;
         }

         scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.probabilityCol())), () -> "probabilityCol is needed to compute logloss");
      }

      Column w = DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()));
      RDD var7;
      if (scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.probabilityCol()))) {
         Column p = DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.probabilityCol()));
         var7 = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.predictionCol())), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol())).cast(.MODULE$), w, p}))).rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(4) == 0) {
                  Object prediction = ((SeqOps)var3.get()).apply(0);
                  Object label = ((SeqOps)var3.get()).apply(1);
                  Object weight = ((SeqOps)var3.get()).apply(2);
                  Object probability = ((SeqOps)var3.get()).apply(3);
                  if (prediction instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var10 = BoxesRunTime.unboxToDouble(label);
                        if (weight instanceof Double) {
                           double var12 = BoxesRunTime.unboxToDouble(weight);
                           if (probability instanceof Vector) {
                              Vector var14 = (Vector)probability;
                              return new Tuple4(BoxesRunTime.boxToDouble(var8), BoxesRunTime.boxToDouble(var10), BoxesRunTime.boxToDouble(var12), var14.toArray());
                           }
                        }
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple4.class));
      } else {
         var7 = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.predictionCol())), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol())).cast(.MODULE$), w}))).rdd().map((x0$2) -> {
            if (x0$2 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$2);
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

            throw new MatchError(x0$2);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      }

      RDD rdd = var7;
      return new MulticlassMetrics(rdd);
   }

   public boolean isLargerBetter() {
      String var2 = (String)this.$(this.metricName());
      switch (var2 == null ? 0 : var2.hashCode()) {
         case -2067421467:
            if ("weightedFalsePositiveRate".equals(var2)) {
               return false;
            }
            break;
         case -905554431:
            if ("falsePositiveRateByLabel".equals(var2)) {
               return false;
            }
            break;
         case -592254036:
            if ("hammingLoss".equals(var2)) {
               return false;
            }
            break;
         case 341482631:
            if ("logLoss".equals(var2)) {
               return false;
            }
      }

      return true;
   }

   public MulticlassClassificationEvaluator copy(final ParamMap extra) {
      return (MulticlassClassificationEvaluator)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "MulticlassClassificationEvaluator: uid=" + var10000 + ", metricName=" + this.$(this.metricName()) + ", metricLabel=" + this.$(this.metricLabel()) + ", beta=" + this.$(this.beta()) + ", eps=" + this.$(this.eps());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$metricLabel$1(final String x$1) {
      return x$1.endsWith("ByLabel");
   }

   public MulticlassClassificationEvaluator(final String uid) {
      this.uid = uid;
      HasPredictionCol.$init$(this);
      HasLabelCol.$init$(this);
      HasWeightCol.$init$(this);
      HasProbabilityCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.metricName = new Param(this, "metricName", "metric name in evaluation " + scala.Predef..MODULE$.wrapRefArray((Object[])MulticlassClassificationEvaluator$.MODULE$.org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames()).mkString("(", "|", ")"), ParamValidators$.MODULE$.inArray((Object)MulticlassClassificationEvaluator$.MODULE$.org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames()), scala.reflect.ClassTag..MODULE$.apply(String.class));
      Predef var10005 = scala.Predef..MODULE$;
      ArrayOps var10006 = scala.collection.ArrayOps..MODULE$;
      this.metricLabel = new DoubleParam(this, "metricLabel", "The class whose metric will be computed in " + var10005.wrapRefArray(var10006.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])MulticlassClassificationEvaluator$.MODULE$.org$apache$spark$ml$evaluation$MulticlassClassificationEvaluator$$supportedMetricNames()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$metricLabel$1(x$1)))).mkString("(", "|", ")") + ". Must be >= 0. The default value is 0.", ParamValidators$.MODULE$.gtEq((double)0.0F));
      this.beta = new DoubleParam(this, "beta", "The beta value, which controls precision vs recall weighting, used in (weightedFMeasure|fMeasureByLabel). Must be > 0. The default value is 1.", ParamValidators$.MODULE$.gt((double)0.0F));
      this.eps = new DoubleParam(this, "eps", "log-loss is undefined for p=0 or p=1, so probabilities are clipped to max(eps, min(1 - eps, p)).", ParamValidators$.MODULE$.inRange((double)0.0F, (double)0.5F, false, false));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.metricName().$minus$greater("f1"), this.eps().$minus$greater(BoxesRunTime.boxToDouble(1.0E-15)), this.metricLabel().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), this.beta().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F))}));
      Statics.releaseFence();
   }

   public MulticlassClassificationEvaluator() {
      this(Identifiable$.MODULE$.randomUID("mcEval"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
