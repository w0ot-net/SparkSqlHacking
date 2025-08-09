package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.Vector;
import breeze.linalg.operators.HasOps.;
import java.io.Serializable;
import org.apache.spark.mllib.stat.distribution.MultivariateGaussian;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Er!\u0002\u000b\u0016\u0011\u0013\u0001c!\u0002\u0012\u0016\u0011\u0013\u0019\u0003\"\u0002\u001a\u0002\t\u0003\u0019\u0004\"\u0002\u001b\u0002\t\u0003)\u0004\"B>\u0002\t\u0003a\b\"CA\u0011\u0003\u0005\u0005I\u0011BA\u0012\r\u0011\u0011S\u0003B\u001c\t\u0011\r3!\u00111A\u0005\u0002\u0011C\u0001\u0002\u0013\u0004\u0003\u0002\u0004%\t!\u0013\u0005\t\u001f\u001a\u0011\t\u0011)Q\u0005\u000b\"A\u0001K\u0002BC\u0002\u0013\u0005\u0011\u000b\u0003\u0005V\r\t\u0005\t\u0015!\u0003S\u0011!1fA!b\u0001\n\u00039\u0006\u0002C1\u0007\u0005\u0003\u0005\u000b\u0011\u0002-\t\u0011\t4!Q1A\u0005\u0002\rD\u0001\u0002\u001b\u0004\u0003\u0002\u0003\u0006I\u0001\u001a\u0005\u0006e\u0019!\t!\u001b\u0005\b]\u001a\u0011\r\u0011\"\u0001p\u0011\u0019\u0019h\u0001)A\u0005a\")AO\u0002C\u0001k\u0006qQ\t\u001f9fGR\fG/[8o'Vl'B\u0001\f\u0018\u0003)\u0019G.^:uKJLgn\u001a\u0006\u00031e\tQ!\u001c7mS\nT!AG\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qi\u0012AB1qC\u000eDWMC\u0001\u001f\u0003\ry'oZ\u0002\u0001!\t\t\u0013!D\u0001\u0016\u00059)\u0005\u0010]3di\u0006$\u0018n\u001c8Tk6\u001c2!\u0001\u0013+!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111\u0006M\u0007\u0002Y)\u0011QFL\u0001\u0003S>T\u0011aL\u0001\u0005U\u00064\u0018-\u0003\u00022Y\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012\u0001I\u0001\u0005u\u0016\u0014x\u000eF\u00027qf\u0004\"!\t\u0004\u0014\u0007\u0019!\u0003\b\u0005\u0002:\u0003:\u0011!h\u0010\b\u0003wyj\u0011\u0001\u0010\u0006\u0003{}\ta\u0001\u0010:p_Rt\u0014\"A\u0014\n\u0005\u00013\u0013a\u00029bG.\fw-Z\u0005\u0003c\tS!\u0001\u0011\u0014\u0002\u001b1|w\rT5lK2L\u0007n\\8e+\u0005)\u0005CA\u0013G\u0013\t9eE\u0001\u0004E_V\u0014G.Z\u0001\u0012Y><G*[6fY&Dwn\u001c3`I\u0015\fHC\u0001&N!\t)3*\u0003\u0002MM\t!QK\\5u\u0011\u001dq\u0005\"!AA\u0002\u0015\u000b1\u0001\u001f\u00132\u00039awn\u001a'jW\u0016d\u0017\u000e[8pI\u0002\nqa^3jO\"$8/F\u0001S!\r)3+R\u0005\u0003)\u001a\u0012Q!\u0011:sCf\f\u0001b^3jO\"$8\u000fI\u0001\u0006[\u0016\fgn]\u000b\u00021B\u0019QeU-\u0011\u0007i{V)D\u0001\\\u0015\taV,\u0001\u0004mS:\fGn\u001a\u0006\u0002=\u00061!M]3fu\u0016L!\u0001Y.\u0003\u0017\u0011+gn]3WK\u000e$xN]\u0001\u0007[\u0016\fgn\u001d\u0011\u0002\rMLw-\\1t+\u0005!\u0007cA\u0013TKB\u0019!LZ#\n\u0005\u001d\\&a\u0003#f]N,W*\u0019;sSb\fqa]5h[\u0006\u001c\b\u0005F\u00037U.dW\u000eC\u0003D!\u0001\u0007Q\tC\u0003Q!\u0001\u0007!\u000bC\u0003W!\u0001\u0007\u0001\fC\u0003c!\u0001\u0007A-A\u0001l+\u0005\u0001\bCA\u0013r\u0013\t\u0011hEA\u0002J]R\f!a\u001b\u0011\u0002\u0011\u0011\u0002H.^:%KF$\"A\u000e<\t\u000b]\u001c\u0002\u0019\u0001\u001c\u0002\u0003aDQA\\\u0002A\u0002ADQA_\u0002A\u0002A\f\u0011\u0001Z\u0001\u0004C\u0012$G#B?\u0002\n\u0005-A\u0003\u0002\u001c\u007f\u0003\u0003AQa \u0003A\u0002Y\nAa];ng\"1q\u000f\u0002a\u0001\u0003\u0007\u0001BAWA\u0003\u000b&\u0019\u0011qA.\u0003\rY+7\r^8s\u0011\u0015\u0001F\u00011\u0001S\u0011\u001d\ti\u0001\u0002a\u0001\u0003\u001f\tQ\u0001Z5tiN\u0004B!J*\u0002\u0012A!\u00111CA\u000f\u001b\t\t)B\u0003\u0003\u0002\u0018\u0005e\u0011\u0001\u00043jgR\u0014\u0018NY;uS>t'bAA\u000e/\u0005!1\u000f^1u\u0013\u0011\ty\"!\u0006\u0003)5+H\u000e^5wCJL\u0017\r^3HCV\u001c8/[1o\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0003\u0005\u0003\u0002(\u00055RBAA\u0015\u0015\r\tYCL\u0001\u0005Y\u0006tw-\u0003\u0003\u00020\u0005%\"AB(cU\u0016\u001cG\u000f"
)
public class ExpectationSum implements Serializable {
   private double logLikelihood;
   private final double[] weights;
   private final DenseVector[] means;
   private final DenseMatrix[] sigmas;
   private final int k;

