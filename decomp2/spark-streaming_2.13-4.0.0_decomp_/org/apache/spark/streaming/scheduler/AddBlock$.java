package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class AddBlock$ extends AbstractFunction1 implements Serializable {
   public static final AddBlock$ MODULE$ = new AddBlock$();

   public final String toString() {
      return "AddBlock";
   }

   public AddBlock apply(final ReceivedBlockInfo receivedBlockInfo) {
      return new AddBlock(receivedBlockInfo);
   }

   public Option unapply(final AddBlock x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.receivedBlockInfo()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AddBlock$.class);
   }

   private AddBlock$() {
   }
}
