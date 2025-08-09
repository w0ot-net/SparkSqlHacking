package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class MalformedAttributeException$ extends AbstractFunction1 implements Serializable {
   public static final MalformedAttributeException$ MODULE$ = new MalformedAttributeException$();

   public final String toString() {
      return "MalformedAttributeException";
   }

   public MalformedAttributeException apply(final String msg) {
      return new MalformedAttributeException(msg);
   }

   public Option unapply(final MalformedAttributeException x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.msg()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MalformedAttributeException$.class);
   }

   private MalformedAttributeException$() {
   }
}
