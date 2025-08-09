package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class PythonStreamBlockId$ extends AbstractFunction2 implements Serializable {
   public static final PythonStreamBlockId$ MODULE$ = new PythonStreamBlockId$();

   public final String toString() {
      return "PythonStreamBlockId";
   }

   public PythonStreamBlockId apply(final int streamId, final long uniqueId) {
      return new PythonStreamBlockId(streamId, uniqueId);
   }

   public Option unapply(final PythonStreamBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcIJ.sp(x$0.streamId(), x$0.uniqueId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PythonStreamBlockId$.class);
   }

   private PythonStreamBlockId$() {
   }
}
