package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import org.apache.spark.mllib.tree.model.InformationGainStats;
import org.apache.spark.mllib.tree.model.Predict;
import scala.Some;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\u0010!\u0001-B\u0001\u0002\r\u0001\u0003\u0006\u0004%\t%\r\u0005\tq\u0001\u0011\t\u0011)A\u0005e!A\u0011\b\u0001BC\u0002\u0013\u0005\u0013\u0007\u0003\u0005;\u0001\t\u0005\t\u0015!\u00033\u0011!Y\u0004A!b\u0001\n\u0003\t\u0004\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u0011u\u0002!Q1A\u0005\u0002yB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\t\u0001\u0002\u0011)\u0019!C\u0001}!A\u0011\t\u0001B\u0001B\u0003%A\u0006\u0003\u0005C\u0001\t\u0015\r\u0011\"\u0001D\u0011!9\u0005A!A!\u0002\u0013!\u0005\"\u0003%\u0001\u0005\u000b\u0007I\u0011\t\u0012J\u0011!\u0011\u0006A!A!\u0002\u0013Q\u0005BB*\u0001\t\u0003\u0011C\u000bC\u0003^\u0001\u0011\u0005c\f\u0003\u0004k\u0001\u0011\u0005#e\u001b\u0005\u0007o\u0002!\tE\t=\t\u0011\u0005-\u0001\u0001\"\u0011!\u0003\u001bA\u0001\"a\u0004\u0001\t\u0003\u0002\u0013\u0011\u0003\u0005\u000b\u0003/\u0001\u0011\u0013!C\u0001A\u0005e\u0001\u0002CA\u0018\u0001\u0011\u0005\u0003%!\u0004\t\u0011\u0005E\u0002\u0001\"\u0011#\u0003gA\u0001\"a\u0011\u0001\t\u0003\u0012\u0013Q\t\u0005\t\u0003\u000f\u0002A\u0011\t\u0011\u0002J\u001d9\u00111\n\u0011\t\n\u00055cAB\u0010!\u0011\u0013\ty\u0005\u0003\u0004T7\u0011\u0005\u0011q\r\u0005\b\u0003SZB\u0011BA6\u0011%\tIhGA\u0001\n\u0013\tYH\u0001\u0007J]R,'O\\1m\u001d>$WM\u0003\u0002\"E\u0005!AO]3f\u0015\t\u0019C%\u0001\u0002nY*\u0011QEJ\u0001\u0006gB\f'o\u001b\u0006\u0003O!\na!\u00199bG\",'\"A\u0015\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001a\u0003CA\u0017/\u001b\u0005\u0001\u0013BA\u0018!\u0005\u0011qu\u000eZ3\u0002\u0015A\u0014X\rZ5di&|g.F\u00013!\t\u0019d'D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0019!u.\u001e2mK\u0006Y\u0001O]3eS\u000e$\u0018n\u001c8!\u0003!IW\u000e];sSRL\u0018!C5naV\u0014\u0018\u000e^=!\u0003\u00119\u0017-\u001b8\u0002\u000b\u001d\f\u0017N\u001c\u0011\u0002\u00131,g\r^\"iS2$W#\u0001\u0017\u0002\u00151,g\r^\"iS2$\u0007%\u0001\u0006sS\u001eDGo\u00115jY\u0012\f1B]5hQR\u001c\u0005.\u001b7eA\u0005)1\u000f\u001d7jiV\tA\t\u0005\u0002.\u000b&\u0011a\t\t\u0002\u0006'Bd\u0017\u000e^\u0001\u0007gBd\u0017\u000e\u001e\u0011\u0002\u001b%l\u0007/\u001e:jif\u001cF/\u0019;t+\u0005Q\u0005CA&Q\u001b\u0005a%BA\u001dN\u0015\t\tcJ\u0003\u0002PI\u0005)Q\u000e\u001c7jE&\u0011\u0011\u000b\u0014\u0002\u0013\u00136\u0004XO]5us\u000e\u000bGnY;mCR|'/\u0001\bj[B,(/\u001b;z'R\fGo\u001d\u0011\u0002\rqJg.\u001b;?)!)fk\u0016-Z5nc\u0006CA\u0017\u0001\u0011\u0015\u0001t\u00021\u00013\u0011\u0015It\u00021\u00013\u0011\u0015Yt\u00021\u00013\u0011\u0015it\u00021\u0001-\u0011\u0015\u0001u\u00021\u0001-\u0011\u0015\u0011u\u00021\u0001E\u0011\u0015Au\u00021\u0001K\u0003!!xn\u0015;sS:<G#A0\u0011\u0005\u0001<gBA1f!\t\u0011G'D\u0001d\u0015\t!'&\u0001\u0004=e>|GOP\u0005\u0003MR\na\u0001\u0015:fI\u00164\u0017B\u00015j\u0005\u0019\u0019FO]5oO*\u0011a\rN\u0001\faJ,G-[2u\u00136\u0004H\u000e\u0006\u0002m_B\u0011Q&\\\u0005\u0003]\u0002\u0012\u0001\u0002T3bM:{G-\u001a\u0005\u0006aF\u0001\r!]\u0001\tM\u0016\fG/\u001e:fgB\u0011!/^\u0007\u0002g*\u0011AOI\u0001\u0007Y&t\u0017\r\\4\n\u0005Y\u001c(A\u0002,fGR|'/A\u0007qe\u0016$\u0017n\u0019;CS:tW\r\u001a\u000b\u0005Yf\f\u0019\u0001C\u0003{%\u0001\u000710\u0001\u0004cS:tW\r\u001a\t\u0004gqt\u0018BA?5\u0005\u0015\t%O]1z!\t\u0019t0C\u0002\u0002\u0002Q\u00121!\u00138u\u0011\u001d\t)A\u0005a\u0001\u0003\u000f\taa\u001d9mSR\u001c\b\u0003B\u001a}\u0003\u0013\u00012a\r?E\u00039qW/\u001c#fg\u000e,g\u000eZ1oiN,\u0012A`\u0001\u0010gV\u0014GO]3f)>\u001cFO]5oOR\u0019q,a\u0005\t\u0011\u0005UA\u0003%AA\u0002y\fA\"\u001b8eK:$h)Y2u_J\f\u0011d];ciJ,W\rV8TiJLgn\u001a\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u0004\u0016\u0004}\u0006u1FAA\u0010!\u0011\t\t#a\u000b\u000e\u0005\u0005\r\"\u0002BA\u0013\u0003O\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%B'\u0001\u0006b]:|G/\u0019;j_:LA!!\f\u0002$\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0019M,(\r\u001e:fK\u0012+\u0007\u000f\u001e5\u0002\u000bQ|w\n\u001c3\u0015\t\u0005U\u0012q\b\t\u0005\u0003o\ti$\u0004\u0002\u0002:)\u0019\u00111H'\u0002\u000b5|G-\u001a7\n\u0007=\nI\u0004\u0003\u0004\u0002B]\u0001\rA`\u0001\u0003S\u0012\fA#\\1y'Bd\u0017\u000e\u001e$fCR,(/Z%oI\u0016DH#\u0001@\u0002\u0011\u0011,W\r]\"paf$\u0012\u0001L\u0001\r\u0013:$XM\u001d8bY:{G-\u001a\t\u0003[m\u0019RaGA)\u0003/\u00022aMA*\u0013\r\t)\u0006\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005e\u00131M\u0007\u0003\u00037RA!!\u0018\u0002`\u0005\u0011\u0011n\u001c\u0006\u0003\u0003C\nAA[1wC&!\u0011QMA.\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\ti%A\u0007ta2LG\u000fV8TiJLgn\u001a\u000b\u0006?\u00065\u0014q\u000e\u0005\u0006\u0005v\u0001\r\u0001\u0012\u0005\b\u0003cj\u0002\u0019AA:\u0003\u0011aWM\u001a;\u0011\u0007M\n)(C\u0002\u0002xQ\u0012qAQ8pY\u0016\fg.\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002~A!\u0011qPAC\u001b\t\t\tI\u0003\u0003\u0002\u0004\u0006}\u0013\u0001\u00027b]\u001eLA!a\"\u0002\u0002\n1qJ\u00196fGR\u0004"
)
public class InternalNode extends Node {
   private final double prediction;
   private final double impurity;
   private final double gain;
   private final Node leftChild;
   private final Node rightChild;
   private final Split split;
   private final ImpurityCalculator impurityStats;

