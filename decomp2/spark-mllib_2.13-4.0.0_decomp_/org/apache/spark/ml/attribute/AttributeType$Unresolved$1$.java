package org.apache.spark.ml.attribute;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class AttributeType$Unresolved$1$ extends AttributeType implements Product, Serializable {
   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String productPrefix() {
      return "Unresolved";
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
      return x$1 instanceof AttributeType$Unresolved$1$;
   }

   public int hashCode() {
      return -1116602959;
   }

   public String toString() {
      return "Unresolved";
   }

   public AttributeType$Unresolved$1$() {
      super("unresolved");
      Product.$init$(this);
   }
}
