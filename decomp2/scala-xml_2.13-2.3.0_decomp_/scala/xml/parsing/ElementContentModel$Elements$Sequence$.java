package scala.xml.parsing;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.ModuleSerializationProxy;

public class ElementContentModel$Elements$Sequence$ extends ElementContentModel$Elements$ManyCompanion implements Serializable {
   public static final ElementContentModel$Elements$Sequence$ MODULE$ = new ElementContentModel$Elements$Sequence$();

   public ElementContentModel$Elements$Sequence apply(final List children) {
      return new ElementContentModel$Elements$Sequence(children);
   }

   public Option unapply(final ElementContentModel$Elements$Sequence x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.children()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$Elements$Sequence$.class);
   }

   public ElementContentModel$Elements$Sequence$() {
      super(',');
   }
}
