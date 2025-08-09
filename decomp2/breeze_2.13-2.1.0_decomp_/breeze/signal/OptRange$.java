package breeze.signal;

import scala.collection.immutable.Range;

public final class OptRange$ {
   public static final OptRange$ MODULE$ = new OptRange$();

   public OptRange.RangeOpt rangeToRangeOpt(final Range r) {
      return new OptRange.RangeOpt(r);
   }

   private OptRange$() {
   }
}
