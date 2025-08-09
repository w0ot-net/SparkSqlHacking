package org.apache.spark.mllib.classification;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.HingeGradient;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import org.apache.spark.mllib.regression.GeneralizedLinearAlgorithm;
import org.apache.spark.mllib.util.DataValidators$;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mg\u0001\u0002\u0011\"\u00011B\u0001\"\u0012\u0001\u0003\u0002\u0004%IA\u0012\u0005\t\u0017\u0002\u0011\t\u0019!C\u0005\u0019\"A!\u000b\u0001B\u0001B\u0003&q\t\u0003\u0005T\u0001\t\u0005\r\u0011\"\u0003U\u0011!A\u0006A!a\u0001\n\u0013I\u0006\u0002C.\u0001\u0005\u0003\u0005\u000b\u0015B+\t\u0011q\u0003!\u00111A\u0005\n\u0019C\u0001\"\u0018\u0001\u0003\u0002\u0004%IA\u0018\u0005\tA\u0002\u0011\t\u0011)Q\u0005\u000f\"A\u0011\r\u0001BA\u0002\u0013%a\t\u0003\u0005c\u0001\t\u0005\r\u0011\"\u0003d\u0011!)\u0007A!A!B\u00139\u0005\"\u00024\u0001\t\u00139\u0007bB7\u0001\u0005\u0004%IA\u001c\u0005\u0007k\u0002\u0001\u000b\u0011B8\t\u000fY\u0004!\u0019!C\u0005o\"11\u0010\u0001Q\u0001\naDq\u0001 \u0001C\u0002\u0013\u0005S\u0010C\u0004\u0002\u0016\u0001\u0001\u000b\u0011\u0002@\t\u0013\u0005e\u0001A1A\u0005R\u0005m\u0001\u0002CA&\u0001\u0001\u0006I!!\b\t\r\u0019\u0004A\u0011AA'\u0011\u001d\t\t\u0006\u0001C)\u0003':q!a\u001b\"\u0011\u0003\tiG\u0002\u0004!C!\u0005\u0011q\u000e\u0005\u0007Mf!\t!!\"\t\u000f\u0005\u001d\u0015\u0004\"\u0001\u0002\n\"9\u0011qQ\r\u0005\u0002\u0005u\u0005bBAD3\u0011\u0005\u00111\u0016\u0005\b\u0003\u000fKB\u0011AA\\\u0011%\ty,GA\u0001\n\u0013\t\tM\u0001\u0006T-6;\u0016\u000e\u001e5T\u000f\u0012S!AI\u0012\u0002\u001d\rd\u0017m]:jM&\u001c\u0017\r^5p]*\u0011A%J\u0001\u0006[2d\u0017N\u0019\u0006\u0003M\u001d\nQa\u001d9be.T!\u0001K\u0015\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0013aA8sO\u000e\u00011c\u0001\u0001.oA\u0019a&M\u001a\u000e\u0003=R!\u0001M\u0012\u0002\u0015I,wM]3tg&|g.\u0003\u00023_\tQr)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:BY\u001e|'/\u001b;i[B\u0011A'N\u0007\u0002C%\u0011a'\t\u0002\t'ZkUj\u001c3fYB\u0011\u0001H\u0011\b\u0003s}r!AO\u001f\u000e\u0003mR!\u0001P\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0014!B:dC2\f\u0017B\u0001!B\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AP\u0005\u0003\u0007\u0012\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001Q!\u0002\u0011M$X\r]*ju\u0016,\u0012a\u0012\t\u0003\u0011&k\u0011!Q\u0005\u0003\u0015\u0006\u0013a\u0001R8vE2,\u0017\u0001D:uKB\u001c\u0016N_3`I\u0015\fHCA'Q!\tAe*\u0003\u0002P\u0003\n!QK\\5u\u0011\u001d\t&!!AA\u0002\u001d\u000b1\u0001\u001f\u00132\u0003%\u0019H/\u001a9TSj,\u0007%A\u0007ok6LE/\u001a:bi&|gn]\u000b\u0002+B\u0011\u0001JV\u0005\u0003/\u0006\u00131!\u00138u\u0003EqW/\\%uKJ\fG/[8og~#S-\u001d\u000b\u0003\u001bjCq!U\u0003\u0002\u0002\u0003\u0007Q+\u0001\bok6LE/\u001a:bi&|gn\u001d\u0011\u0002\u0011I,w\rU1sC6\fAB]3h!\u0006\u0014\u0018-\\0%KF$\"!T0\t\u000fEC\u0011\u0011!a\u0001\u000f\u0006I!/Z4QCJ\fW\u000eI\u0001\u0012[&t\u0017NQ1uG\"4%/Y2uS>t\u0017!F7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]~#S-\u001d\u000b\u0003\u001b\u0012Dq!U\u0006\u0002\u0002\u0003\u0007q)\u0001\nnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:\u0004\u0013A\u0002\u001fj]&$h\bF\u0003iS*\\G\u000e\u0005\u00025\u0001!)Q)\u0004a\u0001\u000f\")1+\u0004a\u0001+\")A,\u0004a\u0001\u000f\")\u0011-\u0004a\u0001\u000f\u0006AqM]1eS\u0016tG/F\u0001p!\t\u00018/D\u0001r\u0015\t\u00118%\u0001\u0007paRLW.\u001b>bi&|g.\u0003\u0002uc\ni\u0001*\u001b8hK\u001e\u0013\u0018\rZ5f]R\f\u0011b\u001a:bI&,g\u000e\u001e\u0011\u0002\u000fU\u0004H-\u0019;feV\t\u0001\u0010\u0005\u0002qs&\u0011!0\u001d\u0002\u0011'F,\u0018M]3e\u0019J*\u0006\u000fZ1uKJ\f\u0001\"\u001e9eCR,'\u000fI\u0001\n_B$\u0018.\\5{KJ,\u0012A \t\u0003a~L1!!\u0001r\u0005=9%/\u00193jK:$H)Z:dK:$\b&\u0002\n\u0002\u0006\u0005E\u0001\u0003BA\u0004\u0003\u001bi!!!\u0003\u000b\u0007\u0005-Q%\u0001\u0006b]:|G/\u0019;j_:LA!a\u0004\u0002\n\t)1+\u001b8dK\u0006\u0012\u00111C\u0001\u0006a9Bd\u0006M\u0001\u000b_B$\u0018.\\5{KJ\u0004\u0003&B\n\u0002\u0006\u0005E\u0011A\u0003<bY&$\u0017\r^8sgV\u0011\u0011Q\u0004\t\u0007\u0003?\tI#!\f\u000e\u0005\u0005\u0005\"\u0002BA\u0012\u0003K\t\u0011\"[7nkR\f'\r\\3\u000b\u0007\u0005\u001d\u0012)\u0001\u0006d_2dWm\u0019;j_:LA!a\u000b\u0002\"\t!A*[:u!\u001dA\u0015qFA\u001a\u0003\u000bJ1!!\rB\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0004\u00026\u0005m\u0012qH\u0007\u0003\u0003oQ1!!\u000f&\u0003\r\u0011H\rZ\u0005\u0005\u0003{\t9DA\u0002S\t\u0012\u00032ALA!\u0013\r\t\u0019e\f\u0002\r\u0019\u0006\u0014W\r\\3e!>Lg\u000e\u001e\t\u0004\u0011\u0006\u001d\u0013bAA%\u0003\n9!i\\8mK\u0006t\u0017a\u0003<bY&$\u0017\r^8sg\u0002\"\u0012\u0001\u001b\u0015\u0006-\u0005\u0015\u0011\u0011C\u0001\fGJ,\u0017\r^3N_\u0012,G\u000eF\u00034\u0003+\n)\u0007C\u0004\u0002X]\u0001\r!!\u0017\u0002\u000f],\u0017n\u001a5ugB!\u00111LA1\u001b\t\tiFC\u0002\u0002`\r\na\u0001\\5oC2<\u0017\u0002BA2\u0003;\u0012aAV3di>\u0014\bBBA4/\u0001\u0007q)A\u0005j]R,'oY3qi\"*\u0001!!\u0002\u0002\u0012\u0005Q1KV'XSRD7k\u0012#\u0011\u0005QJ2#B\r\u0002r\u0005]\u0004c\u0001%\u0002t%\u0019\u0011QO!\u0003\r\u0005s\u0017PU3g!\u0011\tI(a!\u000e\u0005\u0005m$\u0002BA?\u0003\u007f\n!![8\u000b\u0005\u0005\u0005\u0015\u0001\u00026bm\u0006L1aQA>)\t\ti'A\u0003ue\u0006Lg\u000eF\u00074\u0003\u0017\u000by)!%\u0002\u0014\u0006U\u0015q\u0013\u0005\b\u0003\u001b[\u0002\u0019AA\u001a\u0003\u0015Ig\u000e];u\u0011\u0015\u00196\u00041\u0001V\u0011\u0015)5\u00041\u0001H\u0011\u0015a6\u00041\u0001H\u0011\u0015\t7\u00041\u0001H\u0011\u001d\tIj\u0007a\u0001\u00033\na\"\u001b8ji&\fGnV3jO\"$8\u000fK\u0003\u001c\u0003\u000b\t\t\u0002F\u00064\u0003?\u000b\t+a)\u0002&\u0006\u001d\u0006bBAG9\u0001\u0007\u00111\u0007\u0005\u0006'r\u0001\r!\u0016\u0005\u0006\u000br\u0001\ra\u0012\u0005\u00069r\u0001\ra\u0012\u0005\u0006Cr\u0001\ra\u0012\u0015\u00069\u0005\u0015\u0011\u0011\u0003\u000b\ng\u00055\u0016qVAY\u0003gCq!!$\u001e\u0001\u0004\t\u0019\u0004C\u0003T;\u0001\u0007Q\u000bC\u0003F;\u0001\u0007q\tC\u0003];\u0001\u0007q\tK\u0003\u001e\u0003\u000b\t\t\u0002F\u00034\u0003s\u000bY\fC\u0004\u0002\u000ez\u0001\r!a\r\t\u000bMs\u0002\u0019A+)\u000by\t)!!\u0005\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\r\u0007\u0003BAc\u0003\u0017l!!a2\u000b\t\u0005%\u0017qP\u0001\u0005Y\u0006tw-\u0003\u0003\u0002N\u0006\u001d'AB(cU\u0016\u001cG\u000fK\u0003\u001a\u0003\u000b\t\t\u0002K\u0003\u0019\u0003\u000b\t\t\u0002"
)
public class SVMWithSGD extends GeneralizedLinearAlgorithm {
   private double stepSize;
   private int numIterations;
   private double regParam;
   private double miniBatchFraction;
   private final HingeGradient gradient;
   private final SquaredL2Updater updater;
   private final GradientDescent optimizer;
   private final List validators;

