package org.apache.spark.deploy.rest;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class SubmitRestProtocolException$ implements Serializable {
   public static final SubmitRestProtocolException$ MODULE$ = new SubmitRestProtocolException$();

   public Throwable $lessinit$greater$default$2() {
      return null;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SubmitRestProtocolException$.class);
   }

   private SubmitRestProtocolException$() {
   }
}
