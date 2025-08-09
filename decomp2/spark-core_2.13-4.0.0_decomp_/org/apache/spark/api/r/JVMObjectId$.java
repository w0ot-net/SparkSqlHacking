package org.apache.spark.api.r;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class JVMObjectId$ extends AbstractFunction1 implements Serializable {
   public static final JVMObjectId$ MODULE$ = new JVMObjectId$();

   public final String toString() {
      return "JVMObjectId";
   }

   public JVMObjectId apply(final String id) {
      return new JVMObjectId(id);
   }

   public Option unapply(final JVMObjectId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.id()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(JVMObjectId$.class);
   }

   private JVMObjectId$() {
   }
}
