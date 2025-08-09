package org.apache.spark.mllib.classification;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.optimization.L1Updater;
import org.apache.spark.mllib.optimization.LBFGS;
import org.apache.spark.mllib.optimization.LogisticGradient;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import org.apache.spark.mllib.optimization.Updater;
import org.apache.spark.mllib.regression.GeneralizedLinearAlgorithm;
import org.apache.spark.mllib.util.DataValidators$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001\u0002\u0007\u000e\u0001aAQ!\r\u0001\u0005\u0002IBq\u0001\u000e\u0001C\u0002\u0013\u0005S\u0007\u0003\u0004F\u0001\u0001\u0006IA\u000e\u0005\b\u000f\u0002\u0011\r\u0011\"\u0015I\u0011\u0019\t\u0007\u0001)A\u0005\u0013\")!\r\u0001C\u0005G\")A\r\u0001C\u0001K\")q\u000e\u0001C)a\")a\u0010\u0001C!\u007f\"1a\u0010\u0001C!\u0003\u000bAaA \u0001\u0005\n\u00055!a\u0007'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:<\u0016\u000e\u001e5M\u0005\u001a;5K\u0003\u0002\u000f\u001f\u0005q1\r\\1tg&4\u0017nY1uS>t'B\u0001\t\u0012\u0003\u0015iG\u000e\\5c\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7\u0001A\n\u0004\u0001e\u0019\u0003c\u0001\u000e\u001e?5\t1D\u0003\u0002\u001d\u001f\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u0005yY\"AG$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3be\u0006cwm\u001c:ji\"l\u0007C\u0001\u0011\"\u001b\u0005i\u0011B\u0001\u0012\u000e\u0005]aunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002%]9\u0011Qe\u000b\b\u0003M%j\u0011a\n\u0006\u0003Q]\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051j\u0013a\u00029bG.\fw-\u001a\u0006\u0002U%\u0011q\u0006\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Y5\na\u0001P5oSRtD#A\u001a\u0011\u0005\u0001\u0002\u0011!C8qi&l\u0017N_3s+\u00051\u0004CA\u001c;\u001b\u0005A$BA\u001d\u0010\u00031y\u0007\u000f^5nSj\fG/[8o\u0013\tY\u0004HA\u0003M\u0005\u001a;5\u000bK\u0002\u0003{\r\u0003\"AP!\u000e\u0003}R!\u0001Q\t\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002C\u007f\t)1+\u001b8dK\u0006\nA)A\u00032]Er\u0003'\u0001\u0006paRLW.\u001b>fe\u0002B3aA\u001fD\u0003)1\u0018\r\\5eCR|'o]\u000b\u0002\u0013B\u0019!jT)\u000e\u0003-S!\u0001T'\u0002\u0013%lW.\u001e;bE2,'B\u0001(.\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003!.\u0013A\u0001T5tiB!!kU+_\u001b\u0005i\u0013B\u0001+.\u0005%1UO\\2uS>t\u0017\u0007E\u0002W3nk\u0011a\u0016\u0006\u00031F\t1A\u001d3e\u0013\tQvKA\u0002S\t\u0012\u0003\"A\u0007/\n\u0005u[\"\u0001\u0004'bE\u0016dW\r\u001a)pS:$\bC\u0001*`\u0013\t\u0001WFA\u0004C_>dW-\u00198\u0002\u0017Y\fG.\u001b3bi>\u00148\u000fI\u0001\u0014[VdG/\u001b'bE\u0016dg+\u00197jI\u0006$xN]\u000b\u0002#\u0006i1/\u001a;Ok6\u001cE.Y:tKN$\"AZ4\u000e\u0003\u0001AQ\u0001[\u0004A\u0002%\f!B\\;n\u00072\f7o]3t!\t\u0011&.\u0003\u0002l[\t\u0019\u0011J\u001c;)\u0007\u001diT.I\u0001o\u0003\u0015\tdf\r\u00181\u0003-\u0019'/Z1uK6{G-\u001a7\u0015\u0007}\t\u0018\u0010C\u0003s\u0011\u0001\u00071/A\u0004xK&<\u0007\u000e^:\u0011\u0005Q<X\"A;\u000b\u0005Y|\u0011A\u00027j]\u0006dw-\u0003\u0002yk\n1a+Z2u_JDQA\u001f\u0005A\u0002m\f\u0011\"\u001b8uKJ\u001cW\r\u001d;\u0011\u0005Ic\u0018BA?.\u0005\u0019!u.\u001e2mK\u0006\u0019!/\u001e8\u0015\u0007}\t\t\u0001\u0003\u0004\u0002\u0004%\u0001\r!V\u0001\u0006S:\u0004X\u000f\u001e\u000b\u0006?\u0005\u001d\u0011\u0011\u0002\u0005\u0007\u0003\u0007Q\u0001\u0019A+\t\r\u0005-!\u00021\u0001t\u00039Ig.\u001b;jC2<V-[4iiN$raHA\b\u0003#\t\u0019\u0002\u0003\u0004\u0002\u0004-\u0001\r!\u0016\u0005\u0007\u0003\u0017Y\u0001\u0019A:\t\r\u0005U1\u00021\u0001_\u0003M)8/\u001a:TkB\u0004H.[3e/\u0016Lw\r\u001b;tQ\r\u0001Qh\u0011"
)
public class LogisticRegressionWithLBFGS extends GeneralizedLinearAlgorithm {
   private final LBFGS optimizer;
   private final List validators;

