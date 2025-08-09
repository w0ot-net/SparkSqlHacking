package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.ann.FeedForwardTopology;
import org.apache.spark.ml.ann.FeedForwardTopology$;
import org.apache.spark.ml.ann.FeedForwardTrainer;
import org.apache.spark.ml.ann.TopologyModel;
import org.apache.spark.ml.feature.OneHotEncoderModel;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntArrayParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasBlockSize;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg\u0001B\u000e\u001d\u0001\u001dB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0005\u0011\u0005\t/\u0002\u0011\t\u0011)A\u0005\u0003\")\u0011\f\u0001C\u00015\")\u0011\f\u0001C\u0001=\")\u0001\r\u0001C\u0001C\")Q\u000e\u0001C\u0001]\")\u0011\u000f\u0001C\u0001e\")q\u000f\u0001C\u0001q\")1\u0010\u0001C\u0001y\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C!\u0003KAq!!\u000f\u0001\t#\nY\u0004C\u0004\u0002f\u0001!I!a\u001a\b\u000f\u0005\u0005E\u0004#\u0001\u0002\u0004\u001a11\u0004\bE\u0001\u0003\u000bCa!W\t\u0005\u0002\u0005\r\u0006BCAS#\t\u0007I\u0011\u0001\u000f\u0002(\"A\u00111W\t!\u0002\u0013\tI\u000b\u0003\u0006\u00026F\u0011\r\u0011\"\u0001\u001d\u0003OC\u0001\"a.\u0012A\u0003%\u0011\u0011\u0016\u0005\u000b\u0003s\u000b\"\u0019!C\u00019\u0005m\u0006\u0002CA`#\u0001\u0006I!!0\t\u000f\u0005\u0005\u0017\u0003\"\u0011\u0002D\"I\u00111Z\t\u0002\u0002\u0013%\u0011Q\u001a\u0002\u001f\u001bVdG/\u001b7bs\u0016\u0014\b+\u001a:dKB$(o\u001c8DY\u0006\u001c8/\u001b4jKJT!!\b\u0010\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011q\u0004I\u0001\u0003[2T!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0001FN\u001d\u0011\u000b%RCFM\u001a\u000e\u0003qI!a\u000b\u000f\u0003/A\u0013xNY1cS2L7\u000f^5d\u00072\f7o]5gS\u0016\u0014\bCA\u00171\u001b\u0005q#BA\u0018\u001f\u0003\u0019a\u0017N\\1mO&\u0011\u0011G\f\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u0005%\u0002\u0001CA\u00155\u0013\t)DDA\u0014Nk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\\\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d\u0007CA\u00158\u0013\tADD\u0001\u000eNk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\u001c)be\u0006l7\u000f\u0005\u0002;{5\t1H\u0003\u0002==\u0005!Q\u000f^5m\u0013\tq4HA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULG-F\u0001B!\t\u00115J\u0004\u0002D\u0013B\u0011AiR\u0007\u0002\u000b*\u0011aIJ\u0001\u0007yI|w\u000e\u001e \u000b\u0003!\u000bQa]2bY\u0006L!AS$\u0002\rA\u0013X\rZ3g\u0013\taUJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0015\u001eC3!A(V!\t\u00016+D\u0001R\u0015\t\u0011\u0006%\u0001\u0006b]:|G/\u0019;j_:L!\u0001V)\u0003\u000bMKgnY3\"\u0003Y\u000bQ!\r\u00186]A\nA!^5eA!\u001a!aT+\u0002\rqJg.\u001b;?)\t\u00114\fC\u0003@\u0007\u0001\u0007\u0011\tK\u0002\\\u001fVC3aA(V)\u0005\u0011\u0004f\u0001\u0003P+\u0006I1/\u001a;MCf,'o\u001d\u000b\u0003E\u000el\u0011\u0001\u0001\u0005\u0006I\u0016\u0001\r!Z\u0001\u0006m\u0006dW/\u001a\t\u0004M\u001eLW\"A$\n\u0005!<%!B!se\u0006L\bC\u00014k\u0013\tYwIA\u0002J]RD3!B(V\u00031\u0019X\r\u001e\"m_\u000e\\7+\u001b>f)\t\u0011w\u000eC\u0003e\r\u0001\u0007\u0011\u000eK\u0002\u0007\u001fV\u000b\u0011b]3u'>dg/\u001a:\u0015\u0005\t\u001c\b\"\u00023\b\u0001\u0004\t\u0005fA\u0004Pk\u0006\na/A\u00033]Ar\u0003'\u0001\u0006tKRl\u0015\r_%uKJ$\"AY=\t\u000b\u0011D\u0001\u0019A5)\u0007!yU+\u0001\u0004tKR$v\u000e\u001c\u000b\u0003EvDQ\u0001Z\u0005A\u0002y\u0004\"AZ@\n\u0007\u0005\u0005qI\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0013=+\u0016aB:fiN+W\r\u001a\u000b\u0004E\u0006%\u0001B\u00023\u000b\u0001\u0004\tY\u0001E\u0002g\u0003\u001bI1!a\u0004H\u0005\u0011auN\\4)\u0007)yU+A\ttKRLe.\u001b;jC2<V-[4iiN$2AYA\f\u0011\u0015!7\u00021\u0001-Q\rYq*^\u0001\fg\u0016$8\u000b^3q'&TX\rF\u0002c\u0003?AQ\u0001\u001a\u0007A\u0002yD3\u0001D(v\u0003\u0011\u0019w\u000e]=\u0015\u0007I\n9\u0003C\u0004\u0002*5\u0001\r!a\u000b\u0002\u000b\u0015DHO]1\u0011\t\u00055\u00121G\u0007\u0003\u0003_Q1!!\r\u001f\u0003\u0015\u0001\u0018M]1n\u0013\u0011\t)$a\f\u0003\u0011A\u000b'/Y7NCBD3!D(V\u0003\u0015!(/Y5o)\r\u0019\u0014Q\b\u0005\b\u0003\u007fq\u0001\u0019AA!\u0003\u001d!\u0017\r^1tKR\u0004D!a\u0011\u0002TA1\u0011QIA&\u0003\u001fj!!a\u0012\u000b\u0007\u0005%\u0003%A\u0002tc2LA!!\u0014\u0002H\t9A)\u0019;bg\u0016$\b\u0003BA)\u0003'b\u0001\u0001\u0002\u0007\u0002V\u0005u\u0012\u0011!A\u0001\u0006\u0003\t9FA\u0002`IE\nB!!\u0017\u0002`A\u0019a-a\u0017\n\u0007\u0005usIA\u0004O_RD\u0017N\\4\u0011\u0007\u0019\f\t'C\u0002\u0002d\u001d\u00131!\u00118z\u0003-\u0019'/Z1uK6{G-\u001a7\u0015\u000fM\nI'!\u001e\u0002z!9\u0011qH\bA\u0002\u0005-\u0004\u0007BA7\u0003c\u0002b!!\u0012\u0002L\u0005=\u0004\u0003BA)\u0003c\"A\"a\u001d\u0002j\u0005\u0005\t\u0011!B\u0001\u0003/\u00121a\u0018\u00133\u0011\u0019\t9h\u0004a\u0001Y\u00059q/Z5hQR\u001c\bbBA>\u001f\u0001\u0007\u0011QP\u0001\u0011_\nTWm\u0019;jm\u0016D\u0015n\u001d;pef\u00042AZ4\u007fQ\r\u0001q*V\u0001\u001f\u001bVdG/\u001b7bs\u0016\u0014\b+\u001a:dKB$(o\u001c8DY\u0006\u001c8/\u001b4jKJ\u0004\"!K\t\u0014\u000fE\t9)!$\u0002\u0014B\u0019a-!#\n\u0007\u0005-uI\u0001\u0004B]f\u0014VM\u001a\t\u0005u\u0005=%'C\u0002\u0002\u0012n\u0012Q\u0003R3gCVdG\u000fU1sC6\u001c(+Z1eC\ndW\r\u0005\u0003\u0002\u0016\u0006}UBAAL\u0015\u0011\tI*a'\u0002\u0005%|'BAAO\u0003\u0011Q\u0017M^1\n\t\u0005\u0005\u0016q\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003\u0007\u000bQ\u0001\u0014\"G\u000fN+\"!!+\u0011\t\u0005-\u0016\u0011W\u0007\u0003\u0003[SA!a,\u0002\u001c\u0006!A.\u00198h\u0013\ra\u0015QV\u0001\u0007\u0019\n3ui\u0015\u0011\u0002\u0005\u001d#\u0015aA$EA\u0005\u00012/\u001e9q_J$X\rZ*pYZ,'o]\u000b\u0003\u0003{\u0003BAZ4\u0002*\u0006\t2/\u001e9q_J$X\rZ*pYZ,'o\u001d\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004e\u0005\u0015\u0007BBAd3\u0001\u0007\u0011)\u0001\u0003qCRD\u0007fA\rPk\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u001a\t\u0005\u0003W\u000b\t.\u0003\u0003\u0002T\u00065&AB(cU\u0016\u001cG\u000fK\u0002\u0012\u001fVD3\u0001E(v\u0001"
)
public class MultilayerPerceptronClassifier extends ProbabilisticClassifier implements MultilayerPerceptronParams, DefaultParamsWritable {
   private final String uid;
   private IntArrayParam layers;
   private Param solver;
   private Param initialWeights;
   private IntParam blockSize;
   private DoubleParam stepSize;
   private DoubleParam tol;
   private IntParam maxIter;
   private LongParam seed;

