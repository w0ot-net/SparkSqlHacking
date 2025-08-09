package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.util.Collection;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleIterator;
import shaded.parquet.it.unimi.dsi.fastutil.doubles.DoubleSpliterator;

public interface FloatCollection extends Collection, FloatIterable {
   FloatIterator iterator();

   default DoubleIterator doubleIterator() {
      return FloatIterable.super.doubleIterator();
   }

   default FloatSpliterator spliterator() {
      return FloatSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Collection)this), 320);
   }

   default DoubleSpliterator doubleSpliterator() {
      return FloatIterable.super.doubleSpliterator();
   }

   boolean add(float var1);

   boolean contains(float var1);

   boolean rem(float var1);

   /** @deprecated */
   @Deprecated
   default boolean add(Float key) {
      return this.add(key);
   }

   /** @deprecated */
   @Deprecated
   default boolean contains(Object key) {
      return key == null ? false : this.contains((Float)key);
   }

   /** @deprecated */
   @Deprecated
   default boolean remove(Object key) {
      return key == null ? false : this.rem((Float)key);
   }

   float[] toFloatArray();

   /** @deprecated */
   @Deprecated
   default float[] toFloatArray(float[] a) {
      return this.toArray(a);
   }

   float[] toArray(float[] var1);

   boolean addAll(FloatCollection var1);

   boolean containsAll(FloatCollection var1);

   boolean removeAll(FloatCollection var1);

   /** @deprecated */
   @Deprecated
   default boolean removeIf(Predicate filter) {
      return this.removeIf(filter instanceof FloatPredicate ? (FloatPredicate)filter : (key) -> filter.test(SafeMath.safeDoubleToFloat((double)key)));
   }

   default boolean removeIf(FloatPredicate filter) {
      Objects.requireNonNull(filter);
      boolean removed = false;
      FloatIterator each = this.iterator();

      while(each.hasNext()) {
         if (filter.test(each.nextFloat())) {
            each.remove();
            removed = true;
         }
      }

      return removed;
   }

   default boolean removeIf(DoublePredicate filter) {
      FloatPredicate var10001;
      if (filter instanceof FloatPredicate) {
         var10001 = (FloatPredicate)filter;
      } else {
         Objects.requireNonNull(filter);
         var10001 = filter::test;
      }

      return this.removeIf(var10001);
   }

   boolean retainAll(FloatCollection var1);

   /** @deprecated */
   @Deprecated
   default Stream stream() {
      return super.stream();
   }

   default DoubleStream doubleStream() {
      return StreamSupport.doubleStream(this.doubleSpliterator(), false);
   }

   /** @deprecated */
   @Deprecated
   default Stream parallelStream() {
      return super.parallelStream();
   }

   default DoubleStream doubleParallelStream() {
      return StreamSupport.doubleStream(this.doubleSpliterator(), true);
   }
}
