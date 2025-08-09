package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.Updater;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%f\u0001C\u000f\u001f!\u0003\r\t\u0001\t\u0015\t\u000bM\u0002A\u0011A\u001b\t\re\u0002A\u0011\u0001\u0011;\u0011\u0019A\u0005\u0001\"\u0001!\u0013\u001e1aN\bE\u0001A=4a!\b\u0010\t\u0002\u0001\u0002\b\"B=\u0006\t\u0003Q\bbB>\u0006\u0005\u0004%\t\u0001 \u0005\b\u0003\u000b)\u0001\u0015!\u0003~\u0011!\t9!\u0002b\u0001\n\u0003a\bbBA\u0005\u000b\u0001\u0006I! \u0005\n\u0003\u0017)!\u0019!C\u0001\u0003\u001bA\u0001\"!\u0005\u0006A\u0003%\u0011q\u0002\u0005\t\u0003')!\u0019!C\u0001y\"9\u0011QC\u0003!\u0002\u0013i\b\u0002CA\f\u000b\t\u0007I\u0011\u0001?\t\u000f\u0005eQ\u0001)A\u0005{\"I\u00111D\u0003C\u0002\u0013\u0005\u0011Q\u0002\u0005\t\u0003;)\u0001\u0015!\u0003\u0002\u0010!I\u0011qD\u0003C\u0002\u0013\u0005\u0011Q\u0002\u0005\t\u0003C)\u0001\u0015!\u0003\u0002\u0010!I\u00111E\u0003C\u0002\u0013\u0005\u0011Q\u0002\u0005\t\u0003K)\u0001\u0015!\u0003\u0002\u0010!9\u0011qE\u0003\u0005\u0002\u0005%\u0002bBA \u000b\u0011\u0005\u0011\u0011\t\u0005\b\u0003C*A\u0011AA2\u0011\u001d\ti(\u0002C\u0001\u0003\u007fBq!!%\u0006\t\u0003\t\u0019\nC\u0005\u0002 \u0016\t\t\u0011\"\u0003\u0002\"\n)b)Y2u_JL'0\u0019;j_:l\u0015m\u00195j]\u0016\u001c(BA\u0010!\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003C\t\n!!\u001c7\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c2\u0001A\u00150!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fMB\u0011\u0001'M\u0007\u0002=%\u0011!G\b\u0002\u001c\r\u0006\u001cGo\u001c:ju\u0006$\u0018n\u001c8NC\u000eD\u0017N\\3t!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012A\u000e\t\u0003U]J!\u0001O\u0016\u0003\tUs\u0017\u000e^\u0001\u0011S:LGoQ8fM\u001aL7-[3oiN$\"aO\"\u0011\u0005q\nU\"A\u001f\u000b\u0005yz\u0014A\u00027j]\u0006dwM\u0003\u0002AE\u0005)Q\u000e\u001c7jE&\u0011!)\u0010\u0002\u0007-\u0016\u001cGo\u001c:\t\u000b\u0011\u0013\u0001\u0019A#\u0002\u00179,XNR3biV\u0014Xm\u001d\t\u0003U\u0019K!aR\u0016\u0003\u0007%sG/A\u0005ue\u0006Lg.S7qYR!!j\u00161b!\u0011Q3*T)\n\u00051[#A\u0002+va2,'\u0007\u0005\u0002O!6\tqJ\u0003\u0002?A%\u0011!i\u0014\t\u0004UI#\u0016BA*,\u0005\u0015\t%O]1z!\tQS+\u0003\u0002WW\t1Ai\\;cY\u0016DQ\u0001W\u0002A\u0002e\u000bA\u0001Z1uCB\u0019!,X0\u000e\u0003mS!\u0001\u0018\u0012\u0002\u0007I$G-\u0003\u0002_7\n\u0019!\u000b\u0012#\u0011\t)ZEk\u000f\u0005\u0006\t\u000e\u0001\r!\u0012\u0005\u0006E\u000e\u0001\raY\u0001\u0005Y>\u001c8\u000f\u0005\u0002eW:\u0011Q-\u001b\t\u0003M.j\u0011a\u001a\u0006\u0003QR\na\u0001\u0010:p_Rt\u0014B\u00016,\u0003\u0019\u0001&/\u001a3fM&\u0011A.\u001c\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005)\\\u0013!\u0006$bGR|'/\u001b>bi&|g.T1dQ&tWm\u001d\t\u0003a\u0015\u00192!B\u0015r!\t\u0011x/D\u0001t\u0015\t!X/\u0001\u0002j_*\ta/\u0001\u0003kCZ\f\u0017B\u0001=t\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tq.\u0001\u0002H\tV\tQ\u0010E\u0002\u007f\u0003\u0007i\u0011a \u0006\u0004\u0003\u0003)\u0018\u0001\u00027b]\u001eL!\u0001\\@\u0002\u0007\u001d#\u0005%A\u0003BI\u0006lw+\u0001\u0004BI\u0006lw\u000bI\u0001\u0011gV\u0004\bo\u001c:uK\u0012\u001cv\u000e\u001c<feN,\"!a\u0004\u0011\u0007)\u0012V0A\ttkB\u0004xN\u001d;fIN{GN^3sg\u0002\nA\u0002T8hSN$\u0018n\u0019'pgN\fQ\u0002T8hSN$\u0018n\u0019'pgN\u0004\u0013\u0001D*rk\u0006\u0014X\rZ#se>\u0014\u0018!D*rk\u0006\u0014X\rZ#se>\u0014\b%\u0001\rtkB\u0004xN\u001d;fIJ+wM]3tg>\u0014Hj\\:tKN\f\u0011d];qa>\u0014H/\u001a3SK\u001e\u0014Xm]:pe2{7o]3tA\u0005I2/\u001e9q_J$X\rZ\"mCN\u001c\u0018NZ5fe2{7o]3t\u0003i\u0019X\u000f\u001d9peR,Gm\u00117bgNLg-[3s\u0019>\u001c8/Z:!\u0003=\u0019X\u000f\u001d9peR,G\rT8tg\u0016\u001c\u0018\u0001E:vaB|'\u000f^3e\u0019>\u001c8/Z:!\u0003-\u0001\u0018M]:f'>dg/\u001a:\u0015\r\u0005-\u0012qGA\u001e!\u0011\ti#a\r\u000e\u0005\u0005=\"bAA\u0019\u007f\u0005aq\u000e\u001d;j[&T\u0018\r^5p]&!\u0011QGA\u0018\u0005\u001d)\u0006\u000fZ1uKJDa!!\u000f\u0018\u0001\u0004\u0019\u0017AB:pYZ,'\u000f\u0003\u0004\u0002>]\u0001\r!R\u0001\u0011G>,gMZ5dS\u0016tGo]*ju\u0016\f\u0011\u0002]1sg\u0016dun]:\u0015\u0019\u0005\r\u0013\u0011JA'\u0003#\nY&a\u0018\u0011\u0007A\n)%C\u0002\u0002Hy\u0011\u0011EQ1tK\u001a\u000b7\r^8sSj\fG/[8o\u001b\u0006\u001c\u0007.\u001b8fg\u001e\u0013\u0018\rZ5f]RDa!a\u0013\u0019\u0001\u0004\u0019\u0017\u0001\u00037pgN4UO\\2\t\r\u0005=\u0003\u00041\u0001F\u0003)1\u0017m\u0019;peNK'0\u001a\u0005\b\u0003'B\u0002\u0019AA+\u000311\u0017\u000e^%oi\u0016\u00148-\u001a9u!\rQ\u0013qK\u0005\u0004\u00033Z#a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003;B\u0002\u0019AA+\u0003%1\u0017\u000e\u001e'j]\u0016\f'\u000fC\u0003E1\u0001\u0007Q)A\tta2LGoQ8fM\u001aL7-[3oiN$B\"!\u001a\u0002r\u0005U\u0014qOA=\u0003w\u0002rAKA4)6\u000bY'C\u0002\u0002j-\u0012a\u0001V;qY\u0016\u001c\u0004c\u0001(\u0002n%\u0019\u0011qN(\u0003\r5\u000bGO]5y\u0011\u0019\t\u0019(\u0007a\u0001\u001b\u0006a1m\\3gM&\u001c\u0017.\u001a8ug\")A)\u0007a\u0001\u000b\"1\u0011qJ\rA\u0002\u0015Cq!a\u0015\u001a\u0001\u0004\t)\u0006C\u0004\u0002^e\u0001\r!!\u0016\u0002'\r|WNY5oK\u000e{WM\u001a4jG&,g\u000e^:\u0015\u00175\u000b\t)!\"\u0002\n\u00065\u0015q\u0012\u0005\u0007\u0003\u0007S\u0002\u0019\u0001+\u0002\u0013%tG/\u001a:dKB$\bBBAD5\u0001\u0007Q*\u0001\u0004mS:,\u0017M\u001d\u0005\b\u0003\u0017S\u0002\u0019AA6\u0003\u001d1\u0017m\u0019;peNDq!a\u0015\u001b\u0001\u0004\t)\u0006C\u0004\u0002^i\u0001\r!!\u0016\u0002!\u001d,GOU1x!J,G-[2uS>tG#\u0003+\u0002\u0016\u0006e\u00151TAO\u0011\u0019\t9j\u0007a\u0001\u001b\u0006Aa-Z1ukJ,7\u000f\u0003\u0004\u0002\u0004n\u0001\r\u0001\u0016\u0005\u0007\u0003\u000f[\u0002\u0019A'\t\u000f\u0005-5\u00041\u0001\u0002l\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0015\t\u0004}\u0006\u0015\u0016bAAT\u007f\n1qJ\u00196fGR\u0004"
)
public interface FactorizationMachines extends FactorizationMachinesParams {
   static double getRawPrediction(final Vector features, final double intercept, final Vector linear, final Matrix factors) {
      return FactorizationMachines$.MODULE$.getRawPrediction(features, intercept, linear, factors);
   }

