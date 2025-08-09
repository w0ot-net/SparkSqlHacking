package scala.collection.immutable;

import java.io.Serializable;
import scala.Product;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public class TreeSeqMap$Ordering$Zero$ extends TreeSeqMap.Ordering implements Product, Serializable {
   public static final TreeSeqMap$Ordering$Zero$ MODULE$ = new TreeSeqMap$Ordering$Zero$();

   static {
      TreeSeqMap$Ordering$Zero$ var10000 = MODULE$;
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean equals(final Object that) {
      if (that == this) {
         return true;
      } else {
         return that instanceof TreeSeqMap.Ordering ? false : super.equals(that);
      }
   }

   public void format(final StringBuilder sb, final String prefix, final String subPrefix) {
      String $plus$plus$eq_s = (new java.lang.StringBuilder(1)).append(prefix).append("Ø").toString();
      if (sb == null) {
         throw null;
      } else {
         sb.addAll($plus$plus$eq_s);
      }
   }

   public String productPrefix() {
      return "Zero";
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
      return x$1 instanceof TreeSeqMap$Ordering$Zero$;
   }

   public int hashCode() {
      return 2781896;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSeqMap$Ordering$Zero$.class);
   }
}
