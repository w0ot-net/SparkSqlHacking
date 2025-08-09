package org.apache.spark.rdd;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class RDDCheckpointData$ implements Serializable {
   public static final RDDCheckpointData$ MODULE$ = new RDDCheckpointData$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDDCheckpointData$.class);
   }

   private RDDCheckpointData$() {
   }
}
