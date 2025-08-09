package scala.xml.parsing;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public class ElementContentModel$ContentSpec$Children$ implements Serializable {
   public static final ElementContentModel$ContentSpec$Children$ MODULE$ = new ElementContentModel$ContentSpec$Children$();

   public ElementContentModel$ContentSpec$Children parse(final String string, final ElementContentModel.Occurrence occurrence) {
      return new ElementContentModel$ContentSpec$Children(ElementContentModel$Elements$Many$.MODULE$.parse(string), occurrence);
   }

   public ElementContentModel$ContentSpec$Children apply(final ElementContentModel$Elements$Many elements, final ElementContentModel.Occurrence occurrence) {
      return new ElementContentModel$ContentSpec$Children(elements, occurrence);
   }

   public Option unapply(final ElementContentModel$ContentSpec$Children x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.elements(), x$0.occurrence())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$ContentSpec$Children$.class);
   }
}
