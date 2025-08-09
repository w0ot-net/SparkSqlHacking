package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.linalg.Vector;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\r\u001a\u0001e\u0019\u0003\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\t\u0011q\u0002!\u0011!Q\u0001\neB\u0001\"\u0010\u0001\u0003\u0006\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005\u007f!I1\t\u0001BC\u0002\u0013\u0005\u0011\u0004\u0012\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005\u000b\"A!\n\u0001BC\u0002\u0013\u00051\n\u0003\u0005P\u0001\t\u0005\t\u0015!\u0003M\u0011!\u0001\u0006A!b\u0001\n\u0003Y\u0005\u0002C)\u0001\u0005\u0003\u0005\u000b\u0011\u0002'\t\u0011I\u0003!Q1A\u0005\u0002MC\u0001\u0002\u0017\u0001\u0003\u0002\u0003\u0006I\u0001\u0016\u0005\u00073\u0002!\t!\u0007.\t\u000f\u0005\u0004!\u0019!C\u0001E\"1a\r\u0001Q\u0001\n\rDQa\u001a\u0001\u0005\u0002!DQa\u001c\u0001\u0005\u0002ADQ\u0001\u001f\u0001\u0005\u0002eDQ\u0001\u001f\u0001\u0005\nqDq!a\u0002\u0001\t\u0003\tI\u0001\u0003\u0004p\u0001\u0011%\u0011q\u0002\u0005\u0007_\u0002!I!a\u0007\t\r\u0005E\u0002\u0001\"\u0001T\u0005I\u0019E.^:uKJLgn\u001a+sK\u0016tu\u000eZ3\u000b\u0005iY\u0012AC2mkN$XM]5oO*\u0011A$H\u0001\u0006[2d\u0017N\u0019\u0006\u0003=}\tQa\u001d9be.T!\u0001I\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0013aA8sON\u0019\u0001\u0001\n\u0016\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g!\tYCG\u0004\u0002-e9\u0011Q&M\u0007\u0002])\u0011q\u0006M\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq%\u0003\u00024M\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019d%A\u0003j]\u0012,\u00070F\u0001:!\t)#(\u0003\u0002<M\t\u0019\u0011J\u001c;\u0002\r%tG-\u001a=!\u0003\u0011\u0019\u0018N_3\u0016\u0003}\u0002\"!\n!\n\u0005\u00053#\u0001\u0002'p]\u001e\fQa]5{K\u0002\nabY3oi\u0016\u0014x+\u001b;i\u001d>\u0014X.F\u0001F!\t1u)D\u0001\u001a\u0013\tA\u0015D\u0001\bWK\u000e$xN],ji\"tuN]7\u0002\u001f\r,g\u000e^3s/&$\bNT8s[\u0002\nAaY8tiV\tA\n\u0005\u0002&\u001b&\u0011aJ\n\u0002\u0007\t>,(\r\\3\u0002\u000b\r|7\u000f\u001e\u0011\u0002\r!,\u0017n\u001a5u\u0003\u001dAW-[4ii\u0002\n\u0001b\u00195jY\u0012\u0014XM\\\u000b\u0002)B\u0019Q%V,\n\u0005Y3#!B!se\u0006L\bC\u0001$\u0001\u0003%\u0019\u0007.\u001b7ee\u0016t\u0007%\u0001\u0004=S:LGO\u0010\u000b\b/ncVLX0a\u0011\u00159T\u00021\u0001:\u0011\u0015iT\u00021\u0001@\u0011\u0015\u0019U\u00021\u0001F\u0011\u0015QU\u00021\u0001M\u0011\u0015\u0001V\u00021\u0001M\u0011\u0015\u0011V\u00021\u0001U\u0003\u0019I7\u000fT3bMV\t1\r\u0005\u0002&I&\u0011QM\n\u0002\b\u0005>|G.Z1o\u0003\u001dI7\u000fT3bM\u0002\naaY3oi\u0016\u0014X#A5\u0011\u0005)lW\"A6\u000b\u00051\\\u0012A\u00027j]\u0006dw-\u0003\u0002oW\n1a+Z2u_J\fq\u0001\u001d:fI&\u001cG\u000fF\u0002:cNDQA]\tA\u0002%\fQ\u0001]8j]RDQ\u0001^\tA\u0002U\fq\u0002Z5ti\u0006t7-Z'fCN,(/\u001a\t\u0003\rZL!a^\r\u0003\u001f\u0011K7\u000f^1oG\u0016lU-Y:ve\u0016\f1\u0002\u001d:fI&\u001cG\u000fU1uQR\u0019AK_>\t\u000bI\u0014\u0002\u0019A5\t\u000bQ\u0014\u0002\u0019A;\u0015\u000bu\f\t!!\u0002\u0011\u0007-rx+\u0003\u0002\u0000m\t!A*[:u\u0011\u0019\t\u0019a\u0005a\u0001\u000b\u0006i\u0001o\\5oi^KG\u000f\u001b(pe6DQ\u0001^\nA\u0002U\f1bY8naV$XmQ8tiR)A*a\u0003\u0002\u000e!)!\u000f\u0006a\u0001S\")A\u000f\u0006a\u0001kR1\u0011\u0011CA\f\u00033\u0001R!JA\ns1K1!!\u0006'\u0005\u0019!V\u000f\u001d7fe!1\u00111A\u000bA\u0002\u0015CQ\u0001^\u000bA\u0002U$\u0002\"!\u0005\u0002\u001e\u0005}\u0011\u0011\u0005\u0005\u0007\u0003\u00071\u0002\u0019A#\t\u000b)3\u0002\u0019\u0001'\t\u000bQ4\u0002\u0019A;)\u0007Y\t)\u0003\u0005\u0003\u0002(\u00055RBAA\u0015\u0015\r\tYCJ\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0018\u0003S\u0011q\u0001^1jYJ,7-A\u0005mK\u00064gj\u001c3fg\"*\u0001!!\u000e\u0002@A!\u0011qGA\u001e\u001b\t\tIDC\u0002\u0002,uIA!!\u0010\u0002:\t)1+\u001b8dK\u0006\u0012\u0011\u0011I\u0001\u0006c92d\u0006\r"
)
public class ClusteringTreeNode implements Serializable {
   private final int index;
   private final long size;
   private final VectorWithNorm centerWithNorm;
   private final double cost;
   private final double height;
   private final ClusteringTreeNode[] children;
   private final boolean isLeaf;

