package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.util.collection.OpenHashMap;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed\u0001C\n\u0015!\u0003\r\t\u0001\u0007\u0010\t\u000b\u0019\u0002A\u0011\u0001\u0015\t\u000b1\u0002a\u0011A\u0017\t\ru\u0002A\u0011\u0001\f?\u0011\u0015!\u0005A\"\u0001F\u0011\u0019Q\u0005\u0001\"\u0001\u0019\u0017\")!\u000b\u0001C!'\")q\f\u0001C\u0001A\"A\u0011\r\u0001EC\u0002\u0013\u0005!\rC\u0003d\u0001\u0011\u0005A\r\u0003\u0004h\u0001\u0011\u0005a\u0003[\u0004\u0007gRA\tA\u0006;\u0007\rM!\u0002\u0012\u0001\fv\u0011\u00151H\u0002\"\u0001x\u0011\u0015AH\u0002\"\u0001z\u0011%\ti\u0001DI\u0001\n\u0003\ty\u0001\u0003\u0004y\u0019\u0011\u0005\u0011\u0011\u0006\u0005\b\u0003\u000fbA\u0011AA%\u0011\u001d\tI\u0007\u0004C\u0001\u0003W\u0012\u0011\u0003\u0016:fK\u0016s7/Z7cY\u0016lu\u000eZ3m\u0015\t)b#\u0001\u0003ue\u0016,'BA\f\u0019\u0003\tiGN\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h+\ty2g\u0005\u0002\u0001AA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002SA\u0011\u0011EK\u0005\u0003W\t\u0012A!\u00168ji\u0006)AO]3fgV\ta\u0006E\u0002\"_EJ!\u0001\r\u0012\u0003\u000b\u0005\u0013(/Y=\u0011\u0005I\u001aD\u0002\u0001\u0003\u0006i\u0001\u0011\r!\u000e\u0002\u0002\u001bF\u0011a'\u000f\t\u0003C]J!\u0001\u000f\u0012\u0003\u000f9{G\u000f[5oOB\u0011!hO\u0007\u0002)%\u0011A\b\u0006\u0002\u0012\t\u0016\u001c\u0017n]5p]R\u0013X-Z'pI\u0016d\u0017aB4fiR\u0013X-\u001a\u000b\u0003c}BQ\u0001Q\u0002A\u0002\u0005\u000b\u0011!\u001b\t\u0003C\tK!a\u0011\u0012\u0003\u0007%sG/A\u0006ue\u0016,w+Z5hQR\u001cX#\u0001$\u0011\u0007\u0005zs\t\u0005\u0002\"\u0011&\u0011\u0011J\t\u0002\u0007\t>,(\r\\3\u0002\u001f)\fg/\u0019+sK\u0016<V-[4iiN,\u0012\u0001\u0014\t\u0003\u001bBk\u0011A\u0014\u0006\u0003\u001fZ\ta\u0001\\5oC2<\u0017BA)O\u0005\u00191Vm\u0019;pe\u0006AAo\\*ue&tw\rF\u0001U!\t)FL\u0004\u0002W5B\u0011qKI\u0007\u00021*\u0011\u0011lJ\u0001\u0007yI|w\u000e\u001e \n\u0005m\u0013\u0013A\u0002)sK\u0012,g-\u0003\u0002^=\n11\u000b\u001e:j]\u001eT!a\u0017\u0012\u0002\u001bQ|G)\u001a2vON#(/\u001b8h+\u0005!\u0016!\u0004;pi\u0006dg*^7O_\u0012,7/F\u0001B\u0003-\u0001(/\u001a3jGRdU-\u00194\u0015\u00051+\u0007\"\u00024\n\u0001\u0004a\u0015\u0001\u00034fCR,(/Z:\u0002\u0019\u001d,G\u000fT3bM\u001aKW\r\u001c3\u0015\u0005%\f\bC\u00016p\u001b\u0005Y'B\u00017n\u0003\u0015!\u0018\u0010]3t\u0015\tq\u0007$A\u0002tc2L!\u0001]6\u0003\u0017M#(/^2u\r&,G\u000e\u001a\u0005\u0006e*\u0001\r\u0001V\u0001\bY\u0016\fgmQ8m\u0003E!&/Z3F]N,WN\u00197f\u001b>$W\r\u001c\t\u0003u1\u0019\"\u0001\u0004\u0011\u0002\rqJg.\u001b;?)\u0005!\u0018A\u00054fCR,(/Z%na>\u0014H/\u00198dKN,\"A\u001f@\u0015\u000b1[x0a\u0001\t\u000b1r\u0001\u0019\u0001?\u0011\u0007\u0005zS\u0010\u0005\u00023}\u0012)AG\u0004b\u0001k!1\u0011\u0011\u0001\bA\u0002\u0005\u000b1B\\;n\r\u0016\fG/\u001e:fg\"I\u0011Q\u0001\b\u0011\u0002\u0003\u0007\u0011qA\u0001\u0015a\u0016\u0014HK]3f\u001d>\u0014X.\u00197ju\u0006$\u0018n\u001c8\u0011\u0007\u0005\nI!C\u0002\u0002\f\t\u0012qAQ8pY\u0016\fg.\u0001\u000fgK\u0006$XO]3J[B|'\u000f^1oG\u0016\u001cH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005E\u0011qE\u000b\u0003\u0003'QC!a\u0002\u0002\u0016-\u0012\u0011q\u0003\t\u0005\u00033\t\u0019#\u0004\u0002\u0002\u001c)!\u0011QDA\u0010\u0003%)hn\u00195fG.,GMC\u0002\u0002\"\t\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)#a\u0007\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u00035\u001f\t\u0007Q'\u0006\u0003\u0002,\u0005\u0005CCBA\u0017\u0003\u0007\n)\u0005F\u0002M\u0003_A\u0011\"!\r\u0011\u0003\u0003\u0005\u001d!a\r\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\u0005\u0004\u00026\u0005m\u0012qH\u0007\u0003\u0003oQ1!!\u000f#\u0003\u001d\u0011XM\u001a7fGRLA!!\u0010\u00028\tA1\t\\1tgR\u000bw\rE\u00023\u0003\u0003\"Q\u0001\u000e\tC\u0002UBa!\u0006\tA\u0002\u0005}\u0002BBA\u0001!\u0001\u0007\u0011)\u0001\rd_6\u0004X\u000f^3GK\u0006$XO]3J[B|'\u000f^1oG\u0016$R!KA&\u0003+Bq!!\u0014\u0012\u0001\u0004\ty%\u0001\u0003o_\u0012,\u0007c\u0001\u001e\u0002R%\u0019\u00111\u000b\u000b\u0003\t9{G-\u001a\u0005\b\u0003/\n\u0002\u0019AA-\u0003-IW\u000e]8si\u0006t7-Z:\u0011\r\u0005m\u0013QM!H\u001b\t\tiF\u0003\u0003\u0002`\u0005\u0005\u0014AC2pY2,7\r^5p]*\u0019\u00111\r\r\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003O\niFA\u0006Pa\u0016t\u0007*Y:i\u001b\u0006\u0004\u0018A\u00058pe6\fG.\u001b>f\u001b\u0006\u0004h+\u00197vKN$2!KA7\u0011\u001d\tyG\u0005a\u0001\u00033\n1!\\1q\u0001"
)
public interface TreeEnsembleModel {
   static void normalizeMapValues(final OpenHashMap map) {
      TreeEnsembleModel$.MODULE$.normalizeMapValues(map);
   }

