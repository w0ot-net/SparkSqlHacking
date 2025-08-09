package org.apache.spark.storage;

import java.io.Serializable;
import java.util.UUID;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class TempShuffleBlockId$ extends AbstractFunction1 implements Serializable {
   public static final TempShuffleBlockId$ MODULE$ = new TempShuffleBlockId$();

   public final String toString() {
      return "TempShuffleBlockId";
   }

   public TempShuffleBlockId apply(final UUID id) {
      return new TempShuffleBlockId(id);
   }

   public Option unapply(final TempShuffleBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.id()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TempShuffleBlockId$.class);
   }

   private TempShuffleBlockId$() {
   }
}
