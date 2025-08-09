package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class ElementContentModel$Occurrence$Repeat$ extends ElementContentModel.Occurrence implements ElementContentModel$Occurrence$Signed, Product, Serializable {
   public static final ElementContentModel$Occurrence$Repeat$ MODULE$ = new ElementContentModel$Occurrence$Repeat$();

   static {
      ElementContentModel$Occurrence$Signed.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public final String toString() {
      return ElementContentModel$Occurrence$Signed.toString$(this);
   }

   public String sign() {
      return "+";
   }

   public String productPrefix() {
      return "Repeat";
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
      return x$1 instanceof ElementContentModel$Occurrence$Repeat$;
   }

   public int hashCode() {
      return -1850664517;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$Occurrence$Repeat$.class);
   }
}
