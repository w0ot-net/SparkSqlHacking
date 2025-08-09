package org.apache.spark.mllib.stat.distribution;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.eigSym;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.MLUtils$;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ea\u0001\u0002\t\u0012\u0001yA\u0001\"\r\u0001\u0003\u0006\u0004%\tA\r\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005g!AA\t\u0001BC\u0002\u0013\u0005Q\t\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003G\u0011\u0015a\u0005\u0001\"\u0001N\u0011!)\u0006\u0001#b\u0001\n\u00131\u0006B\u0002'\u0001\t\u0003)R\r\u0003\u0005l\u0001!\u0015\r\u0011\"\u0003m\u0011!\t\b\u0001#b\u0001\n\u0013\u0011\b\u0002\u0003;\u0001\u0011\u000b\u0007I\u0011B;\t\u000b]\u0004A\u0011\u0001=\t\u000bq\u0004A\u0011A?\t\u000f]\u0004A\u0011A\u000b\u0002\u0002!9A\u0010\u0001C\u0001+\u0005%\u0001BBA\u0007\u0001\u0011%AN\u0001\u000bNk2$\u0018N^1sS\u0006$XmR1vgNL\u0017M\u001c\u0006\u0003%M\tA\u0002Z5tiJL'-\u001e;j_:T!\u0001F\u000b\u0002\tM$\u0018\r\u001e\u0006\u0003-]\tQ!\u001c7mS\nT!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001'\r\u0001q$\n\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019rcBA\u0014-\u001d\tA3&D\u0001*\u0015\tQS$\u0001\u0004=e>|GOP\u0005\u0002E%\u0011Q&I\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0003G\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002.C\u0005\u0011Q.^\u000b\u0002gA\u0011AgN\u0007\u0002k)\u0011a'F\u0001\u0007Y&t\u0017\r\\4\n\u0005a*$A\u0002,fGR|'\u000fK\u0002\u0002u\u0001\u0003\"a\u000f \u000e\u0003qR!!P\f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002@y\t)1+\u001b8dK\u0006\n\u0011)A\u00032]Mr\u0003'A\u0002nk\u0002B3A\u0001\u001eA\u0003\u0015\u0019\u0018nZ7b+\u00051\u0005C\u0001\u001bH\u0013\tAUG\u0001\u0004NCR\u0014\u0018\u000e\u001f\u0015\u0004\u0007i\u0002\u0015AB:jO6\f\u0007\u0005K\u0002\u0005u\u0001\u000ba\u0001P5oSRtDc\u0001(Q%B\u0011q\nA\u0007\u0002#!)\u0011'\u0002a\u0001g!\u001a\u0001K\u000f!\t\u000b\u0011+\u0001\u0019\u0001$)\u0007IS\u0004\tK\u0002\u0006u\u0001\u000b\u0001B\u0019:fKj,W*^\u000b\u0002/B\u0019\u0001\f\u00180\u000e\u0003eS!A\u000e.\u000b\u0003m\u000baA\u0019:fKj,\u0017BA/Z\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005\u0001z\u0016B\u00011\"\u0005\u0019!u.\u001e2mK\"\u0012aA\u0019\t\u0003A\rL!\u0001Z\u0011\u0003\u0013Q\u0014\u0018M\\:jK:$Hc\u0001(gO\")\u0011g\u0002a\u0001/\")Ai\u0002a\u0001QB\u0019\u0001,\u001b0\n\u0005)L&a\u0003#f]N,W*\u0019;sSb\fQ\u0001^;qY\u0016,\u0012!\u001c\t\u0005A9Dg,\u0003\u0002pC\t1A+\u001e9mKJB#\u0001\u00032\u0002\u0019I|w\u000e^*jO6\f\u0017J\u001c<\u0016\u0003!D#!\u00032\u0002\u0003U,\u0012A\u0018\u0015\u0003\u0015\t\f1\u0001\u001d3g)\tq\u0016\u0010C\u0003{\u0017\u0001\u00071'A\u0001yQ\rY!\bQ\u0001\u0007Y><\u0007\u000f\u001a4\u0015\u0005ys\b\"\u0002>\r\u0001\u0004\u0019\u0004f\u0001\u0007;\u0001R\u0019a,a\u0001\t\ril\u0001\u0019AA\u0003!\u0011A\u0016q\u00010\n\u0005aJFc\u00010\u0002\f!1!P\u0004a\u0001\u0003\u000b\tAdY1mGVd\u0017\r^3D_Z\f'/[1oG\u0016\u001cuN\\:uC:$8\u000fK\u0002\u0001u\u0001\u0003"
)
public class MultivariateGaussian implements Serializable {
   private transient DenseVector breezeMu;
   private transient Tuple2 tuple;
   private transient DenseMatrix rootSigmaInv;
   private transient double u;
   private final Vector mu;
   private final Matrix sigma;
   private transient volatile byte bitmap$trans$0;

   public Vector mu() {
      return this.mu;
   }

   public Matrix sigma() {
      return this.sigma;
   }

