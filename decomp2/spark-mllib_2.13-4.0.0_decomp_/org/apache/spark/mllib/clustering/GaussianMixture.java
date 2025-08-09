package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.Vector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.stat.distribution.MultivariateGaussian;
import org.apache.spark.rdd.RDD;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rb\u0001\u0002\u0016,\u0001YB\u0001\"\u0013\u0001\u0003\u0002\u0004%IA\u0013\u0005\t\u001d\u0002\u0011\t\u0019!C\u0005\u001f\"AQ\u000b\u0001B\u0001B\u0003&1\n\u0003\u0005W\u0001\t\u0005\r\u0011\"\u0003X\u0011!Y\u0006A!a\u0001\n\u0013a\u0006\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0015\u0002-\t\u0011}\u0003!\u00111A\u0005\n)C\u0001\u0002\u0019\u0001\u0003\u0002\u0004%I!\u0019\u0005\tG\u0002\u0011\t\u0011)Q\u0005\u0017\"AA\r\u0001BA\u0002\u0013%Q\r\u0003\u0005j\u0001\t\u0005\r\u0011\"\u0003k\u0011!a\u0007A!A!B\u00131\u0007\"B7\u0001\t\u0013q\u0007\"B7\u0001\t\u0003)\bbB@\u0001\u0005\u0004%IA\u0013\u0005\b\u0003\u0003\u0001\u0001\u0015!\u0003L\u0011%\t\u0019\u0001\u0001a\u0001\n\u0013\t)\u0001C\u0005\u0002\u0014\u0001\u0001\r\u0011\"\u0003\u0002\u0016!A\u0011\u0011\u0004\u0001!B\u0013\t9\u0001C\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005\u001d\u0002\u0001\"\u0001\u0002\u0006!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002BBA\u001a\u0001\u0011\u0005!\nC\u0004\u00028\u0001!\t!!\u000f\t\r\u0005}\u0002\u0001\"\u0001K\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBa!a\u0013\u0001\t\u00039\u0006bBA(\u0001\u0011\u0005\u0011\u0011\u000b\u0005\u0007\u0003/\u0002A\u0011A3\t\u000f\u0005m\u0003\u0001\"\u0001\u0002^!9\u00111\f\u0001\u0005\u0002\u0005u\u0004bBAJ\u0001\u0011%\u0011Q\u0013\u0005\b\u0003#\u0004A\u0011BAj\u0011\u001d\t\u0019\u000f\u0001C\u0005\u0003K<\u0001\"a;,\u0011\u0003Y\u0013Q\u001e\u0004\bU-B\taKAx\u0011\u0019iG\u0005\"\u0001\u0002~\"I\u0011q \u0013C\u0002\u0013\u00051F\u0013\u0005\b\u0005\u0003!\u0003\u0015!\u0003L\u0011\u001d\u0011\u0019\u0001\nC\u0001\u0005\u000bA\u0011Ba\u0005%\u0003\u0003%IA!\u0006\u0003\u001f\u001d\u000bWo]:jC:l\u0015\u000e\u001f;ve\u0016T!\u0001L\u0017\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002/_\u0005)Q\u000e\u001c7jE*\u0011\u0001'M\u0001\u0006gB\f'o\u001b\u0006\u0003eM\na!\u00199bG\",'\"\u0001\u001b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00019T\b\u0005\u00029w5\t\u0011HC\u0001;\u0003\u0015\u00198-\u00197b\u0013\ta\u0014H\u0001\u0004B]f\u0014VM\u001a\t\u0003}\u0019s!a\u0010#\u000f\u0005\u0001\u001bU\"A!\u000b\u0005\t+\u0014A\u0002\u001fs_>$h(C\u0001;\u0013\t)\u0015(A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dC%\u0001D*fe&\fG.\u001b>bE2,'BA#:\u0003\u0005YW#A&\u0011\u0005ab\u0015BA':\u0005\rIe\u000e^\u0001\u0006W~#S-\u001d\u000b\u0003!N\u0003\"\u0001O)\n\u0005IK$\u0001B+oSRDq\u0001\u0016\u0002\u0002\u0002\u0003\u00071*A\u0002yIE\n!a\u001b\u0011\u0002\u001d\r|gN^3sO\u0016t7-\u001a+pYV\t\u0001\f\u0005\u000293&\u0011!,\u000f\u0002\u0007\t>,(\r\\3\u0002%\r|gN^3sO\u0016t7-\u001a+pY~#S-\u001d\u000b\u0003!vCq\u0001V\u0003\u0002\u0002\u0003\u0007\u0001,A\bd_:4XM]4f]\u000e,Gk\u001c7!\u00035i\u0017\r_%uKJ\fG/[8og\u0006\tR.\u0019=Ji\u0016\u0014\u0018\r^5p]N|F%Z9\u0015\u0005A\u0013\u0007b\u0002+\t\u0003\u0003\u0005\raS\u0001\u000f[\u0006D\u0018\n^3sCRLwN\\:!\u0003\u0011\u0019X-\u001a3\u0016\u0003\u0019\u0004\"\u0001O4\n\u0005!L$\u0001\u0002'p]\u001e\f\u0001b]3fI~#S-\u001d\u000b\u0003!.Dq\u0001V\u0006\u0002\u0002\u0003\u0007a-A\u0003tK\u0016$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006_F\u00148\u000f\u001e\t\u0003a\u0002i\u0011a\u000b\u0005\u0006\u00136\u0001\ra\u0013\u0005\u0006-6\u0001\r\u0001\u0017\u0005\u0006?6\u0001\ra\u0013\u0005\u0006I6\u0001\rA\u001a\u000b\u0002_\"\u001aab^?\u0011\u0005a\\X\"A=\u000b\u0005i|\u0013AC1o]>$\u0018\r^5p]&\u0011A0\u001f\u0002\u0006'&t7-Z\u0011\u0002}\u0006)\u0011GL\u001a/a\u0005AanU1na2,7/A\u0005o'\u0006l\u0007\u000f\\3tA\u0005a\u0011N\\5uS\u0006dWj\u001c3fYV\u0011\u0011q\u0001\t\u0006q\u0005%\u0011QB\u0005\u0004\u0003\u0017I$AB(qi&|g\u000eE\u0002q\u0003\u001fI1!!\u0005,\u0005Q9\u0015-^:tS\u0006tW*\u001b=ukJ,Wj\u001c3fY\u0006\u0001\u0012N\\5uS\u0006dWj\u001c3fY~#S-\u001d\u000b\u0004!\u0006]\u0001\u0002\u0003+\u0013\u0003\u0003\u0005\r!a\u0002\u0002\u001b%t\u0017\u000e^5bY6{G-\u001a7!\u0003=\u0019X\r^%oSRL\u0017\r\\'pI\u0016dG\u0003BA\u0010\u0003Ci\u0011\u0001\u0001\u0005\b\u0003G!\u0002\u0019AA\u0007\u0003\u0015iw\u000eZ3mQ\r!r/`\u0001\u0010O\u0016$\u0018J\\5uS\u0006dWj\u001c3fY\"\u001aQc^?\u0002\tM,Go\u0013\u000b\u0005\u0003?\ty\u0003C\u0003J-\u0001\u00071\nK\u0002\u0017ov\fAaZ3u\u0017\"\u001aqc^?\u0002!M,G/T1y\u0013R,'/\u0019;j_:\u001cH\u0003BA\u0010\u0003wAQa\u0018\rA\u0002-C3\u0001G<~\u0003A9W\r^'bq&#XM]1uS>t7\u000fK\u0002\u001aov\f\u0011c]3u\u0007>tg/\u001a:hK:\u001cW\rV8m)\u0011\ty\"a\u0012\t\u000bYS\u0002\u0019\u0001-)\u0007i9X0A\thKR\u001cuN\u001c<fe\u001e,gnY3U_2D3aG<~\u0003\u001d\u0019X\r^*fK\u0012$B!a\b\u0002T!)A\r\ba\u0001M\"\u001aAd^?\u0002\u000f\u001d,GoU3fI\"\u001aQd^?\u0002\u0007I,h\u000e\u0006\u0003\u0002\u000e\u0005}\u0003bBA1=\u0001\u0007\u00111M\u0001\u0005I\u0006$\u0018\r\u0005\u0004\u0002f\u0005-\u0014qN\u0007\u0003\u0003OR1!!\u001b0\u0003\r\u0011H\rZ\u0005\u0005\u0003[\n9GA\u0002S\t\u0012\u0003B!!\u001d\u0002x5\u0011\u00111\u000f\u0006\u0004\u0003kj\u0013A\u00027j]\u0006dw-\u0003\u0003\u0002z\u0005M$A\u0002,fGR|'\u000fK\u0002\u001fov$B!!\u0004\u0002\u0000!9\u0011\u0011M\u0010A\u0002\u0005\u0005\u0005CBAB\u0003\u001b\u000by'\u0004\u0002\u0002\u0006*!\u0011qQAE\u0003\u0011Q\u0017M^1\u000b\u0007\u0005-u&A\u0002ba&LA!a$\u0002\u0006\n9!*\u0019<b%\u0012#\u0005fA\u0010x{\u0006IR\u000f\u001d3bi\u0016<V-[4iiN\fe\u000eZ$bkN\u001c\u0018.\u00198t))\t9*!,\u0002@\u0006%\u0017Q\u001a\t\u0007q\u0005e\u0005,!(\n\u0007\u0005m\u0015H\u0001\u0004UkBdWM\r\t\u0005\u0003?\u000bI+\u0004\u0002\u0002\"*!\u00111UAS\u00031!\u0017n\u001d;sS\n,H/[8o\u0015\r\t9+L\u0001\u0005gR\fG/\u0003\u0003\u0002,\u0006\u0005&\u0001F'vYRLg/\u0019:jCR,w)Y;tg&\fg\u000eC\u0004\u00020\u0002\u0002\r!!-\u0002\t5,\u0017M\u001c\t\u0006\u0003g\u000bY\fW\u0007\u0003\u0003kSA!!\u001e\u00028*\u0011\u0011\u0011X\u0001\u0007EJ,WM_3\n\t\u0005u\u0016Q\u0017\u0002\f\t\u0016t7/\u001a,fGR|'\u000fC\u0004\u0002B\u0002\u0002\r!a1\u0002\u000bMLw-\\1\u0011\u000b\u0005M\u0016Q\u0019-\n\t\u0005\u001d\u0017Q\u0017\u0002\f\t\u0016t7/Z'biJL\u0007\u0010\u0003\u0004\u0002L\u0002\u0002\r\u0001W\u0001\u0007o\u0016Lw\r\u001b;\t\r\u0005=\u0007\u00051\u0001Y\u0003)\u0019X/\\,fS\u001eDGo]\u0001\u000bm\u0016\u001cGo\u001c:NK\u0006tG\u0003BAY\u0003+Dq!a6\"\u0001\u0004\tI.A\u0001y!\u0015q\u00141\\Ap\u0013\r\ti\u000e\u0013\u0002\u0004'\u0016\f\b#BAZ\u0003CD\u0016\u0002BA=\u0003k\u000ba\"\u001b8ji\u000e{g/\u0019:jC:\u001cW\r\u0006\u0003\u0002D\u0006\u001d\bbBAlE\u0001\u0007\u0011\u0011\u001c\u0015\u0004\u0001]l\u0018aD$bkN\u001c\u0018.\u00198NSb$XO]3\u0011\u0005A$3\u0003\u0002\u00138\u0003c\u0004B!a=\u0002|6\u0011\u0011Q\u001f\u0006\u0005\u0003o\fI0\u0001\u0002j_*\u0011\u0011qQ\u0005\u0004\u000f\u0006UHCAAw\u0003Ai\u0015\tW0O+6{f)R!U+J+5+A\tN\u0003b{f*V'`\r\u0016\u000bE+\u0016*F'\u0002\n\u0011d\u001d5pk2$G)[:ue&\u0014W\u000f^3HCV\u001c8/[1ogR1!q\u0001B\u0007\u0005\u001f\u00012\u0001\u000fB\u0005\u0013\r\u0011Y!\u000f\u0002\b\u0005>|G.Z1o\u0011\u0015I\u0005\u00061\u0001L\u0011\u0019\u0011\t\u0002\u000ba\u0001\u0017\u0006\tA-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u0018A!!\u0011\u0004B\u0010\u001b\t\u0011YB\u0003\u0003\u0003\u001e\u0005e\u0018\u0001\u00027b]\u001eLAA!\t\u0003\u001c\t1qJ\u00196fGR\u0004"
)
public class GaussianMixture implements Serializable {
   private int k;
   private double convergenceTol;
   private int maxIterations;
   private long seed;
   private final int nSamples;
   private Option initialModel;

