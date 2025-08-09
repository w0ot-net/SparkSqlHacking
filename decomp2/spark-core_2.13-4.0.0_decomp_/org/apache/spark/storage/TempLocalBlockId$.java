package org.apache.spark.storage;

import java.io.Serializable;
import java.util.UUID;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class TempLocalBlockId$ extends AbstractFunction1 implements Serializable {
   public static final TempLocalBlockId$ MODULE$ = new TempLocalBlockId$();

   public final String toString() {
      return "TempLocalBlockId";
   }

   public TempLocalBlockId apply(final UUID id) {
      return new TempLocalBlockId(id);
   }

   public Option unapply(final TempLocalBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.id()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TempLocalBlockId$.class);
   }

   private TempLocalBlockId$() {
   }
}
