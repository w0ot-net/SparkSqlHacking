package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.operators.HasOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Q!\u0003\u0006\u0001\u0015QA\u0001b\b\u0001\u0003\u0006\u0004%\t!\t\u0005\tK\u0001\u0011\t\u0011)A\u0005E!1a\u0005\u0001C\u0001\u0015\u001dBqA\u000b\u0001C\u0002\u0013\u00051\u0006\u0003\u00048\u0001\u0001\u0006I\u0001\f\u0005\u0006q\u0001!\t%\u000f\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006\u0019\u0002!\t%\u0014\u0002\u0015\rVt7\r^5p]\u0006dG*Y=fe6{G-\u001a7\u000b\u0005-a\u0011aA1o]*\u0011QBD\u0001\u0003[2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d;5\t!\"\u0003\u0002\u001f\u0015\tQA*Y=fe6{G-\u001a7\u0002\u000b1\f\u00170\u001a:\u0004\u0001U\t!\u0005\u0005\u0002\u001dG%\u0011AE\u0003\u0002\u0010\rVt7\r^5p]\u0006dG*Y=fe\u00061A.Y=fe\u0002\na\u0001P5oSRtDC\u0001\u0015*!\ta\u0002\u0001C\u0003 \u0007\u0001\u0007!%A\u0004xK&<\u0007\u000e^:\u0016\u00031\u00022!\f\u001a5\u001b\u0005q#BA\u00181\u0003\u0019a\u0017N\\1mO*\t\u0011'\u0001\u0004ce\u0016,'0Z\u0005\u0003g9\u00121\u0002R3og\u00164Vm\u0019;peB\u0011a#N\u0005\u0003m]\u0011a\u0001R8vE2,\u0017\u0001C<fS\u001eDGo\u001d\u0011\u0002\t\u00154\u0018\r\u001c\u000b\u0004uu\u0012\u0005C\u0001\f<\u0013\tatC\u0001\u0003V]&$\b\"\u0002 \u0007\u0001\u0004y\u0014\u0001\u00023bi\u0006\u00042!\f!5\u0013\t\teFA\u0006EK:\u001cX-T1ue&D\b\"B\"\u0007\u0001\u0004y\u0014AB8viB,H/\u0001\td_6\u0004X\u000f^3Qe\u00164H)\u001a7uCR!!H\u0012%K\u0011\u00159u\u00011\u0001@\u0003%qW\r\u001f;EK2$\u0018\rC\u0003J\u000f\u0001\u0007q(A\u0003j]B,H\u000fC\u0003L\u000f\u0001\u0007q(A\u0003eK2$\u0018-\u0001\u0003he\u0006$G\u0003\u0002\u001eO\u001fBCQa\u0013\u0005A\u0002}BQ!\u0013\u0005A\u0002}BQ!\u0015\u0005A\u00021\nqaY;n\u000fJ\fG\r"
)
public class FunctionalLayerModel implements LayerModel {
   private final FunctionalLayer layer;
   private final DenseVector weights;

   public FunctionalLayer layer() {
      return this.layer;
   }

   public DenseVector weights() {
      return this.weights;
   }

   public void eval(final DenseMatrix data, final DenseMatrix output) {
      ApplyInPlace$.MODULE$.apply(data, output, this.layer().activationFunction().eval());
   }

   public void computePrevDelta(final DenseMatrix nextDelta, final DenseMatrix input, final DenseMatrix delta) {
      ApplyInPlace$.MODULE$.apply(input, delta, this.layer().activationFunction().derivative());
      delta.$colon$times$eq(nextDelta, .MODULE$.dm_dm_UpdateOp_Double_OpMulScalar());
   }

   public void grad(final DenseMatrix delta, final DenseMatrix input, final DenseVector cumGrad) {
   }

   public FunctionalLayerModel(final FunctionalLayer layer) {
      this.layer = layer;
      this.weights = new DenseVector.mcD.sp(0, scala.reflect.ClassTag..MODULE$.Double());
   }
}
