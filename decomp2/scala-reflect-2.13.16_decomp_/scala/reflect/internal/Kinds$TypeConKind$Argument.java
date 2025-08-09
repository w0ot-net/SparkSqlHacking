package scala.reflect.internal;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

public class Kinds$TypeConKind$Argument implements Product, Serializable {
   private final int variance;
   private final Kinds.Kind kind;
   private final Symbols.Symbol sym;
   // $FF: synthetic field
   public final Kinds.TypeConKind$ $outer;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int variance() {
      return this.variance;
   }

   public Kinds.Kind kind() {
      return this.kind;
   }

   public Symbols.Symbol sym() {
      return this.sym;
   }

   public Kinds$TypeConKind$Argument copy(final int variance, final Kinds.Kind kind, final Symbols.Symbol sym) {
      return new Kinds$TypeConKind$Argument(this.scala$reflect$internal$Kinds$TypeConKind$Argument$$$outer(), variance, kind, sym);
   }

   public int copy$default$1() {
      return this.variance();
   }

   public Kinds.Kind copy$default$2() {
      return this.kind();
   }

   public String productPrefix() {
      return "Argument";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return new Variance(this.variance());
         case 1:
            return this.kind();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Kinds$TypeConKind$Argument;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "variance";
         case 1:
            return "kind";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof Kinds$TypeConKind$Argument && ((Kinds$TypeConKind$Argument)x$1).scala$reflect$internal$Kinds$TypeConKind$Argument$$$outer() == this.scala$reflect$internal$Kinds$TypeConKind$Argument$$$outer()) {
            Kinds$TypeConKind$Argument var2 = (Kinds$TypeConKind$Argument)x$1;
            if (this.variance() == var2.variance()) {
               Kinds.Kind var10000 = this.kind();
               Kinds.Kind var3 = var2.kind();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               if (var2.canEqual(this)) {
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
   public Kinds.TypeConKind$ scala$reflect$internal$Kinds$TypeConKind$Argument$$$outer() {
      return this.$outer;
   }

   public Kinds$TypeConKind$Argument(final Kinds.TypeConKind$ $outer, final int variance, final Kinds.Kind kind, final Symbols.Symbol sym) {
      this.variance = variance;
      this.kind = kind;
      this.sym = sym;
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
