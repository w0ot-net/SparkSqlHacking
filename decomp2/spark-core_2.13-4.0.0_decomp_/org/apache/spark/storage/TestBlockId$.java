package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class TestBlockId$ extends AbstractFunction1 implements Serializable {
   public static final TestBlockId$ MODULE$ = new TestBlockId$();

   public final String toString() {
      return "TestBlockId";
   }

   public TestBlockId apply(final String id) {
      return new TestBlockId(id);
   }

   public Option unapply(final TestBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.id()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TestBlockId$.class);
   }

   private TestBlockId$() {
   }
}
