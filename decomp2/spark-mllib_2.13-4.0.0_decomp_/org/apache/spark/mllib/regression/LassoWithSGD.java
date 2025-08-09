package org.apache.spark.mllib.regression;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.L1Updater;
import org.apache.spark.mllib.optimization.LeastSquaresGradient;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001B\u000b\u0017\u0001\u0005B\u0001b\u000e\u0001\u0003\u0002\u0004%I\u0001\u000f\u0005\t{\u0001\u0011\t\u0019!C\u0005}!AA\t\u0001B\u0001B\u0003&\u0011\b\u0003\u0005F\u0001\t\u0005\r\u0011\"\u0003G\u0011!Q\u0005A!a\u0001\n\u0013Y\u0005\u0002C'\u0001\u0005\u0003\u0005\u000b\u0015B$\t\u00119\u0003!\u00111A\u0005\naB\u0001b\u0014\u0001\u0003\u0002\u0004%I\u0001\u0015\u0005\t%\u0002\u0011\t\u0011)Q\u0005s!A1\u000b\u0001BA\u0002\u0013%\u0001\b\u0003\u0005U\u0001\t\u0005\r\u0011\"\u0003V\u0011!9\u0006A!A!B\u0013I\u0004B\u0002-\u0001\t\u0003A\u0012\fC\u0004`\u0001\t\u0007I\u0011\u00021\t\r\u001d\u0004\u0001\u0015!\u0003b\u0011\u001dA\u0007A1A\u0005\n%Da!\u001c\u0001!\u0002\u0013Q\u0007b\u00028\u0001\u0005\u0004%\te\u001c\u0005\u0007y\u0002\u0001\u000b\u0011\u00029\t\u000by\u0004A\u0011K@\u0003\u00191\u000b7o]8XSRD7k\u0012#\u000b\u0005]A\u0012A\u0003:fOJ,7o]5p]*\u0011\u0011DG\u0001\u0006[2d\u0017N\u0019\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sO\u000e\u00011c\u0001\u0001#SA\u00191\u0005\n\u0014\u000e\u0003YI!!\n\f\u00035\u001d+g.\u001a:bY&TX\r\u001a'j]\u0016\f'/\u00117h_JLG\u000f[7\u0011\u0005\r:\u0013B\u0001\u0015\u0017\u0005)a\u0015m]:p\u001b>$W\r\u001c\t\u0003UQr!aK\u0019\u000f\u00051zS\"A\u0017\u000b\u00059\u0002\u0013A\u0002\u001fs_>$h(C\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u00114'A\u0004qC\u000e\\\u0017mZ3\u000b\u0003AJ!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005I\u001a\u0014\u0001C:uKB\u001c\u0016N_3\u0016\u0003e\u0002\"AO\u001e\u000e\u0003MJ!\u0001P\u001a\u0003\r\u0011{WO\u00197f\u00031\u0019H/\u001a9TSj,w\fJ3r)\ty$\t\u0005\u0002;\u0001&\u0011\u0011i\r\u0002\u0005+:LG\u000fC\u0004D\u0005\u0005\u0005\t\u0019A\u001d\u0002\u0007a$\u0013'A\u0005ti\u0016\u00048+\u001b>fA\u0005ia.^7Ji\u0016\u0014\u0018\r^5p]N,\u0012a\u0012\t\u0003u!K!!S\u001a\u0003\u0007%sG/A\tok6LE/\u001a:bi&|gn]0%KF$\"a\u0010'\t\u000f\r+\u0011\u0011!a\u0001\u000f\u0006qa.^7Ji\u0016\u0014\u0018\r^5p]N\u0004\u0013\u0001\u0003:fOB\u000b'/Y7\u0002\u0019I,w\rU1sC6|F%Z9\u0015\u0005}\n\u0006bB\"\t\u0003\u0003\u0005\r!O\u0001\ne\u0016<\u0007+\u0019:b[\u0002\n\u0011#\\5oS\n\u000bGo\u00195Ge\u0006\u001cG/[8o\u0003Ui\u0017N\\5CCR\u001c\u0007N\u0012:bGRLwN\\0%KF$\"a\u0010,\t\u000f\r[\u0011\u0011!a\u0001s\u0005\u0011R.\u001b8j\u0005\u0006$8\r\u001b$sC\u000e$\u0018n\u001c8!\u0003\u0019a\u0014N\\5u}Q)!l\u0017/^=B\u00111\u0005\u0001\u0005\u0006o5\u0001\r!\u000f\u0005\u0006\u000b6\u0001\ra\u0012\u0005\u0006\u001d6\u0001\r!\u000f\u0005\u0006'6\u0001\r!O\u0001\tOJ\fG-[3oiV\t\u0011\r\u0005\u0002cK6\t1M\u0003\u0002e1\u0005aq\u000e\u001d;j[&T\u0018\r^5p]&\u0011am\u0019\u0002\u0015\u0019\u0016\f7\u000f^*rk\u0006\u0014Xm]$sC\u0012LWM\u001c;\u0002\u0013\u001d\u0014\u0018\rZ5f]R\u0004\u0013aB;qI\u0006$XM]\u000b\u0002UB\u0011!m[\u0005\u0003Y\u000e\u0014\u0011\u0002T\u0019Va\u0012\fG/\u001a:\u0002\u0011U\u0004H-\u0019;fe\u0002\n\u0011b\u001c9uS6L'0\u001a:\u0016\u0003A\u0004\"AY9\n\u0005I\u001c'aD$sC\u0012LWM\u001c;EKN\u001cWM\u001c;)\u0007I!(\u0010\u0005\u0002vq6\taO\u0003\u0002x5\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e4(!B*j]\u000e,\u0017%A>\u0002\u000bAr\u0003H\f\u0019\u0002\u0015=\u0004H/[7ju\u0016\u0014\b\u0005K\u0002\u0014ij\f1b\u0019:fCR,Wj\u001c3fYR)a%!\u0001\u0002\u0012!9\u00111\u0001\u000bA\u0002\u0005\u0015\u0011aB<fS\u001eDGo\u001d\t\u0005\u0003\u000f\ti!\u0004\u0002\u0002\n)\u0019\u00111\u0002\r\u0002\r1Lg.\u00197h\u0013\u0011\ty!!\u0003\u0003\rY+7\r^8s\u0011\u0019\t\u0019\u0002\u0006a\u0001s\u0005I\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0015\u0004\u0001QT\b"
)
public class LassoWithSGD extends GeneralizedLinearAlgorithm {
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private final LeastSquaresGradient gradient;
   private final L1Updater updater;
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

   private L1Updater updater() {
      return this.updater;
   }

   public GradientDescent optimizer() {
      return this.optimizer;
   }

   public LassoModel createModel(final Vector weights, final double intercept) {
      return new LassoModel(weights, intercept);
   }

   public LassoWithSGD(final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.regParam = regParam;
      this.miniBatchFraction = miniBatchFraction;
      super();
      this.gradient = new LeastSquaresGradient();
      this.updater = new L1Updater();
      this.optimizer = (new GradientDescent(this.gradient(), this.updater())).setStepSize(this.stepSize()).setNumIterations(this.numIterations()).setRegParam(this.regParam()).setMiniBatchFraction(this.miniBatchFraction());
   }
}
