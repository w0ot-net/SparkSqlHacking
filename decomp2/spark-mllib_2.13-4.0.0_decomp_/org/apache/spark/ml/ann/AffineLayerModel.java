package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.NumericOps;
import java.util.Random;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b!\u0002\f\u0018\u0001]\t\u0003\u0002\u0003\u0017\u0001\u0005\u000b\u0007I\u0011\u0001\u0018\t\u0011i\u0002!\u0011!Q\u0001\n=B\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005{!1\u0011\t\u0001C\u0001/\tCqA\u0012\u0001C\u0002\u0013\u0005q\t\u0003\u0004L\u0001\u0001\u0006I\u0001\u0013\u0005\b\u0019\u0002\u0011\r\u0011\"\u0001/\u0011\u0019i\u0005\u0001)A\u0005_!9a\n\u0001a\u0001\n\u0013q\u0003bB(\u0001\u0001\u0004%I\u0001\u0015\u0005\u0007-\u0002\u0001\u000b\u0015B\u0018\t\u000b]\u0003A\u0011\t-\t\u000bu\u0003A\u0011\t0\t\u000b\u0011\u0004A\u0011I3\b\r-<\u0002\u0012A\fm\r\u00191r\u0003#\u0001\u0018[\")\u0011)\u0005C\u0001m\")q/\u0005C\u0001q\"9\u0011qA\t\u0005\u0002\u0005%\u0001\"CA\u000f#\u0005\u0005I\u0011BA\u0010\u0005A\teMZ5oK2\u000b\u00170\u001a:N_\u0012,GN\u0003\u0002\u00193\u0005\u0019\u0011M\u001c8\u000b\u0005iY\u0012AA7m\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7c\u0001\u0001#QA\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0016\u000e\u0003]I!aK\f\u0003\u00151\u000b\u00170\u001a:N_\u0012,G.A\u0004xK&<\u0007\u000e^:\u0004\u0001U\tq\u0006E\u00021k]j\u0011!\r\u0006\u0003eM\na\u0001\\5oC2<'\"\u0001\u001b\u0002\r\t\u0014X-\u001a>f\u0013\t1\u0014GA\u0006EK:\u001cXMV3di>\u0014\bCA\u00129\u0013\tIDE\u0001\u0004E_V\u0014G.Z\u0001\to\u0016Lw\r\u001b;tA\u0005)A.Y=feV\tQ\b\u0005\u0002*}%\u0011qh\u0006\u0002\f\u0003\u001a4\u0017N\\3MCf,'/\u0001\u0004mCf,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\r#U\t\u0005\u0002*\u0001!)A&\u0002a\u0001_!)1(\u0002a\u0001{\u0005\tq/F\u0001I!\r\u0001\u0014jN\u0005\u0003\u0015F\u00121\u0002R3og\u0016l\u0015\r\u001e:jq\u0006\u0011q\u000fI\u0001\u0002E\u0006\u0011!\rI\u0001\u0005_:,7/\u0001\u0005p]\u0016\u001cx\fJ3r)\t\tF\u000b\u0005\u0002$%&\u00111\u000b\n\u0002\u0005+:LG\u000fC\u0004V\u0017\u0005\u0005\t\u0019A\u0018\u0002\u0007a$\u0013'A\u0003p]\u0016\u001c\b%\u0001\u0003fm\u0006dGcA)Z7\")!,\u0004a\u0001\u0011\u0006!A-\u0019;b\u0011\u0015aV\u00021\u0001I\u0003\u0019yW\u000f\u001e9vi\u0006\u00012m\\7qkR,\u0007K]3w\t\u0016dG/\u0019\u000b\u0005#~\u000b'\rC\u0003a\u001d\u0001\u0007\u0001*A\u0003eK2$\u0018\rC\u0003]\u001d\u0001\u0007\u0001\nC\u0003d\u001d\u0001\u0007\u0001*A\u0005qe\u00164H)\u001a7uC\u0006!qM]1e)\u0011\tfmZ5\t\u000b\u0001|\u0001\u0019\u0001%\t\u000b!|\u0001\u0019\u0001%\u0002\u000b%t\u0007/\u001e;\t\u000b)|\u0001\u0019A\u0018\u0002\u000f\r,Xn\u0012:bI\u0006\u0001\u0012I\u001a4j]\u0016d\u0015-_3s\u001b>$W\r\u001c\t\u0003SE\u00192!\u0005\u0012o!\tyG/D\u0001q\u0015\t\t(/\u0001\u0002j_*\t1/\u0001\u0003kCZ\f\u0017BA;q\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005a\u0017!B1qa2LH\u0003B\"zunDQaO\nA\u0002uBQ\u0001L\nA\u0002=BQ\u0001`\nA\u0002u\faA]1oI>l\u0007c\u0001@\u0002\u00045\tqPC\u0002\u0002\u0002I\fA!\u001e;jY&\u0019\u0011QA@\u0003\rI\u000bg\u000eZ8n\u00035\u0011\u0018M\u001c3p[^+\u0017n\u001a5ugRI\u0011+a\u0003\u0002\u0016\u0005e\u00111\u0004\u0005\b\u0003\u001b!\u0002\u0019AA\b\u0003\u0015qW/\\%o!\r\u0019\u0013\u0011C\u0005\u0004\u0003'!#aA%oi\"9\u0011q\u0003\u000bA\u0002\u0005=\u0011A\u00028v[>+H\u000fC\u0003-)\u0001\u0007q\u0006C\u0003})\u0001\u0007Q0\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\"A!\u00111EA\u0015\u001b\t\t)CC\u0002\u0002(I\fA\u0001\\1oO&!\u00111FA\u0013\u0005\u0019y%M[3di\u0002"
)
public class AffineLayerModel implements LayerModel {
   private final DenseVector weights;
   private final AffineLayer layer;
   private final DenseMatrix w;
   private final DenseVector b;
   private DenseVector ones;

