package org.apache.spark.resource;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorResourceRequest$ implements Serializable {
   public static final ExecutorResourceRequest$ MODULE$ = new ExecutorResourceRequest$();

   public String $lessinit$greater$default$3() {
      return "";
   }

   public String $lessinit$greater$default$4() {
      return "";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorResourceRequest$.class);
   }

   private ExecutorResourceRequest$() {
   }
}
