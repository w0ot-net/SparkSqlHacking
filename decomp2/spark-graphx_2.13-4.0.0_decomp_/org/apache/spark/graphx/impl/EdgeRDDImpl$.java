package org.apache.spark.graphx.impl;

import java.io.Serializable;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import scala.runtime.ModuleSerializationProxy;

public final class EdgeRDDImpl$ implements Serializable {
   public static final EdgeRDDImpl$ MODULE$ = new EdgeRDDImpl$();

   public StorageLevel $lessinit$greater$default$2() {
      return .MODULE$.MEMORY_ONLY();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EdgeRDDImpl$.class);
   }

   private EdgeRDDImpl$() {
   }
}