   public static void randomWeights(final int numIn, final int numOut, final DenseVector weights, final Random random) {
      AffineLayerModel$.MODULE$.randomWeights(numIn, numOut, weights, random);
   }

   public static AffineLayerModel apply(final AffineLayer layer, final DenseVector weights, final Random random) {
      return AffineLayerModel$.MODULE$.apply(layer, weights, random);
   }

   public DenseVector weights() {
      return this.weights;
   }

   public AffineLayer layer() {
      return this.layer;
   }

   public DenseMatrix w() {
      return this.w;
   }

   public DenseVector b() {
      return this.b;
   }

   private DenseVector ones() {
      return this.ones;
   }

   private void ones_$eq(final DenseVector x$1) {
      this.ones = x$1;
   }

   public void eval(final DenseMatrix data, final DenseMatrix output) {
      ((NumericOps)output.apply(.MODULE$.$colon$colon(), breeze.linalg..times..MODULE$, breeze.linalg.Broadcaster..MODULE$.canBroadcastColumns(breeze.linalg.operators.HasOps..MODULE$.handholdCanMapRows_DM()))).$colon$eq(this.b(), breeze.linalg.operators.HasOps..MODULE$.broadcastInplaceOp2_BCols(breeze.linalg.operators.HasOps..MODULE$.handholdCanMapRows_DM(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_DV_Double_OpSet(), breeze.linalg.operators.HasOps..MODULE$.canTraverseCols_DM()));
      BreezeUtil$.MODULE$.dgemm((double)1.0F, this.w(), data, (double)1.0F, output);
   }

   public void computePrevDelta(final DenseMatrix delta, final DenseMatrix output, final DenseMatrix prevDelta) {
      BreezeUtil$.MODULE$.dgemm((double)1.0F, (DenseMatrix)this.w().t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), delta, (double)0.0F, prevDelta);
   }

   public void grad(final DenseMatrix delta, final DenseMatrix input, final DenseVector cumGrad) {
      DenseMatrix cumGradientOfWeights = new DenseMatrix.mcD.sp(this.w().rows(), this.w().cols(), cumGrad.data$mcD$sp(), cumGrad.offset());
      BreezeUtil$.MODULE$.dgemm((double)1.0F / (double)input.cols(), delta, (DenseMatrix)input.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), (double)1.0F, cumGradientOfWeights);
      if (this.ones() == null || this.ones().length() != delta.cols()) {
         this.ones_$eq(breeze.linalg.DenseVector..MODULE$.ones$mDc$sp(delta.cols(), scala.reflect.ClassTag..MODULE$.Double(), breeze.math.Semiring..MODULE$.semiringD()));
      }

      DenseVector cumGradientOfBias = new DenseVector.mcD.sp(cumGrad.data$mcD$sp(), cumGrad.offset() + this.w().size(), 1, this.b().length());
      BreezeUtil$.MODULE$.dgemv((double)1.0F / (double)input.cols(), delta, this.ones(), (double)1.0F, cumGradientOfBias);
   }

   public AffineLayerModel(final DenseVector weights, final AffineLayer layer) {
      this.weights = weights;
      this.layer = layer;
      this.w = new DenseMatrix.mcD.sp(layer.numOut(), layer.numIn(), weights.data$mcD$sp(), weights.offset());
      this.b = new DenseVector.mcD.sp(weights.data$mcD$sp(), weights.offset() + layer.numOut() * layer.numIn(), 1, layer.numOut());
      this.ones = null;
   }
}
