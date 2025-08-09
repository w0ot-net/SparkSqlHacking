package org.apache.spark.mllib.classification;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.LogisticGradient;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import org.apache.spark.mllib.regression.GeneralizedLinearAlgorithm;
import org.apache.spark.mllib.util.DataValidators$;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uc\u0001B\f\u0019\u0001\rB\u0001\u0002\u0010\u0001\u0003\u0002\u0004%I!\u0010\u0005\t\u0005\u0002\u0011\t\u0019!C\u0005\u0007\"A\u0011\n\u0001B\u0001B\u0003&a\b\u0003\u0005K\u0001\t\u0005\r\u0011\"\u0003L\u0011!y\u0005A!a\u0001\n\u0013\u0001\u0006\u0002\u0003*\u0001\u0005\u0003\u0005\u000b\u0015\u0002'\t\u0011M\u0003!\u00111A\u0005\nuB\u0001\u0002\u0016\u0001\u0003\u0002\u0004%I!\u0016\u0005\t/\u0002\u0011\t\u0011)Q\u0005}!A\u0001\f\u0001BA\u0002\u0013%Q\b\u0003\u0005Z\u0001\t\u0005\r\u0011\"\u0003[\u0011!a\u0006A!A!B\u0013q\u0004BB/\u0001\t\u0003Qb\fC\u0004e\u0001\t\u0007I\u0011B3\t\r1\u0004\u0001\u0015!\u0003g\u0011\u001di\u0007A1A\u0005\n9DaA\u001d\u0001!\u0002\u0013y\u0007bB:\u0001\u0005\u0004%\t\u0005\u001e\u0005\b\u0003\u0007\u0001\u0001\u0015!\u0003v\u0011%\t9\u0001\u0001b\u0001\n#\nI\u0001\u0003\u0005\u0002:\u0001\u0001\u000b\u0011BA\u0006\u0011!\tY\u0004\u0001C)5\u0005u\"!\u0007'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:<\u0016\u000e\u001e5T\u000f\u0012S!!\u0007\u000e\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u00111\u0004H\u0001\u0006[2d\u0017N\u0019\u0006\u0003;y\tQa\u001d9be.T!a\b\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0013aA8sO\u000e\u00011c\u0001\u0001%]A\u0019Q\u0005\u000b\u0016\u000e\u0003\u0019R!a\n\u000e\u0002\u0015I,wM]3tg&|g.\u0003\u0002*M\tQr)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:BY\u001e|'/\u001b;i[B\u00111\u0006L\u0007\u00021%\u0011Q\u0006\u0007\u0002\u0018\u0019><\u0017n\u001d;jGJ+wM]3tg&|g.T8eK2\u0004\"aL\u001d\u000f\u0005A2dBA\u00195\u001b\u0005\u0011$BA\u001a#\u0003\u0019a$o\\8u}%\tQ'A\u0003tG\u0006d\u0017-\u0003\u00028q\u00059\u0001/Y2lC\u001e,'\"A\u001b\n\u0005iZ$\u0001D*fe&\fG.\u001b>bE2,'BA\u001c9\u0003!\u0019H/\u001a9TSj,W#\u0001 \u0011\u0005}\u0002U\"\u0001\u001d\n\u0005\u0005C$A\u0002#pk\ndW-\u0001\u0007ti\u0016\u00048+\u001b>f?\u0012*\u0017\u000f\u0006\u0002E\u000fB\u0011q(R\u0005\u0003\rb\u0012A!\u00168ji\"9\u0001JAA\u0001\u0002\u0004q\u0014a\u0001=%c\u0005I1\u000f^3q'&TX\rI\u0001\u000e]Vl\u0017\n^3sCRLwN\\:\u0016\u00031\u0003\"aP'\n\u00059C$aA%oi\u0006\tb.^7Ji\u0016\u0014\u0018\r^5p]N|F%Z9\u0015\u0005\u0011\u000b\u0006b\u0002%\u0006\u0003\u0003\u0005\r\u0001T\u0001\u000f]Vl\u0017\n^3sCRLwN\\:!\u0003!\u0011Xm\u001a)be\u0006l\u0017\u0001\u0004:fOB\u000b'/Y7`I\u0015\fHC\u0001#W\u0011\u001dA\u0005\"!AA\u0002y\n\u0011B]3h!\u0006\u0014\u0018-\u001c\u0011\u0002#5Lg.\u001b\"bi\u000eDgI]1di&|g.A\u000bnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:|F%Z9\u0015\u0005\u0011[\u0006b\u0002%\f\u0003\u0003\u0005\rAP\u0001\u0013[&t\u0017NQ1uG\"4%/Y2uS>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006?\u0002\f'm\u0019\t\u0003W\u0001AQ\u0001P\u0007A\u0002yBQAS\u0007A\u00021CQaU\u0007A\u0002yBQ\u0001W\u0007A\u0002y\n\u0001b\u001a:bI&,g\u000e^\u000b\u0002MB\u0011qM[\u0007\u0002Q*\u0011\u0011NG\u0001\r_B$\u0018.\\5{CRLwN\\\u0005\u0003W\"\u0014\u0001\u0003T8hSN$\u0018nY$sC\u0012LWM\u001c;\u0002\u0013\u001d\u0014\u0018\rZ5f]R\u0004\u0013aB;qI\u0006$XM]\u000b\u0002_B\u0011q\r]\u0005\u0003c\"\u0014\u0001cU9vCJ,G\r\u0014\u001aVa\u0012\fG/\u001a:\u0002\u0011U\u0004H-\u0019;fe\u0002\n\u0011b\u001c9uS6L'0\u001a:\u0016\u0003U\u0004\"a\u001a<\n\u0005]D'aD$sC\u0012LWM\u001c;EKN\u001cWM\u001c;)\u0007IIx\u0010\u0005\u0002{{6\t1P\u0003\u0002}9\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005y\\(!B*j]\u000e,\u0017EAA\u0001\u0003\u0015\u0001d\u0006\u000f\u00181\u0003)y\u0007\u000f^5nSj,'\u000f\t\u0015\u0004'e|\u0018A\u0003<bY&$\u0017\r^8sgV\u0011\u00111\u0002\t\u0007\u0003\u001b\t9\"a\u0007\u000e\u0005\u0005=!\u0002BA\t\u0003'\t\u0011\"[7nkR\f'\r\\3\u000b\u0007\u0005U\u0001(\u0001\u0006d_2dWm\u0019;j_:LA!!\u0007\u0002\u0010\t!A*[:u!\u001dy\u0014QDA\u0011\u0003gI1!a\b9\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0004\u0002$\u0005%\u0012QF\u0007\u0003\u0003KQ1!a\n\u001d\u0003\r\u0011H\rZ\u0005\u0005\u0003W\t)CA\u0002S\t\u0012\u00032!JA\u0018\u0013\r\t\tD\n\u0002\r\u0019\u0006\u0014W\r\\3e!>Lg\u000e\u001e\t\u0004\u007f\u0005U\u0012bAA\u001cq\t9!i\\8mK\u0006t\u0017a\u0003<bY&$\u0017\r^8sg\u0002\n1b\u0019:fCR,Wj\u001c3fYR)!&a\u0010\u0002P!9\u0011\u0011\t\fA\u0002\u0005\r\u0013aB<fS\u001eDGo\u001d\t\u0005\u0003\u000b\nY%\u0004\u0002\u0002H)\u0019\u0011\u0011\n\u000e\u0002\r1Lg.\u00197h\u0013\u0011\ti%a\u0012\u0003\rY+7\r^8s\u0011\u0019\t\tF\u0006a\u0001}\u0005I\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0015\u0004\u0001e|\b"
)
public class LogisticRegressionWithSGD extends GeneralizedLinearAlgorithm {
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private final LogisticGradient gradient;
   private final SquaredL2Updater updater;
   private final GradientDescent optimizer;
   private final List validators;

