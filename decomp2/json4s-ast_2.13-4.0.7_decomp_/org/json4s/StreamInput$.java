package org.json4s;

import java.io.InputStream;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class StreamInput$ extends AbstractFunction1 implements Serializable {
   public static final StreamInput$ MODULE$ = new StreamInput$();

   public final String toString() {
      return "StreamInput";
   }

   public StreamInput apply(final InputStream stream) {
      return new StreamInput(stream);
   }

   public Option unapply(final StreamInput x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.stream()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamInput$.class);
   }

   private StreamInput$() {
   }
}
