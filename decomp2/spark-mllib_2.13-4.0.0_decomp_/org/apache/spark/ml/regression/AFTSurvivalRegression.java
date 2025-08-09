package org.apache.spark.ml.regression;

import breeze.linalg.DenseVector;
import breeze.optimize.CachedDiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.LBFGS;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.feature.InstanceBlock$;
import org.apache.spark.ml.feature.StandardScalerModel$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.optim.aggregator.AFTBlockAggregator;
import org.apache.spark.ml.optim.loss.RDDLossFunction;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tea\u0001\u0002\f\u0018\u0001\tB\u0001\u0002\u0011\u0001\u0003\u0006\u0004%\t%\u0011\u0005\t1\u0002\u0011\t\u0011)A\u0005\u0005\")!\f\u0001C\u00017\")!\f\u0001C\u0001?\")\u0011\r\u0001C\u0001E\")q\r\u0001C\u0001Q\")!\u000f\u0001C\u0001g\")a\u000f\u0001C\u0001o\")Q\u0010\u0001C\u0001}\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\b\u0003;\u0001A\u0011AA\u0010\u0011\u001d\tI\u0003\u0001C)\u0003WAq!!\u0016\u0001\t\u0013\t9\u0006C\u0004\u0002*\u0002!\t%a+\t\u000f\u0005}\u0006\u0001\"\u0011\u0002B\u001e9\u0011q[\f\t\u0002\u0005egA\u0002\f\u0018\u0011\u0003\tY\u000e\u0003\u0004[%\u0011\u0005\u0011\u0011 \u0005\b\u0003w\u0014B\u0011IA\u007f\u0011%\u0011)AEA\u0001\n\u0013\u00119AA\u000bB\rR\u001bVO\u001d<jm\u0006d'+Z4sKN\u001c\u0018n\u001c8\u000b\u0005aI\u0012A\u0003:fOJ,7o]5p]*\u0011!dG\u0001\u0003[2T!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\u0002\u0001'\u0015\u00011%\r\u001b;!\u0015!SeJ\u0017/\u001b\u00059\u0012B\u0001\u0014\u0018\u0005%\u0011Vm\u001a:fgN|'\u000f\u0005\u0002)W5\t\u0011F\u0003\u0002+3\u00051A.\u001b8bY\u001eL!\u0001L\u0015\u0003\rY+7\r^8s!\t!\u0003\u0001\u0005\u0002%_%\u0011\u0001g\u0006\u0002\u001b\u0003\u001a#6+\u001e:wSZ\fGNU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003IIJ!aM\f\u00037\u00053EkU;sm&4\u0018\r\u001c*fOJ,7o]5p]B\u000b'/Y7t!\t)\u0004(D\u00017\u0015\t9\u0014$\u0001\u0003vi&d\u0017BA\u001d7\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\u0004\"a\u000f \u000e\u0003qR!!P\u000e\u0002\u0011%tG/\u001a:oC2L!a\u0010\u001f\u0003\u000f1{wmZ5oO\u0006\u0019Q/\u001b3\u0016\u0003\t\u0003\"a\u0011'\u000f\u0005\u0011S\u0005CA#I\u001b\u00051%BA$\"\u0003\u0019a$o\\8u})\t\u0011*A\u0003tG\u0006d\u0017-\u0003\u0002L\u0011\u00061\u0001K]3eK\u001aL!!\u0014(\u0003\rM#(/\u001b8h\u0015\tY\u0005\nK\u0002\u0002!Z\u0003\"!\u0015+\u000e\u0003IS!aU\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002V%\n)1+\u001b8dK\u0006\nq+A\u00032]Yr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002Q-\u00061A(\u001b8jiz\"\"!\f/\t\u000b\u0001\u001b\u0001\u0019\u0001\")\u0007q\u0003f\u000bK\u0002\u0004!Z#\u0012!\f\u0015\u0004\tA3\u0016\u0001D:fi\u000e+gn]8s\u0007>dGCA2e\u001b\u0005\u0001\u0001\"B3\u0006\u0001\u0004\u0011\u0015!\u0002<bYV,\u0007fA\u0003Q-\u0006A2/\u001a;Rk\u0006tG/\u001b7f!J|'-\u00192jY&$\u0018.Z:\u0015\u0005\rL\u0007\"B3\u0007\u0001\u0004Q\u0007cA6m]6\t\u0001*\u0003\u0002n\u0011\n)\u0011I\u001d:bsB\u00111n\\\u0005\u0003a\"\u0013a\u0001R8vE2,\u0007f\u0001\u0004Q-\u0006y1/\u001a;Rk\u0006tG/\u001b7fg\u000e{G\u000e\u0006\u0002di\")Qm\u0002a\u0001\u0005\"\u001aq\u0001\u0015,\u0002\u001fM,GOR5u\u0013:$XM]2faR$\"a\u0019=\t\u000b\u0015D\u0001\u0019A=\u0011\u0005-T\u0018BA>I\u0005\u001d\u0011un\u001c7fC:D3\u0001\u0003)W\u0003)\u0019X\r^'bq&#XM\u001d\u000b\u0003G~Da!Z\u0005A\u0002\u0005\u0005\u0001cA6\u0002\u0004%\u0019\u0011Q\u0001%\u0003\u0007%sG\u000fK\u0002\n!Z\u000baa]3u)>dGcA2\u0002\u000e!)QM\u0003a\u0001]\"\u001a!\u0002\u0015,\u0002'M,G/Q4he\u0016<\u0017\r^5p]\u0012+\u0007\u000f\u001e5\u0015\u0007\r\f)\u0002\u0003\u0004f\u0017\u0001\u0007\u0011\u0011\u0001\u0015\u0005\u0017A\u000bI\"\t\u0002\u0002\u001c\u0005)!GL\u0019/a\u0005\u00192/\u001a;NCb\u0014En\\2l'&TX-\u00138N\u0005R\u00191-!\t\t\u000b\u0015d\u0001\u0019\u00018)\t1\u0001\u0016QE\u0011\u0003\u0003O\tQa\r\u00182]A\nQ\u0001\u001e:bS:$2ALA\u0017\u0011\u001d\ty#\u0004a\u0001\u0003c\tq\u0001Z1uCN,G\u000f\r\u0003\u00024\u0005\r\u0003CBA\u001b\u0003w\ty$\u0004\u0002\u00028)\u0019\u0011\u0011H\u000e\u0002\u0007M\fH.\u0003\u0003\u0002>\u0005]\"a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003\u0003\n\u0019\u0005\u0004\u0001\u0005\u0019\u0005\u0015\u0013QFA\u0001\u0002\u0003\u0015\t!a\u0012\u0003\u0007}#\u0013'\u0005\u0003\u0002J\u0005=\u0003cA6\u0002L%\u0019\u0011Q\n%\u0003\u000f9{G\u000f[5oOB\u00191.!\u0015\n\u0007\u0005M\u0003JA\u0002B]f\f\u0011\u0002\u001e:bS:LU\u000e\u001d7\u0015\u001d\u0005e\u0013qLA>\u0003\u007f\n\u0019)a\"\u0002&B)1.a\u0017kU&\u0019\u0011Q\f%\u0003\rQ+\b\u000f\\33\u0011\u001d\t\tG\u0004a\u0001\u0003G\n\u0011\"\u001b8ti\u0006t7-Z:\u0011\r\u0005\u0015\u00141NA8\u001b\t\t9GC\u0002\u0002jm\t1A\u001d3e\u0013\u0011\ti'a\u001a\u0003\u0007I#E\t\u0005\u0003\u0002r\u0005]TBAA:\u0015\r\t)(G\u0001\bM\u0016\fG/\u001e:f\u0013\u0011\tI(a\u001d\u0003\u0011%s7\u000f^1oG\u0016Da!! \u000f\u0001\u0004q\u0017aE1diV\fGN\u00117pG.\u001c\u0016N_3J]6\u0013\u0005BBAA\u001d\u0001\u0007!.A\u0006gK\u0006$XO]3t'R$\u0007BBAC\u001d\u0001\u0007!.\u0001\u0007gK\u0006$XO]3t\u001b\u0016\fg\u000eC\u0004\u0002\n:\u0001\r!a#\u0002\u0013=\u0004H/[7ju\u0016\u0014\bCBAG\u0003/\u000bY*\u0004\u0002\u0002\u0010*!\u0011\u0011SAJ\u0003!y\u0007\u000f^5nSj,'BAAK\u0003\u0019\u0011'/Z3{K&!\u0011\u0011TAH\u0005\u0015a%IR$T!\u0015\ti*!)o\u001b\t\tyJC\u0002+\u0003'KA!a)\u0002 \nYA)\u001a8tKZ+7\r^8s\u0011\u0019\t9K\u0004a\u0001U\u0006y\u0011N\\5uS\u0006d7k\u001c7vi&|g.A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\ti+!/\u0011\t\u0005=\u0016QW\u0007\u0003\u0003cSA!a-\u00028\u0005)A/\u001f9fg&!\u0011qWAY\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003w{\u0001\u0019AAW\u0003\u0019\u00198\r[3nC\"\u001aq\u0002\u0015,\u0002\t\r|\u0007/\u001f\u000b\u0004[\u0005\r\u0007bBAc!\u0001\u0007\u0011qY\u0001\u0006Kb$(/\u0019\t\u0005\u0003\u0013\fy-\u0004\u0002\u0002L*\u0019\u0011QZ\r\u0002\u000bA\f'/Y7\n\t\u0005E\u00171\u001a\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001a\u0001\u0003\u0015,)\u0007\u0001\u0001f+A\u000bB\rR\u001bVO\u001d<jm\u0006d'+Z4sKN\u001c\u0018n\u001c8\u0011\u0005\u0011\u00122c\u0002\n\u0002^\u0006\r\u0018\u0011\u001e\t\u0004W\u0006}\u0017bAAq\u0011\n1\u0011I\\=SK\u001a\u0004B!NAs[%\u0019\u0011q\u001d\u001c\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u00111^A{\u001b\t\tiO\u0003\u0003\u0002p\u0006E\u0018AA5p\u0015\t\t\u00190\u0001\u0003kCZ\f\u0017\u0002BA|\u0003[\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\"!!7\u0002\t1|\u0017\r\u001a\u000b\u0004[\u0005}\bB\u0002B\u0001)\u0001\u0007!)\u0001\u0003qCRD\u0007f\u0001\u000bQ-\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\u0002\t\u0005\u0005\u0017\u0011\t\"\u0004\u0002\u0003\u000e)!!qBAy\u0003\u0011a\u0017M\\4\n\t\tM!Q\u0002\u0002\u0007\u001f\nTWm\u0019;)\u0007I\u0001f\u000bK\u0002\u0012!Z\u0003"
)
public class AFTSurvivalRegression extends Regressor implements AFTSurvivalRegressionParams, DefaultParamsWritable {
   private final String uid;
   private Param censorCol;
   private DoubleArrayParam quantileProbabilities;
   private Param quantilesCol;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private BooleanParam fitIntercept;
   private DoubleParam tol;
   private IntParam maxIter;

