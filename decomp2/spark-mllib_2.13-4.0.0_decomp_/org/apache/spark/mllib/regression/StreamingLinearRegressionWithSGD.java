package org.apache.spark.mllib.regression;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001\u0002\u000e\u001c\u0001\u0019B\u0001b\u0010\u0001\u0003\u0002\u0004%I\u0001\u0011\u0005\t\u000b\u0002\u0011\t\u0019!C\u0005\r\"AA\n\u0001B\u0001B\u0003&\u0011\t\u0003\u0005N\u0001\t\u0005\r\u0011\"\u0003O\u0011!\u0011\u0006A!a\u0001\n\u0013\u0019\u0006\u0002C+\u0001\u0005\u0003\u0005\u000b\u0015B(\t\u0011Y\u0003!\u00111A\u0005\n\u0001C\u0001b\u0016\u0001\u0003\u0002\u0004%I\u0001\u0017\u0005\t5\u0002\u0011\t\u0011)Q\u0005\u0003\"A1\f\u0001BA\u0002\u0013%\u0001\t\u0003\u0005]\u0001\t\u0005\r\u0011\"\u0003^\u0011!y\u0006A!A!B\u0013\t\u0005B\u00021\u0001\t\u0003i\u0012\rC\u0003a\u0001\u0011\u0005q\rC\u0004r\u0001\t\u0007I\u0011\u0001:\t\rQ\u0004\u0001\u0015!\u0003/\u0011\u001d1\b\u00011A\u0005\u0012]Dqa\u001f\u0001A\u0002\u0013EA\u0010\u0003\u0004\u007f\u0001\u0001\u0006K\u0001\u001f\u0005\u0007\u007f\u0002!\t!!\u0001\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0001bBA\u000f\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0003K\u0001A\u0011AA\u0014\u0011\u001d\tY\u0004\u0001C\u0001\u0003{\u0011\u0001e\u0015;sK\u0006l\u0017N\\4MS:,\u0017M\u001d*fOJ,7o]5p]^KG\u000f[*H\t*\u0011A$H\u0001\u000be\u0016<'/Z:tS>t'B\u0001\u0010 \u0003\u0015iG\u000e\\5c\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7\u0001A\n\u0004\u0001\u001d\n\u0004\u0003\u0002\u0015*W9j\u0011aG\u0005\u0003Um\u0011\u0001d\u0015;sK\u0006l\u0017N\\4MS:,\u0017M]!mO>\u0014\u0018\u000e\u001e5n!\tAC&\u0003\u0002.7\t)B*\u001b8fCJ\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007C\u0001\u00150\u0013\t\u00014DA\fMS:,\u0017M\u001d*fOJ,7o]5p]^KG\u000f[*H\tB\u0011!\u0007\u0010\b\u0003ger!\u0001N\u001c\u000e\u0003UR!AN\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0014!B:dC2\f\u0017B\u0001\u001e<\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011\u0001O\u0005\u0003{y\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AO\u001e\u0002\u0011M$X\r]*ju\u0016,\u0012!\u0011\t\u0003\u0005\u000ek\u0011aO\u0005\u0003\tn\u0012a\u0001R8vE2,\u0017\u0001D:uKB\u001c\u0016N_3`I\u0015\fHCA$K!\t\u0011\u0005*\u0003\u0002Jw\t!QK\\5u\u0011\u001dY%!!AA\u0002\u0005\u000b1\u0001\u001f\u00132\u0003%\u0019H/\u001a9TSj,\u0007%A\u0007ok6LE/\u001a:bi&|gn]\u000b\u0002\u001fB\u0011!\tU\u0005\u0003#n\u00121!\u00138u\u0003EqW/\\%uKJ\fG/[8og~#S-\u001d\u000b\u0003\u000fRCqaS\u0003\u0002\u0002\u0003\u0007q*\u0001\bok6LE/\u001a:bi&|gn\u001d\u0011\u0002\u0011I,w\rU1sC6\fAB]3h!\u0006\u0014\u0018-\\0%KF$\"aR-\t\u000f-C\u0011\u0011!a\u0001\u0003\u0006I!/Z4QCJ\fW\u000eI\u0001\u0012[&t\u0017NQ1uG\"4%/Y2uS>t\u0017!F7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]~#S-\u001d\u000b\u0003\u000fzCqaS\u0006\u0002\u0002\u0003\u0007\u0011)\u0001\nnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:\u0004\u0013A\u0002\u001fj]&$h\bF\u0003cG\u0012,g\r\u0005\u0002)\u0001!)q(\u0004a\u0001\u0003\")Q*\u0004a\u0001\u001f\")a+\u0004a\u0001\u0003\")1,\u0004a\u0001\u0003R\t!\rK\u0002\u000fS>\u0004\"A[7\u000e\u0003-T!\u0001\\\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002oW\n)1+\u001b8dK\u0006\n\u0001/A\u00032]Er\u0003'A\u0005bY\u001e|'/\u001b;i[V\ta\u0006K\u0002\u0010S>\f!\"\u00197h_JLG\u000f[7!Q\r\u0001\u0012n\\\u0001\u0006[>$W\r\\\u000b\u0002qB\u0019!)_\u0016\n\u0005i\\$AB(qi&|g.A\u0005n_\u0012,Gn\u0018\u0013fcR\u0011q) \u0005\b\u0017J\t\t\u00111\u0001y\u0003\u0019iw\u000eZ3mA\u0005Y1/\u001a;Ti\u0016\u00048+\u001b>f)\u0011\t\u0019!!\u0002\u000e\u0003\u0001AQa\u0010\u000bA\u0002\u0005C3\u0001F5p\u0003-\u0019X\r\u001e*fOB\u000b'/Y7\u0015\t\u0005\r\u0011Q\u0002\u0005\u0006-V\u0001\r!\u0011\u0015\u0005+%\f\t\"\t\u0002\u0002\u0014\u0005)!G\f\u0019/a\u0005\u00012/\u001a;Ok6LE/\u001a:bi&|gn\u001d\u000b\u0005\u0003\u0007\tI\u0002C\u0003N-\u0001\u0007q\nK\u0002\u0017S>\fAc]3u\u001b&t\u0017NQ1uG\"4%/Y2uS>tG\u0003BA\u0002\u0003CAQaW\fA\u0002\u0005C3aF5p\u0003E\u0019X\r^%oSRL\u0017\r\\,fS\u001eDGo\u001d\u000b\u0005\u0003\u0007\tI\u0003C\u0004\u0002,a\u0001\r!!\f\u0002\u001d%t\u0017\u000e^5bY^+\u0017n\u001a5ugB!\u0011qFA\u001b\u001b\t\t\tDC\u0002\u00024u\ta\u0001\\5oC2<\u0017\u0002BA\u001c\u0003c\u0011aAV3di>\u0014\bf\u0001\rj_\u0006\t2/\u001a;D_:4XM]4f]\u000e,Gk\u001c7\u0015\t\u0005\r\u0011q\b\u0005\u0007\u0003\u0003J\u0002\u0019A!\u0002\u0013Q|G.\u001a:b]\u000e,\u0007\u0006B\rj\u0003\u000b\n#!a\u0012\u0002\u000bErSG\f\u0019)\u0007\u0001Iw\u000e"
)
public class StreamingLinearRegressionWithSGD extends StreamingLinearAlgorithm implements Serializable {
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private final LinearRegressionWithSGD algorithm;
   private Option model;

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

