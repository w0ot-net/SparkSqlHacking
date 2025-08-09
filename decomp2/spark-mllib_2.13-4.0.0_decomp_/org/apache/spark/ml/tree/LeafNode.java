package org.apache.spark.ml.tree;

import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import org.apache.spark.mllib.tree.model.Predict;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a\u0001\u0002\n\u0014\u0001yA\u0001b\t\u0001\u0003\u0006\u0004%\t\u0005\n\u0005\tW\u0001\u0011\t\u0011)A\u0005K!AA\u0006\u0001BC\u0002\u0013\u0005C\u0005\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003&\u0011%q\u0003A!b\u0001\n\u0003*r\u0006\u0003\u00059\u0001\t\u0005\t\u0015!\u00031\u0011\u0019I\u0004\u0001\"\u0001\u0016u!)q\b\u0001C!\u0001\"1A\n\u0001C!+5CaA\u0016\u0001\u0005BU9\u0006BB4\u0001\t\u0003\u001a\u0002\u000e\u0003\u0004j\u0001\u0011\u00053C\u001b\u0005\t[\u0002\t\n\u0011\"\u0001\u0014]\"1\u0011\u0010\u0001C!'!DaA\u001f\u0001\u0005BUY\b\u0002CA\u0004\u0001\u0011\u0005S#!\u0003\t\u0011\u0005-\u0001\u0001\"\u0011\u0014\u0003\u001b\u0011\u0001\u0002T3bM:{G-\u001a\u0006\u0003)U\tA\u0001\u001e:fK*\u0011acF\u0001\u0003[2T!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001'\t\u0001q\u0004\u0005\u0002!C5\t1#\u0003\u0002#'\t!aj\u001c3f\u0003)\u0001(/\u001a3jGRLwN\\\u000b\u0002KA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1Ai\\;cY\u0016\f1\u0002\u001d:fI&\u001cG/[8oA\u0005A\u0011.\u001c9ve&$\u00180A\u0005j[B,(/\u001b;zA\u0005i\u0011.\u001c9ve&$\u0018p\u0015;biN,\u0012\u0001\r\t\u0003cYj\u0011A\r\u0006\u0003YMR!\u0001\u0006\u001b\u000b\u0005U:\u0012!B7mY&\u0014\u0017BA\u001c3\u0005IIU\u000e];sSRL8)\u00197dk2\fGo\u001c:\u0002\u001d%l\u0007/\u001e:jif\u001cF/\u0019;tA\u00051A(\u001b8jiz\"Ba\u000f\u001f>}A\u0011\u0001\u0005\u0001\u0005\u0006G\u001d\u0001\r!\n\u0005\u0006Y\u001d\u0001\r!\n\u0005\u0006]\u001d\u0001\r\u0001M\u0001\ti>\u001cFO]5oOR\t\u0011\t\u0005\u0002C\u0013:\u00111i\u0012\t\u0003\t\u001ej\u0011!\u0012\u0006\u0003\rv\ta\u0001\u0010:p_Rt\u0014B\u0001%(\u0003\u0019\u0001&/\u001a3fM&\u0011!j\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!;\u0013a\u00039sK\u0012L7\r^%na2$\"a\u000f(\t\u000b=K\u0001\u0019\u0001)\u0002\u0011\u0019,\u0017\r^;sKN\u0004\"!\u0015+\u000e\u0003IS!aU\u000b\u0002\r1Lg.\u00197h\u0013\t)&K\u0001\u0004WK\u000e$xN]\u0001\u000eaJ,G-[2u\u0005&tg.\u001a3\u0015\u0007mB\u0006\rC\u0003Z\u0015\u0001\u0007!,\u0001\u0004cS:tW\r\u001a\t\u0004Mmk\u0016B\u0001/(\u0005\u0015\t%O]1z!\t1c,\u0003\u0002`O\t\u0019\u0011J\u001c;\t\u000b\u0005T\u0001\u0019\u00012\u0002\rM\u0004H.\u001b;t!\r13l\u0019\t\u0004Mm#\u0007C\u0001\u0011f\u0013\t17CA\u0003Ta2LG/\u0001\bok6$Um]2f]\u0012\fg\u000e^:\u0016\u0003u\u000bqb];ciJ,W\rV8TiJLgn\u001a\u000b\u0003\u0003.Dq\u0001\u001c\u0007\u0011\u0002\u0003\u0007Q,\u0001\u0007j]\u0012,g\u000e\u001e$bGR|'/A\rtk\n$(/Z3U_N#(/\u001b8hI\u0011,g-Y;mi\u0012\nT#A8+\u0005u\u00038&A9\u0011\u0005I<X\"A:\u000b\u0005Q,\u0018!C;oG\",7m[3e\u0015\t1x%\u0001\u0006b]:|G/\u0019;j_:L!\u0001_:\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007tk\n$(/Z3EKB$\b.A\u0003u_>cG\rF\u0002}\u0003\u0007\u00012!`A\u0001\u001b\u0005q(BA@4\u0003\u0015iw\u000eZ3m\u0013\t\u0011c\u0010\u0003\u0004\u0002\u0006=\u0001\r!X\u0001\u0003S\u0012\fA#\\1y'Bd\u0017\u000e\u001e$fCR,(/Z%oI\u0016DH#A/\u0002\u0011\u0011,W\r]\"paf$\u0012a\b"
)
public class LeafNode extends Node {
   private final double prediction;
   private final double impurity;
   private final ImpurityCalculator impurityStats;

   public double prediction() {
      return this.prediction;
   }

   public double impurity() {
      return this.impurity;
   }

   public ImpurityCalculator impurityStats() {
      return this.impurityStats;
   }

   public String toString() {
      double var10000 = this.prediction();
      return "LeafNode(prediction = " + var10000 + ", impurity = " + this.impurity() + ")";
   }

   public LeafNode predictImpl(final Vector features) {
      return this;
   }

   public LeafNode predictBinned(final int[] binned, final Split[][] splits) {
      return this;
   }

   public int numDescendants() {
      return 0;
   }

   public String subtreeToString(final int indentFactor) {
      String prefix = .MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(" "), indentFactor);
      return prefix + "Predict: " + this.prediction() + "\n";
   }

   public int subtreeToString$default$1() {
      return 0;
   }

   public int subtreeDepth() {
      return 0;
   }

   public org.apache.spark.mllib.tree.model.Node toOld(final int id) {
      return new org.apache.spark.mllib.tree.model.Node(id, new Predict(this.prediction(), this.impurityStats().prob(this.prediction())), this.impurity(), true, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$);
   }

   public int maxSplitFeatureIndex() {
      return -1;
   }

   public Node deepCopy() {
      return new LeafNode(this.prediction(), this.impurity(), this.impurityStats());
   }

   public LeafNode(final double prediction, final double impurity, final ImpurityCalculator impurityStats) {
      this.prediction = prediction;
      this.impurity = impurity;
      this.impurityStats = impurityStats;
   }
}
