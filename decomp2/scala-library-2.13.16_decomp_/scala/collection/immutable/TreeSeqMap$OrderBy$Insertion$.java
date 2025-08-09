package scala.collection.immutable;

import java.io.Serializable;
import scala.Product;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public class TreeSeqMap$OrderBy$Insertion$ implements TreeSeqMap.OrderBy, Product, Serializable {
   public static final TreeSeqMap$OrderBy$Insertion$ MODULE$ = new TreeSeqMap$OrderBy$Insertion$();

   static {
      TreeSeqMap$OrderBy$Insertion$ var10000 = MODULE$;
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String productPrefix() {
      return "Insertion";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return new AbstractIterator(this) {
         private int c;
         private final int cmax;
         private final Product x$2;

         public boolean hasNext() {
            return this.c < this.cmax;
         }

         public Object next() {
            Object result = this.x$2.productElement(this.c);
            ++this.c;
            return result;
         }

         public {
            this.x$2 = x$2;
            this.c = 0;
            this.cmax = x$2.productArity();
         }
      };
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof TreeSeqMap$OrderBy$Insertion$;
   }

   public int hashCode() {
      return 1619512975;
   }

   public String toString() {
      return "Insertion";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSeqMap$OrderBy$Insertion$.class);
   }
}
