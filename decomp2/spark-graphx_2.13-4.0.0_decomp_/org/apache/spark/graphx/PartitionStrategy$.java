package org.apache.spark.graphx;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class PartitionStrategy$ implements Serializable {
   public static final PartitionStrategy$ MODULE$ = new PartitionStrategy$();

   public PartitionStrategy fromString(final String s) {
      switch (s == null ? 0 : s.hashCode()) {
         case -2009178873:
            if ("CanonicalRandomVertexCut".equals(s)) {
               return PartitionStrategy.CanonicalRandomVertexCut$.MODULE$;
            }
            break;
         case -1836784549:
            if ("RandomVertexCut".equals(s)) {
               return PartitionStrategy.RandomVertexCut$.MODULE$;
            }
            break;
         case 549318784:
            if ("EdgePartition1D".equals(s)) {
               return PartitionStrategy.EdgePartition1D$.MODULE$;
            }
            break;
         case 549318815:
            if ("EdgePartition2D".equals(s)) {
               return PartitionStrategy.EdgePartition2D$.MODULE$;
            }
      }

      throw new IllegalArgumentException("Invalid PartitionStrategy: " + s);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PartitionStrategy$.class);
   }

   private PartitionStrategy$() {
   }
}
