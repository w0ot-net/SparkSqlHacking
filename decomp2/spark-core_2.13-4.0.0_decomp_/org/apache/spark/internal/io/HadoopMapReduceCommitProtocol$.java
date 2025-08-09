package org.apache.spark.internal.io;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class HadoopMapReduceCommitProtocol$ implements Serializable {
   public static final HadoopMapReduceCommitProtocol$ MODULE$ = new HadoopMapReduceCommitProtocol$();

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HadoopMapReduceCommitProtocol$.class);
   }

   private HadoopMapReduceCommitProtocol$() {
   }
}
