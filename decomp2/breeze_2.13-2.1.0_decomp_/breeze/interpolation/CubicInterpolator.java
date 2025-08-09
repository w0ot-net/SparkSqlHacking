package breeze.interpolation;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.Vector;
import breeze.linalg.operators.HasOps$;
import breeze.math.Field;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005-4AAF\f\u00019!A1\u0006\u0001B\u0001B\u0003%A\u0006\u0003\u00053\u0001\t\u0005\t\u0015!\u0003-\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015A\u0004\u0001\"\u0003:\u0011\u0015y\u0004\u0001\"\u0003A\u0011\u0015\u0011\u0005\u0001\"\u0003D\u0011\u0015)\u0005\u0001\"\u0003G\u0011\u001dA\u0005A1A\u0005\n%Ca!\u0014\u0001!\u0002\u0013Q\u0005b\u0002(\u0001\u0005\u0004%Ia\u0014\u0005\u0007'\u0002\u0001\u000b\u0011\u0002)\t\u000fQ\u0003!\u0019!C\u0005\u001f\"1Q\u000b\u0001Q\u0001\nACQA\u0016\u0001\u0005\n]CqA\u0017\u0001C\u0002\u0013%\u0011\n\u0003\u0004\\\u0001\u0001\u0006IA\u0013\u0005\u00069\u0002!\t&X\u0004\u0006A^A\t!\u0019\u0004\u0006-]A\tA\u0019\u0005\u0006gM!\tA\u001a\u0005\u0006ON!\t\u0001\u001b\u0002\u0012\u0007V\u0014\u0017nY%oi\u0016\u0014\bo\u001c7bi>\u0014(B\u0001\r\u001a\u00035Ig\u000e^3sa>d\u0017\r^5p]*\t!$\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001Q\u0004E\u0002\u001fE\u0015r!a\b\u0011\u000e\u0003]I!!I\f\u0002\u000fA\f7m[1hK&\u00111\u0005\n\u0002\u001c\u0011\u0006tG-_+oSZ\f'/[1uK&sG/\u001a:q_2\fGo\u001c:\u000b\u0005\u0005:\u0002C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#A\u0002#pk\ndW-\u0001\u0005y?\u000e|wN\u001d3t!\ri\u0003'J\u0007\u0002])\u0011q&G\u0001\u0007Y&t\u0017\r\\4\n\u0005Er#A\u0002,fGR|'/\u0001\u0005z?\u000e|wN\u001d3t\u0003\u0019a\u0014N\\5u}Q\u0019QGN\u001c\u0011\u0005}\u0001\u0001\"B\u0016\u0004\u0001\u0004a\u0003\"\u0002\u001a\u0004\u0001\u0004a\u0013!\u00015\u0015\u0005\u0015R\u0004\"B\u001e\u0005\u0001\u0004a\u0014!A6\u0011\u0005\u0019j\u0014B\u0001 (\u0005\rIe\u000e^\u0001\u0002IR\u0011Q%\u0011\u0005\u0006w\u0015\u0001\r\u0001P\u0001\u0007Y\u0006l'\rZ1\u0015\u0005\u0015\"\u0005\"B\u001e\u0007\u0001\u0004a\u0014A\u0001:p)\t)s\tC\u0003<\u000f\u0001\u0007A(A\u0001N+\u0005Q\u0005cA\u0017LK%\u0011AJ\f\u0002\f\t\u0016t7/Z'biJL\u00070\u0001\u0002NA\u0005\t!-F\u0001Q!\ri\u0013+J\u0005\u0003%:\u00121\u0002R3og\u00164Vm\u0019;pe\u0006\u0011!\rI\u0001\u0003[B\f1!\u001c9!\u0003\u0005iGCA\u0013Y\u0011\u0015If\u00021\u0001=\u0003\u0005I\u0017!A!\u0002\u0005\u0005\u0003\u0013aC5oi\u0016\u0014\bo\u001c7bi\u0016$\"!\n0\t\u000b}\u000b\u0002\u0019A\u0013\u0002\u0003a\f\u0011cQ;cS\u000eLe\u000e^3sa>d\u0017\r^8s!\ty2c\u0005\u0002\u0014GB\u0011a\u0005Z\u0005\u0003K\u001e\u0012a!\u00118z%\u00164G#A1\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007UJ'\u000eC\u0003,+\u0001\u0007A\u0006C\u00033+\u0001\u0007A\u0006"
)
public class CubicInterpolator extends package.HandyUnivariateInterpolator {
   private final DenseMatrix M;
   private final DenseVector b;
   private final DenseVector mp;
   private final DenseMatrix A;

   private double h(final int k) {
      return ((double[])this.X())[k + 1] - ((double[])this.X())[k];
   }

   private double d(final int k) {
      return (((double[])this.Y())[k + 1] - ((double[])this.Y())[k]) / this.h(k);
   }

