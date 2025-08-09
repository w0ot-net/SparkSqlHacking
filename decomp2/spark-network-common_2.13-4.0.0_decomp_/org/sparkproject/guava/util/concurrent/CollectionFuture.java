package org.sparkproject.guava.util.concurrent;

import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.collect.ImmutableCollection;
import org.sparkproject.guava.collect.Lists;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class CollectionFuture extends AggregateFuture {
   @CheckForNull
   @LazyInit
   private List values;

   CollectionFuture(ImmutableCollection futures, boolean allMustSucceed) {
      super(futures, allMustSucceed, true);
      List<Present<V>> values = (List<Present<V>>)(futures.isEmpty() ? Collections.emptyList() : Lists.newArrayListWithCapacity(futures.size()));

      for(int i = 0; i < futures.size(); ++i) {
         values.add((Object)null);
      }

      this.values = values;
   }

   final void collectOneValue(int index, @ParametricNullness Object returnValue) {
      List<Present<V>> localValues = this.values;
      if (localValues != null) {
         localValues.set(index, new Present(returnValue));
      }

   }

   final void handleAllCompleted() {
      List<Present<V>> localValues = this.values;
      if (localValues != null) {
         this.set(this.combine(localValues));
      }

   }

   void releaseResources(AggregateFuture.ReleaseResourcesReason reason) {
      super.releaseResources(reason);
      this.values = null;
   }

   abstract Object combine(List values);

   static final class ListFuture extends CollectionFuture {
      ListFuture(ImmutableCollection futures, boolean allMustSucceed) {
         super(futures, allMustSucceed);
         this.init();
      }

      public List combine(List values) {
         List<V> result = Lists.newArrayListWithCapacity(values.size());

         for(Present element : values) {
            result.add(element != null ? element.value : null);
         }

         return Collections.unmodifiableList(result);
      }
   }

   private static final class Present {
      @ParametricNullness
      final Object value;

      Present(@ParametricNullness Object value) {
         this.value = value;
      }
   }
}