   private DenseVector breezeMu$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.breezeMu = this.mu().asBreeze().toDenseVector$mcD$sp(.MODULE$.Double());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.breezeMu;
   }

   private DenseVector breezeMu() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.breezeMu$lzycompute() : this.breezeMu;
   }

   private Tuple2 tuple$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.tuple = this.calculateCovarianceConstants();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.tuple;
   }

   private Tuple2 tuple() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.tuple$lzycompute() : this.tuple;
   }

   private DenseMatrix rootSigmaInv$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.rootSigmaInv = (DenseMatrix)this.tuple()._1();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.rootSigmaInv;
   }

   private DenseMatrix rootSigmaInv() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.rootSigmaInv$lzycompute() : this.rootSigmaInv;
   }

   private double u$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this.u = this.tuple()._2$mcD$sp();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.u;
   }

   private double u() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.u$lzycompute() : this.u;
   }

   public double pdf(final Vector x) {
      return this.pdf(x.asBreeze());
   }

   public double logpdf(final Vector x) {
      return this.logpdf(x.asBreeze());
   }

   public double pdf(final breeze.linalg.Vector x) {
      return scala.math.package..MODULE$.exp(this.logpdf(x));
   }

   public double logpdf(final breeze.linalg.Vector x) {
      breeze.linalg.Vector delta = (breeze.linalg.Vector)x.$minus(this.breezeMu(), breeze.linalg.operators.HasOps..MODULE$.pureFromUpdate(breeze.linalg.operators.HasOps..MODULE$.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_V_V_Idempotent_Double_OpSub()), breeze.linalg.Vector..MODULE$.canCopy()));
      DenseVector v = (DenseVector)this.rootSigmaInv().$times(delta, breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DM_V_eq_DV_Double());
      return this.u() + BoxesRunTime.unboxToDouble(((ImmutableNumericOps)v.t(breeze.linalg.operators.HasOps..MODULE$.transposeTensor(scala..less.colon.less..MODULE$.refl()))).$times(v, breeze.linalg.operators.HasOps..MODULE$.transTimesNormalFromDot(breeze.linalg.operators.HasOps..MODULE$.canDotD()))) * (double)-0.5F;
   }

   private Tuple2 calculateCovarianceConstants() {
      eigSym.EigSym var3 = (eigSym.EigSym)breeze.linalg.eigSym..MODULE$.apply(this.sigma().asBreeze().toDenseMatrix$mcD$sp(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()), breeze.linalg.eigSym.EigSym_DM_Impl..MODULE$);
      if (var3 != null) {
         DenseVector d = (DenseVector)var3.eigenvalues();
         DenseMatrix u = (DenseMatrix)var3.eigenvectors();
         Tuple2 var2 = new Tuple2(d, u);
         DenseVector d = (DenseVector)var2._1();
         DenseMatrix u = (DenseMatrix)var2._2();
         double tol = MLUtils$.MODULE$.EPSILON() * BoxesRunTime.unboxToDouble(breeze.linalg.max..MODULE$.apply(d, breeze.linalg.max..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))) * (double)d.length();

         try {
            double logPseudoDetSigma = BoxesRunTime.unboxToDouble(d.activeValuesIterator().filter((JFunction1.mcZD.sp)(x$2) -> x$2 > tol).map((JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.log(x)).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
            DenseMatrix pinvS = (DenseMatrix)breeze.linalg.diag..MODULE$.apply(new DenseVector.mcD.sp(((DenseVector)d.map$mcD$sp((JFunction1.mcDD.sp)(v) -> v > tol ? scala.math.package..MODULE$.sqrt((double)1.0F / v) : (double)0.0F, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()))).toArray$mcD$sp(.MODULE$.Double())), breeze.linalg.diag..MODULE$.diagDVDMImpl(.MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero()));
            return new Tuple2(pinvS.$times(u.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), breeze.linalg.operators.HasOps..MODULE$.impl_OpMulMatrix_DMD_DMD_eq_DMD()), BoxesRunTime.boxToDouble((double)-0.5F * ((double)this.mu().size() * scala.math.package..MODULE$.log((Math.PI * 2D)) + logPseudoDetSigma)));
         } catch (UnsupportedOperationException var14) {
            throw new IllegalArgumentException("Covariance matrix has no non-zero singular values");
         }
      } else {
         throw new MatchError(var3);
      }
   }

   public MultivariateGaussian(final Vector mu, final Matrix sigma) {
      this.mu = mu;
      this.sigma = sigma;
      scala.Predef..MODULE$.require(sigma.numCols() == sigma.numRows(), () -> "Covariance matrix must be square");
      scala.Predef..MODULE$.require(mu.size() == sigma.numCols(), () -> "Mean vector length must match covariance matrix size");
   }

   public MultivariateGaussian(final DenseVector mu, final DenseMatrix sigma) {
      this(Vectors$.MODULE$.fromBreeze(mu), Matrices$.MODULE$.fromBreeze(sigma));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
