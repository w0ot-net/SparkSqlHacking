package breeze.stats.regression;

import breeze.linalg.DenseMatrix;
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
   bytes = "\u0006\u0005\u0005ed\u0001\u0002\u000e\u001c\u0001\nB\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005[!A\u0001\n\u0001BK\u0002\u0013\u0005\u0011\n\u0003\u0005K\u0001\tE\t\u0015!\u00034\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u0015\u0001\u0006\u0001\"\u0001U\u0011\u001dQ\u0006!!A\u0005\u0002mCqA\u0018\u0001\u0012\u0002\u0013\u0005q\fC\u0004k\u0001E\u0005I\u0011A6\t\u000f5\u0004\u0011\u0011!C!]\"9q\u000fAA\u0001\n\u0003A\bb\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u000f\u0001\u0011\u0011!C!\u0003\u0013A\u0011\"a\u0006\u0001\u0003\u0003%\t!!\u0007\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015\u0002\"CA\u0015\u0001\u0005\u0005I\u0011IA\u0016\u0011%\ti\u0003AA\u0001\n\u0003\nycB\u0005\u00024m\t\t\u0011#\u0001\u00026\u0019A!dGA\u0001\u0012\u0003\t9\u0004\u0003\u0004L)\u0011\u0005\u0011q\n\u0005\n\u0003#\"\u0012\u0011!C#\u0003'B\u0001\u0002\u0015\u000b\u0002\u0002\u0013\u0005\u0015Q\u000b\u0005\n\u00037\"\u0012\u0011!CA\u0003;B\u0011\"a\u001c\u0015\u0003\u0003%I!!\u001d\u000391+\u0017m\u001d;TcV\f'/Z:SK\u001e\u0014Xm]:j_:\u0014Vm];mi*\u0011A$H\u0001\u000be\u0016<'/Z:tS>t'B\u0001\u0010 \u0003\u0015\u0019H/\u0019;t\u0015\u0005\u0001\u0013A\u00022sK\u0016TXm\u0001\u0001\u0014\u000b\u0001\u0019\u0013FN\u001d\u0011\u0005\u0011:S\"A\u0013\u000b\u0003\u0019\nQa]2bY\u0006L!\u0001K\u0013\u0003\r\u0005s\u0017PU3g!\u0011Q3&L\u001a\u000e\u0003mI!\u0001L\u000e\u0003!I+wM]3tg&|gNU3tk2$\bc\u0001\u00182g5\tqF\u0003\u00021?\u00051A.\u001b8bY\u001eL!AM\u0018\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003IQJ!!N\u0013\u0003\r\u0011{WO\u00197f!\t!s'\u0003\u00029K\t9\u0001K]8ek\u000e$\bC\u0001\u001eC\u001d\tY\u0004I\u0004\u0002=\u007f5\tQH\u0003\u0002?C\u00051AH]8pizJ\u0011AJ\u0005\u0003\u0003\u0016\nq\u0001]1dW\u0006<W-\u0003\u0002D\t\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011)J\u0001\rG>,gMZ5dS\u0016tGo]\u000b\u0002[\u0005i1m\\3gM&\u001c\u0017.\u001a8ug\u0002\n\u0001B]*rk\u0006\u0014X\rZ\u000b\u0002g\u0005I!oU9vCJ,G\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00075su\n\u0005\u0002+\u0001!)Q)\u0002a\u0001[!)\u0001*\u0002a\u0001g\u0005)\u0011\r\u001d9msR\u00111G\u0015\u0005\u0006'\u001a\u0001\r!L\u0001\u0002qR\u0011Q&\u0016\u0005\u0006-\u001e\u0001\raV\u0001\u00021B\u0019a\u0006W\u001a\n\u0005e{#a\u0003#f]N,W*\u0019;sSb\fAaY8qsR\u0019Q\nX/\t\u000f\u0015C\u0001\u0013!a\u0001[!9\u0001\n\u0003I\u0001\u0002\u0004\u0019\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002A*\u0012Q&Y\u0016\u0002EB\u00111\r[\u0007\u0002I*\u0011QMZ\u0001\nk:\u001c\u0007.Z2lK\u0012T!aZ\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002jI\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tAN\u000b\u00024C\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u001c\t\u0003aVl\u0011!\u001d\u0006\u0003eN\fA\u0001\\1oO*\tA/\u0001\u0003kCZ\f\u0017B\u0001<r\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t\u0011\u0010\u0005\u0002%u&\u001110\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004}\u0006\r\u0001C\u0001\u0013\u0000\u0013\r\t\t!\n\u0002\u0004\u0003:L\b\u0002CA\u0003\u001b\u0005\u0005\t\u0019A=\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u0001E\u0003\u0002\u000e\u0005Ma0\u0004\u0002\u0002\u0010)\u0019\u0011\u0011C\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0016\u0005=!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0007\u0002\"A\u0019A%!\b\n\u0007\u0005}QEA\u0004C_>dW-\u00198\t\u0011\u0005\u0015q\"!AA\u0002y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019q.a\n\t\u0011\u0005\u0015\u0001#!AA\u0002e\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002s\u00061Q-];bYN$B!a\u0007\u00022!A\u0011Q\u0001\n\u0002\u0002\u0003\u0007a0\u0001\u000fMK\u0006\u001cHoU9vCJ,7OU3he\u0016\u001c8/[8o%\u0016\u001cX\u000f\u001c;\u0011\u0005)\"2#\u0002\u000b\u0002:\u0005\u0015\u0003cBA\u001e\u0003\u0003j3'T\u0007\u0003\u0003{Q1!a\u0010&\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0011\u0002>\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005\u001d\u0013QJ\u0007\u0003\u0003\u0013R1!a\u0013t\u0003\tIw.C\u0002D\u0003\u0013\"\"!!\u000e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u001c\u000b\u0006\u001b\u0006]\u0013\u0011\f\u0005\u0006\u000b^\u0001\r!\f\u0005\u0006\u0011^\u0001\raM\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty&a\u001b\u0011\u000b\u0011\n\t'!\u001a\n\u0007\u0005\rTE\u0001\u0004PaRLwN\u001c\t\u0006I\u0005\u001dTfM\u0005\u0004\u0003S*#A\u0002+va2,'\u0007\u0003\u0005\u0002na\t\t\u00111\u0001N\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u00022\u0001]A;\u0013\r\t9(\u001d\u0002\u0007\u001f\nTWm\u0019;"
)
public class LeastSquaresRegressionResult implements RegressionResult, Product, Serializable {
   private final DenseVector coefficients;
   private final double rSquared;

