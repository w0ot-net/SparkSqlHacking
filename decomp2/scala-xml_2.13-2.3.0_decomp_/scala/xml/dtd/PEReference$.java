package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class PEReference$ extends AbstractFunction1 implements Serializable {
   public static final PEReference$ MODULE$ = new PEReference$();

   public final String toString() {
      return "PEReference";
   }

   public PEReference apply(final String ent) {
      return new PEReference(ent);
   }

   public Option unapply(final PEReference x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.ent()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PEReference$.class);
   }

   private PEReference$() {
   }
}
