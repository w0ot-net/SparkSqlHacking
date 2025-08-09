package org.apache.spark.ml.evaluation;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasRawPredictionCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.MetadataUtils$;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
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
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eh\u0001\u0002\u000e\u001c\u0001\u0019B\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0005\u0011\u0005\t/\u0002\u0011\t\u0011)A\u0005\u0003\")\u0011\f\u0001C\u00015\")\u0011\f\u0001C\u0001?\"91\r\u0001b\u0001\n\u0003!\u0007B\u00026\u0001A\u0003%Q\rC\u0003m\u0001\u0011\u0005\u0001\tC\u0003o\u0001\u0011\u0005q\u000eC\u0004u\u0001\t\u0007I\u0011A;\t\rq\u0004\u0001\u0015!\u0003w\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!a\u0005\u0001\t\u0003\t)\u0002C\u0004\u0002 \u0001!\t!!\t\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002*!9\u0011q\u0006\u0001\u0005B\u0005E\u0002bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003\u0017\u0003A\u0011IAG\u0011\u001d\t9\n\u0001C!\u00033Cq!a+\u0001\t\u0003\nikB\u0004\u00024nA\t!!.\u0007\riY\u0002\u0012AA\\\u0011\u0019If\u0003\"\u0001\u0002V\"9\u0011q\u001b\f\u0005B\u0005e\u0007\"CAs-\u0005\u0005I\u0011BAt\u0005u\u0011\u0015N\\1ss\u000ec\u0017m]:jM&\u001c\u0017\r^5p]\u00163\u0018\r\\;bi>\u0014(B\u0001\u000f\u001e\u0003))g/\u00197vCRLwN\u001c\u0006\u0003=}\t!!\u001c7\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u0001O-\u001ad'\u000f\t\u0003Q%j\u0011aG\u0005\u0003Um\u0011\u0011\"\u0012<bYV\fGo\u001c:\u0011\u00051\nT\"A\u0017\u000b\u00059z\u0013AB:iCJ,GM\u0003\u00021;\u0005)\u0001/\u0019:b[&\u0011!'\f\u0002\u0014\u0011\u0006\u001c(+Y<Qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\u001c\t\u0003YQJ!!N\u0017\u0003\u0017!\u000b7\u000fT1cK2\u001cu\u000e\u001c\t\u0003Y]J!\u0001O\u0017\u0003\u0019!\u000b7oV3jO\"$8i\u001c7\u0011\u0005ijT\"A\u001e\u000b\u0005qj\u0012\u0001B;uS2L!AP\u001e\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003\u0005\u0003\"AQ&\u000f\u0005\rK\u0005C\u0001#H\u001b\u0005)%B\u0001$&\u0003\u0019a$o\\8u})\t\u0001*A\u0003tG\u0006d\u0017-\u0003\u0002K\u000f\u00061\u0001K]3eK\u001aL!\u0001T'\u0003\rM#(/\u001b8h\u0015\tQu\tK\u0002\u0002\u001fV\u0003\"\u0001U*\u000e\u0003ES!AU\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U#\n)1+\u001b8dK\u0006\na+A\u00032]Qr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002P+\u00061A(\u001b8jiz\"\"a\u0017/\u0011\u0005!\u0002\u0001\"B \u0004\u0001\u0004\t\u0005f\u0001/P+\"\u001a1aT+\u0015\u0003mC3\u0001B(bC\u0005\u0011\u0017!B\u0019/e9\u0002\u0014AC7fiJL7MT1nKV\tQ\rE\u0002gO\u0006k\u0011aL\u0005\u0003Q>\u0012Q\u0001U1sC6D3!B(b\u0003-iW\r\u001e:jG:\u000bW.\u001a\u0011)\u0007\u0019y\u0015-A\u0007hKRlU\r\u001e:jG:\u000bW.\u001a\u0015\u0004\u000f=\u000b\u0017!D:fi6+GO]5d\u001d\u0006lW\r\u0006\u0002qc6\t\u0001\u0001C\u0003s\u0011\u0001\u0007\u0011)A\u0003wC2,X\rK\u0002\t\u001f\u0006\fqA\\;n\u0005&t7/F\u0001w!\t1w/\u0003\u0002y_\tA\u0011J\u001c;QCJ\fW\u000eK\u0002\n\u001fj\f\u0013a_\u0001\u0006g9\u0002d\u0006M\u0001\t]Vl')\u001b8tA!\u001a!b\u0014>\u0002\u0015\u001d,GOT;n\u0005&t7/\u0006\u0002\u0002\u0002A!\u00111AA\u0003\u001b\u00059\u0015bAA\u0004\u000f\n\u0019\u0011J\u001c;)\u0007-y%0\u0001\u0006tKRtU/\u001c\"j]N$2\u0001]A\b\u0011\u0019\u0011H\u00021\u0001\u0002\u0002!\u001aAb\u0014>\u0002'M,GOU1x!J,G-[2uS>t7i\u001c7\u0015\u0007A\f9\u0002C\u0003s\u001b\u0001\u0007\u0011\t\u000b\u0003\u000e\u001f\u0006m\u0011EAA\u000f\u0003\u0015\td&\u000e\u00181\u0003-\u0019X\r\u001e'bE\u0016d7i\u001c7\u0015\u0007A\f\u0019\u0003C\u0003s\u001d\u0001\u0007\u0011\tK\u0002\u000f\u001f\u0006\fAb]3u/\u0016Lw\r\u001b;D_2$2\u0001]A\u0016\u0011\u0015\u0011x\u00021\u0001BQ\ryqJ_\u0001\tKZ\fG.^1uKR!\u00111GA\u001d!\u0011\t\u0019!!\u000e\n\u0007\u0005]rI\u0001\u0004E_V\u0014G.\u001a\u0005\b\u0003w\u0001\u0002\u0019AA\u001f\u0003\u001d!\u0017\r^1tKR\u0004D!a\u0010\u0002PA1\u0011\u0011IA$\u0003\u0017j!!a\u0011\u000b\u0007\u0005\u0015s$A\u0002tc2LA!!\u0013\u0002D\t9A)\u0019;bg\u0016$\b\u0003BA'\u0003\u001fb\u0001\u0001\u0002\u0007\u0002R\u0005e\u0012\u0011!A\u0001\u0006\u0003\t\u0019FA\u0002`IE\nB!!\u0016\u0002\\A!\u00111AA,\u0013\r\tIf\u0012\u0002\b\u001d>$\b.\u001b8h!\u0011\t\u0019!!\u0018\n\u0007\u0005}sIA\u0002B]fDC\u0001E(\u0002d\u0005\u0012\u0011QM\u0001\u0006e9\u0002d\u0006M\u0001\u000bO\u0016$X*\u001a;sS\u000e\u001cH\u0003BA6\u0003s\u0002B!!\u001c\u0002v5\u0011\u0011q\u000e\u0006\u00049\u0005E$bAA:?\u0005)Q\u000e\u001c7jE&!\u0011qOA8\u0005m\u0011\u0015N\\1ss\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6+GO]5dg\"9\u00111H\tA\u0002\u0005m\u0004\u0007BA?\u0003\u0003\u0003b!!\u0011\u0002H\u0005}\u0004\u0003BA'\u0003\u0003#A\"a!\u0002z\u0005\u0005\t\u0011!B\u0001\u0003'\u00121a\u0018\u00133Q\u0011\tr*a\"\"\u0005\u0005%\u0015!B\u001a/c9\u0002\u0014AD5t\u0019\u0006\u0014x-\u001a:CKR$XM]\u000b\u0003\u0003\u001f\u0003B!a\u0001\u0002\u0012&\u0019\u00111S$\u0003\u000f\t{w\u000e\\3b]\"\"!cTA\u000e\u0003\u0011\u0019w\u000e]=\u0015\u0007m\u000bY\nC\u0004\u0002\u001eN\u0001\r!a(\u0002\u000b\u0015DHO]1\u0011\u0007\u0019\f\t+C\u0002\u0002$>\u0012\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0005'=\u000b9+\t\u0002\u0002*\u0006)\u0011G\f\u001b/c\u0005AAo\\*ue&tw\rF\u0001BQ\r!rJ\u001f\u0015\u0004\u0001=\u000b\u0017!\b\"j]\u0006\u0014\u0018p\u00117bgNLg-[2bi&|g.\u0012<bYV\fGo\u001c:\u0011\u0005!22c\u0002\f\u0002:\u0006}\u0016Q\u0019\t\u0005\u0003\u0007\tY,C\u0002\u0002>\u001e\u0013a!\u00118z%\u00164\u0007\u0003\u0002\u001e\u0002BnK1!a1<\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!a2\u0002R6\u0011\u0011\u0011\u001a\u0006\u0005\u0003\u0017\fi-\u0001\u0002j_*\u0011\u0011qZ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002T\u0006%'\u0001D*fe&\fG.\u001b>bE2,GCAA[\u0003\u0011aw.\u00193\u0015\u0007m\u000bY\u000e\u0003\u0004\u0002^b\u0001\r!Q\u0001\u0005a\u0006$\b\u000e\u000b\u0003\u0019\u001f\u0006\u0005\u0018EAAr\u0003\u0015\tdF\u000e\u00181\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u000f\u0005\u0003\u0002l\u0006EXBAAw\u0015\u0011\ty/!4\u0002\t1\fgnZ\u0005\u0005\u0003g\fiO\u0001\u0004PE*,7\r\u001e\u0015\u0005-=\u000b\t\u000f\u000b\u0003\u0016\u001f\u0006\u0005\b"
)
public class BinaryClassificationEvaluator extends Evaluator implements HasRawPredictionCol, HasLabelCol, HasWeightCol, DefaultParamsWritable {
   private final String uid;
   private final Param metricName;
   private final IntParam numBins;
   private Param weightCol;
   private Param labelCol;
   private Param rawPredictionCol;

