package org.apache.spark.graphx.impl;

import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Edge$;
import org.apache.spark.graphx.Edge$mcD$sp;
import org.apache.spark.graphx.EdgeTriplet;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.util.collection.OpenHashSet;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

public class EdgePartition$mcD$sp extends EdgePartition {
   public final double[] org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp;

   public EdgePartition withActiveSet(final Iterator iter) {
      return this.withActiveSet$mcD$sp(iter);
   }

   public EdgePartition withActiveSet$mcD$sp(final Iterator iter) {
      OpenHashSet activeSet = new OpenHashSet.mcJ.sp(.MODULE$.apply(Long.TYPE));

      while(iter.hasNext()) {
         activeSet.add$mcJ$sp(BoxesRunTime.unboxToLong(iter.next()));
      }

      return new EdgePartition$mcD$sp(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, new Some(activeSet), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
   }

   public EdgePartition updateVertices(final Iterator iter) {
      return this.updateVertices$mcD$sp(iter);
   }

   public EdgePartition updateVertices$mcD$sp(final Iterator iter) {
      Object newVertexAttrs = this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      System.arraycopy(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, 0, newVertexAttrs, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));

      while(iter.hasNext()) {
         Tuple2 kv = (Tuple2)iter.next();
         scala.runtime.ScalaRunTime..MODULE$.array_update(newVertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$global2local.apply$mcJI$sp(kv._1$mcJ$sp()), kv._2());
      }

      return new EdgePartition$mcD$sp(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, newVertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
   }

   public EdgePartition withoutVertexAttributes(final ClassTag evidence$6) {
      return this.withoutVertexAttributes$mcD$sp(evidence$6);
   }

   public EdgePartition withoutVertexAttributes$mcD$sp(final ClassTag evidence$6) {
      Object newVertexAttrs = evidence$6.newArray(scala.runtime.ScalaRunTime..MODULE$.array_length(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs));
      return new EdgePartition$mcD$sp(this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds, this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds, this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp, this.org$apache$spark$graphx$impl$EdgePartition$$index, this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, newVertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, evidence$6);
   }

   public double attrs(final int pos) {
      return this.attrs$mcD$sp(pos);
   }

   public double attrs$mcD$sp(final int pos) {
      return this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp[pos];
   }

   public EdgePartition reverse() {
      return this.reverse$mcD$sp();
   }

   public EdgePartition reverse$mcD$sp() {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder$mcD$sp(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, this.size(), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);

      for(int i = 0; i < this.size(); ++i) {
         int localSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
         int localDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
         long srcId = this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localSrcId];
         long dstId = this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId];
         double attr = this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp[i];
         builder.add$mcD$sp(dstId, srcId, localDstId, localSrcId, attr);
      }

