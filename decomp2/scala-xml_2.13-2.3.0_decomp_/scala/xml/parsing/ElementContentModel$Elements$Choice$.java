package scala.xml.parsing;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.ModuleSerializationProxy;

public class ElementContentModel$Elements$Choice$ extends ElementContentModel$Elements$ManyCompanion implements Serializable {
   public static final ElementContentModel$Elements$Choice$ MODULE$ = new ElementContentModel$Elements$Choice$();

   public ElementContentModel$Elements$Choice apply(final List children) {
      return new ElementContentModel$Elements$Choice(children);
   }

   public Option unapply(final ElementContentModel$Elements$Choice x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.children()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$Elements$Choice$.class);
   }

   public ElementContentModel$Elements$Choice$() {
      super('|');
   }
}