   private double lambda(final int k) {
      return this.h(k) / (this.h(k - 1) + this.h(k));
   }

   private double ro(final int k) {
      return (double)1 - this.lambda(k);
   }

   private DenseMatrix M() {
      return this.M;
   }

   private DenseVector b() {
      return this.b;
   }

   private DenseVector mp() {
      return this.mp;
   }

   private double m(final int i) {
      double var10000;
      switch (i) {
         case 0:
            var10000 = (double)0.0F;
            break;
         default:
            var10000 = i == ((double[])this.X()).length - 1 ? (double)0.0F : this.mp().apply$mcD$sp(i - 1);
      }

      return var10000;
   }

   private DenseMatrix A() {
      return this.A;
   }

   public double interpolate(final double x) {
      int index = this.bisearch(BoxesRunTime.boxToDouble(x)) - 1;
      double var10000;
      if (index == -1) {
         var10000 = ((double[])this.Y())[0];
      } else {
         double dx = x - ((double[])this.X())[index];
         var10000 = this.A().apply$mcD$sp(index, 0) + this.A().apply$mcD$sp(index, 1) * dx + this.A().apply$mcD$sp(index, 2) * dx * dx + this.A().apply$mcD$sp(index, 3) * dx * dx * dx;
      }

      return var10000;
   }

   public CubicInterpolator(final Vector x_coords, final Vector y_coords) {
      super(x_coords, y_coords, .MODULE$.Double(), Field.fieldDouble$.MODULE$, scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
      if (((double[])this.X()).length < 3) {
         throw new Exception("You must provide at least 3 points for CubicInterpolator.");
      } else {
         this.M = (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mDc$sp(((double[])this.X()).length - 2, ((double[])this.X()).length - 2, (JFunction2.mcDII.sp)(x0$1, x1$1) -> {
            Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(x0$1, x1$1);
            double var3;
            if (var5 != null) {
               int i = ((Tuple2)var5)._1$mcI$sp();
               int j = ((Tuple2)var5)._2$mcI$sp();
               if (j - i == -1) {
                  var3 = this.ro(i + 1);
                  return var3;
               }
            }

            if (var5 != null) {
               int i = ((Tuple2)var5)._1$mcI$sp();
               int j = ((Tuple2)var5)._2$mcI$sp();
               if (j == i) {
                  var3 = (double)2.0F;
                  return var3;
               }
            }

            if (var5 != null) {
               int i = ((Tuple2)var5)._1$mcI$sp();
               int j = ((Tuple2)var5)._2$mcI$sp();
               if (j - i == 1) {
                  var3 = this.lambda(i + 1);
                  return var3;
               }
            }

            var3 = (double)0.0F;
            return var3;
         }, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
         this.b = DenseVector$.MODULE$.tabulate$mDc$sp(((double[])this.X()).length - 2, (JFunction1.mcDI.sp)(x0$2) -> {
            double var2 = (double)6 * (this.d(x0$2 + 1) - this.d(x0$2)) / (this.h(x0$2) + this.h(x0$2 + 1));
            return var2;
         }, .MODULE$.Double());
         this.mp = (DenseVector)this.M().$bslash(this.b(), HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD());
         this.A = (DenseMatrix)DenseMatrix$.MODULE$.tabulate$mDc$sp(((double[])this.X()).length - 1, 4, (JFunction2.mcDII.sp)(x0$3, x1$2) -> {
            Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(x0$3, x1$2);
            double var3;
            if (var5 != null) {
               int k = ((Tuple2)var5)._1$mcI$sp();
               int var7 = ((Tuple2)var5)._2$mcI$sp();
               if (0 == var7) {
                  var3 = ((double[])this.Y())[k];
                  return var3;
               }
            }

            if (var5 != null) {
               int k = ((Tuple2)var5)._1$mcI$sp();
               int var9 = ((Tuple2)var5)._2$mcI$sp();
               if (1 == var9) {
                  var3 = this.d(k) - this.h(k) / (double)6 * ((double)2 * this.m(k) + this.m(k + 1));
                  return var3;
               }
            }

            if (var5 != null) {
               int k = ((Tuple2)var5)._1$mcI$sp();
               int var11 = ((Tuple2)var5)._2$mcI$sp();
               if (2 == var11) {
                  var3 = this.m(k) / (double)2;
                  return var3;
               }
            }

            if (var5 != null) {
               int k = ((Tuple2)var5)._1$mcI$sp();
               int var13 = ((Tuple2)var5)._2$mcI$sp();
               if (3 == var13) {
                  var3 = (this.m(k + 1) - this.m(k)) / (double)6 / this.h(k);
                  return var3;
               }
            }

            scala.Predef..MODULE$.assert(false, () -> "unreachable");
            throw scala.Predef..MODULE$.$qmark$qmark$qmark();
         }, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
