package org.apache.spark.deploy.master;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class MasterMessages$ implements Serializable {
   public static final MasterMessages$ MODULE$ = new MasterMessages$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(MasterMessages$.class);
   }

   private MasterMessages$() {
   }
}
