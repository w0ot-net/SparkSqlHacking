package breeze.stats.regression;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$mcD$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.storage.Zero$;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rb\u0001\u0002\u001b6\trB\u0001B\u0015\u0001\u0003\u0016\u0004%\ta\u0015\u0005\t;\u0002\u0011\t\u0012)A\u0005)\"Aa\f\u0001BK\u0002\u0013\u0005q\f\u0003\u0005d\u0001\tE\t\u0015!\u0003a\u0011!!\u0007A!f\u0001\n\u0003)\u0007\u0002\u00034\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u0011\u001d\u0004!Q3A\u0005\u0002!D\u0001\u0002\u001c\u0001\u0003\u0012\u0003\u0006I!\u001b\u0005\t[\u0002\u0011)\u001a!C\u0001]\"A!\u000f\u0001B\tB\u0003%q\u000e\u0003\u0005t\u0001\tU\r\u0011\"\u0001f\u0011!!\bA!E!\u0002\u0013Q\u0006\"B;\u0001\t\u00031\bbB@\u0001\u0005\u0004%Ia\u0018\u0005\b\u0003\u0003\u0001\u0001\u0015!\u0003a\u0011!\t\u0019\u0001\u0001b\u0001\n\u0013\u0019\u0006bBA\u0003\u0001\u0001\u0006I\u0001\u0016\u0005\t\u0003\u000f\u0001!\u0019!C\u0005?\"9\u0011\u0011\u0002\u0001!\u0002\u0013\u0001\u0007BCA\u0006\u0001!\u0015\r\u0011\"\u0001\u0002\u000e!9\u0011Q\u0003\u0001\u0005\n\u0005]\u0001bBA\u000f\u0001\u0011%\u0011q\u0004\u0005\u0007\u0003W\u0001A\u0011B3\t\u000f\u00055\u0002\u0001\"\u0003\u00020!I\u0011\u0011\b\u0001\u0002\u0002\u0013\u0005\u00111\b\u0005\n\u0003\u0013\u0002\u0011\u0013!C\u0001\u0003\u0017B\u0011\"!\u0019\u0001#\u0003%\t!a\u0019\t\u0013\u0005\u001d\u0004!%A\u0005\u0002\u0005%\u0004\"CA7\u0001E\u0005I\u0011AA8\u0011%\t\u0019\bAI\u0001\n\u0003\t)\bC\u0005\u0002z\u0001\t\n\u0011\"\u0001\u0002j!I\u00111\u0010\u0001\u0002\u0002\u0013\u0005\u0013Q\u0010\u0005\t\u0003\u001f\u0003\u0011\u0011!C\u0001]\"I\u0011\u0011\u0013\u0001\u0002\u0002\u0013\u0005\u00111\u0013\u0005\n\u0003?\u0003\u0011\u0011!C!\u0003CC\u0011\"a,\u0001\u0003\u0003%\t!!-\t\u0013\u0005m\u0006!!A\u0005B\u0005u\u0006\"CAa\u0001\u0005\u0005I\u0011IAb\u0011%\t)\rAA\u0001\n\u0003\n9\rC\u0005\u0002J\u0002\t\t\u0011\"\u0011\u0002L\u001eI\u0011qZ\u001b\u0002\u0002#%\u0011\u0011\u001b\u0004\tiU\n\t\u0011#\u0003\u0002T\"1QO\u000bC\u0001\u0003WD\u0011\"!2+\u0003\u0003%)%a2\t\u0013\u00055(&!A\u0005\u0002\u0006=\b\"CA\u007fUE\u0005I\u0011AA;\u0011%\tyPKI\u0001\n\u0003\tI\u0007C\u0005\u0003\u0002)\n\t\u0011\"!\u0003\u0004!I!Q\u0003\u0016\u0012\u0002\u0013\u0005\u0011Q\u000f\u0005\n\u0005/Q\u0013\u0013!C\u0001\u0003SB\u0011B!\u0007+\u0003\u0003%IAa\u0007\u0003\u001f1\u000b7o]8DC2\u001cW\u000f\\1u_JT!AN\u001c\u0002\u0015I,wM]3tg&|gN\u0003\u00029s\u0005)1\u000f^1ug*\t!(\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0011\u0001Qh\u0011$\u0011\u0005y\nU\"A \u000b\u0003\u0001\u000bQa]2bY\u0006L!AQ \u0003\r\u0005s\u0017PU3g!\tqD)\u0003\u0002F\u007f\t9\u0001K]8ek\u000e$\bCA$P\u001d\tAUJ\u0004\u0002J\u00196\t!J\u0003\u0002Lw\u00051AH]8pizJ\u0011\u0001Q\u0005\u0003\u001d~\nq\u0001]1dW\u0006<W-\u0003\u0002Q#\na1+\u001a:jC2L'0\u00192mK*\u0011ajP\u0001\u0005I\u0006$\u0018-F\u0001U!\r)\u0006LW\u0007\u0002-*\u0011q+O\u0001\u0007Y&t\u0017\r\\4\n\u0005e3&a\u0003#f]N,W*\u0019;sSb\u0004\"AP.\n\u0005q{$A\u0002#pk\ndW-A\u0003eCR\f\u0007%A\u0004pkR\u0004X\u000f^:\u0016\u0003\u0001\u00042!V1[\u0013\t\u0011gKA\u0006EK:\u001cXMV3di>\u0014\u0018\u0001C8viB,Ho\u001d\u0011\u0002\r1\fWN\u00193b+\u0005Q\u0016a\u00027b[\n$\u0017\rI\u0001\no>\u00148.\u0011:sCf,\u0012!\u001b\t\u0004})T\u0016BA6@\u0005\u0015\t%O]1z\u0003)9xN]6BeJ\f\u0017\u0010I\u0001\t\u001b\u0006Cv,\u0013+F%V\tq\u000e\u0005\u0002?a&\u0011\u0011o\u0010\u0002\u0004\u0013:$\u0018!C'B1~KE+\u0012*!\u0003EIU\n\u0015*P-\u0016{F\u000b\u0013*F'\"{E\nR\u0001\u0013\u00136\u0003&k\u0014,F?RC%+R*I\u001f2#\u0005%\u0001\u0004=S:LGO\u0010\u000b\bofT8\u0010`?\u007f!\tA\b!D\u00016\u0011\u0015\u0011V\u00021\u0001U\u0011\u0015qV\u00021\u0001a\u0011\u0015!W\u00021\u0001[\u0011\u00159W\u00021\u0001j\u0011\u001diW\u0002%AA\u0002=Dqa]\u0007\u0011\u0002\u0003\u0007!,\u0001\u0006pkR\u0004X\u000f^\"paf\f1b\\;uaV$8i\u001c9zA\u0005\u00112/\u001b8hY\u0016\u001cu\u000e\\;n]6\u000bGO]5y\u0003M\u0019\u0018N\\4mK\u000e{G.^7o\u001b\u0006$(/\u001b=!\u0003%\u0011Xm];miZ+7-\u0001\u0006sKN,H\u000e\u001e,fG\u0002\naA]3tk2$XCAA\b!\rA\u0018\u0011C\u0005\u0004\u0003')$a\u0003'bgN|'+Z:vYR\faa\u001d5sS:\\Gc\u0001.\u0002\u001a!1\u00111D\u000bA\u0002i\u000b\u0011\u0001_\u0001\u000bG>\u0004\u0018pQ8mk6tG\u0003BA\u0011\u0003O\u00012APA\u0012\u0013\r\t)c\u0010\u0002\u0005+:LG\u000f\u0003\u0004\u0002*Y\u0001\ra\\\u0001\u0007G>dW/\u001c8\u0002\u001f\r|W\u000e];uKJ\u001b\u0018/^1sK\u0012\f\u0011#Z:uS6\fG/Z(oK\u000e{G.^7o)\u0011\t\t$a\u000e\u0011\u0007a\f\u0019$C\u0002\u00026U\u0012A\u0004T3bgR\u001c\u0016/^1sKN\u0014Vm\u001a:fgNLwN\u001c*fgVdG\u000f\u0003\u0004\u0002*a\u0001\ra\\\u0001\u0005G>\u0004\u0018\u0010F\u0007x\u0003{\ty$!\u0011\u0002D\u0005\u0015\u0013q\t\u0005\b%f\u0001\n\u00111\u0001U\u0011\u001dq\u0016\u0004%AA\u0002\u0001Dq\u0001Z\r\u0011\u0002\u0003\u0007!\fC\u0004h3A\u0005\t\u0019A5\t\u000f5L\u0002\u0013!a\u0001_\"91/\u0007I\u0001\u0002\u0004Q\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u001bR3\u0001VA(W\t\t\t\u0006\u0005\u0003\u0002T\u0005uSBAA+\u0015\u0011\t9&!\u0017\u0002\u0013Ut7\r[3dW\u0016$'bAA.\u007f\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005}\u0013Q\u000b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003KR3\u0001YA(\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"!a\u001b+\u0007i\u000by%\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005E$fA5\u0002P\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAA<U\ry\u0017qJ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0010\t\u0005\u0003\u0003\u000bY)\u0004\u0002\u0002\u0004*!\u0011QQAD\u0003\u0011a\u0017M\\4\u000b\u0005\u0005%\u0015\u0001\u00026bm\u0006LA!!$\u0002\u0004\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0016\u0006m\u0005c\u0001 \u0002\u0018&\u0019\u0011\u0011T \u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u001e\n\n\t\u00111\u0001p\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0015\t\u0007\u0003K\u000bY+!&\u000e\u0005\u0005\u001d&bAAU\u007f\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u00055\u0016q\u0015\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00024\u0006e\u0006c\u0001 \u00026&\u0019\u0011qW \u0003\u000f\t{w\u000e\\3b]\"I\u0011Q\u0014\u0013\u0002\u0002\u0003\u0007\u0011QS\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u0000\u0005}\u0006\u0002CAOK\u0005\u0005\t\u0019A8\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012a\\\u0001\ti>\u001cFO]5oOR\u0011\u0011qP\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0016Q\u001a\u0005\n\u0003;C\u0013\u0011!a\u0001\u0003+\u000bq\u0002T1tg>\u001c\u0015\r\\2vY\u0006$xN\u001d\t\u0003q*\u001aRAKAk\u0003C\u00042\"a6\u0002^R\u0003',[8[o6\u0011\u0011\u0011\u001c\u0006\u0004\u00037|\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003?\fINA\tBEN$(/Y2u\rVt7\r^5p]Z\u0002B!a9\u0002j6\u0011\u0011Q\u001d\u0006\u0005\u0003O\f9)\u0001\u0002j_&\u0019\u0001+!:\u0015\u0005\u0005E\u0017!B1qa2LH#D<\u0002r\u0006M\u0018Q_A|\u0003s\fY\u0010C\u0003S[\u0001\u0007A\u000bC\u0003_[\u0001\u0007\u0001\rC\u0003e[\u0001\u0007!\fC\u0003h[\u0001\u0007\u0011\u000eC\u0004n[A\u0005\t\u0019A8\t\u000fMl\u0003\u0013!a\u00015\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$S'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00137\u0003\u001d)h.\u00199qYf$BA!\u0002\u0003\u0012A)aHa\u0002\u0003\f%\u0019!\u0011B \u0003\r=\u0003H/[8o!%q$Q\u0002+a5&|',C\u0002\u0003\u0010}\u0012a\u0001V;qY\u00164\u0004\u0002\u0003B\na\u0005\u0005\t\u0019A<\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tu\u0001\u0003BAA\u0005?IAA!\t\u0002\u0004\n1qJ\u00196fGR\u0004"
)
public class LassoCalculator implements Product, Serializable {
   private LassoResult result;
   private final DenseMatrix data;
   private final DenseVector outputs;
   private final double lambda;
   private final double[] workArray;
   private final int MAX_ITER;
   private final double IMPROVE_THRESHOLD;
   private final DenseVector outputCopy;
   private final DenseMatrix singleColumnMatrix;
   private final DenseVector resultVec;
   private volatile boolean bitmap$0;

