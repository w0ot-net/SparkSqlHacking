package org.apache.spark.ml.attribute;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class AttributeType$Numeric$1$ extends AttributeType implements Product, Serializable {
   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String productPrefix() {
      return "Numeric";
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
      return x$1 instanceof AttributeType$Numeric$1$;
   }

   public int hashCode() {
      return -335760659;
   }

   public String toString() {
      return "Numeric";
   }

   public AttributeType$Numeric$1$() {
      super("numeric");
      Product.$init$(this);
   }
}
