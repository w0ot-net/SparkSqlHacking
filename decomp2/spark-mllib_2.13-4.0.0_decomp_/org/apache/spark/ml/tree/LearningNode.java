package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.tree.model.ImpurityStats;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g!\u0002\u0014(\u0001\u001d\n\u0004\u0002C#\u0001\u0005\u0003\u0007I\u0011\u0001$\t\u0011)\u0003!\u00111A\u0005\u0002-C\u0001\"\u0015\u0001\u0003\u0002\u0003\u0006Ka\u0012\u0005\t%\u0002\u0011\t\u0019!C\u0001'\"A\u0011\f\u0001BA\u0002\u0013\u0005!\f\u0003\u0005]\u0001\t\u0005\t\u0015)\u0003U\u0011!i\u0006A!a\u0001\n\u0003\u0019\u0006\u0002\u00030\u0001\u0005\u0003\u0007I\u0011A0\t\u0011\u0005\u0004!\u0011!Q!\nQC\u0001B\u0019\u0001\u0003\u0002\u0004%\ta\u0019\u0005\tQ\u0002\u0011\t\u0019!C\u0001S\"A1\u000e\u0001B\u0001B\u0003&A\r\u0003\u0005m\u0001\t\u0005\r\u0011\"\u0001n\u0011!\t\bA!a\u0001\n\u0003\u0011\b\u0002\u0003;\u0001\u0005\u0003\u0005\u000b\u0015\u00028\t\u0011U\u0004!\u00111A\u0005\u0002YD!\"!\u0001\u0001\u0005\u0003\u0007I\u0011AA\u0002\u0011%\t9\u0001\u0001B\u0001B\u0003&q\u000fC\u0004\u0002\n\u0001!\t!a\u0003\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\u001c!9\u0011\u0011\u0004\u0001\u0005\u0002\u0005\r\u0002\"CA\u0015\u0001E\u0005I\u0011AA\u0016\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007:\u0001\"a\u0016(\u0011\u00039\u0013\u0011\f\u0004\bM\u001dB\taJA.\u0011\u001d\tI!\u0007C\u0001\u0003WBq!!\u001c\u001a\t\u0003\ty\u0007C\u0004\u0002xe!\t!!\u001f\t\u000f\u0005}\u0014\u0004\"\u0001\u0002\u0002\"9\u0011QQ\r\u0005\u0002\u0005\u001d\u0005bBAF3\u0011\u0005\u0011Q\u0012\u0005\b\u0003#KB\u0011AAJ\u0011\u001d\t9*\u0007C\u0001\u00033Cq!!(\u001a\t\u0003\ty\nC\u0004\u0002&f!\t!a*\t\u000f\u0005-\u0016\u0004\"\u0001\u0002.\"I\u0011QW\r\u0002\u0002\u0013%\u0011q\u0017\u0002\r\u0019\u0016\f'O\\5oO:{G-\u001a\u0006\u0003Q%\nA\u0001\u001e:fK*\u0011!fK\u0001\u0003[2T!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\n\u0004\u0001IB\u0004CA\u001a7\u001b\u0005!$\"A\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]\"$AB!osJ+g\r\u0005\u0002:\u0005:\u0011!\b\u0011\b\u0003w}j\u0011\u0001\u0010\u0006\u0003{y\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002k%\u0011\u0011\tN\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019EI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Bi\u0005\u0011\u0011\u000eZ\u000b\u0002\u000fB\u00111\u0007S\u0005\u0003\u0013R\u00121!\u00138u\u0003\u0019IGm\u0018\u0013fcR\u0011Aj\u0014\t\u0003g5K!A\u0014\u001b\u0003\tUs\u0017\u000e\u001e\u0005\b!\n\t\t\u00111\u0001H\u0003\rAH%M\u0001\u0004S\u0012\u0004\u0013!\u00037fMR\u001c\u0005.\u001b7e+\u0005!\u0006cA\u001aV/&\u0011a\u000b\u000e\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005a\u0003Q\"A\u0014\u0002\u001b1,g\r^\"iS2$w\fJ3r)\ta5\fC\u0004Q\u000b\u0005\u0005\t\u0019\u0001+\u0002\u00151,g\r^\"iS2$\u0007%\u0001\u0006sS\u001eDGo\u00115jY\u0012\faB]5hQR\u001c\u0005.\u001b7e?\u0012*\u0017\u000f\u0006\u0002MA\"9\u0001\u000bCA\u0001\u0002\u0004!\u0016a\u0003:jO\"$8\t[5mI\u0002\nQa\u001d9mSR,\u0012\u0001\u001a\t\u0004gU+\u0007C\u0001-g\u0013\t9wEA\u0003Ta2LG/A\u0005ta2LGo\u0018\u0013fcR\u0011AJ\u001b\u0005\b!.\t\t\u00111\u0001e\u0003\u0019\u0019\b\u000f\\5uA\u00051\u0011n\u001d'fC\u001a,\u0012A\u001c\t\u0003g=L!\u0001\u001d\u001b\u0003\u000f\t{w\u000e\\3b]\u0006Q\u0011n\u001d'fC\u001a|F%Z9\u0015\u00051\u001b\bb\u0002)\u000f\u0003\u0003\u0005\rA\\\u0001\bSNdU-\u00194!\u0003\u0015\u0019H/\u0019;t+\u00059\bC\u0001=\u007f\u001b\u0005I(B\u0001>|\u0003\u0015iw\u000eZ3m\u0015\tACP\u0003\u0002~W\u0005)Q\u000e\u001c7jE&\u0011q0\u001f\u0002\u000e\u00136\u0004XO]5usN#\u0018\r^:\u0002\u0013M$\u0018\r^:`I\u0015\fHc\u0001'\u0002\u0006!9\u0001+EA\u0001\u0002\u00049\u0018AB:uCR\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u000e/\u00065\u0011qBA\t\u0003'\t)\"a\u0006\t\u000b\u0015\u001b\u0002\u0019A$\t\u000bI\u001b\u0002\u0019\u0001+\t\u000bu\u001b\u0002\u0019\u0001+\t\u000b\t\u001c\u0002\u0019\u00013\t\u000b1\u001c\u0002\u0019\u00018\t\u000bU\u001c\u0002\u0019A<\u0002\rQ|gj\u001c3f+\t\ti\u0002E\u0002Y\u0003?I1!!\t(\u0005\u0011qu\u000eZ3\u0015\t\u0005u\u0011Q\u0005\u0005\t\u0003O)\u0002\u0013!a\u0001]\u0006)\u0001O];oK\u0006\u0001Bo\u001c(pI\u0016$C-\u001a4bk2$H%M\u000b\u0003\u0003[Q3A\\A\u0018W\t\t\t\u0004\u0005\u0003\u00024\u0005uRBAA\u001b\u0015\u0011\t9$!\u000f\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001ei\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005}\u0012Q\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017a\u00039sK\u0012L7\r^%na2$RaRA#\u0003\u001fBq!a\u0012\u0018\u0001\u0004\tI%\u0001\bcS:tW\r\u001a$fCR,(/Z:\u0011\tM\nYeR\u0005\u0004\u0003\u001b\"$!B!se\u0006L\bbBA)/\u0001\u0007\u00111K\u0001\u0007gBd\u0017\u000e^:\u0011\u000bM\nY%!\u0016\u0011\tM\nY%Z\u0001\r\u0019\u0016\f'O\\5oO:{G-\u001a\t\u00031f\u0019B!\u0007\u001a\u0002^A!\u0011qLA5\u001b\t\t\tG\u0003\u0003\u0002d\u0005\u0015\u0014AA5p\u0015\t\t9'\u0001\u0003kCZ\f\u0017bA\"\u0002bQ\u0011\u0011\u0011L\u0001\u0006CB\u0004H.\u001f\u000b\b/\u0006E\u00141OA;\u0011\u0015)5\u00041\u0001H\u0011\u0015a7\u00041\u0001o\u0011\u0015)8\u00041\u0001x\u0003%)W\u000e\u001d;z\u001d>$W\rF\u0002X\u0003wBa!! \u001d\u0001\u00049\u0015!\u00038pI\u0016Le\u000eZ3y\u00039aWM\u001a;DQ&dG-\u00138eKb$2aRAB\u0011\u0019\ti(\ba\u0001\u000f\u0006y!/[4ii\u000eC\u0017\u000e\u001c3J]\u0012,\u0007\u0010F\u0002H\u0003\u0013Ca!! \u001f\u0001\u00049\u0015a\u00039be\u0016tG/\u00138eKb$2aRAH\u0011\u0019\tih\ba\u0001\u000f\u0006a\u0011N\u001c3fqR{G*\u001a<fYR\u0019q)!&\t\r\u0005u\u0004\u00051\u0001H\u0003-I7\u000fT3gi\u000eC\u0017\u000e\u001c3\u0015\u00079\fY\n\u0003\u0004\u0002~\u0005\u0002\raR\u0001\u0010[\u0006Dhj\u001c3fg&sG*\u001a<fYR\u0019q)!)\t\r\u0005\r&\u00051\u0001H\u0003\u0015aWM^3m\u0003E\u0019H/\u0019:u\u0013:$W\r_%o\u0019\u00164X\r\u001c\u000b\u0004\u000f\u0006%\u0006BBARG\u0001\u0007q)A\u0004hKRtu\u000eZ3\u0015\u000b]\u000by+!-\t\r\u0005uD\u00051\u0001H\u0011\u0019\t\u0019\f\na\u0001/\u0006A!o\\8u\u001d>$W-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002:B!\u00111XAa\u001b\t\tiL\u0003\u0003\u0002@\u0006\u0015\u0014\u0001\u00027b]\u001eLA!a1\u0002>\n1qJ\u00196fGR\u0004"
)
public class LearningNode implements Serializable {
   private int id;
   private Option leftChild;
   private Option rightChild;
   private Option split;
   private boolean isLeaf;
   private ImpurityStats stats;