   public double prediction() {
      return this.prediction;
   }

   public double impurity() {
      return this.impurity;
   }

   public double gain() {
      return this.gain;
   }

   public Node leftChild() {
      return this.leftChild;
   }

   public Node rightChild() {
      return this.rightChild;
   }

   public Split split() {
      return this.split;
   }

   public ImpurityCalculator impurityStats() {
      return this.impurityStats;
   }

   public String toString() {
      double var10000 = this.prediction();
      return "InternalNode(prediction = " + var10000 + ", impurity = " + this.impurity() + ", split = " + this.split() + ")";
   }

   public LeafNode predictImpl(final Vector features) {
      Node node = this;

      while(node instanceof InternalNode) {
         InternalNode n = (InternalNode)node;
         if (n.split().shouldGoLeft(features)) {
            node = n.leftChild();
         } else {
            node = n.rightChild();
         }
      }

      return (LeafNode)node;
   }

   public LeafNode predictBinned(final int[] binned, final Split[][] splits) {
      Node node = this;

      while(node instanceof InternalNode) {
         InternalNode n = (InternalNode)node;
         int i = n.split().featureIndex();
         if (n.split().shouldGoLeft(binned[i], splits[i])) {
            node = n.leftChild();
         } else {
            node = n.rightChild();
         }
      }

      return (LeafNode)node;
   }

