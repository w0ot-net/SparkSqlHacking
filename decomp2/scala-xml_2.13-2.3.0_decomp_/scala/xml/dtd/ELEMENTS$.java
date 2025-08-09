package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.xml.dtd.impl.Base;

public final class ELEMENTS$ extends AbstractFunction1 implements Serializable {
   public static final ELEMENTS$ MODULE$ = new ELEMENTS$();

   public final String toString() {
      return "ELEMENTS";
   }

   public ELEMENTS apply(final Base.RegExp r) {
      return new ELEMENTS(r);
   }

   public Option unapply(final ELEMENTS x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.r()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ELEMENTS$.class);
   }

   private ELEMENTS$() {
   }
}
