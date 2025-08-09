package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class ValidationException$ extends AbstractFunction1 implements Serializable {
   public static final ValidationException$ MODULE$ = new ValidationException$();

   public final String toString() {
      return "ValidationException";
   }

   public ValidationException apply(final String e) {
      return new ValidationException(e);
   }

   public Option unapply(final ValidationException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.e()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ValidationException$.class);
   }

   private ValidationException$() {
   }
}
