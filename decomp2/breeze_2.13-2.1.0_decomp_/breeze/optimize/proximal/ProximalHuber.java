package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Product;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d\u0001B\f\u0019\u0001~AQ!\u000f\u0001\u0005\u0002iBQ\u0001\u0010\u0001\u0005\u0002uBQ\u0001\u0015\u0001\u0005\u0002ECQ!\u0019\u0001\u0005\u0002\tDQ\u0001\u001a\u0001\u0005\u0002\u0015Dq\u0001\u001b\u0001\u0002\u0002\u0013\u0005!\bC\u0004j\u0001\u0005\u0005I\u0011\t6\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0001\u0010AA\u0001\n\u0003I\b\u0002C@\u0001\u0003\u0003%\t%!\u0001\t\u0013\u0005=\u0001!!A\u0005\u0002\u0005E\u0001\"CA\u000e\u0001\u0005\u0005I\u0011IA\u000f\u0011%\t\t\u0003AA\u0001\n\u0003\n\u0019\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131F\u0004\n\u0003_A\u0012\u0011!E\u0001\u0003c1\u0001b\u0006\r\u0002\u0002#\u0005\u00111\u0007\u0005\u0007sE!\t!a\u0013\t\u0013\u0005\u0015\u0012#!A\u0005F\u0005\u001d\u0002\u0002CA'#\u0005\u0005I\u0011\u0011\u001e\t\u0013\u0005=\u0013#!A\u0005\u0002\u0006E\u0003\"CA,#\u0005\u0005I\u0011BA-\u00055\u0001&o\u001c=j[\u0006d\u0007*\u001e2fe*\u0011\u0011DG\u0001\taJ|\u00070[7bY*\u00111\u0004H\u0001\t_B$\u0018.\\5{K*\tQ$\u0001\u0004ce\u0016,'0Z\u0002\u0001'\u0015\u0001\u0001E\n\u0016.!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fMB\u0011q\u0005K\u0007\u00021%\u0011\u0011\u0006\u0007\u0002\t!J|\u00070[7bYB\u0011\u0011eK\u0005\u0003Y\t\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002/m9\u0011q\u0006\u000e\b\u0003aMj\u0011!\r\u0006\u0003ey\ta\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005U\u0012\u0013a\u00029bG.\fw-Z\u0005\u0003oa\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!\u000e\u0012\u0002\rqJg.\u001b;?)\u0005Y\u0004CA\u0014\u0001\u0003)\u0001(o\u001c=TG\u0006d\u0017M\u001d\u000b\b}\u0005\u001bUI\u0013'O!\t\ts(\u0003\u0002AE\t1Ai\\;cY\u0016DQA\u0011\u0002A\u0002y\n\u0011A\u001e\u0005\u0006\t\n\u0001\rAP\u0001\u0004e\"|\u0007\"\u0002$\u0003\u0001\u00049\u0015AB8sC\u000edW\r\u0005\u0003\"\u0011zr\u0014BA%#\u0005%1UO\\2uS>t\u0017\u0007C\u0003L\u0005\u0001\u0007a(A\u0001m\u0011\u0015i%\u00011\u0001?\u0003\u0005)\b\"B(\u0003\u0001\u0004q\u0014A\u0001=1\u00035\u0001(o\u001c=TKB\f'/\u00192mKR1!+V/_?\u0002\u0004\"!I*\n\u0005Q\u0013#\u0001B+oSRDQAV\u0002A\u0002]\u000b\u0011\u0001\u001f\t\u00041nsT\"A-\u000b\u0005ic\u0012A\u00027j]\u0006dw-\u0003\u0002]3\nYA)\u001a8tKZ+7\r^8s\u0011\u0015!5\u00011\u0001?\u0011\u001515\u00011\u0001H\u0011\u0015Y5\u00011\u0001?\u0011\u0015i5\u00011\u0001?\u00031\u0019XOY4sC\u0012DUOY3s)\tq4\rC\u0003W\t\u0001\u0007a(\u0001\u0003qe>DHc\u0001*gO\")a+\u0002a\u0001/\"9A)\u0002I\u0001\u0002\u0004q\u0014\u0001B2paf\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A6\u0011\u00051\fX\"A7\u000b\u00059|\u0017\u0001\u00027b]\u001eT\u0011\u0001]\u0001\u0005U\u00064\u0018-\u0003\u0002s[\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001e\t\u0003CYL!a\u001e\u0012\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005il\bCA\u0011|\u0013\ta(EA\u0002B]fDqA`\u0005\u0002\u0002\u0003\u0007Q/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0007\u0001R!!\u0002\u0002\fil!!a\u0002\u000b\u0007\u0005%!%\u0001\u0006d_2dWm\u0019;j_:LA!!\u0004\u0002\b\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019\"!\u0007\u0011\u0007\u0005\n)\"C\u0002\u0002\u0018\t\u0012qAQ8pY\u0016\fg\u000eC\u0004\u007f\u0017\u0005\u0005\t\u0019\u0001>\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004W\u0006}\u0001b\u0002@\r\u0003\u0003\u0005\r!^\u0001\tQ\u0006\u001c\bnQ8eKR\tQ/\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0014\u00055\u0002b\u0002@\u0010\u0003\u0003\u0005\rA_\u0001\u000e!J|\u00070[7bY\"+(-\u001a:\u0011\u0005\u001d\n2#B\t\u00026\u0005\u0005\u0003#BA\u001c\u0003{YTBAA\u001d\u0015\r\tYDI\u0001\beVtG/[7f\u0013\u0011\ty$!\u000f\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0007\u0007\u0005\u0003\u0002D\u0005%SBAA#\u0015\r\t9e\\\u0001\u0003S>L1aNA#)\t\t\t$A\u0003baBd\u00170A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005M\u00111\u000b\u0005\t\u0003+*\u0012\u0011!a\u0001w\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\u0003c\u00017\u0002^%\u0019\u0011qL7\u0003\r=\u0013'.Z2u\u0001"
)
public class ProximalHuber implements Proximal, Product, Serializable {
   public static boolean unapply(final ProximalHuber x$0) {
      return ProximalHuber$.MODULE$.unapply(x$0);
   }

