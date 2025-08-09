package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class ElementContentModel$ContentSpec$PCData$ implements ElementContentModel.ContentSpec, Product, Serializable {
   public static final ElementContentModel$ContentSpec$PCData$ MODULE$ = new ElementContentModel$ContentSpec$PCData$();
   private static final String value;

   static {
      Product.$init$(MODULE$);
      value = "#PCDATA";
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String toString() {
      return (new StringBuilder(2)).append("(").append(this.value()).append(")").toString();
   }

   public String value() {
      return value;
   }

   public String productPrefix() {
      return "PCData";
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
      return x$1 instanceof ElementContentModel$ContentSpec$PCData$;
   }

   public int hashCode() {
      return -1940636611;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ElementContentModel$ContentSpec$PCData$.class);
   }
}
