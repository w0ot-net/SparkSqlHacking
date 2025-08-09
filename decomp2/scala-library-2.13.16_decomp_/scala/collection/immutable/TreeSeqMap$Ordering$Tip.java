package scala.collection.immutable;

import java.io.Serializable;
import scala.Product;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.Statics;

public final class TreeSeqMap$Ordering$Tip extends TreeSeqMap.Ordering implements Product, Serializable {
   private final int ord;
   private final Object value;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int ord() {
      return this.ord;
   }

   public Object value() {
      return this.value;
   }

   public TreeSeqMap$Ordering$Tip withValue(final Object s) {
      return s == this.value() ? this : new TreeSeqMap$Ordering$Tip(this.ord(), s);
   }

   public void format(final StringBuilder sb, final String prefix, final String subPrefix) {
      java.lang.StringBuilder var10001 = (new java.lang.StringBuilder(10)).append(prefix).append("Tip(");
      TreeSeqMap.Ordering$ var10002 = TreeSeqMap.Ordering$.MODULE$;
      int toBinaryString_i = this.ord();
      java.lang.StringBuilder var6 = (new java.lang.StringBuilder(1)).append(toBinaryString_i).append("/");
      RichInt$ var10003 = RichInt$.MODULE$;
      String $plus$plus$eq_s = var10001.append(var6.append(Integer.toBinaryString(toBinaryString_i)).toString()).append(" -> ").append(this.value()).append(")\n").toString();
      if (sb == null) {
         throw null;
      } else {
         sb.addAll($plus$plus$eq_s);
      }
   }

   public TreeSeqMap$Ordering$Tip copy(final int ord, final Object value) {
      return new TreeSeqMap$Ordering$Tip(ord, value);
   }

   public int copy$default$1() {
      return this.ord();
   }

   public Object copy$default$2() {
      return this.value();
   }

   public String productPrefix() {
      return "Tip";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.ord();
         case 1:
            return this.value();
         default:
            return Statics.ioobe(x$1);
      }
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
      return x$1 instanceof TreeSeqMap$Ordering$Tip;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "ord";
         case 1:
            return "value";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, "Tip".hashCode());
      var1 = Statics.mix(var1, this.ord());
      var1 = Statics.mix(var1, Statics.anyHash(this.value()));
      int finalizeHash_length = 2;
      return Statics.avalanche(var1 ^ finalizeHash_length);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof TreeSeqMap$Ordering$Tip) {
            TreeSeqMap$Ordering$Tip var2 = (TreeSeqMap$Ordering$Tip)x$1;
            if (this.ord() == var2.ord() && BoxesRunTime.equals(this.value(), var2.value())) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public TreeSeqMap$Ordering$Tip(final int ord, final Object value) {
      this.ord = ord;
      this.value = value;
   }
}
