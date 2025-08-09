package org.apache.spark.graphx.impl;

import java.io.Serializable;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import scala.runtime.ModuleSerializationProxy;

public final class VertexRDDImpl$ implements Serializable {
   public static final VertexRDDImpl$ MODULE$ = new VertexRDDImpl$();

   public StorageLevel $lessinit$greater$default$2() {
      return .MODULE$.MEMORY_ONLY();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(VertexRDDImpl$.class);
   }

   private VertexRDDImpl$() {
   }
}
