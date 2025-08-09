package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ByteBufferBlock$ extends AbstractFunction1 implements Serializable {
   public static final ByteBufferBlock$ MODULE$ = new ByteBufferBlock$();

   public final String toString() {
      return "ByteBufferBlock";
   }

   public ByteBufferBlock apply(final ByteBuffer byteBuffer) {
      return new ByteBufferBlock(byteBuffer);
   }

   public Option unapply(final ByteBufferBlock x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.byteBuffer()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ByteBufferBlock$.class);
   }

   private ByteBufferBlock$() {
   }
}
