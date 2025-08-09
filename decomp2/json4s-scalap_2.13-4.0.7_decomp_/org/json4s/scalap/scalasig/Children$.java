package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class Children$ extends AbstractFunction1 implements Serializable {
   public static final Children$ MODULE$ = new Children$();

   public final String toString() {
      return "Children";
   }

   public Children apply(final Seq symbolRefs) {
      return new Children(symbolRefs);
   }

   public Option unapply(final Children x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.symbolRefs()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Children$.class);
   }

   private Children$() {
   }
}
