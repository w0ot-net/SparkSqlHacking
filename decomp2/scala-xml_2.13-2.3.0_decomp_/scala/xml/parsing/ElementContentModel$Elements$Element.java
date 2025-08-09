package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ElementContentModel$Elements$Element implements ElementContentModel.Elements, Product, Serializable {
   private final String name;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public String toString() {
      return this.name();
   }

   public ElementContentModel$Elements$Element copy(final String name) {
      return new ElementContentModel$Elements$Element(name);
   }

   public String copy$default$1() {
      return this.name();
   }

   public String productPrefix() {
      return "Element";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ElementContentModel$Elements$Element;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label39: {
            if (x$1 instanceof ElementContentModel$Elements$Element) {
               ElementContentModel$Elements$Element var4 = (ElementContentModel$Elements$Element)x$1;
               String var10000 = this.name();
               String var5 = var4.name();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label39;
                  }
               } else if (var10000.equals(var5)) {
                  break label39;
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public ElementContentModel$Elements$Element(final String name) {
      this.name = name;
      Product.$init$(this);
   }
}
