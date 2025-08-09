package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class PublicID$ extends AbstractFunction2 implements Serializable {
   public static final PublicID$ MODULE$ = new PublicID$();

   public final String toString() {
      return "PublicID";
   }

   public PublicID apply(final String publicId, final String systemId) {
      return new PublicID(publicId, systemId);
   }

   public Option unapply(final PublicID x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.publicId(), x$0.systemId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PublicID$.class);
   }

   private PublicID$() {
   }
}