   public static SVMModel train(final RDD input, final int numIterations) {
      return SVMWithSGD$.MODULE$.train(input, numIterations);
   }

   public static SVMModel train(final RDD input, final int numIterations, final double stepSize, final double regParam) {
      return SVMWithSGD$.MODULE$.train(input, numIterations, stepSize, regParam);
   }

   public static SVMModel train(final RDD input, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction) {
      return SVMWithSGD$.MODULE$.train(input, numIterations, stepSize, regParam, miniBatchFraction);
   }

   public static SVMModel train(final RDD input, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction, final Vector initialWeights) {
      return SVMWithSGD$.MODULE$.train(input, numIterations, stepSize, regParam, miniBatchFraction, initialWeights);
   }

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

   private HingeGradient gradient() {
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

   public SVMModel createModel(final Vector weights, final double intercept) {
      return new SVMModel(weights, intercept);
   }

   public SVMWithSGD(final double stepSize, final int numIterations, final double regParam, final double miniBatchFraction) {
      this.stepSize = stepSize;
      this.numIterations = numIterations;
      this.regParam = regParam;
      this.miniBatchFraction = miniBatchFraction;
      super();
      this.gradient = new HingeGradient();
      this.updater = new SquaredL2Updater();
      this.optimizer = (new GradientDescent(this.gradient(), this.updater())).setStepSize(this.stepSize()).setNumIterations(this.numIterations()).setRegParam(this.regParam()).setMiniBatchFraction(this.miniBatchFraction());
      this.validators = new .colon.colon(DataValidators$.MODULE$.binaryLabelValidator(), scala.collection.immutable.Nil..MODULE$);
   }

   public SVMWithSGD() {
      this((double)1.0F, 100, 0.01, (double)1.0F);
   }
}
