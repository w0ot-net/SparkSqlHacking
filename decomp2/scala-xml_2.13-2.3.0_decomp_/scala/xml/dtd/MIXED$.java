package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.xml.dtd.impl.Base;

public final class MIXED$ extends AbstractFunction1 implements Serializable {
   public static final MIXED$ MODULE$ = new MIXED$();

   public final String toString() {
      return "MIXED";
   }

   public MIXED apply(final Base.RegExp r) {
      return new MIXED(r);
   }

   public Option unapply(final MIXED x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.r()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MIXED$.class);
   }

   private MIXED$() {
   }
}
