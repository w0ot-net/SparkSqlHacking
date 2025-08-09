package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import scala.runtime.ModuleSerializationProxy;

public final class StateOperatorProgress$ implements Serializable {
   public static final StateOperatorProgress$ MODULE$ = new StateOperatorProgress$();

   public Map $lessinit$greater$default$12() {
      return new HashMap();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StateOperatorProgress$.class);
   }

   private StateOperatorProgress$() {
   }
}
