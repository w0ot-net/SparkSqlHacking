package org.apache.spark.sql.catalyst.analysis;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class TempTableAlreadyExistsException$ implements Serializable {
   public static final TempTableAlreadyExistsException$ MODULE$ = new TempTableAlreadyExistsException$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TempTableAlreadyExistsException$.class);
   }

   private TempTableAlreadyExistsException$() {
   }
}
