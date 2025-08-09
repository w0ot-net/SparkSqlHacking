package scala.collection.immutable;

import java.io.Serializable;
import java.util.NoSuchElementException;
import scala.runtime.ModuleSerializationProxy;

public final class Range$ implements Serializable {
   public static final Range$ MODULE$ = new Range$();

   public int count(final int start, final int end, final int step, final boolean isInclusive) {
      if (step == 0) {
         throw new IllegalArgumentException("step cannot be 0.");
      } else if (start == end ? !isInclusive : (start < end ? step < 0 : step > 0)) {
         return 0;
      } else {
         long gap = (long)end - (long)start;
         long jumps = gap / (long)step;
         boolean hasStub = isInclusive || gap % (long)step != 0L;
         long result = jumps + (long)(hasStub ? 1 : 0);
         return result > 2147483647L ? -1 : (int)result;
      }
   }

   public int count(final int start, final int end, final int step) {
      return this.count(start, end, step, false);
   }

   public Range.Exclusive apply(final int start, final int end, final int step) {
      return new Range.Exclusive(start, end, step);
   }

   public Range.Exclusive apply(final int start, final int end) {
      return new Range.Exclusive(start, end, 1);
   }

   public Range.Inclusive inclusive(final int start, final int end, final int step) {
      return new Range.Inclusive(start, end, step);
   }

   public Range.Inclusive inclusive(final int start, final int end) {
      return new Range.Inclusive(start, end, 1);
   }

   public Throwable scala$collection$immutable$Range$$emptyRangeError(final String what) {
      return new NoSuchElementException((new StringBuilder(15)).append(what).append(" on empty Range").toString());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Range$.class);
   }

   private Range$() {
   }
}
