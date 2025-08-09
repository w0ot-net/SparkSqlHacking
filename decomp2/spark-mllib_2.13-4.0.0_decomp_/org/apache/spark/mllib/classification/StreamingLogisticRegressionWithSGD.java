package org.apache.spark.mllib.classification;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.StreamingLinearAlgorithm;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001B\r\u001b\u0001\u0015B\u0001\"\u0011\u0001\u0003\u0002\u0004%IA\u0011\u0005\t\u000f\u0002\u0011\t\u0019!C\u0005\u0011\"Aa\n\u0001B\u0001B\u0003&1\t\u0003\u0005P\u0001\t\u0005\r\u0011\"\u0003Q\u0011!!\u0006A!a\u0001\n\u0013)\u0006\u0002C,\u0001\u0005\u0003\u0005\u000b\u0015B)\t\u0011a\u0003!\u00111A\u0005\n\tC\u0001\"\u0017\u0001\u0003\u0002\u0004%IA\u0017\u0005\t9\u0002\u0011\t\u0011)Q\u0005\u0007\"AQ\f\u0001BA\u0002\u0013%!\t\u0003\u0005_\u0001\t\u0005\r\u0011\"\u0003`\u0011!\t\u0007A!A!B\u0013\u0019\u0005B\u00022\u0001\t\u0003a2\rC\u0003c\u0001\u0011\u0005\u0011\u000eC\u0004t\u0001\t\u0007I\u0011\u0003;\t\rU\u0004\u0001\u0015!\u00031\u0011\u001d1\b\u00011A\u0005\u0012]Dqa\u001f\u0001A\u0002\u0013EA\u0010\u0003\u0004\u007f\u0001\u0001\u0006K\u0001\u001f\u0005\u0007\u007f\u0002!\t!!\u0001\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003C\u0001A\u0011AA\u0012\u0005\t\u001aFO]3b[&tw\rT8hSN$\u0018n\u0019*fOJ,7o]5p]^KG\u000f[*H\t*\u00111\u0004H\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tib$A\u0003nY2L'M\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u00144!\u00119#\u0006\f\u0019\u000e\u0003!R!!\u000b\u000f\u0002\u0015I,wM]3tg&|g.\u0003\u0002,Q\tA2\u000b\u001e:fC6Lgn\u001a'j]\u0016\f'/\u00117h_JLG\u000f[7\u0011\u00055rS\"\u0001\u000e\n\u0005=R\"a\u0006'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:lu\u000eZ3m!\ti\u0013'\u0003\u000235\tIBj\\4jgRL7MU3he\u0016\u001c8/[8o/&$\bnU$E!\t!dH\u0004\u00026w9\u0011a'O\u0007\u0002o)\u0011\u0001\bJ\u0001\u0007yI|w\u000e\u001e \n\u0003i\nQa]2bY\u0006L!\u0001P\u001f\u0002\u000fA\f7m[1hK*\t!(\u0003\u0002@\u0001\na1+\u001a:jC2L'0\u00192mK*\u0011A(P\u0001\tgR,\u0007oU5{KV\t1\t\u0005\u0002E\u000b6\tQ(\u0003\u0002G{\t1Ai\\;cY\u0016\fAb\u001d;faNK'0Z0%KF$\"!\u0013'\u0011\u0005\u0011S\u0015BA&>\u0005\u0011)f.\u001b;\t\u000f5\u0013\u0011\u0011!a\u0001\u0007\u0006\u0019\u0001\u0010J\u0019\u0002\u0013M$X\r]*ju\u0016\u0004\u0013!\u00048v[&#XM]1uS>t7/F\u0001R!\t!%+\u0003\u0002T{\t\u0019\u0011J\u001c;\u0002#9,X.\u0013;fe\u0006$\u0018n\u001c8t?\u0012*\u0017\u000f\u0006\u0002J-\"9Q*BA\u0001\u0002\u0004\t\u0016A\u00048v[&#XM]1uS>t7\u000fI\u0001\u0012[&t\u0017NQ1uG\"4%/Y2uS>t\u0017!F7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]~#S-\u001d\u000b\u0003\u0013nCq!\u0014\u0005\u0002\u0002\u0003\u00071)\u0001\nnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:\u0004\u0013\u0001\u0003:fOB\u000b'/Y7\u0002\u0019I,w\rU1sC6|F%Z9\u0015\u0005%\u0003\u0007bB'\f\u0003\u0003\u0005\raQ\u0001\ne\u0016<\u0007+\u0019:b[\u0002\na\u0001P5oSRtD#\u00023fM\u001eD\u0007CA\u0017\u0001\u0011\u0015\tU\u00021\u0001D\u0011\u0015yU\u00021\u0001R\u0011\u0015AV\u00021\u0001D\u0011\u0015iV\u00021\u0001D)\u0005!\u0007f\u0001\blcB\u0011An\\\u0007\u0002[*\u0011aNH\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00019n\u0005\u0015\u0019\u0016N\\2fC\u0005\u0011\u0018!B\u0019/g9\u0002\u0014!C1mO>\u0014\u0018\u000e\u001e5n+\u0005\u0001\u0014AC1mO>\u0014\u0018\u000e\u001e5nA\u0005)Qn\u001c3fYV\t\u0001\u0010E\u0002Es2J!A_\u001f\u0003\r=\u0003H/[8o\u0003%iw\u000eZ3m?\u0012*\u0017\u000f\u0006\u0002J{\"9QJEA\u0001\u0002\u0004A\u0018AB7pI\u0016d\u0007%A\u0006tKR\u001cF/\u001a9TSj,G\u0003BA\u0002\u0003\u000bi\u0011\u0001\u0001\u0005\u0006\u0003R\u0001\ra\u0011\u0015\u0004)-\f\u0018\u0001E:fi:+X.\u0013;fe\u0006$\u0018n\u001c8t)\u0011\t\u0019!!\u0004\t\u000b=+\u0002\u0019A))\u0007UY\u0017/\u0001\u000btKRl\u0015N\\5CCR\u001c\u0007N\u0012:bGRLwN\u001c\u000b\u0005\u0003\u0007\t)\u0002C\u0003Y-\u0001\u00071\tK\u0002\u0017WF\f1b]3u%\u0016<\u0007+\u0019:b[R!\u00111AA\u000f\u0011\u0015iv\u00031\u0001DQ\r92.]\u0001\u0012g\u0016$\u0018J\\5uS\u0006dw+Z5hQR\u001cH\u0003BA\u0002\u0003KAq!a\n\u0019\u0001\u0004\tI#\u0001\bj]&$\u0018.\u00197XK&<\u0007\u000e^:\u0011\t\u0005-\u0012\u0011G\u0007\u0003\u0003[Q1!a\f\u001d\u0003\u0019a\u0017N\\1mO&!\u00111GA\u0017\u0005\u00191Vm\u0019;pe\"\u001a\u0001d[9)\u0007\u0001Y\u0017\u000f"
)
public class StreamingLogisticRegressionWithSGD extends StreamingLinearAlgorithm implements Serializable {
   private double stepSize;
   private int numIterations;
   private double miniBatchFraction;
   private double regParam;
   private final LogisticRegressionWithSGD algorithm;
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

