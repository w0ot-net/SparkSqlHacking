package org.apache.curator.shaded.com.google.common.collect;

import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

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
}
