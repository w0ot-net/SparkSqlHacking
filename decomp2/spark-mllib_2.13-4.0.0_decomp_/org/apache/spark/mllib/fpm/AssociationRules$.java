package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class AssociationRules$ implements Serializable {
   public static final AssociationRules$ MODULE$ = new AssociationRules$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(AssociationRules$.class);
   }

   private AssociationRules$() {
   }
}