   public static AFTSurvivalRegression load(final String path) {
      return AFTSurvivalRegression$.MODULE$.load(path);
   }

   public static MLReader read() {
      return AFTSurvivalRegression$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getCensorCol() {
      return AFTSurvivalRegressionParams.getCensorCol$(this);
   }

   public double[] getQuantileProbabilities() {
      return AFTSurvivalRegressionParams.getQuantileProbabilities$(this);
   }

   public String getQuantilesCol() {
      return AFTSurvivalRegressionParams.getQuantilesCol$(this);
   }

   public boolean hasQuantilesCol() {
      return AFTSurvivalRegressionParams.hasQuantilesCol$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting) {
      return AFTSurvivalRegressionParams.validateAndTransformSchema$(this, schema, fitting);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final Param censorCol() {
      return this.censorCol;
   }

   public final DoubleArrayParam quantileProbabilities() {
      return this.quantileProbabilities;
   }

   public final Param quantilesCol() {
      return this.quantilesCol;
   }

   public final void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$censorCol_$eq(final Param x$1) {
      this.censorCol = x$1;
   }

   public final void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantileProbabilities_$eq(final DoubleArrayParam x$1) {
      this.quantileProbabilities = x$1;
   }

   public final void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantilesCol_$eq(final Param x$1) {
      this.quantilesCol = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public AFTSurvivalRegression setCensorCol(final String value) {
      return (AFTSurvivalRegression)this.set(this.censorCol(), value);
   }

   public AFTSurvivalRegression setQuantileProbabilities(final double[] value) {
      return (AFTSurvivalRegression)this.set(this.quantileProbabilities(), value);
   }

   public AFTSurvivalRegression setQuantilesCol(final String value) {
      return (AFTSurvivalRegression)this.set(this.quantilesCol(), value);
   }

   public AFTSurvivalRegression setFitIntercept(final boolean value) {
      return (AFTSurvivalRegression)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public AFTSurvivalRegression setMaxIter(final int value) {
      return (AFTSurvivalRegression)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public AFTSurvivalRegression setTol(final double value) {
      return (AFTSurvivalRegression)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public AFTSurvivalRegression setAggregationDepth(final int value) {
      return (AFTSurvivalRegression)this.set(this.aggregationDepth(), BoxesRunTime.boxToInteger(value));
   }

   public AFTSurvivalRegression setMaxBlockSizeInMB(final double value) {
      return (AFTSurvivalRegression)this.set(this.maxBlockSizeInMB(), BoxesRunTime.boxToDouble(value));
   }

   public AFTSurvivalRegressionModel train(final Dataset dataset) {
      return (AFTSurvivalRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         label39: {
            instr.logPipelineStage(this);
            instr.logDataset(dataset);
            instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.censorCol(), this.predictionCol(), this.quantilesCol(), this.fitIntercept(), this.maxIter(), this.tol(), this.aggregationDepth(), this.maxBlockSizeInMB()}));
            instr.logNamedValue("quantileProbabilities.size", (long)((double[])this.$(this.quantileProbabilities())).length);
            StorageLevel var10000 = dataset.storageLevel();
            StorageLevel var4 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var4 == null) {
                  break label39;
               }
            } else if (var10000.equals(var4)) {
               break label39;
            }

            instr.logWarning((Function0)(() -> "Input instances will be standardized, blockified to blocks, and then cached during training. Be careful of double caching!"));
         }

         Column casted = org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.censorCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
         Column validatedCensorCol = org.apache.spark.sql.functions..MODULE$.when(casted.isNull().$bar$bar(casted.isNaN()), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.lit("Censors MUST NOT be Null or NaN"))).when(casted.$eq$bang$eq(BoxesRunTime.boxToInteger(0)).$amp$amp(casted.$eq$bang$eq(BoxesRunTime.boxToInteger(1))), org.apache.spark.sql.functions..MODULE$.raise_error(org.apache.spark.sql.functions..MODULE$.concat(.MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.lit("Censors MUST be in {0, 1}, but got "), casted}))))).otherwise(casted);
         RDD instances = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol())), validatedCensorCol, DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
                  Object l = ((SeqOps)var3.get()).apply(0);
                  Object c = ((SeqOps)var3.get()).apply(1);
                  Object v = ((SeqOps)var3.get()).apply(2);
                  if (l instanceof Double) {
                     double var7 = BoxesRunTime.unboxToDouble(l);
                     if (c instanceof Double) {
                        double var9 = BoxesRunTime.unboxToDouble(c);
                        if (v instanceof Vector) {
                           Vector var11 = (Vector)v;
                           return new Instance(var7, var9, var11);
                        }
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Instance.class)).setName("training instances");
         SummarizerBuffer summarizer = (SummarizerBuffer)instances.treeAggregate(Summarizer$.MODULE$.createSummarizerBuffer(.MODULE$.wrapRefArray((Object[])(new String[]{"mean", "std", "count"}))), (c, i) -> c.add(i.features()), (c1, c2) -> c1.merge(c2), BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(SummarizerBuffer.class));
         double[] featuresMean = summarizer.mean().toArray();
         double[] featuresStd = summarizer.std().toArray();
         int numFeatures = featuresStd.length;
         instr.logNumFeatures((long)numFeatures);
         instr.logNumExamples(summarizer.count());
         double actualBlockSizeInMB = BoxesRunTime.unboxToDouble(this.$(this.maxBlockSizeInMB()));
         if (actualBlockSizeInMB == (double)0) {
            actualBlockSizeInMB = InstanceBlock$.MODULE$.DefaultBlockSizeInMB();
            scala.Predef..MODULE$.require(actualBlockSizeInMB > (double)0, () -> "inferred actual BlockSizeInMB must > 0");
            instr.logNamedValue("actualBlockSizeInMB", Double.toString(actualBlockSizeInMB));
         }

         if (!BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numFeatures).exists((JFunction1.mcZI.sp)(i) -> featuresStd[i] == (double)0.0F && summarizer.mean().apply(i) != (double)0.0F)) {
            instr.logWarning((Function0)(() -> "Fitting AFTSurvivalRegressionModel without intercept on dataset with constant nonzero column, Spark MLlib outputs zero coefficients for constant nonzero columns. This behavior is different from R survival::survreg."));
         }

         LBFGS optimizer = new LBFGS(BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.linalg.DenseVector..MODULE$.space_Double());
         double[] initialSolution = (double[])scala.Array..MODULE$.ofDim(numFeatures + 2, scala.reflect.ClassTag..MODULE$.Double());
         Tuple2 var17 = this.trainImpl(instances, actualBlockSizeInMB, featuresStd, featuresMean, optimizer, initialSolution);
         if (var17 != null) {
            double[] rawCoefficients = (double[])var17._1();
            double[] objectiveHistory = (double[])var17._2();
            Tuple2 var16 = new Tuple2(rawCoefficients, objectiveHistory);
            double[] rawCoefficientsx = (double[])var16._1();
            double[] var21 = (double[])var16._2();
            if (rawCoefficientsx == null) {
               MLUtils$.MODULE$.optimizerFailed(instr, optimizer.getClass());
            }

            double[] coefficientArray = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> featuresStd[i] != (double)0 ? rawCoefficientsx[i] / featuresStd[i] : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
            Vector coefficients = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(coefficientArray);
            double intercept = rawCoefficientsx[numFeatures];
            double scale = scala.math.package..MODULE$.exp(rawCoefficientsx[numFeatures + 1]);
            return new AFTSurvivalRegressionModel(this.uid(), coefficients, intercept, scale);
         } else {
            throw new MatchError(var17);
         }
      });
   }

   private Tuple2 trainImpl(final RDD instances, final double actualBlockSizeInMB, final double[] featuresStd, final double[] featuresMean, final LBFGS optimizer, final double[] initialSolution) {
      int numFeatures = featuresStd.length;
      double[] inverseStd = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(featuresStd), (JFunction1.mcDD.sp)(std) -> std != (double)0 ? (double)1.0F / std : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
      double[] scaledMean = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> inverseStd[i] * featuresMean[i], scala.reflect.ClassTag..MODULE$.Double());
      Broadcast bcInverseStd = instances.context().broadcast(inverseStd, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)));
      Broadcast bcScaledMean = instances.context().broadcast(scaledMean, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)));
      RDD scaled = instances.mapPartitions((iter) -> {
         Function1 func = StandardScalerModel$.MODULE$.getTransformFunc((double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), (double[])bcInverseStd.value(), false, true);
         return iter.map((x0$1) -> {
            if (x0$1 != null) {
               double label = x0$1.label();
               double weight = x0$1.weight();
               Vector vec = x0$1.features();
               return new Instance(label, weight, (Vector)func.apply(vec));
            } else {
               throw new MatchError(x0$1);
            }
         });
      }, instances.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Instance.class));
      long maxMemUsage = (long)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(actualBlockSizeInMB * (double)1024L * (double)1024L));
      RDD blocks = InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(scaled, maxMemUsage).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()).setName("training blocks (blockSizeInMB=" + actualBlockSizeInMB + ")");
      Function1 getAggregatorFunc = (x$2) -> new AFTBlockAggregator(bcScaledMean, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), x$2);
      RDDLossFunction costFun = new RDDLossFunction(blocks, getAggregatorFunc, scala.None..MODULE$, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(InstanceBlock.class), scala.reflect.ClassTag..MODULE$.apply(AFTBlockAggregator.class));
      if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, initialSolution, 1, scaledMean, 1);
         initialSolution[numFeatures] += adapt;
      }

      Iterator states = optimizer.iterations(new CachedDiffFunction(costFun, breeze.linalg.DenseVector..MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double())), new DenseVector.mcD.sp(initialSolution));
      ArrayBuilder arrayBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      FirstOrderMinimizer.State state = null;

      while(states.hasNext()) {
         state = (FirstOrderMinimizer.State)states.next();
         arrayBuilder.$plus$eq(BoxesRunTime.boxToDouble(state.adjustedValue()));
      }

      blocks.unpersist(blocks.unpersist$default$1());
      bcInverseStd.destroy();
      bcScaledMean.destroy();
      double[] solution = state == null ? null : ((DenseVector)state.x()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && solution != null) {
         double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.getBLAS(numFeatures).ddot(numFeatures, solution, 1, scaledMean, 1);
         solution[numFeatures] -= adapt;
      }

      return new Tuple2(solution, arrayBuilder.result());
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema, true);
   }

   public AFTSurvivalRegression copy(final ParamMap extra) {
      return (AFTSurvivalRegression)this.defaultCopy(extra);
   }

   public AFTSurvivalRegression(final String uid) {
      this.uid = uid;
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasFitIntercept.$init$(this);
      HasAggregationDepth.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      AFTSurvivalRegressionParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public AFTSurvivalRegression() {
      this(Identifiable$.MODULE$.randomUID("aftSurvReg"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
