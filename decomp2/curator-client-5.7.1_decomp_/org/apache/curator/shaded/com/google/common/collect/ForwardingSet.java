package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSet extends ForwardingCollection implements Set {
   protected ForwardingSet() {
   }

   protected abstract Set delegate();

   public boolean equals(@CheckForNull Object object) {
      return object == this || this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }

   protected boolean standardRemoveAll(Collection collection) {
      return Sets.removeAllImpl(this, (Collection)((Collection)Preconditions.checkNotNull(collection)));
   }

   protected boolean standardEquals(@CheckForNull Object object) {
      return Sets.equalsImpl(this, object);
   }

   protected int standardHashCode() {
      return Sets.hashCodeImpl(this);
   }
}
