package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ExtDef$ extends AbstractFunction1 implements Serializable {
   public static final ExtDef$ MODULE$ = new ExtDef$();

   public final String toString() {
      return "ExtDef";
   }

   public ExtDef apply(final ExternalID extID) {
      return new ExtDef(extID);
   }

   public Option unapply(final ExtDef x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.extID()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExtDef$.class);
   }

   private ExtDef$() {
   }
}
