package org.apache.spark.ml.regression;

import java.io.IOException;
import java.util.UUID;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.BLAS.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamMap$;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasElasticNetParam;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasLoss;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.GeneralMLWritable;
import org.apache.spark.ml.util.GeneralMLWriter;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a\u0001\u0002\u0011\"\u00011B\u0001b\u0012\u0001\u0003\u0006\u0004%\t\u0005\u0013\u0005\t?\u0002\u0011\t\u0011)A\u0005\u0013\"A\u0011\r\u0001BC\u0002\u0013\u0005!\r\u0003\u0005g\u0001\t\u0005\t\u0015!\u00032\u0011!A\u0007A!b\u0001\n\u0003I\u0007\u0002C9\u0001\u0005\u0003\u0005\u000b\u0011\u00026\t\u0011M\u0004!Q1A\u0005\u0002%D\u0001b\u001e\u0001\u0003\u0002\u0003\u0006IA\u001b\u0005\u0007s\u0002!\ta\t>\t\u000fe\u0004A\u0011A\u0012\u0002\b!9\u0011\u0010\u0001C\u0001G\u0005=\u0001\"CA\t\u0001\t\u0007I\u0011IA\n\u0011!\tY\u0002\u0001Q\u0001\n\u0005U\u0001bBA\u000f\u0001\u0011\u0005\u0013q\u0004\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011!\tY\u0006\u0001C\u0001C\u0005u\u0003bBA3\u0001\u0011\u0005\u0013q\r\u0005\b\u0003[\u0002A\u0011IA8\u0011\u001d\t\u0019\t\u0001C!\u0003\u000bCq!a%\u0001\t\u0003\n)jB\u0004\u0002 \u0006B\t!!)\u0007\r\u0001\n\u0003\u0012AAR\u0011\u0019Ih\u0003\"\u0001\u0002B\"9\u00111\u0019\f\u0005B\u0005\u0015\u0007bBAh-\u0011\u0005\u0013\u0011\u001b\u0004\u0007\u000334B!a7\t\reTB\u0011AAo\u0011%\t\u0019O\u0007b\u0001\n\u0013\t)\u000f\u0003\u0005\u0002rj\u0001\u000b\u0011BAt\u0011\u001d\tyM\u0007C!\u0003gD\u0011\"a>\u0017\u0003\u0003%I!!?\u0003+1Kg.Z1s%\u0016<'/Z:tS>tWj\u001c3fY*\u0011!eI\u0001\u000be\u0016<'/Z:tS>t'B\u0001\u0013&\u0003\tiGN\u0003\u0002'O\u0005)1\u000f]1sW*\u0011\u0001&K\u0001\u0007CB\f7\r[3\u000b\u0003)\n1a\u001c:h\u0007\u0001\u0019R\u0001A\u00179w\u0005\u0003BAL\u00182o5\t\u0011%\u0003\u00021C\ty!+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u00023k5\t1G\u0003\u00025G\u00051A.\u001b8bY\u001eL!AN\u001a\u0003\rY+7\r^8s!\tq\u0003\u0001\u0005\u0002/s%\u0011!(\t\u0002\u0017\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:\u0004\u0016M]1ngB\u0011AhP\u0007\u0002{)\u0011ahI\u0001\u0005kRLG.\u0003\u0002A{\t\tr)\u001a8fe\u0006dW\nT,sSR\f'\r\\3\u0011\u0007q\u0012E)\u0003\u0002D{\t\u0011\u0002*Y:Ue\u0006Lg.\u001b8h'VlW.\u0019:z!\tqS)\u0003\u0002GC\tyB*\u001b8fCJ\u0014Vm\u001a:fgNLwN\u001c+sC&t\u0017N\\4Tk6l\u0017M]=\u0002\u0007ULG-F\u0001J!\tQ5K\u0004\u0002L#B\u0011AjT\u0007\u0002\u001b*\u0011ajK\u0001\u0007yI|w\u000e\u001e \u000b\u0003A\u000bQa]2bY\u0006L!AU(\u0002\rA\u0013X\rZ3g\u0013\t!VK\u0001\u0004TiJLgn\u001a\u0006\u0003%>C3!A,^!\tA6,D\u0001Z\u0015\tQV%\u0001\u0006b]:|G/\u0019;j_:L!\u0001X-\u0003\u000bMKgnY3\"\u0003y\u000bQ!\r\u00185]A\nA!^5eA!\u001a!aV/\u0002\u0019\r|WM\u001a4jG&,g\u000e^:\u0016\u0003EB3aA,eC\u0005)\u0017!\u0002\u001a/a9\u0002\u0014!D2pK\u001a4\u0017nY5f]R\u001c\b\u0005K\u0002\u0005/\u0012\f\u0011\"\u001b8uKJ\u001cW\r\u001d;\u0016\u0003)\u0004\"a\u001b7\u000e\u0003=K!!\\(\u0003\r\u0011{WO\u00197fQ\r)qk\\\u0011\u0002a\u0006)\u0011GL\u001a/a\u0005Q\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0011)\u0007\u00199v.A\u0003tG\u0006dW\rK\u0002\b/V\f\u0013A^\u0001\u0006e9\u001ad\u0006M\u0001\u0007g\u000e\fG.\u001a\u0011)\u0007!9V/\u0001\u0004=S:LGO\u0010\u000b\u0007omlx0a\u0001\t\u000b\u001dK\u0001\u0019A%)\u0007m<V\fC\u0003b\u0013\u0001\u0007\u0011\u0007K\u0002~/\u0012DQ\u0001[\u0005A\u0002)D3a`,p\u0011\u0015\u0019\u0018\u00021\u0001kQ\u0011\t\u0019aV;\u0015\u000f]\nI!a\u0003\u0002\u000e!)qI\u0003a\u0001\u0013\")\u0011M\u0003a\u0001c!)\u0001N\u0003a\u0001UR\tq'A\u0006ok64U-\u0019;ve\u0016\u001cXCAA\u000b!\rY\u0017qC\u0005\u0004\u00033y%aA%oi\u0006aa.^7GK\u0006$XO]3tA\u000591/^7nCJLX#\u0001#)\t99\u00161E\u0011\u0003\u0003K\tQ!\r\u00186]A\n\u0001\"\u001a<bYV\fG/\u001a\u000b\u0005\u0003W\t\t\u0004E\u0002/\u0003[I1!a\f\"\u0005]a\u0015N\\3beJ+wM]3tg&|gnU;n[\u0006\u0014\u0018\u0010C\u0004\u00024=\u0001\r!!\u000e\u0002\u000f\u0011\fG/Y:fiB\"\u0011qGA$!\u0019\tI$a\u0010\u0002D5\u0011\u00111\b\u0006\u0004\u0003{)\u0013aA:rY&!\u0011\u0011IA\u001e\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0012\u0002H1\u0001A\u0001DA%\u0003c\t\t\u0011!A\u0003\u0002\u0005-#aA0%iE!\u0011QJA*!\rY\u0017qJ\u0005\u0004\u0003#z%a\u0002(pi\"Lgn\u001a\t\u0004W\u0006U\u0013bAA,\u001f\n\u0019\u0011I\\=)\u0007=9F-\u0001\u0011gS:$7+^7nCJLXj\u001c3fY\u0006sG\r\u0015:fI&\u001cG/[8o\u0007>dGCAA0!\u0015Y\u0017\u0011M\u001cJ\u0013\r\t\u0019g\u0014\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u000fA\u0014X\rZ5diR\u0019!.!\u001b\t\r\u0005-\u0014\u00031\u00012\u0003!1W-\u0019;ve\u0016\u001c\u0018\u0001B2paf$2aNA9\u0011\u001d\t\u0019H\u0005a\u0001\u0003k\nQ!\u001a=ue\u0006\u0004B!a\u001e\u0002~5\u0011\u0011\u0011\u0010\u0006\u0004\u0003w\u001a\u0013!\u00029be\u0006l\u0017\u0002BA@\u0003s\u0012\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004%]k\u0016!B<sSR,WCAAD!\ra\u0014\u0011R\u0005\u0004\u0003\u0017k$aD$f]\u0016\u0014\u0018\r\\'M/JLG/\u001a:)\tM9\u0016qR\u0011\u0003\u0003#\u000bQ!\r\u00187]A\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0013\"\"AcVAMC\t\tY*A\u00034]Ar\u0003\u0007K\u0002\u0001/>\fQ\u0003T5oK\u0006\u0014(+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002/-M9a#!*\u0002,\u0006E\u0006cA6\u0002(&\u0019\u0011\u0011V(\u0003\r\u0005s\u0017PU3g!\u0011a\u0014QV\u001c\n\u0007\u0005=VH\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!a-\u0002>6\u0011\u0011Q\u0017\u0006\u0005\u0003o\u000bI,\u0001\u0002j_*\u0011\u00111X\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002@\u0006U&\u0001D*fe&\fG.\u001b>bE2,GCAAQ\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005\u001d\u0007\u0003\u0002\u001f\u0002J^J1!a3>\u0005!iEJU3bI\u0016\u0014\b\u0006\u0002\rX\u0003\u001f\u000bA\u0001\\8bIR\u0019q'a5\t\r\u0005U\u0017\u00041\u0001J\u0003\u0011\u0001\u0018\r\u001e5)\te9\u0016q\u0012\u0002\u001c\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3m%\u0016\fG-\u001a:\u0014\u0007i\t9\r\u0006\u0002\u0002`B\u0019\u0011\u0011\u001d\u000e\u000e\u0003Y\t\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\u0005\u001d\b\u0003BAu\u0003_l!!a;\u000b\t\u00055\u0018\u0011X\u0001\u0005Y\u0006tw-C\u0002U\u0003W\f!b\u00197bgNt\u0015-\\3!)\r9\u0014Q\u001f\u0005\u0007\u0003+t\u0002\u0019A%\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\b\u0003BAu\u0003{LA!a@\u0002l\n1qJ\u00196fGRDCAF,\u0002\u0010\"\"QcVAH\u0001"
)
public class LinearRegressionModel extends RegressionModel implements LinearRegressionParams, GeneralMLWritable, HasTrainingSummary {
   private final String uid;
   private final Vector coefficients;
   private final double intercept;
   private final double scale;
   private final int numFeatures;
   private Option trainingSummary;
   private Param solver;
   private Param loss;
   private DoubleParam epsilon;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private Param weightCol;
   private BooleanParam standardization;
   private BooleanParam fitIntercept;
   private DoubleParam tol;
   private IntParam maxIter;
   private DoubleParam elasticNetParam;
   private DoubleParam regParam;

