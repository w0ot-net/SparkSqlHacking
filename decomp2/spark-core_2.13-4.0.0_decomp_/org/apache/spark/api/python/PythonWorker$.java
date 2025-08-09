package org.apache.spark.api.python;

import java.io.Serializable;
import java.nio.channels.SocketChannel;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class PythonWorker$ extends AbstractFunction1 implements Serializable {
   public static final PythonWorker$ MODULE$ = new PythonWorker$();

   public final String toString() {
      return "PythonWorker";
   }

   public PythonWorker apply(final SocketChannel channel) {
      return new PythonWorker(channel);
   }

   public Option unapply(final PythonWorker x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.channel()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PythonWorker$.class);
   }

   private PythonWorker$() {
   }
}
