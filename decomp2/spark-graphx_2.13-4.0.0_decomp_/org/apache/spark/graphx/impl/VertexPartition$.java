package org.apache.spark.graphx.impl;

import java.io.Serializable;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.MatchError;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class VertexPartition$ implements Serializable {
   public static final VertexPartition$ MODULE$ = new VertexPartition$();

   public VertexPartition apply(final Iterator iter, final ClassTag evidence$1) {
      Tuple3 var5 = VertexPartitionBase$.MODULE$.initFrom(iter, evidence$1);
      if (var5 != null) {
         OpenHashSet index = (OpenHashSet)var5._1();
         Object values = var5._2();
         BitSet mask = (BitSet)var5._3();
         Tuple3 var4 = new Tuple3(index, values, mask);
         OpenHashSet index = (OpenHashSet)var4._1();
         Object values = var4._2();
         BitSet mask = (BitSet)var4._3();
         return new VertexPartition(index, values, mask, evidence$1);
      } else {
         throw new MatchError(var5);
      }
   }

   public VertexPartitionOps partitionToOps(final VertexPartition partition, final ClassTag evidence$2) {
      return new VertexPartitionOps(partition, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VertexPartition$.class);
   }

   private VertexPartition$() {
   }
}
