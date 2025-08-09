package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Edge$;
import org.apache.spark.graphx.Edge$mcI$sp;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap$mcJI$sp;
import org.apache.spark.util.collection.PrimitiveVector;
import org.apache.spark.util.collection.Sorter;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public class EdgePartitionBuilder$mcI$sp extends EdgePartitionBuilder {
   public final PrimitiveVector edges$mcI$sp;

   public void add(final long src, final long dst, final int d) {
      this.add$mcI$sp(src, dst, d);
   }

   public void add$mcI$sp(final long src, final long dst, final int d) {
      this.edges$mcI$sp.$plus$eq(new Edge$mcI$sp(src, dst, d));
   }

   public EdgePartition toEdgePartition() {
      return this.toEdgePartition$mcI$sp();
   }

   public EdgePartition toEdgePartition$mcI$sp() {
      Edge[] edgeArray = (Edge[])this.edges$mcI$sp.trim().array();
      (new Sorter(Edge$.MODULE$.edgeArraySortDataFormat())).sort(edgeArray, 0, edgeArray.length, Edge$.MODULE$.lexicographicOrdering());
      int[] localSrcIds = new int[edgeArray.length];
      int[] localDstIds = new int[edgeArray.length];
      int[] data = (int[])this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$1.newArray(edgeArray.length);
      GraphXPrimitiveKeyOpenHashMap index = new GraphXPrimitiveKeyOpenHashMap$mcJI$sp(.MODULE$.apply(Long.TYPE), .MODULE$.Int());
      GraphXPrimitiveKeyOpenHashMap global2local = new GraphXPrimitiveKeyOpenHashMap$mcJI$sp(.MODULE$.apply(Long.TYPE), .MODULE$.Int());
      PrimitiveVector local2global = new PrimitiveVector.mcJ.sp(org.apache.spark.util.collection.PrimitiveVector..MODULE$.$lessinit$greater$default$1(), .MODULE$.apply(Long.TYPE));
      Object vertexAttrs = scala.Array..MODULE$.empty(this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2);
      if (edgeArray.length > 0) {
         index.update$mcJI$sp(edgeArray[0].srcId(), 0);
         long currSrcId = edgeArray[0].srcId();
         IntRef currLocalId = IntRef.create(-1);

         for(int i = 0; i < edgeArray.length; ++i) {
            long srcId = edgeArray[i].srcId();
            long dstId = edgeArray[i].dstId();
            localSrcIds[i] = global2local.changeValue$mcJI$sp(srcId, (JFunction0.mcI.sp)() -> {
               ++currLocalId.elem;
               local2global.$plus$eq$mcJ$sp(srcId);
               return currLocalId.elem;
            }, (JFunction1.mcII.sp)(x) -> BoxesRunTime.unboxToInt(scala.Predef..MODULE$.identity(BoxesRunTime.boxToInteger(x))));
            localDstIds[i] = global2local.changeValue$mcJI$sp(dstId, (JFunction0.mcI.sp)() -> {
               ++currLocalId.elem;
               local2global.$plus$eq$mcJ$sp(dstId);
               return currLocalId.elem;
            }, (JFunction1.mcII.sp)(x) -> BoxesRunTime.unboxToInt(scala.Predef..MODULE$.identity(BoxesRunTime.boxToInteger(x))));
            data[i] = edgeArray[i].attr$mcI$sp();
            if (srcId != currSrcId) {
               currSrcId = srcId;
               index.update$mcJI$sp(srcId, i);
            }
         }

         vertexAttrs = this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2.newArray(currLocalId.elem + 1);
      }

      return new EdgePartition$mcI$sp(localSrcIds, localDstIds, data, index, global2local, local2global.trim$mcJ$sp().array$mcJ$sp(), vertexAttrs, scala.None..MODULE$, this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2);
   }

   public EdgePartitionBuilder$mcI$sp(final int size, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(size, evidence$1, evidence$2);
      this.edges$mcI$sp = new PrimitiveVector(size, .MODULE$.apply(Edge.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
