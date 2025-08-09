package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class ElementContentModel$Occurrence$Once$ extends ElementContentModel.Occurrence implements Product, Serializable {
   public static final ElementContentModel$Occurrence$Once$ MODULE$ = new ElementContentModel$Occurrence$Once$();

   static {
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String toString() {
      return "";
   }

   public String productPrefix() {
      return "Once";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ElementContentModel$Occurrence$Once$;
   }

   public int hashCode() {
      return 2462369;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$Occurrence$Once$.class);
   }
}
