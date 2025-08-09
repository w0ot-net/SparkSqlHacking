package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.Seq;
import scala.collection.immutable.Nil.;
import scala.runtime.ModuleSerializationProxy;

public final class DocType$ implements Serializable {
   public static final DocType$ MODULE$ = new DocType$();

   public DocType apply(final String name) {
      return new DocType(name, NoExternalID$.MODULE$, .MODULE$);
   }

   public DocType apply(final String name, final ExternalID extID, final Seq intSubset) {
      return new DocType(name, extID, intSubset);
   }

   public Option unapply(final DocType x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.name(), x$0.extID(), x$0.intSubset())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DocType$.class);
   }

   private DocType$() {
   }
}