   public static BinaryClassificationEvaluator load(final String path) {
      return BinaryClassificationEvaluator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return BinaryClassificationEvaluator$.MODULE$.read();
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

   public final String getRawPredictionCol() {
      return HasRawPredictionCol.getRawPredictionCol$(this);
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

   public final Param rawPredictionCol() {
      return this.rawPredictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasRawPredictionCol$_setter_$rawPredictionCol_$eq(final Param x$1) {
      this.rawPredictionCol = x$1;
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

   public BinaryClassificationEvaluator setMetricName(final String value) {
      return (BinaryClassificationEvaluator)this.set(this.metricName(), value);
   }

   public IntParam numBins() {
      return this.numBins;
   }

   public int getNumBins() {
      return BoxesRunTime.unboxToInt(this.$(this.numBins()));
   }

   public BinaryClassificationEvaluator setNumBins(final int value) {
      return (BinaryClassificationEvaluator)this.set(this.numBins(), BoxesRunTime.boxToInteger(value));
   }

   public BinaryClassificationEvaluator setRawPredictionCol(final String value) {
      return (BinaryClassificationEvaluator)this.set(this.rawPredictionCol(), value);
   }

   public BinaryClassificationEvaluator setLabelCol(final String value) {
      return (BinaryClassificationEvaluator)this.set(this.labelCol(), value);
   }

   public BinaryClassificationEvaluator setWeightCol(final String value) {
      return (BinaryClassificationEvaluator)this.set(this.weightCol(), value);
   }

   public double evaluate(final Dataset dataset) {
      BinaryClassificationMetrics metrics = this.getMetrics(dataset);
      String var7 = (String)this.$(this.metricName());
      double var10000;
      switch (var7 == null ? 0 : var7.hashCode()) {
         case -1339473029:
            if (!"areaUnderROC".equals(var7)) {
               throw new MatchError(var7);
            }

            var10000 = metrics.areaUnderROC();
            break;
         case 1619359117:
            if ("areaUnderPR".equals(var7)) {
               var10000 = metrics.areaUnderPR();
               break;
            }

            throw new MatchError(var7);
         default:
            throw new MatchError(var7);
      }

      double metric = var10000;
      metrics.unpersist();
      return metric;
   }

   public BinaryClassificationMetrics getMetrics(final Dataset dataset) {
      StructType schema = dataset.schema();
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.rawPredictionCol()), new .colon.colon(org.apache.spark.sql.types.DoubleType..MODULE$, new .colon.colon(new VectorUDT(), scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      if (this.isDefined(this.weightCol())) {
         SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.weightCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      }

      MetadataUtils$.MODULE$.getNumFeatures(schema.apply((String)this.$(this.rawPredictionCol()))).foreach((JFunction1.mcVI.sp)(n) -> scala.Predef..MODULE$.require(n == 2, () -> "rawPredictionCol vectors must have length=2, but got " + n));
      RDD scoreAndLabelsWithWeights = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.rawPredictionCol())), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object rawPrediction = ((SeqOps)var3.get()).apply(0);
               Object label = ((SeqOps)var3.get()).apply(1);
               Object weight = ((SeqOps)var3.get()).apply(2);
               if (rawPrediction instanceof Vector) {
                  Vector var7 = (Vector)rawPrediction;
                  if (label instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(label);
                     if (weight instanceof Double) {
                        double var10 = BoxesRunTime.unboxToDouble(weight);
                        return new Tuple3(BoxesRunTime.boxToDouble(var7.apply(1)), BoxesRunTime.boxToDouble(var8), BoxesRunTime.boxToDouble(var10));
                     }
                  }
               }
            }
         }

         if (x0$1 != null) {
            Some var12 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var12.isEmpty() && var12.get() != null && ((SeqOps)var12.get()).lengthCompare(3) == 0) {
               Object rawPrediction = ((SeqOps)var12.get()).apply(0);
               Object label = ((SeqOps)var12.get()).apply(1);
               Object weight = ((SeqOps)var12.get()).apply(2);
               if (rawPrediction instanceof Double) {
                  double var16 = BoxesRunTime.unboxToDouble(rawPrediction);
                  if (label instanceof Double) {
                     double var18 = BoxesRunTime.unboxToDouble(label);
                     if (weight instanceof Double) {
                        double var20 = BoxesRunTime.unboxToDouble(weight);
                        return new Tuple3(BoxesRunTime.boxToDouble(var16), BoxesRunTime.boxToDouble(var18), BoxesRunTime.boxToDouble(var20));
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      return new BinaryClassificationMetrics(scoreAndLabelsWithWeights, BoxesRunTime.unboxToInt(this.$(this.numBins())));
   }

   public boolean isLargerBetter() {
      return true;
   }

   public BinaryClassificationEvaluator copy(final ParamMap extra) {
      return (BinaryClassificationEvaluator)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "BinaryClassificationEvaluator: uid=" + var10000 + ", metricName=" + this.$(this.metricName()) + ", numBins=" + this.$(this.numBins());
   }

   public BinaryClassificationEvaluator(final String uid) {
      this.uid = uid;
      HasRawPredictionCol.$init$(this);
      HasLabelCol.$init$(this);
      HasWeightCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"areaUnderROC", "areaUnderPR"})));
      this.metricName = new Param(this, "metricName", "metric name in evaluation (areaUnderROC|areaUnderPR)", allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.numBins = new IntParam(this, "numBins", "Number of bins to down-sample the curves (ROC curve, PR curve) in area computation. If 0, no down-sampling will occur. Must be >= 0.", ParamValidators$.MODULE$.gtEq((double)0.0F));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.metricName().$minus$greater("areaUnderROC"), this.numBins().$minus$greater(BoxesRunTime.boxToInteger(1000))}));
      Statics.releaseFence();
   }

   public BinaryClassificationEvaluator() {
      this(Identifiable$.MODULE$.randomUID("binEval"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