   public static double $lessinit$greater$default$6() {
      return LassoCalculator$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return LassoCalculator$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option unapply(final LassoCalculator x$0) {
      return LassoCalculator$.MODULE$.unapply(x$0);
   }

   public static double apply$default$6() {
      return LassoCalculator$.MODULE$.apply$default$6();
   }

   public static int apply$default$5() {
      return LassoCalculator$.MODULE$.apply$default$5();
   }

   public static LassoCalculator apply(final DenseMatrix data, final DenseVector outputs, final double lambda, final double[] workArray, final int MAX_ITER, final double IMPROVE_THRESHOLD) {
      return LassoCalculator$.MODULE$.apply(data, outputs, lambda, workArray, MAX_ITER, IMPROVE_THRESHOLD);
   }

   public static Function1 tupled() {
      return LassoCalculator$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return LassoCalculator$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public DenseMatrix data() {
      return this.data;
   }

   public DenseVector outputs() {
      return this.outputs;
   }

   public double lambda() {
      return this.lambda;
   }

   public double[] workArray() {
      return this.workArray;
   }

   public int MAX_ITER() {
      return this.MAX_ITER;
   }

   public double IMPROVE_THRESHOLD() {
      return this.IMPROVE_THRESHOLD;
   }

   private DenseVector outputCopy() {
      return this.outputCopy;
   }

   private DenseMatrix singleColumnMatrix() {
      return this.singleColumnMatrix;
   }

   private DenseVector resultVec() {
      return this.resultVec;
   }

   private LassoResult result$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            boolean improvedResult = true;
            int iter = 0;

            while(improvedResult && iter < this.MAX_ITER()) {
               ++iter;
               improvedResult = false;
               int index$macro$2 = 0;

               for(int limit$macro$4 = this.data().cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  LeastSquaresRegressionResult eoc = this.estimateOneColumn(index$macro$2);
                  double oldCoefficient = this.resultVec().apply$mcD$sp(index$macro$2);
                  this.resultVec().update$mcD$sp(index$macro$2, this.shrink(eoc.coefficients().apply$mcD$sp(0)));
                  if (oldCoefficient != this.resultVec().apply$mcD$sp(index$macro$2)) {
                     improvedResult = true;
                  }
               }
            }

            this.result = new LassoResult(this.resultVec(), this.computeRsquared(), this.lambda());
            this.bitmap$0 = true;
         }
      } catch (Throwable var10) {
         throw var10;
      }

