package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class ExternalSymbol$ extends AbstractFunction3 implements Serializable {
   public static final ExternalSymbol$ MODULE$ = new ExternalSymbol$();

   public final String toString() {
      return "ExternalSymbol";
   }

   public ExternalSymbol apply(final String name, final Option parent, final ScalaSig.Entry entry) {
      return new ExternalSymbol(name, parent, entry);
   }

   public Option unapply(final ExternalSymbol x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.name(), x$0.parent(), x$0.entry())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExternalSymbol$.class);
   }

   private ExternalSymbol$() {
   }
}