   public static ProximalHuber apply() {
      return ProximalHuber$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double prox$default$2() {
      return Proximal.prox$default$2$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public double proxScalar(final double v, final double rho, final Function1 oracle, final double l, final double u, final double x0) {
      int MAX_ITER = 1000;
      double tol = 1.0E-8;
      double g = (double)0.0F;
      double x = .MODULE$.max(l, .MODULE$.min(x0, u));
      double lIter = l;
      double uIter = u;

      for(int iter = 0; iter < MAX_ITER && u - l > tol; ++iter) {
         g = (double)-1 / x + rho * (x - v);
         if (g > (double)0) {
            lIter = .MODULE$.max(lIter, x - g / rho);
            uIter = x;
         } else if (g < (double)0) {
            lIter = x;
            uIter = .MODULE$.min(uIter, x - g / rho);
         }

         x = (lIter + uIter) / (double)2;
      }

      return x;
   }

   public void proxSeparable(final DenseVector x, final double rho, final Function1 oracle, final double l, final double u) {
      x.map$mcD$sp((JFunction1.mcDD.sp)(x$4) -> this.proxScalar(x$4, rho, oracle, l, u, (double)0.0F), DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()));
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, this.proxScalar(x.apply$mcD$sp(index$macro$2), rho, oracle, l, u, (double)0.0F));
      }

   }

   public double subgradHuber(final double x) {
      double var10000;
      if (.MODULE$.abs(x) <= (double)1) {
         var10000 = (double)2 * x;
      } else {
         double projx = x > (double)0 ? x : -x;
         var10000 = (double)2 * projx;
      }

      return var10000;
   }

   public void prox(final DenseVector x, final double rho) {
      this.proxSeparable(x, rho, (JFunction1.mcDD.sp)(xx) -> this.subgradHuber(xx), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
   }

   public ProximalHuber copy() {
      return new ProximalHuber();
   }

   public String productPrefix() {
      return "ProximalHuber";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProximalHuber;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof ProximalHuber) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((ProximalHuber)x$1).canEqual(this);
   }

   public ProximalHuber() {
      Proximal.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
