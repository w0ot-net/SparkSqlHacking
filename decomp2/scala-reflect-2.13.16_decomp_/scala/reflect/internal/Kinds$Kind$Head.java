package scala.reflect.internal;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

public class Kinds$Kind$Head implements Kinds$Kind$ScalaNotation, Product, Serializable {
   private final int order;
   private final Option n;
   private final Option alias;
   // $FF: synthetic field
   public final Kinds.Kind$ $outer;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int order() {
      return this.order;
   }

   public Option n() {
      return this.n;
   }

   public Option alias() {
      return this.alias;
   }

   public String toString() {
      Option var10000 = this.alias();
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (String)(getOrElse_this.isEmpty() ? $anonfun$toString$1(this) : getOrElse_this.get());
      }
   }

   private String typeAlias(final int x) {
      switch (x) {
         case 0:
            return "A";
         case 1:
            return "F";
         case 2:
            return "X";
         case 3:
            return "Y";
         case 4:
            return "Z";
         default:
            return x < 12 ? Character.toString((char)(74 + x)) : "V";
      }
   }

   public Kinds$Kind$Head copy(final int order, final Option n, final Option alias) {
      return new Kinds$Kind$Head(this.scala$reflect$internal$Kinds$Kind$Head$$$outer(), order, n, alias);
   }

   public int copy$default$1() {
      return this.order();
   }

   public Option copy$default$2() {
      return this.n();
   }

   public Option copy$default$3() {
      return this.alias();
   }

   public String productPrefix() {
      return "Head";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.order();
         case 1:
            return this.n();
         case 2:
            return this.alias();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Kinds$Kind$Head;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "order";
         case 1:
            return "n";
         case 2:
            return "alias";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, "Head".hashCode());
      var1 = Statics.mix(var1, this.order());
      var1 = Statics.mix(var1, Statics.anyHash(this.n()));
      var1 = Statics.mix(var1, Statics.anyHash(this.alias()));
      int finalizeHash_length = 3;
      return Statics.avalanche(var1 ^ finalizeHash_length);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof Kinds$Kind$Head && ((Kinds$Kind$Head)x$1).scala$reflect$internal$Kinds$Kind$Head$$$outer() == this.scala$reflect$internal$Kinds$Kind$Head$$$outer()) {
            Kinds$Kind$Head var2 = (Kinds$Kind$Head)x$1;
            if (this.order() == var2.order()) {
               Option var10000 = this.n();
               Option var3 = var2.n();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.alias();
               Option var4 = var2.alias();
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

   // $FF: synthetic method
   public Kinds.Kind$ scala$reflect$internal$Kinds$Kind$Head$$$outer() {
      return this.$outer;
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$2(final int x$11) {
      return Integer.toString(x$11);
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$3() {
      return "";
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$1(final Kinds$Kind$Head $this) {
      StringBuilder var10000 = (new StringBuilder(0)).append($this.typeAlias($this.order()));
      Option var10001 = $this.n();
      if (var10001 == null) {
         throw null;
      } else {
         Option map_this = var10001;
         Object var5 = map_this.isEmpty() ? .MODULE$ : new Some(Integer.toString(BoxesRunTime.unboxToInt(map_this.get())));
         Object var3 = null;
         Option getOrElse_this = (Option)var5;
         var5 = getOrElse_this.isEmpty() ? "" : getOrElse_this.get();
         getOrElse_this = null;
         return var10000.append(var5).toString();
      }
   }

   public Kinds$Kind$Head(final Kinds.Kind$ $outer, final int order, final Option n, final Option alias) {
      this.order = order;
      this.n = n;
      this.alias = alias;
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$toString$2$adapted(final Object x$11) {
      return $anonfun$toString$2(BoxesRunTime.unboxToInt(x$11));
   }
}