   public static LinearRegressionModel load(final String path) {
      return LinearRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LinearRegressionModel$.MODULE$.read();
   }

   public boolean hasSummary() {
      return HasTrainingSummary.hasSummary$(this);
   }

   public HasTrainingSummary setSummary(final Option summary) {
      return HasTrainingSummary.setSummary$(this, summary);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$regression$LinearRegressionParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public double getEpsilon() {
      return LinearRegressionParams.getEpsilon$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return LinearRegressionParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public final String getLoss() {
      return HasLoss.getLoss$(this);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final boolean getStandardization() {
      return HasStandardization.getStandardization$(this);
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

   public final double getElasticNetParam() {
      return HasElasticNetParam.getElasticNetParam$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
   }

   public final Param solver() {
      return this.solver;
   }

   public final Param loss() {
      return this.loss;
   }

   public final DoubleParam epsilon() {
      return this.epsilon;
   }

   public final void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$loss_$eq(final Param x$1) {
      this.loss = x$1;
   }

   public final void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$epsilon_$eq(final DoubleParam x$1) {
      this.epsilon = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasLoss$_setter_$loss_$eq(final Param x$1) {
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final BooleanParam standardization() {
      return this.standardization;
   }

   public final void org$apache$spark$ml$param$shared$HasStandardization$_setter_$standardization_$eq(final BooleanParam x$1) {
      this.standardization = x$1;
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

   public final DoubleParam elasticNetParam() {
      return this.elasticNetParam;
   }

   public final void org$apache$spark$ml$param$shared$HasElasticNetParam$_setter_$elasticNetParam_$eq(final DoubleParam x$1) {
      this.elasticNetParam = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Vector coefficients() {
      return this.coefficients;
   }

   public double intercept() {
      return this.intercept;
   }

   public double scale() {
      return this.scale;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public LinearRegressionTrainingSummary summary() {
      return (LinearRegressionTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public LinearRegressionSummary evaluate(final Dataset dataset) {
      Tuple2 var4 = this.findSummaryModelAndPredictionCol();
      if (var4 != null) {
         LinearRegressionModel summaryModel = (LinearRegressionModel)var4._1();
         String predictionColName = (String)var4._2();
         Tuple2 var3 = new Tuple2(summaryModel, predictionColName);
         LinearRegressionModel summaryModel = (LinearRegressionModel)var3._1();
         String predictionColName = (String)var3._2();
         return new LinearRegressionSummary(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), summaryModel, new double[]{(double)0.0F});
      } else {
         throw new MatchError(var4);
      }
   }

   public Tuple2 findSummaryModelAndPredictionCol() {
      String var2 = (String)this.$(this.predictionCol());
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 0:
            if ("".equals(var2)) {
               String predictionColName = "prediction_" + UUID.randomUUID().toString();
               return new Tuple2(this.copy(ParamMap$.MODULE$.empty()).setPredictionCol(predictionColName), predictionColName);
            }
         default:
            return new Tuple2(this, var2);
      }
   }

   public double predict(final Vector features) {
      return .MODULE$.dot(features, this.coefficients()) + this.intercept();
   }

   public LinearRegressionModel copy(final ParamMap extra) {
      LinearRegressionModel newModel = (LinearRegressionModel)this.copyValues(new LinearRegressionModel(this.uid(), this.coefficients(), this.intercept()), extra);
      return (LinearRegressionModel)((Model)newModel.setSummary(this.trainingSummary())).setParent(this.parent());
   }

   public GeneralMLWriter write() {
      return new GeneralMLWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "LinearRegressionModel: uid=" + var10000 + ", numFeatures=" + this.numFeatures();
   }

   public LinearRegressionModel(final String uid, final Vector coefficients, final double intercept, final double scale) {
      this.uid = uid;
      this.coefficients = coefficients;
      this.intercept = intercept;
      this.scale = scale;
      HasRegParam.$init$(this);
      HasElasticNetParam.$init$(this);
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasFitIntercept.$init$(this);
      HasStandardization.$init$(this);
      HasWeightCol.$init$(this);
      HasSolver.$init$(this);
      HasAggregationDepth.$init$(this);
      HasLoss.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      LinearRegressionParams.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      this.numFeatures = coefficients.size();
      Statics.releaseFence();
   }

   public LinearRegressionModel(final String uid, final Vector coefficients, final double intercept) {
      this(uid, coefficients, intercept, (double)1.0F);
   }

   public LinearRegressionModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), Double.NaN, Double.NaN);
   }

   private static class LinearRegressionModelReader extends MLReader {
      private final String className = LinearRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public LinearRegressionModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().format("parquet").load(dataPath);
         Tuple2 var9 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var9 == null) {
            throw new MatchError(var9);
         } else {
            int majorVersion = var9._1$mcI$sp();
            int minorVersion = var9._2$mcI$sp();
            Tuple2.mcII.sp var8 = new Tuple2.mcII.sp(majorVersion, minorVersion);
            int majorVersion = ((Tuple2)var8)._1$mcI$sp();
            int minorVersion = ((Tuple2)var8)._2$mcI$sp();
            LinearRegressionModel var10000;
            if (majorVersion >= 2 && (majorVersion != 2 || minorVersion > 2)) {
               Row var27 = (Row)data.select("intercept", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"coefficients", "scale"}))).head();
               if (var27 == null) {
                  throw new MatchError(var27);
               }

               Some var28 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var27);
               if (var28.isEmpty() || var28.get() == null || ((SeqOps)var28.get()).lengthCompare(3) != 0) {
                  throw new MatchError(var27);
               }

               Object intercept = ((SeqOps)var28.get()).apply(0);
               Object coefficients = ((SeqOps)var28.get()).apply(1);
               Object scale = ((SeqOps)var28.get()).apply(2);
               if (!(intercept instanceof Double)) {
                  throw new MatchError(var27);
               }

               double var32 = BoxesRunTime.unboxToDouble(intercept);
               if (!(coefficients instanceof Vector)) {
                  throw new MatchError(var27);
               }

               Vector var34 = (Vector)coefficients;
               if (!(scale instanceof Double)) {
                  throw new MatchError(var27);
               }

               double var35 = BoxesRunTime.unboxToDouble(scale);
               Tuple3 var26 = new Tuple3(BoxesRunTime.boxToDouble(var32), var34, BoxesRunTime.boxToDouble(var35));
               double intercept = BoxesRunTime.unboxToDouble(var26._1());
               Vector coefficients = (Vector)var26._2();
               double scale = BoxesRunTime.unboxToDouble(var26._3());
               var10000 = new LinearRegressionModel(metadata.uid(), coefficients, intercept, scale);
            } else {
               Row var16 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"coefficients"}))).select("intercept", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"coefficients"}))).head();
               if (var16 == null) {
                  throw new MatchError(var16);
               }

               Some var17 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var16);
               if (var17.isEmpty() || var17.get() == null || ((SeqOps)var17.get()).lengthCompare(2) != 0) {
                  throw new MatchError(var16);
               }

               Object intercept = ((SeqOps)var17.get()).apply(0);
               Object coefficients = ((SeqOps)var17.get()).apply(1);
               if (!(intercept instanceof Double)) {
                  throw new MatchError(var16);
               }

               double var20 = BoxesRunTime.unboxToDouble(intercept);
               if (!(coefficients instanceof Vector)) {
                  throw new MatchError(var16);
               }

               Vector var22 = (Vector)coefficients;
               Tuple2 var15 = new Tuple2(BoxesRunTime.boxToDouble(var20), var22);
               double intercept = var15._1$mcD$sp();
               Vector coefficients = (Vector)var15._2();
               var10000 = new LinearRegressionModel(metadata.uid(), coefficients, intercept);
            }

            LinearRegressionModel model = var10000;
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         }
      }

      public LinearRegressionModelReader() {
      }
   }
}
