package scala.xml.parsing;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public class ElementContentModel$ContentSpec$Mixed$ extends AbstractFunction1 implements Serializable {
   public static final ElementContentModel$ContentSpec$Mixed$ MODULE$ = new ElementContentModel$ContentSpec$Mixed$();

   public final String toString() {
      return "Mixed";
   }

   public ElementContentModel$ContentSpec$Mixed apply(final List elements) {
      return new ElementContentModel$ContentSpec$Mixed(elements);
   }

   public Option unapply(final ElementContentModel$ContentSpec$Mixed x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.elements()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$ContentSpec$Mixed$.class);
   }
}
