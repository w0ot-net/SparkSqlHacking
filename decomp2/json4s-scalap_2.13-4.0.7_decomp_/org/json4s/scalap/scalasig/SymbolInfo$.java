package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SymbolInfo$ extends AbstractFunction6 implements Serializable {
   public static final SymbolInfo$ MODULE$ = new SymbolInfo$();

   public final String toString() {
      return "SymbolInfo";
   }

   public SymbolInfo apply(final String name, final Symbol owner, final int flags, final Option privateWithin, final int info, final ScalaSig.Entry entry) {
      return new SymbolInfo(name, owner, flags, privateWithin, info, entry);
   }

   public Option unapply(final SymbolInfo x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.name(), x$0.owner(), BoxesRunTime.boxToInteger(x$0.flags()), x$0.privateWithin(), BoxesRunTime.boxToInteger(x$0.info()), x$0.entry())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SymbolInfo$.class);
   }

   private SymbolInfo$() {
   }
}
