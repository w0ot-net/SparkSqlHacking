package org.apache.spark.deploy.master;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class WorkerResourceInfo$ extends AbstractFunction2 implements Serializable {
   public static final WorkerResourceInfo$ MODULE$ = new WorkerResourceInfo$();

   public final String toString() {
      return "WorkerResourceInfo";
   }

   public WorkerResourceInfo apply(final String name, final Seq addresses) {
      return new WorkerResourceInfo(name, addresses);
   }

   public Option unapply(final WorkerResourceInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.addresses())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WorkerResourceInfo$.class);
   }

   private WorkerResourceInfo$() {
   }
}
