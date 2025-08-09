package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ArrayBufferBlock$ extends AbstractFunction1 implements Serializable {
   public static final ArrayBufferBlock$ MODULE$ = new ArrayBufferBlock$();

   public final String toString() {
      return "ArrayBufferBlock";
   }

   public ArrayBufferBlock apply(final ArrayBuffer arrayBuffer) {
      return new ArrayBufferBlock(arrayBuffer);
   }

   public Option unapply(final ArrayBufferBlock x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.arrayBuffer()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArrayBufferBlock$.class);
   }

   private ArrayBufferBlock$() {
   }
}