   public int index() {
      return this.index;
   }

   public long size() {
      return this.size;
   }

   public VectorWithNorm centerWithNorm() {
      return this.centerWithNorm;
   }

   public double cost() {
      return this.cost;
   }

   public double height() {
      return this.height;
   }

   public ClusteringTreeNode[] children() {
      return this.children;
   }

   public boolean isLeaf() {
      return this.isLeaf;
   }

   public Vector center() {
      return this.centerWithNorm().vector();
   }

   public int predict(final Vector point, final DistanceMeasure distanceMeasure) {
      Tuple2 var5 = this.predict(new VectorWithNorm(point), distanceMeasure);
      if (var5 != null) {
         int index = var5._1$mcI$sp();
         return index;
      } else {
         throw new MatchError(var5);
      }
   }

   public ClusteringTreeNode[] predictPath(final Vector point, final DistanceMeasure distanceMeasure) {
      return (ClusteringTreeNode[])this.predictPath(new VectorWithNorm(point), distanceMeasure).toArray(.MODULE$.apply(ClusteringTreeNode.class));
   }

   private List predictPath(final VectorWithNorm pointWithNorm, final DistanceMeasure distanceMeasure) {
      if (this.isLeaf()) {
         return scala.collection.immutable.Nil..MODULE$.$colon$colon(this);
      } else {
         ClusteringTreeNode selected = (ClusteringTreeNode)scala.Predef..MODULE$.wrapRefArray(this.children()).minBy((child) -> BoxesRunTime.boxToDouble($anonfun$predictPath$1(distanceMeasure, pointWithNorm, child)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
         return selected.predictPath(pointWithNorm, distanceMeasure).$colon$colon(selected);
      }
   }

   public double computeCost(final Vector point, final DistanceMeasure distanceMeasure) {
      Tuple2 var7 = this.predict(new VectorWithNorm(point), distanceMeasure);
      if (var7 != null) {
         double cost = var7._2$mcD$sp();
         return cost;
      } else {
         throw new MatchError(var7);
      }
   }

   private Tuple2 predict(final VectorWithNorm pointWithNorm, final DistanceMeasure distanceMeasure) {
      return this.predict(pointWithNorm, distanceMeasure.cost(this.centerWithNorm(), pointWithNorm), distanceMeasure);
   }

   private Tuple2 predict(final VectorWithNorm pointWithNorm, final double cost, final DistanceMeasure distanceMeasure) {
      while(!this.isLeaf()) {
         Tuple2 var8 = (Tuple2)scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.children()), (child) -> new Tuple2(child, BoxesRunTime.boxToDouble(distanceMeasure.cost(child.centerWithNorm(), pointWithNorm))), .MODULE$.apply(Tuple2.class))).minBy((x$10) -> BoxesRunTime.boxToDouble($anonfun$predict$2(x$10)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
         if (var8 == null) {
            throw new MatchError(var8);
         }

         ClusteringTreeNode selectedChild = (ClusteringTreeNode)var8._1();
         double minCost = var8._2$mcD$sp();
         Tuple2 var7 = new Tuple2(selectedChild, BoxesRunTime.boxToDouble(minCost));
         ClusteringTreeNode selectedChild = (ClusteringTreeNode)var7._1();
         double minCost = var7._2$mcD$sp();
         distanceMeasure = distanceMeasure;
         cost = minCost;
         pointWithNorm = pointWithNorm;
         this = selectedChild;
      }

      return new Tuple2.mcID.sp(this.index(), cost);
   }

   public ClusteringTreeNode[] leafNodes() {
      return this.isLeaf() ? (ClusteringTreeNode[])((Object[])(new ClusteringTreeNode[]{this})) : (ClusteringTreeNode[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(this.children()), (x$12) -> x$12.leafNodes(), (xs) -> scala.Predef..MODULE$.wrapRefArray(xs), .MODULE$.apply(ClusteringTreeNode.class));
   }

   // $FF: synthetic method
   public static final double $anonfun$predictPath$1(final DistanceMeasure distanceMeasure$3, final VectorWithNorm pointWithNorm$1, final ClusteringTreeNode child) {
      return distanceMeasure$3.distance(child.centerWithNorm(), pointWithNorm$1);
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$2(final Tuple2 x$10) {
      return x$10._2$mcD$sp();
   }

   public ClusteringTreeNode(final int index, final long size, final VectorWithNorm centerWithNorm, final double cost, final double height, final ClusteringTreeNode[] children) {
      this.index = index;
      this.size = size;
      this.centerWithNorm = centerWithNorm;
      this.cost = cost;
      this.height = height;
      this.children = children;
      this.isLeaf = scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(children));
      scala.Predef..MODULE$.require(this.isLeaf() && index >= 0 || !this.isLeaf() && index < 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
