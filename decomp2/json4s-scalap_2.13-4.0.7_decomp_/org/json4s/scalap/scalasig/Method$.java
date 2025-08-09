package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Method$ extends AbstractFunction4 implements Serializable {
   public static final Method$ MODULE$ = new Method$();

   public final String toString() {
      return "Method";
   }

   public Method apply(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      return new Method(flags, nameIndex, descriptorIndex, attributes);
   }

   public Option unapply(final Method x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToInteger(x$0.flags()), BoxesRunTime.boxToInteger(x$0.nameIndex()), BoxesRunTime.boxToInteger(x$0.descriptorIndex()), x$0.attributes())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Method$.class);
   }

   private Method$() {
   }
}
