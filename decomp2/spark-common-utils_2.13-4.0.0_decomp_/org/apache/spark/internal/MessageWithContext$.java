package org.apache.spark.internal;

import java.io.Serializable;
import java.util.Map;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class MessageWithContext$ extends AbstractFunction2 implements Serializable {
   public static final MessageWithContext$ MODULE$ = new MessageWithContext$();

   public final String toString() {
      return "MessageWithContext";
   }

   public MessageWithContext apply(final String message, final Map context) {
      return new MessageWithContext(message, context);
   }

   public Option unapply(final MessageWithContext x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.message(), x$0.context())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MessageWithContext$.class);
   }

   private MessageWithContext$() {
   }
}