   public static LearningNode getNode(final int nodeIndex, final LearningNode rootNode) {
      return LearningNode$.MODULE$.getNode(nodeIndex, rootNode);
   }

   public static int startIndexInLevel(final int level) {
      return LearningNode$.MODULE$.startIndexInLevel(level);
   }

   public static int maxNodesInLevel(final int level) {
      return LearningNode$.MODULE$.maxNodesInLevel(level);
   }

   public static boolean isLeftChild(final int nodeIndex) {
      return LearningNode$.MODULE$.isLeftChild(nodeIndex);
   }

   public static int indexToLevel(final int nodeIndex) {
      return LearningNode$.MODULE$.indexToLevel(nodeIndex);
   }

   public static int parentIndex(final int nodeIndex) {
      return LearningNode$.MODULE$.parentIndex(nodeIndex);
   }

   public static int rightChildIndex(final int nodeIndex) {
      return LearningNode$.MODULE$.rightChildIndex(nodeIndex);
   }

   public static int leftChildIndex(final int nodeIndex) {
      return LearningNode$.MODULE$.leftChildIndex(nodeIndex);
   }

   public static LearningNode emptyNode(final int nodeIndex) {
      return LearningNode$.MODULE$.emptyNode(nodeIndex);
   }

   public static LearningNode apply(final int id, final boolean isLeaf, final ImpurityStats stats) {
      return LearningNode$.MODULE$.apply(id, isLeaf, stats);
   }

