package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.types.StructField;
import scala.MatchError;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001C\b\u0011!\u0003\r\t\u0001\u0006\u000e\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002a\u0011\u0001\u0015\t\u000b5\u0002A\u0011\u0001\u0018\t\u0011I\u0002\u0001R1A\u0005\u00029BQa\r\u0001\u0005BQBQ\u0001\u0011\u0001\u0005\u0002\u0005CaA\u0011\u0001\u0005\u0002I\u0019\u0005B\u0002#\u0001\r\u0003!R\tC\u0003O\u0001\u0011%q\nC\u0005_\u0001!\u0015\r\u0011\"\u0001\u0013]!Iq\f\u0001EC\u0002\u0013\u0005!\u0003\u0019\u0005\u0007O\u0002!\tA\u00055\t\u0011M\u0004\u0001R1A\u0005\nQDQ\u0001 \u0001\u0005\u0002u\u0014\u0011\u0003R3dSNLwN\u001c+sK\u0016lu\u000eZ3m\u0015\t\t\"#\u0001\u0003ue\u0016,'BA\n\u0015\u0003\tiGN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h'\t\u00011\u0004\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0005\u0005\u0002\u001dK%\u0011a%\b\u0002\u0005+:LG/\u0001\u0005s_>$hj\u001c3f+\u0005I\u0003C\u0001\u0016,\u001b\u0005\u0001\u0012B\u0001\u0017\u0011\u0005\u0011qu\u000eZ3\u0002\u00119,XNT8eKN,\u0012a\f\t\u00039AJ!!M\u000f\u0003\u0007%sG/A\u0003eKB$\b.\u0001\u0005u_N#(/\u001b8h)\u0005)\u0004C\u0001\u001c>\u001d\t94\b\u0005\u00029;5\t\u0011H\u0003\u0002;E\u00051AH]8pizJ!\u0001P\u000f\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003yu\tQ\u0002^8EK\n,xm\u0015;sS:<W#A\u001b\u0002)5\f\u0007p\u00159mSR4U-\u0019;ve\u0016Le\u000eZ3y)\u0005y\u0013!\u0002;p\u001f2$W#\u0001$\u0011\u0005\u001dkU\"\u0001%\u000b\u0005%S\u0015!B7pI\u0016d'BA\tL\u0015\taE#A\u0003nY2L'-\u0003\u0002\u0010\u0011\u0006aA.Z1g\u0013R,'/\u0019;peR\u0011\u0001\u000b\u0018\t\u0004#ZKfB\u0001*U\u001d\tA4+C\u0001\u001f\u0013\t)V$A\u0004qC\u000e\\\u0017mZ3\n\u0005]C&\u0001C%uKJ\fGo\u001c:\u000b\u0005Uk\u0002C\u0001\u0016[\u0013\tY\u0006C\u0001\u0005MK\u00064gj\u001c3f\u0011\u0015i\u0016\u00021\u0001*\u0003\u0011qw\u000eZ3\u0002\u00119,X\u000eT3bm\u0016\f\u0001\u0002\\3bM\u0006#HO]\u000b\u0002CB\u0011!-Z\u0007\u0002G*\u0011AME\u0001\nCR$(/\u001b2vi\u0016L!AZ2\u0003!9{W.\u001b8bY\u0006#HO]5ckR,\u0017\u0001D4fi2+\u0017M\u001a$jK2$GCA5r!\tQw.D\u0001l\u0015\taW.A\u0003usB,7O\u0003\u0002o)\u0005\u00191/\u001d7\n\u0005A\\'aC*ueV\u001cGOR5fY\u0012DQA\u001d\u0007A\u0002U\nq\u0001\\3bM\u000e{G.A\u0006mK\u00064\u0017J\u001c3jG\u0016\u001cX#A;\u0011\tY2\u0018lL\u0005\u0003o~\u00121!T1qQ\ti\u0011\u0010\u0005\u0002\u001du&\u001110\b\u0002\niJ\fgn]5f]R\f1\u0002\u001d:fI&\u001cG\u000fT3bMR\u0019a0a\u0001\u0011\u0005qy\u0018bAA\u0001;\t1Ai\\;cY\u0016Dq!!\u0002\u000f\u0001\u0004\t9!\u0001\u0005gK\u0006$XO]3t!\u0011\tI!a\u0004\u000e\u0005\u0005-!bAA\u0007%\u00051A.\u001b8bY\u001eLA!!\u0005\u0002\f\t1a+Z2u_J\u0004"
)
public interface DecisionTreeModel {
   Node rootNode();

   // $FF: synthetic method
   static int numNodes$(final DecisionTreeModel $this) {
      return $this.numNodes();
   }

   default int numNodes() {
      return 1 + this.rootNode().numDescendants();
   }

   // $FF: synthetic method
   static int depth$(final DecisionTreeModel $this) {
      return $this.depth();
   }

   default int depth() {
      return this.rootNode().subtreeDepth();
   }

   // $FF: synthetic method
   static String toString$(final DecisionTreeModel $this) {
      return $this.toString();
   }

   default String toString() {
      int var10000 = this.depth();
      return "DecisionTreeModel of depth " + var10000 + " with " + this.numNodes() + " nodes";
   }

   // $FF: synthetic method
   static String toDebugString$(final DecisionTreeModel $this) {
      return $this.toDebugString();
   }

   default String toDebugString() {
      String header = this.toString() + "\n";
      return header + this.rootNode().subtreeToString(2);
   }

   // $FF: synthetic method
   static int maxSplitFeatureIndex$(final DecisionTreeModel $this) {
      return $this.maxSplitFeatureIndex();
   }

   default int maxSplitFeatureIndex() {
      return this.rootNode().maxSplitFeatureIndex();
   }

   org.apache.spark.mllib.tree.model.DecisionTreeModel toOld();

   private Iterator leafIterator(final Node node) {
      if (node instanceof LeafNode var4) {
         return .MODULE$.Iterator().single(var4);
      } else if (node instanceof InternalNode var5) {
         return this.leafIterator(var5.leftChild()).$plus$plus(() -> this.leafIterator(var5.rightChild()));
      } else {
         throw new MatchError(node);
      }
   }

   // $FF: synthetic method
   static int numLeave$(final DecisionTreeModel $this) {
      return $this.numLeave();
   }

   default int numLeave() {
      return this.leafIterator(this.rootNode()).size();
   }

   // $FF: synthetic method
   static NominalAttribute leafAttr$(final DecisionTreeModel $this) {
      return $this.leafAttr();
   }

   default NominalAttribute leafAttr() {
      return NominalAttribute$.MODULE$.defaultAttr().withNumValues(this.numLeave());
   }

   // $FF: synthetic method
   static StructField getLeafField$(final DecisionTreeModel $this, final String leafCol) {
      return $this.getLeafField(leafCol);
   }

   default StructField getLeafField(final String leafCol) {
      return this.leafAttr().withName(leafCol).toStructField();
   }

   // $FF: synthetic method
   static Map org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices$(final DecisionTreeModel $this) {
      return $this.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices();
   }

   default Map org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices() {
      return this.leafIterator(this.rootNode()).zipWithIndex().toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static double predictLeaf$(final DecisionTreeModel $this, final Vector features) {
      return $this.predictLeaf(features);
   }

   default double predictLeaf(final Vector features) {
      return (double)BoxesRunTime.unboxToInt(this.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices().apply(this.rootNode().predictImpl(features)));
   }

   static void $init$(final DecisionTreeModel $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
