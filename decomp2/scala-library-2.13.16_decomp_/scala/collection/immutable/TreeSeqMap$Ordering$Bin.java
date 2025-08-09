package scala.collection.immutable;

import java.io.Serializable;
import scala.Product;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.runtime.RichInt$;
import scala.runtime.Statics;

public final class TreeSeqMap$Ordering$Bin extends TreeSeqMap.Ordering implements Product, Serializable {
   private final int prefix;
   private final int mask;
   private final TreeSeqMap.Ordering left;
   private TreeSeqMap.Ordering right;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int prefix() {
      return this.prefix;
   }

   public int mask() {
      return this.mask;
   }

   public TreeSeqMap.Ordering left() {
      return this.left;
   }

   public TreeSeqMap.Ordering right() {
      return this.right;
   }

   public void right_$eq(final TreeSeqMap.Ordering x$1) {
      this.right = x$1;
   }

   public TreeSeqMap.Ordering bin(final TreeSeqMap.Ordering left, final TreeSeqMap.Ordering right) {
      return this.left() == left && this.right() == right ? this : new TreeSeqMap$Ordering$Bin(this.prefix(), this.mask(), left, right);
   }

   public void format(final StringBuilder sb, final String prefix, final String subPrefix) {
      java.lang.StringBuilder var10001 = (new java.lang.StringBuilder(7)).append(prefix).append("Bin(");
      TreeSeqMap.Ordering$ var10002 = TreeSeqMap.Ordering$.MODULE$;
      int toBinaryString_i = this.prefix();
      java.lang.StringBuilder var9 = (new java.lang.StringBuilder(1)).append(toBinaryString_i).append("/");
      RichInt$ var10003 = RichInt$.MODULE$;
      var10001 = var10001.append(var9.append(Integer.toBinaryString(toBinaryString_i)).toString()).append(":");
      TreeSeqMap.Ordering$ var10 = TreeSeqMap.Ordering$.MODULE$;
      int toBinaryString_i = this.mask();
      java.lang.StringBuilder var11 = (new java.lang.StringBuilder(1)).append(toBinaryString_i).append("/");
      var10003 = RichInt$.MODULE$;
      String $plus$plus$eq_s = var10001.append(var11.append(Integer.toBinaryString(toBinaryString_i)).toString()).append(")\n").toString();
      if (sb == null) {
         throw null;
      } else {
         sb.addAll($plus$plus$eq_s);
         Object var7 = null;
         this.left().format(sb, (new java.lang.StringBuilder(4)).append(subPrefix).append("├── ").toString(), (new java.lang.StringBuilder(4)).append(subPrefix).append("│   ").toString());
         this.right().format(sb, (new java.lang.StringBuilder(4)).append(subPrefix).append("└── ").toString(), (new java.lang.StringBuilder(4)).append(subPrefix).append("    ").toString());
      }
   }

   public TreeSeqMap$Ordering$Bin copy(final int prefix, final int mask, final TreeSeqMap.Ordering left, final TreeSeqMap.Ordering right) {
      return new TreeSeqMap$Ordering$Bin(prefix, mask, left, right);
   }

   public int copy$default$1() {
      return this.prefix();
   }

   public int copy$default$2() {
      return this.mask();
   }

   public TreeSeqMap.Ordering copy$default$3() {
      return this.left();
   }

   public TreeSeqMap.Ordering copy$default$4() {
      return this.right();
   }

   public String productPrefix() {
      return "Bin";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.prefix();
         case 1:
            return this.mask();
         case 2:
            return this.left();
         case 3:
            return this.right();
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
      return x$1 instanceof TreeSeqMap$Ordering$Bin;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "prefix";
         case 1:
            return "mask";
         case 2:
            return "left";
         case 3:
            return "right";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, "Bin".hashCode());
      var1 = Statics.mix(var1, this.prefix());
      var1 = Statics.mix(var1, this.mask());
      var1 = Statics.mix(var1, Statics.anyHash(this.left()));
      var1 = Statics.mix(var1, Statics.anyHash(this.right()));
      int finalizeHash_length = 4;
      return Statics.avalanche(var1 ^ finalizeHash_length);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof TreeSeqMap$Ordering$Bin) {
            TreeSeqMap$Ordering$Bin var2 = (TreeSeqMap$Ordering$Bin)x$1;
            if (this.prefix() == var2.prefix() && this.mask() == var2.mask()) {
               TreeSeqMap.Ordering var10000 = this.left();
               TreeSeqMap.Ordering var3 = var2.left();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.right();
               TreeSeqMap.Ordering var4 = var2.right();
               if (var10000 == null) {
                  if (var4 == null) {
                     return true;
                  }
               } else if (var10000.equals(var4)) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public TreeSeqMap$Ordering$Bin(final int prefix, final int mask, final TreeSeqMap.Ordering left, final TreeSeqMap.Ordering right) {
      this.prefix = prefix;
      this.mask = mask;
      this.left = left;
      this.right = right;
      super();
   }
}
