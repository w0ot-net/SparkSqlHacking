package org.apache.spark.rpc.netty;

import java.io.Serializable;
import java.nio.ByteBuffer;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class OneWayOutboxMessage$ extends AbstractFunction1 implements Serializable {
   public static final OneWayOutboxMessage$ MODULE$ = new OneWayOutboxMessage$();

   public final String toString() {
      return "OneWayOutboxMessage";
   }

   public OneWayOutboxMessage apply(final ByteBuffer content) {
      return new OneWayOutboxMessage(content);
   }

   public Option unapply(final OneWayOutboxMessage x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.content()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OneWayOutboxMessage$.class);
   }

   private OneWayOutboxMessage$() {
   }
}
