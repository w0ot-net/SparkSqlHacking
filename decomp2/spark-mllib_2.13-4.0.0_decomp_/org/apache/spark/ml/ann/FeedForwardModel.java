package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!B\u0010!\u0001\tR\u0003\u0002C\u001b\u0001\u0005\u000b\u0007I\u0011A\u001c\t\u0011y\u0002!\u0011!Q\u0001\naB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0011)A\u0005\u0003\")Q\t\u0001C\u0005\r\"9!\n\u0001b\u0001\n\u0003Y\u0005B\u0002*\u0001A\u0003%A\nC\u0004T\u0001\t\u0007I\u0011\u0001+\t\re\u0003\u0001\u0015!\u0003V\u0011\u001dQ\u0006\u00011A\u0005\nmCqa\u0018\u0001A\u0002\u0013%\u0001\r\u0003\u0004g\u0001\u0001\u0006K\u0001\u0018\u0005\bO\u0002\u0001\r\u0011\"\u0003i\u0011\u001d!\b\u00011A\u0005\nUDaa\u001e\u0001!B\u0013I\u0007b\u0002=\u0001\u0001\u0004%I\u0001\u001b\u0005\bs\u0002\u0001\r\u0011\"\u0003{\u0011\u0019a\b\u0001)Q\u0005S\")Q\u0010\u0001C!}\"9\u0011Q\u0002\u0001\u0005B\u0005=\u0001bBA\u0010\u0001\u0011\u0005\u0013\u0011\u0005\u0005\b\u0003K\u0001A\u0011IA\u0014\u0011\u001d\tY\u0003\u0001C!\u0003[9\u0001\"!\r!\u0011\u0003\u0001\u00131\u0007\u0004\b?\u0001B\t\u0001IA\u001b\u0011\u0019)\u0015\u0004\"\u0001\u0002H!9\u0011\u0011J\r\u0005\u0002\u0005-\u0003bBA%3\u0011\u0005\u0011\u0011\u000b\u0005\n\u0003?J\u0012\u0013!C\u0001\u0003CB\u0011\"a\u001e\u001a\u0003\u0003%I!!\u001f\u0003!\u0019+W\r\u001a$pe^\f'\u000fZ'pI\u0016d'BA\u0011#\u0003\r\tgN\u001c\u0006\u0003G\u0011\n!!\u001c7\u000b\u0005\u00152\u0013!B:qCJ\\'BA\u0014)\u0003\u0019\t\u0007/Y2iK*\t\u0011&A\u0002pe\u001e\u001c2\u0001A\u00162!\tas&D\u0001.\u0015\u0005q\u0013!B:dC2\f\u0017B\u0001\u0019.\u0005\u0019\te.\u001f*fMB\u0011!gM\u0007\u0002A%\u0011A\u0007\t\u0002\u000e)>\u0004x\u000e\\8hs6{G-\u001a7\u0002\u000f],\u0017n\u001a5ug\u000e\u0001Q#\u0001\u001d\u0011\u0005ebT\"\u0001\u001e\u000b\u0005m\u0012\u0013A\u00027j]\u0006dw-\u0003\u0002>u\t1a+Z2u_J\f\u0001b^3jO\"$8\u000fI\u0001\ti>\u0004x\u000e\\8hsV\t\u0011\t\u0005\u00023\u0005&\u00111\t\t\u0002\u0014\r\u0016,GMR8so\u0006\u0014H\rV8q_2|w-_\u0001\ni>\u0004x\u000e\\8hs\u0002\na\u0001P5oSRtDcA$I\u0013B\u0011!\u0007\u0001\u0005\u0006k\u0015\u0001\r\u0001\u000f\u0005\u0006\u007f\u0015\u0001\r!Q\u0001\u0007Y\u0006LXM]:\u0016\u00031\u00032\u0001L'P\u0013\tqUFA\u0003BeJ\f\u0017\u0010\u0005\u00023!&\u0011\u0011\u000b\t\u0002\u0006\u0019\u0006LXM]\u0001\bY\u0006LXM]:!\u0003-a\u0017-_3s\u001b>$W\r\\:\u0016\u0003U\u00032\u0001L'W!\t\u0011t+\u0003\u0002YA\tQA*Y=fe6{G-\u001a7\u0002\u00191\f\u00170\u001a:N_\u0012,Gn\u001d\u0011\u0002\r=4gm]3u+\u0005a\u0006C\u0001\u0017^\u0013\tqVFA\u0002J]R\f!b\u001c4gg\u0016$x\fJ3r)\t\tG\r\u0005\u0002-E&\u00111-\f\u0002\u0005+:LG\u000fC\u0004f\u0017\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0013'A\u0004pM\u001a\u001cX\r\u001e\u0011\u0002\u000f=,H\u000f];ugV\t\u0011\u000eE\u0002-\u001b*\u00042a[8r\u001b\u0005a'BA\u001en\u0015\u0005q\u0017A\u00022sK\u0016TX-\u0003\u0002qY\nYA)\u001a8tK6\u000bGO]5y!\ta#/\u0003\u0002t[\t1Ai\\;cY\u0016\f1b\\;uaV$8o\u0018\u0013fcR\u0011\u0011M\u001e\u0005\bK:\t\t\u00111\u0001j\u0003!yW\u000f\u001e9viN\u0004\u0013A\u00023fYR\f7/\u0001\u0006eK2$\u0018m]0%KF$\"!Y>\t\u000f\u0015\f\u0012\u0011!a\u0001S\u00069A-\u001a7uCN\u0004\u0013a\u00024pe^\f'\u000f\u001a\u000b\u0005S~\f\u0019\u0001\u0003\u0004\u0002\u0002M\u0001\rA[\u0001\u0005I\u0006$\u0018\rC\u0004\u0002\u0006M\u0001\r!a\u0002\u0002!%t7\r\\;eK2\u000b7\u000f\u001e'bs\u0016\u0014\bc\u0001\u0017\u0002\n%\u0019\u00111B\u0017\u0003\u000f\t{w\u000e\\3b]\u0006y1m\\7qkR,wI]1eS\u0016tG\u000fF\u0005r\u0003#\t\u0019\"a\u0006\u0002\u001c!1\u0011\u0011\u0001\u000bA\u0002)Da!!\u0006\u0015\u0001\u0004Q\u0017A\u0002;be\u001e,G\u000f\u0003\u0004\u0002\u001aQ\u0001\r\u0001O\u0001\fGVlwI]1eS\u0016tG\u000f\u0003\u0004\u0002\u001eQ\u0001\r\u0001X\u0001\u000ee\u0016\fGNQ1uG\"\u001c\u0016N_3\u0002\u000fA\u0014X\rZ5diR\u0019\u0001(a\t\t\r\u0005\u0005Q\u00031\u00019\u0003)\u0001(/\u001a3jGR\u0014\u0016m\u001e\u000b\u0004q\u0005%\u0002BBA\u0001-\u0001\u0007\u0001(\u0001\fsC^\u0014\u0004K]8cC\nLG.\u001b;z\u0013:\u0004F.Y2f)\rA\u0014q\u0006\u0005\u0007\u0003\u00039\u0002\u0019\u0001\u001d\u0002!\u0019+W\r\u001a$pe^\f'\u000fZ'pI\u0016d\u0007C\u0001\u001a\u001a'\u0011I2&a\u000e\u0011\t\u0005e\u00121I\u0007\u0003\u0003wQA!!\u0010\u0002@\u0005\u0011\u0011n\u001c\u0006\u0003\u0003\u0003\nAA[1wC&!\u0011QIA\u001e\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\t\u0019$A\u0003baBd\u0017\u0010F\u0003H\u0003\u001b\ny\u0005C\u0003@7\u0001\u0007\u0011\tC\u000367\u0001\u0007\u0001\bF\u0003H\u0003'\n)\u0006C\u0003@9\u0001\u0007\u0011\tC\u0005\u0002Xq\u0001\n\u00111\u0001\u0002Z\u0005!1/Z3e!\ra\u00131L\u0005\u0004\u0003;j#\u0001\u0002'p]\u001e\fq\"\u00199qYf$C-\u001a4bk2$HEM\u000b\u0003\u0003GRC!!\u0017\u0002f-\u0012\u0011q\r\t\u0005\u0003S\n\u0019(\u0004\u0002\u0002l)!\u0011QNA8\u0003%)hn\u00195fG.,GMC\u0002\u0002r5\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)(a\u001b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002|A!\u0011QPAB\u001b\t\tyH\u0003\u0003\u0002\u0002\u0006}\u0012\u0001\u00027b]\u001eLA!!\"\u0002\u0000\t1qJ\u00196fGR\u0004"
)
public class FeedForwardModel implements TopologyModel {
   private final Vector weights;
   private final FeedForwardTopology topology;
   private final Layer[] layers;
   private final LayerModel[] layerModels;
   private int offset;
   private DenseMatrix[] outputs;
   private DenseMatrix[] deltas;