   public static Option unapply(final LeastSquaresRegressionResult x$0) {
      return LeastSquaresRegressionResult$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return LeastSquaresRegressionResult$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return LeastSquaresRegressionResult$.MODULE$.curried();
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

   public double apply(final DenseVector x) {
      return BoxesRunTime.unboxToDouble(this.coefficients().dot(x, HasOps$.MODULE$.canDotD()));
   }

   public DenseVector apply(final DenseMatrix X) {
      return (DenseVector)X.$times(this.coefficients(), HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD());
   }

   public LeastSquaresRegressionResult copy(final DenseVector coefficients, final double rSquared) {
      return new LeastSquaresRegressionResult(coefficients, rSquared);
   }

   public DenseVector copy$default$1() {
      return this.coefficients();
   }

   public double copy$default$2() {
      return this.rSquared();
   }

   public String productPrefix() {
      return "LeastSquaresRegressionResult";
   }

   public int productArity() {
      return 2;
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
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof LeastSquaresRegressionResult;
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
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof LeastSquaresRegressionResult) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  LeastSquaresRegressionResult var4 = (LeastSquaresRegressionResult)x$1;
                  if (this.rSquared() == var4.rSquared()) {
                     label36: {
                        DenseVector var10000 = this.coefficients();
                        DenseVector var5 = var4.coefficients();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label36;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label36;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label38;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public LeastSquaresRegressionResult(final DenseVector coefficients, final double rSquared) {
      this.coefficients = coefficients;
      this.rSquared = rSquared;
      Function1.$init$(this);
      Product.$init$(this);
   }
}