   private double stepSize() {
      return this.stepSize;
   }

   private void stepSize_$eq(final double x$1) {
      this.stepSize = x$1;
   }

   private int numIterations() {
      return this.numIterations;
   }

   private void numIterations_$eq(final int x$1) {
      this.numIterations = x$1;
   }

   private double regParam() {
      return this.regParam;
   }

   private void regParam_$eq(final double x$1) {
      this.regParam = x$1;
   }

   private double miniBatchFraction() {
      return this.miniBatchFraction;
   }

   private void miniBatchFraction_$eq(final double x$1) {
      this.miniBatchFraction = x$1;
   }

   private LogisticGradient gradient() {
      return this.gradient;
   }

   private SquaredL2Updater updater() {
      return this.updater;
   }

   public GradientDescent optimizer() {
      return this.optimizer;
   }

   public List validators() {
      return this.validators;
   }

   public LogisticRegressionModel createModel(final Vector weights, final double intercept) {
      return new LogisticRegressionModel(weights, intercept);
   }

   public LogisticRegressionWithSGD(final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.regParam = regParam;
      this.miniBatchFraction = miniBatchFraction;
      super();
      this.gradient = new LogisticGradient();
      this.updater = new SquaredL2Updater();
      this.optimizer = (new GradientDescent(this.gradient(), this.updater())).setStepSize(this.stepSize()).setNumIterations(this.numIterations()).setRegParam(this.regParam()).setMiniBatchFraction(this.miniBatchFraction());
      this.validators = new .colon.colon(DataValidators$.MODULE$.binaryLabelValidator(), scala.collection.immutable.Nil..MODULE$);
   }
}
