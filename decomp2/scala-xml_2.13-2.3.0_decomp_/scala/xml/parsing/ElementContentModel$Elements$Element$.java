package scala.xml.parsing;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public class ElementContentModel$Elements$Element$ extends AbstractFunction1 implements Serializable {
   public static final ElementContentModel$Elements$Element$ MODULE$ = new ElementContentModel$Elements$Element$();

   public final String toString() {
      return "Element";
   }

   public ElementContentModel$Elements$Element apply(final String name) {
      return new ElementContentModel$Elements$Element(name);
   }

   public Option unapply(final ElementContentModel$Elements$Element x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.name()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$Elements$Element$.class);
   }
}