   public static boolean shouldDistributeGaussians(final int k, final int d) {
      return GaussianMixture$.MODULE$.shouldDistributeGaussians(k, d);
   }

   private int k() {
      return this.k;
   }

   private void k_$eq(final int x$1) {
      this.k = x$1;
   }

   private double convergenceTol() {
      return this.convergenceTol;
   }

   private void convergenceTol_$eq(final double x$1) {
      this.convergenceTol = x$1;
   }

   private int maxIterations() {
      return this.maxIterations;
   }

   private void maxIterations_$eq(final int x$1) {
      this.maxIterations = x$1;
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   private int nSamples() {
      return this.nSamples;
   }

   private Option initialModel() {
      return this.initialModel;
   }

   private void initialModel_$eq(final Option x$1) {
      this.initialModel = x$1;
   }

   public GaussianMixture setInitialModel(final GaussianMixtureModel model) {
      .MODULE$.require(model.k() == this.k(), () -> {
         int var10000 = model.k();
         return "Mismatched cluster count (model.k " + var10000 + " != k " + this.k() + ")";
      });
      this.initialModel_$eq(new Some(model));
      return this;
   }

   public Option getInitialModel() {
      return this.initialModel();
   }

   public GaussianMixture setK(final int k) {
      .MODULE$.require(k > 0, () -> "Number of Gaussians must be positive but got " + k);
      this.k_$eq(k);
      return this;
   }

   public int getK() {
      return this.k();
   }

   public GaussianMixture setMaxIterations(final int maxIterations) {
      .MODULE$.require(maxIterations >= 0, () -> "Maximum of iterations must be nonnegative but got " + maxIterations);
      this.maxIterations_$eq(maxIterations);
      return this;
   }

   public int getMaxIterations() {
      return this.maxIterations();
   }

   public GaussianMixture setConvergenceTol(final double convergenceTol) {
      .MODULE$.require(convergenceTol >= (double)0.0F, () -> "Convergence tolerance must be nonnegative but got " + convergenceTol);
      this.convergenceTol_$eq(convergenceTol);
      return this;
   }

   public double getConvergenceTol() {
      return this.convergenceTol();
   }

   public GaussianMixture setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public long getSeed() {
      return this.seed();
   }

   public GaussianMixtureModel run(final RDD data) {
      SparkContext sc = data.sparkContext();
      RDD breezeData = data.map((x$1x) -> x$1x.asBreeze(), scala.reflect.ClassTag..MODULE$.apply(Vector.class)).cache();
      int d = ((Vector)breezeData.first()).length();
      .MODULE$.require(d < GaussianMixture$.MODULE$.MAX_NUM_FEATURES(), () -> "GaussianMixture cannot handle more than " + GaussianMixture$.MODULE$.MAX_NUM_FEATURES() + " features because the size of the covariance matrix is quadratic in the number of features.");
      boolean shouldDistributeGaussians = GaussianMixture$.MODULE$.shouldDistributeGaussians(this.k(), d);
      Option var12 = this.initialModel();
      Tuple2 var10000;
      if (var12 instanceof Some var13) {
         GaussianMixtureModel gmm = (GaussianMixtureModel)var13.value();
         var10000 = new Tuple2(gmm.weights(), gmm.gaussians());
      } else {
         if (!scala.None..MODULE$.equals(var12)) {
            throw new MatchError(var12);
         }

         Vector[] samples = (Vector[])breezeData.takeSample(true, this.k() * this.nSamples(), this.seed());
         var10000 = new Tuple2(scala.Array..MODULE$.fill(this.k(), (JFunction0.mcD.sp)() -> (double)1.0F / (double)this.k(), scala.reflect.ClassTag..MODULE$.Double()), scala.Array..MODULE$.tabulate(this.k(), (ix) -> $anonfun$run$4(this, samples, BoxesRunTime.unboxToInt(ix)), scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class)));
      }

      Tuple2 var11 = var10000;
      if (var11 == null) {
         throw new MatchError(var11);
      } else {
         double[] weights = (double[])var11._1();
         MultivariateGaussian[] gaussians = (MultivariateGaussian[])var11._2();
         Tuple2 var10 = new Tuple2(weights, gaussians);
         double[] weights = (double[])var10._1();
         MultivariateGaussian[] gaussians = (MultivariateGaussian[])var10._2();
         double llh = -Double.MAX_VALUE;
         double llhp = (double)0.0F;
         int iter = 0;

         while(iter < this.maxIterations() && scala.math.package..MODULE$.abs(llh - llhp) > this.convergenceTol()) {
            Broadcast compute = sc.broadcast((Function2)(sumsx, x) -> ExpectationSum$.MODULE$.add(weights, gaussians, sumsx, x), scala.reflect.ClassTag..MODULE$.apply(Function2.class));
            ExpectationSum x$1 = ExpectationSum$.MODULE$.zero(this.k(), d);
            Function2 x$2 = (Function2)compute.value();
            Function2 x$3 = (x$3x, x$4x) -> x$3x.$plus$eq(x$4x);
            int x$4 = breezeData.treeAggregate$default$4(x$1);
            ExpectationSum sums = (ExpectationSum)breezeData.treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(ExpectationSum.class));
            double sumWeights = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(sums.weights()).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            if (shouldDistributeGaussians) {
               int numPartitions = scala.math.package..MODULE$.min(this.k(), 1024);
               Seq tuples = (Seq)scala.package..MODULE$.Seq().tabulate(this.k(), (ix) -> $anonfun$run$7(sums, BoxesRunTime.unboxToInt(ix)));
               Tuple2 var36 = scala.collection.ArrayOps..MODULE$.unzip$extension(.MODULE$.refArrayOps(sc.parallelize(tuples, numPartitions, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)).map((x0$1) -> {
                  if (x0$1 != null) {
                     DenseVector mean = (DenseVector)x0$1._1();
                     DenseMatrix sigma = (DenseMatrix)x0$1._2();
                     double weight = BoxesRunTime.unboxToDouble(x0$1._3());
                     return this.updateWeightsAndGaussians(mean, sigma, weight, sumWeights);
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).collect()), .MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class));
               if (var36 == null) {
                  throw new MatchError(var36);
               }

               double[] ws = (double[])var36._1();
               MultivariateGaussian[] gs = (MultivariateGaussian[])var36._2();
               Tuple2 var35 = new Tuple2(ws, gs);
               double[] ws = (double[])var35._1();
               MultivariateGaussian[] gs = (MultivariateGaussian[])var35._2();
               scala.Array..MODULE$.copy(ws, 0, weights, 0, ws.length);
               scala.Array..MODULE$.copy(gs, 0, gaussians, 0, gs.length);
            } else {
               for(int i = 0; i < this.k(); ++i) {
                  Tuple2 var43 = this.updateWeightsAndGaussians(sums.means()[i], sums.sigmas()[i], sums.weights()[i], sumWeights);
                  if (var43 == null) {
                     throw new MatchError(var43);
                  }

                  double weight = var43._1$mcD$sp();
                  MultivariateGaussian gaussian = (MultivariateGaussian)var43._2();
                  Tuple2 var42 = new Tuple2(BoxesRunTime.boxToDouble(weight), gaussian);
                  double weight = var42._1$mcD$sp();
                  MultivariateGaussian gaussian = (MultivariateGaussian)var42._2();
                  weights[i] = weight;
                  gaussians[i] = gaussian;
               }
            }

            llhp = llh;
            llh = sums.logLikelihood();
            ++iter;
            compute.destroy();
         }

         breezeData.unpersist(breezeData.unpersist$default$1());
         return new GaussianMixtureModel(weights, gaussians);
      }
   }

   public GaussianMixtureModel run(final JavaRDD data) {
      return this.run(data.rdd());
   }

   private Tuple2 updateWeightsAndGaussians(final DenseVector mean, final DenseMatrix sigma, final double weight, final double sumWeights) {
      DenseVector mu = (DenseVector)mean.$div$eq(BoxesRunTime.boxToDouble(weight), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_S_Double_OpDiv());
      BLAS$.MODULE$.syr(-weight, Vectors$.MODULE$.fromBreeze(mu), (org.apache.spark.mllib.linalg.DenseMatrix)Matrices$.MODULE$.fromBreeze(sigma));
      double newWeight = weight / sumWeights;
      MultivariateGaussian newGaussian = new MultivariateGaussian(mu, (DenseMatrix)sigma.$div(BoxesRunTime.boxToDouble(weight), breeze.linalg.operators.HasOps..MODULE$.op_DM_S_Double_OpDiv()));
      return new Tuple2(BoxesRunTime.boxToDouble(newWeight), newGaussian);
   }

   private DenseVector vectorMean(final Seq x) {
      DenseVector v = breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(((Vector)x.apply(0)).length(), scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      x.foreach((xi) -> (DenseVector)v.$plus$eq(xi, breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd()));
      return (DenseVector)v.$div(BoxesRunTime.boxToDouble((double)x.length()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv());
   }

   private DenseMatrix initCovariance(final Seq x) {
      DenseVector mu = this.vectorMean(x);
      DenseVector ss = breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(((Vector)x.apply(0)).length(), scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      x.foreach((xi) -> {
         Vector d = (Vector)xi.$minus(mu, breeze.linalg.operators.HasOps..MODULE$.pureFromUpdate(breeze.linalg.operators.HasOps..MODULE$.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpSub()), breeze.linalg.Vector..MODULE$.canCopy()));
         return (DenseVector)ss.$plus$eq(d.$up$colon$up(BoxesRunTime.boxToDouble((double)2.0F), breeze.linalg.operators.HasOps..MODULE$.impl_Op_V_S_eq_V_Double_OpPow()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd());
      });
      return (DenseMatrix)breeze.linalg.diag..MODULE$.apply(ss.$div(BoxesRunTime.boxToDouble((double)x.length()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpDiv()), breeze.linalg.diag..MODULE$.diagDVDMImpl(scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()));
   }

   // $FF: synthetic method
   public static final MultivariateGaussian $anonfun$run$4(final GaussianMixture $this, final Vector[] samples$1, final int i) {
      Vector[] slice = (Vector[])scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.refArrayOps((Object[])samples$1), i * $this.nSamples(), (i + 1) * $this.nSamples());
      return new MultivariateGaussian($this.vectorMean(scala.collection.ArrayOps..MODULE$.toSeq$extension(.MODULE$.refArrayOps((Object[])slice))), $this.initCovariance(scala.collection.ArrayOps..MODULE$.toSeq$extension(.MODULE$.refArrayOps((Object[])slice))));
   }

   // $FF: synthetic method
   public static final Tuple3 $anonfun$run$7(final ExpectationSum sums$1, final int i) {
      return new Tuple3(sums$1.means()[i], sums$1.sigmas()[i], BoxesRunTime.boxToDouble(sums$1.weights()[i]));
   }

   private GaussianMixture(final int k, final double convergenceTol, final int maxIterations, final long seed) {
      this.k = k;
      this.convergenceTol = convergenceTol;
      this.maxIterations = maxIterations;
      this.seed = seed;
      super();
      this.nSamples = 5;
      this.initialModel = scala.None..MODULE$;
   }

   public GaussianMixture() {
      this(2, 0.01, 100, org.apache.spark.util.Utils..MODULE$.random().nextLong());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
