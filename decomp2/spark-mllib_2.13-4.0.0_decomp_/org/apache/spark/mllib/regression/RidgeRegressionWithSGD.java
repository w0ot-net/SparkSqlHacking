package org.apache.spark.mllib.regression;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.LeastSquaresGradient;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001B\u000b\u0017\u0001\u0005B\u0001b\u000e\u0001\u0003\u0002\u0004%I\u0001\u000f\u0005\t{\u0001\u0011\t\u0019!C\u0005}!AA\t\u0001B\u0001B\u0003&\u0011\b\u0003\u0005F\u0001\t\u0005\r\u0011\"\u0003G\u0011!Q\u0005A!a\u0001\n\u0013Y\u0005\u0002C'\u0001\u0005\u0003\u0005\u000b\u0015B$\t\u00119\u0003!\u00111A\u0005\naB\u0001b\u0014\u0001\u0003\u0002\u0004%I\u0001\u0015\u0005\t%\u0002\u0011\t\u0011)Q\u0005s!A1\u000b\u0001BA\u0002\u0013%\u0001\b\u0003\u0005U\u0001\t\u0005\r\u0011\"\u0003V\u0011!9\u0006A!A!B\u0013I\u0004B\u0002-\u0001\t\u0003A\u0012\fC\u0004`\u0001\t\u0007I\u0011\u00021\t\r\u001d\u0004\u0001\u0015!\u0003b\u0011\u001dA\u0007A1A\u0005\n%Da!\u001c\u0001!\u0002\u0013Q\u0007b\u00028\u0001\u0005\u0004%\te\u001c\u0005\u0007y\u0002\u0001\u000b\u0011\u00029\t\u000by\u0004A\u0011K@\u0003-IKGmZ3SK\u001e\u0014Xm]:j_:<\u0016\u000e\u001e5T\u000f\u0012S!a\u0006\r\u0002\u0015I,wM]3tg&|gN\u0003\u0002\u001a5\u0005)Q\u000e\u001c7jE*\u00111\u0004H\u0001\u0006gB\f'o\u001b\u0006\u0003;y\ta!\u00199bG\",'\"A\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0011\u0013\u0006E\u0002$I\u0019j\u0011AF\u0005\u0003KY\u0011!dR3oKJ\fG.\u001b>fI2Kg.Z1s\u00032<wN]5uQ6\u0004\"aI\u0014\n\u0005!2\"\u0001\u0006*jI\u001e,'+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002+i9\u00111&\r\b\u0003Y=j\u0011!\f\u0006\u0003]\u0001\na\u0001\u0010:p_Rt\u0014\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005I\u001a\u0014a\u00029bG.\fw-\u001a\u0006\u0002a%\u0011QG\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003eM\n\u0001b\u001d;faNK'0Z\u000b\u0002sA\u0011!hO\u0007\u0002g%\u0011Ah\r\u0002\u0007\t>,(\r\\3\u0002\u0019M$X\r]*ju\u0016|F%Z9\u0015\u0005}\u0012\u0005C\u0001\u001eA\u0013\t\t5G\u0001\u0003V]&$\bbB\"\u0003\u0003\u0003\u0005\r!O\u0001\u0004q\u0012\n\u0014!C:uKB\u001c\u0016N_3!\u00035qW/\\%uKJ\fG/[8ogV\tq\t\u0005\u0002;\u0011&\u0011\u0011j\r\u0002\u0004\u0013:$\u0018!\u00058v[&#XM]1uS>t7o\u0018\u0013fcR\u0011q\b\u0014\u0005\b\u0007\u0016\t\t\u00111\u0001H\u00039qW/\\%uKJ\fG/[8og\u0002\n\u0001B]3h!\u0006\u0014\u0018-\\\u0001\re\u0016<\u0007+\u0019:b[~#S-\u001d\u000b\u0003\u007fECqa\u0011\u0005\u0002\u0002\u0003\u0007\u0011(A\u0005sK\u001e\u0004\u0016M]1nA\u0005\tR.\u001b8j\u0005\u0006$8\r\u001b$sC\u000e$\u0018n\u001c8\u0002+5Lg.\u001b\"bi\u000eDgI]1di&|gn\u0018\u0013fcR\u0011qH\u0016\u0005\b\u0007.\t\t\u00111\u0001:\u0003Ii\u0017N\\5CCR\u001c\u0007N\u0012:bGRLwN\u001c\u0011\u0002\rqJg.\u001b;?)\u0015Q6\fX/_!\t\u0019\u0003\u0001C\u00038\u001b\u0001\u0007\u0011\bC\u0003F\u001b\u0001\u0007q\tC\u0003O\u001b\u0001\u0007\u0011\bC\u0003T\u001b\u0001\u0007\u0011(\u0001\u0005he\u0006$\u0017.\u001a8u+\u0005\t\u0007C\u00012f\u001b\u0005\u0019'B\u00013\u0019\u00031y\u0007\u000f^5nSj\fG/[8o\u0013\t17M\u0001\u000bMK\u0006\u001cHoU9vCJ,7o\u0012:bI&,g\u000e^\u0001\nOJ\fG-[3oi\u0002\nq!\u001e9eCR,'/F\u0001k!\t\u00117.\u0003\u0002mG\n\u00012+];be\u0016$GJM+qI\u0006$XM]\u0001\tkB$\u0017\r^3sA\u0005Iq\u000e\u001d;j[&TXM]\u000b\u0002aB\u0011!-]\u0005\u0003e\u000e\u0014qb\u0012:bI&,g\u000e\u001e#fg\u000e,g\u000e\u001e\u0015\u0004%QT\bCA;y\u001b\u00051(BA<\u001b\u0003)\tgN\\8uCRLwN\\\u0005\u0003sZ\u0014QaU5oG\u0016\f\u0013a_\u0001\u0006a9Bd\u0006M\u0001\u000b_B$\u0018.\\5{KJ\u0004\u0003fA\nuu\u0006Y1M]3bi\u0016lu\u000eZ3m)\u00151\u0013\u0011AA\t\u0011\u001d\t\u0019\u0001\u0006a\u0001\u0003\u000b\tqa^3jO\"$8\u000f\u0005\u0003\u0002\b\u00055QBAA\u0005\u0015\r\tY\u0001G\u0001\u0007Y&t\u0017\r\\4\n\t\u0005=\u0011\u0011\u0002\u0002\u0007-\u0016\u001cGo\u001c:\t\r\u0005MA\u00031\u0001:\u0003%Ig\u000e^3sG\u0016\u0004H\u000fK\u0002\u0001ij\u0004"
)
public class RidgeRegressionWithSGD extends GeneralizedLinearAlgorithm {
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private final LeastSquaresGradient gradient;
   private final SquaredL2Updater updater;
   private final GradientDescent optimizer;

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

   private LeastSquaresGradient gradient() {
      return this.gradient;
   }

   private SquaredL2Updater updater() {
      return this.updater;
   }

   public GradientDescent optimizer() {
      return this.optimizer;
   }

   public RidgeRegressionModel createModel(final Vector weights, final double intercept) {
      return new RidgeRegressionModel(weights, intercept);
   }

   public RidgeRegressionWithSGD(final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.regParam = regParam;
      this.miniBatchFraction = miniBatchFraction;
      super();
      this.gradient = new LeastSquaresGradient();
      this.updater = new SquaredL2Updater();
      this.optimizer = (new GradientDescent(this.gradient(), this.updater())).setStepSize(this.stepSize()).setNumIterations(this.numIterations()).setRegParam(this.regParam()).setMiniBatchFraction(this.miniBatchFraction());
   }
}
