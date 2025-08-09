package org.apache.spark.ml.stat.distribution;

import breeze.linalg.DenseMatrix;
import breeze.linalg.eigSym;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.ml.impl.Utils$;
import org.apache.spark.ml.linalg.BLAS$;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrices$;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors$;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001\u0002\b\u0010\u0001qA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005c!A!\t\u0001BC\u0002\u0013\u00051\t\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003E\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0019Q\u0005\u0001\"\u0001\u0014'\"A1\r\u0001EC\u0002\u0013%A\r\u0003\u0005o\u0001!\u0015\r\u0011\"\u0003D\u0011!\u0001\b\u0001#b\u0001\n\u0013\t\b\u0002C:\u0001\u0011\u000b\u0007I\u0011\u0002;\t\u000bY\u0004A\u0011A<\t\u000bm\u0004A\u0011\u0001?\t\r}\u0004A\u0011BA\u0001\u0005QiU\u000f\u001c;jm\u0006\u0014\u0018.\u0019;f\u000f\u0006,8o]5b]*\u0011\u0001#E\u0001\rI&\u001cHO]5ckRLwN\u001c\u0006\u0003%M\tAa\u001d;bi*\u0011A#F\u0001\u0003[2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u0002\u0001'\r\u0001Qd\t\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0011bcBA\u0013+\u001d\t1\u0013&D\u0001(\u0015\tA3$\u0001\u0004=e>|GOP\u0005\u0002A%\u00111fH\u0001\ba\u0006\u001c7.Y4f\u0013\ticF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002,?\u0005!Q.Z1o+\u0005\t\u0004C\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b\u0014\u0003\u0019a\u0017N\\1mO&\u0011ag\r\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\u0005Ad\b\u0005\u0002:y5\t!H\u0003\u0002<+\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005uR$!B*j]\u000e,\u0017%A \u0002\u000bIr\u0003G\f\u0019\u0002\u000b5,\u0017M\u001c\u0011)\u0007\tAd(A\u0002d_Z,\u0012\u0001\u0012\t\u0003e\u0015K!AR\u001a\u0003\r5\u000bGO]5yQ\r\u0019\u0001HP\u0001\u0005G>4\b\u0005K\u0002\u0005qy\na\u0001P5oSRtDc\u0001'O!B\u0011Q\nA\u0007\u0002\u001f!)q&\u0002a\u0001c!\u001aa\n\u000f \t\u000b\t+\u0001\u0019\u0001#)\u0007ACd\bK\u0002\u0006qy\"2\u0001\u0014+`\u0011\u0015yc\u00011\u0001V!\r1&\fX\u0007\u0002/*\u0011A\u0007\u0017\u0006\u00023\u00061!M]3fu\u0016L!aW,\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003=uK!AX\u0010\u0003\r\u0011{WO\u00197f\u0011\u0015\u0011e\u00011\u0001a!\r1\u0016\rX\u0005\u0003E^\u00131\u0002R3og\u0016l\u0015\r\u001e:jq\u0006)A/\u001e9mKV\tQ\rE\u0003\u001fM\u0012c\u0006.\u0003\u0002h?\t1A+\u001e9mKN\u0002\"AM5\n\u0005m\u001b\u0004FA\u0004l!\tqB.\u0003\u0002n?\tIAO]1og&,g\u000e^\u0001\u0010e>|GoU5h[\u0006LeN^'bi\"\u0012\u0001b[\u0001\u0002kV\tA\f\u000b\u0002\nW\u0006\t\"o\\8u'&<W.Y%om6+H.T;\u0016\u0003!D#AC6\u0002\u0007A$g\r\u0006\u0002]q\")\u0011p\u0003a\u0001c\u0005\t\u0001\u0010K\u0002\fqy\na\u0001\\8ha\u00124GC\u0001/~\u0011\u0015IH\u00021\u00012Q\ra\u0001HP\u0001\u001dG\u0006d7-\u001e7bi\u0016\u001cuN^1sS\u0006t7-Z\"p]N$\u0018M\u001c;t+\t\t\u0019\u0001E\u0003\u001f\u0003\u000b\u0001G,C\u0002\u0002\b}\u0011a\u0001V;qY\u0016\u0014\u0004f\u0001\u00019}!\u001a\u0001!!\u0004\u0011\u0007e\ny!C\u0002\u0002\u0012i\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public class MultivariateGaussian implements Serializable {
   private transient Tuple3 tuple;
   private transient Matrix rootSigmaInvMat;
   private transient double u;
   private transient DenseVector rootSigmaInvMulMu;
   private final Vector mean;
   private final Matrix cov;
   private transient volatile byte bitmap$trans$0;

   public Vector mean() {
      return this.mean;
   }

   public Matrix cov() {
      return this.cov;
   }

   private Tuple3 tuple$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            Tuple2 var4 = this.calculateCovarianceConstants();
            if (var4 == null) {
               throw new MatchError(var4);
            }

            DenseMatrix rootSigmaInv = (DenseMatrix)var4._1();
            double u = var4._2$mcD$sp();
            Tuple2 var3 = new Tuple2(rootSigmaInv, BoxesRunTime.boxToDouble(u));
            DenseMatrix rootSigmaInv = (DenseMatrix)var3._1();
            double u = var3._2$mcD$sp();
            Matrix rootSigmaInvMat = Matrices$.MODULE$.fromBreeze(rootSigmaInv);
            DenseVector rootSigmaInvMulMu = rootSigmaInvMat.multiply(this.mean());
            this.tuple = new Tuple3(rootSigmaInvMat, BoxesRunTime.boxToDouble(u), rootSigmaInvMulMu);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var14) {
         throw var14;
      }

      return this.tuple;
   }

   private Tuple3 tuple() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.tuple$lzycompute() : this.tuple;
   }

   private Matrix rootSigmaInvMat$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.rootSigmaInvMat = (Matrix)this.tuple()._1();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rootSigmaInvMat;
   }

   private Matrix rootSigmaInvMat() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.rootSigmaInvMat$lzycompute() : this.rootSigmaInvMat;
   }

   private double u$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.u = BoxesRunTime.unboxToDouble(this.tuple()._2());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.u;
   }

   private double u() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.u$lzycompute() : this.u;
   }

   private DenseVector rootSigmaInvMulMu$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.rootSigmaInvMulMu = (DenseVector)this.tuple()._3();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rootSigmaInvMulMu;
   }

   private DenseVector rootSigmaInvMulMu() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.rootSigmaInvMulMu$lzycompute() : this.rootSigmaInvMulMu;
   }

   public double pdf(final Vector x) {
      return .MODULE$.exp(this.logpdf(x));
   }

   public double logpdf(final Vector x) {
      DenseVector v = this.rootSigmaInvMulMu().copy();
      BLAS$.MODULE$.gemv((double)-1.0F, this.rootSigmaInvMat(), x, (double)1.0F, v);
      return this.u() - (double)0.5F * BLAS$.MODULE$.dot((Vector)v, (Vector)v);
   }

   private Tuple2 calculateCovarianceConstants() {
      eigSym.EigSym var3 = (eigSym.EigSym)breeze.linalg.eigSym..MODULE$.apply(this.cov().asBreeze().toDenseMatrix$mcD$sp(scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()), breeze.linalg.eigSym.EigSym_DM_Impl..MODULE$);
      if (var3 != null) {
         breeze.linalg.DenseVector d = (breeze.linalg.DenseVector)var3.eigenvalues();
         DenseMatrix u = (DenseMatrix)var3.eigenvectors();
         Tuple2 var2 = new Tuple2(d, u);
         breeze.linalg.DenseVector d = (breeze.linalg.DenseVector)var2._1();
         DenseMatrix u = (DenseMatrix)var2._2();
         double tol = Utils$.MODULE$.EPSILON() * BoxesRunTime.unboxToDouble(breeze.linalg.max..MODULE$.apply(d, breeze.linalg.max..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))) * (double)d.length();

         try {
            double logPseudoDetSigma = BoxesRunTime.unboxToDouble(d.activeValuesIterator().filter((JFunction1.mcZD.sp)(x$3) -> x$3 > tol).map((JFunction1.mcDD.sp)(x) -> .MODULE$.log(x)).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            DenseMatrix pinvS = (DenseMatrix)breeze.linalg.diag..MODULE$.apply(new breeze.linalg.DenseVector.mcD.sp(((breeze.linalg.DenseVector)d.map$mcD$sp((JFunction1.mcDD.sp)(v) -> v > tol ? .MODULE$.sqrt((double)1.0F / v) : (double)0.0F, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())), breeze.linalg.diag..MODULE$.diagDVDMImpl(scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()));
            return new Tuple2(pinvS.$times(u.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), BoxesRunTime.boxToDouble((double)-0.5F * ((double)this.mean().size() * .MODULE$.log((Math.PI * 2D)) + logPseudoDetSigma)));
         } catch (UnsupportedOperationException var14) {
            throw new IllegalArgumentException("Covariance matrix has no non-zero singular values");
         }
      } else {
         throw new MatchError(var3);
      }
   }

   public MultivariateGaussian(final Vector mean, final Matrix cov) {
      this.mean = mean;
      this.cov = cov;
      scala.Predef..MODULE$.require(cov.numCols() == cov.numRows(), () -> "Covariance matrix must be square");
      scala.Predef..MODULE$.require(mean.size() == cov.numCols(), () -> "Mean vector length must match covariance matrix size");
   }

   public MultivariateGaussian(final breeze.linalg.DenseVector mean, final DenseMatrix cov) {
      this(Vectors$.MODULE$.fromBreeze(mean), Matrices$.MODULE$.fromBreeze(cov));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