   public int id() {
      return this.id;
   }

   public void id_$eq(final int x$1) {
      this.id = x$1;
   }

   public Option leftChild() {
      return this.leftChild;
   }

   public void leftChild_$eq(final Option x$1) {
      this.leftChild = x$1;
   }

   public Option rightChild() {
      return this.rightChild;
   }

   public void rightChild_$eq(final Option x$1) {
      this.rightChild = x$1;
   }

   public Option split() {
      return this.split;
   }

   public void split_$eq(final Option x$1) {
      this.split = x$1;
   }

   public boolean isLeaf() {
      return this.isLeaf;
   }

   public void isLeaf_$eq(final boolean x$1) {
      this.isLeaf = x$1;
   }

   public ImpurityStats stats() {
      return this.stats;
   }

   public void stats_$eq(final ImpurityStats x$1) {
      this.stats = x$1;
   }

   public Node toNode() {
      return this.toNode(true);
   }

   public Node toNode(final boolean prune) {
      if (this.leftChild().isEmpty() && this.rightChild().isEmpty()) {
         return this.stats().valid() ? new LeafNode(this.stats().impurityCalculator().predict(), this.stats().impurity(), this.stats().impurityCalculator()) : new LeafNode(this.stats().impurityCalculator().predict(), (double)-1.0F, this.stats().impurityCalculator());
      } else {
         .MODULE$.assert(this.leftChild().nonEmpty() && this.rightChild().nonEmpty() && this.split().nonEmpty() && this.stats() != null, () -> "Unknown error during Decision Tree learning.  Could not convert LearningNode to Node.");
         Tuple2 var3 = new Tuple2(((LearningNode)this.leftChild().get()).toNode(prune), ((LearningNode)this.rightChild().get()).toNode(prune));
         if (var3 != null) {
            Node l = (Node)var3._1();
            Node r = (Node)var3._2();
            if (l instanceof LeafNode) {
               LeafNode var6 = (LeafNode)l;
               if (r instanceof LeafNode) {
                  LeafNode var7 = (LeafNode)r;
                  if (prune && var6.prediction() == var7.prediction()) {
                     return new LeafNode(var6.prediction(), this.stats().impurity(), this.stats().impurityCalculator());
                  }
               }
            }
         }

         if (var3 != null) {
            Node l = (Node)var3._1();
            Node r = (Node)var3._2();
            return new InternalNode(this.stats().impurityCalculator().predict(), this.stats().impurity(), this.stats().gain(), l, r, (Split)this.split().get(), this.stats().impurityCalculator());
         } else {
            throw new MatchError(var3);
         }
      }
   }

   public boolean toNode$default$1() {
      return true;
   }

   public int predictImpl(final int[] binnedFeatures, final Split[][] splits) {
      LearningNode node = this;

      while(!node.isLeaf() && node.split().nonEmpty()) {
         Split split = (Split)node.split().get();
         int featureIndex = split.featureIndex();
         boolean splitLeft = split.shouldGoLeft(binnedFeatures[featureIndex], splits[featureIndex]);
         if (node.leftChild().isEmpty()) {
            if (splitLeft) {
               return LearningNode$.MODULE$.leftChildIndex(node.id());
            }

            return LearningNode$.MODULE$.rightChildIndex(node.id());
         }

         if (splitLeft) {
            node = (LearningNode)node.leftChild().get();
         } else {
            node = (LearningNode)node.rightChild().get();
         }
      }

      return node.id();
   }

   public LearningNode(final int id, final Option leftChild, final Option rightChild, final Option split, final boolean isLeaf, final ImpurityStats stats) {
      this.id = id;
      this.leftChild = leftChild;
      this.rightChild = rightChild;
      this.split = split;
      this.isLeaf = isLeaf;
      this.stats = stats;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