   static void computeFeatureImportance(final Node node, final OpenHashMap importances) {
      TreeEnsembleModel$.MODULE$.computeFeatureImportance(node, importances);
   }

   static Vector featureImportances(final DecisionTreeModel tree, final int numFeatures, final ClassTag evidence$1) {
      return TreeEnsembleModel$.MODULE$.featureImportances(tree, numFeatures, evidence$1);
   }

   static boolean featureImportances$default$3() {
      return TreeEnsembleModel$.MODULE$.featureImportances$default$3();
   }

   static Vector featureImportances(final DecisionTreeModel[] trees, final int numFeatures, final boolean perTreeNormalization) {
      return TreeEnsembleModel$.MODULE$.featureImportances(trees, numFeatures, perTreeNormalization);
   }

   DecisionTreeModel[] trees();

   // $FF: synthetic method
   static DecisionTreeModel getTree$(final TreeEnsembleModel $this, final int i) {
      return $this.getTree(i);
   }

   default DecisionTreeModel getTree(final int i) {
      return this.trees()[i];
   }

   double[] treeWeights();

   // $FF: synthetic method
   static Vector javaTreeWeights$(final TreeEnsembleModel $this) {
      return $this.javaTreeWeights();
   }

   default Vector javaTreeWeights() {
      return .MODULE$.dense(this.treeWeights());
   }

   // $FF: synthetic method
   static String toString$(final TreeEnsembleModel $this) {
      return $this.toString();
   }

   default String toString() {
      return "TreeEnsembleModel with " + this.trees().length + " trees";
   }

   // $FF: synthetic method
   static String toDebugString$(final TreeEnsembleModel $this) {
      return $this.toDebugString();
   }

   default String toDebugString() {
      String header = this.toString() + "\n";
      return header + scala.collection.ArrayOps..MODULE$.fold$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(this.trees()), scala.Predef..MODULE$.wrapDoubleArray(this.treeWeights()))))), (x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            int treeIndex = x0$1._2$mcI$sp();
            if (var3 != null) {
               DecisionTreeModel tree = (DecisionTreeModel)var3._1();
               double weight = var3._2$mcD$sp();
               return "  Tree " + treeIndex + " (weight " + weight + "):\n" + tree.rootNode().subtreeToString(4);
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))), "", (x$1, x$2) -> x$1 + x$2);
   }

   // $FF: synthetic method
   static int totalNumNodes$(final TreeEnsembleModel $this) {
      return $this.totalNumNodes();
   }

   default int totalNumNodes() {
      return BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.trees()), (x$3) -> BoxesRunTime.boxToInteger($anonfun$totalNumNodes$1(x$3)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   static Vector predictLeaf$(final TreeEnsembleModel $this, final Vector features) {
      return $this.predictLeaf(features);
   }

   default Vector predictLeaf(final Vector features) {
      double[] indices = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.trees()), (x$4) -> BoxesRunTime.boxToDouble($anonfun$predictLeaf$1(features, x$4)), scala.reflect.ClassTag..MODULE$.Double());
      return .MODULE$.dense(indices);
   }

   // $FF: synthetic method
   static StructField getLeafField$(final TreeEnsembleModel $this, final String leafCol) {
      return $this.getLeafField(leafCol);
   }

   default StructField getLeafField(final String leafCol) {
      return (new AttributeGroup(leafCol, (Attribute[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.trees()), (x$5) -> x$5.leafAttr(), scala.reflect.ClassTag..MODULE$.apply(Attribute.class)))).toStructField();
   }

   // $FF: synthetic method
   static int $anonfun$totalNumNodes$1(final DecisionTreeModel x$3) {
      return x$3.numNodes();
   }

   // $FF: synthetic method
   static double $anonfun$predictLeaf$1(final Vector features$1, final DecisionTreeModel x$4) {
      return x$4.predictLeaf(features$1);
   }

   static void $init$(final TreeEnsembleModel $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