   public LBFGS optimizer() {
      return this.optimizer;
   }

   public List validators() {
      return this.validators;
   }

   private Function1 multiLabelValidator() {
      return (data) -> BoxesRunTime.boxToBoolean($anonfun$multiLabelValidator$1(this, data));
   }

   public LogisticRegressionWithLBFGS setNumClasses(final int numClasses) {
      .MODULE$.require(numClasses > 1);
      this.numOfLinearPredictor_$eq(numClasses - 1);
      if (numClasses > 2) {
         this.optimizer().setGradient(new LogisticGradient(numClasses));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this;
   }

   public LogisticRegressionModel createModel(final Vector weights, final double intercept) {
      return this.numOfLinearPredictor() == 1 ? new LogisticRegressionModel(weights, intercept) : new LogisticRegressionModel(weights, intercept, this.numFeatures(), this.numOfLinearPredictor() + 1);
   }

   public LogisticRegressionModel run(final RDD input) {
      return this.run(input, this.generateInitialWeights(input), false);
   }

   public LogisticRegressionModel run(final RDD input, final Vector initialWeights) {
      return this.run(input, initialWeights, true);
   }

   private LogisticRegressionModel run(final RDD input, final Vector initialWeights, final boolean userSuppliedWeights) {
      if (this.numOfLinearPredictor() == 1) {
         Updater var5 = this.optimizer().getUpdater();
         if (var5 instanceof SquaredL2Updater) {
            return this.runWithMlLogisticRegression$1((double)0.0F, userSuppliedWeights, initialWeights, input);
         } else {
            return var5 instanceof L1Updater ? this.runWithMlLogisticRegression$1((double)1.0F, userSuppliedWeights, initialWeights, input) : (LogisticRegressionModel)super.run(input, initialWeights);
         }
      } else {
         return (LogisticRegressionModel)super.run(input, initialWeights);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$multiLabelValidator$1(final LogisticRegressionWithLBFGS $this, final RDD data) {
      return $this.numOfLinearPredictor() > 1 ? BoxesRunTime.unboxToBoolean(DataValidators$.MODULE$.multiLabelValidator($this.numOfLinearPredictor() + 1).apply(data)) : BoxesRunTime.unboxToBoolean(DataValidators$.MODULE$.binaryLabelValidator().apply(data));
   }

   private final LogisticRegressionModel runWithMlLogisticRegression$1(final double elasticNetParam, final boolean userSuppliedWeights$1, final Vector initialWeights$1, final RDD input$1) {
      LogisticRegression lr = new LogisticRegression();
      lr.setRegParam(this.optimizer().getRegParam());
      lr.setElasticNetParam(elasticNetParam);
      lr.setStandardization(this.useFeatureScaling());
      if (userSuppliedWeights$1) {
         String uid = Identifiable$.MODULE$.randomUID("logreg-static");
         lr.setInitialModel(new org.apache.spark.ml.classification.LogisticRegressionModel(uid, new DenseMatrix(1, initialWeights$1.size(), initialWeights$1.toArray()), Vectors$.MODULE$.dense((double)1.0F, (Seq)scala.collection.immutable.Nil..MODULE$).asML(), 2, false));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      lr.setFitIntercept(this.addIntercept());
      lr.setMaxIter(this.optimizer().getNumIterations());
      lr.setTol(this.optimizer().getConvergenceTol());
      SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(input$1.context()).getOrCreate();
      RDD var10001 = input$1.map((x$3) -> x$3.asML(), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LogisticRegressionWithLBFGS.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.feature.LabeledPoint").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      Dataset df = spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
      org.apache.spark.ml.classification.LogisticRegressionModel mlLogisticRegressionModel = lr.train(df);
      Vector weights = Vectors$.MODULE$.dense(mlLogisticRegressionModel.coefficients().toArray());
      return this.createModel(weights, mlLogisticRegressionModel.intercept());
   }

   public LogisticRegressionWithLBFGS() {
      this.setFeatureScaling(true);
      this.optimizer = new LBFGS(new LogisticGradient(), new SquaredL2Updater());
      this.validators = new scala.collection.immutable..colon.colon(this.multiLabelValidator(), scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