   public static MultilayerPerceptronClassifier load(final String path) {
      return MultilayerPerceptronClassifier$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MultilayerPerceptronClassifier$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final int[] getLayers() {
      return MultilayerPerceptronParams.getLayers$(this);
   }

   public final Vector getInitialWeights() {
      return MultilayerPerceptronParams.getInitialWeights$(this);
   }

   public final int getBlockSize() {
      return HasBlockSize.getBlockSize$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final IntArrayParam layers() {
      return this.layers;
   }

   public final Param solver() {
      return this.solver;
   }

   public final Param initialWeights() {
      return this.initialWeights;
   }

   public final void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$layers_$eq(final IntArrayParam x$1) {
      this.layers = x$1;
   }

   public final void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$initialWeights_$eq(final Param x$1) {
      this.initialWeights = x$1;
   }

   public final IntParam blockSize() {
      return this.blockSize;
   }

   public final void org$apache$spark$ml$param$shared$HasBlockSize$_setter_$blockSize_$eq(final IntParam x$1) {
      this.blockSize = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
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

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public MultilayerPerceptronClassifier setLayers(final int[] value) {
      return (MultilayerPerceptronClassifier)this.set(this.layers(), value);
   }

   public MultilayerPerceptronClassifier setBlockSize(final int value) {
      return (MultilayerPerceptronClassifier)this.set(this.blockSize(), BoxesRunTime.boxToInteger(value));
   }

   public MultilayerPerceptronClassifier setSolver(final String value) {
      return (MultilayerPerceptronClassifier)this.set(this.solver(), value);
   }

   public MultilayerPerceptronClassifier setMaxIter(final int value) {
      return (MultilayerPerceptronClassifier)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public MultilayerPerceptronClassifier setTol(final double value) {
      return (MultilayerPerceptronClassifier)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public MultilayerPerceptronClassifier setSeed(final long value) {
      return (MultilayerPerceptronClassifier)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public MultilayerPerceptronClassifier setInitialWeights(final Vector value) {
      return (MultilayerPerceptronClassifier)this.set(this.initialWeights(), value);
   }

   public MultilayerPerceptronClassifier setStepSize(final double value) {
      return (MultilayerPerceptronClassifier)this.set(this.stepSize(), BoxesRunTime.boxToDouble(value));
   }

   public MultilayerPerceptronClassifier copy(final ParamMap extra) {
      return (MultilayerPerceptronClassifier)this.defaultCopy(extra);
   }

   public MultilayerPerceptronClassificationModel train(final Dataset dataset) {
      return (MultilayerPerceptronClassificationModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.predictionCol(), this.rawPredictionCol(), this.layers(), this.maxIter(), this.tol(), this.blockSize(), this.solver(), this.stepSize(), this.seed(), this.thresholds()}));
         int[] myLayers = (int[])this.$(this.layers());
         int labels = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps(myLayers)));
         instr.logNumClasses((long)labels);
         instr.logNumFeatures((long)BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.intArrayOps(myLayers))));
         Dataset validated = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkClassificationLabels((String)this.$(this.labelCol()), new Some(BoxesRunTime.boxToInteger(labels))).as("_validated_label_"), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol())).as("_validated_features_")})));
         String encodedLabelCol = "_encoded" + this.$(this.labelCol());
         OneHotEncoderModel encodeModel = (new OneHotEncoderModel(this.uid(), new int[]{labels})).setInputCols((String[])((Object[])(new String[]{"_validated_label_"}))).setOutputCols((String[])((Object[])(new String[]{encodedLabelCol}))).setDropLast(false);
         Dataset encodedDataset = encodeModel.transform(validated);
         RDD data = encodedDataset.select("_validated_features_", .MODULE$.wrapRefArray((Object[])(new String[]{encodedLabelCol}))).rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                  Object features = ((SeqOps)var3.get()).apply(0);
                  Object encodedLabel = ((SeqOps)var3.get()).apply(1);
                  if (features instanceof Vector) {
                     Vector var6 = (Vector)features;
                     if (encodedLabel instanceof Vector) {
                        Vector var7 = (Vector)encodedLabel;
                        return new Tuple2(var6, var7);
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         FeedForwardTopology topology = FeedForwardTopology$.MODULE$.multiLayerPerceptron(myLayers, true);
         FeedForwardTrainer trainer = new FeedForwardTrainer(topology, myLayers[0], BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps(myLayers))));
         if (this.isDefined(this.initialWeights())) {
            trainer.setWeights((Vector)this.$(this.initialWeights()));
         } else {
            trainer.setSeed(BoxesRunTime.unboxToLong(this.$(this.seed())));
         }

         label37: {
            label43: {
               Object var10000 = this.$(this.solver());
               String var13 = MultilayerPerceptronClassifier$.MODULE$.LBFGS();
               if (var10000 == null) {
                  if (var13 == null) {
                     break label43;
                  }
               } else if (var10000.equals(var13)) {
                  break label43;
               }

               var10000 = this.$(this.solver());
               String var14 = MultilayerPerceptronClassifier$.MODULE$.GD();
               if (var10000 == null) {
                  if (var14 != null) {
                     throw new IllegalArgumentException("The solver " + this.solver() + " is not supported by MultilayerPerceptronClassifier.");
                  }
               } else if (!var10000.equals(var14)) {
                  throw new IllegalArgumentException("The solver " + this.solver() + " is not supported by MultilayerPerceptronClassifier.");
               }

               trainer.SGDOptimizer().setNumIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setConvergenceTol(BoxesRunTime.unboxToDouble(this.$(this.tol()))).setStepSize(BoxesRunTime.unboxToDouble(this.$(this.stepSize())));
               break label37;
            }

            trainer.LBFGSOptimizer().setConvergenceTol(BoxesRunTime.unboxToDouble(this.$(this.tol()))).setNumIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter())));
         }

         trainer.setStackSize(BoxesRunTime.unboxToInt(this.$(this.blockSize())));
         Tuple2 var16 = trainer.train(data);
         if (var16 != null) {
            TopologyModel mlpModel = (TopologyModel)var16._1();
            double[] objectiveHistory = (double[])var16._2();
            Tuple2 var15 = new Tuple2(mlpModel, objectiveHistory);
            TopologyModel mlpModelx = (TopologyModel)var15._1();
            double[] objectiveHistoryx = (double[])var15._2();
            return this.createModel(dataset, mlpModelx.weights(), objectiveHistoryx);
         } else {
            throw new MatchError(var16);
         }
      });
   }

   private MultilayerPerceptronClassificationModel createModel(final Dataset dataset, final Vector weights, final double[] objectiveHistory) {
      MultilayerPerceptronClassificationModel model = (MultilayerPerceptronClassificationModel)this.copyValues(new MultilayerPerceptronClassificationModel(this.uid(), weights), this.copyValues$default$2());
      Tuple3 var7 = model.findSummaryModel();
      if (var7 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var7._1();
         String predictionColName = (String)var7._3();
         Tuple2 var6 = new Tuple2(summaryModel, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var6._1();
         String predictionColName = (String)var6._2();
         MultilayerPerceptronClassificationTrainingSummaryImpl summary = new MultilayerPerceptronClassificationTrainingSummaryImpl(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), "", objectiveHistory);
         return (MultilayerPerceptronClassificationModel)model.setSummary(new Some(summary));
      } else {
         throw new MatchError(var7);
      }
   }

   public MultilayerPerceptronClassifier(final String uid) {
      this.uid = uid;
      HasSeed.$init$(this);
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasStepSize.$init$(this);
      HasSolver.$init$(this);
      HasBlockSize.$init$(this);
      MultilayerPerceptronParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public MultilayerPerceptronClassifier() {
      this(Identifiable$.MODULE$.randomUID("mlpc"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
