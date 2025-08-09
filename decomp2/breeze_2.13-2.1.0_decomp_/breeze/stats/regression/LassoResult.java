package breeze.stats.regression;

import breeze.linalg.DenseVector;
import breeze.linalg.operators.HasOps$;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001\u0002\u000f\u001e\u0001\u0012B\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005_!A!\n\u0001BK\u0002\u0013\u00051\n\u0003\u0005M\u0001\tE\t\u0015!\u00036\u0011!i\u0005A!f\u0001\n\u0003Y\u0005\u0002\u0003(\u0001\u0005#\u0005\u000b\u0011B\u001b\t\u000b=\u0003A\u0011\u0001)\t\u000bU\u0003A\u0011\u0001,\t\u000fe\u0003\u0011\u0011!C\u00015\"9a\fAI\u0001\n\u0003y\u0006b\u00026\u0001#\u0003%\ta\u001b\u0005\b[\u0002\t\n\u0011\"\u0001l\u0011\u001dq\u0007!!A\u0005B=Dq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011G\u0004\n\u0003ki\u0012\u0011!E\u0001\u0003o1\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011\u0011\b\u0005\u0007\u001fZ!\t!!\u0015\t\u0013\u0005Mc#!A\u0005F\u0005U\u0003\u0002C+\u0017\u0003\u0003%\t)a\u0016\t\u0013\u0005}c#!A\u0005\u0002\u0006\u0005\u0004\"CA:-\u0005\u0005I\u0011BA;\u0005-a\u0015m]:p%\u0016\u001cX\u000f\u001c;\u000b\u0005yy\u0012A\u0003:fOJ,7o]5p]*\u0011\u0001%I\u0001\u0006gR\fGo\u001d\u0006\u0002E\u00051!M]3fu\u0016\u001c\u0001aE\u0003\u0001K-B4\b\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VM\u001a\t\u0005Y5zS'D\u0001\u001e\u0013\tqSD\u0001\tSK\u001e\u0014Xm]:j_:\u0014Vm];miB\u0019\u0001gM\u001b\u000e\u0003ER!AM\u0011\u0002\r1Lg.\u00197h\u0013\t!\u0014GA\u0006EK:\u001cXMV3di>\u0014\bC\u0001\u00147\u0013\t9tE\u0001\u0004E_V\u0014G.\u001a\t\u0003MeJ!AO\u0014\u0003\u000fA\u0013x\u000eZ;diB\u0011A\b\u0012\b\u0003{\ts!AP!\u000e\u0003}R!\u0001Q\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013BA\"(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0012$\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\r;\u0013\u0001D2pK\u001a4\u0017nY5f]R\u001cX#A\u0018\u0002\u001b\r|WM\u001a4jG&,g\u000e^:!\u0003!\u00118+];be\u0016$W#A\u001b\u0002\u0013I\u001c\u0016/^1sK\u0012\u0004\u0013A\u00027b[\n$\u0017-A\u0004mC6\u0014G-\u0019\u0011\u0002\rqJg.\u001b;?)\u0011\t&k\u0015+\u0011\u00051\u0002\u0001\"B$\b\u0001\u0004y\u0003\"\u0002&\b\u0001\u0004)\u0004\"B'\b\u0001\u0004)\u0014!B1qa2LHCA\u001bX\u0011\u0015A\u0006\u00021\u00010\u0003\u0005A\u0018\u0001B2paf$B!U.];\"9q)\u0003I\u0001\u0002\u0004y\u0003b\u0002&\n!\u0003\u0005\r!\u000e\u0005\b\u001b&\u0001\n\u00111\u00016\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0019\u0016\u0003_\u0005\\\u0013A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u001d<\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\u000e\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002Y*\u0012Q'Y\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\u000f\u0005\u0002rm6\t!O\u0003\u0002ti\u0006!A.\u00198h\u0015\u0005)\u0018\u0001\u00026bm\u0006L!a\u001e:\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Q\bC\u0001\u0014|\u0013\taxEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\u0000\u0003\u000b\u00012AJA\u0001\u0013\r\t\u0019a\n\u0002\u0004\u0003:L\b\u0002CA\u0004\u001f\u0005\u0005\t\u0019\u0001>\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0001E\u0003\u0002\u0010\u0005Uq0\u0004\u0002\u0002\u0012)\u0019\u00111C\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0018\u0005E!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\b\u0002$A\u0019a%a\b\n\u0007\u0005\u0005rEA\u0004C_>dW-\u00198\t\u0011\u0005\u001d\u0011#!AA\u0002}\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0001/!\u000b\t\u0011\u0005\u001d!#!AA\u0002i\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002u\u00061Q-];bYN$B!!\b\u00024!A\u0011q\u0001\u000b\u0002\u0002\u0003\u0007q0A\u0006MCN\u001cxNU3tk2$\bC\u0001\u0017\u0017'\u00151\u00121HA$!!\ti$a\u00110kU\nVBAA \u0015\r\t\teJ\u0001\beVtG/[7f\u0013\u0011\t)%a\u0010\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002J\u0005=SBAA&\u0015\r\ti\u0005^\u0001\u0003S>L1!RA&)\t\t9$\u0001\u0005u_N#(/\u001b8h)\u0005\u0001HcB)\u0002Z\u0005m\u0013Q\f\u0005\u0006\u000ff\u0001\ra\f\u0005\u0006\u0015f\u0001\r!\u000e\u0005\u0006\u001bf\u0001\r!N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019'a\u001c\u0011\u000b\u0019\n)'!\u001b\n\u0007\u0005\u001dtE\u0001\u0004PaRLwN\u001c\t\u0007M\u0005-t&N\u001b\n\u0007\u00055tE\u0001\u0004UkBdWm\r\u0005\t\u0003cR\u0012\u0011!a\u0001#\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0004cA9\u0002z%\u0019\u00111\u0010:\u0003\r=\u0013'.Z2u\u0001"
)
public class LassoResult implements RegressionResult, Product, Serializable {
   private final DenseVector coefficients;
   private final double rSquared;
   private final double lambda;