   public static ExpectationSum add(final double[] weights, final MultivariateGaussian[] dists, final ExpectationSum sums, final Vector x) {
      return ExpectationSum$.MODULE$.add(weights, dists, sums, x);
   }

   public static ExpectationSum zero(final int k, final int d) {
      return ExpectationSum$.MODULE$.zero(k, d);
   }

   public double logLikelihood() {
      return this.logLikelihood;
   }

   public void logLikelihood_$eq(final double x$1) {
      this.logLikelihood = x$1;
   }

   public double[] weights() {
      return this.weights;
   }

   public DenseVector[] means() {
      return this.means;
   }

   public DenseMatrix[] sigmas() {
      return this.sigmas;
   }

   public int k() {
      return this.k;
   }

   public ExpectationSum $plus$eq(final ExpectationSum x) {
      for(int i = 0; i < this.k(); ++i) {
         this.weights()[i] += x.weights()[i];
         this.means()[i].$plus$eq(x.means()[i], .MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         this.sigmas()[i].$plus$eq(x.sigmas()[i], .MODULE$.dm_dm_UpdateOp_Double_OpAdd());
      }

      this.logLikelihood_$eq(this.logLikelihood() + x.logLikelihood());
      return this;
   }

   public ExpectationSum(final double logLikelihood, final double[] weights, final DenseVector[] means, final DenseMatrix[] sigmas) {
      this.logLikelihood = logLikelihood;
      this.weights = weights;
      this.means = means;
      this.sigmas = sigmas;
      super();
      this.k = weights.length;
   }
}
