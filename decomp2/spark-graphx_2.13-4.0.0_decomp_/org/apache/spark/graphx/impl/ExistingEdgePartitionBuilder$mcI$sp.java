package org.apache.spark.graphx.impl;

import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap$mcJI$sp;
import org.apache.spark.util.collection.PrimitiveVector;
import org.apache.spark.util.collection.Sorter;
import scala.Option;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;

public class ExistingEdgePartitionBuilder$mcI$sp extends ExistingEdgePartitionBuilder {
   public final PrimitiveVector edges$mcI$sp;

   public void add(final long src, final long dst, final int localSrc, final int localDst, final int d) {
      this.add$mcI$sp(src, dst, localSrc, localDst, d);
   }

   public void add$mcI$sp(final long src, final long dst, final int localSrc, final int localDst, final int d) {
      this.edges$mcI$sp.$plus$eq(new EdgeWithLocalIds$mcI$sp(src, dst, localSrc, localDst, d));
   }

   public EdgePartition toEdgePartition() {
      return this.toEdgePartition$mcI$sp();
   }

   public EdgePartition toEdgePartition$mcI$sp() {
      EdgeWithLocalIds[] edgeArray = (EdgeWithLocalIds[])this.edges$mcI$sp.trim().array();
      (new Sorter(EdgeWithLocalIds$.MODULE$.edgeArraySortDataFormat())).sort(edgeArray, 0, edgeArray.length, EdgeWithLocalIds$.MODULE$.lexicographicOrdering());
      int[] localSrcIds = new int[edgeArray.length];
      int[] localDstIds = new int[edgeArray.length];
      int[] data = (int[])this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$3.newArray(edgeArray.length);
      GraphXPrimitiveKeyOpenHashMap index = new GraphXPrimitiveKeyOpenHashMap$mcJI$sp(.MODULE$.apply(Long.TYPE), .MODULE$.Int());
      if (edgeArray.length > 0) {
         index.update$mcJI$sp(edgeArray[0].srcId(), 0);
         long currSrcId = edgeArray[0].srcId();

         for(int i = 0; i < edgeArray.length; ++i) {
            localSrcIds[i] = edgeArray[i].localSrcId();
            localDstIds[i] = edgeArray[i].localDstId();
            data[i] = edgeArray[i].attr$mcI$sp();
            if (edgeArray[i].srcId() != currSrcId) {
               currSrcId = edgeArray[i].srcId();
               index.update$mcJI$sp(currSrcId, i);
            }
         }
      }

      return new EdgePartition$mcI$sp(localSrcIds, localDstIds, data, index, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$global2local, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$local2global, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$vertexAttrs, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$activeSet, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$3, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$4);
   }

   public ExistingEdgePartitionBuilder$mcI$sp(final GraphXPrimitiveKeyOpenHashMap global2local, final long[] local2global, final Object vertexAttrs, final Option activeSet, final int size, final ClassTag evidence$3, final ClassTag evidence$4) {
      super(global2local, local2global, vertexAttrs, activeSet, size, evidence$3, evidence$4);
      this.edges$mcI$sp = new PrimitiveVector(size, .MODULE$.apply(EdgeWithLocalIds.class));
   }
}