   public static long apply$default$2() {
      return FeedForwardModel$.MODULE$.apply$default$2();
   }

   public static FeedForwardModel apply(final FeedForwardTopology topology, final long seed) {
      return FeedForwardModel$.MODULE$.apply(topology, seed);
   }

   public static FeedForwardModel apply(final FeedForwardTopology topology, final Vector weights) {
      return FeedForwardModel$.MODULE$.apply(topology, weights);
   }

   public Vector weights() {
      return this.weights;
   }

   public FeedForwardTopology topology() {
      return this.topology;
   }

   public Layer[] layers() {
      return this.layers;
   }

   public LayerModel[] layerModels() {
      return this.layerModels;
   }

   private int offset() {
      return this.offset;
   }

   private void offset_$eq(final int x$1) {
      this.offset = x$1;
   }

   private DenseMatrix[] outputs() {
      return this.outputs;
   }

   private void outputs_$eq(final DenseMatrix[] x$1) {
      this.outputs = x$1;
   }

   private DenseMatrix[] deltas() {
      return this.deltas;
   }

   private void deltas_$eq(final DenseMatrix[] x$1) {
      this.deltas = x$1;
   }

   public DenseMatrix[] forward(final DenseMatrix data, final boolean includeLastLayer) {
      int currentBatchSize = data.cols();
      if (this.outputs() == null || this.outputs()[0].cols() != currentBatchSize) {
         this.outputs_$eq(new DenseMatrix[this.layers().length]);
         IntRef inputSize = IntRef.create(data.rows());
         .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.layers())).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            if (this.layers()[i].inPlace()) {
               this.outputs()[i] = this.outputs()[i - 1];
            } else {
               int outputSize = this.layers()[i].getOutputSize(inputSize.elem);
               this.outputs()[i] = new DenseMatrix.mcD.sp(outputSize, currentBatchSize, scala.reflect.ClassTag..MODULE$.Double());
               inputSize.elem = outputSize;
            }
         });
      }

      this.layerModels()[0].eval(data, this.outputs()[0]);
      int end = includeLastLayer ? this.layerModels().length : this.layerModels().length - 1;
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(1), end).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> this.layerModels()[i].eval(this.outputs()[i - 1], this.outputs()[i]));
      return this.outputs();
   }

   public double computeGradient(final DenseMatrix data, final DenseMatrix target, final Vector cumGradient, final int realBatchSize) {
      DenseMatrix[] outputs = this.forward(data, true);
      int currentBatchSize = data.cols();
      if (this.deltas() == null || this.deltas()[0].cols() != currentBatchSize) {
         this.deltas_$eq(new DenseMatrix[this.layerModels().length]);
         IntRef inputSize = IntRef.create(data.rows());
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.layerModels().length - 1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            int outputSize = this.layers()[i].getOutputSize(inputSize.elem);
            this.deltas()[i] = new DenseMatrix.mcD.sp(outputSize, currentBatchSize, scala.reflect.ClassTag..MODULE$.Double());
            inputSize.elem = outputSize;
         });
      }

      int L = this.layerModels().length - 1;
      LayerModel var13 = (LayerModel).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(this.layerModels()));
      if (var13 instanceof LossFunction) {
         double loss = ((LossFunction)var13).loss((DenseMatrix).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputs)), target, this.deltas()[L - 1]);
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(L - 2), 0, -1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> this.layerModels()[i + 1].computePrevDelta(this.deltas()[i + 1], outputs[i + 1], this.deltas()[i]));
         double[] cumGradientArray = cumGradient.toArray();
         IntRef offset = IntRef.create(0);
         .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.layerModels())).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            DenseMatrix input = i == 0 ? data : outputs[i - 1];
            this.layerModels()[i].grad(this.deltas()[i], input, new DenseVector.mcD.sp(cumGradientArray, offset.elem, 1, this.layers()[i].weightSize()));
            offset.elem += this.layers()[i].weightSize();
         });
         return loss;
      } else {
         throw new UnsupportedOperationException("Top layer is required to have objective.");
      }
   }

   public Vector predict(final Vector data) {
      int size = data.size();
      DenseMatrix[] result = this.forward(new DenseMatrix.mcD.sp(size, 1, data.toArray()), true);
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(((DenseMatrix).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])result))).toArray$mcD$sp());
   }

   public Vector predictRaw(final Vector data) {
      DenseMatrix[] result = this.forward(new DenseMatrix.mcD.sp(data.size(), 1, data.toArray()), false);
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(result[result.length - 2].toArray$mcD$sp());
   }

   public Vector raw2ProbabilityInPlace(final Vector data) {
      DenseMatrix dataMatrix = new DenseMatrix.mcD.sp(data.size(), 1, data.toArray());
      ((LayerModel).MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(this.layerModels()))).eval(dataMatrix, dataMatrix);
      return data;
   }

   public FeedForwardModel(final Vector weights, final FeedForwardTopology topology) {
      this.weights = weights;
      this.topology = topology;
      this.layers = topology.layers();
      this.layerModels = new LayerModel[this.layers().length];
      this.offset = 0;
      .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.layers())).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         this.layerModels()[i] = this.layers()[i].createModel(new DenseVector.mcD.sp(this.weights().toArray(), this.offset(), 1, this.layers()[i].weightSize()));
         this.offset_$eq(this.offset() + this.layers()[i].weightSize());
      });
      this.outputs = null;
      this.deltas = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