      return this.result;
   }

   public LassoResult result() {
      return !this.bitmap$0 ? this.result$lzycompute() : this.result;
   }

   private double shrink(final double x) {
      double sb = .MODULE$.signum(x);
      double ab = sb * x;
      return ab > this.lambda() ? sb * (ab - this.lambda()) : (double)0.0F;
   }

   private void copyColumn(final int column) {
      scala.Predef..MODULE$.require(column < this.data().cols());
      scala.Predef..MODULE$.require(column >= 0);
      int index$macro$7 = 0;

      for(int limit$macro$9 = this.outputs().size(); index$macro$7 < limit$macro$9; ++index$macro$7) {
         this.singleColumnMatrix().update$mcD$sp(index$macro$7, 0, this.data().apply$mcD$sp(index$macro$7, column));
         double o = this.outputs().apply$mcD$sp(index$macro$7);
         int index$macro$2 = 0;

         for(int limit$macro$4 = this.data().cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            if (index$macro$2 != column) {
               o -= this.data().apply$mcD$sp(index$macro$7, index$macro$2) * this.resultVec().apply$mcD$sp(index$macro$2);
            }
         }

         this.outputCopy().update$mcD$sp(index$macro$7, o);
      }

   }

   private double computeRsquared() {
      double r2 = (double)0.0F;
      int index$macro$7 = 0;

      for(int limit$macro$9 = this.outputs().size(); index$macro$7 < limit$macro$9; ++index$macro$7) {
         double o = this.outputs().apply$mcD$sp(index$macro$7);
         int index$macro$2 = 0;

         for(int limit$macro$4 = this.data().cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            o -= this.data().apply$mcD$sp(index$macro$7, index$macro$2) * this.resultVec().apply$mcD$sp(index$macro$2);
         }

         r2 += o * o;
      }

      return r2;
   }

   private LeastSquaresRegressionResult estimateOneColumn(final int column) {
      this.copyColumn(column);
      return (LeastSquaresRegressionResult)leastSquaresDestructive$.MODULE$.apply(this.singleColumnMatrix(), this.outputCopy(), this.workArray(), leastSquaresDestructive$.MODULE$.matrixVectorWithWorkArray());
   }

   public LassoCalculator copy(final DenseMatrix data, final DenseVector outputs, final double lambda, final double[] workArray, final int MAX_ITER, final double IMPROVE_THRESHOLD) {
      return new LassoCalculator(data, outputs, lambda, workArray, MAX_ITER, IMPROVE_THRESHOLD);
   }

   public DenseMatrix copy$default$1() {
      return this.data();
   }

   public DenseVector copy$default$2() {
      return this.outputs();
   }

   public double copy$default$3() {
      return this.lambda();
   }

   public double[] copy$default$4() {
      return this.workArray();
   }

   public int copy$default$5() {
      return this.MAX_ITER();
   }

   public double copy$default$6() {
      return this.IMPROVE_THRESHOLD();
   }

   public String productPrefix() {
      return "LassoCalculator";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.data();
            break;
         case 1:
            var10000 = this.outputs();
            break;
         case 2:
            var10000 = BoxesRunTime.boxToDouble(this.lambda());
            break;
         case 3:
            var10000 = this.workArray();
            break;
         case 4:
            var10000 = BoxesRunTime.boxToInteger(this.MAX_ITER());
            break;
         case 5:
            var10000 = BoxesRunTime.boxToDouble(this.IMPROVE_THRESHOLD());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof LassoCalculator;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "data";
            break;
         case 1:
            var10000 = "outputs";
            break;
         case 2:
            var10000 = "lambda";
            break;
         case 3:
            var10000 = "workArray";
            break;
         case 4:
            var10000 = "MAX_ITER";
            break;
         case 5:
            var10000 = "IMPROVE_THRESHOLD";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.data()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputs()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.lambda()));
      var1 = Statics.mix(var1, Statics.anyHash(this.workArray()));
      var1 = Statics.mix(var1, this.MAX_ITER());
      var1 = Statics.mix(var1, Statics.doubleHash(this.IMPROVE_THRESHOLD()));
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label71: {
            boolean var2;
            if (x$1 instanceof LassoCalculator) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label53: {
                  LassoCalculator var4 = (LassoCalculator)x$1;
                  if (this.lambda() == var4.lambda() && this.MAX_ITER() == var4.MAX_ITER() && this.IMPROVE_THRESHOLD() == var4.IMPROVE_THRESHOLD()) {
                     label62: {
                        DenseMatrix var10000 = this.data();
                        DenseMatrix var5 = var4.data();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label62;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label62;
                        }

                        DenseVector var7 = this.outputs();
                        DenseVector var6 = var4.outputs();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label62;
                           }
                        } else if (!var7.equals(var6)) {
                           break label62;
                        }

                        if (this.workArray() == var4.workArray() && var4.canEqual(this)) {
                           var9 = true;
                           break label53;
                        }
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label71;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public LassoCalculator(final DenseMatrix data, final DenseVector outputs, final double lambda, final double[] workArray, final int MAX_ITER, final double IMPROVE_THRESHOLD) {
      this.data = data;
      this.outputs = outputs;
      this.lambda = lambda;
      this.workArray = workArray;
      this.MAX_ITER = MAX_ITER;
      this.IMPROVE_THRESHOLD = IMPROVE_THRESHOLD;
      Product.$init$(this);
      scala.Predef..MODULE$.require(data.rows() == outputs.size());
      scala.Predef..MODULE$.require(data.rows() > data.cols());
      scala.Predef..MODULE$.require(data.rows() == outputs.size());
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.doubleArrayOps(workArray)) >= 2 * data.rows() * data.cols());
      this.outputCopy = DenseVector$.MODULE$.zeros$mDc$sp(outputs.size(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      this.singleColumnMatrix = new DenseMatrix$mcD$sp(data.rows(), 1, scala.reflect.ClassTag..MODULE$.Double());
      this.resultVec = DenseVector$.MODULE$.zeros$mDc$sp(data.cols(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());
   }
}
