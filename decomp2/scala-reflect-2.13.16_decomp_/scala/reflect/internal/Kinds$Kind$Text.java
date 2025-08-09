package scala.reflect.internal;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

public class Kinds$Kind$Text implements Kinds$Kind$ScalaNotation, Product, Serializable {
   private final String value;
   // $FF: synthetic field
   public final Kinds.Kind$ $outer;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String value() {
      return this.value;
   }

   public String toString() {
      return this.value();
   }

   public Kinds$Kind$Text copy(final String value) {
      return new Kinds$Kind$Text(this.scala$reflect$internal$Kinds$Kind$Text$$$outer(), value);
   }

   public String copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "Text";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.value();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Kinds$Kind$Text;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "value";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof Kinds$Kind$Text && ((Kinds$Kind$Text)x$1).scala$reflect$internal$Kinds$Kind$Text$$$outer() == this.scala$reflect$internal$Kinds$Kind$Text$$$outer()) {
            Kinds$Kind$Text var2 = (Kinds$Kind$Text)x$1;
            String var10000 = this.value();
            String var3 = var2.value();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   public Kinds.Kind$ scala$reflect$internal$Kinds$Kind$Text$$$outer() {
      return this.$outer;
   }

   public Kinds$Kind$Text(final Kinds.Kind$ $outer, final String value) {
      this.value = value;
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
