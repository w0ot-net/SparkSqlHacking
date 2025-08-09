package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.None.;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

public class Kinds$Kind$StringState implements Product, Serializable {
   private final Seq tokens;
   // $FF: synthetic field
   public final Kinds.Kind$ $outer;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq tokens() {
      return this.tokens;
   }

   public String toString() {
      Seq var10000 = this.tokens();
      if (var10000 == null) {
         throw null;
      } else {
         IterableOnceOps mkString_this = var10000;
         String mkString_mkString_sep = "";
         return mkString_this.mkString("", mkString_mkString_sep, "");
      }
   }

   public Kinds$Kind$StringState append(final String value) {
      Kinds$Kind$StringState var10000 = new Kinds$Kind$StringState;
      Kinds.Kind$ var10002 = this.scala$reflect$internal$Kinds$Kind$StringState$$$outer();
      Seq var10003 = this.tokens();
      Kinds$Kind$Text $colon$plus_elem = new Kinds$Kind$Text(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), value);
      if (var10003 == null) {
         throw null;
      } else {
         Object var4 = var10003.appended($colon$plus_elem);
         $colon$plus_elem = null;
         var10000.<init>(var10002, (Seq)var4);
         return var10000;
      }
   }

   public Kinds$Kind$StringState appendHead(final int order, final Symbols.Symbol sym) {
      int n = this.countByOrder(order) + 1;
      Option alias = (Option)(sym == this.scala$reflect$internal$Kinds$Kind$StringState$$$outer().scala$reflect$internal$Kinds$Kind$$$outer().NoSymbol() ? .MODULE$ : new Some(sym.nameString()));
      Kinds$Kind$StringState var10000 = new Kinds$Kind$StringState;
      Kinds.Kind$ var10002 = this.scala$reflect$internal$Kinds$Kind$StringState$$$outer();
      Seq var10003 = this.tokens();
      Kinds$Kind$Head $colon$plus_elem = new Kinds$Kind$Head(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), order, new Some(n), alias);
      if (var10003 == null) {
         throw null;
      } else {
         Object var7 = var10003.appended($colon$plus_elem);
         $colon$plus_elem = null;
         var10000.<init>(var10002, (Seq)var7);
         return var10000;
      }
   }

   public int countByOrder(final int o) {
      return this.tokens().count((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$countByOrder$1(o, x0$1)));
   }

   public Kinds$Kind$StringState removeOnes() {
      int maxOrder = BoxesRunTime.unboxToInt(((IterableOnceOps)this.tokens().map((x0$1) -> BoxesRunTime.boxToInteger($anonfun$removeOnes$1(x0$1)))).max(scala.math.Ordering.Int..MODULE$));
      Kinds$Kind$StringState var10000 = new Kinds$Kind$StringState;
      Kinds.Kind$ var10002 = this.scala$reflect$internal$Kinds$Kind$StringState$$$outer();
      RichInt var10003 = scala.runtime.RichInt..MODULE$;
      byte var2 = 0;
      Range var12 = scala.collection.immutable.Range..MODULE$;
      Range.Inclusive var13 = new Range.Inclusive(var2, maxOrder, 1);
      Seq foldLeft_z = this.tokens();
      AbstractIterable foldLeft_this = var13;
      int var10004 = foldLeft_this.length();
      Seq foldLeft_foldl_loop$1_acc = foldLeft_z;
      int foldLeft_foldl_loop$1_end = var10004;

      for(int foldLeft_foldl_loop$1_at = 0; foldLeft_foldl_loop$1_at != foldLeft_foldl_loop$1_end; foldLeft_foldl_loop$1_at = var14) {
         var14 = foldLeft_foldl_loop$1_at + 1;
         Object var8 = foldLeft_this.apply(foldLeft_foldl_loop$1_at);
         foldLeft_foldl_loop$1_acc = $anonfun$removeOnes$2(this, foldLeft_foldl_loop$1_acc, BoxesRunTime.unboxToInt(var8));
         foldLeft_foldl_loop$1_end = foldLeft_foldl_loop$1_end;
      }

      Seq var15 = foldLeft_foldl_loop$1_acc;
      Object var11 = null;
      foldLeft_this = null;
      Object var10 = null;
      var10000.<init>(var10002, var15);
      return var10000;
   }

   public Kinds$Kind$StringState removeAlias() {
      return new Kinds$Kind$StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), (Seq)this.tokens().map((x0$1) -> {
         if (x0$1 instanceof Kinds$Kind$Head) {
            Kinds$Kind$Head var2 = (Kinds$Kind$Head)x0$1;
            int o = var2.order();
            Option n = var2.n();
            if (var2.alias() instanceof Some) {
               return new Kinds$Kind$Head(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), o, n, .MODULE$);
            }
         }

         return x0$1;
      }));
   }

   public Kinds$Kind$StringState copy(final Seq tokens) {
      return new Kinds$Kind$StringState(this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), tokens);
   }

   public Seq copy$default$1() {
      return this.tokens();
   }

   public String productPrefix() {
      return "StringState";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.tokens();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Kinds$Kind$StringState;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "tokens";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof Kinds$Kind$StringState && ((Kinds$Kind$StringState)x$1).scala$reflect$internal$Kinds$Kind$StringState$$$outer() == this.scala$reflect$internal$Kinds$Kind$StringState$$$outer()) {
            Kinds$Kind$StringState var2 = (Kinds$Kind$StringState)x$1;
            Seq var10000 = this.tokens();
            Seq var3 = var2.tokens();
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

         return false;
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   public Kinds.Kind$ scala$reflect$internal$Kinds$Kind$StringState$$$outer() {
      return this.$outer;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$countByOrder$1(final int o$1, final Kinds$Kind$ScalaNotation x0$1) {
      if (x0$1 instanceof Kinds$Kind$Head) {
         int var2 = ((Kinds$Kind$Head)x0$1).order();
         if (o$1 == var2) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final int $anonfun$removeOnes$1(final Kinds$Kind$ScalaNotation x0$1) {
      return x0$1 instanceof Kinds$Kind$Head ? ((Kinds$Kind$Head)x0$1).order() : 0;
   }

   // $FF: synthetic method
   public static final Seq $anonfun$removeOnes$2(final Kinds$Kind$StringState $this, final Seq ts, final int o) {
      return $this.countByOrder(o) <= 1 ? (Seq)ts.map((x0$2) -> {
         if (x0$2 instanceof Kinds$Kind$Head) {
            Kinds$Kind$Head var3 = (Kinds$Kind$Head)x0$2;
            int var4 = var3.order();
            Option a = var3.alias();
            if (o == var4) {
               return new Kinds$Kind$Head($this.scala$reflect$internal$Kinds$Kind$StringState$$$outer(), o, .MODULE$, a);
            }
         }

         return x0$2;
      }) : ts;
   }

   public Kinds$Kind$StringState(final Kinds.Kind$ $outer, final Seq tokens) {
      this.tokens = tokens;
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }

   // $FF: synthetic method
   public static final Seq $anonfun$removeOnes$2$adapted(final Kinds$Kind$StringState $this, final Seq ts, final Object o) {
      return $anonfun$removeOnes$2($this, ts, BoxesRunTime.unboxToInt(o));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