   public LinearRegressionWithSGD algorithm() {
      return this.algorithm;
   }

   public Option model() {
      return this.model;
   }

   public void model_$eq(final Option x$1) {
      this.model = x$1;
   }

   public StreamingLinearRegressionWithSGD setStepSize(final double stepSize) {
      this.algorithm().optimizer().setStepSize(stepSize);
      return this;
   }

   public StreamingLinearRegressionWithSGD setRegParam(final double regParam) {
      this.algorithm().optimizer().setRegParam(regParam);
      return this;
   }

   public StreamingLinearRegressionWithSGD setNumIterations(final int numIterations) {
      this.algorithm().optimizer().setNumIterations(numIterations);
      return this;
   }

   public StreamingLinearRegressionWithSGD setMiniBatchFraction(final double miniBatchFraction) {
      this.algorithm().optimizer().setMiniBatchFraction(miniBatchFraction);
      return this;
   }

   public StreamingLinearRegressionWithSGD setInitialWeights(final Vector initialWeights) {
      this.model_$eq(new Some(this.algorithm().createModel(initialWeights, (double)0.0F)));
      return this;
   }

   public StreamingLinearRegressionWithSGD setConvergenceTol(final double tolerance) {
      this.algorithm().optimizer().setConvergenceTol(tolerance);
      return this;
   }

   public StreamingLinearRegressionWithSGD(final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.regParam = regParam;
      this.miniBatchFraction = miniBatchFraction;
      super();
      this.algorithm = new LinearRegressionWithSGD(this.stepSize(), this.numIterations(), this.regParam(), this.miniBatchFraction());
      this.model = .MODULE$;
   }

   public StreamingLinearRegressionWithSGD() {
      this(0.1, 50, (double)0.0F, (double)1.0F);
   }
}
