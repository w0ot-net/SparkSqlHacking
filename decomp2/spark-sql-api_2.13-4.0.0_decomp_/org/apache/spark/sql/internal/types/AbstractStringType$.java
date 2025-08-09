package org.apache.spark.sql.internal.types;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class AbstractStringType$ implements Serializable {
   public static final AbstractStringType$ MODULE$ = new AbstractStringType$();

   public boolean $lessinit$greater$default$1() {
      return false;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AbstractStringType$.class);
   }

   private AbstractStringType$() {
   }
}