   static Vector combineCoefficients(final double intercept, final Vector linear, final Matrix factors, final boolean fitIntercept, final boolean fitLinear) {
      return FactorizationMachines$.MODULE$.combineCoefficients(intercept, linear, factors, fitIntercept, fitLinear);
   }

   static Tuple3 splitCoefficients(final Vector coefficients, final int numFeatures, final int factorSize, final boolean fitIntercept, final boolean fitLinear) {
      return FactorizationMachines$.MODULE$.splitCoefficients(coefficients, numFeatures, factorSize, fitIntercept, fitLinear);
   }

   static BaseFactorizationMachinesGradient parseLoss(final String lossFunc, final int factorSize, final boolean fitIntercept, final boolean fitLinear, final int numFeatures) {
      return FactorizationMachines$.MODULE$.parseLoss(lossFunc, factorSize, fitIntercept, fitLinear, numFeatures);
   }

   static Updater parseSolver(final String solver, final int coefficientsSize) {
      return FactorizationMachines$.MODULE$.parseSolver(solver, coefficientsSize);
   }

   static String[] supportedLosses() {
      return FactorizationMachines$.MODULE$.supportedLosses();
   }

   static String[] supportedClassifierLosses() {
      return FactorizationMachines$.MODULE$.supportedClassifierLosses();
   }

