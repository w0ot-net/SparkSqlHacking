package org.apache.spark.deploy.master;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorResourceDescription$ extends AbstractFunction3 implements Serializable {
   public static final ExecutorResourceDescription$ MODULE$ = new ExecutorResourceDescription$();

   public Seq $lessinit$greater$default$3() {
      return (Seq).MODULE$.Seq().empty();
   }

   public final String toString() {
      return "ExecutorResourceDescription";
   }

   public ExecutorResourceDescription apply(final Option coresPerExecutor, final int memoryMbPerExecutor, final Seq customResourcesPerExecutor) {
      return new ExecutorResourceDescription(coresPerExecutor, memoryMbPerExecutor, customResourcesPerExecutor);
   }

   public Seq apply$default$3() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Option unapply(final ExecutorResourceDescription x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.coresPerExecutor(), BoxesRunTime.boxToInteger(x$0.memoryMbPerExecutor()), x$0.customResourcesPerExecutor())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorResourceDescription$.class);
   }

   private ExecutorResourceDescription$() {
   }
}
