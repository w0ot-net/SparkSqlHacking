package org.apache.spark.sql.catalyst.analysis;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class CannotReplaceMissingTableException$ implements Serializable {
   public static final CannotReplaceMissingTableException$ MODULE$ = new CannotReplaceMissingTableException$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CannotReplaceMissingTableException$.class);
   }

   private CannotReplaceMissingTableException$() {
   }
}