   public int numDescendants() {
      return 2 + this.leftChild().numDescendants() + this.rightChild().numDescendants();
   }

   public String subtreeToString(final int indentFactor) {
      String prefix = .MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), indentFactor);
      return prefix + "If (" + InternalNode$.MODULE$.org$apache$spark$ml$tree$InternalNode$$splitToString(this.split(), true) + ")\n" + this.leftChild().subtreeToString(indentFactor + 1) + prefix + "Else (" + InternalNode$.MODULE$.org$apache$spark$ml$tree$InternalNode$$splitToString(this.split(), false) + ")\n" + this.rightChild().subtreeToString(indentFactor + 1);
   }

   public int subtreeToString$default$1() {
      return 0;
   }

   public int subtreeDepth() {
      return 1 + scala.math.package..MODULE$.max(this.leftChild().subtreeDepth(), this.rightChild().subtreeDepth());
   }

   public org.apache.spark.mllib.tree.model.Node toOld(final int id) {
      scala.Predef..MODULE$.assert((long)id * 2L < 2147483647L, () -> "Decision Tree could not be converted from new to old API since the old API does not support deep trees.");
      return new org.apache.spark.mllib.tree.model.Node(id, new Predict(this.prediction(), this.impurityStats().prob(this.prediction())), this.impurity(), false, new Some(this.split().toOld()), new Some(this.leftChild().toOld(org.apache.spark.mllib.tree.model.Node$.MODULE$.leftChildIndex(id))), new Some(this.rightChild().toOld(org.apache.spark.mllib.tree.model.Node$.MODULE$.rightChildIndex(id))), new Some(new InformationGainStats(this.gain(), this.impurity(), this.leftChild().impurity(), this.rightChild().impurity(), new Predict(this.leftChild().prediction(), (double)0.0F), new Predict(this.rightChild().prediction(), (double)0.0F))));
   }

   public int maxSplitFeatureIndex() {
      return scala.math.package..MODULE$.max(this.split().featureIndex(), scala.math.package..MODULE$.max(this.leftChild().maxSplitFeatureIndex(), this.rightChild().maxSplitFeatureIndex()));
   }

   public Node deepCopy() {
      return new InternalNode(this.prediction(), this.impurity(), this.gain(), this.leftChild().deepCopy(), this.rightChild().deepCopy(), this.split(), this.impurityStats());
   }

   public InternalNode(final double prediction, final double impurity, final double gain, final Node leftChild, final Node rightChild, final Split split, final ImpurityCalculator impurityStats) {
      this.prediction = prediction;
      this.impurity = impurity;
      this.gain = gain;
      this.leftChild = leftChild;
      this.rightChild = rightChild;
      this.split = split;
      this.impurityStats = impurityStats;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
