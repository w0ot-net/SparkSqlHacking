package spire.syntax.std;

import scala.Tuple2;
import scala.math.BigInt;
import spire.math.Natural;
import spire.math.Number;
import spire.math.Number$;
import spire.math.SafeLong;
import spire.math.SafeLong$;
import spire.math.ULong$;

public final class LiteralBigIntOps$ {
   public static final LiteralBigIntOps$ MODULE$ = new LiteralBigIntOps$();

   public final BigInt $div$tilde$extension(final BigInt $this, final BigInt rhs) {
      return $this.$div(rhs);
   }

   public final BigInt pow$extension(final BigInt $this, final BigInt rhs) {
      return spire.math.package$.MODULE$.pow($this, rhs);
   }

   public final BigInt $times$times$extension(final BigInt $this, final BigInt rhs) {
      return spire.math.package$.MODULE$.pow($this, rhs);
   }

   public final SafeLong $plus$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$plus(rhs);
   }

   public final SafeLong $times$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$times(rhs);
   }

   public final SafeLong $minus$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$minus(rhs);
   }

   public final SafeLong $div$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$div(rhs);
   }

   public final SafeLong $div$tilde$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$div$tilde(rhs);
   }

   public final SafeLong $percent$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$percent(rhs);
   }

   public final Tuple2 $div$percent$extension(final BigInt $this, final SafeLong rhs) {
      return SafeLong$.MODULE$.apply($this).$div$percent(rhs);
   }

   public final BigInt $plus$extension(final BigInt $this, final Natural rhs) {
      return $this.$plus(rhs.toBigInt());
   }

   public final BigInt $times$extension(final BigInt $this, final Natural rhs) {
      return $this.$times(rhs.toBigInt());
   }

   public final BigInt $minus$extension(final BigInt $this, final Natural rhs) {
      return $this.$minus(rhs.toBigInt());
   }

   public final BigInt $div$extension(final BigInt $this, final Natural rhs) {
      return $this.$div(rhs.toBigInt());
   }

   public final BigInt $div$tilde$extension(final BigInt $this, final Natural rhs) {
      return $this.$div(rhs.toBigInt());
   }

   public final BigInt $percent$extension(final BigInt $this, final Natural rhs) {
      return $this.$percent(rhs.toBigInt());
   }

   public final Tuple2 $div$percent$extension(final BigInt $this, final Natural rhs) {
      return $this.$div$percent(rhs.toBigInt());
   }

   public final BigInt $plus$extension(final BigInt $this, final long rhs) {
      return $this.$plus(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final BigInt $times$extension(final BigInt $this, final long rhs) {
      return $this.$times(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final BigInt $minus$extension(final BigInt $this, final long rhs) {
      return $this.$minus(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final BigInt $div$extension(final BigInt $this, final long rhs) {
      return $this.$div(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final BigInt $div$tilde$extension(final BigInt $this, final long rhs) {
      return $this.$div(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final BigInt $percent$extension(final BigInt $this, final long rhs) {
      return $this.$percent(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final Tuple2 $div$percent$extension(final BigInt $this, final long rhs) {
      return $this.$div$percent(ULong$.MODULE$.toBigInt$extension(rhs));
   }

   public final Number $plus$extension(final BigInt $this, final Number rhs) {
      return Number$.MODULE$.apply($this).$plus(rhs);
   }

   public final Number $times$extension(final BigInt $this, final Number rhs) {
      return Number$.MODULE$.apply($this).$times(rhs);
   }

   public final Number $minus$extension(final BigInt $this, final Number rhs) {
      return Number$.MODULE$.apply($this).$minus(rhs);
   }

   public final Number $div$extension(final BigInt $this, final Number rhs) {
      return Number$.MODULE$.apply($this).$div(rhs);
   }

   public final Number $div$tilde$extension(final BigInt $this, final Number rhs) {
      return Number$.MODULE$.apply($this).$div(rhs);
   }

   public final Number $percent$extension(final BigInt $this, final Number rhs) {
      return (Number)Number$.MODULE$.NumberAlgebra().emod(Number$.MODULE$.apply($this), rhs);
   }

   public final Tuple2 $div$percent$extension(final BigInt $this, final Number rhs) {
      return Number$.MODULE$.NumberAlgebra().equotmod(Number$.MODULE$.apply($this), rhs);
   }

   public final int hashCode$extension(final BigInt $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final BigInt $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof LiteralBigIntOps) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               BigInt var5 = x$1 == null ? null : ((LiteralBigIntOps)x$1).lhs();
               if ($this == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if ($this.equals(var5)) {
                  break label31;
               }

               var7 = false;
               break label32;
            }

            var7 = true;
         }

         if (var7) {
            var7 = true;
            return var7;
         }
      }

      var7 = false;
      return var7;
   }

   private LiteralBigIntOps$() {
   }
}