   private double miniBatchFraction() {
      return this.miniBatchFraction;
   }

   private void miniBatchFraction_$eq(final double x$1) {
      this.miniBatchFraction = x$1;
   }

   private double regParam() {
      return this.regParam;
   }

   private void regParam_$eq(final double x$1) {
      this.regParam = x$1;
   }

   public LogisticRegressionWithSGD algorithm() {
      return this.algorithm;
   }

   public Option model() {
      return this.model;
   }

   public void model_$eq(final Option x$1) {
      this.model = x$1;
   }

   public StreamingLogisticRegressionWithSGD setStepSize(final double stepSize) {
      this.algorithm().optimizer().setStepSize(stepSize);
      return this;
   }

   public StreamingLogisticRegressionWithSGD setNumIterations(final int numIterations) {
      this.algorithm().optimizer().setNumIterations(numIterations);
      return this;
   }

   public StreamingLogisticRegressionWithSGD setMiniBatchFraction(final double miniBatchFraction) {
      this.algorithm().optimizer().setMiniBatchFraction(miniBatchFraction);
      return this;
   }

   public StreamingLogisticRegressionWithSGD setRegParam(final double regParam) {
      this.algorithm().optimizer().setRegParam(regParam);
      return this;
   }

   public StreamingLogisticRegressionWithSGD setInitialWeights(final Vector initialWeights) {
      this.model_$eq(new Some(this.algorithm().createModel(initialWeights, (double)0.0F)));
      return this;
   }

   public StreamingLogisticRegressionWithSGD(final double stepSize, final int numIterations, final double miniBatchFraction, final double regParam) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.miniBatchFraction = miniBatchFraction;
      this.regParam = regParam;
      super();
      this.algorithm = new LogisticRegressionWithSGD(this.stepSize(), this.numIterations(), this.regParam(), this.miniBatchFraction());
      this.model = .MODULE$;
   }

   public StreamingLogisticRegressionWithSGD() {
      this(0.1, 50, (double)1.0F, (double)0.0F);
   }
}
