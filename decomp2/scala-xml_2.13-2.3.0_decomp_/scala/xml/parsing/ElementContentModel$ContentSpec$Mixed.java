package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ElementContentModel$ContentSpec$Mixed implements ElementContentModel.ContentSpec, Product, Serializable {
   private final List elements;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public List elements() {
      return this.elements;
   }

   public String toString() {
      String names = this.elements().mkString("|");
      return (new StringBuilder(4)).append("(").append(ElementContentModel$ContentSpec$PCData$.MODULE$.value()).append("|").append(names).append(")*").toString();
   }

   public ElementContentModel$ContentSpec$Mixed copy(final List elements) {
      return new ElementContentModel$ContentSpec$Mixed(elements);
   }

   public List copy$default$1() {
      return this.elements();
   }

   public String productPrefix() {
      return "Mixed";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.elements();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ElementContentModel$ContentSpec$Mixed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "elements";
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
            if (x$1 instanceof ElementContentModel$ContentSpec$Mixed) {
               ElementContentModel$ContentSpec$Mixed var4 = (ElementContentModel$ContentSpec$Mixed)x$1;
               List var10000 = this.elements();
               List var5 = var4.elements();
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

   public ElementContentModel$ContentSpec$Mixed(final List elements) {
      this.elements = elements;
      Product.$init$(this);
   }
}