   public static Option unapply(final LassoResult x$0) {
      return LassoResult$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return LassoResult$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return LassoResult$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public DenseVector coefficients() {
      return this.coefficients;
   }

   public double rSquared() {
      return this.rSquared;
   }

   public double lambda() {
      return this.lambda;
   }

   public double apply(final DenseVector x) {
      return BoxesRunTime.unboxToDouble(this.coefficients().dot(x, HasOps$.MODULE$.canDotD()));
   }

   public LassoResult copy(final DenseVector coefficients, final double rSquared, final double lambda) {
      return new LassoResult(coefficients, rSquared, lambda);
   }

   public DenseVector copy$default$1() {
      return this.coefficients();
   }

   public double copy$default$2() {
      return this.rSquared();
   }

   public double copy$default$3() {
      return this.lambda();
   }

   public String productPrefix() {
      return "LassoResult";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.coefficients();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.rSquared());
            break;
         case 2:
            var10000 = BoxesRunTime.boxToDouble(this.lambda());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof LassoResult;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "coefficients";
            break;
         case 1:
            var10000 = "rSquared";
            break;
         case 2:
            var10000 = "lambda";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.coefficients()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.rSquared()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.lambda()));
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label57: {
            boolean var2;
            if (x$1 instanceof LassoResult) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label40: {
                  LassoResult var4 = (LassoResult)x$1;
                  if (this.rSquared() == var4.rSquared() && this.lambda() == var4.lambda()) {
                     label37: {
                        DenseVector var10000 = this.coefficients();
                        DenseVector var5 = var4.coefficients();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label37;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label37;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label40;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label57;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public LassoResult(final DenseVector coefficients, final double rSquared, final double lambda) {
      this.coefficients = coefficients;
      this.rSquared = rSquared;
      this.lambda = lambda;
      Function1.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(lambda >= (double)0);
   }
}
