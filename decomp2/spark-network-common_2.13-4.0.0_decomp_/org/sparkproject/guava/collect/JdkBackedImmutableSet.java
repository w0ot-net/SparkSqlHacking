package org.sparkproject.guava.collect;

import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
final class JdkBackedImmutableSet extends IndexedImmutableSet {
   private final Set delegate;
   private final ImmutableList delegateList;

   JdkBackedImmutableSet(Set delegate, ImmutableList delegateList) {
      this.delegate = delegate;
      this.delegateList = delegateList;
   }

   Object get(int index) {
      return this.delegateList.get(index);
   }

   public boolean contains(@CheckForNull Object object) {
      return this.delegate.contains(object);
   }

   boolean isPartialView() {
      return false;
   }

   public int size() {
      return this.delegateList.size();
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
