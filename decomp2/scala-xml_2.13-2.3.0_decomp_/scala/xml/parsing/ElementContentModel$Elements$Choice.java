package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ElementContentModel$Elements$Choice extends ElementContentModel$Elements$Many implements Product, Serializable {
   private final List children;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public List children() {
      return this.children;
   }

   public ElementContentModel$Elements$ManyCompanion companion() {
      return ElementContentModel$Elements$Choice$.MODULE$;
   }

   public ElementContentModel$Elements$Choice copy(final List children) {
      return new ElementContentModel$Elements$Choice(children);
   }

   public List copy$default$1() {
      return this.children();
   }

   public String productPrefix() {
      return "Choice";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.children();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ElementContentModel$Elements$Choice;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "children";
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
            if (x$1 instanceof ElementContentModel$Elements$Choice) {
               ElementContentModel$Elements$Choice var4 = (ElementContentModel$Elements$Choice)x$1;
               List var10000 = this.children();
               List var5 = var4.children();
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

   public ElementContentModel$Elements$Choice(final List children) {
      super(children);
      this.children = children;
      Product.$init$(this);
   }
}
