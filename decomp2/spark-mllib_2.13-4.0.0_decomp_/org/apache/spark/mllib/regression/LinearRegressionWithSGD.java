package org.apache.spark.mllib.regression;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.LeastSquaresGradient;
import org.apache.spark.mllib.optimization.SimpleUpdater;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001B\u000b\u0017\u0001\u0005B\u0001b\u000e\u0001\u0003\u0002\u0004%I\u0001\u000f\u0005\t{\u0001\u0011\t\u0019!C\u0005}!AA\t\u0001B\u0001B\u0003&\u0011\b\u0003\u0005F\u0001\t\u0005\r\u0011\"\u0003G\u0011!Q\u0005A!a\u0001\n\u0013Y\u0005\u0002C'\u0001\u0005\u0003\u0005\u000b\u0015B$\t\u00119\u0003!\u00111A\u0005\naB\u0001b\u0014\u0001\u0003\u0002\u0004%I\u0001\u0015\u0005\t%\u0002\u0011\t\u0011)Q\u0005s!A1\u000b\u0001BA\u0002\u0013%\u0001\b\u0003\u0005U\u0001\t\u0005\r\u0011\"\u0003V\u0011!9\u0006A!A!B\u0013I\u0004B\u0002-\u0001\t\u0003A\u0012\fC\u0004`\u0001\t\u0007I\u0011\u00021\t\r\u001d\u0004\u0001\u0015!\u0003b\u0011\u001dA\u0007A1A\u0005\n%Da!\u001c\u0001!\u0002\u0013Q\u0007b\u00028\u0001\u0005\u0004%\te\u001c\u0005\u0007y\u0002\u0001\u000b\u0011\u00029\t\ry\u0004A\u0011\u000b\r\u0000\u0005]a\u0015N\\3beJ+wM]3tg&|gnV5uQN;EI\u0003\u0002\u00181\u0005Q!/Z4sKN\u001c\u0018n\u001c8\u000b\u0005eQ\u0012!B7mY&\u0014'BA\u000e\u001d\u0003\u0015\u0019\b/\u0019:l\u0015\tib$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002?\u0005\u0019qN]4\u0004\u0001M\u0019\u0001AI\u0015\u0011\u0007\r\"c%D\u0001\u0017\u0013\t)cC\u0001\u000eHK:,'/\u00197ju\u0016$G*\u001b8fCJ\fEnZ8sSRDW\u000e\u0005\u0002$O%\u0011\u0001F\u0006\u0002\u0016\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3m!\tQCG\u0004\u0002,c9\u0011AfL\u0007\u0002[)\u0011a\u0006I\u0001\u0007yI|w\u000e\u001e \n\u0003A\nQa]2bY\u0006L!AM\u001a\u0002\u000fA\f7m[1hK*\t\u0001'\u0003\u00026m\ta1+\u001a:jC2L'0\u00192mK*\u0011!gM\u0001\tgR,\u0007oU5{KV\t\u0011\b\u0005\u0002;w5\t1'\u0003\u0002=g\t1Ai\\;cY\u0016\fAb\u001d;faNK'0Z0%KF$\"a\u0010\"\u0011\u0005i\u0002\u0015BA!4\u0005\u0011)f.\u001b;\t\u000f\r\u0013\u0011\u0011!a\u0001s\u0005\u0019\u0001\u0010J\u0019\u0002\u0013M$X\r]*ju\u0016\u0004\u0013!\u00048v[&#XM]1uS>t7/F\u0001H!\tQ\u0004*\u0003\u0002Jg\t\u0019\u0011J\u001c;\u0002#9,X.\u0013;fe\u0006$\u0018n\u001c8t?\u0012*\u0017\u000f\u0006\u0002@\u0019\"91)BA\u0001\u0002\u00049\u0015A\u00048v[&#XM]1uS>t7\u000fI\u0001\te\u0016<\u0007+\u0019:b[\u0006a!/Z4QCJ\fWn\u0018\u0013fcR\u0011q(\u0015\u0005\b\u0007\"\t\t\u00111\u0001:\u0003%\u0011Xm\u001a)be\u0006l\u0007%A\tnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:\fQ#\\5oS\n\u000bGo\u00195Ge\u0006\u001cG/[8o?\u0012*\u0017\u000f\u0006\u0002@-\"91iCA\u0001\u0002\u0004I\u0014AE7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]\u0002\na\u0001P5oSRtD#\u0002.\\9vs\u0006CA\u0012\u0001\u0011\u00159T\u00021\u0001:\u0011\u0015)U\u00021\u0001H\u0011\u0015qU\u00021\u0001:\u0011\u0015\u0019V\u00021\u0001:\u0003!9'/\u00193jK:$X#A1\u0011\u0005\t,W\"A2\u000b\u0005\u0011D\u0012\u0001D8qi&l\u0017N_1uS>t\u0017B\u00014d\u0005QaU-Y:u'F,\u0018M]3t\u000fJ\fG-[3oi\u0006IqM]1eS\u0016tG\u000fI\u0001\bkB$\u0017\r^3s+\u0005Q\u0007C\u00012l\u0013\ta7MA\u0007TS6\u0004H.Z+qI\u0006$XM]\u0001\tkB$\u0017\r^3sA\u0005Iq\u000e\u001d;j[&TXM]\u000b\u0002aB\u0011!-]\u0005\u0003e\u000e\u0014qb\u0012:bI&,g\u000e\u001e#fg\u000e,g\u000e\u001e\u0015\u0004%QT\bCA;y\u001b\u00051(BA<\u001b\u0003)\tgN\\8uCRLwN\\\u0005\u0003sZ\u0014QaU5oG\u0016\f\u0013a_\u0001\u0006a9Bd\u0006M\u0001\u000b_B$\u0018.\\5{KJ\u0004\u0003fA\nuu\u0006Y1M]3bi\u0016lu\u000eZ3m)\u00151\u0013\u0011AA\t\u0011\u001d\t\u0019\u0001\u0006a\u0001\u0003\u000b\tqa^3jO\"$8\u000f\u0005\u0003\u0002\b\u00055QBAA\u0005\u0015\r\tY\u0001G\u0001\u0007Y&t\u0017\r\\4\n\t\u0005=\u0011\u0011\u0002\u0002\u0007-\u0016\u001cGo\u001c:\t\r\u0005MA\u00031\u0001:\u0003%Ig\u000e^3sG\u0016\u0004H\u000fK\u0002\u0001ij\u0004"
)
public class LinearRegressionWithSGD extends GeneralizedLinearAlgorithm {
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private final LeastSquaresGradient gradient;
   private final SimpleUpdater updater;
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

   private SimpleUpdater updater() {
      return this.updater;
   }

   public GradientDescent optimizer() {
      return this.optimizer;
   }

   public LinearRegressionModel createModel(final Vector weights, final double intercept) {
      return new LinearRegressionModel(weights, intercept);
   }

   public LinearRegressionWithSGD(final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.regParam = regParam;
      this.miniBatchFraction = miniBatchFraction;
      super();
      this.gradient = new LeastSquaresGradient();
      this.updater = new SimpleUpdater();
      this.optimizer = (new GradientDescent(this.gradient(), this.updater())).setStepSize(this.stepSize()).setNumIterations(this.numIterations()).setRegParam(this.regParam()).setMiniBatchFraction(this.miniBatchFraction());
   }
}
