package org.apache.spark.deploy.yarn;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class ContainerLocalityPreferences$ extends AbstractFunction2 implements Serializable {
   public static final ContainerLocalityPreferences$ MODULE$ = new ContainerLocalityPreferences$();

   public final String toString() {
      return "ContainerLocalityPreferences";
   }

   public ContainerLocalityPreferences apply(final String[] nodes, final String[] racks) {
      return new ContainerLocalityPreferences(nodes, racks);
   }

   public Option unapply(final ContainerLocalityPreferences x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.nodes(), x$0.racks())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ContainerLocalityPreferences$.class);
   }

   private ContainerLocalityPreferences$() {
   }
}
