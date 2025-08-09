package org.apache.spark.status;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ui.scope.RDDOperationCluster;
import org.apache.spark.ui.scope.RDDOperationNode;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000593Qa\u0003\u0007\u0001\u001dQA\u0001b\u0007\u0001\u0003\u0006\u0004%\t!\b\u0005\tS\u0001\u0011\t\u0011)A\u0005=!A!\u0006\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005,\u0001\t\u0005\t\u0015!\u0003\u001f\u0011!a\u0003A!b\u0001\n\u0003i\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011u\u0002!Q1A\u0005\u0002yB\u0001B\u0011\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0002\u001b%\u0012#u\n]3sCRLwN\\\"mkN$XM],sCB\u0004XM\u001d\u0006\u0003\u001b9\taa\u001d;biV\u001c(BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0014\u0005\u0001)\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g-\u0001\u0002jI\u000e\u0001Q#\u0001\u0010\u0011\u0005}1cB\u0001\u0011%!\t\ts#D\u0001#\u0015\t\u0019C$\u0001\u0004=e>|GOP\u0005\u0003K]\ta\u0001\u0015:fI\u00164\u0017BA\u0014)\u0005\u0019\u0019FO]5oO*\u0011QeF\u0001\u0004S\u0012\u0004\u0013\u0001\u00028b[\u0016\fQA\\1nK\u0002\n!b\u00195jY\u0012tu\u000eZ3t+\u0005q\u0003cA\u00183i5\t\u0001G\u0003\u00022/\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005M\u0002$aA*fcB\u0011QGO\u0007\u0002m)\u0011q\u0007O\u0001\u0006g\u000e|\u0007/\u001a\u0006\u0003s9\t!!^5\n\u0005m2$\u0001\u0005*E\t>\u0003XM]1uS>tgj\u001c3f\u0003-\u0019\u0007.\u001b7e\u001d>$Wm\u001d\u0011\u0002\u001b\rD\u0017\u000e\u001c3DYV\u001cH/\u001a:t+\u0005y\u0004cA\u00183\u0001B\u0011\u0011\tA\u0007\u0002\u0019\u0005q1\r[5mI\u000ecWo\u001d;feN\u0004\u0013A\u0002\u001fj]&$h\bF\u0003A\u000b\u001a;\u0005\nC\u0003\u001c\u0013\u0001\u0007a\u0004C\u0003+\u0013\u0001\u0007a\u0004C\u0003-\u0013\u0001\u0007a\u0006C\u0003>\u0013\u0001\u0007q(A\u000bu_J#Ei\u00149fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:\u0015\u0003-\u0003\"!\u000e'\n\u000553$a\u0005*E\t>\u0003XM]1uS>t7\t\\;ti\u0016\u0014\b"
)
public class RDDOperationClusterWrapper {
   private final String id;
   private final String name;
   private final Seq childNodes;
   private final Seq childClusters;

   public String id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public Seq childNodes() {
      return this.childNodes;
   }

   public Seq childClusters() {
      return this.childClusters;
   }

   public RDDOperationCluster toRDDOperationCluster() {
      boolean isBarrier = this.childNodes().exists((x$3) -> BoxesRunTime.boxToBoolean($anonfun$toRDDOperationCluster$1(x$3)));
      String name = isBarrier ? this.name() + "\n(barrier mode)" : this.name();
      RDDOperationCluster cluster = new RDDOperationCluster(this.id(), isBarrier, name);
      this.childNodes().foreach((childNode) -> {
         $anonfun$toRDDOperationCluster$2(cluster, childNode);
         return BoxedUnit.UNIT;
      });
      this.childClusters().foreach((child) -> {
         $anonfun$toRDDOperationCluster$3(cluster, child);
         return BoxedUnit.UNIT;
      });
      return cluster;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toRDDOperationCluster$1(final RDDOperationNode x$3) {
      return x$3.barrier();
   }

   // $FF: synthetic method
   public static final void $anonfun$toRDDOperationCluster$2(final RDDOperationCluster cluster$1, final RDDOperationNode childNode) {
      cluster$1.attachChildNode(childNode);
   }

   // $FF: synthetic method
   public static final void $anonfun$toRDDOperationCluster$3(final RDDOperationCluster cluster$1, final RDDOperationClusterWrapper child) {
      cluster$1.attachChildCluster(child.toRDDOperationCluster());
   }

   public RDDOperationClusterWrapper(final String id, final String name, final Seq childNodes, final Seq childClusters) {
      this.id = id;
      this.name = name;
      this.childNodes = childNodes;
      this.childClusters = childClusters;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
