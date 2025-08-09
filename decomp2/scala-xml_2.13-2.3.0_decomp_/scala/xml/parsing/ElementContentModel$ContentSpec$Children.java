package scala.xml.parsing;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public final class ElementContentModel$ContentSpec$Children implements ElementContentModel.ContentSpec, Product, Serializable {
   private final ElementContentModel$Elements$Many elements;
   private final ElementContentModel.Occurrence occurrence;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ElementContentModel$Elements$Many elements() {
      return this.elements;
   }

   public ElementContentModel.Occurrence occurrence() {
      return this.occurrence;
   }

   public String toString() {
      return (new StringBuilder(0)).append(this.elements()).append(this.occurrence()).toString();
   }

   public ElementContentModel$ContentSpec$Children copy(final ElementContentModel$Elements$Many elements, final ElementContentModel.Occurrence occurrence) {
      return new ElementContentModel$ContentSpec$Children(elements, occurrence);
   }

   public ElementContentModel$Elements$Many copy$default$1() {
      return this.elements();
   }

   public ElementContentModel.Occurrence copy$default$2() {
      return this.occurrence();
   }

   public String productPrefix() {
      return "Children";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.elements();
         case 1:
            return this.occurrence();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ElementContentModel$ContentSpec$Children;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "elements";
         case 1:
            return "occurrence";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label52: {
            if (x$1 instanceof ElementContentModel$ContentSpec$Children) {
               label45: {
                  ElementContentModel$ContentSpec$Children var4 = (ElementContentModel$ContentSpec$Children)x$1;
                  ElementContentModel$Elements$Many var10000 = this.elements();
                  ElementContentModel$Elements$Many var5 = var4.elements();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label45;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label45;
                  }

                  ElementContentModel.Occurrence var7 = this.occurrence();
                  ElementContentModel.Occurrence var6 = var4.occurrence();
                  if (var7 == null) {
                     if (var6 == null) {
                        break label52;
                     }
                  } else if (var7.equals(var6)) {
                     break label52;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ElementContentModel$ContentSpec$Children(final ElementContentModel$Elements$Many elements, final ElementContentModel.Occurrence occurrence) {
      this.elements = elements;
      this.occurrence = occurrence;
      Product.$init$(this);
   }
}
