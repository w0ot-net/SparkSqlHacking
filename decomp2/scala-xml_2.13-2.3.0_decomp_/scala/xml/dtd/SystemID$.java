package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class SystemID$ extends AbstractFunction1 implements Serializable {
   public static final SystemID$ MODULE$ = new SystemID$();

   public final String toString() {
      return "SystemID";
   }

   public SystemID apply(final String systemId) {
      return new SystemID(systemId);
   }

   public Option unapply(final SystemID x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.systemId()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SystemID$.class);
   }

   private SystemID$() {
   }
}