   static String[] supportedRegressorLosses() {
      return FactorizationMachines$.MODULE$.supportedRegressorLosses();
   }

   static String SquaredError() {
      return FactorizationMachines$.MODULE$.SquaredError();
   }

   static String LogisticLoss() {
      return FactorizationMachines$.MODULE$.LogisticLoss();
   }

   static String[] supportedSolvers() {
      return FactorizationMachines$.MODULE$.supportedSolvers();
   }

   static String AdamW() {
      return FactorizationMachines$.MODULE$.AdamW();
   }

   static String GD() {
      return FactorizationMachines$.MODULE$.GD();
   }

   // $FF: synthetic method
   static org.apache.spark.mllib.linalg.Vector initCoefficients$(final FactorizationMachines $this, final int numFeatures) {
      return $this.initCoefficients(numFeatures);
   }

   default org.apache.spark.mllib.linalg.Vector initCoefficients(final int numFeatures) {
      Random rnd = new Random(BoxesRunTime.unboxToLong(this.$(this.seed())));
      org.apache.spark.mllib.linalg.Vector initialCoefficients = Vectors$.MODULE$.dense((double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.doubleArrayOps((double[])scala.Array..MODULE$.fill(BoxesRunTime.unboxToInt(this.$(this.factorSize())) * numFeatures, (JFunction0.mcD.sp)() -> rnd.nextGaussian() * BoxesRunTime.unboxToDouble(this.$(this.initStd())), scala.reflect.ClassTag..MODULE$.Double())), BoxesRunTime.unboxToBoolean(this.$(this.fitLinear())) ? new double[numFeatures] : scala.Array..MODULE$.emptyDoubleArray(), scala.reflect.ClassTag..MODULE$.Double())), BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? new double[1] : scala.Array..MODULE$.emptyDoubleArray(), scala.reflect.ClassTag..MODULE$.Double()));
      return initialCoefficients;
   }

   // $FF: synthetic method
   static Tuple2 trainImpl$(final FactorizationMachines $this, final RDD data, final int numFeatures, final String loss) {
      return $this.trainImpl(data, numFeatures, loss);
   }

   default Tuple2 trainImpl(final RDD data, final int numFeatures, final String loss) {
      org.apache.spark.mllib.linalg.Vector initialCoefficients = this.initCoefficients(numFeatures);
      int coefficientsSize = initialCoefficients.size();
      BaseFactorizationMachinesGradient gradient = FactorizationMachines$.MODULE$.parseLoss(loss, BoxesRunTime.unboxToInt(this.$(this.factorSize())), BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToBoolean(this.$(this.fitLinear())), numFeatures);
      Updater updater = FactorizationMachines$.MODULE$.parseSolver((String)this.$(this.solver()), coefficientsSize);
      GradientDescent optimizer = (new GradientDescent(gradient, updater)).setStepSize(BoxesRunTime.unboxToDouble(this.$(this.stepSize()))).setNumIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setRegParam(BoxesRunTime.unboxToDouble(this.$(this.regParam()))).setMiniBatchFraction(BoxesRunTime.unboxToDouble(this.$(this.miniBatchFraction()))).setConvergenceTol(BoxesRunTime.unboxToDouble(this.$(this.tol())));
      Tuple2 var11 = optimizer.optimizeWithLossReturned(data, initialCoefficients);
      if (var11 != null) {
         org.apache.spark.mllib.linalg.Vector coefficients = (org.apache.spark.mllib.linalg.Vector)var11._1();
         double[] lossHistory = (double[])var11._2();
         Tuple2 var10 = new Tuple2(coefficients, lossHistory);
         org.apache.spark.mllib.linalg.Vector coefficients = (org.apache.spark.mllib.linalg.Vector)var10._1();
         double[] lossHistory = (double[])var10._2();
         return new Tuple2(coefficients.asML(), lossHistory);
      } else {
         throw new MatchError(var11);
      }
   }

   static void $init$(final FactorizationMachines $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
