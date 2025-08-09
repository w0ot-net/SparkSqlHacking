package breeze.linalg.support;

import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Range;
import scala.package.;

public final class RangeExtender$ {
   public static final RangeExtender$ MODULE$ = new RangeExtender$();
   private static final Range.Exclusive All;

   static {
      All = .MODULE$.Range().apply(0, -1, 1);
   }

   public Range.Exclusive All() {
      return All;
   }

   public final Range getRangeWithoutNegativeIndexes$extension(final Range $this, final int totalLength) {
      Range var10000;
      if ($this.isInclusive()) {
         Tuple2.mcII.sp var5 = new Tuple2.mcII.sp($this.start() < 0 ? totalLength + $this.start() : $this.start(), $this.end() < 0 ? totalLength + $this.end() : $this.end());
         if (var5 == null) {
            throw new MatchError(var5);
         }

         int actualStart = ((Tuple2)var5)._1$mcI$sp();
         int actualEnd = ((Tuple2)var5)._2$mcI$sp();
         if (false || false) {
            throw new MatchError(var5);
         }

         Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(actualStart, actualEnd);
         int actualStart = ((Tuple2)var3)._1$mcI$sp();
         int actualEnd = ((Tuple2)var3)._2$mcI$sp();
         var10000 = scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(actualStart), actualEnd).by($this.step());
      } else {
         if ($this.end() < 0 || $this.start() < 0) {
            throw new IllegalArgumentException("cannot use negative end indexing with 'until', due to ambiguities from Range.end being exclusive");
         }

         var10000 = $this;
      }

      return var10000;
   }

   public final int hashCode$extension(final Range $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Range $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof RangeExtender) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               Range var5 = x$1 == null ? null : ((RangeExtender)x$1).re();
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

   private RangeExtender$() {
   }
}
