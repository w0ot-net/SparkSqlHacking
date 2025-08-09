package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class IteratorBlock$ extends AbstractFunction1 implements Serializable {
   public static final IteratorBlock$ MODULE$ = new IteratorBlock$();

   public final String toString() {
      return "IteratorBlock";
   }

   public IteratorBlock apply(final Iterator iterator) {
      return new IteratorBlock(iterator);
   }

   public Option unapply(final IteratorBlock x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.iterator()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IteratorBlock$.class);
   }

   private IteratorBlock$() {
   }
}
