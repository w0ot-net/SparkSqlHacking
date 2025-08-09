package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class ElementContentModel$ContentSpec$Empty$ implements ElementContentModel$ContentSpec$Simple, Product, Serializable {
   public static final ElementContentModel$ContentSpec$Empty$ MODULE$ = new ElementContentModel$ContentSpec$Empty$();
   private static final String value;

   static {
      ElementContentModel$ContentSpec$Simple.$init$(MODULE$);
      Product.$init$(MODULE$);
      value = "EMPTY";
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public final String toString() {
      return ElementContentModel$ContentSpec$Simple.toString$(this);
   }

   public String value() {
      return value;
   }

   public String productPrefix() {
      return "Empty";
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
      return x$1 instanceof ElementContentModel$ContentSpec$Empty$;
   }

   public int hashCode() {
      return 67081517;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$ContentSpec$Empty$.class);
   }
}