      return builder.toEdgePartition$mcD$sp();
   }

   public EdgePartition map(final Function1 f, final ClassTag evidence$7) {
      return this.map$mcD$sp(f, evidence$7);
   }

   public EdgePartition map$mcD$sp(final Function1 f, final ClassTag evidence$7) {
      Object newData = evidence$7.newArray(this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp.length);
      Edge edge = new Edge$mcD$sp(Edge$.MODULE$.$lessinit$greater$default$1(), Edge$.MODULE$.$lessinit$greater$default$2(), BoxesRunTime.unboxToDouble(Edge$.MODULE$.$lessinit$greater$default$3()));
      int size = this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp.length;

      for(int i = 0; i < size; ++i) {
         edge.srcId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i));
         edge.dstId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i));
         edge.attr$mcD$sp_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp[i]);
         scala.runtime.ScalaRunTime..MODULE$.array_update(newData, i, f.apply(edge));
      }

      return this.withData(newData, evidence$7);
   }

   public EdgePartition filter(final Function1 epred, final Function2 vpred) {
      return this.filter$mcD$sp(epred, vpred);
   }

   public EdgePartition filter$mcD$sp(final Function1 epred, final Function2 vpred) {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder$mcD$sp(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, ExistingEdgePartitionBuilder$.MODULE$.$lessinit$greater$default$5(), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);

      for(int i = 0; i < this.size(); ++i) {
         int localSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
         int localDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
         EdgeTriplet et = new EdgeTriplet();
         et.srcId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localSrcId]);
         et.dstId_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$local2global[localDstId]);
         et.srcAttr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localSrcId));
         et.dstAttr_$eq(scala.runtime.ScalaRunTime..MODULE$.array_apply(this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, localDstId));
         et.attr$mcD$sp_$eq(this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp[i]);
         if (BoxesRunTime.unboxToBoolean(vpred.apply(BoxesRunTime.boxToLong(et.srcId()), et.srcAttr())) && BoxesRunTime.unboxToBoolean(vpred.apply(BoxesRunTime.boxToLong(et.dstId()), et.dstAttr())) && BoxesRunTime.unboxToBoolean(epred.apply(et))) {
            builder.add$mcD$sp(et.srcId(), et.dstId(), localSrcId, localDstId, et.attr$mcD$sp());
         }
      }

      return builder.toEdgePartition$mcD$sp();
   }

   public void foreach(final Function1 f) {
      this.foreach$mcD$sp(f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      this.iterator().foreach(f);
   }

   public EdgePartition groupEdges(final Function2 merge) {
      return this.groupEdges$mcD$sp(merge);
   }

   public EdgePartition groupEdges$mcD$sp(final Function2 merge) {
      ExistingEdgePartitionBuilder builder = new ExistingEdgePartitionBuilder$mcD$sp(this.org$apache$spark$graphx$impl$EdgePartition$$global2local, this.org$apache$spark$graphx$impl$EdgePartition$$local2global, this.org$apache$spark$graphx$impl$EdgePartition$$vertexAttrs, this.org$apache$spark$graphx$impl$EdgePartition$$activeSet, ExistingEdgePartitionBuilder$.MODULE$.$lessinit$greater$default$5(), this.org$apache$spark$graphx$impl$EdgePartition$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartition$$evidence$2);
      long currSrcId = BoxesRunTime.unboxToLong((Object)null);
      long currDstId = BoxesRunTime.unboxToLong((Object)null);
      int currLocalSrcId = -1;
      int currLocalDstId = -1;
      double currAttr = BoxesRunTime.unboxToDouble((Object)null);

      for(int i = 0; i < this.size(); ++i) {
         if (i > 0 && currSrcId == this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i) && currDstId == this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i)) {
            currAttr = merge.apply$mcDDD$sp(currAttr, this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp[i]);
         } else {
            if (i > 0) {
               builder.add$mcD$sp(currSrcId, currDstId, currLocalSrcId, currLocalDstId, currAttr);
            }

            currSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$srcIds(i);
            currDstId = this.org$apache$spark$graphx$impl$EdgePartition$$dstIds(i);
            currLocalSrcId = this.org$apache$spark$graphx$impl$EdgePartition$$localSrcIds[i];
            currLocalDstId = this.org$apache$spark$graphx$impl$EdgePartition$$localDstIds[i];
            currAttr = this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp[i];
         }
      }

      if (this.size() > 0) {
         builder.add$mcD$sp(currSrcId, currDstId, currLocalSrcId, currLocalDstId, currAttr);
      }

      return builder.toEdgePartition$mcD$sp();
   }

   public EdgePartition$mcD$sp(final int[] localSrcIds, final int[] localDstIds, final double[] data$mcD$sp, final GraphXPrimitiveKeyOpenHashMap index, final GraphXPrimitiveKeyOpenHashMap global2local, final long[] local2global, final Object vertexAttrs, final Option activeSet, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(localSrcIds, localDstIds, data$mcD$sp, index, global2local, local2global, vertexAttrs, activeSet, evidence$1, evidence$2);
      this.org$apache$spark$graphx$impl$EdgePartition$$data$mcD$sp = data$mcD$sp;
      Statics.releaseFence();
   }

   public EdgePartition$mcD$sp(final ClassTag evidence$3, final ClassTag evidence$4) {
      this((int[])null, (int[])null, (double[])null, (GraphXPrimitiveKeyOpenHashMap)null, (GraphXPrimitiveKeyOpenHashMap)null, (long[])null, (Object)null, (Option)null, evidence$3, evidence$4);
   }
}
